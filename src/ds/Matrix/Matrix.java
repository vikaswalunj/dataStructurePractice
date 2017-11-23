package ds.Matrix;

import java.util.HashMap;
import java.util.LinkedList;

public class Matrix {

	
	/*
	 * Print given matrix
	 */
	
	public void printMatrix(int m[][], int R, int C){
	
		for (int i=0; i < R; i++){
			for (int j=0; j < C; j++){
				System.out.println("element at i=" +i+",j=" +j+" is="+m[i][j]);
			}
		}
	}
	
	/*
	 * Maximum size square sub matrix with all 1s
	 * lets consider s[R][C] is sum matrix for m[R][C]
	 * 1) Copy first row and first columns from M[R][C] to S[R][C]
	 * 2) for other entries
	 * if m[i][j] is 1 then
	 * 		s[i][j] = min(s[i][j-1], s[i-1][j], s[i-1][j-1])+1
	 * else 
	 * 		s[i][j] = 0;
	 * 3) Find maximum entry in s[R][C]
	 * 4) using value and coordinates of maximum entry in s[i], print sub matrix of m[][]
	 */
	
	
	public void printMaxSubSquare(int m[][], int R, int C){
		int i , j;
		int s[][] = new int[R][C];
		
		/*copy first column from m[][] to s[][]*/
		for (i=0; i<R; i++){
			s[i][0] = m[i][0];
		}
		
		/*copy first row from m[][] to s[][]*/
		for (j=0; j<C; j++ ){
			s[0][j] = m [0][j];
		}
		
		/*construct other entries of*/
		for(i=1; i<R; i++)
		{
			for (j=1; j<C; j++){
				if (m[i][j] == 1) {
					s[i][j] = min(s[i][j-1], s[i-1][j], s[i-1][j-1]) + 1;
				}
			}
		}
		
		/* find maximum entry and its indexes in s[][] */
		int max_of_s = s[0][0]; 
		int max_i = 0; 
		int max_j = 0;
		for (i=0; i<R; i++){
			for (j=0; j<C; j++){
				if (max_of_s < s[i][j]){
					max_of_s = s[i][j];
					max_i = i;
					max_j = j;
				}
			}
		}
		
		/*print maximum size sub-matrix*/
		for (i= max_i; i > (max_i - max_of_s); i--) {
			for (j = max_j; j > (max_j - max_of_s); j--) {
				System.out.println("element at i=" +i+",j=" +j+" is="+m[i][j]);
			}
		}
	}
	
	public int min(int a, int b, int c) {
		if (a<b && a<c)
			return a;
		if (b<a && b<c)
			return b;
		
		return c;
	}
	
	/*
	 * inPlace (Fixed space) transpose of matrix
	 * Too difficult - try later
	 * http://www.geeksforgeeks.org/inplace-m-x-n-size-matrix-transpose/
	 */
	
	/*
	 * Print matrix diagonally 
	 * m[R][C] will always have line 'R+C-1' when printed diagonally
	 */
	public void diagonalOrder(int m[][], int R, int C){
		
		//In output no of lines will be (R+C-1) 
		for (int line=1; line<=(R+C-1); line++){
			
			/* Get column index of first element in this line of output.
			 * The index is 0 for first row lines and (line - R) for remaining */
			int start_col = Math.max(0, line - R);
			
			/* Get count of elements in this line. The count of element is equal to 
			 * minimum of line number, C-start_col and R */
			 int count = min(line, (C - start_col), R);
			
			 /*Print elements of this line*/
			 for (int j=0; j<count; j++)
				 System.out.println(m[Math.min(R, line)-j-1][start_col+j]);
		}
	}
	
	/* Matrix multiplication - O(N^3)
	 */
	
