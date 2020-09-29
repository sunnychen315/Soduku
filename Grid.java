package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;


	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}


	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}


	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Don’t change
	// “this” grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{	
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;
		
		if( isFull() )
			return null;
		
		ArrayList<Grid> grids = new ArrayList<Grid>(9);
		
		for(int i = 0; i < values.length; i++){
			for(int j = 0; j < values[0].length; j++){
				if(values[i][j] == 0){
					xOfNextEmptyCell = i; //finds x of next empty cell
					yOfNextEmptyCell = j; //finds y of next empty cell
					for(int k = 1; k <= values.length; k++){
						Grid g = new Grid(this);
						g.values[xOfNextEmptyCell][yOfNextEmptyCell] = k; //replacing empty value with 1-9 
						grids.add(g); //adding the grids to the array
					}	
					return grids;
				}	
			}	 		
		}		 		
		return grids;

	}


	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		HashSet<Integer> checker = new HashSet<Integer>(); //does not contain duplicates
		ArrayList<Integer> list = new ArrayList<Integer>(); //contains duplicates
		
		// Check every row. If you find an illegal row, return false.
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				if(values[i][j] != 0) {
					checker.add(values[i][j]); //storing values that are not empty into Hashset
					list.add((values[i][j]));  //storing values that are not empty into Arraylist
				}
				if(checker.size() != list.size()) { //if the size of arrays are different, means there is a repeat
					return false;
				}
			}
			//clearing both arrays so they can be reused for checking col and block
			checker.clear();
			list.clear();
	
		}
		
		// Check every column. If you find an illegal column, return false. (same logic as checking row)
		for(int j = 0; j < 9; j ++) {
			for(int i = 0; i < 9; i++) {
				if(values[i][j] != 0) {
					checker.add(values[i][j]);
					list.add((values[i][j]));
				}
				if(checker.size() != list.size()) {
					return false;
				}
			}
			checker.clear();
			list.clear();
		}
		
		
		// Check every block. If you find an illegal block, return false.
		
		
		for(int i = 0; i < 9; i+= 3) {
			for(int j = 0; j < 9; j+=3) {
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < 3; l++) {
						int num = values[i+k][j+l];
						if(num != 0) {
							checker.add(num);
							list.add(num);
						}
						if(checker.size() != list.size()) {
							return false;
						}
					}
					
				}
				checker.clear();
				list.clear();
			}

			
		}

		 
		// All rows/cols/blocks are legal.
		return true;
	}

	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(values[i][j]== 0) {
					return false;
				}
			}
		}
		return true;
	}


	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid g = (Grid)x;
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[0].length; j++) {
				if(g.values[i][j] != this.values[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Grid g = TestGridSupplier.getPuzzle1();
		Grid h = TestGridSupplier.getPuzzle2();
		System.out.println(g.equals(h));
		//System.out.println(g.isLegal());
	}
}
