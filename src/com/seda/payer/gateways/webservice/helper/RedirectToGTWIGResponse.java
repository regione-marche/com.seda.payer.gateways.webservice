package com.seda.payer.gateways.webservice.helper;

import java.io.Serializable;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seda.commons.logger.LoggerServer;
import com.seda.commons.string.Convert;

public class RedirectToGTWIGResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String urlStrumPag;
	private String params;
	private String codiceEsito;
	private String descrizioneEsito;
	private String mavPdfBase64;
	private String mavPdfFilePath;
	private String mavNumeroMav;
	
	public RedirectToGTWIGResponse() { 
		this.urlStrumPag = "";
		this.params = "";
	}

	public RedirectToGTWIGResponse(String urlStrumPag, String codiceEsito, String descrizioneEsito, String params) {
		this.urlStrumPag = urlStrumPag;
		this.params = params;
		this.codiceEsito = codiceEsito;
		this.descrizioneEsito = descrizioneEsito;
	}
	
	
	public String getUrlStrumPag() {
		return urlStrumPag;
	}

	public void setUrlStrumPag(String urlStrumPag) {
		this.urlStrumPag = urlStrumPag;
	}

	public String getCodiceEsito() {
		return codiceEsito;
	}

	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}

	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}

	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}
	
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	

	public void setMavPdfBase64(String pdfBase64) {
		this.mavPdfBase64 = pdfBase64;
	}

	public String getMavPdfBase64() {
		return mavPdfBase64;
	}

	public void setMavNumeroMav(String numeroMav) {
		this.mavNumeroMav = numeroMav;
	}

	public String getMavNumeroMav() {
		return mavNumeroMav;
	}	

	public void setMavPdfFilePath(String pdfFilePath) {
		this.mavPdfFilePath = pdfFilePath;
	}

	/**
	 * Valorizzato solo dopo la notifica
	 * @return
	 */
	public String getMavPdfFilePath() {
		return mavPdfFilePath;
	}
	
	@Override
	public String toString() {
		return "RedirectToGTWIGResponse: [ urlStrumPag = " + urlStrumPag + ", " +
				"codiceEsito = " + codiceEsito + ", descrizioneEsito = " + descrizioneEsito + " ]";
	}

	public static RedirectToGTWIGResponse parse(String xmlText, LoggerServer log) throws Exception {
		//DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //DocumentBuilder db = dbf.newDocumentBuilder();
        //InputSource is = new InputSource();
        //is.setCharacterStream(new StringReader(xmlText));
        //Document doc = db.parse(is);
		
		Document doc=GenericsXml.getXmlDocumentFromString(xmlText);
        
        RedirectToGTWIGResponse response = new RedirectToGTWIGResponse();
        
        /*NodeList nodes = doc.getElementsByTagName("specific");
        // we iterate the specific
        try {
            for (int i = 0; i < nodes.getLength(); ) {
                String urlStrumPag = getCharacterDataFromElement((Element) ((Element) nodes.item(i)).getElementsByTagName("urlStrumPag").item(0)); 
                response.setUrlStrumPag(urlStrumPag);
                break;
             }
		} catch (Exception e) {
			log.error("urlStrumPag not found, node specific is empty");
			System.out.println("urlStrumPag not found, node specific is empty"); 
		}*/
		
        //recupero e setto il nodo urlStrumPag	
		String urlStrumPag = GenericsXml.getElementValue("/payment/responseInitPayment/data/specific/urlStrumPag", doc, log);
		response.setUrlStrumPag(urlStrumPag);
		
		
		//recupero e setto il nodo picParams	
		response.setParams(getParamsXmlString(doc,log));
		
		
		//recupero e setto i nodi codice e descrizione
		
		String codice = GenericsXml.getElementValue("/payment/responseInitPayment/esito/codice", doc, log);
    	String descrizione = GenericsXml.getElementValue("/payment/responseInitPayment/esito/descrizione", doc, log);
    	response.setCodiceEsito(codice);
    	response.setDescrizioneEsito(descrizione);
		
        /*nodes = doc.getElementsByTagName("esito");
        // we iterate the esito
        try {
	        for (int i = 0; i < nodes.getLength(); ) {
	        	String codice = getCharacterDataFromElement((Element) ((Element) nodes.item(i)).getElementsByTagName("codice").item(0));
	        	String descrizione = getCharacterDataFromElement((Element) ((Element) nodes.item(i)).getElementsByTagName("descrizione").item(0));
	        	response.setCodiceEsito(codice);
	        	response.setDescrizioneEsito(descrizione);
				break;
	        }
		} catch (Exception e) {
			log.error("codice e descrizione not found, node esito is empty");
			System.out.println("codice e descrizione not found, node esito is empty"); 
		}*/
		
        return response;
	}
	
	
	public static RedirectToGTWIGResponse parseStorno(String xmlText, LoggerServer log) throws Exception {
			
		Document doc=GenericsXml.getXmlDocumentFromString(xmlText);
        
        RedirectToGTWIGResponse response = new RedirectToGTWIGResponse();
       		
		
		//recupero e setto i nodi codice e descrizione
		
		String codice = GenericsXml.getElementValue("/service/function/esito/codice", doc, log);
    	String descrizione = GenericsXml.getElementValue("/service/function/esito/descrizione", doc, log);
    	
    	response.setCodiceEsito(codice);
    	response.setDescrizioneEsito(descrizione);
		
        return response;
	}
	
	
	public static RedirectToGTWIGResponse parseRidOnLine(String xmlText, int returnCode, LoggerServer log) throws Exception {
		
		// fase di risposta dal gateway di pagamento per una RID
		RedirectToGTWIGResponse response = new RedirectToGTWIGResponse();
        if (xmlText!=null && xmlText.length() > 0)
        {
			Document doc=GenericsXml.getXmlDocumentFromString(xmlText);
	        
	        //recupero e setto il nodo urlStrumPag	
			String urlStrumPag = GenericsXml.getElementValue("/payment/responseInitPayment/data/specific/urlStrumPag", doc, log);
			
			// aggiungo al query string della urlStrumPag altri parametri prelevati dall'XML arrivato
			if (urlStrumPag != null && !urlStrumPag.equals(""))
			{
				String idOperazione = GenericsXml.getElementValue("/payment/responseInitPayment/data/generic/idOperazione", doc, log);
		    	String codiceMerchant = GenericsXml.getElementValue("/payment/responseInitPayment/data/specific/codiceMerchant", doc, log);
		    	if (idOperazione != null && codiceMerchant != null && !idOperazione.equals("") && !codiceMerchant.equals(""))
		    		urlStrumPag += "?idOperazione=" + idOperazione + "&codiceMerchant=" + codiceMerchant;
			}
			response.setUrlStrumPag(urlStrumPag);
			
			//recupero e setto i nodi codice e descrizione		
			String codice = GenericsXml.getElementValue("/payment/responseInitPayment/esito/codice", doc, log);
	    	String descrizione = GenericsXml.getElementValue("/payment/responseInitPayment/esito/descrizione", doc, log);
	    	response.setCodiceEsito(codice);
	    	response.setDescrizioneEsito(descrizione);	
        }
        else
        {
        	// il gateway non ha risposto
        	response.setUrlStrumPag("");
			response.setCodiceEsito("1");
	    	response.setDescrizioneEsito("Il gateway RID non ha risposto correttamente returnCodeHTTP:"+returnCode);
        }
		
        return response;
	}
	
	public static RedirectToGTWIGResponse parseMavOnLine(String xmlText, int returnCode, LoggerServer log) throws Exception 
	{		
		// fase di risposta dal gateway di pagamento per una MAV
		RedirectToGTWIGResponse response = new RedirectToGTWIGResponse();
        if (xmlText!=null && xmlText.length() > 0)
        {
			Document doc=GenericsXml.getXmlDocumentFromString(xmlText);
			
			response.setMavPdfBase64(GenericsXml.getElementValue("/payment/responseInitPayment/data/specific/pdfBase64", doc, log));
	    	response.setMavNumeroMav(GenericsXml.getElementValue("/payment/responseInitPayment/data/specific/numeroMav", doc, log));
	    	//il mavFilePath è salvato dopo la notifica non arriva dalla reponse
	    	
			//recupero e setto i nodi codice e descrizione		
			response.setCodiceEsito(GenericsXml.getElementValue("/payment/responseInitPayment/esito/codice", doc, log));
	    	response.setDescrizioneEsito(GenericsXml.getElementValue("/payment/responseInitPayment/esito/descrizione", doc, log));	
        }
        else
        {
        	// il gateway non ha risposto
        	response.setUrlStrumPag("");
			response.setCodiceEsito("1");
	    	response.setDescrizioneEsito("Il gateway MAV non ha risposto correttamente returnCodeHTTP:"+returnCode);
        }
		
        return response;
	}
	static String getParamsXmlString (Document doc, LoggerServer log ){
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
	
	/**
	 * ritorna la stringa da passare come parametro per il MAV dopo la comunicazione S2S
	 * @return
	 */
	public String getParamsXmlString_MAV()
	{
		// è stato trovato il modo di decodificarlo ma solo nel file quindi viene passato sempre in base 64
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
		   "<mav>" +
	   		   "<numero>" + this.getMavNumeroMav() + "</numero>" +
			   "<pdfpath>" + this.getMavPdfFilePath() + "</pdfpath>" +
			   "<pdfbinario>" + "" + "</pdfbinario>" +		   
		   "</mav>";
	}
	
	static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return "?";
	}

	

}