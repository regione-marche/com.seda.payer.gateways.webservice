package com.seda.payer.gateways.facade.util;


public class RidAEARowEnd_EF extends RidAEARowInit_AL{

	public static final String AEA_ROW_END_EF = "EF";
	
	private Integer numRecord;
	
	public RidAEARowEnd_EF(String sRow)
	{
		super(sRow, AEA_ROW_END_EF);
		try
		{
			// prima colonna vuota
			this.setParam();
			if (this.valid)
			{			
				// dei campi sono analoghi al INIT_AL
				this.numRecord = Integer.parseInt(sRow.substring(82, 89));					
			}
		}
		catch (Exception ex)
		{		
			this.valid = false;
		}
	}
	
	/**
	 * confronta ulteriori stringhe tra la coda e e la testa
	 * @param rowInit
	 * @return
	 */
	public boolean compareRowInitEnd(RidAEARowInit_AL rowInit)
	{
		if (this.creazioneFlusso_ddMMyy.equals(rowInit.getCreazioneFlusso_ddMMyy()) &&
				this.nomeSupporto.equals(rowInit.getNomeSupporto()))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * numero di righe presenti nella comunicazione
	 * @return
	 */
	public Integer getNumRecord() {
		return numRecord;
	}

	
}
