package main.java.model;

public class PaperPrintRate {
	private String ID;
	private double singleBWRate;
	private double singleColourRate;
	private double doubleBWRate;
	private double doubleColourRate;
	
	public PaperPrintRate(String ID, double SingleBW, double SingleColour, double DoubleBW, double DoubleColour) {
		this.ID = ID;
		this.singleBWRate =  SingleBW;
		this.singleColourRate = SingleColour;
		this.doubleBWRate = DoubleBW;
		this.doubleColourRate = DoubleColour;
	}

	public String getID() {
		return ID;
	}

	public double getSingleBW() {
		return singleBWRate;
	}

	public double getSingleColour() {
		return singleColourRate;
	}

	public double getDoubleBW() {
		return doubleBWRate;
	}

	public double getDoubleColour() {
		return doubleColourRate;
	}

	@Override
	public String toString() {
		return "PaperPrintRate [ID=" + ID + ", singleBWRate=" + singleBWRate + ", singleColourRate=" + singleColourRate
				+ ", doubleBWRate=" + doubleBWRate + ", doubleColourRate=" + doubleColourRate + "]";
	}	
}
