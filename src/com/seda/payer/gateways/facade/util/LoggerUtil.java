package com.seda.payer.gateways.facade.util;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.esed.log.req.dati.LogPap;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.payer.gateways.facade.handler.PropertiesPath;
import com.seda.payer.gateways.webservice.config.PropKeys;


public class LoggerUtil {

	
	public void savePapLog(LogPap logPap , PropertiesTree configuration) {
		
		
		String uri = "";
		if ( configuration.getProperty(PropertiesPath.wsLogRequest.format(PropKeys.DEFAULT_NODE.format())) != null 
				&& !configuration.getProperty(PropertiesPath.wsLogRequest.format(PropKeys.DEFAULT_NODE.format())).equals("")){
			 uri = configuration.getProperty(PropertiesPath.wsLogRequest.format(PropKeys.DEFAULT_NODE.format())) + "/savePapLogger";
		}
		
		if(logPap != null) {
			System.out.println("logPap OK uri = " + uri);

			Entity<LogPap> entity =  Entity.entity(logPap, MediaType.APPLICATION_JSON);
			
			WsLogRequestThread wsLogRequestThread = new WsLogRequestThread(uri, entity);
			Thread thread = new Thread(wsLogRequestThread);
			thread.start();		

		}
	
	}


}
