import java.util.Scanner;
import java.util.Arrays;
/*
	A class to help do some calculations.
	TODO:
	-Change from ints to doubles for the xArray.
	-Allow the calculations without frequences.
	-Add the median & lower quartile range.
*/

public class Stats{

	public static void main(String[] args) throws Exception{
		System.out.print("Enter your x values: ");
		double[] xArray = parseInput();
		if(xArray == null){
			System.out.println("Must enter x values");
			throw new Exception();
		}
		System.out.print("Enter the frequencies: ");

		//Realistically frequencies can't be a decimal.
		//But we are reusing parseInput which returns a double...
		double[] fArray = parseInput();
		if(fArray == null){
			fArray = new double[xArray.length];
			Arrays.fill(fArray, 1);
		}

		if(fArray.length != xArray.length){
			System.out.println("There must be the same number of x values as frequencies!");
			throw new Exception();
		}

		double mean = calcMean(xArray, fArray);
		double sd = calcStandardDev(xArray, fArray);
		double ssd = calcSampleStandardDev(xArray, fArray);
		System.out.println("The mean is: " + mean);
		System.out.println("The Standard Deviation is: " + sd);
		System.out.println("The Sample Standard Deviation is: " + ssd);
	}

	private static double calcMedian(double[] xArray, double[] fArray){
		return 0;
	}

	private static double calcLowerQuartile(double[] xArray, double[] fArray){
			return 0;
	}

	private static double calcStandardDev(double[] xArray, double[] fArray){
		double sd = calcSDNumerator(xArray, fArray);
		sd = sd / sumFrequencies(fArray);
		sd = Math.sqrt(sd);
		return sd;
	}

	private static double calcSampleStandardDev(double[] xArray, double[] fArray){
		double ssd = calcSDNumerator(xArray, fArray);
		ssd = ssd / (sumFrequencies(fArray) - 1);
		ssd = Math.sqrt(ssd);
		return ssd;
	}

	/*
		Sums up the totals of the frequencies.
		This has been extracted as it is used as (at least part of)
		the denominator on both Standard Deviation and Sample Standard Deviation.
	*/
	private static double sumFrequencies(double[] fArray){
		double sum = 0;
		for (int i = 0; i < fArray.length; i++){
			sum += fArray[i];
		}
		return sum;
	}

	/*Utility method for calculating the numerator of the Standard Deviation
	 	calculation.
		This part has been extracted because it is used for both
		Standard Deviation & Standard Deviation.
	*/
	private static double calcSDNumerator(double[] xArray, double[] fArray){
		double numerator = 0;
		//Get the mean to be used for calculating the differences of each x value.
		double mean = calcMean(xArray, fArray);

		//For each x value we work out the difference between x and the mean
		//We square each of these values and multiple by the frequency
		// and add them up.
		for (int i = 0; i < xArray.length; i++){
			double diff = xArray[i] - mean;
			double diffSqrd = diff * diff;
			numerator += diffSqrd * fArray[i];
		}
		return numerator;
	}

	private static double calcMean(double[] xArray, double[] fArray){
		double mean = 0;
		for(int i = 0; i < xArray.length; i++){
			double weighting = xArray[i] * fArray[i];
			mean += weighting;
		}
		mean = mean / sumFrequencies(fArray);
		return mean;
	}

	/*
		Takes a list of numbers on the command line and returns them as an array
		of integers.
	*/
	private static double[] parseInput(){
		Scanner reader = new Scanner(System.in);
		String values = reader.nextLine();
		if (values.equals("")){
			return null;
		}
		String[] stringArray = values.split(" ");
		double[] array = new double[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			try {
		        	array[i] = Double.parseDouble(stringArray[i]);
			} catch (NumberFormatException nfe) {
				System.out.println(stringArray[i] +  " Is not a valid number.");
			};
		}
		return array;
	}
}
