package ds.ArrayString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class ArrayString {
	
	
	/*Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
	The input string does not contain leading or trailing spaces and the words are always separated by a single space.
	For example,
	Given s = "the sky is blue",
	return "blue is sky the".*/
	
	public static String stringReverse(String string) {
		if(string == null) {
		return "";
		}

		StringBuilder stringBuilder = new StringBuilder("");

		for(int i = string.length() - 1; i >= 0; i--) {
		if(Character.isWhitespace(string.charAt(i))) {
		stringBuilder.append(string.substring(i + 1, string.length() - stringBuilder.length())).append(" ");;
		}
		}

		stringBuilder.append(string.substring(0, string.length() - stringBuilder.length()));
		return stringBuilder.toString();
	}
	//another way to reverse string
	public static void ReverseString(String s) {
		String[] split = s.split(" ");
		StringBuilder sb = new StringBuilder();

		for (int i = split.length - 1; i >= 0; i--) {
			sb.append(split[i] + " ");
		}
		System.out.println(sb.toString());
	}
	
	/*
	 * evaluate reverse polish notation 
	 * evaluate mathematical expression which contains operators(+,-,/,*) and operands (numbers)
	 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  		["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
	 * iterate through passed string. When operand(number) occurs push it to stack, when operator occurs
	 * pop first two elements from stack and compute. push result to stack again.  
	 */

	public int evalRPN(String[] s){
		int result = 0;
		
		String operators = "+-/*";
		
		Stack<String> stack = new Stack<String>();
		
		for(String e: s){
			if (!operators.contains(e)){
				stack.push(e);
			} else {
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());
				//switch statement allows string only JDK >= 1.7. 
				//For JDK < 1.7 we need to use indexOf function of String on e and use them in Switch statement 
				switch (e){
				case "+" : 
					stack.push(String.valueOf(a+b));
					break;
				case "-" :
					stack.push(String.valueOf(a-b));
					break;
				case "/" :
					stack.push(String.valueOf(a/b));
					break;
				case "*" :
					stack.push(String.valueOf(a*b));
					break;
				}
			}
		}
		
		result = Integer.valueOf(stack.pop());
		return result;
	}
	
	/*
	 * check if given two strings are IsoMorphic - Strings are called isomorphic if characters in one string
	 * can be replaced by characters in another. e.g. 'add' and 'egg' are isomorphic
	 * Use map. put characters from 2 string as key and value pair. 
	 */
	
	public boolean isomorphic(String str1, String str2){
		HashMap<Character, Character> cMap = new HashMap<Character, Character>();
		
		for (int i =0; i<str1.length(); i++){
			char charStr1 = str1.charAt(i);
			char charStr2 = str2.charAt(i);
			if (cMap.containsKey(charStr1)) {
				if (cMap.get(charStr1) != charStr2) 
						return false;
			} else { 
				if (cMap.containsValue(charStr2))
					return false;
				cMap.put(charStr1, charStr2);
			}
		}
		
		return true;
	}
	
	/*
	 * Word Ladder - find shortest transformation sequence from source/start word to target/end word
	 * by going through words in dictionary. Changes between two words should be 1 char only
	 * e.g. start = 'hot' end = 'cog' dictionary - ['cot', 'mot', 'dog', 'cog']
	 */
	
	public int shortestLengthChain(String source, String target, HashSet<String> dic){
		//source and target should be non null and non space
		if (source == null || source == " ")
			return 0;
		if (target == null || target == " ")
			return 0;
		
		//create queue of word node where we can push matching words from dictionary
		LinkedList<WordNode> queue = new LinkedList<WordNode>();
		WordNode wd = new WordNode(source, 0);
		//push source word to queue
		queue.push(wd);
		
		while (!queue.isEmpty()){
			
			//pop front word form queue
			WordNode node = queue.pop();
			
			/* if this is our target word return no of steps from this wordnode as result*/
			if (node.word.equals(target)){
				return node.stepNum;
			}
			
			char[] arr = node.word.toCharArray();
			for (int i=0; i<arr.length ; i++){
				/* replace each character in in word(arr) and check if formed word is in dictionary
				   if it is in dictionary remove that from dictionary and add formed word to queue*/
				for (char c='a'; c <'z'; c++) {
					//add current character to temp so that it can be replaced back later
					char temp = arr[i];
					arr[i] = c;
					
					if (dic.contains(arr.toString())){
						queue.add(new WordNode(arr.toString(), node.stepNum+1));
						dic.remove(arr.toString());
					}
					arr[i] = temp;
				}
						
			}
			
		}
		return 0;
	}
	
	/*Given an array of integers, find two numbers such that they add up to a specific target number.
	  The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
	  Input: numbers={2, 7, 11, 15}, target=9
		Output: index1=0, index2=1	*/	

	public int[] twoSum(int[] nums, int target) {
	    if(nums==null || nums.length<2)
	        return new int[]{0,0};
	    //HashMap<currentValue from array, index of number(target-currentValue)>
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for(int i=0; i<nums.length; i++){
	        if(map.containsKey(nums[i])){
	        	//return {index of partner value, current index}
	            return new int[]{map.get(nums[i]), i};
	        }else{
	        	//add difference (target-currentValue and current index)
	            map.put(target-nums[i], i);
	        }
	    }
	 
	    return new int[]{0,0};
	}
	
	// One more approach will be two pointers on sorted array(one from start and another from end, if sum > target -> end--, if sum < target -> start++)
	
	/*
	 * 3Sum - find 3 integers whos sum will be almost equal to target = 1
	 */
	
	public int threeSumClosest(int[] nums, int target){
		int min = Integer.MAX_VALUE;
		int result = 0;
		
		Arrays.sort(nums);
		
		for (int i=1; i<nums.length; i++){
			int j= i+1;
			int k = nums.length-1;
			
			while (j<k){
				int sum = nums[i]+nums[j]+nums[k];
				int diff = Math.abs(sum-target);
				
				if (diff == 0)
					return sum;
				
				if (diff < min) {
					min = diff;
					result = sum;
				}
				
				if (sum <= target) {
					j++ ;
				} else {
					k--;
				}
			}
		}
		
		return result;
	}
	
	/*
	 * Wildcard matching/REgular expression matching - string and pattern matching 
	 * '?'  - can replace single character, '*' - can replace any sequence of characters
	 * Loop through string 
	 * 		if indexed character in string matches with same indexed pattern character increment index for both string and pattern
	 * 		if pattarn.char = * 
	 */
	
	public boolean isMatch(String s, String p){
		
		if (s == null || p == null)
			return false;
		
		int i = 0;  // iterator for string
		int j = 0;  // iterator for pattern 
		int strIndex = -1;
		int patternIndex = -1;
		
		while (i<s.length()){
//			System.out.println("string char="+s.charAt(i)+" pattern="+p.charAt(j));
			if (j<p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				System.out.println("first if loop= i:"+i+" j:"+j);
				i++;
				j++;
			} else if ((patternIndex != -1) && (j<p.length()) && (p.charAt(j) == '*')){
				System.out.println("second if loop= i:"+i+" j:"+j);
				System.out.println("       startindex= "+patternIndex+" iIndex="+strIndex);
				j = patternIndex +1;
				i = strIndex+1;
				strIndex++;
			} else if ((j<p.length()) && (p.charAt(j) == '*')) {
				System.out.println("Third if loop= i:"+i+" j:"+j);
				System.out.println("       startindex= "+patternIndex+" iIndex="+strIndex);
				patternIndex = j;
				strIndex = i;
				j++;
			} else {
				System.out.println("return false");
				return false;
			}
		}
		
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		
		return j == p.length();
	}
	
	/*
	 * Merge Interval
	 *  Given [1,3],[2,6],[8,10],[15,18] return [1,6],[8,10],[15,18] 
	 */
	
	public List<Interval> merge(List<Interval> intervals) {
	    List<Interval> result = new ArrayList<Interval>();
	 
	    if(intervals==null||intervals.size()==0)
	        return result;
	 
	    Collections.sort(intervals, new Comparator<Interval>(){
	        public int compare(Interval i1, Interval i2){
	            if(i1.start!=i2.start)
	                return i1.start-i2.start;
	            else
	                return i1.end-i2.end;
	        }
	    });
	 
	    Interval pre = intervals.get(0);
	    for(int i=0; i<intervals.size(); i++){
	        Interval curr = intervals.get(i);
	        if(curr.start>pre.end){
	            result.add(pre);
	            pre = curr;
	        }else{
	            Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
	            pre = merged;
	        }
	    }
	    result.add(pre);
	 
	    return result;
	}
	
	/* Implement atoi to convert a string to an integer.
	 * 
	 */
	
	public int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
	 
		// trim white spaces
		str = str.trim();
	 
		char flag = '+';
	 
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// use double to store result
		double result = 0;
	 
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
	 
		if (flag == '-')
			result = -result;
	 
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
	 
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
	 
		return (int) result;
	}
	
	/* Given two sorted integer arrays A and B, merge B into A as one sorted array.
	 * If allowed to take one more array as result space complexity will increase but you can start merging from start
	 * since here we are not allowed, as B merged INTO A, we need to start merging from end so, A.length becomes A.length + B.length
	 */
	
	    public void merge(int A[], int m, int B[], int n) {
	 
	        while(m > 0 && n > 0){
	            if(A[m-1] > B[n-1]){
	                A[m+n-1] = A[m-1];
	                m--;
	            }else{
	                A[m+n-1] = B[n-1];
	                n--;
	            }
	        }
	        
	        /* if anything remaining in B after merge above move that to A. 
	         * We dont have to do the same for A as result itself is A */
	        while(n > 0){
	            A[m+n-1] = B[n-1];
	            n--;
	        }
	    }
	
	    /* Merge two sorted arrays with O(1) extra space
	     * Input: ar1[] = {10};
       			  ar2[] = {2, 3};
			Output: ar1[] = {2}
        			ar2[] = {3, 10}
	     */
	    static void merge(int[] arr1, int[] arr2,int m, int n)
	    {
	        // Iterate through all elements of ar2[] starting from
	        // the last element
	        for (int i=n-1; i>=0; i--)
	        {
	            /* Find the smallest element in first array greater than ar2[i]. Move all
	               elements one position ahead till the smallest greater
	               element is not found */
	            int j, last = arr1[m-1];
	            for (j=m-2; j >= 0 && arr1[j] > arr2[i]; j--)
	                arr1[j+1] = arr1[j];
	      
	            // If there was a greater element
	            if (j != m-2 || last > arr2[i])
	            {
	                arr1[j+1] = arr2[i];
	                arr2[i] = last;
	            }
	        }
	    }
	    
	    
	/*
	 * find longest valid sub String/parenthesis - when string has '(' or ')' characters only
	 */
	
	public void test(){
		ArrayString as = new ArrayString();
		String input = new String(); // null
		input = ""; // empty
		as.longestValidSubString(input);
	}
	public int longestValidSubString(String str)
	{
		
		if (str == null || str.isEmpty())
			return 0;
		
	    int n = str.length();
	 
	    // Create a stack and push -1 as initial index to it.
	    Stack<Integer> stk = new Stack<Integer>();
	    stk.push(-1);
	 
	    // Initialize result
	    int result = 0;
	 
	    // Traverse all characters of given string
	    for (int i=0; i<n; i++)
	    {
	        // If opening bracket, push index of it
	        if (str.charAt(i) == '(')
	          stk.push(i);
	 
	        else // If closing bracket, i.e.,str[i] = ')'
	        {
	            // Pop the previous opening bracket's index
	            stk.pop();
	 
	            // Check if this length formed with base of
	            // current valid substring is more than max 
	            // so far
	            if (!stk.empty())
	                result = Math.max(result, i - stk.peek());
	 
	            // If stack is empty. push current index as 
	            // base for next valid substring (if any)
	            else stk.push(i);
	        }
	    }
	 
	    return result;
	}
	
	/*
	 * Pattern searching / KMP algorithm / implement strStr() / find needle in haystack
	 * e.g. given text = {"AABABAABABAAABAABA"} find pattern = {"AABA"}. if found return index
	 * KMP (Knuth Morris Pratt) algorithm takes O(n) time in worst case 
	 * Naive pattern search takes O(m(n-m+1))
	 * http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
	 */
	public void KMP(){
		
	}
	/*
	 * KMP is too difficult, so this is naive algorithm for pattern searching
	 * 
	 */
	
	public void search(char []pat, char []txt)
	{
		
	    int M = pat.length;
	    int N = txt.length;
	  
	    /* A loop to slide pat[] one by one */
	    for (int i = 0; i <= N - M; i++)
	    {
	        int j;
	  
	        /* For current index i, check for pattern match */
	        for (j = 0; j < M; j++)
	            if (txt[i+j] != pat[j])
	                break;
	 
	        if (j == M)  // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
	           System.out.println("Pattern found at index %d \n" + i);
	    }
	}
	
	
	/*Given an array of n positive integers and a positive integer s, find the minimal length of a sub array of which the sum ≥ s. 
	 * If there isn't one, return 0 instead.
		For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.
	*/

	public int minSubArrayLen(int s, int[] nums) {
	    if(nums==null||nums.length==0)
	        return 0;
	 
	    int start=0; 
	    int i=0;
	    int sum=0;
	 
	    int minLen = Integer.MAX_VALUE;
	 
	    while(i<nums.length){
	        if(sum<s){
	            sum += nums[i];
	            i++;        
	        }else{
	            minLen = Math.min(minLen, i-start);
	            if(start==i-1)
	                return 1;
	 
	            sum -=nums[start];
	            start++;
	        }
	    }
	 
	    while(sum>=s){
	        minLen = Math.min(minLen, i-start);
	 
	        sum -=nums[start++];
	    }
	 
	    return minLen==Integer.MAX_VALUE? 0: minLen;
	}
	
	/*
	 * Find Longest Consecutive sequence in unsorted array in O(n) time
	 * We can not sort as sorting takes O(nLogn) time 
	 * If loops are not nested each loop will take O(n) time. so total may be O(no of loops(constant) * n) ~ O(n) 
	 */
	
	public int longestConSeq(int[] num) {
		
		if (num==null)
			return 0;
		
		int max = 1;
		
		//create hash set to store all elements from array 
		HashSet<Integer> set = new HashSet<Integer>();
		
		//add all integers from array in hashset
		for (int e: num)
			set.add(e);
		
		//for each integer in array search set for its predecessor and successor
		for(int curr: num) {
			int left = curr - 1;
			int right = curr + 1;
			int count = 1;
			
			//search set for consecutive integers less than current integer  
			while(set.contains(left)){
				count++;
				set.remove(left); // we should not remove element from set as it might be needed for second element from integer array 
				left = left - 1;
			}
			//search set for consecutive integers greater than current integer
			while(set.contains(right)){
				count++;
				set.remove(right); // we should not remove element from set as it might be needed for second element from integer array
				right = right + 1;
			}
			
			max = Math.max(max, count);
		}
		return max;
	}
	
	/*
	 * validate if given string is valid palindrome
	 * it can also be solved using stack. - 1) replaceAll function as given. 2) divide remaining string in 2 and add first half to stack. 
	 * 3) validate rest of elements are equal to stack.pop(). If not return false  
	 * Given below is 2 pointer solution (start and end). 
	 */
	public static boolean isValidPalindrome(String s){
		if(s==null||s.length()==0) return false;
 
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); // removes everything but a-zA-Z0-9 then change remaining to lower case
		System.out.println(s);
 
		for(int i = 0; i <= s.length()/2 ; i++){
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
				return false;
			}
		}
 
		return true;
	}
	
	/*
	 * print zigzag conversation with given no of rows 
	 * 
	 */
	
	public String printZigZagConcat(String str, int rows){
		
		//if passed string is null return null
		if (str==null)
			return null;
		
		//if no of rows is 1 then pass string as it is
		if (rows == 1)
			return str;
		//create arraylist of string for storing strings in rows
		String arr[] = new String[rows];
		
		int currRow = 0;
		//initiate direction of rows to down
		boolean down = true;
		//traverse through string and assign each char to one of string in array
		for (int i=0; i<str.length(); i++){
			//append current character to string of currentRow in resultArray
			arr[currRow] += str.charAt(i);
			
			//if last row is reached change direction to up
			if (currRow == (rows-1))
				down = false;
			
			//if first row is reached change direction to down
			if(currRow == 0)
				down = true;
			
			//based on flag change row appropriately
			if (down)
				currRow++;
			else 
				currRow--;
		}
		
		return arr.toString();
	}
	
	/*
	 * longest substring without repeating characters
	 * e.g.  input- "abcabcbb" output - "abc" because 'a' at position 4 is repeating character
	 */
	
	public int lengthOfLongestSubstring(String s) {
	    if(s==null || s.length()==0){
	        return 0;
	    }
	 
	    int start=0;
	    int max = 0;
	 
	    HashSet<Character> set = new HashSet<Character>();
	    for(int i=0; i<s.length(); i++){
	        char c = s.charAt(i);
	        System.out.println("character working:" +c);
	        if(!set.contains(c)){
	            set.add(c);
	            System.out.println("in if add char to set:" +c);
	            max = Math.max(max, i-start+1);
	            System.out.println("max in if:" +max);
	        }else{
	            for(int j=start; j<i; j++){
	                set.remove(s.charAt(j));
	                System.out.println("character removed from set:" +s.charAt(j));	
	                if(s.charAt(j)==c){
	                    start=j+1;
	                    System.out.println("start becomes:" +start);
	                    break;    
	                }
	            }        
	 
	            set.add(c);
	            System.out.println("in else part add char to set:" +c);
	        }
	    }
	    System.out.println("final max:" +max);
	    return max;
	}
	
	/*
	 * Length of longest substring with at the most K Distinct characters
	 * For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb"
	 */
	
	public int lengthOfLongestSubstringKDistinct(String s, int k){
		
		if (s==null)
			return 0;
		
		//Initialize  max (result) and start index for 2nd loop
		int max = 0;
		int start = 0;
		//create hashmap for storing characters of string and its count 
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		//Iterate over string to process each character
		for (int i=0; i<s.length(); i++){
			char c = s.charAt(i);
			//if character already present in map increase its counter else put character in map
			if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
			
			//if map has more than K distinct characters calculate max and remove characters from map till size becomes <=k
			if (map.size()>k) {
				max = Math.max(max, i-start);
				//remove character from map by iterating over input string from start position  
				while (map.size()>k) {
					char t = s.charAt(start);
					int count = map.get(t);
					if (count>1){
						map.put(t, count-1);    //reduced count of character for current occurrence
					} else {
						map.remove(t);
					}
					start++;
				}
			}
				
		}
		//calculate max by length subtracting last starting index form full length of string
		max = Math.max(max, s.length()-start);
		return max;
	}
	
	/*
	 * find minimum element in sorted and rotated array
	 * minimum element will be only element who's previous element is higher than that. If there is no previous element higher then first element is minimum element 
	 * here low and high are indexes so low = 0 and high = arr.length() -1.
	 */
	
	public int findMin(int arr[], int low, int high){
		
		//this condition will handle no minimum element hence first is minimum element
		if (arr[low] > arr[high])
			return arr[low];
		
		//if there is only one element left then return that element 
		if (high == low)
			return arr[low];
		
		//calculate mid
		int mid = (high + low)/2;
		
		//check if arr[mid+1] is minimum element
		if ((high > mid) && (arr[mid] > arr[mid+1]))
			return arr[mid+1];
		
		//check if mid element is minimum element
		if ((mid > low)&&(arr[mid] < arr[mid-1]))
			return arr[mid];
		
		/* decide whether we need to go right or left
		 * If middle element is smaller than last element, then the minimum element lies in left half
		 * Else minimum element lies in right half.
		 */
		if (arr[high] > arr[mid])
			return findMin(arr, low, mid-1);
		
		return findMin(arr, mid+1, high);
		
	}
	
	/*
	 * find largest rectangle area in histogram where width is 1 for each strip and array of height is input
	 * For example, given height = [2,1,5,6,2,3], return 10
	 */
	public int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
	 
		Stack<Integer> stack = new Stack<Integer>();
	 
		int max = 0;
		int i = 0;
	 
		while (i < height.length) {
			//push index to stack when the current height is larger than the previous one
			if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
				stack.push(i);
				i++;
			} else {
			//calculate max value when the current height is less than the previous one
				int p = stack.pop();
				int h = height[p];
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				max = Math.max(h * w, max);
			}
	 
		}
	 
		while (!stack.isEmpty()) {
			int p = stack.pop();
			int h = height[p];
			int w = stack.isEmpty() ? i : i - stack.peek() - 1;
			max = Math.max(h * w, max);
		}
	 
		return max;
	}
	
	/*
	 * find longest common prefix in given array of strings. 
	 * process is 
	 * 1) get minimum length string as starting point to match no of characters in other strings
	 * 2) start from first string to match these characters till last string in array. At any place where character is not matched minimum LCP is found
	 * e.g. Input  : {“geeksforgeeks”, “geeks”, “geek”, “geezer”}
	 *		Output : "gee"
	 */
	
	public String longestCommonPrefix(String[] strs){
		if (strs==null || strs.length ==0)
			return "";
		
		//if just one element is passed return that as LCP
		if (strs.length ==1)
			return strs[0];
		
		int min = Integer.MAX_VALUE;
		
		//find minimum length string from array which can be used as minimum length of characters to match
		for (String st: strs){
			if (st.length() < min)
				min = st.length();
		}
		
		//check one by one all characters of all strings in array. At any point character doesn't match substring becomes LCP 
		for (int i=0; i<min; i++) {
			for (int j=0; j<strs.length-1; j++) {
				String s1 = strs[j];
				String s2 = strs[j+1];
				
				if (s1.charAt(i) != s2.charAt(i))
					return s1.substring(0, i);
				
			}
		}
		//if all minimum length characters matched in loop above return those characters from first string 
		return strs[0].substring(0, min);
	}
	
	/*
	 * Simplify Path - Unix style path
	 *  e.g. 
	 *  path = "/home/", => "/home"
	 *	path = "/a/./b/../../c/", => "/c"
	 *	path = "/../", => "/"
	 *	path = "/home//foo/", => "/home/foo"
	 */
	
	public String simplifyPath(String path) {
	    Stack<String> stack = new Stack<String>();
	 
	    //stack.push(path.substring(0,1));
	 
	    while(path.length()> 0 && path.charAt(path.length()-1) =='/'){
	        path = path.substring(0, path.length()-1);
	    }
	 
	    int start = 0;
	    for(int i=1; i<path.length(); i++){
	        if(path.charAt(i) == '/'){
	            stack.push(path.substring(start, i));
	            start = i;
	        }else if(i==path.length()-1){
	            stack.push(path.substring(start));
	        }
	    }
	 
	    LinkedList<String> result = new LinkedList<String>();
	    int back = 0;
	    while(!stack.isEmpty()){
	        String top = stack.pop();
	 
	        if(top.equals("/.") || top.equals("/")){
	            //nothing
	        }else if(top.equals("/..")){
	            back++;
	        }else{
	            if(back > 0){
	                back--;
	            }else{
	                result.push(top);
	            }
	        }
	    }
	 
	    //if empty, return "/"
	    if(result.isEmpty()){
	        return "/";
	    }
	 
	    StringBuilder sb = new StringBuilder();
	    while(!result.isEmpty()){
	        String s = result.pop();
	        sb.append(s);
	    }
	 
	    return sb.toString();
	}
	
	
	/*
	 * Gas station problem - can we complete circle from one gas station to another
	 * requirement is gas[i] > cost[i]
	 * 1) if the sum of gas >= the sum of cost, then the circle can be completed.
	 * 2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.
	 */
	
	public int canCompleteCircle(int[] gas, int[] cost){
		
		int sumRemaining = 0; //total current remaining
		int total = 0; //total remaining
		int start = 0; //resulting index of starting gas station
		
		for (int i=0; i<gas.length; i++){
			
			int remaining = gas[i] - cost[i];
			//if sumremaining of i-1 > 0 continue
			//i.e. if there was some gas left from previous add this remaining of gas to that 
			if(sumRemaining>0) {
				sumRemaining += remaining;
			} else {
				//otherwise mark this gas station as starting station and mark current remaining/left gas
				sumRemaining = remaining;
				start = i;
			}
			total += remaining;	
		}
		
		if(total>0){
			return start;
		}else{
		return -1;
		}	
	}
	
	/*																1
	 * print pascal triangle. 									1		1
	 * entries in line = line's no							1		2		1
	 * entry in line is binomial coefficient			1		3		3		1
	 * 												1		4		6		4		1			
	 */	
	// here n is no of rows 
	public void printPascal(int n){
		
		for (int line=0; line<n; line++){
			//each line has no of entries equal to line no. i.e. line.length = line. 
			for (int i=0; i<line; ++i){
				System.out.println(binomialCoeff(line, i));
			}
				
		}
	}
	/* 
	 * if (line == i || i == 0)
           arr[line][i] = 1;
           or arraylist.get(line).add(1)
      else // Other values are sum of values just above and left of above
           arr[line][i] = arr[line-1][i-1] + arr[line-1][i];
           or arraylist.get(line).add(arraylist.get(line-1).get(i-1) + arraylist.get(line-1).get(i))
      printf("%d ", arr[line][i]);
	 */
	/*
	 * method to get binomial coefficient for passed line no and entry in line
	 */
	//here n is line no and k is index/entry in that line 
	public int binomialCoeff(int n, int k){
		
		int res = 1;
		if (k > (n-k)){
			k = n - k;
		}
		for (int i=0; i<k; i++){
			res *= (n-i);
			res /= (i+1);
		}
		return res;
	}
	
	/*
	 * count and say
	 * 1, 11, 21, 1211, 111221, ...
	 * 1 is read off as "one 1" or 11.
	 * 11 is read off as "two 1s" or 21.
	 * 21 is read off as "one 2, then one 1" or 1211.
	 */
	
	public String countAndSay(int n){
		if (n<=0)
			return null;
		
		String result = "1";
		
		for (int i=1; i<n; i++){
			StringBuilder sb = new StringBuilder();
			int count = 1;
			
			for (int j=1; j<result.length(); j++){
				if (result.charAt(j) == result.charAt(j-1)){
					count++;
				} else {
					sb.append(count);
					sb.append(result.charAt(j-1));
					count=1;
				}
			}
			
			sb.append(count);
			sb.append(result.charAt(result.length() - 1));
			result = sb.toString();
			i++;
		}
		return result;
	}
	
	/*
	 * Group anagrams
	 * - An anagram is a type of word play, the result of rearranging the letters of a word or phrase to produce a new word or phrase, 
	 * using all the original letters exactly once; for example, Torchwood can be rearranged into Doctor Who
	 * - anagram words if sorted will have same string e.g. 'cat' and 'tac' if sorted becomes 'act'  
	 */
	
	public List<List<String>> groupAnagrams(String[] strs) {
	    List<List<String>> result = new ArrayList<List<String>>();
	 
	    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    for(String str: strs){
	        char[] arr = new char[26];
	        for(int i=0; i<str.length(); i++){
	            arr[str.charAt(i)-'a']++;
	        }
	        String ns = new String(arr);
	 
	        if(map.containsKey(ns)){
	            map.get(ns).add(str);
	        }else{
	            ArrayList<String> al = new ArrayList<String>();
	            al.add(str);
	            map.put(ns, al);
	        }
	    }
	 
	    result.addAll(map.values());
	 
	    return result;
	}
	
	/*
	 * find minimum insertions for forming palindrome using recursion
	 */
	
	public int findMinInsertions(char str[], int l, int h){
		if (l > h)
			return Integer.MAX_VALUE;
		
		if (l==h)
			return 0;
		
		if (l == h-1)
			return (str[l]==str[h] ? 0:1);
		
		//find if first and last character are same. based on that we can decide which decide how to calculate min 
		return (str[l]==str[h] ? findMinInsertions(str, l+1, h-1) : 
				Math.min(findMinInsertions(str, l, h-1), findMinInsertions(str, l+1, h))+1);
	}
	
	/*
	 * get target number using list of numbers and arithmatic operations
	 * e.g. {1,2,3,4} target = 21 .. return 'true' because (1+2)*(3+4) = 21.. 
	 * since there are many combinations of trial here, this fits divide and concure (left half (first->i) and right half(i+1 -> right)) and recursive pattern
	 */
	
	public boolean isReachable(ArrayList<Integer> list, int target) {
		if ((list == null) || (list.size() == 0))
			return false;
		
		if ((list.size() == 1) && (list.get(0) == target))
			return true;
		
		int left = 0;
		int right = list.size() - 1;
		
		ArrayList<Integer> results = getResults (list, left, right, target);
		
		for (int temp: results){
			if (temp == target)
				return true;
		}
		
		return false;
	}
	
	public ArrayList<Integer> getResults(ArrayList<Integer> list, int left, int right, int target){
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		if (left>right){
			return results;
			//if there is only one element retunr that as result
		} else if (left == right) {
			results.add(list.get(left));
			return results;
		}
		
		for (int i=left; i<right; i++ ) {
			//divide and concure, recursive
			ArrayList<Integer> result1 = getResults(list, left, i, target);
			ArrayList<Integer> result2 = getResults(list, i+1, right, target);
			//try each combination of arithmatic operation to get possibility of result
			for (int x: result1) {
				for (int y: result2) {
					results.add(x+y);
					results.add(x-y);
					results.add(x*y);
					if(y!=0)
						results.add(x/y);
				}
			}
			
		}
		return results;
	}
	
	/*
	 * Amazon question - find total of throw ball
	 */
	
	public int findTotal(String[] balls, int n){
		
		int lastLastScore = 0;
		int lastScore = 0;
		int total = 0;
		
		for (String bl: balls){
			
			//if numeric score = value of integer
			if (isNumeric(bl)) {
				lastLastScore = lastScore;
				lastScore = Integer.parseInt(bl);
				total = total + lastScore;
			}
			
			//if X, score = 2 * lastScore
			if (bl == "x" || bl == "X"){
				lastLastScore = lastScore;
				lastScore = 2 * lastScore;
				total = total + lastScore;
			}
			
			//if +, score = lastScore + lastLastScore
			if (bl == "+"){
				int temp = lastLastScore;
				lastLastScore = lastScore;
				lastScore = lastScore + temp;
				total = total + lastScore;
			}
			
			//if z, score = remove last Score
			if (bl == "z" || bl == "Z"){
				total = total - lastScore;
				lastScore = lastLastScore;
			}
		}
		return total;
		
	}
	
	public boolean isNumeric(String str){
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	/*
	 * minimum path sum in triangle. adjucent rows
	 */
	
	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		int[] total = new int[triangle.size()];
		int l = triangle.size() - 1;
	 
		for (int i = 0; i < triangle.get(l).size(); i++) {
			total[i] = triangle.get(l).get(i);
		}
	 
		// iterate from last second row
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
				total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j + 1]);
			}
		}
	 
		return total[0];
	}
	
	/* Print all leaders in array - all elements on right side of leaders are smaller than leader
	 */
	/* Java Function to print leaders in an array */
    public void printLeaders(int arr[], int size)
    {
        int max_from_right =  arr[size-1];
  
        /* Rightmost element is always leader */
        System.out.print(max_from_right + " ");
      
        for (int i = size-2; i >= 0; i--)
        {
            if (max_from_right < arr[i])
            {           
            max_from_right = arr[i];
            System.out.print(max_from_right + " ");
            }
        }    
    }
    
    /* Maximum length Bitonic subarray
     * Given an array A[0 … n-1] containing n positive integers, a subarray A[i … j] is bitonic if there is a k with i <= k <= j such that A[i] <= A[i + 1] ... <= A[k] >= A[k + 1] >= .. A[j – 1] > = A[j]
     * A[] = {12, 4, 78, 90, 45, 23} - {4, 78, 90, 45, 23} - 5
     * A[] = {20, 4, 1, 2, 3, 4, 2, 10} - {1, 2, 3, 4, 2} - 5
     */
    
    public static int bitonic(int arr[], int n)
    {
        int[] inc = new int[n]; // Length of increasing subarray ending 
                                // at all indexes
        int[] dec = new int[n]; // Length of decreasing subarray starting
                                // at all indexes
        int max;
 
        // Length of increasing sequence ending at first index is 1
        inc[0] = 1;
 
        // Length of increasing sequence starting at first index is 1
        dec[n-1] = 1;
 
        // Step 1) Construct increasing sequence array
        for (int i = 1; i < n; i++)
           inc[i] = (arr[i] > arr[i-1])? inc[i-1] + 1: 1;
 
        // Step 2) Construct decreasing sequence array
        for (int i = n-2; i >= 0; i--)
            dec[i] = (arr[i] > arr[i+1])? dec[i+1] + 1: 1;
 
        // Step 3) Find the length of maximum length bitonic sequence
        max = inc[0] + dec[0] - 1;
        for (int i = 1; i < n; i++)
            if (inc[i] + dec[i] - 1 > max)
                max = inc[i] + dec[i] - 1;
 
        return max;
    }
    
    /* Minimum number of jumps to reach end
     * arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9} - 3 (1-> 3 -> 8 ->9)
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
    
    /* Celebrity problem - in party all ppl knows 1 person. But that person doesnt know anybody
     */
    
	 // Returns -1 if celebrity is not present.
	 // If present, returns id  (value from 0 to n-1).
	 int findCelebrity(int n)
	 {
	     // Handle trivial case of size = 2
	  
	     Stack<Integer> s = new Stack<Integer>();
	  
	     int C; // Celebrity
	  
	     // Push everybody to stack
	     for (int i=0; i<n; i++)
	         s.push(i);
	  
	     // Extract top 2
	     int A = s.peek();
	     s.pop();
	     int B = s.peek();
	     s.pop();
	  
	     // Find a potential celevrity
	     while (s.size() > 1)
	     {
	         if (knows(A, B))    // knows(A,B) is hypothetical function which tell us if A knows B or not
	         {
	             A = s.peek();
	             s.pop();
	         }
	         else
	         {
	             B = s.peek();
	             s.pop();
	         }
	     }
	  
	     // Potential candidate?
	     C = s.peek();
	     s.pop();
	  
	     // Last candidate was not examined, it leads
	     // one excess comparison (optimize)
	     if (knows(C, B))
	         C = B;
	  
	     if (knows(C, A))
	         C = A;
	  
	     // Check if C is actually a celebrity or not
	     for (int i = 0; i < n; i++)
	     {
	         // If any person doesn't know 'a' or 'a'
	         // doesn't know any person, return -1
	         if ( (i != C) &&
	                 (knows(C, i) || !knows(i, C)) )
	             return -1;
	     }
	  
	     return C;
	 }
 
 	public Boolean knows(int a, int b)
 	{
 		return true;   // for now just return true but this will return value from matrix M[a][b]
 	}
 	
 	/* Find a sorted subsequence of size 3 in linear time
 	 * arr[] = {12, 11, 10, 5, 6, 2, 30}- 5, 6, 30]
 	 *   smaller = [-1, -1, -1, -1, 3, -1, 5] greater = [6,6,6,6,6,6,-1]
 	 * arr[] = {1, 2, 3, 4} - 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4
 	 *  smaller = [-1, 0, 1, 2] greater = [3,3,3,-1]
 	 */
 	// A function to find a sorted subsequence of size 3
    public static void find3Numbers(int arr[])
    {
        int n = arr.length;
        int max = n-1; //Index of maximum element from right side
        int min = 0; //Index of minimum element from left side
        int i;
 
        // Create an array that will store index of a smaller
        // element on left side. If there is no smaller element
        // on left side, then smaller[i] will be -1.
        int[] smaller = new int[n];
        smaller[0] = -1;  // first entry will always be -1
        for (i = 1; i < n; i++)
        {
            if (arr[i] <= arr[min])
            {
                min = i;
                smaller[i] = -1;
            }
            else
                smaller[i] = min;
        }
 
        // Create another array that will store index of a
        // greater element on right side. If there is no greater
        // element on right side, then greater[i] will be -1.
        int[] greater = new int[n];
        greater[n-1] = -1;  // last entry will always be -1
        for (i = n-2; i >= 0; i--)
        {
            if (arr[i] >= arr[max])
            {
                max = i;
                greater[i] = -1;
            }
            else
                greater[i] = max;
        }
 
        // Now find a number which has both a greater number
        // on right side and smaller number on left side
        for (i = 0; i < n; i++)
        {
            if (smaller[i] != -1 && greater[i] != -1)
            {
                System.out.print(arr[smaller[i]]+" "+
                                 arr[i]+" "+ arr[greater[i]]);
                return;
            }
        }
 
        // If we reach here, then there are no such 3 numbers
        System.out.println("No such triplet found");
        return;
    }
    
    /* Find minimum number of merge operations to make an array palindrome */
    
    // Returns minimum number of count operations
    // required to make arr[] palindrome
    static int findMinOps(int [] arr, int n)
    {
        int ans = 0; // Initialize result
   
        // Start from two corners
        for (int i=0,j=n-1; i<=j;)
        {
            // If corner elements are same,
            // problem reduces arr[i+1..j-1]
            if (arr[i] == arr[j])
            {
                i++;
                j--;
            }
   
            // If left element is greater, then
            // we merge right two elements
            else if (arr[i] > arr[j])
            {
                // need to merge from tail.
                j--;
                arr[j] += arr[j+1] ;
                ans++;
            }
   
            // Else we merge left two elements
            else
            {
                i++;
                arr[i] += arr[i-1];
                ans++;
            }
        }
   
        return ans;
    }
    
    /* Count minimum steps to get the given desired array
     * Incremental operations:Choose 1 element from the array and increment its value by 1.
		Doubling operation: Double the values of all the elements of array.
     */
    
    // Returns count of minimum operations to covert a
    // zero array to arr array with increment and
    // doubling operations.
    // This function computes count by doing reverse
    // steps, i.e., convert arr to zero array.
    public static int countMinOperations(int [] arr, int n)
    {
        // Initialize result (Count of minimum moves)
        int result = 0;
      
        // Keep looping while all elements of arr
        // don't become 0.
        while (true)
        {
            // To store count of zeroes in current
            // arr array
            int zero_count = 0;
      
            int i;  // To find first odd element
            for (i=0; i<n; i++)
            {
                // If odd number found
                if (arr[i] % 2 == 1)
                    break;
      
                // If 0, then increment zero_count
                else if (arr[i] == 0)
                    zero_count++;
            }
      
            // All numbers are 0
            if (zero_count == n)
              return result;
      
            // All numbers are even
            if (i == n)
            {
                // Divide the whole array by 2
                // and increment result
                for (int j=0; j<n; j++)
                   arr[j] = arr[j]/2;
                result++;
            }
      
            // Make all odd numbers even by subtracting
            // one and increment result.
            for (int j=i; j<n; j++)
            {
               if (arr[j] %2 == 1)
               {
                  arr[j]--;
                  result++;
               }
            }
        }
    }
    
		    /* Find lost element from a duplicated array  */
		    
		 // Function to find missing element based on binary
		 // search approach.  arr1[] is of larger size and
		 // N is size of it.  arr1[] and arr2[] are assumed
		 // to be in same order.
		 public int findMissingUtil(int arr1[], int arr2[], int N)
		 {
		     // special case, for only element which is
		     // missing in second array
		     if (N == 1)
		         return arr1[0];
		  
		     // special case, for first element missing
		     if (arr1[0] != arr2[0])
		         return arr1[0];
		  
		     // Initialize current corner points
		     int lo = 0,  hi = N - 1;
		  
		     // loop until lo < hi
		     while (lo < hi)
		     {
		         int mid = (lo + hi) / 2;
		  
		         // If element at mid indices are equal
		         // then go to right subarray
		         if (arr1[mid] == arr2[mid])
		             lo = mid;
		         else
		             hi = mid;
		  
		         // if lo, hi becomes contiguous,  break
		         if (lo == hi - 1)
		             break;
		     }
		  
		     // missing element will be at hi index of
		     // bigger array
		     return arr1[hi];
		 }
    
		 /* amazon question in Sonal's test 
		  */
		 
		 public void closestLocations(int totalrates, List<List<Integer>> allocations, int truckCapacity){
			 
			 List<List<Integer>> result = new ArrayList<List<Integer>>();
			 List<Integer> distance = new ArrayList<Integer>();
			 
			 HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
			 
			 for (List<Integer> list: allocations){
				 int x = list.get(0);
				 int y = list.get(1);
				 
				 hm.put((int)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), list);
				 
			 }
			 
			 Set<Entry<Integer, List<Integer>>> set = hm.entrySet();
			    //create list for those entries
			    ArrayList<Entry<Integer, List<Integer>>> list = new ArrayList<Entry<Integer, List<Integer>>>(set);
			    //sort list in descending order of frequency and ascending order of values 
			    Collections.sort(list, new Comparator<Map.Entry<Integer, List<Integer>>>() {
			        public int compare(Map.Entry<Integer, List<Integer>> o1, Map.Entry<Integer, List<Integer>> o2) {
			            return (o1.getKey()).compareTo(o2.getKey());
			        }
			    });
			    //loop through list. same way we can also loop through map.entrySet()
			    for (Entry<Integer, List<Integer>> entry : list) {
			        System.out.println(entry.getKey() + " <-> " + entry.getValue());
			    }
		 
		 }
		 
		 
		 
		 /* Sort array based on count of occurrences in ascending order of number and descending order of frequency 
		  *  e.g. input - {5,2,8,8,5,5,8,1,1,2} o/p - {5,5,5,8,8,8,1,1,2,2}
		  */
		 
			public void printFrequencySortedArray(int[] array){

				HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();

			     for (int i = 0; i < array.length; i++) {
			        if (data.containsKey(array[i])) {
			            Integer count = data.get(array[i]) + 1;
			            data.put(array[i], count);
			        } else {
			            data.put(array[i], 1);
			        }
			    }
                //get entry sets from map
			    Set<Entry<Integer, Integer>> set = data.entrySet();
			    //create list for those entries
			    ArrayList<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
			    //sort list in descending order of frequency and ascending order of values 
			    Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
			            return (o2.getValue()).compareTo(o1.getValue());
			        }
			    });
			    //loop through list. same way we can also loop through map.entrySet()
			    for (Entry<Integer, Integer> entry : list) {
			        System.out.println(entry.getKey() + " <-> " + entry.getValue());
			    }
				
				
			}
		 
		 /* Rearrange an array in maximum minimum form | Set 2 (O(1) extra space) */
 
		//Prints max at first position, min at second position
		//second max at third position, second min at fourth
		//position and so on.
		public void rearrange(int arr[], int n)
		{
		  // initialize index of first minimum and first
		  // maximum element
		  int max_idx = n-1 , min_idx = 0 ;
		
		  // store maximum element of array
		  int max_elem = arr[n-1] + 1 ;
		
		  // traverse array elements
		  for (int i=0; i < n ; i++)
		  {
		      // at even index : we have to put maximum element
		      if (i % 2 == 0)
		      {
		          arr[i] += (arr[max_idx] % max_elem ) * max_elem;
		          max_idx--;
		      }
		
		      // at odd index : we have to put minimum element
		      else
		      {
		          arr[i] += (arr[min_idx] % max_elem ) * max_elem;
		          min_idx++;
		      }
		  }
		
		  // array elements back to it's original form
		  for (int i = 0 ; i < n; i++)
		      arr[i] = arr[i] / max_elem ;
		}
		
		/* Count Strictly Increasing Subarrays
		 * The idea is based on fact that a sorted subarray of length ‘len’ adds len*(len-1)/2 to result. 
		 * For example, {10, 20, 30, 40} adds 6 to the result.
		 */
		
		public static int countIncreasing(int [] arr, int n)
	    {
	        int cnt = 0;  // Initialize result
	          
	        // Initialize length of current increasing
	        // subarray
	        int len = 1;
	      
	        // Traverse through the array
	        for (int i=0; i < n-1; ++i)
	        {
	            // If arr[i+1] is greater than arr[i],
	            // then increment length
	            if (arr[i + 1] > arr[i])
	                len++;
	                  
	            // Else Update count and reset length
	            else
	            {
	                cnt += (((len - 1) * len) / 2);
	                len = 1;
	            }
	        }
	          
	        // If last length is more than 1
	        if (len > 1)
	            cnt += (((len - 1) * len) / 2);
	      
	        return cnt;
	    }
		
		/* Longest Span with same Sum in two Binary arrays
		 * time complex - O(n), space complex - O(n)
		 */
		
		// Returns length of the longest common sum in arr1[]
	    // and arr2[]. Both are of same size n.
	    public static int longestCommonSum(int arr1[], int arr2[], int n)
	    {
	        // Initialize result
	        int maxLen = 0;
	      
	        // Initialize prefix sums of two arrays
	        int preSum1 = 0, preSum2 = 0;
	      
	        // Create an array to store staring and ending
	        // indexes of all possible diff values. diff[i]
	        // would store starting and ending points for
	        // difference "i-n"
	        int diff[] = new int[2*n+1];
	      
	        // Initialize all starting and ending values as -1.
	        for (int i = 0; i < diff.length; i++) {
	            diff[i] = -1;
	        }
	      
	        // Traverse both arrays
	        for (int i=0; i<n; i++)
	        {
	            // Update prefix sums
	            preSum1 += arr1[i];
	            preSum2 += arr2[i];
	      
	            // Comput current diff and index to be used
	            // in diff array. Note that diff can be negative
	            // and can have minimum value as -1.
	            int curr_diff = preSum1 - preSum2;
	            int diffIndex = n + curr_diff;
	      
	            // If current diff is 0, then there are same number
	            // of 1's so far in both arrays, i.e., (i+1) is
	            // maximum length.
	            if (curr_diff == 0)
	                maxLen = i+1;
	      
	            // If current diff is seen first time, then update
	            // starting index of diff.
	            else if ( diff[diffIndex] == -1)
	                diff[diffIndex] = i;
	      
	            // Current diff is already seen
	            else
	            {
	                // Find lenght of this same sum common span
	                int len = i - diff[diffIndex];
	      
	                // Update max len if needed
	                if (len > maxLen)
	                    maxLen = len;
	            }
	        }
	        return maxLen;
	    }
	    
	    /* Find the subarray with least average */
	    
	    // Prints beginning and ending indexes of subarray
	    // of size k with minimum average
	    public static void findMinAvgSubarray(int[] arr, int n, int k)
	    {
	        // k must be smaller than or equal to n
	        if (n < k)
	           return;
	      
	        // Initialize  beginning index of result
	        int res_index = 0;
	      
	        // Compute sum of first subarray of size k
	        int curr_sum = 0;
	        for (int i=0; i<k; i++)
	           curr_sum += arr[i];
	      
	        // Initialize minimum sum as current sum
	        int min_sum = curr_sum;
	      
	        // Traverse from (k+1)'th element to n'th element
	        for (int i = k; i < n; i++)
	        {
	            // Add current item and remove first item of
	            // previous subarray
	            curr_sum += arr[i] - arr[i-k];
	      
	            // Update result if needed
	            if (curr_sum < min_sum)
	            {
	                min_sum = curr_sum;
	                res_index = (i-k+1);
	            }
	        }
	      
	        System.out.println("Subarray between [" + res_index + ", "
	                              + (res_index + k - 1) + "] has minimum average");
	    }
	    
	    /* You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. 
	     * Conversely, if it's negative, move backward n steps. Determine if there is a loop in this array. 
              For example, given the array [2, -1, 1, 2, 2], index 0 maps to index 2, 1 maps to 0, 2 maps to 3, and so on. 
              There is a loop in this array because 0 maps to 2, 2 maps to 3, and 3 maps to 0 (use the modulo operator).	
	     */
	    public static boolean hasLoop(int start, int[] array){
	        BitSet db = new BitSet(array.length);     // java object which keeps values as 
	        int pos=start;
	        for(int i =0;i<array.length+1;i++){
	            if(db.get(pos)) return false;
	            db.set(pos);          // sets bit at index pos to true
	            pos = pos + array[pos];
	            pos = Math.abs(pos%array.length);   // returns absolute value of integer for taking care of - numbers
	            if (pos == start)  return true;
	        }
	        return false;
	    }
	    
	    	    
	    /* Function prints union of arr1[] and arr2[] of sorted arrays
	    m is the number of elements in arr1[]
	    n is the number of elements in arr2[] */
	    public static int printUnionOfSorted(int arr1[], int arr2[], int m, int n)
	    {
	      int i = 0, j = 0;
	      while (i < m && j < n)
	      {
	        if (arr1[i] < arr2[j])
	          System.out.print(arr1[i++]+" ");
	        else if (arr2[j] < arr1[i])
	          System.out.print(arr2[j++]+" ");
	        else
	        {
	          System.out.print(arr2[j++]+" ");
	          i++;
	        }
	      }
	      
	      /* Print remaining elements of 
	         the larger array */
	      while(i < m)
	       System.out.print(arr1[i++]+" ");
	      while(j < n)
	       System.out.print(arr2[j++]+" ");
	        
	      return 0;   
	    }
	    
	    /* Function prints Intersection of arr1[] and arr2[] which are sorted
	       m is the number of elements in arr1[]
	       n is the number of elements in arr2[] */
	    public static void printIntersectionOfSorted(int arr1[], int arr2[], int m, int n)
	    {
	      int i = 0, j = 0;
	      while (i < m && j < n)
	      {
	        if (arr1[i] < arr2[j])
	          i++;
	        else if (arr2[j] < arr1[i])
	          j++;
	        else
	        {
	          System.out.print(arr2[j++]+" ");
	          i++;
	        }
	      }
	    }
	    
	 // Prints union of arr1[0..m-1] and arr2[0..n-1]
	    public void printUnion(int arr1[], int arr2[], int m, int n) 
	    {
	        // Before finding union, make sure arr1[0..m-1] is smaller
	        if (m > n) 
	        {
	            int tempp[] = arr1;
	            arr1 = arr2;
	            arr2 = tempp;
	 
	            int temp = m;
	            m = n;
	            n = temp;
	        }
	 
	        // Now arr1[] is smaller
	        // Sort the first array and print its elements (these two
	        // steps can be swapped as order in output is not important)
	        Arrays.sort(arr1);
	        for (int i = 0; i < m; i++)
	            System.out.print(arr1[i] + " ");
	 
	        // Search every element of bigger array in smaller array
	        // and print the element if not found
	        for (int i = 0; i < n; i++) 
	        {
	            if (binarySearch(arr1, 0, m - 1, arr2[i]) == -1)
	                System.out.print(arr2[i] + " ");
	        }
	    }
	 
	    // Prints intersection of arr1[0..m-1] and arr2[0..n-1]
	    public void printIntersection(int arr1[], int arr2[], int m, int n) 
	    {
	        // Before finding intersection, make sure arr1[0..m-1] is smaller
	        if (m > n) 
	        {
	            int tempp[] = arr1;
	            arr1 = arr2;
	            arr2 = tempp;
	 
	            int temp = m;
	            m = n;
	            n = temp;
	        }
	 
	        // Now arr1[] is smaller
	        // Sort smaller array arr1[0..m-1]
	        Arrays.sort(arr1);
	 
	        // Search every element of bigger array in smaller array
	        // and print the element if found
	        for (int i = 0; i < n; i++) 
	        {
	            if (binarySearch(arr1, 0, m - 1, arr2[i]) != -1) 
	                System.out.print(arr2[i] + " ");
	        }
	    }
	 
	    // A recursive binary search function. It returns location of x in
	    // given array arr[l..r] is present, otherwise -1
	    public int binarySearch(int arr[], int l, int r, int x) 
	    {
	        if (r >= l) 
	        {
	            int mid = l + (r - l) / 2;
	 
	            // If the element is present at the middle itself
	            if (arr[mid] == x)
	                return mid;
	 
	            // If element is smaller than mid, then it can only be present
	            // in left subarray
	            if (arr[mid] > x)
	                return binarySearch(arr, l, mid - 1, x);
	 
	            // Else the element can only be present in right subarray
	            return binarySearch(arr, mid + 1, r, x);
	        }
	 
	        // We reach here when element is not present in array
	        return -1;
	    }
	    
	    /*find first character in string which is non repetitive
	     * this can be optimized further by creating object counter index(index(first occurrence), count)
	     * then use HashMap<character, counterIndex> -> loop through it
	     */
	    
	    /* even better way is maintain 2 hashmaps 
	     * 1) HashMap<Character, Integer> integer being counter
	     * 2) HashMap<Character, Integer> here integer is index at which char occured. this map will contain characters occured only once. 
	     * Sort map in ascending order of value and return first value 
	     */
	    
	      
	    /* The method returns index of first non-repeating
	       character in a string. If all characters are repeating 
	       then returns -1 */
	    public static int firstNonRepeating(String str)
	    {
	    	char count[] = new char[256];
	    	
	    	for (int i = 0; i < str.length();  i++)
	              count[str.charAt(i)]++;
	    	
	        int index = -1, i;
	      
	        for (i = 0; i < str.length();  i++)
	        {
	            if (count[str.charAt(i)] == 1)
	            {
	                index = i;
	                System.out.println(str.charAt(i));
	                break;
	            }   
	        }  
	       
	      return index;
	    }
	    
	    
    
	    /* if x is present in arr[] then returns 
	       the count of occurrences of x, 
	       otherwise returns -1. 
	       Since it is binary search time complexity is O(Log n)*/
	    static int count(int arr[], int x, int n)
	    {
	      // index of first occurrence of x in arr[0..n-1]    
	      int i; 
	       
	      // index of last occurrence of x in arr[0..n-1]
	      int j; 
	          
	      /* get the index of first occurrence of x */
	      i = first(arr, 0, n-1, x, n);
	      
	      /* If x doesn't exist in arr[] then return -1 */
	      if(i == -1)
	        return i;
	         
	      /* Else get the index of last occurrence of x. 
	         Note that we are only looking in the 
	         subarray after first occurrence */ 
	      j = last(arr, i, n-1, x, n);     
	         
	      /* return count */
	      return j-i+1;
	    }
	      
	    /* if x is present in arr[] then returns the 
	       index of FIRST occurrence of x in arr[0..n-1], 
	       otherwise returns -1 */
	    static int first(int arr[], int low, int high, int x, int n)
	    {
	      if(high >= low)
	      {
	        /*low + (high - low)/2;*/ 
	        int mid = (low + high)/2;  
	        if( ( mid == 0 || x > arr[mid-1]) && arr[mid] == x)
	          return mid;
	        else if(x > arr[mid])
	          return first(arr, (mid + 1), high, x, n);
	        else
	          return first(arr, low, (mid -1), x, n);
	      }
	      return -1;
	    }
	      
	    /* if x is present in arr[] then returns the 
	       index of LAST occurrence of x in arr[0..n-1], 
	       otherwise returns -1 */
	    static int last(int arr[], int low, int high, int x, int n)
	    {
	      if(high >= low)
	      {
	        /*low + (high - low)/2;*/     
	        int mid = (low + high)/2; 
	        if( ( mid == n-1 || x < arr[mid+1]) && arr[mid] == x )
	          return mid;
	        else if(x < arr[mid])
	          return last(arr, low, (mid -1), x, n);
	        else
	          return last(arr, (mid + 1), high, x, n);      
	      }
	      return -1;
	    }
	
	   /*
	    * Left rotate array by given number
	    * GCD - greatest common divisor
	    * TIme complexity - O(n) and space complexity O(1)
	    */
	    
	    /*Function to left rotate arr[] of siz n by d*/
	    void leftRotate(int arr[], int d, int n) 
	    {
	        int i, j, k, temp;
	        for (i = 0; i < gcd(d, n); i++) 
	        {
	            /* move i-th values of blocks */
	            temp = arr[i];
	            j = i;
	            while (1 != 0) 
	            {
	                k = j + d;
	                if (k >= n) 
	                    k = k - n;
	                if (k == i) 
	                    break;
	                arr[j] = arr[k];
	                j = k;
	            }
	            arr[j] = temp;
	        }
	    }
	    
	    /*Function to get gcd (greatest common divisor) of a and b*/
	    int gcd(int a, int b) 
	    {
	        if (b == 0)
	            return a;
	        else
	            return gcd(b, a % b);
	    }
	    
	    /*
	     * one more way to rotate array, this will handle negative by as well
	     * Time complexity is O(n)
	     */
	    
	    public static void rotate(int[] arr, int by) {
	        int n = arr.length;
	        int i = 0;
	        int j = 0;
	        int count = 0;// this variable is used to prove time complexity only
	        //if by is negative derive value for its positive direction
	        if (by <0)
	        	by = n + (by%n);
	        
	        while (i < n) {
	            int k = j;
	            int value = arr[k];
	            do {
	            	count++;
	                k = (k + by) % n;
	                int tmp = arr[k];
	                arr[k] = value;
	                value = tmp;
	                i++;
	            } while (k != j);
	            j++;
	        }
	        System.out.println("count=" +count);
	        
	        for (int v =0; v<arr.length; v++){
	        	System.out.println(arr[v]);
	        }
	    }
	    
	    /*
	     * get nth fibonacci number
	     * 
	     */
	    public static int fib(int n)
	    {
	        int a = 0, b = 1, c;
	        if (n == 0)
	            return a;
	        for (int i = 2; i <= n; i++)
	        {
	            c = a + b;
	            a = b;
	            b = c;
	        }
	        return b;
	    }
	    /* Pancake sorting problem 
	     * http://www.geeksforgeeks.org/pancake-sorting/
	     */
}