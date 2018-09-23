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
						
				//add number to list of numbers used already
				triedNums[currentCell][randNum-1]++;
				
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
	
	public void createFinalBoard(String difficulty)
	{
		int counter = 0;
		Random rand = new Random();
		int randSquares = rand.nextInt(10) + 1;
		int randPosOrNeg = rand.nextInt(2);
		randSquares = randSquares / 2;
		if(randPosOrNeg == 1)
			randSquares = randSquares - (randSquares * 2);
		if(difficulty.equals("medium"))
			randSquares = randSquares + 5;
		if(difficulty.equals("hard"))
			randSquares = randSquares + 10;
		while(counter < (15 + randSquares))
		{
			int randX = rand.nextInt(9);
			int randY = rand.nextInt(9);
			
			if(getCell(randX, randY).getValue() != 0)
			{
				Cell blankCell = new Cell(0, "green");
				this.setCell(randX, randY, blankCell);
				counter++;
			}
		}
	}
	public boolean userChangeCell(int x, int y, int newValue)
	{
		if(getCell(x, y).getColor().equals("red"))
		{
			System.out.println(getCell(x,y).getColor());
			return false;
		}
		else
		{
			Cell newCell = new Cell(newValue, "green");
			this.setCell(x, y, newCell);
			return true;
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
		result.setColor(actualBoard[x][y].getColor());
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
		
		for(int i = xSubOrigin; i <= xSubOrigin + 2; i++)
			for(int j = ySubOrigin; j <= ySubOrigin + 2; j++)
			{
				if(x != i && y != j && value == actualBoard[i][j].getValue())
					squareValid = false;
			}		
		
		return xValid && yValid && squareValid;
	}
	
	public boolean finalCheck()
	{
		boolean result = true;
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(actualBoard[i][j].getValue() == 0)
					result = false;
				else if(!this.checkNum(i, j, actualBoard[i][j].getValue()))
					result = false;
			}
		}
		return result;
	}
	
	/**
	 * returns the full board representation
	 */
	public String toString()
	{
		String result = "";
		//for(int y = 8; y > -1; y--)
		for(int y = 8; y >= 0; y--)
		{
			for(int x = 0; x < 9; x++)
			{
				result = result + actualBoard[x][y].toString() + " ";
			}
			result = result + "\n";
		}
		return result;
	}
	
}
