package ds.Matrix;

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
	 * Count no of rectagles in given matrix when rectangle is represented by 'X' and atleast one line 
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
	
}
