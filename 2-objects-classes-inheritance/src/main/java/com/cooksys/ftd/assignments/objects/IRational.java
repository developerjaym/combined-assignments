package com.cooksys.ftd.assignments.objects;

interface IRational {

	/**
	 * @return the numerator of this rational number
	 */
	int getNumerator();

	/**
	 * @return the denominator of this rational number
	 */
	int getDenominator();

	/**
	 * Specializable constructor to take advantage of shared code between
	 * Rational and SimplifiedRational
	 * <p>
	 * Essentially, this method allows us to implement most of IRational methods
	 * directly in the interface while preserving the underlying type
	 *
	 * @param numerator
	 *            the numerator of the rational value to construct
	 * @param denominator
	 *            the denominator of the rational value to construct
	 * @return the constructed rational value
	 * @throws IllegalArgumentException
	 *             if the given denominator is 0
	 */
	IRational construct(int numerator, int denominator) throws IllegalArgumentException;

	/**
	 * negation of rational values
	 * <p>
	 * definition: `negate(n / d) = -n / d`
	 *
	 * @return the negation of this
	 */
	default IRational negate() {
		return construct(getNumerator() * -1, getDenominator());

	}

	/**
	 * inversion of rational values
	 * <p>
	 * definition: `invert(n / d) = d / n`
	 *
	 * @return the inversion of this
	 * @throws IllegalStateException
	 *             if the numerator of this rational value is 0
	 */
	default IRational invert() throws IllegalStateException {
		if(getNumerator() == 0)
			throw new IllegalStateException();
		return construct(getDenominator(), getNumerator());//put the d in the n spot, put the n in the d spot
	}

	/**
	 * addition of rational values
	 * <p>
	 * definition: `(n1 / d1) + (n2 / d2) = ((n1 * d2) + (n2 * d1)) / (d1 * d2)`
	 *
	 * @param that
	 *            the value to add to this
	 * @return the sum of this and that
	 * @throws IllegalArgumentException
	 *             if that is null
	 */
	default IRational add(IRational that) throws IllegalArgumentException {
		if(that == null)
			throw new IllegalArgumentException();
		int n1 = getNumerator();
		int d1 = getDenominator();
		int n2 = that.getNumerator();
		int d2 = that.getDenominator();
		return construct( ((n1 * d2) + (n2 * d1)), (d1 * d2));
	}

	/**
	 * subtraction of rational values
	 * <p>
	 * definition: `(n1 / d1) - (n2 / d2) = ((n1 * d2) - (n2 * d1)) / (d1 * d2)`
	 *
	 * @param that
	 *            the value to subtract from this
	 * @return the difference between this and that
	 * @throws IllegalArgumentException
	 *             if that is null
	 */
	default IRational sub(IRational that) throws IllegalArgumentException {
		if(that == null)
			throw new IllegalArgumentException();
		int n1 = getNumerator();
		int d1 = getDenominator();
		int n2 = that.getNumerator();
		int d2 = that.getDenominator();
		
		return construct( ((n1 * d2) - (n2 * d1)), (d1 * d2));
	}

	/**
	 * multiplication of rational values
	 * <p>
	 * definition: `(n1 / d1) * (n2 / d2) = (n1 * n2) / (d1 * d2)`
	 *
	 * @param that
	 *            the value to multiply this by
	 * @return the product of this and that
	 * @throws IllegalArgumentException
	 *             if that is null
	 */
	default IRational mul(IRational that) throws IllegalArgumentException {
		if(that == null)
			throw new IllegalArgumentException();
		int n1 = getNumerator();
		int d1 = getDenominator();
		int n2 = that.getNumerator();
		int d2 = that.getDenominator();
		
		return construct( (n1 * n2), (d1 * d2));
	}

	/**
	 * division of rational values
	 * <p>
	 * definition: `(n1 / d1) / (n2 / d2) = (n1 * d2) / (d1 * n2)`
	 *
	 * @param that
	 *            the value to divide this by
	 * @return the ratio of this to that
	 * @throws IllegalArgumentException
	 *             if that is null or if the numerator of that is 0
	 */
	default IRational div(IRational that) throws IllegalArgumentException {
		if(that == null)
			throw new IllegalArgumentException();
		int n1 = getNumerator();
		int d1 = getDenominator();
		int n2 = that.getNumerator();
		int d2 = that.getDenominator();
		
		return construct( (n1 * d2), (d1 * n2));
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
    default int[] simplifyNegatives(int numerator, int denominator)
    {
    	if(denominator < 0)
        {//simplify the negatives
    		numerator = -1*numerator;
    		denominator = -1*denominator;
        }
    	return new int[]{numerator, denominator};
    }
}
