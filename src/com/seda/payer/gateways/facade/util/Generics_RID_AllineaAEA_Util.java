package com.seda.payer.gateways.facade.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.payer.gateways.facade.handler.PropertiesPath;

public class Generics_RID_AllineaAEA_Util {

	private static boolean bSabatoFestivo = true;
	private static boolean bDomenicaFestivo = true;	
	private static int iNumGiorniLavorativiRID = 10;
	
	
	public static Calendar getDate_AutoRevoca(PropertiesTree propertiesTree, Logger logger)
	{		
		//inizializzo la data ad oggi e sottraggo gli X giorni lavorativi
		Calendar cData = Calendar.getInstance();
		// non lavoro con HH:MM:SS:mm
		cData.set(Calendar.HOUR_OF_DAY, 0);
		cData.set(Calendar.MINUTE, 0);
		cData.set(Calendar.SECOND, 0);
		cData.set(Calendar.MILLISECOND, 0);

		
		Hashtable<Integer, List<Integer>>  htMonthDay = getParamRID(propertiesTree, logger);
		int iNumberDayValid = iNumGiorniLavorativiRID;
		
		// lo zero è il valore desiderato
		iNumberDayValid--;

		boolean bFound = false;
		boolean bFeriale = false;
		//cerco il primo giorno feriale passati iNumDayOfPayement feriali
        while (!(bFound && bFeriale))
        {
         	// il giorno corrente della scadenza viene valutato se è feriale
			// il giorno trovato se non è festivo è valido per decrementare il numero di giorni per ottenere il RID
        	if (isFestivo(htMonthDay, cData) ||
				isDomenica_Festivo(cData) ||
				isSabato_Festivo(cData) )
            {
                bFeriale = false;
            }
            else
            {
                // giorno valido per i giorni di attesa
                bFeriale = true;
            }
        	
        	// controllo se sono trascorsi 8 gg feriali per pagare con il RID
        	if (iNumberDayValid <= 0)
                bFound = true;

           
        	// se non è stato trovato il giorno corretto continuo a decrementare il giorno
            // e decremento il numero di giorni da attendere se feriale
            if (!(bFound && bFeriale))
            {
            	cData.add(Calendar.DATE, -1);
                if (bFeriale)
                	iNumberDayValid--;
            }			
		}

		return cData;		
	}
	
	
	private static boolean isFestivo(Hashtable<Integer, List<Integer>> hMonthDay, Calendar cActual)
	{
		if (hMonthDay.containsKey((int)cActual.get(Calendar.MONTH)))
		{
			List<Integer> lDay = hMonthDay.get((int)cActual.get(Calendar.MONTH));
			if (lDay.contains((int)cActual.get(Calendar.DAY_OF_MONTH)))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Ritorna un hash con key i mesi e una lista di giorni festivi
	 * @return
	 */
	private static Hashtable<Integer, List<Integer>>  getMeseGiornoFestivo(String sFestivi)
	{
		Hashtable<Integer, List<Integer>>  hm = new Hashtable<Integer, List<Integer>>();
		String[] sSplit_MeseGiorno = sFestivi.split(",");
		if (sSplit_MeseGiorno!=null && sSplit_MeseGiorno.length > 0)
		{
			int[] iMonthDay = null;
			for (String sSingleValue : sSplit_MeseGiorno)
			{
				iMonthDay = getSingleDate_MonthDay(sSingleValue);
				if (iMonthDay!=null && iMonthDay.length==2)
				{
					List<Integer> lDay = null;
					if (hm.containsKey(iMonthDay[0]))
					{
						lDay = hm.get(iMonthDay[0]);
						if (!lDay.contains(iMonthDay[1]))
							lDay.add(iMonthDay[1]);							
					}
					else
					{
						lDay = new ArrayList<Integer>();
						lDay.add(iMonthDay[1]);
						hm.put(iMonthDay[0], lDay);
					}
				}
			}
		}
		
		return hm;
	}
	
	/**
	 * Setta i parametri del rid e ritorna l'hash con i mesi/giorni
	 */
	private static Hashtable<Integer, List<Integer>> getParamRID(PropertiesTree propertiesTree, Logger logger)
	{
		bSabatoFestivo = true;
		bDomenicaFestivo = true;
		iNumGiorniLavorativiRID = 10;
		String sValue = "";
		Hashtable<Integer, List<Integer>> hmMM_Day = new Hashtable<Integer, List<Integer>>();
		
		try
		{
			//leggo la properties nel formato RID
			// numero di giorni lavorativi per il RID
			sValue = propertiesTree.getProperty(PropertiesPath.rid_giorni_accettazione_revoca_auto.format());
			iNumGiorniLavorativiRID = Integer.parseInt(sValue);
		} catch (Exception e) {
			logger.warn("Generics_RID_AllineaAEA_Util: ERRORE durante la lettura del parametro 'RID.giorni_accettazione_revoca_auto' dal file di properties" , e);
		}
		try
		{
			// sabati festivi
			sValue = propertiesTree.getProperty(PropertiesPath.rid_sabati_festivi.format());
			bSabatoFestivo = (sValue != null && sValue.equals("Y"));
		} catch (Exception e) {
			logger.warn("Generics_RID_AllineaAEA_Util: ERRORE durante la lettura del parametro 'RID.sabatiFestivi' dal file di properties" , e);
		}	
		try
		{
			// domeniche festivi
			sValue = propertiesTree.getProperty(PropertiesPath.rid_domeniche_festive.format());
			bDomenicaFestivo =(sValue != null && sValue.equals("Y"));
		} catch (Exception e) {
			logger.warn("Generics_RID_AllineaAEA_Util: ERRORE durante la lettura del parametro 'RID.domenicheFestive' dal file di properties" , e);
		}
		try
		{
			// giorni festivi
			sValue = propertiesTree.getProperty(PropertiesPath.rid_elenco_giorni_festivi.format());			
			hmMM_Day = getMeseGiornoFestivo(sValue);
		} catch (Exception e) {
			logger.warn("Generics_RID_AllineaAEA_Util: ERRORE durante la lettura del parametro RID.giorniFestivi dal file di properties" , e);
		}
		
				
		// converto i giorni festivi
		return hmMM_Day;
	}
	
	private static int[] getSingleDate_MonthDay(String sSingleValue)
	{		
		int[] sMese_Giorno = new int[2];

		String[] sSplit_MeseGiorno = sSingleValue.split("/");
		if (sSplit_MeseGiorno.length == 2 )
		{
			sMese_Giorno = new int[2];
			// il calendario in JAVA è tale per cui gennaio non è 1 ma 0
			// gg/MM
			sMese_Giorno[1] = Integer.parseInt(sSplit_MeseGiorno[0]);
			sMese_Giorno[0] = Integer.parseInt(sSplit_MeseGiorno[1])-1;
		}

		return sMese_Giorno;
	}
	
	/**
	 * Controlla la data (se il domenica è richiesto festivo) se è domenica festivo
	 * @param cal
	 * @return
	 */
	private static boolean isDomenica_Festivo(Calendar cal)
	{
		if (bSabatoFestivo)
		{
			int iDay = cal.get(Calendar.DAY_OF_WEEK);         //1=domenica 4=Wednesday
			
			//lo trasformo in modo che l'1 sia lunedì e il 7 domenica
			return iDay == Calendar.SUNDAY;
		}
		else
			return false;
	}
	/**
	 * Controlla la data (se il sabato è richiesto festivo) se è sabato festivo
	 * @param cal
	 * @return
	 */
	private static boolean isSabato_Festivo(Calendar cal)
	{
		if (bDomenicaFestivo)
		{
			int iDay = cal.get(Calendar.DAY_OF_WEEK);         //1=domenica 4=Wednesday
			
			//lo trasformo in modo che l'1 sia lunedì e il 7 domenica
			return iDay == Calendar.SATURDAY;
		}
		else
			return false;
			
	}
}
