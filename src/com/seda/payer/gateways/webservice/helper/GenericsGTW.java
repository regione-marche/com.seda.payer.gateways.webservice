package com.seda.payer.gateways.webservice.helper;

import java.util.Properties;

import org.w3c.dom.Document;

import com.seda.commons.logger.LoggerServer;
import com.seda.commons.properties.tree.PropertiesTree;

import com.seda.payer.gateways.facade.bean.GatewayPagamentoBean;
import com.seda.payer.gateways.facade.dto.GatewayPagamentoDto;
import com.seda.payer.gateways.webservice.config.CanaleAttivazioneTypeKeys;
import com.seda.payer.gateways.webservice.config.GatewaysTypeKeys;

import com.seda.payer.gateways.webservice.config.StrumentoPagamentoTypeKeys;
import com.seda.payer.gateways.webservice.dati.RIDAdesioneRequest;
import com.seda.payer.gateways.webservice.dati.RIDAdesioneResponse;
import com.seda.payer.gateways.webservice.dati.RIDRevocaRequest;
import com.seda.payer.gateways.webservice.dati.RIDRevocaResponse;

public class GenericsGTW {

	public static String xmlTextRID_Adesione(RIDAdesioneRequest ridAdesione, String idOperation) throws Exception {
		
		String nomeIntestatario = ridAdesione.getNomeIntestatarioCC();
		String cognomeIntestatario = ridAdesione.getCognomeIntestatarioCC();
		String codFiscaleIntestatario = ridAdesione.getCodiceFiscaleIntestatarioCC();
		
		//i dati dell'intestatario, se non espressi, si assumono uguali a quelli del sottoscrittore (da documentazione)
		if (ridAdesione.getNomeIntestatarioCC().equals("") && ridAdesione.getCognomeIntestatarioCC().equals("") && ridAdesione.getCodiceFiscaleIntestatarioCC().equals(""))
		{
			nomeIntestatario = ridAdesione.getNomeSottoscrittore();
			cognomeIntestatario = ridAdesione.getCognomeSottoscrittore();
			codFiscaleIntestatario = ridAdesione.getCodiceFiscaleSottoscrittore();
		}
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<service>" +
				   "<function>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + CanaleAttivazioneTypeKeys.parse(ridAdesione.getCanalePagamento()) + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + StrumentoPagamentoTypeKeys.RID.format() + "</strumentoPagamento>" +
							   "<codiceFunzione>01</codiceFunzione>" +
						   "</generic>" +
						   "<specific>" +
						   		"<codiceDebitore>" + ridAdesione.getCodiceDebitore() + "</codiceDebitore>" +
						   		"<codiceFiscaleDebitore>" + ridAdesione .getCodiceFiscaleDebitore()+ "</codiceFiscaleDebitore>" +
						   		"<cognomeORagioneSocialeDebitore>" + ridAdesione.getCognomeOrRagioneSocialeDebitore() + "</cognomeORagioneSocialeDebitore>" +
						   		"<ibanDaAddebitare>" + ridAdesione.getIbanDaAddebitare() + "</ibanDaAddebitare>" +
						   		"<idOperazione>" + idOperation + "</idOperazione>" +
						   		
						   		"<nomeSottoscrittore>" + ridAdesione.getNomeSottoscrittore() + "</nomeSottoscrittore>" +
						   		"<cognomeSottoscrittore>" + ridAdesione.getCognomeSottoscrittore() + "</cognomeSottoscrittore>" +
						   		"<codiceFiscaleSottoscrittore>" + ridAdesione.getCodiceFiscaleSottoscrittore() + "</codiceFiscaleSottoscrittore>" +
						   		
						   		"<nomeIntestatarioCC>" + nomeIntestatario + "</nomeIntestatarioCC>" +
						   		"<cognomeIntestatarioCC>" + cognomeIntestatario + "</cognomeIntestatarioCC>" +
						   		"<codiceFiscaleIntestatarioCC>" + codFiscaleIntestatario + "</codiceFiscaleIntestatarioCC>" +
						   		"<userName>" + ridAdesione.getUserName() + "</userName>" +
						   "</specific>" +
					   "</data>" +
				   "</function>" +
			   "</service>";
	}	
	
