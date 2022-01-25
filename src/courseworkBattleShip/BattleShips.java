package courseworkBattleShip;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.stream.Stream;

public class BattleShips {
	//declaring variables
	Random rand = new Random();
	String playerName;
	int row, square;
	int rowGuess, squareGuess;
	int numOfRounds = 0;
	int numOfShips = 5;
	int shipsDestroyed = 0;
	int playerPoints = 0;
	int[] guessArrayOfRow = new int[10];
	int[] guessArrayOfSquare = new int[10];
	String shipOne = "Aircraft Carrier";
	int lengthOfAircraftCarrier = 5;
	int pointsForAircraftCarrier = 2;
	String shipTwo = "Battleship";
	int lengthOfBattleship = 4;
	int pointsForBattleship = 4;
	String shipThree = "Submarine";
	int lengthOfSubmarine = 3;
	int pointsForSubmarine = 6;
	String shipFour = "Destroyer";
	int lengthOfDestroyer = 2;
	int pointsForDestroyer = 8;
	String shipFive = "Patrol boat";
	int lengthOfPatrolBoat = 1;
	int pointsForPatrolBoat = 10;
	boolean debugGame;
	
	int randRowForAircraft;
	int randSquareForAircraft;
	int randRowForBattleship;
	int randSquareForBattleship;
	int randRowForSubmarine;
	int randSquareForSubmarine;
	int randRowForDestroyer;
	int randSquareForDestroyer;
	int randRowForPatrolBoat;
	int randSquareForPatrolBoat;
	String[] alreadyDestroyedShips = new String[10];
	
	private Grid theGrid = new Grid();
	
	public void play() {
		
		//greetings for user when game starts
		welcomeMessage();
		gameMenu();
	}
	
	public void welcomeMessage() {
		String welcomeMessage = "Welcome to my BattleShip Game\n";
		welcomeMessage = welcomeMessage + "Please enter your name";
		playerName = JOptionPane.showInputDialog(welcomeMessage);
	}
	
	public void gameMenu() {
		String menuMessage = "PLease enter a menu number \n 1. New game \n 2. Load Game \n 3. Debug Mode \n 4. High Scores";
		String userMenu = JOptionPane.showInputDialog(menuMessage);
		int userMenuInt = Integer.parseInt(userMenu);
		while(userMenuInt < 1 || userMenuInt > 4) {
			menuMessage = "Error! \n PLease enter a menu number between 1-4 \n 1. New game \n 2. Load Game \n 3. Debug Mode \n 4. High Scores";
			userMenu = JOptionPane.showInputDialog(menuMessage);
			userMenuInt = Integer.parseInt(userMenu);
		}
		if(userMenuInt == 1) {
			placingShips();
			askUserForXY();
		}else if(userMenuInt == 2) {
			loadGame();
		}else if(userMenuInt == 3){
			displayShips();
		}else if(userMenuInt == 4){
			displayHighScore();
		}
	}
	
