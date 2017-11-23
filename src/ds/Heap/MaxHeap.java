package ds.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeap {

	int [] heapArr;  //array representation of heap
	int capacity;  // max capacity of MinHeap
	int heapSize;  // current number of elements in heap
	
	
	public MaxHeap(int cap){
		heapSize = 0;
		capacity = cap;
		heapArr = new int[cap];
	}
	
	//return index of parent node of node at index i
	public int parent(int i){
		return (i-1)/2;
	}
	
	// to get index of left child of node at index i
	public int left(int i){
		return (2*i + 1);
	}
	
	//to get index of right child of node at index i
	public int right(int i){
		return (2*i + 2);
	}
	
	// Returns the minimum key (key at root) from min heap
	public int getMax(){
		return heapArr[0];
	}
	
	//a utility function to swap 2 elemnts 
	public void swap(int x, int y){
		int temp = x;
		x = y;
		y = temp;
	}
	
	//insert new key 'k'
	public void insertKey(int k){
		
		if (heapSize == capacity){
			System.out.println("could not insert key");
			return;
		}
		
		//first insert new key at end
		heapSize++;
		int i = heapSize -1;
		heapArr[i] = k;
		
		//fix min heap property if it is voilated
		while (i!=0 && heapArr[parent(i)] < heapArr[i]) {
			swap(heapArr[i], heapArr[parent(i)]);
			i = parent(i);
		}
	}
	
	//decrease value of key at index 'i' to newValue. It is assumed that newValue is smaller that heapArr[i]
	public void decreaseKey(int i, int newValue){
		heapArr[i] = newValue;
		
		while (i!=0 && heapArr[parent(i)] < heapArr[i]){
			swap(heapArr[i], heapArr[parent(i)]);
			i = parent(i);
		}
	}
	
	//method to remove minimum element (or root) from min heap
	public int extractMax(){
		
		if (heapSize<=0)
			return Integer.MAX_VALUE;
		
		if (heapSize==1){
			heapSize--;
			return heapArr[0];
		}
		
		//store max value and remove it from heap
		int root = heapArr[0];
		heapArr[0] = heapArr[heapSize-1];
		heapSize--;
		MaxHeapify(0);
		
		return root;
	}
	
	//This function deletes key at index i. It first reduced value to minus
	//infinite, then calls extractMin()
	public void deleteKey(int i){
		decreaseKey(i, Integer.MIN_VALUE);
		extractMax();
	}
	
	// A recursive method to heapify a subtree with root at given index
	// This method assumes that the subtrees are already heapified
	public void MaxHeapify(int i){
		int l = left(i);
	    int r = right(i);
	    int largest = i;
	    if (l < heapSize && heapArr[l] > heapArr[i])
	        largest = l;
	    if (r < heapSize && heapArr[r] > heapArr[largest])
	        largest = r;
	    if (largest != i)
	    {
	        swap(heapArr[i], heapArr[largest]);
	        MaxHeapify(largest);
	    }
	}
	
	/* We can also use Max Heap for finding the k’th smallest element. Following is algorithm.
1) Build a Max-Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)

2) For each element, after the k’th element (arr[k] to arr[n-1]), compare it with root of MH.
……a) If the element is less than the root then make it root and call heapify for MH
……b) Else ignore it.
// The step 2 is O((n-k)*logk)

3) Finally, root of the MH is the kth smallest element. 
Time complexity of this solution is O(k + (n-k)*Logk)*/
	public int findKthSmallest(int [] arr, int k){
		
		if (arr == null || arr.length == 0)
			return Integer.MIN_VALUE;
		
		int i;
		//build max heap for first k
		for (i = 0; i < k; i++){
			insertKey(arr[i]);
	    }
		
		while ( i < arr.length) {
			
			if (arr[i] < heapArr[0]){
				heapArr[0] = arr[i];
				MaxHeapify(0);
						
			}
		}
		
		return heapArr[0];
				
	}
	
	/* to use priority queue to create maxheap use comparator 
	 * 
	 */
	private int defaultSize;
	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(defaultSize, new Comparator<Integer>() {
	    public int compare(Integer lhs, Integer rhs) {
	        if (lhs < rhs) return +1;
	        if(lhs>rhs) return -1;
            return 0;
	    }
	});
	
}
