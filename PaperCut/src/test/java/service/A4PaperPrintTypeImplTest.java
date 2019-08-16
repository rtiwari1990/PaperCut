package test.java.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import main.java.exception.PaperPrintException;
import main.java.model.PaperSizeType;
import main.java.service.A4PaperPrintTypeImpl;
import main.java.service.PaperPrintTypeAbstract;
import main.java.utils.PaperPrintRateUtility;

public class A4PaperPrintTypeImplTest {
	
	private static String propFileName = null;
	
	PaperPrintTypeAbstract printType = null;
	
	@Before
	public void setUp() {
		try {
			propFileName = new String("./properties.xml");
			PaperPrintRateUtility.loadProperties(propFileName);
			printType = new A4PaperPrintTypeImpl(PaperPrintRateUtility.get(PaperSizeType.A4.getValue()));
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_singleSideBW_1Paper() {
		try {
			double printCost = printType.singleSideBW(1);
			assertEquals("printCost doesn't match", PaperPrintRateUtility.get("A4").getSingleBW(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_singleSideBW_2Paper() {
		try {
			double printCost = printType.singleSideBW(2);
			assertEquals("printCost doesn't match", 2*PaperPrintRateUtility.get("A4").getSingleBW(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_singleSideColour_1Paper() {
		try {
			double printCost = printType.singleSideColour(1);
			assertEquals("printCost doesn't match", PaperPrintRateUtility.get("A4").getSingleColour(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_singleSideColour_2Paper() {
		try {
			double printCost = printType.singleSideColour(2);
			assertEquals("printCost doesn't match", 2*PaperPrintRateUtility.get("A4").getSingleColour(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_doubleSideBW_1Paper() {
		try {
			double printCost = printType.doubleSideBW(1);
			assertEquals("printCost doesn't match", PaperPrintRateUtility.get("A4").getDoubleBW(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_doubleSideBW_2Paper() {
		try {
			double printCost = printType.doubleSideBW(2);
			assertEquals("printCost doesn't match", 2*PaperPrintRateUtility.get("A4").getDoubleBW(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_doubleSideColour_1Paper() {
		try {
			double printCost = printType.doubleSideColour(1);
			assertEquals("printCost doesn't match", PaperPrintRateUtility.get("A4").getDoubleColour(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test
	public void test_doubleSideColour_2Paper() {
		try {
			double printCost = printType.doubleSideColour(2);
			assertEquals("printCost doesn't match", 2*PaperPrintRateUtility.get("A4").getDoubleColour(), printCost, 0);
		} catch (PaperPrintException e) {
			fail("Unexpected exception reached.");
		}
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_singleSideBW_InvalidQuantity() throws PaperPrintException{
		printType.singleSideBW(-1);
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_singleSideColour_InvalidQuantity() throws PaperPrintException{
		printType.singleSideBW(-1);
	}

	@Test(expected=PaperPrintException.class)
	public void test_doubleSideBW_InvalidQuantity() throws PaperPrintException{
		printType.singleSideBW(-1);
	}
	
	@Test(expected=PaperPrintException.class)
	public void test_doubleSideColour_InvalidQuantity() throws PaperPrintException{
		printType.singleSideBW(-1);
	}
}
