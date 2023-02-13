package com.seda.payer.gateways.facade.util;

import java.io.File;

import com.seda.payer.gateways.facade.dto.RidDto;


public class Generics_RID {

	private static final String ADESIONE = "01";
	private static final String REVOCA = "02";
	private static final String CODICE_AEA_INCORSO = "00000";	
	/**
	 * risposta dall'XML del gateway
	 */
	private static final String S2S_OK = "0";
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
	 * @param f
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public static boolean moveFile(File f, String dir) throws Exception {
		return new File(f.getAbsolutePath()).renameTo(new File(dir + f.getName()));
	}

}
