package com.seda.payer.gateways.webservice.config;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

public enum CanaleAttivazioneTypeKeys {
	WEB, //web
	MOB, //mobile
	ATM, //ATM
	DTT, //digitale terrestre
	CCD, //call center dispositivo
	POS	 //pos fisico
	;
    private static ResourceBundle rb;
    public static String parse( String value ) {
        synchronized(CanaleAttivazioneTypeKeys.class) {
            if (rb == null)
            	rb = ResourceBundle.getBundle(CanaleAttivazioneTypeKeys.class.getName());

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
        synchronized(CanaleAttivazioneTypeKeys.class) {
            if (rb == null)
            	rb = ResourceBundle.getBundle(CanaleAttivazioneTypeKeys.class.getName());

            return MessageFormat.format(rb.getString(name()), args);
        }
    }
}