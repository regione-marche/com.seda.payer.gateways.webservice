package com.seda.payer.gateways.facade.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.emailsender.webservices.dati.EMailSenderRequestType;
import com.seda.emailsender.webservices.dati.EMailSenderResponse;
import com.seda.emailsender.webservices.source.EMailSenderInterface;
import com.seda.payer.commons.utility.DateUtility;
import com.seda.payer.commons.utility.FileUtility;
import com.seda.payer.commons.utility.StringUtility;
import com.seda.payer.core.bean.Aea;
import com.seda.payer.core.dao.RidDao;
import com.seda.payer.gateways.facade.bean.GatewayPagamentoBean;
import com.seda.payer.gateways.facade.dto.GatewayPagamentoDto;
import com.seda.payer.gateways.facade.handler.PropertiesPath;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneCBIInsolutiRIDRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneCBIInsolutiRIDResponse;
import com.seda.payer.pgec.webservice.commons.dati.RecuperaTransazioneBancaRequest;
import com.seda.payer.pgec.webservice.commons.dati.RecuperaTransazioneBancaResponse;
import com.seda.payer.pgec.webservice.commons.dati.TransazioniBeanTransazioni;
import com.seda.payer.pgec.webservice.commons.source.CommonsSOAPBindingStub;
import com.seda.payer.pgec.webservice.commons.source.CommonsServiceLocator;

public class Generics_RID_AllineaCBI {

	public final static String DBSCHEMACODSOCIETA = "dbSchemaCodSocieta";
	
	private static String flowCBIInsolutiPath;
	private static String flowCBIInsolutiInProgressPath;
	private static String flowCBIInsolutiProcessedPath;
	private static String flowCBIInsolutiRejectedPath;
	private static GatewayPagamentoBean facadeBean;
	
	private static void initConfig(PropertiesTree propertiesTree, GatewayPagamentoBean facade)
	{
		// per la gestione RID: flussi CBI per insoluti RID
		flowCBIInsolutiPath = propertiesTree.getProperty(PropertiesPath.FLOW_CBIINSOLUTI_PATH.format(PropertiesPath.baseCatalogName.format()));
		flowCBIInsolutiInProgressPath = propertiesTree.getProperty(PropertiesPath.FLOW_CBIINSOLUTI_IN_PROGRESS_PATH.format(PropertiesPath.baseCatalogName.format()));
		flowCBIInsolutiProcessedPath = propertiesTree.getProperty(PropertiesPath.FLOW_CBIINSOLUTI_PROCESSED_PATH.format(PropertiesPath.baseCatalogName.format()));
		flowCBIInsolutiRejectedPath = propertiesTree.getProperty(PropertiesPath.FLOW_CBIINSOLUTI_REJECTED_PATH.format(PropertiesPath.baseCatalogName.format()));
		
		facadeBean = facade;
	
	}
	
