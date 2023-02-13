package com.seda.payer.gateways.webservice.helper;

import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.seda.payer.gateways.webservice.dati.ResponseType;

public class RedirectToGTWCartaSIResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private com.seda.payer.gateways.webservice.dati.ResponseType response;
	private String orderid;
	private String datacreazione;
	private String datamodifica;
	private String totale;
	private String stato;
	
	
	
	

	public com.seda.payer.gateways.webservice.dati.ResponseType getResponse() {
		return response;
	}

	public void setResponse(
			com.seda.payer.gateways.webservice.dati.ResponseType response) {
		this.response = response;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDatacreazione() {
		return datacreazione;
	}

	public void setDatacreazione(String datacreazione) {
		this.datacreazione = datacreazione;
	}

	public String getDatamodifica() {
		return datamodifica;
	}

	public void setDatamodifica(String datamodifica) {
		this.datamodifica = datamodifica;
	}

	public String getTotale() {
		return totale;
	}

	public void setTotale(String totale) {
		this.totale = totale;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public RedirectToGTWCartaSIResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public RedirectToGTWCartaSIResponse(ResponseType response, String orderid,
			String datacreazione, String datamodifica, String totale,
			String stato) {
		super();
		this.response = response;
		this.orderid = orderid;
		this.datacreazione = datacreazione;
		this.datamodifica = datamodifica;
		this.totale = totale;
		this.stato = stato;
	}

	
	
	public static RedirectToGTWPagOnlineResponse parseCARTA_SI(
			String responseString) {
		    RedirectToGTWPagOnlineResponse response = new RedirectToGTWPagOnlineResponse();
		    InputSource source = new InputSource(new StringReader(responseString));
		    String esitoRichiesta = "";
		    String totale = "";
		    String dataOra = "";
		    String dataPagamentoGateway ="";
		    try {
		    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			    DocumentBuilder db = dbf.newDocumentBuilder();
			    Document document = db.parse(source);
			    XPathFactory xpathFactory = XPathFactory.newInstance();
			    XPath xpath = xpathFactory.newXPath();
		    	esitoRichiesta = (String) xpath.evaluate("/VPOSRES/INTRES/esitoRichiesta", document);
		    	totale = (String) xpath.evaluate("/VPOSRES/INTRES/importo", document);
		    	dataOra = (String) xpath.evaluate("/VPOSRES/INTRES/OPERATIONS_LIST/OPERATION/dataOra", document);
		    	
		    	
				
		    	
		    	
		    } catch (Exception e) {
		        e.printStackTrace();
		    }finally{
		    	source=null;
		    }
		    response.setStato(esitoRichiesta);
		    response.setTotale(totale);
		    
		    
		    
		    response.setDatamodifica(dataOra);
		   
		    
		return response;
	}

	
	
	public RedirectToGTWCartaSIResponse(ResponseType response) {
		super();
		this.response = response;
	}

	public static boolean verificaMAC(String responseString,String securityString) {
		    InputSource source = new InputSource(new StringReader(responseString));
		    XPathFactory xpathFactory = XPathFactory.newInstance();
		    XPath xpath = xpathFactory.newXPath();
		    NodeList operationList;
		    String alias="";
		    String codTrans="";
		    String esitoRichiesta = "";
		    String importo = "";
		    String divisa = "";
		    String codAut="";
		    String NUMELM="";
		    //cilcle...
		    String id_op="";
		    String type_op="";
		    String importo_op="";
		    String divisa_op="";
		    String result="";
		    String user="";
		    String mac="";
		    StringBuffer stringaMAC=new StringBuffer();
		    try {
		    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			    DocumentBuilder db = dbf.newDocumentBuilder();
			    Document document = db.parse(source);
		    	alias = (String) xpath.evaluate("/VPOSRES/alias", document);
		    	codTrans = (String) xpath.evaluate("/VPOSRES/INTRES/codTrans", document);
		    	esitoRichiesta=(String) xpath.evaluate("/VPOSRES/INTRES/esitoRichiesta",document);
		    	importo=(String) xpath.evaluate("/VPOSRES/INTRES/importo",document);
		    	divisa=(String) xpath.evaluate("/VPOSRES/INTRES/divisa",document);
		    	codAut=(String) xpath.evaluate("/VPOSRES/INTRES/codAut",document);
		    	// getting attribute
		    	NodeList nodeList=(NodeList) xpath.evaluate("/VPOSRES/INTRES/OPERATIONS_LIST",document, XPathConstants.NODESET);
		    	if(nodeList.item(0)!=null){
		    		NUMELM=nodeList.item(0).getAttributes().getNamedItem("NUMELM").getTextContent();
		    	}
		    	
		    	mac=(String) xpath.evaluate("/VPOSRES/mac",document);
		    	
		    	stringaMAC.append(alias).append(codTrans).append(esitoRichiesta).append(importo).append(divisa).append(codAut).append(NUMELM);
		    	//cilcle
		    	operationList = (NodeList) xpath.evaluate("/VPOSRES/INTRES/OPERATIONS_LIST/OPERATION", document, XPathConstants.NODESET);
		    	for (int i = 0; i <operationList.getLength(); i++) {
		    		 if(operationList.item(i).getNodeType() == Node.ELEMENT_NODE) {
		    			 Element currentElm = (Element)operationList.item(i);
		    			 id_op=currentElm.getElementsByTagName("id_op").item(0).getTextContent();
		    			 type_op=currentElm.getElementsByTagName("type_op").item(0).getTextContent();
		    			 importo_op=currentElm.getElementsByTagName("importo_op").item(0).getTextContent();
		    			 divisa_op=currentElm.getElementsByTagName("divisa").item(0).getTextContent();
		    			 result=currentElm.getElementsByTagName("result").item(0).getTextContent();
		    			 user=currentElm.getElementsByTagName("user").item(0).getTextContent();
		    			 stringaMAC.append(id_op).append(type_op).append(importo_op).append(divisa_op).append(result).append(user);
		    		 }
		    	}
		    	stringaMAC.append(securityString);
		    	if ((SSHEncode(stringaMAC.toString())).equals(mac)){
	            	return true;
	            }
		    	
		    } catch (Exception e) {
		        e.printStackTrace();
		    }finally{
		    	source=null;
		    }
		
		return false;
	}
	
	private static  String SSHEncode(String stringaMAC) throws NoSuchAlgorithmException {
		 MessageDigest md = MessageDigest.getInstance("SHA");
		    System.out.println("String to encode "+stringaMAC);
		    byte[] dataBytes = stringaMAC.getBytes();
		    md.update(dataBytes);
		
		    byte[] mdbytes = md.digest();
		 
		    //convert the byte to hex format
		    StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++) {
		    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RedirectToGTWCartaSIResponse [datacreazione=");
		builder.append(datacreazione);
		builder.append(", datamodifica=");
		builder.append(datamodifica);
		builder.append(", orderid=");
		builder.append(orderid);
		builder.append(", response=");
		builder.append(response);
		builder.append(", stato=");
		builder.append(stato);
		builder.append(", totale=");
		builder.append(totale);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
