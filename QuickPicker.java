package edu.cuny.csi.csc330.lab6;

import edu.cuny.csi.csc330.util.Randomizer;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class QuickPicker {
	public final static int DEFAULT_GAME_COUNT = 1;
	private static String PROP_FILE_NAME;
	private static String GAME_NAME = "Lotto";
	private static String VENDOR;

	private static int gameCount = DEFAULT_GAME_COUNT;
	private static int selectionPoolSize = 0;
	private static int selectionCount = 0;
	private static int selectionPoolSizeTwo = 0;
	private static int selectionCountTwo = 0;

	public QuickPicker(int gameCount) throws QuickPickerException {
		init(gameCount);
	}

	private void init(int games) throws QuickPickerException {
		gameCount = games;
		initFromPropBundle();
	}

	private static void initFromPropBundle() throws QuickPickerException {
		
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(PROP_FILE_NAME);
			
			if (bundle.containsKey("GameName")) {
				GAME_NAME = bundle.getString("GameName");
			} else {
				throw new QuickPickerException("Can't find: " + PROP_FILE_NAME, QuickPickerException.INVALID_GAMENAME);
			}
			if (bundle.containsKey("Pool1")) {
				selectionCount = Integer.parseInt(bundle.getString("Pool1").split("/")[0]);
				selectionPoolSize = Integer.parseInt(bundle.getString("Pool1").split("/")[1]);
			} else {
				throw new QuickPickerException("Can't find: " + PROP_FILE_NAME, QuickPickerException.INVALID_POOL1);
			}
			if (bundle.containsKey("Pool2")) {
				selectionCountTwo = Integer.parseInt(bundle.getString("Pool2").split("/")[0]);
				selectionPoolSizeTwo = Integer.parseInt(bundle.getString("Pool2").split("/")[1]);
			} else {
				throw new QuickPickerException("Can't find: " + PROP_FILE_NAME, QuickPickerException.INVALID_POOL2);
			}
			if (bundle.containsKey("Vendor")) {
				VENDOR = bundle.getString("Vendor");
			} else {
				throw new QuickPickerException("Can't find: " + PROP_FILE_NAME, QuickPickerException.INVALID_VENDOR);
			}
		} catch (QuickPickerException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	// Method to generate unique lotto numbers
	private int[] generateLottoNumbers() {
		int count = selectionCount + selectionCountTwo;
		int selection[] = new int[count];

		for (int i = 0; i < selectionCount; i++) {
			selection[i] = Randomizer.generateInt(1, selectionPoolSize);
			if (i > 0) {
				if (checkForDuplicate(selection, selection[i], i) == true) {
					continue;
				}
			}
		}

		for (int j = selectionCount; j < count; j++) {
			selection[j] = Randomizer.generateInt(1, selectionPoolSizeTwo);
			if (j > 0) {
				if (checkForDuplicate(selection, selection[j], j) == true) {
					continue;
				}
			}
		}

		Arrays.sort(selection, 0, selectionCount);
		Arrays.sort(selection, selectionCount, count);

		return selection;
	}

	// Method to check of duplicate numbers
	private boolean checkForDuplicate(int[] selection, int value, int endIndex) {
		for (int i = 0; i < endIndex; i++) {
			if (value == selection[i])
				return true;
		}
		return false;
	}

	// Method to display heading, generate game and footer
	public void displayTicket() {
		generateHeading();
		for (int i = 0; i < gameCount; i++) {
			int selection[] = generateLottoNumbers();
			generateGame(i + 1, selection);
		}
		generateFooter();
	}

	// Method to display heading
	private void generateHeading() {
		System.out.println("-------------------------------------\n--------  <<" + GAME_NAME + ">> ---------");

		// Date Class/Object - current date/time
		Date today = new Date();
		System.out.println("  " + today + "\n");
	}

	// Method to display Lotto Numbers
	private void generateGame(int index, int[] selection) {
		if (index != 0)
			System.out.printf(" (%2d) ", index);
		for (int i = 0; i < selection.length; i++) {
			if (i >= selectionCount) {
				System.out.printf(" (( %02d  ))", selection[i]);
			} else {
				System.out.printf(" %02d ", selection[i]);
			}
		}
		System.out.println();
	}

	// Method to display footer
	private void generateFooter() {
		long calcOdd = calculateOdds();
		System.out.printf("\nOdds of winning: 1 in <<%,d>>\n", calcOdd);

		System.out.println("----- (c) " + VENDOR + " -------\n-------------------------------------");
	}

	// Method to Calculate the odds
	private long calculateOdds() {
		long sequencesProduct = 1;
		long sequencesProductTwo = 1;
		long poolsProduct = 1;
		long poolsProductTwo = 1;

		for (int i = 1; i <= selectionCount; i++) {
			sequencesProduct *= i;
		}
		for (int j = selectionPoolSize; j > (selectionPoolSize - selectionCount); j--) {
			poolsProduct *= j;
		}

		if (selectionCountTwo > 0) {
			for (int k = 1; k <= selectionCountTwo; k++) {
				sequencesProductTwo *= k;
			}
			for (int l = selectionPoolSizeTwo; l > (selectionPoolSizeTwo - selectionCountTwo); l--) {
				poolsProductTwo *= l;
			}
		}

		return (poolsProduct * poolsProductTwo) / (sequencesProduct * sequencesProductTwo);
	}

	public static void main(String[] args) throws QuickPickerException {

		int numberOfGames = DEFAULT_GAME_COUNT;
		

		if (args.length > 0) {
			PROP_FILE_NAME = args[0];
		}
		if (args.length > 1) {
			numberOfGames = Integer.parseInt(args[1]);
		}
		
		try {
			QuickPicker lotto = new QuickPicker(numberOfGames);
			lotto.displayTicket();
		} catch (Exception e) {
			throw new QuickPickerException("Can't find: " + PROP_FILE_NAME, QuickPickerException.INVALID_FILENAME);
		}

	}

}
