package ds.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import ds.Graph.Graph.Edge;
import ds.Graph.Graph.UDEdge;

public class GraphAdjucencyList {

	//define map which will have list for all vertices where vertex is key. Vertex can be of any type 
	//here we have defined it as integer but it can be of type T
	private Map<Integer, List<Integer>> adjList;
	
	private Map<UDEdge, List<UDEdge>> udEdgeList;
	
	//create graph for V no of vertices
	public GraphAdjucencyList (int v){
		//initializes the map to with size equal to no of vertices
		adjList = new HashMap<Integer, List<Integer>>();
		//create empty LinkedList for each vertex
		for (int i=0; i < v ; i++ ) {
			adjList.put(i, new LinkedList<Integer>());
		}
	}
	
	//set edge between two vertices
	public void setEdge(int source, int destination){
		//make sure given vertices are not out of adjucencyList
		if (source > adjList.size() || destination > adjList.size()) {
			System.out.println("the vertex is not in Adjucency List");
			return;
		}
		adjList.get(source).add(destination);
		adjList.get(destination).add(source);
	}
	
	//getEdge will return list of vertices that are connected to source
	public List<Integer> getEdge(int source){
		if (source > adjList.size()) {
			System.out.println("vertex is not in adjucent list");
			return null;	
		}
		return adjList.get(source);
	}
	
	//print adjucency list
	public void printAdjList(){
		
		for (int i =0; i < adjList.size(); i ++) {
			List<Integer> list = adjList.get(i);
			System.out.println("adjList at " + i + " is ");
			for (int a : list) {
				System.out.print(" " + a + " ");
			}
		}
	}
	
	// delete Edge 
	public void deleteEdge(int source, int destination){
	
		if (source > adjList.size() || destination > adjList.size()){
			System.out.println("vertices are not present in list");
			return;
		}
		if (adjList.get(source).contains(destination))
				adjList.get(source).remove(destination);
		
		if (adjList.get(destination).contains(source))
				adjList.get(destination).remove(source);
	}
	
	//add vertex to adjucency list 
	public void addVertex(int v, List<Integer> list){
		adjList.put(v, list);
	}
	
	//delete vertex from adjucency list
	public void deleteVertex(int v){
		adjList.remove(v);
	}
	
	/*
	 * Uses of BFS in graph
	 * 1) Shortest path for unweighted graph
	 * 2) Peer to Peer network
	 * 3) Crawlers in search engines
	 * 4) Social Networking websites
	 * 5) GPS navigation system
	 * 6) Broadcasting in network
	 * 7) In Garbage collection
	 * 8) Cycle detection in undirected graph only
	 * 9) Ford-Fulkerson algorithm
	 * 10) TO test if graph is bipartite
	 * 11) Path finding
	 * 12) Finding all nodes within one connected component
	 */
	//get BFS of graph from given vertex
	public void BFS(int v) {
		Boolean visited[] = new Boolean[adjList.size()];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		visited[v] = true;
		queue.add(v);
		
		while(queue.size() != 0){
			v = queue.poll();
			System.out.println("vertex at head of queue");
			adjList.get(v).forEach(ver -> {
				if (!visited[ver]) {
					visited[ver] = true;
					queue.add(ver);
					System.out.println("vertex added to queue" +ver);
				}
			});
		}
	}
	
	/*
	 * Uses of DFS in graph
	 * 1) For unweighted graph, DFS traversal produces minimum spanning tree and all pair shortest path
	 * 2) Detecting cycle in directed and undirected graph - if back edge found during DFS, then graph has cycle
	 * 3) Path finding - find shortest path
	 * 4) Topological Sorting - used in scheduling jobs, instruction scheduling, logic synthesis, order of compilation tasks
	 * 							data serialization, resolving symbol dependancies in linkers
	 * 5) test if graph is bipartite
	 * 6) Finding strongly connected components of graph - graph is called strongly connected when each vertex is connected to each other vertex
	 * 7) Solving puzzles with only one solution - mazes  
	 */
	//get DFS path in graph from given vertex
	public void DFS(int v){
		//mark all viited nodes as true in visited boolean array
		Boolean visited[] = new Boolean[adjList.size()];
		
		DFSUtil(v, visited);
	}
	
