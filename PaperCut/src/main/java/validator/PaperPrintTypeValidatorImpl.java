package main.java.validator;

import main.java.exception.PaperPrintException;

public class PaperPrintTypeValidatorImpl implements PaperPrintTypeValidator {
	
	@Override
	public void validatePrintQuantity(int quantity) throws PaperPrintException {
		if(quantity < 0) {
			throw new PaperPrintException("Number of paper being printed is invalid");
		}
	}
	
	@Override
	public void validatePrintRate(double rate) throws PaperPrintException {
		if(rate < 0) {
			throw new PaperPrintException("Rate of printing is invalid");
		}
	}
}
