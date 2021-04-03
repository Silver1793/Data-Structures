package test;

import project5.BST;
import project5.FallenStars;

import org.junit.After;
import org.junit.Before;
import project5.Meteorite;
import project5.MeteoriteData;
import project5.Location;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
//import project5.MeteoriteData;
//import project5.Meteorite;

//For eclipse
//right-click project folder
//properties
//java build path
//add library
//junit
//junit4
//apply and close

public class Testing {
	 private static final String INPUT_FILE = "src/main/resources/Meteorite_Landings.csv";

	    /**
	     * Test to make sure that the input file exists.
	     */
	    @Test
	    public void testInputFileValidity() {
	        File input = new File(INPUT_FILE);
	        assertTrue(input.exists());
	    }

	    private InputStream systemIn;
	    private ByteArrayInputStream testIn;

	    /**
	     * Before each test, sets systemIn to the value of System.in to preserve it.
	     */
	    @Before
	    public void preserveInput() {
	        systemIn = System.in;
	    }

	    /**
	     * After each test, restores System.in() to its former value.
	     */
	    @After
	    public void restoreInput() {
	        testIn = null;
	        System.setIn(systemIn);
	    }

	    /**
	     * Helper method to provide the command line input for a given test.
	     * @param data The input that the program should process (as a String)
	     */
	    private void provideInput(String data) {
	        testIn = new ByteArrayInputStream(data.getBytes());
	        System.setIn(testIn);
	    }

	    /**
	     * Run that does not specify the input file as a command-line argument.
	     *
	     * Expected: Program should print an error to an error stream and terminate.
	     */
	    @Test
	    public void run1() {
	        FallenStars.main(new String[]{});
	    }

	    /**
	     * Run that specifies an input file, but that file does not exist.
	     *
	     * Expected: Program should print an error to an error stream and terminate.
	     */
	    @Test
	    public void run2() {
	        FallenStars.main(new String[]{"random_directory/nonexistent_file.csv"});
	    }