	//DFS utility which works recursively
	public void DFSUtil(int v, Boolean visited[]){
		visited[v] = true;

		adjList.get(v).forEach(neighbour -> {
			if (!visited[neighbour]) {
				visited[neighbour] = true;
				DFSUtil(neighbour, visited);
			}
		});
	}
	
	
	
	public static void main(String[] args) {
	
		Scanner s = new Scanner(System.in);
		//read no of vertices and edges from system
		int vertices = s.nextInt();
		int edges = s.nextInt();
		int count = 0;
		
		//create adjucency list for given no of vertices
		GraphAdjucencyList aList = new GraphAdjucencyList(vertices);
		
		while(count < edges) {
			int src = s.nextInt();
			int dest = s.nextInt();
			aList.setEdge(src, dest);
			count++;
		}
		
	}
	
	/*
	 * can course be finished given list of prerequisites
	 * http://www.programcreek.com/2014/05/leetcode-course-schedule-java/
	 * BFS solution is given below
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
	    if(prerequisites == null){
	        throw new IllegalArgumentException("illegal prerequisites array");
	    }
	 
	    int len = prerequisites.length;
	 
	    if(numCourses == 0 || len == 0){
	        return true;
	    }
	 
	    // counter for number of prerequisites
	    int[] pCounter = new int[numCourses];
	    for(int i=0; i<len; i++){
	        pCounter[prerequisites[i][0]]++;
	    }
	 
	    //store courses that have no prerequisites
	    LinkedList<Integer> queue = new LinkedList<Integer>();
	    for(int i=0; i<numCourses; i++){
	        if(pCounter[i]==0){
	            queue.add(i);
	        }
	    }
	 
	    // number of courses that have no prerequisites
	    int numNoPre = queue.size();
	 
	    while(!queue.isEmpty()){
	        int top = queue.remove();
	        for(int i=0; i<len; i++){
	            // if a course's prerequisite can be satisfied by a course in queue
	            if(prerequisites[i][1]==top){
	                pCounter[prerequisites[i][0]]--;
	                if(pCounter[prerequisites[i][0]]==0){
	                    numNoPre++;
	                    queue.add(prerequisites[i][0]);
	                }
	            }
	        }
	    }
	 
	    return numNoPre == numCourses;
	}
	
	/* Extension of course schedule problem to print valid sequence 
	 * http://www.programcreek.com/2014/06/leetcode-course-schedule-ii-java/
	 * DFS solution can be used for printing valid sequense
	 */
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
	    if(prerequisites == null){
	        throw new IllegalArgumentException("illegal prerequisites array");
	    }
	 
	    int len = prerequisites.length;
	 
	    //if there is no prerequisites, return a sequence of courses
	    if(len == 0){
	        int[] res = new int[numCourses];
	        for(int m=0; m<numCourses; m++){
	            res[m]=m;
	        }
	        return res;
	    }
	 
	    //records the number of prerequisites each course (0,...,numCourses-1) requires
	    int[] pCounter = new int[numCourses];
	    for(int i=0; i<len; i++){
	        pCounter[prerequisites[i][0]]++;
	    }
	 
	    //stores courses that have no prerequisites
	    LinkedList<Integer> queue = new LinkedList<Integer>();
	    for(int i=0; i<numCourses; i++){
	        if(pCounter[i]==0){
	            queue.add(i);
	        }
	    }
	 
	    int numNoPre = queue.size();
	 
	    //initialize result
	    int[] result = new int[numCourses];
	    int j=0;
	 