	public void placingShips() {
		
		Ship s1 = new Ship(shipOne, lengthOfAircraftCarrier, pointsForAircraftCarrier);
		Ship s2 = new Ship(shipTwo, lengthOfBattleship, pointsForBattleship);
		Ship s3 = new Ship(shipThree, lengthOfSubmarine, pointsForSubmarine);
		Ship s4 = new Ship(shipFour, lengthOfDestroyer, pointsForDestroyer);
		Ship s5 = new Ship(shipFive, lengthOfPatrolBoat, pointsForPatrolBoat);
		
		//allocate a ship of Aircraft Carrier
		//As length of Aircraft Carrier is 5 and grid size is 10x10,
		//that's why I am generating a number between 1-5 so the ship is not placed outside the grid
		randRowForAircraft = rand.nextInt(5) + 1;
		randSquareForAircraft = rand.nextInt(5) + 1;
		
		for(int loop = randSquareForAircraft; loop < (randSquareForAircraft+lengthOfAircraftCarrier); loop++) {
			this.theGrid.addShip(s1, randRowForAircraft, loop);
		}
		
		//allocate a ship of Battleship
		//As length of Battleship is 4 and grid size is 10x10,
		//that's why I am generating a number between 1-6 so the ship is not placed outside the grid
		randRowForBattleship = rand.nextInt(6) + 1;
		randSquareForBattleship = rand.nextInt(6) + 1;
		while(randRowForBattleship == randRowForAircraft) {
			randRowForBattleship = rand.nextInt(6) + 1;
		}
		for(int loop = randSquareForBattleship; loop < (randSquareForBattleship+lengthOfBattleship); loop++) {
			this.theGrid.addShip(s2, randRowForBattleship, loop);
		}
		
		//allocate a ship of Submarine
		//As length of Submarine is 3 and grid size is 10x10,
		//that's why I am generating a number between 1-7 so the ship is not placed outside the grid
		randRowForSubmarine = rand.nextInt(7) + 1;
		randSquareForSubmarine = rand.nextInt(7) + 1;
		while(randRowForSubmarine == randRowForBattleship || randRowForSubmarine == randRowForAircraft) {
			randRowForSubmarine = rand.nextInt(7) + 1;
		}
		for(int loop = randSquareForSubmarine; loop < (randSquareForSubmarine+lengthOfSubmarine); loop++) {
			this.theGrid.addShip(s3, randRowForSubmarine, loop);
		}
		
		//allocate a ship of Destroyer
		//As length of Destroyer is 2 and grid size is 10x10,
		//that's why I am generating a number between 1-8 so the ship is not placed outside the grid
		randRowForDestroyer = rand.nextInt(8) + 1;
		randSquareForDestroyer = rand.nextInt(8) + 1;
		while(randRowForDestroyer == randRowForSubmarine || randRowForDestroyer == randRowForBattleship || 
				randRowForDestroyer == randRowForAircraft) {
			randRowForDestroyer = rand.nextInt(8) + 1;
		}
		for(int loop = randSquareForDestroyer; loop < (randSquareForDestroyer+lengthOfDestroyer); loop++) {
			this.theGrid.addShip(s4, randRowForDestroyer, loop);
		}
		
		//allocate a ship of Patrol Boat
		//As length of Patrol Boat is 1 and grid size is 10x10,
		//that's why I am generating a number between 1-9 so the ship is not placed outside the grid
		randRowForPatrolBoat = rand.nextInt(9) + 1;
		randSquareForPatrolBoat = rand.nextInt(9) + 1;
		while(randRowForPatrolBoat == randRowForDestroyer || randRowForPatrolBoat == randRowForSubmarine || 
				randRowForPatrolBoat == randRowForBattleship || randRowForPatrolBoat == randRowForAircraft) {
			randRowForPatrolBoat = rand.nextInt(9) + 1;
		}
		for(int loop = randSquareForPatrolBoat; loop < (randSquareForPatrolBoat+lengthOfPatrolBoat); loop++) {
			this.theGrid.addShip(s5, randRowForPatrolBoat, loop);
		}

	}
	
	
	public void askUserForXY() {
		//asking user for x and y inside loop
		while(numOfRounds != 10 && shipsDestroyed != numOfShips) {
			
			String rowGuessMessage = "PLease enter a row number between 1 and 10 \n Enter 'save' to save the game";
			String theRowGuess = JOptionPane.showInputDialog(rowGuessMessage);
			if(theRowGuess.endsWith("save")) {
				saveGame();
			}else {
				rowGuess = Integer.parseInt(theRowGuess);
			}
			while(rowGuess < 1 || rowGuess > 10) {
				rowGuessMessage = "Error! \n PLease enter a row number between 1 and 10 \n Enter 'save' to save the game";
				theRowGuess = JOptionPane.showInputDialog(rowGuessMessage);
				if(theRowGuess.endsWith("save")) {
					saveGame();
				}else {
					rowGuess = Integer.parseInt(theRowGuess);
				}
			}
			row = rowGuess;
			
			String squareGuessMessage = "PLease enter a square number between 1 and 10 \n Enter 'save' to save the game";
			String theSquareGuess = JOptionPane.showInputDialog(squareGuessMessage);
			if(theSquareGuess.endsWith("save")) {
				saveGame();
			}else {
				squareGuess = Integer.parseInt(theSquareGuess);
			}
			while(squareGuess < 1 || squareGuess > 10) {
				squareGuessMessage = "Error! \n PLease enter a square number between 1 and 10 \n Enter 'save' to save the game";
				theSquareGuess = JOptionPane.showInputDialog(squareGuessMessage);
				if(theSquareGuess.endsWith("save")) {
					saveGame();
				}else {
					squareGuess = Integer.parseInt(theSquareGuess);
				}
				squareGuess = Integer.parseInt(theSquareGuess);
			}
			square = squareGuess;
			
			
			int i = 0;
			while(guessArrayOfRow[i] == row && guessArrayOfSquare[i] == square) {
				String sameGuessErrorMessage = "Error! You fired at this square before";
				JOptionPane.showMessageDialog(null, sameGuessErrorMessage);
				theRowGuess = JOptionPane.showInputDialog(rowGuessMessage);
				if(theRowGuess.endsWith("save")) {
					saveGame();
				}else {
					rowGuess = Integer.parseInt(theRowGuess);
				}
				rowGuess = Integer.parseInt(theRowGuess);
				while(rowGuess < 1 || rowGuess > 10) {
					rowGuessMessage = "Error! \n PLease enter a row number between 1 and 10 \n Enter 'save' to save the game";
					theRowGuess = JOptionPane.showInputDialog(rowGuessMessage);
					if(theRowGuess.endsWith("save")) {
						saveGame();
					}else {
						rowGuess = Integer.parseInt(theRowGuess);
					}
					rowGuess = Integer.parseInt(theRowGuess);
				}
				row = rowGuess;
				guessArrayOfRow[numOfRounds] = row;
				
				theSquareGuess = JOptionPane.showInputDialog(squareGuessMessage);
				if(theSquareGuess.endsWith("save")) {
					saveGame();
				}else {
					squareGuess = Integer.parseInt(theSquareGuess);
				}
				squareGuess = Integer.parseInt(theSquareGuess);
				while(squareGuess < 1 || squareGuess > 10) {
					squareGuessMessage = "Error! \n PLease enter a square number between 1 and 10 \n Enter 'save' to save the game";
					theSquareGuess = JOptionPane.showInputDialog(squareGuessMessage);
					if(theSquareGuess.endsWith("save")) {
						saveGame();
					}else {
						squareGuess = Integer.parseInt(theSquareGuess);
					}
					squareGuess = Integer.parseInt(theSquareGuess);
				}
				square = squareGuess;
				guessArrayOfSquare[numOfRounds] = square;
			}
			
			guessArrayOfRow[numOfRounds] = row;
			guessArrayOfSquare[numOfRounds] = square;
			

			boolean test;
			test = this.theGrid.isThereAShipOn(row, square);
			if(test == true) {
				Ship shipOnGrid = this.theGrid.whichShipIsThere(row, square);
				
					if(alreadyDestroyedShips[numOfRounds] == shipOnGrid.getType()) {
						String alreadyDestroyedMessage = "Error!\n";
						alreadyDestroyedMessage = alreadyDestroyedMessage + shipOnGrid.getType() + " is already destroyed";
						JOptionPane.showMessageDialog(null, alreadyDestroyedMessage);
					}else {
						String correctSquareMessage = "Hit!\n";
						correctSquareMessage = correctSquareMessage + "The ship is " + shipOnGrid.getType() + "\n";
						correctSquareMessage = correctSquareMessage + "You got " + shipOnGrid.getPoints() + " points";
						JOptionPane.showMessageDialog(null, correctSquareMessage);
						playerPoints += shipOnGrid.getPoints();
						shipsDestroyed++;
						numOfRounds++;
					}
				alreadyDestroyedShips[numOfRounds] = shipOnGrid.getType();
				
			}else {
				String wrongSquareMessage = "Miss! \n Please try again";
				JOptionPane.showMessageDialog(null, wrongSquareMessage);
				numOfRounds++;
			}

		}
			String gameOverMessage = "Game Over!\n";
			gameOverMessage = gameOverMessage + "Number of ships destroyed: " + shipsDestroyed + "\n";
			gameOverMessage = gameOverMessage + "Points received: " + playerPoints;
			JOptionPane.showMessageDialog(null, gameOverMessage);
			highScoreKeeper();
	}

