package com.cooksys.ftd.assignments.objects;

public class Rational implements IRational {
	int numerator;
	int denominator;
    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * No simplification of the numerator/denominator pair should occur in this method.
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public Rational(int numerator, int denominator) throws IllegalArgumentException {
    	if(denominator == 0)
    		throw new IllegalArgumentException();
    	this.numerator = numerator;
    	this.denominator = denominator;
    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {
        return numerator;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {
    	return denominator;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public Rational construct(int numerator, int denominator) throws IllegalArgumentException {
    	if(denominator == 0)
    		throw new IllegalArgumentException();
        return new Rational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rational)
        {
        	Rational other = (Rational) obj;
        	if(other.getNumerator() == this.getNumerator() && other.getDenominator() == this.getDenominator())
        		return true;//they are of the same class and the numerator and denominator are the same
        }
        return false;
    }
    /**
     * Simplifies the numerator and denominator of a rational value, only regarding the negative sign.
     * <p>
     * For example:
     * `simplifyNegatives(1, 10) = [1, 10]`
     * or:
     * `simplify(1, -10) = [-1, 10]`
     *
     * @param numerator the numerator of the IRational
     * @param denominator the denominator of the IRational
     * @return an int[] array holding the changed (simplified) version of the numerator and denominator
     */
    private int[] simplifyNegatives(int numerator, int denominator)
    {
    	if(denominator < 0)
        {//simplify the negatives
    		numerator = -1*numerator;
    		denominator = -1*denominator;
        }
    	return new int[]{numerator, denominator};
    }
    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    @Override
    public String toString() {
    	int[] arr = simplifyNegatives(getNumerator(), getDenominator());//put the negative sign in the right place
    	return arr[0] + "/" + arr[1];
    }
}
