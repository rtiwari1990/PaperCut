package test.java.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import main.java.exception.PaperPrintException;
import main.java.factory.PaperPrintJobFactory;
import main.java.factory.PaperPrintJobFactoryImpl;
import main.java.utils.PaperPrintRateUtility;

public class PaperPrintJobFactoryTest {

	private PaperPrintJobFactory jobFactory = null;
	
	private static String propFileName = null;

	private static final int validTotalPages = 4;
	private static final int validColorPages = 2;
	private static final String validDoubleSidedTrue = "TRUE";
	private static final String validDoubleSidedFalse = "FALSE";
	private static final String nullPaperType = null;
	
	private static final String inValidPaperSide = "invalid";
	private static final String inValidPaperType = "invalid";
	
	@Before
	public void setUp() {
		propFileName = new String("./properties.xml");
		try {
			PaperPrintRateUtility.loadProperties(propFileName);
			jobFactory = new PaperPrintJobFactoryImpl();
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test
	public void test_getPrintJobCost_validDoubleSided() {
		try {
			double printCost = jobFactory.getPrintJobCost(validTotalPages, validColorPages, validDoubleSidedTrue, nullPaperType);
			double expectedColorCost = validColorPages * PaperPrintRateUtility.get("A4").getDoubleColour();
			double expectedBWCost = validColorPages * PaperPrintRateUtility.get("A4").getDoubleBW();
			assertEquals("Expected and actual costs doen't match", (expectedColorCost+expectedBWCost), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test
	public void test_getPrintJobCost_validSingleSided() {
		try {
			double printCost = jobFactory.getPrintJobCost(validTotalPages, validColorPages, validDoubleSidedFalse, nullPaperType);
			double expectedColorCost = validColorPages * PaperPrintRateUtility.get("A4").getSingleColour();
			double expectedBWCost = validColorPages * PaperPrintRateUtility.get("A4").getSingleBW();
			assertEquals("Expected and actual costs doen't match", (expectedColorCost+expectedBWCost), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test
	public void test_getPrintJobCost_validAllColorDoubleSided() {
		try {
			double printCost = jobFactory.getPrintJobCost(validTotalPages, validTotalPages, validDoubleSidedTrue, nullPaperType);
			double expectedColorCost = validTotalPages * PaperPrintRateUtility.get("A4").getDoubleColour();
			assertEquals("Expected and actual costs doen't match", (expectedColorCost), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test
	public void test_getPrintJobCost_validAllColorSingleSided() {
		try {
			double printCost = jobFactory.getPrintJobCost(validTotalPages, validTotalPages, validDoubleSidedFalse, nullPaperType);
			double expectedColorCost = validTotalPages * PaperPrintRateUtility.get("A4").getSingleColour();
			assertEquals("Expected and actual costs doen't match", (expectedColorCost), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test
	public void test_getPrintJobCost_validAllBWDoubleSided() {
		try {
			double printCost = jobFactory.getPrintJobCost(validTotalPages, 0, validDoubleSidedTrue, nullPaperType);
			double expectedColorCost = validTotalPages * PaperPrintRateUtility.get("A4").getDoubleBW();
			assertEquals("Expected and actual costs doen't match", (expectedColorCost), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test
	public void test_getPrintJobCost_validAllBWSingleSided() {
		try {
			double printCost = jobFactory.getPrintJobCost(validTotalPages, 0, validDoubleSidedFalse, nullPaperType);
			double expectedColorCost = validTotalPages * PaperPrintRateUtility.get("A4").getSingleBW();
			assertEquals("Expected and actual costs doen't match", (expectedColorCost), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached "+ e.getStackTrace());
		}
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_getPrintJobCost_InvalidTotalPages() throws PaperPrintException {
		jobFactory.getPrintJobCost(0, validColorPages, validDoubleSidedFalse, nullPaperType);
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_getPrintJobCost_InvalidColorPages() throws PaperPrintException {
		jobFactory.getPrintJobCost(0, validColorPages, validDoubleSidedFalse, nullPaperType);
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_getPrintJobCost_inValidSide() throws PaperPrintException {
		jobFactory.getPrintJobCost(0, validColorPages, inValidPaperSide, nullPaperType);
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_getPrintJobCost_inValidPaperType() throws PaperPrintException {
		jobFactory.getPrintJobCost(0, validColorPages, validDoubleSidedFalse, inValidPaperType);
	}
}
