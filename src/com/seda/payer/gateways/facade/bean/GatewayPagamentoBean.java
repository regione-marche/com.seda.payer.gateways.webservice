package com.seda.payer.gateways.facade.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.emailsender.webservices.dati.EMailSenderRequestType;
import com.seda.emailsender.webservices.dati.EMailSenderResponse;
import com.seda.emailsender.webservices.source.EMailSenderInterface;
import com.seda.emailsender.webservices.source.EMailSenderServiceLocator;
import com.seda.payer.core.bean.FlussiRendInca;
import com.seda.payer.core.bean.GatewayPagamento;
import com.seda.payer.core.bean.RendPos;
import com.seda.payer.core.bean.Rid;
import com.seda.payer.core.bean.Transazione;
import com.seda.payer.core.dao.FlussiRendIncaDao;
import com.seda.payer.core.dao.GatewayPagamentoDao;
import com.seda.payer.core.dao.PagDaRendDao;
import com.seda.payer.core.dao.RidDao;
import com.seda.payer.core.dao.TxTransazioniDao;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.gateways.facade.dto.CollectionDto;
import com.seda.payer.gateways.facade.dto.GatewayPagamentoDto;
import com.seda.payer.gateways.facade.dto.RidDto;
import com.seda.payer.gateways.facade.exception.FacadeException;
import com.seda.payer.gateways.facade.handler.BaseFacadeHandler;
import com.seda.payer.gateways.facade.handler.PropertiesPath;
import com.seda.payer.gateways.facade.util.Generics_RID;
import com.seda.payer.gateways.facade.util.Generics_RID_AllineaAEA;
import com.seda.payer.gateways.facade.util.Generics_RID_AllineaCBI;
import com.seda.payer.gateways.facade.util.Generics_RID_Mail;
/**
 * Session Bean implementation class GatewayPagamentoFacadeBean
 *
 * @ejb.bean name="GatewayPagamentoFacade" type="Stateless" view-type="remote" 
 * 			 transaction-type="Container" jndi-name="ejb/GatewayFacade" 
 * 
 * @ejb.resource-ref res-ref-name="payerDataSource" res-type="javax.sql.DataSource"
 * 					 res-auth="Container" res-sharing-scope="Shareable"
 * 
 * @ejb.util generate="physical"
 * @ejb.transaction type="NotSupported"
 */
public class GatewayPagamentoBean extends BaseFacadeHandler {
	
	//inizio LP PG200070 - 20200812
	//public GatewayPagamentoBean() throws Exception {
	//	super();
	//	applicationStartup();
	//}
	public GatewayPagamentoBean(PropertiesTree propertiesTree) throws Exception {
		super(propertiesTree);
	}
	//fine LP PG200070 - 20200812
	
