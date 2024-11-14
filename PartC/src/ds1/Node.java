package ds1;
/* Οναματεπώνυμο: Τσιανάκας Εμμανουήλ   ΑΕΜ:9992    κινητό: 6976903077   Email: tsianakas@ece.auth.gr
 * Ονοματεπώνυμο: Παπούλιας Μιχαήλ      AEM:10204   κινητό: 6949823576   Email: mpapouli@ece.auth.gr
 */

/* Node Class - contains all necessary variables
 * These are: parent(a node that contains other nodes), children(parent's node-children),
 * 		      nodeDepht (the total tree's depth),
 * 			  nodeMove(an array that contains player's section, player's ordinal and player's dice),
 * 			  nodeBoard(game's board for a specific node) and 
 * 			  nodeEvaluation(evaluation for a specific node)         
 */

import java.util.ArrayList;

public class Node 
{
	Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvalution;
		
	// First NodeConstructor - initializes all its variables to zero.
	public Node() 
	{
		children = new ArrayList<Node>();
		nodeDepth = 0;
		nodeMove = new int[3];
		nodeBoard = new Board();
		nodeEvalution = 0;
	}
		
	// Second NodeConstructor - contains a Node-type object(a)
	public Node(Node a)
	{
		this.children = a.children;
		this.nodeDepth = a.nodeDepth;
		this.nodeMove = a.nodeMove;
		this.nodeBoard = a.nodeBoard;
		this.nodeEvalution = a.nodeEvalution;
	}
		
	// GettersNodeFunctions - Each function returns its corresponding variables.
	public Node getparent()
	{
		return parent;
	}
		
	public int getnodeDepth () 
	{
		return nodeDepth;
	}
		
	public int[] getnodeMove () 
	{
		return nodeMove;
	}
		
	public Board getnodeBoard() 
	{
		return nodeBoard;
	}
		
	public double getnodeEvalution() 
	{
		return nodeEvalution;
	}
		
	// SettersTileFunctions - Each function initializes its corresponding variables.
	public void setparent(Node parent) 
	{
		this.parent = parent;
	}
		
	public void setnodeDepth(int n) 
	{
		this.nodeDepth = n;
	}
		
	public void setnodeMove(int[] n) 
	{
		nodeMove = n;
	}
		
	public void setnodeBoard(Board nodeboard) 
	{
		this.nodeBoard = nodeboard;
	}
		
	public void setnodeEvalution(double nodeEvalution) 
	{
		this.nodeEvalution = nodeEvalution;
	}
}
