package com.seda.payer.gateways.facade.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RidAEARowBase {

	protected String originalRow;
	protected boolean valid;
	
	
	public RidAEARowBase()
	{
		this.valid = false;
	}
	
	public RidAEARowBase(String sRow, String sFirstCode) 
	{
		this.valid = false;
		this.originalRow = sRow;
		try
		{
			// prima colonna vuota
			if (this.originalRow.substring(0, 1).equals(" "))
			{
				if (this.originalRow.substring(1, 3).equals(sFirstCode))
					this.valid = true;
			}
		}
		catch (Exception ex)
		{		
		}
	}
	
	public boolean isValid() {
		return valid;
	}
	
	/**
	 * Convert string to array
	 * @param saaaaMMgghhmmss
	 * @param sAAAAmmgg
	 * @return
	 */
	public static Calendar convertDate_ddMMyy(String sggMMyy)
	{		
		Calendar cal = Calendar.getInstance();
		try
		{
			String sgg = sggMMyy.substring(0, 2);
			String sMM = sggMMyy.substring(2, 4);
			String syy = sggMMyy.substring(4, 6);
			String syyyy = "20"+syy;
			cal = convertDate_ddMMyyyy(sgg+sMM+syyyy);
		}
		catch (Exception ex)
		{
			cal.set(1, Calendar.JANUARY, 1);
		}
		return cal;
		
	}
	
	/**
	 * DD/MM/AAAA converte in AAAAMMDD
	 * @param sDDMMAAAA
	 * @return
	 */
	public static Calendar convertDate_ddMMyyyy(String sDDMMAAAA)
	{
		Calendar cal = Calendar.getInstance();
		try
		{
		    SimpleDateFormat formatterIT = new SimpleDateFormat("ddMMyyyy");
		    java.util.Date utilDate = formatterIT.parse(sDDMMAAAA);
		    cal.setTime(utilDate);			   
		}
		catch (Exception ex)
		{
			cal.set(1, Calendar.JANUARY, 1);  
			
		}
		
		return cal;
	}    
	
}
