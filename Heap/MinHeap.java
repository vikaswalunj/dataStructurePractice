package ds.Heap;

public class MinHeap {

	int [] heapArr;  //array representation of heap
	int capacity;  // max capacity of MinHeap
	int heapSize;  // current number of elements in heap
	
	public MinHeap(int cap){
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
	public int getMin(){
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
		while (i!=0 && heapArr[parent(i)] > heapArr[i]) {
			swap(heapArr[i], heapArr[parent(i)]);
			i = parent(i);
		}
	}
	
	//decrease value of key at index 'i' to newValue. It is assumed that newValue is smaller that heapArr[i]
	public void decreaseKey(int i, int newValue){
		heapArr[i] = newValue;
		
		while (i!=0 && heapArr[parent(i)] > heapArr[i]){
			swap(heapArr[i], heapArr[parent(i)]);
			i = parent(i);
		}
	}
	
	//method to remove minimum element (or root) from min heap
	public int extractMin(){
		
		if (heapSize<=0)
			return Integer.MAX_VALUE;
		
		if (heapSize==1){
			heapSize--;
			return heapArr[0];
		}
		
		//store min value and remove it from heap
		int root = heapArr[0];
		heapArr[0] = heapArr[heapSize-1];
		heapSize--;
		MinHeapify(0);
		
		return root;
	}
	
	//This function deletes key at index i. It first reduced value to minus
	//infinite, then calls extractMin()
	public void deleteKey(int i){
		decreaseKey(i, Integer.MIN_VALUE);
		extractMin();
	}
	
	// A recursive method to heapify a subtree with root at given index
	// This method assumes that the subtrees are already heapified
	public void MinHeapify(int i){
		int l = left(i);
	    int r = right(i);
	    int smallest = i;
	    if (l < heapSize && heapArr[l] < heapArr[i])
	        smallest = l;
	    if (r < heapSize && heapArr[r] < heapArr[smallest])
	        smallest = r;
	    if (smallest != i)
	    {
	        swap(heapArr[i], heapArr[smallest]);
	        MinHeapify(smallest);
	    }
	}
	
}
