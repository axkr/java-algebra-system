/*
 * $Id$
 */

package edu.jas.arith;

//import edu.jas.arith.BigInteger;
//import edu.jas.arith.BigRational;
//import edu.jas.arith.BigComplex;
//import edu.jas.arith.BigQuaternion;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Coefficient Test using JUnit 
 * @author Heinz Kredel.
 */

public class CoefficientTest extends TestCase {

/**
 * main
 */
   public static void main (String[] args) {
          junit.textui.TestRunner.run( suite() );
   }

/**
 * Constructs a <CODE>CoefficientTest</CODE> object.
 * @param name String
 */
   public CoefficientTest(String name) {
          super(name);
   }

/**
 */ 
 public static Test suite() {
     TestSuite suite= new TestSuite(CoefficientTest.class);
     return suite;
   }

   Coefficient a;
   Coefficient b;
   Coefficient c;
   Coefficient d;
   Coefficient e;

   protected void setUp() {
       a = b = c = d = e = null;
   }

   protected void tearDown() {
       a = b = c = d = e = null;
   }


/**
 * Test static initialization and constants for BigInteger
 * 
 */
 public void testIntegerConstants() {
     a = BigInteger.ZERO;
     b = BigInteger.ONE;
     c = b.subtract(b);

     assertTrue("0.isZERO()",a.isZERO());
     assertTrue("1.isONE", b.isONE() );

     assertEquals("1-1 = 0",c,a);
     assertTrue("(1-1).isZERO()",c.isZERO());

     d = b.multiply(b);
     assertTrue("1*1 = 1",d.isONE());

     d = b.multiply(a);
     assertTrue("1*0 = 0",d.isZERO());
   }


/**
 * Test static initialization and constants for BigRational
 * 
 */
 public void testRationalConstants() {
     a = BigRational.ZERO;
     b = BigRational.ONE;
     c = b.subtract(b);

     assertTrue("0.isZERO()",a.isZERO());
     assertTrue("1.isONE", b.isONE() );

     assertEquals("1-1 = 0",c,a);
     assertTrue("(1-1).isZERO()",c.isZERO());

     d = b.multiply(b);
     assertTrue("1*1 = 1",d.isONE());

     d = b.multiply(a);
     assertTrue("1*0 = 0",d.isZERO());
   }


/**
 * Test static initialization and constants for BigComplex
 * 
 */
 public void testComplexConstants() {
     a = BigComplex.ZERO;
     b = BigComplex.ONE;
     c = b.subtract(b);

     assertTrue("0.isZERO()",a.isZERO());
     assertTrue("1.isONE", b.isONE() );

     assertEquals("1-1 = 0",c,a);
     assertTrue("(1-1).isZERO()",c.isZERO());

     d = b.multiply(b);
     assertTrue("1*1 = 1",d.isONE());

     d = b.multiply(a);
     assertTrue("1*0 = 0",d.isZERO());
   }


/**
 * Test static initialization and constants for BigQuaternion
 * 
 */
 public void testQuaternionConstants() {
     a = BigQuaternion.ZERO;
     b = BigQuaternion.ONE;
     c = b.subtract(b);

     assertTrue("0.isZERO()",a.isZERO());
     assertTrue("1.isONE", b.isONE() );

     assertEquals("1-1 = 0",c,a);
     assertTrue("(1-1).isZERO()",c.isZERO());

     d = b.multiply(b);
     assertTrue("1*1 = 1",d.isONE());

     d = b.multiply(a);
     assertTrue("1*0 = 0",d.isZERO());
   }

