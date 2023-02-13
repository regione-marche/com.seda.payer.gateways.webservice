package com.seda.payer.gateways.webservice.helper;

import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.payer.pgec.webservice.commons.dati.TransazioniBeanTransazioni;

public class GatewaysIGThread implements Runnable {
 
	private TransazioniBeanTransazioni beanTransazioni;
	private PropertiesTree env;
	private String dbSchemaCodSocieta;
	
	public GatewaysIGThread (TransazioniBeanTransazioni beanTransazioni, PropertiesTree env, String dbSchemaCodSocieta){
		this.dbSchemaCodSocieta = dbSchemaCodSocieta;
		this.beanTransazioni=beanTransazioni;
		this.env=env;
	}

	public void run() {
		//metodo in thread per notificaTransazione
		if (beanTransazioni!=null && env!=null) {
			try {
				GatewaysIGHelper.notificaTransazione(beanTransazioni, env, "O", dbSchemaCodSocieta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}


}
