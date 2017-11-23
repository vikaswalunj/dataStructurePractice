package ds.Graph;

import java.util.ArrayList;

public class GraphNode {
	
	protected int val;
	ArrayList<GraphNode> neighbours;
	
	public GraphNode(int value){
		this.val = value;
		neighbours = new ArrayList<GraphNode>();
	}

}
