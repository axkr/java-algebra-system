/*
 * $Id$
 */

package edu.jas.arith;

import java.math.BigInteger;

public interface Coefficient {

    public static Coefficient ONE = null;
    public static Coefficient ZERO = null;

    public boolean isZERO();

    public boolean isONE();

    // public Coefficient(BigInteger i); not possible to declare

    public /*static*/ Coefficient fromInteger(BigInteger a);

    public /*static*/ Coefficient fromInteger(long a);

    public Coefficient abs();

    public Coefficient subtract(Coefficient S);

    public Coefficient negate();

    public int signum();

    public Coefficient divide(Coefficient S);

    public Coefficient random(int n);

    public Coefficient multiply(Coefficient S);

    public Coefficient add(Coefficient S);

}
