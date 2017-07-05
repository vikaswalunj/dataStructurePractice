package ar.DynamicProgramming;

import ds.Graph.Graph;

public class FunctionsDynamicProgram {

	
	/* lis() returns the length of the longest increasing
    subsequence in arr[] of size n 
    http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
    For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.*/
	
	 static int lis(int arr[],int n)
	 {
	       int lis[] = new int[n];
	       int i,j,max = 0;
	
	       /* Initialize LIS values for all indexes */
	        for ( i = 0; i < n; i++ )
	           lis[i] = 1;
	
	        /* Compute optimized LIS values in bottom up manner */
	        for ( i = 1; i < n; i++ )
	           for ( j = 0; j < i; j++ ) 
	                      if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
	                 lis[i] = lis[j] + 1;
	
	        /* Pick maximum of all LIS values */
	        for ( i = 0; i < n; i++ )
	           if ( max < lis[i] )
	              max = lis[i];
	
	         return max;
	 }
	
	/*
	 * find LCA - longest common sequence between 2 strings
	 * e.g. "ADKJHE" and "SDJEK" - "DJE" - 3. since K was not in order it did not count
	 * recursive solution 
	 */
	
	public int lca(char []A, char []B, int m, int n){
		if (m==0 || n==0){
			return 0;
		}
		
		//if last character matches in both string then lca = lca (char array - 1 in both)
		if(A[m-1]==B[n-1])
			return 1+lca(A, B, m-1, n-1);
		else 
			//else return lca with length less by 1 in both char array
			return Math.max(lca(A, B, m, n-1),lca(A, B, m-1, n));
	}
	
	/*
	 * Edit Distance - minimum no of operations required to convert string1 to string2
	 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
	 * Input:   str1 = "sunday", str2 = "saturday"
		Output:  3
		Last three and first characters are same.  We basically
		need to convert "un" to "atur".  This can be done using
		below three operations. 
		Replace 'n' with 'r', insert t, insert a
	 * 
	 */
	
	public int editDist(String str1 , String str2 , int m ,int n)
    {
        // If first string is empty, the only option is to
		// insert all characters of second string into first
		if (m == 0) return n;
      
		// If second string is empty, the only option is to
		// remove all characters of first string
		if (n == 0) return m;
      
    	// If last characters of two strings are same, nothing
    	// much to do. Ignore last characters and get count for
    	// remaining strings.
    	if (str1.charAt(m-1) == str2.charAt(n-1))
    		return editDist(str1, str2, m-1, n-1);
      
    	// If last characters are not same, consider all three
    	// operations on last character of first string, recursively
    	// compute minimum cost for all three operations and take
    	// minimum of three values.
    	return 1 + min( editDist(str1,  str2, m, n-1),    // Insert
    					editDist(str1,  str2, m-1, n),   // Remove
    					editDist(str1,  str2, m-1, n-1) // Replace                     
                   		);
    }
	
	
	/*
	 * min of 3 integers. since Math.min supports just 2 integers we created this new method
	 */
	
	public int min(int x, int y, int z){
		if (x<y && x<z)
			return x;
		if (y<x && y<z)
			return y;
		return z;
	}
	
	//maximum utility for 3 integers.
	public int max(int x, int y, int z){
		if (x>y && x>z)
			return x;
		if (y>x && y>z)
			return y;
		return z;
	}
	
	/* Returns cost of minimum cost path from (0,0) to (m, n) in mat[R][C]
	 * The path to reach (m, n) must be through one of the 3 cells: (m-1, n-1) or (m-1, n) or (m, n-1)
	 */
	
	public static int cost[][] = { {1, 2, 3}, {4, 8, 2}, {1, 5, 3} };
	
	public int minCost(int cost[][], int m, int n)
	{
	   if (n < 0 || m < 0)
	      return Integer.MAX_VALUE;
	   else if (m == 0 && n == 0)
	      return cost[m][n];
	   else
	      return cost[m][n] + min( minCost(cost, m-1, n-1),
	                               minCost(cost, m-1, n), 
	                               minCost(cost, m, n-1) );
	}
	
	/*
	 * Returns the count of ways we can sum  S[0...m-1] coins to get sum n 
	 */
	
	public int count( int S[], int m, int n )
	{
	    // If n is 0 then there is 1 solution (do not include any coin)
	    if (n == 0)
	        return 1;
	     
	    // If n is less than 0 then no solution exists
	    if (n < 0)
	        return 0;
	 
	    // If there are no coins and n is greater than 0, then no solution exist
	    if (m <=0 && n >= 1)
	        return 0;
	 
	    // count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
	    return count( S, m - 1, n ) + count( S, m, n-S[m-1] );
	}
	
