/**
 * LAB 3 -  Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import edu.cuny.csi.csc330.util.Randomizer;
import java.util.Arrays;
import java.util.Date;
import java.math.BigInteger;

public class LottoQuickPicker {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "Lotto"; 
	private final static int SELECTION_POOL_SIZE = 59; 
	private final static int SELECTION_COUNT = 6;
	private final static String STORE_NAME = "S.I. Corner Deli";
	private static int [][] lottoArray;

	

	public LottoQuickPicker() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public LottoQuickPicker(int games) {
		init(games);
	}

	private void init(int games) {
		lottoArray = new int[games][SELECTION_COUNT];
	}

	//Method to generate unique lottegamry numbers arrays
	private static void generateUniqueLottoNumbers() {
		int temp = 0;
		
		for (int i = 0; i < lottoArray.length; i++) {
			for (int j = 0; j < lottoArray[i].length; j++) {
				//check for duplicates
				while(checkForDuplicate(lottoArray, temp)) {
					temp = Randomizer.generateInt(1, SELECTION_POOL_SIZE); //Generate numbers from 1-59
				}
				lottoArray[i][j] = temp;
			}
			Arrays.sort(lottoArray[i]);
		}
		
	}
	
	//Method to check for duplicate numbers
	private static boolean checkForDuplicate(int[][] checkArray, int checkNum) {
		for (int i = 0; i < checkArray.length; i++) {
			for (int j = 0; j < checkArray[i].length; j++) {
				if (checkArray[i][j] == checkNum)
					return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	
	public void displayTicket() {
		
		/**
		 * display heading 
		 * 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * 
		 * display footer 
		 */
		
		// display ticket heading 
		displayHeading();
		

		/**
		 * Display selected numbers 
		 */
		generateUniqueLottoNumbers();
		for (int i = 0; i < lottoArray.length; i++) {
			System.out.printf("(%2d) ", i + 1);
			for (int j = 0; j < lottoArray[i].length; j++) {
				System.out.printf(" %02d", lottoArray[i][j]);
			}
			System.out.println();
			
		}

		// display ticket footer 
		displayFooter(); 
		
		return;
	}

	
	
	protected void displayHeading() {
		System.out.println("---------------------------------\n------------  " + GAME_NAME.toUpperCase() + " ------------");
		
		//Date Class/Object - current date/time
		Date today = new Date();
		System.out.println("  " + today + "\n");
	}
	
	protected void displayFooter() {
		BigInteger calcOdd = calculateOdds();
		System.out.printf("\nOdds of winning: 1 in %,d\n", calcOdd);
		
		System.out.println("----- (c) " + STORE_NAME + " -----\n---------------------------------");
		
	}
	

	
	/**
	 * EXTRA CREDIT:
	 * To calculate the odds of winning, use the combination formula:
	 * nCr = n! / r!*(n-r)!
	 * n = SELECTION_POOL_SIZE (59)
	 * r = SELECTION_COUNT (6)
	 * 59! / 6!*(59-6)! = 45,057,474
	 * 
	 * Used BigInteger Class to perform arithmetic operations involving
	 * very big integers
	 * @return 
	 */
	private BigInteger calculateOdds() {
		BigInteger n = factorial(SELECTION_POOL_SIZE);
		BigInteger r = factorial(SELECTION_COUNT);
		BigInteger nSubR = factorial(SELECTION_POOL_SIZE - SELECTION_COUNT);
		BigInteger result;
		
		result = n.divide(r.multiply(nSubR));
		
		return result;
	}
	
	//Method to calculate factorial of a number with BigInteger
    private static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        return factorial;
    }
	

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = DEFAULT_GAME_COUNT; 
		
		if(args.length > 0) {  // if user provided an arg, assume it to be a game count
			numberOfGames = Integer.parseInt(args[0]);  // [0] is the 1st element!
		}
		
		LottoQuickPicker lotto = new LottoQuickPicker(numberOfGames);
		 
		lotto.displayTicket(); 
		

	}

}


//private static int genpick = 0;//selection in pool1
//private static int range1 = 0;// pool1size
//private static int specpick = 0;//selection in pool2
//private static int range2 = 0;//pool21size
