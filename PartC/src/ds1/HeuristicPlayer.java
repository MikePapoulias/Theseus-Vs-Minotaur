package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* HeuristicPlayer Class - contains all necessary variables, extends Player-class
 * These are:
 * 			ArrayList<Integer[]> - type variable named path (stores statistics, necessary 
 * 					for the best movement of the Heuristic Player),
 * 			count1 (total times that Heuristic Player moved up),
 *			count3 (total times that Heuristic Player moved right),
 *			count5 (total times that Heuristic Player moved down),
 *			count7 (total times that Heuristic Player moved left),
 *			thturns (total rounds that HeuristicPlayer has played).
 */       

import java.util.ArrayList;
import java.util.Random;

public class HeuristicPlayer extends Player
{
	ArrayList<Integer[]> path;
	private int count1;
	private int count3;
	private int count5;
	private int count7;
	private int thturns;
	
	// First HeuristicPlayerConstructor - initializes all its variables to zero.
	public HeuristicPlayer()
	{
		super();
		this.path = new ArrayList<Integer[]>();
		count1 = 0;
		count3 = 0;
		count5 = 0;
		count7 = 0;
		thturns = 0;
	}
	
	// Second HeuristicPlayerConstructor - contains all its variables.
	public HeuristicPlayer(int PlayerId, String name, Board board, int score, int x, int y, int n, int count1, int count3, int count5, int count7, int thturns)
	{
		super(PlayerId, name, board, score, x, y);
		this.path = new ArrayList<Integer[]>(n);
		this.count1 = count1;
		this.count3 = count3;
		this.count5 = count5;
		this.count7 = count7;
		this.thturns = thturns;
	}
	
	// Third HeuristicPlayerConstructor - contains a HeuristicPlayer-type object(a).
	public HeuristicPlayer(HeuristicPlayer a)
	{
		this.setPlayerId(a.getPlayerId());
		this.setX(a.getX());
		this.setY(a.getY());
		this.setBoard(a.getBoard());
		this.setScore(a.getScore());
		this.setName(a.getName());
		this.path = a.path;
		this.count1 = a.count1;
		this.count3 = a.count3;
		this.count5 = a.count5;
		this.count7 = a.count7;
		this.thturns = a.thturns;
	}

	// GettersHeuristicPlayerFunctions - Each function returns its corresponding variables.
	public int getcount1() 
	{  
		return count1; 
	}
	
	public int getcount3() 
	{  
		return count3; 
	}
	
	public int getcount5() 
	{  
		return count5; 
	}
	
	public int getcount7() 
	{  
		return count7; 
	}
	
	public int getthturns() 
	{ 
		return thturns;
	}

	// SettersHeuristicPlayerFunctions - Each function initializes its corresponding variables.
	public void setcount1(int count) 
	{	
		count1 = count;
	}
	
	public void setcount3(int count)
	{	
		count3=count; 
	}
	
	public void setcount5(int count)
	{	
		count5=count; 
	}
	
	public void setcount7(int count) 
	{	
		count7=count; 
	}
	