	/*
	 * Matrix Ai has dimension p[i-1] x p[i] for i = 1..n 
	 */
	
    public static int MatrixChainOrder(int p[], int i, int j)
    {
        if (i == j)
            return 0;
 
        int min = Integer.MAX_VALUE;
 
        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count
        for (int k=i; k<j; k++)
        {
            int count = MatrixChainOrder(p, i, k) +
                        MatrixChainOrder(p, k+1, j) +
                        p[i-1]*p[k]*p[j];
 
            if (count < min)
                min = count;
        }
 
        // Return minimum count
        return min;
    }
	
    /*
     * recursive implementation of 0-1 Knapsack problem 
     */
	
 // A utility function that returns maximum of two integers
    static int max(int a, int b) { return (a > b)? a : b; }
     
    // Returns the maximum value that can be put in a knapsack of capacity W, n is size of both arrays wt and val
    static int knapSack(int W, int wt[], int val[], int n)
    {
    	// Base Case
    	if (n == 0 || W == 0)
    		return 0;
     
    	// If weight of the nth item is more than Knapsack capacity W, then
    	// this item cannot be included in the optimal solution
    	if (wt[n-1] > W)
    		return knapSack(W, wt, val, n-1);
     
    	// Return the maximum of two cases: 
    	// (1) nth item included 
    	// (2) not included
    	else return max( val[n-1] + knapSack(W-wt[n-1], wt, val, n-1),
                    	knapSack(W, wt, val, n-1)
                     	);
     }
    
    /* Minimum cost to fill given weight in bag
     * You are given a bag of size W kg and you are provided costs of packets different weights of oranges in array cost[] 
     * where cost[i] is basically cost of ‘i’ kg packet of oranges. 
     * Where cost[i] = -1 means that ‘i’ kg packet of orange is unavailable
     */
    //cost[] - initial cost array, W - capacity of bacg
    public int minimumCost(int cost[], int n, int W){
    	
    	//val[] - store cost of i kg packet of orange, wt[] - weight of packet of orange
    	int val[] = new int[n];
    	int wt[] = new int[n];
    	
    	int size=0;
    	for(int i=0; i<n; i++){
    		if (cost[i] != -1){
    			val[i] = cost[i];
    			wt[i] = i+1;
    			size++;
    		}
    	}
    	
    	n = size;
    	int min_cost[][] = new int[n+1][W+1];
    	
    	//fill 0th row with infinity
    	for (int i=0; i<W; i++)
    		min_cost[0][i] = Integer.MAX_VALUE;
    	
    	//fill 0th column with 0
    	for (int i=0; i<n; i++)
    		min_cost[i][0] = 0;
    	
    	//now check for each weight one by one and fill min_cost array accordingly
    	for (int i=1; i<=n; i++){
    		for (int j=1; j<=W; j++){
    			//if capacity of bag is less than weight of item
    			if (wt[i-1]>j)
    				min_cost[i][j] = min_cost[i-1][j];
    			else
    				//check if we get minimum cost by including it or excluding it
    				min_cost[i][j] = Math.min(min_cost[i-1][j], min_cost[i][j-wt[i-1]] +val[i-1]);
    		}
    	}
    	return (min_cost[n][W]==Integer.MAX_VALUE)? -1 : min_cost[n][W];
    }
    
    /*
     * Egg drop
     */
    
    /* Function to get minimum number of trials needed in worst
    case with n eggs and k floors */
  public int eggDrop(int n, int k)
  {
      // If there are no floors, then no trials needed. OR if there is
      // one floor, one trial needed.
      if (k == 1 || k == 0)
          return k;
   
      // We need k trials for one egg and k floors
      if (n == 1)
          return k;
      int min = Integer.MAX_VALUE, x, res;
   
      // Consider all droppings from 1st floor to kth floor and
      // return the minimum of these values plus 1.
      for (x = 1; x <= k; x++)
      {
          res = max(eggDrop(n-1, x-1), eggDrop(n, k-x));
          if (res < min)
              min = res;
      }
   
      return min + 1;
  }
  
  /*
   * find longest palindromic subsequence
   * e.g. in string "BBABCBCAB" output = 7 because “BABCBAB” is longest palindrome
   */
  // i - starting index and j - last index
  public int longestPalindromeSeq(char [] seq, int i, int j) {
	  
	  //Base case 1: if there is only one character
	  if (i == j)
		  return 1;
	  
	  //Base case 2:if there are only 2 characters and both are same 
	  if ((seq[i] == seq[j]) && (i+1 == j))
		  return 2;
	  
	  //if first and last character match
	  if (seq[i] == seq[j])
		  return longestPalindromeSeq(seq, i+1, j-1) +2;
	  
	  //if first and last character do not match
	  return max(longestPalindromeSeq(seq, i+1, j), longestPalindromeSeq(seq, i, j-1));
  }
  
