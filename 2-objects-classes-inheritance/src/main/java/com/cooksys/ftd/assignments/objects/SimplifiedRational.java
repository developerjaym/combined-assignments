package com.cooksys.ftd.assignments.objects;

public class SimplifiedRational implements IRational {
	int numerator;
	int denominator;
    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
    public static int gcd(int a, int b) throws IllegalArgumentException {
        if(a <= 0 || b < 0)
        	throw new IllegalArgumentException();
        int smallest = Math.min(a, b);//I think these need to be in order
        int biggest = Math.max(a, b);
        if(smallest == 0)//we've found our gcd
        	return biggest;
        return gcd(smallest, biggest%smallest);//keep looking
        
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
        if(denominator == 0)
        	throw new IllegalArgumentException();
        
        int divideBy = gcd(Math.abs(numerator), Math.abs(denominator));//get the greatest common denominator (gcd)
        return new int[]{numerator/divideBy, denominator/divideBy};//divide each by the gcd
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
    	if(denominator == 0)
    		throw new IllegalArgumentException();
    	
    	//set the fields
        this.numerator = numerator;
        this.denominator = denominator;
        
        if(numerator == 0)//no further simpification can be performed
        	return;
        
        int[] arr = simplify(numerator, denominator);//get the values of the numerator and denominator simplified
        this.numerator = arr[0];
        this.denominator = arr[1];
        
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
    	if(denominator < 0)//the denominator has a negative sign, which is weird
        {//simplify the negatives
    		numerator = -1*numerator;
    		denominator = -1*denominator;
        }
    	return new int[]{numerator, denominator};//the negative signs are now in the right place
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
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
    	if(denominator == 0)
    		throw new IllegalArgumentException();
        return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof SimplifiedRational)
        {
    		SimplifiedRational other = (SimplifiedRational) obj;
        	if(other.toString().equals(this.toString()))
        		return true;//they are of the same class and the toStrings are the same. 
        					//The num and den are already simplified and the toString method calls simplifyNegatives() to put the negative sign in the right place
        }
        return false;
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
    	int[] arr = simplifyNegatives(getNumerator(), getDenominator());
    	return arr[0] + "/" + arr[1];
    }
}
