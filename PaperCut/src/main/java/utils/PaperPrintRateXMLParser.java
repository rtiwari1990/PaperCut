package main.java.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.java.exception.PaperPrintException;
import main.java.model.PaperPrintRate;
import main.java.model.PaperPrintRateXMLTag;

public class PaperPrintRateXMLParser {
	
	public PaperPrintRateXMLParser() {
	}
	
	public List<PaperPrintRate> getPaperPrintRate(String propFilename) throws PaperPrintException {
		PaperPrintRate printRate = null;
		List<PaperPrintRate> printRateList = new ArrayList<PaperPrintRate>();
		
		Document document = getDocument(propFilename);		
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				String ID = node.getAttributes().getNamedItem(PaperPrintRateXMLTag.ID.getValue()).getNodeValue();
				printRate = createPaperPrintRate(elem, ID);
				printRateList.add(printRate);
			}
		}
		
		return printRateList;
	}
	
	private Document getDocument(String propFilename) throws PaperPrintException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new PaperPrintException("Reached unexpected ParserConfigurationException");
		}
		
		Document document;
		
		try {
			document = builder.parse(propFilename);
		} catch (SAXException | IOException e) {
			throw new PaperPrintException("Reached unexpected SAXExcetion/IOException");
		}
		
		return document;
	}
	
	private PaperPrintRate createPaperPrintRate(Element elem, String ID) {
		PaperPrintRate printRate = null;
		double singleBW = Double.parseDouble(elem.getElementsByTagName(PaperPrintRateXMLTag.SINGLEBW.getValue())
              	.item(0).getChildNodes().item(0).getNodeValue());

		double singleColour = Double.parseDouble(elem.getElementsByTagName(PaperPrintRateXMLTag.SINGLECOLOUR.getValue()).item(0)
              	.getChildNodes().item(0).getNodeValue());

		double doubleBW = Double.parseDouble(elem.getElementsByTagName(PaperPrintRateXMLTag.DOUBLEBW.getValue())
              	.item(0).getChildNodes().item(0).getNodeValue());

		double doubleColour = Double.parseDouble(elem.getElementsByTagName(PaperPrintRateXMLTag.DOUBLECOLOUR.getValue())
              	.item(0).getChildNodes().item(0).getNodeValue());

		printRate = new PaperPrintRate(ID, singleBW, singleColour, doubleBW, doubleColour);
		return printRate;
	}
}