	public void setthturns(int x) 
	{
		this.thturns = x;
	}

	
/* OpponentDist1 function - this function participates in the selection of the player's
 * best move, checking if the opponent is near, performing upward movements.
 * It's arguments are: th (Theseus' position) and min (Minotaurus' position)
 */
	double OpponentDist1(int th, int min)
	{
		int const_th = th;
		int N = this.getBoard().getN();
		double x = 0;
		
		if(this.getBoard().tiles[th].getup())
			x = -10;
		
		if(!this.getBoard().tiles[th].getup())
		{
			if(min == th+N)
				x = -1;
			
			else
			{
				th += N;
				
				if(min == th+N && !this.getBoard().tiles[th].getup())
					x = -0.5;
				
				else
				{
					if(!this.getBoard().tiles[th].getup())
					{
						th += N;
				
						if(min == th+N && !this.getBoard().tiles[th].getup())
							x = -0.3;
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
/* OpponentDist3 function - this function participates in the selection of the player's
 * best move, checking if the opponent is near, performing right movements.
 * It's arguments are: th (Theseus' position) and min (Minotaurus' position)
*/
	double OpponentDist3(int th, int min)
	{
		int const_th = th;
		double x = 0;
		
		if(this.getBoard().tiles[th].getright())
			x = -10;
		
		if(!this.getBoard().tiles[th].getright())
		{
			if(min == th+1)
				x = -1;
			
			else
			{
				th += 1;
				
				if(min == th+1 && !this.getBoard().tiles[th].getright())
					x = -0.5;
				
				else
				{
					if(!this.getBoard().tiles[th].getright())
					{
						th += 1;
				
						if(min == th+1 && !this.getBoard().tiles[th].getright())
							x = -0.3;
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
/* OpponentDist5 function - this function participates in the selection of the player's
 * best move, checking if the opponent is near, performing downward movements.
 * It's arguments are: th (Theseus' position) and min (Minotaurus' position)
 */	
	double OpponentDist5(int th, int min)
	{
		int const_th = th;
		int N = this.getBoard().getN();
		double x = 0;
		
		if(this.getBoard().tiles[th].getdown())
			x = -10;
		
		if(!this.getBoard().tiles[th].getdown())
		{
			if(min == th-N)
				x = -1;
			
			else
			{
				th -= N;
				
				if(min == th-N && !this.getBoard().tiles[th].getdown())
					x = -0.5;
				
				else
				{
					if(!this.getBoard().tiles[th].getdown())
					{
						th -= N;
				
						if(min == th-N && !this.getBoard().tiles[th].getdown())
							x = -0.3;
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
/* OpponentDist7 function - this function participates in the selection of the player's
 * best move, checking if the opponent is near, performing left movements.
 * It's arguments are: th (Theseus' position) and min (Minotaurus' position)
 */	
	double OpponentDist7(int th, int min)
	{
		int const_th = th;
		double x = 0;
		
		if(this.getBoard().tiles[th].getleft())
			x = -10;
		
		if(!this.getBoard().tiles[th].getleft())
		{
			if(min == th-1)
				x = -1;
			
			else
			{
				th -= 1;
				
				if(min == th-1 && !this.getBoard().tiles[th].getleft())
					x = -0.5;
				
				else
				{
					if(!this.getBoard().tiles[th].getleft())
					{
						th -= 1;
				
						if(min == th-1 && !this.getBoard().tiles[th].getleft())
							x = -0.3;
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
	
/* NearSupplies1 function - this function participates in the selection of the player's
 * best move, checking if there is a near supply, performing upward movements.
 * It's arguments is: th (Theseus' position).
 */
	double NearSupplies1(int th)
	{
		int i;
		int const_th = th;
		int N = this.getBoard().getN();
		int S = this.getBoard().getS();
		double x = 0;
		
		if(this.getBoard().tiles[th].getup())
			x = -10;
		
		if(!this.getBoard().tiles[th].getup())
		{
			for(i=0; i<S; i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == th+N)
				{
					x = 1;
					break;
				}
			}
		
			if(x!=1)
			{
				th += N;
				
				if(!this.getBoard().tiles[th].getup())
				{	
					for(i=0; i<S; i++)
					{
						if(getBoard().supplies[i].getsupplyTileId() == th+N)
						{
							x = 0.5;
							break;
						}
					}
				
					if(x!=0.5)
					{
						if(!this.getBoard().tiles[th].getup())
						{
							th += N;
				
							for(i=0; i<S; i++)
							{
								if(getBoard().supplies[i].getsupplyTileId() == th+N && !this.getBoard().tiles[th].getup())
								{
									x = 0.3;
									break;
								}
							}
						}
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
/* NearSupplies3 function - this function participates in the selection of the player's
 * best move, checking if there is a near supply, performing right movements.
 * It's arguments is: th (Theseus' position).
 */	
	double NearSupplies3(int th)
	{
		int i;
		int const_th = th;
		int S = this.getBoard().getS();
		double x = 0;
		
		if(this.getBoard().tiles[th].getright())
			x = -10;
		
		if(!this.getBoard().tiles[th].getright())
		{
			for(i=0; i<S; i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == th+1)
				{
					x = 1;
					break;
				}
			}
		
			if(x!=1)
			{
				th += 1;
				
				if(!this.getBoard().tiles[th].getright())
				{	
					for(i=0; i<S; i++)
					{
						if(getBoard().supplies[i].getsupplyTileId() == th+1)
						{
							x = 0.5;
							break;
						}
					}
				
					if(x!=0.5)
					{
						if(!this.getBoard().tiles[th].getright())
						{
							th += 1;
				
							for(i=0; i<S; i++)
							{
								if(getBoard().supplies[i].getsupplyTileId() == th+1 && !this.getBoard().tiles[th].getright())
								{
									x = 0.3;
									break;
								}
							}
						}
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
/* NearSupplies5 function - this function participates in the selection of the player's
 * best move, checking if there is a near supply, performing downward movements.
 * It's arguments is: th (Theseus' position).
 */	
	double NearSupplies5(int th)
	{
		int i;
		int const_th = th;
		int N = this.getBoard().getN();
		int S = this.getBoard().getS();
		double x = 0;
		
		if(this.getBoard().tiles[th].getdown())
			x = -10;
		
		if(!this.getBoard().tiles[th].getdown())
		{
			for(i=0; i<S; i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == th-N)
				{
					x = 1;
					break;
				}
			}
		
			if(x!=1)
			{
				th -= N;
				
				if(!this.getBoard().tiles[th].getdown())
				{	
					for(i=0; i<S; i++)
					{
						if(getBoard().supplies[i].getsupplyTileId() == th-N)
						{
							x = 0.5;
							break;
						}
					}
				
					if(x!=0.5)
					{
						if(!this.getBoard().tiles[th].getdown())
						{
							th -= N;
				
							for(i=0; i<S; i++)
							{
								if(getBoard().supplies[i].getsupplyTileId() == th-N && !this.getBoard().tiles[th].getdown())
								{
									x = 0.3;
									break;
								}
							}
						}
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
/* NearSupplies7 function - this function participates in the selection of the player's
 * best move, checking if there is a near supply, performing left movements.
 * It's arguments is: th (Theseus' position).
 */	
	double NearSupplies7(int th)
	{
		int i;
		int const_th = th;
		int S = this.getBoard().getS();
		double x = 0;
		
		if(this.getBoard().tiles[th].getleft())
			x = -10;
		
		if(!this.getBoard().tiles[th].getleft())
		{
			for(i=0; i<S; i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == th-1)
				{
					x = 1;
					break;
				}
			}
		
			if(x!=1)
			{
				th -= 1;
				
				if(!this.getBoard().tiles[th].getleft())
				{	
					for(i=0; i<S; i++)
					{
						if(getBoard().supplies[i].getsupplyTileId() == th-1)
						{
							x = 0.5;
							break;
						}
					}
				
					if(x!=0.5)
					{
						if(!this.getBoard().tiles[th].getleft())
						{
							th -= 1;
				
							for(i=0; i<S; i++)
							{
								if(getBoard().supplies[i].getsupplyTileId() == th-1 && !this.getBoard().tiles[th].getleft())
								{
									x = 0.3;
									break;
								}
							}
						}
					}
				}
			}
		}
		th = const_th;
		return x;
	}
	
	
/* Evaluate function - this function evaluates the HeuristicPlayer's possible movements
 * through an evaluate-algorithm.
 * It's variables are: currentPos (HeuristicPlayer's position),
 * 					   opponentPos (Opponent's position) and
 * 					   dice (HeuristicPlayer's dice)   
 */
	double evaluate(int currentPos, int opponentPos, int dice)
	{
		double x = 0;
		
		if(dice == 1)
			x = 0.46 * NearSupplies1(currentPos) + 0.54 * OpponentDist1(currentPos, opponentPos);
			
		
		if(dice == 3)
			x = 0.46 * NearSupplies3(currentPos) + 0.54 * OpponentDist3(currentPos, opponentPos);
			
			
		if(dice == 5)
			x = 0.46 * NearSupplies5(currentPos) + 0.54 * OpponentDist5(currentPos, opponentPos);
			
		
		if(dice == 7)
			x = 0.46 * NearSupplies7(currentPos) + 0.54 * OpponentDist7(currentPos, opponentPos);
		
		return x;
	}
	
	
/* GetNextMove function - this function returns the HeuristicPlayer's new position, by 
 * calling the evaluate function and classifying it's data with a bubble sort function.
 * It's variables are: currentPos (HeuristicPlayer's position) and
 * 					   opponentPos (Opponent's position) 
 */
	int getNextMove(int currentPos, int opponentPos)
	{
		int i, j;
		int k = 1;
		int m;
		double[][] x_array;
		x_array = new double[2][4];
			
		// Calling the evaluate function and storing it's data to a double array (x_array).
		for(j=0; j<4; j++)
		{
			x_array[0][j] = j+k;
			x_array[1][j] = evaluate(currentPos, opponentPos, j+k);
			k++;
		}
			
		// Bubble sort.
		double temp_evaluation;
		double temp_dice;
		for(m=1; m<4; m++)
		{
			for(j=3; j>=m; j--)
			{
				if(x_array[1][j-1] >= x_array[1][j])
				{
					temp_evaluation = x_array[1][j-1];
					x_array[1][j-1] = x_array[1][j];
					x_array[1][j] = temp_evaluation;
						
					temp_dice = x_array[0][j-1];
					x_array[0][j-1] = x_array[0][j];
					x_array[0][j] = temp_dice;
				}
			}
		}

		// a new Integer[] array - type.	
		Integer[] path_array;
		path_array = new Integer[4];
			
		path_array[0] = (int)x_array[0][3];
		int choice;
			
		// Checking for the HeuristicPlayer's best movement.
		if(x_array[1][3] == x_array[1][2])
		{
			Random rand = new Random();
			choice = rand.nextInt(2);
			if(choice == 0)
				path_array[0] = ((int)x_array[0][3]);
			else if(choice == 1)
				path_array[0] = ((int)x_array[0][2]);
		}
		if(x_array[1][3] == x_array[1][2] && x_array[1][2] == x_array[1][1])
		{
			Random rand = new Random();
			choice = rand.nextInt(3);
			if(choice == 0)
				path_array[0] = ((int)x_array[0][3]);
			else if(choice == 1)
				path_array[0] = ((int)x_array[0][2]);
			else if(choice == 2)
				path_array[0] = ((int)x_array[0][1]);
		}
		if(x_array[1][3] == x_array[1][2] && x_array[1][2] == x_array[1][1] && x_array[1][1] == x_array[1][0])
		{
			Random rand = new Random();
			choice = rand.nextInt(4);
			if(choice == 0)
				path_array[0] = ((int)x_array[0][3]);
			else if(choice == 1)
				path_array[0] = ((int)x_array[0][2]);
			else if(choice == 2)
				path_array[0] = ((int)x_array[0][1]);
			else if(choice == 3)
				path_array[0] = ((int)x_array[0][0]);
		}
		
		path_array[1] = 0;
			
		// If HeuristicPlayer decides move up.
		if(path_array[0] == 1)
		{
			currentPos += this.getBoard().getN();
			this.setY(this.getY() +1);
			setcount1(++this.count1);
				
			for(i=0; i<this.getBoard().getS(); i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == currentPos)
				{
					this.getBoard().supplies[i].setx(-1);
					this.getBoard().supplies[i].sety(-1);
					this.getBoard().supplies[i].setsupplyTileId(-1);
					this.setScore(this.getScore()+1);
					path_array[1] = 1;
						
					break;
				}
			}
			System.out.println("Theseus moved up.");
			if(path_array[1] == 1)
				System.out.println("Theseus picked up a supply.");
			System.out.println("[ Id:" + this.getBoard().tiles[currentPos].gettileId() + "  X:" + this.getBoard().tiles[currentPos].gety() +
							   "  Y:" + this.getBoard().tiles[currentPos].getx() + "  Score:" + this.getScore() + " ]");
		}
			
		// If HeuristicPlayer decides move right.	
		if(path_array[0] == 3)
		{
			currentPos += 1;
			this.setX(this.getX()+1);
			setcount3(++this.count3);
				
			for(i=0; i<this.getBoard().getS(); i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == currentPos)
				{
					this.getBoard().supplies[i].setx(-1);
					this.getBoard().supplies[i].sety(-1);
					this.getBoard().supplies[i].setsupplyTileId(-1);
					this.setScore(this.getScore()+1);
					path_array[1] = 1;
					
					break;
				}
			}
			System.out.println("Theseus moved right.");
			if(path_array[1] == 1)
				System.out.println("Theseus picked up a supply.");
			System.out.println("[ Id:" + this.getBoard().tiles[currentPos].gettileId() + "  X:" + this.getBoard().tiles[currentPos].gety() +
							   "  Y:" + this.getBoard().tiles[currentPos].getx() + "  Score:" + this.getScore() + " ]");
		}
			
		// If HeuristicPlayer decides move down.
		if(path_array[0] == 5)
		{
			currentPos -= this.getBoard().getN();
			this.setY(this.getY() -1);
			setcount5(++this.count5);
				
			for(i=0; i<this.getBoard().getS(); i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == currentPos)
				{
					this.getBoard().supplies[i].setx(-1);
					this.getBoard().supplies[i].sety(-1);
					this.getBoard().supplies[i].setsupplyTileId(-1);
					this.setScore(this.getScore()+1);
					path_array[1] = 1;
						
					break;
				}
			}
			System.out.println("Theseus moved down.");
			if(path_array[1] == 1)
				System.out.println("Theseus picked up a supply.");
			System.out.println("[ Id:" + this.getBoard().tiles[currentPos].gettileId() + "  X:" + this.getBoard().tiles[currentPos].gety() +
							   "  Y:" + this.getBoard().tiles[currentPos].getx() + "  Score:" + this.getScore() + " ]");
		}
			
		// If HeuristicPlayer decides move left.
		if(path_array[0] == 7)
		{
			currentPos -= 1;
			this.setX(this.getX()-1);
			setcount7(++this.count7);

			for(i=0; i<this.getBoard().getS(); i++)
			{
				if(getBoard().supplies[i].getsupplyTileId() == currentPos)
				{
					this.getBoard().supplies[i].setx(-1);
					this.getBoard().supplies[i].sety(-1);
					this.getBoard().supplies[i].setsupplyTileId(-1);
					this.setScore(this.getScore()+1);
					path_array[1] = 1;
						
					break;
				}
			}
			System.out.println("Theseus moved left.");
			if(path_array[1] == 1)
				System.out.println("Theseus picked up a supply.");
			System.out.println("[ Id:" + this.getBoard().tiles[currentPos].gettileId() + "  X:" + this.getBoard().tiles[currentPos].gety() +
							   "  Y:" + this.getBoard().tiles[currentPos].getx() + "  Score:" + this.getScore() + " ]");
		}
			
		/* Checking if there is a near supply, after HeuristicPlauer's movement,
		 * by calling the NearSupplies functions.
		 */
		path_array[2] = -1;
		if((NearSupplies1(currentPos) == 0.3) || (NearSupplies3(currentPos) == 0.3) ||
		   (NearSupplies5(currentPos) == 0.3) || (NearSupplies7(currentPos) == 0.3)	  )
			path_array[2] = 3;
			
		if((NearSupplies1(currentPos) == 0.5) || (NearSupplies3(currentPos) == 0.5) ||
	       (NearSupplies5(currentPos) == 0.5) || (NearSupplies7(currentPos) == 0.5)	  )
			path_array[2] = 2;
			
		if((NearSupplies1(currentPos) == 1) || (NearSupplies3(currentPos) == 1) ||
		   (NearSupplies5(currentPos) == 1) || (NearSupplies7(currentPos) == 1)	  )
			path_array[2] = 1;
			
			
		/* Checking if the opponent is near, after HeuristicPlauer's movement,
		 * by calling the OpponentDist functions.
		 */	
		path_array[3] = -1;
		if((OpponentDist1(currentPos, opponentPos) == -1) || (OpponentDist3(currentPos, opponentPos) == -1) ||
		   (OpponentDist5(currentPos, opponentPos) == -1) || (OpponentDist7(currentPos, opponentPos) == -1)  )
			path_array[3] = 1;
			
		if((OpponentDist1(currentPos, opponentPos) == -0.5) || (OpponentDist3(currentPos, opponentPos) == -0.5) ||
		   (OpponentDist5(currentPos, opponentPos) == -0.5)	|| (OpponentDist7(currentPos, opponentPos) == -0.5))
			path_array[3] = 2;
			
		if((OpponentDist1(currentPos, opponentPos) == -0.3) || (OpponentDist3(currentPos, opponentPos) == -0.3) ||
		   (OpponentDist5(currentPos, opponentPos) == -0.3)	|| (OpponentDist7(currentPos, opponentPos) == -0.3))
			path_array[3] = 3;
			
		// All array's data stores to the list.	
		path.add(path_array);
		
		thturns++;
			
		return currentPos; 
	}
		
/* Statistics function - this function prints all HeuristicPlayer movements statistics
 * on console at the end of game. 	
 */
	public void statistics() 
	{		
		int i;
		int k=1;
		int sums=0;
		
		for(i=0; i<thturns; i++)
		{
			System.out.println();
			System.out.println("Round: " + (i+k));
			
			if(path.get(i)[0] == 1)
				System.out.println("Theseus moved up - (dice = 1)");
			if(path.get(i)[0] == 3)
				System.out.println("Theseus moved right - (dice = 3)");
			if(path.get(i)[0] == 5)
				System.out.println("Theseus moved down - (dice = 5)");
			if(path.get(i)[0] == 7)
				System.out.println("Theseus moved left - (dice = 7)");
				
			if(path.get(i)[1]==1) 
				sums++;
				
			if(sums==1)
				System.out.println("Theseus has picked up 1 supply.");
			else
				System.out.println("Theseus has picked up " + sums + " supplies.");
				
				
			System.out.println("Distance from supply: "+ path.get(i)[2]);
			System.out.println("Distance from opponent: "+ path.get(i)[3]);
			k++;
			
			System.out.print("\n");
				
		}
		
		System.out.println("-------------------------\nTheseus' total movements:");
		
		System.out.println("Up: "+ this.getcount1());
		System.out.println("Right: "+ this.getcount3());
		System.out.println("Down: "+ this.getcount5());
		System.out.println("Left: "+ this.getcount7());
	}
		
}