  /*
   * Cutting rod & prices e.g. for values given below max = 22 (price[2] = price[6])
   * length   | 1   2   3   4   5   6   7   8  
   * --------------------------------------------
   * price    | 1   5   8   9  10  17  17  20
   */
  
  /* Returns the best obtainable price for a rod of length
  n and price[] as prices of different pieces */
  public static int cutRod(int price[], int n)
  {
	  if (n <= 0)
		  return 0;
	  int max_val = Integer.MIN_VALUE;

	  // Recursively cut the rod in different pieces and
	  // compare different configurations
	  for (int i = 0; i<n; i++)
		  max_val = Math.max(max_val,
                         	price[i] + cutRod(price, n-i-1));

	  return max_val;
  }
  
  /* Maximum product cutting
   * Given a rope of length n meters, cut the rope in different parts of integer lengths 
   * in a way that maximizes product of lengths of all parts. You must make at least one cut. 
   * recursion takes exponential time so we will solve this with dp[] approach as it has overlapping problem property
   */
  
  public int maxProd(int n){
	  
	  int val[] = new int[n+1];
	  val[0] = val[1] = 0;
	  
	  //build table val[] in bottom up manner
	  for (int i=1; i<n; i++){
		  int max_val = 0;
		  for (int j=1; j <= 1/2; j++){
			  max_val = max(max_val, j*(i-j), j*val[i-j]);
			val[i] = max_val;  
		  }
	  }
	  return val[n];
  }
  
  
  /*
   * maximum sum increasing sub sequence
   * if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100), 
   * if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10) and 
   * if the input array is {10, 5, 4, 3}, then output should be 10
   */
  /* maxSumIS() returns the maximum sum of increasing
  subsequence in arr[] of size n */
  /*LIS - longest increasing sequense has same solution*/
  
  public int maxSumIS(int arr[], int n) {
	  
	  int i, j, max=0;
	  int msis[] = new int[n];
	  
	  /* initialize msis values for all indexes*/
	  for ( i=0; i<n; i++)
		  msis[i] = arr[i];
	  
	  /*compute maximum sum values in bottom up manner */
	  for (i=1; i<n; i++){
		  for (j=0; j<i; j++){
			  if (arr[i] > arr[j] && msis[i]< msis[j]+arr[i])
				  msis[i] = msis[j] + arr[i];
		  }
	  }
		  
	  /*pick maximum of all msis values*/
	  for ( i = 0; i < n; i++ )
          if ( max < msis[i] )
              max = msis[i];
	  
	  return max;
  }
  
  /*
   * Floyd Warshall algorithm - solving all pairs shortest path i.e. find shortest distance between 
   * every pair of vertices in given edge weighted graph
   * For every pair (i, j) of source and destination vertices respectively, there are two possible cases.
   *	1) k is not an intermediate vertex in shortest path from i to j. We keep the value of dist[i][j] as it is.
   *	2) k is an intermediate vertex in shortest path from i to j. We update the value of dist[i][j] as dist[i][k] + dist[k][j].
   */
  
  public void floydWarshall(int graph[][]){
	  int v = 4;
	  //define result graph/solution matrix
	  int dist[][] = new int[v][v];
	  int i, j, k;
	  
	  /* initialize solution matrix to same as input graph matrix. Or we can say initial values of
	   * shortest distance are based on shortest paths considering no intermediate vertex 
	   */
	  for (i=0; i<v; i++){
		  for (j=0; j<v; j++){
			  dist[i][j] = graph[i][j];
		  }
	  }
	  
	  /* Add all vertices one by one to the set of intermediate
      vertices.
     ---> Before start of a iteration, we have shortest
          distances between all pairs of vertices such that
          the shortest distances consider only the vertices in
          set {0, 1, 2, .. k-1} as intermediate vertices.
     ----> After the end of a iteration, vertex no. k is added
           to the set of intermediate vertices and the set
           becomes {0, 1, 2, .. k} */
	  
	  for (k=0; k<v; k++) {
		  // Pick all vertices as source one by one
		  for (i=0; i<v; i++){
			// Pick all vertices as destination for the
              // above picked source
			  for (j=0; j<v; j++){
				  //if vertex k is on shortest path from i to j, then update value of dist[i][j]
				  if (dist[i][k] + dist[k][j] < dist[i][j])
					  dist[i][j] = dist[i][k] + dist[k][j];
			  }
		  }
	  }
	  //print dist[][] to print solution
  }
  
  
  /* Palindrome partitioning - Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
   * For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”. minimum 3 cuts are needed for “ababbbabbababa”. 
   * The three cuts are “a|babbbab|b|ababa”.
   */
  
