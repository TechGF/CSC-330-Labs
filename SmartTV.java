/*
 * Gary Feng
 * Lab 4 - Smart TV
 */
package edu.cuny.csi.csc330.lab4;

import java.util.Arrays;
import java.util.Date;
import edu.cuny.csi.csc330.util.Randomizer;

public class SmartTV {
	
	// Default Variables
	protected static final String BRAND_NAME = "LG";
	protected static final int MIN_VOLUME = 0;
	protected static final int MAX_VOLUME = 100;
	protected static final int DEFAULT_VOLUME = 45;
	protected static final String DEFAULT_INPUT = "TV";
	protected static final int DEFAULT_CHANNEL = 1;
	protected static final String DEFAULT_STREAM = "Youtube";
	
	// Initialize Array
	protected static final String[] INPUT_SOURCES = {"TV", "AV", "COMPONENT", "VGA", "HDMI 1", "HDMI 2", "VIDEO 1", "VIDEO 2"};
	protected static final int [] CHANNELS = {1, 2, 3, 4, 5, 8, 10, 13, 14, 15, 17, 19, 20, 22, 25, 27, 30, 38, 40, 50};
	protected static final String[] STREAMING_SERVICES = {"Youtube", "Netflix", "Prime Video", "Hulu", "Disney Plus", "Crunchyroll", "HBO Max"};
	
	// Object Instance Properties
	private String serialNumber;
	private boolean powerState;
	private boolean connectInternet;
	private boolean detectInternetConnection;
	private String selectedInputSource;
	private boolean detectInputSource;
	private int selectedVolume;
	private int selectedChannel;
	private String selectedStreamingPlatform;
	private Date firstTurnOn;
	private Date lastTurnOn;

	// Default constructor
	public SmartTV() {
		init();
	}
	
	// Initialize - Only gets invoked by constructor 
	private void init() {
		// Generate serial number
		Integer irand = Randomizer.generateInt(1111111111, 999999999); 
		this.serialNumber = BRAND_NAME + ":" + irand.toString();
	}
	
	// Turn On Smart TV instance
	public void turnOn() {
		Date now = new Date();
		
		if(firstTurnOn == null) {
			connectInternet = true;
			detectInternetConnection = true;
			selectedInputSource = DEFAULT_INPUT;
			detectInputSource = true;
			selectedVolume = DEFAULT_VOLUME;
			selectedChannel = DEFAULT_CHANNEL;
			selectedStreamingPlatform = DEFAULT_STREAM;
			firstTurnOn = now;
		}
		
		powerState = true;
		lastTurnOn = now;
	}
	
	// Turn off Smart TV instance
	public void turnOff() {
		powerState = false;
		connectInternet = false;
		detectInternetConnection = false;
		selectedInputSource = "NO SIGNAL";
		detectInputSource = false;
	}
	
	// Is the Smart TV power state on/off?
	public boolean isOn() {
		return powerState == true;
	}
	
	// Is it connected to Internet true/false?
	public boolean isConnectInternet() {
		return connectInternet == true;
	}

	public void setConnectInternet(boolean connectInternet) {
		this.connectInternet = connectInternet;
	}
	
	// Is Internet detected true/false?
	public boolean isDetectInternetConnection() {
		return detectInternetConnection == true;
	}

	public void setDetectInternetConnection(boolean detectInternetConnection) {
		this.detectInternetConnection = detectInternetConnection;
	}
	
	public String getSelectedInputSource() {
		return selectedInputSource;
	}

	public void setSelectedInputSource(String selectedInputSource) {
		this.selectedInputSource = selectedInputSource;
	}
	
	// Select input source method
	public void selectInputSource(String selectedInputSource) {
		if (Arrays.asList(INPUT_SOURCES).contains(selectedInputSource)) {
			this.selectedInputSource = selectedInputSource;
		} else {
			this.selectedInputSource = "NO SIGNAL";
			this.detectInputSource = false;
		}
	}

