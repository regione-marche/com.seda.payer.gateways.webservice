package com.seda.payer.gateways.webservice.helper;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.seda.commons.logger.LoggerServer;

public class GenericsXml {

	public static Document getXmlDocumentFromString(String xmlString) throws ParserConfigurationException, SAXException, IOException
	{
		
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlString));
		Document doc = builder.parse(is);
		
		return doc;
	}
	
	public static Document getNewDocument() throws ParserConfigurationException
	{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		
		return builder.newDocument();
	}
	
	public static String getElementValue(String xpath_expr, Document doc)
	{
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		try
		{
			Element elem = (Element)(xpath.evaluate(xpath_expr, doc, XPathConstants.NODE));
			if (elem != null)
				return elem.getTextContent();
		}
		catch (Exception e) {}
		
		return "";
	}
	
	public static String getElementValue(String xpath_expr, Document doc, LoggerServer log)
	{
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		try
		{
			Element elem = (Element)(xpath.evaluate(xpath_expr, doc, XPathConstants.NODE));
			if (elem != null)
				return elem.getTextContent();
			else
				log.error("Errore durante la lettura del nodo: " + xpath_expr);
		}
		catch (Exception e) {
			log.error(xpath_expr + ": " + e.getMessage());
		}
		
		return "";
	}
	
	
	
}
