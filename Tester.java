
public class Tester {

	/**
	 * this class tests each cell against every other cell
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board newBoard = new Board();
		
		newBoard.populateRandomBoard();
		
		
		
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				System.out.println(newBoard.checkNum(i, j, newBoard.getCellValue(i, j)));
			}
		
	}

}
