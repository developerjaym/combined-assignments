package com.cooksys.ftd.assignments.control;

import java.util.HashMap;

/**
 * The Fibonacci sequence is simply and recursively defined: the first two elements are `1`, and
 * every other element is equal to the sum of its two preceding elements. For example:
 * <p>
 * [1, 1] =>
 * [1, 1, 1 + 1]  => [1, 1, 2] =>
 * [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] =>
 * ...etc
 */
public class Fibonacci {
	static HashMap<Integer, Integer> foundValues = new HashMap<>();
    /**
     * Calculates the value in the Fibonacci sequence at a given index. For example,
     * `atIndex(0)` and `atIndex(1)` should return `1`, because the first two elements of the
     * sequence are both `1`.
     *
     * @param i the index of the element to calculate
     * @return the calculated element
     * @throws IllegalArgumentException if the given index is less than zero
     */
    public static int atIndex(int i) throws IllegalArgumentException {
        if(i < 0)
        	throw new IllegalArgumentException();
        if(foundValues.containsKey(i))//if we've already calculated this Fibonacci number, it will be in our HashMap already
        	return (int) foundValues.get(i);//to prevent long loops
        int value;//will hold the value of the Fibonacci number
        if(i == 0 || i == 1)
        	value = 1;//the first two Fibonacci numbers have value 1
        else
        	value = atIndex(i-2) + atIndex(i-1);//the value of a Fibonacci number is the sum of the last two Fibonacci numbers
       // System.out.println("i: " + i + " value: " + value);
        foundValues.put(i, value);//now that it's calculated, let's put it into the HashMap for future convenience
        //System.out.println("Index: " + i + "   Answer: " + value);
        return value;
    }

    /**
     * Calculates a slice of the fibonacci sequence, starting from a given start index (inclusive) and
     * ending at a given end index (exclusive).
     *
     * @param start the starting index of the slice (inclusive)
     * @param end   the ending index of the slice(exclusive)
     * @return the calculated slice as an array of int elements
     * @throws IllegalArgumentException if either the given start or end is negative, or if the
     *                                  given end is less than the given start
     */
    public static int[] slice(int start, int end) throws IllegalArgumentException {
        if(start < 0 || end < 0 || end < start)//the given start or end is negative or the the given end is less than the given start
        	throw new IllegalArgumentException();
        int[] returnArr = new int[end - start];//make the array
        int counter = 0;//make the counter to help put everything into the right index
        for(int i = start; i < end; i++)//go through the given range
        {
        	returnArr[counter] = atIndex(i);//add the value to the array at the appropriate index
        	counter++;//go to the next index
        }
        return returnArr;
    }

    /**
     * Calculates the beginning of the fibonacci sequence, up to a given count.
     *
     * @param count the number of elements to calculate
     * @return the beginning of the fibonacci sequence, up to the given count, as an array of int elements
     * @throws IllegalArgumentException if the given count is negative
     */
    public static int[] fibonacci(int count) throws IllegalArgumentException {
        if(count < 0)
        	throw new IllegalArgumentException();
        
    	return slice(0, count);//return the slice from the first Fibonacci number to the count-th Fibonacci number
    }
}
