package com.seda.payer.gateways.facade.messages;

import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * @author mmontisano
 */
public enum Messages {
	KEY_MISSING,
	INVALID_PARAMETER,
	ERROR_GENERIC,
	SQL_ERROR_MINUS_530,
	SQL_ERROR_MINUS_811,
	SQL_ERROR_MINUS_803,
	SQL_ERROR_MINUS_911,
	SQL_ERROR_PLUS_100,
	SQL_ERROR_PLUS_466,
	SQL_ERROR_MINUS_518,
	SQL_ERROR_GENERIC,
    ;
    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(Messages.class) {
            if(rb==null)
                rb = ResourceBundle.getBundle(Messages.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}