package com.seda.payer.gateways.webservice.config;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

public enum GatewaysTypeKeys {
	PAYPAL,
	INFOGROUP,
	PAGOINCONTO,
	MAVONLINE,
	RIDONLINE,
	PAGONLINE,
	MYBANK,
	SATISPAY,
	CARTASI,
	NODOSPC		//PG150180_001 GG
	, MYPAY //PG180290
	;
    private static ResourceBundle rb;
    public static String parse( String value ) {
        synchronized(GatewaysTypeKeys.class) {
            if (rb == null)
            	rb = ResourceBundle.getBundle(GatewaysTypeKeys.class.getName());

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
        synchronized(GatewaysTypeKeys.class) {
            if (rb == null)
            	rb = ResourceBundle.getBundle(GatewaysTypeKeys.class.getName());

            return MessageFormat.format(rb.getString(name()), args);
        }
    }
}