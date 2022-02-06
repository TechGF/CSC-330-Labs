package edu.cuny.csi.csc330.lab6;

public class QuickPickerException extends Exception {

	// static publicly defined error codes
	public static int UNSET = 0;
	public static int INVALID_FILENAME = 1;
	public static int INVALID_GAMENAME = 2;
	public static int INVALID_POOL1 = 3;
	public static int INVALID_POOL2 = 4;
	public static int INVALID_VENDOR = 5;
	
	// static publicly defined exception messages 
	public static String[] MESSAGE = {
			"Code Unspecified",
			"Properties File - can not locate",
			"GameName property is missing",
			"Pool1 property is missing",
			"Pool2 property is missing",
			"Vendor property is missing"
	};
	
	protected int code;
	
	public QuickPickerException() {
		// TODO Auto-generated constructor stub
	}
	
	public QuickPickerException(String m) {
		super(m);
	}
	
	public QuickPickerException(String message, int code) {
		super(message);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return "QuickPickerException [code=" + code + ", toString()=" 
				+ super.toString() + "]\n" + MESSAGE[code];
	}


	public static void main(String[] args) {
		QuickPickerException qpe = new QuickPickerException("Quick Picker Exception message ... ");
		System.out.println(qpe);
		
		QuickPickerException qpe1 = new QuickPickerException("QuickPicker Exception message ...", QuickPickerException.INVALID_FILENAME);
		System.out.println(qpe1);
		
		QuickPickerException qpe2 = new QuickPickerException("QuickPicker Exception message ...", QuickPickerException.INVALID_GAMENAME);
		System.out.println(qpe2);
		
		QuickPickerException qpe3 = new QuickPickerException("QuickPicker Exception message ...", QuickPickerException.INVALID_POOL1);
		System.out.println(qpe3);
		
		QuickPickerException qpe4 = new QuickPickerException("QuickPicker Exception message ...", QuickPickerException.INVALID_POOL2);
		System.out.println(qpe4);
		
		QuickPickerException qpe5 = new QuickPickerException("QuickPicker Exception message ...", QuickPickerException.INVALID_VENDOR);
		System.out.println(qpe5);
		
	}

}
