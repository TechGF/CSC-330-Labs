package edu.cuny.csi.csc330.lab5;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class DrunkWalker {
	
	private Intersection startIntersection;
	private Intersection currentIntersection;
	private List <Intersection> intersectionHistory;
	private Map <Intersection, Integer> intersectionCounter;
	private Integer stepCounter = 1;
	
//	private List<Intersection> journey;
//	private Map<Intersection, Integer> cornerCounts;
//	private Set<Intersection> popularCorners;
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  
	// add other data members here including Collection instances that you will use to 
	//  to track history and statistics ... 

	private DrunkWalker() {
		init();
	}
	
	/**
	 * 
	 * @param avenue
	 * @param street
	 */
	public DrunkWalker(int avenue, int street) {
		init();
		startIntersection = new Intersection(avenue, street);
		currentIntersection = new Intersection(avenue, street);
	}
	
	private void init() {
		// What should be do here to initialize an instance??
		if(this.startIntersection == null)
			this.startIntersection = new Intersection();
		currentIntersection = new Intersection();
		intersectionHistory = new ArrayList <Intersection>();
		intersectionCounter = new HashMap <Intersection, Integer>();
		
//		journey = new ArrayList<Intersection>(200);
//		cornerCounts = new HashMap<Intersection, Integer>(541);
//		// popularCorners = new HashSet<Intersection>(64);
//		popularCorners = new TreeSet<Intersection>();
		                  
		               
	}
	
	/**
	 * step in a random direction 
	 */
	public void step() {
		
		takeAStep(); 
		
		/**  !!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * Now, update the Collections that manage the following:
		 * 
		 *  1) add this next step/intersection to a "history" List that will contain the sequence of all 
		 *  steps taken by this DrunkWalker instance 
		 *  
		 *  2) add this next step to a Intersection -> Counter Map ... The Map 
		 *  Collection can and should be of Generics "Type" <Intersection, Integer> 
		 *  key = Intersection 
		 *  value = Count Value  
		 *  Need to do something like this: 
		 *  if intersection is not saved in Map yet as a key yet, add a key->value pair of Intersection->1 
		 *  else if intersection value is there, the existing key->value pair need to be replaced as 
		 *   Intersection->existing_count+1 
		 *   
		 */
		intersectionHistory.add(currentIntersection);
		
		if (intersectionCounter.get(currentIntersection) == null) {
			intersectionCounter.put(currentIntersection, stepCounter);
		}
		else if (intersectionCounter.containsKey(currentIntersection)) {
			intersectionCounter.put(currentIntersection, stepCounter+1);
		}
//		journey.add(currentIntersection);
//		int count = 1;
//		if(cornerCounts.containsKey(currentIntersection) == true) {
//		    count = cornerCounts.get(currentIntersection);
//		    count++;
//		    popularCorners.add(currentIntersection);
//		}
//		cornerCounts.put (currentIntersection, count);
		
	}
	
	
	private void takeAStep() {
		Direction dir = Direction.getNextRandom(); 
		
		/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * now what do we do based on the random Direction created? 
		 * How do we go about updating the "currentIntersection" instance to reflect the 
		 * direction/step that was just selected? 
		 */
		
		Intersection updateIntersection = new Intersection(currentIntersection.getStreet(), currentIntersection.getAvenue());

		switch (dir) {
		case NORTH:
			updateIntersection.setStreet(updateIntersection.getStreet() + 1);
			break;
		case SOUTH:
			updateIntersection.setStreet(updateIntersection.getStreet() - 1);
			break;
		case EAST:
			updateIntersection.setAvenue(updateIntersection.getAvenue() - 1);
			break;
		case WEST:
			updateIntersection.setAvenue(updateIntersection.getAvenue() + 1);
			break;
		case NORTHEAST:
			updateIntersection.setStreet(updateIntersection.getStreet() + 1);
			updateIntersection.setAvenue(updateIntersection.getAvenue() - 1);
			break;
		case NORTHWEST:
			updateIntersection.setStreet(updateIntersection.getStreet() + 1);
			updateIntersection.setAvenue(updateIntersection.getAvenue() + 1);
			break;
		case SOUTHEAST:
			updateIntersection.setStreet(updateIntersection.getStreet() - 1);
			updateIntersection.setAvenue(updateIntersection.getAvenue() - 1);
			break;
		case SOUTHWEST:
			updateIntersection.setStreet(updateIntersection.getStreet() - 1);
			updateIntersection.setAvenue(updateIntersection.getAvenue() + 1);
			break;	
		default:
			System.out.println("The Drunk Walker is lost.");
			break;
		}
		
		currentIntersection = updateIntersection;
	}


	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * toString() 
	 * @return
	 */
	@Override
	public String toString() {
		return "DrunkWalker [startIntersection=" + startIntersection + ", currentIntersection=" + currentIntersection
				+ ", intersectionHistory=" + intersectionHistory + ", intersectionCounter=" + intersectionCounter
				+ ", stepCounter=" + stepCounter + "]";
	}

	/**
	 * generate string that contains current intersection/location info 
	 */
	public String getLocation() {
		return String.format("Current location: DrunkWalker [avenue=%d, street=%d]", 
				currentIntersection.getAvenue(), currentIntersection.getStreet() );
	}


	/**
	 * Take N number of steps 
	 * @param steps
	 */
	public void fastForward(int steps ) {
		// Considering that we already have a step() method, how would we 
		//  implement this method?  Uhh, think reuse!  
		for (int i = 0; i < steps; i++) {
			step();
		}
	}
	
	/**
	 * Display information about this current walker instance 
	 */
	public void displayWalkDetails() {
		/**
		 * This method needs to display the following information in a neat, readable format 
		 * using calls to System.out.println() or System.out.printf()
		 * 
		 * 1 - starting location 
		 * 2 - current/ending location 
		 * 3 - distance (value returned by howFar() ) 
		 * 4 - number of steps taken - which collection would be able to provide that information, and how?  
		 * 5 - number of unique intersections visited - 
		 * 		which collection would be able to provide that information, and how? 
		 * 6 - Intersections visited more than once 
		 * 
		 */
		Date time = new Date();
		
		System.out.println("\nStart Time: " + time);
		System.out.println("End Time - After FF: " + time);
		System.out.printf("Starting Location: Intersection [avenue=%d, street=%d]", startIntersection.getAvenue(), startIntersection.getStreet());
		System.out.printf("\nCurrent/Ending Location: Intersection [avenue=%d, street=%d]", currentIntersection.getAvenue(), currentIntersection.getStreet());
		System.out.printf("\nDistance (blocks): %d", howFar());
		System.out.printf("\nNumber of steps taken: %d", intersectionHistory.size());
		System.out.printf("\nNumber of unique intersections visited: %d\n\n", intersectionCounter.size());
		intersectionCounter.forEach((k,v) -> System.out.println("Visited Intersection [avenue=" + k.getAvenue() + ", street=" + k.getStreet() + "] " + v + " times!"));

	}
	/**
	 * X Y Coordinate distance formula 
	 *  |x1 - x2| + |y1 - y2|   
	 * @return
	 */
	public int howFar() {
		// |x1 - x2| + |y1 - y2|.
		int startAvenue = startIntersection.getAvenue();
		int avenue = currentIntersection.getAvenue();
		int startStreet = startIntersection.getStreet();
		int street = currentIntersection.getStreet();
	 
		return (Math.abs(startAvenue - avenue) ) + (Math.abs(startStreet - street)); 
	}


	public static void main(String[] args) {
		
		// create Drunkard with initial position (ave,str)
		DrunkWalker billy = new DrunkWalker(6,23);
		
		for(int i = 1 ; i <= 3 ; ++i ) {
			billy.step(); 
			System.out.printf("billy's location after %d steps: %s\n",
					i, billy.getLocation() );
		}
			
		
		// get his current location
		String location = billy.getLocation();
		// get distance from start
		int distance = billy.howFar();
		System.out.println("Current location after fastForward(): " + location);
		System.out.println("That's " + distance + " blocks from start.");
		

		// have him move 25  random intersections
		billy.fastForward(25);
		billy.displayWalkDetails();

	}

}
