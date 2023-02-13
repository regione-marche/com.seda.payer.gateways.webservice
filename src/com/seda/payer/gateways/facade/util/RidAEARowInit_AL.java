package com.seda.payer.gateways.facade.util;

import java.util.Calendar;

public class RidAEARowInit_AL extends RidAEARowBase {

	public static final String AEA_ROW_INIT_AL = "AL";
	
	protected String creazioneFlusso_ddMMyy;
	protected String record_mitt_ricev;
	protected String nomeSupporto;
	
	public RidAEARowInit_AL(String sRow, String sFirstCode) 
	{
		super(sRow, sFirstCode);
	}
	public RidAEARowInit_AL(String sRow) 
	{
		super(sRow, AEA_ROW_INIT_AL);
		this.setParam();
	}
	
	protected void setParam()
	{
		try
		{		
			if (this.valid)
			{
				this.record_mitt_ricev = this.originalRow.substring(3, 13);
				this.creazioneFlusso_ddMMyy = this.originalRow.substring(13, 19);
				this.nomeSupporto = this.originalRow.substring(19, 39);
			}
		}
		catch (Exception ex)
		{	
			this.valid = false;
		}
	}
	
	public String getOriginalRow() {
		return originalRow;
	}

	public Calendar getCreazioneFlusso() {
		return convertDate_ddMMyy(this.creazioneFlusso_ddMMyy);
	}
	public String getCreazioneFlusso_ddMMyy() {
		return this.creazioneFlusso_ddMMyy;
	}
	public String getRecord_mitt_ricev() {
		return record_mitt_ricev;
	}
	public String getNomeSupporto() {
		return nomeSupporto;
	}
	
	
	
	
}