	private static final long serialVersionUID = 1L;
	private String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * @ejb.interface-method
	 * @ejb.transaction type="NotSupported"
	 */
    public GatewayPagamentoDto getGatewayPagamento(String chiaveGateway, String dbSchemaCodSocieta) throws FacadeException {
    	Connection conn=null;
    	try {
    		conn=getConnection(dbSchemaCodSocieta);
    		return new GatewayPagamentoDto(new GatewayPagamentoDao(conn, getSchema(dbSchemaCodSocieta)).doDetail(chiaveGateway));
		} catch (Exception ex) {
			logger.error("getUser failed, generic error due to: ", ex);
			throw new FacadeException(ex);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
		}
    }
    /**
	 * @ejb.interface-method
	 * @ejb.transaction type="NotSupported"
	 */
    public CollectionDto getGatewayPagamentoXml(GatewayPagamento gatewayPagamento,String strDescrUser,String strDescrCanalePagamento,String strDescrCartaPagamento,String strDescrSocieta, String dbSchemaCodSocieta) throws FacadeException {
    	Connection conn=null;
    	try {
    		conn=getConnection(dbSchemaCodSocieta);
    		GatewayPagamentoDao dao = new GatewayPagamentoDao(conn, getSchema(dbSchemaCodSocieta));
    		dao.doRowSets(gatewayPagamento,strDescrUser,strDescrCanalePagamento,strDescrCartaPagamento,strDescrSocieta);
    		return new CollectionDto(dao.getWebRowSetXml(GatewayPagamentoDao.IDX_DOLIST_LISTA), dao.getPageInfo());
		} catch (Exception ex) {
			logger.error("getGatewayPagamentoXml failed, generic error due to: ", ex);
			throw new FacadeException(ex);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
		}
    }
	/**
	 * @ejb.interface-method
	 * @ejb.transaction type="NotSupported"
	 */
    public CollectionDto getGatewayPagamentoXml(GatewayPagamento gatewayPagamento, int rowsPerPage, int pageNumber,String strDescrUser,String strDescrCanalePagamento,String strDescrCartaPagamento,String strDescrSocieta, String dbSchemaCodSocieta) throws FacadeException {
    	Connection conn=null;
    	try {
    		conn=getConnection(dbSchemaCodSocieta);
    		GatewayPagamentoDao dao = new GatewayPagamentoDao(conn, getSchema(dbSchemaCodSocieta));
    		dao.doRowSets(gatewayPagamento, rowsPerPage <= 0 ? this.getDefaultListRows(dbSchemaCodSocieta) : rowsPerPage, pageNumber,strDescrUser,strDescrCanalePagamento,strDescrCartaPagamento,strDescrSocieta);
    		return new CollectionDto(dao.getWebRowSetXml(GatewayPagamentoDao.IDX_DOLIST_LISTA), dao.getPageInfo());
		} catch (Exception ex) {
			logger.error("getGatewayPagamentoXml failed, generic error due to: ", ex);
			throw new FacadeException(ex);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
		}
    }
	/**
	 * @ejb.interface-method
	 * @ejb.transaction type="NotSupported"
	 */
    public void save(GatewayPagamento gatewayPagamento,String codOp, String dbSchemaCodSocieta) throws FacadeException {
    	Connection conn=null;
    	try {
    		conn=getConnection(dbSchemaCodSocieta);
    		new GatewayPagamentoDao(conn, getSchema(dbSchemaCodSocieta)).doSave(gatewayPagamento,codOp);
		} catch (Exception ex) {
			logger.error("save failed, generic error due to: ", ex);
			throw new FacadeException(ex);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
		}
		
    }
	/**
	 * @ejb.interface-method
	 * @ejb.transaction type="NotSupported"
	 */
    public void cancel(GatewayPagamento gatewayPagamento, String dbSchemaCodSocieta) throws FacadeException {
    	Connection conn =null;
    	try {
    		conn=getConnection(dbSchemaCodSocieta);
    		new GatewayPagamentoDao(conn, getSchema(dbSchemaCodSocieta)).doDelete(gatewayPagamento);
		} catch (DaoException ex) { throw new FacadeException(ex.getErrorCode(),ex.getErrorMessage());
		} catch (Exception ex) {
	    	logger.error("cancel failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } finally
	    {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
	    }
    }
    
    /************    RID     *******************/

    public void saveRID(RidDto rid, String dbSchemaCodSocieta) throws FacadeException
	{
    	Connection conn =null;
    	try {
    		writeInfoLog("GatewayPagamentoFacadeBean saveRID execute");
    		if (rid!=null)
    		{
			    Rid ridCoreSave = rid.getRidCore();
	    		conn=getConnection(dbSchemaCodSocieta);
	    		
	    		// salvataggio
	    		new RidDao(conn, getSchema(dbSchemaCodSocieta)).doSave(ridCoreSave);
	    		
	    		//NB: la seguente chiamata è stata spostata nel webservice 
	    		// dei dati sono imposti nel DB (date di salvataggio)
	    		// quindi devo rifare il get dell'elemento
	    		//rid = getRID(ridCoreSave.getCodiceDebitore());	    		
	    		
	    	}
    		else
    			logger.error("GatewayPagamentoFacadeBean saveRID not saved");
    		
    		writeInfoLog("GatewayPagamentoFacadeBean saveRID finished");
    		
		} catch (DaoException ex) {
			throw new FacadeException(ex.getErrorCode(),ex.getErrorMessage());
		} catch (Exception ex) {
	    	logger.error("delRID failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } finally
	    {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
	    }
	}
    public RidDto getRID(String codiceDebitore, String dbSchemaCodSocieta) throws FacadeException
	{
    	RidDto resp = null;
    	Connection conn =null;
    	try {
    		writeInfoLog("GatewayPagamentoFacadeBean getRID execute");
		    conn=getConnection(dbSchemaCodSocieta);
    		
    		Rid ridCore = (new RidDao(conn, getSchema(dbSchemaCodSocieta))).doDetail(codiceDebitore);
    		if (ridCore != null)
    			resp = new RidDto(ridCore);
    		
    		writeInfoLog("GatewayPagamentoFacadeBean getRID finished");
		    
		} catch (DaoException ex) {
			throw new FacadeException(ex.getErrorCode(),ex.getErrorMessage());
		} catch (Exception ex) {
	    	logger.error("delRID failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } finally
	    {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
	    }
	    
    	return resp;
	}
    public void delRID(String codiceDebitore, String dbSchemaCodSocieta) throws FacadeException
	{
    	Connection conn =null;
    	try {
    		writeInfoLog("GatewayPagamentoFacadeBean delRID execute");
		    conn=getConnection(dbSchemaCodSocieta);
    		
    		new RidDao(conn, getSchema(dbSchemaCodSocieta)).doDelete(codiceDebitore);
		    
    		writeInfoLog("GatewayPagamentoFacadeBean delRID finished");
		    
		} catch (DaoException ex) {
			throw new FacadeException(ex.getErrorCode(),ex.getErrorMessage());
		} catch (Exception ex) {
	    	logger.error("delRID failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } finally
	    {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
	    }
    }

    public String[] allineaRID(String dbSchemaCodSocieta) throws FacadeException
	{
    	String[] res = new String[2];
    	String[] resAEA = new String[2];
    	String[] resCBI = new String[2];
    	Connection conn =null;
    	try {
    		writeInfoLog("GatewayPagamentoFacadeBean allineaRID execute");
		    conn = getConnection(dbSchemaCodSocieta);
    		
		    //flussi AEA per gestione adesioni/revoche RID
		    StringBuffer testoMail = new StringBuffer();
		    StringBuffer oggettoMail = new StringBuffer();
		    resAEA = Generics_RID_AllineaAEA.manageAllineaRID_AEAFromFile(this, this.propertiesTree, logger, testoMail, oggettoMail, dbSchemaCodSocieta); 
		    sendMailRiepilogoFlussi(oggettoMail, testoMail,dbSchemaCodSocieta);
		    
		    //verifico se ci sono richieste di revoca scadute da X giorni, da accettare automaticamente
		    int iNumRevoche = Generics_RID_AllineaAEA.acceptRevocaAuto(this, this.propertiesTree, logger, dbSchemaCodSocieta);
		    String sMsgRevocheAuto = "";
		    if (iNumRevoche > 0)
		    	sMsgRevocheAuto = " - " + iNumRevoche + " richieste di revoca scadute, accettate automaticamente";
		    
		    //flussi CBI per gestione insoluti RID 
		    testoMail = new StringBuffer();
		    oggettoMail = new StringBuffer();
		    resCBI = Generics_RID_AllineaCBI.manageAllineaCBI_FromFile(this, this.propertiesTree, logger, testoMail, oggettoMail, dbSchemaCodSocieta);
		    sendMailRiepilogoFlussi(oggettoMail, testoMail, dbSchemaCodSocieta);
		    
		    //combino i 2 esiti
		    if (resAEA[0].equals("00") && resCBI.equals("00"))
		    	res[0] = "00";
		    else
		    	res[0] = "04";
		    
		    res[1] = "AEA Adesione/Revoca (" + resAEA[0] + "): " + resAEA[1] + sMsgRevocheAuto + " - CBI Insoluti (" + resCBI[0] + "): " + resCBI[1]; 
		    	
		    writeInfoLog("GatewayPagamentoFacadeBean allineaRID finished");
		    return res;
		    
		} catch (Exception ex) {
	    	logger.error("GatewayPagamentoFacadeBean allineaRID failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } finally
	    {
			//inizio LP PG21XX04 Leak
			//closeConnection(conn);
	    	try {
	    		if(conn != null) {
	    			conn.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
	    }
    }
    
    private void sendMailRiepilogoFlussi(final StringBuffer oggettoMail, final StringBuffer testoMail, final String cutecute)
    {
    	Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					String emailNotificaAdmin = propertiesTree.getProperty(PropertiesPath.emailAdmin.format());
					EMailSenderInterface mailSender = getEmailSenderService(propertiesTree);
					
					EMailSenderRequestType req = new EMailSenderRequestType(
							emailNotificaAdmin,
							"","","", 
							oggettoMail.toString(), 
							testoMail.toString(),
							"", cutecute);
					
					EMailSenderResponse response = mailSender.getEMailSender(req);				
					
					logger.info("sendMailRiepilogoFlussi - emailSender response - " + response.getValue());
				} catch (Exception e) {
					logger.warn("sendMailRiepilogoFlussi failed, generic error due to: ", e);
				}
			}
		};
		thread.start();
    }
    
    public static EMailSenderInterface getEmailSenderService(PropertiesTree propertiesTree)
	{
		EMailSenderInterface emailsenderService = null;
		EMailSenderServiceLocator emailsenderServiceLocator = new EMailSenderServiceLocator();
		try {
			emailsenderService = emailsenderServiceLocator.getEMailSenderPort(
					new URL(propertiesTree.getProperty(PropertiesPath.wsEMailSenderUrl.format(PropertiesPath.baseCatalogName.format()))));
			return emailsenderService;
		} catch (Exception e) {
			return null;
		}
	}
    public void sendRIDMailAdesioneRevoca(RidDto rid, String dbSchemaCodSocieta) throws FacadeException
	{
    	try {
    		writeInfoLog("GatewayPagamentoFacadeBean sendRIDMailAdesioneRevoca execute");
		  
    		// sto per inviare la mail di notifica per richiesta adesione
	    	if (Generics_RID.isFunzioneAdesione(rid))
	    	{
			    // se adesione 
	    		if (Generics_RID.isRIDRichiestaAccettata(rid))
	    		{
	    			// richiesta avvenuta
	    			Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.ADESIONE, Generics_RID_Mail.INVIO.OK, Generics_RID_Mail.TIPO.NONE,
	    					rid, this.propertiesTree, logger, dbSchemaCodSocieta);								
	    		}
	    		else
	    		{
	    			// richiesta fallita
	    			Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.ADESIONE, Generics_RID_Mail.INVIO.KO, Generics_RID_Mail.TIPO.NONE,
	    					rid, this.propertiesTree, logger, dbSchemaCodSocieta);								
			
	    		}				   
	    	}
	    	else
	    	{
			    // se revoca 
	    		if (Generics_RID.isRIDRichiestaAccettata(rid))
	    		{
	    			// richiesta avvenuta
	    			Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.REVOCA, Generics_RID_Mail.INVIO.OK, Generics_RID_Mail.TIPO.NONE,
	    					rid, this.propertiesTree, logger, dbSchemaCodSocieta);								
			
	    		}
	    		else
	    		{
	    			// richiesta fallita
	    			Generics_RID_Mail.notificaRID(Generics_RID_Mail.FUNZIONE.REVOCA, Generics_RID_Mail.INVIO.KO, Generics_RID_Mail.TIPO.NONE,
	    					rid, this.propertiesTree, logger, dbSchemaCodSocieta);								
	    		}				   
		    }
			
	    	writeInfoLog("GatewayPagamentoFacadeBean sendRIDMailAdesioneRevoca finished");
		    
		} catch (Exception ex) {
	    	logger.error("sendRIDMailAdesioneRevoca failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    } 
    }    

    public void sendRIDMailPagamento(String sCOdiceDebitore, String sObjectMail, String sTextMail, String dbSchemaCodSocieta) throws FacadeException
	{
    	try {
    		writeInfoLog("sendRIDMailPagamento allineaRID execute");
			
    		RidDto ridFacade = this.getRID(sCOdiceDebitore, dbSchemaCodSocieta);
    		if (ridFacade !=null)
    		{		    
			    // se adesione 
	    		if (Generics_RID_Mail.generaAndSendMail(this.emailSender, ridFacade, sObjectMail, sTextMail, logger))
	    			writeInfoLog("sendRIDMailPagamento allineaRID finished");
	    		else		    			
	    			logger.error("sendRIDMailPagamento allineaRID finished : not send mail");
			    
    		}
    		else		
    			logger.error("sendRIDMailPagamento allineaRID finished : not send mail - codice debitore not valid:"+sCOdiceDebitore);
		    
		} catch (Exception ex) {
	    	logger.error("delRID failed, generic error due to: ", ex);
		    throw new FacadeException(ex);
	    }
    }
    

	public boolean generaFlussiRendicontazionePos(String dbSchemaCodSocieta) throws FacadeException {
		String pathDest = "";
		boolean eccezione = false;
		boolean fileNonElaborati = false;
		int numFileNonElaborati = 0;
		List<String> listCodAzNonElabValErr = new ArrayList<String>();
		List<String> listCodAzNonElabNonVal = new ArrayList<String>();
		String cutecute = "";
		List<String> listCutecuteSenzaSia = new ArrayList<String>();
		int fileCancellati = 0;
		Connection connection = null;
		File file = null;
		FileOutputStream outFile = null;
		StringBuffer testoMailPos = new StringBuffer();
	    StringBuffer oggettoMail = new StringBuffer();
		int flussiProdotti = 0;
		int transazioniElaborate = 0;
		int transazioniNonElaborate = 0;
		
		try
		{
			//istanzio una connessione con autocommit = false
    		//in modo da eseguire tutte le operazioni seguenti in un'unica transazione
    		//e in caso di fallimento di una, faccio rollback di tutto
			connection = getConnection(dbSchemaCodSocieta, false);
			PagDaRendDao pagDaRendDao = new PagDaRendDao(connection, getSchema(dbSchemaCodSocieta));
			List<RendPos> listaPagDaRendDao = pagDaRendDao.ListaRendPos();
			
			testoMailPos.append("Inizio elaborazione flussi RNINCAEXT<br>");
		    
			int progressivoInvio = 0;
			int num = -1;
			String recordTesta = "";
			String recordDettaglio = "";
			String recordCoda = "";
			String codiceSocieta = "";
			String codiceSocietaDaProperties = "";
			int numRecDettaglio = 2;
			int numRecPerSocieta = 0;
			BigDecimal importoTotale = new BigDecimal(0.00);
			BigDecimal commissioniTotali = new BigDecimal(0.00);
			String dataCreazione = "";
			String dataTransazione = "";
			String nomeFileRend = "";
			String codAzPrec = "";
			List<String> listChiaviTrans = new ArrayList<String>();
			
			for(RendPos rendPos : listaPagDaRendDao)
			{
				codiceSocietaDaProperties = propertiesTree.getProperty(PropertiesPath.codAz.format(rendPos.getCodiceSocietaMittente())); 
				
				try
				{
					num = Integer.parseInt(codiceSocietaDaProperties);
				}catch (NumberFormatException e)
				{
					num = -1;
				}
				
				if(((codiceSocietaDaProperties != null) && (codiceSocietaDaProperties.trim() != "") && (num != -1) && (codiceSocietaDaProperties.length() == 5)) &&
				   (!rendPos.getCodiceSocietaMittente().equals(null)) && (!rendPos.getCodiceSocietaMittente().equals(""))){
					
					//costruisco il RECORD DI TESTA
					if ((codiceSocieta.equals("")) || (!rendPos.getCodiceSocietaMittente().equals(codiceSocieta))){
						DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
				 		Date date = new Date();
				 		dataCreazione = formatter.format(date);
	
						FlussiRendIncaDao flussiRendIncaDao = new FlussiRendIncaDao(connection, getSchema(dbSchemaCodSocieta));
						progressivoInvio = flussiRendIncaDao.doDetail(rendPos.getCodiceSocietaMittente());
				 		
				 		//creo il file
				 		nomeFileRend = "RNINCAEXT" + dataCreazione + codiceSocietaDaProperties + formatNumber(Integer.toString(progressivoInvio+1), 7) + ".txt";
						pathDest = getRnincaextPath() + nomeFileRend;  
						file = new File(pathDest);
						if(!file.exists())
							file.createNewFile();
						else
						{
							file.delete();
							file.createNewFile();
						}
						outFile = new FileOutputStream(file);
						
						//trovo il numero di record dello stesso codice societa'
						codiceSocieta = rendPos.getCodiceSocietaMittente();
						for(RendPos rendPosTemp : listaPagDaRendDao)
						{
							if(codiceSocieta.equals(rendPosTemp.getCodiceSocietaMittente())){
								numRecPerSocieta++;
							}
						}
						
						StringBuffer sBufferTesta = new StringBuffer();
						sBufferTesta.append(String.format("%2s", "HR"));								//TIPO RECORD
						sBufferTesta.append(String.format("%6s", dataCreazione));						//DATA CREAZIONE FLUSSO
						sBufferTesta.append(String.format("%5s", codiceSocietaDaProperties)); 	//CODICE SOCIETA'
						sBufferTesta.append(String.format("%7s", formatNumber(Integer.toString(progressivoInvio+1), 7)));	//PROGRESSIVO INVIO
						sBufferTesta.append(lineSeparator);
						
						recordTesta = sBufferTesta.toString();
						outFile.write(recordTesta.getBytes());
						testoMailPos.append("Inizio creazione dei file nella cartella: " + getRnincaextPath() + "<br>");
					    testoMailPos.append("Flusso con nome supporto: " + recordTesta.substring(2) + "<br>");
					    testoMailPos.append("Inizio scrittura transazioni<br>"); 
					}
					
					
	
					//costruisco i RECORDS DETTAGLIO
					if(codiceSocieta.equals(rendPos.getCodiceSocietaMittente())){
						numRecDettaglio++;	//numero record compresi testa e coda
						importoTotale = importoTotale.add(rendPos.getImporto());
						commissioniTotali = commissioniTotali.add(rendPos.getCommissioni());
					}
					
					DateFormat formatter = new SimpleDateFormat("ddMMyyHHmmss");
			 		dataTransazione = formatter.format(rendPos.getDataTransazione().getTime());
					
					StringBuffer sBufferDettaglio = new StringBuffer();
					sBufferDettaglio.append(String.format("%6s", formatNumber(Integer.toString(numRecDettaglio-2), 6)));	//NUMERO PROGRESSIVO
					sBufferDettaglio.append(String.format("%12s", dataTransazione));										//DATA TRANSAZIONE
					sBufferDettaglio.append(String.format("%-36s", rendPos.getCodicePayer()));		 						//CODICE PAYER
					sBufferDettaglio.append(String.format("%-25s", rendPos.getIdOperazione()));								//ID OPERAZIONE
					sBufferDettaglio.append(String.format("%10s", formatNumber(rendPos.getImporto().toString().replace(".", ""), 10)));		//IMPORTO
					sBufferDettaglio.append(String.format("%10s", formatNumber(rendPos.getCommissioni().toString().replace(".", ""), 10)));	//COMMISSIONI
					String canPag = "";
					Integer rendPosInt = (Integer) rendPos.getCodiceCanalePagamento();
					if(rendPosInt.toString().length() == 1)
					{
						canPag = "0" + rendPosInt.toString();
					}else{
						canPag = rendPosInt.toString();
					}
					sBufferDettaglio.append(String.format("%2s", canPag));													//CODICE CANALE PAGAMENTO
					String codStru = "";
					Integer codStruInt = (Integer) rendPos.getCodiceStrumento();
					if(codStruInt.toString().length() == 1)
					{
						codStru = "0" + codStruInt.toString();
					}else{
						codStru = codStruInt.toString();
					}
					sBufferDettaglio.append(String.format("%2s", codStru));													//CODICE STRUMENTO
					sBufferDettaglio.append(String.format("%2s", "00"));													//CODICE SERVIZIO
					sBufferDettaglio.append(lineSeparator);
	
					recordDettaglio = sBufferDettaglio.toString();
					outFile.write(recordDettaglio.getBytes());
					transazioniElaborate++;
					testoMailPos.append("La transazione: " + rendPos.getCodicePayer() + " e' stata associata<br>");
	
					//mi salvo le chiavi transazioni per identificare tutti i record della TRA che ho inserito nel flusso
					listChiaviTrans.add(rendPos.getCodicePayer());

					//costruisco il RECORD DI CODA
					if ((numRecDettaglio-2) == numRecPerSocieta){
						StringBuffer sBufferCoda = new StringBuffer();
						sBufferCoda.append(String.format("%2s", "EF"));												//TIPO RECORD
						sBufferCoda.append(String.format("%6s", dataCreazione));									//DATA CREAZIONE
						sBufferCoda.append(String.format("%5s", codiceSocietaDaProperties));						//CODICE SOCIETA'
						sBufferCoda.append(String.format("%6s", formatNumber(Integer.toString(numRecDettaglio), 6)));//NUMERO RECORD
						sBufferCoda.append(String.format("%10s", formatNumber(importoTotale.toString().replace(".", ""), 10)));		//TOTALE IMPORTO
						sBufferCoda.append(String.format("%10s", formatNumber(commissioniTotali.toString().replace(".", ""), 10)));	//TOTALE COMMISSIONI
						
						numRecPerSocieta = 0;
						numRecDettaglio = 2;
						importoTotale = new BigDecimal(0.00);
						commissioniTotali = new BigDecimal(0.00);
						
						
						recordCoda = sBufferCoda.toString();
						outFile.write(recordCoda.getBytes());
						flussiProdotti++;
						outFile.close();
						//inizio LP PG21XX04 Leak
						outFile = null;
						//fine LP PG21XX04 Leak
						testoMailPos.append("Il Flusso è stato prodotto<br><br>");
						
						
						//inserisco un nuovo record testata POF
						FlussiRendInca flussiRendInca = new FlussiRendInca();
						flussiRendInca.setDataCreazioneFlusso(new java.sql.Date(Calendar.getInstance().getTime().getTime()));	
						flussiRendInca.setCodiceSocietaMittente(codiceSocieta);
						flussiRendInca.setProgressivoInvio(new BigDecimal(progressivoInvio+1));	
						flussiRendInca.setNomeFileRend(nomeFileRend);
						flussiRendInca.setOperatoreAggiornamento("batchRnincaext");
						FlussiRendIncaDao flussiRendIncaDao = new FlussiRendIncaDao(connection, getSchema(dbSchemaCodSocieta));
						String chiavePof = flussiRendIncaDao.doSave(flussiRendInca);
						
						//aggiorno la TRA 
						for(String chiaveTrans : listChiaviTrans){	
							Transazione trans = new Transazione();
							trans.setChiaveTransazione(chiaveTrans);
							trans.setChiaveFlussoRendicontazioneRnincaext(chiavePof);										//inserisco CHIAVE FLUSSO DI RENDICONTAZIONE RNINCAEXT
							trans.setOpertoreUltimoAggiornamento("batchRnincaext");											//aggiorno OPERATORE INSERIMENTO
							trans.setDataUltimoAggiornamento(new java.sql.Date(Calendar.getInstance().getTime().getTime()));//aggiorno DATA ULTIMO AGGIORNAMENTO	
							TxTransazioniDao txTransazioniDao = new TxTransazioniDao(connection, getSchema(dbSchemaCodSocieta));
							txTransazioniDao.aggiornaTransazione(trans);
						}
						listChiaviTrans.clear();
						//se tutti gli inserimenti riguardanti il flusso sono andati a buon fine, eseguo la commit
			    		connection.commit();
					}
				}else{
					fileNonElaborati = true;
					transazioniNonElaborate++;
					if(!rendPos.getCodiceSocietaMittente().equals(codAzPrec)){
						codAzPrec = rendPos.getCodiceSocietaMittente();
						numFileNonElaborati++;
						
						//aggiungo il cod az che non ho elaborato
						if ((codiceSocietaDaProperties == null) || (codiceSocietaDaProperties.trim() == "")){
							listCodAzNonElabNonVal.add(rendPos.getCodiceSocietaMittente());							
						}else if((num == -1) || (codiceSocietaDaProperties.length() != 5)){
							listCodAzNonElabValErr.add(rendPos.getCodiceSocietaMittente());	
						}
					}
					if ((rendPos.getCodiceSocietaMittente().equals(null)) || (rendPos.getCodiceSocietaMittente().equals(""))){
						//TODO
						//qui devo prendere il cutecute e scrivere nella mail e nel log: errore, manca il codiceSIA per il cutecute xxxxx
						cutecute = rendPos.getCutecute();
						if(!listCutecuteSenzaSia.contains(cutecute)){
							listCutecuteSenzaSia.add(cutecute);	
							numFileNonElaborati++;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException e1) {
				
				//cancella file se si verifica un'eccezione
				if(file.exists()){
					try {
						outFile.close();
						//inizio LP PG21XX04 Leak
						outFile = null;
						//fine LP PG21XX04 Leak
						file.delete();
						fileCancellati++;
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				
				logger.error("inizializzaTransazione failed - rollback failed: ", e1);
				throw new FacadeException(e);
			}
			logger.info("inizializzaTransazione failed - rollback eseguito");
			logger.error("inizializzaTransazione failed, generic error due to: ", e);
			testoMailPos.append("Riscontrato errore, verificare il log. Cancellato il flusso: " + file.getName() + "<br>");
			eccezione = true;
			
			//cancella file se si verifica un'eccezione
			if(file.exists()){
				try {
					outFile.close();
					//inizio LP PG21XX04 Leak
					outFile = null;
					//fine LP PG21XX04 Leak
					file.delete();
					fileCancellati++;
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			throw new FacadeException(e);
		}
		finally {
			testoMailPos.append("Riepilogo<br>");
			if((!eccezione) && (!fileNonElaborati)){
				oggettoMail.append("Elaborazione Flussi RNINCAEXT");
				testoMailPos.append("Flussi prodotti: " + flussiProdotti + "<br>");
				testoMailPos.append("Transazioni elaborate: " + transazioniElaborate + "<br>");
			}else{
				oggettoMail.append("Elaborazione Flussi RNINCAEXT - presenza anomalie");
				if(!fileNonElaborati){
					testoMailPos.append("Transazioni elaborate: " + transazioniElaborate + "<br>");
					testoMailPos.append("Transazioni non elaborate: " + transazioniNonElaborate + "<br>");
					testoMailPos.append("Flussi cancellati: " + fileCancellati + "<br>");
					//gestione log
					logger.error("Flussi cancellati: " + fileCancellati);
					eccezione = false;	
				}else{
					testoMailPos.append("Flussi prodotti: " + flussiProdotti + "<br>");
					testoMailPos.append("Transazioni elaborate: " + transazioniElaborate + "<br>");
					testoMailPos.append("Transazioni non elaborate: " + transazioniNonElaborate + "<br>");
					testoMailPos.append("flussi non elaborati: " + numFileNonElaborati + "<br>");
					
					for(String s : listCodAzNonElabNonVal){
						testoMailPos.append("Riscontrato errore. Flusso con codiceAzienda: <b>" + s + "</b> non elaborato per mancata valorizzazione nel file di properties gatewaysEjbRoot<br>");	
						//gestione log
						logger.error("Flusso con codiceAzienda: " + s + " non elaborato per mancata valorizzazione nel file di properties gatewaysEjbRoot");
					}
					
					for(String s : listCodAzNonElabValErr){
						testoMailPos.append("Riscontrato errore. Flusso con codiceAzienda: <b>" + s + "</b> non elaborato per errata valorizzazione nel file di properties gatewaysEjbRoot<br>");
						//gestione log
						logger.error("Flusso con codiceAzienda: " + s + " non elaborato per errata valorizzazione nel file di properties gatewaysEjbRoot");
					}
					
					for(String s : listCutecuteSenzaSia){
						testoMailPos.append("Riscontrato errore. Il seguente cutecute: <b>" + s + "</b> ha il codiceSIA non valorizzato<br>");
						//gestione log
						logger.error("Il seguente cutecute: " + s + " ha il codiceSIA non valorizzato");
					}
				}
			}
			
			sendMailRiepilogoFlussiRnincaext(oggettoMail, testoMailPos,dbSchemaCodSocieta);
			//inizio LP PG21XX04 Leak
			//closeConnection(connection);
	    	try {
	    		if(connection != null) {
	    			connection.close();
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
			}
			if(outFile != null) {
				try {
					outFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
    	}
		return true;
	}
	
	private void sendMailRiepilogoFlussiRnincaext(final StringBuffer oggettoMail, final StringBuffer testoMail, final String cutecute)
    {
    	Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					String emailRiepilogo = propertiesTree.getProperty(PropertiesPath.emailRiepilogo.format());
					EMailSenderInterface mailSender = getEmailSenderService(propertiesTree);
					
					EMailSenderRequestType req = new EMailSenderRequestType(
							emailRiepilogo,
							"","","", 
							oggettoMail.toString(), 
							testoMail.toString(),
							"",cutecute);
					
					EMailSenderResponse response = mailSender.getEMailSender(req);				
					
					logger.info("sendMailRiepilogoFlussiRnincaext - emailSender response - " + response.getValue());
				} catch (Exception e) {
					logger.warn("sendMailRiepilogoFlussiRnincaext failed, generic error due to: ", e);
				}
			}
		};
		thread.start();
    }
	
	private String formatNumber(String number, int iLenght)
	{
		String formattedString = number;
		while(formattedString.length() < iLenght){
			formattedString = "0" + formattedString;
		}
		return formattedString;
	}
	
	private String getRnincaextPath()
	{
		try
		{
			return propertiesTree.getProperty(PropertiesPath.pathRnincaext.format());
		} catch (Exception e) {
			logger.warn("getRnincaextPath: ERRORE durante la lettura del parametro 'pathRnincaext' dal file di properties" , e);
		}
		return null;
	}	
	

    private void writeInfoLog(String sInfo)
    {
    	if (logger != null)
    		logger.info(sInfo);
    }
}