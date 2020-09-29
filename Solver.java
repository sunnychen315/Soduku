package sudoku;

import java.util.*;


public class Solver 
{
	private Grid problem;
	private ArrayList<Grid>	solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	// 
	// FINISH THIS.
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
				return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			solutions.add(grid);
		}
		else
		{
			if(eval == Evaluation.CONTINUE) {
				ArrayList<Grid> g = grid.next9Grids();
				for(Grid grd : g) {
					solveRecurse(grd);
				}
				
			}
		}
	}
	
	//
	// COMPLETE THIS
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if(!grid.isLegal()) {
			return Evaluation.ABANDON;
		}
		else if(grid.isLegal() && grid.isFull() == true) {
			return Evaluation.ACCEPT;
		}
		else 
			return Evaluation.CONTINUE;

	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		// or any other puzzle
		Solver solver = new Solver(g);
		System.out.println("Will solve\n" + g);
		System.out.println();
		solver.solve();
		System.out.println(solver.getSolutions());
		System.out.println(solver.getSolutions().get(0).equals(TestGridSupplier.getSolution1()));
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
	}
}
