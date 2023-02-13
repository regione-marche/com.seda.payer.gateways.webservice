package com.seda.payer.gateways.webservice.log;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Events {
	TRA_ALIGN,
	START_TRA_ALIGN,
	STOP_TRA_ALIGN,
	END_TRA_ALIGN
	;

    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(Events.class) {
            if (rb == null)
                rb = ResourceBundle.getBundle(Events.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}
