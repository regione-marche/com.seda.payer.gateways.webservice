/**
 * GatewaysIGSOAPBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.seda.payer.gateways.webservice.ig.source;

import com.seda.payer.gateways.webservice.config.PropKeys;
import com.seda.payer.gateways.webservice.handler.WebServiceHandler;
import com.seda.payer.gateways.webservice.helper.GatewaysIGHelper;
import com.seda.payer.gateways.webservice.helper.GatewaysIGThread;
import com.seda.payer.gateways.webservice.ig.dati.BeanInvioEsitoResponse;
import com.seda.payer.pgec.webservice.commons.dati.TransazioniBeanTransazioni;
import com.seda.payer.pgec.webservice.commons.source.CommonsSOAPBindingStub;

public class GatewaysIG extends WebServiceHandler implements com.seda.payer.gateways.webservice.ig.source.GatewaysIGInterface {

	private static final String EMPTY_URL_MERCHANT = "";
	/* (non-Javadoc)
	 * @see com.seda.payer.gateways.webservice.ig.source.GatewaysIGInterface#invioEsito(com.seda.payer.gateways.webservice.ig.dati.BeanInvioEsitoResponse)
	 */
	public com.seda.payer.gateways.webservice.ig.dati.BeanInvioEsitoResponse invioEsito(com.seda.payer.gateways.webservice.ig.dati.BeanInvioEsitoRequest in) throws java.rmi.RemoteException, com.seda.payer.gateways.webservice.ig.srv.FaultType {
		try {
			//metodo richiamato dal concentratore InfoGroup al termine del pagamento
			info("dataOperazione.IG - " + in.getDataOperazione().getTime());
			info("canaleAttivazione.IG - " + in.getCanaleAttivazione());
			info("strumentoPagamento.IG - " + in.getStrumentoPagamento());
			info("idOperazione.IG - " + in.getIdOperazione());
			info("codiceMerchant.IG - " + in.getCodiceMerchant());
			info("tipoChiamata.IG - " + in.getTipoChiamata());
			info("codiceEsito.IG - " + in.getCodiceEsito());
			info("descrizioneEsito.IG - " + in.getDescrizioneEsito());

			//setto il codice società leggendolo da file properties
			dbSchemaCodSocieta = propertiesTree().getProperty(PropKeys.INFOGROUP_DBSCHEMACODSOCIETA.format());;
			if (dbSchemaCodSocieta == null || dbSchemaCodSocieta.length() == 0)
				throw new Exception("Parametro infogroup.dbSchemaCodSocieta not found in properties file");

			// we check TIPO CHIAMATA
			String tipoChiamata = in.getTipoChiamata();
			if (!(tipoChiamata.equalsIgnoreCase("online") || tipoChiamata.equalsIgnoreCase("batch")))
				throw new Exception("Parametro Tipo Chiamata non valorizzato correttamente");

			if (!(in.getCodiceEsito() == 1 || in.getCodiceEsito() == 0 || in.getCodiceEsito() == 2) )
				throw new Exception("Parametro Codice Esito non valorizzato correttamente");

			// we initialize commons manager
			CommonsSOAPBindingStub binding = GatewaysIGHelper.getCommonsManager(propertiesTree(), dbSchemaCodSocieta);
			// we retrieve beanTransazioni
			TransazioniBeanTransazioni beanTransazioni = null;
			try { beanTransazioni = GatewaysIGHelper.recuperaTransazione(binding, in, loggerServer());
			} catch (Exception e) { throw new Exception("Errore nel recupero della transazione"); }

			if (beanTransazioni.getChiave_transazione() == null)
				throw new Exception("CodiceMerchant " + in.getCodiceMerchant() + " non trovato!");

			// we retrieve urlRedirectQuietanza 
			//inizio LP PG200070
			//String urlRedirectQuietanza = GatewaysIGHelper.getUrlRedirectQuietanza(beanTransazioni.getChiave_gateway_di_pagamento(), env, loggerServer(), dbSchemaCodSocieta);
			//inizio LP PG200070 - 20200812
			//String urlRedirectQuietanza = GatewaysIGHelper.getUrlRedirectQuietanza(beanTransazioni.getChiave_gateway_di_pagamento(), loggerServer(), dbSchemaCodSocieta);
			String urlRedirectQuietanza = GatewaysIGHelper.getUrlRedirectQuietanza(beanTransazioni.getChiave_gateway_di_pagamento(), loggerServer(), dbSchemaCodSocieta, configuration);
			//fine LP PG200070 - 20200812
			//fine LP PG200070
			
			//Nel caso di esito DUBBIO (in.getCodiceEsito()== 2) non devo fare nulla
			if(in.getCodiceEsito()== 0 || in.getCodiceEsito()== 1)
			{
				// NAUTES è il punto in cui bisogna aggiornare la transazione 
				// ma per il MAV viene fatto nell'integraGTWSinc l'aggiornamento del numeroMAV
				// e sull'allineaTransazione vengono aggiornati i valori specifici di pagamento del MAV
				
				// we update transaction
				try { GatewaysIGHelper.aggiornaTransazione(beanTransazioni, in, binding, loggerServer());
				} catch (Exception e) {
					error("aggiornaTransazione failed, generic error due to: ", e);
					throw new Exception("Allineamento, modalità " + in.getTipoChiamata() 
						+ ", CodiceMerchant " + in.getCodiceMerchant() + " fallito"); }
	
				// we check If codiceEsito is 0 then notify transaction
				if (in.getCodiceEsito() == 0) {
					// we notify transaction
					try { new Thread(new GatewaysIGThread(beanTransazioni, propertiesTree(), dbSchemaCodSocieta)).start();
					} catch (Exception e) {
						error("notificaTransazione failed, generic error due to: ", e);
						throw new Exception("Notifica allineamento, modalità " + in.getTipoChiamata() 
							+ ", CodiceMerchant " + in.getCodiceMerchant() + " fallita"); }
				}
			}
			
			/*
			String urlRedirect = tipoChiamata.equalsIgnoreCase("online") 
					? urlRedirectQuietanza + "?TipoGateway=I&csrfToken=" + 
					  beanTransazioni.getChiave_transazione_sistema_esterno() + 
					  "&TransactionID=" + in.getCodiceMerchant() + "&Esito=" + in.getCodiceEsito() 
					: EMPTY_URL_MERCHANT;*/
			
		
			urlRedirectQuietanza = urlRedirectQuietanza.contains("?")
					? urlRedirectQuietanza + "&TransactionID=" + in.getCodiceMerchant() + "&csrfToken=" + 
							  beanTransazioni.getChiave_transazione_sistema_esterno()
					: urlRedirectQuietanza + "?TransactionID=" + in.getCodiceMerchant() + "&csrfToken=" + 
					  beanTransazioni.getChiave_transazione_sistema_esterno();
			
			//Nel getChiave_transazione_sistema_esterno viene inserito l'ultimo CSRF Token generato nel web			
			String urlRedirect = tipoChiamata.equalsIgnoreCase("online") 
								? urlRedirectQuietanza
								: EMPTY_URL_MERCHANT;

			info("Url redirect: " + urlRedirect);

			return new BeanInvioEsitoResponse(in.getTipoChiamata(), in.getCanaleAttivazione(), in.getStrumentoPagamento(),
			           in.getIdOperazione(), in.getCodiceMerchant(), "SUCCESS", 0, urlRedirect);
		} catch (Exception e) {
			error("invioEsito failed, generic error due to: ", e);
			return new BeanInvioEsitoResponse(in.getTipoChiamata(), in.getCanaleAttivazione(), in.getStrumentoPagamento(),
			           in.getIdOperazione(), in.getCodiceMerchant(), e.getMessage(), 1,
			           EMPTY_URL_MERCHANT);
		}
    }
}