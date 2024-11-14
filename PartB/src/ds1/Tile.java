package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* Tile Class - contains all necessary variables
 * These are: tileId(tile's Id), x(dashboard's section), y(dashboard's ordinal), 
 *            up(indicates if there is a wall on the top side of the tile),
 *            down(indicates if there is a wall on the bottom side of the tile),
 *            left(indicates if there is a wall on the left side of the tile) and
 *            right(indicates if there is a wall on the right side of the tile.
*/
public class Tile {
	private int tileId;
	private int x;
	private int y;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	
	// First TileConstructor - initializes all its variables to zero.
	public Tile()
	{
		tileId = 0;
		x = 0;
		y = 0;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	// Second TileConstructor - contains all its variables.
	public Tile(int tileId, int x, int y, boolean up, boolean down, boolean left, boolean right)
	{
		this.tileId = tileId;
		this.x = x;
		this.y = y;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	// Third TileConstructor - contains a Tile-type object(ob).
	public Tile(Tile ob)
	{
		tileId = ob.tileId;
		x = ob.x;
		y = ob.y;
		up = ob.up;
		down = ob.down;
		left = ob.left;
		right = ob.right;
	}

	// GettersTileFunctions - Each function returns its corresponding variables.
	public int gettileId()
	{
		return tileId;
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public boolean getup()
	{
		return up;
	}
	
	public boolean getdown()
	{
		return down;
	}
	
	public boolean getleft()
	{
		return left;
	}
	
	public boolean getright()
	{
		return right;
	}
	
	// SettersTileFunctions - Each function initializes its corresponding variables.
	public void settileId(int tileId)
	{
		this.tileId = tileId;
	}
	
	public void setx(int x)
	{
		this.x = x;
	}
	
	public void sety(int y)
	{
		this.y = y;
	}
	
	public void setup(boolean up)
	{
		this.up = up;
	}
	
	public void setdown(boolean down)
	{
		this.down = down;
	}
	
	public void setleft(boolean left)
	{
		this.left = left;
	}
	
	public void setright(boolean right)
	{
		this.right = right;
	}
}
