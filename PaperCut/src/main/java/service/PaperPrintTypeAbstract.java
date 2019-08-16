package main.java.service;

import main.java.exception.PaperPrintException;
import main.java.validator.PaperPrintTypeValidator;

public abstract class PaperPrintTypeAbstract {
	
	private PaperPrintTypeValidator printTypeValidator = null;
	
	public PaperPrintTypeAbstract(PaperPrintTypeValidator printTypeValidator) {
		this.printTypeValidator = printTypeValidator;
	}

	public double calculateCost(int quantity, double rate) throws PaperPrintException {
		printTypeValidator.validatePrintQuantity(quantity);
		printTypeValidator.validatePrintRate(rate);
		return (double)quantity * rate;
	}

	public abstract double singleSideBW(int quantity) throws PaperPrintException;
	public abstract double singleSideColour(int quantity) throws PaperPrintException;
	public abstract double doubleSideBW(int quantity) throws PaperPrintException;
	public abstract double doubleSideColour(int quantity) throws PaperPrintException;
}
