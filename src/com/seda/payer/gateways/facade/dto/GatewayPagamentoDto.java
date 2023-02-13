package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;
import java.sql.SQLException;
import com.seda.payer.core.bean.GatewayPagamento;

public class GatewayPagamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String codiceNegozio;
	private java.lang.String chiaveGateway;
	private java.lang.String urlApiEndpoint;
	private java.lang.String apiUser;
	private java.lang.String apiPassword;
	private java.lang.String apiSignature;
	private java.lang.String urlApiImage;
	private java.lang.String apiVersion;
	private java.lang.String urlApiCancel;
	private java.lang.String descrizioneGateway;
	private java.lang.String urlSitoWebGateway;
	private java.lang.String tipoGateway;
	private java.lang.String emailNotificaAdmin;
	private java.lang.String urlApiRedirect;
	private java.lang.String codiceMacAvvio;
	private java.lang.String codiceMacEsito;
	private java.lang.String tipoAutorizzazione;
	private java.lang.String tipoContabilizzazione;
	private java.lang.String opzioniAggiuntive;
	private java.lang.String flagAttivazioneProxy;
	private java.lang.String indirizzoServerProxy;
	private java.lang.String portaServerProxy;
	private java.lang.String utenteProxy;
	private java.lang.String passwordProxy;
	private java.lang.String flagAttivazione;
	private java.lang.String codiceOperatore;		
    private UserDto user;
    private CanalePagamentoDto canale;
    private CartaPagamentoDto carta;


    
    public GatewayPagamentoDto() { 
    	user = new UserDto();
    	canale = new CanalePagamentoDto();
    	carta = new CartaPagamentoDto();

    }

    public GatewayPagamentoDto(GatewayPagamento object) throws SQLException {
    	if (object == null)
    		return;
    	codiceNegozio = object.getCodiceNegozio();
    	chiaveGateway = object.getChiaveGateway();
    	urlApiEndpoint = object.getUrlApiEndpoint();
    	apiUser = object.getApiUser();
    	apiPassword = object.getApiPassword();
    	apiSignature = object.getApiSignature();
    	urlApiImage = object.getUrlApiImage();
    	apiVersion = object.getApiVersion();
    	descrizioneGateway = object.getDescrizioneGateway();
    	urlSitoWebGateway = object.getUrlSitoWebGateway();
    	tipoGateway = object.getTipoGateway();
    	emailNotificaAdmin = object.getEmailNotificaAdmin();
    	urlApiRedirect = object.getUrlApiRedirect();
    	urlApiCancel = object.getUrlApiCancel();
    	codiceMacAvvio = object.getCodiceMacAvvio();
    	codiceMacEsito = object.getCodiceMacEsito();
    	tipoAutorizzazione = object.getTipoAutorizzazione();
    	tipoContabilizzazione = object.getTipoContabilizzazione();
    	opzioniAggiuntive = object.getOpzioniAggiuntive();
    	flagAttivazione = object.getFlagAttivazione();
    	codiceOperatore = object.getCodiceOperatore();
    	user = new UserDto(object.getUser());
    	canale = new CanalePagamentoDto(object.getCanale());
    	carta = new CartaPagamentoDto(object.getCarta());
    }

	public java.lang.String getCodiceNegozio() {
		return codiceNegozio;
	}

	public void setCodiceNegozio(java.lang.String codiceNegozio) {
		this.codiceNegozio = codiceNegozio;
	}

	public java.lang.String getChiaveGateway() {
		return chiaveGateway;
	}

	public void setChiaveGateway(java.lang.String chiaveGateway) {
		this.chiaveGateway = chiaveGateway;
	}

	public java.lang.String getUrlApiEndpoint() {
		return urlApiEndpoint;
	}

	public void setUrlApiEndpoint(java.lang.String urlApiEndpoint) {
		this.urlApiEndpoint = urlApiEndpoint;
	}

	public java.lang.String getApiUser() {
		return apiUser;
	}

	public void setApiUser(java.lang.String apiUser) {
		this.apiUser = apiUser;
	}

	public java.lang.String getApiPassword() {
		return apiPassword;
	}

	public void setApiPassword(java.lang.String apiPassword) {
		this.apiPassword = apiPassword;
	}

	public java.lang.String getApiSignature() {
		return apiSignature;
	}

	public void setApiSignature(java.lang.String apiSignature) {
		this.apiSignature = apiSignature;
	}

	public java.lang.String getUrlApiImage() {
		return urlApiImage;
	}

	public void setUrlApiImage(java.lang.String urlApiImage) {
		this.urlApiImage = urlApiImage;
	}

	public java.lang.String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(java.lang.String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public java.lang.String getDescrizioneGateway() {
		return descrizioneGateway;
	}

	public void setDescrizioneGateway(java.lang.String descrizioneGateway) {
		this.descrizioneGateway = descrizioneGateway;
	}

	public java.lang.String getUrlSitoWebGateway() {
		return urlSitoWebGateway;
	}

	public void setUrlSitoWebGateway(java.lang.String urlSitoWebGateway) {
		this.urlSitoWebGateway = urlSitoWebGateway;
	}

	public java.lang.String getTipoGateway() {
		return tipoGateway;
	}

	public void setTipoGateway(java.lang.String tipoGateway) {
		this.tipoGateway = tipoGateway;
	}

	public java.lang.String getEmailNotificaAdmin() {
		return emailNotificaAdmin;
	}

	public void setEmailNotificaAdmin(java.lang.String emailNotificaAdmin) {
		this.emailNotificaAdmin = emailNotificaAdmin;
	}

	public java.lang.String getUrlApiRedirect() {
		return urlApiRedirect;
	}

	public void setUrlApiRedirect(java.lang.String urlApiRedirect) {
		this.urlApiRedirect = urlApiRedirect;
	}

	public java.lang.String getCodiceMacAvvio() {
		return codiceMacAvvio;
	}

	public void setCodiceMacAvvio(java.lang.String codiceMacAvvio) {
		this.codiceMacAvvio = codiceMacAvvio;
	}

	public java.lang.String getCodiceMacEsito() {
		return codiceMacEsito;
	}

	public void setCodiceMacEsito(java.lang.String codiceMacEsito) {
		this.codiceMacEsito = codiceMacEsito;
	}

	public java.lang.String getTipoAutorizzazione() {
		return tipoAutorizzazione;
	}

	public void setTipoAutorizzazione(java.lang.String tipoAutorizzazione) {
		this.tipoAutorizzazione = tipoAutorizzazione;
	}

	public java.lang.String getTipoContabilizzazione() {
		return tipoContabilizzazione;
	}

	public void setTipoContabilizzazione(java.lang.String tipoContabilizzazione) {
		this.tipoContabilizzazione = tipoContabilizzazione;
	}

	public java.lang.String getOpzioniAggiuntive() {
		return opzioniAggiuntive;
	}

	public void setOpzioniAggiuntive(java.lang.String opzioniAggiuntive) {
		this.opzioniAggiuntive = opzioniAggiuntive;
	}

	public java.lang.String getFlagAttivazioneProxy() {
		return flagAttivazioneProxy;
	}

	public void setFlagAttivazioneProxy(java.lang.String flagAttivazioneProxy) {
		this.flagAttivazioneProxy = flagAttivazioneProxy;
	}

	public java.lang.String getIndirizzoServerProxy() {
		return indirizzoServerProxy;
	}

	public void setIndirizzoServerProxy(java.lang.String indirizzoServerProxy) {
		this.indirizzoServerProxy = indirizzoServerProxy;
	}

	public java.lang.String getPortaServerProxy() {
		return portaServerProxy;
	}

	public void setPortaServerProxy(java.lang.String portaServerProxy) {
		this.portaServerProxy = portaServerProxy;
	}

	public java.lang.String getUtenteProxy() {
		return utenteProxy;
	}

	public void setUtenteProxy(java.lang.String utenteProxy) {
		this.utenteProxy = utenteProxy;
	}

	public java.lang.String getPasswordProxy() {
		return passwordProxy;
	}

	public void setPasswordProxy(java.lang.String passwordProxy) {
		this.passwordProxy = passwordProxy;
	}

	public java.lang.String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(java.lang.String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CanalePagamentoDto getCanale() {
		return canale;
	}

	public void setCanale(CanalePagamentoDto canale) {
		this.canale = canale;
	}

	public CartaPagamentoDto getCarta() {
		return carta;
	}

	public void setCarta(CartaPagamentoDto carta) {
		this.carta = carta;
	}

	public java.lang.String getUrlApiCancel() {
		return urlApiCancel;
	}

	public void setUrlApiCancel(java.lang.String urlApiCancel) {
		this.urlApiCancel = urlApiCancel;
	}
}