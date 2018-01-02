package ds.bitManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
/*
OR (|)	AND (&)		XOR (^)		Left Shift (<<)		Right Shift (>>)	Not (~)
1|0=1	1&0=0		1^0=1		0010<<2=1000		1100>>2=0011		~1=0
 0 - false
 1 - true
XOR means one of them needs to be true and other false
<< 1 = means 'multiply by 2' e.g. 10(1010) << 1(0001)  = 20 (10100)
>> 1 = means 'divide by 2'   e.g. 10(1010) >> 1(0001) = 5 (0101). 
to check uncommon bits we use XOR ^. e.g. 4(0100) ^ 2(0010) = 5(0110)
& -> change bits when number is anded with 0 e.g. 0(0000) & 2(0010) = 0(0000)  
Tip - when you wan to check i'th bit right shift number by i (n >> i) then AND result with 1 ( & 1). Result will be value of bit.
*/
public class Bit {

	/* Given an array of integers, every element appears two times except for one. Find that single one
	 * 2,2,3,4,4 - (0)0000 ^ (2)0001 = (2)0001 ^ (2)0001 =(0) 0000 ^ (3) 0011 = (3) 0011 ^ (4) 0100 =  (7)0111 ^ (4) 0100 = (3) 0011
	 * output is 0011 which is 3
	 * 2,3,4,4,2 - (0)0000 ^ (2)0001 = (2)0001 ^ (3)0011 =(2) 0010 ^ (4) 0100 = (6) 0110 ^ (4) 0100 =  (2)0010 ^ (2) 0010 = (0) 0000
	 * output is 0000 which is 0  
	 * So this doesn't work for unsorted array. Only sorted array will work
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
	 * count no of zeroes between two 1's by counting no of zeroes at last bit.
	 */
	public static int getGap(int N) {
		int max = 0;
		int count = -1;
		int r = 0;
	 
		System.out.println("result=" +Integer.toBinaryString(0 & 2));
		while (N > 0) {
			// get right most bit & shift right
			r = N & 1;
			System.out.println("r =" +Integer.toBinaryString(r));
			N = N >> 1;
			System.out.println("N =" +Integer.toBinaryString(N));
	 
			if (0 == r && count >= 0) {
				count++;
				System.out.println("count incremented to=" +count);
			}
	 
			if (1 == r) {
				max = count > max ? count : max;
				System.out.println("max=" +max);
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
		System.out.println("a=" +Integer.toBinaryString(a));
		int b = (n >> j) & 1;
		System.out.println("b=" +Integer.toBinaryString(b));
	 
		if ((a ^ b) != 0) {
			System.out.println("a^b != 0");
			int temp = (1 << i) | (1 << j);
			return n ^= temp;
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
		int result = Bit.getGap(9);
		System.out.println(result);
	}
}
