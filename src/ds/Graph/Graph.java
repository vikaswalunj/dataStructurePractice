package ds.Graph;

public class Graph {
	
	//inner class to represent weighted directed edge in graph
	public class Edge {
		public int src;
		public int dest;
		public int weight;
        Edge() {
            src = dest = weight = 0;
        }
	};
	
	//inner class to represent weighted undirected edge in graph
	public class UDEdge {
		public int ver;
		public int weight;
	    UDEdge() {
	       ver = weight = 0;
	    }
	};
	
	public int V;
	public int E;
    public Edge edge[];
    public UDEdge udEdge[];
 
    // Creates a directed and undirected graph with V vertices and E edges
    Graph(int v, int e)
    {
        V = v;
        E = e;
        //directed
        edge = new Edge[e];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
        //undirected
        udEdge = new UDEdge[e];
        for (int i=0; i<e; ++i)
            udEdge[i] = new UDEdge();
    }
    
     
}
