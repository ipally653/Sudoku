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
		int coord[] = new int[2];
		
		if(x % 3 == 1) x--;
		if(x % 3 == 2)x = x - 2;
		else x++;		
		if(y % 3 == 1) y--;
		if(y % 3 == 2) y = y - 2;
		else y++;
		
		coord[0] = x;
		coord[1] = y;
		return coord;
	}
	
}












