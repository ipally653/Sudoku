/*
 * This class is to perform operations using coordinates, such as finding ranges 
 * of coords that should be compared, this is mostly apart from board class to improve 
 * readability of code
 */
public class CoordOperations {

	public CoordOperations()
	{
		
	}
	
	/**
	 * This function returns the origin coords of one of the 9 3x3 subsquares that 
	 * make up a sudoku board for comparision
	 */
	public int[] getSquareCompareCoords(int x, int y)
	{
		//System.out.println("x: " + x + " y: " + y);
		int coord[] = new int[2];
		
		if(x % 3 == 1) x--;
		else if(x % 3 == 2) x = x - 2;		
		
		if(y % 3 == 1) y--;
		else if(y % 3 == 2) y = y - 2;
		
		//System.out.println("New x: " + x + " new y: " + y);

		coord[0] = x;
		coord[1] = y;
		return coord;
	}
	
}












