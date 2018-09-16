public int populateBoard(int currX, int currY, int finish)
	{
		int triedNums[][] = new int[81][9];
		for(int i = 0; i < 81; i++)
			for(int j = 0; j < 9; j++)
				triedNums[i][j] = 0;
		//if board is filled, exit loop
		while(finish != 82)
		{
			boolean fits = false;
			System.out.println("current X: " + currX + " current Y: " + currY + " current count: " + finish);
			System.out.println("current board: \n" + this.toString());
			
			Cell emptyCell = new Cell(-1, "red");
			
			int pool[] = new int[9];
			for(int i = 0; i < 9; i++)
				pool[i] = 0;
			
			while((pool[0] == 0 || 
					pool[1] == 0 ||	
					pool[2] == 0 || 
					pool[3] == 0 || 
					pool[4] == 0 ||	
					pool[5] == 0 ||	
					pool[6] == 0 ||	
					pool[7] == 0 || 
					pool[8] == 0) && !fits)
			{
				//get a random number
				Random rand = new Random();
				int randNum = rand.nextInt(9) + 1;
				while(pool[randNum-1] != 0 && triedNums[finish][randNum-1] == 0)
					randNum = rand.nextInt(9)+1;
				
				//add number to list of numbers used already
				System.out.println("rand: " + randNum + " pool: " + pool[randNum-1] + " triedNums: " + triedNums[finish][randNum-1]);
				pool[randNum-1]++;
				triedNums[finish][randNum-1]++;
				
				//if number fits in puzzle, insert it
				if(this.checkNum(currX, currY, randNum))
				{
					Cell newCell = new Cell(randNum, "red");
					this.setCell(currX, currY, newCell);
					if(currX < 8)
					{
						currX++;
						finish++;
						fits = true;
					}
					else
					{
						currX = 0;
						currY++;
						finish++;
						fits = true;
					}
				}
			}
			if(!fits)
			{
				finish--;
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
			}
				
		}
		
		return finish;
	}

















public int populateBoard(int currX, int currY, int finish)
{
	//if board is filled, exit recursion
	if(finish == 82)
	{
		System.out.println("Finished!");
		return finish;
	}
	//else try and insert a new cell
	else
	{
		System.out.println("current X: " + currX + " current Y: " + currY + " current count: " + finish);
		System.out.println("current board: \n" + this.toString());
		Cell emptyCell = new Cell(-1, "red");
		
		int pool[] = new int[9];
		for(int i = 0; i < 9; i++)
			pool[i] = 0;
		//keeps executing til numbers 1-9 have been tried
		while(pool[0] == 0 || 
				pool[1] == 0 ||	
				pool[2] == 0 || 
				pool[3] == 0 || 
				pool[4] == 0 ||	
				pool[5] == 0 ||	
				pool[6] == 0 ||	
				pool[7] == 0 || 
				pool[8] == 0)
		{
			//get a random number
			Random rand = new Random();
			int randNum = rand.nextInt(9) + 1;
			while(pool[randNum-1] != 0)
				randNum = rand.nextInt(9)+1;
			
			//add number to list of numbers
			pool[randNum-1]++;
			
			//if number fits in puzzle, insert it
			if(this.checkNum(currX, currY, randNum))
			{
				Cell newCell = new Cell(randNum, "red");
				this.setCell(currX, currY, newCell);
				if(currX < 8)
					return populateBoard(++currX, currY, ++finish);
				else
					return populateBoard(0, ++currY, ++finish);
			}				
		}	
		//if this point is reached without recusing then that
		//means no number will fit on the board in this spot without 
		//recursing backwards
		
		//delete previous cell
		if(currX == 0)
		{	
			System.out.println("Recursing X: 8 Y: " + (currY - 1));
			this.setCell(8, currY - 1, emptyCell);
			return populateBoard(8, --currY, --finish);
		}
		else
		{
			System.out.println("Recursing X: " + (currX-1) + " Y: " + currY);
			this.setCell(currX - 1, currY, emptyCell);
			return populateBoard(--currX, currY, --finish);
		}
	}
	
	
}