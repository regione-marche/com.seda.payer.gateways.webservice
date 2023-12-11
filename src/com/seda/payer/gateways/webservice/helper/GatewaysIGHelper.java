package com.seda.payer.gateways.webservice.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;


import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.emailsender.webservices.dati.EMailSenderRequestType;
import com.seda.emailsender.webservices.dati.EMailSenderResponse;
import com.seda.emailsender.webservices.source.EMailSenderInterface;
import com.seda.emailsender.webservices.source.EMailSenderServiceLocator;
import com.seda.payer.commons.utility.DateUtility;
import com.seda.payer.commons.utility.NumberUtility;
import com.seda.payer.commons.utility.StringUtility;
import com.seda.payer.gateways.facade.bean.GatewayPagamentoBean;
import com.seda.payer.gateways.facade.dto.GatewayPagamentoDto;
import com.seda.payer.gateways.webservice.config.PropKeys;
import com.seda.payer.gateways.webservice.ig.dati.BeanInvioEsitoRequest;
import com.seda.payer.notifiche.webservice.dati.NotificaAutorizzazioneBancaRequestType;
import com.seda.payer.notifiche.webservice.dati.NotificaMAVRequestType;
import com.seda.payer.notifiche.webservice.dati.NotificaMAVResponseType;
import com.seda.payer.notifiche.webservice.dati.NotificaQuietanzaBollettiniRequestType;
import com.seda.payer.notifiche.webservice.dati.NotificaRIDRequestType;
import com.seda.payer.notifiche.webservice.dati.NotificaRIDResponseType;
import com.seda.payer.notifiche.webservice.source.NotificheSOAPBindingStub;
import com.seda.payer.notifiche.webservice.source.NotificheServiceLocator;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneGenericRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneGenericResponse;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneIGMavRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneIGMavResponse;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneResponse;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioniIGRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioniIGRequestCanalePagemanto;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioniIGRequestTipoGateway;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioniIGResponse;
import com.seda.payer.pgec.webservice.commons.dati.ConfigPagamentoSingleRequest;
import com.seda.payer.pgec.webservice.commons.dati.ConfigPagamentoSingleResponse;
import com.seda.payer.pgec.webservice.commons.dati.RecuperaTransazioneRequestType;
import com.seda.payer.pgec.webservice.commons.dati.RecuperaTransazioneResponseType;
import com.seda.payer.pgec.webservice.commons.dati.ResponseTypeRetCode;
import com.seda.payer.pgec.webservice.commons.dati.Transazioni;
import com.seda.payer.pgec.webservice.commons.dati.TransazioniBeanTransazioni;
import com.seda.payer.pgec.webservice.commons.source.CommonsSOAPBindingStub;
import com.seda.payer.pgec.webservice.commons.source.CommonsServiceLocator;
//PG150180_001 GG - inizio
import com.seda.payer.pgec.webservice.anagente.dati.AnagEnteSearchRequest;
import com.seda.payer.pgec.webservice.anagente.dati.AnagEnteSearchResponse;
import com.seda.payer.pgec.webservice.anagente.source.AnagEnteImplementationStub;
import com.seda.payer.pgec.webservice.anagente.source.AnagEnteServiceLocator;
import com.seda.payer.pgec.webservice.configutentetiposervizioente.dati.ConfigUtenteTipoServizioEnteDetailRequest;
import com.seda.payer.pgec.webservice.configutentetiposervizioente.dati.ConfigUtenteTipoServizioEnteDetailResponse;
import com.seda.payer.pgec.webservice.configutentetiposervizioente.source.ConfigUtenteTipoServizioEnteImplementationStub;
import com.seda.payer.pgec.webservice.configutentetiposervizioente.source.ConfigUtenteTipoServizioEnteServiceLocator;
import com.seda.payer.pgec.webservice.ente.dati.EnteDetailRequest;
import com.seda.payer.pgec.webservice.ente.dati.EnteDetailResponse;
import com.seda.payer.pgec.webservice.ente.source.EnteImplementationStub;
import com.seda.payer.pgec.webservice.ente.source.EnteServiceLocator;
import com.seda.payer.pgec.webservice.mip.source.MIPSOAPBidingStub;
import com.seda.payer.pgec.webservice.mip.source.MIPServiceLocator;
//PG150180_001 GG - fine
import com.seda.payer.pgec.webservice.gatewaypagamento.dati.GatewayPagamentoDetailRequest;
import com.seda.payer.pgec.webservice.gatewaypagamento.dati.GatewayPagamentoDetailResponse;
import com.seda.payer.pgec.webservice.gatewaypagamento.source.GatewayPagamentoImplementationStub;
import com.seda.payer.pgec.webservice.gatewaypagamento.source.GatewayPagamentoServiceLocator;

