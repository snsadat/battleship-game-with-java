package courseworkBattleShip;

import java.util.ArrayList;

public class Row {
	
	private ArrayList <Square> theSquares = new ArrayList <Square> ();
	private int number;
	private final int NUMBER_OF_SQUARES = 10;
	
	public Row(int number) {
		Square tempSquare;
		for(int loop = 1; loop <= NUMBER_OF_SQUARES; loop++) {
			tempSquare = new Square(loop);
			this.theSquares.add(tempSquare);
		}
		setNumber(number);
	}
	
	//getters and setters
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return this.number;
	}
	
	public ArrayList <Square> getTheSquares() {
		return this.theSquares;
	}
	
}//end class
