package com.seda.payer.gateways.webservice.handler;

import java.util.Iterator;
//inizio LP PG200070
//import java.util.Properties;
//fine LP PG200070
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.rpc.server.ServletEndpointContext;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;


import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.j2ee5.webservice.spi.JaxRpc10WebServiceHandler;
import com.seda.payer.gateways.webservice.config.PrintStrings;

public abstract class WebServiceHandler extends JaxRpc10WebServiceHandler {

	protected String loggerContextName = PrintStrings.LOGGER_CONTEXT_NAME.format();
	protected String treeContextName = PrintStrings.TREE_CONTEXT_NAME.format();
	//inizio LP PG200070
	//protected Properties env;
	//fine LP PG200070
	protected String dbSchemaCodSocieta; 
	protected PropertiesTree configuration;
	protected LoggerWrapper LOG = CustomLoggerManager.get(WebServiceHandler.class);
	
	
	@Override
	public void init(Object endPointContext) throws ServiceException {
		super.init(endPointContext);
		
    	propertiesTree(treeContextName);
    	
    	setDbSchemaCodSocieta(endPointContext);
    	configuration = propertiesTree();
	}
	
	@SuppressWarnings("unchecked")
	private void setDbSchemaCodSocieta (Object endPointContext) {
		ServletEndpointContext ctx=null;
		
		if (javax.xml.rpc.server.ServletEndpointContext.class.isInstance(endPointContext))
			ctx = (ServletEndpointContext) endPointContext;

		if (ctx != null) {
			SOAPMessageContext mc = (SOAPMessageContext)ctx.getMessageContext();
			// process SOAP header as shown in the message handler
			try {
				SOAPHeader header = mc.getMessage().getSOAPPart().getEnvelope().getHeader();

				Iterator headers = header.examineAllHeaderElements(); //header.extractHeaderElements("http://schemas.xmlsoap.org/soap/actor/next");
				while (headers.hasNext()) {
					SOAPHeaderElement he = (SOAPHeaderElement)headers.next();
					if(he.getNodeName().equals("dbSchemaCodSocieta"))
						dbSchemaCodSocieta = new String(he.getValue());
				} 
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
	}
}