	@SuppressWarnings("unchecked")
	public static String[] manageAllineaCBI_FromFile(GatewayPagamentoBean facadeBean, 
			PropertiesTree propertiesTree, LoggerWrapper logger, StringBuffer testoMail, StringBuffer oggettoMail, String dbSchemaCodSocieta)
	{
		testoMail.append("Elaborazione flussi CBI (insoluti RID)<br>");
		
		initConfig(propertiesTree, facadeBean);
		String[] sRes = new String[2];
		int fileTotali = 0;
		int fileProcessatiOk = 0;
		try 
		{
			testoMail.append("Elaborazione dei file nella cartella " + flowCBIInsolutiPath + "<br>");
			logger.info("Elaborazione flussi CBI (insoluti RID) in: " + flowCBIInsolutiPath);
	    	
			// ricerco dal path ftp tutti i flussi CBI presenti  
			TreeSet treeSet = FileUtility.find(flowCBIInsolutiPath, Pattern.compile("^(.*?)[\\|/]*.(txt)"), FileUtility.ORDER_ASC);
			fileTotali = treeSet.size();
			testoMail.append("Trovati " + fileTotali + " file<br><br>"); 
			testoMail.append("");
			Iterator iter = treeSet.iterator();
			boolean isWasAligned = true;
			boolean isWasAlignedWithNoErrors = true;
			if (iter != null && !treeSet.isEmpty()) 
			{
				// ciclo for su tutti i RnInca trovati			    
				while (iter.hasNext()) 
				{
					File f = (File) iter.next();
					// we define fileNameToElab & pathFullFileToElab
					String fileNameToElab = f.getName().substring(0, f.getName().indexOf(".")) + ".txt";
					String pathFullFileToElab = flowCBIInsolutiInProgressPath + fileNameToElab;
					
					testoMail.append("Elaborazione del file " + fileNameToElab + "<br>");
					// we move file into progress folder
					Generics_RID.moveFile(f, flowCBIInsolutiInProgressPath);
					logger.info("Allineamento CBI insoluti RID process.FILE - " + pathFullFileToElab);
					boolean bParseOk = false;
					try {
						bParseOk = parseFileCBI(pathFullFileToElab, testoMail, logger);
						
						if (bParseOk)
						{
							//allineamento transazione
							isWasAlignedWithNoErrors &= allineaTransazioniCBI_InsolutiRID(pathFullFileToElab, fileNameToElab, logger, propertiesTree, testoMail, dbSchemaCodSocieta);
						}
					}
					catch (Exception e)
					{
						// we move file into rejected folder
						Generics_RID.moveFile(new File(pathFullFileToElab), flowCBIInsolutiRejectedPath);
						testoMail.append("File scartato<br><br>");
						
						e.printStackTrace();
						logger.error("Allineamento CBI insoluti RID - " + e.getMessage());
						sRes[0] = "03";
						sRes[1] = "Processo di allineamento CBI (insoluti RID) fallito";
						return sRes;
					}

					if (bParseOk) {
						// we move file into processed folder
						Generics_RID.moveFile(new File(pathFullFileToElab), flowCBIInsolutiProcessedPath);
						logger.info("AllineaCBI (Insoluti RID) file:"+pathFullFileToElab+" Processed move on folder:"+flowCBIInsolutiProcessedPath);
						testoMail.append("File processato<br><br>");
						fileProcessatiOk++;
					} else {
						// we move file into rejected folder
						Generics_RID.moveFile(new File(pathFullFileToElab), flowCBIInsolutiRejectedPath);
						logger.info("AllineaCBI (Insoluti RID) file:"+pathFullFileToElab+" Rejected move on folder:"+flowCBIInsolutiRejectedPath);
						testoMail.append("File scartato<br><br>");
					}
					
					isWasAligned &= bParseOk;
				}
				
				if (isWasAligned)
				{
					if (isWasAlignedWithNoErrors)
					{
						//allineamento ok
						sRes[0] = "00";
						sRes[1] = "Allineamento CBI (Insoluti RID) OK";
					}
					else
					{
						//nessun flusso scartato ma errore nell'elaborazione di uno o più record
						sRes[0] = "01";
						sRes[1] = "Allineamento CBI (Insoluti RID) eseguito con errori nell'elaborazione di uno o più record. Nessun flusso scartato.";
					}
				}
				else
				{	
					if (isWasAlignedWithNoErrors)
					{
						//errore di parse in uno o più flussi
						sRes[0] = "02";
						sRes[1] = "Errore nell'allineamento CBI (Insoluti RID). Uno o più flussi scartati. I flussi non scartati sono stati elaborati correttamente.";
					}
					else
					{
						//errore di parse in uno o più flussi ed errore di elaborazione nei flussi non scartati
						//errore di parse in uno o più flussi
						sRes[0] = "03";
						sRes[1] = "Errore nell'allineamento  CBI (Insoluti RID). Uno o più flussi scartati ed errori nell'elaborazione di uno o più record dei flussi non scartati.";
					}
				}
				
				testoMail.append("Processo terminato<br><br>");
				appendReportMail(testoMail, fileTotali, fileProcessatiOk, oggettoMail);
				
				return sRes;
			}
			
			logger.info("Nessun flusso CBI da elaborare");
			sRes[0] = "04";
			sRes[1] = "Nessun flusso CBI da elaborare";
			
			testoMail.append("Processo terminato<br><br>");
			appendReportMail(testoMail, fileTotali, fileProcessatiOk, oggettoMail);
			return sRes;
			
		} catch (Exception e) {
			logger.error("Allineamento CBI insoluti RID failed, generic error due to: ", e);
			sRes[0] = "05";
			sRes[1] = "Processo di allineamento CBI (insoluti RID) fallito";
			testoMail.append("Processo di allineamento CBI (insoluti RID) fallito per errore generico<br><br>");
			appendReportMail(testoMail, fileTotali, fileProcessatiOk, oggettoMail);
			return sRes;
		}
	}
	
