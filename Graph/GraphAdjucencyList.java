package ds.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
			Iterator<Integer> list = adjList.get(v).listIterator();
			while (list.hasNext()) {
				int ver = list.next();
				if (!visited[ver]) {
					visited[ver] = true;
					queue.add(ver);
					System.out.println("vertex added to queue" +ver);
				}
			}
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
		
		Iterator<Integer> i = adjList.get(v).listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]){
				visited[n] = true;
				DFSUtil(n, visited);
			}
		}
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
		
		Iterator<UDEdge> list = udEdgeList.get(src).listIterator();
		
		while (list.hasNext()) {
			UDEdge e = list.next();
			
			int v = e.ver;
			int w = e.weight;
			//if vertex is already there then there is cycle and we ignore that
			if (visited[v])
				continue;
			
			//if current weight is more than k return true
			if (w>k)
				return true;
			
			//else add this vertex to visited
			visited[v] = true;
			
			//call same function in recursion and check if this vertex can provide path > k
			if (isPathGreaterThanK(v, k, visited))
				return true;
			
			//backtrack
			visited[v] = false;
		}
		
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
	 
}