	// Is input source detected true/false?
	public boolean isDetectInputSource() {
		return detectInputSource == true;
	}

	public void setDetectInputSource(boolean detectInputSource) {
		this.detectInputSource = detectInputSource;
	}

	public int getVolume() {
		return selectedVolume;
	}
	
	public void setVolume(int volume) {
		this.selectedVolume = volume;
	}
	
	// Decrease Volume
	public void decreaseVolume(int volume) {
		this.selectedVolume -= volume;
	}
	
	// Increase Volume
	public void increaseVolume(int volume) {
		this.selectedVolume += volume;
	}
	
	public int getSelectedChannel() {
		return selectedChannel;
	}

	// set channel method 
	public void setSelectedChannel(int selectedChannel) {
		this.selectedChannel = selectedChannel;
	}
	
	public String getSelectedStreamingPlatform() {
		return selectedStreamingPlatform;
	}

	public void setSelectedStreamingPlatform(String selectedStreamingPlatform) {
		this.selectedStreamingPlatform = selectedStreamingPlatform;
	}
	
	// Select Streaming Platform method
	public void selectStreamingPlatform(String selectedStreamingPlatform) {
		if (Arrays.asList(STREAMING_SERVICES).contains(selectedStreamingPlatform)) {
			this.selectedStreamingPlatform = selectedStreamingPlatform;
		} else {
			this.selectedStreamingPlatform = "Streaming Platform Not Found";
		}
	}
	
	// toString
	@Override
	public String toString() {
		return "SmartTV [serialNumber=" + serialNumber + ", powerState=" + powerState + ", \nconnectInternet="
				+ connectInternet + ", detectInternetConnection=" + detectInternetConnection + ", \nselectedInputSource="
				+ selectedInputSource + ", detectInputSource=" + detectInputSource + ", \nselectedVolume="
				+ selectedVolume + ", selectedChannel=" + selectedChannel + ", selectedStreamingPlatform=" + selectedStreamingPlatform
				+ ", \ninputSources=" + Arrays.toString(INPUT_SOURCES) + ", \nchannels=" + Arrays.toString(CHANNELS)
				+ ", \nstreamingPlatforms=" + Arrays.toString(STREAMING_SERVICES) 
				+ ", \nfirstTurnOn= " + firstTurnOn + ", lastTurnOn= " + lastTurnOn + "]";
	}

	public static void main(String[] args) {
		// New Instance
		SmartTV smartTV = new SmartTV();
		
		System.out.println("New Instance\n" + smartTV + "\n");
		System.out.println();
		
		// Turn On TV
		smartTV.turnOn();
		System.out.println("Turned On\n" + smartTV + "\n");
		System.out.println();
		
		// Increase Volume by 25
		smartTV.increaseVolume(25);
		System.out.println("Increase Volume by 25\n" + smartTV + "\n");
		System.out.println();
		
		// Decrease Volume by 10
		smartTV.decreaseVolume(10);
		System.out.println("Decrease Volume by 10\n" + smartTV + "\n");
		System.out.println();
		
		// Selecting Channel 10
		smartTV.setSelectedChannel(10);
		System.out.println("Select Channel - 10\n" + smartTV + "\n");
		System.out.println();
		
		// Changing Input Source to HDMI 1
		smartTV.selectInputSource("HDMI 1");
		System.out.println("Select Input Source - HDMI 1\n" + smartTV + "\n");
		System.out.println();
		
		// Selecting Streaming Platform - PLATFORM NOT FOUND
		smartTV.selectStreamingPlatform("Funanimation");
		System.out.println("Select Stream - Funanimation\n" + smartTV + "\n");
		System.out.println();
		
		// Selecting Stream Platform - Netflix
		smartTV.selectStreamingPlatform("Netflix");
		System.out.println("Select Stream - Netflix\n" + smartTV + "\n");
		System.out.println();
	
		// Turn Off TV
		smartTV.turnOff();
		System.out.println("Turned off\n" + smartTV + "\n");
		System.out.println();
		
	}

}