public class GatewaysIGHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String DBSCHEMACODSOCIETA = "dbSchemaCodSocieta";
	/**
	 * @return
	 */
	private LoggerWrapper log = CustomLoggerManager.get(GatewaysIGHelper.class);
	public static CommonsSOAPBindingStub getCommonsManager(PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		// we initialize commons serviceLocator
		CommonsServiceLocator serviceLocator = new CommonsServiceLocator();
		// we initialize commons stub
		CommonsSOAPBindingStub binding = (CommonsSOAPBindingStub)serviceLocator.getCommonsPort(
				new URL(env.getProperty(PropKeys.COMMONS_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
		
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		return binding;
	}
	
	//PG150180_001 GG - inizio
	public static ConfigUtenteTipoServizioEnteImplementationStub  getConfigUtenteTipoServizioEnteImplementationStub (PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		// we initialize commons serviceLocator
		ConfigUtenteTipoServizioEnteServiceLocator serviceLocator = new ConfigUtenteTipoServizioEnteServiceLocator();
		// we initialize commons stub
		ConfigUtenteTipoServizioEnteImplementationStub  binding = (ConfigUtenteTipoServizioEnteImplementationStub )serviceLocator.getConfigUtenteTipoServizioEntePort(
				new URL(env.getProperty(PropKeys.WSCONFIGUTENTETIPOSERVIZIOENTE_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
				
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		return binding;
	}
	
	public static EnteImplementationStub  getEnteImplementationStub (PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		// we initialize commons serviceLocator
		EnteServiceLocator serviceLocator = new EnteServiceLocator();
		// we initialize commons stub
		EnteImplementationStub  binding = (EnteImplementationStub )serviceLocator.getEntePort(
				new URL(env.getProperty(PropKeys.WSENTE_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
		
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		return binding;
	}
	
	public static AnagEnteImplementationStub  getAnagEnteImplementationStub (PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		// we initialize commons serviceLocator
		AnagEnteServiceLocator serviceLocator = new AnagEnteServiceLocator();
		// we initialize commons stub
		AnagEnteImplementationStub  binding = (AnagEnteImplementationStub )serviceLocator.getAnagEntePort(
				new URL(env.getProperty(PropKeys.WSANAGENTE_ENDPOINURL.format(PropKeys.DEFAULT_NODE.format()))));
		
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		return binding;
	}
	
	public static MIPSOAPBidingStub getMIPSOAPBidingStub(PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		// we initialize commons serviceLocator
		MIPServiceLocator serviceLocator = new MIPServiceLocator();
		// we initialize commons stub
		MIPSOAPBidingStub binding = (MIPSOAPBidingStub)serviceLocator.getMIPPort(
				new URL(env.getProperty(PropKeys.WSMIP_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
		
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		return binding;
	}
	//PG150180_001 GG - fine
	

	/**
	 * @param chiave_gateway_di_pagamento
	 * @throws Exception
	 */
	public static String getUrlRedirectQuietanza(String chiave_gateway_di_pagamento, LoggerWrapper log, String dbSchemaCodSocieta,PropertiesTree propertiesTree) throws Exception {
	//fine LP PG200070 - 20200812
	//fine LP PG200070
		log.info("invioEsito - Start Get UrlRedirectQuietanza");
		PropertiesTree env = propertiesTree;
		GatewayPagamentoBean service = new GatewayPagamentoBean(env);
		//fine LP PG200070 - 20200812
		GatewayPagamentoDto gateway = service.getGatewayPagamento(chiave_gateway_di_pagamento, dbSchemaCodSocieta);
		String urlRedirectQuietanza = gateway.getUrlSitoWebGateway();
		log.info("invioEsito - Url Redirect Al Payer Per Quietanza, " + urlRedirectQuietanza);
		log.info("invioEsito - End Get UrlRedirectQuietanza");
		return urlRedirectQuietanza;
	}
	
	/**
	 * @param chiave_gateway_di_pagamento
	 * @throws Exception
	 */
	public static String getTipoGateway(String chiave_gateway_di_pagamento, PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		GatewayPagamentoServiceLocator serviceLocator = new GatewayPagamentoServiceLocator();
		GatewayPagamentoImplementationStub binding = (GatewayPagamentoImplementationStub)serviceLocator.getGatewayPagamentoPort(
				new URL(env.getProperty(PropKeys.GATEWAYPAGAMENTO_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
		
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		GatewayPagamentoDetailResponse res = binding.getGatewayPagamento(new GatewayPagamentoDetailRequest(chiave_gateway_di_pagamento));
		if (res != null && res.getGatewaypagamento() != null && res.getGatewaypagamento().getTipoGateway() != null)
			return res.getGatewaypagamento().getTipoGateway();

		return "";
	}

	
	/**
	 * @param binding
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static TransazioniBeanTransazioni recuperaTransazione(CommonsSOAPBindingStub binding, BeanInvioEsitoRequest in, LoggerWrapper log) throws Exception {
		RecuperaTransazioneRequestType recuperaTransazione = new RecuperaTransazioneRequestType(in.getCodiceMerchant());
		log.info("invioEsito - Start recuperaTransazione");
		RecuperaTransazioneResponseType recuperaTransazioneResponse = binding.recuperaTransazione(recuperaTransazione);
		Transazioni transazioni = recuperaTransazioneResponse.getBeanTransazioni();
		TransazioniBeanTransazioni beanTransazioni = transazioni.getBeanTransazioni();
		log.info("invioEsito - End recuperaTransazione");
		log.info("invioEsito - ChiaveTransazione, " + beanTransazioni.getChiave_transazione());
		/*
		log.info("invioEsito - EsitoTransazione, " + in.getCodiceEsito());
		log.info("invioEsito - CodAutBanca, " + in.getIdOperazione());
		log.info("invioEsito - CodIdBanca, " + in.getIdOperazione());
		log.info("invioEsito - DataPagamento, " + in.getDataOperazione());
		*/

		return beanTransazioni;
	}
	
	/**
	 * @param chiaveTransazione
	 * @param idOperazione
	 * @param binding
	 * @param log
	 * @return
	 * @throws Exception
	 */
	private static TransazioniBeanTransazioni recuperaTransazione(String chiaveTransazione, String idOperazione, 
			CommonsSOAPBindingStub binding, LoggerWrapper log) throws Exception {
		RecuperaTransazioneRequestType recuperaTransazione = new RecuperaTransazioneRequestType(chiaveTransazione);
		log.info("recuperaTransazione - Start recuperaTransazione");		
		RecuperaTransazioneResponseType recuperaTransazioneResponse = binding.recuperaTransazione(recuperaTransazione);
		Transazioni transazioni = recuperaTransazioneResponse.getBeanTransazioni();
		TransazioniBeanTransazioni beanTransazioni = transazioni.getBeanTransazioni();
		log.info("recuperaTransazione - End recuperaTransazione");
		log.info("recuperaTransazione - ChiaveTransazione, " + beanTransazioni.getChiave_transazione());
		log.info("recuperaTransazione - CodAutBanca, " + beanTransazioni.getCodice_autorizzazione_banca() + " (idOperazione := " + idOperazione + ")");
		log.info("recuperaTransazione - CodIdBanca, " + beanTransazioni.getCodice_identificativo_banca() + " (idOperazione := " + idOperazione + ")");
		log.info("recuperaTransazione - DataPagamento, " + beanTransazioni.getData_effettivo_pagamento_su_gateway());
		if (beanTransazioni.getChiave_transazione() == null)
			throw new Exception("ChiaveTransazione is null"); 

		return beanTransazioni;
	}
	/**
	 * è richiamata dall'invioEsito ovvero dopo il pagamento con un gateway (non MAV)
	 * @param beanTransazioni
	 * @param in
	 * @param binding
	 * @throws Exception
	 */
	public static void aggiornaTransazione(TransazioniBeanTransazioni beanTransazioni, BeanInvioEsitoRequest in, CommonsSOAPBindingStub binding, LoggerWrapper log) throws Exception {
		// we check If Flag_esito_transazione is 0 (pending) then update transaction
		if (beanTransazioni.getFlag_esito_transazione().compareTo("0") == 0 ||
				beanTransazioni.getFlag_esito_transazione().compareTo("2") == 0) 
		{
    		// we update transaction state (dovuto al termine del pagamento lato web proviene dall'invioEsito)
			Timestamp timestamp = Timestamp.valueOf("1000-01-01 00:00:00.000");
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp.getTime());
			AggiornaTransazioneRequest aggiornaTransazione = new AggiornaTransazioneRequest(
        			beanTransazioni.getChiave_transazione(), 
        			in.getCodiceEsito() == 0 ? "1" : "2", 
        			in.getIdOperazione(), 
        			in.getIdOperazione(), 
        			in.getDataOperazione(),
        			cal,
        			"");
        	AggiornaTransazioneResponse aggiornaTransazioneResponse = binding.aggiornaTransazione(aggiornaTransazione);
        	
        	log.info("aggiornaTransazione response code - " + aggiornaTransazioneResponse.getResponse().getRetCode().getValue());
        	log.info("aggiornaTransazione response message - " + aggiornaTransazioneResponse.getResponse().getRetMessage());
		}
	}
	/**
	 * è richiamata dall'allineamentoTransazione per aggiornare ciò che è arrivato dal flusso RNINCATRANS ma solo per il MAV
	 * @param beanTransazioni
	 * @param idOperazione
	 * @param dataOperazione
	 * @param binding
	 * @param log
	 * @throws Exception
	 */
	private static boolean aggiornaTransazioneMAV(TransazioniBeanTransazioni beanTransazioni, String idOperazione, Date dataOperazione, Date dataAccreddito, String nomeFile, 
			CommonsSOAPBindingStub binding, LoggerWrapper log) throws Exception 
	{
		// è il edesimo aggiornamento per una transazione standard
		return aggiornaTransazione(beanTransazioni, idOperazione, dataOperazione, dataAccreddito, nomeFile, binding, log);
	}
	/**
	 * è richiamata dall'allineamentoTransazione per aggiornare ciò che è arrivato dal flusso RNINCATRANS
	 * @param beanTransazioni
	 * @param idOperazione
	 * @param dataOperazione
	 * @param binding
	 * @param log
	 * @throws Exception
	 */
	private static boolean aggiornaTransazione(TransazioniBeanTransazioni beanTransazioni, String idOperazione, Date dataOperazione, Date dataAccredito, String nomeFile, 
			CommonsSOAPBindingStub binding, LoggerWrapper log) throws Exception {
		// we update transaction state
		Calendar dataOperazioneToCalendar = Calendar.getInstance();
		dataOperazioneToCalendar.setTime(dataOperazione);
		Calendar dataAccreditoToCalendar = Calendar.getInstance();
		dataAccreditoToCalendar.setTime(dataAccredito);
    	String note="RNINCATRANS: "+nomeFile;
    	if (beanTransazioni.getFlag_esito_transazione().equals("1"))
    		note="";
		AggiornaTransazioneRequest aggiornaTransazione = new AggiornaTransazioneRequest(
    			beanTransazioni.getChiave_transazione(), 
    			"1", 
    			idOperazione, 
    			idOperazione, 
    			dataOperazioneToCalendar,
    			dataAccreditoToCalendar,
    			note);
    	//		"RNINCATRANS: "+nomeFile);
    	AggiornaTransazioneResponse aggiornaTransazioneResponse = binding.aggiornaTransazione(aggiornaTransazione);
    	log.info("aggiornaTransazione response code - " + aggiornaTransazioneResponse.getResponse().getRetCode().getValue());
    	log.info("aggiornaTransazione response message - " + aggiornaTransazioneResponse.getResponse().getRetMessage());
    	return aggiornaTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ? true : false;
	}
	
	/**
	 * è richiamata dall'allineamentoTransazione per aggiornare ciò che è arrivato dal flusso RNINCATRANS
	 * @param chiaveTransazione
	 * @param note
	 * @param statoStorno
	 * @param binding
	 * @param log
	 * @throws Exception
	 */
	private static boolean aggiornaTransazioneStorno(String chiaveTransazione, String note, String statoStorno, CommonsSOAPBindingStub binding, LoggerWrapper log) throws Exception {

		TransazioniBeanTransazioni beanTransazioni=new TransazioniBeanTransazioni();
		
		beanTransazioni.setChiave_transazione(chiaveTransazione);
		beanTransazioni.setTipo_storno(statoStorno);
		beanTransazioni.setNote_generiche(note);
		beanTransazioni.setFlag_esito_transazione("2");
				
		AggiornaTransazioneGenericRequest request=new AggiornaTransazioneGenericRequest();
		Transazioni transazione=new Transazioni();
		
		transazione.setBeanTransazioni(beanTransazioni);
		
		request.setTransazione(transazione);
		AggiornaTransazioneGenericResponse responseAggiornaTransazioneGenericResponse = binding.aggiornaTransazioneGeneric(request);
    	
		return responseAggiornaTransazioneGenericResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ? true : false;
	}
	
	
	/**
	 * @param chiaveTransazione
	 * @param env
	 * @throws Exception
	 */
	/*
	public static void notificaTransazioneDirect(String chiaveTransazione, PropertiesTree env) throws Exception {
		// we initialize notificheStub
		NotificheSOAPBindingStub ntfStub = getNotificheWS(env);
		
		NotificaQuietanzaBollettiniRequestType notificaQuietanzaBollettiniRequestType=new NotificaQuietanzaBollettiniRequestType();
	    notificaQuietanzaBollettiniRequestType.setChiave_transazione(chiaveTransazione);
	    notificaQuietanzaBollettiniRequestType.setModalita("B");
	    notificaQuietanzaBollettiniRequestType.setTipo_notifica("U");
	    
	    ntfStub.notificaQuietanzaBollettini(notificaQuietanzaBollettiniRequestType);
		
	    
	    NotificaAutorizzazioneBancaRequestType notificaAutorizzazioneBancaRequestType=new NotificaAutorizzazioneBancaRequestType();
		notificaAutorizzazioneBancaRequestType.setChiave_transazione(chiaveTransazione);
		notificaAutorizzazioneBancaRequestType.setModalita("B");
		notificaAutorizzazioneBancaRequestType.setTipo_destinatario("T");
		notificaAutorizzazioneBancaRequestType.setTipo_notifica("T");
		
		ntfStub.notificaAutorizzazioneBanca(notificaAutorizzazioneBancaRequestType);
	
	}
	*/
	/**
	 * @param beanTransazioni
	 * @throws Exception
	 */
	public static void notificaTransazione(TransazioniBeanTransazioni beanTransazioni, PropertiesTree env, String modalita, String dbSchemaCodSocieta) throws Exception 
	{
		// we initialize notificheStub
		NotificheSOAPBindingStub ntfStub = getNotificheWS(env, dbSchemaCodSocieta);

		//Get tipoGateway
		String tipoGateway = getTipoGateway(beanTransazioni.getChiave_gateway_di_pagamento(), env, dbSchemaCodSocieta);
		
		// we notify QUIETANZA BOLLETTINI by MAIL
		if (beanTransazioni.getInvio_notifica_bollettini_per_email().equalsIgnoreCase("N")){
			NotificaQuietanzaBollettiniRequestType notificaQuietanzaBollettiniRequestType=new NotificaQuietanzaBollettiniRequestType();
		    notificaQuietanzaBollettiniRequestType.setChiave_transazione(beanTransazioni.getChiave_transazione());
		    notificaQuietanzaBollettiniRequestType.setModalita(modalita);
		    notificaQuietanzaBollettiniRequestType.setTipo_notifica("U");
		    notificaQuietanzaBollettiniRequestType.setTipoGateway(tipoGateway);
		    
		    ntfStub.notificaQuietanzaBollettini(notificaQuietanzaBollettiniRequestType);
		}
		

		// we notify AUTORIZZAZIONE BANCA for CONTRIBUENTE by MAIL 
		if (beanTransazioni.getInvio_autorizzazione_banca_per_email_contribuente().equalsIgnoreCase("N")){
			NotificaAutorizzazioneBancaRequestType notificaAutorizzazioneBancaRequestType=new NotificaAutorizzazioneBancaRequestType();
			notificaAutorizzazioneBancaRequestType.setChiave_transazione(beanTransazioni.getChiave_transazione());
			notificaAutorizzazioneBancaRequestType.setModalita(modalita);
			notificaAutorizzazioneBancaRequestType.setTipo_destinatario("C");
			notificaAutorizzazioneBancaRequestType.setTipo_notifica("E");
			
			ntfStub.notificaAutorizzazioneBanca(notificaAutorizzazioneBancaRequestType);
		}

		// we notify AUTORIZZAZIONE BANCA for AMMINISTRATORE by MAIL
		if (beanTransazioni.getInvio_autorizzazione_banca_per_email_amministratore().equalsIgnoreCase("N")){
			NotificaAutorizzazioneBancaRequestType notificaAutorizzazioneBancaRequestType=new NotificaAutorizzazioneBancaRequestType();
			notificaAutorizzazioneBancaRequestType.setChiave_transazione(beanTransazioni.getChiave_transazione());
			notificaAutorizzazioneBancaRequestType.setModalita(modalita);
			notificaAutorizzazioneBancaRequestType.setTipo_destinatario("A");
			notificaAutorizzazioneBancaRequestType.setTipo_notifica("E");
			
			ntfStub.notificaAutorizzazioneBanca(notificaAutorizzazioneBancaRequestType);
		}
		
		
		// we notify AUTORIZZAZIONE BANCA for CONTRIBUENTE by SMS
		if (beanTransazioni.getInvio_notifica_autorizzazione_banca_per_sms().equalsIgnoreCase("N")){
			NotificaAutorizzazioneBancaRequestType notificaAutorizzazioneBancaRequestType=new NotificaAutorizzazioneBancaRequestType();
			notificaAutorizzazioneBancaRequestType.setChiave_transazione(beanTransazioni.getChiave_transazione());
			notificaAutorizzazioneBancaRequestType.setModalita(modalita);
			notificaAutorizzazioneBancaRequestType.setTipo_destinatario("C");
			notificaAutorizzazioneBancaRequestType.setTipo_notifica("S");
			
			ntfStub.notificaAutorizzazioneBanca(notificaAutorizzazioneBancaRequestType);
		}

	}
	/**
	 * @param message
	 * @param log
	 * @throws Exception
	 */
	public static void notificaMancatoAllineamento(final String message, final LoggerWrapper log, final PropertiesTree env, final String emailNotificaAdmin, final String cutecute) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					EMailSenderServiceLocator mailSenderServiceLocator = new EMailSenderServiceLocator();
					EMailSenderInterface mailSender = 
						mailSenderServiceLocator.getEMailSenderPort(
								new URL(env.getProperty(PropKeys.EMAILSENDER_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
					
					EMailSenderResponse response = mailSender.getEMailSender(
							new EMailSenderRequestType(
									emailNotificaAdmin,
									"",""," ", 
									env.getProperty(PropKeys.EMAIL_NOTIFY_SUBJECT.format(PropKeys.DEFAULT_NODE.format())), message,"",cutecute)
									);
					
					log.info("emailSender response - " + response.getValue());
				} catch (Exception e) {
					log.warn("notificaMancatoAllineamento failed, generic error due to: ", e);
				}
			}
		};
		thread.start();
	}
	/**
	 * @param nextFlow
	 * @param env
	 * @param log
	 * @return
	 * @throws Exception
	 */
	//inizio LP PG200070 - 20200812
	//public static boolean allineaTransazioniIG(String nextFlow, String nomeFile, PropertiesTree env, LoggerWrapper log, String dbSchemaCodSocieta) throws Exception {
	public static boolean allineaTransazioniIG(String nextFlow, String nomeFile, PropertiesTree env, LoggerWrapper log, String dbSchemaCodSocieta) throws Exception {
	//fine LP PG200070 - 20200812
		FileInputStream fis = new FileInputStream(nextFlow);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		try {
			String linea = br.readLine();
			
			String dataCreazione = null;		/* RC HR */
			String nomeSupporto = null;			/* RC HR */
			String dataScadenzaBolletta = null;	/* RC 10 */
			String dataAccredito = null;	    /* RC 10 */
			String importLordo = null;			/* RC 10 */
			String segnoImporto = null;			/* RC 10 */
			String chiaveTransazione = null;	/* RC 50 */
			String canaleAttivazione = null;	/* RC 50 */
			String strumentoPagamento = null;	/* RC 50 */
			String idOperazione = null;			/* RC 70 */
			
			
			boolean isWasAlligned = false;
			while (linea != null) {
				String typeRecord = linea.substring(1, 3);
				// we skip blank line
				if (StringUtility.isEmpty(typeRecord.replaceAll(" ", ""))) {
					linea = br.readLine();
					continue;
				}
				if (typeRecord.equalsIgnoreCase("HR")) {
					dataCreazione = linea.substring(13, 19);
					nomeSupporto = linea.substring(19, 39);
					dataScadenzaBolletta = null;
					importLordo = null;
					segnoImporto = null;
					chiaveTransazione = null;
					canaleAttivazione = null;
					strumentoPagamento = null;
					idOperazione = null;
					log.info("NomeSupporto := " + nomeSupporto + ", DataCreazione := " + dataCreazione);
				} else if (typeRecord.equalsIgnoreCase("10")) {
					dataScadenzaBolletta = linea.substring(10, 16);
					dataAccredito = linea.substring(16, 22);
					importLordo = linea.substring(33, 46);
					segnoImporto = linea.substring(46, 47);
					log.info("\tDataScadenzaBolletta := " + dataScadenzaBolletta);
					log.info("\tImportoLordo := " + importLordo);
					log.info("\tSegnoImporto := " + segnoImporto);
				} else if (typeRecord.equalsIgnoreCase("50")) {
					chiaveTransazione = linea.substring(10, 46);
					canaleAttivazione = linea.substring(46, 48);
					strumentoPagamento = linea.substring(48, 50);
					log.info("\tChiaveTransazione := " + chiaveTransazione);
					log.info("\tCanaleAttivazione := " + canaleAttivazione);
					log.info("\tStrumentoPagamento := " + strumentoPagamento);
				} else if (typeRecord.equalsIgnoreCase("70")) {
					//idOperazione = linea.substring(10, 23);
					idOperazione = linea.substring(10, 22);
					log.info("\tIdOperazione := " + idOperazione);
					log.info("\tAllineamento transazione in corso...");
					
					// we align transaction
					// la transazione è MAV è gestita all'interno 
					//inizio LP PG200070 - 20200812
					//isWasAlligned = allineaTransazioneIG(nomeSupporto, dataScadenzaBolletta, dataAccredito, chiaveTransazione, idOperazione, importLordo, segnoImporto, nomeFile, env, log, dbSchemaCodSocieta);
					isWasAlligned = allineaTransazioneIG(nomeSupporto, dataScadenzaBolletta, dataAccredito, chiaveTransazione, idOperazione, importLordo, segnoImporto, nomeFile, env, log, dbSchemaCodSocieta);
					//fine LP PG200070 - 20200812
					
					if (isWasAlligned)
						log.info("Chiave transazione PayER " + chiaveTransazione + ", Id operazione Infogroup " + idOperazione + 
								" e Nome supporto " + nomeSupporto + " allineati su sistema PayER");
					else 
						log.warn("Chiave transazione PayER " + chiaveTransazione + ", Id operazione Infogroup " + idOperazione + 
							" e Nome supporto " + nomeSupporto + " non allineati");
				} 
				else if (typeRecord.equalsIgnoreCase("EF")) 
				{
					// RNINCATRANS fallimento --> aggiornare l'esito della transazione a 2 (transazione fallita) 
					// il MAV non esula da questo comportamento
					
					log.info("aggiornaTransazioniIG.request MAV - fallito " + chiaveTransazione);
					String days = env.getProperty(PropKeys.UPDATE_TRA_PENDING_DAY_INT.format());
					CommonsSOAPBindingStub commonsInterface = GatewaysIGHelper.getCommonsManager(env, dbSchemaCodSocieta);
					Calendar dataInizioTransazione = Calendar.getInstance();
					dataInizioTransazione.setTime(DateUtility.parse("ddMMyyHHmmss", dataCreazione + "235959"));
					dataInizioTransazione.add(Calendar.DATE, -Integer.parseInt(days));
					AggiornaTransazioniIGRequest aggiornaTransazioniIGReq = new AggiornaTransazioniIGRequest( "2", dataInizioTransazione, 
							AggiornaTransazioniIGRequestTipoGateway.I, AggiornaTransazioniIGRequestCanalePagemanto.WEB);
					log.info("aggiornaTransazioniIG.request.esitoTransazione - " + aggiornaTransazioniIGReq.getEsitoTransazione());
					log.info("aggiornaTransazioniIG.request.dataInizioTransazione - " + aggiornaTransazioniIGReq.getDataInizioTransazione());
					log.info("aggiornaTransazioniIG.request.tipoGateway - " + aggiornaTransazioniIGReq.getTipoGateway());
					log.info("aggiornaTransazioniIG.request.canalePagemanto - " + aggiornaTransazioniIGReq.getCanalePagemanto());
					
					AggiornaTransazioniIGResponse aggiornaTransazioniIGResp = commonsInterface.aggiornaTransazioniIG(aggiornaTransazioniIGReq);
					log.info("aggiornaTransazioniIG.CODE - " + aggiornaTransazioniIGResp.getResponse().getRetCode().getValue());
					log.info("aggiornaTransazioniIG.MESSAGE - " + aggiornaTransazioniIGResp.getResponse().getRetMessage());
					if (!isWasAlligned)
						isWasAlligned = aggiornaTransazioniIGResp.getResponse().getRetCode().getValue().equals(
								ResponseTypeRetCode._value1) ? true : false;
						
				}
				
				linea = br.readLine();
			}
			return isWasAlligned;
		} catch (Exception e) {
			log.error("Errore allineaTransazioniIG: ", e);
			return false;
		} finally {
			try { br.close(); isr.close(); fis.close();
			} catch (Exception ignore) {  }
		}
	}
	
	
	/**
	 * @param nomeSupporto
	 * @param dataIncassoBolletta
	 * @param chiaveTransazione
	 * @param idOperazione
	 * @param env
	 * @param log
	 * @return
	 */
	//inizio LP PG200070 - 20200812
	//private static boolean allineaTransazioneIG(String nomeSupporto, String dataIncassoBolletta, String dataAccredito, String chiaveTransazione, String idOperazione,
	//		String importoLordo, String segnoImporto, String nomeFile, PropertiesTree env, LoggerWrapper log, String dbSchemaCodSocieta) {
	private static boolean allineaTransazioneIG(String nomeSupporto, String dataIncassoBolletta, String dataAccredito, String chiaveTransazione, String idOperazione,
			String importoLordo, String segnoImporto, String nomeFile, PropertiesTree env, LoggerWrapper log, String dbSchemaCodSocieta) {
	//fine LP PG200070 - 20200812
		
		String emailNotificaAdmin = env.getProperty(PropKeys.EMAIL_NOTIFY_TO_LIST.format()); 
		boolean isWasAlligned = false;
		
		try {
			
			// we initialize commons manager
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(env, dbSchemaCodSocieta);
			
			// Recupero le informazioni relative alla transazione
			TransazioniBeanTransazioni beanTransazioni;
			try { 
				beanTransazioni = GatewaysIGHelper.recuperaTransazione(chiaveTransazione, idOperazione, binding, log);
			} catch (Exception e) {
				final String errMsg = "Transazione inesistente nel sistema PayER.<br><br>" + 
								"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
								"<br>Nome supporto: " + nomeSupporto ; 
				log.warn(errMsg.replaceAll("<br>", ""));
				// we send mail notify error
				GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env, emailNotificaAdmin,dbSchemaCodSocieta);
				return false;
			}
			
			//Recupero le informazioni del gateway di pagamento per recuperare l'email di notifica dell'amministratore.
			//inizio LP PG200070 - 20200812
			GatewayPagamentoDto gateway = GenericsGTW.getGatewayDetail(beanTransazioni.getChiave_gateway_di_pagamento(), env, dbSchemaCodSocieta);			
			//fine LP PG200070 - 20200812
			emailNotificaAdmin = gateway.getEmailNotificaAdmin();
			
			// Transazione contabilizzata positivamente con richiesta di storno in corso.
			if(beanTransazioni.getTipo_storno().equals("1") && segnoImporto.equals("+")){
				
				String errMsg = "Transazione contabilizzata positivamente con richiesta di storno in corso.<br><br>" +
				"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
				"<br>Nome supporto: " + nomeSupporto + "<br>Esito Transazione Payer: " + beanTransazioni.getFlag_esito_transazione() +
				"<br>Flag Storno: " + beanTransazioni.getTipo_storno() + "<br>Segno Importo: " + segnoImporto ;

				log.warn(errMsg);
				GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
				return false;
			}
			
			//Transazione stornata sul gateway di pagamento ma senza la presenza di una richiesta di storno.
			if(beanTransazioni.getTipo_storno().equals("0") && segnoImporto.equals("-")){
				
				String errMsg = "Transazione stornata sul gateway di pagamento ma senza la presenza di una richiesta di storno.<br><br>" +
				"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
				"<br>Nome supporto: " + nomeSupporto + "<br>Esito Transazione Payer: " + beanTransazioni.getFlag_esito_transazione() +
				"<br>Flag Storno: " + beanTransazioni.getTipo_storno() + "<br>Segno Importo: " + segnoImporto ;

				log.warn(errMsg);
				GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
				return false;
			}
			
			//Transazione stornata sul gateway di pagamento e già rendicontata all'ente.
			if(!beanTransazioni.getStato_rendicontazione().equals("0") && segnoImporto.equals("-")){
				
				String errMsg = "Transazione stornata sul gateway di pagamento e già rendicontata all'ente.<br><br>" +
				"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
				"<br>Nome supporto: " + nomeSupporto + "<br>Esito Transazione Payer: " + beanTransazioni.getFlag_esito_transazione() +
				"<br>Flag Storno: " + beanTransazioni.getTipo_storno() + "<br>Segno Importo: " + segnoImporto +
				"<br>Stato Rendicontazione: " + beanTransazioni.getStato_rendicontazione();

				log.warn(errMsg);
				
				StringBuilder sb=new StringBuilder(beanTransazioni.getNote_generiche());
				sb.append("|Data conferma storno: " + getDateTime());

		
				GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
				
				return aggiornaTransazioneStorno(chiaveTransazione, sb.toString(), "2", binding, log);
				
			}
			
			//Allineamento conferma transazione stornata.
			if(segnoImporto.equals("-")){
				
				if (beanTransazioni.getFlag_esito_transazione().equals("1") && beanTransazioni.getTipo_storno().equals("1")) {
					StringBuilder sb=new StringBuilder(beanTransazioni.getNote_generiche());
					sb.append("|Data conferma storno: " + getDateTime());
					return aggiornaTransazioneStorno(chiaveTransazione, sb.toString(), "2", binding, log);
				}
				else
					return false;
			}
			
			

			if (beanTransazioni.getFlag_esito_transazione().equals("0")) 
			{
				BigDecimal importoTotale = beanTransazioni.getImporto_totale_transazione().setScale(2);
				BigDecimal importoLordoFlusso = NumberUtility.parse(importoLordo, 11, 2);
				if (importoTotale.compareTo(importoLordoFlusso) != 0) 
				{
					String errMsg = "Incongruenza negli importi della transazione.<br><br>" +
									"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
									"<br>Nome supporto: " + nomeSupporto + "<br>Esito Transazione Payer: " + beanTransazioni.getFlag_esito_transazione() +
									"<br>Importo lordo nel flusso: " + importoLordoFlusso + "<br>Importo totale in base dati: " + importoTotale ;
					
					log.warn(errMsg);
					GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
					return false;
				}
				
				try 
				{ 
					// la transazione è MAV non devo aggiornarla ma devo chiamare 
					if (GenericsGTW.isGatewayMav(gateway))
						isWasAlligned = aggiornaTransazioneMAV(beanTransazioni, idOperazione, 
								DateUtility.parse("ddMMyy", dataIncassoBolletta), DateUtility.parse("ddMMyy", dataAccredito),nomeFile, binding, log);
					else
						isWasAlligned = aggiornaTransazione(beanTransazioni, idOperazione, 
							DateUtility.parse("ddMMyy", dataIncassoBolletta), DateUtility.parse("ddMMyy", dataAccredito), nomeFile, binding, log);
				} 
				catch (Exception e) 
				{
					String errMsg = "Errore nell'aggiornamento della transazione.<br><br>" +
									"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
									"<br>Nome supporto: " + nomeSupporto + "<br>Messaggio Errore: " + e.getMessage(); 
					log.warn(errMsg, e);
					GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env, emailNotificaAdmin,dbSchemaCodSocieta);
					return false;
				}
				
			} 
			else if (beanTransazioni.getFlag_esito_transazione().equals("2")) 
			{
				isWasAlligned = false;
				BigDecimal importoTotale = beanTransazioni.getImporto_totale_transazione().setScale(2);
				BigDecimal importoLordoFlusso = NumberUtility.parse(importoLordo, 11, 2);
				String errMsg = "Transazione fallita su sistema PayER.<br><br>" +
 								"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
								"<br>Nome supporto: " + nomeSupporto + "<br>Esito Transazione Payer: " + beanTransazioni.getFlag_esito_transazione() +
								"<br>Importo lordo nel flusso: " + importoLordoFlusso + "<br>Importo totale in base dati " + importoTotale ;
				log.warn(errMsg);
				GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
			}
			else if (beanTransazioni.getFlag_esito_transazione().equals("1"))
			{
				if (GenericsGTW.isGatewayRid(gateway)) {
					//se la transazione è stata pagata con un gateway di tipo Rid-online
					//devo inviare la mail al contribuente poichè la precedente mail inviata a conclusione
					//del pagamento on-line riportava la dicitura "SALVO BUON FINE", mentre in questo caso
					//viene confermato l'avvenuto pagamento
					//aggiornaTransazione(beanTransazioni, idOperazione, DateUtility.parse("ddMMyy", dataIncassoBolletta), DateUtility.parse("ddMMyy", dataAccredito), nomeFile, binding, log);
					aggiornaTransazione(beanTransazioni, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getData_effettivo_pagamento_su_gateway().getTime(), DateUtility.parse("ddMMyy", dataAccredito), nomeFile, binding, log);
					isWasAlligned = true;
				} else {
					
					BigDecimal importoTotale = beanTransazioni.getImporto_totale_transazione().setScale(2);
					BigDecimal importoLordoFlusso = NumberUtility.parse(importoLordo, 11, 2);
					if (importoTotale.compareTo(importoLordoFlusso) != 0) {
						String errMsg = "Incongruenza negli importi della transazione.<br><br>" +
										"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
										"<br>Nome supporto: " + nomeSupporto + "<br>Esito Transazione Payer: " + beanTransazioni.getFlag_esito_transazione() +
										"<br>Importo lordo nel flusso: " + importoLordoFlusso + "<br>Importo totale in base dati: " + importoTotale ;
						log.warn(errMsg);
						GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
						return false;
					}
					
					try {
						//isWasAlligned=aggiornaTransazione(beanTransazioni, idOperazione, DateUtility.parse("ddMMyy", dataIncassoBolletta), DateUtility.parse("ddMMyy", dataAccredito), nomeFile, binding, log);
						aggiornaTransazione(beanTransazioni, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getData_effettivo_pagamento_su_gateway().getTime(), DateUtility.parse("ddMMyy", dataAccredito), nomeFile, binding, log);
						
						//GatewaysIGHelper.notificaTransazione(beanTransazioni, env, "O", dbSchemaCodSocieta);
					} catch (Exception e) {
						String errMsg = "Errore nella notifica della transazione.<br><br>" +
										"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
										"<br>Nome supporto: " + nomeSupporto + "<br>Messaggio Errore: " + e.getMessage(); 
						log.warn(errMsg, e);
						GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
						return false;
					}
					
					
				}
				
			}
			
			// Viene notificata la transazione nel caso ci sia stato un allineamento con il flusso RNINCATRANS
			if (isWasAlligned) 
			{
				try 
				{ 
					// la transazione è MAV non devo aggiornarla ma devo chiamare 
					if (GenericsGTW.isGatewayMav(gateway))
						notificaMAVPagato(chiaveTransazione, env, log, dbSchemaCodSocieta);
					else if (GenericsGTW.isGatewayRid(gateway))
						notificaRIDPagato(chiaveTransazione, env, log, dbSchemaCodSocieta);
					else
						//notificaTransazioneDirect(chiaveTransazione, env);
						notificaTransazione(beanTransazioni, env, "O", dbSchemaCodSocieta);
				} 
				catch (Exception e) 
				{ 
					String errMsg = "Errore nella notifica della transazione.<br><br>" +
									"ID transazione PayER: " + chiaveTransazione + "<br>ID operazione Infogroup: " + idOperazione + 
									"<br>Nome supporto: " + nomeSupporto + "<br>Messaggio Errore: " + e.getMessage(); 
					log.warn(errMsg, e);
					GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
					return false;
				}
			}
			
			return true;

		} catch (Exception e) {
			String errMsg = "Errore allineaTransazioneIG: " + e.getMessage();
			log.error(errMsg, e);
			GatewaysIGHelper.notificaMancatoAllineamento(errMsg, log, env,emailNotificaAdmin,dbSchemaCodSocieta);
			return false;
		}
	}
	/**
	 * @param f
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public static boolean move(File f, String dir) throws Exception {
		return new File(f.getAbsolutePath()).renameTo(new File(dir + f.getName()));
	}
	
	/**
	 * E' arrivata la risposta dal InfoGroup con il MAV associato -->
	 * 	- recupero la transazione indicata, 
	 * 	- aggiorno la transazione con il numero MAV
	 *  - notifico con mail all'utente
	 *  
	 * @param gtwigRequest
	 * @param response
	 * @param propertiesTree
	 * @param LoggerWrapper
	 * @return
	 * @throws Exception
	 */
	public static boolean gestisciMAVPostS2S(RedirectToGTWIGRequest gtwigRequest, RedirectToGTWIGResponse response, 
			PropertiesTree propertiesTree, LoggerWrapper LoggerWrapper, String dbSchemaCodSocieta) throws Exception
    {
		boolean result = true;		
		
		// we initialize commons manager
		CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree, dbSchemaCodSocieta);
		// we retrieve beanTransazioni
		TransazioniBeanTransazioni beanTransazioni = null;
		try 
		{ 
			beanTransazioni = GatewaysIGHelper.recuperaTransazione(gtwigRequest.getCodiceMerchant(), gtwigRequest.getIdOperazione(), binding, LoggerWrapper);		
		} 
		catch (Exception e) 
		{ 
			throw new Exception("gestisciMAV - Errore nel recupero della transazione");
		}

		if (beanTransazioni.getChiave_transazione() == null)
			throw new Exception("gestisciMAV CodiceMerchant " + gtwigRequest.getCodiceMerchant() + " non trovato!");
					
		// we update transaction
		try 
		{ 
				
			// bisogna aggiornare la transazione SOLO con il numero MAV 
			//il resto si aggiorna quando c'è l'allineamento con il RNINCATRANS
			AggiornaTransazioneIGMavRequest aggiornaTransazione = new AggiornaTransazioneIGMavRequest();
			aggiornaTransazione.setChiaveTransazione(beanTransazioni.getChiave_transazione());
			aggiornaTransazione.setNumeroMAV(response.getMavNumeroMav());
        	AggiornaTransazioneIGMavResponse aggiornaTransazioneResponse = binding.aggiornaTransazioneIGMav(aggiornaTransazione);
        	
        	LoggerWrapper.info("aggiornaTransazioneMAV response code - " + aggiornaTransazioneResponse.getResponse().getRetCode().getValue());
        	LoggerWrapper.info("aggiornaTransazioneMAV response message - " + aggiornaTransazioneResponse.getResponse().getRetMessage());
			
		} 
		catch (Exception e) 
		{
			LoggerWrapper.error("aggiornaTransazione failed, generic error due to: ", e);
			throw new Exception("gestisciMAV  CodiceMerchant " + gtwigRequest.getCodiceMerchant() + " fallito"); 
		}
		
		// invio mail e salvo il pdf nel file syste e mi ritorna tale posizione con il nome del file
		notificaMAVNuovo(gtwigRequest.getCodiceMerchant(), gtwigRequest.getCodDebitore(), response, propertiesTree, LoggerWrapper, dbSchemaCodSocieta);
    	   	
		return result;
    }    
	 
	/**
	 * crea il pdf e ne invia una copia all'utente come notifica,
	 * e setta il filePath del pdf nella response passata
	 * @param beanTransazioni
	 * @throws Exception
	 */
	private static boolean notificaMAVNuovo(String idTransazione, String codiceFiscale,
			RedirectToGTWIGResponse response, PropertiesTree env, LoggerWrapper LoggerWrapper, String dbSchemaCodSocieta) throws Exception 
	{
		// we initialize notificheStub
		NotificheSOAPBindingStub ntfStub = getNotificheWS(env, dbSchemaCodSocieta);

		NotificaMAVRequestType mavNotificaRequest = new NotificaMAVRequestType();
		mavNotificaRequest.setChiave_transazione(idTransazione);
		mavNotificaRequest.setTipo_notifica("S"); // salvataggio
		mavNotificaRequest.setModalita("O");	// online
		mavNotificaRequest.setNumMAV(response.getMavNumeroMav());
		mavNotificaRequest.setPdfBinario(response.getMavPdfBase64());
		mavNotificaRequest.setCodiceFiscale(codiceFiscale);
		
		// risposta dalla Notifica che crea anche il pdf
	    NotificaMAVResponseType responseNotificaMAV = ntfStub.notificaMAV(mavNotificaRequest);
	    if (isNotificaOK(responseNotificaMAV))
	    {
	    	response.setMavPdfFilePath(responseNotificaMAV.getPdfFilePath());
	    	return true;
	    }
	    else
	    {	    	
	    	LoggerWrapper.error("notificaMAVNuovo failed, not saved file pdf: ");
	    	response.setMavPdfFilePath("");
	    	return false;
	    }
	    
	}
	/**
	 * notifica il pagamento tramite batch
	 * @param beanTransazioni
	 * @throws Exception
	 */
	private static boolean notificaMAVPagato(String chiaveTransazione, PropertiesTree env, LoggerWrapper LoggerWrapper, String dbSchemaCodSocieta) throws Exception {
		// we initialize notificheStub
		NotificheSOAPBindingStub ntfStub = getNotificheWS(env, dbSchemaCodSocieta);

		NotificaMAVRequestType mav=new NotificaMAVRequestType();
		mav.setChiave_transazione(chiaveTransazione);
		mav.setTipo_notifica("P"); // pagato
		mav.setModalita("B");	// manager/batch
		mav.setNumMAV("");
		mav.setCodiceFiscale("");
		
		NotificaMAVResponseType responseNotificaMAV = ntfStub.notificaMAV(mav);
		if (isNotificaOK(responseNotificaMAV))
	    {
		  	return true;
	    }
	    else
	    {
	    	LoggerWrapper.error("notificaMAVPagato failed, not send mail");		    
		    return false;
	    }
	}
	
	private static boolean notificaRIDPagato(String chiaveTransazione, PropertiesTree env, LoggerWrapper LoggerWrapper, String dbSchemaCodSocieta) throws Exception
	{
		String operFunzione = "RN";
		String operInvio = "OK";
		String operTipo = "B";
		
		NotificaRIDRequestType notificaRequestRID = new NotificaRIDRequestType();
		// parametri per mail
		notificaRequestRID.setOperazioneFunzione(operFunzione);
		notificaRequestRID.setOperazioneInvio(operInvio);
		notificaRequestRID.setOperazioneTipo(operTipo);
		
		notificaRequestRID.setChiaveTransazione(chiaveTransazione);
		
		NotificheSOAPBindingStub notifiheWs = getNotificheWS(env, dbSchemaCodSocieta);
		
		NotificaRIDResponseType responseNotificaRID = notifiheWs.notificaRID(notificaRequestRID);
		if (responseNotificaRID!=null && responseNotificaRID.getResponse().getRetcode().getValue().equals("00"))
	    {
		  	return true;
	    }
	    else
	    {
	    	LoggerWrapper.error("notificaRIDPagato failed, not send notifica");		    
		    return false;
	    }
	}
	
	/**
	 * ritorna se la risposta del la notifica è valida
	 * @param responseNotificaMAV
	 * @return
	 */
	private static boolean isNotificaOK(NotificaMAVResponseType responseNotificaMAV)
	{
		return responseNotificaMAV.getResponse().getRetcode().getValue().equals(Generics_RID.PAYER_CODE_OK);
	}
	
	/**
	 * ritorna il server delle notifiche
	 * @param env
	 * @return
	 * @throws AxisFault
	 * @throws MalformedURLException
	 * @throws ServiceException 
	 */
	public static NotificheSOAPBindingStub getNotificheWS(PropertiesTree env, String dbSchemaCodSocieta) throws AxisFault, MalformedURLException, ServiceException
	{
		NotificheServiceLocator serviceLocator = new NotificheServiceLocator();
		NotificheSOAPBindingStub binding = (NotificheSOAPBindingStub)serviceLocator.getNotifichePort(
				new URL(env.getProperty(PropKeys.NOTIFICHE_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));
		
		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	
		
		return binding;
	}
	
	public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	//PG150180_001 GG - inizio
	public static ConfigUtenteTipoServizioEnteDetailResponse getConfigUtenteTipoServizioEnteDetailResponse(PropertiesTree propertiesTree, String dbSchemaCodSocieta, String companyCode, String codiceUtente, String chiaveEnte, String codiceTipologiaServizio) throws Exception {
		
		ConfigUtenteTipoServizioEnteDetailResponse res = null;
		ConfigUtenteTipoServizioEnteDetailRequest in = new ConfigUtenteTipoServizioEnteDetailRequest(companyCode, codiceUtente, chiaveEnte, codiceTipologiaServizio);
		in.setCompanyCode(companyCode == null ? "" : companyCode);
		in.setUserCode(codiceUtente == null ? "" : codiceUtente);
		in.setChiaveEnte(chiaveEnte == null ? "" : chiaveEnte);
		in.setCodiceTipologiaServizio(codiceTipologiaServizio == null ? "" : codiceTipologiaServizio);
		ConfigUtenteTipoServizioEnteImplementationStub binding = GatewaysIGHelper.getConfigUtenteTipoServizioEnteImplementationStub(propertiesTree, dbSchemaCodSocieta);
				
		res = binding.getConfigUtenteTipoServizioEnte(in);
		
		return res;
	}
	
	public static EnteDetailResponse getEnteDetailSearchResponse(PropertiesTree propertiesTree, String dbSchemaCodSocieta, String companyCode, String userCode, String chiaveEnte) throws Exception
	{
		 
		EnteDetailResponse res = null;
		EnteDetailRequest in = new EnteDetailRequest(companyCode, userCode, chiaveEnte);
		in.setCompanyCode(companyCode == null ? "" : companyCode);
		in.setUserCode(userCode == null ? "" : userCode);
		in.setChiaveEnte(chiaveEnte == null ? "" : chiaveEnte);
		
		EnteImplementationStub binding = GatewaysIGHelper.getEnteImplementationStub(propertiesTree, dbSchemaCodSocieta);
		res = binding.getEnte(in);

		return res;
	}	

	
	public static AnagEnteSearchResponse getAnagEnteSearchResponse(PropertiesTree propertiesTree, String dbSchemaCodSocieta, String chiaveEnte, String siglaProvincia) throws Exception
	{
		AnagEnteSearchResponse res = null;
		AnagEnteSearchRequest in = new AnagEnteSearchRequest();
		
		in.setChiaveEnte(chiaveEnte);
		in.setSiglaProvincia(siglaProvincia);
		
		in.setRowsPerPage(0);
		in.setPageNumber(0);
		in.setOrder("");
		in.setCodiceBelfiore("");
		in.setCodiceEnte("");
		in.setDescrizioneEnte("");

		AnagEnteImplementationStub  binding = GatewaysIGHelper.getAnagEnteImplementationStub(propertiesTree, dbSchemaCodSocieta);
		res = binding.getAnagEntes(in);
		return res;
	}
	//PG150180_001 GG - fine
	
	//inizio LP PG200070
	public static ConfigPagamentoSingleResponse recuperaFunzioneEnte(PropertiesTree propertiesTree, String dbSchemaCodSocieta, ConfigPagamentoSingleRequest in) throws Exception
	{
		ConfigPagamentoSingleResponse res = null;
		CommonsSOAPBindingStub  binding = GatewaysIGHelper.getCommonsManager(propertiesTree, dbSchemaCodSocieta);
		res = binding.recuperaFunzioneEnte(in);
		return res;
	}
	//fine LP PG200070
}