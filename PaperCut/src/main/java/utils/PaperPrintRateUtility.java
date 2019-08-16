package main.java.utils;

import java.util.List;

import main.java.exception.PaperPrintException;
import main.java.model.PaperPrintRate;

public class PaperPrintRateUtility {
	
	private static PaperPrintRateXMLParser xMLParser = new PaperPrintRateXMLParser();
	private static PaperPrintRateMap rateMap = new PaperPrintRateMap();
	
	public PaperPrintRateUtility() {
	}
	
	public static void loadProperties(String propFilename) throws PaperPrintException {
		List<PaperPrintRate> printRateList = xMLParser.getPaperPrintRate(propFilename);
		rateMap.putAll(printRateList);
	}
	
	public static PaperPrintRate get(String key) {
		return rateMap.get(key);
	}
}
