// KAUSTAV DAS SHARMA 260772982

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		
		int rowNum = available[index];
		if(rowNum == 6) {
			return;
		}
		board[index][rowNum] = player;
		available[index]++;
		if (available[index] == 6) {
			spaceLeft = false;
		}
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		
		int[] row = new int[7];
		for (int i = 0; i<7; i++) {
			row[i] = board[i][available[lastColumnPlayed]-1];
		}
		
		if (winSide(board[lastColumnPlayed],player) || winSide(row,player)) {
			return true;
		}
		
		int colNum = lastColumnPlayed;
		int rowNum = available[lastColumnPlayed] - 1;
		int count = 0;
		
		while (colNum>= 0 && colNum<7 && rowNum >= 0 && rowNum <6 && board[colNum][rowNum] == player) {
				
			count++;

			colNum++;
			rowNum++;
		}
		
		if (count>=4) {
			return true;
		}
		
		else {
			colNum = lastColumnPlayed - 1;
			rowNum = available[lastColumnPlayed] - 1 - 1;
		}
		
		while (colNum>= 0 && colNum<7 && rowNum >= 0 && rowNum <6 && board[colNum][rowNum] == player) {
			
			count++;

			colNum--;
			rowNum--;
		}
		
		if (count>=4) {
			return true;
		}
		
		else {
			colNum = lastColumnPlayed;
			rowNum = available[lastColumnPlayed] - 1;
			count = 0;
			
		}
		
		while (colNum>= 0 && colNum<7 && rowNum >= 0 && rowNum <6 && board[colNum][rowNum] == player) {
			
			count++;

			colNum++;
			rowNum--;
		}
		
		if (count>=4) {
			return true;
		}
		
		else {
			colNum = lastColumnPlayed - 1;
			rowNum = available[lastColumnPlayed] - 1 + 1;
		}
		
		while (colNum>= 0 && colNum<7 && rowNum >= 0 && rowNum <6 && board[colNum][rowNum] == player) {
			
			count++;

			colNum--;
			rowNum++;
		}
		
		if (count >= 4) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean winSide (int[] arr, int player) {
		int count = 0;
		for (int i=0;i<arr.length-1;i++) {
			if (arr[i] == player && arr[i+1] == player) {
				count++;
			}
			
			else {
				count = 0;
			}
			
			if (count == 3) {
				break;
			}
		}
		
		if (count == 3) {
			return true;
		} 
		
		return false;
	}
	
	public int canWinNextRound (int player){
		
		int output = -1;
		for (int i= 0; i < 7; i++) {
			addDisk(i,player);
			if (available[i] < 6) {
				if (isWinning(i, player)) {
					output = i;
					removeDisk(i);
					return i;
				}
				removeDisk(i);
			}
		}
		
		return output;
		
	}
	
	public void removeDisk (int index){
		
		int rowNum = available[index] - 1;
		board[index][rowNum] = 0;
		available[index]--;
	}
	
	
	public int canWinTwoTurns (int player){
		int winning = -1;
		boolean winnable = true;
		for(int i  = 0; i < 7; i++) {
			if(available[i] <6) {
				addDisk(i, player);
				winnable = true;
				for(int j = 0; j < 7; j ++) {
					if(available[j] <6) {
						addDisk(j, 3- player);
						if(canWinNextRound(player) == -1 || isWinning(j, 3- player)) {
							winnable = false;
						}
						removeDisk(j);
						
					}
				}
				removeDisk(i);
				if(winnable) {
					return i;
				}
			}
				
		}
			
		return winning;
	}
	
	
} 