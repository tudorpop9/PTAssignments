package HW1.HW1;

import core.Polinom;
import junit.framework.*;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        //super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    //verificare formate
    public void testFormat1() {
    	assertEquals(new Polinom("x^2+2x+1"), new Polinom("X^2   +   2x   + 1 "));
    }
    
    public void testFormat2() {
    	try {
    	     new Polinom("ee3x3423");
    	     fail("Oh well..");
    	} catch( NumberFormatException e ) {
    		assertTrue(true);
    	}
    }
    
    public void testFormat3() {
    	try {
    	     new Polinom("xx21/x2 + 5");
    	     fail("Oh well..");
    	} catch( NumberFormatException e ) {
    		assertTrue(true);
    	}
    }
    
    public void testFormat4() {
    	try {
    	     new Polinom("3x5 + x^3 +2");
    	     fail("Oh well..");
    	} catch( NumberFormatException e ) {
    		assertTrue(true);
    	}
    }
    
    /// Testare operatii
    
    public void testAdd() {
    	assertEquals((new Polinom("2x^4 +4x^2 -x+2")).toString(), (Polinom.addP(new Polinom("2x^3 + 2x^2 +1"), new Polinom("2x^4 - 2x^3 +2x^2- x +1")).toString()));
    }
    
    public void testSub() {
    	assertEquals((new Polinom("-2x^4 +4x^3 +x")).toString(), (Polinom.supP(new Polinom("2x^3 + 2x^2 +1"), new Polinom("2x^4 - 2x^3 +2x^2- x +1")).toString()));
    }
    
    public void testMul() {
    	assertEquals((new Polinom("x^2 + 2x+1")).toString(), (Polinom.mulP(new Polinom("x+1"), new Polinom("x+1")).toString()));
    }
    
    public void testMul2() {
    	assertEquals((new Polinom("x^8+2x^6 +4x^5 + 18x^3 + 3x^2 + 24x + 12")).toString(), (Polinom.mulP(new Polinom("x^3+2x+1"), new Polinom("x^5 + 3x^2+12")).toString()));
    }
    
   /* public void testDiv() {
    	ArrayList<Polinom> pTest = new ArrayList<Polinom>();//....hmmmmmmm
    	
    	assertEquals(pTest, (Polinom.divP(new Polinom("x^2+2x+1"), new Polinom("x+2"))));
    }*/
    
    public void testDeriv() {
    	assertEquals((new Polinom("2x+2")).toString(), (Polinom.derivP(new Polinom("x^2+2x+1"))).toString());
    }
    
    public void testIntegr() {
    	assertEquals((new Polinom("x^3+x^2+x")).toString(), (Polinom.integrP(new Polinom("3x^2+2x+1"))).toString());
    }
    
   /* public void testTest() {
    	assertTrue(true);
    }*/
    
    
}
