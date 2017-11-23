package al.Sort;

//Java program for implementation of Selection Sort
public class AllSort
{
	
	//Time complexity - Best - Average - worst - O(N^2)
	//space  complexity - O(1)
 public void selectionSort(int arr[])
 {
     int n = arr.length;

     // One by one move boundary of unsorted subarray
     for (int i = 0; i < n-1; i++)
     {
         // Find the minimum element in unsorted array
         int min_idx = i;
         for (int j = i+1; j < n; j++)
             if (arr[j] < arr[min_idx])
                 min_idx = j;

         // Swap the found minimum element with the first
         // element
         int temp = arr[min_idx];
         arr[min_idx] = arr[i];
         arr[i] = temp;
     }
 }
 
 //Time complexity - best - omega(n), average - o(n^2), worst - o(n^2)
 //Space Complexity - o(1)
 public void bubbleSort(int arr[])
 {
     int n = arr.length;
     for (int i = 0; i < n-1; i++)
         for (int j = 0; j < n-i-1; j++)
             if (arr[j] > arr[j+1])
             {
                 // swap temp and arr[i]
                 int temp = arr[j];
                 arr[j] = arr[j+1];
                 arr[j+1] = temp;
             }
 }
 
 //time complexity - best - omega(n), average - O(n^2), worst - O(n^2)
 //space complexity - best - O(1)
 /*Function to sort array using insertion sort*/
 public void insertionSort(int arr[])
 {
     int n = arr.length;
     for (int i=1; i<n; ++i)
     {
         int key = arr[i];
         int j = i-1;

         /* Move elements of arr[0..i-1], that are
            greater than key, to one position ahead
            of their current position */
         while (j>=0 && arr[j] > key)
         {
             arr[j+1] = arr[j];
             j = j-1;
         }
         arr[j+1] = key;
     }
 }
 
    //time complexity - best - Ω(n log(n)) , average - Θ(n log(n)) , worst - O(n log(n))
    //space complexity - o(n)
	//Main function that sorts arr[l..r] using
	// merge()
	public void mergeSort(int arr[], int l, int r)
	{
	   if (l < r)
	   {
	       // Find the middle point
	   int m = (l+r)/2;
	
	   // Sort first and second halves
	   mergeSort(arr, l, m);
	   mergeSort(arr , m+1, r);
	
	   // Merge the sorted halves
	       merge(arr, l, m, r);
	   }
	}


	 //Merges two subarrays of arr[].
	 // First subarray is arr[l..m]
	 // Second subarray is arr[m+1..r]
	 public void merge(int arr[], int l, int m, int r)
	 {
	     // Find sizes of two subarrays to be merged
	     int n1 = m - l + 1;
	     int n2 = r - m;
	
	     /* Create temp arrays */
	     int L[] = new int [n1];
	     int R[] = new int [n2];
	
	     /*Copy data to temp arrays*/
	     for (int i=0; i<n1; ++i)
	         L[i] = arr[l + i];
	     for (int j=0; j<n2; ++j)
	         R[j] = arr[m + 1+ j];
	
	
	     /* Merge the temp arrays */
	
	     // Initial indexes of first and second subarrays
	     int i = 0, j = 0;
	
	     // Initial index of merged sub array array
	     int k = l;
	     while (i < n1 && j < n2)
	     {
	         if (L[i] <= R[j])
	         {
	             arr[k] = L[i];
	             i++;
	         }
	         else
	         {
	             arr[k] = R[j];
	             j++;
	         }
	         k++;
	     }
	
	     /* Copy remaining elements of L[] if any */
	     while (i < n1)
	     {
	         arr[k] = L[i];
	         i++;
	         k++;
	     }
	
	     /* Copy remaining elements of R[] if any */
	     while (j < n2)
	     {
	         arr[k] = R[j];
	         j++;
	         k++;
	     }
	 }

	 
	 /* The main function that implements QuickSort()
     arr[] --> Array to be sorted,
     low  --> Starting index,
     high  --> Ending index */
	 //time complexity - best - Ω(n log(n)), average - Θ(n log(n)), worst - O(n^2)
	 //space complexity - O(log(n))
   public void quickSort(int arr[], int low, int high)
   {
       if (low < high)
       {
           /* pi is partitioning index, arr[pi] is 
             now at right place */
           int pi = partition(arr, low, high);

           // Recursively sort elements before
           // partition and after partition
           quickSort(arr, low, pi-1);
           quickSort(arr, pi+1, high);
       }
   }
   
   /* This function takes last element as pivot,
   places the pivot element at its correct
   position in sorted array, and places all
   smaller (smaller than pivot) to left of
   pivot and all greater elements to right
   of pivot */
	public int partition(int arr[], int low, int high)
	{
	    int pivot = arr[high]; 
	    int i = (low-1); // index of smaller element
	    for (int j=low; j<high; j++)
	    {
	        // If current element is smaller than or
	        // equal to pivot
	        if (arr[j] <= pivot)
	        {
	            i++;
	
	            // swap arr[i] and arr[j]
	            int temp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = temp;
	        }
	    }
	
	    // swap arr[i+1] and arr[high] (or pivot)
	    int temp = arr[i+1];
	    arr[i+1] = arr[high];
	    arr[high] = temp;
	
	    return i+1;
	}
  
}