
import java.util.Scanner;
import java.util.Random;

public class Drop7
{
	public static Board board;

	public static void main(String[] args) 
	{

		board = new Board(7);
		//board.populateRandomly();
		//board.printBoard();
		//board.pop();
		//board.printBoard();

		Scanner scanner = new Scanner(System.in);
		Random rn = new Random();

		//int num  = scanner.nextInt(); 
		int rand = rn.nextInt(7) + 1;
		int num;

		do {
			System.out.println("Next drop: " + rand);
			//board.printBoard();
			num = scanner.nextInt();

			if ( num == 9 )
				break;

			board.drop(num, rand); 
			board.pop();
			board.printBoard();

			rand = rn.nextInt(7) + 1;


		} while ( true );

	}

}

class Board 
{
	public LinkedList board[];
	public int size;

	public Board (int s) {
		size = s;
		board = new LinkedList[size];

		for ( int i = 0; i < size; i++ ) 
			board[i] = new LinkedList();

	}

	public void drop(int i, int v) {
		board[i].add(v);
	}



	public void pop() {
		// Start from the assumption nothing pops 
		boolean popped = false;


		int sizes[] = new int[size];
		for ( int i = 0; i < size; i++ )
			sizes[i] = board[i].size();

		int scores[][] = new int[size][size];
		int score;
		for ( int i = size - 1; i >= 0; i-- ) {
			for ( int j = 0; j < size; j++ ) {
				score = board[j].size() - i;
				if ( score < 0 )
					score = 0;
				scores[j][i] = score;
			}
		}

		int count = 0;
		for ( int y = 0; y < size; y++ ) {
			for ( int x = 0; x < size; x++ ) {
				// scores[x][y] is a number and 
				// increments the count of the sub-array
				if ( scores[x][y] > 0 )
					count++;
				// Reached an empty spot, work backwards 
				// by count, setting scores[x][y] to 
				// that count 
				else {
					score = count;
					while ( count > 0 ) {
						scores[x-count][y] = score;
						count--;
					}
					count = 0;
				}

				// At the end of the row, work backwats 
				// by count settings scores as it goes
				if ( count != 0 && x == size - 1 ) {
					score = count;
					while ( count > 0 ) {
						scores[x-count+1][y] = score;
						count--;
					}
					count = 0;
				}
			}
			count = 0;
		}

		int curr; 
		for ( int j = size - 1; j >= 0; j-- ) {
			for ( int i = 0; i < size; i++ ) {
				curr = board[i].getValueAt(j+1);
				if ( curr == scores[i][j] ) {
					popped = true;
					// Mark the value for popping later 
					// (avoids dynamic sizing)
					board[i].setValueAt(j+1, -1);
				}
			}
		}

		int prevSize; 
		for ( int i = 0; i < size; i++) {
			// Popping a value changes the size of the board 
			prevSize = board[i].size();

			// Vertical removals
			board[i].removeValue(board[i].size());
			if ( prevSize != board[i].size() )
				popped = true;

			// Horizontal removals 
			board[i].removeValue(-1);
		}

		// Board needs to be checked again for combos 
		if ( popped == true ) {
			// Decomment for testing 
			//printBoard();
			pop();
		}

		// Nothing changed, return to main
		else 
			return;


	}


	public void printBoard() {
		int curr;
		for ( int j = size; j > 0; j-- ) {
			for ( int i = 0; i < size; i++ ) {
				curr = board[i].getValueAt(j);
				if ( curr == -1 )
					System.out.print(" *");
				else 
					System.out.print(" " + curr);
			}
			System.out.println();
		}
		System.out.println(" -------------");
	}

	// Next two methods are for testing purposes
	// Populate Randomly generates a board state 
	public void populateRandomly() {
		Random rn = new Random();
		int x;
		int rand; 

		for ( int i = 0; i < size; i++ ) {
			x = rn.nextInt(size) + 1;
			//System.out.println("x = " + x);
			for ( int j = 0; j < x; j++ ) {
				rand = rn.nextInt(size) + 1;
				board[i].add(rand);
			}
		}
	}


	public void printLists() {
		for ( int i = 0; i < size; i++ ) 
			System.out.println(board[i].toString());
	}

}