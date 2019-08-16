package main.java.factory;

import main.java.exception.PaperPrintException;
import main.java.model.PaperSizeType;
import main.java.model.PrintColourType;
import main.java.model.PrintSideType;
import main.java.service.A4PaperPrintTypeImpl;
import main.java.service.PaperPrintTypeAbstract;
import main.java.utils.PaperPrintRateUtility;
import main.java.validator.PaperPrintJobFactoryValidator;
import main.java.validator.PaperPrintJobFactoryValidatorImpl;

public class PaperPrintJobFactoryImpl implements PaperPrintJobFactory {
	
	private PaperPrintJobFactoryValidator factoryValidator = null;
	
	public PaperPrintJobFactoryImpl() {
		factoryValidator = new PaperPrintJobFactoryValidatorImpl();
	}
	
	@Override
	public double getPrintJobCost(int totalPages, int colorPages, String isDoubleSided, String paperType) throws PaperPrintException {
		double printCost = 0;
		PaperSizeType sizeType = this.getPaperSizeType(paperType);
		PrintSideType sideType = getPrintSideType(isDoubleSided);
		
		factoryValidator.validatePaperSizeType(sizeType);
		factoryValidator.validatePrintSideType(sideType);
		factoryValidator.validateTotalPages(totalPages);
		factoryValidator.validateColorPages(colorPages);
		factoryValidator.validateTotalAndColorPages(totalPages, colorPages);
		
		printCost += this.getPrintJobCost(sizeType,
				PrintColourType.COLOUR,
				sideType,
				colorPages);
		printCost += this.getPrintJobCost(sizeType,
				PrintColourType.BW,
				sideType,
				(totalPages - colorPages));
		
		return printCost;
	}
	
	private double getPrintJobCost(PaperSizeType sizeType, PrintColourType colourType, PrintSideType sideType, int quantity ) throws PaperPrintException {
		double printCost = 0;
		PaperPrintTypeAbstract printType = this.getPaperPrintType(sizeType);
		
		if(colourType.equals(PrintColourType.BW) && sideType.equals(PrintSideType.DOUBLE_SIDED)) {
			printCost = printType.doubleSideBW(quantity);
		}else if(colourType.equals(PrintColourType.BW) && sideType.equals(PrintSideType.SINGLE_SIDED)) {
			printCost = printType.singleSideBW(quantity);
		}else if(colourType.equals(PrintColourType.COLOUR) && sideType.equals(PrintSideType.DOUBLE_SIDED)) {
			printCost = printType.doubleSideColour(quantity);
		}else if(colourType.equals(PrintColourType.COLOUR) && sideType.equals(PrintSideType.SINGLE_SIDED)) {
			printCost = printType.singleSideColour(quantity);
		}
		
		return printCost;
	}
	
	private PrintSideType getPrintSideType(String isDoubleSided){
		if(isDoubleSided.contentEquals("TRUE")) {
			return PrintSideType.DOUBLE_SIDED;
		}else if(isDoubleSided.contentEquals("FALSE")) {
			return PrintSideType.SINGLE_SIDED;
		}
		return null;
	}
	
	private PaperSizeType getPaperSizeType(String sizeType) {
		if(sizeType == null) {
			return PaperSizeType.A4;
		}
		return null;
	}
	
	private PaperPrintTypeAbstract getPaperPrintType(PaperSizeType paperSize) {
		PaperPrintTypeAbstract printType = null;
		
		if(paperSize.equals(PaperSizeType.A4)) {
			printType = new A4PaperPrintTypeImpl(PaperPrintRateUtility.get(PaperSizeType.A4.getValue()));
		}
		
		return printType;
	}
}
