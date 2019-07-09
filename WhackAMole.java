//package whack;

import java.util.Scanner;
import java.util.Random; 

public class WhackAMole {
	
	int score, molesLeft, attemptsLeft; 
	char[][] moleGrid;
	
	//Constructor
	WhackAMole (int numAttempts, int gridDimension) {
		this.attemptsLeft = numAttempts; 
		this.moleGrid = new char[gridDimension][gridDimension];
		molesLeft = 0; 
		//HERE NEED TO INITIALIZE MOLEGRID WITH APPROPRIATE CHARACTER!
		for (int i = 0 ; i < moleGrid.length ; i++) {
			for (int j = 0; j < moleGrid[i].length ; j++) {
				this.moleGrid[i][j] = '*'; 
			}
		}
	}
	
	//Given a location, a mole is placed at that location
	boolean place(int x, int y) {
		if (moleGrid[x][y] == 'M') {
			return false;
		}
		else {
			this.moleGrid[x][y] = 'M';
			molesLeft++;
			return true;
		}
	}
	
	//Given a location, the user will take a whack at that location
	void whack(int x, int y) {
		if (moleGrid[x][y] == 'M') {
			this.moleGrid[x][y] = 'W';
			this.score++;
			this.attemptsLeft--;
			this.molesLeft--;
		}
		else {
			this.attemptsLeft--;
		}
	}
	
	//Prints grid to user without showing moles, will only show whacked spots
	void printGridToUser() {
		for (int i = 0 ; i < moleGrid.length ; i++) {
			for (int j = 0; j < moleGrid[i].length ; j++) {
				if ( moleGrid[i][j] == 'W') {
					System.out.print(" " + moleGrid[i][j] + " ");
				}
				else {
					System.out.print(" " + "*" + " ");
				}
			}
			System.out.println(" ");
		}
	}
	
	//Prints entire grid completely
	void printGrid() {
		for (int i = 0 ; i < moleGrid.length ; i++) {
			for (int j = 0; j < moleGrid[i].length ; j++) {
				if ( moleGrid[i][j] == 'M' || moleGrid[i][j] == 'W') {
					System.out.print(" " + moleGrid[i][j] + " ");
				}
				else {
					System.out.print(" " + "*" + " ");
				}
			}
			System.out.println(" ");
		}
	}
	
	//MAIN method
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random(); 
		WhackAMole newGame = new WhackAMole(50, 10);
		
		int xCoordinate = 0 , yCoordinate = 0 ; 
		
		//Placing the moles randomly on the board
		for (int i=0 ; i< 10 ; i++) {
			boolean placeMole = false; 
			
			while (placeMole != true) {	
				int x = rand.nextInt(10);
				int y = rand.nextInt(10); 
				
				placeMole = newGame.place(x,y);
			}
		}
		//Starting screen
		System.out.println("WELCOME TO THE WHACK-A-MOLE GAME! "); 
		System.out.println("You will have 50 attempts to whack the 10 available moles. "); 
		System.out.println("To exit the game enter the -1 for the x and y coordinates");
		
		
		//asking user to play the game
		while ((newGame.attemptsLeft != 0) && ( xCoordinate != -1 && yCoordinate != -1)) {
			
			newGame.printGridToUser(); 
			
			//Getting input from user
			System.out.println(" "); 
			System.out.println("AttemptsLeft: " + newGame.attemptsLeft);
			System.out.print("Enter an x coordinate (from 0-9): ");
			xCoordinate = scanner.nextInt(); 
			System.out.print("Enter a y coordinate (from 0-9): ");
			yCoordinate = scanner.nextInt();  
			System.out.println(" "); 
		
			//playing game as long as user doesn't quit
			if ( xCoordinate != -1 && yCoordinate != -1) {
				newGame.whack(xCoordinate,yCoordinate);
			}
			else {
				System.out.println("Thank you for playing!");
			}
			
		}
		//Printing out the game board after user quits or no attempts left
		newGame.printGrid();
	}

}