	public void multiply(int a[][], int b[][], int c[][], int n){
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				c[i][j] = 0;
				for (int k=0; k < n; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
	}
	
	/*
	 * Print matrix in spiral format/insert 'X' and '0' in spiral format
	 */
	public void spiralFormat(String a[][], int re, int ce) {
		
		int i, rs=0, cs=0;
		String insertChar = "";
		/*  rs - starting row index
        	re - ending row index
        	cs - starting column index
        	ce - ending column index
        	i - iterator
		 */
		
		while (rs<re && cs<ce){
			
			/* print first row form remaining rows */
			for (i=cs; i<ce; i++){
				System.out.println(a[rs][i]);
			//	a[k][i] = insertChar;
			}
			rs++;
			
			/* print last column from remaining columns */
			for (i=rs; i<re; i++){
				System.out.println(a[i][ce-1]);
				//a[i][n-1] = insertChar;  
			}
			ce--;
			
			/*print last row from remaining rows */
			if (rs < re){
				for (i = ce-1 ; i >= cs; i--){
					System.out.println(a[re-1][i]);
					//a[m-1][i] = insertChar;
				}
			}
			re--;
			
			/*print first column from remaining column*/
			if (cs<ce){
				for (i=re-1; i>=rs; i--){
					System.out.println(a[i][cs]);
					//a[i][l] = insertChar;
				}
			}
			cs++;
			
			//flip character for next iteration
			insertChar = ((insertChar == "0") ? "X" : "0");
		}
	}
	
	/*
	 * Print all elements in sorted order from row and column wise sorted matrix
	 * It involves 3 methods - Young Tableu algorithm
	 * 1) printSortedOrder(int m[][], int n)
	 * 2) extractMin(int m[][], int n)
	 * 3) youngify(int m[][], int r, int c)
	 */
	
	public void printSorted(int m[][], int n){
		for (int i=0; i<n*n; i++)
			System.out.println(extractMin(m, n));
	}
	
	public int extractMin(int m[][], int n){
		int ret = m[0][0];
		m[0][0] = Integer.MAX_VALUE;
		youngify(m, n, 0, 0);          // adjust matrix to put smallest element in m[0][0]
		return ret;
	}
	
	public void youngify(int m[][], int n, int r, int c){
		
		//find values at down and right side of m[r][c]
		int downVal = (r+1 < n) ? m[r+1][c] : Integer.MAX_VALUE;
		int rightVal = (c+1 < n) ? m[r][c+1] : Integer.MAX_VALUE;
		
		//if m[r][c] is the down right corner element, return
		if (downVal == Integer.MAX_VALUE && rightVal == Integer.MAX_VALUE)
			return;
		
		//move smaller of two values (downVal & rightVal) and recur for smaller value
		if (downVal < rightVal)
	    {
	        m[r][c] = downVal;
	        m[r+1][c] = Integer.MAX_VALUE;
	        youngify(m, n, r+1, c);
	    }
	    else
	    {
	        m[r][c] = rightVal;
	        m[r][c+1] = Integer.MAX_VALUE;
	        youngify(m, n, r, c+1);
	    }
	}
	
	/*
	 * Count no of rectangles in given matrix when rectangle is represented by 'X' and atleast one line 
	 * should have only 'O'
	 */
	
	public int countIslands(int m[][], int r, int c)
	{
	    int count = 0; // Initialize result
	 
	    // Traverse the input matrix
	    for (int i=0; i<r; i++)
	    {
	        for (int j=0; j<c; j++)
	        {
	            // If current cell is 'X', then check
	            // whether this is top-leftmost of a
	            // rectangle. If yes, then increment count
	            if (m[i][j] == 'X')
	            {
	                if ((i == 0 || m[i-1][j] == 'O') &&
	                    (j == 0 || m[i][j-1] == 'O'))
	                    count++;
	            }
	        }
	    }
	 
	    return count;
	}
	
	/*
	 * find the largest rectangle of 1's with swapping of columns allowed
	 * 
	 */
	
	// Returns area of the largest rectangle of 1's
	/*public int maxArea(Integer mat[][], int r, int c)
	{
	    // An auxiliary array to store count of consecutive 1's
	    // in every column.
	    public int[][] hist = new int[r+1][c+1];
	 
	    // Step 1: Fill the auxiliary array hist[][]
	    for (int i=0; i<c; i++)
	    {
	        // First row in hist[][] is copy of first row in mat[][]
	        hist[0][i] = mat[0][i];
	 
	        // Fill remaining rows of hist[][]
	        for (int j=1; j<r; j++)
	            hist[j][i] = (mat[j][i]==0)? 0: hist[j-1][i]+1;
	    }
	 
	 
	    // Step 2: Sort rows of hist[][] in non-increasing order
	    for (int i=0; i<r; i++)
	    {
	        int count[r+1] = {0};
	 
	        // counting occurrence
	        for (int j=0; j<C; j++)
	            count[hist[i][j]]++;
	 
	        //  Traverse the count array from right side
	        int col_no = 0;
	        for (int j=r; j>=0; j--)
	        {
	            if (count[j] > 0)
	            {
	                for (int k=0; k<count[j]; k++)
	                {
	                    hist[i][col_no] = j;
	                    col_no++;
	                }
	            }
	        }
	    }
	 
	    // Step 3: Traverse the sorted hist[][] to find maximum area
	    int curr_area, max_area = 0;
	    for (int i=0; i<r; i++)
	    {
	        for (int j=0; j<c; j++)
	        {
	            // Since values are in decreasing order,
	            // The area ending with cell (i, j) can
	            // be obtained by multiplying column number
	            // with value of hist[i][j]
	            curr_area = (j+1)*hist[i][j];
	            if (curr_area > max_area)
	                max_area = curr_area;
	        }
	    }
	    return max_area;
	}*/
	
	/*
	 * validity of given Tic-Tac-Toe board
	 * 1) countX == countO || countX == countO + 1
	 * 2) if o is in win condition then check 
	 *    a) if x is also in win condition  /// then it is invalid
	 *    b) if countX != countO  /// then it is invalid
	 * 3) If x is in win condition then check if countX == countO + 1. If not then it is invalid   
	 */
	
	//Combination of winning indexes on board
	public int win[][] = {{0,1,2},
					   {3,4,5},
					   {6,7,8},
					   {0,3,6},
					   {1,4,7},
					   {2,5,8},
					   {0,4,8},
					   {2,4,6}};

	//function to determine if board has 'X' or 'C' as winning character
	public Boolean isCWin(char board[], char c){
		
		for (int i=0; i<8; i++) {
			if ((board[win[i][0]] == c) &&
				(board[win[i][1]] == c)	&&
				(board[win[i][2]] == c))
				return true;
		}
		return false;
	}
	
	// Returns true if given board is valid, else returns false
	public Boolean isValid(char board[])
	{
	    // Count number of 'X' and 'O' in the given board
	    int xCount=0, oCount=0;
	    for (int i=0; i<9; i++)
	    {
	       if (board[i]=='X') xCount++;
	       if (board[i]=='O') oCount++;
	    }
	 
	    // Board can be valid only if either xCount and oCount
	    // is same or xount is one more than oCount
	    if (xCount==oCount || xCount==oCount+1)
	    {
	        // Check if 'O' is winner
	        if (isCWin(board, 'O'))
	        {
	            // Check if 'X' is also winner, then
	            // return false
	            if (isCWin(board, 'X'))
	                 return false;
	 
	            // Else return true xCount and yCount are same
	            return (xCount == oCount);
	        }
	 
	        // If 'X' wins, then count of X must be greater
	        if (isCWin(board, 'X') && xCount != oCount + 1)
	        return false;    
	 
	        // If 'O' is not winner, then return true
	        return true;
	    }
	    return false;
	}
	
	/* Boolean matrix question 
	 * Given a boolean matrix mat[M][N] of size M X N, modify it such that if a matrix cell mat[i][j] is 1 (or true) then make all the cells of ith row and jth column as 1
	algorithm 
	1) Create two temporary arrays row[M] and col[N]. Initialize all values of row[] and col[] as 0.
	2) Traverse the input matrix mat[M][N]. If you see an entry mat[i][j] as true, then mark row[i] and col[j] as true.
	3) Traverse the input matrix mat[M][N] again. For each entry mat[i][j], check the values of row[i] and col[j]. If any of the two values (row[i] or col[j]) is true, then mark mat[i][j] as true
	*/
	public void modifyMatrix(Boolean mat[][], int R, int C)
	{
	    Boolean[] row = new Boolean[R];
	    Boolean[] col = new Boolean[C];
	 
	    int i, j;
	 
	 
	    /* Initialize all values of row[] as 0 */
	    for (i = 0; i < R; i++)
	    {
	       row[i] = false;
	    }
	 
	 
	    /* Initialize all values of col[] as 0 */
	    for (i = 0; i < C; i++)
	    {
	       col[i] = false;
	    }
	 
	 
	    /* Store the rows and columns to be marked as 1 in row[] and col[]
	       arrays respectively */
	    for (i = 0; i < R; i++)
	    {
	        for (j = 0; j < C; j++)
	        {
	            if (mat[i][j] == true)
	            {
	                row[i] = true;
	                col[j] = true;
	            }
	        }
	    }
	 
	    /* Modify the input matrix mat[] using the above constructed row[] and
	       col[] arrays */
	    for (i = 0; i < R; i++)
	    {
	        for (j = 0; j < C; j++)
	        {
	            if ( row[i] == true || col[j] == true )
	            {
	                mat[i][j] = true;
	            }
	        }
	    }
	}
	
	/* print common elements from matrix 
	 * print elements which are present in all rows 
	 * time complexity is O(mn)
	 */
	
	public void printColumn(int mat[][]){
		
		//get length/no of rows of matrix
		int r = mat.length;
		//get size/ no of columns of matrix
		int c = mat[0].length;
		HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
		//put all elements of first row in map
		for (int i = 0; i<c; i++){
			mp.put(mat[0][i], 1);
		}
		//starting from second row for each element check if its present in map and value is same as no of row we are processing
		//if element is present in all rows (i==m-1) print it
		for (int i=1; i<r; i++){
			for (int j=0; j<c; j++){
				if (mp.get(mat[i][j]) == i){
					mp.put(mat[i][j], i+1);
					
					if (i==r-1)
						System.out.println(mat[i][j]);
				}

					
			}
			
		}
		
	}
	
	/* Find sum of all elements in a matrix except the elements in row and/or column of given cell?
	 * Time complexity - R*C+n(where n = arr[].length)
	 */
	
	public int printSums(int mat[][], Cell arr[], int n){
		
		int R = mat.length;
		int C = mat[0].length;
		int row[] = new int[R];
		int col[] = new int[C];
		
		int sum = 0;
		//first get sum of all elements from matrix
		for (int r = 0; r < mat.length; r++){
			
			for (int c = 0; c<mat[0].length; c++){
				sum += mat[r][c];
				
			}
		}
		
		//now deduct elements from sum which are in row or column of array 
		for (int len = 0; len <arr.length; len++){
			int ro = arr[len].R; 
			int co = arr[len].C;
			sum -= row[ro] - col[co] + mat[ro][co];
		}
		
		return sum;
	}
	
	/* Find common elements in all rows of given row-wise sorted matrix
	 */
	public int findCommonElement(int mat[][]){
		int R = mat.length;
		int C = mat[0].length;
		//an array to store indexes of current last column
		int column[] = new int[R];
		int min_row; // to store index of row whose value is more
		
		//initialise current last element for all rows. 
		int i;
		for (i=0; i<R; i++){
			column[i] = C-1;
		}
		
		min_row = 0; // lets start with first row is min row 
		
		//keep finding minimum row in current last column
		while (column[min_row]>=0){
			
			//find minimum in current last column
			for (i=0; i<R; i++){
				if (mat[i][column[i]] < mat[min_row][column[min_row]])
					min_row = i;
			}
			
			int count = 0;
			
			//traverse current last columns for all rows
			for (i = 0; i<R; i++){
				//decrease current index of row if its not equal or less than min_row 
				if (mat[i][column[i]] > mat[min_row][column[min_row]]){
					if (column[i]==0)
						return -1;
					
					column[i] -= 1; //decrease current index by 1
				}
				else 
					count++;
			}
			
			//if count == R return the value
			if (count == R)
				return mat[min_row][column[min_row]];
		}
		
		return -1;
	}
	
	/* No of paths with exactly k coins
	 * (0,0) -> (m,n)
	 * recursive function -> time complexity is exponential
	 * using dynamic programming we can memoize result in 3D array dp[m][n][k] to store result. TimeCom -> m*n*k
	 */
	
	public int pathCoins(int mat[][], int r, int c, int k){
		
		//base cases
		if (r<0 || c<0) return 0;
		if (r==0 && c==0) return (k == mat[r][c] ? 1:0);
		
		// (m,n) can be reached wither through (m-1, n) or (m, n-1) 
		return pathCoins(mat, r-1, c, k-mat[r][c]) +
				pathCoins(mat, r, c-1, k-mat[r][c]);
	}
	
	int R;
    int C;
	// dir = 0 for left, dir = 1 for facing right.  This function returns
	// number of maximum coins that can be collected starting from (i, j).
	public int maxCoinsRec(char arr[][],  int i, int j, int dir)
	{
	    R = arr.length;
	    C = arr[0].length;
		// If this is a invalid cell or if cell is a blocking cell
	    if (isValid(i,j) == false || arr[i][j] == '#')
	        return 0;
	 
	    // Check if this cell contains the coin 'C' or if its empty 'E'.
	    int result = (arr[i][j] == 'C')? 1: 0;
	 
	    // Get the maximum of two cases when you are facing right in this cell
	    if (dir == 1) // Direction is right
	       return result + Math.max(maxCoinsRec(arr, i+1, j, 0),     // Down
	                             maxCoinsRec(arr, i, j+1, 1));  // Ahead in right
	 
	    // Direction is left
	    // Get the maximum of two cases when you are facing left in this cell
	     return  result + Math.max(maxCoinsRec(arr, i+1, j, 1),    // Down
	                           maxCoinsRec(arr, i, j-1, 0));  // Ahead in left
	}
	
	// to check whether current cell is out of the grid or not
	boolean isValid(int i, int j)
	{
	    return (i >=0 && i < R && j >=0 && j < C);
	}
	
	/* SubMatrix sum queries
	 * tlr - top left row, tlc - top left column
	 * rbr - right bottom row, rbc - right bottom column
	 * This approach is good when we have only one sum query. If we have multiple sum queries then 
	 * creating auxillary array makes more sense which is explained after this 
	 */
	
	public int subMatrix(int mat[][], int tlr, int tlc, int rbr, int rbc) {
		
		int sum =0;
		//base cases
		int row = mat.length;
		int col = mat[0].length;
		if (tlr<0 || tlr>row || tlc<0 || tlc>col || rbr<0 || rbr>row || rbc<0 || rbc>col)
			return 0; 
		
		//itereate through matrix in given sub matrix and add elements one by one
		for (int R = tlr ; R<=rbr; R++) {
			for (int C = tlc; C <= rbc; C++) {
				sum += mat[R][C];
			}
		}
		
		return sum;
	}
	
	// Function to pre-process input mat[M][N].  This function
	// mainly fills aux[M][N] such that aux[i][j] stores sum
	// of elements from (0,0) to (i,j)
	public void preProcess(int mat[][], int aux[][])
	{
		int M = mat.length;
		int N = mat[0].length;
	   // Copy first row of mat[][] to aux[][]
	   for (int i=0; i<N; i++)
	      aux[0][i] = mat[0][i];
	 
	   // Do column wise sum
	   for (int i=1; i<M; i++)
	      for (int j=0; j<N; j++)
	         aux[i][j] = mat[i][j] + aux[i-1][j];
	 
	   // Do row wise sum
	   for (int i=0; i<M; i++)
	      for (int j=1; j<N; j++)
	         aux[i][j] += aux[i][j-1];
	}
	 
	// A O(1) time function to compute sum of submatrix
	// between (tli, tlj) and (rbi, rbj) using aux[][]
	// which is built by the preprocess function
	public int sumQuery(int aux[][], int tli, int tlj, int rbi,
	                                              int rbj)
	{
	    // result is now sum of elements between (0, 0) and
	    // (rbi, rbj)
	    int res = aux[rbi][rbj];
	 
	    // Remove elements between (0, 0) and (tli-1, rbj)
	    if (tli > 0)
	       res = res - aux[tli-1][rbj];
	 
	    // Remove elements between (0, 0) and (rbi, tlj-1)
	    if (tlj > 0)
	       res = res - aux[rbi][tlj-1];
	 
	    // Add aux[tli-1][tlj-1] as elements between (0, 0)
	    // and (tli-1, tlj-1) are subtracted twice
	    if (tli > 0 && tlj > 0)
	       res = res + aux[tli-1][tlj-1];
	 
	    return res;
	}
	
	/* Construct tree from ancestor matrix
	 * 
	 */
	
	// Constructs tree from ancestor matrix
	public TreeNode ancestorTree(int mat[][])
	{
		int N = mat.length;
	    // Binary array to determine weather
	    // parent is set for node i or not
	    int parent[] = new int[N];
	 
	    // Root will store the root of the constructed tree
	    TreeNode root = new TreeNode();
	 
	    // Create a multimap, sum is used as key and row
	    // numbers are used as values
	    HashMap<Integer, Integer> mm = new HashMap<Integer, Integer>();
	 
	    for (int i = 0; i < N; i++)
	    {
	        int sum = 0; // Initialize sum of this row
	        for (int j = 0; j < N; j++)
	            sum += mat[i][j];
	 
	        // insert(sum, i) pairs into the multimap
	        mm.put(sum, i);
	    }
	 
	    // node[i] will store node for i in constructed tree
	    TreeNode node[] = new TreeNode[N];
	 
	    // Traverse all entries of multimap.  Note that values
	    // are accessed in increasing order of sum
	    for (auto it = mm.begin(); it != mm.end(); ++it)
	    {
	      // create a new node for every value
	      node[it->second] = newNode(it->second);
	 
	      // To store last processed node. This node will be
	      // root after loop terminates
	      root = node[it->second];
	 
	      // if non-leaf node
	      if (it->first != 0)
	      {
	        // traverse row 'it->second' in the matrix
	        for (int i = 0; i < N; i++)
	        {
	           // if parent is not set and ancestor exits
	           if (!parent[i] && mat[it->second][i])
	           {
	             // check for unoccupied left/right node
	             // and set parent of node i
	             if (!node[it->second]->left)
	               node[it->second]->left = node[i];
	             else
	               node[it->second]->right = node[i];
	 
	             parent[i] = 1;
	           }
	        }
	      }
	    }
	    return root;
	}
	
	/* shortest path in binary maze
	 * 
	 */
	
	// function to find the shortest path between
	// a given source cell to a destination cell.
	int BFS(int mat[][], Cell src, Cell dest)
	{
	    // check source and destination cell
	    // of the matrix have value 1
	    if (mat[src.R][src.C] != 1 || mat[dest.R][dest.C] != 1)
	        return Integer.MAX_VALUE;
	 
	    boolean visited[][] = new boolean[mat.length][mat[0].length];
	    memset(visited, false, visited.length);
	     
	    // Mark the source cell as visited
	    visited[src.R][src.C] = true;
	 
	    // Create a queue for BFS
	    LinkedList<Cell> q = new LinkedList<Cell>();
	     
	    // distance of source cell is 0
	    q.push(src);  // Enqueue source cell
	 
	    // Do a BFS starting from source cell
	    while (!q.isEmpty())
	    {
	        Cell curr = q.peek();
	 
	        // If we have reached the destination cell,
	        // we are done
	        if (curr.R == dest.R && curr.C == dest.C)
	            return curr.dist;
	 
	        // Otherwise dequeue the front cell in the queue
	        // and enqueue its adjacent cells
	        q.pop();
	 
	        for (int i = 0; i < 4; i++)
	        {
	            int row = pt.x + rowNum[i];
	            int col = pt.y + colNum[i];
	             
	            // if adjacent cell is valid, has path and
	            // not visited yet, enqueue it.
	            if (isValid(row, col) && mat[row][col] && 
	               !visited[row][col])
	            {
	                // mark cell as visited and enqueue it
	                visited[row][col] = true;
	                queueNode Adjcell = { {row, col},
	                                      curr.dist + 1 };
	                q.push(Adjcell);
	            }
	        }
	    }
	 
	    //return -1 if destination cannot be reached
	    return Integer.MAX_VALUE;
	}
	
	/* Given a Boolean Matrix, find k such that all elements in k’th row are 0 and k’th column are 1.
	 * http://www.geeksforgeeks.org/find-k-such-that-all-elements-in-kth-row-are-0-and-kth-column-are-1-in-a-boolean-matrix/
	 */
	
	public int find(int arr[][])
	{
		int n = arr.length;
	    // Start from top-most rightmost corner
	    // (We could start from other corners also)
	    int r=0, c=n-1;
	 
	    // Initialize result
	    int res = -1;
	 
	    // Find the index (This loop runs at most 2n times, we either
	    // increment row number or decrement column number)
	    while (r<n && c>=0)
	    {
	        // If current element is 0, then this row may be a solution
	        if (arr[r][c] == 0)
	        {
	            // Check for all elements in this row
	            while (c >= 0 && (arr[r][c] == 0 || r == c))
	                c--;
	 
	            // If all values are 0, then store this row as result
	            if (c == -1)
	            {
	                res = r;
	                break;
	            }
	 
	            // We reach here if we found a 1 in current row, so this
	            //  row cannot be a solution, increment row number
	            else r++;
	        }
	        else // If current element is 1
	        {
	            // Check for all elements in this column
	            while (r<n && (arr[r][c] == 1 || r == c))
	                r++;
	 
	            // If all elements are 1
	            if (r == n)
	            {
	                res = c;
	                break;
	            }
	 
	            // We reach here if we found a 0 in current column, so this
	            // column cannot be a solution, increment column number
	            else c--;
	        }
	    }
	 
	    // If we could not find result in above loop, then result doesn't exist
	    if (res == -1)
	       return res;
	 
	    // Check if above computed res is valid
	    for (int ro=0; ro<n; ro++)
	       if (res != ro && arr[ro][res] != 1)
	          return -1;
	    for (int co=0; co<n; co++)
	       if (res != co && arr[res][co] != 0)
	          return -1;
	 
	    return res;
	}
	
	/* Given an n x n square matrix, find sum of all sub-squares of size k x k
	 */
	
	// A O(n^2) function to find sum of all sub-squares of size k x k
	// in a given square matrix of size n x n
	void printSumTricky(int mat[][], int k)
	{
		int n = mat.length;
	   // k must be smaller than or equal to n
	   if (k > n) return;
	 
	   // 1: PREPROCESSING
	   // To store sums of all strips of size k x 1
	   int stripSum[][] = new int[n][n];
	 
	   // Go column by column
	   for (int j=0; j<n; j++)
	   {
	       // Calculate sum of first k x 1 rectangle in this column
	       int sum = 0;
	       for (int i=0; i<k; i++)
	          sum += mat[i][j];
	       stripSum[0][j] = sum;
	 
	       // Calculate sum of remaining rectangles
	       for (int i=1; i<n-k+1; i++)
	       {
	            sum += (mat[i+k-1][j] - mat[i-1][j]);
	            stripSum[i][j] = sum;
	       }
	   }
	 
	   // 2: CALCULATE SUM of Sub-Squares using stripSum[][]
	   for (int i=0; i<n-k+1; i++)
	   {
	      // Calculate and print sum of first subsquare in this row
	      int sum = 0;
	      for (int j = 0; j<k; j++)
	           sum += stripSum[i][j];
	      		System.out.println("sum" +sum);
	      // Calculate sum of remaining squares in current row by
	      // removing the leftmost strip of previous sub-square and
	      // adding a new strip
	      for (int j=1; j<n-k+1; j++)
	      {
	         sum += (stripSum[i][j+k-1] - stripSum[i][j-1]);
	         System.out.println("sum" +sum);
	      }
	 
	   }
	}
	
	/* Find row with maximum 1's in it
	 * Time complexity of this is O(mLogn)
	 * Other way to do it is iterate through all rows and keep row number which has max 1's so far. 
	 * Time complexity for that approach will be O(m*n)
	 */
	
	// The main function that returns index of row with maximum number of 1s. 
	public int rowWithMax1s(int mat[][])
	{
		int R = mat.length;
		int C = mat[0].length;
	    int max_row_index = 0, max = -1; // Initialize max values
	 
	    // Traverse for each row and count number of 1s by finding the index 
	    // of first 1
	    int i, index;
	    for (i = 0; i < R; i++)
	    {
	       index = first (mat[i], 0, C-1);
	       if (index != -1 && C-index > max)
	       {
	           max = C - index;
	           max_row_index = i;
	       }
	    }
	 
	    return max_row_index;
	}
	
	/* A function to find the index of first index of 1 in a boolean array arr[] */
	public int first(int arr[], int low, int high)
	{
	  if(high >= low)
	  {
	    // get the middle index  
	    int mid = low + (high - low)/2; 
	 
	    // check if the element at middle index is first 1
	    if ( ( mid == 0 || arr[mid-1] == 0) && arr[mid] == 1)
	      return mid;
	 
	    // if the element is 0, recur for right side
	    else if (arr[mid] == 0)
	      return first(arr, (mid + 1), high);
	 
	    else // If element is not first 1, recur for left side
	      return first(arr, low, (mid -1));
	  }
	  return -1;
	}
	
	/* Inplace rotation of square matrix by 90 degrees in anti clockwise direction*/
	public void rotateMatrixanticlockwise(int mat[][]){
		
		int N = mat.length;
		//consider all squares one by one
		for (int r=0; r<N/2; r++) {
			//consider elements in group of 4 in current square
			for (int c=r; c<N-r-1; c++) {
				//store current cell in temp variable
				int temp = mat[r][c];
				//move values from right to top
				mat[r][c] = mat[c][N-1-r];
				//move values from bottom to right
				mat[c][N-1-r] = mat[N-1-r][N-1-c];
				//move values from left to bottom
				mat[N-1-r][N-1-c] = mat[N-1-c][r];
				//assign temp to left
				mat[N-1-c][r] = temp;
			}
		}
	}
	
	/* Rotate matrix in clockwise direction*/
	
	// A function to rotate a matrix mat[][] of size R x C.
	// Initially, m = R and n = C
	void rotateMatrixclockwise(int m, int n, int mat[][])
	{
	    int row = 0, col = 0;
	    int prev, curr;
	 
	    /*
	       row - Staring row index
	       m - ending row index
	       col - starting column index
	       n - ending column index
	       i - iterator
	    */
	    while (row < m && col < n)
	    {
	 
	        if (row + 1 == m || col + 1 == n)
	            break;
	 
	        // Store the first element of next row, this
	        // element will replace first element of current
	        // row
	        prev = mat[row + 1][col];
	 
	         /* Move elements of first row from the remaining rows */
	        for (int i = col; i < n; i++)
	        {
	            curr = mat[row][i];
	            mat[row][i] = prev;
	            prev = curr;
	        }
	        row++;
	 
	        /* Move elements of last column from the remaining columns */
	        for (int i = row; i < m; i++)
	        {
	            curr = mat[i][n-1];
	            mat[i][n-1] = prev;
	            prev = curr;
	        }
	        n--;
	 
	         /* Move elements of last row from the remaining rows */
	        if (row < m)
	        {
	            for (int i = n-1; i >= col; i--)
	            {
	                curr = mat[m-1][i];
	                mat[m-1][i] = prev;
	                prev = curr;
	            }
	        }
	        m--;
	 
	        /* Move elements of first column from the remaining rows */
	        if (col < n)
	        {
	            for (int i = m-1; i >= row; i--)
	            {
	                curr = mat[i][col];
	                mat[i][col] = prev;
	                prev = curr;
	            }
	        }
	        col++;
	    }
	 
	    // Print rotated matrix
	    for (int i=0; i<R; i++)
	    {
	        for (int j=0; j<C; j++)
	          System.out.println("mat[i][j]="+mat[i][j]) ;
	    }
	}
	 
	
}
