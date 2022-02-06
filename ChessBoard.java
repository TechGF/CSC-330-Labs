package edu.cuny.csi.csc330.lab1;

/**
 * Generates a unicode textual chessboard 
 * Reference:  https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode 
 * @author lji
 *
 */
public class ChessBoard {
	
	// Array of pieces values Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook 
	static private final  char [] WHITE_PIECES = {
		'\u2656', '\u2658', '\u2657', '\u2655', '\u2654', '\u2657', '\u2658', '\u2656'
	};

	// unicode value of a pawn 
	static private  final char PAWN = '\u2659'; 
	// our choice for "empty square" - medium rectangle ... 
	static private  final char EMPTY = '\u25AD'; 
	
	private static final int ROWS = 8; 
	
	private static final String HEADING = 
			"-----------------------------------------\n"
			+ "        Unicode Symbols Chessboard\n" 
			+ "-----------------------------------------\n";
	
	// Black piece values are equiv white pieces + 6
	private static final int BLACK_PIECE_DISPLACEMENT = 6;
	
	static private void displayHeading() {
		System.out.println(HEADING.toUpperCase()); //heading to upper-cased
	}
	
	private static void displayBoard() {
		for(int row = ROWS ; row >= 1  ; --row ) {
			System.out.printf("%-5d", row);
			
			int chessPiecesLength = WHITE_PIECES.length; //length of the chess pieces, 8
			
			
			for (int i = 0; i < chessPiecesLength; i++) {
				if (row == 8) { //row 8, print out black pieces
					System.out.printf("%4c", WHITE_PIECES[i] + BLACK_PIECE_DISPLACEMENT);
				}
				else if (row == 7) { //row 7, print out black pawns
					System.out.printf("%4c", PAWN + BLACK_PIECE_DISPLACEMENT);
				}
				else if (row == 6 || row == 5 || row == 4 || row == 3) { //row 3, 4, 5, 6, prints out empty squares
					System.out.printf("%4c", EMPTY);
				}
				else if (row == 2) { //row 2, prints out white pawns
					System.out.printf("%4c", PAWN);
				}
				else if (row == 1) { //row 1, prints out white pieces
					System.out.printf("%4c", WHITE_PIECES[i]);
				}
			}
			
			/*
			//Professor solution
			switch(row) {
				//baseline white pieces
			case 1:
				for(int i = 0 ; i < WHITE_PIECES.length; ++i) {
				System.out.printf("%4c", WHITE_PIECES[i]);
			}
				break;
				//black pieces
			case 8:
				for(int i = 0 ; i < WHITE_PIECES.length; ++i) {
				System.out.printf("%4c", WHITE_PIECES[i] + BLACK_PIECE_DISPLACEMENT);
			}
				break;
				//Black/White Pawns
			case 2:
				for(int i = 0 ; i < WHITE_PIECES.length; ++i) {
				System.out.printf("%4c", PAWN);
			}
				break;
			case 7:
				for(int i = 0 ; i < WHITE_PIECES.length; ++i) {
				System.out.printf("%4c", PAWN + BLACK_PIECE_DISPLACEMENT);
			}
				break;
				//empty rows
				default:
					for(int i = 0 ; i < WHITE_PIECES.length; ++i) {
						System.out.printf("%4c", EMPTY);
					}
						break;
		}
		*/
			
			System.out.println("");
			
		}
		
	}
	
	// Only Public Method Used Directly By main() 
	static public void display() {
		displayHeading(); // display heading 
		displayBoard(); 
	
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method 
		ChessBoard.display();
			

	}
	
}