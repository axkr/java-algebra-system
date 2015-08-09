/*
 * $Id$
 */

package edu.jas.gb;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.jas.poly.GenWordPolynomial;
import edu.jas.poly.Word;
import edu.jas.structure.RingElem;


/**
 * Polynomial word reduction sequential use algorithm. Implements normalform.
 * @param <C> coefficient type
 * @author Heinz Kredel
 */

public class WordReductionSeq<C extends RingElem<C>> // should be FieldElem<C>>
                extends WordReductionAbstract<C> {


    private static final Logger logger = Logger.getLogger(WordReductionSeq.class);


    private static boolean debug = logger.isDebugEnabled();


    /**
     * Constructor.
     */
    public WordReductionSeq() {
    }


    /**
     * Normalform.
     * @param Ap polynomial.
     * @param Pp polynomial list.
     * @return nf(Ap) with respect to Pp.
     */
    @SuppressWarnings("unchecked")
    public GenWordPolynomial<C> normalform(List<GenWordPolynomial<C>> Pp, GenWordPolynomial<C> Ap) {
        if (Pp == null || Pp.isEmpty()) {
            return Ap;
        }
        if (Ap == null || Ap.isZERO()) {
            return Ap;
        }
        if (!Ap.ring.coFac.isField()) {
            throw new IllegalArgumentException("coefficients not from a field");
        }
        Map.Entry<Word, C> m;
        int l;
        GenWordPolynomial<C>[] P;
        synchronized (Pp) {
            l = Pp.size();
            P = new GenWordPolynomial[l];
            //P = Pp.toArray();
            for (int i = 0; i < Pp.size(); i++) {
                P[i] = Pp.get(i);
            }
        }
        Word[] htl = new Word[l];
        C[] lbc = (C[]) new RingElem[l]; // want C[]
        GenWordPolynomial<C>[] p = new GenWordPolynomial[l];
        int i;
        int j = 0;
        for (i = 0; i < l; i++) {
            p[i] = P[i];
            m = p[i].leadingMonomial();
            if (m != null) {
                p[j] = p[i];
                htl[j] = m.getKey();
                lbc[j] = m.getValue();
                j++;
            }
        }
        l = j;
        Word e;
        C a;
        boolean mt = false;
        GenWordPolynomial<C> R = Ap.ring.getZERO();
        C cone = Ap.ring.coFac.getONE();

        //GenWordPolynomial<C> T = null;
        GenWordPolynomial<C> Q = null;
        GenWordPolynomial<C> S = Ap;
        while (S.length() > 0) {
            m = S.leadingMonomial();
            e = m.getKey();
            a = m.getValue();
            for (i = 0; i < l; i++) {
                mt = e.multipleOf(htl[i]);
                if (mt)
                    break;
            }
            if (!mt) {
                //logger.debug("irred");
                //T = new OrderedMapPolynomial( a, e );
                R = R.sum(a, e);
                S = S.subtract(a, e);
                // System.out.println(" S = " + S);
            } else {
                Word[] elr = e.divideWord(htl[i]);
                e = elr[0];
                Word f = elr[1];
                if (debug) {
                    logger.info("red divideWord: e = " + e + ", f = " + f);
                }
                a = a.divide(lbc[i]);
                Q = p[i].multiply(a, e, cone, f);
                S = S.subtract(Q);
            }
        }
        return R;
    }


    /**
     * Normalform with left and right recording.
     * @param lrow left recording matrix, is modified.
     * @param rrow right recording matrix, is modified.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @return nf(Pp,Ap), the normal form of Ap wrt. Pp.
     */
    @SuppressWarnings("unchecked")
    public GenWordPolynomial<C> normalform(List<GenWordPolynomial<C>> lrow, List<GenWordPolynomial<C>> rrow,
                    List<GenWordPolynomial<C>> Pp, GenWordPolynomial<C> Ap) {
        if (Pp == null || Pp.isEmpty()) {
            return Ap;
        }
        if (Ap == null || Ap.isZERO()) {
            return Ap;
        }
        if (!Ap.ring.coFac.isField()) {
            throw new IllegalArgumentException("coefficients not from a field");
        }
        int l = Pp.size();
        GenWordPolynomial<C>[] P = new GenWordPolynomial[l];
        synchronized (Pp) {
            //P = Pp.toArray();
            for (int i = 0; i < Pp.size(); i++) {
                P[i] = Pp.get(i);
            }
        }
        Word[] htl = new Word[l];
        C[] lbc = (C[]) new RingElem[l]; // want C[]
        GenWordPolynomial<C>[] p = new GenWordPolynomial[l];
        Map.Entry<Word, C> m;
        int j = 0;
        int i;
        for (i = 0; i < l; i++) {
            p[i] = P[i];
            m = p[i].leadingMonomial();
            if (m != null) {
                p[j] = p[i];
                htl[j] = m.getKey();
                lbc[j] = m.getValue();
                j++;
            }
        }
        l = j;
        Word e;
        C a;
        boolean mt = false;
        GenWordPolynomial<C> zero = Ap.ring.getZERO();
        GenWordPolynomial<C> R = Ap.ring.getZERO();
        C cone = Ap.ring.coFac.getONE();

        GenWordPolynomial<C> fac = null;
        // GenWordPolynomial<C> T = null;
        GenWordPolynomial<C> Q = null;
        GenWordPolynomial<C> S = Ap;
        while (S.length() > 0) {
            m = S.leadingMonomial();
            e = m.getKey();
            a = m.getValue();
            for (i = 0; i < l; i++) {
                mt = e.multipleOf(htl[i]);
                if (mt)
                    break;
            }
            if (!mt) {
                //logger.debug("irred");
                R = R.sum(a, e);
                S = S.subtract(a, e);
                // System.out.println(" S = " + S);
            } else {
                Word[] elr = e.divideWord(htl[i]);
                e = elr[0];
                Word f = elr[1];
                if (debug) {
                    logger.info("redRec divideWord: e = " + e + ", f = " + f);
                }
                C c = lbc[i];
                a = a.divide(c);
                Q = p[i].multiply(a, e, cone, f);
                S = S.subtract(Q);
                // left row
                fac = lrow.get(i);
                if (fac == null) {
                    fac = zero.sum(a, e); // cone or a ?
                } else {
                    fac = fac.sum(a, e);  // cone or a ?
                }
                lrow.set(i, fac);
                // right row
                fac = rrow.get(i);
                if (fac == null) {
                    fac = zero.sum(cone, f); // a or cone ?
                } else {
                    fac = fac.sum(cone, f);  // a or cone ?
                }
                rrow.set(i, fac);
            }
        }
        return R;
    }


    /**
     * Left normalform with recording.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @return nf(Pp,Ap), the left normal form of Ap wrt. Pp.
     */
    public GenWordPolynomial<C> leftNormalform(List<GenWordPolynomial<C>> Pp, GenWordPolynomial<C> Ap) {
        if (Pp == null || Pp.isEmpty()) {
            return Ap;
        }
        if (Ap == null || Ap.isZERO()) {
            return Ap;
        }
        List<GenWordPolynomial<C>> lrow = new ArrayList<GenWordPolynomial<C>>(Pp.size());
        for (int i = 0; i < Pp.size(); i++ ) {
            lrow.add(Ap.ring.getZERO());
        }
        GenWordPolynomial<C> r = leftNormalform(lrow, Pp, Ap);
        return r;
    }


    /**
     * Left normalform with recording.
     * @param lrow left recording matrix, is modified.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @return nf(Pp,Ap), the left normal form of Ap wrt. Pp.
     */
    @SuppressWarnings("unchecked")
    public GenWordPolynomial<C> leftNormalform(List<GenWordPolynomial<C>> lrow, 
                    List<GenWordPolynomial<C>> Pp, GenWordPolynomial<C> Ap) {
        if (Pp == null || Pp.isEmpty()) {
            return Ap;
        }
        if (Ap == null || Ap.isZERO()) {
            return Ap;
        }
        if (!Ap.ring.coFac.isField()) {
            throw new IllegalArgumentException("coefficients not from a field");
        }
        int l = Pp.size();
        GenWordPolynomial<C>[] P = new GenWordPolynomial[l];
        synchronized (Pp) {
            //P = Pp.toArray();
            for (int i = 0; i < Pp.size(); i++) {
                P[i] = Pp.get(i);
            }
        }
        Word[] htl = new Word[l];
        C[] lbc = (C[]) new RingElem[l]; // want C[]
        GenWordPolynomial<C>[] p = new GenWordPolynomial[l];
        Map.Entry<Word, C> m;
        int j = 0;
        int i;
        for (i = 0; i < l; i++) {
            p[i] = P[i];
            m = p[i].leadingMonomial();
            if (m != null) {
                p[j] = p[i];
                htl[j] = m.getKey();
                lbc[j] = m.getValue();
                j++;
            }
        }
        l = j;
        Word e;
        C a;
        boolean mt = false;
        GenWordPolynomial<C> zero = Ap.ring.getZERO();
        GenWordPolynomial<C> R = Ap.ring.getZERO();
        C cone = Ap.ring.coFac.getONE();

        GenWordPolynomial<C> fac = null;
        // GenWordPolynomial<C> T = null;
        GenWordPolynomial<C> Q = null;
        GenWordPolynomial<C> S = Ap;
        while (S.length() > 0) {
            m = S.leadingMonomial();
            e = m.getKey();
            a = m.getValue();
            for (i = 0; i < l; i++) {
                mt = e.multipleOf(htl[i]);
                if (mt)
                    break;
            }
            if (!mt) {
                //logger.info("irred_1");
                R = R.sum(a, e);
                S = S.subtract(a, e);
                // System.out.println(" S = " + S);
            } else {
                Word g = e;
                Word[] elr = e.divideWord(htl[i]);
                e = elr[0];
                Word f = elr[1];
                if (false) {
                    logger.info("redRec divideWord: e = " + e + ", f = " + f);
                }
                if (f.isONE()) { 
		    C c = lbc[i];
                    //System.out.println("a = " + a + ", c = " + c);
		    a = a.divide(c);
                    //System.out.println("a/c = " + a);
		    Q = p[i].multiply(a, e, cone, f);
		    S = S.subtract(Q);
		    // left row
		    fac = lrow.get(i);
		    if (fac == null) {
			fac = zero.sum(a, e);
		    } else {
			fac = fac.sum(a, e);
		    }
		    lrow.set(i, fac);
                } else {
                    //logger.info("irred_2");
                    R = R.sum(a, g);
                    S = S.subtract(a, g);
                }
            }
        }
        return R;
    }


    /**
     * Right normalform with recording.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @return nf(Pp,Ap), the right normal form of Ap wrt. Pp.
     */
    public GenWordPolynomial<C> rightNormalform(List<GenWordPolynomial<C>> Pp, GenWordPolynomial<C> Ap) {
        if (Pp == null || Pp.isEmpty()) {
            return Ap;
        }
        if (Ap == null || Ap.isZERO()) {
            return Ap;
        }
        List<GenWordPolynomial<C>> lrow = new ArrayList<GenWordPolynomial<C>>(Pp.size());
        for (int i = 0; i < Pp.size(); i++ ) {
            lrow.add(Ap.ring.getZERO());
        }
        GenWordPolynomial<C> r = rightNormalform(lrow, Pp, Ap);
        return r;
    }


    /**
     * Right normalform with recording.
     * @param rrow right recording matrix, is modified.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @return nf(Pp,Ap), the right normal form of Ap wrt. Pp.
     */
    @SuppressWarnings("unchecked")
    public GenWordPolynomial<C> rightNormalform(List<GenWordPolynomial<C>> rrow, 
                    List<GenWordPolynomial<C>> Pp, GenWordPolynomial<C> Ap) {
        if (Pp == null || Pp.isEmpty()) {
            return Ap;
        }
        if (Ap == null || Ap.isZERO()) {
            return Ap;
        }
        if (!Ap.ring.coFac.isField()) {
            throw new IllegalArgumentException("coefficients not from a field");
        }
        int l = Pp.size();
        GenWordPolynomial<C>[] P = new GenWordPolynomial[l];
        synchronized (Pp) {
            //P = Pp.toArray();
            for (int i = 0; i < Pp.size(); i++) {
                P[i] = Pp.get(i);
            }
        }
        Word[] htl = new Word[l];
        C[] lbc = (C[]) new RingElem[l]; // want C[]
        GenWordPolynomial<C>[] p = new GenWordPolynomial[l];
        Map.Entry<Word, C> m;
        int j = 0;
        int i;
        for (i = 0; i < l; i++) {
            p[i] = P[i];
            m = p[i].leadingMonomial();
            if (m != null) {
                p[j] = p[i];
                htl[j] = m.getKey();
                lbc[j] = m.getValue();
                j++;
            }
        }
        l = j;
        Word e;
        C a;
        boolean mt = false;
        GenWordPolynomial<C> zero = Ap.ring.getZERO();
        GenWordPolynomial<C> R = Ap.ring.getZERO();
        C cone = Ap.ring.coFac.getONE();

        GenWordPolynomial<C> fac = null;
        // GenWordPolynomial<C> T = null;
        GenWordPolynomial<C> Q = null;
        GenWordPolynomial<C> S = Ap;
        while (S.length() > 0) {
            m = S.leadingMonomial();
            e = m.getKey();
            a = m.getValue();
            for (i = 0; i < l; i++) {
                mt = e.multipleOf(htl[i]);
                if (mt)
                    break;
            }
            if (!mt) {
                //logger.info("irred_1");
                R = R.sum(a, e);
                S = S.subtract(a, e);
                // System.out.println(" S = " + S);
            } else {
                Word g = e;
                Word[] elr = e.divideWord(htl[i]);
                e = elr[0];
                Word f = elr[1];
                if (false) {
                    logger.info("redRec divideWord: e = " + e + ", f = " + f);
                }
                if (e.isONE()) { 
		    C c = lbc[i];
                    //System.out.println("a = " + a + ", c = " + c);
		    a = a.divide(c);
                    //System.out.println("a/c = " + a);
		    Q = p[i].multiply(cone, e, a, f);
		    S = S.subtract(Q);
		    // left row
		    fac = rrow.get(i);
		    if (fac == null) {
			fac = zero.sum(a, f);
		    } else {
			fac = fac.sum(a, f);
		    }
		    rrow.set(i, fac);
                } else {
                    //logger.info("irred_2");
                    R = R.sum(a, g);
                    S = S.subtract(a, g);
                }
            }
        }
        return R;
    }

}
