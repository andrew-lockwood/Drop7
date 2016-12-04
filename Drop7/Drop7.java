
import java.util.Scanner;
import java.util.Random;

public class Drop7
{
	public static Board board;
	public static void main(String[] args) 
	{

		board = new Board(7);
		board.populateRandomly();
		board.printBoard();
		System.out.println();

		//test.findVertical();
		board.pop();
		board.printBoard();



		/*
		Scanner scanner = new Scanner(System.in);

		int num; 
		Random rn = new Random();
		int x;
		char rand;

		while ( (num = scanner.nextInt()) != 9)
		{
			x = rn.nextInt(7) + 1;

			System.out.println("next num: " + x);
			rand = (char)(x + '0');
			drop(num, rand); 
			pop();
			printBoard();
		}
		*/
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

	public void pop() {
		boolean popped = false;

		// Create an array of list sizes 
		int sizes[] = new int[size];
		for ( int i = 0; i < size; i++ )
			sizes[i] = board[i].size();

		int scores[][] = new int[size][size];
		int score;
		for ( int i = 0; i < size; i++ ) {
			for ( int j = 0; j < size; j++ ) {
				score = board[i].size() - i;
				if ( score < 0 )
					score = 0;
				scores[j][i] = (score);
			}
		}

		int count = 0;
		for ( int y = 0; y < size; y++ ) {
			for ( int x = 0; x < size; x++ ) {
				if ( scores[x][y] > 0 )
					count++;
				else {
					score = count;
					while ( count > 0 ) {
						scores[x-count][y] = score;
						count--;
					}
				}
				if ( count != 0 && x == size - 1 ) {
					score = count;
					while ( count > 0 ) {
						scores[x-count+1][y] = score;
						count--;
					}
				}
			}
		}

		int curr; 
		for ( int j = size - 1; j >= 0; j-- ) {
			for ( int i = 0; i < size; i++ ) {
				curr = board[i].getValueAt(j+1);
				if ( curr == scores[i][j] ) {
					popped = true;
					board[i].setValueAt(j+1, -1);
				}
			}
		}

		int prevSize; 
		for ( int i = 0; i < size; i++) {
			prevSize = board[i].size();
			board[i].removeValue(board[i].size());
			// Popping a value changes the size of the board 
			if ( prevSize != board[i].size() )
				popped = true;
			board[i].removeValue(-1);
		}

		if ( popped == true ) {
			// Decomment for testing 
			//printBoard();
			pop();
		}

		else 
			return;


	}

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
		System.out.println("--------------");
	}
}