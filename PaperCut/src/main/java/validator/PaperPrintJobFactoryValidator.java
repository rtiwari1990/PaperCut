package main.java.validator;

import main.java.exception.PaperPrintException;
import main.java.model.PaperSizeType;
import main.java.model.PrintSideType;

public interface PaperPrintJobFactoryValidator {

	void validatePrintSideType(PrintSideType sideType) throws PaperPrintException;

	void validatePaperSizeType(PaperSizeType sizeType) throws PaperPrintException;
	
	void validateTotalPages(int totalPages) throws PaperPrintException;
	
	void validateColorPages(int colorPages) throws PaperPrintException;
	
	void validateTotalAndColorPages(int totalPages, int colorPages) throws PaperPrintException;

}