package main.java.model;

public enum PaperPrintRateXMLTag {
	ID("ID"),
	SINGLEBW("SingleBW"),
	SINGLECOLOUR("SingleColour"),
	DOUBLEBW("DoubleBW"),
	DOUBLECOLOUR("DoubleColour");
	
	private String value;
	
	private PaperPrintRateXMLTag(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