    //--------------------------------------------------------


/**
 * Test string constructor and toString for BigInteger
 * 
 */
 public void testIntegerConstructor() {
     a = new BigInteger( 1 );
     b = new BigInteger( -1 );
     c = new BigInteger( 0 );

     d = a.add(b);
     assertTrue("'1'.isONE()",a.isONE() );
     assertTrue("1+(-1) = 0",d.isZERO());
     d = a.negate();
     assertEquals("-1 = -(1)",d,b);

     d = a.multiply(c);
     assertTrue("'0'.isZERO()",d.isZERO() );
     d = b.multiply(b);
     assertTrue("(-1)*(-1) = 1",d.isONE());

     a = new BigInteger( 3 );
     b = new BigInteger( "3" );
     assertEquals("3 = '3'",a,b);

     a = new BigInteger( -5 );
     b = new BigInteger( "-5" );
     assertEquals("-5 = '-5'",a,b);

     //          0         1         2         3         4 
     //          0123456789012345678901234567890123456789012345
     String s = "1111111111111111111111111111111111111111111111";
     a = new BigInteger( s );
     String t = a.toString();
     assertEquals("stringConstr = toString",s,t);
   }

/**
 * Test string constructor and toString for BigRational
 * 
 */
 public void testRationalConstructor() {
     a = new BigRational( 1 );
     b = new BigRational( -1 );
     c = new BigRational( 0 );

     d = a.add(b);
     assertTrue("'1'.isONE()",a.isONE() );
     assertTrue("1+(-1) = 0",d.isZERO());
     d = a.negate();
     assertEquals("-1 = -(1)",d,b);

     d = a.multiply(c);
     assertTrue("'0'.isZERO()",d.isZERO() );
     d = b.multiply(b);
     assertTrue("(-1)*(-1) = 1",d.isONE());

     a = new BigRational( 3 );
     b = new BigRational( "3" );
     assertEquals("3 = '3'",a,b);

     a = new BigRational( -5 );
     b = new BigRational( "-5" );
     assertEquals("-5 = '-5'",a,b);

     //          0         1         2         3         4 
     //          0123456789012345678901234567890123456789012345
     String s = "1111111111111111111111111111111111111111111111";
     a = new BigRational( s );
     String t = a.toString();
     assertEquals("stringConstr = toString",s,t);
   }

/**
 * Test string constructor and toString for BigComplex
 * 
 */
 public void testComplexConstructor() {
     a = new BigComplex( 1 );
     b = new BigComplex( -1 );
     c = new BigComplex( 0 );

     d = a.add(b);
     assertTrue("'1'.isONE()",a.isONE() );
     assertTrue("1+(-1) = 0",d.isZERO());
     d = a.negate();
     assertEquals("-1 = -(1)",d,b);

     d = a.multiply(c);
     assertTrue("'0'.isZERO()",d.isZERO() );
     d = b.multiply(b);
     assertTrue("(-1)*(-1) = 1",d.isONE());

     a = new BigComplex( 3 );
     b = new BigComplex( "3" );
     assertEquals("3 = '3'",a,b);

     a = new BigComplex( -5 );
     b = new BigComplex( "-5" );
     assertEquals("-5 = '-5'",a,b);

     //          0         1         2         3         4 
     //          0123456789012345678901234567890123456789012345
     String s = "1111111111111111111111111111111111111111111111";
     a = new BigComplex( s );
     String t = a.toString();
     assertEquals("stringConstr = toString",s,t);
   }

/**
 * Test string constructor and toString for BigQuaternion
 * 
 */
 public void testQuaternionConstructor() {
     a = new BigQuaternion( 1 );
     b = new BigQuaternion( -1 );
     c = new BigQuaternion( 0 );

     d = a.add(b);
     assertTrue("'1'.isONE()",a.isONE() );
     assertTrue("1+(-1) = 0",d.isZERO());
     d = a.negate();
     assertEquals("-1 = -(1)",d,b);

     d = a.multiply(c);
     assertTrue("'0'.isZERO()",d.isZERO() );
     d = b.multiply(b);
     assertTrue("(-1)*(-1) = 1",d.isONE());

     a = new BigQuaternion( 3 );
     b = new BigQuaternion( "3" );
     assertEquals("3 = '3'",a,b);

     a = new BigQuaternion( -5 );
     b = new BigQuaternion( "-5" );
     assertEquals("-5 = '-5'",a,b);

     //          0         1         2         3         4 
     //          0123456789012345678901234567890123456789012345
     String s = "1111111111111111111111111111111111111111111111";
     a = new BigQuaternion( s );
     String t = a.toString();
     assertEquals("stringConstr = toString",s,t);
   }