  //Returns the minimum number of cuts needed to partition a string
  //such that every part is a palindrome
  public int minPalPartion(char [][]str)
  {
	  // Get the length of the string
	  int n = str.length;
 
	  /* Create two arrays to build the solution in bottom up manner
      	C[i][j] = Minimum number of cuts needed for palindrome partitioning
                	of substring str[i..j]
      	P[i][j] = true if substring str[i..j] is palindrome, else false
      	Note that C[i][j] is 0 if P[i][j] is true */
	  int C[][] = new int[n][n];
	  boolean P[][] = new boolean[n][n];
 
   int i, j, k, L; // different looping variables
 
   // Every substring of length 1 is a palindrome
   for (i=0; i<n; i++)
   {
       P[i][i] = true;
       C[i][i] = 0;
   }
 
   /* L is substring length. Build the solution in bottom up manner by
      considering all substrings of length starting from 2 to n.
      The loop structure is same as Matrx Chain Multiplication problem (
      See http://www.geeksforgeeks.org/archives/15553 )*/
   for (L=2; L<=n; L++)
   {
       // For substring of length L, set different possible starting indexes
       for (i=0; i<n-L+1; i++)
       {
           j = i+L-1; // Set ending index
 
           // If L is 2, then we just need to compare two characters. Else
           // need to check two corner characters and value of P[i+1][j-1]
           if (L == 2)
               P[i][j] = (str[i] == str[j]);
           else
               P[i][j] = (str[i] == str[j]) && P[i+1][j-1];
 
           // IF str[i..j] is palindrome, then C[i][j] is 0
           if (P[i][j] == true)
               C[i][j] = 0;
           else
           {
               // Make a cut at every possible localtion starting from i to j,
               // and get the minimum cost cut.
               C[i][j] = Integer.MAX_VALUE;
               for (k=i; k<=j-1; k++)
                   C[i][j] = min(C[i][j], C[i][k] + C[k+1][j]+1);
           }
       }
   }
 
   // Return the min cut value for complete string. i.e., str[0..n-1]
   return C[0][n-1];
  }
  
  //utility ti return minimum between two integers
  static int min(int a, int b) { return (a<b)?a:b;}
  
  
  /* Partition Problem - 
   * Following are the two main steps to solve this problem:
	* 1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, so return false.
	* 2) If sum of array elements is even, calculate sum/2 and find a subset of array with sum equal to sum/2.
   */
  
  		//A utility function that returns true if there is a
  		// subset of arr[] with sun equal to given sum
	  static boolean isSubsetSum (int arr[], int n, int sum)
	  {
	      // Base Cases
	      if (sum == 0)
	          return true;
	      if (n == 0 && sum != 0)
	          return false;
	
	      // If last element is greater than sum, then ignore it
	      if (arr[n-1] > sum)
	          return isSubsetSum (arr, n-1, sum);
	
	      /* else, check if sum can be obtained by any of
	         the following
	      (a) including the last element
	      (b) excluding the last element
	      */
	      return isSubsetSum (arr, n-1, sum) ||
	             isSubsetSum (arr, n-1, sum-arr[n-1]);
	  }
	
	  // Returns true if arr[] can be partitioned in two
	  // subsets of equal sum, otherwise false
	  static boolean findPartition (int arr[], int n)
	  {
	      // Calculate sum of the elements in array
	      int sum = 0;
	      for (int i = 0; i < n; i++)
	          sum += arr[i];
	
	      // If sum is odd, there cannot be two subsets
	      // with equal sum
	      if (sum%2 != 0)
	          return false;
	
	      // Find if there is subset with sum equal to half
	      // of total sum
	      return isSubsetSum (arr, n, sum/2);
	  }


	  /*Longest increasing sub sequence size (N Log N)
	   * Strategy is given in 3 points
	   * 1)  If A[i] is smallest among all end candidates of active lists, we will start 
   				new active list of length 1.
   		 2)	If A[i] is largest among all end candidates of active lists, we will clone the largest active 
  			list, and extend it by A[i].
  		 3) If A[i] is in between, we will find a list with largest end element that is smaller than A[i]. 
  			Clone and extend this list by A[i]. We will discard all other lists of same length as that of this modified list. 	
			
			Very good explanation at http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	   		Variations of LIS http://www.geeksforgeeks.org/dynamic-programming-set-14-variations-of-lis/
	   */
	  
	  public int longestIncreasingSubSequense(int A[], int size){
		  //Add boundary case when arraySize is one
		  int []tailTable = new int[size];
		  int len; //always points to empty slot
		  
		  tailTable[0] = A[0];
		  len = 1;
		  
		  for (int i=1; i<size; i++){
			  
			  if (A[i]<tailTable[0])
				  //case 1:new smallest value
				  tailTable[0] = A[i];
			  
			  else if (A[i] > tailTable[len-1])
				  	//case 2: extend largest subSequence
				  tailTable[len++] = A[i];
			  
			  else 
				  //Case 3: replace existing list and add end element as A[i] for one of list
				  tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
		  }
		  
		  return len;
	  }
	  
