//package com.seda.payer.gateways.webservice.helper;
//
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.AnomaliaWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.ChiaveWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.ConsegnaAnticipataDocumentoService;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.ConsegnaAnticipataDocumentoServiceBeanServiceLocator;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.DocumentoWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.EsitoVerificaFileWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.FileDocumentoWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.InformazioniVerificaDocumentoWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.ParteRappresentazioneDocumentoWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.ProprietaWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.RisultatoConsegnaAnticipataWS;
//import it.unimaticaspa.unirepo.service.versamenti.anticipati.VersioneDocumentoWS;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Calendar;
//import java.util.Iterator;
//import java.util.Locale;
//import java.util.TreeSet;
//import java.util.regex.Pattern;
//
//import com.seda.commons.logger.LoggerServer;
//import com.seda.commons.properties.tree.PropertiesTree;
//import com.seda.payer.commons.utility.FileUtility;
//import com.seda.payer.gateways.webservice.config.PropKeys;
//
//public class Generics_RID_AllineaPDF {
//
//	private static String flowRidPdfInput;
//	private static String flowRidPdfProcessed;
//	private static int intervalloOreArchiviazione;
//	
//	
//	/**
//	 * precarica i file di configurazione
//	 * @param propertiesTree
//	 */
//	private static void initConfig(PropertiesTree propertiesTree) 
//	{
//		flowRidPdfInput = propertiesTree.getProperty(PropKeys.FLOW_RID_PDF_PATHINPUT.format(PropKeys.DEFAULT_NODE.format()));
//		flowRidPdfProcessed = propertiesTree.getProperty(PropKeys.FLOW_RID_PDF_PATHPROCESSED.format(PropKeys.DEFAULT_NODE.format()));
//		try {
//			intervalloOreArchiviazione = Integer.parseInt(propertiesTree.getProperty(PropKeys.RID_ARCHIVIAZIONE_NTERVALLO_ORE.format()));
//		} catch (Exception e) {
//			intervalloOreArchiviazione = 1;
//		}
//		if (intervalloOreArchiviazione < 1)
//			intervalloOreArchiviazione = 1;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static String[] manageAllineaRID_PDF(PropertiesTree propertiesTree, 
//			LoggerServer logger)
//	{
//		initConfig(propertiesTree);
//		String[] sRes = new String[2];
//		try 
//		{
//			logger.info("Elaborazione flussi manageAllineaRID_PDF in: " + flowRidPdfInput);
//	    	
//			// devo leggere dal path tutti i file presenti
//	    	// we find compressed flow file 
//			TreeSet treeSet = FileUtility.find(flowRidPdfInput, Pattern.compile("^(.*?)[\\|/]*.(pdf)"), FileUtility.ORDER_ASC);
//			Iterator iter = treeSet.iterator();
//			boolean isWasAlligned = false;
//			boolean isSendWS = true;
//			if (iter != null && !treeSet.isEmpty()) 
//			{
//				// ciclo for su tutti i RID trovati			    
//				while (iter.hasNext()) 
//				{
//					File fActual = (File) iter.next();
//					// we define fileNameToElab & pathFullFileToElab
//					//String fileNameToElab = fActual.getName().substring(0, fActual.getName().indexOf(".")) + ".txt";
//										
//					// we allign l'archiviazione RID_<codice_fiscale>
//					if (isValidSendArchiviazione(fActual, logger))
//					{
//						// il file deve essere inviato al WS di archiviazione
//						String codiceFiscale = fActual.getName().substring(0, fActual.getName().indexOf("."));
//						if (sendFileWSArchive(fActual, codiceFiscale, propertiesTree, logger))
//						{
//							isWasAlligned = true;
//								
//							 // il file è stato inviato al WS diarchiviazione
//						    logger.info("manageAllineaRID_PDF file:"+fActual.getAbsolutePath()+" Processed move on folder:"+flowRidPdfProcessed);
//							move(fActual, flowRidPdfProcessed);
//						}
//						else
//							isSendWS = false;
//							
//					}
//				}
//								
//				if (isWasAlligned && isSendWS)
//				{
//					// ci si è allineati
//					sRes[0] = "00";
//					sRes[1] = "OK";
//				}
//				else
//				{
//					if (!isSendWS)
//					{	
//						// errore nella comunicazioen con il WS
//						sRes[0] = "01";
//						sRes[1] = "Errore nell'elaborazione dei flussi: WS è fallito";
//					}
//					else
//					{	
//						// errore nei flussi
//						sRes[0] = "02";
//						sRes[1] = "Errore nell'elaborazione dei flussi: nessun file allineato";					
//					}
//				}
//				
//				writeInfo(logger, sRes);
//				return sRes;
//			}
//			
//			sRes[0] = "02";
//			sRes[1] = "Nessun flusso da elaborare";
//			writeInfo(logger, sRes);
//			return sRes;
//			
//		} 
//		catch (Exception e) 
//		{			
//			sRes[0] = "03";
//			sRes[1] = "Processo di allineamento PDF richiesta adesione fallito";
//			logger.error("manageAllineaRID_PDF failed, generic error due to: ", e);
//			return sRes;
//		}
//	}
//	
//
//	/**
//	 * verifica che il file sia processabile
//	 * @param fActual
//	 * @param logger
//	 * @return
//	 */
//	private static boolean isValidSendArchiviazione(File fActual, LoggerServer logger)
//	{
//		if (fActual.exists())
//		{
//			// prelevo la data dell'ultima modifica del file
//			Calendar calFileLastModified = Calendar.getInstance();
//			calFileLastModified.setTimeInMillis(fActual.lastModified());
////	     	System.out.println(cal.get(Calendar.YEAR)+" "+(cal.get(Calendar.MONTH)+1)+" "+cal.get(Calendar.DAY_OF_MONTH)+
////	     			" "+cal.get(Calendar.HOUR_OF_DAY)+" "+cal.get(Calendar.MINUTE)+" "+cal.get(Calendar.SECOND));
//	     	
//	     	// il file non deve essere modificata nelle 24 ore precedenti al lancio del batch ovvero ad adesso 
//	     	Calendar calActual = Calendar.getInstance();
//	     	calFileLastModified.add(Calendar.HOUR, intervalloOreArchiviazione);
//	     	if (calFileLastModified.before(calActual))
//	     	{
//	     		logger.info("il file risulta: "+fActual.getName()+" con data precedente alle " + String.valueOf(intervalloOreArchiviazione) + " ore - Da archiviare");
//	     		return true;
//	     	}
//	     	else
//	     		logger.info("il file risulta: "+fActual.getName()+" con data successiva alle " + String.valueOf(intervalloOreArchiviazione) + " ore");
//		}
//		
//		return false;
//	}
//	
//	private static ConsegnaAnticipataDocumentoService getArchiviazione(PropertiesTree env, LoggerServer logger) 
//	{
//		try 
//		{
//			String sUrl = env.getProperty(PropKeys.RID_ARCHIVIAZIONE_URL.format());
//			logger.info("getArchiviazione - URL WS:"+sUrl);
//			ConsegnaAnticipataDocumentoServiceBeanServiceLocator archiService = new ConsegnaAnticipataDocumentoServiceBeanServiceLocator();
//			ConsegnaAnticipataDocumentoService service = archiService.getConsegnaAnticipataDocumentoServiceBeanPort(new URL(sUrl));
//			return (ConsegnaAnticipataDocumentoService)service;
//		}
//		catch (Exception e)
//		{
//			System.out.println("getArchiviazione exception: " + e.getMessage());			
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	private static boolean sendFileWSArchive(File fActual, String chiaveFile, PropertiesTree propertiesTree, LoggerServer logger)
//	{
//		try 
//		{
//			// collegarsi al WS per la migrazione del file			
//			ConsegnaAnticipataDocumentoService consegnaRID = getArchiviazione(propertiesTree, logger);
//			
//			// crezione del documento
//			DocumentoWS documento = createWSDocumento(fActual, chiaveFile, logger);			
//			// parametri proprietà
//			ProprietaWS[] proprieta = createProprieta_MetodoConsegna(chiaveFile);
//			
//			// cguamata al WS
//			RisultatoConsegnaAnticipataWS res = consegnaRID.consegnaAnticipata(documento, proprieta);		
//			if (res.getEsito().equals("0000"))
//			{
//				logger.info("Chiamata all'archiviazione WS OK: "+getResult(res));
//				return true;
//			}
//			else
//			{
//				logger.error("Chiamata all'archiviazione WS FALLITA: "+getResult(res));
//				return false;
//			}
//		} 
//		catch (Exception e) 
//		{
//			logger.error("Chiamata all'archiviazione WS FALLITA: error generics", e);
//			return false;
//		}
//	}
//	private static ProprietaWS[] createProprieta_MetodoConsegna(String chiaveFile)
//	{
//		// pag 25 e 26
//		ProprietaWS[] proprieta = new ProprietaWS[5];
//		// family
//		proprieta[0] = createWSProprieta("family", "unimatica");			
//		// organizzazione
//		proprieta[1] = createWSProprieta("organizzazione", "lepida");
//		// struttura
//		proprieta[2] = createWSProprieta("struttura", "payer");
//		// user_id
//		proprieta[3] = createWSProprieta("user_id", "versatore_lepida");
//		// chiave --> RID_<codicefiscale>
//		proprieta[4] = createWSProprieta("chiave", chiaveFile);
//		return proprieta;
//	}
//	private static DocumentoWS createWSDocumento(File fActual, String chiaveFile, LoggerServer logger)
//	{
//		DocumentoWS documento = new DocumentoWS();	
//		// chiave
//		documento.setChiave(createWSChiave(chiaveFile));
//		
//		// proprietà
//		ProprietaWS[] propDocumento = new ProprietaWS[3];
//		int iNum = 0;
//		propDocumento[iNum++] = createWSProprieta("forza_conservazione", "true");
//		// non può essere stringa vuota altrimenti ho codice errore Codice:0016 -->	Descrizione:Il tipo documento non puo' essere : [null].
//		propDocumento[iNum++] = createWSProprieta("tipo_documento", "pdf");
//		propDocumento[iNum++] = createWSProprieta("forza_accettazione", "true");
//		//propDocumento[iNum++] = createWSProprieta("RIFIUTA_SE_TUTTO_NON_FIRMATO", "false");		
//		documento.setProprieta(propDocumento);
//		
//		// documento originale
//		documento.setVersioneOriginale(createWSVersioneDocumento(fActual, logger));		
//		
//		return documento;
//	}
//	private static ChiaveWS	createWSChiave(String chiaveFile)
//	{
//		// chiave pag 5
//		Calendar calNow = Calendar.getInstance();				
//		
//		ChiaveWS chiave = new ChiaveWS();
//		ProprietaWS[] propChiave = new ProprietaWS[3];
//		propChiave[0] = createWSProprieta("numero", formatCalendarData(calNow));
//		propChiave[1] = createWSProprieta("anno", String.valueOf(calNow.get(Calendar.YEAR)));
//		propChiave[2] = createWSProprieta("registro", chiaveFile);
//		chiave.setProprieta(propChiave);
//		
//		return chiave;
//	}
//	private static VersioneDocumentoWS createWSVersioneDocumento(File fActual, LoggerServer logger)
//	{
//		// pag 9
//		VersioneDocumentoWS versDocumento = new VersioneDocumentoWS();
//		
//		ProprietaWS[] prop = new ProprietaWS[1];
//		prop[0] = createWSProprieta("formato_versione", "1");
//		versDocumento.setProprieta(prop);
//		
//		// parti rappresentazione
//		ParteRappresentazioneDocumentoWS[] parti = new ParteRappresentazioneDocumentoWS[1];
//		parti[0] = createWSParteRappresentazione(fActual, logger);
//		versDocumento.setParti(parti);
//		
//		return versDocumento;
//	}
//	private static ParteRappresentazioneDocumentoWS createWSParteRappresentazione(File fActual, LoggerServer logger)
//	{
//		// pag 9
//		ParteRappresentazioneDocumentoWS parte = new ParteRappresentazioneDocumentoWS();
//				
//		ProprietaWS[] prop = new ProprietaWS[2];
//		prop[0] = createWSProprieta("formato_parte", "pdf");
//		prop[1] = createWSProprieta("numero_parte", "0");
//		parte.setProprieta(prop);
//		
//		// files
//		FileDocumentoWS[] files = new FileDocumentoWS[1];
//		files[0] = createWSFileDocumento(fActual, logger);
//		parte.setFiles(files);
//		
//		return parte;
//	}
//	private static FileDocumentoWS createWSFileDocumento(File fActual, LoggerServer logger)
//	{
//		// pag 10 e 21
//		FileDocumentoWS file = new FileDocumentoWS();
//		file.setDati(readFileAsString(fActual, logger));
//		// proprietà
//		file.setProprieta(createProprieta_FileDocumento(fActual));
//		
//		return file;
//	}
//	
//	private static ProprietaWS[] createProprieta_FileDocumento(File fActual)
//	{
//		// pag 11 e 21
//		ProprietaWS[] proprieta = new ProprietaWS[4];
//		int iNum = 0;
//		proprieta[iNum++] = createWSProprieta("nome_file", fActual.getName());			
//		proprieta[iNum++] = createWSProprieta("formato_file", "pdf");
//		proprieta[iNum++] = createWSProprieta("id_cliente", fActual.getName());
//		proprieta[iNum++] = createWSProprieta("ordine_presentazione", "0");
//		
//		// falcotativi
//		//proprieta[iNum++] = createWSProprieta("forza_conservazione", "true");
//		//proprieta[iNum++] = createWSProprieta("forza_accettazione", "true");
//		//proprieta[iNum++] = createWSProprieta("RIFIUTA_SE_TUTTO_NON_FIRMATO", "false");		
//		
//		return proprieta;
//	}	
//	private static ProprietaWS createWSProprieta(String sChiave, String sValue)
//	{
//		ProprietaWS prop = new ProprietaWS();
//		prop.setChiave(sChiave);			
//		prop.setValore(sValue);
//		return prop;
//	}
//	/**
//	 * Ritorna AAAA-MM-ggTHH:mm:ss
//	 * @param data
//	 * @return
//	 */
//	private static String formatCalendarData(Calendar data)
//	{
//		if (data != null)
//		{
//			Calendar cal = Calendar.getInstance(Locale.ITALIAN);
//			cal.setTime(data.getTime());
//		
//			return formatNumToString(2, String.valueOf(cal.get(Calendar.YEAR))) + "-" +
//				Generics.formatNumToString(2, String.valueOf((cal.get(Calendar.MONTH) + 1))) + "-" +
//				String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + 
//				"T" + 
//				Generics.formatNumToString(2, String.valueOf(cal.get(Calendar.HOUR_OF_DAY))) + ":" +
//				Generics.formatNumToString(2, String.valueOf(cal.get(Calendar.MINUTE))) + ":" +
//				Generics.formatNumToString(2, String.valueOf(cal.get(Calendar.SECOND)));
//		}
//		else
//			return "";
//	}
//	private static String formatNumToString(int iLenght, String sNumToFormat)
//	{
//		String formattedString = sNumToFormat;
//
//		while(formattedString.length() < iLenght) 
//		{
//			formattedString = "0" + formattedString;
//		}
//		return formattedString;
//	}
//	 
//	/**
//	 * 
//	 * @param logger
//	 * @param sRes
//	 */
//	private static void writeInfo(LoggerServer logger, String[] sRes)
//	{
//		logger.info("AllineaRID res:"+sRes[0]+" message:"+sRes[1]);		
//	}	
//	/**
//	 * @param f
//	 * @param dir
//	 * @return
//	 * @throws Exception
//	 */
//	private static boolean move(File f, String dir) throws Exception {
//		return new File(f.getAbsolutePath()).renameTo(new File(dir + f.getName()));
//	}
//	/** 
//	 * carica il file in una stringa
//	 * @param filePath
//	 * @return
//	 * @throws java.io.IOException
//	 */
//	private static String readFileAsString(File filePath, LoggerServer logger)
//	{
//		
//		
//		try
//		{
//	        /*StringBuffer fileData = new StringBuffer(1000);
//	        BufferedReader reader = new BufferedReader(new FileReader(filePath.getAbsolutePath()));
//	        char[] buf = new char[1024];
//	        int numRead=0;
//	        while((numRead=reader.read(buf)) != -1){
//	            String readData = String.valueOf(buf, 0, numRead);
//	            fileData.append(readData);
//	            buf = new char[1024];
//	        }
//	        reader.close();
//	        return fileData.toString();*/
//			return com.itextpdf.text.pdf.codec.Base64.encodeFromFile(filePath.getAbsolutePath());
//		}
//		catch (Exception ex)
//		{
//			logger.error("readFileAsString FALLITO: error generics", ex);
//			return "";		
//		}
//    }
//	
//	private static String getResult(RisultatoConsegnaAnticipataWS res)
//	{
//		String ACAPO = "\r\n";
//		String ACAPO_TAB = "\r\n\t";
//		String sResult = ACAPO+"Esito:"+res.getEsito()+ACAPO;
//		sResult += " - Chiave:"+res.getChiave()+ACAPO;		
//		sResult += " - Proprietà: "+getResult(res.getProprieta())+ACAPO;
//		
//		if (res.getAnomalie()!=null && res.getAnomalie().length>0)
//		{
//			String sAnomalie = "";
//			for (AnomaliaWS anomal:res.getAnomalie())
//			{
//				sAnomalie += ACAPO_TAB+"[Gravità:"+anomal.getGravita()+ACAPO_TAB+"Codice:"+anomal.getCodice()+ACAPO_TAB+"Descrizione:"+anomal.getDescrizione();
//				sAnomalie += ACAPO_TAB+"Proprietà: "+getResult(anomal.getProprieta())+ACAPO_TAB+"]";
//			}
//		
//			sResult += " - Anomalie: "+sAnomalie+ACAPO;		
//		}
//		
//		InformazioniVerificaDocumentoWS info = res.getInfoVerificaDocumento();
//		if (info!=null)
//		{
//			String sEsitoVerifiche = "";
//			if (info.getDocumento().getVersioneOriginale().getParti().length>0 &&
//				info.getDocumento().getVersioneOriginale().getParti()[0].getFiles().length>0)
//			{
//				EsitoVerificaFileWS esito = info.getDocumento().getVersioneOriginale().getParti()[0].getFiles()[0].getEsitoVerifica();
//				sEsitoVerifiche = " (Complessivo:"+esito.getEsitoComplessivoFile()+") ";
//			}
//			
//			String sInfo = ACAPO_TAB+"[";
//			sInfo += ACAPO_TAB+"Esiti: "+sEsitoVerifiche;
//			sInfo += ACAPO_TAB+"Proprietà: "+getResult(info.getProprieta());
//			sInfo += ACAPO_TAB+"]";
//			sResult += " - InfoVerifiche: "+sInfo+ACAPO;		
//		}
//		
//		return sResult;		
//	}
//	private static String getResult(ProprietaWS[] properties)
//	{
//		String sProprieta = "";
//		if (properties!=null && properties.length>0)
//		{
//			for (ProprietaWS prop:properties)
//				sProprieta += "("+prop.getChiave()+":"+prop.getValore()+")\t";
//			
//		}
//		else
//			sProprieta = " -- ";
//		
//		return sProprieta;
//	}
//}
//