    //--------------------------------------------------------

/**
 * Test random and compares Integer
 * 
 */
 public void testIntegerRandom() {
     a = BigInteger.ZERO.random( 500 );
     b = new BigInteger( "" + a );
     c = b.subtract(a);

     assertTrue("a-'a' = 0",c.isZERO());
     assertEquals("compareTo('a',a) = 0", 0, b.compareTo(a) );
     assertEquals("signum('a'-a) = 0", 0, c.signum() );
 }

/**
 * Test random and compares Rational
 * 
 */
 public void testRationalRandom() {
     a = BigRational.ZERO.random( 500 );
     b = new BigRational( "" + a );
     c = b.subtract(a);

     assertTrue("a-'a' = 0",c.isZERO());
     assertEquals("compareTo('a',a) = 0", 0, b.compareTo(a) );
     assertEquals("signum('a'-a) = 0", 0, c.signum() );
 }

/**
 * Test random and compares Complex
 * 
 */
 public void testComplexRandom() {
     a = BigComplex.ZERO.random( 500 );
     b = new BigComplex( "" + a );
     c = b.subtract(a);

     assertTrue("a-'a' = 0",c.isZERO());
     assertEquals("compareTo('a',a) = 0", 0, b.compareTo(a) );
     assertEquals("signum('a'-a) = 0", 0, c.signum() );
 }

/**
 * Test random and compares Quaternion
 * 
 */
 public void testQuaternionRandom() {
     a = BigQuaternion.ZERO.random( 500 );
     b = new BigQuaternion( "" + a );
     c = b.subtract(a);

     assertTrue("a-'a' = 0",c.isZERO());
     assertEquals("signum('a'-a) = 0", 0, c.signum() );
     assertEquals("compareTo('a',a) = 0", 0, b.compareTo(a) );
 }


