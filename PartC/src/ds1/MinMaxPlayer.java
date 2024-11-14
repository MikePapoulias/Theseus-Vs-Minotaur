package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* MinMaxPlayer Class - contains all necessary variables, extends Player-class
 * These are:
 * 			ArrayList<Integer[]> - type variable named path (stores statistics, necessary 
 * 					for the best movement of the MinMaxPlayer),
 * 			count1 (total times that MinMaxPlayer moved up),
 *			count3 (total times that MinMaxPlayer moved right),
 *			count5 (total times that MinMaxPlayer moved down),
 *			count7 (total times that MinMaxPlayer moved left),
 *			thturns (total rounds that MinMaxPlayer has played).
 */       

import java.util.ArrayList;
import java.util.Random;

public class MinMaxPlayer extends Player 
{
	ArrayList<Integer[]> path;
	int count1;
	int count3;
	int count5;
	int count7;
	int thturns;
	
	// First MinMaxPlayerConstructor - initializes all its variables to zero.
	public MinMaxPlayer() 
	{
		super();
		this.path = new ArrayList<Integer[]>();
		count1 = 0;
		count3 = 0;
		count5 = 0;
		count7 = 0;
		thturns = 0;
	}
	
	// Second MinMaxPlayerConstructor - contains all its variables.
	public MinMaxPlayer(int PlayerId, String name, Board board, int score, int x, int y, int n, int count1, int count3, int count5, int count7, int thturns) 
	{
		super(PlayerId, name, board, score, x, y);
		this.path = new ArrayList<Integer[]>(n);
		this.count1 = count1;
		this.count3 = count3;
		this.count5 = count5;
		this.count7 = count7;
		this.thturns = thturns;
	}
	
	// Third MinMaxPlayerConstructor - contains a HeuristicPlayer-type object(a).
	public MinMaxPlayer(MinMaxPlayer a) 
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
	
	// GettersMinMaxPlayerFunctions - Each function returns its corresponding variables.
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

	// SettersMinMaxPlayerFunctions - Each function initializes its corresponding variables.
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
		int N = getBoard().getN();
		double x = 0;
			
