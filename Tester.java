import java.util.Scanner;

public class Tester {

	/**
	 * this class tests each cell against every other cell
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyboard = new Scanner(System.in);
		
		Board gameBoard = new Board();
		System.out.println(gameBoard);
		gameBoard.populateBoard(0, 0, 0);
		System.out.println(gameBoard);
		gameBoard.createFinalBoard("easy");
		System.out.println(gameBoard);
		int x = 0;
		while(x != 9)
		{
			System.out.println("Enter x, y coord and a value");
			x = keyboard.nextInt();
			int y = keyboard.nextInt();
			int newValue = keyboard.nextInt();
			if( !(x > 8 || x < 0) && !(y > 8 || y < 0))
			{
				boolean validNum = gameBoard.userChangeCell(x, y, newValue);
				if(!validNum)
					System.out.println("cant change that number");
				
			}
			else
				System.out.println("thats not a valid coord");
			System.out.println(gameBoard);
		}
		System.out.println("final check: " + gameBoard.finalCheck());
		
		
		
		
		
		/*for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				System.out.println(newBoard.checkNum(i, j, newBoard.getCellValue(i, j)));
			}*/
		
	}


}
