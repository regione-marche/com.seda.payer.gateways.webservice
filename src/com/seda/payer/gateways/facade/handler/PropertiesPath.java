package com.seda.payer.gateways.facade.handler;

import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * @author mmontisano
 *
 */
public enum PropertiesPath {
	baseCatalogName,
	baseCatalogInitialContextFactory,
	baseCatalogJndiAlias, 
	baseCatalogSchema, 
	defaultListRows,
	baseLogger,
	
	emailAdmin,
	emailSubject,
	emailSubjectAEA,
	
	rid_giorni_accettazione_revoca_auto,
	rid_sabati_festivi,
	rid_domeniche_festive,
	rid_elenco_giorni_festivi,
	
	FLOW_RID_PATH,
	FLOW_RID_IN_PROGRESS_PATH,
	FLOW_RID_PROCESSED_PATH,
	FLOW_RID_REJECTED_PATH,
	
	FLOW_CBIINSOLUTI_PATH,
	FLOW_CBIINSOLUTI_IN_PROGRESS_PATH,
	FLOW_CBIINSOLUTI_PROCESSED_PATH,
	FLOW_CBIINSOLUTI_REJECTED_PATH,
			
	wsEMailSenderUrl,	
	wsNotificheUrl,
	wsCommonsUrl,
	wsLogRequest,
	pathRnincaext,
	emailRiepilogo,
	codAz
	;

    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(PropertiesPath.class) {
            if(rb==null)
                rb = ResourceBundle.getBundle(PropertiesPath.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}