	  /* CeilINdex is nothing but binary search for function above */
	  
	  public int CeilIndex(int tailTable[], int start, int end, int key){
		  
		  while (end - start > 1) {
			  int m = start + (end - start)/2;
			  if(tailTable[m]>=key)
				  end = m;
			  else
				  start = m;
		  }  
		  return end;
	  }
	  
	   
	  /* Box stacking problem 
	   * You want to create a stack of boxes which is as tall as possible, but you can only stack a box on top of another box if the dimensions of the 2-D base 
	   * of the lower box are each strictly larger than those of the 2-D base of the higher box.
	   * 1) Generate all 3 rotations of all boxes. The size of rotation array becomes 3 times the size of original array. For simplicity, we consider depth as always smaller than or equal to width.
		 2) Sort the above generated 3n boxes in decreasing order of base area.
		 3) After sorting the boxes, the problem is same as LIS with following optimal substructure property.
			MSH(i) = Maximum possible Stack Height with box i at top of stack
			MSH(i) = { Max ( MSH(j) ) + height(i) } where j < i and width(j) > width(i) and depth(j) > depth(i).
			If there is no such j then MSH(i) = height(i)
		 4) To get overall maximum height, we return max(MSH(i)) where 0 < i < n
	   */


	  /* Returns the height of the tallest stack that can be
	   formed with give type of boxes */
	public int maxStackHeight( Box arr[], int n )
	{
	   /* Create an array of all rotations of given boxes
	      For example, for a box {1, 2, 3}, we consider three
	      instances{{1, 2, 3}, {2, 1, 3}, {3, 1, 2}} */
	   Box rot[] = new Box[3*n];
	   int index = 0;
	   for (int i = 0; i < n; i++)
	   {
	      // Copy the original box
	      rot[index] = arr[i];
	      index++;
	 
	      // First rotation of box
	      rot[index].h = arr[i].w;
	      rot[index].d = max(arr[i].h, arr[i].d);
	      rot[index].w = min(arr[i].h, arr[i].d);
	      index++;
	 
	      // Second rotation of box
	      rot[index].h = arr[i].d;
	      rot[index].d = max(arr[i].h, arr[i].w);
	      rot[index].w = min(arr[i].h, arr[i].w);
	      index++;
	   }
	 
	   // Now the number of boxes is 3n
	   n = 3*n;
	 
	   /* Sort the array 'rot[]' in decreasing order, using library
	      function for quick sort */
	   //qsort (rot, n, sizeof(rot[0]), compare);
	 
	   // Uncomment following two lines to print all rotations
	   // for (int i = 0; i < n; i++ )
	   //    printf("%d x %d x %d\n", rot[i].h, rot[i].w, rot[i].d);
	 
	   /* Initialize msh values for all indexes 
	      msh[i] --> Maximum possible Stack Height with box i on top */
	   int msh[] = new int[n];
	   for (int i = 0; i < n; i++ )
	      msh[i] = rot[i].h;
	 
	   /* Compute optimized msh values in bottom up manner */
	   for (int i = 1; i < n; i++ )
	      for (int j = 0; j < i; j++ )
	         if ( rot[i].w < rot[j].w &&
	              rot[i].d < rot[j].d &&
	              msh[i] < msh[j] + rot[i].h
	            )
	         {
	              msh[i] = msh[j] + rot[i].h;
	         }
	 
	 
	   /* Pick maximum of all msh values */
	   int max = -1;
	   for ( int i = 0; i < n; i++ )
	      if ( max < msh[i] )
	         max = msh[i];
	 
	   return max;
	}


	/* Minimum no of jumps to reach at end 
	 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. 
	 * Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
	 * If an element is 0, then cannot move through that element
	 * e.g. Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
		Output: 3 (1-> 3 -> 8 ->9)
	 */
	
	// Returns minimum number of jumps to reach arr[h] from arr[l]
	public int minJumps(int arr[], int l, int h)
	{
	   // Base case: when source and destination are same
	   if (h == l)
	     return 0;
	 
	   // When nothing is reachable from the given source
	   if (arr[l] == 0)
	     return Integer.MAX_VALUE;
	 
	   // Traverse through all the points reachable from arr[l]. Recursively
	   // get the minimum number of jumps needed to reach arr[h] from these
	   // reachable points.
	   int min = Integer.MAX_VALUE;
	   for (int i = l+1; i <= h && i <= l + arr[l]; i++)
	   {
	       int jumps = minJumps(arr, i, h);
	       if(jumps != Integer.MAX_VALUE && jumps + 1 < min)
	           min = jumps + 1;
	   }
	 
	   return min;
	}
	
