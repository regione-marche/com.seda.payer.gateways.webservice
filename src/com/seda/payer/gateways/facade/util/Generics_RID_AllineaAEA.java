package com.seda.payer.gateways.facade.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;



import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.emailsender.webservices.dati.EMailSenderRequestType;
import com.seda.emailsender.webservices.dati.EMailSenderResponse;
import com.seda.emailsender.webservices.source.EMailSenderInterface;
import com.seda.payer.commons.utility.FileUtility;
import com.seda.payer.core.bean.Rid;
import com.seda.payer.core.dao.RidDao;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.gateways.facade.bean.GatewayPagamentoBean;
import com.seda.payer.gateways.facade.dto.RidDto;
import com.seda.payer.gateways.facade.exception.FacadeException;
import com.seda.payer.gateways.facade.handler.PropertiesPath;

public class Generics_RID_AllineaAEA {
	
	private static String flowRidPath;
	private static String flowRidInProgressPath;
	private static String flowRidProcessedPath;
	private static String flowRidRejectedPath;
	
	/**
	 * precarica i file di configurazione
	 * @param propertiesTree
	 */
	private static void initConfig(PropertiesTree propertiesTree)
	{
		// per la gestione del RID: flussi AEA per adesioni/revoche RID 
		flowRidPath = propertiesTree.getProperty(PropertiesPath.FLOW_RID_PATH.format(PropertiesPath.baseCatalogName.format()));
		flowRidInProgressPath = propertiesTree.getProperty(PropertiesPath.FLOW_RID_IN_PROGRESS_PATH.format(PropertiesPath.baseCatalogName.format()));
		flowRidProcessedPath = propertiesTree.getProperty(PropertiesPath.FLOW_RID_PROCESSED_PATH.format(PropertiesPath.baseCatalogName.format()));
		flowRidRejectedPath = propertiesTree.getProperty(PropertiesPath.FLOW_RID_REJECTED_PATH.format(PropertiesPath.baseCatalogName.format()));
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static String[] manageAllineaRID_AEAFromFile(GatewayPagamentoBean serviceFacade, 
			PropertiesTree propertiesTree, 
			LoggerWrapper logger, StringBuffer testoMail, StringBuffer oggettoMail,
			String dbSchemaCodSocieta)
	{
		
		testoMail.append("Elaborazione flussi AEA<br>");
		
		initConfig(propertiesTree);
		String[] sRes = new String[2];
		int fileTotali = 0;
		int fileProcessatiOk = 0;
		try 
		{
			testoMail.append("Elaborazione dei file nella cartella " + flowRidPath + "<br>");
			logger.info("Elaborazione flussi AEA in: " + flowRidPath);
	    	
			// devo leggere dall'FTP tutte le comunicazioni RID arrivate
	    	// we find compressed flow file 
			TreeSet treeSet = FileUtility.find(flowRidPath, Pattern.compile("^(.*?)[\\|/]*.(txt)"), FileUtility.ORDER_ASC);
			fileTotali = treeSet.size();
			testoMail.append("Trovati " + fileTotali + " file<br><br>"); 
			testoMail.append("");
			Iterator iter = treeSet.iterator();
			boolean isWasAligned = true;
			boolean isWasAlignedWithNoErrors = true;
			if (iter != null && !treeSet.isEmpty()) 
			{
				// ciclo for su tutti i RID trovati			    
				while (iter.hasNext()) 
				{
					/**ELABORAZIONE SINGOLO FILE**/
					
					File f = (File) iter.next();
					// we define fileNameToElab & pathFullFileToElab
					String fileNameToElab = f.getName().substring(0, f.getName().indexOf(".")) + ".txt";
					String pathFullFileToElab = flowRidInProgressPath + fileNameToElab;
					
					testoMail.append("Elaborazione del file " + fileNameToElab + "<br>");
					
					// we move file into progress folder
					Generics_RID.moveFile(f, flowRidInProgressPath);
					logger.info("Flusso AEA process.FILE - " + pathFullFileToElab);
					
					// we allign transactions
					List<RidAEARowInfo_12> listRidAEA = getRowFromFile_RID(pathFullFileToElab, logger, testoMail);
					boolean bParseOk = true; 
					
					int recordTotali = 0;
					int recordOk = 0;
					if (listRidAEA != null)
					{
						bParseOk = true; //--> output
						if (listRidAEA.size()>0)
						{
							//allineamento in DB in base a codice fiscale debitore per ogni record del flusso
							for (RidAEARowInfo_12 ridAEA:listRidAEA)
							{
								recordTotali++;
								
								try {
									saveAndSendMailAEA(ridAEA, serviceFacade, propertiesTree, logger, dbSchemaCodSocieta);
									recordOk++;
								} catch (FacadeException e) {
									
									isWasAlignedWithNoErrors = false;
									final String errMsg = "Errore durante l'allineamento del seguente record del flusso AEA:<br><br>" + 
									"Nome flusso AEA: " + fileNameToElab + "<br>" +
									"Numero disposizione: " + ridAEA.getNumeroProgressivo() + "<br>" +
									"Codice debitore: " + ridAEA.getCodiceDebitore() + "<br>" +
									"Codice causale: " + ridAEA.getCodiceCausale();
									logger.warn(errMsg.replaceAll("<br>", "-"));
									// we send mail notify error
									notificaErroreAllineamentoAEA(errMsg, logger, propertiesTree,dbSchemaCodSocieta);
								}
							}
						}
						
						if (recordTotali == 0)
							testoMail.append("Nessun record da elaborare<br>");
						else
							testoMail.append("Record elaborati correttamente: " + recordOk + " su " + recordTotali + "<br>");
					}
					else
					{
						bParseOk = false; //--> rejected
						logger.warn("Flusso AEA file: " + pathFullFileToElab + " errori durante il parse del file");
					}
						
					if (bParseOk)
					{	
						// we move file into processed folder
						Generics_RID.moveFile(new File(pathFullFileToElab), flowRidProcessedPath);
						logger.info("AllineaRID file:"+pathFullFileToElab+" Processed move on folder:"+flowRidProcessedPath);
						testoMail.append("File processato<br><br>");
						fileProcessatiOk++;
					} 
					else
					{
						// we move file into rejected folder
						Generics_RID.moveFile(new File(pathFullFileToElab), flowRidRejectedPath);
						logger.info("AllineaRID file:"+pathFullFileToElab+" Rejected move on folder:"+flowRidRejectedPath);
						testoMail.append("File scartato<br><br>");
					}
					
					isWasAligned &= bParseOk;
					
					/**FINE ELABORAZIONE SINGOLO FILE**/
				}
								
				if (isWasAligned)
				{
					if (isWasAlignedWithNoErrors)
					{
						//allineamento ok
						sRes[0] = "00";
						sRes[1] = "Allineamento AEA OK";
					}
					else
					{
						//nessun flusso scartato ma errore nell'elaborazione di uno o più record
						sRes[0] = "01";
						sRes[1] = "Allineamento AEA eseguito con errori nell'elaborazione di uno o più record. Nessun flusso scartato.";
					}
				}
				else
				{	
					if (isWasAlignedWithNoErrors)
					{
						//errore di parse in uno o più flussi
						sRes[0] = "02";
						sRes[1] = "Errore nell'allineamento AEA. Uno o più flussi scartati. I flussi non scartati sono stati elaborati correttamente.";
					}
					else
					{
						//errore di parse in uno o più flussi ed errore di elaborazione nei flussi non scartati
						//errore di parse in uno o più flussi
						sRes[0] = "03";
						sRes[1] = "Errore nell'allineamento AEA. Uno o più flussi scartati ed errori nell'elaborazione di uno o più record dei flussi non scartati.";
					}
				}
				
				writeInfo(logger, sRes);
				
				testoMail.append("Processo terminato<br><br>");
				appendReportMail(testoMail, fileTotali, fileProcessatiOk, oggettoMail);
				
				return sRes;
			}
			
			sRes[0] = "04";
			sRes[1] = "Nessun flusso AEA da elaborare";
			writeInfo(logger, sRes);
			
			testoMail.append("Processo terminato<br><br>");
			appendReportMail(testoMail, fileTotali, fileProcessatiOk, oggettoMail);
			
			return sRes;
			
		} catch (Exception e) {
			
			sRes[0] = "05";
			sRes[1] = "Processo di allineamento AEA fallito";
			logger.error("AllineaRID manageAllineaAEAFromFile failed, generic error due to: ", e);
			testoMail.append("Processo di allineamento AEA fallito per errore generico<br><br>");
			appendReportMail(testoMail, fileTotali, fileProcessatiOk, oggettoMail);
			return sRes;
		}
	}
	
	private static void appendReportMail(StringBuffer testoMail, int fileTotali, int fileProcessatiOk, StringBuffer oggettoMail)
	{
		String oggettoMailStr = "Elaborazione flussi AEA";
		int fileScartati = fileTotali - fileProcessatiOk;
		if (fileScartati > 0)
			oggettoMailStr += " con file SCARTATI. Elaborati: " + fileProcessatiOk + " - Scartati: " + fileScartati;
		else
			oggettoMailStr += ". Elaborati: " + fileProcessatiOk;
		oggettoMail.append(oggettoMailStr);
		
		testoMail.append("--------------- REPORT ---------------<br>"); 
		testoMail.append("NUMERO FILE = " + fileTotali + "<br>");
		testoMail.append("NUMERO FILE SCARTATI = " + fileScartati + "<br>");
		testoMail.append("NUMERO FILE PROCESSATI = " + fileProcessatiOk + "<br>");
	}
	
	/**
	 * Gestione file di testo AEA per rid
	 * @param nextFlowPath
	 * @param logger
	 * @return
	 * @throws FileNotFoundException
	 */
	private static List<RidAEARowInfo_12> getRowFromFile_RID(String nextFlowPath, LoggerWrapper logger, StringBuffer testoMail) throws FileNotFoundException
	{
		FileInputStream fis = new FileInputStream(nextFlowPath);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		try 
		{
			// accedo alla prima riga
			List<String> lRidAEA = new ArrayList<String>();
			String linea = br.readLine();
			while (linea != null) 
			{
				lRidAEA.add(linea);
				linea = br.readLine();
			}
			
			// analizza tutte le info 
			return manageAllineaAEAAdesione(lRidAEA, testoMail);			
		} 
		catch (Exception e) 
		{
			logger.error("Errore allineaRIDIG: ", e);
			testoMail.append("Processo di allineamento AEA fallito per errore di parse generico<br><br>");
			return null;
		} finally {
			try { br.close(); isr.close(); fis.close();
			} catch (Exception ignore) {  }
		}
	}
	
	/**
	 * possono essere presenti degli elementi null ad indicare che la conversione non è riuscita per tutti gli utenti
	 * @param lAEA_AdesioneOK
	 * @return
	 */
	private static List<RidAEARowInfo_12> manageAllineaAEAAdesione(List<String> lAEA_AdesioneOK, StringBuffer testoMail)
	{
		boolean bParseOk = true;
		StringBuffer testoAnomalie = new StringBuffer();
		
		//prima devono essere eseguiti i controlli di correttezza del file
		//se c'è anche un solo errore, il file non deve essere completamente scartato
		List<RidAEARowInfo_12> rid12_OUT = new ArrayList<RidAEARowInfo_12>();
		if (lAEA_AdesioneOK.size() > 0)
		{
			if (lAEA_AdesioneOK.size() > 2)
			{
				RidAEARowInit_AL ridInit = new RidAEARowInit_AL(lAEA_AdesioneOK.get(0));
				if (ridInit.isValid())
				{		
					RidAEARowEnd_EF ridEnd = new RidAEARowEnd_EF(lAEA_AdesioneOK.get(lAEA_AdesioneOK.size()-1));
					// numero di righe sono pari a quelle indicate nel record di coda
					if (ridEnd.isValid() && ridEnd.getNumRecord()==lAEA_AdesioneOK.size() &&
							ridEnd.compareRowInitEnd(ridInit))
					{
						// non c'è il vincolo del limiti delle righe posso avere più righe con sequenza
						// 12-50-70 per l'adesione
						// 12-70 per il diniego
						
						// rimuovo la riga iniziale AL e finale EF
						lAEA_AdesioneOK.remove(0);
						lAEA_AdesioneOK.remove(lAEA_AdesioneOK.size()-1);					
						
						for (int i= 0; i<lAEA_AdesioneOK.size(); i++)
						{
							// la riga 12 è sempre presente
							RidAEARowInfo_12 rid12 = new RidAEARowInfo_12(lAEA_AdesioneOK.get(i));
							if (rid12.isValid())
							{
								boolean bRigaAnalizzata = false;
								// analizzo la seconda riga
								i++;
								if (lAEA_AdesioneOK.size() > i)
								{
									RidAEARowInfo_50 rid50 = new RidAEARowInfo_50(lAEA_AdesioneOK.get(i));
									if (rid50.isValid())
									{
										// la riga 50 è presente deve essere presente la riga 70  a chiusura
										// analizzo la seconda riga
										i++;
										if (lAEA_AdesioneOK.size() > i)
										{
											RidAEARowInfo_70 rid70 = new RidAEARowInfo_70(lAEA_AdesioneOK.get(i));
											if (rid12.compareRowInfoBase(rid50) && rid70.isValid() && rid12.compareRowInfoBase(rid70))
											{
												// risposta di adesione accettata
												rid12_OUT.add(rid12);
												bRigaAnalizzata = true;
											}
											else
											{
												bParseOk &= false;
												if (!rid12.compareRowInfoBase(rid50))
													testoAnomalie.append("- il numero disposizione " + rid12.getNumeroProgressivo() + " del record 50 non corrisponde al record 12<br>");
												if (!rid70.isValid())
													testoAnomalie.append("- record 70 (numero disposizione " + rid12.getNumeroProgressivo() + ") non presente o non valido<br>");
												else if (!rid12.compareRowInfoBase(rid70))
													testoAnomalie.append("- il numero disposizione " + rid12.getNumeroProgressivo() + " del record 70 non corrisponde al record 12<br>");
											}
										}
										else
										{
											bParseOk &= false;
											testoAnomalie.append("- record 70 (numero disposizione " + rid12.getNumeroProgressivo() + ") non presente o non valido<br>");
										}
									}
									else
									{
										// per l'adesione KO ho righe 12, 70
										// la riga 50 assente controllo se è presente la riga 70 subito dopo la 12
										// l'indice i non cambia
										RidAEARowInfo_70 rid70 = new RidAEARowInfo_70(lAEA_AdesioneOK.get(i));
										if (rid70.isValid() && rid12.compareRowInfoBase(rid70))
										{
											// la risposta è valida anche se ho un TXT senza riga 50 ovvero diniego dell'adesione --> 90600
											rid12_OUT.add(rid12);
											bRigaAnalizzata = true;
										}
										else
										{
											bParseOk &= false;
											if (!rid70.isValid())
												testoAnomalie.append("- record 70 (numero disposizione " + rid12.getNumeroProgressivo() + ") non presente o non valido<br>");
											else if (!rid12.compareRowInfoBase(rid70))
												testoAnomalie.append("- il numero disposizione " + rid12.getNumeroProgressivo() + " del record 70 non corrisponde al record 12<br>");
										}
									}
								}
								else
								{
									bParseOk &= false;
									testoAnomalie.append("- record 70 (numero disposizione " + rid12.getNumeroProgressivo() + ") non presente o non valido<br>");
								}
								if (!bRigaAnalizzata)
								{
									if (lAEA_AdesioneOK.size() > i)
									{
										RidAEARowInfo_12 rid12new = new RidAEARowInfo_12(lAEA_AdesioneOK.get(i));
										if (rid12new.isValid())
											i--; //devo riconsiderare la riga corrente poichè è di nuovo una riga 12
									}
								}
									
							}
							else 
							{
								bParseOk &= false;
								testoAnomalie.append("- record 12 non valido o non presente<br>");
							}
						}
					}
					else
					{
						bParseOk &= false;
						testoAnomalie.append("- il numero righe del record di coda non corrisponde<br>");
					}
				}
				else
				{
					bParseOk &= false;
					testoAnomalie.append("- il record di testa non è valido<br>");
				}
			}
			else
			{
				bParseOk &= false;
				if (lAEA_AdesioneOK.size() == 1)
				{
					testoAnomalie.append("- il file non è formalmente corretto<br>");
				}
				else if (lAEA_AdesioneOK.size() == 2)
				{
					testoAnomalie.append("- non ci sono record da elaborare<br>");
				}
			}
		}
		else
		{
			bParseOk &= false;
			testoAnomalie.append("- il file è vuoto<br>");
		}
			

		if (!bParseOk)
		{
			if (testoAnomalie.length() > 0)
			{
				testoMail.append("Il flusso presenta le seguenti anomalie:<br>");
				testoMail.append(testoAnomalie.toString());
			}
			return null;
		}
		
		return rid12_OUT;		
	}
	
	/**
	 * 
	 * @param logger
	 * @param sRes
	 */
	private static void writeInfo(LoggerWrapper logger, String[] sRes)
	{
		logger.info("AllineaRID res:"+sRes[0]+" message:"+sRes[1]);		
	}
	/**
	 * 
	 * @param codiceDebitore
	 * @param serviceFacade
	 * @param emailSender
	 * @param propertiesTree
	 * @param logger
	 * @throws FacadeException
	 */
	private static void saveAndSendMailAEA(RidAEARowInfo_12 ridAEA, 
			GatewayPagamentoBean serviceFacade, 
			PropertiesTree propertiesTree,
			LoggerWrapper logger, String dbSchemaCodSocieta) throws FacadeException
	{
		if (ridAEA != null)
		{
			// devo salvare il RID con le nuove informazioni
	    	RidDto ridFacade = new RidDto();
			ridFacade.setCodiceDebitore(ridAEA.getCodiceDebitore());
			ridFacade.setCodiceCausaleAEA(ridAEA.getCodiceCausale());
			serviceFacade.saveRID(ridFacade, dbSchemaCodSocieta);
			
			// ricarico il relativo RID per avere le info complete
	    	ridFacade = serviceFacade.getRID(ridFacade.getCodiceDebitore(), dbSchemaCodSocieta);
	    		
			if (Generics_RID.isFunzioneAdesione(ridFacade))
	    	{
				// se adesione 
		    	if (Generics_RID.isRIDBancaEsitoPositivo(ridFacade))
		    		Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.ADESIONE, Generics_RID_Mail.INVIO.OK, Generics_RID_Mail.TIPO.BATCH,
		    				ridFacade, propertiesTree, logger, dbSchemaCodSocieta);	
		    	else					
		    		Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.ADESIONE, Generics_RID_Mail.INVIO.KO, Generics_RID_Mail.TIPO.BATCH,
		    				ridFacade, propertiesTree, logger, dbSchemaCodSocieta);	
				
	    	}
	    	else
	    	{
	    		// revoca 	
	    		if (Generics_RID.isRIDBancaEsitoPositivo(ridFacade))
	    			Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.REVOCA, Generics_RID_Mail.INVIO.OK, Generics_RID_Mail.TIPO.BATCH,
		    				ridFacade, propertiesTree, logger, dbSchemaCodSocieta);	
		    	else
		    		Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.REVOCA, Generics_RID_Mail.INVIO.KO, Generics_RID_Mail.TIPO.BATCH,
		    				ridFacade, propertiesTree, logger, dbSchemaCodSocieta);
		   	
	    	}
		}
		else
			logger.error("sendMailAEA not valid ridAEA");
	}
	
	public static int acceptRevocaAuto( 
			GatewayPagamentoBean serviceFacade,
			PropertiesTree propertiesTree, LoggerWrapper logger, String dbSchemaCodSocieta) throws FacadeException
	{
		int iNumRevoche = 0;
		logger.info("acceptRevocaAuto started - Ricerca richieste di revoca in corso con termini di accettazione scaduti");
		//calcolo la data corrispondente a X giorni lavorativi fa
		Calendar cDate = Generics_RID_AllineaAEA_Util.getDate_AutoRevoca(propertiesTree, logger);
		String sData = Generics.formatCalendarData(cDate, "yyyy-MM-dd");
		
		List<Rid> listRid = null;
		//recupero dal db tutte le richieste di revoca in corso non ancora gestite e minori della data calcolata
    	Connection conn =null;
    	try {

		    conn = serviceFacade.getConnection(dbSchemaCodSocieta);
    		
		    RidDao dao = new RidDao(conn, serviceFacade.getSchema(dbSchemaCodSocieta));
    		listRid = dao.doListAutoRevoca(sData);
    		
		    
		} catch (DaoException ex) {
			throw new FacadeException(ex.getErrorCode(),ex.getErrorMessage());
		} catch (Exception ex) {
	    	logger.error("acceptRevocaAuto failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } finally {
			//inizio LP PG21XX04 Leak
	    	//serviceFacade.closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
	    }
		
	    if (listRid != null && listRid.size() > 0)
	    {
	    	iNumRevoche = listRid.size();
			logger.info("acceptRevocaAuto: " + iNumRevoche + " richieste di revoca scadute trovate");
	    }
	    else
	    	logger.info("acceptRevocaAuto: Nessuna richiesta di revoca scaduta trovata");
		
	    //per ogni richiesta di revoca imposto la causale a 99999 (accettata) e mando la notifica
		RidAEARowInfo_12 ridAEA = null;
		for (Rid rid : listRid)
		{
			ridAEA = new RidAEARowInfo_12();
			ridAEA.setCodiceDebitore(rid.getCodiceDebitore());
			ridAEA.setCodiceCausale("99999");
			saveAndSendMailAEA(ridAEA, serviceFacade, propertiesTree, logger, dbSchemaCodSocieta);
		}
		logger.info("acceptRevocaAuto finished");
		
		return iNumRevoche;
	}
	
	public static void notificaErroreAllineamentoAEA(final String message, final LoggerWrapper logger, final PropertiesTree propertiesTree, final String cutecute) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					String emailNotificaAdmin = propertiesTree.getProperty(PropertiesPath.emailAdmin.format());
					
					EMailSenderInterface mailSender = GatewayPagamentoBean.getEmailSenderService(propertiesTree);
					
					EMailSenderRequestType req = new EMailSenderRequestType(
							emailNotificaAdmin,
							"","","", 
							propertiesTree.getProperty(PropertiesPath.emailSubjectAEA.format()), 
							message,
							"",cutecute);
					
					EMailSenderResponse response = mailSender.getEMailSender(req);				
					
					logger.info("notificaErroreAllineamentoAEA - emailSender response - " + response.getValue());
				} catch (Exception e) {
					logger.warn("notificaErroreAllineamentoAEA failed, generic error due to: ", e);
				}
			}
		};
		thread.start();
	}
}
