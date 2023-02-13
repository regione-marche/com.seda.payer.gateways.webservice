package com.seda.payer.gateways.webservice.helper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class Generics {

	/**
	 * Convert string to array
	 * @param saaaaMMgghhmmss
	 * @param sAAAAmmgg
	 * @return
	 */
	public static boolean convertDate(String saaaaMMgghhmmss, String[] sAAAAmmgg)
	{
		sAAAAmmgg = new String[3];
		if ((saaaaMMgghhmmss != null) && (saaaaMMgghhmmss.length()==14))
		{
			try
			{
				sAAAAmmgg[0] = saaaaMMgghhmmss.substring(0, 4);
				sAAAAmmgg[1] = saaaaMMgghhmmss.substring(4, 6);
				sAAAAmmgg[2] = saaaaMMgghhmmss.substring(6, 8);
				return true;
			}
			catch (Exception ex)
			{
				//LogWriter.logError("Conversione data dal DB non validi", ex);
				return false;
			}
		}
		
		return false;
	}
	
	public static Integer getIntFromString(String sInt, Logger logger)
	{	
		try
		{
			if (sInt!=null && sInt.length()!=0)
				return Integer.parseInt(sInt);
			else
				return 0;
		}
		catch (Exception ex)
		{
			logger.error("Agenda.Facade getIntFromString value:"+sInt, ex);	
			return 0;
		}
	}
	
	public static void getDecimalPartInt(BigDecimal bdTemp, String[] sInt_Centesimi)
	{
		// big decimal XXXX00 --> dove 00 è la parte decimale
		if (bdTemp != new BigDecimal(0))
		{
			String sBigValue = bdTemp.toBigInteger().toString();
			sInt_Centesimi[0] = sBigValue.substring(0, sBigValue.length()-2);
			sInt_Centesimi[1] = sBigValue.substring(sBigValue.length()-2, sBigValue.length());
		}
		else
		{
			sInt_Centesimi[0] = "0";
			sInt_Centesimi[1] = "00";			
		}	
	}
	
	/**
	 * ritorna l'array con due valori Intero e decimali
	 * @param bdTemp
	 * @param sInt_Centesimi
	 */
	public static void getStringsFromDecimal(BigDecimal bdTemp, String[] sInt_Centesimi)
	{		
		String sBigValue = formatDecimalNumber(bdTemp);
		// sBigValue = XXXX,00
		sInt_Centesimi[0] = sBigValue.substring(0, sBigValue.length()-3);
		sInt_Centesimi[1] = sBigValue.substring(sBigValue.length()-2, sBigValue.length());
	}
	
	public static void getLongPartIntDecimal(Long lTemp, String[] sInt_Centesimi)
	{
		// big decimal XXXX00 --> dove 00 è la parte decimale
		if (lTemp != 0L)
		{
			String sBigValue = lTemp.toString();
			sInt_Centesimi[0] = sBigValue.substring(0, sBigValue.length()-2);
			sInt_Centesimi[1] = sBigValue.substring(sBigValue.length()-2, sBigValue.length());
		}
		else
		{
			sInt_Centesimi[0] = "0";
			sInt_Centesimi[1] = "00";			
		}	
	}
	
	public static void getDecimalPartInt(String sBigValue, String[] sInt_Centesimi)
	{		
		// big decimal XXXX00 --> dove 00 è la parte decimale
		//Integer iDecimal = Integer.getInteger(sBigValue);
		if (sBigValue.length() > 0 )
		{
			//String sBigValue = iDecimal.toString();
			sInt_Centesimi[0] = sBigValue.substring(0, sBigValue.length()-2);
			sInt_Centesimi[1] = sBigValue.substring(sBigValue.length()-2, sBigValue.length());
		}
		else
		{
			sInt_Centesimi[0] = "0";
			sInt_Centesimi[1] = "00";			
		}	
	}
	
	public static BigDecimal getBigDecimalFromString(String sParteIntera, String sParteDecimale)
	{
		BigDecimal bdRes = new BigDecimal(0);
		if (sParteIntera.length()!=0 && sParteDecimale.length()!=0)
		{
			double dParteDecimale = Integer.valueOf(sParteDecimale) / 100.0;
			double dRes = Integer.valueOf(sParteIntera) + dParteDecimale;
			
			bdRes = new BigDecimal(dRes);
		}
		
		return bdRes;
	}
	
	public static BigDecimal getBigDecimalFromString(String sImporto)
	{
		//importo = XXX,YY
		String[] aImporto = sImporto.split("\\,");
		if (aImporto.length == 2)
		{
			return getBigDecimalFromString(aImporto[0], aImporto[1]);
		}
		else
			return BigDecimal.ZERO;
	}
	
	/**
	 * Splitta con l'elemento "|"
	 * @param iNumStrings: numero di elementi attesi nello split
	 * @param sToSPlit: stringa da separare
	 * @return l'array con le stringhe splittate
	 */
	public static String[] getSplit_NString(int iNumStrings, String sToSplit)
	{
		String[] sSplit = null;
		if (sToSplit != null)
		{
			String[] sSplitTemp = sToSplit.split("\\|",-1);
			
			if (sSplitTemp.length != iNumStrings)
			{
				sSplit = new String[iNumStrings];
				for (int k=0; k<iNumStrings; k++)
					sSplit[k] = "";
			}
			else
				sSplit = sSplitTemp;	
		}
		else
		{
			sSplit = new String[iNumStrings];
			for (int k=0; k<iNumStrings; k++)
				sSplit[k] = "";
		}
			
		return sSplit;
	}
	
	
	/**
	 * Dato un intero, lo divide per 100 e ritorna il valore formattato con 2 decimali e la virgola come separatore decimale.
	 * @param iValue
	 * @return
	 */
	/*public static String formatDecimalNumber(int iValue)
	{
		DecimalFormat dcFormat = getDecimalFormat();
		
		double dApp = iValue / 100.0;
		BigDecimal bdApp = new BigDecimal(dApp);
		bdApp = bdApp.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return dcFormat.format(dApp);
	}*/
	
	public static String formatDecimalNumber(BigDecimal bdValue)
	{
		DecimalFormat dcFormat = getDecimalFormat();
		bdValue = bdValue.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return dcFormat.format(bdValue);
	}
	
	public static DecimalFormat getDecimalFormat()
	{
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(); 
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');

		DecimalFormat dcFormat = new DecimalFormat("#0.00", symbols);
		return dcFormat;
	}
	
	/**
	 * Dato un intero, lo divide per 100 e ritorna il valore formattato con 2 decimali e la virgola come separatore decimale.
	 * @param iValue
	 * @return
	 */
	public static String formatDateTime_gg_MM_aaaa(String saaaaMMgghhmmss)
	{
		if (saaaaMMgghhmmss.length() == 14)
		{
			return saaaaMMgghhmmss.substring(6, 8) +""+saaaaMMgghhmmss.substring(4, 6)+""+saaaaMMgghhmmss.substring(0, 4);			
		}
		else
			return "";
	}
	
	/*
	 * Ritorna la data corrente
	 */
	public static Calendar getCurrentDate()
	{
		return Calendar.getInstance();
	}
	
	/**
	 * Ritorna GG/MM/AAAA
	 * @param data
	 * @return
	 */
	public static String formatCalendarData(Calendar data)
	{
		if (data != null)
		{
			Calendar cal = Calendar.getInstance(Locale.ITALIAN);
			cal.setTime(data.getTime());
		
			return formatNumToString(2, String.valueOf(cal.get(Calendar.DAY_OF_MONTH))) + "/" +
				Generics.formatNumToString(2, String.valueOf((cal.get(Calendar.MONTH) + 1))) + "/" +
				String.valueOf(cal.get(Calendar.YEAR));
		}
		else
			return "";
	}
	
	/**
	 * Formato ora HH:MM:SS
	 * @param data
	 * @return
	 */
	public static String formatCalendarOra(Calendar data)
	{
		if (data != null)
		{
			Calendar cal = Calendar.getInstance(Locale.ITALIAN);
			cal.setTime(data.getTime());
		
			return Generics.formatNumToString(2, String.valueOf(cal.get(Calendar.HOUR_OF_DAY))) + ":" +
				Generics.formatNumToString(2, String.valueOf(cal.get(Calendar.MINUTE))) + ":" +
				Generics.formatNumToString(2, String.valueOf(cal.get(Calendar.SECOND)));
		}
		else
			return "";
	}
	
	/*********************************** OPERAZIONI DATE - CALENDAR **************************/
	public static Calendar getMinDate()
	{
		Timestamp timestamp = Timestamp.valueOf("1000-01-01 00:00:00.000");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());

		//Calendar cal = Calendar.getInstance();
		//cal.set(1970,0,1,0,0,0);
		return cal;
	}
	
	
	/***
	 * Ritorna un Calendar da una data passata nel formato passato come parametro
	 * @param sDataDDMMYYYY
	 * @return
	 */
	public static Calendar getCalendarFromDateString(String sData, String sDateFormat)
	{
		if (sData != null && !sData.equals(""))
		{
			try
			{
				SimpleDateFormat formatDateTime = new SimpleDateFormat(sDateFormat);
				Date date = formatDateTime.parse(sData);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return cal;
			} 
			catch (Exception e) {
				return getMinDate();
			}
		}
		
		return getMinDate();
	}
	
	public static String formatNumToString(int iLenght, String sNumToFormat)
	{
		String formattedString = sNumToFormat;

		while(formattedString.length() < iLenght) 
		{
			formattedString = "0" + formattedString;
		}
		return formattedString;
	}
	
	public static String getMonthName(String sMonth)
	{
		if (sMonth.equals("01"))
			return "Gennaio";
		else if (sMonth.equals("02"))
			return "Febbraio";
		else if (sMonth.equals("03"))
			return "Marzo";
		else if (sMonth.equals("04"))
			return "Aprile";
		else if (sMonth.equals("05"))
			return "Maggio";
		else if (sMonth.equals("06"))
			return "Giugno";
		else if (sMonth.equals("07"))
			return "Luglio";
		else if (sMonth.equals("08"))
			return "Agosto";
		else if (sMonth.equals("09"))
			return "Settembre";
		else if (sMonth.equals("10"))
			return "Ottobre";
		else if (sMonth.equals("11"))
			return "Novembre";
		else if (sMonth.equals("12"))
			return "Dicembre";
		else
			return "";
	}
	
}
