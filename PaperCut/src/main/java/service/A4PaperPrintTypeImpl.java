package main.java.service;

import main.java.exception.PaperPrintException;
import main.java.model.PaperPrintRate;
import main.java.validator.PaperPrintTypeValidatorImpl;

public class A4PaperPrintTypeImpl extends PaperPrintTypeAbstract{
	
	protected PaperPrintRate printRate = null;
	
	public A4PaperPrintTypeImpl(PaperPrintRate printRate) {
		super(new PaperPrintTypeValidatorImpl());
		this.printRate = printRate;
	}
	
	@Override
	public double singleSideBW(int quantity) throws PaperPrintException {
		if(printRate != null) {
			return calculateCost(quantity, printRate.getSingleBW());
		}
		return 0;
	}

	@Override
	public double singleSideColour(int quantity) throws PaperPrintException {
		if(printRate != null) {
			return calculateCost(quantity, printRate.getSingleColour());
		}
		return 0;
	}

	@Override
	public double doubleSideBW(int quantity) throws PaperPrintException {
		if(printRate != null) {
			return calculateCost(quantity, printRate.getDoubleBW());
		}
		return 0;
	}

	@Override
	public double doubleSideColour(int quantity) throws PaperPrintException {
		if(printRate != null) {
			return calculateCost(quantity, printRate.getDoubleColour());
		}
		return 0;
	}

}
