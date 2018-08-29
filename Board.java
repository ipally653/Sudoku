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
				actualBoard[x][y] = new Cell(x+1, "red");
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
	public void placeCell(int x, int y, Cell newCell)
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
	 * @return T/F whether cell breaks a rule
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
		for(int x = 0; x < 9; x++)
		{
			for(int y = 8; y > -1; y--)
			{
				result = result + actualBoard[x][y].toString();
			}
			result = result + "\n";
		}
		return result;
	}
	
}
