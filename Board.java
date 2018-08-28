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
				actualBoard[x][y] = new Cell(1,"red");
			}
		}
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
