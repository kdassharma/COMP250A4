// KAUSTAV DAS SHARMA

import java.io.*;

public class Game {

	public static void main(String[] args) {
		InputStreamReader input2 = new InputStreamReader(System.in);
		System.out.println(Game.play(input2));
	}
	
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		int command = -1;
		c.print();
		System.out.print("Player 2, enter a move: ");
		while (true) { 
			
			try {
				command = Integer.parseInt(keyboard.readLine());
				if (!(command <= 6 && command >= 0)) {
					c.print();
					System.out.print("Player 2, enter a valid command: ");
				}
				else if(c.available[command] < 6) {
					break;
				}
				
				else {
					throw new Exception(); 
				}

			}
			
			catch (Exception e) {
				c.print();
				System.out.print("This column is full! Please enter a move: ");
			}
		}
		
		return command; 
	}
	
	public static int firstMovePlayer1 (){
		return 3;
	}
	
	public static int movePlayer1 (int columnPlayed2, Configuration c){
	
		int last = columnPlayed2;
		int output = -1;
		if (c.canWinNextRound(1) != -1) {
			output = c.canWinNextRound(1);
		}
		
		else if (c.canWinTwoTurns(1) != -1) {
			output = c.canWinTwoTurns(1);
		}
	
		else {
			
			int counter = 0;
			while (true) {
				if (last - counter >=0) {
					if (c.available[last-counter] < 6) {
						output = last - counter;
						break;
					}
				}
				
				if (last + counter < 7){
					if (c.available[last+counter] < 6) {
						output = last + counter;
						break;

					}
				}
				
				counter++;
				
			}
			
		}
		
		return output;
		
	}
	
}
