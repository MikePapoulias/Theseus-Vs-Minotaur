package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* Player Class - contains all necessary variables
 * These are playerId(the player's id), name(player's name), board(the table-board),
 *        score(player's score), x(player's section) and y(player's ordinal)
 */        

public class Player {
	private int playerId;
	private String name;
	private Board board;
	private int score;
	private int x;
	private int y;
	
	// First PlayerConstructor - initializes all its variables to zero.
	public Player()
	{
		playerId = 0;
		name = " ";
		board = new Board();
		score = 0;
		x = 0;
		y = 0;
	}
	
	// Second TileConstructor - contains all its variables.
	public Player(int playerId, String name, Board board, int score, int x, int y)
	{
		this.playerId = playerId;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
	}
	
	// Third TileConstructor - contains a Player-type object(ob).
	public Player(Player ob)
	{
		playerId = ob.playerId;
		name = ob.name;
		board = ob.board;
		score = ob.score;
		x = ob.x;
		y = ob.y;
	}
	
	// GettersPlayerFunctions - Each function returns its corresponding variables.
	public int getPlayerId()
	{
		return playerId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Board getBoard()
	{
		
		return board;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	// SettersPlayerFunctions - Each function initializes its corresponding variables.
	public void setPlayerId(int playerId)
	{
		this.playerId = playerId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setBoard(Board board)
	{
		this.board = board;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
/* This function decides player's movement randomly.
 * It's variables are: randomMove(a random number in: {1 = up, 3 = right, 5 = down, 7 = left}),
 * 					   N(board's size). 
 * Returns an 1 x 4 array: array[0] == player's id
 * 						   array[1] == tile's section
 * 						   array[2] == tile's ordinal
 * 						   array[3] == player's score 
 */
	public int[] move(int id, int dice)
	{	
		int[] array = new int[4];
		int N = board.getN();
		 
		if(dice == 1)
		{
			if(board.tiles[id].getup())
			{
				System.out.println(name + " cannot move up.");
				array[0] = id;
				array[1] = board.tiles[id].getx();
				array[2] = board.tiles[id].gety();
				array[3] = score;
			}
			
			else if(!board.tiles[id].getup() && id<N*N-N)
			{
				System.out.println(name + " moved up.");
				
				array[0] = id + N;
				array[1] = board.tiles[id+N].getx();
				array[2] = board.tiles[id+N].gety();
				
				int i;
				for(i=0; i<board.getS(); i++)
				{
					if(board.supplies[i].getsupplyTileId() == id+N && playerId == 0)
					{
						score++;
						
						board.supplies[i].setx(-1);
						board.supplies[i].sety(-1);
						board.supplies[i].setsupplyTileId(-1);
						
						array[3] = score;
						System.out.println(name + " picked up a supply.");
						break;
					}
					else
						array[3] = score;
				}
			}
		}
		

		if(dice == 3)
		{
			if(board.tiles[id].getright())
			{
				System.out.println(name + " cannot move right.");
				array[0] = id;
				array[1] = board.tiles[id].getx();
				array[2] = board.tiles[id].gety();
				array[3] = score;
			}
			
			else if(!board.tiles[id].getright() && id<N*N-1)
			{
				System.out.println(name + " moved right.");
				
				array[0] = id + 1;
				array[1] = board.tiles[id+1].getx();
				array[2] = board.tiles[id+1].gety();
				
				int i;
				for(i=0; i<board.getS(); i++)
				{
					if(board.supplies[i].getsupplyTileId() == id+1 && playerId == 0)
					{
						score++;
						
						board.supplies[i].setx(-1);
						board.supplies[i].sety(-1);
						board.supplies[i].setsupplyTileId(-1);
						
						array[3] = score;
						System.out.println(name + " picked up a supply.");
						break;
					}
					else
						array[3] = score;
				}
			}
		}
		
		if(dice == 5)
		{
			if(board.tiles[id].getdown())
			{
				System.out.println(name + " cannot move down.");
				array[0] = id;
				array[1] = board.tiles[id].getx();
				array[2] = board.tiles[id].gety();
				array[3] = score;
			}
			
			else if(!board.tiles[id].getdown() && id>=N)
			{
				System.out.println(name + " moved down.");
				
				array[0] = id - N;
				array[1] = board.tiles[id-N].getx();
				array[2] = board.tiles[id-N].gety();
				
				int i;
				for(i=0; i<board.getS(); i++)
				{
					if(board.supplies[i].getsupplyTileId() == id-N && playerId == 0)
					{
						score++;
						
						board.supplies[i].setx(-1);
						board.supplies[i].sety(-1);
						board.supplies[i].setsupplyTileId(-1);
						
						array[3] = score;
						System.out.println(name + " picked up a supply.");
						break;
					}
					else
						array[3] = score;
				}
			}
		}
		
		if(dice == 7)
		{
			if(board.tiles[id].getleft())
			{
				System.out.println(name + " cannot move left.");
				array[0] = id;
				array[1] = board.tiles[id].getx();
				array[2] = board.tiles[id].gety();
				array[3] = score;
			}
			
			else if(!board.tiles[id].getleft() && id>0)
			{
				System.out.println(name + " moved left.");
				
				array[0] = id - 1;
				array[1] = board.tiles[id-1].getx();
				array[2] = board.tiles[id-1].gety();
				
				int i;
				for(i=0; i<board.getS(); i++)
				{
					if(board.supplies[i].getsupplyTileId() == id-1 && playerId == 0)
					{
						score++;
						
						board.supplies[i].setx(-1);
						board.supplies[i].sety(-1);
						board.supplies[i].setsupplyTileId(-1);
						
						array[3] = score;
						System.out.println(name + " picked up a supply.");
						break;
					}
					else
						array[3] = score;
				}
			}
		}
		
		return array;
	}
}
