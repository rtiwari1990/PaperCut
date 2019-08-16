package main.java.model;

public enum PaperSizeType {
	A4("A4");
	
	private String value;
	
	private PaperSizeType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
