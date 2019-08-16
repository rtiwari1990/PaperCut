package main.java.validator;

import main.java.exception.PaperPrintException;

public interface PaperPrintTypeValidator {

	void validatePrintQuantity(int quantity) throws PaperPrintException;

	void validatePrintRate(double rate) throws PaperPrintException;

}