	/**
	 * @param pathFullFileToElab
	 * @param testoMail
	 * @param logger
	 * @return
	 */
	/**
	 * @param pathFullFileToElab
	 * @param testoMail
	 * @param logger
	 * @return
	 */
	private static boolean parseFileCBI(String pathFullFileToElab, StringBuffer testoMail, LoggerWrapper logger)
	{
		StringBuffer testoAnomalie = new StringBuffer();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(pathFullFileToElab);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
		
			List<String> righe = new ArrayList<String>();
			String linea = br.readLine();
			while (linea != null) 
			{
				righe.add(linea);
				linea = br.readLine();
			}
			if (righe.size() == 0)
				testoAnomalie.append("- il file è vuoto<br>");
			else if (righe.size() == 1)
				testoAnomalie.append("- il file non è formalmente corretto<br>");
			else if (righe.size() >= 2)
			{
				//ho almeno il record di testa e di coda: li verifico
				String recordTesta = righe.get(0);
				
				//controllo IR nel record di testa
				if (!recordTesta.substring(1,3).equals("IR"))
					testoAnomalie.append("- record di testa non valido: codice 'IR' non presente<br>");
				
				String recordCoda = righe.get(righe.size() - 1);
				//controllo EF nel record di testa
				if (!recordCoda.substring(1,3).equals("EF"))
					testoAnomalie.append("- record di coda non valido: codice 'EF' non presente<br>");
				
				//controllo numero disposizioni
				long numeroDisposizioni = 0;
				if (!isNumber(recordCoda.substring(45,52)))
					testoAnomalie.append("- il numero delle disposizioni nel record di coda deve essere un numero intero<br>");
				else
					numeroDisposizioni = Long.parseLong(recordCoda.substring(45,52));
				
				//controllo numero record
				long numeroRecord = 0;
				if (!isNumber(recordCoda.substring(82,89)))
					testoAnomalie.append("- il numero totale dei record definito nel record di coda deve essere un numero intero<br>");
				else 
				{
					numeroRecord = Long.parseLong(recordCoda.substring(82,89));
					if (numeroRecord != righe.size())
						testoAnomalie.append("- il numero di record nel flusso è " + righe.size() + 
										" e non " + numeroRecord + " come specificato nel record di coda<br>");
				}
				
				//importo
				long importoTotale = 0;
				if (!isNumber(recordCoda.substring(52,67)))
					testoAnomalie.append("- l'importo definito nel record di coda deve essere un numero intero<br>");
				else
					importoTotale = Long.parseLong(recordCoda.substring(52,67));
				
				
				//se sono andati bene i controlli preliminari di testata e coda proseguo
				if (testoAnomalie.length() == 0)
				{
					if (righe.size() == 2)
					{
						testoAnomalie.append("- non ci sono record da elaborare<br>");
						if (numeroDisposizioni > 0)
							testoAnomalie.append("- il numero delle disposizioni nel flusso è 0 e non " + numeroDisposizioni + 
									" come specificato nel record di coda<br>");
					}
					else
					{
						//definisco un hashmap che ha come chiave il numero disposizione 
						//e come valore la lista dei record della disposizione
						SortedMap<Long, List<String>> hmDisposizioni = new TreeMap<Long, List<String>>();
						
						//leggo la prima riga
						long disposizionePrec = 0;
						for (int i=1; i<righe.size() - 1; i++)
						{
							String riga = righe.get(i);
							if (checkDisposizione(riga, testoAnomalie))
							{
								long disposizione = Long.parseLong(riga.substring(3,10));
								if (hmDisposizioni.containsKey(disposizione))
								{
									if (disposizione != disposizionePrec)
									{
										//se la disposizione corrente è diversa dalla precedente
										//ma è già presente nel'hashmpap, vuol dire che è duplicata
										testoAnomalie.append("- disposizione numero " + disposizione + " duplicata. Non inclusa nel conteggio delle disposizioni.<br>");
									}
									else
									{
										//aggiorno la List<String> della disposizione corrente
										List<String> listRighe = hmDisposizioni.get(disposizione);
										listRighe.add(riga);
										hmDisposizioni.put(disposizione, listRighe);
									}
								}
								else
								{
									List<String> listRighe = new ArrayList<String>();
									listRighe.add(riga);
									hmDisposizioni.put(disposizione, listRighe);
									if (disposizione != disposizionePrec)
										disposizionePrec = disposizione;
								}
							}
						}
												
						//controllo numero totale disposizioni se corrisponde al record di coda
						if (hmDisposizioni.size() != numeroDisposizioni)
							testoAnomalie.append("- il numero delle disposizioni nel flusso è " + hmDisposizioni.size() + 
									" e non " + numeroDisposizioni + " come specificato nel record di coda<br>");
						
						long importoCalcolato = 0;
						//per ogni disposizione verifico che ci siano almeno il record 10 e il 70 e che non siano duplicati
						for (long disp : hmDisposizioni.keySet())
						{
							boolean b10 = false;
							boolean b70 = false;
							for (String record : hmDisposizioni.get(disp))
							{
								String tipoRecord = record.substring(1,3);
								if (tipoRecord.equals("10"))
								{
									if (!b10)
										b10 = true;
									else
										testoAnomalie.append("- tipo record 10 duplicato per la disposizione numero " + disp + "<br>");
									
									if (!isNumber(record.substring(33, 46)))
										testoAnomalie.append("- l'importo specificato per la disposizione numero " + disp + " deve essere un numero intero<br>");
									else
										importoCalcolato += Long.parseLong(record.substring(33, 46));
								}
								else if (tipoRecord.equals("70"))
								{
									if (!b70)
										b70 = true;
									else
										testoAnomalie.append("- tipo record 70 duplicato per la disposizione numero " + disp + "<br>");
								}
							}
							if (!b10)
								testoAnomalie.append("- tipo record 10 mancante per la disposizione numero " + disp + "<br>");
							if (!b70)
								testoAnomalie.append("- tipo record 70 mancante per la disposizione numero " + disp + "<br>");
						}
						
						//verifico che l'importo calcolato corrisponda a quello indicato nel record di coda
						if (importoCalcolato != importoTotale)
							testoAnomalie.append("- l'importo calcolato è " + formatDecimalNumberDivideBy100(importoCalcolato) + 
									" e non " + formatDecimalNumberDivideBy100(importoTotale) + " come specificato nel record di coda<br>");
					}
				}
			}
				
				
			if (testoAnomalie.length() > 0)
			{
				testoMail.append("Il flusso presenta le seguenti anomalie:<br>");
				testoMail.append(testoAnomalie.toString());
				return false;
			}
			else
				return true;
		} 
		catch (Exception e) 
		{
			logger.error("Errore parseFileCBI: ", e);
			testoMail.append("Errore generico durante il parse del file<br><br>");
			return false;
		} finally {
			//inizio LP PG21XX04 Leak
			//try { 
			//	if (br != null) br.close(); 
			//	if (isr != null) isr.close(); 
			//	if (fis != null) fis.close();
			//} catch (Exception ignore) {  }
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				br = null;
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				isr = null;
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	private static void appendReportMail(StringBuffer testoMail, int fileTotali, int fileProcessatiOk, StringBuffer oggettoMail)
	{
		String oggettoMailStr = "Elaborazione flussi CBI (insoluti RID)";
		int fileScartati = fileTotali - fileProcessatiOk;
		if (fileScartati > 0)
			oggettoMailStr += " con file SCARTATI. Elaborati: " + fileTotali + " - Scartati: " + fileScartati;
		else
			oggettoMailStr += ". Elaborati: " + fileTotali;
		oggettoMail.append(oggettoMailStr);
		
		testoMail.append("--------------- REPORT ---------------<br>"); 
		testoMail.append("NUMERO FILE = " + fileTotali + "<br>");
		testoMail.append("NUMERO FILE SCARTATI = " + fileScartati + "<br>");
		testoMail.append("NUMERO FILE PROCESSATI = " + fileProcessatiOk + "<br>");
	}
	
	public static boolean allineaTransazioniCBI_InsolutiRID(String nextFlow, String nomeFile, LoggerWrapper logger, PropertiesTree propertiesTree, StringBuffer testoMail, String dbSchemaCodSocieta) throws Exception {
		//inizio LP PG21XX04 Leak
		//FileInputStream fis = new FileInputStream(nextFlow);
		//InputStreamReader isr = new InputStreamReader(fis);
		//BufferedReader br = new BufferedReader(isr);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		//fine LP PG21XX04 Leak
		int recordTotali = 0;
		int recordOk = 0;
		try {
			//inizio LP PG21XX04 Leak
			fis = new FileInputStream(nextFlow);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			//fine LP PG21XX04 Leak
			String linea = br.readLine();
			String dataCreazione = null;		/* Record IR (14-19 GGMMAA)*/
			String causale = null;				/* Record 10 (29-33)*/
			String idOperazione = null; 		/* Record 70 (11-25)*/
			
			boolean isWasAlligned = false;
			while (linea != null) {
				try {
					String typeRecord = linea.substring(1, 3);
					// we skip blank line
					if (StringUtility.isEmpty(typeRecord.replaceAll(" ", ""))) {
						linea = br.readLine();
						continue;
					}
					if (typeRecord.equalsIgnoreCase("IR")) {
						dataCreazione = linea.substring(13, 19);
						idOperazione = null;
						causale = null;
						logger.info("DataCreazione := " + dataCreazione);
						isWasAlligned = true;
					} else if (typeRecord.equalsIgnoreCase("10")) {
						causale = linea.substring(28, 33);
						logger.info("\tCausale := " + causale);
					} else if (typeRecord.equalsIgnoreCase("70")) {
						recordTotali++;
						idOperazione = linea.substring(10, 25);
						logger.info("\tIdOperazione := " + idOperazione);
						logger.info("\tAllineamento transazione (insoluto RID) in corso...");
						
						// we align transaction
						boolean bSingleRes = allineaTransazioneCBI_InsolutiRID(nomeFile, dataCreazione, idOperazione, causale, propertiesTree, logger, dbSchemaCodSocieta);
						if (bSingleRes) 
							recordOk++;
						
						isWasAlligned &= bSingleRes;
					} 
				} catch (Exception e) {
					isWasAlligned = false;
				}
				
				linea = br.readLine();
			}
			if (recordTotali == 0)
				testoMail.append("Nessun record da elaborare<br>");
			else
				testoMail.append("Record elaborati correttamente: " + recordOk + " su " + recordTotali + "<br>");
			
			return isWasAlligned;
		} catch (Exception e) {
			logger.error("Errore generico allineaTransazioniCBI_InsolutiRID: ", e);
			return false;
		} finally {
			//inizio LP PG21XX04 Leak
			//try { 
			//	if (br != null) br.close(); 
			//	if (isr != null) isr.close(); 
			//	if (fis != null) fis.close();
			//} catch (Exception ignore) {  }
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				br = null;
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				isr = null;
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public static boolean allineaTransazioneCBI_InsolutiRID(String nomeFlusso, String dataCreazione, String idOperazione, String causale, PropertiesTree propertiesTree, LoggerWrapper logger, String dbSchemaCodSocieta) 
	{
		String emailNotificaAdmin = propertiesTree.getProperty(PropertiesPath.emailAdmin.format());
		
		try {
			// we initialize commons manager
			CommonsSOAPBindingStub commonsService = getCommonsService(propertiesTree, dbSchemaCodSocieta);
			// recupero la transazione a partire dall'idoperazione passato dal flusso CBI, con tipo gateway = RID
			TransazioniBeanTransazioni beanTransazioni = null;
			try { 
				RecuperaTransazioneBancaRequest in = new RecuperaTransazioneBancaRequest(idOperazione, idOperazione, "R"); 
				RecuperaTransazioneBancaResponse res = commonsService.recuperaTransazioneBanca(in);
				if (res != null && res.getResponse().getRetCode().getValue().equals("00"))
					beanTransazioni = res.getBeanTransazioni().getBeanTransazioni();
				if (beanTransazioni == null)
				{
					final String errMsg = "Errore durante la ricerca della transazione nel sistema PayER.<br><br>" + 
					"Nome flusso CBI: " + nomeFlusso + "<br>" +
					"ID operazione Infogroup: " + idOperazione; 
					logger.warn(errMsg.replaceAll("<br>", "-"));
					// we send mail notify error
					notificaErroreAllineamentoCBI_InsolutiRID(errMsg, logger, propertiesTree, emailNotificaAdmin,dbSchemaCodSocieta);
					return false;
				}
					
			} catch (Exception e) {
				final String errMsg = "Transazione inesistente nel sistema PayER.<br><br>" + 
								"Nome flusso CBI: " + nomeFlusso + "<br>" +
								"ID operazione Infogroup: " + idOperazione; 
				logger.warn(errMsg.replaceAll("<br>", "-"));
				// we send mail notify error
				notificaErroreAllineamentoCBI_InsolutiRID(errMsg, logger, propertiesTree, emailNotificaAdmin, dbSchemaCodSocieta);
				return false;
			}
			
			//La transazione è stata trovata
			String chiaveTransazione = beanTransazioni.getChiave_transazione();
			//recupero eventuali note già presenti dell'RNINCATRANS per concatenare le info sul CBI
			String note = beanTransazioni.getNote_generiche();
			//Recupero le informazioni del gateway di pagamento per recuperare l'email di notifica dell'amministratore.
			GatewayPagamentoDto gateway = facadeBean.getGatewayPagamento(beanTransazioni.getChiave_gateway_di_pagamento(), dbSchemaCodSocieta);			
			emailNotificaAdmin = gateway.getEmailNotificaAdmin();
			
			// we update transaction
			boolean isWasAlligned = false;
			
			try 
			{ 
				
				note += (!note.equals("") ? " --- " : "") + "CBI (Insoluto RID): " + nomeFlusso + " (Cod. Causale: " + causale + ")";
				Calendar calDataCreazione = Calendar.getInstance();
				calDataCreazione.setTime(DateUtility.parse("ddMMyy", dataCreazione));
				AggiornaTransazioneCBIInsolutiRIDRequest req = new AggiornaTransazioneCBIInsolutiRIDRequest(
															chiaveTransazione,
															calDataCreazione,
															note, 
															"2"); //esito insoluto RID = 2 (negativo)
				AggiornaTransazioneCBIInsolutiRIDResponse res = commonsService.aggiornaTransazioneCBIInsolutiRID(req);
					
				isWasAlligned = (res != null && res.getResponse().getRetCode().getValue().equals("00"));
				
				if (isWasAlligned)
				{
					//se è andato tutto bene, notifico al contribuente
					logger.info("La transazione è stata allineata correttamente. " +
							" Nome flusso CBI: " + nomeFlusso + 
							" ID operazione Infogroup: " + idOperazione +   
							" ID transazione PayER: " + chiaveTransazione);
					
					boolean bNotifica = notificaAllineamentoCBI_InsolutiRID(chiaveTransazione, causale, propertiesTree, logger, dbSchemaCodSocieta);
					if (!bNotifica)
					{
						String errMsg = "Errore nella notifica dell'allineamento della transazione.<br><br>" +
						"Nome flusso CBI: " + nomeFlusso + "<br>" +
						"ID operazione Infogroup: " + idOperazione + "<br>" +  
						"ID transazione PayER: " + chiaveTransazione + "<br>";
						logger.warn(errMsg.replaceAll("<br>", "-"));
						notificaErroreAllineamentoCBI_InsolutiRID(errMsg, logger, propertiesTree, emailNotificaAdmin,dbSchemaCodSocieta);
						return false;
					}
				}
				else
				{
					String errMsg = "Errore nell'aggiornamento della transazione.<br><br>" +
					"Nome flusso CBI: " + nomeFlusso + "<br>" +
					"ID operazione Infogroup: " + idOperazione + "<br>" +  
					"ID transazione PayER: " + chiaveTransazione + "<br>" +
					(res != null ? "Messaggio Errore: " + res.getResponse().getRetMessage() : ""); 
					logger.warn(errMsg.replaceAll("<br>", "-"));
					notificaErroreAllineamentoCBI_InsolutiRID(errMsg, logger, propertiesTree, emailNotificaAdmin,dbSchemaCodSocieta);
					return false;
				}
				
				
				return isWasAlligned;
			} 
			catch (Exception e) 
			{
				String errMsg = "Errore nell'aggiornamento della transazione.<br><br>" +
								"Nome flusso CBI: " + nomeFlusso + "<br>" +
								"ID operazione Infogroup: " + idOperazione + "<br>" +  
								"ID transazione PayER: " + chiaveTransazione + "<br>" +
								"Messaggio Errore: " + e.getMessage(); 
				logger.warn(errMsg.replaceAll("<br>", "-"), e);
				notificaErroreAllineamentoCBI_InsolutiRID(errMsg, logger, propertiesTree, emailNotificaAdmin,dbSchemaCodSocieta);
				return false;
			}


		} catch (Exception e) {
			String errMsg = "Errore Allineamento Transazione CBI - InsolutiRID: " + e.getMessage();
			logger.error(errMsg, e);
			notificaErroreAllineamentoCBI_InsolutiRID(errMsg, logger, propertiesTree, emailNotificaAdmin,dbSchemaCodSocieta);
			return false;
		}
	}
	
	private static boolean notificaAllineamentoCBI_InsolutiRID(String chiaveTransazione, String codCausale, PropertiesTree propertiesTree, LoggerWrapper logger, String dbSchemaCodSocieta)
	{
		try 
		{ 
			String descrizioneCausaleAEA_CBI = "";
			
			Connection conn=null;
	    	try {
	    		conn=facadeBean.getConnection(dbSchemaCodSocieta);
	    		RidDao dao = new RidDao(conn, facadeBean.getSchema(dbSchemaCodSocieta));
	    		Aea aea = dao.doDetailAea(codCausale);
	    		if (aea != null)
	    			descrizioneCausaleAEA_CBI = aea.getDescrizioneCausale();
	    	} catch (Exception e) { }
			//inizio LP PG21XX04 Leak
			finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			Generics_RID_Mail.notificaRID_CBI(  
					chiaveTransazione, codCausale, descrizioneCausaleAEA_CBI,
    				propertiesTree, logger, dbSchemaCodSocieta);	
			
			return true;
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @param message
	 * @param log
	 * @throws Exception
	 */
	public static void notificaErroreAllineamentoCBI_InsolutiRID(final String message, final LoggerWrapper logger, final PropertiesTree propertiesTree, final String emailNotificaAdmin, final String cutecute) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					EMailSenderInterface mailSender = GatewayPagamentoBean.getEmailSenderService(propertiesTree);
					
					EMailSenderRequestType req = new EMailSenderRequestType(
							emailNotificaAdmin,
							"","","", 
							propertiesTree.getProperty(PropertiesPath.emailSubject.format()) + " (Insoluto RID)", 
							message,
							"",cutecute);
					
					EMailSenderResponse response = mailSender.getEMailSender(req);				
					
					logger.info("notificaMancatoAllineamentoCBI_InsolutiRID - emailSender response - " + response.getValue());
				} catch (Exception e) {
					logger.warn("notificaMancatoAllineamentoCBI_InsolutiRID failed, generic error due to: ", e);
				}
			}
		};
		thread.start();
	}
	
	
	private static CommonsSOAPBindingStub getCommonsService(PropertiesTree propertiesTree, String dbSchemaCodSocieta)
	{
		CommonsSOAPBindingStub commonsService = null;
		CommonsServiceLocator commonsServiceLocator = new CommonsServiceLocator();
		try {
			commonsService = (CommonsSOAPBindingStub)commonsServiceLocator.getCommonsPort(
					new URL(propertiesTree.getProperty(PropertiesPath.wsCommonsUrl.format(PropertiesPath.baseCatalogName.format()))));
			
			commonsService.clearHeaders();
			commonsService.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);
			return commonsService;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static boolean isNumber(String valore)
	{
		if (valore.trim().equals(""))
			return false;
		
		try { Long.parseLong(valore); }
		catch(Exception e) { return false; }
		
		return true;
	}
	
	private static boolean checkDisposizione(String riga, StringBuffer testoAnomalie)
	{
		if (!isNumber(riga.substring(3,10)))
		{
			testoAnomalie.append("- il numero della disposizione alla riga " + riga + " deve esser eun numeor intero<br>");
			return false;
		}
		return true;
	}
	
	private static String formatDecimalNumberDivideBy100(long valore)
	{
		BigDecimal bdValue = new BigDecimal(valore);
		bdValue = bdValue.divide(new BigDecimal(100));
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(); 
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');

		DecimalFormat dcFormat = new DecimalFormat("#0.00", symbols);
		bdValue = bdValue.setScale(2, BigDecimal.ROUND_HALF_UP);
			
		return dcFormat.format(bdValue);
		
	}
	
	
	
		
}
