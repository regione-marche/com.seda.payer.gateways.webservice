package com.seda.payer.gateways.webservice.config;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

public enum StrumentoPagamentoTypeKeys {
	CARTA_DI_CREDITO,
	MAV,
	RID,
	PAGA_IN_CONTO
	;
    private static ResourceBundle rb;
    public static String parse( String value ) {
        synchronized(StrumentoPagamentoTypeKeys.class) {
            if (rb == null)
            	rb = ResourceBundle.getBundle(StrumentoPagamentoTypeKeys.class.getName());

            Enumeration<String> keys = rb.getKeys();
            while (keys.hasMoreElements()) {
            	String nextKey = keys.nextElement();
            	String nextValue = rb.getString(nextKey);
            	if (nextKey.compareTo(value) == 0)
            		return nextValue;
            	else if (nextValue.compareTo(value) == 0)
            		return nextKey;
            }
            return null;
        }
    }
    public String format( Object... args ) {
        synchronized(StrumentoPagamentoTypeKeys.class) {
            if (rb == null)
            	rb = ResourceBundle.getBundle(StrumentoPagamentoTypeKeys.class.getName());

            return MessageFormat.format(rb.getString(name()), args);
        }
    }
}