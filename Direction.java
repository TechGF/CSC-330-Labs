/**
 * Partially completed Direction ENUM 
 */

package edu.cuny.csi.csc330.lab5;

import edu.cuny.csi.csc330.util.Randomizer;


public enum Direction {
	 NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST;
	 // !!! Add 4 more Direction Values - NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST
	 
	 // methods 
	 public Direction getFavorite() {
		 return SOUTH;  // It's getting cold! ... 
	 }
	 
	 public static Direction getNextRandom() {
		 	
		 	/******************************
		 	 * !!!!!
		 	 * WHAT CHANGES NEED TO BE MADE HERE SO THAT THE 4 NEW RANDOM DIRECTIONS ARE CONSIDERED 
		 	 */
			int direction = Randomizer.generateInt(1, 8); 
		
			// 1 = south,  2 = west, 3 = north, 4 = east 
			if(direction == 1) { // south 
				 return SOUTH;
			}
			else if(direction == 2) {   // west 
				 return WEST; 
			}
			else if(direction == 3) {   // north 
				 return NORTH; 
			}
			else if (direction == 4) {    // east 
				return EAST; 
			}
			else if (direction == 5) {    // north east 
				return NORTHEAST; 
			}
			else if (direction == 6) {    // north west 
				return NORTHWEST; 
			}
			else if (direction == 7) {    // south west 
				return SOUTHWEST; 
			}
			else { // south east
				return SOUTHEAST;
			}
			
	 }
	 
	 public static void main(String [] args)  {
		 
		 int c = 0; 
		 while(c++ < 100) {  
			 System.out.println(Direction.getNextRandom() );
		 }
		 
	 }
	 
	 
}
