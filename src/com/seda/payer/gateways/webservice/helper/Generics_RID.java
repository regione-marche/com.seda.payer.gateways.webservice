package com.seda.payer.gateways.webservice.helper;

import com.seda.payer.gateways.facade.dto.RidDto;


public class Generics_RID {

	/**
	 * prefisso per il file di adesione del RID
	 */
	public static final String PREFIX_RID = "RID_";
	
	private static final String ADESIONE = "01";
	private static final String REVOCA = "02";
	private static final String CODICE_AEA_INCORSO = "00000";	
	/**
	 * risposta dall'XML del gateway S2S
	 */
	public static final String S2S_OK = "0";
	/**
	 * risposta OK nel payer
	 */
	public static final String PAYER_CODE_OK = "00";
	/**
	 * risposta KO nel payer
	 */
	private static final String PAYER_CODE_KO = "01";
	/**
	 * risposta che avviene in base all'allineamento gateways (P=positivo / N=Negativo)
	 */
	private static final String S2S_AEA_ESITO_POSITIVO = "P";
	
	public static String getCodiceFunzione(boolean bAdesione)
    {
    	return bAdesione ? ADESIONE : REVOCA;
    }
	public static boolean isFunzioneAdesione(RidDto ridFacade)
    {
    	return ridFacade.getCodiceFunzione().equalsIgnoreCase(ADESIONE) ? true : false;
    }
	
	/**
	 * Ritorna se  la banca ha dato esito positivo alla funzione relativa
	 * @param ridFacade
	 * @return
	 */
	public static boolean isRIDRichiestaAccettata(RidDto ridFacade)
    {
		// risposta 0 o 1 del gateway
    	return ridFacade.getCodiceEsitoRispostaS2S().equalsIgnoreCase(S2S_OK) ? true : false;
    }	
	/**
	 * Ritorna se  la banca ha dato esito positivo alla funzione relativa
	 * @param ridFacade
	 * @return
	 */
	public static boolean isRIDBancaEsitoPositivo(RidDto ridFacade)
    {
		// risposta in base al codice AEA ridFacade.getCodiceCausaleAEA()
    	return ridFacade.getEsitoCausaleAEA().equalsIgnoreCase(S2S_AEA_ESITO_POSITIVO) ? true : false;
    }
	/**
	 * Indica se il RID è incorso (la isRIDRichiestaAccettata deve essere true)
	 * @param ridFacade
	 * @return
	 */
	public static boolean isRIDInCorso(RidDto ridFacade)
    {
		// è in corso se il codice è specifico CODICE_AEA_INCORSO
    	return (ridFacade.getCodiceCausaleAEA().equalsIgnoreCase(CODICE_AEA_INCORSO) ? true : false);
    }

	/**
	 * Converte il codice di risposta S2s in un codice valido per il PAYER
	 * @param sCodiceFromS2S
	 * @return
	 */
	public static String convertValidResponseS2S(String sCodiceFromS2S)
	{
		if (sCodiceFromS2S.equals(Generics_RID.S2S_OK))
			return Generics_RID.PAYER_CODE_OK;
		else
			return Generics_RID.PAYER_CODE_KO;
	}
}
