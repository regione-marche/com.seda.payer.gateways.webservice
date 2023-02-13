package com.seda.payer.gateways.webservice.source;

//inizio LP PG200070
//import java.util.Properties;

//import javax.naming.Context;
//fine LP PG200070

import com.seda.payer.gateways.webservice.dati.AllineaAutomaticoTransazioneIGResponse;



public class TestService {
	
	public static void main(String[] args) {
		try {
			Gateways gateways = new Gateways() {
				@Override
				protected void initConfig() {
					//inizio LP PG200070
					//env = new Properties();
					//env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
					//env.put(Context.PROVIDER_URL, "ejbd://localhost:5199");
					//fine LP PG200070
					flowPath = "D:\\FileTemporanei\\Payer\\FlussiInfoGroup\\input\\";
					flowInProgressPath = "D:\\FileTemporanei\\Payer\\FlussiInfoGroup\\progress\\";
					flowProcessedPath = "D:\\FileTemporanei\\Payer\\FlussiInfoGroup\\output\\";
					flowRejectedPath = "D:\\FileTemporanei\\Payer\\FlussiInfoGroup\\rejected\\";
//					updateTraPendingDayInterval = "1";
//					emailNotifyToLisy = "marco.montisano@gmail.com";
//					emailNotifyCCList = "marco.montisano@gmail.com";
//					emailNotifyCCNList = "marco.montisano@gmail.com";
				}
			};
			AllineaAutomaticoTransazioneIGResponse response = gateways.allineaAutomaticoTransazioneIG();
			
			System.out.println("[" + response.getResponse().getRetCode() + "] - " + 
					response.getResponse().getRetMessage());	

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}