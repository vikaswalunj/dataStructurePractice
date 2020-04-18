package ds.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Functions {
	
	/* Assume you have an array of length n initialized with all 0's and are given k update operations.
	Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
	Return the modified array after all k operations were executed.
	 */
	public int[] getModifiedArray(int length, int[][] updates) {
	    int result[] = new int[length];
	    if(updates==null || updates.length==0)
	        return result;
	 
	    //sort updates by starting index
	    Arrays.sort(updates, Comparator.comparingInt(a -> a[0]));
	 
	    ArrayList<int[]> list = new ArrayList<int[]>();
	 
	    //create a heap sorted by ending index
	    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.comparingInt(a -> updates[a][1]));
	 
	    int sum=0;
	    int j=0;
	    for(int i=0; i<length; i++){
	        //substract value from sum when ending index is reached
	        while(!queue.isEmpty() && updates[queue.peek()][1] < i){
	            int top = queue.poll();
	            sum -= updates[top][2];    
	        }
	 
	        //add value to sum when starting index is reached
	        while(j<updates.length && updates[j][0] <= i){
	           sum = sum+updates[j][2];
	           queue.offer(j);
	           j++;
	        }
	 
	        result[i]=sum;    
	    }
	 
	    return result;
	}
	
	/* merge k sorted arrays
	 * 
	 */
	
	public static int[] mergeKSortedArray(int[][] arr) {
		//PriorityQueue is heap in Java 
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
		int total=0;
 
		//add arrays to heap
		for (int i = 0; i < arr.length; i++) {
			queue.add(new ArrayContainer(arr[i], 0));
			total = total + arr[i].length;
		}
 
		int m=0;
		int result[] = new int[total];
 
		//while heap is not empty
		while(!queue.isEmpty()){
			ArrayContainer ac = queue.poll();
			result[m++]=ac.arr[ac.index];
 
			if(ac.index < ac.arr.length-1){
				queue.add(new ArrayContainer(ac.arr, ac.index+1));
			}
		}
 
		return result;
	}
	
	
}

class ArrayContainer implements Comparable<ArrayContainer> {
	int[] arr;
	int index;
 
	public ArrayContainer(int[] arr, int index) {
		this.arr = arr;
		this.index = index;
	}
 
	@Override
	public int compareTo(ArrayContainer o) {
		return this.arr[this.index] - o.arr[o.index];
	}
}