	/*Ugly Numbers
	 * Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15
	 * Given a number n, the task is to find n’th Ugly number.
	 * n=7, output = 8; n=10, output=12; n=15, output=24
	 */
	
	/*This function divides a by greatest divisible 
	  power of b*/
	public int maxDivide(int a, int b)
	{
	  while (a%b == 0)
	   a = a/b; 
	  return a;
	}   
	 
	/* Function to check if a number is ugly or not */
	public boolean isUgly(int no)
	{
	  no = maxDivide(no, 2);
	  no = maxDivide(no, 3);
	  no = maxDivide(no, 5);
	   
	  return (no == 1)? true : false;
	}    
	 
	/* Function to get the nth ugly number*/
	public int getNthUglyNo(int n)
	{
	  int i = 1; 
	  int count = 1;   /* ugly number count */
	 
	  /*Check for all integers untill ugly count 
	    becomes n*/
	  while (n > count)
	  {
	    i++;      
	    if (isUgly(i))
	      count++; 
	  }
	  return i;
	}

	/*Bellman-Ford algorithm to find shortest path from source vertex to all other vertices in graph
	 * Time complexity is O(VE) which is worst than Dijkstra's algorithm whoes time complexity is O(VLogV)
	 * But Dijkstra's algorith does not take negative weights in consideration.
	 * Algorithm
	 * 1) Initialize dist[V] for all vertices with value infinite for shortest distance except dist[src] with 0. 
	 * 2) Step to calculate shortest distance. Loop |V|-1 times for u to v edge 
	 * 		if dist[V] > dist[u] + weight(uv) then dist[v] = dist[u] + weight(uv)
	 * 3) repeat above step. If we still find shortest path for any dist[V] that means we have negative cycle
	 * 		report it as negative cycle
	 */
	
