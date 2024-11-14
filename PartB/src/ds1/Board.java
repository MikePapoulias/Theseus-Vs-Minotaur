package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* Board Class - contains all necessary variables
 * These are: N(boardgame's dimension), S(Supplies' number), W(walls' number),
 *			 tiles[](Tile-type array) and supplies[](Supply-type array)
 */
import java.util.Random;
import java.util.ArrayList;

public class Board {
	private int N;
	private int S;
	private int W;
	Tile [] tiles;
	Supply[] supplies;
	
	// First BoardConstructor - null constructor
	public Board()
	{
		N = 0;
		S = 0;
		W = 0;
	}
	
	// Second BoardConstructor - contains N, S and W variables.
	public Board(int N, int S, int W, Tile[] tiles, Supply[] supplies)
	{
		
		this.N = N;
		this.S = S;
		this.W = W;
		this.tiles = tiles;
		this.supplies = supplies;
	}
	
	// Third BoardConstructor - contains a Board-type object(ob).
	public Board(Board ob)
	{
		N = ob.N;
		S = ob.S;
		W = ob.W;
		tiles = ob.tiles;
		supplies = ob.supplies;
	}
	
	// GettersBoardFunctions - Each function returns its corresponding variables.
	public int getN()
	{
		return N;
	}
	
	public int getS()
	{
		return S;
	}
	
	int getW()
	{
		return W;
	}
	
	Tile[] gettiles()
	{
		return tiles;
	}
	
	Supply[] getsupplies()
	{
		return supplies;
	}
	
	// SettersBoardFunctions - Each function initializes its corresponding variables.
	void setN(int N)
	{
		this.N = N;
	}
	
	void setS(int S)
	{
		this.S = S;
	}
	
	void setW(int W)
	{
		this.W = W;
	}
	
	void settiles(Tile[] tiles)
	{
		this.tiles = tiles;
	}
	
	void setsupplies(Supply[] supplies)
	{
		this.supplies = supplies;
	}
	
	
/* CreateTile Function - initializes the tiles-object randomly.
 * It's variables are: id(tile's id), i(board's section) and j(board's ordinal)
 */
	void createTile()
	{
		int id=0;
		int i;
		int j;
		          
		for(i=0; i<N; i++)
		{
			for (j=0; j<N; j++) 
			{
				tiles[id] = new Tile(id, i, j, false, false, false, false);
				id++;   
		    }
		}
	}
		      
/* CreateSupply Function - initializes the supplies-object randomly.
 * It's variables are: S(Supplie's number), i(length of supplies' list) and
 *                     and rand_int(a random number between 0 - N*N-1)
 */
	void createSupply()
	{
		ArrayList<Integer> arr = new ArrayList<Integer>(S);
        for(int i=0 ; i<S ; i++)
        {
            Random rand = new Random();
            int rand_int = rand.nextInt(N*N);
            if(arr.indexOf(rand_int) == -1 && rand_int != 0 && rand_int != (int)(N*N)/2)
            {
                supplies[i] = new Supply(i , tiles[rand_int].getx() , tiles[rand_int].gety() , rand_int);
                arr.add(rand_int);
            }
            else
                i--;
        }

	}
		     
/* This function creates the game-board, which contains tiles, walls and supplies.
 * 	  It's variables are: i(tile's id) and leftW(the maximum possible random walls inside board).   
 */
	void createBoard()
	{
		int i;
		
		
		for(i=1; i<N; i++) 
			tiles[i].setdown(true);

		for(i=N*N-N; i<N*N; i++) 
			tiles[i].setup(true);

		for(i=0; i<N*N; i++)
		{
			if(tiles[i].gety() == 0)
				tiles[i].setleft(true);
		
			if(tiles[i].gety() == N-1) 
				tiles[i].setright(true);
		}

		
		int leftW = W-(4*N-1);

		do 
		{
			int sumidW = 0;

			Random rand = new Random();
			int randomid = rand.nextInt(N*N);
			if(randomid == 0)
				randomid++;

			
			if(tiles[randomid].getdown())
				sumidW++;
			
			if(tiles[randomid].getleft())
				sumidW++;
		
			if(tiles[randomid].getright())
		 		sumidW++;
		 
			if(tiles[randomid].getup())
				sumidW++;
		

			
			if(sumidW < 2 && leftW > 0  && !tiles[randomid].getdown()) 
			{
				boolean rand1;
				rand1 = rand.nextBoolean();
				
				if(tiles[randomid].getx() > 0  && ((tiles[randomid-N].getup() ? 1 : 0) + (tiles[randomid-N].getdown() ? 1 : 0)
				+ (tiles[randomid-N].getleft() ? 1 : 0) + (tiles[randomid-N].getright() ? 1 : 0)) < 2 && rand1 == true && tiles[randomid].gettileId() != N) 
				{
					tiles[randomid].setdown(true);
					tiles[randomid-N].setup(true);
					sumidW++;
					leftW-=2;
				}
				else
					leftW--;
			} 
			
			if(sumidW < 2 &&  leftW > 0 && !tiles[randomid].getleft()) 
			{
				boolean rand4;
				rand4 = rand.nextBoolean();
				
				if(tiles[randomid].gety() > 0 && ((tiles[randomid-1].getup() ? 1 : 0) + (tiles[randomid-1].getdown() ? 1 : 0)
				+ (tiles[randomid-1].getleft() ? 1 : 0) + (tiles[randomid-1].getright() ? 1 : 0)) < 2 && rand4 == true && tiles[randomid].gettileId() != 1)
				{
					tiles[randomid].setleft(true);
					tiles[randomid-1].setright(true);
					sumidW++;
					leftW-=2;
				}
				else
					leftW--;
			}

			if(sumidW < 2 && leftW > 0  && !tiles[randomid].getup())
			{
				boolean rand2;
				rand2 = rand.nextBoolean();
				
				if (tiles[randomid].getx() < N-1  && ((tiles[randomid+N].getup() ? 1 : 0) + (tiles[randomid+N].getdown() ? 1 : 0)
				+ (tiles[randomid+N].getleft() ? 1 : 0) + (tiles[randomid+N].getright() ? 1 : 0)) < 2 && rand2 == true)
				{
					tiles[randomid].setup(true);
					tiles[randomid+N].setdown(true);
					sumidW++;
					leftW-=2;
				}
				else
					leftW--;
			}

			if(sumidW < 2 && leftW > 0 && !tiles[randomid].getright()) 
			{
				boolean rand3;
				rand3 = rand.nextBoolean();
				
				if(tiles[randomid].gety() < N-1 && ((tiles[randomid+1].getup() ? 1 : 0) + (tiles[randomid+1].getdown() ? 1 : 0)
				+ (tiles[randomid+1].getleft() ? 1 : 0) + (tiles[randomid+1].getright() ? 1 : 0)) < 2 && rand3 == true)
				{
					tiles[randomid].setright(true);
					tiles[randomid+1].setleft(true);
					sumidW++;
					leftW-=2;
				}
				else
					leftW--;
			}
				
		}while(leftW > 0);
	}
	
	
/* This function returns the initialized board to a String[][]-type array.
 * It's variables are: id(tile's id), i(the first dimension), j(the second dimension)  
 */
	public String[][] getStringRepresentation(int theseusTile, int minotaurTile)
	{	
		String [][] array;
		array = new String[2*N +1][N];
		int id = 0;
		int i, j;
		
		for(i=0; i<2*N; i+=2) 
		{
			for(j=0; j<N; j++) 
			{
				if(j==0)
				{
					if(tiles[id].getdown())
						array[i][j] = "+ --- +";
					else 
						array[i][j] = "+     +";
					
					if(tiles[id].getup())
						array[i+2][j] = "+ --- +";
					else 
						array[i+2][j] = "+     +";
					
					
					
					if(tiles[id].getleft() && tiles[id].getright())
						array[i+1][j] = "|     |";

					else if(tiles[id].getleft() && !tiles[id].getright())
						array[i+1][j] = "|      ";

					else if(!tiles[id].getleft() && tiles[id].getright())
						array[i+1][j] = "      |";
					else
						array[i+1][j] = "       ";
				}
				
				else //if(j > 0)
				{
					if(tiles[id].getdown())
						array[i][j] = " --- +";
					else 
						array[i][j] = "     +";
					
					if(tiles[id].getup())
						array[i+2][j] = " --- +";
					else 
						array[i+2][j] = "     +";

					
					if(tiles[id].getleft() && tiles[id].getright())
						array[i+1][j] = "     |";

					else if(tiles[id].getleft() && !tiles[id].getright())
						array[i+1][j] = "      ";

					else if(!tiles[id].getleft() && tiles[id].getright())
						array[i+1][j] = "     |";
					
					else
						array[i+1][j] = "      ";

				}
				id++;
			}
			
		}
		
		id=0;
		for(i=0; i<2*N; i+=2)
		{
			for(j=0; j<N; j++)
			{
				if(tiles[id].gettileId() == theseusTile)
				{
					if(j==0)
					{
						if(tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "|  T  |";
		
						if(tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "|  T   ";
		
						if(!tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "   T  |" ;
		
						if(!tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "   T   " ;
					}
					else //if(j > 0)
					{
						if(tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "  T  |";
		
						if(tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "  T   ";
		
						if(!tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "  T  |" ;
		
						if(!tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "  T   " ;
					}
						
				}
				
				id++;
			}
		}

		
		id=0;
		for(i=0; i<2*N; i=i+2) 
		{
			for(j=0; j<N; j++) 
			{
				if(tiles[id].gettileId() == minotaurTile)
				{
					if(j==0)
					{
						if(tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "|  M  |";
					
						if(tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "|  M   ";
		
						if(!tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "   M  |" ;
		
						if(!tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "   M   " ;
					}
					else //if(j > 0)
					{
						if(tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "  M  |";
					
						if(tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "  M   ";
		
						if(!tiles[id].getleft() && tiles[id].getright())
							array[i+1][j] = "  M  |" ;
		
						if(!tiles[id].getleft() && !tiles[id].getright())
							array[i+1][j] = "  M   " ;
					}
				} 
				id++;
			}
		}


		id=0;
		for(i=0; i<2*N; i+=2)
		{
			for(j=0; j<N; j++)
			{ 
				for(int k=0; k<S; k++)
				{
					if(j==0)
					{
						if(tiles[id].gettileId() == supplies[k].getsupplyTileId() && tiles[id].gettileId() != minotaurTile) 
						{
							if(tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = "| S"+(k+1) +"  |";
		
							if(tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = "| S"+(k+1) +"   ";
		
							if(!tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = "  S"+(k+1) +"  |";
		
							if(!tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = "  S"+(k+1) +"   ";
						}
					}
					else //if(j > 0)
					{
						if(tiles[id].gettileId() == supplies[k].getsupplyTileId() && tiles[id].gettileId() != minotaurTile) 
						{
							if(tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = " S"+(k+1) +"  |";
		
							if(tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = " S"+(k+1) +"   ";
		
							if(!tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = " S"+(k+1) +"  |";
		
							if(!tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = " S"+(k+1) +"   ";
						}
					}
					
					
					if(j==0)
					{
						if(tiles[id].gettileId() == supplies[k].getsupplyTileId() && tiles[id].gettileId() == minotaurTile) 
						{
							if(tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = "| M"+"+S"+(k+1)+"|";
						
							if(tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = "| M"+"+S"+(k+1)+" ";
			
							if(!tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = " M"+"+S"+(k+1)+" |";
			
							if(!tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = " M"+"+S"+(k+1)+"  ";
						}
					}
					
					else //if(j>0)
					{
						if(tiles[id].gettileId() == supplies[k].getsupplyTileId() && tiles[id].gettileId() == minotaurTile) 
						{
							if(tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = " M"+"+S"+(k+1)+"|";
						
							if(tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = " M"+"+S"+(k+1)+" ";
			
							if(!tiles[id].getleft() && tiles[id].getright())
								array[i+1][j] = " M"+"+S"+(k+1)+"|";
			
							if(!tiles[id].getleft() && !tiles[id].getright())
								array[i+1][j] = " M"+"+S"+(k+1)+" ";
						}
					}
				} 
				id++;
			}
		}

		return array;
	}

}


