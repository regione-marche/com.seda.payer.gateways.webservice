package com.seda.payer.gateways.webservice.listener;

import javax.servlet.ServletContextEvent;
import com.seda.compatibility.SystemVariable;
import com.seda.j2ee5.listener.spi.ApplicationListenerHandler;
import com.seda.payer.gateways.webservice.config.PrintStrings;
/**
 * 
 * @author mmontisano
 *
 */
public class ApplicationListener extends ApplicationListenerHandler {
	/* (non-Javadoc)
	 * @see com.seda.j2ee5.listener.spi.ApplicationListenerHandler#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) { }
	/* (non-Javadoc)
	 * @see com.seda.j2ee5.listener.spi.ApplicationListenerHandler#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		String rootPath = event.getServletContext().getInitParameter(PrintStrings.CONFIG_FILE.format());
		if (rootPath==null) {
			SystemVariable sv = new SystemVariable();
			rootPath=sv.getSystemVariableValue(PrintStrings.ROOT.format());
			sv=null;
		} 
		if (rootPath!=null) {
			configurePropertiesTree(PrintStrings.TREE_CONTEXT_NAME.format(), rootPath);
			// initialize log4j for this application context
//			configureLogger(PrintStrings.LOGGER_CONTEXT_NAME.format(), 
//				propertiesTree().getProperties(PrintStrings.LOGGER_PROPERTIES_NAME.format()));

			info("<com.seda.payer.gateways.webservice - contextInitialized()>");
			info("<com.seda.payer.gateways.webservice - application is started>");
		}
	}
}