		if(this.getBoard().tiles[th].getup())
			x = 0;
			
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
			x = 0;
			
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
			x = 0;
			
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
			x = 0;
			
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
	
	
/* Evaluate function - this function evaluates the MinMaxPlayer's possible movements
 * 					   through an evaluate-algorithm.
 * It's variables are: currentPos (MinMaxPlayer's position),
 * 					   opponentPos (Opponent's position),
 * 					   dice (HeuristicPlayer's dice) and
 * 					   board (the game-board)   
 */	
	double evaluate(int currentPos, int opponentPos, int dice, Board board)
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
	
	
/* Evaluate_Minotaur function - this function evaluates the Minotaur's possible movements
 * 					   through an evaluate-algorithm.
 * It's variables are: currentPos (MinMaxPlayer's position) and
 * 					   opponentPos (Opponent's position),  
 */	
	double evaluate_minotaur(int currentPos, int opponentPos)
	{
		int i, j;
		int choice;
		double x = 0;
		double temp;
		double[] array = new double[4];
		
		array[0] = - OpponentDist1(opponentPos, currentPos);
			
		array[1] = - OpponentDist3(opponentPos, currentPos);
			
		array[2] = - OpponentDist5(opponentPos, currentPos);
		
		array[3] = - OpponentDist7(opponentPos, currentPos);
		
		// Bubble-sort function for minotaur's best theoretical movement.
		for(i=1; i<4; i++)
		{
			for(j=3; j>=i; j--)
			{
				if(array[j-1] >= array[j])
				{
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
		
		x = array[3];
		if(array[3] == array[2])
		{
			Random rand = new Random();
			choice = rand.nextInt(2);
			if(choice == 0)
				x = array[3];
			else if(choice == 1)
				x = array[2];
		}
		if(array[3] == array[2] && array[2] == array[1])
		{
			Random rand = new Random();
			choice = rand.nextInt(3);
			if(choice == 0)
				x = array[3];
			else if(choice == 1)
				x = array[2];
			else if(choice == 2)
				x = array[1];
		}
		if(array[3] == array[2] && array[2] == array[1] && array[1] == array[0])
		{
			Random rand = new Random();
			choice = rand.nextInt(4);
			if(choice == 0)
				x = array[3];
			else if(choice == 1)
				x = array[2];
			else if(choice == 2)
				x = array[1];
			else if(choice == 3)
				x = array[0];
		}
		return x;
	}	
	
	
/* ChooseMinMaxMove function - this function implements the MinMax algorithm in a tree with a depth of two movements 
 * 		(the first level stores the evaluations of Theseu's movements, the second level
 * 		stores the evaluations of Minotaur's movement) 
 * 		and it selects the best movement for Theseus.
 * It's variables are: root(tree's node-root),
 * 					   th (MinMaxPlayer's position) and
 * 					   min (Opponent's position) 
 */
	int chooseMinMaxMove(Node root, int th, int min) 
	{	
		int size = root.children.size();
		double[][] value_1 = new double[2][size];
		int choice_1;
		double temp_1;
		double temp_1pointer;
		int choice_2;
		int i, j, k, l;
		
		
		for(i=0; i<size; i++)
		{
			int size_children = root.children.get(i).children.size();
			double[] value_2 = new double[size_children];
			double minimum = root.children.get(i).children.get(0).getnodeEvalution();
			
			for(j=1; j<size_children; j++)
			{
				value_2[j] = root.children.get(i).children.get(j).getnodeEvalution();
				root.children.get(i).children.get(j).setnodeEvalution(value_2[j]);
				if(value_2[j] < minimum)
					minimum = value_2[j];
				
				if(value_2[size_children-1] == value_2[size_children-2])
				{
					Random rand = new Random();
					choice_2 = rand.nextInt(2);
					
					if(choice_2 == 0)
						minimum = value_2[size_children-1];
					else if(choice_2 == 1)
						minimum = value_2[size_children-2];
				}
			}
			// Set the minimum evaluation on level 1 - set the index.
			root.children.get(i).setnodeEvalution(minimum);
			value_1[0][i] = i;
			value_1[1][i] = (minimum);
		}
			
		for(k=1; k<size; k++)
		{
			for(l=size-1; l>=k; l--)
			{
				if(value_1[1][l-1] >= value_1[1][l])
				{
					temp_1 = value_1[1][l-1];
					value_1[1][l-1] = value_1[1][l];
					value_1[1][l] = temp_1;
							
					temp_1pointer = value_1[0][l-1];
					value_1[0][l-1] = value_1[0][l];
					value_1[0][l] = temp_1pointer;
				}
			}
		}
			
		double maximum = value_1[1][size-1];
		int index_1 = (int)value_1[0][size-1];
		
		if(value_1[1][size-1] == value_1[1][size-2])
		{
			Random rand = new Random();
			choice_1 = rand.nextInt(2);
			
			if(choice_1 == 0)
			{
				maximum = value_1[1][size-1];
				index_1 = (int)value_1[0][size-1];
			}
			else if(choice_1 == 1)
			{
				maximum = value_1[1][size-2];
				index_1 = (int)value_1[0][size-2];
			}
		} 														  
		
		root.setnodeEvalution(maximum);
		root.setnodeMove(root.children.get(index_1).getnodeMove());
		
		return root.getnodeMove()[2];
	}
	
	
/* GetNexMove function - this function returns the MinMaxPlayer's new position, by 
 * calling the createMySubtree and the chooseMinMaxMove functions.
 * It's variables are: currentPos (MinMaxPlayer's position),
 * 					   opponentCurrentPos (Opponent's position) and
 * 					   b (a Minotaur-player)
 */	
	int getNextMove (int currentPos, int opponentCurrentPos, Player b)
	{
		Node root = new Node();
		root.nodeBoard = this.getBoard();
		int i;
		
		createMySubtree(currentPos, opponentCurrentPos, root, 1, b);
		
		int dice = chooseMinMaxMove(root, currentPos, opponentCurrentPos);
	
		// a new Integer[] array - type.
		Integer[] path_array;
		path_array = new Integer[4];
		
		// MinMaxPlayer's dice.
		path_array[0] = dice;
		
		
		path_array[1] = 0;
		// If MinMaxPlayer decides move up.
		if(dice == 1)
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
			
		// If MinMaxPlayer decides move right.	
		if(dice == 3)
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
			
		// If MinMaxPlayer decides move down.
		if(dice == 5)
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
			
		// If MinMaxPlayer decides move left.
		if(dice == 7)
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
		
		/* Checking if there is a near supply, after MinMaxPlayer's movement,
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
			
			
		/* Checking if the opponent is near, after MinMaxPlayer's movement,
		 * by calling the OpponentDist functions.
		 */	
		path_array[3] = -1;
		if((OpponentDist1(currentPos, opponentCurrentPos) == -1) || (OpponentDist3(currentPos, opponentCurrentPos) == -1) ||
		   (OpponentDist5(currentPos, opponentCurrentPos) == -1) || (OpponentDist7(currentPos, opponentCurrentPos) == -1)  )
			path_array[3] = 1;
			
		if((OpponentDist1(currentPos, opponentCurrentPos) == -0.5) || (OpponentDist3(currentPos, opponentCurrentPos) == -0.5) ||
		   (OpponentDist5(currentPos, opponentCurrentPos) == -0.5)	|| (OpponentDist7(currentPos, opponentCurrentPos) == -0.5))
			path_array[3] = 2;
			
		if((OpponentDist1(currentPos, opponentCurrentPos) == -0.3) || (OpponentDist3(currentPos, opponentCurrentPos) == -0.3) ||
		   (OpponentDist5(currentPos, opponentCurrentPos) == -0.3)	|| (OpponentDist7(currentPos, opponentCurrentPos) == -0.3))
			path_array[3] = 3;
		
		// All array's data stores to the list.	
		path.add(path_array);
				
		thturns++;
				
		return currentPos;
	}
	
	
/* Statistics function - this function prints all MinMaxPlayer's movements statistics
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
	
	/*  createMySubtree function-this function finds the available MinMax player's movements and creates the tree branches for each 
	 *  available movement at first level and by calling thecreateOpponentSubtree function complete the tree branches
	 *  at second level.
	 *  Its variable are:
	 *  	currentPos (MinMax Player's position),opponentCurrentPos(Minotaur Player's position),root(tree's Node root),
	 *		depth(the depth of tree's branches),b(a Minotaur Player).
	 */	
	void createMySubtree(int currentPos, int opponentCurrentPos, Node root, int depth, Player b)
	{
		if(!this.getBoard().tiles[currentPos].getup())
		{
			Board board1 = this.getBoard();
			
			Node a1 = new Node();
			a1.nodeBoard = board1;
			a1.parent = root;
			a1.nodeMove[0] = this.getX();
			a1.nodeMove[1] = this.getY() + 1;
			a1.nodeMove[2] = 1;
			a1.nodeDepth = depth;
			a1.nodeEvalution = evaluate(currentPos, opponentCurrentPos, 1, board1);
			
			createOpponentSubtree(currentPos + board1.getN(), opponentCurrentPos, a1, depth+1, a1.nodeEvalution, b);
			root.children.add(a1);
		}
		
		if(!this.getBoard().tiles[currentPos].getright())
		{
			Board board1 = this.getBoard();
			
			Node a3 = new Node();
			a3.nodeBoard = board1;
			a3.parent = root;
			a3.nodeMove[0] = this.getX() + 1;
			a3.nodeMove[1] = this.getY();
			a3.nodeMove[2] = 3;
			a3.nodeDepth = depth;
			a3.nodeEvalution = evaluate(currentPos, opponentCurrentPos, 3, board1);
			
			createOpponentSubtree(currentPos + 1, opponentCurrentPos, a3, depth+1, a3.nodeEvalution, b);
			root.children.add(a3);
		}

		if(!this.getBoard().tiles[currentPos].getdown())
		{
			Board board1 = this.getBoard();
			
			Node a5 = new Node();
			a5.nodeBoard = board1;
			a5.parent = root;
			a5.nodeMove[0] = this.getX();
			a5.nodeMove[1] = this.getY() - 1;
			a5.nodeMove[2] = 5;
			a5.nodeDepth = depth;
			a5.nodeEvalution = evaluate(currentPos, opponentCurrentPos, 5, board1);
			
			createOpponentSubtree(currentPos - board1.getN(), opponentCurrentPos, a5, depth+1, a5.nodeEvalution, b);
			root.children.add(a5);
		}
		
		if(!this.getBoard().tiles[currentPos].getleft())
		{
			Board board1 = this.getBoard();
			
			Node a7 = new Node();
			a7.nodeBoard = board1;
			a7.parent = root;
			a7.nodeMove[0] = this.getX() - 1;
			a7.nodeMove[1] = this.getY();
			a7.nodeMove[2] = 7;
			a7.nodeDepth = depth;
			a7.nodeEvalution = evaluate(currentPos, opponentCurrentPos, 7, board1);
			
			createOpponentSubtree(currentPos - 1, opponentCurrentPos, a7, depth+1, a7.nodeEvalution, b);
			root.children.add(a7);
		}
	}
	
	/*  createOpponentSubtree function-this function finds the available Minotaur movements and creates the tree's branches for each 
	 *  available movement at second level.
	 *  Its variable are:
	 *  	currentPos (MinMax Player's position),opponentCurrentPos(Minotaur Player's position),parent(Node that this function creates 
	 *  	its branches),depth(the depth of tree's branches),parentEval(the evaluation of Node parent),a(a Minotaur Player).
	 */	
	void createOpponentSubtree(int currentPos, int opponentCurrentPos, Node parent, int depth, double parentEval, Player a)
	{
		if(!this.getBoard().tiles[opponentCurrentPos].getup())
		{
			Node a1 = new Node();
			a1.nodeBoard = parent.nodeBoard;
			a1.parent = parent;
			a1.nodeMove[0] = a.getX();
			a1.nodeMove[1] = a.getY() + 1;
			a1.nodeMove[2] = 1;
			a1.nodeDepth = depth;
			a1.nodeEvalution = parentEval - evaluate_minotaur(currentPos, opponentCurrentPos);
			parent.children.add(a1);
		}
		
		if(!this.getBoard().tiles[opponentCurrentPos].getright())
		{
			Node a3 = new Node();
			a3.nodeBoard = parent.nodeBoard;
			a3.parent = parent;
			a3.nodeMove[0] = a.getX() + 1;
			a3.nodeMove[1] = a.getY();
			a3.nodeMove[2] = 3;
			a3.nodeDepth = depth;
			a3.nodeEvalution =parentEval - evaluate_minotaur(currentPos, opponentCurrentPos);
			parent.children.add(a3);
		}
		
		if(!this.getBoard().tiles[opponentCurrentPos].getdown())
		{
			Node a5 = new Node();
			a5.nodeBoard = parent.nodeBoard;
			a5.parent = parent;
			a5.nodeMove[0] = a.getX();
			a5.nodeMove[1] = a.getY() - 1;
			a5.nodeMove[2] = 5;
			a5.nodeDepth = depth;
			a5.nodeEvalution = parentEval - evaluate_minotaur(currentPos, opponentCurrentPos);
			parent.children.add(a5);
		}
		
		if(!this.getBoard().tiles[opponentCurrentPos].getleft())
		{	
			Node a7 = new Node();
			a7.nodeBoard = parent.nodeBoard;
			a7.parent = parent;
			a7.nodeMove[0] = a.getX() - 1;
			a7.nodeMove[1] = a.getY();
			a7.nodeMove[2] = 7;
			a7.nodeDepth = depth;
			a7.nodeEvalution = parentEval - evaluate_minotaur(currentPos, opponentCurrentPos);
			parent.children.add(a7);
		}
	}
	
}
