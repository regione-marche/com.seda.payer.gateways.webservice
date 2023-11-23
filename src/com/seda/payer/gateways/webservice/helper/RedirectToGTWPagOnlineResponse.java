package com.seda.payer.gateways.webservice.helper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.defender.rule.Regex;
import com.seda.payer.gateways.webservice.dati.ResponseType;

public class RedirectToGTWPagOnlineResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private com.seda.payer.gateways.webservice.dati.ResponseType response;
	private String orderid;
	private String datacreazione;
	private String datamodifica;
	private String totale;
	private String stato;
	//private String params;

	public RedirectToGTWPagOnlineResponse(String orderid, String datacreazione, String datamodifica, String totale, String stato) {
		this.orderid = orderid;
		this.datacreazione = datacreazione;
		this.datamodifica = datamodifica;
		this.totale = totale;
		this.stato = stato;
	}
	
	public RedirectToGTWPagOnlineResponse() {
		// TODO Auto-generated constructor stub
	}

	public RedirectToGTWPagOnlineResponse(ResponseType responseType,
			String orderid2, String datacreazione2, String datamodifica2,
			String stato2, String totale2) {
		this.response = responseType;
		this.orderid = orderid2;
		this.datacreazione = datacreazione2;
		this.datamodifica = datamodifica2;
		this.totale = totale2;
		this.stato = stato2;
	}

	public RedirectToGTWPagOnlineResponse(ResponseType responseType) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RedirectToGTWIGResponse: [ orderid = " + orderid + ", " +
				"datacreazione = " + datacreazione + ", datamodifica = " + datamodifica + ", totale = " + totale + "" +
				", stato = " + stato + " ]";
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
	
	
	//Esempio ok :
	//PAYMENTTYPE=CartCred&AMOUNT=400&DEPOSITAMOUNT=0&OBJECT=PAYMENT&TIMEMODIFIED=19.06.2012+12%3A46&STATE=AB&ORDERNUMBER=eef7f674-31c4-4c86-a6ef-6e64530880b0&CURRENCY=978&APPROVEAMOUNT=0&TIMECREATED=19.06.2012+12%3A45&AMOUNTEXP10=-2
	//Esempio ko :
	//DESCRIZIONE=STATO ORDINE FALLITO: ordine inesistente&SRC=1&PRC=10119
	
	public static RedirectToGTWPagOnlineResponse parse(String text) throws Exception {
		
		RedirectToGTWPagOnlineResponse response = new RedirectToGTWPagOnlineResponse();
		
		String[] arrayResponse = (URLDecoder.decode(text, "UTF-8")).split("&");
    
		/*if (arrayResponse[0].trim().substring(0, 11).equalsIgnoreCase("DESCRIZIONE")) {
			String stateFall = "ORDINEFALLITO";
			response.setStato(stateFall);
		} 
		else 
		{*/
		for (int i = 0; i < arrayResponse.length; i++) {
			String[] paymentType = arrayResponse[i].split("=");
			if(i==0){
				if (paymentType[i].trim().equalsIgnoreCase("DESCRIZIONE")) {
					String stateFall = "SOSPESA";
					String appo="";
					if(paymentType[1].trim().equalsIgnoreCase("STATO ORDINE FALLITO: ordine inesistente")){
						stateFall = "ORDINEFALLITO";
					}
					response.setStato(stateFall);
					i = arrayResponse.length;
					
				}
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("AMOUNT")) {
				response.setTotale(paymentType[1]);
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("DEPOSITAMOUNT")) {
				String[] depositaAmount = arrayResponse[i].split("=");
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("OBJECT")) {
				String[] object = arrayResponse[i].split("=");
			}
			
			
			if (paymentType[0].trim().equalsIgnoreCase("AUTHID")) {
				String[] authID = arrayResponse[i].split("=");
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("TIMEMODIFIED")) {
//				String[] timeModified = arrayResponse[i].split("=");
				response.setDatamodifica(paymentType[1]);
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("STATE")) {
//				String[] state = arrayResponse[i].split("=");
				response.setStato(paymentType[1]);
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("ORDERNUMBER")) {
//				String[] orderNumber = arrayResponse[i].split("=");
				response.setOrderid(paymentType[1]);
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("CURRENCY")) {
				String[] currency = arrayResponse[i].split("=");
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("APPROVEAMOUNT")) {
				String[] approveAmount = arrayResponse[i].split("=");
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("TIMECREATED")) {
//				String[] timeCreated = arrayResponse[i].split("=");
				response.setDatacreazione(paymentType[1]);
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("BRAND")) {
				String[] brand = arrayResponse[i].split("=");
			}
			
			if (paymentType[0].trim().equalsIgnoreCase("AMOUNTEXP10")) {
				String[] amountExp10 = arrayResponse[i].split("=");
			}
			
			
			
//			switch (i) {
//			case 0:
//				String[] paymentType = arrayResponse[i].split("=");
//				if (paymentType[0].trim().equalsIgnoreCase("DESCRIZIONE")) {
//					String stateFall = "ORDINEFALLITO";
//					response.setStato(stateFall);
//					i = arrayResponse.length;
//				}
//				break;
//			case 1:
//				String[] amount = arrayResponse[i].split("=");
//				response.setTotale(amount[1]);
//				break;
//			case 2:
//				String[] depositaAmount = arrayResponse[i].split("=");
//				break;
//			case 3:
//				String[] object = arrayResponse[i].split("=");
//				break;
//			case 4:
//				String[] timeModified = arrayResponse[i].split("=");
//				response.setDatamodifica(timeModified[1]);
//				break;
//			case 5:
//				String[] state = arrayResponse[i].split("=");
//				response.setStato(state[1]);
//				break;
//			case 6:
//				String[] orderNumber = arrayResponse[i].split("=");
//				response.setOrderid(orderNumber[1]);
//				break;
//			case 7:
//				String[] currency = arrayResponse[i].split("=");
//				break;
//			case 8:
//				String[] approveAmount = arrayResponse[i].split("=");
//				break;
//			case 9:
//				String[] timeCreated = arrayResponse[i].split("=");
//				response.setDatacreazione(timeCreated[1]);
//				break;
//			case 10:
//				String[] amountExp10 = arrayResponse[i].split("=");
//				break;
//			}
		}

		return response;
	}
	
	
	static String getParamsXmlString (Document doc, LoggerWrapper log ){
		try
		{
			//lettura docIntero
			//Document doc = GenericsXml.getXmlDocumentFromString(sXml);
			
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
			Element elem = (Element)(xpath.evaluate("/payment/responseInitPayment/data/specific/picParams", doc, XPathConstants.NODE));
			if (elem != null)
			{
				NodeList nodeList = elem.getChildNodes();
				
				//creazione docRidotto (solo il nodo picParams)
				Document docNew = GenericsXml.getNewDocument();
				
				Element root = docNew.createElement("picParams");
				if(nodeList != null && nodeList.getLength() > 0) 
				{
					for (int i = 0 ; i < nodeList.getLength(); i++) 
					{
						Node node = nodeList.item(i);
						root.appendChild(docNew.importNode(node, true));
					}
				}
				docNew.appendChild(root); 
	
				return Convert.documentToString(docNew, false);
			}
			
				
		} catch (Exception e) {
			log.error("getParamsXmlString: Errore nel recupero del nodo picParams");
			e.printStackTrace();
		}
		
		return "";
	}

	static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return "?";
	}

	public void setResponse(com.seda.payer.gateways.webservice.dati.ResponseType response) {
		this.response = response;
	}

	public com.seda.payer.gateways.webservice.dati.ResponseType getResponse() {
		return response;
	}

}