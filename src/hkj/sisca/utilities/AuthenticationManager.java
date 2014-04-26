package hkj.sisca.utilities;
import java.util.Random;

/**
 * This class generates and checks random 16 digit identification for validation and use as 
 * authentication features for SAD and CAS software. The algorithm used is the Luhn algorithm.
 * @author hfranqui
 *
 */
public class AuthenticationManager {

	/**
	 * Generates a 16 digit random identification ID
	 * @return The randomly-generated 16 digit number 
	 */
	public long generateAuthenticationID() {
		
		Random generator = new Random();
		int beginDigit = 0;
		
		// Generate first digit and avoid to generate a credit card number
		do  {
			beginDigit = 1 + generator.nextInt(9);
		}
		while (beginDigit == 3 || beginDigit == 4 || beginDigit == 5 || beginDigit == 6);
		
		int firstDigits = (beginDigit * 1000) + (100 + (int) (generator.nextFloat() * 900));
		
		// Begin appending the first 4 digits to the authentication ID
		StringBuilder sb = new StringBuilder();
		sb.append(firstDigits);
		
		// Generate the next 11 digits
		for (int i = 0; i < 2; i++) {
			int temp = 1000 + ((int) (generator.nextFloat() * 9000));
			sb.append(temp);
		}
		sb.append(100 + ((int) generator.nextFloat() * 9000));
		
		// Generate check digit, the last digit
		String temp = Integer.toString(9 * getDigitsSum(sb.toString() + "0"));
		sb.append(temp.charAt(temp.length()-1));
		
		// For debug purposes
		//System.out.println("Number: " + sb.toString());
		
		return Long.parseLong(sb.toString());
	}
	
	/**
	 * Checks if a given authentication ID is valid. The validation is performed using the Luhn algorithm 
	 * @param id The authentication ID to check for validity
	 * @return True if the authentication ID is valid, false otherwise
	 */
	public boolean checkAuthenticationIDValidity(long id) {
		return this.getDigitsSum(Long.toString(id)) % 10 == 0;
	}
	
	/**
	 * Computes the sum of the digits of a given number. This sum is computed according to the Luhn algorithm
	 * @param number The number to check for, in String format
	 * @return The resulting sum of the digits as given by the Luhn algorithm
	 */
	private int getDigitsSum(String number) {
		int evenSum = 0, oddSum = 0;
		
		// Calculates the sum from the right most digit and two each after
		for (int i = number.length()-1; i >= 0; i = i - 2) {
			evenSum = evenSum + Integer.parseInt(number.charAt(i) + "");
			// For debug purposes
			//System.out.println(number.charAt(i));
		}
		
		// Gets the other numbers, doubles them and stores it in an String variable
		StringBuilder sb = new StringBuilder();
		for (int i = number.length()-2; i >= 0; i = i - 2) {
			// For debug purposes
			//System.out.println("Single: " + number.charAt(i) + ". Double: " + (2 * Integer.parseInt(number.charAt(i) + "")));
			sb.append(2 * Integer.parseInt(number.charAt(i) + ""));
			
		}
		// For debug purposes
		//System.out.println("Resulting: " + sb.toString());
		
		// Sums all digits of the generated String resulting of doubling numbers
		for (int i = 0; i < sb.toString().length(); i++) {
			//System.out.println("oddsum: " + oddSum);
			oddSum = oddSum + (Integer.parseInt(sb.toString().charAt(i) + ""));
			//System.out.println(sb.toString().charAt(i));
		}

		// For debug purposes
		//System.out.println(oddSum + evenSum);
		return evenSum + oddSum;
	}

}