	    while(!queue.isEmpty()){
	        int c = queue.remove();
	        result[j++]=c;
	 
	        for(int i=0; i<len; i++){
	            if(prerequisites[i][1]==c){
	                pCounter[prerequisites[i][0]]--;
	                if(pCounter[prerequisites[i][0]]==0){
	                    queue.add(prerequisites[i][0]);
	                    numNoPre++;
	                }
	            }
	 
	        }
	    }
	 
	    //return result
	    if(numNoPre==numCourses){
	        return result;
	    }else{
	        return new int[0];
	    }
	}
	
	/* find minimum height tree
	 * 
	 */
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
	    List<Integer> result = new ArrayList<Integer>();
	    if(n==0){
	        return result;
	    }
	    if(n==1){
	        result.add(0);
	        return result;
	    }
	 
	    ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
	    for(int i=0; i<n; i++){
	        graph.add(new HashSet<Integer>());
	    }
	 
	    for(int[] edge: edges){
	        graph.get(edge[0]).add(edge[1]);
	        graph.get(edge[1]).add(edge[0]);
	    }
	 
	    LinkedList<Integer> leaves = new LinkedList<Integer>();
	    for(int i=0; i<n; i++){
	        if(graph.get(i).size()==1){
	            leaves.offer(i);
	        }
	    }
	 
	    if(leaves.size()==0){
	        return result;
	    }
	 
	    while(n>2){
	        n = n-leaves.size();
	 
	        LinkedList<Integer> newLeaves = new LinkedList<Integer>();
	 
	        for(int l: leaves){
	            int neighbor = graph.get(l).iterator().next();
	            graph.get(neighbor).remove(l);
	            if(graph.get(neighbor).size()==1){
	                newLeaves.add(neighbor);
	            }
	        }
	 
	        leaves = newLeaves;
	    }
	 
	    return leaves;
	}
	/*****************************BackTracking Problems **************************************/
	/* find if there is path of more than k length(weight) from source
	 */
	Boolean visited[] = new Boolean[udEdgeList.size()];
	//v - source vertex, k - total weight to be checked
	public boolean isPathGreaterThanK(int src, int k, Boolean [] visited){
		
		//mark source as visited
		visited[src] = true;
		
		udEdgeList.get(src).forEach( e -> {
			int v = e.ver;
			int w = e.weight;
			//if vertex is already there then there is cycle and we ignore that
			if (!visited[v]) {

				//if current weight is more than k return true
				if (w > k)
					return true;

				//else add this vertex to visited
				visited[v] = true;

				//call same function in recursion and check if this vertex can provide path > k
				if (isPathGreaterThanK(v, k - w, visited))
					return true;

				//backtrack
				visited[v] = false;
			}
		});

		//if no adjacent could provide path return false
		return false;
		
	}
	
	/* Tug of war 
	 * 
	 */
	
	// function that tries every possible solution by calling itself recursively
	void TOWUtil(int arr[], int n, boolean curr_elements[], int no_of_selected_elements,
	             boolean soln[], int min_diff, int sum, int curr_sum, int curr_position)
	{
	    // checks whether the it is going out of bound
	    if (curr_position == n)
	        return;
	 
	    // checks that the numbers of elements left are not less than the
	    // number of elements required to form the solution
	    if ((n/2 - no_of_selected_elements) > (n - curr_position))
	        return;
	 
	    // consider the cases when current element is not included in the solution
	    TOWUtil(arr, n, curr_elements, no_of_selected_elements,
	              soln, min_diff, sum, curr_sum, curr_position+1);
	 
	    // add the current element to the solution
	    no_of_selected_elements++;
	    curr_sum = curr_sum + arr[curr_position];
	    curr_elements[curr_position] = true;
	 
	    // checks if a solution is formed
	    if (no_of_selected_elements == n/2)
	    {
	        // checks if the solution formed is better than the best solution so far
	        if (Math.abs(sum/2 - curr_sum) < min_diff)
	        {
	            min_diff = Math.abs(sum/2 - curr_sum);
	            for (int i = 0; i<n; i++)
	                soln[i] = curr_elements[i];
	        }
	    }
	    else
	    {
	        // consider the cases where current element is included in the solution
	        TOWUtil(arr, n, curr_elements, no_of_selected_elements, soln,
	                  min_diff, sum, curr_sum, curr_position+1);
	    }
	 
	    // removes current element before returning to the caller of this function
	    curr_elements[curr_position] = false;
	}
	 
	// main function that generate an arr
	void tugOfWar(int arr[], int n)
	{
	    // the boolen array that contains the inclusion and exclusion of an element
	    // in current set. The number excluded automatically form the other set
	    boolean curr_elements[] = new boolean[n];
	 
	    // The inclusion/exclusion array for final solution
	    boolean soln[] = new boolean[n];
	 
	    int min_diff = Integer.MAX_VALUE;
	 
	    int sum = 0;
	    for (int i=0; i<n; i++)
	    {
	        sum += arr[i];
	        curr_elements[i] =  soln[i] = false;
	    }
	 
	    // Find the solution using recursive function TOWUtil()
	    TOWUtil(arr, n, curr_elements, 0, soln, min_diff, sum, 0, 0);
	 
	    // Print the solution
	    System.out.println( "The first subset is: ");
	    for (int i=0; i<n; i++)
	    {
	        if (soln[i] == true)
	        	System.out.println( arr[i] +" ");
	    }
	    System.out.println("\nThe second subset is: ");
	    for (int i=0; i<n; i++)
	    {
	        if (soln[i] == false)
	        	System.out.println(arr[i] + " ");
	    }
	}
	
	/* Rat n Maze problem  */
	//we need to write one more function which will create sol[][] array same size of maze and call this utility
	//function in for loop for x and y 
	public boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]){
		//if x and y are at destination return true
		if ((x == maze.length-1) && (y == maze.length-1)) {
			sol[x][y] = 1;
			return true;
		}
		// here we can add one more condition to check if x and y are not out of bound only then proceed further else return false
		//mark x and y as part of solution path
		sol[x][y] = 1;
		
		//move forward with x direction
		if (solveMazeUtil(maze, x+1, y, sol))
			return true;
		
		//if solution is not found in x direction move down in y direction 
		if (solveMazeUtil(maze, x, y+1, sol))
			return true;
		
		//if none of the above movements worked then un mark x, y from path of solution (backtrack)
		sol[x][y] = 0;
		return false;
		
	}

	//BFS

	public GraphNode cloneGraphNew(GraphNode source) {

		LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
		queue.add(source);

		//put the node into map
		HashMap<GraphNode, GraphNode> copyMap = new HashMap<GraphNode, GraphNode>();
		copyMap.put(source, new GraphNode(source.val));

		while (!queue.isEmpty()) {
			//get front node in queue and visit its neighbours
			GraphNode u = queue.poll();

			GraphNode cloneNodeU = copyMap.get(u);
			u.neighbours.forEach(n -> {
				GraphNode cloneNodeG = copyMap.get(n);
				if (cloneNodeG == null) {
					queue.add(n);
					cloneNodeG = new GraphNode(n.val);
					copyMap.put(n, cloneNodeG);
				}
				cloneNodeU.neighbours.add(cloneNodeG);
			});
		}

		return copyMap.get(source);
	}

	/* Given n nodes labeled from 0 to n-1 and a list of undirected edges
	(each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
	a graph, G, is a tree iff the following two conditions are met:

	1.  G is fully connected. In other words, for every pair of nodes in G, there is a path between them.
	2.	G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.

	DFS
	1. G is fully connected if, and only if, we started a depth-first search from a single source
		and discovered all nodes in G during it.
	2. G contains no cycles if, and only if, the depth-first search never goes back to an already discovered node.
		We need to be careful though not to count trivial cycles of the form A → B → A
		that occur with most implementations of undirected edges.
	 */

	public boolean validTree(int n, int[][] edges) {
		// Create a new list of lists.
		List<List<Integer>> adjacencyList = new ArrayList<>();
		// Initialise an empty list for each node.
		for (int i = 0; i < n; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		// Go through the edge list, populating the adjacency list.
		for (int[] edge : edges) {
			adjacencyList.get(edge[0]).add(edge[1]);
			adjacencyList.get(edge[1]).add(edge[0]);
		}

		// Use a map to keep track of how we got into each node.
		//i.e. it keeps track of the "parent" node that we got to a node from
		Map<Integer, Integer> parent = new HashMap<>();
		parent.put(0, -1);
		Stack<Integer> stack = new Stack<>();
		stack.push(0);

		// While there are nodes remaining on the stack...
		while (!stack.isEmpty()) {
			int node = stack.pop();  // Take one off to visit.
			// Check for unseen neighbours of this node:
			for (int neighbour : adjacencyList.get(node)) {
				// Don't look at the trivial cycle.
				if (parent.get(node) == neighbour) {
					continue;
				}
				// Check if we've already seen this node.
				if (parent.containsKey(neighbour)) {
					return false;    // There must be a cycle.
				}
				// Otherwise, put this neighbour onto stack
				// and record that it has been seen.
				stack.push(neighbour);
				parent.put(neighbour, node);
			}
		}

		return parent.size() == n;
	}


	/* alien dictionary
	3 steps
	1. 	Extracting dependency rules from the input. For example "A must be before C", "X must be before D", or "E must be before B".
	2. 	Putting the dependency rules into a graph with letters as nodes and dependencies as edges (an adjacency list is best).
	3. 	Topologically sorting the graph nodes.
	 */
	public String alienOrder(String[] words) {

		// Step 1: Create data structures and find all unique letters.
		Map<Character, List<Character>> adjList = new HashMap<>();
		Map<Character, Integer> counts = new HashMap<>();  //dependency metric. letter dependent on how many other letters
		for (String word : words) {
			for (char c : word.toCharArray()) {
				counts.put(c, 0);
				adjList.put(c, new ArrayList<>());
			}
		}

		// Step 2: Find all edges.
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			// Check that word2 is not a prefix of word1.
			if (word1.length() > word2.length() && word1.startsWith(word2)) {
				return "";
			}
			// Find the first non match and insert the corresponding relation.
			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				if (word1.charAt(j) != word2.charAt(j)) {
					adjList.get(word1.charAt(j)).add(word2.charAt(j));
					counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
					break;
				}
			}
		}

		// Step 3: Breadth-first search.
		StringBuilder sb = new StringBuilder();
		Queue<Character> queue = new LinkedList<>();
		for (Character c : counts.keySet()) {
			if (counts.get(c).equals(0)) {
				queue.add(c);
			}
		}
		while (!queue.isEmpty()) {
			Character c = queue.remove();
			sb.append(c);
			for (Character next : adjList.get(c)) {
				counts.put(next, counts.get(next) - 1);
				if (counts.get(next).equals(0)) {
					queue.add(next);
				}
			}
		}

		if (sb.length() < counts.size()) {
			return "";
		}
		return sb.toString();
	}

	/* Connected components
	write a function to find the number of connected components in an undirected graph.
	 */

	public int countComponents(int n, int[][] edges) {
		int res = 0;
		// Create a new list of lists.
		List<List<Integer>> adjacencyList = new ArrayList<>();
		// Initialise an empty list for each node.
		for (int i = 0; i < n; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		// Go through the edge list, populating the adjacency list.
		for (int[] edge : edges) {
			adjacencyList.get(edge[0]).add(edge[1]);
			adjacencyList.get(edge[1]).add(edge[0]);
		}
		boolean[] visited = new boolean[n];
		// because there are unconnected edges we need to use for loop vs Stack and while loop
		for(int i=0; i<n; i++){
			if(!visited[i]){
				dfs(i, visited, adjacencyList);
				res++;
			}
		}
		return res;
	}

	public void dfs(int i, boolean[] visited, List<List<Integer>> adjacencyList) {
		if(visited[i]) {
			return;
		}
		visited[i] = true;
		for(int edge : adjacencyList.get(i)) {
			dfs(edge, visited, adjacencyList);
		}
	}


	/* Reconstruct Itinerary
		Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
		Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
	 */

	public List<String> findItinerary(List<List<String>> tickets) {

		// origin -> list of destinations
		HashMap<String, LinkedList<String>> flightMap = new HashMap<>();
		LinkedList<String> result = null;

		// Step 1). build the graph first
		for(List<String> ticket : tickets) {
			String origin = ticket.get(0);
			String dest = ticket.get(1);
			if (flightMap.containsKey(origin)) {
				LinkedList<String> destList = flightMap.get(origin);
				destList.add(dest);
			} else {
				LinkedList<String> destList = new LinkedList<>();
				destList.add(dest);
				flightMap.put(origin, destList);
			}
		}

		// Step 2). order the destinations
		flightMap.forEach((key, value) -> Collections.sort(value));

		result = new LinkedList<>();
		// Step 3). post-order DFS
		this.DFS("JFK", flightMap, result);
		return result;
	}

	protected void DFS(String origin, HashMap<String, LinkedList<String>> flightMap, LinkedList<String> result) {
		// Visit all the outgoing edges first.
		if (flightMap.containsKey(origin)) {
			LinkedList<String> destList = flightMap.get(origin);
			while (!destList.isEmpty()) {
				// while we visit the edge, we trim it off from graph.
				String dest = destList.pollFirst();
				DFS(dest, flightMap, result);
			}
		}
		// add the airport to the head of the itinerary
		result.offerFirst(origin);
	}

	/* calculate equations
	Equations are given in the format A / B = k, where A and B are variables represented as strings,
	and k is a real number (floating point number). Given some queries, return the answers.
	If the answer does not exist, return -1.0.

	Example:
	Given a / b = 2.0, b / c = 3.0.
	queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
	return [6.0, 0.5, -1.0, 1.0, -1.0 ].
	 */
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		HashMap<String, Integer> map = new HashMap<>();
		int index = 0;
		for(int i = 0; i < values.length; i++) {
			List<String> pair = equations.get(i);
			if(!map.containsKey(pair.get(0))) {
				map.put(pair.get(0), index++);
			}

			if(!map.containsKey(pair.get(1))) {
				map.put(pair.get(1), index++);
			}
		}

		int n = map.size();
		Double[][] graph = new Double[n][n];
		for(int i = 0; i < values.length; i++) {
			List<String> pair = equations.get(i);
			int a = map.get(pair.get(0)), b = map.get(pair.get(1));
			graph[a][b] = values[i];
			graph[b][a] = values[i] == 0 ? 0 : 1.0 / values[i];
		}

		for(int i = 0; i < n; i++) {
			boolean[] visited = new boolean[n];
			visited[i] = true;
			graph[i][i] = 1.0;
			dfs(i, i, 1.0, visited, graph);
		}

		double[] result = new double[queries.size()];
		for(int i = 0; i < result.length; i++) {
			List<String> q = queries.get(i);
			String from = q.get(0), to = q.get(1);
			if(map.containsKey(from) && map.containsKey(to) && graph[map.get(from)][map.get(to)] != null) {
				result[i] = graph[map.get(from)][map.get(to)];
			} else {
				result[i] = -1;
			}
		}

		return result;
	}

	private void dfs(int start, int cur, double curValue, boolean[] visited, Double[][] graph) {
		for(int i = 0; i < visited.length; i++) {
			if(visited[i]) continue;
			if(graph[cur][i] != null) {
				graph[start][i] = curValue * graph[cur][i];
				visited[i] = true;
				dfs(start, i, curValue * graph[cur][i], visited, graph);
			}
		}
	}
}
