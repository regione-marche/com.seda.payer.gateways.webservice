package com.seda.payer.gateways.webservice.helper;

import java.rmi.RemoteException;

import com.seda.commons.logger.LoggerWrapper;
import com.seda.payer.gateways.facade.bean.GatewayPagamentoBean;
import com.seda.payer.gateways.facade.dto.RidDto;
import com.seda.payer.gateways.facade.exception.FacadeException;
import com.seda.payer.gateways.webservice.helper.Generics_RID;
import com.seda.payer.gateways.webservice.dati.RIDAdesioneRequest;
import com.seda.payer.gateways.webservice.dati.RIDRevocaRequest;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoResponse;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoResponseStatoRichiesta;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoResponseTipoRichiesta;

public class Generics_RID_WS {

	private static final String YES = "Y";
	private static final String NO = "N";
	
	public static void setResponseRID(RIDVerificaStatoResponseTipoRichiesta eType, RIDVerificaStatoResponseStatoRichiesta eState,  
			boolean bFlagRichiediAdesione, boolean bFlagRichiediRevoca, boolean bFlagAbilitaRID, RIDVerificaStatoResponse resp)
	{
		//inizializzazione (campi non nillable)
    	resp.setTipoRichiesta(eType);
    	resp.setStatoRichiesta(eState);    	
    	resp.setFlagAdesione(bFlagRichiediAdesione ? YES : NO);
    	resp.setFlagRevoca(bFlagRichiediRevoca ? YES : NO);
    	resp.setFlagAbilitazioneRID(bFlagAbilitaRID ? YES : NO);
    	
	}
    public static void saveNewAdesioneRevoca(GatewayPagamentoBean service, RidDto ridFacade, LoggerWrapper log, String dbSchemaCodSocieta) throws RemoteException, FacadeException
    {
    	// cancello il vecchio record dell'utente
    	service.delRID(ridFacade.getCodiceDebitore(), dbSchemaCodSocieta);
    	log.info("GatewayRID saveNewAdesioneRevoca: delRID execute");	
		
    	// ricreo il nuovo record per l'adesione/revoca richiesta
    	service.saveRID(ridFacade, dbSchemaCodSocieta); 
    	log.info("GatewayRID saveNewAdesioneRevoca: saveRID execute");	
		
    }
	public static RidDto convertRID(RIDAdesioneRequest in, String idOperation)
    {
    	RidDto ridFacade = new RidDto();
    	
    	ridFacade.setCodiceFunzione(Generics_RID.getCodiceFunzione(true));
    	ridFacade.setCodiceDebitore(in.getCodiceDebitore());
    	ridFacade.setCodiceFiscale(in.getCodiceFiscaleDebitore());
    	ridFacade.setCognomeOrRagSOc(in.getCognomeOrRagioneSocialeDebitore());
    	ridFacade.setCodiceIBAN(in.getIbanDaAddebitare());
    	ridFacade.setIdOperazione(idOperation);
    	ridFacade.setNomeSottoscrittore(in.getNomeSottoscrittore());
    	ridFacade.setCognomeSottoscrittore(in.getCognomeSottoscrittore());
    	ridFacade.setCodFiscSottoscrittore(in.getCodiceFiscaleSottoscrittore());
    	ridFacade.setMailSottoscrittore(in.getMailUser());
    	ridFacade.setNomeIntestatario(in.getNomeIntestatarioCC());
    	ridFacade.setCognomeIntestatario(in.getCognomeIntestatarioCC());
    	ridFacade.setCodFiscIntestatario(in.getCodiceFiscaleIntestatarioCC());
    	ridFacade.setUserName(in.getUserName());
    	ridFacade.setCodiceEsitoRispostaS2S("1");
    	ridFacade.setDescrizioneEsitoRispostaS2S("");
    	ridFacade.setCodiceCausaleAEA("00000"); // rimetto il di default
    	ridFacade.setCodiceRispostaS2SHTTP("");
    	ridFacade.setDescrizioneRispostaS2SHTTP("");
    	ridFacade.setNomeOperatore(in.getOperatoreUltimoAggiornamento());
    	
    	return ridFacade;
    }

	public static void convertRID(RIDRevocaRequest in, String idOperation, RidDto ridUpdate)
    {
    	// gli altri parametri sono settati tramite il get precedente
    	ridUpdate.setCodiceFunzione(Generics_RID.getCodiceFunzione(false));
    	ridUpdate.setIdOperazione(idOperation);
    	ridUpdate.setCodiceEsitoRispostaS2S("1");
    	ridUpdate.setDescrizioneEsitoRispostaS2S("");
    	ridUpdate.setCodiceCausaleAEA("00000"); // rimetto il di default
    	ridUpdate.setCodiceRispostaS2SHTTP("");
    	ridUpdate.setDescrizioneRispostaS2SHTTP("");
    	ridUpdate.setNomeOperatore(in.getOperatoreUltimoAggiornamento());
    }
	public static void modifyRID(String[] sReturnCode_Desc_S2SResponse, String[] sReturnCode_Desc_XmlResponse, RidDto ridFacade) throws Exception
    {
    	if (ridFacade != null)
    	{    	
	    	ridFacade.setCodiceEsitoRispostaS2S(sReturnCode_Desc_S2SResponse[0]);
	    	ridFacade.setDescrizioneEsitoRispostaS2S(sReturnCode_Desc_S2SResponse[1]);
	    	ridFacade.setCodiceCausaleAEA("00000"); // rimetto il di default
	    	ridFacade.setCodiceRispostaS2SHTTP(sReturnCode_Desc_XmlResponse[0]);
	    	ridFacade.setDescrizioneRispostaS2SHTTP(sReturnCode_Desc_XmlResponse[1]);
    	}
    	else
    		throw new Exception("modifyRid - Not valid ridFacade");
    }	
}
