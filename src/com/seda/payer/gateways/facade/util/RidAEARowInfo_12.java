package com.seda.payer.gateways.facade.util;

import java.util.Calendar;


public class RidAEARowInfo_12 extends RidAEARowInfoBase {

	public static final String AEA_ROW_INFO_12 = "12";
	private String creazioneFlusso_ddMMyy;
	private String creazioneDisposizione_ddMMyy;
	private String codiceCausale;
	private String codiceIBAN;
	private String tipoCodiceCliente;
	private String codiceDebitore;
	
	public RidAEARowInfo_12()
	{}
	
	public RidAEARowInfo_12(String sRow)
	{
		super(sRow, AEA_ROW_INFO_12);
		try
		{
			// prima colonna vuota
			if (this.valid)
			{
				// controllo di altri codici RidAEARowInfoBase
				this.creazioneFlusso_ddMMyy = this.originalRow.substring(10, 16);
				this.creazioneDisposizione_ddMMyy = this.originalRow.substring(16, 22);
				this.codiceCausale = this.originalRow.substring(28, 33);
				
				// codice IBAN formato da più campi				
				this.codiceIBAN = this.originalRow.substring(43, 45);
				this.codiceIBAN += this.originalRow.substring(45, 47);
				this.codiceIBAN += this.originalRow.substring(68, 91);
				
				
				this.tipoCodiceCliente = this.originalRow.substring(96, 97);
				if (this.tipoCodiceCliente.equals("4"))
				{
					// il codice del tipo è cliente deve essere 4 altrimenti errore 
					this.codiceDebitore = this.originalRow.substring(97, 113);						
				}
				else
					this.valid = false;
			}
		}
		catch (Exception ex)
		{	
			this.valid = false;
		}
	}
	
	public Calendar getCreazioneFlusso() {
		return convertDate_ddMMyy(this.creazioneFlusso_ddMMyy);
	}
	public Calendar getCreazioneDisposizione() {
		return convertDate_ddMMyy(this.creazioneDisposizione_ddMMyy);
	}
	public String getCodiceCausale() {
		return codiceCausale;
	}
	public String getCodiceIBAN() {
		return codiceIBAN;
	}
	public String getCodiceDebitore() {
		return codiceDebitore;
	}
	
	public void setCodiceCausale(String codiceCausale) {
		this.codiceCausale = codiceCausale;
	}
	
	public void setCodiceDebitore(String codiceDebitore) {
		this.codiceDebitore = codiceDebitore;
	}
}
