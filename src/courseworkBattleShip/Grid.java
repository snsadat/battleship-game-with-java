package courseworkBattleShip;

import java.util.ArrayList;

public class Grid {
	
	private ArrayList <Row> theRows = new ArrayList <Row> ();
	private final int NUMBER_OF_ROWS = 10;
	public Grid() {
		Row tempRow;
		for(int loop = 1; loop <= NUMBER_OF_ROWS; loop++) {
			tempRow = new Row(loop);
			this.theRows.add(tempRow);
		}
	}// end Grid
	
	//is there a ship on a particular square
	public boolean isThereAShipOn(int row, int square) {
		for(Row tempRow : this.theRows) {
			if(tempRow.getNumber() == row) {
				//found the correct row
				for(Square tempSquare : tempRow.getTheSquares()) {
					if(tempSquare.getNumber() == square) {
						//found the correct square
						return tempSquare.isThereAShip();
					}
				}
			}
		}
		return false;
	}
	
	
	public void addShip(Ship ship, int row, int square) {
		for(Row tempRow : this.theRows) {
			if(tempRow.getNumber() == row) {
				//found the correct row
				for(Square tempSquare : tempRow.getTheSquares()) {
					if(tempSquare.getNumber() == square) {
						//found the correct square
						tempSquare.setShip(ship);
					}
				}
			}
		}
	}
	
	
	public Ship whichShipIsThere(int row, int square) {
		for(Row tempRow : this.theRows) {
			if(tempRow.getNumber() == row) {
				//found the correct row
				for(Square tempSquare : tempRow.getTheSquares()) {
					if(tempSquare.getNumber() == square) {
						//found the correct square
						return tempSquare.getShip();
					}
				}
			}
		}
		return null;
	}
	
}//end class
