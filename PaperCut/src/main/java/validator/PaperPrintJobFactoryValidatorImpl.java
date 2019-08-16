package main.java.validator;

import main.java.exception.PaperPrintException;
import main.java.model.PaperSizeType;
import main.java.model.PrintSideType;

public class PaperPrintJobFactoryValidatorImpl implements PaperPrintJobFactoryValidator {
	
	@Override
	public void validatePrintSideType(PrintSideType sideType) throws PaperPrintException {
		if(sideType == null) {
			throw new PaperPrintException("Invalid value of sideType: "+ sideType);
		}
	}
	
	@Override
	public void validatePaperSizeType(PaperSizeType sizeType) throws PaperPrintException {
		if(sizeType == null) {
			throw new PaperPrintException("Invalid value of sizeType: "+sizeType);
		}
	}

	@Override
	public void validateTotalPages(int totalPages) throws PaperPrintException {
		if(totalPages < 1) {
			throw new PaperPrintException("Invalid value of sizeType: "+totalPages);
		}
	}

	@Override
	public void validateColorPages(int colorPages) throws PaperPrintException {
		if(colorPages < 0) {
			throw new PaperPrintException("Invalid value of colorPages: "+colorPages);
		}
	}

	@Override
	public void validateTotalAndColorPages(int totalPages, int colorPages) throws PaperPrintException {
		if(totalPages < colorPages) {
			throw new PaperPrintException("Invalid value of totalPages: "+ totalPages +" colorPages: "+ colorPages);
		}
	}
}
