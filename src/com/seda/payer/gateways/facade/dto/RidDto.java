package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.payer.core.bean.Rid;

public class RidDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codiceFunzione;
	private String codiceDebitore;
	private String codiceFiscale;
	private String cognomeOrRagSOc;
	private String codiceIBAN;
	private String idOperazione;
	private String nomeSottoscrittore;
	private String cognomeSottoscrittore;
	private String codFiscSottoscrittore;
	private String mailSottoscrittore;
	private String nomeIntestatario;
	private String cognomeIntestatario;
	private String codFiscIntestatario;
	private String userName;
	private String codiceEsitoRispostaS2S; // 1 o 0
	private String descrizioneEsitoRispostaS2S;
	
	private String codiceCausaleAEA;
	private String codiceRispostaS2SHTTP;
	private String descrizioneRispostaS2SHTTP;
	
	private Calendar dataInserimento;
	private Calendar dataAggiornamento;
	private String nomeOperatore;
	
	private String esitoCausaleAEA; 
	private String descrizioneeCausaleAEA;
	
	public RidDto()
	{
		this.dataAggiornamento = Calendar.getInstance();
		this.esitoCausaleAEA= "";
    	this.descrizioneeCausaleAEA= "";
   
	}
    public RidDto(Rid rid) { 

    	this.codiceFunzione = rid.getCodiceFunzione();
    	this.codiceDebitore = rid.getCodiceDebitore();
    	this.codiceFiscale = rid.getCodiceFiscale();
    	this.cognomeOrRagSOc = rid.getCognomeOrRagSOc();
    	this.codiceIBAN = rid.getCodiceIBAN();
    	this.idOperazione = rid.getIdOperazione();
    	this.nomeSottoscrittore = rid.getNomeSottoscrittore();
    	this.cognomeSottoscrittore = rid.getCognomeSottoscrittore();
    	this.codFiscSottoscrittore = rid.getCodFiscSottoscrittore();
    	this.mailSottoscrittore = rid.getMailSottoscrittore();
    	this.nomeIntestatario = rid.getNomeIntestatario();
    	this.cognomeIntestatario = rid.getCognomeIntestatario();
    	this.codFiscIntestatario = rid.getCodFiscIntestatario();
    	this.userName = rid.getUserName();
    	this.codiceEsitoRispostaS2S = rid.getCodiceEsitoRispostaS2S();
    	this.descrizioneEsitoRispostaS2S = rid.getDescrizioneEsitoRispostaS2S();
    	
    	this.codiceCausaleAEA = rid.getCodiceCausaleAEA();
    	this.codiceRispostaS2SHTTP = rid.getCodiceRispostaS2SHTTP();
    	this.descrizioneRispostaS2SHTTP = rid.getDescrizioneRispostaS2SHTTP();
    	
    	this.dataInserimento = rid.getDataInserimento();
    	this.dataAggiornamento = rid.getDataAggiornamento();
    	
    	this.nomeOperatore= rid.getNomeOperatore();
    	
    	// solo in GET
    	this.esitoCausaleAEA= rid.getEsitoCausaleAEA();
    	this.descrizioneeCausaleAEA= rid.getDescrizioneeCausaleAEA();
    }
    
    public Rid getRidCore()
    {
    	Rid ridCore = new Rid();
    	ridCore.setCodiceFunzione(this.getCodiceFunzione());
    	ridCore.setCodiceDebitore(this.getCodiceDebitore());
    	ridCore.setCodiceFiscale(this.getCodiceFiscale());
    	ridCore.setCognomeOrRagSOc(this.getCognomeOrRagSOc());
    	ridCore.setCodiceIBAN(this.getCodiceIBAN());
    	ridCore.setIdOperazione(this.getIdOperazione());
    	ridCore.setNomeSottoscrittore(this.getNomeSottoscrittore());
    	ridCore.setCognomeSottoscrittore(this.getCognomeSottoscrittore());
    	ridCore.setCodFiscSottoscrittore(this.getCodFiscSottoscrittore());
    	ridCore.setMailSottoscrittore(this.getMailSottoscrittore());
    	ridCore.setNomeIntestatario(this.getNomeIntestatario());
    	ridCore.setCognomeIntestatario(this.getCognomeIntestatario());
    	ridCore.setCodFiscIntestatario(this.getCodFiscIntestatario());
    	ridCore.setUserName(this.getUserName());
    	ridCore.setCodiceEsitoRispostaS2S(this.getCodiceEsitoRispostaS2S());
    	ridCore.setDescrizioneEsitoRispostaS2S(this.getDescrizioneEsitoRispostaS2S());
		
    	ridCore.setCodiceCausaleAEA(this.getCodiceCausaleAEA());
    	ridCore.setCodiceRispostaS2SHTTP(this.getCodiceRispostaS2SHTTP());
    	ridCore.setDescrizioneRispostaS2SHTTP(this.getDescrizioneRispostaS2SHTTP());
		
    	//readonly ridCore.setDataInserimento(this.getDataInserimento());
    	// readonly ridCore.setDataAggiornamento(this.getDataAggiornamento());
		
    	ridCore.setNomeOperatore(this.getNomeOperatore());
		
		// solo in GET
    	// readonly ridCore.setEsitoCausaleAEA(this.getEsitoCausaleAEA());
    	// readonly ridCore.setDescrizioneeCausaleAEA(this.getDescrizioneeCausaleAEA());
    	return ridCore;
    }
    
	public void setCodiceFunzione(String codiceFunzione) {
		this.codiceFunzione = codiceFunzione;
	}

	public String getCodiceFunzione() {
		return codiceFunzione;
	}

	public void setCodiceDebitore(String codiceDebitore) {
		this.codiceDebitore = codiceDebitore;
	}

	public String getCodiceDebitore() {
		return codiceDebitore;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCognomeOrRagSOc(String cognomeOrRagSOc) {
		this.cognomeOrRagSOc = cognomeOrRagSOc;
	}

	public String getCognomeOrRagSOc() {
		return cognomeOrRagSOc;
	}

	public void setCodiceIBAN(String codiceIBAN) {
		this.codiceIBAN = codiceIBAN;
	}

	public String getCodiceIBAN() {
		return codiceIBAN;
	}

	public void setIdOperazione(String idOperazione) {
		this.idOperazione = idOperazione;
	}

	public String getIdOperazione() {
		return idOperazione;
	}

	public void setNomeSottoscrittore(String nomeSottoscrittore) {
		this.nomeSottoscrittore = nomeSottoscrittore;
	}

	public String getNomeSottoscrittore() {
		return nomeSottoscrittore;
	}

	public void setCognomeSottoscrittore(String cognomeSottoscrittore) {
		this.cognomeSottoscrittore = cognomeSottoscrittore;
	}

	public String getCognomeSottoscrittore() {
		return cognomeSottoscrittore;
	}

	public void setCodFiscSottoscrittore(String codFiscSottoscrittore) {
		this.codFiscSottoscrittore = codFiscSottoscrittore;
	}

	public String getCodFiscSottoscrittore() {
		return codFiscSottoscrittore;
	}

	public void setMailSottoscrittore(String mailSottoscrittore) {
		this.mailSottoscrittore = mailSottoscrittore;
	}

	public String getMailSottoscrittore() {
		return mailSottoscrittore;
	}

	public void setNomeIntestatario(String nomeIntestatario) {
		this.nomeIntestatario = nomeIntestatario;
	}

	public String getNomeIntestatario() {
		return nomeIntestatario;
	}

	public void setCognomeIntestatario(String cognomeIntestatario) {
		this.cognomeIntestatario = cognomeIntestatario;
	}

	public String getCognomeIntestatario() {
		return cognomeIntestatario;
	}

	public void setCodFiscIntestatario(String codFiscIntestatario) {
		this.codFiscIntestatario = codFiscIntestatario;
	}

	public String getCodFiscIntestatario() {
		return codFiscIntestatario;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setCodiceEsitoRispostaS2S(String codiceEsitoRispostaS2S) {
		this.codiceEsitoRispostaS2S = codiceEsitoRispostaS2S;
	}

	public String getCodiceEsitoRispostaS2S() {
		return codiceEsitoRispostaS2S;
	}

	public void setDescrizioneEsitoRispostaS2S(
			String descrizioneEsitoRispostaS2S) {
		this.descrizioneEsitoRispostaS2S = descrizioneEsitoRispostaS2S;
	}

	public String getDescrizioneEsitoRispostaS2S() {
		return descrizioneEsitoRispostaS2S;
	}

	public void setCodiceCausaleAEA(String codiceCausaleAEA) {
		this.codiceCausaleAEA = codiceCausaleAEA;
	}

	public String getCodiceCausaleAEA() {
		return codiceCausaleAEA;
	}

	public void setCodiceRispostaS2SHTTP(String codiceRispostaS2SHTTP) {
		this.codiceRispostaS2SHTTP = codiceRispostaS2SHTTP;
	}

	public String getCodiceRispostaS2SHTTP() {
		return codiceRispostaS2SHTTP;
	}

	public void setDescrizioneRispostaS2SHTTP(String descrizioneRispostaS2SHTTP) {
		this.descrizioneRispostaS2SHTTP = descrizioneRispostaS2SHTTP;
	}

	public String getDescrizioneRispostaS2SHTTP() {
		return descrizioneRispostaS2SHTTP;
	}

	public Calendar getDataInserimento() {
		return dataInserimento;
	}

		public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}

	public String getNomeOperatore() {
		return nomeOperatore;
	}

	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

	public String getEsitoCausaleAEA() {
		return esitoCausaleAEA;
	}

	public String getDescrizioneeCausaleAEA() {
		return descrizioneeCausaleAEA;
	}
	
}