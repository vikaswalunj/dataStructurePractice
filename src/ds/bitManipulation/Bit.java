package ds.bitManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Bit {

	/* Given an array of integers, every element appears two times except for one. Find that single one
	 * 
	 */
	public int singleNumber(int[] A){
		int x =0;
		for (int a: A){
			System.out.println("a=" +a);
			x = x^a;
			System.out.println("x=" +x);
		}
		return x;
	}
	
	/* Given an array of integers, every element appears three times except for one. Find that single one
	 * 
	 */
	public int singleNo(int[] A) {
	    int ones = 0, twos = 0, threes = 0;
	    for (int i = 0; i < A.length; i++) {
	        twos |= ones & A[i];
	        ones ^= A[i];
	        threes = ones & twos;
	        ones &= ~threes;
	        twos &= ~threes;
	    }
	    return ones;
	}
	
	/* Get maximum binary Gap.
	 * e.g. 9's binary form is 1001, the gap is 2.
	 */
	public static int getGap(int N) {
		int max = 0;
		int count = -1;
		int r = 0;
	 
		while (N > 0) {
			// get right most bit & shift right
			r = N & 1;
			N = N >> 1;
	 
			if (0 == r && count >= 0) {
				count++;
			}
	 
			if (1 == r) {
				max = count > max ? count : max;
				count = 0;
			}
		}
	 
		return max;
	}
	
	/* Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
		For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
	 */
	public int hammingWeight(int n) {
	    int count = 0;
	    for(int i=1; i<33; i++){
	        if(getBit(n, i) == true){
	            count++;
	        }
	    }
	    return count;
	}
	 
	public boolean getBit(int n, int i){
	    return (n & (1 << i)) != 0;
	}
	
	/* Reverse bits of a given 32 bits unsigned integer
	 * given input 43261596 (represented in binary as 00000010100101000001111010011100), 
	 * return 964176192 (represented in binary as 00111001011110000010100101000000).
	 */
	public int reverseBits(int n) {
		for (int i = 0; i < 16; i++) {
			n = swapBits(n, i, 32 - i - 1);
		}
	 
		return n;
	}
	 
	public int swapBits(int n, int i, int j) {
		int a = (n >> i) & 1;
		int b = (n >> j) & 1;
	 
		if ((a ^ b) != 0) {
			return n ^= (1 << i) | (1 << j);
		}
	 
		return n;
	}
	
	/* Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule
	 * given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", return: ["AAAAACCCCC", "CCCCCAAAAA"].
	 */
	public List<String> findRepeatedDnaSequences(String s) {
		ArrayList<String> result = new ArrayList<String>();
	 
		int len = s.length();
		if (len < 10) {
			return result;
		}
	 
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
	 
		HashSet<Integer> temp = new HashSet<Integer>();
		HashSet<Integer> added = new HashSet<Integer>();
	 
		int hash = 0;
		for (int i = 0; i < len; i++) {
			if (i < 9) {
				//each ACGT fit 2 bits, so left shift 2
				hash = (hash << 2) + map.get(s.charAt(i)); 
			} else {
				hash = (hash << 2) + map.get(s.charAt(i));
				//make length of hash to be 20
				hash = hash &  (1 << 20) - 1; 
	 
				if (temp.contains(hash) && !added.contains(hash)) {
					result.add(s.substring(i - 9, i + 1));
					added.add(hash); //track added
				} else {
					temp.add(hash);
				}
			}
	 
		}
	 
		return result;
	}
	
	/*Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range
	 * given the range [5, 7], you should return 4
	 */
	
	public int rangeBitwiseAnd(int m, int n) {
	     while (n > m) {
	          n = n & n - 1;
	     }
	     return m & n;
	}
	
	/* Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
	 * 
	 */
	
	public int getSum(int a, int b) {
		 
		   while(b!=0){
		       int c = a&b;
		       a=a^b;
		       b=c<<1;
		   }
		 
		   return a;
		}
	
	/* Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
	 * calculate the number of 1's in their binary representation and return them as an array.
	 * For num = 5 you should return [0,1,1,2,1,2].
	 */
	
	public int[] countBits(int num) {
	    int[] result = new int[num+1];
	 
	    int p = 1; //p tracks the index for number x
	    int pow = 1;
	    for(int i=1; i<=num; i++){
	        if(i==pow){
	            result[i] = 1;
	            pow <<= 1;
	            p = 1;
	        }else{
	            result[i] = result[p]+1;
	            p++;
	        }
	 
	    }
	 
	    return result;
	}
	
	/* Given a non-negative integer n representing the total number of bits in the code, 
	 * print the sequence of gray code. A gray code sequence must begin with 0
	 * given n = 2, return [0,1,3,2]. Its gray code sequence is: 00 - 0, 01 - 1, 11 - 3, 10 - 2
	 */
	public List<Integer> grayCode(int n) {
	    if(n==0){
	        List<Integer> result = new ArrayList<Integer>();
	        result.add(0);
	        return result;
	    }
	 
	    List<Integer> result = grayCode(n-1);
	    int numToAdd = 1<<(n-1);
	 
	    for(int i=result.size()-1; i>=0; i--){
	        result.add(numToAdd+result.get(i));
	    }
	 
	    return result;
	}
	
	
	
	public static void main (String[] args){
		
		int [] A = {4,4,5,6,6};
		Bit b = new Bit();
		int result = b.singleNumber(A);
		System.out.println(result);
	}
}
