package com.seda.payer.gateways.webservice.helper;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Base64;




public class RedirectToGTWMyBank implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numeroCommerciante; //
	private String stabilimento;
	private String userID;
	private String password;
	private String numeroOrdine;
	private java.math.BigDecimal totaleOrdine;
	private String valuta;
	private String tipoPagamento;
	private String flagDeposito;
	private String urlOk;
	private String urlKo;
	private String tipoRispostaApv;
	private java.lang.String flagRiciclaOrdine;
	private java.lang.String causalePagamento;
	private java.lang.String emailCompratore;
	private java.lang.String langCompratore;

	private String securityString;
	private String strURL;
	private String token;
	private String tipoGateway;

	
	public RedirectToGTWMyBank() {
		
	}
	/**
	 * @param numeroCommerciante
	 * @param stabilimento
	 * @param userID
	 * @param password
	 * @param numeroOrdine
	 * @param totaleOrdine
	 * @param valuta
	 * @param tipoPagamento
	 * @param flagDeposito
	 * @param urlOk
	 * @param urlKo
	 * @param tipoRispostaApv
	 * @param flagRiciclaOrdine
	 * @param causalePagamento
	 * @param emailCompratore
	 * @param langCompratore
	 */
	public RedirectToGTWMyBank(String numeroCommerciante,
			String stabilimento, String userID, String password,
			String numeroOrdine, BigDecimal totaleOrdine, String valuta,
			String tipoPagamento, String flagDeposito, String urlOk,
			String urlKo, String tipoRispostaApv, String flagRiciclaOrdine,
			String causalePagamento, String emailCompratore,
			String langCompratore, String securityString, String strURL, String token, String tipoGateway) {
			
		super();
		this.numeroCommerciante = numeroCommerciante;
		this.stabilimento = stabilimento;
		this.userID = userID;
		this.password = password;
		this.numeroOrdine = numeroOrdine!=null?numeroOrdine.replaceFirst("-",""):numeroOrdine;
		this.totaleOrdine = totaleOrdine;
		this.valuta = valuta;
		this.tipoPagamento = tipoPagamento;
		this.flagDeposito = flagDeposito;
		this.urlOk = urlOk;
		this.urlKo = urlKo;
		this.tipoRispostaApv = tipoRispostaApv;
		this.flagRiciclaOrdine = flagRiciclaOrdine;
		this.causalePagamento = causalePagamento;
		this.emailCompratore = emailCompratore;
		this.langCompratore = langCompratore;
		this.securityString = securityString;
		this.strURL = strURL;
		this.token = token;
		this.tipoGateway = tipoGateway;
		
	}

	public String getUrl() throws NoSuchAlgorithmException {
	
		/*String stringaMAC = "numeroCommerciante=" + this.numeroCommerciante + "&userID=" + this.userID
				+ "&password=" + this.password + "&numeroOrdine=" + this.numeroOrdine + "&totaleOrdine=" + impToString(this.totaleOrdine)
				+ "&valuta=" + this.valuta + "&flagDeposito=" + this.flagDeposito + "&urlOk=" + this.urlOk + "&" + token 
				+ "&numeroOrdine=" + this.numeroOrdine + "&urlKo=" + this.urlKo + "?tipoGateway=" + this.tipoGateway 
				+ "&" + this.token + "&numeroOrdine=" + this.numeroOrdine 
				+ "&tipoRispostaApv=" + this.tipoRispostaApv + "&flagRiciclaOrdine=" + this.flagRiciclaOrdine
				+ "&stabilimento=" + this.stabilimento;*/
		
	
		String stringaMAC = "numeroCommerciante=" + this.numeroCommerciante 
		+ "&userID=" + this.userID
		+ "&password=" + this.password 
		+ "&numeroOrdine=" + this.numeroOrdine
		+ "&totaleOrdine=" + impToString(this.totaleOrdine)
		+ "&valuta=" + this.valuta 
		+ "&flagDeposito=" + this.flagDeposito 
		+ "&urlOk=" + this.urlOk 
		+ "&urlKo="+ this.urlKo  
		+ "&tipoRispostaApv=" + this.tipoRispostaApv 
		+ "&flagRiciclaOrdine=" + this.flagRiciclaOrdine
		+ "&stabilimento=" + this.stabilimento;
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		String stringaMD5 = stringaMAC + "&" + this.securityString;
		md.update(stringaMD5.getBytes());
		byte[] bMac = md.digest();
		String sMacEncoded = Base64.getEncoder().encodeToString(bMac);
		sMacEncoded = sMacEncoded.substring(0,24);
		
		String stringaURL = this.strURL + "?";
		String stringaFinaleUrl="";
		try {
			
			/*stringaFinaleUrl = stringaURL + "numeroCommerciante=" + this.numeroCommerciante + "&userID=" + this.userID
			+ "&password=qualunque" + "&numeroOrdine=" + this.numeroOrdine + "&totaleOrdine=" + impToString(this.totaleOrdine)
			+ "&valuta=" + this.valuta + "&flagDeposito=" + this.flagDeposito + "&urlOk=" + URLEncoder.encode(this.urlOk + "&" + token 
			+ "&numeroOrdine=" + this.numeroOrdine,"UTF-8") + "&urlKo=" + URLEncoder.encode(this.urlKo + "?tipoGateway=" + this.tipoGateway 
			+ "&" + this.token + "&numeroOrdine=" + this.numeroOrdine,"UTF-8") 
			+ "&tipoRispostaApv=" + this.tipoRispostaApv + "&flagRiciclaOrdine=" + this.flagRiciclaOrdine
			+ "&stabilimento=" + this.stabilimento + "&mac=" +  URLEncoder.encode(sMacEncoded,"UTF-8");*/
			
			stringaFinaleUrl = stringaURL + "numeroCommerciante=" + this.numeroCommerciante + "&userID=" + this.userID
			+ "&password=qualunque" + "&numeroOrdine=" + this.numeroOrdine + "&totaleOrdine=" + impToString(this.totaleOrdine)
			+ "&valuta=" + this.valuta + "&flagDeposito=" + this.flagDeposito + "&urlOk=" + URLEncoder.encode(this.urlOk ,"UTF-8") 
			+ "&urlKo=" + URLEncoder.encode(this.urlKo ,"UTF-8") 
			+ "&tipoRispostaApv=" + this.tipoRispostaApv + "&flagRiciclaOrdine=" + this.flagRiciclaOrdine
			+ "&stabilimento=" + this.stabilimento + "&mac=" +  URLEncoder.encode(sMacEncoded,"UTF-8");
			

			
//			stringaFinaleUrl = stringaURL + "numeroCommerciante=" + this.numeroCommerciante + "&userID=" + this.userID
//			+ "&password="+this.password + "&numeroOrdine=" + this.numeroOrdine + "&totaleOrdine=" + impToString(this.totaleOrdine)
//			+ "&valuta=" + this.valuta + "&flagDeposito=" + this.flagDeposito + "&urlOk=" + URLEncoder.encode(this.urlOk ,"UTF-8") 
//			+ "&urlKo=" + URLEncoder.encode(this.urlKo ,"UTF-8") 
//			+ "&tipoRispostaApv=" + this.tipoRispostaApv + "&flagRiciclaOrdine=" + this.flagRiciclaOrdine
//			+ "&stabilimento=" + this.stabilimento + "&mac=" +  URLEncoder.encode(sMacEncoded,"UTF-8");
				
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//campi facoltativi
		stringaFinaleUrl = stringaFinaleUrl + "&tipoPagamento=" + tipoPagamento + "&emailCompratore=" + emailCompratore + "&langCompratore=" + langCompratore;
		if(!this.causalePagamento.equals("")){
			stringaFinaleUrl = stringaFinaleUrl + "&causalePagamento=" +this.causalePagamento; 
		}
		return stringaFinaleUrl;
	}
	
	public static Calendar StringToCalender(String datai) {
		try {
			
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat("dd-MMM-yy");
			date = (Date) formatter.parse(datai);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		}
		return null;
	}
	
	private String impToString(BigDecimal importo) {
		return importo.toString().replace(" ", "0").replace(".", "").replace(",", "");
	}
			
	public String getNumeroCommerciante() {
		return numeroCommerciante;
	}

	public void setNumeroCommerciante(String numeroCommerciante) {
		this.numeroCommerciante = numeroCommerciante;
	}

	public String getStabilimento() {
		return stabilimento;
	}

	public void setStabilimento(String stabilimento) {
		this.stabilimento = stabilimento;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public java.math.BigDecimal getTotaleOrdine() {
		return totaleOrdine;
	}

	public void setTotaleOrdine(java.math.BigDecimal totaleOrdine) {
		this.totaleOrdine = totaleOrdine;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getFlagDeposito() {
		return flagDeposito;
	}

	public void setFlagDeposito(String flagDeposito) {
		this.flagDeposito = flagDeposito;
	}

	public String getUrlOk() {
		return urlOk;
	}

	public void setUrlOk(String urlOk) {
		this.urlOk = urlOk;
	}

	public String getUrlKo() {
		return urlKo;
	}

	public void setUrlKo(String urlKo) {
		this.urlKo = urlKo;
	}

	public String getTipoRispostaApv() {
		return tipoRispostaApv;
	}

	public void setTipoRispostaApv(String tipoRispostaApv) {
		this.tipoRispostaApv = tipoRispostaApv;
	}

	public java.lang.String getFlagRiciclaOrdine() {
		return flagRiciclaOrdine;
	}

	public void setFlagRiciclaOrdine(java.lang.String flagRiciclaOrdine) {
		this.flagRiciclaOrdine = flagRiciclaOrdine;
	}

	public java.lang.String getCausalePagamento() {
		return causalePagamento;
	}

	public void setCausalePagamento(java.lang.String causalePagamento) {
		this.causalePagamento = causalePagamento;
	}

	public java.lang.String getEmailCompratore() {
		return emailCompratore;
	}

	public void setEmailCompratore(java.lang.String emailCompratore) {
		this.emailCompratore = emailCompratore;
	}

	public java.lang.String getLangCompratore() {
		return langCompratore;
	}

	public void setLangCompratore(java.lang.String langCompratore) {
		this.langCompratore = langCompratore;
	}

	public String getSecurityString() {
		return securityString;
	}

	public void setSecurityString(String securityString) {
		this.securityString = securityString;
	}

	public void setStrURL(String strURL) {
		this.strURL = strURL;
	}

	public String getStrURL() {
		return strURL;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setTipoGateway(String tipoGateway) {
		this.tipoGateway = tipoGateway;
	}

	public String getTipoGateway() {
		return tipoGateway;
	}
	
	
	public static void main(String args[]){       
		String  digetString="numeroCommerciante=9999888&userID=9999888_4316887&password=Inizio000&numeroOrdine=cf2d97c3-bc65-4c09-a663-644220c92be7&totaleOrdine=100&valuta=978&flagDeposito=Y&urlOk=http://localhost:8415/payer/cart/quietanza.do?TipoGateway=B&formName=quietanza&urlKo=http://localhost:8415/payer/cart/quietanza.do?TipoGateway=O&formName=quietanza&tipoRispostaApv=wait&flagRiciclaOrdine=N&stabilimento=99966";

		
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		String stringaMD5 = digetString + "&" + "b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1b1";
			md.update(stringaMD5.getBytes());
			byte[] bMac = md.digest();
			String sMacEncoded = Base64.getEncoder().encodeToString(bMac);
			sMacEncoded = sMacEncoded.substring(0,24);
		System.out.println("Mac "+URLEncoder.encode(sMacEncoded,"UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
