package al.DivideAndConq;

public class Functions {
	
	/* calculate power(x,y) - Note int y (not unsigned int) so it will allow negative values as well
	 * we memoize value of power(x,y/2) in temp so that time complexity becomes O(Logn)
	 */
	public float power(float x, int y)
	{
	    float temp;
	    if( y == 0)
	       return 1;
	    temp = power(x, y/2);       
	    if (y%2 == 0)  // if y is even and greater than 0
	        return temp*temp;
	    else
	    {
	        if(y > 0) //if y is odd and greater than 0
	            return x*temp*temp;
	        else       // if y is less than 0
	            return (temp*temp)/x;
	    }
	}  

	/* Median of two sorted arrays
	 * There are two ways to get it. 1) Simply count while merging. When we reached element n(of of 2n) we found our median.
	 * Time complexity of that will be O(n).
	 * If you want to get it in O(LogN) follow below method.
	 */
	/* This function returns median of ar1[] and ar2[].
	   Assumptions in this function:
	   Both ar1[] and ar2[] are sorted arrays
	   Both have n elements */
	public static float getMedian2(int[] a1,int[] a2){
		int end = a1.length-1;
		return _getMedian2(a1,0,end,a2,0,end);
	}
 
	private static float _getMedian2(int[] a1, int s1,int e1, int[] a2,int s2, int e2){
		//System.out.println("("+a1[s1]+","+a1[e1]+") to ("+a2[s2]+","+a2[e2]+")");
		int len = e1-s1+1;
		if(len <= 0)
			return -1;
		if(len == 1)
			return (a1[s1]+a2[s2])/2.0f;
		if(len == 2)
			return (Math.max(a1[s1],a2[s2])+Math.min(a1[e1],a2[e2]))/2.0f;
		
		float m1 = getM(a1,s1,e1); /* get the median of the first array */
		float m2 = getM(a2,s2,e2); /* get the median of the second array */
		
		/* If medians are equal then return either m1 or m2 */
		if(m1 == m2)
			return m1;
		
		/* if m1 < m2 then median must exist in ar1[m1....] and
        ar2[....m2] */
		if(m1 < m2){
			if(len%2 == 0)
				return _getMedian2(a1,s1+(len/2),e1, a2,s2,s2+(len/2)-1);
			return _getMedian2(a1,s1+(len/2),e1, a2,s2,s2+(len/2));
		}
 
		/* if m1 > m2 then median must exist in ar1[....m1] and
        ar2[m2...] */
		if(len%2 == 0)
			return _getMedian2(a1,s1,s1+(len/2)-1, a2,s2+(len/2),e2);
		return _getMedian2(a1,s1,s1+(len/2), a2,s2+(len/2),e2);
	}
	
	private static float getM(int[] arr,int s,int e){
		int len = e-s+1;
		int i=len/2;
		if(len%2 == 0){
			return (arr[i]+arr[i+1])/2.0f;
		}
		return arr[i];
	}
	
	/* Count Inversions in an array
	 * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
	 * e.g. The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
	 */
	
	//O(n^2) implementation
	 public static int getInvCount(int arr[], int n)
	    {
	      int inv_count = 0;
	      for (int i = 0; i < n - 1; i++)
	        for (int j = i+1; j < n; j++)
	          if (arr[i] > arr[j])
	            inv_count++;
	      
	      return inv_count;
	    }
	 
	 //enhanced merge sort implementation - time complexity O(nLogn)
	  /* This method sorts the input array and returns the
     number of inversions in the array */
	  public static int mergeSort(int arr[], int array_size)
	  {
	      int temp[] = new int[array_size];
	      return _mergeSort(arr, temp, 0, array_size - 1);
	  }
	    
	  /* An auxiliary recursive method that sorts the input array and
	    returns the number of inversions in the array. */
	  public static int _mergeSort(int arr[], int temp[], int left, int right)
	  {
	    int mid, inv_count = 0;
	    if (right > left)
	    {
	      /* Divide the array into two parts and call _mergeSortAndCountInv()
	         for each of the parts */
	      mid = (right + left)/2;
	    
	      /* Inversion count will be sum of inversions in left-part, right-part
	        and number of inversions in merging */
	      inv_count  = _mergeSort(arr, temp, left, mid);
	      inv_count += _mergeSort(arr, temp, mid+1, right);
	    
	      /*Merge the two parts*/
	      inv_count += merge(arr, temp, left, mid+1, right);
	    }
	    return inv_count;
	  }
	    
	  /* This method merges two sorted arrays and returns inversion count in
	     the arrays.*/
	  public static int merge(int arr[], int temp[], int left, int mid, int right)
	  {
	    int i, j, k;
	    int inv_count = 0;
	    
	    i = left; /* i is index for left subarray*/
	    j = mid;  /* j is index for right subarray*/
	    k = left; /* k is index for resultant merged subarray*/
	    while ((i <= mid - 1) && (j <= right))
	    {
	      if (arr[i] <= arr[j])
	      {
	        temp[k++] = arr[i++];
	      }
	      else
	      {
	        temp[k++] = arr[j++];
	    
	       /*this is tricky -- see above explanation/diagram for merge()*/
	        inv_count = inv_count + (mid - i);
	      }
	    }
	    
	    /* Copy the remaining elements of left subarray
	     (if there are any) to temp*/
	    while (i <= mid - 1)
	      temp[k++] = arr[i++];
	    
	    /* Copy the remaining elements of right subarray
	     (if there are any) to temp*/
	    while (j <= right)
	      temp[k++] = arr[j++];
	    
	    /*Copy back the merged elements to original array*/
	    for (i=left; i <= right; i++)
	      arr[i] = temp[i];
	    
	    return inv_count;
	  }
	 
	  /*
	   * 
	   */
	  
	// A recursive function to find the smallest distance. The array P contains
	// all points sorted according to x coordinate
	float closestUtil(Point P[], int n)
	{
	    // If there are 2 or 3 points, then use brute force
	    if (n <= 3)
	        return bruteForce(P, n);
	 
	    // Find the middle point
	    int mid = n/2;
	    Point midPoint = P[mid];
	 
	    // Consider the vertical line passing through the middle point
	    // calculate the smallest distance dl on left of middle point and
	    // dr on right side
	    float dl = closestUtil(P, mid);
	    float dr = closestUtil(P + mid, n-mid);
	 
	    // Find the smaller of two distances
	    float d = Math.min(dl, dr);
	 
	    // Build an array strip[] that contains points close (closer than d)
	    // to the line passing through the middle point
	    Point strip[] = new Point[n];
	    int j = 0;
	    for (int i = 0; i < n; i++)
	        if (Math.abs(P[i].x - midPoint.x) < d)
	            strip[j] = P[i], j++;
	 
	    // Find the closest points in strip.  Return the minimum of d and closest
	    // distance is strip[]
	    return min(d, stripClosest(strip, j, d) );
	}
	 
	// The main functin that finds the smallest distance
	// This method mainly uses closestUtil()
	float closest(Point P[], int n)
	{
	    qsort(P, n, sizeof(Point), compareX);
	 
	    // Use recursive function closestUtil() to find the smallest distance
	    return closestUtil(P, n);
	}
}
