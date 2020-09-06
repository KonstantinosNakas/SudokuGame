import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
	final static int INDEX = 9;

	private static int[][] createArray() {
		String[][] readArray = new String[9][9];
		int[][] sudokuArray = new int[9][9];
		try {
			File file = new File("Sudoku.txt"); 
			Scanner sc = new Scanner(file);
			int m = 0;
			while (sc.hasNextLine()) {
				readArray[m] = sc.nextLine().split(" ");
		    	m++;
		  	} 
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		for (int i=0; i<INDEX; i++) {
			for (int j=0; j<INDEX; j++) {
				sudokuArray[i][j] = Integer.parseInt(readArray[i][j]); 
			}
		}
		return sudokuArray;
	}
	
	private static void printGame(int[][] sudokuArray) {
		System.out.println();
		for (int i=0; i<INDEX; i++) {
			for (int j=0; j<INDEX; j++) {
				System.out.print(sudokuArray[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static boolean valid(int[][] sudokuArray, int value, int x, int y) {
		for (int i=0; i<INDEX; i++) {
			if (sudokuArray[x][i]==value || sudokuArray[i][y]==value) {
				return false;
			}
		}
		int xBox = 0;
		int yBox = 0;
		if (x>2 && x<=5) {
			xBox = xBox + 3;
		}else if (x > 5) {
			xBox = xBox + 6;
		}
		if (y>2 && y<=5) {
			yBox = yBox + 3;
		}else if (y > 5) {
			yBox = yBox + 6;
		}
		for (int i=xBox; i<3+xBox; i++) {
			for (int j=yBox; j<3+yBox; j++) {
				if (sudokuArray[i][j] == value) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void playSudoku(int[][] sudokuArray) {
		printGame(sudokuArray);
		Scanner in = new Scanner(System.in);
		int value;
		int x;
		int y;
		int numberOfEmptySquares = 0;
		for (int i=0; i<INDEX; i++) {
			for (int j=0; j<INDEX; j++) {
				if (sudokuArray[i][j] == 0) {
					numberOfEmptySquares++;
				}
			}
		}	
		while (numberOfEmptySquares !=0 ) {
			System.out.println("Enter a number between 1-9:");
	        value = in.nextInt(); 
	        while (value<1 || value>9) {
	        	System.out.println("Wrong input. Give again a number between 1-9:");
	        	value = in.nextInt(); 
	        }
	        System.out.println("Enter the X value of square you want to put the number (range: 0-8)");
	        x = in.nextInt();
	        while (x<0 || x>8) {
	        	System.out.println("Wrong input. Give again a number between 0-8:");
	        	x = in.nextInt(); 
	        }
	        System.out.println("Enter the Y value of square you want to put the number (range: 0-8):");
	        y = in.nextInt();
	        while (y<0 || y>8) {
	        	System.out.println("Wrong input. Give again a number between 0-8:");
	        	y = in.nextInt(); 
	        }
	        if (valid(sudokuArray, value, x, y)) {
	        	sudokuArray[x][y] = value;
	        	numberOfEmptySquares--;
	        	printGame(sudokuArray);
	        }else {
	        	System.out.println("Number " + value + " is not valid in the specific square.");
	        }
        }
		System.out.println("Congratulations! You solve sudoku puzzle.");
	}
	
	public static boolean Solve(int[][] sudokuArray) {
		for (int i=0; i<INDEX; i++) {
			for (int j=0; j<INDEX; j++) {
				if (sudokuArray[i][j]==0){
					for (int k=1; k<=INDEX; k++) {
						if (valid(sudokuArray,k,i,j)) {
							sudokuArray[i][j] = k;
							if (Solve(sudokuArray))
								return true;
						}	
						sudokuArray[i][j] = 0;
					}
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] sudokuArray = createArray();
		
		System.out.println("To solve sudoku by yourself type 1. To solve by computer type everything else.");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		if (num == 1) {
			playSudoku(sudokuArray);
		}else {	
			Solve(sudokuArray);
			printGame(sudokuArray);
		}	
		
	}

}
