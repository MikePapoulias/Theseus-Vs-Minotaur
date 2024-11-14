package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* Game Class - contains just a variable.
 * This is: round(the game-round).
*/
public class Game {
	private int round;
	
	// First GameConstructor - initializes the round to zero.
	public Game()
	{
		round = 0;
	}
	
	// Second GameConstructor - contains the round-variables.
	public Game(int round)
	{
		this.round = round;
	}
	
	// Third GameConstructor - contains a Game-type object(ob).
	public Game(Game ob)
	{
		round = ob.round;
	}
	
	// GetterGameFunction - Returns it's corresponding variable.
	public int getRound()
	{
		return round;
	}
	
	// SetterSupplyFunction - Initializes it's corresponding variable.
	public void setRound(int round)
	{
		this.round = round;
	}
	
	
	// Main()
	public static void main(String[] args) {
		int N = 15;
		int S = 4;
		int W = (N*N*3+1)/2;
		int i, j;
		int id = 0;
		
		Tile[] tiles=new Tile[N*N];
		
		for(i=0; i<N*N; i++)
			tiles[i] = new Tile();

		Supply[] supplies = new Supply[S];
		for(i=0; i<S; i++)
		supplies[i] = new Supply();


		Board board = new Board(N, S, W, tiles, supplies);
		board.createTile();
		board.createSupply();
		board.createBoard();
		
		
		Player a, b;
		a = new Player(0, "Theseus", board, 0, 0, 0);
		b = new Player(1, "Minotaurus", board, 0, N/2, N/2);
		Game game = new Game();
		
		
		System.out.println("The board game:\n");
		
		for(i=2*N; i>=0; i--) 
		{
			for(j=0; j<N; j++)
			{
				System.out.print(board.getStringRepresentation(0, N*N/2)[i][j]);
			}
			System.out.println();
		}
		
		System.out.print("\n\n");
		
		System.out.println("-> START GAME:\n");
		
		int th = 0;
		int min = N*N/2;
		
		
		id = 0;
		do
		{
			board.tiles[0].setdown(true);
			
			int newround = game.getRound() + 1;
			game.setRound(newround);
			
			for(i=0; i<N; i++)
				System.out.print("======");
			System.out.println();
			
			System.out.print("Round: "+game.getRound());
			if(id == 0)
				System.out.print(" - "+a.getName() +"' turn:\n");
			else if(id == 1)
				System.out.print(" - "+b.getName() +"' turn:\n");
			
			
			
			int[] array = new int[4];
			
			if(id == 0)
			{
				array = a.move(th);
				System.out.println("[ Id:"+array[0]+"   X:"+array[2]+"   Y:"+array[1]+"   Score:"+array[3]+" ]");
			
				th = array[0];
			}
			

			if(id == 1)
			{
				array = b.move(min);
				System.out.println("[ Id:"+array[0]+"   X:"+array[2]+"   Y:"+array[1]+"   Score:"+array[3]+" ]");
			
				min = array[0];
			}
			
			
			if(id == 0)
				id = 1;
			
			else if(id == 1)
				id = 0;
			
			
			for(i=2*N; i>=0; i--) 
			{
				for(j=0; j<N; j++)
				{
					System.out.print(board.getStringRepresentation(th, min)[i][j]);
				}
				System.out.println();
			}
			
			System.out.print("\n\n");
			
			if(th == min)
			{
				if(a.getScore() == S)
				{
					System.out.println("Theseus picked up all supplies!\nTheseus won!");
					break;
				}
				else
				{
					System.out.println("Minotaur killed Theseus.\nMinotaur won.");
					break;
				}
			}
			
			if(a.getScore() == S)
			{
				System.out.println("Theseus picked up all supplies!\nTheseus won!");
				break;
			}
			
			if(game.getRound() == 200)
			{
				System.out.println("Draw.");
				break;
			}
			
		}while(true);

		System.out.println("-> GAME OVER!");
		
	}
	
}
