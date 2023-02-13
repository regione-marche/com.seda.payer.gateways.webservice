package com.seda.payer.gateways.facade.util;

import java.net.URL;

import org.apache.log4j.Logger;

import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.payer.gateways.facade.dto.RidDto;
import com.seda.payer.gateways.facade.handler.PropertiesPath;
import com.seda.payer.gateways.facade.ws.EMailSender;
import com.seda.payer.notifiche.webservice.dati.NotificaRIDRequestType;
import com.seda.payer.notifiche.webservice.dati.NotificaRIDResponseType;
import com.seda.payer.notifiche.webservice.source.NotificheSOAPBindingStub;
import com.seda.payer.notifiche.webservice.source.NotificheServiceLocator;


public class Generics_RID_Mail {

	public final static String DBSCHEMACODSOCIETA = "dbSchemaCodSocieta";
	
	public enum FUNZIONE
	{
		ADESIONE,
		REVOCA
	}
	public enum INVIO
	{
		OK,
		KO
	}
	public enum TIPO
	{
		BATCH,
		NONE
	}
	
	/**
	 * notifica di pagamento solamente per adesione/revoca
	 * @param funzione
	 * @param invio
	 * @param tipo
	 * @param ridFacade
	 * @param notificheWS
	 * @param env
	 * @param logger
	 * @return
	 */
	public static boolean notificaRID(FUNZIONE funzione, INVIO invio, TIPO tipo, 
			RidDto ridFacade, PropertiesTree env, Logger logger,
			String dbSchemaCodSocieta)
	{
		String operFunzione = "";
		switch (funzione)
		{
			case ADESIONE:
				operFunzione = "AD";
				break;
		
			case REVOCA:
				operFunzione = "RE";
				break;
		}
		
		String operInvio = "";
		switch (invio)
		{
			case OK:
				operInvio = "OK";
				break;
			
			case KO:
				operInvio = "KO";
				break;		
		}
		
		String operTipo = "";
		switch (tipo)
		{
			case BATCH:
				operTipo = "B";
				break;
			
			case NONE:
				operTipo = "N";
				break;		
		}		
		
		NotificaRIDRequestType notificaRequestRID = new NotificaRIDRequestType();
		// parametri per mail
		notificaRequestRID.setOperazioneFunzione(operFunzione);
		notificaRequestRID.setOperazioneInvio(operInvio);
		notificaRequestRID.setOperazioneTipo(operTipo);
		
		// sottoscrittore
		notificaRequestRID.setSottoscrittoreNome(ridFacade.getNomeSottoscrittore());
		notificaRequestRID.setSottoscrittoreCognome(ridFacade.getCognomeSottoscrittore());
		notificaRequestRID.setSottoscrittoreCodFiscale(ridFacade.getCodiceFiscale());
		notificaRequestRID.setSottoscrittoreMail(ridFacade.getMailSottoscrittore());
		
		// intestario
		notificaRequestRID.setIntestatarioCCNome(ridFacade.getNomeIntestatario());
		notificaRequestRID.setIntestatarioCCCognome(ridFacade.getCognomeIntestatario());
		notificaRequestRID.setIntestatarioCCCodFiscale(ridFacade.getCodFiscIntestatario());
		
		// generiche info
		notificaRequestRID.setCodiceIBAN(ridFacade.getCodiceIBAN());
		notificaRequestRID.setCodiceCausaleAEA(ridFacade.getCodiceCausaleAEA());
		notificaRequestRID.setDataInserimento(ridFacade.getDataInserimento());
		notificaRequestRID.setDescrizioneCausaleAEA(ridFacade.getDescrizioneeCausaleAEA());
	
		NotificheSOAPBindingStub notifiheWs = getNotificheService(env, dbSchemaCodSocieta);
		
		NotificaRIDResponseType responseNotificaRID = null;
		try {
			responseNotificaRID = notifiheWs.notificaRID(notificaRequestRID);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
		if (responseNotificaRID!=null && responseNotificaRID.getResponse().getRetcode().getValue().equals("00"))
	    {
		  	return true;
	    }
	    else
	    {
	    	logger.error("notificaRID failed, not send notifica");		    
		    return false;
	    }
	}
	
	public static boolean notificaRID_CBI(
			String chiaveTransazione, String codiceCausaleAEA, String descrizioneCausaleAEA,
			PropertiesTree propertiesTree, Logger logger, String dbSchemaCodSocieta)
	{
		try
		{
			String operFunzione = "CB";
			String operInvio = "OK";
			String operTipo = "B";
			
			NotificaRIDRequestType notificaRequestRID = new NotificaRIDRequestType();
			// parametri per mail
			notificaRequestRID.setOperazioneFunzione(operFunzione);
			notificaRequestRID.setOperazioneInvio(operInvio);
			notificaRequestRID.setOperazioneTipo(operTipo);
			
			notificaRequestRID.setChiaveTransazione(chiaveTransazione);
			notificaRequestRID.setCodiceCausaleAEA(codiceCausaleAEA);
			notificaRequestRID.setDescrizioneCausaleAEA(descrizioneCausaleAEA);
		
			NotificheSOAPBindingStub notifiheWs = getNotificheService(propertiesTree, dbSchemaCodSocieta);
			
			NotificaRIDResponseType responseNotificaRID = notifiheWs.notificaRID(notificaRequestRID);
			if (responseNotificaRID!=null && responseNotificaRID.getResponse().getRetcode().getValue().equals("00"))
		    {
			  	return true;
		    }
		    else
		    {
		    	logger.error("notificaRID failed, not send notifica");		    
			    return false;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("notificaRID failed, not send notifica");		    
		    return false;
		}
	}
	
	public static boolean generaAndSendMail(EMailSender eMailSender, RidDto ridFacade, String sObject, String sText, Logger logger)
	{
		if (eMailSender!=null && ridFacade.getMailSottoscrittore() != null && ridFacade.getMailSottoscrittore().length()!=0)
		{
			if (sObject!=null && sObject.trim().length()>0 && sText!=null && sText.trim().length()>0)
				return eMailSender.sendEMail(ridFacade.getMailSottoscrittore(), "", "", sObject, sText);
			else
			{
				// se il testo è vuoto è voluto quindi come se fosse stata inviata
				logger.info("GatewaysRID not send mail : object and text empty");
				return true;
			}			
		}
		else
		{
			logger.error("GatewaysRID not send mail : mail user absent");
			return false;
		}
	}

	private static NotificheSOAPBindingStub getNotificheService(PropertiesTree propertiesTree, String dbSchemaCodSocieta)
	{
		NotificheSOAPBindingStub notificheService = null;
		NotificheServiceLocator notificheServiceLocator = new NotificheServiceLocator();
		try {
			notificheService = (NotificheSOAPBindingStub)notificheServiceLocator.getNotifichePort(
					new URL(propertiesTree.getProperty(PropertiesPath.wsNotificheUrl.format(PropertiesPath.baseCatalogName.format()))));
			
			notificheService.clearHeaders();
			notificheService.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
			
			return notificheService;
		} catch (Exception e) {
			return null;
		}
	}

}