	public void saveGame() {
		try {
	      FileWriter myWriter = new FileWriter("savegame.txt");
	      myWriter.write(playerName + "\n");
	      myWriter.append(Integer.toString(randRowForAircraft) + "\n");
	      myWriter.append(Integer.toString(randSquareForAircraft) + "\n");
	      myWriter.append(Integer.toString(randRowForBattleship) + "\n");
	      myWriter.append(Integer.toString(randSquareForBattleship) + "\n");
	      myWriter.append(Integer.toString(randRowForSubmarine) + "\n");
	      myWriter.append(Integer.toString(randSquareForSubmarine) + "\n");
	      myWriter.append(Integer.toString(randRowForDestroyer) + "\n");
	      myWriter.append(Integer.toString(randSquareForDestroyer) + "\n");
	      myWriter.append(Integer.toString(randRowForPatrolBoat) + "\n");
	      myWriter.append(Integer.toString(randSquareForPatrolBoat) + "\n");
	      myWriter.append(Integer.toString(numOfRounds) + "\n");
	      myWriter.append(Integer.toString(shipsDestroyed) + "\n");
	      myWriter.append(Integer.toString(playerPoints) + "\n");
	      myWriter.append(Arrays.toString(guessArrayOfRow) + "\n");
	      myWriter.append(Arrays.toString(guessArrayOfSquare) + "\n");
	      myWriter.append(Arrays.toString(alreadyDestroyedShips));
	      myWriter.close();
	      String gameSavedMessage = "Game saved successfully";
	      JOptionPane.showMessageDialog(null, gameSavedMessage);
	      System.exit(0);
	    } catch (IOException e) {
	      String gameSavingErrorMessage = "An error occurred. Game could not be saved.";
	      JOptionPane.showMessageDialog(null, gameSavingErrorMessage);
	      e.printStackTrace();
	    }
	}
	
