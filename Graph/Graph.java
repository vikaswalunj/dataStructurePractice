package ds.Graph;

public class Graph {
	
	//inner class to represent weighted edge in graph
	public class Edge {
		public int src;
		public int dest;
		public int weight;
        Edge() {
            src = dest = weight = 0;
        }
	};
	
	public int V;
	public int E;
    public Edge edge[];
 
    // Creates a graph with V vertices and E edges
    Graph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
}