	// The main function that finds shortest distances from src
    // to all other vertices using Bellman-Ford algorithm.  The
    // function also detects negative weight cycle
    void BellmanFord(Graph graph,int src)
    {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];
 
        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i=0; i<V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
 
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i=1; i<V; ++i)
        {
            for (int j=0; j<E; ++j)
            {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u]!=Integer.MAX_VALUE &&
                    dist[u]+weight<dist[v])
                    dist[v]=dist[u]+weight;
            }
        }
 
        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle.
        for (int j=0; j<E; ++j)
        {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u]!=Integer.MAX_VALUE &&
                dist[u]+weight<dist[v])
              System.out.println("Graph contains negative weight cycle");
        }
    }
    
    /* Optimal binary search tree
     * Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the number of searches to keys[i]. 
     * Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
		Let us first define the cost of a BST. The cost of a BST node is level of that node multiplied by its frequency. Level of root is 1.
     */
    
    int optCost(int freq[], int i, int j)
    {
       // Base cases
       if (j < i)      // If there are no elements in this subarray
         return 0;
       if (j == i)     // If there is one element in this subarray
         return freq[i];
     
       // Get sum of freq[i], freq[i+1], ... freq[j]
       int fsum = sum(freq, i, j);
     
       // Initialize minimum value
       int min = Integer.MAX_VALUE;
     
       // One by one consider all elements as root and recursively find cost
       // of the BST, compare the cost with min and update min if needed
       for (int r = i; r <= j; ++r)
       {
           int cost = optCost(freq, i, r-1) + optCost(freq, r+1, j);
           if (cost < min)
              min = cost;
       }
     
       // Return minimum value
       return min + fsum;
    }
    
    // A utility function to get sum of array elements freq[i] to freq[j]
    int sum(int freq[], int i, int j)
    {
        int s = 0;
        for (int k = i; k <=j; k++)
           s += freq[k];
        return s;
    }

    /* Subset sum problem
     * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
     * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9 output: true 
     * Solution is exactly same to isSubsetSum method above for partition
     */
    
	 // Returns true if there is a subset of set[] with sum
	 // equal to given sum
	public boolean isItSubsetSum(int set[], int n, int sum)
	{
	   // Base Cases
	   if (sum == 0)
	     return true;
	   if (n == 0 && sum != 0)
	     return false;
	  
	   // If last element is greater than sum, then ignore it
	   if (set[n-1] > sum)
	     return isSubsetSum(set, n-1, sum);
	  
	   /* else, check if sum can be obtained by any of the following
	      (a) including the last element
	      (b) excluding the last element   */
	   return isSubsetSum(set, n-1, sum) || 
	                               isSubsetSum(set, n-1, sum-set[n-1]);
	}
	
	/* count all possible paths from top left to bottom right of mXn matrix
	 */
	public int numberOfPaths(int mat[][]){
		int r = mat.length;
		int c = mat[0].length;
		//create 2D array to store result of subproblems
		int count[][] = new int[r][c];
		
		//count of path to reach any cell in first column is 1 
		for (int i=0; i<r; i++){
			count[i][0] = 1;
		}
		
		//count of path to reach any cell in first row is 1
		for (int j=0; j<c; j++){
			count[0][j] = 1;
		}
		
		//calculate count of paths from other cells in bottom up manner
		for (int i=1; i<r; i++) {
			for (int j=1; j<c; j++) {
				count[i][j] = count[i-1][j] + count[i][j-1];
				//if diagonal movements are allowed same calculation becomes
				//count[i][j] = count[i-1][j] + count[i][j-1] + count [i-1][j-1];
			}
		}
		return count[r-1][c-1];
	}
	
	/* Count number of ways to reach given score in a game
	 * allowed moves are 3, 5, 10
	 */
	public int count(int n){
		//table[i] will store count of ways for value i
		int table[] = new int[n+1];
		//initialize table array 
		for (int i=0; i<table.length; i++)
			table[i] = 0;
		//base case - if n =0 
		table[0] =1;
		//one by one consider given 3 moves and update table[] after the index >= value picked
		for (int i=3; i<n; i++)
			table[i] += table[i-3];    //table[i-value] because move of value - possible values till last move
		for (int i=5; i<n; i++)
			table[i] += table[i-5];
		for (int i=10 ; i<n; i++)
			table[i] += table[i-10];
		
		return table[n];
		
	}
	
	/*Recursively break a number in 3 parts to get maximum sum
	 * Given a number n, we can divide it in only three parts n/2, n/3 and n/4 (we will consider only integer part). 
	 */
	
	public int breakSum(int n){
        //since this problem has overlapping problem we will use dynamic programming array to store results
		int dp[] = new int[n+1];
		//base condition
		dp[0] = 0; dp[1] = 1;
		
		for (int i=2; i<n; i++){
			dp[i] = Math.max(dp[i/2] +dp[i/3] + dp[i/4], i);
		}
		
		return dp[n];
	}
	
	/* Count number of ways to partition set into k subsets
	 */
	
	// Returns count of different partitions of n
	// elements in k subsets
	public int countP(int n, int k)
	{
	  // Table to store results of subproblems
	  int dp[][] = new int[n+1][k+1];
	 
	  // Base cases
	  for (int i=0; i<=n; i++)
	     dp[i][0] = 0;
	  for (int i=0; i<=k; i++)
	     dp[0][k] = 0;
	 
	  // Fill rest of the entries in dp[][]
	  // in bottom up manner
	  for (int i=1; i<=n; i++)
	     for (int j=1; j<=i; j++)
	       if (j == 1 || i == j)
	          dp[i][j] = 1;
	       else
	          dp[i][j] = j*dp[i-1][j] + dp[i-1][j-1];
	 
	  return dp[n][k];
	}
	
	/* Dice Throw
	 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. 
	 * X is the summation of values on each face when all the dice are thrown
	 */
	
	// The main function that returns number of ways to get sum 'x'
	// with 'n' dice and 'm' with m faces.
	public int findWays(int m, int n, int x)
	{
	    // Create a table to store results of subproblems.  One extra 
	    // row and column are used for simpilicity (Number of dice
	    // is directly used as row index and sum is directly used
	    // as column index).  The entries in 0th row and 0th column
	    // are never used.
	    int table[][] = new int[n + 1][x + 1];
	    // memset(table, 0, sizeof(table)); // Initialize all entries as 0
	 
	    // Table entries for only one dice
	    for (int j = 1; j <= m && j <= x; j++)
	        table[1][j] = 1;
	 
	    // Fill rest of the entries in table using recursive relation
	    // i: number of dice, j: sum
	    for (int i = 2; i <= n; i++)
	        for (int j = 1; j <= x; j++)
	            for (int k = 1; k <= m && k < j; k++)
	                table[i][j] += table[i-1][j-k];
	 
	    /* Uncomment these lines to see content of table
	    for (int i = 0; i <= n; i++)
	    {
	      for (int j = 0; j <= x; j++)
	        cout << table[i][j] << " ";
	      cout << endl;
	    } */
	    return table[n][x];
	}
	
	/* Optimal strategy for game
	 *  Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent by alternating turns. 
	 *  In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin. 
	 *  Determine the maximum possible amount of money we can definitely win if we move first
	 */
	
	public int optimalStrategyOfGame(int arr[], int n){
		
		int table[][] = new int[n][n];
		int gap, x, y, z, i, j;
		
		for(gap=0; gap<n; ++gap){
			for (i=0, j=gap; j<n; ++i, ++j){
				x = ((i+2) <=j) ? table[i+2][j] : 0;
				y = ((i+1) <=(j-1)) ? table[i+1][j-1] : 0;
				z = (i <= (j-2)) ? table[i][j-2] : 0;
				table[i][j] = Math.max(arr[i]+Math.min(x, y), arr[j]+Math.min(y, z));
			}
		}
		return table[0][n-1];
	}
	
	/*Word break problem
	 * Given an input string and a dictionary of words, 
	 * find out if the input string can be segmented into a space-separated sequence of dictionary words
	 */
	
	public boolean dictionaryContains(String word)
	{
	    String dictionary[] = {"mobile","samsung","sam","sung","man","mango",
	                           "icecream","and","go","i","like","ice","cream"};
	    int size = dictionary.length;
	    for (int i = 0; i < size; i++)
	        if (dictionary[i].equalsIgnoreCase(word))
	           return true;
	    return false;
	}
	
	// Returns true if string can be segmented into space separated
	// words, otherwise returns false
	public boolean wordBreak(String str)
	{
	    int size = str.length();
	    if (size == 0)   return true;
	 
	    // Create the DP table to store results of subroblems. The value wb[i]
	    // will be true if str[0..i-1] can be segmented into dictionary words,
	    // otherwise false.
	    boolean wb[] = new boolean[size+1] ;
	    //memset(wb, 0, wb.length); // Initialize all values as false.
	 
	    for (int i=1; i<=size; i++)
	    {
	        // if wb[i] is false, then check if current prefix can make it true.
	        // Current prefix is "str.substr(0, i)"
	        if (wb[i] == false && dictionaryContains( str.substring(0, i) ))
	            wb[i] = true;
	 
	        // wb[i] is true, then check for all substrings starting from
	        // (i+1)th character and store their results.
	        if (wb[i] == true)
	        {
	            // If we reached the last prefix
	            if (i == size)
	                return true;
	 
	            for (int j = i+1; j <= size; j++)
	            {
	                // Update wb[j] if it is false and can be updated
	                // Note the parameter passed to dictionaryContains() is
	                // substring starting from index 'i' and length 'j-i'
	                if (wb[j] == false && dictionaryContains( str.substring(i, j-i) ))
	                    wb[j] = true;
	 
	                // If we reached the last character
	                if (j == size && wb[j] == true)
	                    return true;
	            }
	        }
	    }
	 
	    /* Uncomment these lines to print DP table "wb[]"
	     for (int i = 1; i <= size; i++)
	        system.out.println("wb[i]="+wb[i]); */
	 
	    // If we have tried all prefixes and none of them worked
	    return false;
	}
	
	/* Maximum profit by buying and selling a share at most twice
	 */
	
	public int maxProfite(int price[], int n){
		
		//create profite array and intialize it to 0
		int profit[] = new int[n];
		for (int i=0; i<n; i++){
			profit[i] = 0;
		}
		
		/*get maximum profit with only one transaction */
		int max_price = price[n-1];
		for (int i=n-2; i>=0; i++){
			if (price[i] > max_price)
				max_price = price[i];
			
			//maximum of previous max, profit by buying at price[i] and selling at max_price
			profit[i] = Math.max(profit[i+1], max_price-price[i]);
		}
		
		/* get maximum profit with 2 transactions allowed */
		int min_price = price[0];
		for (int i=1; i<n; i++){
			if (price[i]<min_price)
				min_price = price[i];
			//maximum of previous maximum, (Buy, Sell) at (min_price, price[i]) and 
			//add profit of other trans. stored in profit[i]
			profit[i] = Math.max(profit[i-1], profit[i] + (price[i]-min_price));
		}
		return profit[n-1]; 
	}
	
	/* Longest Repeating Subsequence
	 * Given a string, find length of the longest repeating subseequence such that 
	 * the two subsequence don’t have same string character at same position,
	 */
	
	public int findLongestRepeatingSubSeq(String str)
	{
	    int n = str.length();
	 
	    // Create and initialize DP table
	    int dp[][] = new int[n+1][n+1];
	    for (int i=0; i<=n; i++)
	        for (int j=0; j<=n; j++)
	            dp[i][j] = 0;
	 
	    // Fill dp table (similar to LCS loops)
	    for (int i=1; i<=n; i++)
	    {
	        for (int j=1; j<=n; j++)
	        {
	            // If characters match and indexes are not same
	            if (str.charAt(i-1) == str.charAt(j-1) && i!=j)
	                dp[i][j] =  1 + dp[i-1][j-1];                               
	            // If characters do not match
	            else
	                dp[i][j] = max(dp[i][j-1], dp[i-1][j]);
	        }
	    }
	    return dp[n][n];
	}
}
