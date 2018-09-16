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
	public int populateBoard(int currX, int currY, int currentCell)
	{
		int triedNums[][] = new int[82][9];
		for(int i = 0; i < 81; i++)
			for(int j = 0; j < 9; j++)
				triedNums[i][j] = 0;
		//if board is filled, exit loop
		while(currentCell < 81)
		{
			boolean fits = false;
			System.out.println("current X: " + currX + " current Y: " + currY + " current count: " + currentCell);
			System.out.println("current board: \n" + this.toString());
			
			Cell emptyCell = new Cell(-1, "red");
			
			
			while((triedNums[currentCell][0] == 0 ||
					triedNums[currentCell][1] == 0 ||
					triedNums[currentCell][2] == 0 ||
					triedNums[currentCell][3] == 0 ||
					triedNums[currentCell][4] == 0 ||
					triedNums[currentCell][5] == 0 ||
					triedNums[currentCell][6] == 0 ||
					triedNums[currentCell][7] == 0 ||
					triedNums[currentCell][8] == 0) && !fits)
			{
				//get a random number
				Random rand = new Random();
				int randNum = rand.nextInt(9) + 1;
				while(triedNums[currentCell][randNum-1] != 0)
				{
					randNum = rand.nextInt(9)+1;
				}
				boolean allNumsTried = true;
				int counter = 0;
				while(counter < 8 && allNumsTried)
					if(triedNums[currentCell][counter++] == 0)
						allNumsTried = false;
						
				System.out.println(allNumsTried);
				//add number to list of numbers used already
				System.out.println("rand: " + randNum + " triedNums: " + triedNums[currentCell][randNum-1]);
				triedNums[currentCell][randNum-1]++;
				if(triedNums[currentCell][randNum-1] > 1)
				{
					System.out.println("DUN BROK");
				}
				
				//if number fits in puzzle, insert it
				if(this.checkNum(currX, currY, randNum) && !allNumsTried)
				{
					Cell newCell = new Cell(randNum, "red");
					this.setCell(currX, currY, newCell);
					if(currX < 8)
					{
						currX++;
						currentCell++;
						fits = true;
					}
					else
					{
						currX = 0;
						currY++;
						currentCell++;
						fits = true;
					}
				}
			}
			if(!fits)
			{
				if(currentCell >= 0)
					for(int i = 0; i < 9; i++)
					{	
						triedNums[currentCell][i] = 0;
					}
				if(currX == 0)
				{
					currX = 8;
					currY--;
					this.setCell(currX, currY, emptyCell);
				}
				else
				{
					currX--;
					this.setCell(currX, currY, emptyCell);
				}
				currentCell--;
			}
				
		}
		
		return currentCell;
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
