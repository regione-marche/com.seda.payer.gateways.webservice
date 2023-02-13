package com.seda.payer.gateways.facade.handler;

import java.sql.Connection;
import java.util.Properties;

import org.apache.log4j.Hierarchy;
import org.apache.log4j.Logger;

import com.seda.commons.logger.LoggerHierarchyServer;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.compatibility.SystemVariable;
import com.seda.data.dao.DAOHelper;
import com.seda.emailsender.webservices.srv.EMailSenderFaultType;
import com.seda.j2ee5.jndi.JndiProxy;
import com.seda.j2ee5.jndi.JndiProxyException;
import com.seda.payer.gateways.facade.ws.EMailSender;


public abstract class BaseFacadeHandler {
	
	private String wsEMailSenderUrl = "";
	protected EMailSender emailSender;
	private String wsNotificheUrl = "";
	private String wsCommonsUrl;
	private String wsLogRequest = "";
	
	protected Logger logger;

	protected PropertiesTree propertiesTree;
	//inizio LP PG200070 - 20200812
	protected Properties log4jConfiguration;
	//fine LP PG200070 - 20200812
	
	//inizio LP PG200070 - 20200812
	public BaseFacadeHandler(PropertiesTree propertiesTree) throws Exception	{
		super();
		this.propertiesTree = propertiesTree;
		applicationStartup();
	}
	//fine LP PG200070 - 20200812

	/**
	 * applicationStartup()
	 */
	protected void applicationStartup() throws Exception {
		// get SystemVariable handler
//		SystemVariable sv = new SystemVariable();
//		// load the tree properties for this application 
//		String propertiesRootPath = sv.getSystemVariableValue(PrintStrings.ROOT.format());
		try {
//			this.propertiesTree = new PropertiesTree(propertiesRootPath);
			String catalogName = PropertiesPath.baseCatalogName.format();
		
			/* we set wsEMailSenderUrl */
			this.wsEMailSenderUrl = propertiesTree.getProperty(PropertiesPath.wsEMailSenderUrl.format(catalogName));
			if (this.wsEMailSenderUrl == null || this.wsEMailSenderUrl.length() == 0)
				throw new Exception("wsEMailSenderUrl value for ws.wsEMailSenderUrl not found");
			else			
				emailSender = new EMailSender(this.wsEMailSenderUrl);			
			
			/* we set notifiche */
			this.wsNotificheUrl = propertiesTree.getProperty(PropertiesPath.wsNotificheUrl.format(catalogName));
			if (this.wsNotificheUrl == null || this.wsNotificheUrl.length() == 0)
				throw new Exception("wsNotificheUrl value for ws.wsNotificheUrl not found");
			
			this.wsCommonsUrl = propertiesTree.getProperty(PropertiesPath.wsCommonsUrl.format(catalogName));
			if (this.wsCommonsUrl == null || this.wsCommonsUrl.length() == 0)
				throw new Exception("wsCommonsUrl value not found");
			
			this.wsLogRequest = propertiesTree.getProperty(PropertiesPath.wsLogRequest.format(catalogName));
			if (this.wsLogRequest == null || this.wsCommonsUrl.length() == 0)
				throw new Exception("wsLogRequest value not found");
			
			
			/* we initialize logger */
//            Properties log4jConfiguration = propertiesTree.getProperties(PropertiesPath.baseLogger.format());                         
//            LoggerHierarchyServer loggerHierarchyServer = new LoggerHierarchyServer();
//            Hierarchy hierarchy = loggerHierarchyServer.configure(log4jConfiguration);
            
            logger = Logger.getLogger("FILE");
            logger.info("<com.seda.payer.facade - applicationStartup()>");
		}
		catch(EMailSenderFaultType e)
		{
			System.out.println("Remote exception: " + e.getMessage());
			System.out.println("EMilSenderFaultType: " + e.getMessage1());
		}
		catch (Exception e) { throw e; }
    }
	/**
	 * @return the dataSourceName
	 */
	public String getDataSourceName(String dbSchemaCodSocieta) {
		return propertiesTree.getProperty(PropertiesPath.baseCatalogJndiAlias.format(dbSchemaCodSocieta));
	}
	
	/**
	 * @return the connection
	 * @param dbSchemaCodSocieta Codice societa per connessione dinamica al DB
	 */
	public Connection getConnection(String dbSchemaCodSocieta) throws JndiProxyException {
		return new JndiProxy().getSqlConnection(getInitialContextFactory(dbSchemaCodSocieta), getDataSourceName(dbSchemaCodSocieta), true);
	}
	
	
	/**
	 * @return the connection
	 * @param dbSchemaCodSocieta Codice societa per connessione dinamica al DB
	 * @param autoCommit Autocommit
	 */
	public Connection getConnection(String dbSchemaCodSocieta,boolean autoCommit ) throws JndiProxyException {
		return new JndiProxy().getSqlConnection(getInitialContextFactory(dbSchemaCodSocieta), getDataSourceName(dbSchemaCodSocieta), autoCommit);
	}
	
	
	/**
	 * @param connection
	 */
	public void closeConnection(Connection connection) {
		DAOHelper.closeIgnoringException(connection);
	}

	
	/**
	 * @return the initialContextFactory
	 */
	public String getInitialContextFactory(String dbSchemaCodSocieta) {
		return propertiesTree.getProperty(PropertiesPath.baseCatalogInitialContextFactory.format(dbSchemaCodSocieta));
	}
	
	
	/**
	 * @return the schema
	 */
	public String getSchema(String dbSchemaCodSocieta) {
		return propertiesTree.getProperty(PropertiesPath.baseCatalogSchema.format(dbSchemaCodSocieta));
	}

	
	/**
	 * @return the defaultListRows
	 */
	public int getDefaultListRows(String dbSchemaCodSocieta) {
		return Integer.parseInt(propertiesTree.getProperty(PropertiesPath.defaultListRows.format(dbSchemaCodSocieta)));
	}


}