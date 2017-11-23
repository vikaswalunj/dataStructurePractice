package ds.Graph;

import java.util.HashMap;
import java.util.LinkedList;

public class CloneGraphProgram {

	public GraphNode cloneGraph(GraphNode source) {
		
		LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
		queue.add(source);
		
		//put the node into map
		HashMap<GraphNode, GraphNode> copyMap = new HashMap<GraphNode, GraphNode>();
		copyMap.put(source, new GraphNode(source.val));
		
		while (!queue.isEmpty()) {
			//get front node in queue and visit its neighbours
			GraphNode u = queue.poll();
			
			GraphNode cloneNodeU = copyMap.get(u);
			
			if (u.neighbours != null) {
				
				for (GraphNode neigh : u.neighbours) {
					
					GraphNode cloneNodeG = copyMap.get(neigh);
					//get corresponding cloned node of neighbour node from map
					if (copyMap.get(neigh) == null){
						//if not cloneed before follow our clonning process 
						queue.add(neigh);
						
						cloneNodeG = new GraphNode(neigh.val);
						copyMap.put(neigh, cloneNodeG);
					}
					
					//add these neighbours to 
					cloneNodeU.neighbours.add(cloneNodeG);
				}
			}
			
		}
		
		return copyMap.get(source);
	}
}