	public void loadGame() {
		try {
	      File myObj = new File("savegame.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        playerName = myReader.nextLine();
		    randRowForAircraft = Integer.parseInt(myReader.nextLine());
		    randSquareForAircraft = Integer.parseInt(myReader.nextLine());
		    randRowForBattleship = Integer.parseInt(myReader.nextLine());
		    randSquareForBattleship = Integer.parseInt(myReader.nextLine());
		    randRowForSubmarine = Integer.parseInt(myReader.nextLine());
		    randSquareForSubmarine = Integer.parseInt(myReader.nextLine());
		    randRowForDestroyer = Integer.parseInt(myReader.nextLine());
		    randSquareForDestroyer = Integer.parseInt(myReader.nextLine());
		    randRowForPatrolBoat = Integer.parseInt(myReader.nextLine());
		    randSquareForPatrolBoat = Integer.parseInt(myReader.nextLine());
		    numOfRounds = Integer.parseInt(myReader.nextLine());
		    shipsDestroyed = Integer.parseInt(myReader.nextLine());
		    playerPoints = Integer.parseInt(myReader.nextLine());
		    
		    
		    String stringGuessArrayOfRow = myReader.nextLine();
		    int[] guessArrayOfRow=Stream.of(stringGuessArrayOfRow.replaceAll("[\\[\\]\\, ]", "").split("")).mapToInt(Integer::parseInt).toArray();
		    
		    String stringGuessArrayOfSquare = myReader.nextLine();
		    int[] guessArrayOfSquare=Stream.of(stringGuessArrayOfSquare.replaceAll("[\\[\\]\\, ]", "").split("")).mapToInt(Integer::parseInt).toArray();

		    String[] alreadyDestroyedShips = myReader.nextLine().replaceAll("\\[", "")
                    .replaceAll("]", "")
                    .split(",");
		    String gameLoadedMessage = "Game loaded successfully";
		    JOptionPane.showMessageDialog(null, gameLoadedMessage);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	    	String gameLoadingErrorMessage = "An error occurred. Game could not be loaded.";
		    JOptionPane.showMessageDialog(null, gameLoadingErrorMessage);
	      e.printStackTrace();
	    }
		
		
		//placing ships
		Ship s1 = new Ship(shipOne, lengthOfAircraftCarrier, pointsForAircraftCarrier);
		Ship s2 = new Ship(shipTwo, lengthOfBattleship, pointsForBattleship);
		Ship s3 = new Ship(shipThree, lengthOfSubmarine, pointsForSubmarine);
		Ship s4 = new Ship(shipFour, lengthOfDestroyer, pointsForDestroyer);
		Ship s5 = new Ship(shipFive, lengthOfPatrolBoat, pointsForPatrolBoat);
		
		//allocate a ship of Aircraft Carrier
		for(int loop = randSquareForAircraft; loop < (randSquareForAircraft+lengthOfAircraftCarrier); loop++) {
			this.theGrid.addShip(s1, randRowForAircraft, loop);
		}
		
		//allocate a ship of Battleship
		for(int loop = randSquareForBattleship; loop < (randSquareForBattleship+lengthOfBattleship); loop++) {
			this.theGrid.addShip(s2, randRowForBattleship, loop);
		}
		
		//allocate a ship of Submarine
		for(int loop = randSquareForSubmarine; loop < (randSquareForSubmarine+lengthOfSubmarine); loop++) {
			this.theGrid.addShip(s3, randRowForSubmarine, loop);
		}
		
		//allocate a ship of Destroyer
		for(int loop = randSquareForDestroyer; loop < (randSquareForDestroyer+lengthOfDestroyer); loop++) {
			this.theGrid.addShip(s4, randRowForDestroyer, loop);
		}
		
		//allocate a ship of Patrol Boat
		for(int loop = randSquareForPatrolBoat; loop < (randSquareForPatrolBoat+lengthOfPatrolBoat); loop++) {
			this.theGrid.addShip(s5, randRowForPatrolBoat, loop);
		}
		
		
		//asking user for X&Y
		askUserForXY();
	}
	
	public void highScoreKeeper() {
		try
		{
		    String filename= "highscore.txt";
		    FileWriter myWriter = new FileWriter(filename,true); //the true will append the new data
		    myWriter.append(playerName + " - " + Integer.toString(playerPoints));
		    
		    myWriter.append("\n");
		    myWriter.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public void displayHighScore() {
		try {
			  String highScoreMessage = "List of players with their scores";
		      File myObj = new File("highscore.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  highScoreMessage = highScoreMessage + "\n" + myReader.nextLine();
		      }

		      JOptionPane.showMessageDialog(null, highScoreMessage);
		      myReader.close();
		    } catch (FileNotFoundException e) {
		    	String gameLoadingErrorMessage = "An error occurred. Game could not be loaded.";
			    JOptionPane.showMessageDialog(null, gameLoadingErrorMessage);
		      e.printStackTrace();
		    }
		gameMenu();
	}
	
	public void displayShips() {
		placingShips();
		//display ship of Aircraft Carrier
		String displayAircraftCarrier = "Aircraft Carrier: ";
		for(int loop = randSquareForAircraft; loop < (randSquareForAircraft+lengthOfAircraftCarrier); loop++) {
			displayAircraftCarrier = displayAircraftCarrier + ("(" + randRowForAircraft + "," + loop + ") ");
		}
		
		//display ship of Battleship
		String displayBattleship = "Battleship: ";
		for(int loop = randSquareForBattleship; loop < (randSquareForBattleship+lengthOfBattleship); loop++) {
			displayBattleship = displayBattleship + ("(" + randRowForBattleship + "," + loop + ") ");
		}
		
		//display ship of Submarine
		String displaySubmarine = "Submarine: ";
		for(int loop = randSquareForSubmarine; loop < (randSquareForSubmarine+lengthOfSubmarine); loop++) {
			displaySubmarine = displaySubmarine + ("(" + randRowForSubmarine + "," + loop + ") ");
		}
		
		//display ship of Destroyer
		String displayDestroyer = "Destroyer: ";
		for(int loop = randSquareForDestroyer; loop < (randSquareForDestroyer+lengthOfDestroyer); loop++) {
			displayDestroyer = displayDestroyer + ("(" + randRowForDestroyer + "," + loop + ") ");
		}
		
		//display ship of Patrol Boat
		String displayPatrolBoat = "Patrol Boat: ";
		for(int loop = randSquareForPatrolBoat; loop < (randSquareForPatrolBoat+lengthOfPatrolBoat); loop++) {
			displayPatrolBoat = displayPatrolBoat + ("(" + randRowForPatrolBoat + "," + loop + ") ");
		}
		JOptionPane.showMessageDialog(null, displayAircraftCarrier + "\n" + 
				displayBattleship + "\n" + displaySubmarine + "\n" +
				displayDestroyer + "\n" + displayPatrolBoat);
		
		//asking user for X&Y
		askUserForXY();
	}

}//end class
