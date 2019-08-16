PaperCut Assignment

package main.java.demo

	public class PaperPrintDemo
		This class is demonstrates the working of this java application. Run this class to execute this application.
		Path of the input csv file can be configured in the variable named "inputFileName".
		Path of the properties file containing rates for different paper types, print sides and colors can be configured in the variable "propFileName".


package main.java.exception

	public class PaperPrintException extends Exception
		This class extends Exception class encapsulates exceptions thrown by this application.


package main.java.factory;

	public interface PaperPrintJobFactory

		public double getPrintJobCost(int totalPages, int colorPages, String isDoubleSided, String paperType) throws PaperPrintException;
			This method takes input from user, calculates print cost and returns the cost.

	public class PaperPrintJobFactoryImpl implements PaperPrintJobFactory
		This class implements PaperPrintJobFactory.

		public double getPrintJobCost(int totalPages, int colorPages, String isDoubleSided, String paperType) throws PaperPrintException;
			Overrides this method from the interface.

		private double getPrintJobCost(PaperSizeType sizeType, PrintColourType colourType, PrintSideType sideType, int quantity ) throws PaperPrintException
			This is overloaded private method that calculates the print cost and returns the cost to its calling method.

		private PrintSideType getPrintSideType(String isDoubleSided)
			It returns PrintSideType object for the given string variable named isDoubleSided.

		private PaperSizeType getPaperSizeType(String sizeType)
			It returns PaperSizeType object for given string variable named sizeType.

		private PaperPrintTypeAbstract getPaperPrintType(PaperSizeType paperSize)
			It returns correct subclass of PaperPrintTypeAbstract for the given PaperSizeType variable named paperSize.


package main.java.model

	public class PaperPrintRate
		This class holds print rates for different combination of print sides and colour for a paper size as defined by 'ID' member. 'ID' holds String value
		for a PaperSizeType enum's instance.

	public enum PaperPrintRateXMLTag
		This enum's different instances represent different xml tags in an xml properties file.

	public enum PaperSizeType
		This enum's instances represent different supported paper types.

	public enum PrintColourType
		This enum's instances represent different supported print colour types.

	public enum PrintSideType
		This enum's instances represent different supported print sides types.


package main.java.service

	public abstract class PaperPrintTypeAbstract
		This abstract class encapsulates the printing options available per paper type e.g A4. It has abstract different methods representing each
		print option.

		Constructor:
			public PaperPrintTypeAbstract(PaperPrintTypeValidator printTypeValidator)

		public double calculateCost(int quantity, double rate) throws PaperPrintException
			This method multiplies the quntity and rate values to calculate the print cost and returns the cost.

		public abstract double singleSideBW(int quantity) throws PaperPrintException

		public abstract double singleSideColour(int quantity) throws PaperPrintException

		public abstract double doubleSideBW(int quantity) throws PaperPrintException

		public abstract double doubleSideColour(int quantity) throws PaperPrintException

	public class A4PaperPrintTypeImpl extends PaperPrintTypeAbstract
		This class extends and implements the methods from PaperPrintTypeAbstract class. It represents the print oprions available with A4 paper size.

		Constructor:
			public A4PaperPrintTypeImpl(PaperPrintRate printRate) : It takes object of PaperPrintRate that holds rates for different options for A4
			paper size.


package main.java.utils
	
	public class PaperPrintRateMap
		This class represents in memory cache for xml properties file. It holds JAVA's HashMap data structure internally to cache the data in the form
		of key-value pair.

		public void put(String key, PaperPrintRate value)
			This method put the key-value pair in the map.

		public PaperPrintRate get(String key)
			This method returns the value for given key from the map.

		public void remove(String key)
			This method removes the key-value pair from the map.

		public void putAll(Map<String, PaperPrintRate> printRateMap)
			This method puts all the contents of printRateMap into the cache.

		public void putAll(List<PaperPrintRate> printRateList)
			This method puts all the contents of printRateList into the cache with PaperPrintRate's ID as key and PaperPrintRate as value.

	public class PaperPrintRateUtility
		This is an utility class for loading properties file's contents into in-memory cache and reading key-value pair from the cache.

		Constructor:
			public PaperPrintRateUtility()

		public static void loadProperties(String propFilename) throws PaperPrintException
			This method loads properties from the the file with path as provided in 'propFilename' variable.

		public static PaperPrintRate get(String key)
			This method returns PaperPrintRate objects for given key from the cache.

	public class PaperPrintRateXMLParser
		This class acts as xml parser for xml properties file.

		Constructor:
			public PaperPrintRateXMLParser()

		public List<PaperPrintRate> getPaperPrintRate(String propFilename)
			Reads the properties from file as defined by path propFilename into List of PaperPrintRate data structure.

	
package main.java.validator

	public interface PaperPrintJobFactoryValidator
		This interface contains method for validating data from PaperPrintJobFactory class
		
	public class PaperPrintJobFactoryValidatorImpl implements PaperPrintJobFactoryValidator
		This class implements the methods from interface PaperPrintJobFactoryValidator.
			
	public interface PaperPrintTypeValidator
		This interface contains method for validating data from PaperPrintType class

	public class PaperPrintTypeValidatorImpl implements PaperPrintTypeValidator
		This class implements the method from the interface PaperPrintTypeValidator


package test.java.model
	
	public class PaperPrintJobFactoryTest
		This class tests the functionality of PaperPrintJobFactory class

	
package test.java.service

	public class A4PaperPrintTypeImplTest
		This class tests the functionality of A4PaperPrintTypeImpl class



Design Decision:

	Different combination of print sides and colours can be added/removed from the abstract class PaperPrintTypeAbstract. Whereas different paper types can be
	added/removed as subclass extending from PaperPrintTypeAbstract. Also, rates of different combinations can be stored in a properties file from where it can
	be loaded into in-memory HashMap data structure. This map stores print rates for different print options as an object. To load and read data from the cache
	an utility class, PaperPrintRateUtility, is used. Moreover, types and values for different paper sizes, print sides and colours are defined in their
	respective enum classes. This code structure is scalable and maintainable for more print options that might come as a requirement in the future.