	public static String[] parseRidAdesione(RIDAdesioneResponse response, String sXML_Response, LoggerServer log) throws Exception
	{
		String[] sResponseCode_Desc = new String[2];
		
		Document doc=GenericsXml.getXmlDocumentFromString(sXML_Response);
        
		//recupero e setto i nodi codice e descrizione		
		sResponseCode_Desc[0] = GenericsXml.getElementValue("/service/function/esito/codice", doc, log);
		sResponseCode_Desc[1] = GenericsXml.getElementValue("/service/function/esito/descrizione", doc, log);
    	
    	// risposta nel payer
    	response.setRetCode(Generics_RID.convertValidResponseS2S(sResponseCode_Desc[0]));
    	response.setRetMessage(sResponseCode_Desc[1]);
    	
		return sResponseCode_Desc;
	}
	
	
	public static String xmlTextRID_Revoca(RIDRevocaRequest ridRevoca, String idOperation) throws Exception {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<service>" +
				   "<function>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + CanaleAttivazioneTypeKeys.parse(ridRevoca.getCanalePagamento()) + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + StrumentoPagamentoTypeKeys.RID.format() + "</strumentoPagamento>" +
							   "<codiceFunzione>02</codiceFunzione>" +
						   "</generic>" +
						   "<specific>" +
						   		"<codiceDebitore>" + ridRevoca.getCodiceDebitore() + "</codiceDebitore>" +
						   		"<userName>" + ridRevoca.getUsername() + "</userName>" +
						   		"<idOperazione>" + idOperation + "</idOperazione>" +						   		
						   "</specific>" +
					   "</data>" +
				   "</function>" +
			   "</service>";
	}	
	public static String[]  parseRidRevoca(RIDRevocaResponse response, String sXML_Response, LoggerServer log) throws Exception
	{
		String[] sResponseCode_Desc = new String[2];
		//response = new RIDRevocaResponse();
		
		Document doc=GenericsXml.getXmlDocumentFromString(sXML_Response);
        
		//recupero e setto i nodi codice e descrizione		
		sResponseCode_Desc[0] = GenericsXml.getElementValue("/service/function/esito/codice", doc, log);
		sResponseCode_Desc[1] = GenericsXml.getElementValue("/service/function/esito/descrizione", doc, log);
    	
    	// risposta nel payer
    	response.setRetCode(Generics_RID.convertValidResponseS2S(sResponseCode_Desc[0]));
    	response.setRetMessage(sResponseCode_Desc[1]);
    	
    	return sResponseCode_Desc;
    }
	
	/**
	 * recupera il gateway indicato
	 * @param chiaveGateway
	 * @param env
	 * @return
	 * @throws Exception
	 */
	//inizio LP PG200070 - 20200812
	//public static GatewayPagamentoDto getGatewayDetail(String chiaveGateway, PropertiesTree env, String dbSchemaCodSocieta) throws Exception
	public static GatewayPagamentoDto getGatewayDetail(String chiaveGateway, PropertiesTree env, String dbSchemaCodSocieta) throws Exception
	//fine LP PG200070 - 20200812
	{		
		//inizio LP PG200070 - 20200812
		//GatewayPagamentoBean service = new GatewayPagamentoBean();
		GatewayPagamentoBean service = new GatewayPagamentoBean(env);
		//fine LP PG200070 - 20200812
		return service.getGatewayPagamento(chiaveGateway, dbSchemaCodSocieta);		
	}
	
	/**
	 * indica se il gateway è MAV
	 * @param gateway
	 * @return
	 * @throws Exception 
	 */
	public static boolean isGatewayMav(String chiaveGateway, PropertiesTree env, String dbSchemaCodSocieta) throws Exception
	{
		//inizio LP PG200070 - 20200812
		//GatewayPagamentoDto gateway = getGatewayDetail(chiaveGateway, env, dbSchemaCodSocieta);	
		GatewayPagamentoDto gateway = getGatewayDetail(chiaveGateway, env, dbSchemaCodSocieta);	
		//fine LP PG200070 - 20200812
		return isGatewayMav(gateway);
		
	}
	/**
	 * indica se il gateway è MAV
	 * @param gateway
	 * @return
	 */
	public static boolean isGatewayMav(GatewayPagamentoDto gateway)
	{
		return gateway.getTipoGateway().compareTo(GatewaysTypeKeys.MAVONLINE.format()) == 0;
	}
	/**
	 * indica se il gateway è RID
	 * @param gateway
	 * @return
	 */
	public static boolean isGatewayRid(GatewayPagamentoDto gateway)
	{
		return gateway.getTipoGateway().compareTo(GatewaysTypeKeys.RIDONLINE.format()) == 0;			
	}
}
