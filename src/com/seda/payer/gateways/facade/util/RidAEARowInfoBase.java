package com.seda.payer.gateways.facade.util;


public class RidAEARowInfoBase extends RidAEARowBase {

	public String numeroProgressivo;
	
	public RidAEARowInfoBase()
	{}
	
	public RidAEARowInfoBase(String sRow, String sFirstCode)
	{
		super(sRow, sFirstCode);
		try
		{
			if (this.valid)
			{
				this.numeroProgressivo = this.originalRow.substring(3, 10);
			}
		}
		catch (Exception ex)
		{	
			this.valid = false;
		}
	}
	
	public boolean compareRowInfoBase(RidAEARowInfoBase rid)
	{
		return this.numeroProgressivo.equals(rid.getNumeroProgressivo());
	}
	
	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}
	
}
