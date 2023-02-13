package com.seda.payer.gateways.webservice.helper;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.codec.binary.Base64;

import com.seda.data.DataFormat;
import com.seda.payer.commons.security.UUIDGenerator;
import com.seda.payer.commons.utility.NumberUtility;
import com.seda.payer.commons.utility.StringUtility;

public class RedirectToGTWIGRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String canaleAttivazione;
	private String strumentoPagamento;
	private String idOperazione;
	private java.math.BigDecimal importoNetto;
	private java.math.BigDecimal commissioni;
	private String codiceMerchant;
    private java.lang.String apiEndPointUrl;
    private java.lang.String tokenCsrf;
    private java.lang.String urlOk;
    private java.lang.String urlKo; 
    private java.lang.String urlAnnullo;

    private java.lang.String codDebitore;
    private java.lang.String userName;
    private java.lang.String scadenzaPagamento;
    private java.lang.String causalePagamento;

    private java.lang.String mav_cognomeOrRagioneSocialeDebitore;
    private java.lang.String mav_nomeDebitore;		
    private java.lang.String mav_indirizzoDebitore;		
    private java.lang.String mav_capDebitore;		
    private java.lang.String mav_localitaDebitore;		
    private java.lang.String mav_provinciaDebitore;	
    
    private java.lang.String identificativiDocumenti;
    private java.lang.String mailUtente;	
	
	public RedirectToGTWIGRequest(String canaleAttivazione, String strumentoPagamento, 
								  java.math.BigDecimal importoNetto, 
								  java.math.BigDecimal commissioni, 
								  String codiceMerchant,
								  java.lang.String apiEndPointUrl,
								  java.lang.String tokenCsrf,
								  java.lang.String urlOk, /* urlSitoWebGateway - GTW_DGTWURLS */
								  java.lang.String urlKo, /* urlApiRedirect - GTW_DGTWARED */ 
								  java.lang.String urlAnnullo, /* urlApiCancel - GTW_DGTWACLS */
								  java.lang.String identificativiDocumenti,
								  java.lang.String mailUtente) throws Exception {
		this.canaleAttivazione = canaleAttivazione;
		this.strumentoPagamento = strumentoPagamento;
		this.importoNetto = importoNetto;
		this.commissioni = commissioni;
		this.codiceMerchant = codiceMerchant;
		this.idOperazione = (String) new UUIDGenerator(UUIDGenerator.BIT_FRMT_12).generateSecureToken();
		this.apiEndPointUrl = apiEndPointUrl;
		this.tokenCsrf = tokenCsrf;
	    this.urlOk = urlOk;
	    this.urlKo = urlKo; 
	    this.urlAnnullo = urlAnnullo;
	    this.identificativiDocumenti = identificativiDocumenti;
	    this.mailUtente = mailUtente;
	}
	
	public RedirectToGTWIGRequest(String canaleAttivazione, String strumentoPagamento, 
			  java.math.BigDecimal importoNetto, 			  
			  String codiceMerchant,
			  String idOperazione,
			  String apiEndPointUrl) throws Exception {
						this.canaleAttivazione = canaleAttivazione;
						this.strumentoPagamento = strumentoPagamento;
						this.importoNetto = importoNetto;
						this.codiceMerchant = codiceMerchant;
						this.idOperazione = idOperazione;
						this.apiEndPointUrl = apiEndPointUrl;
						}
	
	
	public RedirectToGTWIGRequest(String canaleAttivazione, String strumentoPagamento, 
			  java.math.BigDecimal importoNetto, 
			  java.math.BigDecimal commissioni, 
			  String codiceMerchant,
			  java.lang.String apiEndPointUrl,
			  java.lang.String tokenCsrf,
			  java.lang.String urlOk, /* urlSitoWebGateway - GTW_DGTWURLS */
			  java.lang.String urlKo, /* urlApiRedirect - GTW_DGTWARED */ 
			  java.lang.String urlAnnullo, /* urlApiCancel - GTW_DGTWACLS */
			  String sCodDebitore,
			  String suserName,
			  String sScadenzaPagamento,
			  String sCausalePagamento) throws Exception
				  
	  {
		this(canaleAttivazione, 
			strumentoPagamento,
			importoNetto, 
			commissioni, 
			codiceMerchant,
			apiEndPointUrl,
			tokenCsrf,
			urlOk,
			urlKo, 
			urlAnnullo,
			null,
			null);
		
		 // costruttore RID
		 this.codDebitore = sCodDebitore;
	     this.userName = suserName;
	     this.scadenzaPagamento = sScadenzaPagamento;
	     this.causalePagamento = sCausalePagamento;

	}
	
	public RedirectToGTWIGRequest(String canaleAttivazione, String strumentoPagamento, 
			  java.math.BigDecimal importoNetto, 
			  java.math.BigDecimal commissioni, 
			  String codiceMerchant,
			  java.lang.String apiEndPointUrl,
			  java.lang.String tokenCsrf,
			  java.lang.String urlOk, /* urlSitoWebGateway - GTW_DGTWURLS */
			  java.lang.String urlKo, /* urlApiRedirect - GTW_DGTWARED */ 
			  java.lang.String urlAnnullo, /* urlApiCancel - GTW_DGTWACLS */
			  String sCodDebitore,
			  String sUserName,
			  String sScadenzaPagamento,
			  String sCausalePagamento,
			  String cognomeOrRagioneSocialeDebitore,
			  String nomeDebitore,
			  String indirizzoDebitore,
			  String capDebitore,
			  String localitaDebitore,
			  String provinciaDebitore) throws Exception
				  
	  {
		this(canaleAttivazione, 
			strumentoPagamento,
			importoNetto, 
			commissioni, 
			codiceMerchant,
			apiEndPointUrl,
			tokenCsrf,
			urlOk,
			urlKo, 
			urlAnnullo,
			sCodDebitore,
			sUserName,
			sScadenzaPagamento,
			sCausalePagamento);
		
		// costruttore MAV
		this.mav_cognomeOrRagioneSocialeDebitore = cognomeOrRagioneSocialeDebitore;
   		this.mav_nomeDebitore = nomeDebitore;		
   		this.mav_indirizzoDebitore = indirizzoDebitore;		
   		this.mav_capDebitore = capDebitore;		
   		this.mav_localitaDebitore = localitaDebitore;		
   		this.mav_provinciaDebitore = provinciaDebitore;	
   		
	}

	public String getCanaleAttivazione() {
		return canaleAttivazione;
	}

	public void setCanaleAttivazione(String canaleAttivazione) {
		this.canaleAttivazione = canaleAttivazione;
	}

	public String getStrumentoPagamento() {
		return strumentoPagamento;
	}

	public void setStrumentoPagamento(String strumentoPagamento) {
		this.strumentoPagamento = strumentoPagamento;
	}

	public String getIdOperazione() {
		return idOperazione;
	}

	public void setIdOperazione(String idOperazione) {
		this.idOperazione = idOperazione;
	}

	public java.math.BigDecimal getImportoNetto() {
		return importoNetto;
	}

	public void setImportoNetto(java.math.BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}

	public java.math.BigDecimal getCommissioni() {
		return commissioni;
	}

	public void setCommissioni(java.math.BigDecimal commissioni) {
		this.commissioni = commissioni;
	}

	public String getCodiceMerchant() {
		return codiceMerchant;
	}

	public void setCodiceMerchant(String codiceMerchant) {
		this.codiceMerchant = codiceMerchant;
	}

	public java.lang.String getApiEndPointUrl() {
		return apiEndPointUrl;
	}

	public void setApiEndPointUrl(java.lang.String apiEndPointUrl) {
		this.apiEndPointUrl = apiEndPointUrl;
	}

	public java.lang.String getTokenCsrf() {
		return tokenCsrf;
	}

	public void setTokenCsrf(java.lang.String tokenCsrf) {
		this.tokenCsrf = tokenCsrf;
	}

	public java.lang.String getUrlOk() {
		return urlOk;
	}

	public void setUrlOk(java.lang.String urlOk) {
		this.urlOk = urlOk;
	}

	public java.lang.String getUrlKo() {
		return urlKo;
	}

	public void setUrlKo(java.lang.String urlKo) {
		this.urlKo = urlKo;
	}

	public java.lang.String getUrlAnnullo() {
		return urlAnnullo;
	}

	public void setUrlAnnullo(java.lang.String urlAnnullo) {
		this.urlAnnullo = urlAnnullo;
	}

	public String xmlTextCartaDiCredito() throws Exception {
		String identificativiDocumentiBase64 = "";
		try {
			identificativiDocumentiBase64 = new String(Base64.encodeBase64(identificativiDocumenti.getBytes("UTF-8")));
		} catch (Exception e) {}
		
		if (identificativiDocumentiBase64.length() > 3000)
			identificativiDocumentiBase64 = ""; //NB: non si può troncare la stringa codificata in base64, altrimenti non può più essere decodificata
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<payment>" +
				   "<initPayment>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + canaleAttivazione + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + strumentoPagamento + "</strumentoPagamento>" +
							   "<idOperazione>" + idOperazione + "</idOperazione>" +
							   "<importoNetto>" + impToString(importoNetto) + "</importoNetto>" +
							   "<commissioni>" + impToString(commissioni) + "</commissioni>" +
						   "</generic>" +
						   "<specific>" +
						   		"<codiceMerchant>" + codiceMerchant + "</codiceMerchant>" +
						   		"<mailUtente>" + mailUtente + "</mailUtente>" +
						   		"<identificativiDocumenti>" + identificativiDocumentiBase64 + "</identificativiDocumenti>" +
						   "</specific>" +
					   "</data>" +
				   "</initPayment>" +
			   "</payment>";
	}
	
	public String xmlTextPagoInConto() throws Exception {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<payment>" +
				   "<initPayment>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + canaleAttivazione + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + strumentoPagamento + "</strumentoPagamento>" +
							   "<idOperazione>" + idOperazione + "</idOperazione>" +
							   "<importoNetto>" + impToString(importoNetto) + "</importoNetto>" +
							   "<commissioni>" + impToString(commissioni) + "</commissioni>" +
						   "</generic>" +
						   "<specific>" +
						   		"<codiceMerchant>" + this.codiceMerchant + "</codiceMerchant>" +
							    "<UrlOK><![CDATA[" + getFullUrlOk() + "]]></UrlOK>" + 
							    "<UrlKO><![CDATA[" + getFullUrlKo() + "]]></UrlKO>" +
						        "<UrlAnnullo><![CDATA[" + getFullUrlAnnullo() + "]]></UrlAnnullo>" +
						   "</specific>" +
					   "</data>" +
				   "</initPayment>" +
			   "</payment>";
	}

	
	public String xmlTextStorno() throws Exception {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<service>" +
				   "<function>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + canaleAttivazione + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + strumentoPagamento + "</strumentoPagamento>" +
							   "<codiceFunzione>03</codiceFunzione>" +
						   "</generic>" +
						   "<specific>" +
						   		"<idOperazione>" + idOperazione + "</idOperazione>" +
						   		"<codiceMerchant>" + codiceMerchant + "</codiceMerchant>" +
						   		"<importoNetto>" + impToString(importoNetto) + "</importoNetto>" +							        
						   "</specific>" +
					   "</data>" +
				   "</function>" +
			   "</service>";
	}
	
	
	public String xmlTextRID() throws Exception {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<payment>" +
				   "<initPayment>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + canaleAttivazione + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + strumentoPagamento + "</strumentoPagamento>" +
							   "<idOperazione>" + idOperazione + "</idOperazione>" +
							   "<importoNetto>" + impToString(importoNetto) + "</importoNetto>" +
							   "<commissioni>" + impToString(commissioni) + "</commissioni>" +
						   "</generic>" +
						   "<specific>" +
						   		"<codiceMerchant>" + this.codiceMerchant + "</codiceMerchant>" +
						   		"<codiceDebitore>" + this.codDebitore + "</codiceDebitore>" +
						   		"<userName>" + this.codDebitore + "</userName>" +
						   		"<scadenzaPagamento>" + this.scadenzaPagamento + "</scadenzaPagamento>" +
						   		"<causalePagamento>" + this.causalePagamento + "</causalePagamento>" +							       
						   "</specific>" +
					   "</data>" +
				   "</initPayment>" +
			   "</payment>";
	}
	public String xmlTextMAV() throws Exception {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			   "<payment>" +
				   "<initPayment>" +
					   "<data>" +
						   "<generic>" +
							   "<canaleAttivazione>" + canaleAttivazione + "</canaleAttivazione>" +
							   "<strumentoPagamento>" + strumentoPagamento + "</strumentoPagamento>" +
							   "<idOperazione>" + idOperazione + "</idOperazione>" +
							   "<importoNetto>" + impToString(importoNetto) + "</importoNetto>" +
							   "<commissioni>" + impToString(commissioni) + "</commissioni>" +
						   "</generic>" +
						   "<specific>" +
						   		"<codiceMerchant>" + this.codiceMerchant + "</codiceMerchant>" +
						   		"<codiceFiscaleDebitore>" + this.codDebitore + "</codiceFiscaleDebitore>" +
						   		"<codiceDebitore>" + this.codDebitore + "</codiceDebitore>" +
						   		"<userName>" + this.codDebitore + "</userName>" +						   		
						   		"<cognomeORagioneSocialeDebitore>" + this.mav_cognomeOrRagioneSocialeDebitore + "</cognomeORagioneSocialeDebitore>" +
						   		"<nomeDebitore>" + this.mav_nomeDebitore + "</nomeDebitore>" +
						   		"<causalePagamento>" + this.causalePagamento + "</causalePagamento>" +		
						   		"<indirizzoDebitore>" + this.mav_indirizzoDebitore + "</indirizzoDebitore>" +		
						   		"<capDebitore>" + this.mav_capDebitore + "</capDebitore>" +		
						   		"<localitaDebitore>" + this.mav_localitaDebitore + "</localitaDebitore>" +		
						   		"<provinciaDebitore>" + this.mav_provinciaDebitore + "</provinciaDebitore>" +							   	   
						   		"<scadenzaPagamento>" + this.scadenzaPagamento + "</scadenzaPagamento>" +						       
						   "</specific>" +
					   "</data>" +
				   "</initPayment>" +
			   "</payment>";
	}
	
	private String impToString(BigDecimal importo) {
		return DataFormat.format(importo, 15, 2).replace(" ", "0").replace(".", "").replace(",", "");
	}

	String priceToString(BigDecimal price) {
		return NumberUtility.frmtByPattern("#0000000000000.00", 
				new BigDecimal(StringUtility.replace(NumberUtility.frmtByPattern("#.00", 
						price.doubleValue()), ",", ".", true)).doubleValue()).replaceFirst(",", "");
	}
	
	private String getFullUrlOk () {
		return getUrlOk().contains("?") 
					? getUrlOk() + "&TransactionID=" + this.codiceMerchant + "&" + this.tokenCsrf 
					: getUrlOk() + "?TransactionID=" + this.codiceMerchant + "&" + this.tokenCsrf;
	}
	
	private String getFullUrlKo () {
		return getUrlKo().contains("?") 
					? getUrlKo() + "&TransactionID=" + this.codiceMerchant + "&" + this.tokenCsrf 
					: getUrlKo() + "?TransactionID=" + this.codiceMerchant + "&" + this.tokenCsrf;
	}
	
	private String getFullUrlAnnullo () {
		return getUrlAnnullo().contains("?") 
					? getUrlAnnullo() + "&TransactionID=" + this.codiceMerchant + "&" + this.tokenCsrf 
					: getUrlAnnullo() + "?TransactionID=" + this.codiceMerchant + "&" + this.tokenCsrf;
	}
	

	public static void main(String[] args) {
		try {
			java.math.BigDecimal bigDecimal = new java.math.BigDecimal(13.25);
			System.out.println(bigDecimal.doubleValue());
			System.out.println(NumberUtility.frmtByPattern("#000000000000000", 
					new Integer(String.valueOf(bigDecimal.doubleValue()).replaceAll("\\.", ""))));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setCodDebitore(java.lang.String sCodDebitore) {
		this.codDebitore = sCodDebitore;
	}
	public java.lang.String getCodDebitore() {
		return codDebitore;
	}
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	public java.lang.String getUserName() {
		return userName;
	}
	public void setScadenzaPagamento(java.lang.String sScadenzaPagamento) {
		this.scadenzaPagamento = sScadenzaPagamento;
	}
	public java.lang.String getScadenzaPagamento() {
		return scadenzaPagamento;
	}
	public void setCausalePagamento(java.lang.String sCausalePagamento) {
		this.causalePagamento = sCausalePagamento;
	}
	public java.lang.String getCausalePagamento() {
		return causalePagamento;
	}

	public void setIdentificativiDocumenti(java.lang.String identificativiDocumenti) {
		this.identificativiDocumenti = identificativiDocumenti;
	}

	public java.lang.String getIdentificativiDocumenti() {
		return identificativiDocumenti;
	}

	public void setMailUtente(java.lang.String mailUtente) {
		this.mailUtente = mailUtente;
	}

	public java.lang.String getMailUtente() {
		return mailUtente;
	}
}