    //--------------------------------------------------------


/**
 * Test addition for Integer
 * 
 */
 public void testIntegerAddition() {
     // neutral element
     a = BigInteger.ZERO.random( 500 );
     d = a.add(BigInteger.ZERO);
     assertEquals("a+0 = a",d,a);
     d = a.subtract(BigInteger.ZERO);
     assertEquals("a-0 = a",d,a);

     // inverse operations
     b = a.add(a);
     c = b.subtract(a);
     assertEquals("(a+a)-a = a",c,a);
     b = a.subtract(a);
     c = b.add(a);
     assertEquals("(a-a)+a = a",c,a);

     // comutativity
     b = BigInteger.ZERO.random( 500 );
     c = a.add(b);
     d = b.add(a);
     assertEquals("a+b = b+a", c, d );

     // negation
     c = a.subtract(b);
     d = a.add(b.negate());
     assertEquals("a-b = a+(-b)", c, d );

     // associativity
     c = BigInteger.ZERO.random( 500 );
     d = a.add(b.add(c));
     e = a.add(b).add(c);
     assertEquals("a+(b+c) = (a+b)+c", d, e );
 }

/**
 * Test addition for Rational
 * 
 */
 public void testRationalAddition() {
     // neutral element
     a = BigRational.ZERO.random( 500 );
     d = a.add(BigRational.ZERO);
     assertEquals("a+0 = a",d,a);
     d = a.subtract(BigRational.ZERO);
     assertEquals("a-0 = a",d,a);

     // inverse operations
     b = a.add(a);
     c = b.subtract(a);
     assertEquals("(a+a)-a = a",c,a);
     b = a.subtract(a);
     c = b.add(a);
     assertEquals("(a-a)+a = a",c,a);

     // comutativity
     b = BigRational.ZERO.random( 500 );
     c = a.add(b);
     d = b.add(a);
     assertEquals("a+b = b+a", c, d );

     // negation
     c = a.subtract(b);
     d = a.add(b.negate());
     assertEquals("a-b = a+(-b)", c, d );

     // associativity
     c = BigRational.ZERO.random( 500 );
     d = a.add(b.add(c));
     e = a.add(b).add(c);
     assertEquals("a+(b+c) = (a+b)+c", d, e );
 }

/**
 * Test addition for Complex
 * 
 */
 public void testComplexAddition() {
     // neutral element
     a = BigComplex.ZERO.random( 500 );
     d = a.add(BigComplex.ZERO);
     assertEquals("a+0 = a",d,a);
     d = a.subtract(BigComplex.ZERO);
     assertEquals("a-0 = a",d,a);

     // inverse operations
     b = a.add(a);
     c = b.subtract(a);
     assertEquals("(a+a)-a = a",c,a);
     b = a.subtract(a);
     c = b.add(a);
     assertEquals("(a-a)+a = a",c,a);

     // comutativity
     b = BigComplex.ZERO.random( 500 );
     c = a.add(b);
     d = b.add(a);
     assertEquals("a+b = b+a", c, d );

     // negation
     c = a.subtract(b);
     d = a.add(b.negate());
     assertEquals("a-b = a+(-b)", c, d );

     // associativity
     c = BigComplex.ZERO.random( 500 );
     d = a.add(b.add(c));
     e = a.add(b).add(c);
     assertEquals("a+(b+c) = (a+b)+c", d, e );
 }

/**
 * Test addition for Quaternion
 * 
 */
 public void testQuaternionAddition() {
     // neutral element
     a = BigQuaternion.ZERO.random( 500 );
     d = a.add(BigQuaternion.ZERO);
     assertEquals("a+0 = a",d,a);
     d = a.subtract(BigQuaternion.ZERO);
     assertEquals("a-0 = a",d,a);

     // inverse operations
     b = a.add(a);
     c = b.subtract(a);
     assertEquals("(a+a)-a = a",c,a);
     b = a.subtract(a);
     c = b.add(a);
     assertEquals("(a-a)+a = a",c,a);

     // comutativity
     b = BigQuaternion.ZERO.random( 500 );
     c = a.add(b);
     d = b.add(a);
     assertEquals("a+b = b+a", c, d );

     // negation
     c = a.subtract(b);
     d = a.add(b.negate());
     assertEquals("a-b = a+(-b)", c, d );

     // associativity
     c = BigQuaternion.ZERO.random( 500 );
     d = a.add(b.add(c));
     e = a.add(b).add(c);
     assertEquals("a+(b+c) = (a+b)+c", d, e );
 }

