/*
 * The Cell object is for individual numbers on the board, it contains the value
 * of the number in the cell and the color of the number for use in verifying if
 * the numbers were put in by the user of by the program
 */
public class Cell {
	private int value;
	private String color;
	
	/**
	 * @param value - desired value of cell
	 * @param color - desired color of number
	 */
	public Cell(int value, String color)
	{
		setValue(value);
		setColor(color);
	}
	
	/**
	 * sets the value of the cell to a number between 1 and 9, returns 
	 * -1 if the number does not fit within that range
	 * @param value to be set in cell
	 */
	public void setValue(int value)
	{
		if(!(value < 1 || value > 9))	
			this.value = value;
		else
			this.value = -1;
	}
	
	/**
	 * @return value in cell
	 */
	public int getValue()
	{
		return value;
	}
	/**
	 * sets the color of the cell
	 * @param color to be set in cell
	 */
	public void setColor(String color)
	{
		this.color = color;
	}
	/**
	 * @return color in cell
	 */
	public String getColor()
	{
		return color;
	}
	
	public String toString()
	{
		return "value: " + value + " color: " + color;
	}
}
