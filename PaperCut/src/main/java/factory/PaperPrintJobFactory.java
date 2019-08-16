package main.java.factory;

import main.java.exception.PaperPrintException;

public interface PaperPrintJobFactory {

	public double getPrintJobCost(int totalPages, int colorPages, String isDoubleSided, String paperType)
			throws PaperPrintException;

}