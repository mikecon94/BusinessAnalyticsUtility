import java.util.Scanner;

/*
	A class to help do some calculations.
	TODO:
	-Change from ints to doubles for the xArray.
	-Allow the calculations without frequences.	
*/

public class Stats{

	public static void main(String[] args) throws Exception{
		System.out.print("Enter your x values: ");
		int[] xArray = parseInput();
		System.out.print("Enter the frequencies: ");
		int[] fArray = parseInput();
		if(fArray.length != xArray.length){
			System.out.println("There must be the same number of x values as frequencies!");
			throw new Exception();
		}

		double mean = calcMean(xArray, fArray);
		double sd = calcStandardDev(xArray, fArray);
		System.out.println("The mean is: " + mean);
		System.out.println("The Standard Deviation is: " + sd);
	}

	private static double calcStandardDev(int[] xArray, int[] fArray){
		double sd = calcSDNumerator(xArray, fArray);
		sd = sd / sumFrequencies(fArray);
		sd = Math.sqrt(sd);
		return sd;
	}

	private static double calcSampleStandardDev(int[] xArray, int[] fArray){
		double ssd = calcSDNumerator(xArray, fArray);
		ssd = ssd / (sumFrequencies(fArray) - 1);
		ssd = Math.sqrt(ssd);
		return ssd;
	}

	private static double sumFrequencies(int[] fArray){
		double sum = 0;
		for (int i = 0; i < fArray.length; i++){
			sum += fArray[i];
		}
		return sum;
	}

	//Utility method for calculating the numerator
	//of the Standard Deviation calculation.
	private static double calcSDNumerator(int[] xArray, int[] fArray){
		double numerator = 0;
		double mean = calcMean(xArray, fArray);
		for (int i = 0; i < xArray.length; i++){
			double diff = xArray[i] - mean;
			double diffSqrd = diff * diff;
			numerator += diffSqrd * fArray[i];
		}
		return numerator;
	}

	private static double calcMean(int[] xArray, int[] fArray){
		double mean = 0;
		for(int i = 0; i < xArray.length; i++){
			double weighting = xArray[i] * fArray[i];
			mean += weighting;
		}
		mean = mean / sumFrequencies(fArray);
		return mean;
	}

	private static int[] parseInput(){
		Scanner reader = new Scanner(System.in);
		String xValues = reader.nextLine();
		String[] stringArray = xValues.split(" ");
		int[] xArray = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			try {
		        	xArray[i] = Integer.parseInt(stringArray[i]);
			} catch (NumberFormatException nfe) {
				System.out.println(stringArray[i] +  " Is not a valid number.");
			};
		}
		return xArray;
	}
}
