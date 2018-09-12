import java.util.Random;
/*
 * The board class stores a 2 dimensional array of cells to be displayed as a board later
 * For coordinate purposes the origin of x and y will be the bottom left corner
 */
public class Board {

	
	private Cell[][] actualBoard = new Cell[9][9];
	
	/**
	 * constructs a board full of default cells
	 */
	public Board()
	{
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				actualBoard[x][y] = new Cell(-1, "red");
			}
		}
	}
	
	/**
	 * This method makes a practice board, does not necessarily follow sudoku rules
	 */
	public int populateBoard(int currX, int currY, int finish)
	{
		//if board is filled, exit recursion
		if(finish == 82)
		{
			System.out.println("Finished!");
			return finish;
		}
		//else try and insert a new cell
		else
		{
			System.out.println("current X: " + currX + " current Y: " + currY + " current count: " + finish);
			System.out.println("current board: \n" + this.toString());
			Cell emptyCell = new Cell(-1, "red");
			
			int pool[] = new int[9];
			for(int i = 0; i < 9; i++)
				pool[i] = 0;
			//keeps executing til numbers 1-9 have been tried
			while(pool[0] == 0 || 
					pool[1] == 0 ||	
					pool[2] == 0 || 
					pool[3] == 0 || 
					pool[4] == 0 ||	
					pool[5] == 0 ||	
					pool[6] == 0 ||	
					pool[7] == 0 || 
					pool[8] == 0)
			{
				//get a random number
				Random rand = new Random();
				int randNum = rand.nextInt(9) + 1;
				while(pool[randNum-1] != 0)
					randNum = rand.nextInt(9)+1;
				
				//add number to list of numbers
				pool[randNum-1]++;
				
				//if number fits in puzzle, insert it
				if(this.checkNum(currX, currY, randNum))
				{
					Cell newCell = new Cell(randNum, "red");
					this.setCell(currX, currY, newCell);
					if(currX < 8)
						return populateBoard(++currX, currY, ++finish);
					else
						return populateBoard(0, ++currY, ++finish);
				}				
			}	
			//if this point is reached without recusing then that
			//means no number will fit on the board in this spot without 
			//recursing backwards
			
			//delete previous cell
			if(currX == 0)
			{	
				System.out.println("Recursing");
				this.setCell(8, --currY, emptyCell);
				return populateBoard(8, --currY, --finish);
			}
			else
			{
				System.out.println("Recursing");
				this.setCell(--currX, currY, emptyCell);
				return populateBoard(--currX, currY, --finish);
			}
		}
		
		
	}
	
	/**
	 * gets a cell from the board
	 * @param x coord
	 * @param y coord
	 * @return cell at x, y coords
	 */
	public Cell getCell(int x, int y)
	{
		Cell result = new Cell();
		result.setValue(actualBoard[x][y].getValue());
		return result;
	}
	
	public int getCellValue(int x, int y)
	{
		int result = actualBoard[x][y].getValue();
		return result;
	}
	/**
	 * 
	 * @param x - x coord of new Cell
	 * @param y - y coord of new Cell
	 * @param newCell - new Cell to be inserted
	 */
	public void setCell(int x, int y, Cell newCell)
	{
		actualBoard[x][y].setValue(newCell.getValue());
		actualBoard[x][y].setColor(newCell.getColor());
		
	}
	
	/**
	 * This function checks each number against all others on the board
	 * to see if the current cell is breaking the rules
	 * @param x coord
	 * @param y coord
	 * @param value in cell
	 * @return True if the cell fits in the current board, false otherwise
	 */
	public boolean checkNum(int x, int y, int value) 
	{
		//compares value to all other x values
		boolean xValid = true;
		for(int i = 0; i < 9; i++)
		{
			if(x != i && value == actualBoard[i][y].getValue())
				xValid = false;
		}
		
		//compares value to all other y values
		boolean yValid = true;
		for(int i = 0; i < 9; i++)
		{
			if(y != i && value == actualBoard[x][i].getValue())
				yValid = false;
		}
		
		//compares value to other values in 3x3 subsquare
		boolean squareValid = true;
		CoordOperations subSquare = new CoordOperations();
		int[] subOrigin = subSquare.getSquareCompareCoords(x, y);
		int xSubOrigin = subOrigin[0];
		int ySubOrigin = subOrigin[1];
		
		for(int i = xSubOrigin; i < xSubOrigin + 2; i++)
			for(int j = ySubOrigin; j < ySubOrigin + 2; j++)
			{
				if(x != i &&
						y != j &&
						value == actualBoard[i][j].getValue())
				squareValid = false;
			}
		
		
		return xValid && yValid && squareValid;
	}
	
	/**
	 * returns the full board representation
	 */
	public String toString()
	{
		String result = "";
		for(int y = 8; y > -1; y--)
		{
			for(int x = 0; x < 9; x++)
			{
				result = result + actualBoard[x][y].toString();
			}
			result = result + "\n";
		}
		return result;
	}
	
}
