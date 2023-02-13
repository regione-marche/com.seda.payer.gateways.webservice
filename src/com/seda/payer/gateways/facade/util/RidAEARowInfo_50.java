package com.seda.payer.gateways.facade.util;


public class RidAEARowInfo_50 extends RidAEARowInfoBase {

	public static final String AEA_ROW_INFO_50 = "50";
	
	public RidAEARowInfo_50(String sRow)
	{
		super(sRow, AEA_ROW_INFO_50);
		try
		{
			// prima colonna vuota
			if (this.valid)
			{
				// controllo di altri codici
//				this.record_mitt_ricev = sRow.substring(3, 13);
//				this.creazioneFlusso_ddMMyy = sRow.substring(13, 19);
//				this.nomeSupporto = sRow.substring(19, 39);
//				this.numRecord = Integer.parseInt(sRow.substring(82, 89));
			}
		}
		catch (Exception ex)
		{	
			this.valid = false;
		}
	}
}
