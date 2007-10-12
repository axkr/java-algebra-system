/*
 * $Id$
 */

package edu.jas.ring;

import java.util.List;

import java.io.Serializable;

import edu.jas.poly.ExpVector;
import edu.jas.poly.GenPolynomial;
import edu.jas.structure.RingElem;


/**
 * Polynomial Reduction interface.
 * Defines S-Polynomial, normalform, criterion 4, module criterion
 * and irreducible set.
 * @author Heinz Kredel
 */

public interface Reduction<C extends RingElem<C>> 
                 extends Serializable {


    /**
     * S-Polynomial.
     * @typeparam C coefficient type.
     * @param Ap polynomial.
     * @param Bp polynomial.
     * @return spol(Ap,Bp) the S-polynomial of Ap and Bp.
     */
    public GenPolynomial<C> SPolynomial(GenPolynomial<C> Ap, 
                                        GenPolynomial<C> Bp);


    /**
     * S-Polynomial with recording.
     * @typeparam C coefficient type.
     * @param S recording matrix, is modified.
     * @param i index of Ap in basis list.
     * @param Ap a polynomial.
     * @param j index of Bp in basis list.
     * @param Bp a polynomial.
     * @return Spol(Ap, Bp), the S-Polynomial for Ap and Bp.
     */
    public GenPolynomial<C> 
           SPolynomial(List<GenPolynomial<C>> S,
                       int i,
                       GenPolynomial<C> Ap, 
                       int j,
                       GenPolynomial<C> Bp);


    /**
     * Module criterium.
     * @typeparam C coefficient type.
     * @param modv number of module variables.
     * @param A polynomial.
     * @param B polynomial.
     * @return true if the module S-polynomial(i,j) is required.
     */
    public boolean moduleCriterion(int modv, 
                                   GenPolynomial<C> A, 
                                   GenPolynomial<C> B);


    /**
     * GB criterium 4.
     * Use only for commutative polynomial rings.
     * @typeparam C coefficient type.
     * @param A polynomial.
     * @param B polynomial.
     * @param e = lcm(ht(A),ht(B))
     * @return true if the S-polynomial(i,j) is required, else false.
     */
    public boolean criterion4(GenPolynomial<C> A, 
                              GenPolynomial<C> B, 
                              ExpVector e);


    /**
     * GB criterium 4.
     * Use only for commutative polynomial rings.
     * @typeparam C coefficient type.
     * @param A polynomial.
     * @param B polynomial.
     * @return true if the S-polynomial(i,j) is required, else false.
     */
    public boolean criterion4(GenPolynomial<C> A, 
                              GenPolynomial<C> B);


    /**
     * Is top reducible.
     * @typeparam C coefficient type.
     * @param A polynomial.
     * @param P polynomial list.
     * @return true if A is top reducible with respect to P.
     */
    public boolean isTopReducible(List<GenPolynomial<C>> P, 
                                  GenPolynomial<C> A);


    /**
     * Is reducible.
     * @typeparam C coefficient type.
     * @param A polynomial.
     * @param P polynomial list.
     * @return true if A is reducible with respect to P.
     */
    public boolean isReducible(List<GenPolynomial<C>> P, 
                               GenPolynomial<C> A);


    /**
     * Is in Normalform.
     * @typeparam C coefficient type.
     * @param A polynomial.
     * @param P polynomial list.
     * @return true if A is in normalform with respect to P.
     */
    public boolean isNormalform(List<GenPolynomial<C>> P, 
                                GenPolynomial<C> A);


    /**
     * Normalform.
     * @typeparam C coefficient type.
     * @param A polynomial.
     * @param P polynomial list.
     * @return nf(A) with respect to P.
     */
    public GenPolynomial<C> normalform(List<GenPolynomial<C>> P, 
                                       GenPolynomial<C> A);


    /**
     * Normalform Set.
     * @typeparam C coefficient type.
     * @param Ap polynomial list.
     * @param Pp polynomial list.
     * @return list of nf(a) with respect to Pp for all a in Ap.
     */
    public List<GenPolynomial<C>> normalform(List<GenPolynomial<C>> Pp, 
                                             List<GenPolynomial<C>> Ap);


    /**
     * Normalform with recording.
     * @typeparam C coefficient type.
     * @param row recording matrix, is modified.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @return nf(Pp,Ap), the normal form of Ap wrt. Pp.
     */
    public GenPolynomial<C> 
           normalform(List<GenPolynomial<C>> row,
                      List<GenPolynomial<C>> Pp, 
                      GenPolynomial<C> Ap);


    /**
     * Irreducible set.
     * @typeparam C coefficient type.
     * @param Pp polynomial list.
     * @return a list P of polynomials which are in normalform wrt. P.
     */
    public List<GenPolynomial<C>> irreducibleSet(List<GenPolynomial<C>> Pp);



    /**
     * Is reduction of normal form.
     * @param row recording matrix, is modified.
     * @param Pp a polynomial list for reduction.
     * @param Ap a polynomial.
     * @param Np nf(Pp,Ap), a normal form of Ap wrt. Pp.
     * @return true, if Np + sum( row[i]*Pp[i] ) == Ap, else false.
     */

    public boolean 
           isReductionNF(List<GenPolynomial<C>> row,
                         List<GenPolynomial<C>> Pp, 
                         GenPolynomial<C> Ap,
                         GenPolynomial<C> Np);

}
