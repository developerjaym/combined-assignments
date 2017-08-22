package com.cooksys.ftd.assignments.objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        int smallest = Math.abs(a);
        int biggest = Math.abs(b);
        if(b < a)
        {
        	smallest = b;
        	biggest = a;
        }
        else if(b == a)
        {
        	return a;//or b
        }
        if(smallest == 0)
        	return biggest;
        return gcd(smallest, biggest%smallest);
        
        /*
        int smallest = a;
        int biggest = b;
        if(b < a)
        {
        	smallest = b;
        	biggest = a;
        }
        else if(b == a)
        {
        	return a;//or b
        }
        if(biggest%smallest == 0)
        	return smallest;
        
        int gcd = 1;
        for(int i = smallest; i >= 1; i--)
        //for(int i = 1; i <= smallest; i++)
        {
        	if(smallest % i == 0 && biggest % i == 0)
        	{
        		System.out.println("for " + a + ", " + b + ", the gcd: " + i);
        		//gcd = i;
        		return i;
        	}
        }
        return gcd;*/
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
        
        int divideBy = gcd(Math.abs(numerator), Math.abs(denominator));
        //if(numerator == 0)
        //	return new int[]{0, 1};
        return new int[]{numerator/divideBy, denominator/divideBy};
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
    	
        this.numerator = numerator;
        this.denominator = denominator;
        
        if(numerator == 0)
        	return;
        
        int divideBy = gcd(Math.abs(numerator),Math.abs( denominator));
        System.out.println("gcd: " + divideBy);
        this.numerator = numerator/divideBy;
        this.denominator = denominator/divideBy;
        
        /*if(denominator < 0)
        {//simplify the negatives
        	this.numerator = -1*this.numerator;
        	this.denominator = -1*this.denominator;
        }*/
        System.out.println(" up here");
        System.out.println("   n: " + this.numerator);
        System.out.println("   d: " + this.denominator);
        
    }
    private void simplifyNegatives()
    {
    	if(denominator < 0)
        {//simplify the negatives
        	this.numerator = -1*this.numerator;
        	this.denominator = -1*this.denominator;
        }
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
        		return true;
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
    	String returnMe = "";
    	simplifyNegatives();
    	System.out.println("n: " + this.getNumerator());
    	System.out.println("d: " + this.getDenominator());
    	if((this.getNumerator() >= 0 && this.getDenominator() >= 0) || (this.getNumerator() <= 0 && this.getDenominator() >= 0))//both positive or above 0
    	{
    		returnMe = getNumerator() + "/" + getDenominator();
    	}
        else if(this.getNumerator() < 0 && this.getDenominator() < 0)//num negative, den negative
        {
        	returnMe =  -1*this.getNumerator() + "/" + -1*this.getDenominator();
        }
        else if(this.getDenominator() < 0 && this.getNumerator() >= 0)//den negative, num positive
        {
        	returnMe =  "-" + -1*this.getNumerator() + "/" + -1*this.getDenominator();
        }
        else//both negative
        {
        	returnMe =  "-" + -1*this.getNumerator() + "/" + -1*this.getDenominator();
        }
    	System.out.println("to string: " + returnMe);
    	return returnMe;
    }
    public static void main(String[] args)
    {
    	SimplifiedRational sr = new SimplifiedRational(-10000, -20010);
    	System.out.println(sr.toString());
    	
    }
}
