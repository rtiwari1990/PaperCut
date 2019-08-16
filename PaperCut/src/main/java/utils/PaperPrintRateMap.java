package main.java.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.model.PaperPrintRate;

public class PaperPrintRateMap {
	private Map<String, PaperPrintRate> printRateMap = new HashMap<String, PaperPrintRate>();
	
	public void put(String key, PaperPrintRate value) {
		this.printRateMap.put(key, value);
	}
	
	public PaperPrintRate get(String key) {
		return this.printRateMap.get(key);
	}
	
	public void remove(String key) {
		this.printRateMap.remove(key);
	}
	
	public void putAll(Map<String, PaperPrintRate> printRateMap) {
		this.printRateMap.putAll(printRateMap);
	}
	
	public void putAll(List<PaperPrintRate> printRateList) {
		Map<String, PaperPrintRate> tempRateMap = new HashMap<String, PaperPrintRate>();
		for(PaperPrintRate printRate : printRateList) {
			tempRateMap.put(printRate.getID(), printRate);
		}
		this.printRateMap.putAll(tempRateMap);
	}
}
