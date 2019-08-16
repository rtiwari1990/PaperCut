package main.java.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import main.java.exception.PaperPrintException;
import main.java.factory.PaperPrintJobFactory;
import main.java.factory.PaperPrintJobFactoryImpl;
import main.java.model.PaperSizeType;
import main.java.model.PrintColourType;
import main.java.model.PrintSideType;
import main.java.utils.PaperPrintRateUtility;

public class PaperPrintDemo {

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	private static String propFileName = new String("./properties.xml");
	private static String inputFileName = new String("./inputFile.csv");
	
	public static void main(String[] args) {		
		try {
			PaperPrintRateUtility.loadProperties(propFileName);
			System.out.println("Total print cost of the job is "+df2.format(calculatePrintCost(readInputFile(inputFileName))));		
		} catch (PaperPrintException e) {
			System.out.println("Reached an unexpected exception. Aborting the program");
		}
	}
		
	public static List<String[]> readInputFile(String inputFileName) {
		List<String[]> inputList = new ArrayList<String[]>();
		
		String splitBy = new String(",");
		
		FileReader fileRdr;
		BufferedReader bufferedRdr;
		
		try {
			fileRdr = new FileReader(inputFileName);
			bufferedRdr = new BufferedReader(fileRdr);
			
			String line = null;
			
			line = bufferedRdr.readLine(); // Read and skip first header line
			while((line = bufferedRdr.readLine()) != null) {
				String[] inputArr = line.split(splitBy);
				inputList.add(inputArr);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputList;
	}

	public static double calculatePrintCost(List<String[]> inputList) throws PaperPrintException {
		PaperPrintJobFactory jobFactory = new PaperPrintJobFactoryImpl();
		
		double printCost = 0;
		
		int totalPagesIndex = 0;
		int colorPagesIndex = 1;
		int doubleSidedIndex = 2;
		
		for(String[] inputArr: inputList) {
			printCost += jobFactory.getPrintJobCost(Integer.valueOf(inputArr[totalPagesIndex]),
													Integer.valueOf(inputArr[colorPagesIndex]),
													inputArr[doubleSidedIndex],
													null);
		}
		
		return printCost;
	}
}
