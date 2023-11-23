/**
 * GatewaysImplementationImpl.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf311012.09 v41410192429
 */
package com.seda.payer.gateways.webservice.source;

//PG150180_001 GG - inizio
import gov.telematici.pagamenti.ws.EsitoChiediStatoRPT;
import gov.telematici.pagamenti.ws.FaultBean;
import gov.telematici.pagamenti.ws.NodoChiediCopiaRTRisposta;
import gov.telematici.pagamenti.ws.NodoChiediStatoRPTRisposta;
import gov.telematici.pagamenti.ws.NodoInviaRPTRisposta;
import gov.telematici.pagamenti.ws.TipoStoricoRPT;
import gov.telematici.pagamenti.ws.TipoStoricoVersamento;
//PG150180_001 GG - fine

//inizio LP PG1900XX_001
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.CtMessaggiMessaggio;
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.CtRichiestaStandard;
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.CtRispostaStandard;
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.JppaPdpExternalFacetServiceLocator;
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.JppaPdpExternalServicesEndpoint;
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.StEsito;
import it.maggioli.informatica.jcitygov.pagopa.payservice.pdp.connector.jppapdp.external.StOperazione;
//fine LP PG1900XX_001

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.regex.Pattern;

//inizio LP PG200070
//import javax.naming.Context;
//fine LP PG200070
//PG150180_001 GG - inizio
import javax.sql.rowset.CachedRowSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.rpc.ServiceException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
//PG150180_001 GG - fine
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
//import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;
import org.esed.MyPayPagonet.webservice.operazioni.ChiediEsitoCarrelloDovutiMyPay;
import org.esed.MyPayPagonet.webservice.operazioni.ChiediEsitoCarrelloDovutiMyPayRisposta;
import org.esed.MyPayPagonet.webservice.operazioni.ChiediPagatiConRicevutaMyPay;
import org.esed.MyPayPagonet.webservice.operazioni.ChiediPagatiConRicevutaMyPayRisposta;
import org.esed.MyPayPagonet.webservice.operazioni.EsitoChiediEsitoCarrelloDovutiMyPayRisposta;
import org.esed.MyPayPagonet.webservice.operazioni.InviaCarrelloDovutiMyPay;
import org.esed.MyPayPagonet.webservice.operazioni.InviaCarrelloDovutiMyPayRisposta;
import org.esed.MyPayPagonet.webservice.operazioni.MyPayPagonetFacetServiceLocator;
import org.esed.MyPayPagonet.webservice.operazioni.MyPayPagonetServicesEndpoint;

import java.util.Base64;
import CoreComponentTypes.apis.ebay.BasicAmountType;
import PayPalAPI.api.ebay.DoExpressCheckoutPaymentReq;
import PayPalAPI.api.ebay.DoExpressCheckoutPaymentRequestType;
import PayPalAPI.api.ebay.DoExpressCheckoutPaymentResponseType;
import PayPalAPI.api.ebay.GetExpressCheckoutDetailsReq;
import PayPalAPI.api.ebay.GetExpressCheckoutDetailsRequestType;
import PayPalAPI.api.ebay.GetExpressCheckoutDetailsResponseType;
import PayPalAPI.api.ebay.GetTransactionDetailsReq;
import PayPalAPI.api.ebay.GetTransactionDetailsRequestType;
import PayPalAPI.api.ebay.GetTransactionDetailsResponseType;
import PayPalAPI.api.ebay.PayPalAPIAASoapBindingStub;
import PayPalAPI.api.ebay.PayPalAPISoapBindingStub;
import PayPalAPI.api.ebay.SetExpressCheckoutReq;
import PayPalAPI.api.ebay.SetExpressCheckoutRequestType;
import PayPalAPI.api.ebay.SetExpressCheckoutResponseType;
import PayPalAPI.api.ebay.TransactionSearchReq;
import PayPalAPI.api.ebay.TransactionSearchRequestType;
import PayPalAPI.api.ebay.TransactionSearchResponseType;

//import com.aciworldwide.commerce.gateway.plugins.NotEnoughDataException;
//import com.aciworldwide.commerce.gateway.plugins.e24TranPipe;
import com.e_sed.satispay.client.SatispayClient;
import com.e_sed.satispay.client.domains.ChargeRequest;
import com.e_sed.satispay.client.domains.ChargeResponse;
import com.e_sed.satispay.client.domains.UserResponse;
import com.esed.easybridge.webservice.dati.InviaCarrelloRTPRequest;
import com.esed.easybridge.webservice.dati.InviaCarrelloRTPResponse;
import com.esed.easybridge.webservice.source.EasybridgeInterface;
import com.esed.easybridge.webservice.source.EasybridgeLocator;
import com.esed.easybridge.webservice.source.EasybridgeSOAPBindingStub;
import com.esed.log.req.dati.LogPap;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.commons.string.Convert;
//inizio LP PG200070
//import com.seda.j2ee5.maf.components.servicelocator.ServiceLocator;
//fine LP PG200070
import com.seda.payer.commons.security.UUIDGenerator;
import com.seda.payer.commons.utility.FileUtility;
//inizio LP PG200070
//import com.seda.payer.facade.dto.ConfigPagamentoDto;
//import com.seda.payer.facade.dto.EnteDto;
//import com.seda.payer.facade.interfaces.CommonsFacade;
//import com.seda.payer.facade.interfaces.CommonsFacadeRemoteHome;
//import com.seda.payer.facade.interfaces.EnteFacade;
//import com.seda.payer.facade.interfaces.EnteFacadeRemoteHome;
//fine LP PG200070
import com.seda.payer.gateways.facade.bean.GatewayPagamentoBean;
import com.seda.payer.gateways.facade.dto.GatewayPagamentoDto;
import com.seda.payer.gateways.facade.dto.RidDto;
import com.seda.payer.gateways.facade.util.LoggerUtil;
import com.seda.payer.gateways.webservice.config.CanaleAttivazioneTypeKeys;
import com.seda.payer.gateways.webservice.config.GatewaysTypeKeys;
import com.seda.payer.gateways.webservice.config.PropKeys;
import com.seda.payer.gateways.webservice.config.RptNodoSpcDatiPagamenti;
import com.seda.payer.gateways.webservice.config.StrumentoPagamentoTypeKeys;
import com.seda.payer.gateways.webservice.dati.AllineaAutomaticoTransazioneIGResponse;
import com.seda.payer.gateways.webservice.dati.AllineaAutomaticoTransazioneRequest;
import com.seda.payer.gateways.webservice.dati.AllineaAutomaticoTransazioneResponse;
import com.seda.payer.gateways.webservice.dati.AllineaManualeTransazioneRequest;
import com.seda.payer.gateways.webservice.dati.AllineaManualeTransazioneResponse;
import com.seda.payer.gateways.webservice.dati.AllineaTransazioneRequest;
import com.seda.payer.gateways.webservice.dati.AllineaTransazioneResponse;
import com.seda.payer.gateways.webservice.dati.GatewayType;
import com.seda.payer.gateways.webservice.dati.GeneraFlussiRendicontazionePosRequest;
import com.seda.payer.gateways.webservice.dati.GeneraFlussiRendicontazionePosResponse;
import com.seda.payer.gateways.webservice.dati.GeneraMessageGTWRequest;
import com.seda.payer.gateways.webservice.dati.GeneraMessageGTWResponse;
import com.seda.payer.gateways.webservice.dati.IntegraGTWAsincRequest;
import com.seda.payer.gateways.webservice.dati.IntegraGTWAsincResponse;
import com.seda.payer.gateways.webservice.dati.IntegraGTWSincDTTResponse;
import com.seda.payer.gateways.webservice.dati.IntegraGTWSincRequest;
import com.seda.payer.gateways.webservice.dati.IntegraGTWSincResponse;
import com.seda.payer.gateways.webservice.dati.IntegraGTWSinc_MultiEnteRequest;
import com.seda.payer.gateways.webservice.dati.RIDAdesioneRequest;
import com.seda.payer.gateways.webservice.dati.RIDAdesioneResponse;
import com.seda.payer.gateways.webservice.dati.RIDAllineaArchiviazioneRequest;
import com.seda.payer.gateways.webservice.dati.RIDAllineaArchiviazioneResponse;
import com.seda.payer.gateways.webservice.dati.RIDAllineaRequest;
import com.seda.payer.gateways.webservice.dati.RIDAllineaResponse;
import com.seda.payer.gateways.webservice.dati.RIDRevocaRequest;
import com.seda.payer.gateways.webservice.dati.RIDRevocaResponse;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoRequest;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoResponse;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoResponseStatoRichiesta;
import com.seda.payer.gateways.webservice.dati.RIDVerificaStatoResponseTipoRichiesta;
import com.seda.payer.gateways.webservice.dati.RedirectToGTWRequest;
import com.seda.payer.gateways.webservice.dati.RedirectToGTWResponse;
import com.seda.payer.gateways.webservice.dati.ResponseType;
import com.seda.payer.gateways.webservice.dati.ResponseTypeRetCode;
import com.seda.payer.gateways.webservice.dati.RichiediStornoResponse;
import com.seda.payer.gateways.webservice.dati.TransazioneType;
import com.seda.payer.gateways.webservice.dati.VerificaEsitoGTWRequest;
import com.seda.payer.gateways.webservice.dati.VerificaEsitoGTWResponse;
import com.seda.payer.gateways.webservice.handler.WebServiceHandler;

import com.seda.payer.gateways.webservice.helper.GatewaysIGHelper;
import com.seda.payer.gateways.webservice.helper.Generics;
import com.seda.payer.gateways.webservice.helper.GenericsGTW;
import com.seda.payer.gateways.webservice.helper.Generics_RID;
//import com.seda.payer.gateways.webservice.helper.Generics_RID_AllineaPDF;
import com.seda.payer.gateways.webservice.helper.Generics_RID_WS;
import com.seda.payer.gateways.webservice.helper.NodoSpcHelper;		//PG150180_001 GG
import com.seda.payer.gateways.webservice.helper.RedirectToGTWCartaSIResponse;
import com.seda.payer.gateways.webservice.helper.RedirectToGTWIGRequest;
import com.seda.payer.gateways.webservice.helper.RedirectToGTWIGResponse;
import com.seda.payer.gateways.webservice.helper.RedirectToGTWMyBank;
import com.seda.payer.gateways.webservice.helper.RedirectToGTWPagOnline;
import com.seda.payer.gateways.webservice.helper.RedirectToGTWPagOnlineResponse;
import com.seda.payer.gateways.webservice.helper.RedirectToGTWCartaSi;
import com.seda.payer.gateways.webservice.log.Events;
import com.seda.payer.gateways.webservice.srv.FaultType;
//inizio LP PG1900XX_001
import com.esed.mybridge.webservice.operazioni.EsedFacetServiceLocator;
import com.esed.mybridge.webservice.operazioni.EsedServicesEndpoint;
import com.esed.mybridge.webservice.operazioni.head.ParametroVuoto;
import com.ibm.wsdl.util.StringUtils;
//fine LP PG1900XX_001
import com.seda.payer.notifiche.webservice.dati.RecuperaPdfRIDRequestType;
import com.seda.payer.notifiche.webservice.dati.RecuperaPdfRIDResponseType;
import com.seda.payer.notifiche.webservice.source.NotificheSOAPBindingStub;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaEsitoTransazioneRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaEsitoTransazioneResponse;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneGenericRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaTransazioneGenericResponse;
//PG150180_001 GG - inizio
import com.seda.payer.pgec.webservice.commons.dati.AggiornaPSNRequest;
import com.seda.payer.pgec.webservice.commons.dati.AggiornaPSNResponse;
import com.seda.payer.pgec.webservice.commons.dati.BeanFreccia;
import com.seda.payer.pgec.webservice.commons.dati.BeanIV;
import com.seda.payer.pgec.webservice.commons.dati.BeanIci;
import com.seda.payer.pgec.webservice.commons.dati.ConfigPagamento;
import com.seda.payer.pgec.webservice.commons.dati.ConfigPagamentoSingleRequest;
import com.seda.payer.pgec.webservice.commons.dati.ConfigPagamentoSingleResponse;
import com.seda.payer.pgec.webservice.commons.dati.InizializzaTransazioneResponse;
import com.seda.payer.pgec.webservice.commons.dati.InserisciRptRequest;
import com.seda.payer.pgec.webservice.commons.dati.InserisciRptResponse;
//PG150180_001 GG - fine
import com.seda.payer.pgec.webservice.commons.dati.RecuperaTransazioneRequestType;
import com.seda.payer.pgec.webservice.commons.dati.RecuperaTransazioneResponseType;
//PG150180_001 GG - inizio
import com.seda.payer.pgec.webservice.commons.dati.RptNodoSpc;
import com.seda.payer.pgec.webservice.commons.dati.RptNodoSpcRequest;
import com.seda.payer.pgec.webservice.commons.dati.RptNodoSpcResponse;
import com.seda.payer.pgec.webservice.commons.dati.UpdateRptNodoSpcRequest;
//PG150180_001 GG - fine
import com.seda.payer.pgec.webservice.commons.dati.Transazioni;
import com.seda.payer.pgec.webservice.commons.dati.TransazioniBeanTransazioni;
import com.seda.payer.pgec.webservice.commons.source.CommonsSOAPBindingStub;
//PG150180_001 GG - inizio
import com.seda.payer.pgec.webservice.anagente.dati.AnagEnteSearchResponse;
import com.seda.payer.pgec.webservice.configutentetiposervizioente.dati.ConfigUtenteTipoServizioEnteDetailResponse;
import com.seda.payer.pgec.webservice.ente.dati.EnteDetailResponse;
import com.seda.payer.pgec.webservice.mip.dati.MINSavePaymentStatusRequest;
import com.seda.payer.pgec.webservice.mip.dati.MINSaveRequest;
import com.seda.payer.pgec.webservice.mip.dati.MINUpdatePaymentStatusRequest;
import com.seda.payer.pgec.webservice.mip.dati.MIPUpdatePaymentStatusRequest;
import com.seda.payer.pgec.webservice.mip.dati.ModuloIntegrazionePagamentiNodo;
import com.seda.payer.pgec.webservice.mip.dati.ModuloIntegrazionePagamentiNodoPaymentStatus;
import com.seda.payer.pgec.webservice.mip.dati.ModuloIntegrazionePagamentiPaymentStatus;
import com.seda.payer.pgec.webservice.mip.dati.StatusResponse;
import com.seda.payer.pgec.webservice.mip.source.MIPSOAPBidingStub;
//PG150180_001 GG - fine

import eBLBaseComponents.apis.ebay.CountryCodeType;
import eBLBaseComponents.apis.ebay.CurrencyCodeType;
import eBLBaseComponents.apis.ebay.CustomSecurityHeaderType;
import eBLBaseComponents.apis.ebay.DoExpressCheckoutPaymentRequestDetailsType;
import eBLBaseComponents.apis.ebay.DoExpressCheckoutPaymentResponseDetailsType;
import eBLBaseComponents.apis.ebay.ErrorType;
import eBLBaseComponents.apis.ebay.PaymentActionCodeType;
import eBLBaseComponents.apis.ebay.PaymentDetailsItemType;
import eBLBaseComponents.apis.ebay.PaymentDetailsType;
import eBLBaseComponents.apis.ebay.PaymentTransactionSearchResultType;
import eBLBaseComponents.apis.ebay.SetExpressCheckoutRequestDetailsType;
import eBLBaseComponents.apis.ebay.UserIdPasswordType;

public class Gateways extends WebServiceHandler implements com.seda.payer.gateways.webservice.source.GatewaysInterface {
	
	static final PaymentActionCodeType DEFAULT_PAYMENT_ACTION = PaymentActionCodeType.Sale;
	static final String PP_ACK_SUCCESS = "Success";
	static final String EMPTY_TOKEN = " ";
	static final BigDecimal EMPTY_ORDER_TOTAL = new BigDecimal(0);
	enum ChannelNotAllowed {
		ATM
	}
	
	public static final String TRA_COMPLETED_STATE = "1";
	public static final String TRA_FAILED_STATE = "2";
	protected String flowPath;
	
	
	protected String flowInProgressPath;
	protected String flowProcessedPath;
	protected String flowRejectedPath;
	protected String setefiDttType;
	protected String setefiDttAlias;
	protected String setefiDttPath;
	protected String setefiDttAction;
	protected String setefiDttCurrencycode;
	protected String setefiDttUdf1;
	protected String setefiDdttResult;
	
	protected String template="";
	
	
	protected void initConfig() {
		flowPath = propertiesTree().getProperty(PropKeys.FLOW_PATH.format(PropKeys.DEFAULT_NODE.format()));
		flowInProgressPath = propertiesTree().getProperty(PropKeys.FLOW_IN_PROGRESS_PATH.format(PropKeys.DEFAULT_NODE.format()));
		flowProcessedPath = propertiesTree().getProperty(PropKeys.FLOW_PROCESSED_PATH.format(PropKeys.DEFAULT_NODE.format()));
		flowRejectedPath = propertiesTree().getProperty(PropKeys.FLOW_REJECTED_PATH.format(PropKeys.DEFAULT_NODE.format()));
	}

	protected void initSetefiConfig()
	{
		setefiDttType = propertiesTree().getProperty(PropKeys.SETEFI_DTT_TYPE.format(PropKeys.DEFAULT_NODE.format()));;
		setefiDttAlias = propertiesTree().getProperty(PropKeys.SETEFI_DTT_ALIAS.format(PropKeys.DEFAULT_NODE.format()));;
		setefiDttPath = propertiesTree().getProperty(PropKeys.SETEFI_DTT_PATH.format(PropKeys.DEFAULT_NODE.format()));;
		setefiDttAction = propertiesTree().getProperty(PropKeys.SETEFI_DTT_ACTION.format(PropKeys.DEFAULT_NODE.format()));;
		setefiDttCurrencycode = propertiesTree().getProperty(PropKeys.SETEFI_DTT_CURRENCYCODE.format(PropKeys.DEFAULT_NODE.format()));;
		setefiDttUdf1 = propertiesTree().getProperty(PropKeys.SETEFI_DTT_UDF1.format(PropKeys.DEFAULT_NODE.format()));;
		setefiDdttResult = propertiesTree().getProperty(PropKeys.SETEFI_DTT_RESULT.format(PropKeys.DEFAULT_NODE.format()));;
	}
	//inizio LP PG200070 - 20200812
	private GatewayPagamentoBean gatewayFacade = null;

	private GatewayPagamentoBean getFacadeService() throws Exception
	{
		if(gatewayFacade == null) {
			gatewayFacade = new GatewayPagamentoBean(configuration);
		}
		return gatewayFacade;
	}
	//fine LP PG200070 - 20200812
	
	/**
	 * Dovrà effettuare l'integrazione con il gateway di pagamento in modalità sincrona.
	 * <br>
	 * Avrà come parametri di input la chiave del gateway <code>chiaveGTW</code>, l'attributo Custom <code>chiaveTransazione</code>
	 * cui verrà passata l'informazione della chiave della transazione, l'importo totale della transazione
	 * <code>impTotTransazione</code> e l'email del contribuente <code>emailContribuente</code>.
	 * <br>
	 * Avrà come parametri di output un codice esito operazione <code>retCode</code>, un messaggio di ritorno
	 * relativo al codice esito <code>retMessage</code> e l'url completo del token <code>urlRedirectGTW</code>
	 * che servirà all'applicazione web Payer per effettuare il redirect al Gateway.
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.IntegraGTWSincRequest
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#integraGTWSinc(com.seda.payer.gateways.webservice.dati.IntegraGTWSincRequest)
	 */
	public IntegraGTWSincResponse integraGTWSinc(IntegraGTWSincRequest in) throws RemoteException, FaultType {
		try {
			info("Inizio integraGTWSinc");
			
			
			
			
			// we retry gateway info
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), EMPTY_TOKEN), 
							in.getImpTotTransazione()));
			
			//salviamo il template e modifichiamo la url per gestire il bilinguismo
			if(in.getTransazione().getChiaveTransazione().contains("||")){
				String DNS_IT=propertiesTree().getProperty(PropKeys.DNS_IT.format(dbSchemaCodSocieta));
				String DNS_DE=propertiesTree().getProperty(PropKeys.DNS_DE.format(dbSchemaCodSocieta));
				if(DNS_IT==null){
					System.out.println("La chiave per il bilinguismo gatewaysWs.DNS_IT."+dbSchemaCodSocieta+" non è definita!");
				}
				if(DNS_DE==null){
					System.out.println("La chiave per il bilinguismo gatewaysWs.DNS_DE."+dbSchemaCodSocieta+" non è definita!");
				}
				template=in.getTransazione().getChiaveTransazione().split("\\|\\|")[1];
				System.out.println("Template: " + template);
				in.getTransazione().setChiaveTransazione(in.getTransazione().getChiaveTransazione().split("\\|\\|")[0]);
				if(template.equalsIgnoreCase("bolzano")){
					if(DNS_DE==null||DNS_IT==null){	
						return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "com.seda.payer.gateways.webservice- Configurazioni delle url di redirect per il bilingusimo mancanti o non configurate per il codice societa  "+dbSchemaCodSocieta), "","","");
					}
					generaMessageGTWResponse.setApiReturnURL(generaMessageGTWResponse.getApiReturnURL().replace(DNS_DE,DNS_IT));	
					System.out.println("Return url: "+generaMessageGTWResponse.getApiReturnURL());
				}else if(template.equalsIgnoreCase("bolzanoDE")){
					if(DNS_DE!=null && DNS_IT!=null){
						generaMessageGTWResponse.setApiReturnURL(generaMessageGTWResponse.getApiReturnURL().replace(DNS_IT,DNS_DE));
					}
					System.out.println("Return url: "+generaMessageGTWResponse.getApiReturnURL());
					
				}else{
					System.out.println("Url non soggetta a bilinguismo");
					System.out.println("Return url: "+generaMessageGTWResponse.getApiReturnURL());
				}
			}
			
			
			if (GatewaysTypeKeys.parse(generaMessageGTWResponse.getTipoGateway()) == null)
				return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: tipo gateway non riconosciuto"),"","","");
			
			// 2013.10.02 CONTROLLO BLINDATURA
			System.out.println("Controllo Blindatura su Gateway - integraGTWSinc");
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
			
			RecuperaTransazioneResponseType recuperaTransazioneResp =
				binding.recuperaTransazione(new RecuperaTransazioneRequestType(in.getTransazione().getChiaveTransazione()));
			Transazioni transazioni = recuperaTransazioneResp.getBeanTransazioni();
			if (transazioni.getBeanTransazioni().getFlag_esito_transazione().compareTo("1") == 0)
			{
				System.out.println("Errore: tx " + transazioni.getBeanTransazioni().getChiave_transazione() + " è già presente con con esito positivo, pagamento bloccato");
				return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore: tx " + transazioni.getBeanTransazioni().getChiave_transazione() + " è già presente con con esito positivo, pagamento bloccato"),"","","");
			}
			
			
			String apiRedirect = "";
			String params = "";
			
			if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.PAYPAL.format()) == 0) {
				if (generaMessageGTWResponse.getApiReturnURL().trim().length() == 0 ||  
						generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0 ||
						generaMessageGTWResponse.getApiRedirect().trim().length() == 0 )
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				// we prepare redirectToGTW request
				RedirectToGTWRequest redirectToGTWRequest = new RedirectToGTWRequest(
						in.getTransazione(), generaMessageGTWResponse.getApiReturnURL(), 
						generaMessageGTWResponse.getApiCancelURL(), generaMessageGTWResponse.getApiOrderTotal(), 
						generaMessageGTWResponse.getApiUsername(), generaMessageGTWResponse.getApiPassword(),
						generaMessageGTWResponse.getApiSignature(), generaMessageGTWResponse.getApiEndPointUrl(),
						generaMessageGTWResponse.getApiVersion(), in.getTokenCsrf());
				
				// we retry redirectToGTW response
				RedirectToGTWResponse response = this.redirectToGTW(redirectToGTWRequest, in.getIdentificativiDocumenti(),in.getEmailContribuente());
				
				if (!response.getResponse().getRetCode().equals(ResponseTypeRetCode.value1))
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nell'integrazione con il gateway: " + response.getResponse().getRetMessage() ), "","","");
				
				apiRedirect = generaMessageGTWResponse.getApiRedirect() + response.getToken();
				
			} else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.INFOGROUP.format()) == 0) {
				if (generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				RedirectToGTWIGRequest gtwigRequest = new RedirectToGTWIGRequest(
						CanaleAttivazioneTypeKeys.parse(in.getCanalePagamento()), 
						StrumentoPagamentoTypeKeys.CARTA_DI_CREDITO.format(), in.getImpTotTransazione().subtract(in.getImpCostiTransazione()), 
						in.getImpCostiTransazione(), in.getTransazione().getChiaveTransazione(), 
						generaMessageGTWResponse.getApiEndPointUrl(), in.getTokenCsrf(), null, null, null, in.getIdentificativiDocumenti(), in.getEmailContribuente());
				
				RedirectToGTWIGResponse response = this.redirectToGTWIG(gtwigRequest);
				if (response.getCodiceEsito().equalsIgnoreCase("1"))
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, response.getDescrizioneEsito()), "","","");
				
				apiRedirect = response.getUrlStrumPag();
				
			} else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.PAGOINCONTO.format()) == 0) {
				if (generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				RedirectToGTWIGRequest gtwigRequest = new RedirectToGTWIGRequest(
						CanaleAttivazioneTypeKeys.parse(in.getCanalePagamento()), 
						StrumentoPagamentoTypeKeys.PAGA_IN_CONTO.format(), in.getImpTotTransazione().subtract(in.getImpCostiTransazione()), 
						in.getImpCostiTransazione(), in.getTransazione().getChiaveTransazione(), 
						generaMessageGTWResponse.getApiEndPointUrl(), in.getTokenCsrf(), 
						generaMessageGTWResponse.getApiReturnURL(), generaMessageGTWResponse.getApiRedirect(), 
						generaMessageGTWResponse.getApiCancelURL(), null, null);
				
				RedirectToGTWIGResponse response = this.redirectToGTWPagoInConto(gtwigRequest);
				if (response.getCodiceEsito().equalsIgnoreCase("1"))
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, response.getDescrizioneEsito()), "","","");
				
				apiRedirect = response.getUrlStrumPag();
				params = response.getParams();
			}
			else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.RIDONLINE.format()) == 0) 
			{
				if (generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "RIDONLINE Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				// pagamento tramite RID Capitolo 5 PG100053 
				RedirectToGTWIGRequest gtwigRequest = new RedirectToGTWIGRequest(
						CanaleAttivazioneTypeKeys.parse(in.getCanalePagamento()), 
						StrumentoPagamentoTypeKeys.RID.format(), 
						in.getImpTotTransazione().subtract(in.getImpCostiTransazione()), 
						in.getImpCostiTransazione(), 
						in.getTransazione().getChiaveTransazione(), 
						generaMessageGTWResponse.getApiEndPointUrl(), 
						in.getTokenCsrf(), 
						generaMessageGTWResponse.getApiReturnURL(), 
						generaMessageGTWResponse.getApiRedirect(), 
						generaMessageGTWResponse.getApiCancelURL(),
						in.getCodiceDebitore(),
						in.getUserName(),
						in.getScadenzaPagamento(),
						in.getCausalePagamento());
				
				RedirectToGTWIGResponse response = this.redirectToGTWRidOnLine(gtwigRequest);					
				if (response.getCodiceEsito().equalsIgnoreCase("1"))
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, response.getDescrizioneEsito()), "","","");
				
				apiRedirect = response.getUrlStrumPag();
				params = response.getParams();
			}
			else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.MAVONLINE.format()) == 0) 
			{
				if (generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "MAVONLINE Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				// pagamento tramite MAV  
				RedirectToGTWIGRequest gtwigRequest = new RedirectToGTWIGRequest(
						CanaleAttivazioneTypeKeys.parse(in.getCanalePagamento()), 
						StrumentoPagamentoTypeKeys.MAV.format(), 
						in.getImpTotTransazione().subtract(in.getImpCostiTransazione()), 
						in.getImpCostiTransazione(), 
						in.getTransazione().getChiaveTransazione(), 
						generaMessageGTWResponse.getApiEndPointUrl(), 
						in.getTokenCsrf(), 
						generaMessageGTWResponse.getApiReturnURL(), 
						generaMessageGTWResponse.getApiRedirect(), 
						generaMessageGTWResponse.getApiCancelURL(),
						in.getCodiceDebitore(),
						in.getUserName(),
						in.getScadenzaPagamento(),
						in.getCausalePagamento(),
						in.getMavCognomeOrRagioneSocialeDebitore(),
						in.getMavNomeDebitore(),
						in.getMavIndirizzoDebitore(),
						in.getMavCapDebitore(),
						in.getMavLocalitaDebitore(),
						in.getMavProvinciaDebitore());
				
				RedirectToGTWIGResponse response = this.redirectToGTWMAVOnLine(gtwigRequest);
				if (response.getCodiceEsito().equalsIgnoreCase(Generics_RID.S2S_OK))
				{
					// fare il download del file e salvare il file
					if (GatewaysIGHelper.gestisciMAVPostS2S(gtwigRequest, response, propertiesTree(), LOG, dbSchemaCodSocieta))
					{				
						// gestire il download del MAV all'utente con un write nella pagina lato web
						apiRedirect = "";//response.getUrlStrumPag();
						params = response.getParamsXmlString_MAV();
						info("integraGTWSinc - param: " + params);						
					}	
				}
				else
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, response.getDescrizioneEsito()), "","","");
				
			} else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.PAGONLINE.format()) == 0) {
				if (generaMessageGTWResponse.getApiReturnURL().trim().length() == 0 ||  
						generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0 ||
						generaMessageGTWResponse.getApiRedirect().trim().length() == 0 ||
						generaMessageGTWResponse.getApiVersion().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				// we prepare redirectToGTW request
				RedirectToGTWPagOnline redirectToGTWPagOnline = new RedirectToGTWPagOnline(
						generaMessageGTWResponse.getApiVersion(),
						generaMessageGTWResponse.getCodNegozio(), generaMessageGTWResponse.getApiUsername(),  generaMessageGTWResponse.getApiPassword(),
						in.getTransazione().getChiaveTransazione(), in.getImpTotTransazione(), "978",
						"CartCred", "Y", generaMessageGTWResponse.getApiReturnURL() ,
						generaMessageGTWResponse.getApiRedirect(), "wait", "N",
						in.getCausalePagamento(), in.getEmailContribuente(),
						"it", generaMessageGTWResponse.getApiSignature(), generaMessageGTWResponse.getApiEndPointUrl(), in.getTokenCsrf(), generaMessageGTWResponse.getTipoGateway());
				
				apiRedirect = redirectToGTWPagOnline.getUrl();
				info("Url Redirect: " + apiRedirect);
				
			} else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.MYBANK.format()) == 0) {
				if (generaMessageGTWResponse.getApiReturnURL().trim().length() == 0 ||  
						generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0 ||
						generaMessageGTWResponse.getApiRedirect().trim().length() == 0 ||
						generaMessageGTWResponse.getApiVersion().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				String	causalePagamento ="";
				causalePagamento = in.getCausalePagamento();
				if(propertiesTree().getProperty(PropKeys.DESC_CAUSALE_PAGAMENTO.format())!=null)
					causalePagamento = propertiesTree().getProperty(PropKeys.DESC_CAUSALE_PAGAMENTO.format()) + " " + in.getTransazione().getChiaveTransazione();
				
				// we prepare redirectToGTW request
				RedirectToGTWMyBank redirectToGTWMyBank = new RedirectToGTWMyBank(
						generaMessageGTWResponse.getApiVersion(),
						generaMessageGTWResponse.getCodNegozio(), generaMessageGTWResponse.getApiUsername(),  generaMessageGTWResponse.getApiPassword(),
						in.getTransazione().getChiaveTransazione(), in.getImpTotTransazione(), "978",
						"MyBank", "Y", generaMessageGTWResponse.getApiReturnURL() ,
						generaMessageGTWResponse.getApiRedirect(), "wait", "N",
						causalePagamento, in.getEmailContribuente(),
						"it", generaMessageGTWResponse.getApiSignature(), generaMessageGTWResponse.getApiEndPointUrl(), in.getTokenCsrf(), generaMessageGTWResponse.getTipoGateway());
				
				apiRedirect = redirectToGTWMyBank.getUrl();
				info("Url Redirect: " + apiRedirect);
				
			} else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.CARTASI.format()) == 0) {
				if (generaMessageGTWResponse.getApiReturnURL().trim().length() == 0 ||  
						generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0 ||
						generaMessageGTWResponse.getApiRedirect().trim().length() == 0 ||
						generaMessageGTWResponse.getApiVersion().trim().length() == 0)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");
				
				RedirectToGTWCartaSi redirectToGTWCartaSi=new RedirectToGTWCartaSi(
						generaMessageGTWResponse.getApiUsername(),
						generaMessageGTWResponse.getCodNegozio(), 
						generaMessageGTWResponse.getApiUsername(),  
						generaMessageGTWResponse.getApiPassword(),
						in.getTransazione().getChiaveTransazione(), 
						in.getImpTotTransazione(), 
						"EUR",
						"CartaSI", 
						"Y", 
						generaMessageGTWResponse.getApiReturnURL() ,
						generaMessageGTWResponse.getApiRedirect(), 
						"wait", 
						"N",
						in.getCausalePagamento(), 
						in.getEmailContribuente(),
						template.equalsIgnoreCase("bolzanoDE")?"GER":"ITA", 
								generaMessageGTWResponse.getApiSignature(), generaMessageGTWResponse.getApiEndPointUrl(), in.getTokenCsrf(), generaMessageGTWResponse.getTipoGateway());
				
				apiRedirect = redirectToGTWCartaSi.getUrl();
				info("Url Redirect: " + apiRedirect);
				
			}else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.SATISPAY.format()) == 0) {
				if ( generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0 ||					
						generaMessageGTWResponse.getApiSignature().trim().length()==0 ||
						generaMessageGTWResponse.getApiReturnURL().length()==0
				)
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "-Satispay:Errore nella configurazione del gateway di pagamento: parametri di configurazione mancanti"), "","","");	
				info("Satispay: Creato Satispay client");
				SatispayClient satispayClient=new SatispayClient(generaMessageGTWResponse.getApiEndPointUrl().trim(), false,null, 0, false,generaMessageGTWResponse.getApiSignature());
				//Recupera user id da phone in userName
				
				UserResponse userResponse=satispayClient.create_or_GetUser(in.getUserName());
				if(userResponse==null){
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "-Satispay: Errore nel recupero id utente Satispay con numero telefonico "+in.getUserName()), "","","");
				}
				String userId=userResponse.getId();
				info("Satispay: Id utente: " + userId);
				info("Satispay: Richiesta addebito") ;
				ChargeRequest chargeRequest = new ChargeRequest();
				chargeRequest.setAmount(String.valueOf(in.getImpTotTransazione().multiply(new BigDecimal(100)).longValue()));
				chargeRequest.setCurrency("EUR");
				chargeRequest.setDescription(in.getCausalePagamento());
				if(in.getEmailContribuente()!=null&&!in.getEmailContribuente().trim().equals("")) chargeRequest.setRequired_success_email(true);
				chargeRequest.setUser_id(userId);
				chargeRequest.setExpire_in(Integer.valueOf(in.getScadenzaPagamento()));
				ChargeResponse chargeResponse=satispayClient.createACharge(chargeRequest);
				if(chargeResponse!=null&&chargeResponse.getId()!=null){			
					TransazioniBeanTransazioni beanTransazioni=new TransazioniBeanTransazioni();
					beanTransazioni.setChiave_transazione(in.getTransazione().getChiaveTransazione());
					beanTransazioni.setCampo_opzionale_1(chargeResponse.getId());
					AggiornaTransazioneGenericRequest request=new AggiornaTransazioneGenericRequest();
					Transazioni transazione=new Transazioni();					
					transazione.setBeanTransazioni(beanTransazioni);					
					request.setTransazione(transazione);
					AggiornaTransazioneGenericResponse responseAggiornaTransazioneGenericResponse = binding.aggiornaTransazioneGeneric(request);
					if(!responseAggiornaTransazioneGenericResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode.value1.getValue())) {						
						return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "-Satispay: Errore in aggiornameto della chiave della transazione"), "","","");					
					}
				}
				else if(chargeResponse==null){
					return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "-Satispay:Errore durante la richiesta di pagamento"), "","","");
				}	
				info("-Satispay: urlback: "+apiRedirect) ;
				params=chargeResponse.getId();
				apiRedirect = generaMessageGTWResponse.getApiReturnURL().concat("&idtra="+in.getTransazione().getChiaveTransazione()+"&numtel="+in.getUserName()+"&idtragtw="+chargeResponse.getId());// redirect url	
				//Se il numero i telefono non è valido o non è riconosciuto si va in quietanzaerror
				if(chargeResponse!=null&&userResponse.getCode()>0){
					apiRedirect = generaMessageGTWResponse.getApiCancelURL().concat("&idtra="+in.getTransazione().getChiaveTransazione()+"&numtel="+in.getUserName()+"&idtragtw="+chargeResponse.getId());// redirect url		
				}
			}
			//PG150180_001 GG - inizio
			//inizio LP PG180290
			//per adesso stesse operazioni di nodosp per mypay
			//else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.NODOSPC.format()) == 0) {
			else if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.NODOSPC.format()) == 0
					 || generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.MYPAY.format()) == 0 ) {
				
			boolean bIsMyPay = 	(generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.MYPAY.format()) == 0);
			//fine LP PG180290
				
				
				//Chiave transazione
				String chiaveTra = in.getTransazione().getChiaveTransazione();
				String cfPagatoreBollettino = "";
				String denominazionePagatoreBollettino = "";
				String ibanAccreditoBollettino = "";		//PG170070 GG
//				String ibanAppoggioBollettino = "";		//PG170070 GG
				Boolean tipoPagamentoPoste = false;
				String codiceContestoPagamento = "n/a";	//PG160130 GG 01082016
				String chiavePSP = in.getTransazione().getChiavePspNodoSpc();
				//psp.getChiave() + "|" + psc.getIdCanale()
				String idPsp = "";
				String idCanale = "";
				String idIntermediarioPsp = "";
				String tipoVersamento = "";
				String[] values = chiavePSP.split("\\|");
				//inizio LP PG180290
				String tipoDovuto = null;
				String myPayTipoDovutoLst = "";
				//fine LP PG180290
				//inizio LP PG200360
				PropertiesTree configurationNodoWS = null;
				//boolean bStopErroreTassonomia = false;
				//boolean bStopDiversaTassonomia = false;
				boolean bStopAssenzaTassonomia = false;
				String sDefaultTassonomia = "";
				//String tassonomiaConf = null;
				String tassonomia = null;
				//inizio LP EP22405X
				String causalePreavvisiBRAV = "";
				//fine LP EP22405X
				

				try {
					//Leggo informazioni da file di config
//					PropertiesTree configuration;
//					try {
//						String rootPath = System.getenv("NODOSPCWS_CONFIG_ROOT");
//						if (rootPath == null){
//							throw new Exception("Variabile di sistema NODOSPCWS_CONFIG_ROOT non definita");
//						}
//						configuration = new PropertiesTree(rootPath);
//					} catch (Exception e) {
//						throw new Exception("Errore durante il caricamento del file di configurazione NODOSPCWS_CONFIG_ROOT. " + e.getMessage(),e);
//					}
					if (configuration != null){
						configurationNodoWS = configuration;
						/*
						String appo = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".stoperroretassonomia." + dbSchemaCodSocieta);
						bStopErroreTassonomia = (appo != null ? !appo.trim().equalsIgnoreCase("N") : false);
						appo = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".stopdiversatassonomia." + dbSchemaCodSocieta);
						bStopDiversaTassonomia = (appo != null ? !appo.trim().equalsIgnoreCase("N") : false);
						appo = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".stopassenzatassonomia." + dbSchemaCodSocieta);
						*/
						String appo = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stopassenzatassonomia." + dbSchemaCodSocieta);
						bStopAssenzaTassonomia = (appo != null ? !appo.trim().equalsIgnoreCase("N") : false);
						appo = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".defaulttassonomia." + dbSchemaCodSocieta);
						sDefaultTassonomia = (appo != null ? appo.trim() : "0000000DF");
					}
				} catch (Exception e) {
					e.printStackTrace();
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTra, TRA_FAILED_STATE, "", "", Calendar.getInstance());
					info("Aggiornamento esito transazione in stato FALLITA: " + chiaveTra);
					binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);	//TODO da verificare
					throw new Exception(e.getMessage(), e);
					
				}
				//fine LP PG200360
				
				if (values.length > 1){
					idPsp = values[0];
					idCanale = values[1];
				}
				if (values.length > 2){
					idIntermediarioPsp = values[2];
				}
				if (values.length > 3){
					tipoVersamento = values[3];
				}
				if (values.length > 4){
					tipoPagamentoPoste = true;
				}
				info("chiavePSP = "+ chiavePSP);
				info("idPsp = "+ idPsp);
				info("idCanale = "+ idCanale);
				info("idIntermediarioPsp = "+ idIntermediarioPsp);
				info("tipoVersamento = "+ tipoVersamento);
				info("tipoPagamentoPoste = "+ tipoPagamentoPoste);
				
				
				String idBollettiniList = ""; //PG160230_001 GG 23112016 - inserimento lista bollettini in causaleVersamento RPT per Soris
				//Estraggo tutti i bollettini della transazione (TDT-TIC-TFR-ONE): creerò una RPT per ogni bollettino
				BeanIci[] IciList = recuperaTransazioneResp.getListIci();
				BeanIV[] IVList = recuperaTransazioneResp.getListIV();
				BeanFreccia[] FrecciaList = recuperaTransazioneResp.getListFreccia();
				
				HashMap<String, String> entiBollettini = new HashMap<String, String>(); //Contiene le indicazione dell'ente relativo ad ogni bollettino della transazione 
				HashMap<String, String> codiciTipoServizioBollettini = new HashMap<String, String>(); //Contiene le indicazione del tipoServizio relativo ad ogni bollettino della transazione 
				
				List<RptNodoSpc> listRpt = new ArrayList<RptNodoSpc>();
				
				List<RptNodoSpcDatiPagamenti> listRptDatiPagamenti = new ArrayList<RptNodoSpcDatiPagamenti>();
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				
				BigDecimal imptotalebollettino = BigDecimal.ZERO;
				RptNodoSpc itemRpt = null;
				RptNodoSpcDatiPagamenti itemRptDatiPagamenti = null;
				boolean okrpt = false;
				
//PG170300 - 30/1/2018 - INIZIO - MODIFICATO PER GESTIRE IL MULTIBOLLETTINO... PRIMA DELLA MODIFICA VENIVA TUTTO TOTALIZZATO IN UN UNICO 'DATI SINGOLO VERSAMENTO' 
//				if (IciList != null){
//					//BigDecimal imptotalebollettino = BigDecimal.ZERO;	//GG
//					//RptNodoSpc itemRpt = new RptNodoSpc();	//GG
//					itemRpt = new RptNodoSpc();	//GG
//					//boolean okrpt = false;	/GG
//					for (BeanIci beanIci : IciList) {
//						okrpt = true;
//						
//						//Set...
//						itemRpt.setChiaveTra(chiaveTra);
//						itemRpt.setChiaveTabella(beanIci.getChiave_transazione_ici());
//						itemRpt.setCodiceTabella("TIC");
//						itemRpt.setCodSocieta(beanIci.getCodice_societa());
//						itemRpt.setCodUtente(beanIci.getCodice_utente());
//						imptotalebollettino = imptotalebollettino.add(beanIci.getImporto_movimento());
//						itemRpt.setImporto(imptotalebollettino);
//
//						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIci.getChiave_ente());
//						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIci.getCodice_tipologia_servizio());
//						
//						if(cfPagatoreBollettino.trim().length()==0 && beanIci.getCodice_fiscale().trim().length()>0)
//							cfPagatoreBollettino = beanIci.getCodice_fiscale().trim();
//						
//						if(denominazionePagatoreBollettino.trim().length()==0 && beanIci.getDenominazione().trim().length()>0)
//							denominazionePagatoreBollettino = beanIci.getDenominazione().trim();
//
//					}
////					if(okrpt)
////						listRpt.add(itemRpt);
//				}
				if (itemRpt==null) itemRpt = new RptNodoSpc();
				if (itemRptDatiPagamenti == null) itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
				for (BeanIci beanIci : IciList) {
					//inizio LP PG180290
					tipoDovuto = null;
					//fine LP PG180290
					//inizio LP PG200360
					//tassonomiaConf = null;
					tassonomia = null;
					//fine LP PG200360
					//inizio LP EP22405X
					causalePreavvisiBRAV = (beanIci.getCausalePreavvisiBRAV() != null ? beanIci.getCausalePreavvisiBRAV().trim() : "");
					//fine LP EP22405X
					okrpt = true;
					itemRpt.setChiaveTra(chiaveTra);
					itemRpt.setChiaveTabella(beanIci.getChiave_transazione_ici());
					itemRpt.setCodiceTabella("TIC");
					itemRpt.setCodSocieta(beanIci.getCodice_societa());
					itemRpt.setCodUtente(beanIci.getCodice_utente());
					imptotalebollettino = imptotalebollettino.add(beanIci.getImporto_movimento());
					itemRpt.setImporto(imptotalebollettino);
					
					if(cfPagatoreBollettino.trim().length()==0 && beanIci.getCodice_fiscale().trim().length()>0)
						cfPagatoreBollettino = beanIci.getCodice_fiscale().trim();
					
					if(denominazionePagatoreBollettino.trim().length()==0 && beanIci.getDenominazione().trim().length()>0)
						denominazionePagatoreBollettino = beanIci.getDenominazione().trim();
					
//					ConfigUtenteTipoServizioEnteDetailResponse  configUtenteEnte =null;
//					configUtenteEnte = GatewaysIGHelper.getConfigUtenteTipoServizioEnteDetailResponse(propertiesTree(), dbSchemaCodSocieta, beanIci.getCodice_societa(), beanIci.getCodice_utente(), beanIci.getChiave_ente(), beanIci.getCodice_tipologia_servizio());
					ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIci.getCodice_societa(),
							beanIci.getCodice_utente(),
							beanIci.getChiave_ente(),
							beanIci.getCodice_tipologia_servizio(),
							"WEB"); 
					ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
					
//					ibanAccreditoBollettino = configUtenteEnte.getConfigutentetiposervizioente().getCodiceIban();
					ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN();
//					ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
					//inizio LP PG180290
					if(bIsMyPay) {
						tipoDovuto = configPagamentoSingleResponse.getConfigPagamento().getTipoDovuto();
						//inizio LP PG180290 2020-05-21
						if(tipoDovuto.trim().length() == 0) {
							info("Errore per Tipologia Servizio: '" + beanIci.getCodice_tipologia_servizio() + "' tipo dovuto non valorizzato.");
							tipoDovuto = "TIPO_DOVUTO_NON_VALORIZZATO";
						}
						//fine LP PG180290 2020-05-21
					}
					//fine LP PG180290
					//inizio LP PG200360
					/*
					tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
					if(bStopErroreTassonomia && (tassonomiaConf == null || tassonomiaConf.trim().length() == 0)) {
						String err = "Errore per tipologia servizio: '" + beanIci.getCodice_tipologia_servizio() + "' tassonomia non valorizzata.";
						info(err);
						throw new Exception(err);
					}
					if(tassonomiaConf != null) {
						tassonomiaConf = tassonomiaConf.trim();
					}
					*/
					//fine LP PG200360
					if(tipoPagamentoPoste){
						info("tipoPagamentoPoste POSTE ");
//						if(configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban()!=null && !configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban().equals(""))
//							ibanAccreditoBollettino = configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban();
						if(configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2()!=null && 
						  !configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2().equals(""))
							ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
					}
					
//                  CARICA L'ARRAY PER TOTALIZZARE A LIVELLO DI IBAN 
//                  PER GLI ICI NON C'E' LA GESTIONE DEL BOLLO DIGITALE... 
//					la totalizzazione avviene sull'indice dell'array stabilito dal campo TIC_PTICPIUR DETERMINATO IN FASE DI CARICAMENTO A PARITà DI IBAN
//					QUESTO FARà IN MODO CHE IL PROGRESSIVO DI INVIO CORRISPONDA AL PROGRESSIVO BOLLETTINO DELLA RPT
// da togliere ...					
//					boolean trovato=false;
//					for (int i=0;i<listRptDatiPagamenti.size();i++) {
//						if (listRptDatiPagamenti.get(i).getIbanAccredito().trim().equals(ibanAccreditoBollettino.trim())) {
//							listRptDatiPagamenti.get(i).setImportoSingoloPagamento(listRptDatiPagamenti.get(i).getImportoSingoloPagamento().add(beanIci.getImporto_movimento()));
//							trovato=true;
//						}
//					}
//					if (!trovato){
//						itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
//						itemRptDatiPagamenti.setIbanAccredito(ibanAccreditoBollettino);
//						itemRptDatiPagamenti.setImportoSingoloPagamento(beanIci.getImporto_movimento());
//						listRptDatiPagamenti.add(itemRptDatiPagamenti);	
//					}
					int progBollettino = beanIci.getProgBollettino();
					//inizio LP PG200360
					tassonomia = (beanIci.getTassonomia() != null ? beanIci.getTassonomia().trim() : null);
					/*
					if(bStopDiversaTassonomia && (tassonomia != null && !tassonomiaConf.equalsIgnoreCase(tassonomia))) {
						String err = "Errore tassonomia pagamento diversa da tassonomia tipologia servizio: '" + beanIci.getCodice_tipologia_servizio() + "'."; 
						info(err);
						throw new Exception(err);
					}
					if(tassonomia == null) {
						tassonomia = tassonomiaConf;	
					}
					*/
					if(tassonomia == null || tassonomia.length() == 0) {
						if(bStopAssenzaTassonomia) {
							String err = "Errore tassonomia pagamento non valorizzata."; 
							info(err);
							throw new Exception(err);
						}
						info("Possibile errore tassonomia non valorizzata si usa tassonomia default.");
						tassonomia = sDefaultTassonomia;
						//beanIci.setTassonomia(tassonomia);
					}
					//fine LP PG200360
					
					if (listRptDatiPagamenti.get(progBollettino).getIbanAccredito()==null){
						listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
//						listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
						listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(beanIci.getImporto_movimento());
						listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						//inizio LP PG200360
						listRptDatiPagamenti.get(progBollettino).setTassonomia(tassonomia);
						//fine LP PG200360
						//inizio LP EP22405X
						listRptDatiPagamenti.get(progBollettino).setCausalePreavvisiBRAV(causalePreavvisiBRAV);
						//fine LP EP22405X
						//inizio LP PG180290
						if(bIsMyPay) {
							listRptDatiPagamenti.get(progBollettino).setTipoDovuto(tipoDovuto);
						}
						//fine LP PG180290
					} else {
						listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
//						listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
						listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(listRptDatiPagamenti.get(progBollettino).getImportoSingoloPagamento().add(beanIci.getImporto_movimento()));
						listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
					}
				}
				
				//PG170300 - 30/1/2018 - FINE 
				if (IVList != null){
//PG170300 - 30/1/2018 - INIZIO - MODIFICATO PER GESTIRE IL MULTIBOLLETTINO... PRIMA DELLA MODIFICA VENIVA TUTTO TOTALIZZATO IN UN UNICO 'DATI SINGOLO VERSAMENTO' 
//				                  LA MODIFICA HA LO SCOPO DI POTER CREARE TANTI 'DATI SINGOLO VERSAMENTO' QUANTI SONO GLI IBAN (AL MASSIMO 5
//				                  I BOLLETTINI ANDRANNO TOTALIZZATI A PARITA' DI iban
//					//BigDecimal imptotalebollettino = BigDecimal.ZERO;
//					//RptNodoSpc itemRpt = new RptNodoSpc();
//					if (itemRpt == null) itemRpt = new RptNodoSpc();
//					//boolean okrpt = false;
//					for (BeanIV beanIV : IVList) {
//						okrpt = true;
//						//Set...
//						itemRpt.setChiaveTra(chiaveTra);
//						itemRpt.setChiaveTabella(beanIV.getChiave_transazione_dettaglio());
//						itemRpt.setCodiceTabella("TDT");
//						itemRpt.setCodSocieta(beanIV.getCodice_societa());
//						itemRpt.setCodUtente(beanIV.getCodice_utente());
//						imptotalebollettino = imptotalebollettino.add(beanIV.getImporto_totale_bollettino());
//						itemRpt.setImporto(imptotalebollettino);
//						
//						if(cfPagatoreBollettino.trim().length()==0 && beanIV.getCodice_fiscale().trim().length()>0)
//							cfPagatoreBollettino = beanIV.getCodice_fiscale().trim();
//						
//						if(denominazionePagatoreBollettino.trim().length()==0 && beanIV.getDenominazione().trim().length()>0)
//							denominazionePagatoreBollettino = beanIV.getDenominazione().trim();
//						
//						//PG170070 GG - inizio
//						if(ibanAccreditoBollettino.trim().length()==0 && beanIV.getCodiceIban().trim().length()>0)
//							ibanAccreditoBollettino = beanIV.getCodiceIban().trim();
//						//PG170070 GG - fine
//						
//						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIV.getChiave_ente_ent());
//						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIV.getCodice_tipologia_servizio());
//						
////						listRpt.add(itemRpt);
//						idBollettiniList = idBollettiniList.trim().length()>0?(idBollettiniList.concat(";").concat(beanIV.getCodice_bollettino_premarcato_mav())):beanIV.getCodice_bollettino_premarcato_mav();	//PG160230_001 GG 23112016
//					}
////					if(okrpt)
////						listRpt.add(itemRpt);
					
					if (itemRpt == null) itemRpt = new RptNodoSpc();
					if (itemRptDatiPagamenti == null) itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
					
					for (BeanIV beanIV : IVList) {
						//inizio LP PG180290
						tipoDovuto = null;
						//fine LP PG180290
						//inizio LP PG200360
						//tassonomiaConf = null;
						tassonomia = null;
						//fine LP PG200360
						//inizio LP EP22405X
						causalePreavvisiBRAV = (beanIV.getCausalePreavvisiBRAV() != null ? beanIV.getCausalePreavvisiBRAV().trim() : "");
						//fine LP EP22405X
						okrpt = true;
						itemRpt.setChiaveTra(chiaveTra);
						itemRpt.setChiaveTabella(beanIV.getChiave_transazione_dettaglio());
						itemRpt.setCodiceTabella("TDT");
						itemRpt.setCodSocieta(beanIV.getCodice_societa());
						itemRpt.setCodUtente(beanIV.getCodice_utente());
						imptotalebollettino = imptotalebollettino.add(beanIV.getImporto_totale_bollettino());
						itemRpt.setImporto(imptotalebollettino);
						
						if(cfPagatoreBollettino.trim().length()==0 && beanIV.getCodice_fiscale().trim().length()>0)
							cfPagatoreBollettino = beanIV.getCodice_fiscale().trim();
						
						if(denominazionePagatoreBollettino.trim().length()==0 && beanIV.getDenominazione().trim().length()>0)
							denominazionePagatoreBollettino = beanIV.getDenominazione().trim();
						
						if(ibanAccreditoBollettino.trim().length()==0 && beanIV.getCodiceIban().trim().length()>0)
							ibanAccreditoBollettino = beanIV.getCodiceIban().trim();
						else{
//							ConfigUtenteTipoServizioEnteDetailResponse  configUtenteEnte =null;
//							configUtenteEnte = GatewaysIGHelper.getConfigUtenteTipoServizioEnteDetailResponse(propertiesTree(), dbSchemaCodSocieta, beanIV.getCodice_societa(), beanIV.getCodice_utente(), beanIV.getChiave_ente_ent(), beanIV.getCodice_tipologia_servizio());
							ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIV.getCodice_societa(),
									beanIV.getCodice_utente(),
									beanIV.getChiave_ente_ent(),
									beanIV.getCodice_tipologia_servizio(),
									"WEB"); 
							ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
//							ibanAccreditoBollettino = configUtenteEnte.getConfigutentetiposervizioente().getCodiceIban();
							ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN();
//							ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
							//inizio LP PG180290
							if(bIsMyPay) {
								tipoDovuto = configPagamentoSingleResponse.getConfigPagamento().getTipoDovuto();
								//inizio LP PG180290 2020-05-21
								if(tipoDovuto.trim().length() == 0) {
									info("Errore per Tipologia Servizio: '" + beanIV.getCodice_tipologia_servizio() + "' tipo dovuto non valorizzato.");
									tipoDovuto = "TIPO_DOVUTO_NON_VALORIZZATO";
								}
								//fine LP PG180290 2020-05-21
							}
							//fine LP PG180290
							//inizio LP PG200360
							//tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
							//fine LP PG200360
							if(tipoPagamentoPoste){
								info("tipoPagamentoPoste POSTE ");
//								if(configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban()!=null && !configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban().equals(""))
//									ibanAccreditoBollettino = configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban();
								if(configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2()!=null && !configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2().equals(""))
									ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
							}
							
						}
						info("iban "+ibanAccreditoBollettino);
						//inizio LP PG180290
						//inizio LP PG200360
						if(bIsMyPay && tipoDovuto == null) {
						//if((bIsMyPay && tipoDovuto == null) || tassonomia == null) {
						//fine LP PG200360
							ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIV.getCodice_societa(),
									beanIV.getCodice_utente(),
									beanIV.getChiave_ente_ent(),
									beanIV.getCodice_tipologia_servizio(),
									"WEB"); 
							ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
						//inizio LP PG200360
						//	if(bIsMyPay) {
						//fine LP PG200360
							tipoDovuto = configPagamentoSingleResponse.getConfigPagamento().getTipoDovuto();
								if(tipoDovuto.trim().length() == 0) {
									info("Errore per Tipologia Servizio: '" + beanIV.getCodice_tipologia_servizio() + "' tipo dovuto non valorizzato.");
									tipoDovuto = "TIPO_DOVUTO_NON_VALORIZZATO";
								}
						//inizio LP PG200360
							//}
							//tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
						//fine LP PG200360
						}
						//inizio LP PG200360
						/*
						if(bStopErroreTassonomia && (tassonomiaConf == null || tassonomiaConf.trim().length() == 0)) {
							String err = "Errore per tipologia servizio: '" + beanIV.getCodice_tipologia_servizio() + "' tassonomia non valorizzata.";
							info(err);
							throw new Exception(err);
						}
						if(tassonomiaConf != null) {
							tassonomiaConf = tassonomiaConf.trim();
						}
						*/
						//fine LP PG200360
						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIV.getChiave_ente_ent());
						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIV.getCodice_tipologia_servizio());
						
						idBollettiniList = idBollettiniList.trim().length()>0?(idBollettiniList.concat(";").concat(beanIV.getCodice_bollettino_premarcato_mav())):beanIV.getCodice_bollettino_premarcato_mav();	//PG160230_001 GG 23112016
						
						BigDecimal importoMarcaDaBolloDigitale = beanIV.getImportoMarcaDaBolloDigitale();
						String segnaturaMarcaDaBolloDigitale = beanIV.getSegnaturaMarcaDaBolloDigitale().trim();
						String tipoBolloDaErogare = beanIV.getTipoBolloDaErogare().trim();
						String provinciaResidenza = beanIV.getProvinciaResidenza().trim();
						

						int progBollettino = beanIV.getProgBollettino();
						//inizio LP PG200360
						tassonomia = (beanIV.getTassonomia() != null ? beanIV.getTassonomia().trim() : null);
						/*
						if(bStopDiversaTassonomia && (tassonomia != null && !tassonomiaConf.equalsIgnoreCase(tassonomia))) {
							String err = "Errore tassonomia pagamento diversa da tassonomia tipologia servizio: '" + beanIV.getCodice_tipologia_servizio() + "'."; 
							info(err);
							throw new Exception(err);
						}
						if(tassonomia == null) {
							tassonomia = tassonomiaConf;	
						}
						*/
						if(tassonomia == null || tassonomia.length() == 0) {
							if(bStopAssenzaTassonomia) {
								String err = "Errore tassonomia pagamento non valorizzata."; 
								info(err);
								throw new Exception(err);
							}
							info("Possibile errore tassonomia non valorizzata si usa tassonomia default.");
							tassonomia = sDefaultTassonomia;
							//beanIV.setTassonomia(tassonomia);
						}
						//fine LP PG200360
						if (listRptDatiPagamenti.get(progBollettino).getIbanAccredito()==null){
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
//							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(beanIV.getImporto_totale_bollettino().subtract(importoMarcaDaBolloDigitale));
							listRptDatiPagamenti.get(progBollettino).setImportoMarcaDaBolloDigitale(new BigDecimal("0.0"));
							listRptDatiPagamenti.get(progBollettino).setSegnaturaMarcaDaBolloDigitale("");
							listRptDatiPagamenti.get(progBollettino).setTipoBolloDaErogare("");
							//inizio LP PG200360
							listRptDatiPagamenti.get(progBollettino).setTassonomia(tassonomia);
							//fine LP PG200360
							//inizio LP EP22405X
							listRptDatiPagamenti.get(progBollettino).setCausalePreavvisiBRAV(causalePreavvisiBRAV);
							//fine LP EP22405X
							itemRptDatiPagamenti.setProvinciaResidenza("");
							if (tipoBolloDaErogare.length()>0) { // ok marca da bollo digitale
								itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
								itemRptDatiPagamenti.setImportoSingoloPagamento(new BigDecimal("0.0"));
								itemRptDatiPagamenti.setIbanAccredito("");
//								itemRptDatiPagamenti.setIbanAppoggio("");
								itemRptDatiPagamenti.setImportoMarcaDaBolloDigitale(importoMarcaDaBolloDigitale);
								itemRptDatiPagamenti.setSegnaturaMarcaDaBolloDigitale(segnaturaMarcaDaBolloDigitale);
								itemRptDatiPagamenti.setTipoBolloDaErogare(tipoBolloDaErogare);
								itemRptDatiPagamenti.setProvinciaResidenza(provinciaResidenza);
								//inizio LP PG200360
								//TODO: Non so se qui bisogna mettere una tassonomia ad hoc per il bollo
								itemRptDatiPagamenti.setTassonomia(tassonomia);
								//fine LP PG200360
								//inizio LP PG180290
								itemRptDatiPagamenti.setItemValorizzato("S"); //mancava
								if(bIsMyPay) {
									itemRptDatiPagamenti.setTipoDovuto(tipoDovuto);
								}
								//fine LP PG180290
								listRptDatiPagamenti.add(itemRptDatiPagamenti); // il bollo si troverà sempre in sesta posizione	
							}
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
							//inizio LP PG180290
							if(bIsMyPay) {
								listRptDatiPagamenti.get(progBollettino).setTipoDovuto(tipoDovuto);
							}
							//fine LP PG180290
						} else {
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
//							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(listRptDatiPagamenti.get(progBollettino).getImportoSingoloPagamento().add(beanIV.getImporto_totale_bollettino()));
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						}
					}
					// listRpt.add(itemRpt); MA PERCHE' STA QUI???
				}
				
//PG170300 - 30/1/2018 - FINE 
				
				
				if (FrecciaList != null){
//PG170300 - 30/1/2018 - INIZIO - MODIFICATO PER GESTIRE IL MULTIBOLLETTINO... PRIMA DELLA MODIFICA VENIVA TUTTO TOTALIZZATO IN UN UNICO 'DATI SINGOLO VERSAMENTO' 
//                LA MODIFICA HA LO SCOPO DI POTER CREARE TANTI 'DATI SINGOLO VERSAMENTO' QUANTI SONO GLI IBAN (AL MASSIMO 5
//                I BOLLETTINI ANDRANNO TOTALIZZATI A PARITA' DI iban
//					//BigDecimal imptotalebollettino = BigDecimal.ZERO;
//					//RptNodoSpc itemRpt = new RptNodoSpc();
//					if (itemRpt==null) itemRpt = new RptNodoSpc();
//					//boolean okrpt = false;
//					for (BeanFreccia beanFreccia : FrecciaList) {
//						okrpt = true;
//						
//						//Set...
//						itemRpt.setChiaveTra(chiaveTra);
//						itemRpt.setChiaveTabella(beanFreccia.getChiave_transazione_dettaglio());
//						itemRpt.setCodiceTabella("TFR");
//						itemRpt.setCodSocieta(beanFreccia.getCodice_societa());
//						itemRpt.setCodUtente(beanFreccia.getCodice_utente());
//						imptotalebollettino = imptotalebollettino.add(beanFreccia.getImporto_totale_bollettino());
//						itemRpt.setImporto(imptotalebollettino);
//						
//						if(cfPagatoreBollettino.trim().length()==0 && beanFreccia.getCodice_fiscale().trim().length()>0)
//							cfPagatoreBollettino = beanFreccia.getCodice_fiscale().trim();
//						
//						if(denominazionePagatoreBollettino.trim().length()==0 && beanFreccia.getDenominazione_contribuente().trim().length()>0)
//							denominazionePagatoreBollettino = beanFreccia.getDenominazione_contribuente().trim();
//						
////						//PG170070 GG - inizio	//TODO da verificare
////						if(ibanAccreditoBollettino.trim().length()==0 && beanFreccia.getCodice_iban().trim().length()>0)
////							ibanAccreditoBollettino = beanFreccia.getCodice_iban().trim();
////						//PG170070 GG - fine
//						
//						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanFreccia.getChiave_ente_ent());
//						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanFreccia.getCodice_tipologia_servizio());
//						
//						idBollettiniList = idBollettiniList.trim().length()>0?(idBollettiniList.concat(";").concat(beanFreccia.getCodice_identificativo_pagamento())):beanFreccia.getCodice_identificativo_pagamento();	//PG160230_001 GG 23112016
					if (itemRpt==null) itemRpt = new RptNodoSpc();
					if (itemRptDatiPagamenti == null) itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
					for (BeanFreccia beanFreccia : FrecciaList) {
						//inizio LP PG180290
						tipoDovuto = null;
						//fine LP PG180290
						//inizio LP PG200360
						//tassonomiaConf = null;
						tassonomia = null;
						//fine LP PG200360
						//inizio LP EP22405X
						causalePreavvisiBRAV = (beanFreccia.getCausalePreavvisiBRAV() != null ? beanFreccia.getCausalePreavvisiBRAV().trim() : "");
						//fine LP EP22405X
						okrpt = true;
						itemRpt.setChiaveTra(chiaveTra);
						itemRpt.setChiaveTabella(beanFreccia.getChiave_transazione_dettaglio());
						itemRpt.setCodiceTabella("TFR");
						itemRpt.setCodSocieta(beanFreccia.getCodice_societa());
						itemRpt.setCodUtente(beanFreccia.getCodice_utente());
						imptotalebollettino = imptotalebollettino.add(beanFreccia.getImporto_totale_bollettino());
						itemRpt.setImporto(imptotalebollettino);
						
						if(cfPagatoreBollettino.trim().length()==0 && beanFreccia.getCodice_fiscale().trim().length()>0)
							cfPagatoreBollettino = beanFreccia.getCodice_fiscale().trim();
						
						if(denominazionePagatoreBollettino.trim().length()==0 && beanFreccia.getDenominazione_contribuente().trim().length()>0)
							denominazionePagatoreBollettino = beanFreccia.getDenominazione_contribuente().trim();
						
						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanFreccia.getChiave_ente_ent());
						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanFreccia.getCodice_tipologia_servizio());

						idBollettiniList = idBollettiniList.trim().length()>0?(idBollettiniList.concat(";").concat(beanFreccia.getCodice_identificativo_pagamento())):beanFreccia.getCodice_identificativo_pagamento();	//PG160230_001 GG 23112016
//						ConfigUtenteTipoServizioEnteDetailResponse  configUtenteEnte =null;
//						configUtenteEnte = GatewaysIGHelper.getConfigUtenteTipoServizioEnteDetailResponse(propertiesTree(), dbSchemaCodSocieta, beanIci.getCodice_societa(), beanIci.getCodice_utente(), beanIci.getChiave_ente(), beanIci.getCodice_tipologia_servizio());
						ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanFreccia.getCodice_societa(),
								beanFreccia.getCodice_utente(),
								beanFreccia.getChiave_ente_ent(),
								beanFreccia.getCodice_tipologia_servizio(),
								"WEB"); 
						ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest); 
						
//						ibanAccreditoBollettino = configUtenteEnte.getConfigutentetiposervizioente().getCodiceIban();
						ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN();
//						ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						//inizio LP PG180290
						if(bIsMyPay) {
							tipoDovuto = configPagamentoSingleResponse.getConfigPagamento().getTipoDovuto();
							//inizio LP PG180290 2020-05-21
							if(tipoDovuto.trim().length() == 0) {
								info("Errore per Tipologia Servizio: '" + beanFreccia.getCodice_tipologia_servizio() + "' tipo dovuto non valorizzato.");
								tipoDovuto = "TIPO_DOVUTO_NON_VALORIZZATO";
							}
							//fine LP PG180290 2020-05-21
						}
						//fine LP PG180290
						//inizio LP PG200360
						/*
						tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
						if(bStopErroreTassonomia && (tassonomiaConf == null || tassonomiaConf.trim().length() == 0)) {
							String err = "Errore per tipologia servizio: '" + beanFreccia.getCodice_tipologia_servizio() + "' tassonomia non valorizzata.";
							info(err);
							throw new Exception(err);
						}
						if(tassonomiaConf != null) {
							tassonomiaConf = tassonomiaConf.trim();
						}
						*/
						//fine LP PG200360
						if(tipoPagamentoPoste){
							info("tipoPagamentoPoste POSTE ");
//							if(configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban()!=null && !configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban().equals(""))
//								ibanAccreditoBollettino = configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban();
							if(configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2()!=null && !configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2().equals(""))
								ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						}
						
						
//	                    CARICA L'ARRAY PER TOTALIZZARE A LIVELLO DI IBAN 
//	                    PER GLI ICI NON C'E' LA GESTIONE DEL BOLLO DIGITALE... 
//						la totalizzazione avviene sull'indice dell'array stabilito dal campo TFT_PTFTPIUR DETERMINATO IN FASE DI CARICAMENTO A PARITà DI IBAN
//						QUESTO FARà IN MODO CHE IL PROGRESSIVO DI INVIO CORRISPONDA AL PROGRESSIVO BOLLETTINO DELLA RPT
// togliere
//						boolean trovato = false;
//						for (int i=0;i<listRptDatiPagamenti.size();i++) {
//							if (listRptDatiPagamenti.get(i).getIbanAccredito().trim().equals(ibanAccreditoBollettino.trim())) {
//								listRptDatiPagamenti.get(i).setImportoSingoloPagamento(listRptDatiPagamenti.get(i).getImportoSingoloPagamento().add(beanFreccia.getImporto_totale_bollettino()));
//								trovato=true;
//							}
//						}
//						if (!trovato){
//							itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
//							itemRptDatiPagamenti.setIbanAccredito(ibanAccreditoBollettino);
//							itemRptDatiPagamenti.setImportoSingoloPagamento(beanFreccia.getImporto_totale_bollettino());
//							listRptDatiPagamenti.add(itemRptDatiPagamenti);	
//						}
						int progBollettino = beanFreccia.getProgBollettino();
						//inizio LP PG200360
						tassonomia = (beanFreccia.getTassonomia() != null ? beanFreccia.getTassonomia().trim() : null);
						/*
						if(bStopDiversaTassonomia && (tassonomia != null && !tassonomiaConf.equalsIgnoreCase(tassonomia))) {
							String err = "Errore tassonomia pagamento diversa da tassonomia tipologia servizio: '" + beanFreccia.getCodice_tipologia_servizio() + "'."; 
							info(err);
							throw new Exception(err);
						}
						if(tassonomia == null) {
							tassonomia = tassonomiaConf;	
						}
						*/
						if(tassonomia == null || tassonomia.length() == 0) {
							if(bStopAssenzaTassonomia) {
								String err = "Errore tassonomia pagamento non valorizzata."; 
								info(err);
								throw new Exception(err);
							}
							info("Possibile errore tassonomia non valorizzata si usa tassonomia default.");
							tassonomia = sDefaultTassonomia;
							//beanFreccia.setTassonomia(tassonomia);
						}
						//fine LP PG200360
						if (listRptDatiPagamenti.get(progBollettino).getIbanAccredito()==null){
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
//							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(beanFreccia.getImporto_totale_bollettino());
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
							//inizio LP PG200360
							listRptDatiPagamenti.get(progBollettino).setTassonomia(tassonomia);
							//fine LP PG200360
							//inizio LP EP22405X
							listRptDatiPagamenti.get(progBollettino).setCausalePreavvisiBRAV(causalePreavvisiBRAV);
							//fine LP EP22405X
							//inizio LP PG180290
							if(bIsMyPay) {
								listRptDatiPagamenti.get(progBollettino).setTipoDovuto(tipoDovuto);
							}
							//fine LP PG180290
						} else {
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
//							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(listRptDatiPagamenti.get(progBollettino).getImportoSingoloPagamento().add(beanFreccia.getImporto_totale_bollettino()));
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						}
					}
//PG170300 - 30/1/2018 - FINE 
//					if(okrpt)
//						listRpt.add(itemRpt);
				}
				
				if(okrpt)
					listRpt.add(itemRpt);
				
				//inizio LP PG180290
				if(bIsMyPay && okrpt) {
					for (int i=0; i < listRptDatiPagamenti.size(); i++){
						if (listRptDatiPagamenti.get(i).getItemValorizzato()!= null
							&& listRptDatiPagamenti.get(i).getItemValorizzato().equals("S")
							&& listRptDatiPagamenti.get(i).getImportoSingoloPagamento().compareTo(BigDecimal.ZERO) != 0
							) {
							tipoDovuto = listRptDatiPagamenti.get(i).getTipoDovuto();
							if(tipoDovuto != null && tipoDovuto.length() > 0) {
								if(myPayTipoDovutoLst.length() > 0) {
									myPayTipoDovutoLst += ";" + tipoDovuto;
								} else {
									myPayTipoDovutoLst += tipoDovuto;
								}
							}
						}
					}
				}
				//fine LP PG180290
				
				String identificativoIntermediarioPA = "", identificativoStazioneIntermediarioPA="", 
				identificativoDominio = "", passwordPsp = "";
				boolean proxyEnabled = false;
				String tipoFirma = "", password = "", username = "", nodoSpcUrl = "", proxyHost = "", 
				proxyPort = "", proxyUser = "", proxyPassword = "";
				String cfPagatore = "", nomePagatore = "", datiSpecificiRiscossione = "";
				//inizio LP PG1900XX_001
				String nodospcGestore = "E";
				String nodospcTipologia = "N";
				//fine LP PG1900XX_001
				//inizio LP PG1900XX_001 uso MyBridge
				String mybridgeUrl = "";
				//fine LP PG1900XX_001 uso MyBridge
				//inizio LP PG1900XX_001 uso EasyBridge
				String easybridgeUrl = ""; 
				//fine LP PG1900XX_001 uso EasyBridge
				//inizio LP PG180290
				String myPayPagonetUrl = "";
				String myPayCodiceIpaEnte = "";
				String myPayPasswordEnte = "";
				String myPayPasswordEnteCrypt = "";
				//fine LP PG180290
				//28092017 GG - inserito try-catch in modo da evitare transazioni sospese in caso di errore in inserimento RPT: per le transazioni per cui si verifica un errore prima della chiamata al nodo, si aggiorna lo stato a 2
				try {
					//Recupero dati dell'ente relativo al pagamento corrente
					//inizio LP PG200360
					////Leggo informazioni da file di config
					//PropertiesTree configuration;
//					PropertiesTree configuration = configurationNodoWS;
//					if (configuration == null) {
//					//fine LP PG200360
//					try {
//						String rootPath = System.getenv("NODOSPCWS_CONFIG_ROOT");
//						if (rootPath == null){
//							throw new Exception("Variabile di sistema NODOSPCWS_CONFIG_ROOT non definita");
//						}
//						configuration = new PropertiesTree(rootPath);
//					} catch (Exception e) {
//						throw new Exception("Errore durante il caricamento del file di configurazione NODOSPCWS_CONFIG_ROOT. " + e.getMessage(),e);
//					}
//					//inizio LP PG200360
//					}
//					//fine LP PG200360
					
					if (configuration != null){
						identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa");
						identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa");
						//identificativoDominio = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospciddominio");
						passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp");
						password = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword");
						username = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername");
						nodoSpcUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc");
						
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta)!=null)
							identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta);
						
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta)!=null)
							identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta);
						
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta)!=null)
							passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta);
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta)!=null)
							password = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta);
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta)!=null)
							username = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta);
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta)!=null)
							nodoSpcUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta);
						
						//inizio LP PG1900XX_001
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore") != null) {
							nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore");
						}
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta) != null) {
							nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta);
						}
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia") != null) {
							nodospcTipologia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia");
						}
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia." + dbSchemaCodSocieta) != null) {
							nodospcTipologia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia." + dbSchemaCodSocieta);
						}
						//fine LP PG1900XX_001
						//inizio LP PG1900XX_001 uso MyBridge
						mybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge");
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge." + dbSchemaCodSocieta) != null) {
							mybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge." + dbSchemaCodSocieta);
						}
						//fine LP PG1900XX_001 uso MyBridge
						
						//inizio LP PG1900XX_001 uso EasyBridge
						easybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge");
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge." + dbSchemaCodSocieta)!=null)
							easybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge." + dbSchemaCodSocieta);
						//fine LP PG1900XX_001 uso EasyBridge
						
						//inizio LP PG180290
						if(bIsMyPay) {
							myPayPagonetUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mypaypagoneturl");
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mypaypagoneturl." + dbSchemaCodSocieta)!=null) {
								myPayPagonetUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mypaypagoneturl." + dbSchemaCodSocieta);
							}
						}
						//fine LP PG180290

						info("nodoSpcUrl = "+ nodoSpcUrl);
						info("username = "+ username);
						info("password = "+ password);
//						info("passwordPsp = "+ passwordPsp);
						//inizio LP PG1900XX_001
						info("nodospcGestore = "+ nodospcGestore);
						info("nodospcTipologia = "+ nodospcTipologia);
						//fine LP PG1900XX_001
//						info("identificativoStazioneIntermediarioPA = "+ identificativoStazioneIntermediarioPA);
						info("identificativoIntermediarioPA = "+ identificativoIntermediarioPA);
						//inizio LP PG1900XX_001 uso MyBridge
						info("mybridgeUrl = "+ mybridgeUrl);
						//fine LP PG1900XX_001 uso MyBridge
						//inizio LP PG1900XX_001 uso EasyBridge
						info("easybridgeUrl = "+ easybridgeUrl);
						//fine LP PG1900XX_001 uso EasyBridge
						//inizio LP PG180290
						if(bIsMyPay) {
							info("myPayPagonetUrl = "+ myPayPagonetUrl);
						}
						//fine LP PG180290

						proxyEnabled = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyenabled").equals("1");
						proxyHost = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyhost");
						proxyPort = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyport");
						proxyUser = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyuser");
						proxyPassword = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxypassword");
						
						cfPagatore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".pagatoreanonimo.cf");
						nomePagatore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".pagatoreanonimo.nome");
						datiSpecificiRiscossione = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".datispecificiriscossione");
						

						System.out.println("Gateways - [riga 1518] - datiSpecificiRiscossione: "+datiSpecificiRiscossione);
						
						
						/* i seguenti campi sono da prendere dai dati di configurazione dell'ente */
						//cfEnteCreditore = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".cfentecreditore");
						//nomeEnteCreditore = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nomeentecreditore");
						//ibanAccredito = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".ibanaccredito");
						
					}
					info("Lettura da File di configurazione");
					info("cfPagatore = " + cfPagatore);
					info("nomePagatore = " + nomePagatore);
					
					if(cfPagatoreBollettino.trim().length()>0) //Se da uno dei bollettini posso recuperare il codice fiscale, lo utilizzo come pagatore per il nodo
						cfPagatore = cfPagatoreBollettino.trim();
					
					if(denominazionePagatoreBollettino.trim().length()>0) //Se da uno dei bollettini posso recuperare la denominazione, lo utilizzo come denominazione del pagatore per il nodo
						nomePagatore = denominazionePagatoreBollettino.trim();
					else{
						if(dbSchemaCodSocieta.equals("000P6"))
							nomePagatore = cfPagatore;
					}
						
					
					info("Lettura post bollettino");
					info("cfPagatore = " + cfPagatore);
					info("nomePagatore = " + nomePagatore);
					
					// controllo della lunghezza sul pagatore
					if(nomePagatore.length()>70)
						nomePagatore = nomePagatore.substring(0,70);
					
					//Ciclo su listIuv, inserisco nel db, creo RPT
					for (RptNodoSpc rptNodoSpc : listRpt) {
						//Creo IUV
						rptNodoSpc.setRpt("");//l'xml rpt verrà creata subito dopo aver creato lo iuv perchè nell'xml deve essere scritto anche lo iuv
						rptNodoSpc.setRptEsito("");
						rptNodoSpc.setRptFirma("");
						rptNodoSpc.setRt("");
						rptNodoSpc.setRtEsito("");
						rptNodoSpc.setRtFirma("");
						rptNodoSpc.setCodContestoPagamento(codiceContestoPagamento);	//PG160130 GG 01082016
						//31012017 GG - inizio
						rptNodoSpc.setIdPSP(idPsp);
						rptNodoSpc.setIdIntermediarioPSP(idIntermediarioPsp);
						rptNodoSpc.setIdCanalePSP(idCanale);
						//31012017 GG - fine
						
						//					String codiceIuv = NodoSpcHelper.inserisciRPT(propertiesTree(), LOG, dbSchemaCodSocieta, rptNodoSpc);
						//					rptNodoSpc.setCodiceIuv(codiceIuv);
						
						String chiaveEnte = "";
						if(entiBollettini.containsKey(rptNodoSpc.getCodiceTabella() + "_" + rptNodoSpc.getChiaveTabella())) {
							chiaveEnte = entiBollettini.get(rptNodoSpc.getCodiceTabella() + "_" + rptNodoSpc.getChiaveTabella());
						}
						
						String ibanAccredito = "";
						String cfEnteCreditore = "";
						String nomeEnteCreditore = "";
						
//inizio LP PG180290
//Commento parte finora eseguita ma non di nessuna utilita'
/*						
						//Recupero l'iban di accredito per ogni bollettino dalla configurazione del suo ente/servizio
						
						try {
							String tipoServizio = "";
							ConfigUtenteTipoServizioEnteDetailResponse  configUtenteEnte =null;
							info("codiciTipoServizioBollettini = "+ codiciTipoServizioBollettini);
							info("rptNodoSpc.getCodiceTabella() = "+ rptNodoSpc.getCodiceTabella());
							info("rptNodoSpc.getChiaveTabella()() = "+ rptNodoSpc.getChiaveTabella());
							info("ricerca chiave = "+ configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nodoibancreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte));
//PG170300 - 30/1/2018 - INIZIO - IL CODICE IBAN NON E' PIU' UNIVOCO MA VA PRESO QUELLO CARICATO IN FASE DI TOTALIZZAZIONE E CARICAMENTO DELL'IBAN
//							if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nodoibancreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte)!=null){
//								ibanAccredito = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nodoibancreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte);
//								info("ibanAccredito da eventuale file= "+ ibanAccredito);
//							}
//							else{ 
//								if (ibanAccreditoBollettino.trim().length()>0) {
//									ibanAccredito = ibanAccreditoBollettino;
//									
//								} else {
//									if(codiciTipoServizioBollettini.containsKey(rptNodoSpc.getCodiceTabella() + "_" + rptNodoSpc.getChiaveTabella())) {
//										tipoServizio = codiciTipoServizioBollettini.get(rptNodoSpc.getCodiceTabella() + "_" + rptNodoSpc.getChiaveTabella());
//									}
//									info("tipo servizio = "+ tipoServizio);
//									
//									configUtenteEnte = GatewaysIGHelper.getConfigUtenteTipoServizioEnteDetailResponse(propertiesTree(), dbSchemaCodSocieta, rptNodoSpc.getCodSocieta(), rptNodoSpc.getCodUtente(), chiaveEnte, tipoServizio);
//									ibanAccredito = configUtenteEnte.getConfigutentetiposervizioente().getCodiceIban();
//									info("ibanAccredito da tabella= "+ ibanAccredito);
//								}
//							}
//							
//							
//							if(tipoPagamentoPoste)
//							{
//								info("tipoPagamentoPoste POSTE ");
//								if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nodoibancreditatoenteposte." + dbSchemaCodSocieta + "." + chiaveEnte)!=null)
//									ibanAccredito = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nodoibancreditatoenteposte."+ dbSchemaCodSocieta + "." + chiaveEnte);
//								else{
//									if(ibanAccreditoBollettino.trim().length()>0) {
//										ibanAccredito = ibanAccreditoBollettino;
//									} else {
//										if(configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban()!=null && !configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban().equals(""))
//											ibanAccredito = configUtenteEnte.getConfigutentetiposervizioente().getSecondoCodiceIban();
//									}
//								
//								}
//								info("POSTE ibanaccreditato = "+ ibanAccredito);
//							}	
//PG170300 - 30/1/2018 - FINE
						}
						catch (Exception e) {
							//e.printStackTrace();
							error("Impossibile recuperare IbanAccredito per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage());
							throw new Exception("Impossibile recuperare IbanAccredito per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage(),e);		//20082016 GG
						}
*/						
//fine LP PG180290

						//Recupero cf ente per ogni bollettino dalla configurazione del suo ente
						try {
							
							//inizio LP PG180290
							if(bIsMyPay) {
								//Qui si deve prelevare codIpaEnte, password, passwordHash256
								//da fare arrivare a MyPay su IntestazioneEnte
								EnteDetailResponse  enteDetail = GatewaysIGHelper.getEnteDetailSearchResponse(propertiesTree(), dbSchemaCodSocieta, rptNodoSpc.getCodSocieta(), rptNodoSpc.getCodUtente(), chiaveEnte);
								
								cfEnteCreditore = enteDetail.getEnte().getCFiscalePIva();
								info("cfEnteCreditore da basedati = "+ cfEnteCreditore);
								myPayCodiceIpaEnte = enteDetail.getEnte().getCodIpaEnte();
								info("myPayCodiceIpaEnte = " + myPayCodiceIpaEnte);
								myPayPasswordEnte = enteDetail.getEnte().getPasswordEnte();
								info("myPayPasswordEnte = " + myPayPasswordEnte);
								myPayPasswordEnteCrypt = enteDetail.getEnte().getPasswordSha256Ente();
								info("myPayPasswordEnteCrypt = " + myPayPasswordEnteCrypt);
								info("myPayTipoDovutoLst = " + myPayTipoDovutoLst);
							} else {
							//fine  LP PG180290

							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".nodocreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte)!=null) {
								cfEnteCreditore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".nodocreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte);
								info("cfEnteCreditore letto da file= "+ cfEnteCreditore);
							}
							else {
								EnteDetailResponse  enteDetail = GatewaysIGHelper.getEnteDetailSearchResponse(propertiesTree(), dbSchemaCodSocieta, rptNodoSpc.getCodSocieta(), rptNodoSpc.getCodUtente(), chiaveEnte);
								
								cfEnteCreditore = enteDetail.getEnte().getCFiscalePIva();
								info("cfEnteCreditore letto basedati = "+ cfEnteCreditore);
							}
							//inizio LP PG180290
							}
							//fine  LP PG180290
						}
						catch (Exception e) {
							//e.printStackTrace();
							error("Impossibile recuperare CF per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage());
							throw new Exception("Impossibile recuperare CF per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage(),e);		//20082016 GG
						}
						
						//Recupero nome ente per ogni bollettino dalla configurazione del suo ente
						try {
							AnagEnteSearchResponse anagenteSearchResponse =  GatewaysIGHelper.getAnagEnteSearchResponse(propertiesTree(), dbSchemaCodSocieta, chiaveEnte,"");
							nomeEnteCreditore = getDescrizioneEnte(anagenteSearchResponse.getResponse().getListXml());
						}
						catch (Exception e) {
							//e.printStackTrace();
							error("Impossibile recuperare Nome per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage());
							throw new Exception("Impossibile recuperare Nome per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage(),e);		//20082016 GG
						}
						
						//Modifica richiesta il 12/06/2014
						identificativoDominio = cfEnteCreditore.trim();
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta + "." + identificativoDominio)!=null) {
							identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta + "." + identificativoDominio);
						}
						info("identificativoStazioneIntermediarioPA = "+ identificativoStazioneIntermediarioPA);
						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta + "." + identificativoDominio)!=null) {
							passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta + "." + identificativoDominio);
						}
						info("passwordPsp = "+ passwordPsp);
						
						//01082016 GG PG160130 - spostato inserisciRPT dopo recupero idDominio - inizio
						rptNodoSpc.setIdDominio(identificativoDominio);
						String[] codici = NodoSpcHelper.inserisciRPT(propertiesTree(), LOG, dbSchemaCodSocieta, rptNodoSpc);
						String strPaymentId = codici[0];
						String codiceIuv = codici[1];
						BigInteger paymentId = new BigInteger(strPaymentId);
						System.out.println("codiceIuv = " + codiceIuv);
						rptNodoSpc.setCodiceIuv(codiceIuv);
						System.out.println("paymentId = " + paymentId);
						rptNodoSpc.setId(paymentId);
						//01082016 GG PG160130 - spostato inserisciRPT dopo recupero idDominio - fine
						
						
						//PASSEROTTINO
						byte[] rpt = null;
						String rptXml = "";
						//01082016 GG PG160130 introdoto codiceContestoPagamento
						//PG160230_001 GG 23112016 - inizio
						String idBollettiniListForCausaleVersamento = null;
						if(dbSchemaCodSocieta.equals("000TO"))
							idBollettiniListForCausaleVersamento = idBollettiniList;
						//PG160230_001 GG 23112016 - fine	
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//						if (tipoPagamentoPoste) {
//							rptXml = NodoSpcHelper.generaRPTXmlPoste(propertiesTree(), LOG, "", identificativoDominio, identificativoStazioneIntermediarioPA, cfPagatore, nomePagatore, in.getEmailContribuente()
//									, cfEnteCreditore, nomeEnteCreditore, rptNodoSpc.getImporto(), codiceIuv, ibanAccredito, datiSpecificiRiscossione, tipoVersamento, codiceContestoPagamento, idBollettiniListForCausaleVersamento); 	//PG160230_001 GG 23112016
//							rptNodoSpc.setRpt(rptXml);
//							//Salvo RPT su db
//						} else {
//							//Creo RPT
//							rptXml = NodoSpcHelper.generaRPTXml(propertiesTree(), LOG, "", identificativoDominio, identificativoStazioneIntermediarioPA, cfPagatore, nomePagatore, in.getEmailContribuente()
//									, cfEnteCreditore, nomeEnteCreditore, rptNodoSpc.getImporto(), codiceIuv, ibanAccredito, datiSpecificiRiscossione, tipoVersamento, codiceContestoPagamento, idBollettiniListForCausaleVersamento);	//PG160230_001 GG 23112016
//							rptNodoSpc.setRpt(rptXml);
//							//Salvo RPT su db
//						}

						if(cfPagatore==null || cfPagatore.equals("") || cfPagatore.equals(" "))
							cfPagatore = "ANONIMO";
						
						System.out.println("Gateways - [riga 1739] - datiSpecificiRiscossione: "+datiSpecificiRiscossione);
						if (tipoPagamentoPoste) {
							rptXml = NodoSpcHelper.generaRPTXmlPoste(propertiesTree(), 
									LOG, 
									"", 
									identificativoDominio, 
									identificativoStazioneIntermediarioPA, 
									cfPagatore, 
									nomePagatore, 
									in.getEmailContribuente(),
									cfEnteCreditore, 
									nomeEnteCreditore, 
									rptNodoSpc.getImporto(), 
									codiceIuv, 
									ibanAccredito, 
									datiSpecificiRiscossione, 
									tipoVersamento, 
									codiceContestoPagamento, 
									idBollettiniListForCausaleVersamento,
									listRptDatiPagamenti,
									//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP
									null,
									null
									, null //PG200060
									, null //PG200060
									);
									//PG180070 - 31/7/2018 - FINE

							rptNodoSpc.setRpt(rptXml);
							//Salvo RPT su db
						} else {
							//Creo RPT
							rptXml = NodoSpcHelper.generaRPTXml(propertiesTree(), 
									LOG, 
									"", 
									identificativoDominio, 
									identificativoStazioneIntermediarioPA, 
									cfPagatore, 
									nomePagatore, 
									in.getEmailContribuente(), 
									cfEnteCreditore, 
									nomeEnteCreditore, 
									rptNodoSpc.getImporto(), 
									codiceIuv, 
									ibanAccredito, 
									datiSpecificiRiscossione, 
									tipoVersamento, 
									codiceContestoPagamento, 
									idBollettiniListForCausaleVersamento,
									listRptDatiPagamenti,
									//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP
									null,
									null
									, null //PG200060
									, null //PG200060
									);
									//PG180070 - 31/7/2018 - FINE
							rptNodoSpc.setRpt(rptXml);
							//Salvo RPT su db
						}
//PG170300 - 30/1/2018 - FINE
						rpt = rptXml.getBytes("UTF-8");
						
						//					//Creo RPT
						//					String rptXml = NodoSpcHelper.generaRPTXml(propertiesTree(), LOG, "", identificativoDominio, identificativoStazioneIntermediarioPA, cfPagatore, nomePagatore, in.getEmailContribuente()
						//							, cfEnteCreditore, nomeEnteCreditore, rptNodoSpc.getImporto(), codiceIuv, ibanAccredito, datiSpecificiRiscossione, tipoVersamento);
						//					rptNodoSpc.setRpt(rptXml);
						//					//Salvo RPT su db
						//					byte[] rpt = rptXml.getBytes();
						
						//10082016 GG PG160130
						//					String iuv = "0";
						//					//Prendo solo la parte numerica
						//					System.out.println("codiceIuv = " + codiceIuv);
						//					if (codiceIuv.length() > 4)
						//						iuv = codiceIuv.substring(4);
						//					
						//					System.out.println("variabile iuv = " + iuv);
						//					BigInteger paymentId = new BigInteger(iuv);
						//					
						//					System.out.println("paymentId = " + paymentId);
						//					rptNodoSpc.setId(paymentId);
						//10082016 GG PG160130
						
						InserisciRptRequest inserisciRptRequest = new InserisciRptRequest(paymentId.intValue(), rpt);
						InserisciRptResponse res = binding.inserisciRPT(inserisciRptRequest);
						//31012017 GG - inizio
						if (!res.getRetCode().equals("00")) {
							throw new Exception("Errore in inserisciRPT: " + res.getRetMessage());		//31012017 GG
						}
						//31012017 GG - fine
					}
				} catch (Exception e) {
					//28092017 GG - inserito try-catch in modo da evitare transazioni sospese in caso di errore in inserimento RPT: per le transazioni per cui si verifica un errore prima della chiamata al nodo, si aggiorna lo stato a 2
					e.printStackTrace();
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTra, TRA_FAILED_STATE, "", "", Calendar.getInstance());
					info("Aggiornamento esito transazione in stato FALLITA: " + chiaveTra);
					binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);	//TODO da verificare
					throw new Exception(e.getMessage(), e);
				}
				
				if (listRpt.size()>=1){ //Quando si abiliterà la chiamata carrelloRPT allora qui dovrà entrare solo con size()==1
					
					String chiaveEnte = "";
					if(entiBollettini.containsKey(listRpt.get(0).getCodiceTabella() + "_" + listRpt.get(0).getChiaveTabella())) {
						chiaveEnte = entiBollettini.get(listRpt.get(0).getCodiceTabella() + "_" + listRpt.get(0).getChiaveTabella());
					}
					
					//Chiamo WS Payer per scrittura tabelle log (MIN, MPN, ecc...)
					try {
						MIPSOAPBidingStub mipWsServer = GatewaysIGHelper.getMIPSOAPBidingStub(propertiesTree(), dbSchemaCodSocieta);
						//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
						//ModuloIntegrazionePagamentiNodo min = new ModuloIntegrazionePagamentiNodo(chiaveTra, listRpt.get(0).getCodSocieta(), listRpt.get(0).getCodUtente(), chiaveEnte, identificativoIntermediarioPA, 
						//		listRpt.get(0).getCodiceIuv(), "", "", new String(listRpt.get(0).getRpt().getBytes("UTF-8")), "", "", "", "", "", Calendar.getInstance());
						ModuloIntegrazionePagamentiNodo min = new ModuloIntegrazionePagamentiNodo(chiaveTra, listRpt.get(0).getCodSocieta(), listRpt.get(0).getCodUtente(), chiaveEnte, identificativoIntermediarioPA, 
								listRpt.get(0).getCodiceIuv(), "", "", new String(listRpt.get(0).getRpt().getBytes("UTF-8"), "UTF-8"), "", "", "", "", "", Calendar.getInstance());
						//fine LP 20210325
						//inizio LP PG180290
						if(bIsMyPay) {
							min.setNumeroOperazione(""); //sbianco il codiceIUV
						}
						//fine LP PG180290
						MINSaveRequest inMinSaveRequest = new MINSaveRequest(min );
						StatusResponse statusResponse = mipWsServer.saveNodo(inMinSaveRequest);
						info("Esito salvataggio MIN: " + statusResponse.getRetCode() + " - " + statusResponse.getRetMessage());
						
//						String jindi_context = env.getProperty(Context.INITIAL_CONTEXT_FACTORY);
//						String jindi_provider = env.getProperty(Context.PROVIDER_URL);
//						
//						ModuloIntegrazionePagamentiNodoFacadeRemoteHome	serviceRemoteHome = (ModuloIntegrazionePagamentiNodoFacadeRemoteHome) ServiceLocator
//									.getInstance().getRemoteHome(jindi_provider, jindi_context,
//											null, null, ModuloIntegrazionePagamentiNodoFacadeRemoteHome.JNDI_NAME,
//											ModuloIntegrazionePagamentiNodoFacadeRemoteHome.class);
//							
//						ModuloIntegrazionePagamentiNodoFacade service =  serviceRemoteHome.create();
//
//						com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodo mipBean = new com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodo();
//			    		mipBean.setChiaveTransazione(chiaveTra);
//			    		mipBean.setNumeroOperazione(listRpt.get(0).getCodiceIuv());
//			    		mipBean.setIdPortale("");
//			    		mipBean.setXmlPaymentRequest( listRpt.get(0).getRpt());
//			    		mipBean.setXmlPaymentData("");
//			    		mipBean.setStatoPagamentoCUP("");
//			    		mipBean.setUltimoEsitoNotifica("");
//			    		mipBean.setCodicePortaleEsterno(identificativoIntermediarioPA);
//			    		mipBean.setParametriOpzionali1("");
//			    		mipBean.setParametriOpzionali2("");
//			    		mipBean.setDataUltimoAggiornamento( Calendar.getInstance());
//			    		mipBean.setCodiceSocieta(dbSchemaCodSocieta);
//			    		mipBean.setCodiceUtente(listRpt.get(0).getCodUtente());
//			    		mipBean.setChiaveEnte("");
//			    		mipBean.setCommitNotifica("");
//						if (mipBean != null)
//						{
//							//info("Request execute successfully");
//							service.save(mipBean, dbSchemaCodSocieta);
//							info("Salvataggio MIN riuscito con successo.");
//						}
						
					}
					catch (Exception e) {
						e.printStackTrace();
						error("Impossibile salvare MIN:" + e.getMessage());
						AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTra, TRA_FAILED_STATE, "", "", Calendar.getInstance());
						info("Aggiornamento esito transazione in stato FALLITA: " + chiaveTra);
						binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);	//TODO da verificare
						throw new Exception("Errore in salvataggio MIN: " + e.getMessage(),e);		//31012017 GG
					}
					
					//inizio LP PG1900XX_001 uso MyBridge
					NodoInviaRPTRisposta nodoInviaRPTRisposta = null;
					//inizio LP PG180290
					String identificativoUnivocoDovuto = "";
					if(bIsMyPay) {
						MyPayPagonetFacetServiceLocator locatorMyPay = new MyPayPagonetFacetServiceLocator();
						MyPayPagonetServicesEndpoint serviceMyPay = locatorMyPay.getMyPayPagonetServicesPort(new URL(myPayPagonetUrl));
						info(Events.TRA_ALIGN.format() + " |  inviaCarrelloDovutiMyPay");
						byte[] rpt = listRpt.get(0).getRpt().getBytes("UTF-8"); //per adesso invio rpt pagoPA
						InviaCarrelloDovutiMyPay bodyrichiestaMyPay = new InviaCarrelloDovutiMyPay();
						bodyrichiestaMyPay.setPassword(myPayPasswordEnte);
						bodyrichiestaMyPay.setPasswordSha256(myPayPasswordEnteCrypt);
						//bodyrichiestaMyPay.setTipoDovuto(myPayTipoDovuto); //se unico tributo 
						//bodyrichiestaMyPay.setTipoDovuto(tipoDovutoBollettini); //se tributo può essere diverso per elementi nella rpt
						
						bodyrichiestaMyPay.setRpt(rpt);
						org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT headerMyPay = new org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT();
						headerMyPay.setCodIpaEnte(myPayCodiceIpaEnte);
						String urlReturn = generaMessageGTWResponse.getApiReturnURL();
						String csrfToken = ((in.getTokenCsrf() != null && in.getTokenCsrf().length() > 0) ? in.getTokenCsrf() : "");
						if(urlReturn.indexOf('?') == -1) {
							urlReturn += "?";
						} else {
							urlReturn += "&";
						}
						urlReturn += "TransactionID=" + in.getTransazione().getChiaveTransazione() + "&" + csrfToken;
						//TODO: inserendo l'URL sotto mypay risponde bene ma non se si fa il redirect su MyPay
						String enteSILInviaRispostaPagamentoUrl = urlReturn;
						//enteSILInviaRispostaPagamentoUrl è l'url dell'applicazione web dell'Ente a cui verrà
						//inviato l'esito della transazione (non l'esito del pagamento)
						headerMyPay.setEnteSILInviaRispostaPagamentoUrl(enteSILInviaRispostaPagamentoUrl);
						
						InviaCarrelloDovutiMyPayRisposta res =  serviceMyPay.inviaCarrelloDovutiMyPay(bodyrichiestaMyPay, headerMyPay);
						nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
						if(res != null) {
							if(res.getEsito().equals("KO")) {
								nodoInviaRPTRisposta.setEsito("KO");
								org.esed.MyPayPagonet.webservice.operazioni.FaultBean ff = res.getFault();
								FaultBean ffOut = new FaultBean();
								ffOut.setFaultCode(ff.getFaultCode());
								ffOut.setFaultString(ff.getFaultString());
								ffOut.setId(ff.getId());
								ffOut.setDescription(ff.getDescription());
								ffOut.setSerial(ff.getSerial());
								nodoInviaRPTRisposta.setFault(ffOut);
							} else {
								nodoInviaRPTRisposta.setEsito("OK");
								nodoInviaRPTRisposta.setRedirect(res.getRedirect());
								nodoInviaRPTRisposta.setUrl(res.getUrl());
								identificativoUnivocoDovuto = res.getEsito(); //Su esito arrivano lista di iud o KO
							}
						} else {
							nodoInviaRPTRisposta.setEsito("KO");
							String description = "inviaCarrelloDovutiMyPay to MyPay: 99: errore generico";
							FaultBean ffOut = new FaultBean();
							ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
							ffOut.setFaultString("Errore generico.");
							ffOut.setId(identificativoDominio);
							ffOut.setDescription(description);
							ffOut.setSerial(1);
							nodoInviaRPTRisposta.setFault(ffOut);
						}
					} else {
					//fine LP PG180290
					if (nodospcGestore.equals("M")) {
						EsedFacetServiceLocator locator = new EsedFacetServiceLocator();
						EsedServicesEndpoint serviceM = locator.getEsedServicesPort(new URL(mybridgeUrl + "/EsedServicesPort"));

						info(Events.TRA_ALIGN.format() + " |  nodoInviaRPT vs Myridge");
						byte[] rpt = listRpt.get(0).getRpt().getBytes("UTF-8");	//GG 18112016
						String iuv = listRpt.get(0).getCodiceIuv();
						com.esed.mybridge.webservice.operazioni.NodoInviaRPT bodyrichiesta = new com.esed.mybridge.webservice.operazioni.NodoInviaRPT(passwordPsp, idPsp.trim(), idIntermediarioPsp.trim(), idCanale.trim(), tipoFirma, rpt);
						com.esed.mybridge.webservice.operazioni.head.IntestazionePPT header = new com.esed.mybridge.webservice.operazioni.head.IntestazionePPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), identificativoDominio.trim(), iuv.trim(), codiceContestoPagamento.trim());
						
						com.esed.mybridge.webservice.operazioni.NodoInviaRPTRisposta res = serviceM.nodoInviaRPT(bodyrichiesta, header);
						
						nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
						if(res != null) {
							nodoInviaRPTRisposta.setEsito(res.getEsito());
							if(res.getEsito().equals("KO")) {
								com.esed.mybridge.webservice.operazioni.FaultBean ff = res.getFault();
								FaultBean ffOut = new FaultBean();
								ffOut.setFaultCode(ff.getFaultCode());
								ffOut.setFaultString(ff.getFaultString());
								ffOut.setId(ff.getId());
								ffOut.setDescription(ff.getDescription());
								ffOut.setSerial(ff.getSerial());
								nodoInviaRPTRisposta.setFault(ffOut);
							} else {
								nodoInviaRPTRisposta.setRedirect(res.getRedirect());
								nodoInviaRPTRisposta.setUrl(res.getUrl());
							}
						} else {
							nodoInviaRPTRisposta.setEsito("KO");
							String description = "nodoInviaRPT to MyBridge: 99: errore generico";
							FaultBean ffOut = new FaultBean();
							ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
							ffOut.setFaultString("Errore generico.");
							ffOut.setId(identificativoDominio);
							ffOut.setDescription(description);
							ffOut.setSerial(1);
							nodoInviaRPTRisposta.setFault(ffOut);
						}
					} else {
					//fine LP PG1900XX_001 uso MyBridge
					//inizio LP PG1900XX_001 uso EasyBridge
					/*
						//Richiamare il WS del Nodo nodoInviaRPT()
						PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
						//PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(generaMessageGTWResponse.getApiEndPointUrl())); //"http://INDIRIZZO_NODOSPC_PORTA_DI_DOMINIO"
						String nodoSpcPortDomain = nodoSpcUrl;//ambiente di test
						//inizio LP PG1900XX_001
						//PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoInviaRPT"));
						PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
						
						if(nodospcTipologia.equals("N")) {
							pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoInviaRPT"));
						} else {
							pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain));
						}
						//fine LP PG1900XX_001
						
						PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub)pagamentiTelematiciRPT;
						//inizio LP PG1900XX_001
						if(nodospcTipologia.equals("N")) {
						//fine LP PG1900XX_001
							_stub.setUsername(username);
							_stub.setPassword(password);
						//inizio LP PG1900XX_001
						}	
						//fine LP PG1900XX_001
						
						HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
						QName  portName = _stub.getPortName();
						List handlerChain = hr.getHandlerChain(portName);
						
						HandlerInfo hi = new HandlerInfo();
						hi.setHandlerClass(WsNodoRpcHandler.class);
						handlerChain.add(hi);
						
						if(proxyEnabled) {
							System.getProperties().put("http.proxyHost", proxyHost);
							System.getProperties().put("http.nonProxyHosts", "localhost");
							System.getProperties().put("http.proxyPort", proxyPort);
							System.getProperties().put("http.proxyUser", proxyUser);
							System.getProperties().put("http.proxyPassword", proxyPassword);
						}
						String identificativoUnivocoVersamento = "";
	//					String codiceContestoPagamento = "n/a";	//01082016 GG PG160130 spostato perchè utilizzato in precedenza
						
						
						//RptNodoSpcRequest inRecuperaRPT = new RptNodoSpcRequest(listRpt.get(0).getId(), "", "");
						//RptNodoSpcResponse outRecuperRPT = binding.recuperaRPT(inRecuperaRPT);
						
						
						//byte[] rpt = Base64.encodeBase64(listRpt.get(0).getRpt().getBytes());
						byte[] rpt = listRpt.get(0).getRpt().getBytes("UTF-8");	//GG 18112016
						//18112016 GG - inizio per debug
	//					String rpt2 = Base64.encode(listRpt.get(0).getRpt().getBytes("UTF-8"));
	//					System.out.println(rpt2);
						//18112016 GG - fine per debug
						
						identificativoUnivocoVersamento = listRpt.get(0).getCodiceIuv();
						NodoInviaRPT bodyrichiesta = new NodoInviaRPT(passwordPsp, idPsp.trim(), idIntermediarioPsp.trim(), idCanale.trim(), tipoFirma, rpt);
						IntestazionePPT header = new IntestazionePPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim(),null,null);
						info("INIZIO CHIAMATA nodoInviaRPT");
						//inizio LP PG1900XX_001 uso MyBridge
						//NodoInviaRPTRisposta nodoInviaRPTRisposta = pagamentiTelematiciRPT.nodoInviaRPT(bodyrichiesta, header);
						nodoInviaRPTRisposta = pagamentiTelematiciRPT.nodoInviaRPT(bodyrichiesta, header);
						//fine LP PG1900XX_001 uso MyBridge
					*/
						com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator locator = new com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator();
						com.esed.easybridge.webservice.operazioni.EsedServicesEndpoint serviceE = locator.getEsedServicesPort(new URL(easybridgeUrl + "/EsedServicesPort"));

						info(Events.TRA_ALIGN.format() + " |  nodoInviaRPT vs EasyBridge");
						String cutecute = "";
						
						byte[] rpt = listRpt.get(0).getRpt().getBytes("UTF-8");	//GG 18112016
						String iuv = listRpt.get(0).getCodiceIuv();
						com.esed.easybridge.webservice.operazioni.NodoInviaRPT bodyrichiestaE = new com.esed.easybridge.webservice.operazioni.NodoInviaRPT(passwordPsp, idPsp.trim(), idIntermediarioPsp.trim(), idCanale.trim(), tipoFirma, rpt);
						com.esed.easybridge.webservice.operazioni.head.IntestazionePPT headerE = new com.esed.easybridge.webservice.operazioni.head.IntestazionePPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), identificativoDominio.trim(), iuv.trim(), codiceContestoPagamento.trim(), dbSchemaCodSocieta);
						
						com.esed.easybridge.webservice.operazioni.NodoInviaRPTRisposta resE = serviceE.nodoInviaRPT(bodyrichiestaE, headerE);
						
						nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
						if(resE != null) {
							nodoInviaRPTRisposta.setEsito(resE.getEsito());
							if(resE.getEsito().equals("KO")) {
								com.esed.easybridge.webservice.operazioni.FaultBean ff = resE.getFault();
								FaultBean ffOut = new FaultBean();
								ffOut.setFaultCode(ff.getFaultCode());
								ffOut.setFaultString(ff.getFaultString());
								ffOut.setId(ff.getId());
								ffOut.setDescription(ff.getDescription());
								ffOut.setSerial(ff.getSerial());
								nodoInviaRPTRisposta.setFault(ffOut);
							} else {
								nodoInviaRPTRisposta.setRedirect(resE.getRedirect());
								nodoInviaRPTRisposta.setUrl(resE.getUrl());
							}
						} else {
							nodoInviaRPTRisposta.setEsito("KO");
							String description = "nodoInviaRPT to EasyBridge: 99: errore generico";
							FaultBean ffOut = new FaultBean();
							ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
							ffOut.setFaultString("Errore generico.");
							ffOut.setId(identificativoDominio);
							ffOut.setDescription(description);
							ffOut.setSerial(1);
							nodoInviaRPTRisposta.setFault(ffOut);
						}
					//fine LP PG1900XX_001 uso EasyBridge
					//inizio LP PG1900XX_001 uso MyBridge
					}
					//fine LP PG1900XX_001 uso MyBridge
					//inizio LP PG180290
					}
					//fine LP PG180290
					info("ESITO nodoInviaRPT = " + nodoInviaRPTRisposta.getEsito());
					if (nodoInviaRPTRisposta.getFault() != null && nodoInviaRPTRisposta.getFault().getFaultCode() != null && !nodoInviaRPTRisposta.getFault().getFaultCode().equals("")){
						String err_msg = "Fault code: " + nodoInviaRPTRisposta.getFault().getFaultCode() + " Fault String: " + nodoInviaRPTRisposta.getFault().getFaultString() + "Fault Description: " + nodoInviaRPTRisposta.getFault().getDescription();
						error(err_msg);
						try {
							RptNodoSpc rptNodoSpcToUpdate = listRpt.get(0); //new RptNodoSpc();
							//rptNodoSpcToUpdate.setId(listRpt.get(0).getId());
							//inizio LP PG180290
							if(bIsMyPay) {
								String codiceIuvMyPay = rptNodoSpcToUpdate.getCodiceIuv();
								codiceIuvMyPay = "MP" + codiceIuvMyPay.replaceFirst("RF", "");  
								rptNodoSpcToUpdate.setCodiceIuv(codiceIuvMyPay);
								//rptNodoSpcToUpdate.setIdentificativoTipoDovuto(myPayTipoDovuto);
								rptNodoSpcToUpdate.setIdentificativoTipoDovuto(myPayTipoDovutoLst);
								String errMsg = nodoInviaRPTRisposta.getFault().getFaultCode();
								if(errMsg.length() > 100)
									errMsg = errMsg.substring(0, 100);
								rptNodoSpcToUpdate.setRptEsito(errMsg);
							} else {
							//fine LP PG180290
							String errMsg = "KO:" + nodoInviaRPTRisposta.getFault().getFaultCode();
							if(errMsg.length()>20)
								errMsg = errMsg.substring(0, 20);
							rptNodoSpcToUpdate.setRptEsito(errMsg);
							//inizio LP PG180290
							}
							//fine LP PG180290
							UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
							binding.updateRptNodoSpc(inUpdateRPT );
						}
						catch (Exception e) {
							warn("Aggiornamento Esito KO RPT non riuscito");
						}
						//PG160150_002 GG - inizio
						//return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, err_msg), "","","");
						String retMessage = nodoInviaRPTRisposta.getFault().getFaultCode();
						if (nodoInviaRPTRisposta.getFault().getFaultString()!=null && !nodoInviaRPTRisposta.getFault().getFaultString().trim().equals("")) {
							retMessage = retMessage.concat(" - ").concat(nodoInviaRPTRisposta.getFault().getFaultString());
						}
						return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, retMessage), "","","");
						//PG160150_002 GG - fine
					}
					else{
						//inizio LP PG180290
						String idSessionMyPay = "";
						if(bIsMyPay) {
							if(nodoInviaRPTRisposta.getRedirect() == 1) {
								apiRedirect = nodoInviaRPTRisposta.getUrl();
								if(apiRedirect.indexOf("idSession=") > -1) {
									idSessionMyPay = apiRedirect.substring(apiRedirect.indexOf("idSession=") + 10);
									info("idSessionMyPay: " + idSessionMyPay);
								}
							}
							else {
								//errore deve esserci la redirect per MyPay
							}
						}
						//fine LP PG180290
						try {
							RptNodoSpc rptNodoSpcToUpdate = listRpt.get(0); //new RptNodoSpc();
							//rptNodoSpcToUpdate.setId(listRpt.get(0).getId());
							rptNodoSpcToUpdate.setRptEsito("OK");
							//inizio LP PG180290
							//per mypay (ma va bene anche per gli altri gateways non aggiorno la rpt, rimane quella presente sul DB
							//che nel caso di mypay forse sostituisco a quella tipo pagopa con quella inviata a mypay (== dovuti)
							if(bIsMyPay) {
								String codiceIuvMyPay = rptNodoSpcToUpdate.getCodiceIuv();
								codiceIuvMyPay = "MP" + codiceIuvMyPay.replaceFirst("RF", "");  
								rptNodoSpcToUpdate.setCodiceIuv(codiceIuvMyPay);
								rptNodoSpcToUpdate.setIdSessioneCarrello(idSessionMyPay);
								rptNodoSpcToUpdate.setIdentificativoUnivocoDovuto(identificativoUnivocoDovuto);
								//rptNodoSpcToUpdate.setIdentificativoTipoDovuto(myPayTipoDovuto);
								rptNodoSpcToUpdate.setIdentificativoTipoDovuto(myPayTipoDovutoLst);
							}
							//fine LP PG180290
							UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
							binding.updateRptNodoSpc(inUpdateRPT );
						}
						catch (Exception e) {
							warn("Aggiornamento Esito OK RPT non riuscito");
						}
						
						//Leggere l'url restituita per la redirect
						if(nodoInviaRPTRisposta.getRedirect() == 1) {
							apiRedirect = nodoInviaRPTRisposta.getUrl();
							info("Url Redirect: " + apiRedirect);
							String idsessionPSP = "";
							if(apiRedirect.indexOf("idSession=")>-1) {
								idsessionPSP = apiRedirect.substring(apiRedirect.indexOf("idSession=")+ 10);
							}
							params = idsessionPSP;
						}
						else {
							apiRedirect = "";
							info("Redirect non previsto per il pagamento nel PSP selezionato: " + idPsp);
						}
					}
				}
				else{
					//COMMENTATO PERCHE' IL METODO nodoInviaCarrelloRPT NON E' ANCORA IMPLEMENTATO...
//					TipoElementoListaRPT[] RPTList = new TipoElementoListaRPT[listRpt.size()];
//					for (int i=0; i<listRpt.size(); i++) {
//						RPTList[i].setRpt(Base64.encodeBase64(listRpt.get(i).getRpt().getBytes()));
//					}
//					String idCarrello = "";
//					NodoInviaCarrelloRPT carrelloRPT = new NodoInviaCarrelloRPT(password, idPsp, idIntermediarioPsp, idCanale, RPTList);
//					IntestazioneCarrelloPPT header = new IntestazioneCarrelloPPT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, idCarrello);
//					NodoInviaCarrelloRPTRisposta nodoInviaCarrelloRPTRisposta = pagamentiTelematiciRPT.nodoInviaCarrelloRPT(carrelloRPT, header);
				}
				
			}
			//PG150180_001 GG - fine
			
			info("Fine integraGTWSinc");
			// we return response
			return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"), apiRedirect,generaMessageGTWResponse.getTipoGateway(),params);
			
		} catch (Exception ex) {
			error(ex.getMessage(), ex);
			return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), "","","");
		}   	
	}
	
//PG180070 - 30/1/2018 - INIZIO - Gestione carrello multiente per PSP
	
	public IntegraGTWSincResponse integraGTWSinc_MultiEnte(IntegraGTWSinc_MultiEnteRequest in) throws RemoteException, FaultType {	
		
		System.out.println("Inizio Elaborazione Multi Carrello");
		String xml = ""; 
		String rptXml_Totale = ""; 
		
		//salviamo il template e modifichiamo la url per gestire il bilinguismo
		String lingua = "&lang=IT";
		String chiave_transazione = in.getIntegraGTWSinc_MultiEnte(0).getTransazione().getChiaveTransazione();
		if(chiave_transazione.contains("||")){
			template=in.getIntegraGTWSinc_MultiEnte(0).getTransazione().getChiaveTransazione().split("\\|\\|")[1];
			System.out.println("Template: " + template);
			if (template.equalsIgnoreCase("BOLZANODE")) lingua = "&lang=de";
			if (template.equalsIgnoreCase("BOLZANOEN")) lingua = "&lang=en";
			chiave_transazione =in.getIntegraGTWSinc_MultiEnte(0).getTransazione().getChiaveTransazione().split("\\|\\|")[0];
		}
		
		//inizio LP PG210130 Step04
		String codiceContestoPagamento = "";
		String codiceIUV = "";
		boolean bMultiBeneficiario = false;
		String chiaveGTW = in.getIntegraGTWSinc_MultiEnte(0).getTransazione().getChiaveGTW();
		int iMB = chiaveGTW.indexOf("MULTIBENEFICIARIO");
		if(iMB != -1) {
			bMultiBeneficiario = true;
			chiaveGTW = chiaveGTW.substring(0, iMB);
			in.getIntegraGTWSinc_MultiEnte(0).getTransazione().setChiaveGTW(chiaveGTW);
		}
		//fine  LP PG210130 Step04
		
		PropertiesTree configuration = readConf();
		
		IntegraGTWSincRequest request = new IntegraGTWSincRequest();
		IntegraGTWSincResponse response = new IntegraGTWSincResponse();
		
		String init = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String carrello = "<carrelloRPT><cutecute>"+dbSchemaCodSocieta+"</cutecute>";
		//inizio LP PG210130 Step04
		if(bMultiBeneficiario)
			carrello += "<multibeneficiario>S</multibeneficiario>";
		//fine  LP PG210130 Step04
		String fine = "</carrelloRPT>";
		ArrayList<RptNodoSpc> nspcList = new ArrayList<RptNodoSpc>(); 
		
		for (int i=0;i<in.getIntegraGTWSinc_MultiEnte().length;i++){
			request = in.getIntegraGTWSinc_MultiEnte(i);
			//inizio LP PG210130 Step04
			//HashMap<RptNodoSpc,IntegraGTWSincResponse> resp_Xml = multiCarrello(request,configuration);
			HashMap<RptNodoSpc,IntegraGTWSincResponse> resp_Xml = multiCarrello(request, configuration, bMultiBeneficiario, codiceContestoPagamento, codiceIUV);
			//fine  LP PG210130 Step04

			Set<Map.Entry<RptNodoSpc,IntegraGTWSincResponse>> set = resp_Xml.entrySet();
			for (Map.Entry<RptNodoSpc,IntegraGTWSincResponse> me : set){
//				contiene 1 solo elemento
				RptNodoSpc nspc = new RptNodoSpc(); 
				nspc = me.getKey();
				response = me.getValue();
				
				nspcList.add(nspc);
				xml = nspc.getRpt();
				//inizio LP PG210130 Step04
				//TODO: Devo determinare in caso di multibenefeciario il codiceiuv da passare alla rpt successiva
				//      da verificare se va bene determinarli in questo modo....
				//      Ricordati in fase di costruzione sul carrello web bisogna che il primo item sia sempre quello relativo all'ente principale 
				if(i == 0 && bMultiBeneficiario) {
					codiceIUV = nspc.getCodiceIuv();
					codiceContestoPagamento = nspc.getCodContestoPagamento();
				}
				//fine LP PG210130 Step04
			}
			
			if (!response.getResponse().getRetCode().toString().equals("00")){
				System.out.println("     Elaborazione Multi Carrello INTERROTTA!");
				return response;
			} else {
				xml = xml.replace(init,"");
				if (i==0){
					xml = carrello.concat(xml);
					//primo elemento aggiunge in.getIntegraGTWSinc_MultiEnte().length
				}
				if (i==(in.getIntegraGTWSinc_MultiEnte().length-1)){
					//ultimo elemento 
					xml = xml.concat(fine);
				}
				rptXml_Totale = rptXml_Totale+xml;
			}
		}
		rptXml_Totale = init+rptXml_Totale;

		System.out.println("   xmlRpt_Totale = "+rptXml_Totale);
		InviaCarrelloRTPResponse inviaCarrelloRTPResponse = null;
		try {
			//inizio LP PG1900XX_001
			String nodospcGestore = "E";
			if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore") != null) {
				nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore");
			}
			if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta) != null) {
				nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta);
			}
			if(nodospcGestore.equals("E")) {
			//fine LP PG1900XX_001
				inviaCarrelloRTPResponse = call_EasyBridge(rptXml_Totale,configuration);
			//inizio LP PG1900XX_001
			} else {
				inviaCarrelloRTPResponse = call_MyBridge(rptXml_Totale, configuration);
			}
			//fine LP PG1900XX_001
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CommonsSOAPBindingStub binding = null;
		try {
			binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
		} catch (Exception ex) {
			error(ex.getMessage(), ex);
			return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), "","","");
		}
		String apiRedirect="";		
		String idsessionPSP = "";
		// qui aggiorna TUTTE le RPT dopo la chiamata a EASYBRIDGE

//PG180070 - 22/8/2018 - INIZIO - Gestione carrello multiente per PSP
//		if (inviaCarrelloRTPResponse.getFault() != null && 
//			inviaCarrelloRTPResponse.getFault().getFaultCode() != null && 
//			!inviaCarrelloRTPResponse.getFault().getFaultCode().equals("")){
		if (!inviaCarrelloRTPResponse.getEsitoComplessivoOperazione().equals("OK")){
			System.out.println("EasyBridge KO");
			com.esed.easybridge.webservice.dati.FaultBean[] fbean = inviaCarrelloRTPResponse.getListaErroriRPT();
			// EASYBRIDGE ko
//			String err_msg = "Fault code: " + inviaCarrelloRTPResponse.getFault().getFaultCode() + " Fault String: " + inviaCarrelloRTPResponse.getFault().getFaultString() + "Fault Description: " + inviaCarrelloRTPResponse.getFault().getDescription();
//			error(err_msg);
			try {
//				for (int k=0;k<nspcList.size();k++){
//					RptNodoSpc rptNodoSpcToUpdate = nspcList.get(0); //new RptNodoSpc();
//					String errMsg = "KO:" + inviaCarrelloRTPResponse.getListaErroriRPT().getFaultCode();
//					if(errMsg.length()>20)
//						errMsg = errMsg.substring(0, 20);
//					rptNodoSpcToUpdate.setRptEsito(errMsg);
//					UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
//					binding.updateRptNodoSpc(inUpdateRPT );
//				}
				String errMsg = "KO:";
				//inizio LP PG210130 Step04
				if (fbean != null) {
					//TODO forse non si deve ciclare ma prendere l'errore k per la rpt k ?
					for (int s = 0; s < fbean.length; s++){
						errMsg = errMsg + fbean[s].getFaultCode();
						if(errMsg.length() > 20)
							errMsg = errMsg.substring(0, 20);
					}
				}
				//fine LP PG210130 Step04
				for (int k=0;k<nspcList.size();k++){
					//inizio LP PG210130 Step04
					//TODO. Perche' qui si metteva solo sulla prima RPT l'esito a KO ? 
					//RptNodoSpc rptNodoSpcToUpdate = nspcList.get(0); //new RptNodoSpc();
					//if (fbean!=null) {
					//	for (int s=0;s<fbean.length;s++){
					//		errMsg = errMsg + fbean[s].getFaultCode();
					//		if(errMsg.length()>20)
					//			errMsg = errMsg.substring(0, 20);
					//	}
					//}
					RptNodoSpc rptNodoSpcToUpdate = nspcList.get(k);
					//fine LP PG210130 Step04
//PG180070 - 22/8/2018 - FINE
					rptNodoSpcToUpdate.setRptEsito(errMsg);
					UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
					binding.updateRptNodoSpc(inUpdateRPT );
				}
				
				
				AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiave_transazione, TRA_FAILED_STATE, "", "", Calendar.getInstance());
				info("Aggiornamento esito transazione in stato FALLITA: " + chiave_transazione);
				binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);	//TODO da verificare
				//inizio LP PG210130 Step04
				if(bMultiBeneficiario) {
					chiave_transazione = in.getIntegraGTWSinc_MultiEnte(1).getTransazione().getChiaveTransazione();
					if(chiave_transazione.contains("||")){
						chiave_transazione = in.getIntegraGTWSinc_MultiEnte(1).getTransazione().getChiaveTransazione().split("\\|\\|")[0];
					}
					aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiave_transazione, TRA_FAILED_STATE, "", "", Calendar.getInstance());
					info("Aggiornamento esito seconda transazione per pagamento multibeneficiario in stato FALLITA: " + chiave_transazione);
					binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
				}
				//fine LP PG210130 Step04
			}
			catch (Exception e) {
				warn("Aggiornamento Esito KO RPT non riuscito");
				error(e.getMessage(), e);
				return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2,"Aggiornamento Esito KO RPT non riuscito"), "","","");
			}
		
//PG180070 - 22/8/2018 - INIZIO - Gestione carrello multiente per PSP
//			String retMessage = inviaCarrelloRTPResponse.getFault().getFaultCode();
//			if (inviaCarrelloRTPResponse.getFault().getFaultString()!=null && !inviaCarrelloRTPResponse.getFault().getFaultString().trim().equals("")) {
//				retMessage = retMessage.concat(" - ").concat(inviaCarrelloRTPResponse.getFault().getFaultString()));
//			}
			String retMessage = "";
			//inizio LP PAGONET-318
			if(dbSchemaCodSocieta.equals("000RM")
				|| dbSchemaCodSocieta.equals("000LP")) {
				if (inviaCarrelloRTPResponse!=null && inviaCarrelloRTPResponse.getFault()!=null && (inviaCarrelloRTPResponse.getFault().getDescription() != null && inviaCarrelloRTPResponse.getFault().getDescription().trim().length() > 0)) {
					retMessage = inviaCarrelloRTPResponse.getFault().getDescription();
				}
				if (fbean != null) {
					for (int s = 0; s < fbean.length; s++) {
						if(retMessage.length() > 0)
							retMessage += " - ";
						retMessage += fbean[s].getDescription();
					}
					if(retMessage.length() > 1024)
						retMessage = retMessage.substring(0, 1024);
				}
				if(retMessage.length() <= 0)
					retMessage = "Si è verificato un errore durante l'integrazione con il gateway.";
			} else
			//fine LP PAGONET-318
			if (fbean!=null) {
				for (int s=0;s<fbean.length;s++){
					retMessage = retMessage.concat(fbean[s].getDescription()).concat(" - ");
				}
				//inizio LP PAGONET-318
				if(retMessage.length() > 1024)
					retMessage = retMessage.substring(0, 1024);
				//fine LP PAGONET-318
			} else 
				retMessage = "Si è verificato un errore durante l'integrazione con il gateway.";
			
//PG180070 - 22/8/2018 - FINE
			return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, retMessage), "","","");

		} else {
			System.out.println("EasyBridge OK");
			// EASYBRIDGE SUCCESFULL
			try {
				for (int k=0;k<nspcList.size();k++){
					//inizio LP PG210130 Step04
					//RptNodoSpc rptNodoSpcToUpdate = nspcList.get(0); //new RptNodoSpc();
					//TODO. Perche' qui si metteva solo sulla prima RPT l'esito a OK ? 
					RptNodoSpc rptNodoSpcToUpdate = nspcList.get(k);
					//fine LP PG210130 Step04
					rptNodoSpcToUpdate.setRptEsito("OK");
					UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
					binding.updateRptNodoSpc(inUpdateRPT );
				}
			}
			catch (Exception e) {
				warn("Aggiornamento Esito OK RPT non riuscito");
			}

		
			//Leggere l'url restituita per la redirect
			apiRedirect = inviaCarrelloRTPResponse.getUrl()+lingua;
			if(!apiRedirect.trim().equals("")) {
				info("Url Redirect: " + apiRedirect);
				if(apiRedirect.indexOf("idSession=")>-1) {
					idsessionPSP = apiRedirect.substring(apiRedirect.indexOf("idSession=")+ 10);
				}
			} else {
				info("Redirect non previsto per il pagamento NODO selezionato ");
			}
		}
		
		response = new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"), apiRedirect,GatewaysTypeKeys.NODOSPC.format(),idsessionPSP);

		
		System.out.println("Fine Elaborazione Multi Carrello");

		return response;
		
	}

	//inizio LP PG1900XX_001
    private Calendar Now(java.util.Date d) {
    	GregorianCalendar gcalCurrenDate = new GregorianCalendar();
    	gcalCurrenDate.setTime(d);
    	Calendar calDateNow = Calendar.getInstance();
    	calDateNow.set(Calendar.YEAR, gcalCurrenDate.get(Calendar.YEAR));
    	calDateNow.set(Calendar.MONTH, gcalCurrenDate.get(Calendar.MONTH));
    	calDateNow.set(Calendar.DATE, gcalCurrenDate.get(Calendar.DATE));
    	calDateNow.set(Calendar.HOUR_OF_DAY, gcalCurrenDate.get(Calendar.HOUR_OF_DAY));
    	calDateNow.set(Calendar.MINUTE, gcalCurrenDate.get(Calendar.MINUTE));
    	calDateNow.set(Calendar.SECOND, gcalCurrenDate.get(Calendar.SECOND));
    	calDateNow.set(Calendar.MILLISECOND, gcalCurrenDate.get(Calendar.MILLISECOND));
    	return calDateNow;
    }

    private String getTagToXmlRPT(String xmlin, String tag) {
    	String init = "<" + tag + ">";
    	String end = "</" + tag + ">";
    	int inizio = xmlin.indexOf(init); 
    	int fine = xmlin.indexOf(end); 
		if(inizio != -1 && fine != -1) {
			String id = xmlin.substring(inizio + init.length(), fine);
			return id;
		}
    	return null;
	}

    private String getIdDominioToXmlRPT(String xmlin) {
    	return getTagToXmlRPT(xmlin, "identificativoDominio");
	}
    
    private String getCuteCuteToXmlRPT(String xmlin) {
    	return getTagToXmlRPT(xmlin, "cutecute");
	}

    private String getVersioneToXmlRPT(String xmlin) {
    	return getTagToXmlRPT(xmlin, "versioneOggetto");
	}
    
    private boolean verificaVersione(String xmlout, String ver)
    {
		String versione = getVersioneToXmlRPT(xmlout);
		if(versione != null && versione.equals(ver)) {
			xmlout = xmlout.replace("<versioneOggetto>" + versione + "</versioneOggetto>","");
			if(getVersioneToXmlRPT(xmlout) != null) {
				return false;
			}
			return true;
		}
		return false;
    }

    private String ParserToMybrigde(String xmlin, String ver) {
		//Bisogna eseguire un parser del xml e modificarlo per passarlo a MyBridge
		//Vanno sostituiti dei pezzi di xml
    	String xmlout = xmlin.replace("\r\n","").replace("\t","");
		//<?xml version="1.0" encoding="UTF-8"?>
    	//   ==> '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><InviaCarrelloRPTRichiesta xmlns="http://jcitygov.informatica.maggioli.it/pagopa/payservice/pdp/connector/jppapdp/external/schema/1_0">'
		xmlout = xmlout.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		                       ,"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><InviaCarrelloRPTRichiesta xmlns=\"http://jcitygov.informatica.maggioli.it/pagopa/payservice/pdp/connector/jppapdp/external/schema/1_0\">");
		//<carrelloRPT>	==> '<carrelloRTP>'
		xmlout = xmlout.replace("<carrelloRPT>","<carrelloRTP>");
		//<cutecute>XXXXX</cutecute>      ==> ''
		String cutecute = getCuteCuteToXmlRPT(xmlout);
		//String oldcutecute = cutecute; 
		if(cutecute != null) {
			xmlout = xmlout.replace("<cutecute>" + cutecute + "</cutecute>","");
			if(getCuteCuteToXmlRPT(xmlout) != null) {
				//non dovrebbe mai succedere......
				return null;
			}
		}
		//<RPT xmlns="http://www.digitpa.gov.it/schemas/2011/Pagamenti/"> ==> ''
		xmlout = xmlout.replace("<RPT xmlns=\"http://www.digitpa.gov.it/schemas/2011/Pagamenti/\">","");
		//<versioneOggetto>6.2.0</versioneOggetto> ==> ''
		xmlout = xmlout.replace("<versioneOggetto>" + ver + "</versioneOggetto>","");
		//<dominio>	==> ''
		xmlout = xmlout.replace("<dominio>","");
		//Per ogni identificativoDominio
		//<identificativoDominio>02770891204</identificativoDominio> ==> '<idDominio>02770891204</idDominio>' 
		String idDominio = getIdDominioToXmlRPT(xmlout);
		while(idDominio != null) {
			xmlout = xmlout.replace("<identificativoDominio>" + idDominio + "</identificativoDominio>"
					               ,"<idDominio>" + idDominio + "</idDominio>");
			idDominio = getIdDominioToXmlRPT(xmlout);
		}
		//<identificativoStazioneRichiedente>02770891204_01</identificativoStazioneRichiedente> ==> ''
		String identificativoStazioneRichiedente = getTagToXmlRPT(xmlout, "identificativoStazioneRichiedente");
		while(identificativoStazioneRichiedente != null) {
			xmlout = xmlout.replace("<identificativoStazioneRichiedente>" + identificativoStazioneRichiedente + "</identificativoStazioneRichiedente>","");
			identificativoStazioneRichiedente = getTagToXmlRPT(xmlout, "identificativoStazioneRichiedente");
		}
		//</dominio>	==> ''
		xmlout = xmlout.replace("</dominio>","");
		//<identificativoMessaggioRichiesta>20180725155258</identificativoMessaggioRichiesta>       ==> ''
		String identificativoMessaggioRichiesta = getTagToXmlRPT(xmlout, "identificativoMessaggioRichiesta");
		while(identificativoMessaggioRichiesta != null) {
			xmlout = xmlout.replace("<identificativoMessaggioRichiesta>" + identificativoMessaggioRichiesta + "</identificativoMessaggioRichiesta>","");
			identificativoMessaggioRichiesta = getTagToXmlRPT(xmlout, "identificativoMessaggioRichiesta");
		}
		//<dataOraMessaggioRichiesta>2018-07-25T15:52:58</dataOraMessaggioRichiesta>                ==> ''
		String dataOraMessaggioRichiesta = getTagToXmlRPT(xmlout, "dataOraMessaggioRichiesta");
		while(dataOraMessaggioRichiesta != null) {
			xmlout = xmlout.replace("<dataOraMessaggioRichiesta>" + dataOraMessaggioRichiesta + "</dataOraMessaggioRichiesta>","");
			dataOraMessaggioRichiesta = getTagToXmlRPT(xmlout, "dataOraMessaggioRichiesta");
		}
		//inizio LP BUG rimanevano sezioni <enteBeneficiario>........</enteBeneficiario> vanno rimosse
		//<enteBeneficiario>........</enteBeneficiario> ==> ''
		String temp = getTagToXmlRPT(xmlout, "enteBeneficiario");
		if(temp != null) {
			xmlout = xmlout.replace("<enteBeneficiario>" + temp + "</enteBeneficiario>","");
			temp = getTagToXmlRPT(xmlout, "enteBeneficiario");
			while(temp != null) {
				xmlout = xmlout.replace("<enteBeneficiario>" + temp + "</enteBeneficiario>","");
				temp = getTagToXmlRPT(xmlout, "enteBeneficiario");
			}
		}
		//fine LP BUG rimanevano sezioni <enteBeneficiario>........</enteBeneficiario> vanno rimosse
		//inizio LP BUG rimanevano sezioni <firmaRicevuta>........</firmaRicevuta> vanno rimosse
		//<firmaRicevuta>........</firmaRicevuta> ==> ''
		temp = getTagToXmlRPT(xmlout, "firmaRicevuta");
		if(temp != null) {
			xmlout = xmlout.replace("<firmaRicevuta>" + temp + "</firmaRicevuta>","");
			temp = getTagToXmlRPT(xmlout, "firmaRicevuta");
			while(temp != null) {
				xmlout = xmlout.replace("<firmaRicevuta>" + temp + "</firmaRicevuta>","");
				temp = getTagToXmlRPT(xmlout, "firmaRicevuta");
			}
		}
		//fine LP BUG rimanevano sezioni <firmaRicevuta>........</firmaRicevuta> vanno rimosse
		//........ IL RESTO INVARIATO				
		//</RPT> ==> ''
		xmlout = xmlout.replace("</RPT>","");
		//</carrelloRPT> ==> '</carrelloRTP>'
		xmlout = xmlout.replace("</carrelloRPT>","</carrelloRTP>");
		//IN FONDO ALLA STRINGA aggiungere '</InviaCarrelloRPTRichiesta>'
		xmlout += "</InviaCarrelloRPTRichiesta>"; 
    	return xmlout;
    }
    
	public InviaCarrelloRTPResponse call_MyBridge(String xml, PropertiesTree configuration) throws RemoteException, FaultType, MalformedURLException, ServiceException
	{
		String ver = "6.2.0";
		info("INIZIO CHIAMATA MyBridge");
		
		String mybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge");
		if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge." + dbSchemaCodSocieta) != null) {
			mybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge." + dbSchemaCodSocieta);
		}

		InviaCarrelloRTPResponse inviaCarrelloRTPResponse = new InviaCarrelloRTPResponse();
		String idDominio = getIdDominioToXmlRPT(xml);
		if(idDominio == null) {
			inviaCarrelloRTPResponse.setEsitoComplessivoOperazione("K0");
			List<com.esed.easybridge.webservice.dati.FaultBean> listaErroriRPT = new ArrayList<com.esed.easybridge.webservice.dati.FaultBean>();
			com.esed.easybridge.webservice.dati.FaultBean ff = new com.esed.easybridge.webservice.dati.FaultBean();
			ff.setFaultCode("PAA_SEMANTICA");
			ff.setFaultString("Errore semantico.");
			ff.setId("");
			ff.setDescription("Errore idDominio non definito");
			ff.setSerial(1);
			/*
			 altri valori fault a "" ? 
			 */
			listaErroriRPT.add(ff);
			inviaCarrelloRTPResponse.setListaErroriRPT(listaErroriRPT.toArray(new com.esed.easybridge.webservice.dati.FaultBean[listaErroriRPT.size()]));
			return inviaCarrelloRTPResponse;
		}
		boolean testVer = verificaVersione(xml, ver);
		if(!testVer) {
			//Se xml che arriva non ha versione '6.2.0' la modifico in '1.0'
			//la differenza dovrebbe essere solo nella presenza o meno del
			//nodo soggettoVersante che è comunque 'opzionale'
			//(poi nel parse si rimuove in ogni caso il nodo versioneOggetto)
			testVer = verificaVersione(xml, "1.0");
			if(testVer) {
				ver = "1.0";
			} else {
				inviaCarrelloRTPResponse.setEsitoComplessivoOperazione("K0");
				List<com.esed.easybridge.webservice.dati.FaultBean> listaErroriRPT = new ArrayList<com.esed.easybridge.webservice.dati.FaultBean>();
				com.esed.easybridge.webservice.dati.FaultBean ff = new com.esed.easybridge.webservice.dati.FaultBean();
				ff.setFaultCode("PAA_SEMANTICA");
				ff.setFaultString("Errore semantico.");
				ff.setId(idDominio);
				ff.setDescription("Errore versione xml RPT diversa da '1.0' e da '6.2.0'");
				ff.setSerial(1);
				/*
				 altri valori fault a "" ? 
				 */
				listaErroriRPT.add(ff);
				inviaCarrelloRTPResponse.setListaErroriRPT(listaErroriRPT.toArray(new com.esed.easybridge.webservice.dati.FaultBean[listaErroriRPT.size()]));
				return inviaCarrelloRTPResponse;
			}
		}
		String xmlMybridge = ParserToMybrigde(xml, ver);
		if(xmlMybridge == null) {
			inviaCarrelloRTPResponse.setEsitoComplessivoOperazione("K0");
			List<com.esed.easybridge.webservice.dati.FaultBean> listaErroriRPT = new ArrayList<com.esed.easybridge.webservice.dati.FaultBean>();
			com.esed.easybridge.webservice.dati.FaultBean ff = new com.esed.easybridge.webservice.dati.FaultBean();
			ff.setFaultCode("PAA_SEMANTICA");
			ff.setFaultString("Errore semantico.");
			ff.setId(idDominio);
			ff.setDescription("Errore sono presenti RPT con diversi cutecute");
			ff.setSerial(1);
			/*
			 altri valori fault a "" ? 
			 */
			listaErroriRPT.add(ff);
			inviaCarrelloRTPResponse.setListaErroriRPT(listaErroriRPT.toArray(new com.esed.easybridge.webservice.dati.FaultBean[listaErroriRPT.size()]));
			return inviaCarrelloRTPResponse;
		}

		String xmlDettaglioRichiesta = "";
		JppaPdpExternalFacetServiceLocator locator = new JppaPdpExternalFacetServiceLocator();
		JppaPdpExternalServicesEndpoint service = null;
		CtRispostaStandard res = null; 
		CtRichiestaStandard in = new CtRichiestaStandard();
		java.util.Date d = new java.util.Date();
		Calendar dataOperazione = Now(d);
		
		TimeZone tm = TimeZone.getDefault();
		System.out.println("MyBridge JppapdpExternal TimeZone: " + tm.getDisplayName() + " ID: " + tm.getID());

		in.setDataOperazione(dataOperazione);
		in.setVersion("1.0");
		//inizio LP PG2000XX_005
    	//in.setIdentificativoDominio(idDominio);
		////Inserisco il carattere ";" alla fine x marcare provenienza da Gateways
    	in.setIdentificativoDominio(idDominio + ";");
		//fine LP PG2000XX_005
    	/*
		service = locator.getJppaPdpExternalServicesPort(new URL("http://cowsesed01.seda.intra:8080/myBridge/JppaPdpExternalServicesPort"));
		*/
		service = locator.getJppaPdpExternalServicesPort(new URL(mybridgeUrl + "/JppaPdpExternalServicesPort"));
		System.out.println("MyBridge JppapdpExternal url: " + mybridgeUrl + "/JppaPdpExternalServicesPort");
		
		xmlDettaglioRichiesta = xmlMybridge;
		in.setOperazione(StOperazione.InviaCarrelloRPT);
		in.setXmlDettaglioRichiesta(xmlDettaglioRichiesta);
		
		res = service.inviaCarrelloRPT(in);
		System.out.println("Data inizio " + dataOperazione.getTime() + " " + dataOperazione.toString());
		System.out.println("Esito complessivo " + res.getOperazione().getValue() + ": " + res.getEsito().toString());
		System.out.println("Data fine " + res.getDataOperazione().getTime() + " " + res.getDataOperazione().toString());
		CtMessaggiMessaggio[] ct = res.getMessaggi();
		if(res.getEsito().toString().equals(StEsito._ERROR)) {
			inviaCarrelloRTPResponse.setEsitoComplessivoOperazione("KO");
			if(ct != null) {
				List<com.esed.easybridge.webservice.dati.FaultBean> listaErroriRPT = new ArrayList<com.esed.easybridge.webservice.dati.FaultBean>();
				for(int i = 0; i < ct.length; i++) {
					System.out.println("Messaggio: " + ct[i].getCodice() + " - " + ct[i].getDescrizione());
					if(!ct[i].getCodice().equals("")) {
						com.esed.easybridge.webservice.dati.FaultBean ff = new com.esed.easybridge.webservice.dati.FaultBean();
						ff.setFaultCode(ct[i].getCodice());
						ff.setFaultString(ct[i].getDescrizione());
						ff.setId(idDominio);
						ff.setDescription(ct[i].getDescrizione());
						ff.setSerial(1);
						/*
						 altri valori fault a "" ? 
						 */
						listaErroriRPT.add(ff);
					}
				}
				if(listaErroriRPT.size() > 0) {
					inviaCarrelloRTPResponse.setListaErroriRPT(listaErroriRPT.toArray(new com.esed.easybridge.webservice.dati.FaultBean[listaErroriRPT.size()]));
				}
			}
		} else {
			System.out.println("XmlDettaglioRisposta:");
			System.out.println(res.getXmlDettaglioRisposta());
			String xmlDettaglioRisposta = res.getXmlDettaglioRisposta();
			//prima sostituisco &lt; e &gt; 
			xmlDettaglioRisposta = xmlDettaglioRisposta.replace("&lt;","<").replace("&gt;",">");
			/*
				<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
				<InviaCarrelloRPTRisposta xmlns="http://jcitygov.informatica.maggioli.it/pagopa/payservice/pdp/connector/jppapdp/external/schema/1_0">
				    <outcome>
				        <code>OK</code>
				    </outcome>
				    <dataResult>
				        <identificativoUnivocoVersamento>RF72000000000000000006148</identificativoUnivocoVersamento>
				        <pspSupportaRedirect>1</pspSupportaRedirect>
				        <redirectURL>https://wisp2.pagopa.gov.it/wallet/welcome?idSession=be51c1c2-1b7f-48ad-bc0b-dd4e902ecf0a</redirectURL>
				    </dataResult>
				</InviaCarrelloRPTRisposta>
				
				<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
				<InviaCarrelloRPTRisposta xmlns="http://jcitygov.informatica.maggioli.it/pagopa/payservice/pdp/connector/jppapdp/external/schema/1_0">
					<outcome>
						<code>KO</code>
						<messaggi>
							<code>01</code>
							<descrizione>UNO</descrizione>
						</messaggi>
						<messaggi>
							<code>02</code>
							<descrizione>DUE</descrizione>
						</messaggi>
					</outcome>
				</InviaCarrelloRPTRisposta>
				

			*/
			//prima sostituisco 'xmlns....' con '' 
			xmlDettaglioRisposta = xmlDettaglioRisposta.replace("<InviaCarrelloRPTRisposta xmlns=\"http://jcitygov.informatica.maggioli.it/pagopa/payservice/pdp/connector/jppapdp/external/schema/1_0\">"
				                                               ,"<InviaCarrelloRPTRisposta>");

 			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
	
			DocumentBuilder builder = null;
			try {
				builder = domFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document doc = null;
			try {
			    doc = builder.parse(new InputSource(new StringReader(xmlDettaglioRisposta)));
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doc.getDocumentElement().normalize();
			
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			Node nCode = null;
	        try {
	        	nCode = (Node)(xpath.evaluate("/InviaCarrelloRPTRisposta/outcome/code", doc, XPathConstants.NODE));
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String code = nCode.getTextContent();
			if(!code.equals("OK")) {
				inviaCarrelloRTPResponse.setEsitoComplessivoOperazione("KO");
				NodeList messNodeList = doc.getElementsByTagName("messaggi");
				List<com.esed.easybridge.webservice.dati.FaultBean> listaErroriRPT = new ArrayList<com.esed.easybridge.webservice.dati.FaultBean>();
		        for(int i = 0; i < messNodeList.getLength(); i++) {
		        	Node messNode = messNodeList.item(i);
		        	
		        	if (messNode.getNodeType() == Node.ELEMENT_NODE) {
		        		Element eElement = (Element) messNode;
		        		
			    		String codeM = "";
		        		if(eElement.getElementsByTagName("code") != null) {
		        			codeM =((Element)eElement.getElementsByTagName("code").item(0)).getTextContent();
		        		}
			    		String desc = "";
		        		if(eElement.getElementsByTagName("descrizione") != null) {
		        			desc =((Element)eElement.getElementsByTagName("descrizione").item(0)).getTextContent();
		        		}
		        		if(!codeM.equals("")) {
							com.esed.easybridge.webservice.dati.FaultBean ff = new com.esed.easybridge.webservice.dati.FaultBean();
							ff.setFaultCode(codeM);
							ff.setFaultString(desc);
							ff.setId(idDominio);
							ff.setDescription(desc);
							ff.setSerial(1);
							/*
							 altri valori fault a "" ? 
							 */
							listaErroriRPT.add(ff);
		        		}
		        	}
		        }
		        if(listaErroriRPT.size() > 0) {
		        	inviaCarrelloRTPResponse.setListaErroriRPT(listaErroriRPT.toArray(new com.esed.easybridge.webservice.dati.FaultBean[listaErroriRPT.size()]));
		        }
			} else {
				inviaCarrelloRTPResponse.setEsitoComplessivoOperazione("OK");
				Node nPspSupportaRedirect = null;
				Node nRedirectURL = null;
				String pspSupportaRedirect = null;
				String redirectURL = null;
		        try {
		        	nPspSupportaRedirect = (Node)(xpath.evaluate("/InviaCarrelloRPTRisposta/dataResult/pspSupportaRedirect", doc, XPathConstants.NODE));
		        	pspSupportaRedirect = nPspSupportaRedirect.getTextContent();
		        	if(pspSupportaRedirect.equals("1")) {
		        		nRedirectURL = (Node)(xpath.evaluate("/InviaCarrelloRPTRisposta/dataResult/redirectURL", doc, XPathConstants.NODE));
		        		redirectURL = nRedirectURL.getTextContent();
		        	}
				} catch (XPathExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(pspSupportaRedirect.equals("1")) {
					inviaCarrelloRTPResponse.setUrl(redirectURL);
				}
			}
		}
		info("ESITO CHIAMATA MyBridge = " + inviaCarrelloRTPResponse.getEsitoComplessivoOperazione());
		return inviaCarrelloRTPResponse;
	}
	//fine LP PG1900XX_001

	public InviaCarrelloRTPResponse call_EasyBridge(String xml,PropertiesTree configuration) throws RemoteException, FaultType, MalformedURLException, ServiceException {
		
//		String nodoSpcUrl = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospc");
//		if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta)!=null)
//			nodoSpcUrl = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta);
		
		String easybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge");
		if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge." + dbSchemaCodSocieta)!=null)
			easybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge." + dbSchemaCodSocieta);

		System.out.println("easybridgeUrl: " + easybridgeUrl);
		EasybridgeLocator easybridgeLocator = new EasybridgeLocator();
		EasybridgeInterface service = easybridgeLocator.getEasybridgePort(new URL(easybridgeUrl + "/EasybridgePort")); 
		
//		EasybridgeSOAPBindingStub _stub = (EasybridgeSOAPBindingStub)pagamentiTelematiciRPT;
		 
//		String password = "";
//		if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta)!=null)
//			password = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta);
//		
//		String username = "";
//		if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta)!=null)
//			username = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta);
//			
//		_stub.setUsername(username);
//		_stub.setPassword(password);
//		
//		HandlerRegistry hr = easybridgeLocator.getHandlerRegistry();
//		QName  portName = _stub.getPortName();
//		List handlerChain = hr.getHandlerChain(portName);
//		
//		HandlerInfo hi = new HandlerInfo();
//		hi.setHandlerClass(WsNodoRpcHandler.class);
//		handlerChain.add(hi);
//
//		boolean proxyEnabled = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyenabled").equals("1");
//		String proxyHost = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyhost");
//		String proxyPort = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyport");
//		String proxyUser = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyuser");
//		String proxyPassword = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxypassword");
//
//		if(proxyEnabled) {
//			System.getProperties().put("http.proxyHost", proxyHost);
//			System.getProperties().put("http.nonProxyHosts", "localhost");
//			System.getProperties().put("http.proxyPort", proxyPort);
//			System.getProperties().put("http.proxyUser", proxyUser);
//			System.getProperties().put("http.proxyPassword", proxyPassword);
//		}
		
//		yann
		java.util.Date dataInizio = new java.util.Date();
        Timestamp dIni = new Timestamp(dataInizio.getTime());
       
        
		InviaCarrelloRTPRequest in = new InviaCarrelloRTPRequest();
		in.setXmlCarrelloRTP(xml);
		info("INIZIO CHIAMATA EasyBridge");
		InviaCarrelloRTPResponse inviaCarrelloRTPResponse = service.inviaCarrelloRTP(in);
		
		info("ESITO CHIAMATA EasyBridge = " + inviaCarrelloRTPResponse.getEsitoComplessivoOperazione());
        
		try {
			
			String xmlCarrelloRPT = new String(in.getXmlCarrelloRTP().getBytes(),"UTF-8");
			EasybridgeSOAPBindingStub stub = (EasybridgeSOAPBindingStub)service;
			String xmlOut = stub._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString().toString();
			
	    	String erroreFault = "";
	    	String identificativoDominio = "";
			String identificativoUnivocoVersamento = "";

			java.util.Date dataFine = new java.util.Date();
	        Timestamp dFin = new Timestamp(dataFine.getTime());

	    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);

			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse( new ByteArrayInputStream(xmlCarrelloRPT.getBytes()));
			doc.getDocumentElement().normalize();
			
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

	        Node cuteCuteNode = (Node)(xpath.evaluate("/carrelloRPT/cutecute", doc, XPathConstants.NODE));
	        String cuteCute = cuteCuteNode != null ?  cuteCuteNode.getTextContent() : "";
	        
	        NodeList rptNodeList = doc.getElementsByTagName("RPT");
	        
	        for(int i = 0; i < rptNodeList.getLength(); i++) {
	        	Node rptNode = rptNodeList.item(i);
	        	
	        	if (rptNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element eElement = (Element) rptNode;
	        		
	        		if(eElement.getElementsByTagName("datiVersamento") != null) {
	        			identificativoUnivocoVersamento =((Element)eElement.getElementsByTagName("datiVersamento").item(0)).getElementsByTagName("identificativoUnivocoVersamento").item(0).getTextContent();
	        
	        		}
	        		if(eElement.getElementsByTagName("dominio") != null) {
	        			identificativoDominio =((Element)eElement.getElementsByTagName("dominio").item(0)).getElementsByTagName("identificativoDominio").item(0).getTextContent();
	        		}
		    
	        	}
	        }

			
			if (inviaCarrelloRTPResponse.getFault() != null && inviaCarrelloRTPResponse.getFault().getFaultCode() != null) {
	    		erroreFault = inviaCarrelloRTPResponse.getFault().getFaultCode() + ": " 
		    			+ inviaCarrelloRTPResponse.getFault().getDescription() + ", " 
		    			+ inviaCarrelloRTPResponse.getFault().getFaultString();
	    	}
			LogPap logPap = new LogPap();
	        logPap.setCodiceUtente(cuteCute);
	        logPap.setIdDominio(identificativoDominio);
	        logPap.setIuv(identificativoUnivocoVersamento);
	        logPap.setXmlRequest(xml);
	        logPap.setXmlResponse(xmlOut);
	        logPap.setDataInizioChiamata(dIni);
	        logPap.setDataFineChiamata(dFin);
	        logPap.setErrore(erroreFault);
	        logPap.setEsito(inviaCarrelloRTPResponse.getEsitoComplessivoOperazione() != null ? inviaCarrelloRTPResponse.getEsitoComplessivoOperazione().toString() : "");
	        logPap.setOperazione("EasyBridge - gateways");
	        logPap.setDbSchemaCodSocieta(dbSchemaCodSocieta);
	        

		    try {
		        LoggerUtil PapLogger = new LoggerUtil();
		        info("inizio salvataggio log");
		        PapLogger.savePapLog(logPap, configuration);
		        info("fine salvataggio log");
		        
		    } catch (Exception e) {
		        
		        e.printStackTrace();
		        error("com.seda.payer.gateways.webservice.source/Gateways Errore in savePapLog/ inviaCarrelloRTP " + e.getMessage());
		    	
		    } 
		    
		} catch (Exception e) {
			 e.printStackTrace();
			 error("com.seda.payer.gateways.webservice.source/Gateways Errore in caricamento dati/ inviaCarrelloRTP " + e.getMessage());
		}

		return inviaCarrelloRTPResponse;
		
	}
	
	public PropertiesTree readConf() {
		
		String identificativoIntermediarioPA = "", identificativoStazioneIntermediarioPA="",identificativoDominio = "", passwordPsp = "";
		boolean proxyEnabled = false;
		String tipoFirma = "", password = "", username = "", nodoSpcUrl = "", proxyHost = "",proxyPort = "", proxyUser = "", proxyPassword = "";
		String cfPagatore = "", nomePagatore = "", datiSpecificiRiscossione = "";
		//inizio LP PG1900XX_001
		String nodospcGestore = "E";
		String nodospcTipologia= "N";
		//fine LP PG1900XX_001
		//inizio LP PG200360
		String stopErroreTassonomia = "N";
		String stopDiversaTassonomia = "N";
		String stopAssenzaTassonomia = "N";
		String sDefaultTassonomia = "";

		//inizio LP PG200360
		
//		PropertiesTree configuration;
		try {
			//Recupero dati dell'ente relativo al pagamento corrente
			
			//Leggo informazioni da file di config
//			try {
//				String rootPath = System.getenv("NODOSPCWS_CONFIG_ROOT");
//				if (rootPath == null){
//					throw new Exception("Variabile di sistema NODOSPCWS_CONFIG_ROOT non definita");
//				}
//				configuration = new PropertiesTree(rootPath);
//			} catch (Exception e) {
//				throw new Exception("Errore durante il caricamento del file di configurazione NODOSPCWS_CONFIG_ROOT. " + e.getMessage(),e);
//			}
			
			if (configuration != null){
				identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa");
				identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa");
				passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp");
				password = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword");
				username = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername");
				nodoSpcUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc");
				
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta)!=null)
					identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta);
				
				
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta)!=null)
					identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta);
				
				
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta)!=null)
					passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta);
				
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta)!=null)
					password = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta);
				
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta)!=null)
					username = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta);
				
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta)!=null)
					nodoSpcUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta);

				//inizio LP PG1900XX_001
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore") != null) {
					nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore");
				}
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta) != null) {
					nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta);
				}
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia") != null) {
					nodospcTipologia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia");
				}
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia." + dbSchemaCodSocieta) != null) {
					nodospcTipologia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia." + dbSchemaCodSocieta);
				}
				//fine LP PG1900XX_001
				
				info("nodoSpcUrl = "+ nodoSpcUrl);
				info("username = "+ username);
				info("password = "+ password);
//				info("passwordPsp = "+ passwordPsp);
				//inizio LP PG1900XX_001
				info("nodospcGestore = "+ nodospcGestore);
				info("nodospcTipologia = "+ nodospcTipologia);
				//fine LP PG1900XX_001
//				info("identificativoStazioneIntermediarioPA = "+ identificativoStazioneIntermediarioPA);
				info("identificativoIntermediarioPA = "+ identificativoIntermediarioPA);
				
				proxyEnabled = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyenabled").equals("1");
				proxyHost = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyhost");
				proxyPort = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyport");
				proxyUser = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyuser");
				proxyPassword = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxypassword");
				
				cfPagatore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".pagatoreanonimo.cf");
				nomePagatore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".pagatoreanonimo.nome");
				datiSpecificiRiscossione = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".datispecificiriscossione");

				System.out.println("Gateways - [riga 3006] - datiSpecificiRiscossione: "+datiSpecificiRiscossione);
				
				/* i seguenti campi sono da prendere dai dati di configurazione dell'ente */
				//cfEnteCreditore = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".cfentecreditore");
				//nomeEnteCreditore = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nomeentecreditore");
				//ibanAccredito = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".ibanaccredito");
				
				//inizio LP PG200360
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stoperroretassonomia." + dbSchemaCodSocieta) != null)
					stopErroreTassonomia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stoperroretassonomia." + dbSchemaCodSocieta).trim();
				info("stopErroreTassonomia = "+ stopErroreTassonomia);
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stopdiversatassonomia." + dbSchemaCodSocieta) != null)
					stopDiversaTassonomia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stopdiversatassonomia." + dbSchemaCodSocieta).trim();
				info("stopDiversaTassonomia = "+ stopDiversaTassonomia);
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stopassenzatassonomia." + dbSchemaCodSocieta) != null)
					stopAssenzaTassonomia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stopassenzatassonomia." + dbSchemaCodSocieta).trim();
				info("stopAssenzaTassonomia = "+ stopAssenzaTassonomia);
				if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".defaulttassonomia." + dbSchemaCodSocieta) != null)
					sDefaultTassonomia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".defaulttassonomia." + dbSchemaCodSocieta).trim();
				info("sDefaultTassonomia = '" + sDefaultTassonomia + "'");
				//fine LP PG200360
			}
			
		} catch (Exception e) {
			warn("Errore lettura configuration");
			error(e.getMessage(), e);
			return null;
		}
		return configuration;
		
	}
	

	//inizio LP PG210130 Step04
	//public HashMap<RptNodoSpc,IntegraGTWSincResponse> multiCarrello(IntegraGTWSincRequest in,PropertiesTree configuration) throws RemoteException, FaultType {
	public HashMap<RptNodoSpc,IntegraGTWSincResponse> multiCarrello(IntegraGTWSincRequest in, PropertiesTree configuration, boolean bMultiBeneficiario, String codiceContestoPagamentoExt, String codiceIUVExt) throws RemoteException, FaultType {
		String numeroAvviso = "";
	//fine LP PG210130 Step04

		HashMap<RptNodoSpc,IntegraGTWSincResponse> respXml = new HashMap<RptNodoSpc,IntegraGTWSincResponse>(); 
		IntegraGTWSincResponse resp = new IntegraGTWSincResponse(); 
		RptNodoSpc itemRpt = new RptNodoSpc();
		
		try {
//			// we retry gateway info
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), EMPTY_TOKEN),in.getImpTotTransazione()));
			
//			//salviamo il template e modifichiamo la url per gestire il bilinguismo
			if(in.getTransazione().getChiaveTransazione().contains("||")){
//				String DNS_IT=propertiesTree().getProperty(PropKeys.DNS_IT.format(dbSchemaCodSocieta));
//				String DNS_DE=propertiesTree().getProperty(PropKeys.DNS_DE.format(dbSchemaCodSocieta));
//				if(DNS_IT==null){
//					System.out.println("La chiave per il bilinguismo gatewaysWs.DNS_IT."+dbSchemaCodSocieta+" non è definita!");
//				}
//				if(DNS_DE==null){
//					System.out.println("La chiave per il bilinguismo gatewaysWs.DNS_DE."+dbSchemaCodSocieta+" non è definita!");
//				}
				template=in.getTransazione().getChiaveTransazione().split("\\|\\|")[1];
				System.out.println("Template: " + template);
				in.getTransazione().setChiaveTransazione(in.getTransazione().getChiaveTransazione().split("\\|\\|")[0]);
//				if(template.equalsIgnoreCase("bolzano")){
//					if(DNS_DE==null||DNS_IT==null){	
//						resp = new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "com.seda.payer.gateways.webservice- Configurazioni delle url di redirect per il bilingusimo mancanti o non configurate per il codice societa  "+dbSchemaCodSocieta), "","","");
//						respXml.put(itemRpt,resp);
//						return respXml;
//					}
//					generaMessageGTWResponse.setApiReturnURL(generaMessageGTWResponse.getApiReturnURL().replace(DNS_DE,DNS_IT));	
//					System.out.println("Return url: "+generaMessageGTWResponse.getApiReturnURL());
//				}else if(template.equalsIgnoreCase("bolzanoDE")){
//					if(DNS_DE!=null && DNS_IT!=null){
//						generaMessageGTWResponse.setApiReturnURL(generaMessageGTWResponse.getApiReturnURL().replace(DNS_IT,DNS_DE));
//					}
//					System.out.println("Return url: "+generaMessageGTWResponse.getApiReturnURL());
//					
//				}else{
//					System.out.println("Url non soggetta a bilinguismo");
//					System.out.println("Return url: "+generaMessageGTWResponse.getApiReturnURL());
//				}
			}
			

			if (GatewaysTypeKeys.parse(generaMessageGTWResponse.getTipoGateway()) == null){
				resp = new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore nella configurazione del gateway di pagamento: tipo gateway non riconosciuto"),"","","");
				respXml.put(itemRpt,resp);
				return respXml;
			}
			
			// 2013.10.02 CONTROLLO BLINDATURA
			System.out.println("Controllo Blindatura su Gateway - integraGTWSinc_MultiEnte");
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
			
			RecuperaTransazioneResponseType recuperaTransazioneResp = binding.recuperaTransazione(new RecuperaTransazioneRequestType(in.getTransazione().getChiaveTransazione()));
			Transazioni transazioni = recuperaTransazioneResp.getBeanTransazioni();
			if (transazioni.getBeanTransazioni().getFlag_esito_transazione().compareTo("1") == 0) {
				System.out.println("Errore: tx " + transazioni.getBeanTransazioni().getChiave_transazione() + " è già presente con con esito positivo, pagamento bloccato");
				resp = new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore: tx " + transazioni.getBeanTransazioni().getChiave_transazione() + " è già presente con con esito positivo, pagamento bloccato"),"","","");
				respXml.put(itemRpt,resp);
				return respXml;
			}
			
			String apiRedirect = "";
			String params = "";
			
			if (generaMessageGTWResponse.getTipoGateway().compareTo(GatewaysTypeKeys.NODOSPC.format()) == 0) {
				//Chiave transazione
				String chiaveTra = in.getTransazione().getChiaveTransazione();
				String cfPagatoreBollettino = "";
				String denominazionePagatoreBollettino = "";
				String ibanAccreditoBollettino = "";		//PG170070 GG
				String ibanAppoggioBollettino = "";		//PG170070 GG
				//inizio LP PG200360
				//String tassonomiaConf = null;
				//boolean bStopErroreTassonomia = false;
				//boolean bStopDiversaTassonomia = false;
				boolean bStopAssenzaTassonomia = false;
				String sDefaultTassonomia = "";
				String tassonomia = null;
				//inizio LP EP22405X
				String causalePreavvisiBRAV = "";
				//fine LP EP22405X

				try {
					/*
					String appo = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".stoperroretassonomia." + dbSchemaCodSocieta);
					bStopErroreTassonomia = (appo != null ? !appo.trim().equalsIgnoreCase("N") : false);
					appo = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".stopdiversatassonomia." + dbSchemaCodSocieta);
					bStopDiversaTassonomia = (appo != null ? !appo.trim().equalsIgnoreCase("N") : false);
					appo = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".stopassenzatassonomia." + dbSchemaCodSocieta);
					*/
					String appo = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".stopassenzatassonomia." + dbSchemaCodSocieta);
					bStopAssenzaTassonomia = (appo != null ? !appo.trim().equalsIgnoreCase("N") : false);
					appo = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".defaulttassonomia." + dbSchemaCodSocieta);
					sDefaultTassonomia = (appo != null ? appo.trim() : "0000000DF");
				} catch (Exception e) {
					e.printStackTrace();
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTra, TRA_FAILED_STATE, "", "", Calendar.getInstance());
					info("Aggiornamento esito transazione in stato FALLITA: " + chiaveTra);
					binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
					throw new Exception(e.getMessage(), e);
					
				}
				//fine LP PG200360

				Boolean tipoPagamentoPoste = false;
				String codiceContestoPagamento = "n/a";	//PG160130 GG 01082016
				String chiavePSP = in.getTransazione().getChiavePspNodoSpc();
				//psp.getChiave() + "|" + psc.getIdCanale()
				String idPsp = "";
				String idCanale = "";
				String idIntermediarioPsp = "";
				String tipoVersamento = "";
				String[] values = chiavePSP.split("\\|");
				
				
				if (values.length > 1){
					idPsp = values[0];
					idCanale = values[1];
				}
				if (values.length > 2){
					idIntermediarioPsp = values[2];
				}
				if (values.length > 3){
					tipoVersamento = values[3];
				}
				if (values.length > 4){
					tipoPagamentoPoste = true;
				}
				info("chiavePSP = "+ chiavePSP);
				info("idPsp = "+ idPsp);
				info("idCanale = "+ idCanale);
				info("idIntermediarioPsp = "+ idIntermediarioPsp);
				info("tipoVersamento = "+ tipoVersamento);
				info("tipoPagamentoPoste = "+ tipoPagamentoPoste);
				
				
				String idBollettiniList = ""; //PG160230_001 GG 23112016 - inserimento lista bollettini in causaleVersamento RPT per Soris
				//Estraggo tutti i bollettini della transazione (TDT-TIC-TFR-ONE): creerò una RPT per ogni bollettino
				BeanIci[] IciList = recuperaTransazioneResp.getListIci();
				BeanIV[] IVList = recuperaTransazioneResp.getListIV();
				BeanFreccia[] FrecciaList = recuperaTransazioneResp.getListFreccia();
				
				HashMap<String, String> entiBollettini = new HashMap<String, String>(); //Contiene le indicazione dell'ente relativo ad ogni bollettino della transazione 
				HashMap<String, String> codiciTipoServizioBollettini = new HashMap<String, String>(); //Contiene le indicazione del tipoServizio relativo ad ogni bollettino della transazione 
				
				
				List<RptNodoSpc> listRpt = new ArrayList<RptNodoSpc>();
				
				List<RptNodoSpcDatiPagamenti> listRptDatiPagamenti = new ArrayList<RptNodoSpcDatiPagamenti>();
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());
				listRptDatiPagamenti.add(new RptNodoSpcDatiPagamenti());

				BigDecimal imptotalebollettino = BigDecimal.ZERO;
//				RptNodoSpc itemRpt = null;
				RptNodoSpcDatiPagamenti itemRptDatiPagamenti = null;
				boolean okrpt = false;
				
				
				
				if (IciList != null){

					//okrpt = false;
					if (itemRptDatiPagamenti == null) itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
					
					for (BeanIci beanIci : IciList) {
						okrpt = true;
						//inizio LP PG200360
						//tassonomiaConf = null;
						tassonomia = null;
						//fine LP PG200360
						//inizio LP EP22405X
						causalePreavvisiBRAV = (beanIci.getCausalePreavvisiBRAV() != null ? beanIci.getCausalePreavvisiBRAV().trim() : "");
						//fine LP EP22405X
						itemRpt.setChiaveTra(chiaveTra);
						itemRpt.setChiaveTabella(beanIci.getChiave_transazione_ici());
						itemRpt.setCodiceTabella("TIC");
						itemRpt.setCodSocieta(beanIci.getCodice_societa());
						itemRpt.setCodUtente(beanIci.getCodice_utente());
						imptotalebollettino = imptotalebollettino.add(beanIci.getImporto_movimento());
						itemRpt.setImporto(imptotalebollettino);
						
						if(cfPagatoreBollettino.trim().length()==0 && beanIci.getCodice_fiscale().trim().length()>0)
							cfPagatoreBollettino = beanIci.getCodice_fiscale().trim();
						
						if(denominazionePagatoreBollettino.trim().length()==0 && beanIci.getDenominazione().trim().length()>0)
							denominazionePagatoreBollettino = beanIci.getDenominazione().trim();
	
						ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIci.getCodice_societa(),
																													beanIci.getCodice_utente(),
																													beanIci.getChiave_ente(),
																													beanIci.getCodice_tipologia_servizio(),
																													"WEB"); 
						ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
						
//inizio LP 20180906
						if(configPagamentoSingleResponse.getConfigPagamento()==null){
							configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIci.getCodice_societa(),
									beanIci.getCodice_utente(),
									beanIci.getChiave_ente(),
									beanIci.getCodice_tipologia_servizio(),
									"MOB");


								configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
						}
//fine LP 20180906
						//inizio LP PG200360
						/*
						tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
						if(bStopErroreTassonomia && (tassonomiaConf == null || tassonomiaConf.trim().length() == 0)) {
							String err = "Errore per Tipologia Servizio: '" + beanIci.getCodice_tipologia_servizio() + "' tassonomia non valorizzata."; 
							info(err);
							throw new Exception(err);
						}
						if(tassonomiaConf != null) {
							tassonomiaConf = tassonomiaConf.trim();
						}
						*/
						//fine LP PG200360
						ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN();
						ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						if(tipoPagamentoPoste){
							info("tipoPagamentoPoste POSTE ");
							if(configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2()!=null && !configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2().equals(""))
								ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						}
						
						int progBollettino = beanIci.getProgBollettino();
						//inizio LP PG200360
						tassonomia = (beanIci.getTassonomia() != null ? beanIci.getTassonomia().trim() : null);
						/*
						if(bStopDiversaTassonomia
						   && tassonomia != null
						   && tassonomia.length() > 0
						   && tassonomiaConf.length() > 0
						   && !tassonomiaConf.equalsIgnoreCase(tassonomia))
						{
							String err = "Errore tassonomia pagamento diversa da tassonomia tipologia servizio: '" + beanIci.getCodice_tipologia_servizio() + "'."; 
							info(err);
							throw new Exception(err);
						}
						if(tassonomia == null || tassonomia.length() == 0) {
							tassonomia = tassonomiaConf;	
						}
						*/
						if(tassonomia == null || tassonomia.length() == 0) {
							if(bStopAssenzaTassonomia) {
								String err = "Errore tassonomia pagamento non valorizzata."; 
								info(err);
								throw new Exception(err);
							}
							info("Possibile errore tassonomia non valorizzata si usa tassonomia default.");
							tassonomia = sDefaultTassonomia;
						}
						//fine LP PG200360
						if (listRptDatiPagamenti.get(progBollettino).getIbanAccredito()==null){
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(beanIci.getImporto_movimento());
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
							//inizio LP PG200360
							listRptDatiPagamenti.get(progBollettino).setTassonomia(tassonomia);
							//fine LP PG200360
							//inizio LP EP22405X
							listRptDatiPagamenti.get(progBollettino).setCausalePreavvisiBRAV(causalePreavvisiBRAV);
							//fine LP EP22405X
						} else {
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(listRptDatiPagamenti.get(progBollettino).getImportoSingoloPagamento().add(beanIci.getImporto_movimento()));
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						}
					}

//					if(okrpt)
//						listRpt.add(itemRpt);
				}

				if (IVList != null){
					System.out.println("sono qui12");
					//okrpt = false;
					if (itemRpt == null) itemRpt = new RptNodoSpc();
					if (itemRptDatiPagamenti == null) itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
					
					for (BeanIV beanIV : IVList) {
						okrpt = true;
						//inizio LP PG200360
						//tassonomiaConf = null;
						tassonomia = null;
						//fine LP PG200360
						//inizio LP EP22405X
						causalePreavvisiBRAV = (beanIV.getCausalePreavvisiBRAV() != null ? beanIV.getCausalePreavvisiBRAV().trim() : "");
						//fine LP EP22405X
						itemRpt.setChiaveTra(chiaveTra);
						itemRpt.setChiaveTabella(beanIV.getChiave_transazione_dettaglio());
						itemRpt.setCodiceTabella("TDT");
						itemRpt.setCodSocieta(beanIV.getCodice_societa());
						itemRpt.setCodUtente(beanIV.getCodice_utente());
						imptotalebollettino = imptotalebollettino.add(beanIV.getImporto_totale_bollettino());
						itemRpt.setImporto(imptotalebollettino);
						System.out.println("sono qui 3");
						if(cfPagatoreBollettino.trim().length()==0 && beanIV.getCodice_fiscale().trim().length()>0)
							cfPagatoreBollettino = beanIV.getCodice_fiscale().trim();
						
						if(denominazionePagatoreBollettino.trim().length()==0 && beanIV.getDenominazione().trim().length()>0)
							denominazionePagatoreBollettino = beanIV.getDenominazione().trim();
						
						
						System.out.println("sono qui 43");
						// QF: LOGICA DEGLI IBAN:
						// valorizzo sia ibanAccredito che appoggio con quelli della configurazione.
						// se l'IBAN del bollettino è valorizzato...allora l'ibanAccredito lo valorizzo con quello del bollettino.
						
						ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIV.getCodice_societa(),
								beanIV.getCodice_utente(),
								beanIV.getChiave_ente_ent(),
								beanIV.getCodice_tipologia_servizio(),
								"WEB");


						ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
						
						if(configPagamentoSingleResponse.getConfigPagamento()==null){
							configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanIV.getCodice_societa(),
							beanIV.getCodice_utente(),
							beanIV.getChiave_ente_ent(),
							beanIV.getCodice_tipologia_servizio(),
							"MOB");
							
							
							configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
						}
						//inizio LP PG200360
						/*
						tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
						if(bStopErroreTassonomia && (tassonomiaConf == null || tassonomiaConf.trim().length() == 0)) {
							String err = "Errore per Tipologia Servizio: '" + beanIV.getCodice_tipologia_servizio() + "' tassonomia non valorizzata.";
							info(err);
							throw new Exception(err);
						}
						if(tassonomiaConf != null) {
							tassonomiaConf = tassonomiaConf.trim();
						}
						*/
						//fine LP PG200360
						
						//inizio LP PG200060
						if(!(dbSchemaCodSocieta.equals("000RM") || dbSchemaCodSocieta.equals("000LP"))) {
						//fine LP PG200060
						// imposto l'ibanAPPOGGIO
						//inizio LP PG210130 Step04
							if(bMultiBeneficiario) {
								if(ibanAppoggioBollettino.trim().length() == 0 && beanIV.getCodiceIbanPostale().trim().length() > 0){
									ibanAppoggioBollettino = beanIV.getCodiceIbanPostale().trim();
								} else {
									ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
								}
							} else
						//fine LP PG210130 Step04
						ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						//inizio LP PG200060
						}
						//fine LP PG200060
						
						if(ibanAccreditoBollettino.trim().length()==0 && beanIV.getCodiceIban().trim().length()>0)
							ibanAccreditoBollettino = beanIV.getCodiceIban().trim();
						else{							
							ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN();
							//inizio LP PG200060
							if(!(dbSchemaCodSocieta.equals("000RM") || dbSchemaCodSocieta.equals("000LP"))) {
							//fine LP PG200060
							if(tipoPagamentoPoste){
								info("tipoPagamentoPoste POSTE ");
								if(configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2()!=null && !configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2().equals(""))
									ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
							}
							//inizio LP PG200060
							}
							//fine LP PG200060
						}
						
						//inizio LP PG200060
						if(dbSchemaCodSocieta.equals("000RM") || dbSchemaCodSocieta.equals("000LP")) {
							if(ibanAppoggioBollettino.trim().length()==0 && beanIV.getCodiceIbanPostale().trim().length()>0){
								ibanAppoggioBollettino = beanIV.getCodiceIbanPostale().trim();
							} else {
								ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
							}
						}
						//fine LP PG200060
						
						info("iban "+ibanAccreditoBollettino);
						info("iban Appoggio: "+ibanAppoggioBollettino); //LP PG200060

						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIV.getChiave_ente_ent());
						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanIV.getCodice_tipologia_servizio());
						
						idBollettiniList = idBollettiniList.trim().length()>0?(idBollettiniList.concat(";").concat(beanIV.getCodice_bollettino_premarcato_mav())):beanIV.getCodice_bollettino_premarcato_mav();	//PG160230_001 GG 23112016
						//inizio LP PG210130 Step04
						if(numeroAvviso.length() == 0)
							numeroAvviso = beanIV.getCodice_bollettino_premarcato_mav();
						//fine LP PG210130 Step04

						BigDecimal importoMarcaDaBolloDigitale = beanIV.getImportoMarcaDaBolloDigitale();
						String segnaturaMarcaDaBolloDigitale = beanIV.getSegnaturaMarcaDaBolloDigitale().trim();
						String tipoBolloDaErogare = beanIV.getTipoBolloDaErogare().trim();
						String provinciaResidenza = beanIV.getProvinciaResidenza().trim();
						
						int progBollettino = beanIV.getProgBollettino();
						//inizio LP PG200360
						tassonomia = (beanIV.getTassonomia() != null ? beanIV.getTassonomia().trim() : null);
						/*
						if(bStopDiversaTassonomia
						   && tassonomia != null
						   && tassonomia.length() > 0
						   && tassonomiaConf.length() > 0
						   && !tassonomiaConf.equalsIgnoreCase(tassonomia))
						{
							String err = "Errore tassonomia pagamento diversa da tassonomia tipologia servizio: '" + beanIV.getCodice_tipologia_servizio() + "'."; 
							info(err);
							throw new Exception(err);
						}
						if(tassonomia == null || tassonomia.length() == 0) {
							tassonomia = tassonomiaConf;	
						}
						*/
						if(tassonomia == null || tassonomia.length() == 0) {
							if(bStopAssenzaTassonomia) {
								String err = "Errore tassonomia pagamento non valorizzata."; 
								info(err);
								throw new Exception(err);
							}
							info("Possibile errore tassonomia non valorizzata si usa tassonomia default.");
							tassonomia = sDefaultTassonomia;
						}
						//fine LP PG200360
						if (listRptDatiPagamenti.get(progBollettino).getIbanAccredito()==null){
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(beanIV.getImporto_totale_bollettino().subtract(importoMarcaDaBolloDigitale));
							listRptDatiPagamenti.get(progBollettino).setImportoMarcaDaBolloDigitale(new BigDecimal("0.0"));
							listRptDatiPagamenti.get(progBollettino).setSegnaturaMarcaDaBolloDigitale("");
							listRptDatiPagamenti.get(progBollettino).setTipoBolloDaErogare("");
							//inizio LP PG200360
							listRptDatiPagamenti.get(progBollettino).setTassonomia(tassonomia);
							//fine LP PG200360
							//inizio LP EP22405X
							listRptDatiPagamenti.get(progBollettino).setCausalePreavvisiBRAV(causalePreavvisiBRAV);
							//fine LP EP22405X
							itemRptDatiPagamenti.setProvinciaResidenza("");
							if (tipoBolloDaErogare.length()>0) { // ok marca da bollo digitale
								itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
								itemRptDatiPagamenti.setImportoSingoloPagamento(new BigDecimal("0.0"));
								itemRptDatiPagamenti.setIbanAccredito("");
								itemRptDatiPagamenti.setIbanAppoggio("");
								itemRptDatiPagamenti.setImportoMarcaDaBolloDigitale(importoMarcaDaBolloDigitale);
								itemRptDatiPagamenti.setSegnaturaMarcaDaBolloDigitale(segnaturaMarcaDaBolloDigitale);
								itemRptDatiPagamenti.setTipoBolloDaErogare(tipoBolloDaErogare);
								itemRptDatiPagamenti.setProvinciaResidenza(provinciaResidenza);
								//inizio LP PG200360
								//
								//TODO: Non so se qui bisogna mettere una tassonomia ad hoc per il bollo
								//
								itemRptDatiPagamenti.setTassonomia(tassonomia);
								//fine LP PG200360
								itemRptDatiPagamenti.setItemValorizzato("S");
								listRptDatiPagamenti.add(itemRptDatiPagamenti); // il bollo si troverà sempre in sesta posizione	
							}
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						} else {
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(listRptDatiPagamenti.get(progBollettino).getImportoSingoloPagamento().add(beanIV.getImporto_totale_bollettino()));
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						}
					}

//					if(okrpt)
//						listRpt.add(itemRpt);

				}
				

				
				if (FrecciaList != null){
					
					//okrpt = false;
					
					if (itemRpt==null) itemRpt = new RptNodoSpc();
					if (itemRptDatiPagamenti == null) itemRptDatiPagamenti = new RptNodoSpcDatiPagamenti();
					
					for (BeanFreccia beanFreccia : FrecciaList) {
						okrpt = true;
						//inizio LP PG200360
						//tassonomiaConf = null;
						tassonomia = null;
						//fine LP PG200360
						//inizio LP EP22405X
						causalePreavvisiBRAV = (beanFreccia.getCausalePreavvisiBRAV() != null ? beanFreccia.getCausalePreavvisiBRAV().trim() : "");
						//fine LP EP22405X
						itemRpt.setChiaveTra(chiaveTra);
						itemRpt.setChiaveTabella(beanFreccia.getChiave_transazione_dettaglio());
						itemRpt.setCodiceTabella("TFR");
						itemRpt.setCodSocieta(beanFreccia.getCodice_societa());
						itemRpt.setCodUtente(beanFreccia.getCodice_utente());
						imptotalebollettino = imptotalebollettino.add(beanFreccia.getImporto_totale_bollettino());
						itemRpt.setImporto(imptotalebollettino);
						
						if(cfPagatoreBollettino.trim().length()==0 && beanFreccia.getCodice_fiscale().trim().length()>0)
							cfPagatoreBollettino = beanFreccia.getCodice_fiscale().trim();
						
						if(denominazionePagatoreBollettino.trim().length()==0 && beanFreccia.getDenominazione_contribuente().trim().length()>0)
							denominazionePagatoreBollettino = beanFreccia.getDenominazione_contribuente().trim();
						
						entiBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanFreccia.getChiave_ente_ent());
						codiciTipoServizioBollettini.put(itemRpt.getCodiceTabella() + "_" + itemRpt.getChiaveTabella() , beanFreccia.getCodice_tipologia_servizio());

						idBollettiniList = idBollettiniList.trim().length()>0?(idBollettiniList.concat(";").concat(beanFreccia.getCodice_identificativo_pagamento())):beanFreccia.getCodice_identificativo_pagamento();	//PG160230_001 GG 23112016
						ConfigPagamentoSingleRequest configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanFreccia.getCodice_societa(),
																													beanFreccia.getCodice_utente(),
																													beanFreccia.getChiave_ente_ent(),
																													beanFreccia.getCodice_tipologia_servizio(),
																													"WEB"); 
						ConfigPagamentoSingleResponse configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest); 
//inizio LP 20180906
						if(configPagamentoSingleResponse.getConfigPagamento()==null){
							configPagamentoSingleRequest = new ConfigPagamentoSingleRequest(beanFreccia.getCodice_societa(),
									beanFreccia.getCodice_utente(),
									beanFreccia.getChiave_ente_ent(),
									beanFreccia.getCodice_tipologia_servizio(),
									"MOB");


								configPagamentoSingleResponse = recuperaFunzioneEnte(configPagamentoSingleRequest);
						}
//fine LP 20180906
						//inizio LP PG200360
						/*
						tassonomiaConf = configPagamentoSingleResponse.getConfigPagamento().getDatiSpecificiIncasso();
						if(bStopErroreTassonomia && (tassonomiaConf == null || tassonomiaConf.trim().length() == 0)) {
							String err = "Errore per Tipologia Servizio: '" + beanFreccia.getCodice_tipologia_servizio() + "' tassonomia non valorizzata.";
							info(err);
							throw new Exception(err);
						}
						if(tassonomiaConf != null) {
							tassonomiaConf = tassonomiaConf.trim();
						}
						*/
						//fine LP PG200360
						ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN();
						ibanAppoggioBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						if(tipoPagamentoPoste){
							info("tipoPagamentoPoste POSTE ");
							if(configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2()!=null && !configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2().equals(""))
								ibanAccreditoBollettino = configPagamentoSingleResponse.getConfigPagamento().getCodiceIBAN2();
						}
						int progBollettino = beanFreccia.getProgBollettino();
						//inizio LP PG200360
						tassonomia = (beanFreccia.getTassonomia() != null ? beanFreccia.getTassonomia().trim() : null);
						/*
						if(bStopDiversaTassonomia
						   && tassonomia != null
						   && tassonomia.length() > 0
						   && tassonomiaConf.length() > 0
						   && !tassonomiaConf.equalsIgnoreCase(tassonomia))
						{
							String err = "Errore tassonomia pagamento diversa da tassonomia tipologia servizio: '" + beanFreccia.getCodice_tipologia_servizio() + "'."; 
							info(err);
							throw new Exception(err);
						}
						if(tassonomia == null || tassonomia.length() == 0) {
							tassonomia = tassonomiaConf;	
						}
						*/
						if(tassonomia == null || tassonomia.length() == 0) {
							if(bStopAssenzaTassonomia) {
								String err = "Errore tassonomia pagamento non valorizzata."; 
								info(err);
								throw new Exception(err);
							}
							info("Possibile errore tassonomia non valorizzata si usa tassonomia default.");
							tassonomia = sDefaultTassonomia;
						}
						//fine LP PG200360
						if (listRptDatiPagamenti.get(progBollettino).getIbanAccredito()==null){
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(beanFreccia.getImporto_totale_bollettino());
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
							//inizio LP PG200360
							listRptDatiPagamenti.get(progBollettino).setTassonomia(tassonomia);
							//fine LP PG200360
							//inizio LP EP22405X
							listRptDatiPagamenti.get(progBollettino).setCausalePreavvisiBRAV(causalePreavvisiBRAV);
							//fine LP EP22405X
						} else {
							listRptDatiPagamenti.get(progBollettino).setIbanAccredito(ibanAccreditoBollettino);
							listRptDatiPagamenti.get(progBollettino).setIbanAppoggio(ibanAppoggioBollettino);
							listRptDatiPagamenti.get(progBollettino).setImportoSingoloPagamento(listRptDatiPagamenti.get(progBollettino).getImportoSingoloPagamento().add(beanFreccia.getImporto_totale_bollettino()));
							listRptDatiPagamenti.get(progBollettino).setItemValorizzato("S");
						}
					}

//					if(okrpt)
//						listRpt.add(itemRpt);
				}
				

				if(okrpt)
					listRpt.add(itemRpt);
				
				String identificativoDominio = "";
				String cfPagatore = "";
				String nomePagatore = "";
				String datiSpecificiRiscossione = "";
				String identificativoIntermediarioPA = "";
				String identificativoStazioneIntermediarioPA = "";
				//inizio LP PG200060
				String causale = "";
				String causaleMB = "";
				//fine LP PG200060
//				String identificativoIntermediarioPA = "", identificativoStazioneIntermediarioPA="",identificativoDominio = "", passwordPsp = "";
//				boolean proxyEnabled = false;
//				String tipoFirma = "", password = "", username = "", nodoSpcUrl = "", proxyHost = "",proxyPort = "", proxyUser = "", proxyPassword = "";
//				String cfPagatore = "", nomePagatore = "", datiSpecificiRiscossione = "";
//				
				try {
//					//Recupero dati dell'ente relativo al pagamento corrente
//								
//					//Leggo informazioni da file di config
//					PropertiesTree configuration;
//					try {
//						String rootPath = System.getenv("NODOSPCWS_CONFIG_ROOT");
//						if (rootPath == null){
//							throw new Exception("Variabile di sistema NODOSPCWS_CONFIG_ROOT non definita");
//						}
//						configuration = new PropertiesTree(rootPath);
//					} catch (Exception e) {
//						throw new Exception("Errore durante il caricamento del file di configurazione NODOSPCWS_CONFIG_ROOT. " + e.getMessage(),e);
//					}
//					
					if (configuration != null){
						identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa");
						identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa");
//						passwordPsp = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp");
//						password = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword");
//						username = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername");
//						nodoSpcUrl = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospc");
//						
//						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta)!=null)
							identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta);
//						
//						
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta)!=null)
							identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta);
//						
//						
//						if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta)!=null)
//							passwordPsp = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta);
//						
//						if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta)!=null)
//							password = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta);
//						
//						if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta)!=null)
//							username = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta);
//							
//						if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta)!=null)
//							nodoSpcUrl = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta);
//						
//						
//						info("nodoSpcUrl = "+ nodoSpcUrl);
//						info("username = "+ username);
//						info("password = "+ password);
//						info("passwordPsp = "+ passwordPsp);
//						info("identificativoStazioneIntermediarioPA = "+ identificativoStazioneIntermediarioPA);
//						info("identificativoIntermediarioPA = "+ identificativoIntermediarioPA);
//						
//						
//						proxyEnabled = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyenabled").equals("1");
//						proxyHost = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyhost");
//						proxyPort = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyport");
//						proxyUser = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyuser");
//						proxyPassword = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxypassword");
//						
						//inizio PG200060
						if(!(dbSchemaCodSocieta.equals("000RM") || dbSchemaCodSocieta.equals("000LP"))) {
						//fine LP PG200060
						cfPagatore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".pagatoreanonimo.cf");
						//inizio PG200060
						}
						//fine LP PG200060
						nomePagatore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".pagatoreanonimo.nome");
						datiSpecificiRiscossione = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".datispecificiriscossione");
						
						//inizio PG200060
						if(dbSchemaCodSocieta.equals("000RM") || dbSchemaCodSocieta.equals("000LP")) {
							try {
								TransazioniBeanTransazioni transazione = transazioni.getBeanTransazioni(); 
								String paymentRequest = transazione.getPayment_request(); //XML chiamata da portale esterno
								if(paymentRequest.trim().length() > 0) {
									System.out.println("trovato paymentRequest per la TX in esame");
									DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
									domFactory.setNamespaceAware(true);
									DocumentBuilder builder = domFactory.newDocumentBuilder();
									InputStream iStream = new ByteArrayInputStream(paymentRequest.getBytes("UTF-8"));
									InputSource iSource = new InputSource(iStream);
									iSource.setEncoding("UTF-8");
									//Document doc = builder.parse( new ByteArrayInputStream(paymentRequest.getBytes()));
									Document doc = builder.parse(iSource);
									
									XPathFactory factory = XPathFactory.newInstance();
									XPath xpath = factory.newXPath();
									String datiSpecifici ="";
									Node datiSpecificiNode = (Node)(xpath.evaluate("//*[local-name()='DatiSpecifici']", doc, XPathConstants.NODE)); //TODO: Si potrebbe usare tutta la xpath corretta, ovvero per aosta: "//ServiceData/DatiSpecifici/Causale"
									if(datiSpecificiNode != null)
										datiSpecifici = datiSpecificiNode.getTextContent();
									
									//PG200020_001 SB - inizio 
									
									if (!datiSpecifici.trim().equals(""))
									{
										Node causaleNode = (Node)(xpath.evaluate("//*[local-name()='DatiSpecifici']/*[local-name()='Causale']", doc, XPathConstants.NODE));
										Node causaleMBNode = (Node)(xpath.evaluate("//*[local-name()='DatiSpecifici']/*[local-name()='CausaleMB']", doc, XPathConstants.NODE));
										
										if(causaleNode !=null)
											causale = causaleNode.getTextContent();
										if(causaleMBNode !=null)
											causaleMB = causaleMBNode.getTextContent();
										
										//PG200020_001 SB - fine			
										//inizio LP PG200360
										//TODO: lascio cosi' ?
										boolean bRimuovi = true;
										if(!bRimuovi) {
										//fine LP PG200360
										int indexDatiSpecifici = datiSpecifici.indexOf("<CodiceServizio>");
										if (indexDatiSpecifici == 0)
										{
											System.out.println("trovato tag datiSpecifici");
											datiSpecifici = datiSpecifici.replace("<CodiceServizio>", "9/");
											datiSpecifici = datiSpecifici.replace("</CodiceServizio>", "/");
											datiSpecifici = datiSpecifici.replace("<NumeroAccertamentoServizio>", "");
											
											datiSpecifici = datiSpecifici.replace("</NumeroAccertamentoServizio>", "");
											datiSpecificiRiscossione = datiSpecifici; 
											System.out.println("[riga 3750] - datiSpecificiRiscossione: "+datiSpecificiRiscossione);
										}
										//inizio LP PG200360
										}
										//fine LP PG200360
									}
								}
							}
							catch (Exception e) {
									error("Errore nella lettura del payment_request [transazione="+ transazioni.getBeanTransazioni().getChiave_transazione() +"]: " + e.getMessage());
							}
						}
						//fine LP PG200060
						
//						
//						
//						/* i seguenti campi sono da prendere dai dati di configurazione dell'ente */
//						//cfEnteCreditore = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".cfentecreditore");
//						//nomeEnteCreditore = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".nomeentecreditore");
//						//ibanAccredito = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".ibanaccredito");
//						
//					}
					info("Lettura da File di configurazione");
					info("cfPagatore = " + cfPagatore);
					info("nomePagatore = " + nomePagatore);
										
					if(cfPagatoreBollettino.trim().length()>0) //Se da uno dei bollettini posso recuperare il codice fiscale, lo utilizzo come pagatore per il nodo
						cfPagatore = cfPagatoreBollettino.trim();
	
					
					//if(denominazionePagatoreBollettino.trim().length()>0) //Se da uno dei bollettini posso recuperare la denominazione, lo utilizzo come denominazione del pagatore per il nodo
					if(transazioni.getBeanTransazioni().getDenominazione_contribuente_per_invio_posta_ordinaria().trim().length()>0)
					{
						nomePagatore = transazioni.getBeanTransazioni().getDenominazione_contribuente_per_invio_posta_ordinaria().trim();
						//in.setAnagraficaVersante(transazioni.getBeanTransazioni().getDenominazione_contribuente_per_invio_posta_ordinaria().trim());
					}
					else
					{
						if(denominazionePagatoreBollettino.trim().length()>0)
							nomePagatore = denominazionePagatoreBollettino.trim();
						else  { 
							if(dbSchemaCodSocieta.equals("000P6"))
								nomePagatore = cfPagatore;
						}
					}
					
					info("Lettura post bollettino");
					info("cfPagatore = " + cfPagatore);
					info("nomePagatore = " + nomePagatore);
					
					// controllo della lunghezza sul pagatore
					if(nomePagatore.length()>70)
						nomePagatore = nomePagatore.substring(0,70);
					
					//Ciclo su listIuv, inserisco nel db, creo RPT
					for (RptNodoSpc rptNodoSpc : listRpt) {
						//Creo IUV
						rptNodoSpc.setRpt("");//l'xml rpt verrà creata subito dopo aver creato lo iuv perchè nell'xml deve essere scritto anche lo iuv
						rptNodoSpc.setRptEsito("");
						rptNodoSpc.setRptFirma("");
						rptNodoSpc.setRt("");
						rptNodoSpc.setRtEsito("");
						rptNodoSpc.setRtFirma("");
						rptNodoSpc.setCodContestoPagamento(codiceContestoPagamento);	//PG160130 GG 01082016
						//31012017 GG - inizio
						rptNodoSpc.setIdPSP(idPsp);
						rptNodoSpc.setIdIntermediarioPSP(idIntermediarioPsp);
						rptNodoSpc.setIdCanalePSP(idCanale);
						//31012017 GG - fine
						
						
						String chiaveEnte = "";
						if(entiBollettini.containsKey(rptNodoSpc.getCodiceTabella() + "_" + rptNodoSpc.getChiaveTabella())) {
							chiaveEnte = entiBollettini.get(rptNodoSpc.getCodiceTabella() + "_" + rptNodoSpc.getChiaveTabella());
						}
						
						String ibanAccredito = "";
						String cfEnteCreditore = "";
						String nomeEnteCreditore = "";
						
						//Recupero l'iban di accredito per ogni bollettino dalla configurazione del suo ente/servizio
						try {
							String tipoServizio = "";
							ConfigUtenteTipoServizioEnteDetailResponse  configUtenteEnte =null;
							info("codiciTipoServizioBollettini = "+ codiciTipoServizioBollettini);
							info("rptNodoSpc.getCodiceTabella() = "+ rptNodoSpc.getCodiceTabella());
							info("rptNodoSpc.getChiaveTabella()() = "+ rptNodoSpc.getChiaveTabella());
							info("ricerca chiave = "+ configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".nodoibancreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte));
						}
						catch (Exception e) {
							//e.printStackTrace();
							error("Impossibile recuperare IbanAccredito per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage());
							throw new Exception("Impossibile recuperare IbanAccredito per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage(),e);		//20082016 GG
						}
						
						//Recupero cf ente per ogni bollettino dalla configurazione del suo ente
						try {
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".nodocreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte)!=null) {
								cfEnteCreditore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".nodocreditatoente."+ dbSchemaCodSocieta + "." + chiaveEnte);
								info("cfEnteCreditore letto da file= "+ cfEnteCreditore);
							}
								
							else {
								EnteDetailResponse  enteDetail = GatewaysIGHelper.getEnteDetailSearchResponse(propertiesTree(), dbSchemaCodSocieta, rptNodoSpc.getCodSocieta(), rptNodoSpc.getCodUtente(), chiaveEnte);
								
								cfEnteCreditore = enteDetail.getEnte().getCFiscalePIva();
								info("cfEnteCreditore letto basedati = "+ cfEnteCreditore);
							}
								
							
							
						}
						catch (Exception e) {
							//e.printStackTrace();
							error("Impossibile recuperare CF per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage());
							throw new Exception("Impossibile recuperare CF per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage(),e);		//20082016 GG
						}
											
						//Recupero nome ente per ogni bollettino dalla configurazione del suo ente
						try {
							AnagEnteSearchResponse anagenteSearchResponse =  GatewaysIGHelper.getAnagEnteSearchResponse(propertiesTree(), dbSchemaCodSocieta, chiaveEnte,"");
							nomeEnteCreditore = getDescrizioneEnte(anagenteSearchResponse.getResponse().getListXml());
						}
						catch (Exception e) {
							//e.printStackTrace();
							error("Impossibile recuperare Nome per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage());
							throw new Exception("Impossibile recuperare Nome per Ente [chiaveEnte="+ chiaveEnte +"]: " + e.getMessage(),e);		//20082016 GG
						}
						
						//Modifica richiesta il 12/06/2014
						identificativoDominio = cfEnteCreditore.trim();
						//inizio LP PG200060 
						//TODO: l'assegnamento sotto non è eseguito sul codice RM per adesso non lo eseguo se RM
						if(!(dbSchemaCodSocieta.equals("000RM") || dbSchemaCodSocieta.equals("000LP"))) {
						//fine LP PG200060 
						if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta + "." + identificativoDominio)!=null) {
							identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta + "." + identificativoDominio);
						}
						//inizio LP PG200060 
						}
						//fine LP PG200060 
						info("identificativoStazioneIntermediarioPA = "+ identificativoStazioneIntermediarioPA);
						
//						if(configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta + "." + identificativoDominio)!=null) {
//							passwordPsp = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta + "." + identificativoDominio);
//						}
//						info("passwordPsp = "+ passwordPsp);
						
						//01082016 GG PG160130 - spostato inserisciRPT dopo recupero idDominio - inizio
						rptNodoSpc.setIdDominio(cfEnteCreditore.trim());
						//inizio LP PG210130 Step04
						String codiceIuv = "";
						BigInteger paymentId = null;
						if(bMultiBeneficiario && codiceIUVExt.length() > 0) {
							codiceIuv = codiceIUVExt;
							rptNodoSpc.setCodiceIuv(codiceIuv);
							codiceContestoPagamento = codiceContestoPagamentoExt;
							rptNodoSpc.setCodContestoPagamento(codiceContestoPagamento);
							String codice = NodoSpcHelper.inserisciRPTMB(propertiesTree(), LOG, dbSchemaCodSocieta, rptNodoSpc);
							String strPaymentId = codice;
							paymentId = new BigInteger(strPaymentId);
							System.out.println("riga 3988 paymentId = " + paymentId);
							rptNodoSpc.setId(paymentId);
						} else {
						//fine LP PG210130 Step04
						String[] codici = NodoSpcHelper.inserisciRPT(propertiesTree(), LOG, dbSchemaCodSocieta, rptNodoSpc);
						String strPaymentId = codici[0];
						//inizio LP PG210130 Step04
						//String codiceIuv = codici[1];
						//BigInteger paymentId = new BigInteger(strPaymentId);
						codiceIuv = codici[1];
						paymentId = new BigInteger(strPaymentId);
						//fine LP PG210130 Step04
						System.out.println("codiceIuv = " + codiceIuv);
						rptNodoSpc.setCodiceIuv(codiceIuv);
						System.out.println("paymentId = " + paymentId);
						rptNodoSpc.setId(paymentId);
						//inizio LP PG210130 Step04
							if(bMultiBeneficiario) {
								//TODO Forse va creata una nuova sp che consente di gestire il progressivo del carrello 0000X ?
								//     E che va usata per bMultiBeneficiario == true e codiceIUVExt == ""
								//     cioè quanto arriva la rpt dell'ente principale devo generare un progressivo
								//     Posso prendere gli ultimi 5 ch dello iuv ? Per adesso faccio così ...
								//codiceContestoPagamento = identificativoDominio + numeroAvviso + "-" + "00001";
								String miniiuv = "00000";
								int lenIuv = codiceIuv.length();
								if(lenIuv == 5) {
									miniiuv = codiceIuv;
								} else if(lenIuv > 5) {
									miniiuv = codiceIuv.substring(lenIuv - 5);
								} else {
									miniiuv = String.format("%5.5s", codiceIuv).replace(' ', '0');
								}
								codiceContestoPagamento = identificativoDominio + numeroAvviso + "-" + miniiuv;
								rptNodoSpc.setCodContestoPagamento(codiceContestoPagamento);
								NodoSpcHelper.updateRPTMB(propertiesTree(), LOG, dbSchemaCodSocieta, rptNodoSpc);
							}
						}
						//fine LP PG210130 Step04
						byte[] rpt = null;
						String idBollettiniListForCausaleVersamento = null;
						String rptXml ="";
						if(dbSchemaCodSocieta.equals("000TO"))
							idBollettiniListForCausaleVersamento = idBollettiniList;
						
						if(cfPagatore==null || cfPagatore.equals("") || cfPagatore.equals(" "))
							cfPagatore = "ANONIMO";
						
						
						if (tipoPagamentoPoste) {
							rptXml = NodoSpcHelper.generaRPTXmlPoste(propertiesTree(), 
																	 LOG, 
																	 "", 
																	 identificativoDominio, 
																	 identificativoStazioneIntermediarioPA, 
																	 cfPagatore, 
																	 nomePagatore, 
																	 in.getEmailContribuente(),
																	 cfEnteCreditore, 
																	 nomeEnteCreditore, 
																	 rptNodoSpc.getImporto(), 
																	 codiceIuv, 
																	 ibanAccredito, 
																	 datiSpecificiRiscossione, 
																	 "BBT",	//tipoVersamento, 
																	 codiceContestoPagamento, 
																	 idBollettiniListForCausaleVersamento,
																	 listRptDatiPagamenti,
//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP
																	 in.getAnagraficaVersante(),
																	 in.getIdenfigicativoUnivocoVersante()
																	 , causale //PG200060
																	 , causaleMB //PG200060
																	 );
//PG180070 - 31/7/2018 - FINE
							rptNodoSpc.setRpt(rptXml);
							//Salvo RPT su db
						} else {
							//Creo RPT
							rptXml = NodoSpcHelper.generaRPTXml(propertiesTree(), 
									                            LOG, 
									                            "", 
									                            identificativoDominio, 
									                            identificativoStazioneIntermediarioPA, 
									                            cfPagatore, 
									                            nomePagatore, 
									                            in.getEmailContribuente(), 
									                            cfEnteCreditore, 
									                            nomeEnteCreditore, 
									                            rptNodoSpc.getImporto(), 
									                            codiceIuv, 
									                            ibanAccredito, 
									                            datiSpecificiRiscossione, 
									                            "BBT",	//tipoVersamento, 
									                            codiceContestoPagamento, 
									                            idBollettiniListForCausaleVersamento,
									                            listRptDatiPagamenti,
//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP
																in.getAnagraficaVersante(),
																in.getIdenfigicativoUnivocoVersante()
																, causale //PG200060
																, causaleMB //PG200060
																);
//PG180070 - 31/7/2018 - FINE
							rptNodoSpc.setRpt(rptXml);
							//Salvo RPT su db
						}
						rpt = rptXml.getBytes("UTF-8");
						
						InserisciRptRequest inserisciRptRequest = new InserisciRptRequest(paymentId.intValue(), rpt);
						InserisciRptResponse res = binding.inserisciRPT(inserisciRptRequest);
						//31012017 GG - inizio
						if (!res.getRetCode().equals("00")) {
							throw new Exception("Errore in inserisciRPT: " + res.getRetMessage());		//31012017 GG
						}
						//31012017 GG - fine
					}
					}
				} catch (Exception e) {
					//28092017 GG - inserito try-catch in modo da evitare transazioni sospese in caso di errore in inserimento RPT: per le transazioni per cui si verifica un errore prima della chiamata al nodo, si aggiorna lo stato a 2
					e.printStackTrace();
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTra, TRA_FAILED_STATE, "", "", Calendar.getInstance());
					info("Aggiornamento esito transazione in stato FALLITA: " + chiaveTra);
					binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);	//TODO da verificare
					throw new Exception(e.getMessage(), e);
				}
								
				if (listRpt.size()>=1){ //Quando si abiliterà la chiamata carrelloRPT allora qui dovrà entrare solo con size()==1

					String chiaveEnte = "";
					if(entiBollettini.containsKey(listRpt.get(0).getCodiceTabella() + "_" + listRpt.get(0).getChiaveTabella())) {
						chiaveEnte = entiBollettini.get(listRpt.get(0).getCodiceTabella() + "_" + listRpt.get(0).getChiaveTabella());
					}
					
					//Chiamo WS Payer per scrittura tabelle log (MIN, MPN, ecc...)
					try {
						MIPSOAPBidingStub mipWsServer = GatewaysIGHelper.getMIPSOAPBidingStub(propertiesTree(), dbSchemaCodSocieta);
						//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
						//ModuloIntegrazionePagamentiNodo min = new ModuloIntegrazionePagamentiNodo(chiaveTra, listRpt.get(0).getCodSocieta(), listRpt.get(0).getCodUtente(), chiaveEnte, identificativoIntermediarioPA, 
						//		listRpt.get(0).getCodiceIuv(), "", "", new String(listRpt.get(0).getRpt().getBytes("UTF-8")), "", "", "", "", "", Calendar.getInstance());
						ModuloIntegrazionePagamentiNodo min = new ModuloIntegrazionePagamentiNodo(chiaveTra, listRpt.get(0).getCodSocieta(), listRpt.get(0).getCodUtente(), chiaveEnte, identificativoIntermediarioPA, 
								listRpt.get(0).getCodiceIuv(), "", "", new String(listRpt.get(0).getRpt().getBytes("UTF-8"), "UTF-8"), "", "", "", "", "", Calendar.getInstance());
						//fine LP 20210325
						MINSaveRequest inMinSaveRequest = new MINSaveRequest(min );
						StatusResponse statusResponse = mipWsServer.saveNodo(inMinSaveRequest);
						info("Esito salvataggio MIN: " + statusResponse.getRetCode() + " - " + statusResponse.getRetMessage());
												

					}
					catch (Exception e) {
						e.printStackTrace();
						error("Impossibile salvare MIN:" + e.getMessage());
						AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTra, TRA_FAILED_STATE, "", "", Calendar.getInstance());
						info("Aggiornamento esito transazione in stato FALLITA: " + chiaveTra);
						binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);	//TODO da verificare
						throw new Exception("Errore in salvataggio MIN: " + e.getMessage(),e);		//31012017 GG
					}
					
					
					// CHIAMATA AL NUOVO NODO					
					
//					Questa va fatta solo sull'ultimo elemento
					
					
					
//					//Richiamare il WS del Nodo nodoInviaRPT()
//					PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
//					//PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(generaMessageGTWResponse.getApiEndPointUrl())); //"http://INDIRIZZO_NODOSPC_PORTA_DI_DOMINIO"
//					String nodoSpcPortDomain = nodoSpcUrl;//ambiente di test
//					PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoInviaRPT")); 
//					
//					PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub)pagamentiTelematiciRPT;
//					_stub.setUsername(username);
//					_stub.setPassword(password);
//					
//					HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
//					QName  portName = _stub.getPortName();
//					List handlerChain = hr.getHandlerChain(portName);
//					
//					HandlerInfo hi = new HandlerInfo();
//					hi.setHandlerClass(WsNodoRpcHandler.class);
//					handlerChain.add(hi);
//	
//					if(proxyEnabled) {
//						System.getProperties().put("http.proxyHost", proxyHost);
//						System.getProperties().put("http.nonProxyHosts", "localhost");
//						System.getProperties().put("http.proxyPort", proxyPort);
//						System.getProperties().put("http.proxyUser", proxyUser);
//						System.getProperties().put("http.proxyPassword", proxyPassword);
//					}
//					String identificativoUnivocoVersamento = "";
//					
//					
//					byte[] rpt = listRpt.get(0).getRpt().getBytes("UTF-8");	//GG 18112016
//					
//					identificativoUnivocoVersamento = listRpt.get(0).getCodiceIuv();
//					NodoInviaRPT bodyrichiesta = new NodoInviaRPT(passwordPsp, idPsp.trim(), idIntermediarioPsp.trim(), idCanale.trim(), tipoFirma, rpt);
//					IntestazionePPT header = new IntestazionePPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());
//					info("INIZIO CHIAMATA nodoInviaRPT");
//					NodoInviaRPTRisposta nodoInviaRPTRisposta = pagamentiTelematiciRPT.nodoInviaRPT(bodyrichiesta, header);
//					info("ESITO nodoInviaRPT = " + nodoInviaRPTRisposta.getEsito());
//					
					
					
					
					
//					if (nodoInviaRPTRisposta.getFault() != null && nodoInviaRPTRisposta.getFault().getFaultCode() != null && !nodoInviaRPTRisposta.getFault().getFaultCode().equals("")){
//						String err_msg = "Fault code: " + nodoInviaRPTRisposta.getFault().getFaultCode() + " Fault String: " + nodoInviaRPTRisposta.getFault().getFaultString() + "Fault Description: " + nodoInviaRPTRisposta.getFault().getDescription();
//						error(err_msg);
//						try {
//							RptNodoSpc rptNodoSpcToUpdate = listRpt.get(0); //new RptNodoSpc();
//							String errMsg = "KO:" + nodoInviaRPTRisposta.getFault().getFaultCode();
//							if(errMsg.length()>20)
//								errMsg = errMsg.substring(0, 20);
//							rptNodoSpcToUpdate.setRptEsito(errMsg);
//							UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
//							binding.updateRptNodoSpc(inUpdateRPT );
//						}
//						catch (Exception e) {
//							warn("Aggiornamento Esito KO RPT non riuscito");
//						}
//						String retMessage = nodoInviaRPTRisposta.getFault().getFaultCode();
//						if (nodoInviaRPTRisposta.getFault().getFaultString()!=null && !nodoInviaRPTRisposta.getFault().getFaultString().trim().equals("")) {
//							retMessage = retMessage.concat(" - ").concat(nodoInviaRPTRisposta.getFault().getFaultString());
//						}
//						return new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, retMessage), "","","");
//					
//					} else {
						
//						try {
//							RptNodoSpc rptNodoSpcToUpdate = listRpt.get(0); //new RptNodoSpc();
//							rptNodoSpcToUpdate.setRptEsito("OK");
//							UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpcToUpdate );
//							binding.updateRptNodoSpc(inUpdateRPT );
//						}
//						catch (Exception e) {
//							warn("Aggiornamento Esito OK RPT non riuscito");
//						}

						//Leggere l'url restituita per la redirect
//						if(nodoInviaRPTRisposta.getRedirect() == 1) {
//							apiRedirect = nodoInviaRPTRisposta.getUrl();
//							info("Url Redirect: " + apiRedirect);
//							String idsessionPSP = "";
//							if(apiRedirect.indexOf("idSession=")>-1) {
//								idsessionPSP = apiRedirect.substring(apiRedirect.indexOf("idSession=")+ 10);
//							}
//							params = idsessionPSP;
//						}
//						else {
//							apiRedirect = "";
//							info("Redirect non previsto per il pagamento nel PSP selezionato: " + idPsp);
//						}
//					}
				}
				else{
					//COMMENTATO PERCHE' IL METODO nodoInviaCarrelloRPT NON E' ANCORA IMPLEMENTATO...
//					TipoElementoListaRPT[] RPTList = new TipoElementoListaRPT[listRpt.size()];
//					for (int i=0; i<listRpt.size(); i++) {
//						RPTList[i].setRpt(Base64.encodeBase64(listRpt.get(i).getRpt().getBytes()));
//					}
//					String idCarrello = "";
//					NodoInviaCarrelloRPT carrelloRPT = new NodoInviaCarrelloRPT(password, idPsp, idIntermediarioPsp, idCanale, RPTList);
//					IntestazioneCarrelloPPT header = new IntestazioneCarrelloPPT(identificativoIntermediarioPA, identificativoStazioneIntermediarioPA, idCarrello);
//					NodoInviaCarrelloRPTRisposta nodoInviaCarrelloRPTRisposta = pagamentiTelematiciRPT.nodoInviaCarrelloRPT(carrelloRPT, header);
				}

			}
			
			info("Fine integraGTWSinc_MultiEnte");
			// we return response SUCCESFULL
			resp = new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"), apiRedirect,generaMessageGTWResponse.getTipoGateway(),params);
			respXml.put(itemRpt,resp);
			return respXml;

		} catch (Exception ex) {
			ex.printStackTrace();
			error(ex.getMessage(), ex);
			resp = new IntegraGTWSincResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), "","","");
			respXml.put(itemRpt,resp);
			return respXml;

		}   	
		
		
	}
//PG180070 - 30/1/2018 - FINE

	
	/**
	 * E' il metodo che verrà chiamato direttamente dal metodo <code><b>allineaTransazioni</b></code> del programma 
	 * client com.seda.payer.allineamentogateways per l'allineamento automatico di tutte le transazioni
	 * che si trovano in uno stato pending.
	 * <br><br>
	 * Avrà come parametro di input <code>dataPagamento</code> (not mandatory).
	 * <br>
	 * Avrà come parametri di output un codice esito operazione (RetCode) e un messaggio di ritorno
	 * relativo al codice esito (RetMessage).
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.AllineaAutomaticoTransazioneRequest
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#allineaAutomaticoTransazione(com.seda.payer.gateways.webservice.dati.AllineaAutomaticoTransazioneRequest)
	 */
	public AllineaAutomaticoTransazioneResponse allineaAutomaticoTransazione(AllineaAutomaticoTransazioneRequest in) throws RemoteException, FaultType {
		try {
			
			// we initialize commonsSOAPBindingStub 
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
			
			// we retry recuperaTransazioniPending response
			com.seda.payer.pgec.webservice.commons.dati.TransazioneType[] transazioneTypes = binding.recuperaTransazioniPending().getListTransazioni();
			// we inspect transazioneTypes
			if (transazioneTypes == null || transazioneTypes.length <= 0)
				return new AllineaAutomaticoTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, "transaction pending not found"));

			boolean isWasAligned = false;
			for (int i = 0; i < transazioneTypes.length; i++) {
				com.seda.payer.pgec.webservice.commons.dati.TransazioneType nextTransaction = transazioneTypes[i];
				info(Events.START_TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW());
				try {
					// we prepare integraGTWAsinc request
					IntegraGTWAsincRequest asincRequest = new IntegraGTWAsincRequest(); {
						//PG150180_001 GG - inizio
						//asincRequest.setTransazione(new TransazioneType(nextTransaction.getChiaveTransazione(), nextTransaction.getChiaveGTW()));
						asincRequest.setTransazione(new TransazioneType(nextTransaction.getChiaveTransazione(), nextTransaction.getChiaveGTW(), ""));
						//PG150180_001 GG - fine
						asincRequest.setDataPagamento(in.getDataPagamento());
					}
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() +
					", { allineaAutomaticoTransazione > Start }");

					// we retry integraGTWAsinc response
					IntegraGTWAsincResponse asincResponse = this.integraGTWAsinc(asincRequest, "batch");

					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() +
					", { allineaAutomaticoTransazione > Retry Response }");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
					", Response: [ ");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", \t\t\tCode=" + asincResponse.getResponse().getRetCode().getValue() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", \t\t\tMessage=" + asincResponse.getResponse().getRetMessage() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", \t\t\tEsitoTransazione=" + asincResponse.getEsitoTransazione() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", \t\t\tCodeAutorizzazioneBanca=" + asincResponse.getCodAutBanca() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", \t\t\tCodeIdentificativoBanca=" + asincResponse.getCodIdBanca() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", \t\t\tDataEffettivoPagamento=" + (asincResponse.getDataPagamento() != null 
									? asincResponse.getDataPagamento().getTime() : null )+ ";");					
					info(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
					", ]");

					if (asincResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1))
						isWasAligned = true;

					info(Events.END_TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW());
					info(" ");
					info(" ");
				} catch (Exception e) {
					warn(Events.TRA_ALIGN.format() + " | T: " + nextTransaction.getChiaveTransazione() + " | G: " + nextTransaction.getChiaveGTW() + 
							", failed, generic error due to: " + e.getMessage());
				}
			}
			return new AllineaAutomaticoTransazioneResponse(new ResponseType(
					isWasAligned ? ResponseTypeRetCode.value1 : ResponseTypeRetCode.value2, 
							isWasAligned ? "Success" : "Failed, transaction founded zero aligned zero"));

		} catch (Exception ex) {
			error("allineaAutomaticoTransazione failed, generic error due to: ", ex);
			return new AllineaAutomaticoTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
		}
	}
	/**
	 * Avrà il compito di allineare lo stato delle transazioni del PayER in base ai flussi di
	 * rendicontazione RNINCATRANS inviati tramite FTP/FTPS dal gateway di pagamento InfoGroup.
	 */
	@SuppressWarnings("unchecked")
	public AllineaAutomaticoTransazioneIGResponse allineaAutomaticoTransazioneIG() throws RemoteException, FaultType {
		initConfig();
		try {
			info("Elaborazione flussi in: " + flowPath);
			// we find compressed flow file 
			TreeSet treeSet = FileUtility.find(flowPath, Pattern.compile("^(.*?)[\\|/]*.(txt)"), FileUtility.ORDER_ASC);
			Iterator iter = treeSet.iterator();
			boolean isWasAlligned = false;
			if (iter != null && !treeSet.isEmpty()) {
				while (iter.hasNext()) {
					File f = (File) iter.next();
					// we define fileNameToElab & pathFullFileToElab
					String fileNameToElab = f.getName().substring(0, f.getName().indexOf(".")) + ".txt";
					String pathFullFileToElab = flowInProgressPath + fileNameToElab;
					// we move file into progress folder
					GatewaysIGHelper.move(f, flowInProgressPath);
					info("process.FILE - " + pathFullFileToElab);

					// we allign transactions
					//isWasAlligned = GatewaysIGHelper.allineaTransazioniIG(pathFullFileToElab, propertiesTree(), LOG);
					
					try{
						//we allign transactions
						//inizio LP PG200070 - 20200812
						//isWasAlligned = GatewaysIGHelper.allineaTransazioniIG(pathFullFileToElab, fileNameToElab, propertiesTree(), LOG, dbSchemaCodSocieta);
						isWasAlligned = GatewaysIGHelper.allineaTransazioniIG(pathFullFileToElab, fileNameToElab, propertiesTree(), LOG, dbSchemaCodSocieta);
						//fine LP PG200070 - 20200812
					}
					catch (Exception e)
					{
						// we move file into rejected folder
						GatewaysIGHelper.move(new File(pathFullFileToElab), flowRejectedPath);
						
						e.printStackTrace();
						error("allineaAutomaticoTransazioneIG - " + e.getMessage());
						return new AllineaAutomaticoTransazioneIGResponse(new ResponseType(ResponseTypeRetCode.value2, 
						"Processo di allineamento transazioni InfoGroup fallito"));
					}
					
					
					
					if (isWasAlligned) {
						// we move file into processed folder
						GatewaysIGHelper.move(new File(pathFullFileToElab), flowProcessedPath);
					} else {
						// we move file into rejected folder
						GatewaysIGHelper.move(new File(pathFullFileToElab), flowRejectedPath);
					}
				}
				return new AllineaAutomaticoTransazioneIGResponse(isWasAlligned ? new ResponseType(ResponseTypeRetCode.value1, "Elaborazione terminata con successo")
						: new ResponseType(ResponseTypeRetCode.value2, "Errore nell'elaborazione dei flussi"));
			}
			info("Nessun flusso da elaborare");
			return new AllineaAutomaticoTransazioneIGResponse(
					new ResponseType(ResponseTypeRetCode.value1, "Nessun flusso da elaborare"));

		} catch (Exception e) {
			error("allineaAutomaticoTransazioneIG failed, generic error due to: ", e);
			return new AllineaAutomaticoTransazioneIGResponse(new ResponseType(ResponseTypeRetCode.value2, 
					"Processo di allineamento transazioni InfoGroup fallito"));
		}
	}
	/**
	 * E' il metodo che verrà chiamato direttamente dall'interfaccia manager delle transazioni per
	 * l'allineamento manuale di una singola transazione.
	 * <br><br>
	 * Avrà come parametri di input la chiave della transazione <code>chiaveTransazione</code> 
	 * e la chiave del gateway <code>chiaveGTW</code>.
	 * <br>
	 * Avrà come parametri di output un codice esito operazione <code>retCode</code> e un messaggio di ritorno
	 * relativo al codice esito <code>retMessage</code>.
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.AllineaManualeTransazioneRequest
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#allineaManualeTransazione(com.seda.payer.gateways.webservice.dati.AllineaManualeTransazioneRequest)
	 */
	public AllineaManualeTransazioneResponse allineaManualeTransazione(AllineaManualeTransazioneRequest in) throws RemoteException, FaultType {
		try {
			System.out.println("inizio allineaManualeTransazione");
			// we prepare integraGTWAsinc request
			IntegraGTWAsincRequest asincRequest = new IntegraGTWAsincRequest(); {
				asincRequest.setTransazione(in.getTransazione());
			}
			// we retry integraGTWAsinc response
			IntegraGTWAsincResponse asincResponse = this.integraGTWAsinc(asincRequest, "online");
			info("integraGTWAsinc.CODE - " + asincResponse.getResponse().getRetCode().getValue());
			info("integraGTWAsinc.MESSAGE - " + asincResponse.getResponse().getRetMessage());
			info("integraGTWAsinc.ESITO_TRANSAZIONE - " + asincResponse.getEsitoTransazione());
			info("integraGTWAsinc.COD_AUT_BANCA - " + asincResponse.getCodAutBanca());
			boolean isWasAligned = false;
			if (asincResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ||
				asincResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value3))
				isWasAligned = true;

			return new AllineaManualeTransazioneResponse(new ResponseType(
					isWasAligned ? ResponseTypeRetCode.value1 : ResponseTypeRetCode.value2, 
							isWasAligned ? "Success" : "Failed, transaction " + in.getTransazione() + " not found and not align"), asincResponse.getStatoRPT());  //SVILUPPO_009 SB

		} catch (Exception ex) {
			error("allineaAutomaticoTransazione failed, generic error due to: ", ex);
			return new AllineaManualeTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), "");  //SVILUPPO_009 SB
		}
	}
	/**
	 * Nella modalità asincrona avrà il compito di colloquiare con il gateway preposto (PAYPAL) per
	 * verificare lo stato di una specifica transazione.
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.AllineaTransazioneRequest
	 */
	private AllineaTransazioneResponse allineaTransazione(AllineaTransazioneRequest in) throws RemoteException, FaultType {
		try {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazione > Start }");
			// we retry info gateway
			
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
			// we call PayPalAPI service - transactionSearch
			
			TransactionSearchResponseType transactionSearchResp = this.transactionSearch(in.getDataPagamento(), generaMessageGTWResponse);
			//TransactionSearchResponseType transactionSearchResp = this.transactionSearch(in.getTransazione().getChiaveTransazione(),in.getDataPagamento(), generaMessageGTWResponse);
			if (transactionSearchResp.getAck().getValue().toString().equals(PP_ACK_SUCCESS)) {
				if (transactionSearchResp.getPaymentTransactions() != null && transactionSearchResp.getPaymentTransactions().length > 0) {
					info(Events.TRA_ALIGN.format() + " | PAYPAL: ciclo sulla risposta di  transactionSearch da PayPal per chiedere il dettaglio di ogni transazione");
					for (int i = 0; i < transactionSearchResp.getPaymentTransactions().length; i++) {
						PaymentTransactionSearchResultType searchResultType = transactionSearchResp.getPaymentTransactions(i);
						info(Events.TRA_ALIGN.format() + " | PAYPAL: chiedo dettaglio per IDPAYPAL = " + searchResultType.getTransactionID());
						
						GetTransactionDetailsResponseType details = 
							this.getTransactionDetails(searchResultType.getTransactionID(), generaMessageGTWResponse);
						
						
//						GetExpressCheckoutDetailsResponseType Mydetails  = this.getExpressCheckoutDetails(searchResultType, generaMessageGTWResponse);
						
						//ANDREA PAYPAL
						//if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom() == null)
						
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getInvoiceID() == null)
							continue;
						
						//ANDREA PAYPAL
						//if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom()
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getInvoiceID()
								.equals(in.getTransazione().getChiaveTransazione()))
						{
							info(Events.TRA_ALIGN.format() + " | PAYPAL: Trovato in elenco ID = " + in.getTransazione().getChiaveTransazione());
							return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"));
						}
						
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom() == null)
							continue;

						//FABRIZIO PAYPAL
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom()
								.equals(in.getTransazione().getChiaveTransazione()))
						{
							info(Events.TRA_ALIGN.format() + " | PAYPAL: Trovato in elenco ID = " + in.getTransazione().getChiaveTransazione());
							return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"));
						}
					}
				} else return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, "Not found transactions"));
			}
			return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value3, "Not found transaction " + 
					in.getTransazione().getChiaveTransazione()));

		} catch (Exception ex) {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazione > Abort }");
			warn(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", generic error due to: " + ex.getMessage());
			return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
		}
	}
	
	
	private AllineaTransazioneResponse allineaTransazione(AllineaTransazioneRequest in,TransazioniBeanTransazioni beentrans) throws RemoteException, FaultType {
		try {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazione > Start }");
			// we retry info gateway
			
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
			// we call PayPalAPI service - transactionSearch
			
			TransactionSearchResponseType transactionSearchResp = this.transactionSearch(in.getDataPagamento(), generaMessageGTWResponse);
			//TransactionSearchResponseType transactionSearchResp = this.transactionSearch(in.getTransazione().getChiaveTransazione(),in.getDataPagamento(), generaMessageGTWResponse);
			if (transactionSearchResp.getAck().getValue().toString().equals(PP_ACK_SUCCESS)) {
				if (transactionSearchResp.getPaymentTransactions() != null && transactionSearchResp.getPaymentTransactions().length > 0) {
					info(Events.TRA_ALIGN.format() + " | PAYPAL: ciclo sulla risposta di  transactionSearch da PayPal per chiedere il dettaglio di ogni transazione");
					for (int i = 0; i < transactionSearchResp.getPaymentTransactions().length; i++) {
						PaymentTransactionSearchResultType searchResultType = transactionSearchResp.getPaymentTransactions(i);
						info(Events.TRA_ALIGN.format() + " | PAYPAL: chiedo dettaglio per IDPAYPAL = " + searchResultType.getTransactionID());
						
						GetTransactionDetailsResponseType details = 
							this.getTransactionDetails(searchResultType.getTransactionID(), generaMessageGTWResponse);
						
						
//						GetExpressCheckoutDetailsResponseType Mydetails  = this.getExpressCheckoutDetails(searchResultType, generaMessageGTWResponse);
						
						//ANDREA PAYPAL
						//if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom() == null)
						
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getInvoiceID() == null)
							continue;
						
						//ANDREA PAYPAL
						//if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom()
						info(Events.TRA_ALIGN.format() + " | details.getPaymentTransactionDetails().getPaymentItemInfo().getInvoiceID()= " + details.getPaymentTransactionDetails().getPaymentItemInfo().getInvoiceID());
						info(Events.TRA_ALIGN.format() + " |in.getTransazione().getChiaveTransazione()= " + in.getTransazione().getChiaveTransazione());
						
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getInvoiceID()
								.equals(in.getTransazione().getChiaveTransazione()))
						{
							info(Events.TRA_ALIGN.format() + " | PAYPAL: Trovato in elenco ID = " + in.getTransazione().getChiaveTransazione());
							beentrans.setCodice_autorizzazione_banca(searchResultType.getTransactionID());
							beentrans.setCodice_identificativo_banca(searchResultType.getTransactionID());
							
							SimpleDateFormat dataEffettivoPagamento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dataPagamentoGateway = dataEffettivoPagamento.format(details.getPaymentTransactionDetails().getPaymentInfo().getPaymentDate().getTime());
							
							Calendar  dataPagamentoGatewayDate = Calendar.getInstance();
							dataPagamentoGatewayDate.setTime(dataEffettivoPagamento.parse(dataPagamentoGateway));
							
							beentrans.setData_effettivo_pagamento_su_gateway(dataPagamentoGatewayDate);
							
							
							return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"));
						}
						
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom() == null)
							continue;

						//FABRIZIO PAYPAL
						info(Events.TRA_ALIGN.format() + " |details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom()= " + details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom());
						if (details.getPaymentTransactionDetails().getPaymentItemInfo().getCustom()
								.equals(in.getTransazione().getChiaveTransazione()))
						{
							beentrans.setCodice_autorizzazione_banca(searchResultType.getTransactionID());
							beentrans.setCodice_identificativo_banca(searchResultType.getTransactionID());
							
							SimpleDateFormat dataEffettivoPagamento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dataPagamentoGateway = dataEffettivoPagamento.format(details.getPaymentTransactionDetails().getPaymentInfo().getPaymentDate().getTime());
							
							Calendar  dataPagamentoGatewayDate = Calendar.getInstance();
							dataPagamentoGatewayDate.setTime(dataEffettivoPagamento.parse(dataPagamentoGateway));
							
							beentrans.setData_effettivo_pagamento_su_gateway(dataPagamentoGatewayDate);
							
							
							info(Events.TRA_ALIGN.format() + " | PAYPAL: Trovato in elenco ID = " + in.getTransazione().getChiaveTransazione());
							return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"));
						}
					}
				} else return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, "Not found transactions"));
			}
			return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value3, "Not found transaction " + 
					in.getTransazione().getChiaveTransazione()));

		} catch (Exception ex) {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazione > Abort }");
			warn(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", generic error due to: " + ex.getMessage());
			return new AllineaTransazioneResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
		}
	}
	
	
	/**
	 * Nella modalità asincrona avrà il compito di colloquiare con il gateway preposto (PAGONLINE) per
	 * verificare lo stato di una specifica transazione.
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.AllineaTransazioneRequest
	 */
	private RedirectToGTWPagOnlineResponse allineaTransazionePagOnline(AllineaTransazioneRequest in, String tipoEsecuzione) throws RemoteException, FaultType {
		
		try {
			// we retry info gateway
			info("allineaTransazionePagOnline ");
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
			
				try {
					
					info("leggo ");
					String proxyHost = System.getProperty("http.proxyHost");
					info("leggo "+System.getProperty("http.proxyHost"));
					Integer proxyPort = 8080;
					info("leggo dopo");
					try { proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
					} catch (Exception e) { error("allineaTransazionePagOnline - Invalid proxy port. Use default port 8080"); }

					String proxyUser = System.getProperty("http.proxyUser");
					info("leggo user"+System.getProperty("http.proxyUser"));
					String proxyPassword = System.getProperty("http.proxyPassword");
					info("leggo pwd"+System.getProperty("http.proxyPassword"));
					HttpClient client = new HttpClient();
					if (proxyHost != null) {
						HostConfiguration config = client.getHostConfiguration(); 
						config.setProxy(proxyHost, proxyPort);
						AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
						info("allineaTransazionePagOnline - http.proxyHost = " + proxyHost);
						info("allineaTransazionePagOnline - http.proxyPort = " + proxyPort);
						Credentials credentials=null;
						if (proxyUser != null) {
							credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
							info("allineaTransazionePagOnline - http.proxyUser = " + proxyUser);
						}
						client.getState().setProxyCredentials(authScope, credentials); 
					}
					
					info("leggo AHAHAHAHAHAHAH");
					URL url = new URL(generaMessageGTWResponse.getApiCancelURL());
					info("leggo url "+generaMessageGTWResponse.getApiCancelURL());
					info("leggo path "+url.getPath());
		            PostMethod method =null;
					try {
						method = new PostMethod(url.getPath());
					} catch (Throwable x) {
						x.printStackTrace();
						throw new FaultType(-805, x.getMessage());
					}
		            
		            info("leggo dopo path "+url.getPath());
		            
		            method.addParameter("numeroCommerciante", generaMessageGTWResponse.getApiVersion());
		            method.addParameter("stabilimento", generaMessageGTWResponse.getCodNegozio());
		            method.addParameter("userID", generaMessageGTWResponse.getApiUsername());
		            method.addParameter("password", generaMessageGTWResponse.getApiPassword());
		            method.addParameter("tipoComando", "QUERYORDER");
		            method.addParameter("formatoRisposta", "plaintext");
		            method.addParameter("numeroOrdine", in.getTransazione().getChiaveTransazione());
		            
		            
		            info("numeroCommerciante= "+ generaMessageGTWResponse.getApiVersion());
		            info("stabilimento="+ generaMessageGTWResponse.getCodNegozio());
		            info("userID="+ generaMessageGTWResponse.getApiUsername());
		            info("password="+ generaMessageGTWResponse.getApiPassword());
		            info("tipoComando="+ "QUERYORDER");
		            info("formatoRisposta="+ "plaintext");
		            info("numeroOrdine="+ in.getTransazione().getChiaveTransazione());
		            
		            info("dopo add parameter ");
		            
		            if (url.getProtocol().equals("https")) {
		            	info("protocollo https");
//		                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new  EasySSLProtocolSocketFactory(), 443);
		                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new  SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
		                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
		                
		            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());
		            
		            info("client setHost configurato");
		            
		            @SuppressWarnings("unused")
					int returnCode = client.executeMethod(method);
		            info("client.executeMethod eseguito");
					//inizio LP PG21XX04 Leak
		            //InputStream response = method.getResponseBodyAsStream();
		            //String responseString = readInputStreamAsString(response);
		            String responseString = method.getResponseBodyAsString();
					//fine LP PG21XX04 Leak
		            
		            RedirectToGTWPagOnlineResponse ritorna = new RedirectToGTWPagOnlineResponse();
		            ritorna = RedirectToGTWPagOnlineResponse.parse(responseString);
		            info("responseString = " + responseString);
		            
		            if (tipoEsecuzione.equalsIgnoreCase("online")) {
//		            	if (ritorna.getStato().equalsIgnoreCase("CO") ||
//			 		           	ritorna.getStato().equalsIgnoreCase("OK")) {
		            	if (ritorna.getStato().equalsIgnoreCase("CO")) {
			 		           	return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
			 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		            }	
			 		        else if (ritorna.getStato().equalsIgnoreCase("KO") ||
			 		           		ritorna.getStato().equalsIgnoreCase("AB") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ST") ||
			 		           		ritorna.getStato().equalsIgnoreCase("EX") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ORDINEFALLITO")) {
			 		        	    return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value3, "Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
//			 		           		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        } else {
			 		          		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato sconosciuto = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        }
		            } else if (tipoEsecuzione.equalsIgnoreCase("batch")) {
		            	if (ritorna.getStato().equalsIgnoreCase("CO")) {
			 		           	return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
			 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		            }	
			 		        else if (ritorna.getStato().equalsIgnoreCase("KO") ||
			 		           		ritorna.getStato().equalsIgnoreCase("AB") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ST") ||
			 		           		ritorna.getStato().equalsIgnoreCase("EX") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ORDINEFALLITO")) {
			 		           		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value3, "Errore: Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        } else {
			 		          		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore: Stato sconosciuto = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        }
		            }
		            return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error : Elaborazione diversa da batch e online"));
		           
				} catch (Exception ex) {
					LOG.error("allineaTransazionePagOnline failed - generic error due to: ", ex);
					ex.printStackTrace();
					return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error "));
				}
				catch (Throwable ex) {
					LOG.error("allineaTransazionePagOnline failed - generic error due to: ", ex);
					LOG.error("allineaTransazionePagOnline failed - generic error due to: ", ex);
						ex.printStackTrace();
						return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error "));
				}
			
		} catch (Exception ex) {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazionePagOnline > Abort }");
			warn(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", generic error due to: " + ex.getMessage());
			return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
		}
	}

private RedirectToGTWPagOnlineResponse allineaTransazioneMyBank(AllineaTransazioneRequest in, String tipoEsecuzione) throws RemoteException, FaultType {
		
		try {
			// we retry info gateway
			info("allineaTransazioneMyBank ");
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
			info("DOPO ");
				try {
					
					info("leggo ");
					String proxyHost = System.getProperty("http.proxyHost");
					info("leggo "+System.getProperty("http.proxyHost"));
					Integer proxyPort = 8080;
					info("leggo dopo");
					try { proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
					} catch (Exception e) { error("allineaTransazionePagOnline - Invalid proxy port. Use default port 8080"); }

					String proxyUser = System.getProperty("http.proxyUser");
					info("leggo user"+System.getProperty("http.proxyUser"));
					String proxyPassword = System.getProperty("http.proxyPassword");
					info("leggo pwd"+System.getProperty("http.proxyPassword"));
					HttpClient client = new HttpClient();
					if (proxyHost != null) {
						HostConfiguration config = client.getHostConfiguration(); 
						config.setProxy(proxyHost, proxyPort);
						AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
						info("allineaTransazionePagOnline - http.proxyHost = " + proxyHost);
						info("allineaTransazionePagOnline - http.proxyPort = " + proxyPort);
						Credentials credentials=null;
						if (proxyUser != null) {
							credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
							info("allineaTransazionePagOnline - http.proxyUser = " + proxyUser);
						}
						client.getState().setProxyCredentials(authScope, credentials); 
					}
					
					info("leggo AHAHAHAHAHAHAH");
					URL url = new URL(generaMessageGTWResponse.getApiCancelURL());
					info("leggo url "+generaMessageGTWResponse.getApiCancelURL());
					info("leggo path "+url.getPath());
		            PostMethod method =null;
					try {
						method = new PostMethod(url.getPath());
					} catch (Throwable x) {
						x.printStackTrace();
						throw new FaultType(-805, x.getMessage());
					}
		            
		            info("leggo dopo path "+url.getPath());
		            
		            method.addParameter("numeroCommerciante", generaMessageGTWResponse.getApiVersion());
		            method.addParameter("stabilimento", generaMessageGTWResponse.getCodNegozio());
		            method.addParameter("userID", generaMessageGTWResponse.getApiUsername());
		            method.addParameter("password", generaMessageGTWResponse.getApiPassword());
		            method.addParameter("tipoComando", "QUERYORDER");
		            method.addParameter("formatoRisposta", "plaintext");
		            method.addParameter("numeroOrdine", in.getTransazione().getChiaveTransazione().replaceFirst("-", ""));
		            
		            if (url.getProtocol().equals("https")) {
//		                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
		                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
		                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
		            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

		            @SuppressWarnings("unused")
					int returnCode = client.executeMethod(method);
		            
					//inizio LP PG21XX04 Leak
		            //InputStream response = method.getResponseBodyAsStream();
		            //String responseString = readInputStreamAsString(response);
		            String responseString = method.getResponseBodyAsString();
					//fine LP PG21XX04 Leak
		            RedirectToGTWPagOnlineResponse ritorna = new RedirectToGTWPagOnlineResponse();
		            ritorna = RedirectToGTWPagOnlineResponse.parse(responseString);
		            info("responseString = " + responseString);
		            
		            if (tipoEsecuzione.equalsIgnoreCase("online")) {
//		            	if (ritorna.getStato().equalsIgnoreCase("CO") ||
//			 		           	ritorna.getStato().equalsIgnoreCase("OK")) {
		            	if (ritorna.getStato().equalsIgnoreCase("CO")) {
			 		           	return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
			 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		            }	
			 		        else if (ritorna.getStato().equalsIgnoreCase("KO") ||
			 		           		ritorna.getStato().equalsIgnoreCase("AB") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ST") ||
			 		           		ritorna.getStato().equalsIgnoreCase("EX") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ORDINEFALLITO")) {
			 		        	    return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value3, "Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
//			 		           		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        } else {
			 		          		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato sconosciuto = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        }
		            } else if (tipoEsecuzione.equalsIgnoreCase("batch")) {
		            	if (ritorna.getStato().equalsIgnoreCase("CO")) {
			 		           	return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
			 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		            }	
			 		        else if (ritorna.getStato().equalsIgnoreCase("KO") ||
			 		           		ritorna.getStato().equalsIgnoreCase("AB") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ST") ||
			 		           		ritorna.getStato().equalsIgnoreCase("EX") ||
			 		           		ritorna.getStato().equalsIgnoreCase("ORDINEFALLITO")) {
			 		           		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value3, "Errore: Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        } else {
			 		          		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore: Stato sconosciuto = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        }
		            }
		            return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error : Elaborazione diversa da batch e online"));
		           
				} catch (Exception ex) {
					LOG.error("allineaTransazionePagOnline failed - generic error due to: ", ex);
					ex.printStackTrace();
					return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error "));
				}
			
		} catch (Exception ex) {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazionePagOnline > Abort }");
			warn(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", generic error due to: " + ex.getMessage());
			return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
		}
	}
	

private RedirectToGTWPagOnlineResponse allineaTransazioneSatispay(AllineaTransazioneRequest in, String tipoEsecuzione) throws RemoteException, FaultType {
	
	try {
		// we retry info gateway
		info("PRIMA ");
		GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
				new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
		info("DOPO ");
			try {
				
				info("leggo ");
				String proxyHost = System.getProperty("http.proxyHost");
				info("leggo "+System.getProperty("http.proxyHost"));
				Integer proxyPort = 8080;
				info("leggo dopo");
				try { proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
				} catch (Exception e) { error("allineaTransazionePagOnline - Invalid proxy port. Use default port 8080"); }

				String proxyUser = System.getProperty("http.proxyUser");
				info("leggo user"+System.getProperty("http.proxyUser"));
				String proxyPassword = System.getProperty("http.proxyPassword");
				info("leggo pwd"+System.getProperty("http.proxyPassword"));
				
				if (proxyHost != null) {
					AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
					info("allineaTransazionePagOnline - http.proxyHost = " + proxyHost);
					info("allineaTransazionePagOnline - http.proxyPort = " + proxyPort);
					Credentials credentials=null;
					if (proxyUser != null) {
						credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
						info("allineaTransazionePagOnline - http.proxyUser = " + proxyUser);
					}
					
				}
				
				info("leggo AHAHAHAHAHAHAH");
				URL url = new URL(generaMessageGTWResponse.getApiCancelURL());
				info("leggo url "+generaMessageGTWResponse.getApiCancelURL());
				info("leggo path "+url.getPath());           
	            info("leggo dopo path "+url.getPath()); 
	            RedirectToGTWPagOnlineResponse ritorna = new RedirectToGTWPagOnlineResponse();
	            String dataesitopagamento="";
	            try{
                CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);	
				SatispayClient satispayClient=new SatispayClient(generaMessageGTWResponse.getApiEndPointUrl().trim(), false,null, 0, false,generaMessageGTWResponse.getApiSignature());
				RecuperaTransazioneResponseType recuperaTransazioneResp =
				binding.recuperaTransazione(new RecuperaTransazioneRequestType(in.getTransazione().getChiaveTransazione()));
				
				
				if(!recuperaTransazioneResp.getBeanTransazioni().getBeanTransazioni().getCampo_opzionale_1().equalsIgnoreCase("")){
				
	            ChargeResponse chargeResponse=satispayClient.getACharge(recuperaTransazioneResp.getBeanTransazioni().getBeanTransazioni().getCampo_opzionale_1());
	            Date expired_date=chargeResponse.getExpire_date();
	           
	            ritorna.setOrderid(chargeResponse.getId());
	            
	            ritorna.setStato(chargeResponse.getPaid().concat("|").concat(chargeResponse.getStatus()));
	            ritorna.setTotale(chargeResponse.getAmount());
	            if(chargeResponse.getCharge_date()!=null)
	            {
	            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	            	dataesitopagamento = formatter.format(chargeResponse.getCharge_date());
	            }
	            	
	            	 
	            
	            if(Calendar.getInstance().getTime().after(expired_date)&&(!ritorna.getStato().equalsIgnoreCase("true|SUCCESS"))) {
	            	ritorna.setStato(chargeResponse.getPaid().concat("|FAILURE"));	
	            }
	            
				}else{//utente non registrato
					ritorna.setStato("false|FAILURE");	
				}
	            }catch(Exception e){
	            	throw new Exception("-Satispay: problemi nel recupero dell'addebito con chiave "+in.getTransazione().getChiaveTransazione());
	            }
	            
	            //29122016 GG - inizio - inizializzazione data pagamento con data corrente nel formato
//	            DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm");
//	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	            
	            Date today = Calendar.getInstance().getTime();
	            String dataModifica = df.format(today);
	            
	            if(dataesitopagamento!=null && !dataesitopagamento.trim().equals(""))
	            {
	            	dataModifica = dataesitopagamento; // è già in formato ISO 8601 ed in formato stringa
	            }
	            ritorna.setDatamodifica(dataModifica);
	            
	            //29122016 GG - fine - inizializzazione data pagamento con data corrente nel formato
	            
	            info("BBBBBBBBBBBBBBBBBBBBB");
	            String esito=ritorna.getStato().split("\\|")[0];
	            String messaggio=ritorna.getStato().split("\\|")[1];
	            if (tipoEsecuzione.equalsIgnoreCase("online")) {
	            	if (esito.equalsIgnoreCase("true")&&messaggio.equalsIgnoreCase("SUCCESS")) {
		 		           	return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
		 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
		 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
		 		            }	
		 		        else if (esito.equalsIgnoreCase("false")&&messaggio.equalsIgnoreCase("REQUIRED")) {
		 		        	    return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato transazione in attesa di pagamento =  " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
		 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
		 		        } else {
		 		          		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value3, "Stato transazione annullato  = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
		 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
		 		        }
	            } else if (tipoEsecuzione.equalsIgnoreCase("batch")) {
	            	if (esito.equalsIgnoreCase("true")&&messaggio.equalsIgnoreCase("SUCCESS")) {
		 		           	return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
		 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
		 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
		 		            }	
		 		        else if (esito.equalsIgnoreCase("false")&&messaggio.equalsIgnoreCase("REQUIRED")) {
		 		           		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore: Stato transazione in attesa di pagamento = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
		 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
		 		        } else {
		 		          		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value3, "Errore: Stato transazione annullato = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
		 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
		 		        }
	            }
	            return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error : Elaborazione diversa da batch e online"));
	           
			} catch (Exception ex) {
				error("allineaTransazionePagOnline failed - generic error due to: ", ex);
				ex.printStackTrace();
				return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error "));
			}
		
	} catch (Exception ex) {
		info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
		", { allineaTransazionePagOnline > Abort }");
		warn(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
				", generic error due to: " + ex.getMessage());
		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
	}
}



	public String readInputStreamAsString(InputStream in) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(in);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result = bis.read();
		while (result != -1) {
			byte b = (byte) result;
			buf.write(b);
			result = bis.read();
		}
		return buf.toString();
	}

	/** 
	 * In base alla chiave di uno specifico gateway <code>chiaveGTW</code> 
	 * dovrà recuperare nella relativa tabella (PYGTWTB) tutti i parametri di comunicazione/crittografia che serviranno 
	 * poi ai metodi AllineaTransazione() e RedirectToGTW() per colloquiare con il gateway stesso
	 * <br> 
	 * @param in com.seda.payer.gateways.webservice.GeneraMessageGTWRequest
	 */
	private GeneraMessageGTWResponse generaMessageGTW(GeneraMessageGTWRequest in) throws RemoteException, FaultType {
		try {
			info("Inizio generaMessageGTWaaa");
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
			GatewayPagamentoBean service = getFacadeService();
			//fine LP PG200070 - 20200812
			info("Inizio generaMessageGTW AAAAA");
			GatewayPagamentoDto gateway = service.getGatewayPagamento(in.getGateway().getChiaveGTW(), dbSchemaCodSocieta);
			info("Fine generaMessageGTWaaa ");

			
			
			return new GeneraMessageGTWResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"), 
					gateway.getUrlApiRedirect(), gateway.getUrlSitoWebGateway(), gateway.getUrlApiCancel(), 
					in.getOrdertotal().toString(), gateway.getApiUser(), gateway.getApiPassword(), 
					gateway.getApiSignature(), gateway.getUrlApiEndpoint(), gateway.getApiVersion(), gateway.getTipoGateway(), gateway.getCodiceNegozio(),gateway.getOpzioniAggiuntive());
		} catch (Exception e) {
			info("Sono nel catch");
			info(e.getMessage());
			info(" aaa Sono nel catch");
			error(e.getMessage());
			throw new FaultType(Integer.parseInt(ResponseTypeRetCode._value2), e.getMessage());
		}
	}
	/**
	 * Dovrà effettuare l'integrazione con il gateway di pagamento in modalità asincrona verificando lo 
	 * stato di una specifica transazione.
	 * <br>
	 * Avrà come parametri di input la chiave della transazione <code>chiaveTransazione</code> e la chiave del 
	 * gateway <code>chiaveGTW</code> ed una data di ricerca <code>dataPagamento</code> (not mandatory).
	 * <br>
	 * Avrà come parametri di output oltre ad un codice esito operazione <code>retCode</code> e un messaggio di
	 * ritorno relativo al codice esito <code>retMessage</code>, l'esito della transazione <code>esitoTransazione</code>, 
	 * il codice autorizzazione banca <code>codAutBanca</code>, il codice identificativo banca <code>codIdBanca</code> 
	 * e la data di effettivo pagamento sul gateway <code>dataPagamento</code>. 
	 * <br>
	 * Queste ultime tre informazioni saranno valorizzate solamente in caso di transazione completata con successo 
	 * (EsitoTransazione = 1).
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.IntegraGTWAsincRequest
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#integraGTWAsinc(com.seda.payer.gateways.webservice.dati.IntegraGTWAsincRequest)
	 */
	public IntegraGTWAsincResponse integraGTWAsinc(IntegraGTWAsincRequest in, String tipoEsecuzione) throws RemoteException, FaultType {
		try {
			// we initialize commonsSOAPBindingStub
			System.out.println("inizio integraGTWAsinc");
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
			
			// we retry transaction details (Commons.recuperaTransazione)
			RecuperaTransazioneResponseType recuperaTransazioneResp =
				binding.recuperaTransazione(new RecuperaTransazioneRequestType(in.getTransazione().getChiaveTransazione()));
			
			
			System.out.println("recuperaTransazione fatta");
			
			
			Transazioni transazioni = recuperaTransazioneResp.getBeanTransazioni();
			TransazioniBeanTransazioni beanTransazioni = transazioni.getBeanTransazioni();
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { integraGTWAsinc > Start }");
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", TransazioniBeanTransazioni: [");
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", \t\t\tChiave_transazione=" + beanTransazioni.getChiave_transazione() + ";");
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", \t\t\tCodice_autorizzazione_banca=" + beanTransazioni.getCodice_autorizzazione_banca() + ";");
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", \t\t\tCodice_identificativo_banca=" + beanTransazioni.getCodice_identificativo_banca() + ";");
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", \t\t\tData_effettivo_pagamento_su_gateway=" + (beanTransazioni.getData_effettivo_pagamento_su_gateway() != null
							? beanTransazioni.getData_effettivo_pagamento_su_gateway().getTime() : null) + ";");
			info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", ]");
			// we disable allineaTransazione for ChannelDiscarded
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
			GatewayPagamentoBean service = getFacadeService();
			//fine LP PG200070 - 20200812
			GatewayPagamentoDto gateway = service.getGatewayPagamento(in.getTransazione().getChiaveGTW(), dbSchemaCodSocieta);
			
			if (gateway.getCanale().getChiaveCanalePagamento().equals(ChannelNotAllowed.ATM.toString()))
				return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, 
						"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " denied, payment channel " + 
						gateway.getCanale().getChiaveCanalePagamento() + " is not allowed"), 
						TRA_FAILED_STATE, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
						beanTransazioni.getData_effettivo_pagamento_su_gateway(), "");
			//gab modifica
			if ((gateway.getTipoGateway().compareTo(GatewaysTypeKeys.INFOGROUP.format()) == 0)  ||
			   (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.MAVONLINE.format()) == 0)   ||
			   (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.PAGOINCONTO.format()) == 0) ||
			   (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.RIDONLINE.format()) == 0)) 
			{
				return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, 
						"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " denied, payment channel " + 
						gateway.getCanale().getChiaveCanalePagamento() + " is not allowed"), 
						TRA_FAILED_STATE, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
						beanTransazioni.getData_effettivo_pagamento_su_gateway(),"");
			}
			
			AllineaTransazioneRequest transazioneRequest = new AllineaTransazioneRequest(); 
			
			if (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.PAGONLINE.format()) == 0) {
				RedirectToGTWPagOnlineResponse transazionePagOnlineResponse = new RedirectToGTWPagOnlineResponse();
				// we prepare allineaTransazione request
				
				{
					transazioneRequest.setTransazione(in.getTransazione());
					transazioneRequest.setDataPagamento(in.getDataPagamento());
				}
				// we retry allineaTransazione response
				transazionePagOnlineResponse = this.allineaTransazionePagOnline(transazioneRequest, tipoEsecuzione);
				
				info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() +", { integraGTWAsinc > Retry Response }");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + transazionePagOnlineResponse.getResponse().getRetCode().getValue() + ";");	        	
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + transazionePagOnlineResponse.getResponse().getRetMessage() + ";");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
				
				String autorizzazioneBanca = "";
				String identificativoBanca = "";
				Calendar data = Calendar.getInstance();
				
				// se la risposta dal gateway è andata a buon fine aggiorno altrimenti la trx rimane sospesa e non aggiorno niente.
				if (transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ||
					transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value3)) {
					//autorizzazioneBanca = gateway.getApiVersion();
					//identificativoBanca = gateway.getCodiceNegozio();
					data = Generics.getCalendarFromDateString(transazionePagOnlineResponse.getDatamodifica(), "dd.MM.yyyy hh:mm");
					Calendar minDate = Generics.getMinDate();			
					if (data.equals(minDate)) {
						data = Generics.getCalendarFromDateString(transazionePagOnlineResponse.getDatamodifica(), "yyyy-MM-dd hh:mm:ss.SSS");
					}
					
					// we update transaction state
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(
							in.getTransazione().getChiaveTransazione(), transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
							? TRA_COMPLETED_STATE : TRA_FAILED_STATE, autorizzazioneBanca, identificativoBanca, 
									data);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", { aggiornaEsitoTransazione > Start } ");
					
					// we call method aggiornaEsitoTransazione
					AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResp = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", { aggiornaEsitoTransazione > Retry Response } ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + aggiornaEsitoTransazioneResp.getResponse().getRetCode().getValue() + ";");	        	
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + aggiornaEsitoTransazioneResp.getResponse().getRetMessage() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
				} 
				
				return new IntegraGTWAsincResponse(transazionePagOnlineResponse.getResponse(), 
						transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
						? TRA_COMPLETED_STATE : TRA_FAILED_STATE, 
								autorizzazioneBanca, identificativoBanca, 
								data,"");				
			} 
			///RP
			else if (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.MYBANK.format()) == 0) {
				RedirectToGTWPagOnlineResponse transazionePagOnlineResponse = new RedirectToGTWPagOnlineResponse();
				// we prepare allineaTransazione request
				
				{
					transazioneRequest.setTransazione(in.getTransazione());
					transazioneRequest.setDataPagamento(in.getDataPagamento());
				}
				// we retry allineaTransazione response
//				transazionePagOnlineResponse = this.allineaTransazionePagOnline(transazioneRequest, tipoEsecuzione);
				transazionePagOnlineResponse = this.allineaTransazioneMyBank(transazioneRequest, tipoEsecuzione);
				
				info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() +", { integraGTWAsinc > Retry Response }");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + transazionePagOnlineResponse.getResponse().getRetCode().getValue() + ";");	        	
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + transazionePagOnlineResponse.getResponse().getRetMessage() + ";");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
				
				String autorizzazioneBanca = "";
				String identificativoBanca = "";
				Calendar data = Calendar.getInstance();
				
				// se la risposta dal gateway è andata a buon fine aggiorno altrimenti la trx rimane sospesa e non aggiorno niente.
				if (transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ||
					transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value3)) {
					//autorizzazioneBanca = gateway.getApiVersion();
					//identificativoBanca = gateway.getCodiceNegozio();
					data = Generics.getCalendarFromDateString(transazionePagOnlineResponse.getDatamodifica(), "dd.MM.yyyy hh:mm");
					
					// we update transaction state
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(
							in.getTransazione().getChiaveTransazione(), transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
							? TRA_COMPLETED_STATE : TRA_FAILED_STATE, autorizzazioneBanca, identificativoBanca, 
									data);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", { aggiornaEsitoTransazione > Start } ");
					
					// we call method aggiornaEsitoTransazione
					AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResp = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", { aggiornaEsitoTransazione > Retry Response } ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + aggiornaEsitoTransazioneResp.getResponse().getRetCode().getValue() + ";");	        	
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + aggiornaEsitoTransazioneResp.getResponse().getRetMessage() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
				} 
				
				return new IntegraGTWAsincResponse(transazionePagOnlineResponse.getResponse(), 
						transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
						? TRA_COMPLETED_STATE : TRA_FAILED_STATE, 
								autorizzazioneBanca, identificativoBanca, 
								data,"");				
			} 
			///RP forse sarebbe sa usare un ogg. RedirectToGTWPagOnlineResponse  RedirectToGTWCartaSIResponse
			else if(gateway.getTipoGateway().compareTo(GatewaysTypeKeys.CARTASI.format()) == 0){
				///CARTA SI

				RedirectToGTWCartaSIResponse transazionePagOnlineResponse = new RedirectToGTWCartaSIResponse();
				// we prepare allineaTransazione request
				
				{
					transazioneRequest.setTransazione(in.getTransazione());
					transazioneRequest.setDataPagamento(in.getDataPagamento());
				}
				// we retry allineaTransazione response
				transazionePagOnlineResponse = this.allineaTransazionePagOnlineCARTA_SI(transazioneRequest, tipoEsecuzione);
				
				info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() +", { integraGTWAsinc > Retry Response }");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + transazionePagOnlineResponse.getResponse().getRetCode().getValue() + ";");	        	
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + transazionePagOnlineResponse.getResponse().getRetMessage() + ";");
				info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
				
				String autorizzazioneBanca = "";
				String identificativoBanca = "";
				Calendar data = Calendar.getInstance();
				
				// se la risposta dal gateway è andata a buon fine aggiorno altrimenti la trx rimane sospesa e non aggiorno niente.
				if (transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ||
					transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value3)) {
					//autorizzazioneBanca = gateway.getApiVersion();
					//identificativoBanca = gateway.getCodiceNegozio();
					data = Generics.getCalendarFromDateString(transazionePagOnlineResponse.getDatamodifica(), "dd/MM/yyyy HH:mm:ss");
					
					
					
					
					// we update transaction state
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(
							in.getTransazione().getChiaveTransazione(), transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
							? TRA_COMPLETED_STATE : TRA_FAILED_STATE, autorizzazioneBanca, identificativoBanca, 
									data);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", { aggiornaEsitoTransazione > Start } ");
					
					// we call method aggiornaEsitoTransazione
					AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResp = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", { aggiornaEsitoTransazione > Retry Response } ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + aggiornaEsitoTransazioneResp.getResponse().getRetCode().getValue() + ";");	        	
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + aggiornaEsitoTransazioneResp.getResponse().getRetMessage() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
				} 
				
				return new IntegraGTWAsincResponse(transazionePagOnlineResponse.getResponse(), 
						transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
						? TRA_COMPLETED_STATE : TRA_FAILED_STATE, 
								autorizzazioneBanca, identificativoBanca, 
								data,"");				
			
				
			}
			else {
				
				if (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.PAYPAL.format()) == 0) 
				{
					
					AllineaTransazioneResponse transazioneResponse = new AllineaTransazioneResponse();
					// we prepare allineaTransazione request
								
					{ 
						info(Events.TRA_ALIGN.format() + " | Inizio Allienamento per Tx " + in.getTransazione().getChiaveTransazione());
						
						transazioneRequest.setTransazione(in.getTransazione());
						transazioneRequest.setDataPagamento(beanTransazioni.getData_inizio_transazione());
					}
					// we retry allineaTransazione response
					//transazioneResponse = this.allineaTransazione(transazioneRequest);
					transazioneResponse = this.allineaTransazione(transazioneRequest,beanTransazioni);
					
					info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() +", { integraGTWAsinc > Retry Response }");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + transazioneResponse.getResponse().getRetCode().getValue() + ";");	        	
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + transazioneResponse.getResponse().getRetMessage() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
					
					if(ResponseTypeRetCode._value1.equals(TRA_COMPLETED_STATE)){
						
					}
						
						
						
					// we update transaction state
					AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(
							in.getTransazione().getChiaveTransazione(), transazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
							? TRA_COMPLETED_STATE : TRA_FAILED_STATE, 
									beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
									beanTransazioni.getData_effettivo_pagamento_su_gateway());
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", { aggiornaEsitoTransazione > Start } ");
					
					// we call method aggiornaEsitoTransazione
					AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResp = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
					
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", { aggiornaEsitoTransazione > Retry Response } ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + aggiornaEsitoTransazioneResp.getResponse().getRetCode().getValue() + ";");	        	
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + aggiornaEsitoTransazioneResp.getResponse().getRetMessage() + ";");
					info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
					
					
					return new IntegraGTWAsincResponse(transazioneResponse.getResponse(), 
							transazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
							? TRA_COMPLETED_STATE : TRA_FAILED_STATE, 
									beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
									beanTransazioni.getData_effettivo_pagamento_su_gateway(),"");

					//PG150180_001 GG - inizio
					
				} else {
					//Nodo SPC
					//inizio LP PG180290
					//if (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.NODOSPC.format()) == 0) 
					boolean bIsMyPay = false;
					boolean bFaultMyPay = false;
					boolean bFoundIUVMyPay = false;
					boolean bCheckIUDMyPay = false;
					boolean bIsModello3MyPay = false;
					boolean bNonRifaiInviaCarrello = true;

					if (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.NODOSPC.format()) == 0
						|| gateway.getTipoGateway().compareTo(GatewaysTypeKeys.MYPAY.format()) == 0) 
					//fine LP PG180290
					{
					//inizio LP PG180290
						bIsMyPay = (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.MYPAY.format()) == 0);
						String myPayPagonetUrl = "";
						String myPayCodiceIpaEnte = "";
						String myPayPasswordEnte = "";
						String myPayPasswordEnteCrypt = "";
						//String myPayTipoDovuto = "";
					//fine LP PG180290
//						info(Events.TRA_ALIGN.format() + " | generaMessageGTW inizio");
//						GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
//								new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), EMPTY_TOKEN), null));
//						info(Events.TRA_ALIGN.format() + " | generaMessageGTW fine");
						//Leggo informazioni da file di config
						//String password = generaMessageGTWResponse.getApiPassword();
						info(Events.TRA_ALIGN.format() + " | recupero configurazione NODOSPCWS_CONFIG_ROOT");
						
//						
//						PropertiesTree configuration;
//						try {
//							configuration = new PropertiesTree("NODOSPCWS_CONFIG_ROOT");
//						} catch (Exception e) {
//							throw new Exception("Errore durante la creazione del contesto elaborativo " + e.getMessage(),e);
//						}
//						
//						
//						PropertiesTree configuration;
//						try {
//							String rootPath = System.getenv("NODOSPCWS_CONFIG_ROOT");
//							if (rootPath == null){
//								throw new Exception("Variabile di sistema NODOSPCWS_CONFIG_ROOT non definita");
//							}
//							configuration = new PropertiesTree(rootPath);
//						} catch (Exception e) {
//							throw new Exception("Errore durante il caricamento del file di configurazione NODOSPCWS_CONFIG_ROOT. " + e.getMessage(),e);
//						}
						
						System.out.println("sono dentro alla IF NODO del GTW");
						
						
						
						info(Events.TRA_ALIGN.format() + " | recupero configurazione NODOSPCWS_CONFIG_ROOT fine");
						
						
						String tipoFirma = "";
						String identificativoIntermediarioPA = "";
						String identificativoStazioneIntermediarioPA = "";
						String identificativoDominio = "";
						String identificativoUnivocoVersamento = "";
						String codiceContestoPagamento = "n/a";
						//inizio LP PG1900XX_001
						String nodospcGestore = "E";
						String nodospcTipologia= "N";
						//fine LP PG1900XX_001
						//inizio LP PG1900XX_001 uso MyBridge
						String mybridgeUrl = "";
						//fine LP PG1900XX_001 uso MyBridge
						//inizio LP PG1900XX_001 uso EasyBridge
						String easybridgeUrl = "";
						//String cutecute = PropKeys.DEFAULT_NODE.format();
						//fine LP PG1900XX_001 uso EasyBridge
						String passwordPsp = "";
						boolean proxyEnabled = false;
						String password = "", username = "", nodoSpcUrl = "", proxyHost = "", 
						proxyPort = "", proxyUser = "", proxyPassword = "";

						if (configuration != null){
					
							identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa");
							identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa");
							//identificativoDominio = configuration.getProperty(PropKeys.DEFAULT_NODE.format() + ".url.nodospciddominio");
							passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp");
							password = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword");
							username = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername");
							nodoSpcUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc");
							
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta)!=null)
								identificativoIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidintermediariopa." + dbSchemaCodSocieta);
							
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta)!=null)
								identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta);
							
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta)!=null)
								passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta);
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta)!=null)
								password = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpassword." + dbSchemaCodSocieta);
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta)!=null)
								username = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcusername." + dbSchemaCodSocieta);
								
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta)!=null)
								nodoSpcUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospc." + dbSchemaCodSocieta);
							//inizio LP PG1900XX_001
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore") != null) {
								nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore");
							}
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta) != null) {
								nodospcGestore = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcGestore." + dbSchemaCodSocieta);
							}
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia") != null) {
								nodospcTipologia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia");
							}
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia." + dbSchemaCodSocieta) != null) {
								nodospcTipologia = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcTipologia." + dbSchemaCodSocieta);
							}
							//fine LP PG1900XX_001							
							
							//inizio LP PG1900XX_001 uso MyBridge
							mybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge");
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge." + dbSchemaCodSocieta) != null) {
								mybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mybridge." + dbSchemaCodSocieta);
							}
							//fine LP PG1900XX_001 uso MyBridge
							
							//inizio LP PG1900XX_001 uso EasyBridge
							easybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge");
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge." + dbSchemaCodSocieta)!=null)
								easybridgeUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.easybridge." + dbSchemaCodSocieta);
							//fine LP PG1900XX_001 uso EasyBridge
							//inizio LP PG180290
							if(bIsMyPay) {
								myPayPagonetUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mypaypagoneturl");
								if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mypaypagoneturl." + dbSchemaCodSocieta)!=null) {
									myPayPagonetUrl = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.mypaypagoneturl." + dbSchemaCodSocieta);
								}
							}
							//fine LP PG180290

							proxyEnabled = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyenabled").equals("1");
							proxyHost = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyhost");
							proxyPort = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyport");
							proxyUser = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxyuser");
							proxyPassword = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcproxypassword");							

						}
						info(Events.TRA_ALIGN.format() + " | recupero configurazioni da file fine");
						//Estraggo tutti gli rpt della transazione (uno per ogni bollettino)
						RptNodoSpcRequest nodoSpcRequest = new RptNodoSpcRequest(null, in.getTransazione().getChiaveTransazione(), null, null, null);	//29072016 GG PG160130 introdotto codContestoPagamento e idDominio
						RptNodoSpcResponse nodoSpcResponse = binding.recuperaRPT(nodoSpcRequest);
						info(Events.TRA_ALIGN.format() + " |  binding.recuperaRPT FINE");
						
						System.out.println("binding.recuperaRPT FINE");

						RptNodoSpc[] listaRpt = nodoSpcResponse.getListaRptNodoSpc();
						for (RptNodoSpc rptNodoSpc : listaRpt) {
							codiceContestoPagamento = rptNodoSpc.getCodContestoPagamento();
							identificativoUnivocoVersamento = rptNodoSpc.getCodiceIuv();
							String esitoPagamento = "9"; //9 impossibile recuperare esito da RT
							
							String strPaymentId = identificativoUnivocoVersamento;
							//inizio LP PG180290
							if(bIsMyPay && identificativoUnivocoVersamento.length() == 0) {
								strPaymentId = "0";
							}
							//fine LP PG180290
							//Prendo solo la parte numerica
							if (strPaymentId.length() > 4)
								strPaymentId = strPaymentId.substring(4);
							BigInteger paymentId = new BigInteger(strPaymentId);

							//Recupero identificativoDominio dalla RPT
							String strRPT = rptNodoSpc.getRpt();
							try {
								DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
								domFactory.setNamespaceAware(true);
								DocumentBuilder builder = domFactory.newDocumentBuilder();
								Document doc = builder.parse( new InputSource(new StringReader(strRPT)));
								
								XPathFactory factory = XPathFactory.newInstance();
								XPath xpath = factory.newXPath();

								Node nodeIdentificativoDominio = (Node)(xpath.evaluate("//*[local-name()='identificativoDominio']", doc, XPathConstants.NODE));
								if(nodeIdentificativoDominio != null) {
									identificativoDominio = nodeIdentificativoDominio.getTextContent();
								}
								
							}
							catch(Exception xmlEx) {
								error("Impossibile recuperare l'IdentificativoDominio dall'XML della RPT inviata", xmlEx);
							}
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta + "." + identificativoDominio)!=null) {
								identificativoStazioneIntermediarioPA = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcidstazioneintermediariopa." + dbSchemaCodSocieta + "." + identificativoDominio);
							}
							info("identificativoStazioneIntermediarioPA = "+ identificativoStazioneIntermediarioPA);
							
							if(configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta + "." + identificativoDominio)!=null) {
								passwordPsp = configuration.getProperty(PropKeys.NODOSPCWS.format() + PropKeys.DEFAULT_NODE.format() + ".url.nodospcpasswordpsp." + dbSchemaCodSocieta + "." + identificativoDominio);
							}
							info("passwordPsp = "+ passwordPsp);						
							
							if(proxyEnabled) {
								System.getProperties().put("http.proxyHost", proxyHost);
								System.getProperties().put("http.nonProxyHosts", "localhost");
								System.getProperties().put("http.proxyPort", proxyPort);
								System.getProperties().put("http.proxyUser", proxyUser);
								System.getProperties().put("http.proxyPassword", proxyPassword);
							}

							//inizio LP PG1900XX_001 uso EasyBridge
							/*
							//Richiamare il WS del Nodo nodoInviaRPT()
							PagamentiTelematiciRPTserviceLocator pagamentiTelematiciRPTserviceLocator = new PagamentiTelematiciRPTserviceLocator();
							//PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(generaMessageGTWResponse.getApiEndPointUrl())); //"http://INDIRIZZO_NODOSPC_PORTA_DI_DOMINIO"
							String nodoSpcPortDomain = nodoSpcUrl;//ambiente di test
							*/
							//fine LP PG1900XX_001 uso EasyBridge
							System.out.println("ESITO RECUPERO  = " + rptNodoSpc.getRptEsito());
							System.out.println("integraGTWAsinc - nodospcTipologia  = " + nodospcTipologia);
							//30012017 GG - inizio
							//inizio LP PG180290
							if (bIsMyPay) {
								String chiaveEnte = GetChiaveEnte(recuperaTransazioneResp);
								EnteDetailResponse  enteDetail = GatewaysIGHelper.getEnteDetailSearchResponse(propertiesTree(), dbSchemaCodSocieta, rptNodoSpc.getCodSocieta(), rptNodoSpc.getCodUtente(), chiaveEnte);

								myPayCodiceIpaEnte = enteDetail.getEnte().getCodIpaEnte();
								myPayPasswordEnte = enteDetail.getEnte().getPasswordEnte();
								myPayPasswordEnteCrypt = enteDetail.getEnte().getPasswordSha256Ente();
								System.out.println("Gateway MyPay");
								System.out.println("integraGTWAsinc - myPayPagonetUrl  = " + myPayPagonetUrl);
								System.out.println("integraGTWAsinc - myPayCodiceIpaEnte  = " + myPayCodiceIpaEnte);
								System.out.println("integraGTWAsinc - myPayPasswordEnte  = " + myPayPasswordEnte);
								System.out.println("integraGTWAsinc - myPayPasswordEnteCrypt  = " + myPayPasswordEnteCrypt);
							}	
							//fine LP PG180290
							if (!rptNodoSpc.getRptEsito().trim().equalsIgnoreCase("OK")) {			
							//inizio LP PG1900XX_001 uso MyBridge
								NodoInviaRPTRisposta nodoInviaRPTRisposta = null;
								String sRptEsito = "";
								//inizio LP PG180290
								if (bIsMyPay) {
									//TODO: prendo il codice errore da Esito se è presente nella forma "KO:<errore>"
									//      e genero un generico fault con <errore>
									//      altrimenti restituisco un generico fault con PAA SYSTEM ERROR
									if(bNonRifaiInviaCarrello) {
										String codiceErrore = "PAA SYSTEM ERROR";
										String descErrore = "Errore generico.";
										String codiceErroreTrovato = rptNodoSpc.getRptEsito().trim();
										String tipoOperazione = "paaSILAttivaEsterna"; 
										if(rptNodoSpc.getCodiceIuv().startsWith("MP")) {
											tipoOperazione = "inviaCarrelloDovutiMyPay";
										}
										String description = "Operazione " + tipoOperazione + " to MyPay: ";
										if(codiceErroreTrovato.length() > 0 && !codiceErroreTrovato.equalsIgnoreCase("KO") ) {
											codiceErrore = codiceErroreTrovato;
											descErrore = codiceErrore; 
										}
										description += descErrore;
										FaultBean ffOut = new FaultBean();
										ffOut.setFaultCode(codiceErrore);
										ffOut.setFaultString(descErrore);
										ffOut.setId(myPayCodiceIpaEnte);
										ffOut.setDescription(description);
										ffOut.setSerial(1);
										nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
										nodoInviaRPTRisposta.setFault(ffOut);
										nodoInviaRPTRisposta.setEsito("KO");
									} else {
										MyPayPagonetFacetServiceLocator locatorMyPay = new MyPayPagonetFacetServiceLocator();
										MyPayPagonetServicesEndpoint serviceMyPay = locatorMyPay.getMyPayPagonetServicesPort(new URL(myPayPagonetUrl));
										info(Events.TRA_ALIGN.format() + " | inviaCarrelloDovutiMyPay");
										byte[] rpt = rptNodoSpc.getRpt().getBytes("UTF-8");
										InviaCarrelloDovutiMyPay bodyrichiestaMyPay = new InviaCarrelloDovutiMyPay();
										bodyrichiestaMyPay.setPassword(myPayPasswordEnte);
										bodyrichiestaMyPay.setPasswordSha256(myPayPasswordEnteCrypt);
										//bodyrichiestaMyPay.setTipoDovuto(myPayTipoDovuto); //se unico tributo 
										//bodyrichiestaMyPay.setTipoDovuto(tipoDovutoBollettini); //se tributo può essere diverso per elementi nella rpt
										bodyrichiestaMyPay.setRpt(rpt);
										org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT headerMyPay = new org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT();
										headerMyPay.setCodIpaEnte(myPayCodiceIpaEnte);
										//headerMyPay.setEnteSILInviaRispostaPagamentoUrl(enteSILInviaRispostaPagamentoUrl); //qui non deve essere passato
										InviaCarrelloDovutiMyPayRisposta res = serviceMyPay.inviaCarrelloDovutiMyPay(bodyrichiestaMyPay, headerMyPay);
										nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
										if(res != null) {
											if(res.getEsito().equals("KO")) {
												org.esed.MyPayPagonet.webservice.operazioni.FaultBean ff = res.getFault();
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode(ff.getFaultCode());
												ffOut.setFaultString(ff.getFaultString());
												ffOut.setId(ff.getId());
												ffOut.setDescription(ff.getDescription());
												ffOut.setSerial(ff.getSerial());
												rptNodoSpc.setRptEsito(ffOut.getFaultCode());
												nodoInviaRPTRisposta.setFault(ffOut);
												nodoInviaRPTRisposta.setEsito("KO");
											} else {
												String idSessionMyPay = "";
												String apiRedirect = res.getUrl();
												if(apiRedirect.indexOf("idSession=") > -1) {
													idSessionMyPay = apiRedirect.substring(apiRedirect.indexOf("idSession=") + 10);
												}
												String identificativoUnivocoDovuto = res.getEsito(); //Su esito arriva lista iud o KO
												//rptNodoSpc.setIdCanalePSP(idSessionMyPay.replace("-", "")); //session
												//rptNodoSpc.setIdIntermediarioPSP(identificativoUnivocoDovuto); //iud
												rptNodoSpc.setRptEsito("OK");
												rptNodoSpc.setIdSessioneCarrello(idSessionMyPay);
												rptNodoSpc.setIdentificativoUnivocoDovuto(identificativoUnivocoDovuto);
												//rptNodoSpc.setIdentificativoTipoDovuto(myPayTipoDovuto);
												nodoInviaRPTRisposta.setRedirect(res.getRedirect());
												nodoInviaRPTRisposta.setUrl(apiRedirect);
												nodoInviaRPTRisposta.setEsito("OK");
											}
										} else {
											String description = "inviaCarrelloDovutiMyPay to MyPay: Errore generico";
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode("PAA SYSTEM ERROR");
											ffOut.setFaultString("Errore generico.");
											ffOut.setId(myPayCodiceIpaEnte);
											ffOut.setDescription(description);
											ffOut.setSerial(1);
											rptNodoSpc.setRptEsito(ffOut.getFaultCode());
											nodoInviaRPTRisposta.setFault(ffOut);
											nodoInviaRPTRisposta.setEsito("KO");
										}
									}
								} else {
								//fine LP PG180290
								if (nodospcGestore.equals("M")) {
									EsedFacetServiceLocator locator = new EsedFacetServiceLocator();
									EsedServicesEndpoint serviceM = locator.getEsedServicesPort(new URL(mybridgeUrl + "/EsedServicesPort"));

									info(Events.TRA_ALIGN.format() + " |  nodoInviaRPT vs Myridge");
									byte[] rpt = rptNodoSpc.getRpt().getBytes("UTF-8");
									com.esed.mybridge.webservice.operazioni.NodoInviaRPT bodyrichiesta = new com.esed.mybridge.webservice.operazioni.NodoInviaRPT(passwordPsp, rptNodoSpc.getIdPSP().trim(), rptNodoSpc.getIdIntermediarioPSP().trim(), rptNodoSpc.getIdCanalePSP().trim(), tipoFirma, rpt);
									com.esed.mybridge.webservice.operazioni.head.IntestazionePPT header = new com.esed.mybridge.webservice.operazioni.head.IntestazionePPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), rptNodoSpc.getCodContestoPagamento());
									
									com.esed.mybridge.webservice.operazioni.NodoInviaRPTRisposta res = serviceM.nodoInviaRPT(bodyrichiesta, header);
									
									nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
									if(res != null) {
										nodoInviaRPTRisposta.setEsito(res.getEsito());
										if(res.getEsito().equals("KO")) {
											com.esed.mybridge.webservice.operazioni.FaultBean ff = res.getFault();
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode(ff.getFaultCode());
											ffOut.setFaultString(ff.getFaultString());
											ffOut.setId(ff.getId());
											ffOut.setDescription(ff.getDescription());
											ffOut.setSerial(ff.getSerial());
											nodoInviaRPTRisposta.setFault(ffOut);
										} else {
											nodoInviaRPTRisposta.setRedirect(res.getRedirect());
											nodoInviaRPTRisposta.setUrl(res.getUrl());
										}
									} else {
										nodoInviaRPTRisposta.setEsito("KO");
										String description = "nodoInviaRPT to MyBridge: 99: errore generico";
										FaultBean ffOut = new FaultBean();
										ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
										ffOut.setFaultString("Errore generico.");
										ffOut.setId(identificativoDominio);
										ffOut.setDescription(description);
										ffOut.setSerial(1);
										nodoInviaRPTRisposta.setFault(ffOut);
									}
								} else {
								
									com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator locator = new com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator();
									com.esed.easybridge.webservice.operazioni.EsedServicesEndpoint serviceE = locator.getEsedServicesPort(new URL(easybridgeUrl + "/EsedServicesPort"));

									info(Events.TRA_ALIGN.format() + " |  nodoInviaRPT vs EasyBridge");
									
									byte[] rpt = rptNodoSpc.getRpt().getBytes("UTF-8");
									com.esed.easybridge.webservice.operazioni.NodoInviaRPT bodyrichiestaE = new com.esed.easybridge.webservice.operazioni.NodoInviaRPT(passwordPsp, rptNodoSpc.getIdPSP().trim(), rptNodoSpc.getIdIntermediarioPSP().trim(), rptNodoSpc.getIdCanalePSP().trim(), tipoFirma, rpt);
									com.esed.easybridge.webservice.operazioni.head.IntestazionePPT headerE = new com.esed.easybridge.webservice.operazioni.head.IntestazionePPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), rptNodoSpc.getCodContestoPagamento(), dbSchemaCodSocieta);
									
									com.esed.easybridge.webservice.operazioni.NodoInviaRPTRisposta res = serviceE.nodoInviaRPT(bodyrichiestaE, headerE);
									
									nodoInviaRPTRisposta = new NodoInviaRPTRisposta();
									if(res != null) {
										nodoInviaRPTRisposta.setEsito(res.getEsito());
										if(res.getEsito().equals("KO")) {
											com.esed.easybridge.webservice.operazioni.FaultBean ff = res.getFault();
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode(ff.getFaultCode());
											ffOut.setFaultString(ff.getFaultString());
											ffOut.setId(ff.getId());
											ffOut.setDescription(ff.getDescription());
											ffOut.setSerial(ff.getSerial());
											nodoInviaRPTRisposta.setFault(ffOut);
										} else {
											nodoInviaRPTRisposta.setRedirect(res.getRedirect());
											nodoInviaRPTRisposta.setUrl(res.getUrl());
										}
									} else {
										nodoInviaRPTRisposta.setEsito("KO");
										String description = "nodoInviaRPT to EasyBridge: 99: errore generico";
										FaultBean ffOut = new FaultBean();
										ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
										ffOut.setFaultString("Errore generico.");
										ffOut.setId(identificativoDominio);
										ffOut.setDescription(description);
										ffOut.setSerial(1);
										nodoInviaRPTRisposta.setFault(ffOut);
									}
								//fine LP PG1900XX_001 uso EasyBridge
								}
								//fine LP PG1900XX_001 uso MyBridge
								//inizio LP PG180290
								}
								//fine LP PG180290
								if (nodoInviaRPTRisposta.getFault() != null && nodoInviaRPTRisposta.getFault().getFaultCode() != null && nodoInviaRPTRisposta.getFault().getFaultCode() != "" && !nodoInviaRPTRisposta.getFault().getFaultCode().equals("PPT_RPT_DUPLICATA")){
									String err_msg = "Fault code: " + nodoInviaRPTRisposta.getFault().getFaultCode() + " Fault String: " + nodoInviaRPTRisposta.getFault().getFaultString() + "Fault Description: " + nodoInviaRPTRisposta.getFault().getDescription();
									error(err_msg);
									//inizio LP PG180290
									if(!bIsMyPay) {
									//fine LP PG180290
									String errMsg = "KO:" + nodoInviaRPTRisposta.getFault().getFaultCode();
									if(errMsg.length()>20)
										errMsg = errMsg.substring(0, 20);
									rptNodoSpc.setRptEsito(errMsg);
									//inizio LP PG180290
									}
									//fine LP PG180290
									sRptEsito = nodoInviaRPTRisposta.getFault().getFaultCode();	//17092018
								}
								else{
									rptNodoSpc.setRptEsito("OK");
								}
								
								System.out.println("nodoInviaRPTRisposta esito  = " + sRptEsito);
								//inizio LP PG180290
								if(!bIsMyPay || !bNonRifaiInviaCarrello) {
								//fine LP PG180290
									//inizio LP PG190220
									String oldErEsito = rptNodoSpc.getErEsito();
									if(oldErEsito != null && oldErEsito.trim().length() > 0) {
										//Se arriva qui un record rpt che ha avuto in precedenza una
										//richiesta di annullo tecnico allora eseguo il reset delle
										//colonne coinvolte: RtAnnullata, RtAnnullataEsito, Rr, Er, etc....
										info(Events.TRA_ALIGN.format() + " | Reset precedente annullo tecnico");
										Calendar cal = Calendar.getInstance();
										cal.set(1000, 0, 1, 0, 0, 0);
										rptNodoSpc.setRtAnnullata("<svuota>");
										rptNodoSpc.setRtAnnullataEsito("<svuota>");
										rptNodoSpc.setRr("<svuota>");
										rptNodoSpc.setEr("<svuota>");
										rptNodoSpc.setErEsito("<svuota>");
										rptNodoSpc.setEsitoInvioRevocaEmailAdmin("<svuota>");
										rptNodoSpc.setDataInvioRevocaEmailAdmin(cal);
										rptNodoSpc.setEsitoInvioRevocaEmailContribuente("<svuota>");
										rptNodoSpc.setDataInvioRevocaEmailContribuente(cal);
									}
									//fine LP PG190220
								info(Events.TRA_ALIGN.format() + " |  Risposta nodoInviaRPT = " + rptNodoSpc.getRptEsito());
								UpdateRptNodoSpcRequest inUpdateRPTNodoInviaRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpc);
								binding.updateRptNodoSpc(inUpdateRPTNodoInviaRPT);
								info(Events.TRA_ALIGN.format() + " |  Esito RPT salvato correttamente");
								//inizio LP PG180290
								}
								//fine LP PG180290
								//17092018 - inizio 
								//inizio LP PG180290
								//if (sRptEsito.equalsIgnoreCase("PPT_PSP_SCONOSCIUTO") || sRptEsito.equalsIgnoreCase("PPT_CANALE_SCONOSCIUTO") || sRptEsito.equalsIgnoreCase("PPT_DOMINIO_SCONOSCIUTO")) {	//KO
								boolean bEsitoKO = false;
								//inizio LP PG180290
								if(bIsMyPay) {
									bEsitoKO = sRptEsito.equalsIgnoreCase("PPT_SYSTEM_ERROR") ||
											   sRptEsito.equalsIgnoreCase("PAA SYSTEM ERROR") ||
											   sRptEsito.equalsIgnoreCase("PAA_SYSTEM_ERROR") ||
										       sRptEsito.equalsIgnoreCase("PAA_ENTE_NON_VALIDO") ||											
											   sRptEsito.equalsIgnoreCase("PAA_XML_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IUD_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IUD_DUPLICATO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IUV_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IUV_DUPLICATO") ||
										       sRptEsito.equalsIgnoreCase("PAA_TIPO_VERSAMENTO_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_DATI_SPECIFICI_RISCOSSIONE_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_CODICE_FISCALE_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_P_IVA_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_NUMERO_DOVUTI_PER_IUV_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IDENTIFICATIVO_TIPO_DOVUTO_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IMPORTO_MARCA_BOLLO_DIGITALE_NON_VALIDA") ||
										       sRptEsito.equalsIgnoreCase("PAA_IMPORTO_SINGOLO_VERSAMENTO_NON_VALIDO") ||
										       sRptEsito.equalsIgnoreCase("PAA_IMPORTO_BILANCIO_NON_VALIDO");
								} else {
									bEsitoKO = sRptEsito.equalsIgnoreCase("PPT_PSP_SCONOSCIUTO") ||
									           sRptEsito.equalsIgnoreCase("PPT_CANALE_SCONOSCIUTO") ||
									           sRptEsito.equalsIgnoreCase("PPT_DOMINIO_SCONOSCIUTO")||
									           sRptEsito.equalsIgnoreCase("PPT_STAZIONE_INT_PA_SCONOSCIUTA");
								}
								if(bEsitoKO) {
								//fine LP PG180290
									String chiaveTransazione = rptNodoSpc.getChiaveTra();
									String codAutorizzazioneBanca = "";
									String codIdBanca = "";
									Calendar dataPagamento = Calendar.getInstance(); 
									info(Events.TRA_ALIGN.format() + " | Aggiorno in stato fallito chiaveTransazione = " + chiaveTransazione);
									try {
										//esito 2 per KO
										AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTransazione, "2", codAutorizzazioneBanca, codIdBanca, dataPagamento);
										// we call method aggiornaEsitoTransazione
										AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResponse = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
										
										ResponseType responseType= null;
										if(aggiornaEsitoTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1)) {
											responseType = new ResponseType(ResponseTypeRetCode.value1, 
													"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " successfully");
										}
										else {
											responseType = new ResponseType(ResponseTypeRetCode.value2, 
												"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " unsuccessfully," + aggiornaEsitoTransazioneResponse.getResponse().getRetMessage());
										}
										
										return new IntegraGTWAsincResponse(responseType,
												(aggiornaEsitoTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
												? TRA_COMPLETED_STATE : TRA_FAILED_STATE), 
														codAutorizzazioneBanca, codIdBanca, 
														dataPagamento,"");	//TODO da togliere che altrimenti non aggiorna
										
									} catch (Exception e) {
										error("integraGTWAsinc - aggiornaEsitoTransazione non riuscita", e);
									}
								}
								//17092018 - fine
								
//								try {
//									String codAutorizzazioneBanca = "";
//									String codIdBanca = "";
//									Calendar dataPagamento = Calendar.getInstance();
//									
//									//esito 1 per ok oppure 2 per KO
//									AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(in.getTransazione().getChiaveTransazione(), "2", codAutorizzazioneBanca, codIdBanca, dataPagamento);
//									// we call method aggiornaEsitoTransazione
//									AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResponse = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
//									
//									ResponseType responseType= null;
//									if(aggiornaEsitoTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1)) {
//										responseType = new ResponseType(ResponseTypeRetCode.value1, 
//												"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " successfully");
//									}
//									else {
//										responseType = new ResponseType(ResponseTypeRetCode.value2, 
//											"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " unsuccessfully," + aggiornaEsitoTransazioneResponse.getResponse().getRetMessage());
//									}
//									
//									return new IntegraGTWAsincResponse(responseType,
//											(aggiornaEsitoTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
//											? TRA_COMPLETED_STATE : TRA_FAILED_STATE), 
//													codAutorizzazioneBanca, codIdBanca, 
//													dataPagamento);
//									
//								} catch (Exception e) {
//									error("integraGTWAsinc - aggiornaEsitoTransazione non riuscita", e);
//								}
								
								
							}
							//Se dopo l'invio l'esito è OK allora posso riprendere le normali operazioni
							if (rptNodoSpc.getRptEsito().trim().equalsIgnoreCase("OK")) {
							//30012017 GG - fine
								//inizio LP PG1900XX_001 uso MyBridge
								NodoChiediStatoRPTRisposta nodoChiediStatoRPTRisposta = null;
								//inizio LP PG1900XX_001 uso EasyBridge
								/*
								PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
								*/
								//fine LP PG1900XX_001 uso EasyBridge
								//inizio LP PG180290
								NodoChiediCopiaRTRisposta nodoChiediCopiaRTRispostaRitenta = null;
								String statoRPT = null;
								String identificativoUnivocoVersamentoMyPay = "";
								String identificativoUnivocoDovutoMyPay = "";
								String idSessioneCarrelloMyPay = "";
								MyPayPagonetFacetServiceLocator locatorMyPay = null;
								MyPayPagonetServicesEndpoint serviceMyPay = null;

								if(bIsMyPay) {
									String ideTemp = rptNodoSpc.getIdSessioneCarrello();
									if(ideTemp != null && ideTemp.trim().length() > 0) {
										idSessioneCarrelloMyPay = ideTemp;
									}
									identificativoUnivocoDovutoMyPay  = rptNodoSpc.getIdentificativoUnivocoDovuto();
									if(identificativoUnivocoDovutoMyPay != null && identificativoUnivocoDovutoMyPay.trim().length() > 0) {
										//prendo il primo iud se sono poresenti piu' di uno dovrebbe
										if(identificativoUnivocoDovutoMyPay.indexOf(";") != -1) {
											identificativoUnivocoDovutoMyPay = identificativoUnivocoDovutoMyPay.substring(0, identificativoUnivocoDovutoMyPay.indexOf(";")); 
										}
										bCheckIUDMyPay = true;
									}

									locatorMyPay = new MyPayPagonetFacetServiceLocator();
									serviceMyPay = locatorMyPay.getMyPayPagonetServicesPort(new URL(myPayPagonetUrl));

									if(idSessioneCarrelloMyPay.length() > 0) {
										//dovrei essere su un modello 1 
										//Stati che mi posso aspettare da paaSILChiediEsitoCarrelloDovuti
										//tramite chiediEsitoCarrelloDovutiMyPay:
										//
										//      fault
										//           PAA_ENTE_NON_VALIDO
										//           PAA_ID_SESSION_NON_VALIDO
										//           PAA SYSTEM ERROR
										//
										//      esito
										//           PREDISPOSTO
										//           NUOVO_CARRELLO
										//           PAGATO
										//           NON_PAGATO
										//           PARZ_PAGATO
										//           DECORR_TERM
										//           DECORR_TERM_PARZ
										//           STATO_CARRELLO_ABORT 
										//           STATO_CARRELLO_IMPOSSIBILE_INVIARE_RP
										//           STATO_CARRELLO_SCADUTO
										//           STATO_CARRELLO_SCADUTO_ELABORATO
										//
										//      esito ricevuto da da mypay:
										//           "PREDISPOSTO",
										//           "SCADUTO ELABORATO"
										//           "TRANSAZIONE NON COMPLETATA"
										info(Events.TRA_ALIGN.format() + " | chiediEsitoCarrelloDovutiMyPay ");
										ChiediEsitoCarrelloDovutiMyPay bodyrichiestaMyPay = new ChiediEsitoCarrelloDovutiMyPay();
										bodyrichiestaMyPay.setPassword(myPayPasswordEnte);
										bodyrichiestaMyPay.setIdSessionCarrello(idSessioneCarrelloMyPay);
										org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT headerMyPay = new org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT();
										headerMyPay.setCodIpaEnte(myPayCodiceIpaEnte);
										ChiediEsitoCarrelloDovutiMyPayRisposta res =  serviceMyPay.chiediEsitoCarrelloDovutiMyPay(bodyrichiestaMyPay, headerMyPay);
										nodoChiediCopiaRTRispostaRitenta = new NodoChiediCopiaRTRisposta();
										if(res != null) {
											if(res.getFault() != null) {
												org.esed.MyPayPagonet.webservice.operazioni.FaultBean ff = res.getFault();
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode(ff.getFaultCode());
												ffOut.setFaultString(ff.getFaultString());
												ffOut.setId(ff.getId());
												ffOut.setDescription(ff.getDescription());
												ffOut.setSerial(ff.getSerial());
												nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
												bFaultMyPay = true;
												statoRPT = ffOut.getFaultCode();
												info(Events.TRA_ALIGN.format() + " | Risposta chiediEsitoCarrelloDovutiMyPay con Fault");
											} else {
												EsitoChiediEsitoCarrelloDovutiMyPayRisposta esitoMPay  = res.getEsito();
												statoRPT = esitoMPay.getStato();
												identificativoUnivocoVersamentoMyPay = esitoMPay.getIdentificativoUnivocoVersamento();
												if(identificativoUnivocoVersamentoMyPay != null) {
													info(Events.TRA_ALIGN.format() + " | Risposta identificativoUnivocoVersamentoMyPay = " + identificativoUnivocoVersamentoMyPay);
													bFoundIUVMyPay = true;
													identificativoUnivocoVersamento = identificativoUnivocoVersamentoMyPay;
													strPaymentId = identificativoUnivocoVersamento; 
													if (strPaymentId.length() > 4)
														strPaymentId = strPaymentId.substring(4);
													paymentId = new BigInteger(strPaymentId);
												} else {
													info(Events.TRA_ALIGN.format() + " | Risposta identificativoUnivocoVersamentoMyPay = <non presente su response>");
													if(esitoMPay.getRt() != null) {
														//Se lo iuv non è presente sulla response lo prelevo dalla rt
														try {
															DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
															domFactory.setNamespaceAware(true);
															DocumentBuilder builder = domFactory.newDocumentBuilder();
															Document doc = builder.parse( new ByteArrayInputStream(esitoMPay.getRt()));
															
															XPathFactory factory = XPathFactory.newInstance();
															XPath xpath = factory.newXPath();
														
															Node nodeDatiPagamento = (Node) (xpath.evaluate("//*[local-name()='datiPagamento']/*[local-name()='identificativoUnivocoVersamento']", doc, XPathConstants.NODE));
															if(nodeDatiPagamento != null){
																identificativoUnivocoVersamentoMyPay = nodeDatiPagamento.getTextContent();
																info(Events.TRA_ALIGN.format() + " | prelevo da RT identificativoUnivocoVersamento = " + identificativoUnivocoVersamentoMyPay);
																if(identificativoUnivocoVersamentoMyPay != null) {
																	bFoundIUVMyPay = true;
																	identificativoUnivocoVersamento = identificativoUnivocoVersamentoMyPay; 
																	strPaymentId = identificativoUnivocoVersamento; 
																	if (strPaymentId.length() > 4)
																		strPaymentId = strPaymentId.substring(4);
																	paymentId = new BigInteger(strPaymentId);
																}
															}
														} catch (Exception e) {
															error("Impossibile interpretare l'XML della RT inviata per determinare IUV", e);
														}
													}
												}
												nodoChiediCopiaRTRispostaRitenta.setRt(esitoMPay.getRt());
												info(Events.TRA_ALIGN.format() + " | Risposta chiediEsitoCarrelloDovutiMyPay = " + statoRPT);
											}
										} else {
											String description = "chiediEsitoCarrelloDovutiMyPay 99: errore generico";
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode("PAA SYSTEM ERROR");
											ffOut.setFaultString("Errore generico.");
											ffOut.setId(myPayCodiceIpaEnte);
											ffOut.setDescription(description);
											ffOut.setSerial(1);
											nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
											bFaultMyPay = true;
											statoRPT = ffOut.getFaultCode();
										}
										if(bFaultMyPay) {
											String err_msg = "Fault code: " + nodoChiediCopiaRTRispostaRitenta.getFault().getFaultCode() + " Fault String: " + nodoChiediCopiaRTRispostaRitenta.getFault().getFaultString();
											error(err_msg);
										}
									} else {
										//dovrei essere su un modello 3 
										identificativoUnivocoVersamentoMyPay = identificativoUnivocoVersamento.trim();
										if(identificativoUnivocoVersamentoMyPay != null && identificativoUnivocoVersamentoMyPay.length() > 0) {
											bFoundIUVMyPay = true;
											bIsModello3MyPay = true;
											statoRPT = "DA DETERMINARE ATTRAVERSO ChiediPagatiConRicevutaMyPay";
										}
									}
								} else {
								//fine LP PG180290
								if (nodospcGestore.equals("M")) {
									EsedFacetServiceLocator locator = new EsedFacetServiceLocator();
									EsedServicesEndpoint serviceM = locator.getEsedServicesPort(new URL(mybridgeUrl + "/EsedServicesPort"));
									info(Events.TRA_ALIGN.format() + " |  nodoChiediStatoRPT vs Myridge");
									com.esed.mybridge.webservice.operazioni.NodoChiediStatoRPT bodyrichiesta = new com.esed.mybridge.webservice.operazioni.NodoChiediStatoRPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());  
									ParametroVuoto parametroFalso = new ParametroVuoto(""); 
									com.esed.mybridge.webservice.operazioni.NodoChiediStatoRPTRisposta res = serviceM.nodoChiediStatoRPT(bodyrichiesta, parametroFalso);
									
									nodoChiediStatoRPTRisposta = new NodoChiediStatoRPTRisposta();
									if(res != null) {
										if(res.getFault() != null) {
											com.esed.mybridge.webservice.operazioni.FaultBean ff = res.getFault();
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode(ff.getFaultCode());
											ffOut.setFaultString(ff.getFaultString());
											ffOut.setId(ff.getId());
											ffOut.setDescription(ff.getDescription());
											ffOut.setSerial(ff.getSerial());
											nodoChiediStatoRPTRisposta.setFault(ffOut);
										} else {
											com.esed.mybridge.webservice.operazioni.EsitoChiediStatoRPT esitoAgid = res.getEsito();
											EsitoChiediStatoRPT esitoOut = new EsitoChiediStatoRPT();
											esitoOut.setStato(esitoAgid.getStato());
											esitoOut.setUrl(esitoAgid.getUrl());
											esitoOut.setRedirect(esitoAgid.getRedirect());
											com.esed.mybridge.webservice.operazioni.TipoStoricoRPT[] listaTipoAgid = esitoAgid.getElementoStoricoRPT();
											if(listaTipoAgid != null) {
												int ik = listaTipoAgid.length;
												TipoStoricoRPT[] listTipoOut = new TipoStoricoRPT[ik];
												esitoOut.setElementoStoricoRPT(listTipoOut);
												int ii = 0;
												for (com.esed.mybridge.webservice.operazioni.TipoStoricoRPT tipoStoricoRPT : listaTipoAgid) {
													TipoStoricoRPT tipo = new TipoStoricoRPT();
													tipo.setData(tipoStoricoRPT.getData());
													tipo.setDescrizione(tipoStoricoRPT.getDescrizione());
													tipo.setStato(tipoStoricoRPT.getStato());
													esitoOut.setElementoStoricoRPT(ii, tipo);
													ii++;
												}
											}
											com.esed.mybridge.webservice.operazioni.TipoStoricoVersamento[] listaVersamentoAgid = esitoAgid.getElementoStoricoVersamento();
											if(listaVersamentoAgid != null) {
												int ik = listaVersamentoAgid.length;
												TipoStoricoVersamento[] listVersOut = new TipoStoricoVersamento[ik];
												esitoOut.setElementoStoricoVersamento(listVersOut);
												int ii = 0;
												for (com.esed.mybridge.webservice.operazioni.TipoStoricoVersamento tipoVer : listaVersamentoAgid) {
													TipoStoricoVersamento tipo = new TipoStoricoVersamento();
													tipo.setData(tipoVer.getData());
													tipo.setDescrizione(tipoVer.getDescrizione());
													tipo.setStato(tipoVer.getStato());
													tipo.setProgressivo(tipoVer.getProgressivo());
													esitoOut.setElementoStoricoVersamento(ii, tipo);
													ii++;
												}
											}
											nodoChiediStatoRPTRisposta.setEsito(esitoOut);
										}
									} else {
										String description = "nodoChiediStatoRPT to MyBridge: 99: errore generico";
										FaultBean ffOut = new FaultBean();
										ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
										ffOut.setFaultString("Errore generico.");
										ffOut.setId(identificativoDominio);
										ffOut.setDescription(description);
										ffOut.setSerial(1);
										nodoChiediStatoRPTRisposta.setFault(ffOut);
									}
								} else {	
								//fine LP PG1900XX_001 uso MyBridge
								//inizio LP PG1900XX_001 uso EasyBridge
								/*	
									//inizio LP PG1900XX_001
									//PagamentiTelematiciRPT pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoChiediStatoRPT"));
									//inizio LP PG1900XX_001 uso MyBridge
									//PagamentiTelematiciRPT pagamentiTelematiciRPT = null;
									//fine LP PG1900XX_001 uso MyBridge
									
									if(nodospcTipologia.equals("N")) {
										pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain + "/nodoChiediStatoRPT")); 
									} else {
										pagamentiTelematiciRPT = pagamentiTelematiciRPTserviceLocator.getPagamentiTelematiciRPTPort(new URL(nodoSpcPortDomain)); 
										
									}
									//fine LP PG1900XX_001
									PagamentiTelematiciRPTbindingStub _stub = (PagamentiTelematiciRPTbindingStub)pagamentiTelematiciRPT;
									//inizio LP PG1900XX_001
									if(nodospcTipologia.equals("N")) {
									//fine LP PG1900XX_001
										_stub.setUsername(username);
										_stub.setPassword(password);
									//inizio LP PG1900XX_001
									}
									//fine LP PG1900XX_001
									
									HandlerRegistry hr = pagamentiTelematiciRPTserviceLocator.getHandlerRegistry();
									QName  portName = _stub.getPortName();
									List handlerChain = hr.getHandlerChain(portName);
									
									HandlerInfo hi = new HandlerInfo();
									hi.setHandlerClass(WsNodoRpcHandler.class);
									handlerChain.add(hi);
					
									//Chiedo stato RPT
									info(Events.TRA_ALIGN.format() + " |  nodoChiediStatoRPT");
									NodoChiediStatoRPT bodyrichiesta = new NodoChiediStatoRPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());
									//inizio LP PG1900XX_001 uso MyBridge
									//NodoChiediStatoRPTRisposta nodoChiediStatoRPTRisposta = pagamentiTelematiciRPT.nodoChiediStatoRPT(bodyrichiesta);
									nodoChiediStatoRPTRisposta = pagamentiTelematiciRPT.nodoChiediStatoRPT(bodyrichiesta);
									//fine LP PG1900XX_001 uso MyBridge
									//NodoChiediStatoRPTRisposta nodoChiediStatoRPTRisposta = pagamentiTelematiciRPT.nodoChiediStatoRPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());
								*/
									com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator locator = new com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator();
									com.esed.easybridge.webservice.operazioni.EsedServicesEndpoint serviceE = locator.getEsedServicesPort(new URL(easybridgeUrl + "/EsedServicesPort"));
									info(Events.TRA_ALIGN.format() + " |  nodoChiediStatoRPT vs EasyBridge");
									
									com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPT bodyrichiestaE = new com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());
									com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute parametroCuteCute = new com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute(dbSchemaCodSocieta); 
									com.esed.easybridge.webservice.operazioni.NodoChiediStatoRPTRisposta res = serviceE.nodoChiediStatoRPT(bodyrichiestaE, parametroCuteCute);
									
									nodoChiediStatoRPTRisposta = new NodoChiediStatoRPTRisposta();
									if(res != null) {
										if(res.getFault() != null) {
											com.esed.easybridge.webservice.operazioni.FaultBean ff = res.getFault();
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode(ff.getFaultCode());
											ffOut.setFaultString(ff.getFaultString());
											ffOut.setId(ff.getId());
											ffOut.setDescription(ff.getDescription());
											ffOut.setSerial(ff.getSerial());
											nodoChiediStatoRPTRisposta.setFault(ffOut);
										} else {
											com.esed.easybridge.webservice.operazioni.EsitoChiediStatoRPT esitoAgid = res.getEsito();
											EsitoChiediStatoRPT esitoOut = new EsitoChiediStatoRPT();
											esitoOut.setStato(esitoAgid.getStato());
											esitoOut.setUrl(esitoAgid.getUrl());
											esitoOut.setRedirect(esitoAgid.getRedirect());
											com.esed.easybridge.webservice.operazioni.TipoStoricoRPT[] listaTipoAgid = esitoAgid.getElementoStoricoRPT();
											if(listaTipoAgid != null) {
												int ik = listaTipoAgid.length;
												TipoStoricoRPT[] listTipoOut = new TipoStoricoRPT[ik];
												esitoOut.setElementoStoricoRPT(listTipoOut);
												int ii = 0;
												for (com.esed.easybridge.webservice.operazioni.TipoStoricoRPT tipoStoricoRPT : listaTipoAgid) {
													TipoStoricoRPT tipo = new TipoStoricoRPT();
													tipo.setData(tipoStoricoRPT.getData());
													tipo.setDescrizione(tipoStoricoRPT.getDescrizione());
													tipo.setStato(tipoStoricoRPT.getStato());
													esitoOut.setElementoStoricoRPT(ii, tipo);
													ii++;
												}
											}
											com.esed.easybridge.webservice.operazioni.TipoStoricoVersamento[] listaVersamentoAgid = esitoAgid.getElementoStoricoVersamento();
											if(listaVersamentoAgid != null) {
												int ik = listaVersamentoAgid.length;
												TipoStoricoVersamento[] listVersOut = new TipoStoricoVersamento[ik];
												esitoOut.setElementoStoricoVersamento(listVersOut);
												int ii = 0;
												for (com.esed.easybridge.webservice.operazioni.TipoStoricoVersamento tipoVer : listaVersamentoAgid) {
													TipoStoricoVersamento tipo = new TipoStoricoVersamento();
													tipo.setData(tipoVer.getData());
													tipo.setDescrizione(tipoVer.getDescrizione());
													tipo.setStato(tipoVer.getStato());
													tipo.setProgressivo(tipoVer.getProgressivo());
													esitoOut.setElementoStoricoVersamento(ii, tipo);
													ii++;
												}
											}
											nodoChiediStatoRPTRisposta.setEsito(esitoOut);
										}
									} else {
										String description = "nodoChiediStatoRPT to EasyBridge: 99: errore generico";
										FaultBean ffOut = new FaultBean();
										ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
										ffOut.setFaultString("Errore generico.");
										ffOut.setId(identificativoDominio);
										ffOut.setDescription(description);
										ffOut.setSerial(1);
										nodoChiediStatoRPTRisposta.setFault(ffOut);
									}
								//fine LP PG1900XX_001 uso EasyBridge
								//inizio LP PG1900XX_001 uso MyBridge
								}
								//fine LP PG1900XX_001 uso MyBridge
								//inizio LP PG180290
								//String statoRPT;
								}
								if(!bIsMyPay) {
								//fine LP PG180290
								if (nodoChiediStatoRPTRisposta.getFault() != null && nodoChiediStatoRPTRisposta.getFault().getFaultCode() != null && nodoChiediStatoRPTRisposta.getFault().getFaultCode() != ""){
									String err_msg = "Fault code: " + nodoChiediStatoRPTRisposta.getFault().getFaultCode() + " Fault String: " + nodoChiediStatoRPTRisposta.getFault().getFaultString();
									error(err_msg);
									statoRPT = nodoChiediStatoRPTRisposta.getFault().getFaultCode();
								}
								else{ //Chiamata effettuata con successo 
									statoRPT = nodoChiediStatoRPTRisposta.getEsito().getStato();
								}
								//fine LP PG1900XX_001 uso MyBridge
								//inizio LP PG180290
								}
								//fine LP PG180290

								info(Events.TRA_ALIGN.format() + " |  Risposta STATO statoRPT = " + statoRPT);
								System.out.println(Events.TRA_ALIGN.format() + " |  Risposta STATO statoRPT = " + statoRPT);
								//recupero la riga RPT corrispondente
								String chiaveTransazione = "";
								String esitoTransazione = "0";
								String codAutorizzazioneBanca = "";  //!!!!QUESTA INFORMAZIONE NON MI ARRIVA DAL METODO nodoChiediStatoRPT
								String codIdBanca = "";  //!!!!QUESTA INFORMAZIONE NON MI ARRIVA DAL METODO nodoChiediStatoRPT
								Calendar dataPagamento = Calendar.getInstance();  //!!!!QUESTA INFORMAZIONE NON MI ARRIVA DAL METODO nodoChiediStatoRPT
								
								// se per qualche motivo in precedenza ho avuto un errore di mancata ricezione RT, il NODO risponde con 
								// RT_RIFIUTATA_PA e per questo motivo devo richiedere copia RT e poi procedere con la richiesta di stato
								//inizio LP PG180290
								//if(statoRPT.equalsIgnoreCase("RT_RIFIUTATA_PA") || statoRPT.equalsIgnoreCase("RT_ERRORE_INVIO_A_PA") || statoRPT.equalsIgnoreCase("RPT_ACCETTATA_PSP") || statoRPT.equalsIgnoreCase("RT_RIFIUTATA_PA") || statoRPT.equalsIgnoreCase("RT_ACCETTATA_NODO") || statoRPT.equalsIgnoreCase("RT_ESITO_SCONOSCIUTO_PA") || statoRPT.equalsIgnoreCase("RT_GENERATA_NODO")) { //KO
								

								if(bIsMyPay ||
								   statoRPT.equalsIgnoreCase("RT_RIFIUTATA_PA") ||
								   statoRPT.equalsIgnoreCase("RT_ERRORE_INVIO_A_PA") ||
								   statoRPT.equalsIgnoreCase("RPT_ACCETTATA_PSP") ||
								   statoRPT.equalsIgnoreCase("RT_RIFIUTATA_PA") ||
								   statoRPT.equalsIgnoreCase("RT_ACCETTATA_NODO") ||
								   statoRPT.equalsIgnoreCase("RT_ESITO_SCONOSCIUTO_PA") ||
								   statoRPT.equalsIgnoreCase("RT_GENERATA_NODO") ||
								   (statoRPT.equalsIgnoreCase("RPT_RIFIUTATA_NODO") && dbSchemaCodSocieta.equals("000RM"))	//05082021 GG inserito per segnalazioni su CURMIT
								   ) { //KO
									
									if(!bIsMyPay) {
								//fine LP PG180290
									
									//Richiedo copia RT per sbloccare il NODO da questo errore e richiedo lo stato
									info(Events.TRA_ALIGN.format() + " |  Richiedo copia RT perchè ho ricevuto RT_RIFIUTATA_PA o RT_ACCETTATA_NODO o RT_ESITO_SCONOSCIUTO_PA o RPT_ACCETTATA_PSP o RT_ERRORE_INVIO_A_PA o RT_GENERATA_NODO");
									info(Events.TRA_ALIGN.format() + " |  identificativoIntermediarioPA = " + identificativoIntermediarioPA.trim());
									info(Events.TRA_ALIGN.format() + " |  identificativoStazioneIntermediarioPA = " + identificativoStazioneIntermediarioPA.trim());
									info(Events.TRA_ALIGN.format() + " |  passwordPsp() = " + passwordPsp);
									info(Events.TRA_ALIGN.format() + " |  identificativoDominio = " + identificativoDominio.trim());
									info(Events.TRA_ALIGN.format() + " |  identificativoUnivocoVersamento = " + identificativoUnivocoVersamento.trim());
									info(Events.TRA_ALIGN.format() + " |  codiceContestoPagamento = " + codiceContestoPagamento.trim());
									
									//inizio LP PG1900XX_001 uso MyBridge
									//inizio LP PG180290
									//NodoChiediCopiaRTRisposta nodoChiediCopiaRTRispostaRitenta = null;
									//fine LP PG180290
									if (nodospcGestore.equals("M")) {
										EsedFacetServiceLocator locator = new EsedFacetServiceLocator();
										EsedServicesEndpoint serviceM = locator.getEsedServicesPort(new URL(mybridgeUrl + "/EsedServicesPort"));
										info(Events.TRA_ALIGN.format() + " |  nodoChiediCopiaRT vs Myridge");
										com.esed.mybridge.webservice.operazioni.NodoChiediCopiaRT bodyrichiesta = new com.esed.mybridge.webservice.operazioni.NodoChiediCopiaRT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());  
										ParametroVuoto parametroFalso = new ParametroVuoto(""); 
										com.esed.mybridge.webservice.operazioni.NodoChiediCopiaRTRisposta res = serviceM.nodoChiediCopiaRT(bodyrichiesta, parametroFalso);
										nodoChiediCopiaRTRispostaRitenta = new NodoChiediCopiaRTRisposta();
										if(res != null) {
											if(res.getFault() != null) {
												com.esed.mybridge.webservice.operazioni.FaultBean ff = res.getFault();
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode(ff.getFaultCode());
												ffOut.setFaultString(ff.getFaultString());
												ffOut.setId(ff.getId());
												ffOut.setDescription(ff.getDescription());
												ffOut.setSerial(ff.getSerial());
												nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
											} else {
												nodoChiediCopiaRTRispostaRitenta.setRt(res.getRt());
												nodoChiediCopiaRTRispostaRitenta.setTipoFirma(res.getTipoFirma());
											}
										} else {
											String description = "nodoChiediCopiaRT to MyBridge: 99: errore generico";
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
											ffOut.setFaultString("Errore generico.");
											ffOut.setId(identificativoDominio);
											ffOut.setDescription(description);
											ffOut.setSerial(1);
											nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
										}
									} else {
									//fine LP PG1900XX_001 uso MyBridge
									//inizio LP PG1900XX_001 uso EasyBridge
									/*	
										NodoChiediCopiaRT bodyrichiestaCopiaRT = new NodoChiediCopiaRT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());
										//inizio LP PG1900XX_001 uso MyBridge
										//NodoChiediCopiaRTRisposta nodoChiediCopiaRTRispostaRitenta = pagamentiTelematiciRPT.nodoChiediCopiaRT(bodyrichiestaCopiaRT);
										nodoChiediCopiaRTRispostaRitenta = pagamentiTelematiciRPT.nodoChiediCopiaRT(bodyrichiestaCopiaRT);
										//fine LP PG1900XX_001 uso MyBridge
									*/
										com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator locator = new com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator();
										com.esed.easybridge.webservice.operazioni.EsedServicesEndpoint serviceE = locator.getEsedServicesPort(new URL(easybridgeUrl + "/EsedServicesPort"));
										info(Events.TRA_ALIGN.format() + " |  nodoChiediCopiaRT vs EasyBridge");
										
										com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRT bodyrichiestaE = new com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());  
										com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute parametroCuteCute = new com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute(dbSchemaCodSocieta); 
										com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRTRisposta res = serviceE.nodoChiediCopiaRT(bodyrichiestaE, parametroCuteCute);
										nodoChiediCopiaRTRispostaRitenta = new NodoChiediCopiaRTRisposta();
										if(res != null) {
											if(res.getFault() != null) {
												com.esed.easybridge.webservice.operazioni.FaultBean ff = res.getFault();
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode(ff.getFaultCode());
												ffOut.setFaultString(ff.getFaultString());
												ffOut.setId(ff.getId());
												ffOut.setDescription(ff.getDescription());
												ffOut.setSerial(ff.getSerial());
												nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
											} else {
												nodoChiediCopiaRTRispostaRitenta.setRt(res.getRt());
												nodoChiediCopiaRTRispostaRitenta.setTipoFirma(res.getTipoFirma());
											}
										} else {
											String description = "nodoChiediCopiaRT to EasyBridge: 99: errore generico";
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
											ffOut.setFaultString("Errore generico.");
											ffOut.setId(identificativoDominio);
											ffOut.setDescription(description);
											ffOut.setSerial(1);
											nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
										}
									//fine LP PG1900XX_001 uso EasyBridge
									//inizio LP PG1900XX_001 uso MyBridge
									}
									//fine LP PG1900XX_001 uso MyBridge
									//inizio LP PG180290
									} else {
										//Questa chiamata da rispetto alla chiediEsitoCarrelloDovutiMyPay
										//solo l'informazione in più tipoFirma (che se valorizzato può valere "CADES" o "XADES")
										//La rt per il modello 1 se presente è già ottenuta dalla chiamanta a chiediEsitoCarrelloDovutiMyPay
										if(bFoundIUVMyPay || bCheckIUDMyPay) {
											info(Events.TRA_ALIGN.format() + " |  eseguo ChiediPagatiConRicevutaMyPay");
											info(Events.TRA_ALIGN.format() + " |  identificativoUnivocoVersamentoMyPay = " + identificativoUnivocoVersamentoMyPay);
											//la ricerca può essere fatta o per identificativoUnivocoDovuto o per identificativoUnivocoVersamento
											ChiediPagatiConRicevutaMyPay bodyrichiestaMyPay = new ChiediPagatiConRicevutaMyPay();
											bodyrichiestaMyPay.setPassword(myPayPasswordEnte);
											if(bFoundIUVMyPay) {
												bodyrichiestaMyPay.setIdentificativoUnivocoVersamento(identificativoUnivocoVersamentoMyPay);
											} else {
												bodyrichiestaMyPay.setIdentificativoUnivocoDovuto(identificativoUnivocoDovutoMyPay);
											}
											org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT headerMyPay = new org.esed.MyPayPagonet.webservice.operazioni.head.IntestazionePPT();
											headerMyPay.setCodIpaEnte(myPayCodiceIpaEnte);
											ChiediPagatiConRicevutaMyPayRisposta res =  serviceMyPay.chiediPagatiConRicevutaMyPay(bodyrichiestaMyPay, headerMyPay);
											nodoChiediCopiaRTRispostaRitenta = new NodoChiediCopiaRTRisposta();
											if(res != null) {
												if(res.getFault() != null) {
													org.esed.MyPayPagonet.webservice.operazioni.FaultBean ff = res.getFault();
													FaultBean ffOut = new FaultBean();
													ffOut.setFaultCode(ff.getFaultCode());
													ffOut.setFaultString(ff.getFaultString());
													ffOut.setId(ff.getId());
													ffOut.setDescription(ff.getDescription());
													ffOut.setSerial(ff.getSerial());
													statoRPT = ffOut.getFaultCode();
													nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
													info(Events.TRA_ALIGN.format() + " |  Risposta ChiediPagatiConRicevutaMyPay con Fault");
													info(Events.TRA_ALIGN.format() + " |  Risposta ChiediPagatiConRicevutaMyPay fault code: " + statoRPT);
												} else {
													nodoChiediCopiaRTRispostaRitenta.setRt(res.getRt());
													nodoChiediCopiaRTRispostaRitenta.setTipoFirma(res.getTipoFirma());
													info(Events.TRA_ALIGN.format() + " |  Risposta ChiediPagatiConRicevutaMyPay tipofirma: " + res.getTipoFirma());
												}
											} else {
												String description = "chiediPagatiConRicevutaMyPay 99: errore generico";
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode("PAA SYSTEM ERROR");
												ffOut.setFaultString("Errore generico.");
												ffOut.setId(myPayCodiceIpaEnte);
												ffOut.setDescription(description);
												ffOut.setSerial(1);
												statoRPT = ffOut.getFaultCode();
												nodoChiediCopiaRTRispostaRitenta.setFault(ffOut);
											}
										}
									}
									//fine LP PG180290
									
									byte[] rtRitenta = nodoChiediCopiaRTRispostaRitenta.getRt();
									if (rtRitenta != null){
										try {
											DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
											domFactory.setNamespaceAware(true);
											DocumentBuilder builder = domFactory.newDocumentBuilder();
											Document doc = builder.parse( new ByteArrayInputStream(rtRitenta));
											
											XPathFactory factory = XPathFactory.newInstance();
											XPath xpath = factory.newXPath();

											Node nodeEsito = (Node)(xpath.evaluate("//*[local-name()='codiceEsitoPagamento']", doc, XPathConstants.NODE));
											if(nodeEsito != null) {
												esitoPagamento = nodeEsito.getTextContent();
											}
											
											// Identificativo PSP
											String idAttestante ="";
											Node nodeIdentificativoAttestante = (Node) (xpath.evaluate("//*[local-name()='istitutoAttestante']/*[local-name()='identificativoUnivocoAttestante']/*[local-name()='codiceIdentificativoUnivoco']", doc, XPathConstants.NODE));
											if(nodeIdentificativoAttestante != null){
												idAttestante=nodeIdentificativoAttestante.getTextContent();
												rptNodoSpc.setIdPSP(idAttestante);
											}
											// Descrizione PSP
											String descAttestante ="";
											Node nodeDenominazioneAttestante = (Node) (xpath.evaluate("//*[local-name()='istitutoAttestante']/*[local-name()='denominazioneAttestante']", doc, XPathConstants.NODE));
											if(nodeDenominazioneAttestante != null){
												descAttestante=nodeDenominazioneAttestante.getTextContent();
											}

											info(Events.TRA_ALIGN.format() + " |  esitoPagamento RT = " + esitoPagamento);

											//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
											//rptNodoSpc.setRt(new String(rtRitenta));
											rptNodoSpc.setRt(new String(rtRitenta, "UTF-8"));
											//fine LP 20210325
											rptNodoSpc.setRtEsito(esitoPagamento);
											//inizio LP PG1890290
											//Aggiorno lo iuv con quello generato da MyPay
											if(bIsMyPay && !bIsModello3MyPay) {
												if(bFoundIUVMyPay) {
													//Inserisco iuv generato da MyPay
													rptNodoSpc.setCodiceIuv(identificativoUnivocoVersamentoMyPay);
													identificativoUnivocoVersamento = identificativoUnivocoVersamentoMyPay;
													strPaymentId = identificativoUnivocoVersamento; 
													if (strPaymentId.length() > 4)
														strPaymentId = strPaymentId.substring(4);
													paymentId = new BigInteger(strPaymentId);
												} else {
													//Prelevo lo iuv dalla rt
													String identificativoUnivocoVersamentoLoc = "";
													Node nodeDatiPagamento = (Node) (xpath.evaluate("//*[local-name()='datiPagamento']/*[local-name()='identificativoUnivocoVersamento']", doc, XPathConstants.NODE));
													if(nodeDatiPagamento != null){
														identificativoUnivocoVersamentoLoc = nodeDatiPagamento.getTextContent();
														info(Events.TRA_ALIGN.format() + " |  identificativoUnivocoVersamentoLoc = " + identificativoUnivocoVersamentoLoc);
														rptNodoSpc.setCodiceIuv(identificativoUnivocoVersamentoLoc);
														identificativoUnivocoVersamento = identificativoUnivocoVersamentoLoc;
														strPaymentId = identificativoUnivocoVersamento; 
														if (strPaymentId.length() > 4)
															strPaymentId = strPaymentId.substring(4);
														paymentId = new BigInteger(strPaymentId);
													}
												}
											}
											//fine LP PG1890290
											UpdateRptNodoSpcRequest inUpdateRPT = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpc );
											binding.updateRptNodoSpc(inUpdateRPT );

											AggiornaPSNRequest aggiornaPSNRequest = new AggiornaPSNRequest(idAttestante, descAttestante);

											//inizio LP PG180290
											//TODO: lo devo fare anche nel caso MyPay ?
											if(!bIsMyPay) {
											//fine LP PG180290
											//PG190180_001 - inizio - 20190521
											if(dbSchemaCodSocieta.equals("000P5")){
												if(idAttestante!= null && descAttestante != null){
													AggiornaPSNResponse aggiornaPSNResponse = binding.inserisciPSN(aggiornaPSNRequest);
													if(!aggiornaPSNResponse.isUpdate()){
														info("Errore: non è stato possibile inserire il PSP");
													}
												}
											}
											//PG190180_001 - fine - 20190521
											//inizio LP PG180290
											}
											//fine LP PG180290

											info(Events.TRA_ALIGN.format() + " |  RT esitoPagamento " + esitoPagamento);
											info(Events.TRA_ALIGN.format() + " |  RT salvata correttamente ");
											if(esitoPagamento.equals("0"))
											{
												esitoTransazione = "1";
											}
											else
											{
												esitoTransazione = "2";
											}
											info(Events.TRA_ALIGN.format() + " |  esitoTransazione = " + esitoTransazione);
											
											Node nodeCodAutorizzazioneBanca = (Node)(xpath.evaluate("//*[local-name()='identificativoMessaggioRicevuta']", doc, XPathConstants.NODE));
											if(nodeCodAutorizzazioneBanca != null) {
												codAutorizzazioneBanca = nodeCodAutorizzazioneBanca.getTextContent();
											}
										
											Node nodeCodIdBanca = (Node)(xpath.evaluate("//*[local-name()='datiPagamento']/*[local-name()='datiSingoloPagamento']/*[local-name()='identificativoUnivocoRiscossione']", doc, XPathConstants.NODE));
											if(nodeCodIdBanca != null) {
												codIdBanca = nodeCodIdBanca.getTextContent();
											}
										
											Node nodeDataPagamento = (Node)(xpath.evaluate("//*[local-name()='dataOraMessaggioRicevuta']", doc, XPathConstants.NODE));
											if(nodeDataPagamento != null) {
												String dataPagamentoStr = nodeDataPagamento.getTextContent();
												SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //2014-03-24T16:35:19
												dataPagamento.setTime(sdf.parse(dataPagamentoStr));
											}
											
											
											// Salva sulla tabella PYMPNTB per la loggattura visibile sul dettaglio del monitoraggio nodo
											MIPSOAPBidingStub mipBinding = null;
											try {
												// we initialize commonsSOAPBindingStub
												mipBinding = GatewaysIGHelper.getMIPSOAPBidingStub(propertiesTree(), dbSchemaCodSocieta);
												info(Events.TRA_ALIGN.format() + " |  inizio savePaymentNodoStatus ");
												//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
												//ModuloIntegrazionePagamentiNodoPaymentStatus mpn = new ModuloIntegrazionePagamentiNodoPaymentStatus(in.getTransazione().getChiaveTransazione(), paymentId.intValue(), 
												//		0, "M", "", identificativoIntermediarioPA, identificativoUnivocoVersamento, esitoPagamento, new String(rtRitenta), "",
												//		"", "", "", "", "", Calendar.getInstance(), "", "", "", "", identificativoDominio);
												ModuloIntegrazionePagamentiNodoPaymentStatus mpn = new ModuloIntegrazionePagamentiNodoPaymentStatus(in.getTransazione().getChiaveTransazione(), paymentId.intValue(), 
														0, "M", "", identificativoIntermediarioPA, identificativoUnivocoVersamento, esitoPagamento, new String(rtRitenta, "UTF-8"), "",
														"", "", "", "", "", Calendar.getInstance(), "", "", "", "", identificativoDominio);
												//fine LP 20210325
												MINSavePaymentStatusRequest inMINSavePaymentStatusRequest = new MINSavePaymentStatusRequest(mpn );
												mipBinding.savePaymentNodoStatus(inMINSavePaymentStatusRequest );
												info(Events.TRA_ALIGN.format() + " |  fine savePaymentNodoStatus ");
												
											} catch (Exception e) {
												info(Events.TRA_ALIGN.format() + " |  mipBinding.savePaymentNodoStatus non riuscito: " + e.getMessage());
												error("integraGTWAsinc - mipBinding.savePaymentNodoStatus non riuscito", e);
												
											}
											
										}
										catch(Exception xmlEx) {
											error("Impossibile interpretare l'XML della RT inviata", xmlEx);
										}
									}
									else{
										//inizio LP PG180290
										if(!bIsMyPay) {
										//fine LP PG180290
										info(Events.TRA_ALIGN.format() + " |  nodoChiediCopiaRTRispostaRitenta.getRt() = null");
										//RT non ricevuta
										return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, 
												"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " unsuccessfully, RT non disponibile nel Nodo per la transazione corrente"), 
												TRA_FAILED_STATE, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
												beanTransazioni.getData_effettivo_pagamento_su_gateway(), statoRPT);
										//inizio LP PG180290
										}
										//fine LP PG180290

									}
									
								}
								
								if(statoRPT.equalsIgnoreCase("RPT_RIFIUTATA_NODO")) { //KO
									if (!dbSchemaCodSocieta.equals("000RM") || esitoTransazione.equals("0")) {	//05082021 GG inserito per segnalazioni CURMIT
										esitoTransazione = "2";
									}
								}
								else if(statoRPT.equalsIgnoreCase("RPT_RIFIUTATA_PSP")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("PPT_RPT_SCONOSCIUTA")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("PPT_PSP_SCONOSCIUTO")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("RPT_ERRORE_INVIO_A_PSP")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("PPT_CANALE_SCONOSCIUTO")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("PPT_DOMINIO_SCONOSCIUTO")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("PPT_STAZIONE_INT_PA_SCONOSCIUTA")) { //KO
									esitoTransazione = "2";
								}
								else if(statoRPT.equalsIgnoreCase("RT_ACCETTATA_PA")) { //OK
									esitoTransazione = "1";
									
									//Richiedo copia RT
									info(Events.TRA_ALIGN.format() + " |  Richiedo copia RT");

									
									//inizio LP PG1900XX_001 uso MyBridge
									NodoChiediCopiaRTRisposta nodoChiediCopiaRTRisposta = null;
									if (nodospcGestore.equals("M")) {
										EsedFacetServiceLocator locator = new EsedFacetServiceLocator();
										EsedServicesEndpoint serviceM = locator.getEsedServicesPort(new URL(mybridgeUrl + "/EsedServicesPort"));
										info(Events.TRA_ALIGN.format() + " |  nodoChiediCopiaRT vs Myridge");
										com.esed.mybridge.webservice.operazioni.NodoChiediCopiaRT bodyrichiesta = new com.esed.mybridge.webservice.operazioni.NodoChiediCopiaRT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());  
										ParametroVuoto parametroFalso = new ParametroVuoto(""); 
										com.esed.mybridge.webservice.operazioni.NodoChiediCopiaRTRisposta res = serviceM.nodoChiediCopiaRT(bodyrichiesta, parametroFalso);
										nodoChiediCopiaRTRisposta = new NodoChiediCopiaRTRisposta();
										if(res != null) {
											if(res.getFault() != null) {
												com.esed.mybridge.webservice.operazioni.FaultBean ff = res.getFault();
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode(ff.getFaultCode());
												ffOut.setFaultString(ff.getFaultString());
												ffOut.setId(ff.getId());
												ffOut.setDescription(ff.getDescription());
												ffOut.setSerial(ff.getSerial());
												nodoChiediCopiaRTRisposta.setFault(ffOut);
											} else {
												nodoChiediCopiaRTRisposta.setRt(res.getRt());
												nodoChiediCopiaRTRisposta.setTipoFirma(res.getTipoFirma());
											}
										} else {
											String description = "nodoChiediCopiaRT to MyBridge: 99: errore generico";
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
											ffOut.setFaultString("Errore generico.");
											ffOut.setId(identificativoDominio);
											ffOut.setDescription(description);
											ffOut.setSerial(1);
											nodoChiediCopiaRTRisposta.setFault(ffOut);
										}
									} else {
									//fine LP PG1900XX_001 uso MyBridge
									//inizio LP PG1900XX_001 uso EasyBridge
									/*
										NodoChiediCopiaRT bodyrichiestaCopiaRT = new NodoChiediCopiaRT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());
										//inizio LP PG1900XX_001 uso MyBridge
										//NodoChiediCopiaRTRisposta nodoChiediCopiaRTRisposta = pagamentiTelematiciRPT.nodoChiediCopiaRT(bodyrichiestaCopiaRT);
										nodoChiediCopiaRTRisposta = pagamentiTelematiciRPT.nodoChiediCopiaRT(bodyrichiestaCopiaRT);
										//fine LP PG1900XX_001 uso MyBridge
									*/
										com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator locator = new com.esed.easybridge.webservice.operazioni.EsedFacetServiceLocator();
										com.esed.easybridge.webservice.operazioni.EsedServicesEndpoint serviceE = locator.getEsedServicesPort(new URL(easybridgeUrl + "/EsedServicesPort"));
										info(Events.TRA_ALIGN.format() + " |  nodoChiediCopiaRT vs EasyBridge");
										com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRT bodyrichiestaE = new com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRT(identificativoIntermediarioPA.trim(), identificativoStazioneIntermediarioPA.trim(), passwordPsp, identificativoDominio.trim(), identificativoUnivocoVersamento.trim(), codiceContestoPagamento.trim());  
										com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute parametroCuteCute = new com.esed.easybridge.webservice.operazioni.head.ParametroCuteCute(dbSchemaCodSocieta); 
										com.esed.easybridge.webservice.operazioni.NodoChiediCopiaRTRisposta res = serviceE.nodoChiediCopiaRT(bodyrichiestaE, parametroCuteCute);
										nodoChiediCopiaRTRisposta = new NodoChiediCopiaRTRisposta();
										if(res != null) {
											if(res.getFault() != null) {
												com.esed.easybridge.webservice.operazioni.FaultBean ff = res.getFault();
												FaultBean ffOut = new FaultBean();
												ffOut.setFaultCode(ff.getFaultCode());
												ffOut.setFaultString(ff.getFaultString());
												ffOut.setId(ff.getId());
												ffOut.setDescription(ff.getDescription());
												ffOut.setSerial(ff.getSerial());
												nodoChiediCopiaRTRisposta.setFault(ffOut);
											} else {
												nodoChiediCopiaRTRisposta.setRt(res.getRt());
												nodoChiediCopiaRTRisposta.setTipoFirma(res.getTipoFirma());
											}
										} else {
											String description = "nodoChiediCopiaRT to EasyBridge: 99: errore generico";
											FaultBean ffOut = new FaultBean();
											ffOut.setFaultCode("PPT_SYSTEM_ERROR"); //PAA_SYSTEM_ERROR
											ffOut.setFaultString("Errore generico.");
											ffOut.setId(identificativoDominio);
											ffOut.setDescription(description);
											ffOut.setSerial(1);
											nodoChiediCopiaRTRisposta.setFault(ffOut);
										}
									//fine LP PG1900XX_001 uso EasyBridge
									//inizio LP PG1900XX_001 uso MyBridge
									}
									//fine LP PG1900XX_001 uso MyBridge
									byte[] rt = nodoChiediCopiaRTRisposta.getRt();

									if (rt != null){
										//recupero la riga RPT corrispondente
//											String chiaveTransazione = "";
//											String esitoTransazione = "";
//											String codAutorizzazioneBanca = "";
//											String codIdBanca = "";
//											Calendar dataPagamento = Calendar.getInstance();
										
										try {
											DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
											domFactory.setNamespaceAware(true);
											DocumentBuilder builder = domFactory.newDocumentBuilder();
											Document doc = builder.parse( new ByteArrayInputStream(rt));
											
											XPathFactory factory = XPathFactory.newInstance();
											XPath xpath = factory.newXPath();

											Node nodeEsito = (Node)(xpath.evaluate("//*[local-name()='codiceEsitoPagamento']", doc, XPathConstants.NODE));
											if(nodeEsito != null) {
												esitoPagamento = nodeEsito.getTextContent();
//														0 Pagamento eseguito
//														1 Pagamento non eseguito
//														2 Pagamento parzialmente eseguito
//														3 Decorrenza termini
//														4 Decorrenza termini parziale
											}
											
											if(esitoPagamento.equals("0"))
												esitoTransazione = "1";
											else
												esitoTransazione = "2";

											Node nodeCodAutorizzazioneBanca = (Node)(xpath.evaluate("//*[local-name()='identificativoMessaggioRicevuta']", doc, XPathConstants.NODE));
											if(nodeCodAutorizzazioneBanca != null) {
												codAutorizzazioneBanca = nodeCodAutorizzazioneBanca.getTextContent();
											}
											
											Node nodeCodIdBanca = (Node)(xpath.evaluate("//*[local-name()='datiPagamento']/*[local-name()='datiSingoloPagamento']/*[local-name()='identificativoUnivocoRiscossione']", doc, XPathConstants.NODE));
											if(nodeCodIdBanca != null) {
												codIdBanca = nodeCodIdBanca.getTextContent();
											}
											
											Node nodeDataPagamento = (Node)(xpath.evaluate("//*[local-name()='dataOraMessaggioRicevuta']", doc, XPathConstants.NODE));
											if(nodeDataPagamento != null) {
												String dataPagamentoStr = nodeDataPagamento.getTextContent();
												SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //2014-03-24T16:35:19
												dataPagamento.setTime(sdf.parse(dataPagamentoStr));
											}

											// Salva sulla tabella PYMPNTB per la loggattura visibile sul dettaglio del monitoraggio nodo
											MIPSOAPBidingStub mipBinding = null;
											try {
												// we initialize commonsSOAPBindingStub
												mipBinding = GatewaysIGHelper.getMIPSOAPBidingStub(propertiesTree(), dbSchemaCodSocieta);
												//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
												//ModuloIntegrazionePagamentiNodoPaymentStatus mpn = new ModuloIntegrazionePagamentiNodoPaymentStatus(in.getTransazione().getChiaveTransazione(), paymentId.intValue(), 
												//		0, "M", "", identificativoIntermediarioPA, identificativoUnivocoVersamento, esitoPagamento, new String(rt), "",
												//		"", "", "", "", "", Calendar.getInstance(), "", "", "", "", identificativoDominio);
												ModuloIntegrazionePagamentiNodoPaymentStatus mpn = new ModuloIntegrazionePagamentiNodoPaymentStatus(in.getTransazione().getChiaveTransazione(), paymentId.intValue(), 
														0, "M", "", identificativoIntermediarioPA, identificativoUnivocoVersamento, esitoPagamento, new String(rt, "UTF-8"), "",
														"", "", "", "", "", Calendar.getInstance(), "", "", "", "", identificativoDominio);
												//fine LP 20210325
												MINSavePaymentStatusRequest inMINSavePaymentStatusRequest = new MINSavePaymentStatusRequest(mpn );
												mipBinding.savePaymentNodoStatus(inMINSavePaymentStatusRequest );
												
											} catch (Exception e) {
												error("integraGTWAsinc - mipBinding.savePaymentNodoStatus non riuscito", e);
											}
											
										}
										catch(Exception xmlEx) {
											error("Impossibile interpretare l'XML della RT inviata", xmlEx);
										}
											
									} //Fine RT != null
									else {
										//RT non ricevuta
										return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, 
												"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " unsuccessfully, RT non disponibile nel Nodo per la transazione corrente"), 
												TRA_FAILED_STATE, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
												beanTransazioni.getData_effettivo_pagamento_su_gateway(),statoRPT);
									}
									//FINE Richiedo copia RT

									
									
								}
								//inizio LP PG180290
								/*
								fault paaSILChiediEsitoCarrelloDovuti
								
								PAA_ENTE_NON_VALIDO
								PAA_ID_SESSION_NON_VALIDO
								PAA SYSTEM ERROR
																==> esitoTransazione = "2" ==> KO

								fault per paaSILChiediPagatiConRicevuta
								
								PAA_ENTE_NON_VALIDO					codice IPA Ente non valido o password errata
								PAA_ID_SESSION_NON_VALIDO			Parametro ‘idSession’ non valido
								PAA_IUV_NON_VALIDO					Parametro ‘identificativoUnivocoVersamento’ non valido
								PAA_IUD_NON_VALIDO					Parametro ‘identificativoUnivocoDovuto’ non valido
								PAA SYSTEM ERROR					Errore generico

																==> esitoTransazione = "2" ==> KO


								PAA_PAGAMENTO_NON_INIZIATO			Il pagamento non è ancora iniziato, l’utente non ha ancora raggiunto
								                                    MyPay oppure sta navigando le pagine di MyPay ma non ha ancora raggiunto
								                                    il portale del WISP/PSP. La RPT non è ancora stata inviata.
								                                    
								PAA_PAGAMENTO_IN_CORSO				Il pagamento risulta in corso. La RPT è stata inviata al nodo e l’utente
								                                    ha abbandonato MyPay e rediretto sul WISP/PSP.
								                                    
								                                 ==> esitoTransazione = "0" ==> Pending
								                                    
								PAA_PAGAMENTO_ANNULLATO				L’utente ha abbandonato la procedura di pagamento durante la navigazione
								                                    delle pagine di MyPay, prima di arrivare sul WISP/PSP e quindi prima di
								                                    inviare la RPT al nodo. Da considerarsi come esito negativo del pagamento.

								PAA_PAGAMENTO_SCADUTO				Il dovuto caricato è rimasto in stato PAA_PAGAMENTO_NON_INIZIATO
								                                    per più di 15 minuti. Da considerarsi come esito negativo del pagamento.
								                                    
								                                 ==> esitoTransazione = "2" ==> K0
								*/
								
								//fine LP PG1890290
								else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA_ENTE_NON_VALIDO")) {
									esitoTransazione = "2";
								} else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA_ID_SESSION_NON_VALIDO")) {
									esitoTransazione = "2";
								} else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA_IUV_NON_VALIDO")) {
									esitoTransazione = "2";
								} else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA_IUD_NON_VALIDO")) {
									esitoTransazione = "2";
								} else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA SYSTEM ERROR")) {
									esitoTransazione = "2";
								} else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA_PAGAMENTO_ANNULLATO")) {
									esitoTransazione = "2";
								} else if(bIsMyPay && statoRPT.equalsIgnoreCase("PAA_PAGAMENTO_SCADUTO")) {
									esitoTransazione = "2";
								}
								//fine LP PG180290
								else { //Pending...
									
								}
								//rptNodoSpc.setRt(new String(rt));
								//rptNodoSpc.setRtEsito(esitoPagamento);
								//UpdateRptNodoSpcRequest updateRptNodoSpcRequest = new UpdateRptNodoSpcRequest(dbSchemaCodSocieta, rptNodoSpc);
								//binding.updateRptNodoSpc(updateRptNodoSpcRequest);
								
								chiaveTransazione = rptNodoSpc.getChiaveTra();
								info(Events.TRA_ALIGN.format() + " | Aggiorno chiaveTransazione = " + chiaveTransazione);
								info(Events.TRA_ALIGN.format() + " | Aggiorno codAutorizzazioneBanca = " + codAutorizzazioneBanca);
								info(Events.TRA_ALIGN.format() + " | Aggiorno codIdBanca = " + codIdBanca);
								info(Events.TRA_ALIGN.format() + " |   esitoTransazione = " + esitoTransazione);
								
								
								
								
								
								
								
								try {
									//esito 1 per ok oppure 2 per KO
									AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(chiaveTransazione, esitoTransazione, codAutorizzazioneBanca, codIdBanca, dataPagamento);
									// we call method aggiornaEsitoTransazione
									AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResponse = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
									
									ResponseType responseType= null;
									if(aggiornaEsitoTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1)) {
										responseType = new ResponseType(ResponseTypeRetCode.value1, 
												"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " successfully");
									}
									else {
										responseType = new ResponseType(ResponseTypeRetCode.value2, 
											"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " unsuccessfully," + aggiornaEsitoTransazioneResponse.getResponse().getRetMessage());
									}
									
									return new IntegraGTWAsincResponse(responseType,
											(aggiornaEsitoTransazioneResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
											? TRA_COMPLETED_STATE : TRA_FAILED_STATE), 
													codAutorizzazioneBanca, codIdBanca, 
													dataPagamento, statoRPT);
									
								} catch (Exception e) {
									error("integraGTWAsinc - aggiornaEsitoTransazione non riuscita", e);
								}
								//FINE chiedo stato RPT
								
							} //30012017 GG	
						}
						
					} //Fine se Nodo SPC
					//PG150180_001 GG - fine
					else if (gateway.getTipoGateway().compareTo(GatewaysTypeKeys.SATISPAY.format()) == 0) {
						RedirectToGTWPagOnlineResponse transazionePagOnlineResponse = new RedirectToGTWPagOnlineResponse();
						// we prepare allineaTransazione request
						
						{
							transazioneRequest.setTransazione(in.getTransazione());
							transazioneRequest.setDataPagamento(in.getDataPagamento());
						}
						
						transazionePagOnlineResponse = this.allineaTransazioneSatispay(transazioneRequest, tipoEsecuzione);
						
						info(Events.TRA_ALIGN.format() + " | T: " + beanTransazioni.getChiave_transazione() + " | G: " + in.getTransazione().getChiaveGTW() +", { integraGTWAsinc > Retry Response }");
						info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
						info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + transazionePagOnlineResponse.getResponse().getRetCode().getValue() + ";");	        	
						info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + transazionePagOnlineResponse.getResponse().getRetMessage() + ";");
						info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
						
						String autorizzazioneBanca = "";
						String identificativoBanca = "";
						Calendar data = Calendar.getInstance();
						
						// se la risposta dal gateway è andata a buon fine aggiorno altrimenti la trx rimane sospesa e non aggiorno niente.
						if (transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) ||
							transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value3)) {
							//autorizzazioneBanca = gateway.getApiVersion();
							//identificativoBanca = gateway.getCodiceNegozio();
							data = Generics.getCalendarFromDateString(transazionePagOnlineResponse.getDatamodifica(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
							
							// we update transaction state
							AggiornaEsitoTransazioneRequest aggiornaEsitoTransazioneRequest = new AggiornaEsitoTransazioneRequest(
									in.getTransazione().getChiaveTransazione(), transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
									? TRA_COMPLETED_STATE : TRA_FAILED_STATE, autorizzazioneBanca, identificativoBanca, 
											data);
							
							info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
							", { aggiornaEsitoTransazione > Start } ");
							
							// we call method aggiornaEsitoTransazione
							AggiornaEsitoTransazioneResponse aggiornaEsitoTransazioneResp = binding.aggiornaEsitoTransazione(aggiornaEsitoTransazioneRequest);
							
							info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", { aggiornaEsitoTransazione > Retry Response } ");
							info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", Response: [ ");
							info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tCode=" + aggiornaEsitoTransazioneResp.getResponse().getRetCode().getValue() + ";");	        	
							info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", \t\t\tMessage=" + aggiornaEsitoTransazioneResp.getResponse().getRetMessage() + ";");
							info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + ", ]");
						} 
						
						return new IntegraGTWAsincResponse(transazionePagOnlineResponse.getResponse(), 
								transazionePagOnlineResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode._value1) 
								? TRA_COMPLETED_STATE : TRA_FAILED_STATE, 
										autorizzazioneBanca, identificativoBanca, 
										data, "");				
					} 
				}
				
				//se non è nessun gateway previsto, la lascio invariata.
				return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, 
						"Alignment transaction " + in.getTransazione().getChiaveTransazione() + " denied, payment channel " + 
						gateway.getCanale().getChiaveCanalePagamento() + " is not allowed"), 
						TRA_FAILED_STATE, beanTransazioni.getCodice_autorizzazione_banca(), beanTransazioni.getCodice_identificativo_banca(), 
						beanTransazioni.getData_effettivo_pagamento_su_gateway(),"");
			}
			
		} catch (Exception ex) {
			System.out.println("GTW ASINC: ");
			ex.printStackTrace();
			error("integraGTWAsinc failed - generic error due to: ", ex);
			return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), TRA_FAILED_STATE, null, null, null, "");
		}catch (Throwable ex) {
			System.out.println("GTW ASINC: ");
			ex.printStackTrace();
			error("integraGTWAsinc failed - generic error due to: ", ex);
			return new IntegraGTWAsincResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), TRA_FAILED_STATE, null, null, null, "");
		}
	}
	private RedirectToGTWCartaSIResponse allineaTransazionePagOnlineCARTA_SI(
			AllineaTransazioneRequest in, String tipoEsecuzione) {

		try {
			// we retry info gateway
			info("allineaTransazionePagOnlineCARTA_SI ");
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getTransazione().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
			info("DOPO ");
				try {
					
					info("leggo ");
					String proxyHost = System.getProperty("http.proxyHost");
					info("leggo "+System.getProperty("http.proxyHost"));
					Integer proxyPort = 8080;
					info("leggo dopo");
					try { proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
					} catch (Exception e) { error("allineaTransazionePagOnline - Invalid proxy port. Use default port 8080"); }

					String proxyUser = System.getProperty("http.proxyUser");
					info("leggo user"+System.getProperty("http.proxyUser"));
					String proxyPassword = System.getProperty("http.proxyPassword");
					info("leggo pwd"+System.getProperty("http.proxyPassword"));
					HttpClient client = new HttpClient();
					if (proxyHost != null) {
						HostConfiguration config = client.getHostConfiguration(); 
						config.setProxy(proxyHost, proxyPort);
						AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
						info("allineaTransazionePagOnline - http.proxyHost = " + proxyHost);
						info("allineaTransazionePagOnline - http.proxyPort = " + proxyPort);
						Credentials credentials=null;
						if (proxyUser != null) {
							credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
							info("allineaTransazionePagOnline - http.proxyUser = " + proxyUser);
						}
						client.getState().setProxyCredentials(authScope, credentials); 
					}
					
					
					URL url = new URL(generaMessageGTWResponse.getApiCancelURL());
					info("leggo url "+generaMessageGTWResponse.getApiCancelURL());
					info("leggo path "+url.getPath());
		            PostMethod method =null;
					try {
						method = new PostMethod(url.getPath());
					} catch (Throwable x) {
						x.printStackTrace();
						throw new FaultType(-805, x.getMessage());
					}
		            
		            info("leggo dopo path "+url.getPath());
		            String alias=generaMessageGTWResponse.getApiUsername();
		            String codTans=hashEncode(in.getTransazione().getChiaveTransazione());
		            String id_op="";
		            //// id op
		            SimpleDateFormat sdf=new SimpleDateFormat("HHmmssSSS");
		   		    Calendar calendar=Calendar.getInstance();
		   		    Date date=calendar.getTime();
		   		    int giornoSettimana=Calendar.DAY_OF_WEEK;
		   		    Timestamp ts=new Timestamp(date.getTime());
		            id_op=String.valueOf(giornoSettimana).concat(sdf.format(date));
		            // id op
		            String type_op="V";
		            String user=generaMessageGTWResponse.getApiUsername();
		            String securityString=generaMessageGTWResponse.getApiSignature();
		            String mac=calcolaMac(alias.concat(codTans).concat(id_op).concat(type_op).concat(user).concat(securityString));
		            String xml="<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>" +
		            		    "<VPOSREQ>" +
		            		     "<alias>"+alias+"</alias>" +
		            		     "<INTREQ>" +
		            		      "<codTrans>"+codTans+"</codTrans>"+
		            		      "<id_op>"+id_op+"</id_op>"+
		                          "<type_op>"+type_op+"</type_op>"+
		                         "</INTREQ>"+
		                         "<user>"+user+"</user>"+
		                         "<mac>"+mac+"</mac>"+
		                        "</VPOSREQ>";
		            method.setRequestEntity(new StringRequestEntity(xml));
		            
		            
		            info("xml= "+ xml);
		            
//		            method.addParameter("numeroCommerciante", generaMessageGTWResponse.getApiVersion());
//		            method.addParameter("stabilimento", generaMessageGTWResponse.getCodNegozio());
//		            method.addParameter("userID", generaMessageGTWResponse.getApiUsername());
//		            method.addParameter("password", generaMessageGTWResponse.getApiPassword());
//		            method.addParameter("tipoComando", "QUERYORDER");
//		            method.addParameter("formatoRisposta", "plaintext");
//		            method.addParameter("numeroOrdine", in.getTransazione().getChiaveTransazione().replaceFirst("-", ""));
		            
		            if (url.getProtocol().equals("https")) {
//		                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
		            	Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
		            	client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
		            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

		            @SuppressWarnings("unused")
					int returnCode = client.executeMethod(method);
		            
					//inizio LP PG21XX04 Leak
		            //InputStream response = method.getResponseBodyAsStream();
		            //String responseString = readInputStreamAsString(response);
		            String responseString = method.getResponseBodyAsString();
					//fine LP PG21XX04 Leak
		            RedirectToGTWPagOnlineResponse ritorna = new RedirectToGTWPagOnlineResponse();
		            ritorna = RedirectToGTWCartaSIResponse.parseCARTA_SI(responseString);
		            info("responseString = " + responseString);
		            
		            info("BBBBBBBBBBBBBBBBBBBBB");
		            boolean statoMAC=RedirectToGTWCartaSIResponse.verificaMAC(responseString,generaMessageGTWResponse.getApiSignature());
		            
		            if (tipoEsecuzione.equalsIgnoreCase("online")) {
//		            	if (ritorna.getStato().equalsIgnoreCase("CO") ||
//			 		           	ritorna.getStato().equalsIgnoreCase("OK")) {
		            
		            	if (ritorna.getStato().equalsIgnoreCase("0")) {
			 		           	return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
			 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		            }	
		            	 //il codice di errore 3 id_op duplicato non modifica lo stat
			 		        else if (ritorna.getStato().equalsIgnoreCase("16") ||
			 		           		ritorna.getStato().equalsIgnoreCase("2") ||
			 		           		ritorna.getStato().equalsIgnoreCase("8") ||
			 		           		ritorna.getStato().equalsIgnoreCase("21") ||        		
			 		           	    ritorna.getStato().equalsIgnoreCase("32")||
			 		           	    statoMAC) {
			 		        	    return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value3, "Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
//			 		           		return new RedirectToGTWPagOnlineResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale()+" verifica MAC response ="+statoMAC);
			 		        } else {
			 		          		return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value2, "Stato sconosciuto o MAC errato = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale()+" verifica MAC response ="+statoMAC);
			 		        }
		            } else if (tipoEsecuzione.equalsIgnoreCase("batch")) {
		            	if (ritorna.getStato().equalsIgnoreCase("0")) {
			 		           	return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value1, "Transaction " + 
			 							in.getTransazione().getChiaveTransazione()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 							ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		            }	
		            	//il codice di errore 3 id_op duplicato non modifica lo stat
			 		        else if (ritorna.getStato().equalsIgnoreCase("16") ||
			 		           		ritorna.getStato().equalsIgnoreCase("2") ||
			 		           		ritorna.getStato().equalsIgnoreCase("8") ||
			 		           		ritorna.getStato().equalsIgnoreCase("21") ||	           		
			 		           		ritorna.getStato().equalsIgnoreCase("32")||
			 		           	    !statoMAC) {
			 		           		return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value3, "Errore: Stato transazione = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        } else {
			 		          		return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value2, "Errore: Stato sconosciuto = " + ritorna.getStato()), ritorna.getOrderid(), ritorna.getDatacreazione(),
			 								ritorna.getDatamodifica(), ritorna.getStato(), ritorna.getTotale());
			 		        }
		            }
		            return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error : Elaborazione diversa da batch e online"));
		           
				} catch (Exception ex) {
					error("allineaTransazionePagOnline failed - generic error due to: ", ex);
					ex.printStackTrace();
					return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value2, "Generic Error "));
				}
			
		} catch (Exception ex) {
			info(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
			", { allineaTransazionePagOnline > Abort }");
			warn(Events.TRA_ALIGN.format() + " | T: " + in.getTransazione().getChiaveTransazione() + " | G: " + in.getTransazione().getChiaveGTW() + 
					", generic error due to: " + ex.getMessage());
			return new RedirectToGTWCartaSIResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()));
		}
	}

	private String calcolaMac(String stringaMAC) throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] dataBytes = stringaMAC.getBytes();
			md.update(dataBytes);
			byte[] mdbytes = md.digest();

			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
	       String sMacEncoded=sb.toString();
		return sMacEncoded.toUpperCase();
	}

	/**
	 * Nella modalità sincrona avrà il compito di colloquiare con il gateway preposto (PAYPAL) per
	 * inizializzare una specifica transazione.
	 * 
	 * @param in com.seda.payer.gateways.webservice.dati.RedirectToGTWRequest
	 */
	private RedirectToGTWResponse redirectToGTW(RedirectToGTWRequest in, String elementi, String mailContrib) throws RemoteException, FaultType {
		try {
			// we set expressCheckoutReq
			SetExpressCheckoutReq setExpressCheckoutRequest = new SetExpressCheckoutReq(); {
				SetExpressCheckoutRequestType setExpressCheckoutRequestType = new SetExpressCheckoutRequestType(); {
					SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetailsType = new SetExpressCheckoutRequestDetailsType(); {
						BasicAmountType orderTotal = new BasicAmountType(); {
							orderTotal.set_value(in.getApiOrderTotal());
							orderTotal.setCurrencyID(CurrencyCodeType.EUR);
						}
						// we get csrfToken
						String csrfToken = ((in.getTokenCsrf() != null && in.getTokenCsrf().length() > 0) ? in.getTokenCsrf() : "");
						// we append csrfToken on return url
						String returnUrl = in.getApiReturnURL().indexOf('?') == -1
						? in.getApiReturnURL() + "?" + "TransactionID=" + in.getTransazione().getChiaveTransazione() + "&" + csrfToken
								: in.getApiReturnURL() + "&" + "TransactionID=" + in.getTransazione().getChiaveTransazione() + "&" + csrfToken;
						// we append csrfToken on cancel url
						String cancelUrl = in.getApiCancelURL().indexOf('?') == -1 
						? in.getApiCancelURL() + "?" + csrfToken : in.getApiCancelURL() + "&" + csrfToken;
						// setExpressCheckoutRequestDetailsType.setCustom(in.getTransazione().getChiaveTransazione());
						
						
						PaymentDetailsType paymentDetailsType = new PaymentDetailsType(); {
							paymentDetailsType.setCustom(in.getTransazione().getChiaveTransazione());
						}
						
						String	descrizionePagamentoPaypal ="";
						if(propertiesTree().getProperty(PropKeys.DESCRIZIONE_PAGAMENTO_PAYPAL.format(dbSchemaCodSocieta))!=null)
							descrizionePagamentoPaypal = propertiesTree().getProperty(PropKeys.DESCRIZIONE_PAGAMENTO_PAYPAL.format(dbSchemaCodSocieta));

						// QF se descrizione paypal diversa, gestisco l'invio a paypal di alcuni parametri che 
						// possono essere valorizzati.
						
						
						if(!descrizionePagamentoPaypal.equals(""))
						{
							PaymentDetailsItemType item = new PaymentDetailsItemType();
							item.setAmount(orderTotal);
							item.setDescription(descrizionePagamentoPaypal);
							item.setName(descrizionePagamentoPaypal);
							paymentDetailsType.setPaymentDetailsItem(new PaymentDetailsItemType[] { item });
							setExpressCheckoutRequestDetailsType.setBuyerEmail(mailContrib);
							
						}					
						
						setExpressCheckoutRequestDetailsType.setPaymentDetails(new PaymentDetailsType[] { paymentDetailsType } );
						setExpressCheckoutRequestDetailsType.setOrderTotal(orderTotal);
						setExpressCheckoutRequestDetailsType.setReturnURL(returnUrl);
						setExpressCheckoutRequestDetailsType.setCancelURL(cancelUrl);
					}
					setExpressCheckoutRequestType.setSetExpressCheckoutRequestDetails(setExpressCheckoutRequestDetailsType);
					setExpressCheckoutRequestType.setVersion(in.getApiVersion());
				}
				setExpressCheckoutRequest.setSetExpressCheckoutRequest(setExpressCheckoutRequestType);
			}
			// we set customSecurityHeader
			CustomSecurityHeaderType customSecurityHeaderType = getApiCustomSecurityHeader(in.getApiUsername(),
					in.getApiPassword(), in.getApiSignature());
			System.out.println("inizio setExpressCheckout");
			// we call paypal service - setExpressCheckout
			PayPalAPIAASoapBindingStub binding = new PayPalAPIAASoapBindingStub(new URL(in.getApiEndPointUrl()), null); 
			// we retry response
			SetExpressCheckoutResponseType setExpressCheckoutResponseType = 
				binding.setExpressCheckout(setExpressCheckoutRequest, customSecurityHeaderType);
			System.out.println("fine setExpressCheckout");
			
			info("PayPalAPI.api.ebay.setExpressCheckout.ACK - " + setExpressCheckoutResponseType.getAck());
			info("PayPalAPI.api.ebay.setExpressCheckout.TOKEN - " + setExpressCheckoutResponseType.getToken());
			info("PayPalAPI.api.ebay.setExpressCheckout.ERRORS - " + setExpressCheckoutResponseType.getErrors());
			
			System.out.println("PayPalAPI.api.ebay.setExpressCheckout.ACK - " + setExpressCheckoutResponseType.getAck());
			System.out.println("PayPalAPI.api.ebay.setExpressCheckout.TOKEN - " + setExpressCheckoutResponseType.getToken());
			System.out.println("PayPalAPI.api.ebay.setExpressCheckout.ERRORS - " + setExpressCheckoutResponseType.getErrors());
			
			if (setExpressCheckoutResponseType.getErrors() != null && setExpressCheckoutResponseType.getErrors().length > 0) {
				for (ErrorType nextError : setExpressCheckoutResponseType.getErrors()) {
					error("PayPalAPI.api.ebay.setExpressCheckout.error.CODE - " + nextError.getErrorCode());
					error("PayPalAPI.api.ebay.setExpressCheckout.error.LONG_MSG - " + nextError.getLongMessage());
					error("PayPalAPI.api.ebay.setExpressCheckout.error.SHORT_MSG - " + nextError.getShortMessage());
					throw new Exception(nextError.getShortMessage());
				}
			}
			// we return the response
			return new RedirectToGTWResponse(new ResponseType(ResponseTypeRetCode.value1, "Success"),
					setExpressCheckoutResponseType.getToken());

		} catch (Exception ex) {
			error("redirectToGTW failed - generic error due to: ", ex);
			return new RedirectToGTWResponse(new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), null);
		}
	}
	/**
	 * Nella modalità sincrona avrà il compito di colloquiare con il gateway preposto (INFO GROUP) per
	 * inizializzare una specifica transazione.
	 * 
	 * @param in com.seda.payer.gateways.webservice.dati.RedirectToGTWIGRequest
	 */
	protected RedirectToGTWIGResponse redirectToGTWIG(RedirectToGTWIGRequest in) throws RemoteException, FaultType {
		try {
			info("redirectToGTWIG request - " + in.xmlTextCartaDiCredito());
			String proxyHost = System.getProperty("http.proxyHost");
			Integer proxyPort = 8080;
			try { proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
			} catch (Exception e) { error("redirectToGTWIG - Invalid proxy port. Use default port 8080"); }

			String proxyUser = System.getProperty("http.proxyUser");
			String proxyPassword = System.getProperty("http.proxyPassword");
			HttpClient client = new HttpClient();
			if (proxyHost != null) {
				HostConfiguration config = client.getHostConfiguration(); 
				config.setProxy(proxyHost, proxyPort);
				AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
				info("redirectToGTWIG - http.proxyHost = " + proxyHost);
				info("redirectToGTWIG - http.proxyPort = " + proxyPort);
				Credentials credentials=null;
				if (proxyUser != null) {
					credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
					info("redirectToGTWIG - http.proxyUser = " + proxyUser);
				}
				client.getState().setProxyCredentials(authScope, credentials); 
			}
            URL url = new URL(in.getApiEndPointUrl());
            PostMethod method = new PostMethod(url.getPath());
            method.addParameter("initPayment", in.xmlTextCartaDiCredito());
            if (url.getProtocol().equals("https")) {
//                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
                info("redirectToGTWIG infogroup http host - " + url.getHost());
                info("redirectToGTWIG infogroup http port - " + client.getHostConfiguration().getPort());
            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

            int returnCode = client.executeMethod(method);
            info("redirectToGTWIG infogroup http returnCode - " + returnCode);
            String response = method.getResponseBodyAsString();
            info("redirectToGTWIG infogroup http response - " + response);
            String xmlText = response.substring(response.indexOf("=")+1);
			info("redirectToGTWIG infogroup http xml response - " + xmlText);
			return RedirectToGTWIGResponse.parse(xmlText, LOG);

		} catch (Exception ex) {
			error("redirectToGTWIG failed - generic error due to: ", ex);
			ex.printStackTrace();
			return new RedirectToGTWIGResponse(null, "1", "Generic Err", null);
		}
	}
	
	/**
	 * Effettua lo scambio S2S con Infogroup per la richiesta di storno di una transazione effettuata con carta di credito
	 * 
	 * @param in com.seda.payer.gateways.webservice.dati.RedirectToGTWIGRequest
	 */
	protected RedirectToGTWIGResponse richiediStornoS2S(RedirectToGTWIGRequest in) throws RemoteException, FaultType {
		try {
			info("richiediStornoS2S request - " + in.xmlTextStorno());
			
			String proxyHost = System.getProperty("http.proxyHost");
			Integer proxyPort = 8080;
			try { 
				proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
			} catch (Exception e) { 
				error("richiediStornoS2S - Invalid proxy port. Use default port 8080"); 
			}

			String proxyUser = System.getProperty("http.proxyUser");
			String proxyPassword = System.getProperty("http.proxyPassword");
			
			HttpClient client = new HttpClient();
			
			if (proxyHost != null) {
				HostConfiguration config = client.getHostConfiguration(); 
				config.setProxy(proxyHost, proxyPort);
				AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
				info("richiediStornoS2S - http.proxyHost = " + proxyHost);
				info("richiediStornoS2S - http.proxyPort = " + proxyPort);
				Credentials credentials=null;
				if (proxyUser != null) {
					credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
					info("richiediStornoS2S - http.proxyUser = " + proxyUser);
				}
				client.getState().setProxyCredentials(authScope, credentials); 
			}
			
            //URL url = new URL(in.getApiEndPointUrl());
			// Stesso URL utilizzato per la richiesta/revoca adesione RID
			URL url = getUrlRidS2S();
            PostMethod method = new PostMethod(url.getPath());
            
            method.addParameter("initServizio", in.xmlTextStorno());
           
            if (url.getProtocol().equals("https")) {
//                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
                info("richiediStornoS2S http host - " + url.getHost());
                info("richiediStornoS2S http port - " + client.getHostConfiguration().getPort());
            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

            // chiamata http S2S
            int returnCode = client.executeMethod(method);
            
            info("richiediStornoS2S http returnCode - " + returnCode);
            
            if(returnCode==200) {
	            String response = method.getResponseBodyAsString();
	            info("richiediStornoS2S http response - " + response);
	            
	            String xmlText = response.substring(response.indexOf("=")+1);
	            return RedirectToGTWIGResponse.parseStorno(xmlText, LOG);
            } else
            	return new RedirectToGTWIGResponse(null, "-1", "Errore nella chiamata S2S: HTTP Return code " + returnCode, null);
            
			

		} catch (Exception ex) {
			error("richiediStornoS2S failed - generic error due to: ", ex);
			ex.printStackTrace();
			return new RedirectToGTWIGResponse(null, "-2", ex.getMessage(), null);
		}
	}
	
	
	/**
	 * Nella modalità sincrona avrà il compito di colloquiare con il gateway preposto (INFO GROUP) per
	 * inizializzare una specifica transazione.
	 * 
	 * @param in com.seda.payer.gateways.webservice.dati.RedirectToGTWIGRequest
	 */
	protected RedirectToGTWIGResponse redirectToGTWPagoInConto(RedirectToGTWIGRequest in) throws RemoteException, FaultType {
		try {
			info("redirectToGTWPagoInConto request - " + in.xmlTextPagoInConto());

			String proxyHost = System.getProperty("http.proxyHost");
			Integer proxyPort = 8080;
			try { proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
			} catch (Exception e) { error("redirectToGTWPagoInConto - Invalid proxy port. Use default port 8080"); }

			String proxyUser = System.getProperty("http.proxyUser");
			String proxyPassword = System.getProperty("http.proxyPassword");
			HttpClient client = new HttpClient();
			if (proxyHost != null) {
				HostConfiguration config = client.getHostConfiguration(); 
				config.setProxy(proxyHost, proxyPort);
				AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
				info("redirectToGTWPagoInConto - http.proxyHost = " + proxyHost);
				info("redirectToGTWPagoInConto - http.proxyPort = " + proxyPort);
				Credentials credentials = null;
				if (proxyUser != null) {
					credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
					info("redirectToGTWPagoInConto - http.proxyUser = " + proxyUser);
				}
				client.getState().setProxyCredentials(authScope, credentials); 
			}
            URL url = new URL(in.getApiEndPointUrl());
            PostMethod method = new PostMethod(url.getPath());
            method.addParameter("initPayment", in.xmlTextPagoInConto());
            if (url.getProtocol().equals("https")) {
//                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
                info("redirectToGTWPagoInConto infogroup http host - " + url.getHost());
                info("redirectToGTWPagoInConto infogroup http port - " + client.getHostConfiguration().getPort());
            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

            int returnCode = client.executeMethod(method);
            info("redirectToGTWPagoInConto infogroup http returnCode - " + returnCode);
            String response = method.getResponseBodyAsString();
            info("redirectToGTWPagoInConto infogroup http response - " + response);
            String xmlText = response.substring(response.indexOf("=")+1);
			info("redirectToGTWPagoInConto infogroup http xml response - " + xmlText);
			
			return RedirectToGTWIGResponse.parse(xmlText, LOG);

		} catch (Exception ex) {
			error("redirectToGTWIG failed - generic error due to: ", ex);
			ex.printStackTrace();
			return new RedirectToGTWIGResponse(null, "1", "Generic Err", null);
		}
	}
	/**
	 * Nella modalità sincrona avrà il compito di colloquiare con il gateway preposto (INFO GROUP) per
	 * inizializzare una specifica transazione RID
	 * 
	 * @param in com.seda.payer.gateways.webservice.dati.RedirectToGTWIGRequest
	 */
	protected RedirectToGTWIGResponse redirectToGTWRidOnLine(RedirectToGTWIGRequest in) throws RemoteException, FaultType {
		try {
			info("redirectToGTWRidOnLine request - " + in.xmlTextRID());

			String proxyHost = System.getProperty("http.proxyHost");
			Integer proxyPort = 8080;
			try 
			{ 
				proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
			} 
			catch (Exception e) 
			{
				error("redirectToGTWRidOnLine - Invalid proxy port. Use default port 8080"); 
			}

			String proxyUser = System.getProperty("http.proxyUser");
			String proxyPassword = System.getProperty("http.proxyPassword");
			HttpClient client = new HttpClient();
			if (proxyHost != null) {
				HostConfiguration config = client.getHostConfiguration(); 
				config.setProxy(proxyHost, proxyPort);
				AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
				info("redirectToGTWRidOnLine - http.proxyHost = " + proxyHost);
				info("redirectToGTWRidOnLine - http.proxyPort = " + proxyPort);
				Credentials credentials = null;
				if (proxyUser != null) {
					credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
					info("redirectToGTWRidOnLine - http.proxyUser = " + proxyUser);
				}
				client.getState().setProxyCredentials(authScope, credentials); 
			}
            URL url = new URL(in.getApiEndPointUrl());
            PostMethod method = new PostMethod(url.getPath());
            method.addParameter("initPayment", in.xmlTextRID());
            if (url.getProtocol().equals("https")) {
//                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
                info("redirectToGTWRidOnLine infogroup http host - " + url.getHost());
                info("redirectToGTWRidOnLine infogroup http port - " + client.getHostConfiguration().getPort());
            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

            // chiamata S2S payer --> concentratore (richiesta RID)
            int returnCode = client.executeMethod(method);
            info("redirectToGTWRidOnLine infogroup http returnCode - " + returnCode);
            String response = method.getResponseBodyAsString();
            info("redirectToGTWRidOnLine infogroup http response - " + response);
            String xmlText = response.substring(response.indexOf("=")+1);
			info("redirectToGTWRidOnLine infogroup http xml response - " + xmlText);
			
			// risposta S2S concentratore --> payer (accettazione RID) ritorna URL per la conferma dell'utente 
            return RedirectToGTWIGResponse.parseRidOnLine(xmlText, returnCode, LOG);

		} catch (Exception ex) {
			error("redirectToGTWIG redirectToGTWRidOnLine failed - generic error due to: ", ex);
			ex.printStackTrace();
			return new RedirectToGTWIGResponse(null, "1", "Generic Err", null);
		}
	}
	/**
	 * Nella modalità sincrona avrà il compito di colloquiare con il gateway preposto (INFO GROUP) per
	 * inizializzare una specifica transazione MAV
	 * 
	 * @param in com.seda.payer.gateways.webservice.dati.RedirectToGTWIGRequest
	 */
	protected RedirectToGTWIGResponse redirectToGTWMAVOnLine(RedirectToGTWIGRequest in) throws RemoteException, FaultType {
		try {
			info("redirectToGTWMavOnLine request - " + in.xmlTextMAV());

			String proxyHost = System.getProperty("http.proxyHost");
			Integer proxyPort = 8080;
			try 
			{ 
				proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
			} 
			catch (Exception e) 
			{
				error("redirectToGTWMavOnLine - Invalid proxy port. Use default port 8080"); 
			}

			String proxyUser = System.getProperty("http.proxyUser");
			String proxyPassword = System.getProperty("http.proxyPassword");
			HttpClient client = new HttpClient();
			if (proxyHost != null) {
				HostConfiguration config = client.getHostConfiguration(); 
				config.setProxy(proxyHost, proxyPort);
				AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
				info("redirectToGTWMavOnLine - http.proxyHost = " + proxyHost);
				info("redirectToGTWMavOnLine - http.proxyPort = " + proxyPort);
				Credentials credentials = null;
				if (proxyUser != null) {
					credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
					info("redirectToGTWMavOnLine - http.proxyUser = " + proxyUser);
				}
				client.getState().setProxyCredentials(authScope, credentials); 
			}
            URL url = new URL(in.getApiEndPointUrl());
            PostMethod method = new PostMethod(url.getPath());
            method.addParameter("initPayment", in.xmlTextMAV());
            if (url.getProtocol().equals("https")) {
//                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
                Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
                client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
                info("redirectToGTWMavOnLine infogroup http host - " + url.getHost());
                info("redirectToGTWMavOnLine infogroup http port - " + client.getHostConfiguration().getPort());
            } else client.getHostConfiguration().setHost(url.getHost(), url.getPort());

            // chiamata S2S payer --> concentratore (richiesta MAV)
            int returnCode = client.executeMethod(method);
            info("redirectToGTWMavOnLine infogroup http returnCode - " + returnCode);
            String response = method.getResponseBodyAsString();
            info("redirectToGTWMavOnLine infogroup http response - " + response);
            String xmlText = response.substring(response.indexOf("=")+1);
			info("redirectToGTWMavOnLine infogroup http xml response - " + xmlText);
			
			// risposta S2S concentratore --> payer (accettazione MAV) NON ritorna URL per la conferma dell'utente 
            return RedirectToGTWIGResponse.parseMavOnLine(xmlText, returnCode, LOG);

		} catch (Exception ex) {
			error("redirectToGTWIG redirectToGTWMavOnLine failed - generic error due to: ", ex);
			ex.printStackTrace();
			return new RedirectToGTWIGResponse(null, "1", "Generic Err", null);
		}
	}
	/**
	 * Avrà il compito, sia per la modalità sincrona che asincrona, di verificare l'esito di risposta del
	 * gateway e l'attendibilità del token rispettando i criteri di sicurezza
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.VerificaEsitoGTWRequest
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#verificaEsitoGTW(com.seda.payer.gateways.webservice.dati.VerificaEsitoGTWRequest)
	 */
	public VerificaEsitoGTWResponse verificaEsitoGTW(VerificaEsitoGTWRequest in) throws RemoteException, FaultType {
		String stringaMac = "";
		try {
			// we retry gateway info
			GeneraMessageGTWResponse messageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getGateway().getChiaveGTW(), in.getGateway().getToken()), 
							EMPTY_ORDER_TOTAL));
			
			
			
			if (in.getDatiPagOnline().getTipoGateway().equals("O")) {
				//calcolo il mac e verifico che sia lo stesso passato dal gateway PagOnline
				if (in.getDatiPagOnline().getEsito().equalsIgnoreCase("KO")) {
					stringaMac = "numeroOrdine=" + in.getDatiPagOnline().getNumeroOrdine() + "&numeroCommerciante=" + in.getDatiPagOnline().getNumeroCommerciante() + "&stabilimento=" + in.getDatiPagOnline().getStabilimento()
					+ "&esito=" + in.getDatiPagOnline().getEsito() + "&" + messageGTWResponse.getApiSignature();	
				} else {
					stringaMac = "numeroOrdine=" + in.getDatiPagOnline().getNumeroOrdine() + "&numeroCommerciante=" + in.getDatiPagOnline().getNumeroCommerciante() + "&stabilimento=" + in.getDatiPagOnline().getStabilimento()
					+ "&esito=" + in.getDatiPagOnline().getEsito() + "&dataApprovazione=" + calendarToString(in.getDatiPagOnline().getDataApprovazione()) + "&" + messageGTWResponse.getApiSignature();
				}
				
				
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(stringaMac.getBytes());
				byte[] bMac = md.digest();
				String sMacEncoded = Base64.getEncoder().encodeToString(bMac);
				sMacEncoded = sMacEncoded.substring(0,24);
				
				//forzatura
				//in.getDatiPagOnline().setEsito("KO");
				
				Calendar dataSistema = Calendar.getInstance();
				//imposto l'ora della data che dal gateway non viene valorizzata.
				System.out.println("data 1: " + in.getDatiPagOnline().getDataApprovazione());
				Calendar dataPagamentoCompleta = in.getDatiPagOnline().getDataApprovazione();
				
				System.out.println("");
				Calendar dataApprovTemp = new GregorianCalendar(
													dataSistema.get(Calendar.YEAR), 
													dataSistema.get(Calendar.MONTH),
													dataSistema.get(Calendar.DAY_OF_MONTH),
													dataSistema.get(Calendar.HOUR_OF_DAY), 
													dataSistema.get(Calendar.MINUTE), 
													dataSistema.get(Calendar.SECOND)) ;
				
				//dataSistema.setTime(dataApprovTemp.getTime());
				
				//dataPagamentoCompleta.set(Calendar.ZONE_OFFSET, (Calendar.ZONE_OFFSET/(60*60*1000)));
				//dataPagamentoCompleta.set(Calendar.HOUR_OF_DAY, dataSistema.get(Calendar.HOUR_OF_DAY));
				//dataPagamentoCompleta.set(Calendar.MINUTE, dataSistema.get(Calendar.MINUTE));
				//dataPagamentoCompleta.set(Calendar.SECOND, dataSistema.get(Calendar.SECOND));
				
				in.getDatiPagOnline().setDataApprovazione(dataApprovTemp);
				
				//System.out.println("data 3: " + dataPagamentoCompleta);
				//in.getDatiPagOnline().setDataApprovazione(dataPagamentoCompleta);
				
				if (in.getDatiPagOnline().getMac().equals(sMacEncoded)) {
					if (in.getDatiPagOnline().getEsito().equalsIgnoreCase("OK")) {
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value1, "Success"), 
								TRA_COMPLETED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
					else {
						error("verificaEsitoGTW failed, PagOnline: La transazione è andata a buon fine ma lo stato risulta KO");
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
								TRA_FAILED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
				}
				else {
					error("verificaEsitoGTW failed, PagOnline: MAC di ritorno dal Gateway diverso da quello calcolato");
					return new VerificaEsitoGTWResponse(
							new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
							TRA_FAILED_STATE, 
							in.getDatiPagOnline().getNumeroCommerciante(), 
							in.getDatiPagOnline().getStabilimento(), 
							in.getDatiPagOnline().getDataApprovazione());
				}
					
			} else if(in.getDatiPagOnline().getTipoGateway().equals("B")){
				//calcolo il mac e verifico che sia lo stesso passato dal gateway MyBank
				if (in.getDatiPagOnline().getEsito().equalsIgnoreCase("KO")) {
					stringaMac = "numeroOrdine=" + in.getDatiPagOnline().getNumeroOrdine().replaceFirst("-", "") + "&numeroCommerciante=" + in.getDatiPagOnline().getNumeroCommerciante() + "&stabilimento=" + in.getDatiPagOnline().getStabilimento()
					+ "&esito=" + in.getDatiPagOnline().getEsito() + "&" + messageGTWResponse.getApiSignature();	
				} else {
					stringaMac = "numeroOrdine=" + in.getDatiPagOnline().getNumeroOrdine().replaceFirst("-","") + "&numeroCommerciante=" + in.getDatiPagOnline().getNumeroCommerciante() + "&stabilimento=" + in.getDatiPagOnline().getStabilimento()
					+ "&esito=" + in.getDatiPagOnline().getEsito() + "&dataApprovazione=" + calendarToString(in.getDatiPagOnline().getDataApprovazione()) + "&" + messageGTWResponse.getApiSignature();
				}
				
				
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(stringaMac.getBytes());
				byte[] bMac = md.digest();
				String sMacEncoded = Base64.getEncoder().encodeToString(bMac);
				sMacEncoded = sMacEncoded.substring(0,24);
				
				//forzatura
				//in.getDatiPagOnline().setEsito("KO");
				
				Calendar dataSistema = Calendar.getInstance();
				//imposto l'ora della data che dal gateway non viene valorizzata.
				System.out.println("data 1: " + in.getDatiPagOnline().getDataApprovazione());
				Calendar dataPagamentoCompleta = in.getDatiPagOnline().getDataApprovazione();
				
				System.out.println("");
				Calendar dataApprovTemp = new GregorianCalendar(
													dataSistema.get(Calendar.YEAR), 
													dataSistema.get(Calendar.MONTH),
													dataSistema.get(Calendar.DAY_OF_MONTH),
													dataSistema.get(Calendar.HOUR_OF_DAY), 
													dataSistema.get(Calendar.MINUTE), 
													dataSistema.get(Calendar.SECOND)) ;
				
				//dataSistema.setTime(dataApprovTemp.getTime());
				
				//dataPagamentoCompleta.set(Calendar.ZONE_OFFSET, (Calendar.ZONE_OFFSET/(60*60*1000)));
				//dataPagamentoCompleta.set(Calendar.HOUR_OF_DAY, dataSistema.get(Calendar.HOUR_OF_DAY));
				//dataPagamentoCompleta.set(Calendar.MINUTE, dataSistema.get(Calendar.MINUTE));
				//dataPagamentoCompleta.set(Calendar.SECOND, dataSistema.get(Calendar.SECOND));
				
				in.getDatiPagOnline().setDataApprovazione(dataApprovTemp);
				
				//System.out.println("data 3: " + dataPagamentoCompleta);
				//in.getDatiPagOnline().setDataApprovazione(dataPagamentoCompleta);
				
				if (in.getDatiPagOnline().getMac().equals(sMacEncoded)) {
					if (in.getDatiPagOnline().getEsito().equalsIgnoreCase("OK")) {
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value1, "Success"), 
								TRA_COMPLETED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
					else {
						error("verificaEsitoGTW failed, MyBank: La transazione è andata a buon fine ma lo stato risulta KO");
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
								TRA_FAILED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
				}
				else {
					error("verificaEsitoGTW failed, MyBank: MAC di ritorno dal Gateway diverso da quello calcolato");
					return new VerificaEsitoGTWResponse(
							new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
							TRA_FAILED_STATE, 
							in.getDatiPagOnline().getNumeroCommerciante(), 
							in.getDatiPagOnline().getStabilimento(), 
							in.getDatiPagOnline().getDataApprovazione());
				
				}
			}else if(in.getDatiPagOnline().getTipoGateway().equals("W")){

	
				if (in.getDatiPagOnline().getEsito().equalsIgnoreCase("OK")) {
					
					Calendar dataSistema = Calendar.getInstance();
					//imposto l'ora della data che dal gateway non viene valorizzata.
					System.out.println("data 1: " + in.getDatiPagOnline().getDataApprovazione());
					Calendar dataPagamentoCompleta = in.getDatiPagOnline().getDataApprovazione();
					
					System.out.println("");
					Calendar dataApprovTemp = new GregorianCalendar(
														dataSistema.get(Calendar.YEAR), 
														dataSistema.get(Calendar.MONTH),
														dataSistema.get(Calendar.DAY_OF_MONTH),
														dataSistema.get(Calendar.HOUR_OF_DAY), 
														dataSistema.get(Calendar.MINUTE), 
														dataSistema.get(Calendar.SECOND)) ;
					
					//dataSistema.setTime(dataApprovTemp.getTime());
					
					//dataPagamentoCompleta.set(Calendar.ZONE_OFFSET, (Calendar.ZONE_OFFSET/(60*60*1000)));
					//dataPagamentoCompleta.set(Calendar.HOUR_OF_DAY, dataSistema.get(Calendar.HOUR_OF_DAY));
					//dataPagamentoCompleta.set(Calendar.MINUTE, dataSistema.get(Calendar.MINUTE));
					//dataPagamentoCompleta.set(Calendar.SECOND, dataSistema.get(Calendar.SECOND));
					
					in.getDatiPagOnline().setDataApprovazione(dataApprovTemp);
					
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value1, "Success"), 
								TRA_COMPLETED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
					else {
						error("verificaEsitoGTW failed, CartaSi: La transazione non si e completata correttamente");
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
								TRA_FAILED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
//				}
//				else {
//					error("verificaEsitoGTW failed, MyBank: MAC di ritorno dal Gateway diverso da quello calcolato");
//					return new VerificaEsitoGTWResponse(
//							new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
//							TRA_FAILED_STATE, 
//							in.getDatiPagOnline().getNumeroCommerciante(), 
//							in.getDatiPagOnline().getStabilimento(), 
//							in.getDatiPagOnline().getDataApprovazione());
//			}
				}else if(in.getDatiPagOnline().getTipoGateway().equals("Y")){
					GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
							new GeneraMessageGTWRequest(new GatewayType(in.getGateway().getChiaveGTW(), ""), EMPTY_ORDER_TOTAL));
					CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
					
					SatispayClient satispayClient=new SatispayClient(generaMessageGTWResponse.getApiEndPointUrl().trim(), false,null, 0, false,generaMessageGTWResponse.getApiSignature());
					RecuperaTransazioneResponseType recuperaTransazioneResp =
						binding.recuperaTransazione(new RecuperaTransazioneRequestType(in.getDatiPagOnline().getNumeroOrdine()));
					ChargeResponse chargeResponse=satispayClient.getACharge(recuperaTransazioneResp.getBeanTransazioni().getBeanTransazioni().getCampo_opzionale_1());
					if(chargeResponse!=null){
						String datapagamento = "X";
						if(chargeResponse.getCharge_date()!=null)
						{
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
							datapagamento = formatter.format(chargeResponse.getCharge_date());
						}
				            	
						
				        String esito=chargeResponse.getPaid().concat("|").concat(chargeResponse.getStatus()).concat("|").concat(datapagamento);
						Date expired_date=chargeResponse.getExpire_date();
						if(Calendar.getInstance().getTime().after(expired_date)&&!chargeResponse.getPaid().equalsIgnoreCase("true")&&!chargeResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
							esito="false|FAILURE";
	                    }
						
						if(chargeResponse.getCharge_date()!=null)
						{
							Calendar data = Calendar.getInstance();
							data = Generics.getCalendarFromDateString(datapagamento, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
							return new VerificaEsitoGTWResponse(
									new ResponseType(ResponseTypeRetCode.value1, esito), 
									TRA_COMPLETED_STATE, 
									"", 
									"", 
									data);	
						}
						else
						{
							return new VerificaEsitoGTWResponse(
									new ResponseType(ResponseTypeRetCode.value1, esito), 
									TRA_COMPLETED_STATE, 
									"", 
									"", 
									Calendar.getInstance());	
						}
						
					}else {
						error("verificaEsitoGTW failed, CartaSi: La transazione non si e completata correttamente");
						return new VerificaEsitoGTWResponse(
								new ResponseType(ResponseTypeRetCode.value2, "Unsuccess"), 
								TRA_FAILED_STATE, 
								in.getDatiPagOnline().getNumeroCommerciante(), 
								in.getDatiPagOnline().getStabilimento(), 
								in.getDatiPagOnline().getDataApprovazione());	
					}
					
					
					
				}else {
				// we call PayPalAPI service - getExpressCheckoutDetails
				GetExpressCheckoutDetailsResponseType expressCheckoutDetailsResponse = this.getExpressCheckoutDetails(in, messageGTWResponse);
				
						
				//ANDREA PAYPAL
				expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().setCustom(in.getDatiPagOnline().getNumeroOrdine());
				expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().setInvoiceID(in.getDatiPagOnline().getNumeroOrdine());

				// we prepare data for call PayPalAPI service - doExpressCheckoutPayment
				DoExpressCheckoutPaymentResponseType expressCheckoutPaymentResponse = this.doExpressCheckoutPayment(in, expressCheckoutDetailsResponse, messageGTWResponse);

				if(expressCheckoutPaymentResponse!=null){
					// we return the response
					return new VerificaEsitoGTWResponse(
							new ResponseType(ResponseTypeRetCode.value1, "Success"), 
							TRA_COMPLETED_STATE, 
							(expressCheckoutPaymentResponse.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo())[0].getTransactionID(), 
							(expressCheckoutPaymentResponse.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo())[0].getTransactionID(), 
							expressCheckoutPaymentResponse.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo()[0].getPaymentDate());
				} else {
					// non c'è corrispondenza fra il gateway paypal utilizzato e la nazionalità del conto paypal.
					error("verificaEsitoGTW failed, generic error due to: gateway paypal utilizzato non congruente con il conto paypal");
					return new VerificaEsitoGTWResponse(
							new ResponseType(ResponseTypeRetCode.value3,"gateway paypal utilizzato non congruente con il conto paypal"), TRA_FAILED_STATE, null, null, null);
				}
			}

		} catch (Exception ex) {
			error("verificaEsitoGTW failed, generic error due to: ", ex);
			return new VerificaEsitoGTWResponse(
					new ResponseType(ResponseTypeRetCode.value2, ex.getMessage()), TRA_FAILED_STATE, null, null, null);
		}
	}
	
	
	
	private boolean controllaCountryCode(CountryCodeType codeType,
			String opzioniAggiuntive) {
		//CONTO PAYAL EUROPEO
		if(opzioniAggiuntive.equalsIgnoreCase("E")){
			if(codeType.equals(CountryCodeType.AT) || codeType.equals(CountryCodeType.BE) || codeType.equals(CountryCodeType.BG)
					|| codeType.equals(CountryCodeType.CY) || codeType.equals(CountryCodeType.CZ) || codeType.equals(CountryCodeType.DK)
					|| codeType.equals(CountryCodeType.EE) || codeType.equals(CountryCodeType.FI) || codeType.equals(CountryCodeType.FR)
					|| codeType.equals(CountryCodeType.DE) || codeType.equals(CountryCodeType.GR) || codeType.equals(CountryCodeType.IE)
					|| codeType.equals(CountryCodeType.IS) || codeType.equals(CountryCodeType.IT) || codeType.equals(CountryCodeType.LV)
					|| codeType.equals(CountryCodeType.LI) || codeType.equals(CountryCodeType.LT) || codeType.equals(CountryCodeType.LU)
					|| codeType.equals(CountryCodeType.MT) || codeType.equals(CountryCodeType.NO) || codeType.equals(CountryCodeType.NL)
					|| codeType.equals(CountryCodeType.PL) || codeType.equals(CountryCodeType.PT) || codeType.equals(CountryCodeType.GB)
					|| codeType.equals(CountryCodeType.SK) || codeType.equals(CountryCodeType.RO) || codeType.equals(CountryCodeType.SI)
					|| codeType.equals(CountryCodeType.ES) || codeType.equals(CountryCodeType.SE) || codeType.equals(CountryCodeType.HU))
				return true;
			else
				return false;
		
		//CONTO PAYAL INTERNAZIOANALE
		} else if(opzioniAggiuntive.equalsIgnoreCase("I")){
			if(codeType.equals(CountryCodeType.AT) || codeType.equals(CountryCodeType.BE) || codeType.equals(CountryCodeType.BG)
					|| codeType.equals(CountryCodeType.CY) || codeType.equals(CountryCodeType.CZ) || codeType.equals(CountryCodeType.DK)
					|| codeType.equals(CountryCodeType.EE) || codeType.equals(CountryCodeType.FI) || codeType.equals(CountryCodeType.FR)
					|| codeType.equals(CountryCodeType.DE) || codeType.equals(CountryCodeType.GR) || codeType.equals(CountryCodeType.IE)
					|| codeType.equals(CountryCodeType.IS) || codeType.equals(CountryCodeType.IT) || codeType.equals(CountryCodeType.LV)
					|| codeType.equals(CountryCodeType.LI) || codeType.equals(CountryCodeType.LT) || codeType.equals(CountryCodeType.LU)
					|| codeType.equals(CountryCodeType.MT) || codeType.equals(CountryCodeType.NO) || codeType.equals(CountryCodeType.NL)
					|| codeType.equals(CountryCodeType.PL) || codeType.equals(CountryCodeType.PT) || codeType.equals(CountryCodeType.GB)
					|| codeType.equals(CountryCodeType.SK) || codeType.equals(CountryCodeType.RO) || codeType.equals(CountryCodeType.SI)
					|| codeType.equals(CountryCodeType.ES) || codeType.equals(CountryCodeType.SE) || codeType.equals(CountryCodeType.HU))
				return false;
			else
				return true;
		//NON CODIFICATO
		} else
			return true;
	}

	/**
	 * @param in
	 * @param messageGTWResponse
	 * @return
	 * @throws Exception
	 * @author marco.montisano
	 */
	private GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(VerificaEsitoGTWRequest in, 
			GeneraMessageGTWResponse messageGTWResponse) throws Exception {
		// we prepare expressCheckoutDetails request
		GetExpressCheckoutDetailsReq expressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq(); {
			GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequestType = new GetExpressCheckoutDetailsRequestType(); {
				getExpressCheckoutDetailsRequestType.setToken(in.getGateway().getToken());
				getExpressCheckoutDetailsRequestType.setVersion(messageGTWResponse.getApiVersion());
			}
			expressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(getExpressCheckoutDetailsRequestType);
		}
		// we set customSecurityHeader
		CustomSecurityHeaderType customSecurityHeaderType = getApiCustomSecurityHeader(messageGTWResponse.getApiUsername(),
				messageGTWResponse.getApiPassword(), messageGTWResponse.getApiSignature());

		// we call PayPalAPI service - getExpressCheckoutDetails
		PayPalAPIAASoapBindingStub binding = new PayPalAPIAASoapBindingStub(new URL(messageGTWResponse.getApiEndPointUrl()), null);
		// we retry response
		GetExpressCheckoutDetailsResponseType expressCheckoutDetailsResponse = 
			binding.getExpressCheckoutDetails(expressCheckoutDetailsReq, customSecurityHeaderType);        	

		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.ACK - " + expressCheckoutDetailsResponse.getAck());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.TOKEN (returned by setExpressCheckout) - " + in.getGateway().getToken());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.PAYERID - " + expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.CUSTOM - " + expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getCustom());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.PAYMENT_ACTION - " + 
				expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.AMOUNT - " + 
				expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.ERRORS - " + expressCheckoutDetailsResponse.getErrors());
		if (expressCheckoutDetailsResponse.getErrors() != null && expressCheckoutDetailsResponse.getErrors().length > 0) {
			for (ErrorType nextError : expressCheckoutDetailsResponse.getErrors()) {
				error("PayPalAPI.api.ebay.getExpressCheckoutDetails.error.CODE - " + nextError.getErrorCode());
				error("PayPalAPI.api.ebay.getExpressCheckoutDetails.error.LONG_MSG - " + nextError.getLongMessage());
				error("PayPalAPI.api.ebay.getExpressCheckoutDetails.error.SHORT_MSG - " + nextError.getShortMessage());
				throw new Exception(nextError.getShortMessage());    			
			}
		}
		return expressCheckoutDetailsResponse;
	}
	/**
	 * @param in
	 * @param expressCheckoutDetailsResponse
	 * @param messageGTWResponse
	 * @return
	 * @throws Exception
	 * @author marco.montisano
	 */
	private DoExpressCheckoutPaymentResponseType doExpressCheckoutPayment(VerificaEsitoGTWRequest in, 
			GetExpressCheckoutDetailsResponseType expressCheckoutDetailsResponse, GeneraMessageGTWResponse messageGTWResponse
	) throws Exception {
		
		DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponseType=null;
		
		CountryCodeType codeType=expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerCountry();
		//Giulia---Per Soris non si deve effettuare il controllaCountryCode--120613
		String	flagPaypal ="";
		if(propertiesTree().getProperty(PropKeys.flagPaypal.format(dbSchemaCodSocieta))!=null)
			flagPaypal = propertiesTree().getProperty(PropKeys.flagPaypal.format(dbSchemaCodSocieta));
		
		Boolean validita = false;
		if (flagPaypal.equals("Y"))
		{ 
			validita = true;
			System.out.println("validita"+ " " +validita);
		}
		else { 
		validita =(controllaCountryCode(codeType,messageGTWResponse.getOpzioniAggiuntive()));
		}
		if (validita)
		{
			
			DoExpressCheckoutPaymentReq expressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
			DoExpressCheckoutPaymentRequestType expressCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
			DoExpressCheckoutPaymentRequestDetailsType paymentDetailsRequestType = new DoExpressCheckoutPaymentRequestDetailsType();
			
			paymentDetailsRequestType.setToken(in.getGateway().getToken());
			paymentDetailsRequestType.setPayerID(expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
			paymentDetailsRequestType.setPaymentAction(DEFAULT_PAYMENT_ACTION);
			
			
			PaymentDetailsType paymentDetails = new PaymentDetailsType(); 
			BasicAmountType orderTotal = new BasicAmountType(); 
			orderTotal.set_value(in.getImpTotTransazione().toString());
			orderTotal.setCurrencyID(CurrencyCodeType.EUR);
			paymentDetails.setOrderTotal(orderTotal);
			
			paymentDetailsRequestType.setPaymentDetails(new PaymentDetailsType[] { paymentDetails });
		
			expressCheckoutPaymentRequestType.setVersion(messageGTWResponse.getApiVersion());
			expressCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(paymentDetailsRequestType);

			expressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(expressCheckoutPaymentRequestType);
			
			
			CustomSecurityHeaderType customSecurityHeaderType = getApiCustomSecurityHeader(messageGTWResponse.getApiUsername(),messageGTWResponse.getApiPassword(), messageGTWResponse.getApiSignature());

			PayPalAPIAASoapBindingStub binding = new PayPalAPIAASoapBindingStub(new URL(messageGTWResponse.getApiEndPointUrl()), null);
			
			//CONFERMO IL PAGAMENTO A PAYPAL
			doExpressCheckoutPaymentResponseType = binding.doExpressCheckoutPayment(expressCheckoutPaymentReq, customSecurityHeaderType);
			
			info("PayPalAPI.api.ebay.doExpressCheckoutPayment.ACK - " + doExpressCheckoutPaymentResponseType.getAck());
			//info("PayPalAPI.api.ebay.doExpressCheckoutPayment.ERRORS - " + doExpressCheckoutPaymentResponseType.getErrors());
			
			if (doExpressCheckoutPaymentResponseType.getErrors() != null && doExpressCheckoutPaymentResponseType.getErrors().length > 0) {
				for (ErrorType nextError : doExpressCheckoutPaymentResponseType.getErrors()) {
					error("PayPalAPI.api.ebay.doExpressCheckoutPayment.error.CODE - " + nextError.getErrorCode());
					error("PayPalAPI.api.ebay.doExpressCheckoutPayment.error.LONG_MSG - " + nextError.getLongMessage());
					error("PayPalAPI.api.ebay.doExpressCheckoutPayment.error.SHORT_MSG - " + nextError.getShortMessage());
					throw new Exception(nextError.getShortMessage());
				}
			}
			DoExpressCheckoutPaymentResponseDetailsType responseDetails = 
				doExpressCheckoutPaymentResponseType.getDoExpressCheckoutPaymentResponseDetails();
			info("PayPalAPI.api.ebay.doExpressCheckoutPayment.TRANSACTION_ID - " + (responseDetails.getPaymentInfo())[0].getTransactionID());
			info("PayPalAPI.api.ebay.doExpressCheckoutPayment.GROSS_AMOUNT - " + (responseDetails.getPaymentInfo())[0].getGrossAmount().get_value());
			info("PayPalAPI.api.ebay.doExpressCheckoutPayment.CURRENCY_ID - " + (responseDetails.getPaymentInfo())[0].getGrossAmount().getCurrencyID());
			info("PayPalAPI.api.ebay.doExpressCheckoutPayment.PATMENT_DATE - " + (responseDetails.getPaymentInfo())[0].getPaymentDate().getTime());
					
		}
		
		
		return doExpressCheckoutPaymentResponseType;
	}
	/**
	 * @param paymentDate
	 * @param generaMessageGTWResponse
	 * @return
	 * @throws Exception
	 */
	private TransactionSearchResponseType transactionSearch(Calendar paymentDate, GeneraMessageGTWResponse generaMessageGTWResponse) throws Exception {
		// we initialize payPalAPISoapBindingStub
		PayPalAPISoapBindingStub binding = new PayPalAPISoapBindingStub(new URL(generaMessageGTWResponse.getApiEndPointUrl()), null);
		// we prepare transactionSearchReq
		TransactionSearchReq transactionSearchReq = new TransactionSearchReq(); {
			TransactionSearchRequestType transactionSearchRequestType = new TransactionSearchRequestType(); {
				Calendar startDate = Calendar.getInstance();
				Calendar endDate = Calendar.getInstance();
				// we check If input dataPagamento is empty then search all transaction by sysdate
				String dataPagamento = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
				if (paymentDate != null) {
					DateFormat dfRead = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
//					dataPagamento = new SimpleDateFormat("MM/dd/yyyy").format(paymentDate.getTime());
//					startDate.setTime(dfRead.parse(dataPagamento + " 00:00:00 +0000"));
					paymentDate.add(Calendar.HOUR_OF_DAY, -1);
					dataPagamento = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z").format(paymentDate.getTime());
					
					
					startDate.setTime(dfRead.parse(dataPagamento));
					
					
					info(Events.TRA_ALIGN.format() + " | PAYPAL: interrogo elenco con data " + dataPagamento);
					
					//paymentDate.add(Calendar.DAY_OF_MONTH, 1);
					paymentDate.add(Calendar.HOUR_OF_DAY, 3);
					//dataPagamento = new SimpleDateFormat("MM/dd/yyyy").format(paymentDate.getTime());
					dataPagamento = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z").format(paymentDate.getTime());
					//endDate.setTime(dfRead.parse(dataPagamento + " 23:59:59 +0000"));
					endDate.setTime(dfRead.parse(dataPagamento));
				} else {
					DateFormat dfRead = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
					startDate.setTime(dfRead.parse(dataPagamento + " 00:00:00 +0000"));
					endDate.setTime(dfRead.parse(dataPagamento + " 23:59:59 +0000"));					
				}

				// we set on transactionSearchRequestType startDate & endDate 
				transactionSearchRequestType.setStartDate(startDate);
				transactionSearchRequestType.setEndDate(endDate);
				transactionSearchRequestType.setVersion(generaMessageGTWResponse.getApiVersion());
				
//				transactionSearchRequestType.setTransactionClass(PaymentTransactionClassCodeType.Received);
//				transactionSearchRequestType.setStatus(PaymentTransactionStatusCodeType.Success);
				
				
			}
			transactionSearchReq.setTransactionSearchRequest(transactionSearchRequestType);
		}
		// we prepare customSecurityHeaderType
		CustomSecurityHeaderType customSecurityHeaderType =
			this.getApiCustomSecurityHeader(generaMessageGTWResponse.getApiUsername(), 
					generaMessageGTWResponse.getApiPassword(), generaMessageGTWResponse.getApiSignature());
		// we retry PayPalAPI transactionSearch response 
		TransactionSearchResponseType transactionSearchResponse = binding.transactionSearch(transactionSearchReq, 
				customSecurityHeaderType);

		info("PayPalAPI.api.ebay.transactionSearch.ACK - " + transactionSearchResponse.getAck());
		info("PayPalAPI.api.ebay.transactionSearch.ERRORS - " + transactionSearchResponse.getErrors());
		if (transactionSearchResponse.getErrors() != null && transactionSearchResponse.getErrors().length > 0) {
			for (ErrorType nextError : transactionSearchResponse.getErrors()) {
				error("PayPalAPI.api.ebay.transactionSearch.error.CODE - " + nextError.getErrorCode());
				error("PayPalAPI.api.ebay.transactionSearch.error.LONG_MSG - " + nextError.getLongMessage());
				error("PayPalAPI.api.ebay.transactionSearch.error.SHORT_MSG - " + nextError.getShortMessage());
				throw new Exception(nextError.getShortMessage());
			}
		}
		return transactionSearchResponse;
	}
	
	
	private TransactionSearchResponseType transactionSearch(String idTransazione,Calendar paymentDate, GeneraMessageGTWResponse generaMessageGTWResponse) throws Exception {
		// we initialize payPalAPISoapBindingStub
		PayPalAPISoapBindingStub binding = new PayPalAPISoapBindingStub(new URL(generaMessageGTWResponse.getApiEndPointUrl()), null);
		// we prepare transactionSearchReq
		TransactionSearchReq transactionSearchReq = new TransactionSearchReq(); {
			TransactionSearchRequestType transactionSearchRequestType = new TransactionSearchRequestType(); {
				Calendar startDate = Calendar.getInstance();
				Calendar endDate = Calendar.getInstance();
				// we check If input dataPagamento is empty then search all transaction by sysdate
				String dataPagamento = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
				if (paymentDate != null) {
					DateFormat dfRead = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
					dataPagamento = new SimpleDateFormat("MM/dd/yyyy").format(paymentDate.getTime());
					startDate.setTime(dfRead.parse(dataPagamento + " 00:00:00 +0000"));
					info(Events.TRA_ALIGN.format() + " | PAYPAL: interrogo elenco con data " + dataPagamento);
					
					paymentDate.add(Calendar.DAY_OF_MONTH, 1);
					dataPagamento = new SimpleDateFormat("MM/dd/yyyy").format(paymentDate.getTime());
					endDate.setTime(dfRead.parse(dataPagamento + " 23:59:59 +0000"));
				} else {
					DateFormat dfRead = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
					startDate.setTime(dfRead.parse(dataPagamento + " 00:00:00 +0000"));
					endDate.setTime(dfRead.parse(dataPagamento + " 23:59:59 +0000"));					
				}

				// we set on transactionSearchRequestType startDate & endDate 
				transactionSearchRequestType.setStartDate(startDate);
				transactionSearchRequestType.setEndDate(endDate);
				// QF magia
				transactionSearchRequestType.setInvoiceID(idTransazione);
				
				
				
				transactionSearchRequestType.setVersion(generaMessageGTWResponse.getApiVersion());
			}
			transactionSearchReq.setTransactionSearchRequest(transactionSearchRequestType);
		}
		// we prepare customSecurityHeaderType
		CustomSecurityHeaderType customSecurityHeaderType =
			this.getApiCustomSecurityHeader(generaMessageGTWResponse.getApiUsername(), 
					generaMessageGTWResponse.getApiPassword(), generaMessageGTWResponse.getApiSignature());
		// we retry PayPalAPI transactionSearch response 
		TransactionSearchResponseType transactionSearchResponse = binding.transactionSearch(transactionSearchReq, 
				customSecurityHeaderType);

		info("PayPalAPI.api.ebay.transactionSearch.ACK - " + transactionSearchResponse.getAck());
		info("PayPalAPI.api.ebay.transactionSearch.ERRORS - " + transactionSearchResponse.getErrors());
		if (transactionSearchResponse.getErrors() != null && transactionSearchResponse.getErrors().length > 0) {
			for (ErrorType nextError : transactionSearchResponse.getErrors()) {
				error("PayPalAPI.api.ebay.transactionSearch.error.CODE - " + nextError.getErrorCode());
				error("PayPalAPI.api.ebay.transactionSearch.error.LONG_MSG - " + nextError.getLongMessage());
				error("PayPalAPI.api.ebay.transactionSearch.error.SHORT_MSG - " + nextError.getShortMessage());
				throw new Exception(nextError.getShortMessage());
			}
		}
		return transactionSearchResponse;
	}
	
	/**
	 * @param transactionID
	 * @param generaMessageGTWResponse
	 * @return
	 * @throws Exception
	 */
	private GetTransactionDetailsResponseType getTransactionDetails(String transactionID, GeneraMessageGTWResponse generaMessageGTWResponse) throws Exception {
		// we initialize payPalAPISoapBindingStub
		PayPalAPISoapBindingStub binding = new PayPalAPISoapBindingStub(new URL(generaMessageGTWResponse.getApiEndPointUrl()), null);
		// we prepare transactionDetailsReq
		GetTransactionDetailsReq transactionDetailsReq = new GetTransactionDetailsReq(); {
			GetTransactionDetailsRequestType getTransactionDetailsRequest = new GetTransactionDetailsRequestType(); {
				getTransactionDetailsRequest.setTransactionID(transactionID);
				getTransactionDetailsRequest.setVersion(generaMessageGTWResponse.getApiVersion());
			}
			transactionDetailsReq.setGetTransactionDetailsRequest(getTransactionDetailsRequest);
		}
		// we prepare customSecurityHeaderType
		CustomSecurityHeaderType customSecurityHeaderType =
			this.getApiCustomSecurityHeader(generaMessageGTWResponse.getApiUsername(), 
					generaMessageGTWResponse.getApiPassword(), generaMessageGTWResponse.getApiSignature());
		// we retry PayPalAPI transactionSearch response 
		GetTransactionDetailsResponseType transactionDetailsResp = binding.getTransactionDetails(transactionDetailsReq,  
				customSecurityHeaderType);
		
		
		info("PayPalAPI.api.ebay.getTransactionDetails.ACK - " + transactionDetailsResp.getAck());
		if (transactionDetailsResp.getErrors() != null && transactionDetailsResp.getErrors().length > 0) {
			for (ErrorType nextError : transactionDetailsResp.getErrors()) {
				error("PayPalAPI.api.ebay.getTransactionDetails.error.CODE - " + nextError.getErrorCode());
				error("PayPalAPI.api.ebay.getTransactionDetails.error.LONG_MSG - " + nextError.getLongMessage());
				error("PayPalAPI.api.ebay.getTransactionDetails.error.SHORT_MSG - " + nextError.getShortMessage());
				//throw new Exception(nextError.getShortMessage());
			}
		}
		if (transactionDetailsResp.getPaymentTransactionDetails().getPaymentItemInfo().getCustom() != null)
		{
			info("PayPalAPI.api.ebay.getTransactionDetails.paymentItemInfo.CUSTOM - " 
				+ transactionDetailsResp.getPaymentTransactionDetails().getPaymentItemInfo().getCustom());
			
		}
		
		return transactionDetailsResp;
	}
	
	// QF modificato getExpressCheckoutDetails in modo da richiamare questo metodo per avere il dettaglio di una tranaszione
	// come detto da PAYPAL.
	
	private GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(PaymentTransactionSearchResultType transactionID, 
			GeneraMessageGTWResponse messageGTWResponse) throws Exception {
		// we prepare expressCheckoutDetails request
		GetExpressCheckoutDetailsReq expressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq(); {
			GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequestType = new GetExpressCheckoutDetailsRequestType(); {
				getExpressCheckoutDetailsRequestType.setToken(transactionID.getTransactionID());
				getExpressCheckoutDetailsRequestType.setVersion(messageGTWResponse.getApiVersion());
			}
			expressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(getExpressCheckoutDetailsRequestType);
		}
		// we set customSecurityHeader
		CustomSecurityHeaderType customSecurityHeaderType = getApiCustomSecurityHeader(messageGTWResponse.getApiUsername(),
				messageGTWResponse.getApiPassword(), messageGTWResponse.getApiSignature());

		// we call PayPalAPI service - getExpressCheckoutDetails
		PayPalAPIAASoapBindingStub binding = new PayPalAPIAASoapBindingStub(new URL(messageGTWResponse.getApiEndPointUrl()), null);
		// we retry response
		GetExpressCheckoutDetailsResponseType expressCheckoutDetailsResponse = 
			binding.getExpressCheckoutDetails(expressCheckoutDetailsReq, customSecurityHeaderType);        	

		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.ACK - " + expressCheckoutDetailsResponse.getAck());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.TOKEN (returned by setExpressCheckout) - " + transactionID);
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.PAYERID - " + expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.CUSTOM - " + expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getCustom());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.PAYMENT_ACTION - " + 
				expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.AMOUNT - " + 
				expressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails());
		info("PayPalAPI.api.ebay.getExpressCheckoutDetails.ERRORS - " + expressCheckoutDetailsResponse.getErrors());
		if (expressCheckoutDetailsResponse.getErrors() != null && expressCheckoutDetailsResponse.getErrors().length > 0) {
			for (ErrorType nextError : expressCheckoutDetailsResponse.getErrors()) {
				error("PayPalAPI.api.ebay.getExpressCheckoutDetails.error.CODE - " + nextError.getErrorCode());
				error("PayPalAPI.api.ebay.getExpressCheckoutDetails.error.LONG_MSG - " + nextError.getLongMessage());
				error("PayPalAPI.api.ebay.getExpressCheckoutDetails.error.SHORT_MSG - " + nextError.getShortMessage());
				throw new Exception(nextError.getShortMessage());    			
			}
		}
		return expressCheckoutDetailsResponse;
	}
	
	
	
	/**
	 * @param object
	 * @return
	 * @author marco.montisano
	 */
	private CustomSecurityHeaderType getApiCustomSecurityHeader(String apiUsername, String apiPassword, String apiSignature) throws Exception {
		CustomSecurityHeaderType customSecurityHeaderType = new CustomSecurityHeaderType(); {
			UserIdPasswordType credentials = new UserIdPasswordType(); {
				credentials.setUsername(apiUsername);
				credentials.setPassword(apiPassword);
				credentials.setSignature(apiSignature);
			}
			customSecurityHeaderType.setCredentials(credentials);
		}
		return customSecurityHeaderType;
	}

	/**
	 * @param object
	 * @return
	 * @author 	Vincenzo Ammendola	 
	 * E' il metodo che verrà chiamato in DttCommons nel servizio EseguiPagamento serve per il 
	 * pagamento tramite Setefi	 
	 * <br><br>	 
	 * @param in com.seda.payer.gateways.webservice.dati.IntegraGTWSincDTTResponse
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#integraGTWSincDTT(com.seda.payer.gateways.webservice.dati.IntegraGTWSincDTTRequest in)
	 */
	public com.seda.payer.gateways.webservice.dati.IntegraGTWSincDTTResponse integraGTWSincDTT(com.seda.payer.gateways.webservice.dati.IntegraGTWSincDTTRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType {

		initSetefiConfig();
//		System.out.println("type: "+ setefiDttType);
//		System.out.println("alias: "+ setefiDttAlias);
//		System.out.println("path: "+ setefiDttPath );
//		System.out.println("action: "+ setefiDttAction );
//		System.out.println("currencyCode: "+ setefiDttCurrencycode );
//		System.out.println("udf1: "+ setefiDttUdf1);
//		System.out.println("result1: "+setefiDdttResult );
//
//		Random rnd = new Random(System.currentTimeMillis()); 		
//		e24TranPipe e24tp = new e24TranPipe();	
//		e24tp.setCard(in.getCardNumber());
//		e24tp.setAmt(String.valueOf(in.getImpTotTrans())); 
//		e24tp.setType(setefiDttType);
//		e24tp.setAlias(setefiDttAlias);
//		
//		
//		e24tp.setResourcePath(setefiDttPath);
//		e24tp.setAction(setefiDttAction );
//		e24tp.setExpMonth(in.getExpMonth());
//		e24tp.setExpYear(in.getExpYear());
//		e24tp.setCurrencyCode(setefiDttCurrencycode );
//		e24tp.setMember(in.getHolder());
//		e24tp.setCvv2(in.getCvv2());
//		e24tp.setTrackId((String.valueOf(Math.abs(rnd.nextLong()))).substring(0,8));
//		e24tp.setUdf1(setefiDttUdf1);
//		
//	
//		int result = Integer.parseInt(setefiDdttResult);
//		System.out.println("int result = Integer.parseInt(result1)=  "+ result);
//		try {
//			info(" performTransaction() ");
//			result = e24tp.performTransaction();
//		}
//		catch (NotEnoughDataException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		
//		
		IntegraGTWSincDTTResponse transazione = new IntegraGTWSincDTTResponse(); 
//		
//		if (result == e24TranPipe.FAILURE) {
//			transazione.setEsitoTransazione("errore");		
//
//		}
//		else if (result == e24TranPipe.SUCCESS && e24tp.getResult().equalsIgnoreCase
//				("APPROVED")) {
//			
//			String transId = e24tp.getTransId();
//			
//			transazione.setEsitoTransazione("OK:" + e24tp.getTransId());
//			
//			
//			info("");
//			info("Result Code = " + e24tp.getResult());
//			info("Auth Code = " + e24tp.getAuth());
//			info("Tran Ref = " + e24tp.getRef());
//			info("Card Balance = "+ e24tp.getCardBalance());
//			info("AVR Code = " + e24tp.getAvr());
//			info("Post Date = " + e24tp.getDate());
//			info("TransId = " + transId);
//			info("TrackId = " + e24tp.getTrackId());
//			info("UDF1 = " + e24tp.getUdf1());
//			info("UDF2 = " + e24tp.getUdf2());
//			info("UDF3 = " + e24tp.getUdf3());
//			info("UDF4 = " + e24tp.getUdf4());
//			info("UDF5 = " + e24tp.getUdf5());
//			
//			//sincDTTResponse.setEsitoTransazione("e24tp.getError()"+":"+e24tp.getTrackId());  // ??????
//		}
//		else {
//			/* RICORDA
//			 * il valore è KO per due attuali (15-10-2008) possibili motivi:
//			 * 1-la risposta di Setefi è FAILURE;
//			 * 2-la risposta è SUCCESS ma il campo result non è valorizzato
//			 */	
//			transazione.setEsitoTransazione("KO");				
//			//sincDTTResponse.setEsitoTransazione("e24tp.getError()"+":"+e24tp.getTrackId());  // ??????			
//		}
//
//		info("fine del serevizio gateway ");
//
//
//		
//
//		info("fine del serevizio gateway "+ transazione.getEsitoTransazione());
//		System.out.println("fine del serevizio gateway "+ transazione.getEsitoTransazione());
		return transazione;
	}
	
	
	public RIDAdesioneResponse adesioneRID(RIDAdesioneRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType {
		
		RIDAdesioneResponse resp = new RIDAdesioneResponse();
		try
		{			
			info("adesioneRID: Request execute");	
			
			// prelevo il servizio facade
			String idOperation = (String) new UUIDGenerator(UUIDGenerator.BIT_FRMT_12).generateSecureToken();			
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
			GatewayPagamentoBean service = getFacadeService();
			//fine LP PG200070 - 20200812
			
			// cancello il vecchio record dell'utente e ne genero uno nuovo per l'adesione
			RidDto ridFacade = Generics_RID_WS.convertRID(in, idOperation);
			if (ridFacade != null)
			{
				Generics_RID_WS.saveNewAdesioneRevoca(service, ridFacade, LOG, dbSchemaCodSocieta);
				
				// chiamata al S2S di Infogroup
				String[] sReturnCode_Desc_XmlResponse = sendRidGenericS2S("initServizio", GenericsGTW.xmlTextRID_Adesione(in, idOperation));
				String[] sReturnCode_Desc_S2SResponse = GenericsGTW.parseRidAdesione(resp, sReturnCode_Desc_XmlResponse[1], LOG);
				
				// setto i nuovi parametri arrivati dalla response
				Generics_RID_WS.modifyRID(sReturnCode_Desc_S2SResponse, sReturnCode_Desc_XmlResponse, ridFacade);
		    	// scrivo nel DB l'esisto della chiamata			
		    	service.saveRID(ridFacade, dbSchemaCodSocieta);
		    	// dei dati sono imposti nel DB (date di salvataggio)
	    		// quindi devo rifare il get dell'elemento
		    	ridFacade = service.getRID(ridFacade.getCodiceDebitore(), dbSchemaCodSocieta);
		    	
				// invio mail per la richiesta di revoca RID			
		    	service.sendRIDMailAdesioneRevoca(ridFacade, dbSchemaCodSocieta);
		    	
		    	//PG100055: settare il path del pdf creato (relativo all'adaesione)	    	
		    	resp.setPathPdf(this.recuperaPathPdfRID(ridFacade.getCodiceFiscale()));
		    	
				info("adesioneRID: Request finished");	
			}
			else
			{
				error("adesioneRID not valid ridFacade");
				resp.setRetCode("01");
				resp.setRetMessage("Si è verificato un problema durante il recupero del ridFacade in adesioneRID");
			}
			
		}
		catch (Exception e) {
			error("adesioneRID " + e.getMessage());
			resp.setRetCode("01");
			resp.setRetMessage("Si è verificato un problema durante il recupero delle configurazioni.");
			e.printStackTrace();
		}
		
		return resp;
    }
    public RIDRevocaResponse revocaRID(RIDRevocaRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType {
    	
    	RIDRevocaResponse resp = new RIDRevocaResponse();
		try
		{			
			info("revocaRID: Request execute");	
			
			// prelevo il servizio facade
			String idOperation = (String) new UUIDGenerator(UUIDGenerator.BIT_FRMT_12).generateSecureToken();
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
			GatewayPagamentoBean service = getFacadeService();
			//fine LP PG200070 - 20200812
			
			
			// prelevo il precedente RID (deve esistere se chiedo la revoca)
			RidDto ridFacade = service.getRID(in.getCodiceDebitore(), dbSchemaCodSocieta);			
			if (ridFacade != null)
			{				
				// cancello il vecchio record dell'utente e ne genero uno nuovo per la revoca con i campi aggiornati
				Generics_RID_WS.convertRID(in, idOperation, ridFacade);
				Generics_RID_WS.saveNewAdesioneRevoca(service, ridFacade, LOG,dbSchemaCodSocieta);
				
				// chiamata al S2S di Infogroup
				String[] sReturnCode_Desc_XmlResponse = sendRidGenericS2S("initServizio", GenericsGTW.xmlTextRID_Revoca(in, idOperation));
				String[] sReturnCode_Desc_S2SResponse = GenericsGTW.parseRidRevoca(resp, sReturnCode_Desc_XmlResponse[1], LOG);
				
				// setto i nuovi parametri arrivati dalla response
				Generics_RID_WS.modifyRID(sReturnCode_Desc_S2SResponse, sReturnCode_Desc_XmlResponse, ridFacade);
		    	// scrivo nel DB l'esisto della chiamata
		    	service.saveRID(ridFacade, dbSchemaCodSocieta);
		    	// dei dati sono imposti nel DB (date di salvataggio)
	    		// quindi devo rifare il get dell'elemento
		    	ridFacade = service.getRID(ridFacade.getCodiceDebitore(),dbSchemaCodSocieta);
		    	
		    	// invio mail per la richiesta di revoca RID			
				service.sendRIDMailAdesioneRevoca(ridFacade, dbSchemaCodSocieta);
				
		    	info("revocaRID: Request finished");	
			}
			else
			{	
				error("revocaRID: Request finished not valid revoca");
				resp.setRetCode("01");
				resp.setRetMessage("Si è verificato un problema durante il recupero del ridFacade in revocaRID");
			
			}
			
		}
		catch (Exception e) {
			error("revocaRID " + e.getMessage());
			resp.setRetCode("01");
			resp.setRetMessage("Si è verificato un problema durante il recupero delle configurazioni.");
		}
		
		return resp;
    }
    public RIDVerificaStatoResponse verificaStatoRID(RIDVerificaStatoRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType {
    	
    	RIDVerificaStatoResponse resp = new RIDVerificaStatoResponse();
    	resp.setCodiceAEA("");
    	resp.setCodiceAEADescrizione("");
    	
    	//inizializzazione (campi non nillable)
    	Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.N, 
    			RIDVerificaStatoResponseStatoRichiesta.NONE ,
				false, false, false, resp);
    	try
		{		
    		info("verificaStatoRID: Request execute");	
    		
			// prelevo il servizio facade
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
			GatewayPagamentoBean service = getFacadeService();
			//fine LP PG200070 - 20200812
			
			// prelevo il vecchio record dell'utente
	    	RidDto ridFacade = service.getRID(in.getCodiceFiscale(),dbSchemaCodSocieta);

//	    	Ric		TIPO	STATO		AD	RE	AB
//	    	Nessuna		N	Nessuna		Y	N	N
//	    	Adesione	A	In errore	Y	N	N
//	    	Adesione	A	In corso	N	N	N
//	    	Adesione	A	Accettata	N	Y	Y
//	    	Adesione	A	Negata		Y	N	N
//	    	Revoca		R	In errore	N	Y	Y
//	    	Revoca		R	In corso	N	N	N
//	    	Revoca		R	Accettata	Y	N	N
//	    	Revoca		R	Negata		N	Y	Y

	    	if (ridFacade == null)
	    	{
	    		// Nessuna		N	Nessuna		Y	N	N		    	
	    		// non esiste un RID nel DB per l'utente indicato
	    		Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.N, 
	    				RIDVerificaStatoResponseStatoRichiesta.NONE,  
	    				true, false, false, resp);	        	
	    	}
	    	else
	    	{
	    		// richiesta
		    	if (Generics_RID.isFunzioneAdesione(ridFacade))
		    	{
		    		// adesione
		    		if (Generics_RID.isRIDRichiestaAccettata(ridFacade))
		    		{
		    			// richiesta accettata (XML non in errore)		    			
		    			if (Generics_RID.isRIDInCorso(ridFacade))
		    			{
		    				// Adesione	A	In corso	N	N	N
		    				Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.A, 
			    					RIDVerificaStatoResponseStatoRichiesta.INCORSO,  
				    				false, false, false, resp);	
			    		}
		    			else
		    			{
		    				if (Generics_RID.isRIDBancaEsitoPositivo(ridFacade))
		    				{
		    					// Adesione	A	Accettata	N	Y	Y
		    					Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.A, 
				    					RIDVerificaStatoResponseStatoRichiesta.ACCETTATA,  
					    				false, true, true, resp);	
		    				}
				    		else
				    		{
				    			// Adesione	A	Negata		Y	N	N
				    			Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.A, 
				    					RIDVerificaStatoResponseStatoRichiesta.NEGATA,  
					    				true, false, false, resp);	
				    			
				    			resp.setCodiceAEA(ridFacade.getCodiceCausaleAEA());
				    	    	resp.setCodiceAEADescrizione(ridFacade.getDescrizioneeCausaleAEA());
				    	    	
				    		}
		    			}
		    		}
		    		else
		    		{
		    			// in errore
		    			// Adesione	A	In errore	Y	N	N
		    			Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.A, 
		    					RIDVerificaStatoResponseStatoRichiesta.INERRORE,  
			    				true, false, false, resp);	
		    		}
		    	}
		    	else
		    	{
		    		// revoca
		    		if (Generics_RID.isRIDRichiestaAccettata(ridFacade))
		    		{
		    			// richiesta accettata (XML non in errore)
		    			if (Generics_RID.isRIDInCorso(ridFacade))
		    			{
		    				// Revoca		R	In corso	N	N	N
		    				Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.R, 
			    					RIDVerificaStatoResponseStatoRichiesta.INCORSO,  
				    				false, false, false, resp);	
			    		}
		    			else
		    			{
		    				if (Generics_RID.isRIDBancaEsitoPositivo(ridFacade))
		    				{
		    					// Revoca		R	Accettata	Y	N	N
		    					Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.R, 
				    					RIDVerificaStatoResponseStatoRichiesta.ACCETTATA,  
					    				true, false, false, resp);	
		    				}
				    		else
				    		{
				    			// Revoca		R	Negata		N	Y	Y
				    			Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.R, 
				    					RIDVerificaStatoResponseStatoRichiesta.NEGATA,  
					    				false, true, true, resp);	
				    		
				    			resp.setCodiceAEA(ridFacade.getCodiceCausaleAEA());
				    	    	resp.setCodiceAEADescrizione(ridFacade.getDescrizioneeCausaleAEA());
				    		}		    				
			    		}
		    		}
		    		else
		    		{
		    			// in errore
		    			// Revoca		R	In errore	N	Y	Y
		    			Generics_RID_WS.setResponseRID(RIDVerificaStatoResponseTipoRichiesta.R, 
		    					RIDVerificaStatoResponseStatoRichiesta.INERRORE,  
			    				false, true, true, resp);	
		    		}        	
		    	}
	    	}
	    	
	    	// risposta
			resp.setRetCode("00");
			resp.setRetMessage("OK");
			
			info("verificaStatoRID: Request finished FlagAdesione:"+resp.getFlagAdesione()+" FlagRevoca:"+resp.getFlagRevoca()+" FlagAbilitazioneRID:"+resp.getFlagAbilitazioneRID());	
			
		}
		catch (Exception e) {
			error("verificaStatoRID " + e.getMessage());
			resp.setRetCode("01");
			resp.setRetMessage("Si è verificato un problema durante il recupero delle configurazioni.");
		}
		
		return resp;
    }
    public RIDAllineaResponse allineaRID(RIDAllineaRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType
    {
    	initConfig();
    	RIDAllineaResponse resp = new RIDAllineaResponse();
    	resp.setRetCode("03");
    	resp.setRetMessage("error generic");
		try
		{	
	    	info("allineaRID: Request execute");	
				
			// prelevo il servizio facade
			// String idOperation = (String) new UUIDGenerator(UUIDGenerator.BIT_FRMT_12).generateSecureToken();
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
			GatewayPagamentoBean service = getFacadeService();
			//fine LP PG200070 - 20200812
			String[] sRes = service.allineaRID(dbSchemaCodSocieta);
			// parametri della response uguali alla request
			resp.setRetCode(sRes[0]);
			resp.setRetMessage(sRes[1]);				
	
			info("allineaRID: Request finished resp:"+resp.getRetCode()+" message:"+resp.getRetMessage());	
			
		}
		catch (Exception e) {
			error("allineaRID " + e.getMessage());
			resp.setRetCode("01");
			resp.setRetMessage("Si è verificato un problema durante il recupero delle configurazioni.");
		}
		
		return resp;
    }
    public RIDAllineaArchiviazioneResponse allineaRIDArchiviazione(RIDAllineaArchiviazioneRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType
    {
    	initConfig();
    	RIDAllineaArchiviazioneResponse resp = new RIDAllineaArchiviazioneResponse();
//    	resp.setRetCode("03");
//    	resp.setRetMessage("error generic");
//		try
//		{	
//	    	info("allineaRIDArchiviazione: Request execute");	
//			
//	    	// allineo l'archiviazione dei file
//	    	String[] sRes = Generics_RID_AllineaPDF.manageAllineaRID_PDF(propertiesTree(), LOG);			
//			// parametri della response uguali alla request
//			resp.setRetCode(sRes[0]);
//			resp.setRetMessage(sRes[1]);				
//	
//			info("allineaRIDArchiviazione: Request finished resp:"+resp.getRetCode()+" message:"+resp.getRetMessage());	
//			
//		}
//		catch (Exception e) {
//			error("allineaRIDArchiviazione " + e.getMessage());
//			resp.setRetCode("01");
//			resp.setRetMessage("Si è verificato un problema durante il recupero delle configurazioni.");
//		}
		
		return resp;
    }
    
       
    private URL getUrlRidS2S() throws MalformedURLException
	{
		return new URL(propertiesTree().getProperty(PropKeys.RID_URL.format()));
	}	
    /**
     * Ritorna la response con la comunicazione del gateway di InfoGroup 
     * ovvero il codice di ritorno= returncode[0]  e l'xml[1]
     * @param sNameMethodWS
     * @param sXmlToSend
     * @return
     * @throws RemoteException
     * @throws FaultType
     */
    private String[] sendRidGenericS2S(String sNameMethodWS, String sXmlToSend) throws RemoteException, FaultType {
    	String[] sResponse = new String[2];
		try {
			info("sendRidGenericS2S request - " + sXmlToSend);

			String proxyHost = System.getProperty("http.proxyHost");
			Integer proxyPort = 8080;
			try 
			{ 
				proxyPort= Integer.parseInt(System.getProperty("http.proxyPort","8080"));
			} 
			catch (Exception e) 
			{
				error("sendRidGenericS2S - Invalid proxy port. Use default port 8080"); 
			}

			String proxyUser = System.getProperty("http.proxyUser");
			String proxyPassword = System.getProperty("http.proxyPassword");
			HttpClient client = new HttpClient();
			if (proxyHost != null) 
			{
				HostConfiguration config = client.getHostConfiguration(); 
				config.setProxy(proxyHost, proxyPort);
				AuthScope authScope = new AuthScope(proxyHost, proxyPort); 
				info("sendRidGenericS2S - http.proxyHost = " + proxyHost);
				info("sendRidGenericS2S - http.proxyPort = " + proxyPort);
				Credentials credentials = null;
				if (proxyUser != null) {
					credentials = new UsernamePasswordCredentials(proxyUser, proxyPassword);
					info("sendRidGenericS2S - http.proxyUser = " + proxyUser);
				}
				client.getState().setProxyCredentials(authScope, credentials); 
			}
			
           URL url = getUrlRidS2S();
           info("sendRidGenericS2S infogroup url - " + url);
           PostMethod method = new PostMethod(url.getPath());
           method.addParameter(sNameMethodWS, sXmlToSend);
           if (url.getProtocol().equals("https")) 
           {
//               Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new EasySSLProtocolSocketFactory(), 443);
               Protocol sslSelfSignedCert = new Protocol("https", (ProtocolSocketFactory) new SSLProtocolSocketFactory(), 443); // Quaresima 27/02/2018
               client.getHostConfiguration().setHost(url.getHost(), 443, sslSelfSignedCert);
               info("sendRidGenericS2S infogroup http host - " + url.getHost());
               info("sendRidGenericS2S infogroup http port - " + client.getHostConfiguration().getPort());
           } 
           else 
        	   client.getHostConfiguration().setHost(url.getHost(), url.getPort());

           // chiamata S2S payer --> concentratore (richiesta RID adesione)
           int returnCode = client.executeMethod(method);
           info("sendRidGenericS2S infogroup http returnCode - " + returnCode);
           String response = method.getResponseBodyAsString();
           info("sendRidGenericS2S infogroup http response - " + response);
           String xmlText = response.substring(response.indexOf("=")+1);
			info("sendRidGenericS2S infogroup http xml response - " + xmlText);
			
			// risposta S2S concentratore --> payer (accettazione RID) ritorna URL per la conferma dell'utente 
			sResponse[0] = String.valueOf(returnCode);
			sResponse[1] = xmlText;
           return sResponse;

		} catch (Exception ex) {
			error("sendRidGenericS2S failed - generic error due to: ", ex);
			ex.printStackTrace();
			return sResponse;
		}
	}   
    private String recuperaPathPdfRID(String codiceFiscale)
	{
		String sPath = "";
		try
		{
			// we initialize notificheStub
			this.info("recuperaPathPdfRID - init");
			NotificheSOAPBindingStub notificheCaller = GatewaysIGHelper.getNotificheWS(propertiesTree(), dbSchemaCodSocieta);

			RecuperaPdfRIDRequestType in = new RecuperaPdfRIDRequestType();
			in.setCodiceFiscale(codiceFiscale);			
			RecuperaPdfRIDResponseType responsePdfRID = notificheCaller.recuperaPdfRID(in);
			if (responsePdfRID.getResponse().getRetcode().getValue().equals(Generics_RID.PAYER_CODE_OK))
			{
				sPath = responsePdfRID.getPdfFilePath();
				this.info("recuperaPathPdfRID - end - path del pdf del RID:"+sPath);
			}
			else
				this.error("recuperaPathPdfRID - end - NOT VALID path del pdf del RID:"+sPath);
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		// andata a buon fine  
		return sPath;
						
	}
   
    
    @SuppressWarnings("unused")
	private void Test() throws Exception
    {
		
//		// TEST send MAIL
//		// prelevo il vecchio record dell'utente
		//inizio LP PG200070 - 20200812
		//GatewayPagamentoBean service = new GatewayPagamentoBean();
		GatewayPagamentoBean service = getFacadeService();
		//fine LP PG200070 - 20200812
		RidDto ridFacade = service.getRID("MRTDNL75D22E388B", "000TO");	    	
    	service.sendRIDMailAdesioneRevoca(ridFacade, "000TO");
    	String sPathfileRID = this.recuperaPathPdfRID(ridFacade.getCodiceFiscale());
    	
    	ridFacade.setCodiceEsitoRispostaS2S("1");
    	service.sendRIDMailAdesioneRevoca(ridFacade, "000TO");

    	// la transazione è MAV è gestita all'interno 
		//GatewaysIGHelper.allineaTransazioneIG("test", "250611", "d579208b-db5a-4e02-973e-3e7e4a2f1ada", "idOperazione", "0000000005000", "nome_file_RNINCATRANS",  propertiesTree(), LOG);
		
    	
    }
    
    
    /**
	 * Dovrà effettuare la richiesta di storno presso il gateway di pagamento Infogroup solo per pagamenti fatti con CARTA DI CREDITO.
	 * <br>
	 * Avrà come parametri di input la chiave della transazione <code>codiceMerchant</code>,
	 * l'id dell'operazione sul gateway <code>idOperazione</code> e l'importo netto della transazione <code>importoNetto</code>.
	 * <br>
	 * Avrà come parametri di output un codice esito operazione <code>retCode</code>, un messaggio di ritorno
	 * relativo al codice esito <code>retMessage</code>
	 * <br>
	 * @param in com.seda.payer.gateways.webservice.dati.RichiediStornoRequest
	 * @see com.seda.payer.gateways.webservice.source.GatewaysInterface#richiediStorno(com.seda.payer.gateways.webservice.dati.RichiediStornoRequest)
	 */
    public com.seda.payer.gateways.webservice.dati.RichiediStornoResponse richiediStorno(com.seda.payer.gateways.webservice.dati.RichiediStornoRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType {
    	    	
    	try {
    		
    		    		
    		// we retry gateway info
			GeneraMessageGTWResponse generaMessageGTWResponse = this.generaMessageGTW(
					new GeneraMessageGTWRequest(new GatewayType(in.getChiaveGateway(), EMPTY_TOKEN), 
							new BigDecimal(0)));
    		
			if (generaMessageGTWResponse.getApiEndPointUrl().trim().length() == 0)
				return new RichiediStornoResponse("-1", "Errore nella configurazione del gateway di pagamento: parametro ApiEndPointUrl mancante");
    		
			RedirectToGTWIGRequest gtwigRequest = new RedirectToGTWIGRequest(
					CanaleAttivazioneTypeKeys.parse(in.getCanalePagamento()), 
					StrumentoPagamentoTypeKeys.CARTA_DI_CREDITO.format(), in.getImportoNetto(), 
					in.getCodiceMerchant(),in.getIdOperazione(), generaMessageGTWResponse.getApiEndPointUrl());
			
			
			RedirectToGTWIGResponse redirectToGTWIGResponse = richiediStornoS2S(gtwigRequest);
			
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
			
			
			StringBuilder sb=new StringBuilder();
			TransazioniBeanTransazioni beanTransazioni=new TransazioniBeanTransazioni();
			
			beanTransazioni.setChiave_transazione(in.getCodiceMerchant());
			if (redirectToGTWIGResponse.getCodiceEsito().equals("0")){
				sb.append("Richiesta storno: OK|Data richiesta: " + GatewaysIGHelper.getDateTime() +"|Operatore: " + in.getCodiceOperatore());
				beanTransazioni.setTipo_storno("1");
				beanTransazioni.setNote_generiche(sb.toString());
			} else {
				sb.append("Richiesta storno: KO|Data richiesta: " + GatewaysIGHelper.getDateTime() +"|Operatore: " + in.getCodiceOperatore());
				beanTransazioni.setNote_generiche(sb.toString());
			}
			
			AggiornaTransazioneGenericRequest request=new AggiornaTransazioneGenericRequest();
			Transazioni transazione=new Transazioni();
			
			transazione.setBeanTransazioni(beanTransazioni);
			
			request.setTransazione(transazione);
			AggiornaTransazioneGenericResponse responseAggiornaTransazioneGenericResponse = binding.aggiornaTransazioneGeneric(request);
			

			if(!responseAggiornaTransazioneGenericResponse.getResponse().getRetCode().getValue().equals(ResponseTypeRetCode.value1.getValue()) ||
					!redirectToGTWIGResponse.getCodiceEsito().equals("0")) {
				
				return new RichiediStornoResponse("-3","Errore nell'esecuzione dello storno!!"); 
				
			}

			return new RichiediStornoResponse(redirectToGTWIGResponse.getCodiceEsito(), 
					redirectToGTWIGResponse.getDescrizioneEsito());
		
			
		} catch (Exception e) {
			error(e.getMessage());
			e.printStackTrace();
			return new RichiediStornoResponse("-2", e.getMessage());
		}
    	        
    }

	public GeneraFlussiRendicontazionePosResponse generaFlussiRendicontazionePos(
			GeneraFlussiRendicontazionePosRequest in) throws RemoteException,
			FaultType {
		GeneraFlussiRendicontazionePosResponse generaFlussiRendicontazionePosResponse = null;
//		ResponseType response = new ResponseType();
//		try {
			//inizio LP PG200070 - 20200812
			//GatewayPagamentoBean service = new GatewayPagamentoBean();
//			try {
//				GatewayPagamentoBean service = getFacadeService();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//fine LP PG200070 - 20200812
//			boolean bResult = service.generaFlussiRendicontazionePos(dbSchemaCodSocieta);
//
//			if (bResult) {
//				response.setRetMessage("WS method GeneraFlussiRendicontazionePos eseguito con successo");
//				response.setRetCode(ResponseTypeRetCode.value1);
//			} else {
//				response.setRetMessage("WS method GeneraFlussiRendicontazionePos eseguito con errore");
//				response.setRetCode(ResponseTypeRetCode.value2);
//			}
//
//			generaFlussiRendicontazionePosResponse = new GeneraFlussiRendicontazionePosResponse();
//			generaFlussiRendicontazionePosResponse.setResponse(response);
//
//		} catch (CreateException e) {
//			e.printStackTrace();
//			response.setRetMessage("WS method GeneraFlussiRendicontazionePos eseguito con errore");
//			response.setRetCode(ResponseTypeRetCode.value2);
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setRetMessage("WS method GeneraFlussiRendicontazionePos eseguito con errore");
//			response.setRetCode(ResponseTypeRetCode.value2);
//		}
		return generaFlussiRendicontazionePosResponse;
	}
    
	private static String calendarToString (Calendar cal){
		String strdate = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		if (cal != null) {
			strdate = sdf.format(cal.getTime());
		}
		return strdate;
	}

	public IntegraGTWAsincResponse integraGTWAsinc(IntegraGTWAsincRequest in)
			throws RemoteException, FaultType {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private String hashEncode(String cTran) {
		String shaKey="";
	    try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] dataBytes = cTran.getBytes("UTF-8");
			md.update(dataBytes);
			byte[] mdbytes = md.digest();
			shaKey=Base64.getEncoder().encodeToString(mdbytes);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Si sono verificati problemi nella generazione del codice hask associato alla chiave della transazione");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return shaKey;
	}
	
//	private String getFlagPaypal()
//	{
//		if (BaseFacadeHandler.propertiesTree != null)
//		{
//			try
//			{
//				return BaseFacadeHandler.propertiesTree.getProperty(PropertiesPath.contabilitaFtpUteGes.format(dbSchemaCodSocieta));
//			} catch (Exception e) {}
//		}
//		return "";
//	}
	
	//PG180150_001 GG - inizio
	private String getDescrizioneEnte(String listXml)
	{
		//inizio LP PG21XX04 Leak
		CachedRowSet webRowSetEnte = null;
		//fine LP PG21XX04 Leak
		try
		{
			//inizio LP PG21XX04 Leak
			//CachedRowSet webRowSetEnte = Convert.stringToWebRowSet(listXml);
			webRowSetEnte = Convert.stringToWebRowSet(listXml);
			//fine LP PG21XX04 Leak
			if (webRowSetEnte != null) 
			{
				if (webRowSetEnte.next())
					return webRowSetEnte.getString(6);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(webRowSetEnte != null) {
				try {
					webRowSetEnte.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		
		return "";
	}

	//Nodo pagamenti SPC
//	public SPCAllineaResponse allineaSPC(SPCAllineaRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.srv.FaultType
//    {
//    	SPCAllineaResponse resp = new SPCAllineaResponse();
//    	resp.setRetCode("03");
//    	resp.setRetMessage("error generic");
//		try
//		{	
//	    	info("allineaSPC: Request execute");	
				
			// prelevo il servizio facade
//			GatewayPagamentoFacade service = GatewayPagamentoFacadeServiceLocator.getHome(env).create();
//			String[] sRes = service.allineaRID(dbSchemaCodSocieta);
			// parametri della response uguali alla request
//			resp.setRetCode(sRes[0]);
//			resp.setRetMessage(sRes[1]);				
	
//			info("allineaSPC: Request finished resp:"+resp.getRetCode()+" message:"+resp.getRetMessage());	
//			
//		}
//		catch (Exception e) {
//			error("allineaSPC " + e.getMessage());
//			resp.setRetCode("01");
//			resp.setRetMessage("Si è verificato un problema durante il recupero delle configurazioni.");
//		}
//		
//		return resp;
//    }

//    private com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodo beanToBeanMIN_Core(ModuloIntegrazionePagamentiNodo object){
//    	com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodo mipBean = null;
//    	if (object != null)
//    	{
//    		mipBean = new com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodo();
//    		mipBean.setChiaveTransazione(object.getChiaveTransazione());
//    		mipBean.setNumeroOperazione(object.getNumeroOperazione());
//    		mipBean.setIdPortale(object.getIdPortale());
//    		mipBean.setXmlPaymentRequest(object.getXmlPaymentRequest());
//    		mipBean.setXmlPaymentData(object.getXmlPaymentData());
//    		mipBean.setStatoPagamentoCUP(object.getStatoPagamentoCUP());
//    		mipBean.setUltimoEsitoNotifica(object.getUltimoEsitoNotifica());
//    		mipBean.setCodicePortaleEsterno(object.getCodicePortaleEsterno());
//    		mipBean.setParametriOpzionali1(object.getParametriOpzionali1());
//    		mipBean.setParametriOpzionali2(object.getParametriOpzionali2());
//    		mipBean.setDataUltimoAggiornamento(object.getDataUltimoAggiornamento());
//    		mipBean.setCodiceSocieta(object.getCodiceSocieta());
//    		mipBean.setCodiceUtente(object.getCodiceUtente());
//    		mipBean.setChiaveEnte(object.getChiaveEnte());
//    		mipBean.setCommitNotifica(object.getCommitNotifica());
//    	}
//    	return mipBean;
//    }
	//PG180150_001 GG - fine
	
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
	
	//inizio LP PG200070
	/*
	public ConfigPagamentoSingleResponse recuperaFunzioneEnte(
			ConfigPagamentoSingleRequest in) throws java.rmi.RemoteException,
			com.seda.payer.pgec.webservice.commons.srv.FaultType {
		
		ConfigPagamentoSingleResponse result = new ConfigPagamentoSingleResponse();

		String jindi_context = env.getProperty(Context.INITIAL_CONTEXT_FACTORY);
		String jindi_provider = env.getProperty(Context.PROVIDER_URL);
		ConfigPagamento conf = null;

		try {
			CommonsFacadeRemoteHome serviceRemoteHome;
			serviceRemoteHome = (CommonsFacadeRemoteHome) ServiceLocator.getInstance().getRemoteHome(jindi_provider, jindi_context, null, null, CommonsFacadeRemoteHome.JNDI_NAME, CommonsFacadeRemoteHome.class);

			CommonsFacade service = serviceRemoteHome.create();

			ConfigPagamentoDto confDto = service.getConfigPagamento(in.getCodSocieta(), in.getCodUtente(), in.getChiaveEnte(), in.getCodTipologiaServizio(), in.getCanalePagamento(), dbSchemaCodSocieta);

			if (confDto != null) {
				conf = beanToBeanConfigPagamento(confDto);

				result.setRetCode("00");
				result.setRetMessage("FUNZIONE RECUPERATA CORRETTAMENTE PER L'ENTE SELEZIONATO: "
						+ in.getCodSocieta()
						+ " - "
						+ in.getCodUtente()
						+ " - "
						+ in.getChiaveEnte()
						+ ". TIPOLOGIA SERVIZIO: "
						+ in.getCodTipologiaServizio());
			} else {
				result.setRetCode("01");
				result.setRetMessage("FUNZIONE NON TROVATA PER L'ENTE SELEZIONATO: "
						+ in.getCodSocieta()
						+ " - "
						+ in.getCodUtente()
						+ " - "
						+ in.getChiaveEnte()
						+ ". TIPOLOGIA SERVIZIO: "
						+ in.getCodTipologiaServizio());
			}
		} catch (Exception e) {
			result.setRetCode("02");
			result.setRetMessage("SI E' VERIFICATO UN ERRORE DURANTE IL RECUPERO DELLA LISTA FUNZIONI PER L'ENTE SELEZIONATO: "
					+ in.getCodSocieta()
					+ " - "
					+ in.getCodUtente()
					+ " - "
					+ in.getChiaveEnte()
					+ ". TIPOLOGIA SERVIZIO: "
					+ in.getCodTipologiaServizio());
			e.printStackTrace();
		}

		result.setConfigPagamento(conf);

		return result;
	}

	private ConfigPagamento beanToBeanConfigPagamento(ConfigPagamentoDto object) {
		ConfigPagamento conf = new ConfigPagamento();
		if (object != null) {
			conf.setCodSocieta(object.getCodSocieta());
			conf.setCodUtente(object.getCodUtente());
			conf.setChiaveEnte(object.getChiaveEnte());
			conf.setCanalePagamento(object.getCanalePagamento());
			conf.setCodTipologiaServizio(object.getCodTipologiaServizio());
			conf.setDescTipologiaServizio(object.getDescTipologiaServizio());
			conf.setDescFunzionePagamento(object.getDescFunzionePagamento());
			conf.setNumeroContoCorrente(object.getNumeroContoCorrente());
			conf.setIntestatarioContoCorrente(object.getIntestatarioContoCorrente());
			conf.setTipoDocumento(object.getTipoDocumento());
			conf.setFlagControlloRange(object.getFlagControlloRange());
			conf.setEmailDestinatario(object.getEmailDestinatario());
			conf.setEmailCCN(object.getEmailCCN());
			conf.setEmailMittente(object.getEmailMittente());
			conf.setDescrMittente(object.getDescrMittente());
			conf.setFlagAllegato(object.getFlagAllegato());
			conf.setCodiceSIA(object.getCodiceSIA());
			conf.setCodiceIBAN(object.getCodiceIBAN());
			conf.setFlagTipoPagamento(object.getFlagTipoPagamento());
			conf.setTipoBollettino(object.getTipoBollettino());
			conf.setFunzionePagamentoProtetta(object.getFunzionePagamentoProtetta());
			conf.setUrlServizioWebIntegraEnte(object.getUrlServizioWebIntegraEnte());
			conf.setFlagIntegrazioneSeda(object.getFlagIntegrazioneSeda());
			conf.setCodiceUtenteSeda(object.getCodiceUtenteSeda());
			conf.setModalitaBollettino(object.getModalitaBollettino());
			conf.setCodiceIBAN2(object.getCodiceIBAN2());	//PG160150_002 GG
			conf.setFlagNotificaPagamento(object.getFlagNotificaPagamento()); //PG170150 CT
			conf.setUrlServizioWebNotificaPagamento(object.getUrlServizioWebNotificaPagamento()); //PG170150 CT
			//inizio LP PG180290
			conf.setFlagPagoPA(object.getFlagPagoPA()); //questo assegnamento mancava
			conf.setTipoDovuto(object.getTipoDovuto());
			//fine LP PG180290
			
		}
		return conf;
	}
	*/

	private ConfigPagamentoSingleResponse recuperaFunzioneEnte(ConfigPagamentoSingleRequest in) throws Exception
	{
		return GatewaysIGHelper.recuperaFunzioneEnte(propertiesTree(), dbSchemaCodSocieta, in);
	}
	//fine LP PG200070

	
	//inizio LP PG180290
	private String GetChiaveEnte(RecuperaTransazioneResponseType recuperaTransazioneResp) {
		String chiaveEnte = "";
		BeanIV[] bollIV = recuperaTransazioneResp.getListIV();
		BeanFreccia[] bollFr = recuperaTransazioneResp.getListFreccia();
		BeanIci[] bollIci = recuperaTransazioneResp.getListIci();
		if(bollIV.length > 0) {
			chiaveEnte = bollIV[0].getChiave_ente_con();
		} else if(bollFr.length > 0)  {
			chiaveEnte = bollFr[0].getChiave_ente_con();
		} else if(bollIci.length > 0)  {
				chiaveEnte = bollIci[0].getChiave_ente();
		}
		return chiaveEnte;
	}
	//fine LP PG180290
	
}