	    /**
	     * Run where the command line argument is valid but an invalid query is sent.
	     *
	     * Provided input: 'name Dhofar', 'quit'
	     * Expected: Program should print an error for invalid query and continue to run.
	     */
	    @Test
	    public void run3() {
	        provideInput("name Dhofar\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument is valid but an invalid "location" query is sent.
	     *
	     * Provided input: 'location 90.1 180.1', 'quit'
	     * Expected: Program should print an error for invalid query and continue to run.
	     */
	    @Test
	    public void run4() {
	        provideInput("location 90.1 180.1\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * A valid "location" query is sent, and there is a result.
	     *
	     * Provided input: 'location 33.45 -111.51667', 'quit'
	     * Expected: The program should print results for the query, then terminate.
	     */
	    @Test
	    public void run5() {
	        try {
	            provideInput("location 3.45 -11.567\nquit");
	            FallenStars.main(new String[]{INPUT_FILE});
	        } catch (StackOverflowError e) {
	            System.err.println("This failure means you got a StackOverflowError.\nThis happened on my machine when I ran all the tests at once.\nRun this test individually, and see if the error persists.");
	        }
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * A valid "year" query is sent, and there are results.
	     *
	     * Provided input: 'year 1920', 'quit'
	     * Expected: The program should print results for the query, then terminate.
	     */
	    @Test
	    public void run6() {
	        provideInput("year 1920\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * A valid "mass" query is sent, and there are results.
	     *
	     * Provided input: 'mass 7000', 'quit'
	     * Expected: The program should print results for the query, then terminate.
	     */
	    @Test
	    public void run7() {
	        provideInput("mass 7000\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * A valid "mass" query is sent, but there are no results.
	     *
	     * Provided input: 'mass 12345', 'quit'
	     * Expected: Program should print a message for no matches found and continue to run.
	     */
	    @Test
	    public void run8() {
	        provideInput("mass 12345\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * A valid "year" query is sent, but there are no results.
	     *
	     * Provided input: 'year 2021', 'quit'
	     * Expected: Expected: Program should print a message for no matches found and continue to run.
	     */
	    @Test
	    public void run9() {
	        provideInput("year 2021\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * Multiple queries are entered. The first two are valid and should produce results, the last is invalid.
	     *
	     * Provided input: 'location 0.0 0.0', 'mass 5', 'mast 25000', 'quit'
	     * Expected: Program should produce results for the first two queries and an error for the last, then terminate when 'quit' is entered.
	     */
	    @Test
	    public void run10() {
	        provideInput("location 0.0 0.0\nmass 5\nmast 25000\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	    /**
	     * Run where the command line argument specified and valid.
	     * Multiple queries are entered. All three queries are valid and should produce results.
	     *
	     * Provided input: 'location 0.0 37.66667', 'year 2003', 'mass 6580', 'quit'
	     * Expected: Program should produce results for all three queries and then terminate when 'quit' is entered.
	     */
	    @Test
	    public void run11() {
	        provideInput("location 0.0 37.66667\nyear 2003\nmass 6580\nquit");
	        FallenStars.main(new String[]{INPUT_FILE});
	    }

	// Test cases for equals()

    @Test
    public void equalsNull2() {
        assertFalse(TestObjects.TEST_METEORITE_DATA.equals(null));
    }

    @Test
    public void equalsOtherTypeString() {
        assertFalse(TestObjects.TEST_METEORITE_DATA.equals("Test"));
    }

    @Test
    public void equalsOtherTypeBST() {
        assertFalse(TestObjects.TEST_METEORITE_DATA.equals(TestObjects.TEST_METEORITE_BST_FULL));
    }

    @Test
    public void equalsSelf2() {
        assertTrue(TestObjects.TEST_METEORITE_DATA.equals(TestObjects.TEST_METEORITE_DATA));
    }

    @Test
    public void equalsEquivalent2() {
        MeteoriteData equivalent = new MeteoriteData() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};

        assertTrue(TestObjects.TEST_METEORITE_DATA.equals(equivalent));
    }

    @Test
    public void equalsEquivalentAfterSameRemove() {
        MeteoriteData e1 = new MeteoriteData() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};

        MeteoriteData e2 = new MeteoriteData() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};

        e1.remove(TestObjects.METEORITE_1);
        e1.remove(TestObjects.METEORITE_5);
        e1.remove(TestObjects.METEORITE_10);
        e1.remove(TestObjects.METEORITE_15);

        e2.remove(TestObjects.METEORITE_1);
        e2.remove(TestObjects.METEORITE_10);
        e2.remove(TestObjects.METEORITE_5);
        e2.remove(TestObjects.METEORITE_15);

        assertTrue(e1.equals(e2));
    }

    @Test
    public void equalsEquivalentAfterDifferentRemove() {
        MeteoriteData e1 = new MeteoriteData() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};

        MeteoriteData e2 = new MeteoriteData() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};

        e1.remove(TestObjects.METEORITE_1);
        e1.remove(TestObjects.METEORITE_5);
        e1.remove(TestObjects.METEORITE_10);
        e1.remove(TestObjects.METEORITE_15);

        e2.remove(TestObjects.METEORITE_1);
        e2.remove(TestObjects.METEORITE_10);
        e2.remove(TestObjects.METEORITE_5);
        e2.remove(TestObjects.METEORITE_16);

        assertFalse(e1.equals(e2));
    }

    // Test cases for iterator()

    @Test
    public void iteratorEmptyBST2() {
        Iterator<Meteorite> i = new MeteoriteData().iterator();
        assertFalse(i.hasNext());
    }

    @Test
    public void iteratorAfterRemove() {
        MeteoriteData e = new MeteoriteData() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};

        e.remove(TestObjects.METEORITE_5);
        e.remove(TestObjects.METEORITE_10);
        e.remove(TestObjects.METEORITE_15);

        Iterator<Meteorite> i = e.iterator();
        assertEquals(TestObjects.METEORITE_7, i.next());
        assertEquals(TestObjects.METEORITE_14, i.next());
        assertEquals(TestObjects.METEORITE_6, i.next());
        assertEquals(TestObjects.METEORITE_3, i.next());
        assertEquals(TestObjects.METEORITE_12, i.next());
        assertEquals(TestObjects.METEORITE_8, i.next());
        assertEquals(TestObjects.METEORITE_18, i.next());
        assertEquals(TestObjects.METEORITE_16, i.next());
        assertEquals(TestObjects.METEORITE_2, i.next());
        assertEquals(TestObjects.METEORITE_4, i.next());
        assertEquals(TestObjects.METEORITE_17, i.next());
        assertEquals(TestObjects.METEORITE_9, i.next());
        assertEquals(TestObjects.METEORITE_13, i.next());
        assertEquals(TestObjects.METEORITE_11, i.next());
    }

    @Test
    public void iteratorWithRemove() {
        Iterator<Meteorite> i = TestObjects.TEST_METEORITE_DATA.iterator();
        assertEquals(TestObjects.METEORITE_15, i.next());
        assertEquals(TestObjects.METEORITE_7, i.next());
        assertEquals(TestObjects.METEORITE_5, i.next());
        assertEquals(TestObjects.METEORITE_14, i.next());
        assertEquals(TestObjects.METEORITE_6, i.next());
        assertEquals(TestObjects.METEORITE_3, i.next());
        assertEquals(TestObjects.METEORITE_12, i.next());
        assertEquals(TestObjects.METEORITE_8, i.next());
        assertEquals(TestObjects.METEORITE_18, i.next());
        assertEquals(TestObjects.METEORITE_16, i.next());
        assertEquals(TestObjects.METEORITE_2, i.next());
        assertEquals(TestObjects.METEORITE_1, i.next());
        assertEquals(TestObjects.METEORITE_10, i.next());
        assertEquals(TestObjects.METEORITE_4, i.next());
        assertEquals(TestObjects.METEORITE_17, i.next());
        assertEquals(TestObjects.METEORITE_9, i.next());
        assertEquals(TestObjects.METEORITE_13, i.next());
        assertEquals(TestObjects.METEORITE_11, i.next());
    }
	
	
	@Test(expected = IllegalArgumentException.class)
    public void getByYearNegativeOne() {
		TestObjects.TEST_METEORITE_DATA.getByYear(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByYearIntegerMin() {
    	TestObjects.TEST_METEORITE_DATA.getByYear(Integer.MIN_VALUE);
    }

    /*
     * I thought about putting a test here that tests for year zero, but it would immediately
     * fail because Meteorites cannot have a year <= 0. Is there a good way to test for year = 0?
     */

    @Test
    public void getByYearValidButNoResults() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByYear(1766).iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void getByYearArbitrary1() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByYear(1995).iterator();

        assertEquals(TestObjects.METEORITE_17, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void getByYearArbitrary2() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByYear(2002).iterator();

        assertEquals(TestObjects.METEORITE_2, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void getByYearArbitrary3() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByYear(1866).iterator();

        assertEquals(TestObjects.METEORITE_12, it.next());
        assertEquals(TestObjects.METEORITE_18, it.next());
        assertEquals(TestObjects.METEORITE_11, it.next());

        assertFalse(it.hasNext());
    }
    @Test(expected = IllegalArgumentException.class)
    public void getByMassBothParamsInvalid() {
    	TestObjects.TEST_METEORITE_DATA.getByMass(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByMassMassInvalid() {
    	TestObjects.TEST_METEORITE_DATA.getByMass(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByMassDeltaInvalid() {
    	TestObjects.TEST_METEORITE_DATA.getByMass(2, -1);
    }

    @Test
    public void getByMassEmptyCollection() {
        assertNull(new MeteoriteData().getByMass(50, 10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByMassEmptyCollectionBothParamsInvalid() {
        new MeteoriteData().getByMass(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByMassEmptyCollectionMassInvalid() {
        new MeteoriteData().getByMass(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByMassEmptyCollectionDeltaInvalid() {
        new MeteoriteData().getByMass(2, -1);
    }

    @Test
    public void getByMassValidButNoResults() {
        assertNull(TestObjects.TEST_METEORITE_DATA.getByMass(1294, 357));
    }

    @Test
    public void getByMassArbitrary1() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByMass(2374, 335).iterator();

        assertEquals(TestObjects.METEORITE_16, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void getByMassArbitrary2() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByMass(916, 831).iterator();

        assertEquals(TestObjects.METEORITE_1, it.next());  // Lorem                   1 1975    234   76.60000  -81.90000
        assertEquals(TestObjects.METEORITE_12, it.next()); // Dolor                  30 1875    645   49.40000   90.20000
        assertEquals(TestObjects.METEORITE_11, it.next()); // Volutpat                2 1866    654   55.00000  112.00000

        assertFalse(it.hasNext());
    }

    @Test
    public void getByMassArbitrary3() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByMass(4092, 616).iterator();

        assertEquals(TestObjects.METEORITE_5, it.next());  // Amet                    5 2019   3555   23.70000  157.30000
        assertEquals(TestObjects.METEORITE_3, it.next());  // Dolor                   3 1860   3911  -80.20000 -131.90000

        assertFalse(it.hasNext());
    }

    @Test
    public void getByMassArbitrary4() {
        Iterator<Meteorite> it = TestObjects.TEST_METEORITE_DATA.getByMass(7088, 855).iterator();

        assertEquals(TestObjects.METEORITE_13, it.next()); // Turpis                  4 1922   6308  -77.50000  -77.00000
        assertEquals(TestObjects.METEORITE_8, it.next());  // Elit                    8 1974   7333  -90.00000  -83.50000
        assertEquals(TestObjects.METEORITE_10, it.next()); // Lorem                  10 1901   7333   26.40000  137.50000
        assertEquals(TestObjects.METEORITE_14, it.next()); // Amet                   50 1855   7353   76.80000   57.40000

        assertFalse(it.hasNext());
    }
	
	public static Location LOCATION_0 = new Location(0, 0);
    public static Location LOCATION_1 = new Location(-81.4, 123.7);
    public static Location LOCATION_2 = new Location(-29.0, -120.5);
    public static Location LOCATION_3 = new Location(58.0, 1.2);

    @Test(expected = IllegalArgumentException.class)
    public void getByLocationNullLoc() {
    	TestObjects.TEST_METEORITE_DATA.getByLocation(null);
    }

    @Test
    public void getByLocationEmptyData() {
        assertNull(new MeteoriteData().getByLocation(LOCATION_0)); 
    }

    @Test
    public void getByLocationArbitrary0() {
        assertEquals(TestObjects.METEORITE_6, TestObjects.TEST_METEORITE_DATA.getByLocation(LOCATION_0));
    }

    @Test
    public void getByLocationArbitrary1() {
        assertEquals(TestObjects.METEORITE_8, TestObjects.TEST_METEORITE_DATA.getByLocation(LOCATION_1));
    }

    @Test
    public void getByLocationArbitrary2() {
        assertEquals(TestObjects.METEORITE_2, TestObjects.TEST_METEORITE_DATA.getByLocation(LOCATION_2));
    }

    @Test
    public void getByLocationArbitrary3() {
        assertEquals(TestObjects.METEORITE_7, TestObjects.TEST_METEORITE_DATA.getByLocation(LOCATION_3));
    }

	
	@Test(expected = IllegalArgumentException.class)
    public void constructorInvalidName() {
        new Meteorite("", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidIDZero() {
        new Meteorite("Name", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidIDNegativeOne() {
        new Meteorite("Name", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidIDIntMin() {
        new Meteorite("Name", Integer.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMassInvalidZero() {
        Meteorite m = new Meteorite("Name", 1);
        m.setMass(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMassInvalidNegativeOne() {
        Meteorite m = new Meteorite("Name", 1);
        m.setMass(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMassInvalidIntMin() {
        Meteorite m = new Meteorite("Name", 1);
        m.setMass(Integer.MIN_VALUE);
    }

    @Test
    public void setGetMassValid() {
        Meteorite m = new Meteorite("Name", 1);
        m.setMass(10);
        assertEquals(10, m.getMass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setYearInvalidZero() {
        Meteorite m = new Meteorite("Name", 1);
        m.setYear(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setYearInvalidNegativeOne() {
        Meteorite m = new Meteorite("Name", 1);
        m.setYear(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setYearInvalidGreaterThanCurrent() {
        Meteorite m = new Meteorite("Name", 1);
        m.setYear(2021);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setYearInvalidValidCurrent() {
        Meteorite m = new Meteorite("Name", 1);
        m.setYear(2020);
    }

    @Test // Test that no exceptions are thrown.
    public void setYearValidLastYear() {
        Meteorite m = new Meteorite("Name", 1);
        m.setYear(2019);
    }

    @Test
    public void setGetYearValid() {
        Meteorite m = new Meteorite("Name", 1);
        m.setYear(1995);
        assertEquals(1995, m.getYear());
    }

    @Test // Testing that an exception is NOT thrown.
    public void setLocationNull() {
        Meteorite m = new Meteorite("Name", 1);
        m.setLocation(null);
    }

    @Test // Only one location test because locations are input-tested on creation.
    public void setGetLocation() {
        Meteorite m = new Meteorite("Name", 1);
        Location l = new Location(-87.6, 37.2);
        m.setLocation(l);
        assertEquals(l, m.getLocation());
    }
	
    @Test
    public void equalsNull1() {
        Meteorite m = new Meteorite("aaaaa", 1);
        assertFalse(m.equals(null));
    }

    @Test
    public void equalsOtherType() {
        Meteorite m = new Meteorite("aaaaa", 1);
        Location l = new Location(0, 0);
        assertFalse(m.equals(l));
    }

    @Test
    public void equalsSelf1() {
        Meteorite m = new Meteorite("aaaaa", 1);
        assertTrue(m.equals(m));
    }

    @Test
    public void equalsEquivalent1() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aaaaa", 1);
        assertTrue(m1.equals(m2));
    }

    @Test
    public void equalsOtherNameDifferent() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aabbb", 1);
        assertFalse(m1.equals(m2));
    }

    @Test
    public void equalsOtherIDDifferent() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aaaaa", 2);
        assertFalse(m1.equals(m2));
    }

    @Test
    public void equalsOtherNameAndIDDifferent() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aabbb", 2);
        assertFalse(m1.equals(m2));
    }

    // compareTo()

    @Test
    public void compareToSelf() {
        Meteorite m = new Meteorite("aaaaa", 1);
        assertEquals(0, m.compareTo(m));
    }

    @Test
    public void compareToEquivalent() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aaaaa", 1);
        assertEquals(0, m1.compareTo(m2));
    }

    @Test
    public void compareToNameDifferentIDSame() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aabbb", 1);
        assertTrue(m1.compareTo(m2) < 0);
    }

    @Test
    public void compareToNameSameIDDifferent() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aaaaa", 2);
        assertTrue(m1.compareTo(m2) < 0);
    }

    @Test
    public void compareToNameLessIDGreater() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aabbb", 2);
        assertTrue(m1.compareTo(m2) < 0);
    }

    @Test
    public void compareToNameGreaterIDLess() {
        Meteorite m1 = new Meteorite("aabbb", 1);
        Meteorite m2 = new Meteorite("aaaaa", 2);
        assertTrue(m1.compareTo(m2) > 0);
    }

    @Test
    public void compareToNameAndIDGreater() {
        Meteorite m1 = new Meteorite("aaaaa", 1);
        Meteorite m2 = new Meteorite("aabbb", 2);
        assertTrue(m1.compareTo(m2) < 0);
    }

    // toString()

    @Test
    public void toStringBaseOnly() {
        Meteorite m = new Meteorite("aaaaa", 1);
        assertEquals("aaaaa                   1                                  ", m.toString());
    }

    @Test
    public void toStringBaseMass() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setMass(10);
        assertEquals("aaaaa                   1          10                      ", m.toString());
    }

    @Test
    public void toStringBaseYear() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setYear(1996);
        assertEquals("aaaaa                   1 1996                             ", m.toString());
    }

    @Test
    public void toStringBaseLocation() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setLocation(new Location(-87.6, 37.2));
        assertEquals("aaaaa                   1              -87.60000   37.20000", m.toString());
    }

    @Test
    public void toStringMassYear() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setMass(10);
        m.setYear(1996);
        System.out.println(m.toString());
        assertEquals("aaaaa                   1 1996     10                      ", m.toString());
    }

    @Test
    public void toStringMassLocation() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setMass(10);
        m.setLocation(new Location(-87.6, 37.2));
        System.out.println(m.toString());
        assertEquals("aaaaa                   1          10  -87.60000   37.20000", m.toString());
    }

    @Test
    public void toStringYearLocation() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setYear(1996);
        m.setLocation(new Location(-87.6, 37.2));
        System.out.println(m.toString());
        assertEquals("aaaaa                   1 1996         -87.60000   37.20000", m.toString());
    }

    @Test
    public void toStringFull() {
        Meteorite m = new Meteorite("aaaaa", 1);
        m.setMass(10);
        m.setYear(1996);
        m.setLocation(new Location(-87.6, 37.2));
        System.out.println(m.toString());
        assertEquals("aaaaa                   1 1996     10  -87.60000   37.20000", m.toString());
    }
    
	@Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLatJustOver1() {
        new Location(-90.1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLatJustOver2() {
        new Location(90.1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLatDoubleMin() {
        new Location(-Double.MAX_VALUE, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLatDoubleMax() {
        new Location(Double.MAX_VALUE, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLongJustOver1() {
        new Location(0, -180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLongJustOver2() {
        new Location(0, 180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLongDoubleMin() {
        new Location(0, -Double.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalLongDoubleMax() {
        new Location(0, Double.MAX_VALUE);
    }

    @Test // Tests that this does NOT throw an exception.
    public void constructorLegal1() {
        new Location(0, 0);
    }

    @Test // Tests that this does NOT throw an exception.
    public void constructorLegal2() {
        new Location(-90, -180);
    }

    @Test // Tests that this does NOT throw an exception.
    public void constructorLegal3() {
        new Location(90, 180);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDistanceNull() {
        new Location(0, 0).getDistance(null);
    }

    @Test
    public void getDistanceValid1() {
        Location l1 = new Location(0, 0);
        Location l2 = new Location(23.0, -25.0);

        assertEquals(3720.7004167992345, l1.getDistance(l2), 0.0000000001);
    }

    @Test
    public void getDistanceValid2() {
        Location l1 = new Location(-87.6, 37.2);
        Location l2 = new Location(23.0, -25.0);

        assertEquals(12438.750276842267, l1.getDistance(l2), 0.0000000001);
    }

    @Test
    @SuppressWarnings("all")
    public void equalsSelf() {
        Location l = new Location(-87.6, 37.2);
        assertTrue(l.equals(l));
    }

    @Test
    @SuppressWarnings("all")
    public void equalsNull() {
        Location l = new Location(-87.6, 37.2);
        assertFalse(l.equals(null));
    }

    @Test
    @SuppressWarnings("all")
    public void equalsEquivalent() {
        Location l1 = new Location(-87.6, 37.2);
        Location l2 = new Location(-87.6, 37.2);
        assertTrue(l1.equals(l2));
    }

    @Test
    @SuppressWarnings("all")
    public void equalsOther() {
        Location l1 = new Location(-87.6, 37.2);
        Location l2 = new Location(23.0, -25.0);
        assertFalse(l1.equals(l2));
    }

    @Test
    @SuppressWarnings("all")
    public void equalsOtherSimilarTrue() {
        Location l1 = new Location(-87.6, 37.2);
        Location l2 = new Location(-87.600001, 37.200001);
        assertTrue(l1.equals(l2));
    }

    @Test
    @SuppressWarnings("all")
    public void equalsOtherSimilarFalse() {
        Location l1 = new Location(-87.6, 37.2);
        Location l2 = new Location(-87.60001, 37.20001);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void getLatLong1() {
        Location l = new Location(-87.6, 37.2);
        assertEquals(-87.6, l.getLatitude(), 0.0);
        assertEquals(37.2, l.getLongitude(), 0.0);
    }

    @Test
    public void getLatLong2() {
        Location l = new Location(23.0, -25.0);
        assertEquals(23.0, l.getLatitude(), 0.0);
        assertEquals(-25.0, l.getLongitude(), 0.0);
    }

	@Test
    public void isEmptyTrueInteger() {
        BST<Integer> bst = new BST<>();
        assertTrue(bst.isEmpty());
    }

    @Test
    public void isEmptyTrueMeteorite() {
        BST<Meteorite> bst = new BST<>();
        assertTrue(bst.isEmpty());
    }

    @Test
    public void isEmptyFalseInteger() {
        assertFalse(TestObjects.INTEGER_BST.isEmpty());
    }

    @Test
    public void isEmptyFalseMeteorite() {
        assertFalse(TestObjects.TEST_METEORITE_BST_FULL.isEmpty());
    }

    // Test cases for iterator implementation.

    @Test
    public void iteratorEmptyBST() {
        Iterator<Meteorite> i = new BST<Meteorite>().iterator();
        assertFalse(i.hasNext());
    }

    @Test
    public void iteratorIntegerBST() {
        Iterator<Integer> it = TestObjects.INTEGER_BST.iterator();
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, (int)it.next());
        }
    }

    @Test
    public void iteratorMeteoriteBST() {
        Iterator<Meteorite> i = TestObjects.TEST_METEORITE_BST_FULL.iterator();
        assertEquals(TestObjects.METEORITE_15, i.next());
        assertEquals(TestObjects.METEORITE_7, i.next());
        assertEquals(TestObjects.METEORITE_5, i.next());
        assertEquals(TestObjects.METEORITE_14, i.next());
        assertEquals(TestObjects.METEORITE_6, i.next());
        assertEquals(TestObjects.METEORITE_3, i.next());
        assertEquals(TestObjects.METEORITE_12, i.next());
        assertEquals(TestObjects.METEORITE_8, i.next());
        assertEquals(TestObjects.METEORITE_18, i.next());
        assertEquals(TestObjects.METEORITE_16, i.next());
        assertEquals(TestObjects.METEORITE_2, i.next());
        assertEquals(TestObjects.METEORITE_1, i.next());
        assertEquals(TestObjects.METEORITE_10, i.next());
        assertEquals(TestObjects.METEORITE_4, i.next());
        assertEquals(TestObjects.METEORITE_17, i.next());
        assertEquals(TestObjects.METEORITE_9, i.next());
        assertEquals(TestObjects.METEORITE_13, i.next());
        assertEquals(TestObjects.METEORITE_11, i.next());
    }

    // Test toString() method.

    @Test
    public void toStringEmptyInteger() {
        assertEquals("[]", new BST<Integer>().toString());
    }

    @Test
    public void toStringEmptyMeteorite() {
        assertEquals("[]", new BST<Meteorite>().toString());
    }

    @Test
    public void toStringInteger() {
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", TestObjects.INTEGER_BST.toString());
    }

    @Test
    public void toStringMeteorite() {
        assertEquals(
                "[Adipiscing              7 2005  94168   73.60000   20.40000, " +
                        "Amet                    5 2019   3555   23.70000  157.30000, " +
                        "Consectetur             6 2016   7947  -32.70000   33.50000, " +
                        "Dolor                   3 1860   3911  -80.20000 -131.90000, " +
                        "Elit                    8 1974   7333  -90.00000  -83.50000, " +
                        "Ipsum                   2 2002   5325  -31.00000  -70.80000, " +
                        "Lorem                   1 1975    234   76.60000  -81.90000, " +
                        "Sit                     4 1776   1963   -8.30000 -166.40000, " +
                        "Suspendisse             9 1969  55555   90.00000  -32.10000]",

                        TestObjects.TEST_METEORITE_BST_HALF.toString()
        );
    }

    // Test toArray()

    @Test
    public void toArrayEmptyInteger() {
        Object[] arr = new BST<Integer>().toArray();
        assertEquals(0, arr.length);
    }

    @Test
    public void toArrayEmptyMeteorite() {
        Object[] arr = new BST<Meteorite>().toArray();
        assertEquals(0, arr.length);
    }

    @Test
    public void toArrayInteger() {
        Object[] arr = TestObjects.INTEGER_BST.toArray();
        for (int i = 0; i < 10; i++) {
            assertEquals(i+1, (int)arr[i]);
        }
    }

    @Test
    public void toArrayMeteorite() {
        Object[] arr = TestObjects.TEST_METEORITE_BST_FULL.toArray();
        assertEquals(TestObjects.METEORITE_15, arr[0]);
        assertEquals(TestObjects.METEORITE_7, arr[1]);
        assertEquals(TestObjects.METEORITE_5, arr[2]);
        assertEquals(TestObjects.METEORITE_14, arr[3]);
        assertEquals(TestObjects.METEORITE_6, arr[4]);
        assertEquals(TestObjects.METEORITE_3, arr[5]);
        assertEquals(TestObjects.METEORITE_12, arr[6]);
        assertEquals(TestObjects.METEORITE_8, arr[7]);
        assertEquals(TestObjects.METEORITE_18, arr[8]);
        assertEquals(TestObjects.METEORITE_16, arr[9]);
        assertEquals(TestObjects.METEORITE_2, arr[10]);
        assertEquals(TestObjects.METEORITE_1, arr[11]);
        assertEquals(TestObjects.METEORITE_10, arr[12]);
        assertEquals(TestObjects.METEORITE_4, arr[13]);
        assertEquals(TestObjects.METEORITE_17, arr[14]);
        assertEquals(TestObjects.METEORITE_9, arr[15]);
        assertEquals(TestObjects.METEORITE_13, arr[16]);
        assertEquals(TestObjects.METEORITE_11, arr[17]);
    }

	// Test cases for first()
	@Test(expected = NullPointerException.class)
    public void testRangeNullPointerInteger1() {
		TestObjects.INTEGER_BST.getRange(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testRangeNullPointerInteger2() {
    	TestObjects.INTEGER_BST.getRange(1, null);
    }

    @Test(expected = NullPointerException.class)
    public void testRangeNullPointerMeteorite1() {
    	TestObjects.TEST_METEORITE_BST_FULL.getRange(null, TestObjects.METEORITE_11);
    }

    @Test(expected = NullPointerException.class)
    public void testRangeNullPointerMeteorite2() {
    	TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_15, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeIllegalArgumentInteger() {
    	TestObjects.INTEGER_BST.getRange(10, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeIllegalArgumentMeteorite() {
    	TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_11,TestObjects.METEORITE_15);
    }

    @Test
    public void testRangeEmptyInteger() {
        BST<Integer> bst = new BST<>();
        assertEquals(0, bst.getRange(1, 10).size());
    }

    @Test
    public void testRangeEmptyMeteorite() {
        BST<Meteorite> bst = new BST<>();
        assertEquals(0, bst.getRange(TestObjects.METEORITE_15, TestObjects.METEORITE_11).size());
    }

    @Test
    public void testRangeIntegerAll() {
        ArrayList<Integer> al = TestObjects.INTEGER_BST.getRange(1, 10);
        for (int i = 0; i < 10; i++) {
            assertEquals(i+1, (int)al.get(i));
        }
    }

    @Test
    public void testRangeIntegerHalf1() {
        int min = 1;
        int max = 5;

        ArrayList<Integer> al = TestObjects.INTEGER_BST.getRange(min, max);
        for (int i = min; i <= max; i++) {
            assertEquals(i, (int)al.get(i-min));
        }

        assertEquals(max-min+1, al.size());
    }

    @Test
    public void testRangeIntegerHalf2() {
        int min = 6;
        int max = 10;

        ArrayList<Integer> al = TestObjects.INTEGER_BST.getRange(min, max);
        for (int i = min; i <= max; i++) {
            assertEquals(i, (int)al.get(i-min));
        }

        assertEquals(max-min+1, al.size());
    }

    @Test
    public void testRangeIntegerArbitrary1() {
        int min = 4;
        int max = 8;

        ArrayList<Integer> al = TestObjects.INTEGER_BST.getRange(min, max);
        for (int i = min; i <= max; i++) {
            assertEquals(i, (int)al.get(i-min));
        }

        assertEquals(max-min+1, al.size());
    }

    @Test
    public void testRangeIntegerArbitrary2() {
        int min = 2;
        int max = 7;

        ArrayList<Integer> al = TestObjects.INTEGER_BST.getRange(min, max);
        for (int i = min; i <= max; i++) {
            assertEquals(i, (int)al.get(i-min));
        }

        assertEquals(max-min+1, al.size());
    }

    @Test
    public void testRangeMeteoriteAll() {
        ArrayList<Meteorite> al = TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_15, TestObjects.METEORITE_11);
        assertEquals(TestObjects.METEORITE_15, al.get(0));
        assertEquals(TestObjects.METEORITE_7, al.get(1));
        assertEquals(TestObjects.METEORITE_5, al.get(2));
        assertEquals(TestObjects.METEORITE_14, al.get(3));
        assertEquals(TestObjects.METEORITE_6, al.get(4));
        assertEquals(TestObjects.METEORITE_3, al.get(5));
        assertEquals(TestObjects.METEORITE_12, al.get(6));
        assertEquals(TestObjects.METEORITE_8, al.get(7));
        assertEquals(TestObjects.METEORITE_18, al.get(8));
        assertEquals(TestObjects.METEORITE_16, al.get(9));
        assertEquals(TestObjects.METEORITE_2, al.get(10));
        assertEquals(TestObjects.METEORITE_1, al.get(11));
        assertEquals(TestObjects.METEORITE_10, al.get(12));
        assertEquals(TestObjects.METEORITE_4, al.get(13));
        assertEquals(TestObjects.METEORITE_17, al.get(14));
        assertEquals(TestObjects.METEORITE_9, al.get(15));
        assertEquals(TestObjects.METEORITE_13, al.get(16));
        assertEquals(TestObjects.METEORITE_11, al.get(17));

        assertEquals(18, al.size());
    }

    @Test
    public void testRangeMeteoriteHalf1() {
        ArrayList<Meteorite> al = TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_15, TestObjects.METEORITE_18);
        assertEquals(TestObjects.METEORITE_15, al.get(0));
        assertEquals(TestObjects.METEORITE_7, al.get(1));
        assertEquals(TestObjects.METEORITE_5, al.get(2));
        assertEquals(TestObjects.METEORITE_14, al.get(3));
        assertEquals(TestObjects.METEORITE_6, al.get(4));
        assertEquals(TestObjects.METEORITE_3, al.get(5));
        assertEquals(TestObjects.METEORITE_12, al.get(6));
        assertEquals(TestObjects.METEORITE_8, al.get(7));
        assertEquals(TestObjects.METEORITE_18, al.get(8));

        assertEquals(9, al.size());
    }

    @Test
    public void testRangeMeteoriteHalf2() {
        ArrayList<Meteorite> al = TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_16, TestObjects.METEORITE_11);
        assertEquals(TestObjects.METEORITE_16, al.get(0));
        assertEquals(TestObjects.METEORITE_2, al.get(1));
        assertEquals(TestObjects.METEORITE_1, al.get(2));
        assertEquals(TestObjects.METEORITE_10, al.get(3));
        assertEquals(TestObjects.METEORITE_4, al.get(4));
        assertEquals(TestObjects.METEORITE_17, al.get(5));
        assertEquals(TestObjects.METEORITE_9, al.get(6));
        assertEquals(TestObjects.METEORITE_13, al.get(7));
        assertEquals(TestObjects.METEORITE_11, al.get(8));

        assertEquals(9, al.size());
    }

    @Test
    public void testRangeMeteoriteArbitrary1() {
        ArrayList<Meteorite> al = TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_12, TestObjects.METEORITE_10);
        assertEquals(TestObjects.METEORITE_12, al.get(0));
        assertEquals(TestObjects.METEORITE_8, al.get(1));
        assertEquals(TestObjects.METEORITE_18, al.get(2));
        assertEquals(TestObjects.METEORITE_16, al.get(3));
        assertEquals(TestObjects.METEORITE_2, al.get(4));
        assertEquals(TestObjects.METEORITE_1, al.get(5));
        assertEquals(TestObjects.METEORITE_10, al.get(6));

        assertEquals(7, al.size());
    }

    @Test
    public void testRangeMeteoriteArbitrary2() {
        ArrayList<Meteorite> al = TestObjects.TEST_METEORITE_BST_FULL.getRange(TestObjects.METEORITE_5, TestObjects.METEORITE_8);
        assertEquals(TestObjects.METEORITE_5, al.get(0));
        assertEquals(TestObjects.METEORITE_14, al.get(1));
        assertEquals(TestObjects.METEORITE_6, al.get(2));
        assertEquals(TestObjects.METEORITE_3, al.get(3));
        assertEquals(TestObjects.METEORITE_12, al.get(4));
        assertEquals(TestObjects.METEORITE_8, al.get(5));

        assertEquals(6, al.size());
    }
    @Test(expected = NoSuchElementException.class)
    public void firstIntegerEmpty() {
        BST<Integer> bst = new BST<>();
        bst.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void firstMeteoriteEmpty() {
        BST<Meteorite> bst = new BST<>();
        bst.first();
    }

    @Test
    public void firstIntegerValid() {
        assertEquals(1, (int)TestObjects.INTEGER_BST.first());
    }

    @Test
    public void firstMeteoriteValid() {
        assertEquals(TestObjects.METEORITE_15, TestObjects.TEST_METEORITE_BST_FULL.first());
    }

    // Test cases for last()

    @Test(expected = NoSuchElementException.class)
    public void lastIntegerEmpty() {
        BST<Integer> bst = new BST<>();
        bst.last();
    }

    @Test(expected = NoSuchElementException.class)
    public void lastMeteoriteEmpty() {
        BST<Meteorite> bst = new BST<>();
        bst.last();
    }

    @Test
    public void lastIntegerValid() {
        assertEquals(10, (int)TestObjects.INTEGER_BST.last());
    }

    @Test
    public void lastMeteoriteValid() {
        assertEquals(TestObjects.METEORITE_11, TestObjects.TEST_METEORITE_BST_FULL.last());
    }

	@Test
    public void equalsNullMeteorite() {
        assertFalse(TestObjects.TEST_METEORITE_BST_FULL.equals(null));
    }

    @Test
    public void equalsNullInteger() {
        assertFalse(TestObjects.INTEGER_BST.equals(null));
    }

    @Test
    public void equalsOtherMeteorite() {
        assertFalse(TestObjects.TEST_METEORITE_BST_FULL.equals(TestObjects.INTEGER_BST));
    }

    @Test
    public void equalsOtherInteger() {
        assertFalse(TestObjects.INTEGER_BST.equals(TestObjects.TEST_METEORITE_BST_FULL));
    }

    @Test
    public void equalsSelfMeteorite() {
        assertTrue(TestObjects.TEST_METEORITE_BST_FULL.equals(TestObjects.TEST_METEORITE_BST_FULL));
    }

    @Test
    public void equalsSelfInteger() {
        assertTrue(TestObjects.INTEGER_BST.equals(TestObjects.INTEGER_BST));
    }

    @Test
    public void equalsOtherEquivalentMeteorite() {
        BST<Meteorite> equivalent = new BST<Meteorite>() {{
            add(TestObjects.METEORITE_1); add(TestObjects.METEORITE_2); add(TestObjects.METEORITE_3); add(TestObjects.METEORITE_4); add(TestObjects.METEORITE_5); add(TestObjects.METEORITE_6);
            add(TestObjects.METEORITE_7); add(TestObjects.METEORITE_8); add(TestObjects.METEORITE_9); add(TestObjects.METEORITE_10); add(TestObjects.METEORITE_11); add(TestObjects.METEORITE_12);
            add(TestObjects.METEORITE_13); add(TestObjects.METEORITE_14); add(TestObjects.METEORITE_15); add(TestObjects.METEORITE_16); add(TestObjects.METEORITE_17); add(TestObjects.METEORITE_18);
        }};
        assertTrue(TestObjects.TEST_METEORITE_BST_FULL.equals(equivalent));
    }

    @Test
    public void equalsOtherEquivalentInteger() {
        BST<Integer> equivalent = new BST<Integer>() {{
            add(5); add(3); add(2); add(4); add(1); add(10); add(8); add(6); add(7); add(9);
        }};
        assertTrue(TestObjects.INTEGER_BST.equals(equivalent));
    }

	 @Test
	    public void intContainsArbitrary() {
	        assertTrue(TestObjects.INTEGER_BST.contains(4));
	    }

	    @Test
	    public void intContainsBeginning() {
	        assertTrue(TestObjects.INTEGER_BST.contains(1));
	    }

	    @Test
	    public void intContainsEnd() {
	        assertTrue(TestObjects.INTEGER_BST.contains(10));
	    }

	    @Test(expected = NullPointerException.class)
	    public void intContainsNull() {
	        TestObjects.INTEGER_BST.contains(null);
	    }

	    @Test(expected = ClassCastException.class)
	    public void intContainsInvalidObjectString() {
	        TestObjects.INTEGER_BST.contains("Test");
	    }

	    @Test(expected = ClassCastException.class)
	    public void intContainsInvalidObjectOther() {
	        TestObjects.INTEGER_BST.contains(TestObjects.METEORITE_1);
	    }


	    @Test(expected = ClassCastException.class)
	    public void intContainsInvalidObjectSelf() {
	        TestObjects.INTEGER_BST.contains(TestObjects.INTEGER_BST);
	    }

	    @Test
	    public void intContainsFalseOneBelow() {
	        assertFalse(TestObjects.INTEGER_BST.contains(0));
	    }

	    @Test
	    public void intContainsFalseOneAbove() {
	        assertFalse(TestObjects.INTEGER_BST.contains(11));
	    }

	    @Test
	    public void intContainsFalseMinVal() {
	        assertFalse(TestObjects.INTEGER_BST.contains(Integer.MIN_VALUE));
	    }

	    @Test
	    public void intContainsFalseMaxVal() {
	        assertFalse(TestObjects.INTEGER_BST.contains(Integer.MAX_VALUE));
	    }

	    @Test
	    public void intContainsFalseArbitrary1() {
	        assertFalse(TestObjects.INTEGER_BST.contains(14579));
	    }

	    @Test
	    public void intContainsFalseArbitrary2() {
	        assertFalse(TestObjects.INTEGER_BST.contains(25246789));
	    }

	    // TESTS FOR METEORITE BST

	    @Test
	    public void metContainsArbitrary() {
	        assertTrue(TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.METEORITE_5));
	    }

	    @Test
	    public void metContainsBeginning() {
	        assertTrue(TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.METEORITE_1));
	    }

	    @Test
	    public void metContainsEnd() {
	        assertTrue(TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.METEORITE_9));
	    }

	    @Test(expected = NullPointerException.class)
	    public void metContainsNull() {
	        TestObjects.TEST_METEORITE_BST_HALF.contains(null);
	    }

	    @Test(expected = ClassCastException.class)
	    public void metContainsInvalidObjectString() {
	        TestObjects.TEST_METEORITE_BST_HALF.contains("Test");
	    }

	    @Test(expected = ClassCastException.class)
	    public void metContainsInvalidObjectOther() {
	        TestObjects.TEST_METEORITE_BST_HALF.contains(437850);
	    }


	    @Test(expected = ClassCastException.class)
	    public void metContainsInvalidObjectSelf() {
	        TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.TEST_METEORITE_BST_HALF);
	    }

	    @Test
	    public void metContainsFalseOneAbove() {
	        assertFalse(TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.METEORITE_10));
	    }

	    @Test
	    public void metContainsFalseArbitrary1() {
	        assertFalse(TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.METEORITE_14));
	    }

	    @Test
	    public void metContainsFalseArbitrary2() {
	        assertFalse(TestObjects.TEST_METEORITE_BST_HALF.contains(TestObjects.METEORITE_18));
	    }
	@Test
    public void BST_contains_nullInput() {
		
		try {
			BST<String> tree1 = new BST<>();
        	tree1.contains(null);
        	fail("A NullPointerException should have been thrown");
        }
        catch(NullPointerException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_nullInput1() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(11);
			tree1.add(15);
			tree1.add(2);
        	tree1.contains(null);
        	fail("A NullPointerException should have been thrown");
        }
        catch(NullPointerException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	
	/*@Test
    public void BST_contains_StringFromEmptyIntegerTree() {
		//I am assuming that this is correct. Not very sure about this test.
		//	Depends on what the professor wants.
		//Since the tree is empty, the values inside the tree are undetermined yet
		//	Since a string is part of <T extends Comparable<?>>, the string ("10") could be 
		//	compared to the empty tree and will definitely return false. However, a 
		//	ClassCastException will be thrown once something gets added to the tree.
		try {
			BST<Integer> tree1 = new BST<>();
        	assertEquals("Returned value should be false", tree1.contains("10"), false);
        	//tree1.add(1);
        	//tree1.contains("10");
        	//fail("A ClassCastException should have been thrown");
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }*/
	
	@Test
	public void BST_contains_notComparable() {
		//I am assuming that this is correct. Not very sure about this test.
		//	Depends on what the professor wants.
		//This test throws exception because arraylist is not <T extends Comparable<?>>
		try {
			BST<Integer> tree1 = new BST<>();
        	tree1.contains(new ArrayList<>());
        	fail("A ClassCastException should have been thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_notComparable1() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(1);
        	tree1.contains(new ArrayList<>());
        	fail("A ClassCastException should have been thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_notComparable2() {

		try {
			BST<String> tree1 = new BST<>();
			tree1.add("1");
        	tree1.contains(new ArrayList<>());
        	fail("A ClassCastException should have been thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_notComparable3() {

		try {
			BST<String> tree1 = new BST<>();
			tree1.add("1");
        	tree1.contains(new Object[1]);
        	fail("A ClassCastException should have been thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_notComparable4() {

		try {
			BST<String> tree1 = new BST<>();
			tree1.add("1");
			Object[] string = {"1"};
        	tree1.contains(string);
        	fail("A ClassCastException should have been thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_notComparable5() {

		try {
			BST<String> tree1 = new BST<>();
			tree1.add("1");
			String[] string = {"1"};
        	tree1.contains(string);
        	fail("A ClassCastException should have been thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid1() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(11);
			tree1.add(15);
			tree1.add(2);
        	assertEquals("Should be true", tree1.contains(10), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid2() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(10), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid3() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(2), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid4() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(5), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid5() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(7), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid6() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(11), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	
	@Test
    public void BST_contains_valid7() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(12), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	@Test
    public void BST_contains_valid8() {

		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(2);
			tree1.add(7);
			tree1.add(12);
			tree1.add(11);
			tree1.add(15);

        	assertEquals("Should be true", tree1.contains(15), true);
        }
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	
	@Test
    public void BST_contains_StringFromInteger() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(10);
			tree1.add(5);
			tree1.add(11);
			tree1.add(15);
			tree1.add(2);
        	tree1.contains("10");
        	fail("A ClassCastException should have be thrown");
        }
        catch(ClassCastException ex) {}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
    }
	
	
	@Test
    public void BST_isEmpty_emptyTree1() {
		
		try {
			BST<String> tree1 = new BST<>();
			assertEquals("Expected output should be true", tree1.isEmpty(), true);
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
    public void BST_isEmpty_emptyTree2() {
		
		try {
			BST<Integer> tree2 = new BST<>();
			assertEquals("Expected output should be true", tree2.isEmpty(), true);
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
    public void BST_isEmpty_Tree1() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("str");
			assertEquals("Expected output should be true", tree1.isEmpty(), false);
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
    public void BST_isEmpty_Tree2() {
		try {
			BST<Integer> tree2 = new BST<>();
			tree2.add(12);
			assertEquals("Expected output should be true", tree2.isEmpty(), false);
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
    public void BST_empty_itr_hasNext() {
		
		try {
			BST<String> tree1 = new BST<>();
	        Iterator<String> itr = tree1.iterator();
	        assertEquals("Empty tree iterator should not return true for hasNext()", 
	        		itr.hasNext(), false);
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
	public void BST_itr_next2() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        
	        Iterator<String> itr = tree1.iterator();
	        
	        assertEquals("Wrong iteration of 1 element", 
	        		itr.next().equals("salmon"), true);
	        
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_itr_next() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        tree1.add("flounder");
	        tree1.add("grouper");
	        tree1.add("cod");
	        tree1.add("carp");
	        tree1.add("tilapia");
	        tree1.add("catfish");
	        tree1.add("bluefish");
	        tree1.add("tuna");
	        tree1.add("yellowtail");
	        tree1.add("herring");
	        tree1.add("sturgeon");
	        Iterator<String> itr = tree1.iterator();
	        Object[] arr = {"bluefish", "carp", "catfish", "cod", 
	        		"flounder", "grouper", "herring", "salmon", 
	        		"sturgeon", "tilapia", "tuna", "yellowtail"};
	        for (int i = 0; i < 12; i++) {
	        	if (!itr.next().equals(arr[i]))
	        		fail("Wrong order of iteration");
	        }
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	
	@Test
    public void BST_itr_Tree2() {
		try {
			BST<Integer> tree2 = new BST<>();
			tree2.add(1);
			tree2.add(2);
			tree2.add(3);
			tree2.add(4);
			tree2.add(8);
			tree2.add(7);
			tree2.add(6);
			tree2.add(5);
			Iterator<Integer> itr = tree2.iterator();
			int[] arr = {1,2,3,4,5,6,7,8};
			for (int i = 0; i < 8; i++) {
				if (itr.next() != arr[i])
					fail("Wrong iteration order");
			}
			if (itr.hasNext())
				fail("There should not be any elements remaining");
			
        	if (tree2.isEmpty() == true)
        		fail("Itr should not eliminate the tree");
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	
	@Test
    public void BST_getRange_nullparam1() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.getRange(null, null);
			fail("A NullPointerException should have been thrown");
	    }
		catch (NullPointerException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_nullparam2() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.getRange(null, "12");
			fail("A NullPointerException should have been thrown");
	    }
		catch (NullPointerException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_nullparam3() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.getRange("12", null);
			fail("A NullPointerException should have been thrown");
	    }
		catch (NullPointerException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_emptyTree1() {
		
		try {
			BST<String> tree1 = new BST<>();
			ArrayList<String> str = tree1.getRange(" ", "zzzzzzzzzzzz");
			assertEquals("Empty tree should not produce an non-empty ArrayList", str.size(), 0);

	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_emptyTree2() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			ArrayList<Integer> str = tree1.getRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
			assertEquals("Empty tree should not produce an non-empty ArrayList", str.size(), 0);
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_invalidparam1() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.getRange(Integer.MAX_VALUE, Integer.MIN_VALUE);
			fail("IllegalArgumentException expected");
		}
		catch(IllegalArgumentException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_invalidparam2() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.getRange(0, -1);
			fail("IllegalArgumentException expected");
		}
		catch(IllegalArgumentException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_invalidparam3() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.getRange("b", "a");
			fail("A IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_validparam1() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.getRange("a", "a");
		}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_validparam2() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.getRange(0, 0);
		}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_validresult1() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("a");
			if (tree1.getRange("a", "a").size() != 1)
				fail("ArrayList should only contain 1 element");
			if (!tree1.getRange("a", "a").get(0).equals("a"))
				fail ("ArrayList should only contain \"a\" as the element");
		}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_validresult3() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("a");
			tree1.add("b");
			if (tree1.getRange("a", "a").size() != 1)
				fail("ArrayList should only contain 1 element");
			if (!tree1.getRange("a", "a").get(0).equals("a"))
				fail ("ArrayList should only contain \"a\" as the element");
		}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	
	@Test
    public void BST_getRange_validresult2() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(1);
			tree1.add(0);
			if (tree1.getRange(1, 1).size() != 1)
				fail("ArrayList should only contain 1 element");
			if (tree1.getRange(0, 0).get(0) != 0)
				fail ("ArrayList should only contain \"a\" as the element");
			if (tree1.getRange(5, 5).size() != 0)
				fail ("ArrayList should be empty for finding "
						+ "nonexistent values");
		}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_validresult5() {
		
		try {
			BST<Integer> tree1 = new BST<>();
			tree1.add(1);
			tree1.add(0);
			tree1.add(5);
			if (tree1.getRange(0, 1).size() != 2)
				fail("ArrayList should only contain 2 element");
			if (tree1.getRange(0, 1).get(0) != 0)
				fail ("ArrayList should contain 0 as the first element");
			if (tree1.getRange(0, 5).get(2) != 5)
				fail ("ArrayList should contain 5 as the last element");
		}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	
	@Test
    public void BST_getRange_validRange6() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        tree1.add("flounder");
	        tree1.add("grouper");
	        tree1.add("cod");
	        tree1.add("carp");
	        tree1.add("tilapia");
	        tree1.add("catfish");
	        tree1.add("bluefish");
	        tree1.add("tuna");
	        tree1.add("yellowtail");
	        tree1.add("herring");
	        tree1.add("sturgeon");
	        ArrayList<String> arr1 = tree1.getRange("bluefish", "yellowtail");
	        if (arr1.size() != 12)
	        	fail("Not all elements were added to arraylist");
	        Object[] arr = {"bluefish", "carp", "catfish", "cod", 
	        		"flounder", "grouper", "herring", "salmon", 
	        		"sturgeon", "tilapia", "tuna", "yellowtail"};
	        for (int i = 0; i < 12; i++) {
	        	if (!arr1.get(i).equals(arr[i]))
	        		fail("Wrong order of values in ArrayList");
	        }
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	@Test
    public void BST_getRange_validRange7() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        tree1.add("flounder");
	        tree1.add("grouper");
	        tree1.add("cod");
	        tree1.add("carp");
	        tree1.add("tilapia");
	        tree1.add("catfish");
	        tree1.add("bluefish");
	        tree1.add("tuna");
	        tree1.add("yellowtail");
	        tree1.add("herring");
	        tree1.add("sturgeon");
	        ArrayList<String> arr1 = tree1.getRange("bluefish", "bluefish");
	        if (arr1.size() != 1)
	        	fail("There should only be 1 element in ArrayList");
	        Object[] arr = {"bluefish"};
	        if (!arr1.get(0).equals(arr[0]))
	        	fail("Wrong element added to ArrayList");
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_getRange_validRange8() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        tree1.add("flounder");
	        tree1.add("grouper");
	        tree1.add("cod");
	        tree1.add("carp");
	        tree1.add("tilapia");
	        tree1.add("catfish");
	        tree1.add("bluefish");
	        tree1.add("tuna");
	        tree1.add("yellowtail");
	        tree1.add("herring");
	        tree1.add("sturgeon");
	        ArrayList<String> arr1 = tree1.getRange("bluefish", "cod");
	        if (arr1.size() != 4)
	        	fail("There should only be 4 elements in ArrayList");
	        Object[] arr = {"bluefish", "carp", "catfish", "cod"};
	        for (int i = 0; i < 4; i++) {
	        	if (!arr1.get(i).equals(arr[i]))
	        		fail("Wrong order of values in ArrayList");
	        }
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	
	@Test
    public void BST_first_nullroot() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.first();
	        fail("An NoSuchElementException should have been thrown");
	    }
		catch(NoSuchElementException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_first_valid() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        tree1.add("flounder");
	        tree1.add("grouper");
	        tree1.add("cod");
	        tree1.add("carp");
	        tree1.add("tilapia");
	        tree1.add("catfish");
	        tree1.add("bluefish");
	        tree1.add("tuna");
	        tree1.add("yellowtail");
	        tree1.add("herring");
	        tree1.add("sturgeon");
	        String str = tree1.first();
	        if (!str.equals("bluefish"))
	        	fail("Wrong first element");
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	
	@Test
    public void BST_first_valid2() {
		try {
			BST<Integer> tree2 = new BST<>();
			tree2.add(1);
			tree2.add(2);
			tree2.add(3);
			tree2.add(4);
			tree2.add(8);
			tree2.add(7);
			tree2.add(6);
			tree2.add(5);
			int val = tree2.first();
			if (val != 1)
				fail("Wrong first element");
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
    public void BST_last_nullroot() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.last();
	        fail("An NoSuchElementException should have been thrown");
	    }
		catch(NoSuchElementException ex) {}
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	@Test
    public void BST_last_valid() {
		
		try {
			BST<String> tree1 = new BST<>();
			tree1.add("salmon");
	        tree1.add("flounder");
	        tree1.add("grouper");
	        tree1.add("cod");
	        tree1.add("carp");
	        tree1.add("tilapia");
	        tree1.add("catfish");
	        tree1.add("bluefish");
	        tree1.add("tuna");
	        tree1.add("yellowtail");
	        tree1.add("herring");
	        tree1.add("sturgeon");
	        String str = tree1.last();
	        if (!str.equals("yellowtail"))
	        	fail("Wrong last element");
	    }
        catch(Exception ex) {
        	fail("Unexpected exception thrown.");
        }
    }
	
	
	@Test
    public void BST_last_valid2() {
		try {
			BST<Integer> tree2 = new BST<>();
			tree2.add(1);
			tree2.add(2);
			tree2.add(3);
			tree2.add(4);
			tree2.add(8);
			tree2.add(7);
			tree2.add(6);
			tree2.add(5);
			int val = tree2.last();
			if (val != 8)
				fail("Wrong last element");
        }
        catch(Exception ex) {
        	fail("Unexpected exception thrown");
        }
    }
	
	@Test
	public void BST_equals_valid() {
		try {
			BST<String> tree = new BST<>();
	        tree.add("salmon");
	        tree.add("flounder");
	        tree.add("grouper");
	        tree.add("cod");
	        tree.add("carp");
	        tree.add("tilapia");
	        tree.add("catfish");
	        tree.add("bluefish");
	        tree.add("tuna");
	        tree.add("yellowtail");
	        tree.add("herring");
	        tree.add("sturgeon");

	        BST<String> ntree = new BST<>();

	        ntree.add("salmon");
	        ntree.add("flounder");
	        ntree.add("grouper");
	        ntree.add("cod");
	        ntree.add("carp");
	        ntree.add("tilapia");
	        ntree.add("catfish");
	        ntree.add("bluefish");
	        ntree.add("tuna");
	        ntree.add("yellowtail");
	        if (tree.equals(ntree))
	        	fail("Tree is not equal at this point");
	        ntree.add("herring");
	        ntree.add("sturgeon");
		}
		catch (Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_equals_valid2() {
		try {
			BST<String> tree = new BST<>();
	        tree.add("salmon");
	        tree.add("flounder");
	        tree.add("grouper");
	        tree.add("cod");
	        tree.add("carp");
	        tree.add("tilapia");
	        tree.add("catfish");
	        tree.add("bluefish");
	        tree.add("tuna");
	        tree.add("yellowtail");
	        tree.add("herring");
	        tree.add("sturgeon");

	        BST<String> ntree = new BST<>();
	        if (tree.equals(ntree))
	        	fail("Tree is not equal at this point");
		}
		catch (Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_equals_valid3() {
		try {
			BST<String> tree = new BST<>();
	        tree.add("salmon");
	        tree.add("flounder");
	        tree.add("grouper");
	        tree.add("cod");
	        tree.add("carp");
	        tree.add("tilapia");
	        tree.add("catfish");
	        tree.add("bluefish");
	        tree.add("tuna");
	        tree.add("yellowtail");
	        tree.add("herring");
	        tree.add("sturgeon");

	        BST<String> ntree = new BST<>();

	        ntree.add("salmon");
	        ntree.add("flounder");
	        ntree.add("grouper");
	        ntree.add("cod");
	        ntree.add("carp");
	        ntree.add("tilapia");
	        ntree.add("catfish");
	        ntree.add("bluefish");
	        ntree.add("tuna");
	        ntree.add("yellowtail");
	        ntree.add("herring");
	        ntree.add("sturgeon");
	        if (!tree.equals(ntree))
	        	fail("Tree is equal at this point");
		}
		catch (Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_equals_valid4() {
		try {
			BST<String> tree = new BST<>();
	        tree.add("salmon");
	        tree.add("flounder");
	        tree.add("grouper");
	        tree.add("cod");
	        tree.add("carp");
	        tree.add("tilapia");
	        tree.add("catfish");
	        tree.add("bluefish");
	        tree.add("tuna");
	        tree.add("yellowtail");
	        tree.add("herring");
	        tree.add("sturgeon");
	        if (!tree.equals(tree))
	        	fail("Tree is equal to itself");
		}
		catch (Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	
	@Test
	public void BST_equals_valid5() {
		try {
			BST<String> tree = new BST<>();
	        tree.add("salmon");
	        tree.add("flounder");
	        tree.add("grouper");
	        tree.add("cod");
	        tree.add("carp");
	        tree.add("tilapia");
	        tree.add("catfish");
	        tree.add("bluefish");
	        tree.add("tuna");
	        tree.add("yellowtail");
	        tree.add("herring");
	        tree.add("sturgeon");

	        BST<Integer> tree2 = new BST<>();

	        tree2.add(1);
			tree2.add(2);
			tree2.add(3);
			tree2.add(4);
			tree2.add(8);
			tree2.add(7);
			tree2.add(6);
			tree2.add(5);
	        if (tree.equals(tree2))
	        	fail("Trying to compare integer with string");
		}
		catch (Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_equals_emptytrees() {
		try {
			BST<String> tree = new BST<>();
			BST<Integer> tree2 = new BST<>();
			if (!tree.equals(tree2))
				fail("2 empty trees are equal");
			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
	}
	
	@Test
	public void BST_equals_valid1() {
		try {
			BST<String> tree = new BST<>();
			BST<Integer> tree2 = new BST<>();
			tree2.add(1);
			if (tree.equals(tree2))
				fail("the 2 trees are not equal");
			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
	}
	
	@Test
	public void BST_toString_valid1() {
		try {
			BST<String> tree = new BST<>();
			tree.add("salmon");
	        tree.add("flounder");
	        tree.add("grouper");
	        tree.add("cod");
	        tree.add("carp");
	        tree.add("tilapia");
	        tree.add("catfish");
	        tree.add("bluefish");
	        tree.add("tuna");
	        tree.add("yellowtail");
	        tree.add("herring");
	        tree.add("sturgeon");
			String str = "[bluefish, carp, catfish, cod, "
					+ "flounder, grouper, herring, salmon, "
					+ "sturgeon, tilapia, tuna, yellowtail"
					+ "]";
			if (!tree.toString().equals(str))
				fail("Incorrect string for toString() returned");			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
	}
	
	
	@Test
	public void BST_toString_valid2() {
		try {
			BST<Integer> tree2 = new BST<>();

	        tree2.add(1);
			tree2.add(2);
			tree2.add(3);
			tree2.add(4);
			tree2.add(8);
			tree2.add(7);
			tree2.add(6);
			tree2.add(5);
			String str = "[1, 2, 3, 4, 5, 6, 7, 8]";
			if (!tree2.toString().equals(str))
				fail("Incorrect string for toString() returned");			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_toString_emptyTree() {
		try {
			BST<Integer> tree2 = new BST<>();

			String str = "[]";
			if (!tree2.toString().equals(str))
				fail("Incorrect string for toString() returned");			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_toString_emptyTree2() {
		try {
			BST<String> tree2 = new BST<>();

			String str = "[]";
			if (!tree2.toString().equals(str))
				fail("Incorrect string for toString() returned");			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_toArray_emptyTree() {
		try {
			BST<Integer> tree2 = new BST<>();
			Object[] arr = tree2.toArray();
			if (arr.length != 0) 
				fail("Empty tree should return empty array");
			
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
	@Test
	public void BST_toArray_Tree() {
		try {
			BST<Integer> tree2 = new BST<>();

	        tree2.add(1);
			tree2.add(2);
			tree2.add(3);
			tree2.add(4);
			tree2.add(8);
			tree2.add(7);
			tree2.add(6);
			tree2.add(5);
			Object[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
			Object[] arr2 = tree2.toArray();
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != arr2[i])
					fail("Wrong order of elements of the array");
			}	
		}
		catch(Exception ex) {
			fail("Unexpected exception thrown");
		}
		
	}
	
}