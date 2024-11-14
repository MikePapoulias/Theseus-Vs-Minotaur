package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* Supply Class - contains all necessary variables
 * These are: supplyId(supply's Id), x(dashboard's section), y(dashboard's ordinal) and 
 *            supplyTileId(supply's tile's id).
*/
public class Supply {

	private int supplyId;
	private int x;
	private int y;
	private int supplyTileId;
	
	// First SupplyConstructor - initializes all its variables to zero.
	public Supply()
	{
		supplyId = 0;
		x = 0;
		y = 0;
		supplyTileId = 0;
	}			
	
	// Second SupplyConstructor - contains all its variables.
	public Supply(int supplyId, int x, int y, int supplyTileId)
	{
		this.supplyId = supplyId;
		this.x = x;
		this.y = y;
		this.supplyTileId = supplyTileId;
	}
	
	// Third SupplyConstructor - contains a Supply-type object(ob).
	public Supply(Supply ob)
	{
		supplyId = ob.supplyId;
		x = ob.x;
		y = ob.y;
		supplyTileId = ob.supplyTileId;
	}

	// GettersSupplyFunctions - Each function returns its corresponding variables.
	public int getsupplyId()
	{
		return supplyId;
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public int getsupplyTileId()
	{
		return supplyTileId;
	}
	
	// SettersSupplyFunctions - Each function initializes its corresponding variables.
	public void setsupplyId(int supplyId)
	{
		this.supplyId = supplyId;
	}
	
	public void setx(int x)
	{
		this.x = x;
	}
	
	public void sety(int y)
	{
		this.y = y;
	}
	
	public void setsupplyTileId(int supplyTileId)
	{
		this.supplyTileId = supplyTileId;
	}

}
  