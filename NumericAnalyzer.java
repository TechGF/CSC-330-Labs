/*
 * Gary Feng
 * CSC 330
 * Lab 2: Numeric Analyzer
 * */
package edu.cuny.csi.csc330.lab2;

import java.lang.Math;
import java.util.Arrays;

public class NumericAnalyzer {

	// empty constructor
    public NumericAnalyzer() {

    }
    
    // Sorts unsorted numbers array
	public void sortArray(int[] numbers) {
		Arrays.sort(numbers);
	}
	
	// Stores the array length in count
	public int getCount(int[] numbers) {
		int count = numbers.length;
		return count;
	}
	
	// Get minimum value
	public int getMin(int[] numbers) {
		int min = numbers[0];
		return min;
	}

	// Get max value
	public int getMax(int[] numbers) {
		int max = numbers[numbers.length - 1];
		return max;
	}
	
	// Calculating the range
	public int getRange(int[] numbers) {
		int range = getMax(numbers) - getMin(numbers);
		return range;
	}

	// Calculating the sum
	public int getSum(int[] numbers) {
		int sum = 0;
		for (int value : numbers) {
			sum += value;
		}
		return sum;
	}

	// Calculating the mean
	public int getMean(int[] numbers) {
		int mean = getSum(numbers) / numbers.length;
		return mean;
	}

	// Calculating the median
	public int getMedian(int[] numbers) {
		int median;
		if (numbers.length % 2 == 0) { //even set of numbers
			median = (numbers[numbers.length / 2] + numbers[numbers.length / 2 - 1]) / 2;
		} else { //odd set of numbers
			median =  numbers[numbers.length / 2];
		}
		return median;
	}

	// Calculating the variance
	public double getVariance(int[] numbers) {
		double variance = 0;
		for (int value : numbers) {
			variance += Math.pow(value - getMean(numbers), 2);
		}
		variance = variance / numbers.length;
		return variance;
	}

	// Calculating the standard deviation
	public double getStandardDeviation(int[] numbers) { 
		double standardDeviation;
		standardDeviation = Math.sqrt(getVariance(numbers)); 
		return standardDeviation; 
	}
	
	// Error checking: Flags non-numeric parameters
	public static boolean isNumeric(String values) {
		try {
			@SuppressWarnings("unused")
			int num = Integer.parseInt(values);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	 
	// Calling all methods
	public void analyze(int[] numbers) {
		sortArray(numbers);
		getCount(numbers);
		getMin(numbers);
		getMax(numbers);
		getRange(numbers);
		getSum(numbers);
		getMean(numbers);
		getMedian(numbers);
		getVariance(numbers);
		getStandardDeviation(numbers);
	}

	// Display the numbers arrays and methods
	public void display(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
        	System.out.printf("%,d   ", numbers[i]);
        }
        System.out.printf("\n\n");
        System.out.printf("%-30s %,10d\n", "Count:", getCount(numbers));
        System.out.printf("%-30s %,10d\n", "Min:", getMin(numbers));
        System.out.printf("%-30s %,10d\n", "Max:", getMax(numbers));
        System.out.printf("%-30s %,10d\n", "Range:", getRange(numbers));
        System.out.printf("%-30s %,10d\n", "Sum:", getSum(numbers));
        System.out.printf("%-30s %,10d\n", "Mean:", getMean(numbers));
        System.out.printf("%-30s %,10d\n", "Median:", getMedian(numbers));
        System.out.printf("%-30s %,10d\n", "Variance:", (int)getVariance(numbers));
        System.out.printf("%-30s %,10d\n", "Standard Deviation:", (int)getStandardDeviation(numbers));
	}
	
	public static void main(String[] args) {
		// main() method code fragment example 
		if(args.length == 0 ) {
		// Display some error message … (System.err. )
			System.err.printf("Pass in 1 or more positive integer number values.");
			System.exit(1); 
		}

		// create an integer array 
		int [] numbers = new int[args.length]; 
		for(int i = 0 ; i < args.length ; ++i ) {
			
		  ///////////////////////////
		  ///// EXTRA CREDIT FEATURE 
		  if(!NumericAnalyzer.isNumeric(args[i])) {
			System.err.println("Expecting Numeric Data: " + args[i]);
			System.exit(2); // exit code for invalid data 
		  }
		  //////////////////////////
		  
		  numbers[i] = Integer.parseInt(args[i]); 
		}

		NumericAnalyzer analyzer = new NumericAnalyzer();
		
		// Purpose of this is to separate analyze and display
		analyzer.analyze(numbers); 
		analyzer.display(numbers); 
		System.exit(0); 

	}

}
