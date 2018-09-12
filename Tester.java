
public class Tester {

	/**
	 * this class tests each cell against every other cell
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board gameBoard = new Board();
		System.out.println(gameBoard);
		gameBoard.populateBoard(0, 0, 0);
		System.out.println(gameBoard);
		
		
		
		
		/*for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				System.out.println(newBoard.checkNum(i, j, newBoard.getCellValue(i, j)));
			}*/
		
	}
	private static int testMethod(int x)
	{
		System.out.println("X: " + x);
		if(x < 10)
			return testMethod(++x);
		else
			return x;
		//System.out.println("X: " + x);
		
		
	}

}
