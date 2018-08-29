
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board newBoard = new Board();
		System.out.println(newBoard);
		boolean x = false;
		x = newBoard.checkNum(3, 4, 2);
		System.out.println(x);
	}

}