    //--------------------------------------------------------


/**
 * Test multiplication for Integer
 * 
 */
 public void testIntegerMultiplication() {
     // neutral element
     a = BigInteger.ZERO.random( 500 );
     d = a.multiply(BigInteger.ONE);
     assertEquals("a*1 = a",d,a);
     d = a.divide(BigInteger.ONE);
     assertEquals("a/1 = a",d,a);

     // inverse operations
     b = a.multiply(a);
     c = b.divide(a);
     assertEquals("(a*a)/a = a",c,a);
     b = a.divide(a);
     c = b.multiply(a);
     assertEquals("(a/a)*a = a",c,a);

     // comutativity
     b = BigInteger.ZERO.random( 500 );
     c = a.multiply(b);
     d = b.multiply(a);
     assertEquals("a*b = b*a", c, d );

     // inverse
     d = c.divide(b);
     // e = c.multiply( b.inverse() );
     e = a;
     assertEquals("a/b = a*(1/b)", d, e );

     // associativity
     c = BigInteger.ZERO.random( 500 );
     d = a.multiply(b.multiply(c));
     e = a.multiply(b).multiply(c);
     assertEquals("a*(b*c) = (a*b)*c", d, e );
 }

/**
 * Test multiplication for Rational
 * 
 */
 public void testRationalMultiplication() {
     // neutral element
     a = BigRational.ZERO.random( 500 );
     d = a.multiply(BigRational.ONE);
     assertEquals("a*1 = a",d,a);
     d = a.divide(BigRational.ONE);
     assertEquals("a/1 = a",d,a);

     // inverse operations
     b = a.multiply(a);
     c = b.divide(a);
     assertEquals("(a*a)/a = a",c,a);
     b = a.divide(a);
     c = b.multiply(a);
     assertEquals("(a/a)*a = a",c,a);

     // comutativity
     b = BigRational.ZERO.random( 500 );
     c = a.multiply(b);
     d = b.multiply(a);
     assertEquals("a*b = b*a", c, d );

     // inverse
     d = c.divide(b);
     e = c.multiply( b.inverse() );
     //e = a;
     assertEquals("a/b = a*(1/b)", d, e );

     // associativity
     c = BigRational.ZERO.random( 500 );
     d = a.multiply(b.multiply(c));
     e = a.multiply(b).multiply(c);
     assertEquals("a*(b*c) = (a*b)*c", d, e );
 }

/**
 * Test multiplication for Complex
 * 
 */
 public void testComplexMultiplication() {
     // neutral element
     a = BigComplex.ZERO.random( 500 );
     d = a.multiply(BigComplex.ONE);
     assertEquals("a*1 = a",d,a);
     d = a.divide(BigComplex.ONE);
     assertEquals("a/1 = a",d,a);

     // inverse operations
     b = a.multiply(a);
     c = b.divide(a);
     assertEquals("(a*a)/a = a",c,a);
     b = a.divide(a);
     c = b.multiply(a);
     assertEquals("(a/a)*a = a",c,a);

     // comutativity
     b = BigComplex.ZERO.random( 500 );
     c = a.multiply(b);
     d = b.multiply(a);
     assertEquals("a*b = b*a", c, d );

     // inverse
     d = c.divide(b);
     e = c.multiply( b.inverse() );
     //e = a;
     assertEquals("a/b = a*(1/b)", d, e );

     // associativity
     c = BigComplex.ZERO.random( 500 );
     d = a.multiply(b.multiply(c));
     e = a.multiply(b).multiply(c);
     assertEquals("a*(b*c) = (a*b)*c", d, e );
 }

/**
 * Test multiplication for Quaternion
 * 
 */
 public void testQuaternionMultiplication() {
     // neutral element
     a = BigQuaternion.ZERO.random( 500 );
     d = a.multiply(BigQuaternion.ONE);
     assertEquals("a*1 = a",d,a);
     d = a.divide(BigQuaternion.ONE);
     assertEquals("a/1 = a",d,a);

     // inverse operations
     b = a.multiply(a);
     c = b.divide(a);
     assertEquals("(a*a)/a = a",c,a);
     b = a.divide(a);
     c = b.multiply(a);
     assertEquals("(a/a)*a = a",c,a);

     // inverse
     b = BigQuaternion.ZERO.random( 500 );
     c = b.multiply(a);
     d = c.divide(b);
     e = c.multiply( b.inverse() );
     //e = a;
     assertEquals("a/b = a*(1/b)", d, e );

     // associativity
     c = BigQuaternion.ZERO.random( 500 );
     d = a.multiply(b.multiply(c));
     e = a.multiply(b).multiply(c);
     assertEquals("a*(b*c) = (a*b)*c", d, e );

     // non comutativity
     a = BigQuaternion.I;
     b = BigQuaternion.J;
     c = a.multiply(b);
     d = b.multiply(a);
     assertEquals("I*J = -J*I", c, d.negate() );
     a = BigQuaternion.I;
     b = BigQuaternion.K;
     c = a.multiply(b);
     d = b.multiply(a);
     assertEquals("I*K = -K*I", c, d.negate() );
     a = BigQuaternion.J;
     b = BigQuaternion.K;
     c = a.multiply(b);
     d = b.multiply(a);
     assertEquals("J*K = -K*J", c, d.negate() );
 }

}
