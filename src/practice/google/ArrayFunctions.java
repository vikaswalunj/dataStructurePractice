package practice.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class ArrayFunctions {
	
	/* generate random number between 0 to n which is not present in list */
	
	public int random(int n, Set<Integer> ex) {
		
		if(n <= ex.size()) return -1;
		int num = 0;
		do {
			num = new Random().nextInt(n);
		} while(ex.contains(num));
		return num;
	}
	
	/* Balance a string with parentheses. 
	 * "a(b)" -> "a(b)"; "(((((" -> ""; "(()())" -> "(()())"; ")ab(()" -> "ab()"; etc...
	 */
	private static StringBuilder balanceParanthesis(String str) {

        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackPos = new Stack<>();

        StringBuilder output = new StringBuilder("");
        for ( int i = 0 ; i < str.length() ; i++)   {
            char curr = str.charAt(i);
            switch (curr)  {
                case '(' : stack.push('(');
                            stackPos.push(output.length());
                break;
                case ')' :
                    if ( stack.size() != 0) {
                        char ch = stack.pop();
                        int pos = stackPos.pop();
                        output.insert(pos , ch);
                        output.append(')');
                    }
                break;

                default:
                    output.append(curr);
                }
        }

        return output;
    }
	
	/* Find all the abbreviations of string: 
      eg  ABC 
          SOME Valid abbreviations are : 
          1BC, 2C, 3, A1C, AB1, A2,  
           NOT VALID 
           11C(two numbers cannot occur continuously)
	 */
 
	/*public static void main(String args[]) throws Exception {
		functions f = new functions();
		ArrayList<String> res = new ArrayList<String>();
		res = f.abbreviation("ABC");
		for(String s : res){
			System.out.println(s + " ");
		}
		
	}*/
	    
    public ArrayList<String> abbreviation(String s) {
        return rec(s);
    }

    private ArrayList<String> rec(String s) {
        if (s.length() == 1) {
            ArrayList<String> res = new ArrayList<>();
            res.add("1");
            res.add(s);
            return res;
        }
        char curr = s.charAt(0);
        ArrayList<String> prev = rec(s.substring(1));
        ArrayList<String> result = new ArrayList<>();
        for (String p : prev) {
            char first = p.charAt(0);
            if (first >= '0' && first <= '9') { // it's a number
                if (first < '9') {
                    Integer newNr = Integer.valueOf(first + "") + 1;
                    result.add(newNr + p.substring(1));
                }
                result.add(curr + p);
            } else {
                result.add(curr + p);
                result.add("1" + p);
            }
        }
        return result;
    }
    
    /* find occurance of no y in range of 1 - x where y is always single digit
     * E.g. 
		if x=25,y=2 
		function should return 9(as 22 contains two occurrences of 2) - 2,12,20,21,22,23,24,25
     */
    
    public static void main(String args[]) throws Exception {
		ArrayFunctions f = new ArrayFunctions();
		f.numOcurrences(25, 2);
	}
    
    int numOcurrences(int x, int y)
    {
        int occur = 0;
        while (x > 0) {
            int val = x;
            while (val > 0) {
                occur += (val%10 == y) ? 1:0;
                val = val / 10;
            }
            --x;
        }
        System.out.println(occur);
        return occur;
    }
    
    /* Return the pivot index of the given array of numbers. 
     * The pivot index is the index where the sum of the numbers on the left is equal to the sum of the numbers on the right. 
     * Input Array {1,2,3,4,0,6}
     */
    public static void splitArray(){
    	int[]a={1,2,3,0,6};
    	int l=0;
    	int r=a.length-1;
    	int somL = 0,somR=0;
    
    	while(l<r){
    		if(somL<somR) {
    			somL+=a[l];
    			l++;
    		}
    		else {
    			somR+=a[r];
    			r--;
    		}
    	}
    
    	System.out.println("index"+l);
    
    }
    
    /* You have L, a list containing some digits (0 to 9). Write a function answer(L) which finds the largest number 
     * that can be made from some or all of these digits and is divisible by 3. If it is not possible to make such a 
     * number, return 0 as the answer. L will contain anywhere from 1 to 9 digits. The same digit may appear multiple
     *  times in the list, but each element in the list may only be used once. 
     */
    
    public static int answer(int[] l) { 

    	// Your code goes here. 
    	ArrayList<Integer> list0 = new ArrayList<Integer>(); 
    	ArrayList<Integer> list1 = new ArrayList<Integer>(); 
    	ArrayList<Integer> list2 = new ArrayList<Integer>(); 
    	int sum =0; 
    	
    	Arrays.sort(l); 
    	
    	for(int i = 0; i<l.length; i++){ 
    		if(l[i] % 3 == 0){ 
    			list0.add(l[i]); 
    		}else if(l[i] % 3 == 1){ 
    			list1.add(l[i]); 
    		}else{ 
    			list2.add(l[i]); 
    		} 
    		sum += l[i]; 
    	} 

    	if(sum%3==0){ 
    		StringBuilder strNum = new StringBuilder(); 
    		for(int i = l.length-1; i >= 0; i--) 
    		{ 
    			strNum.append(l[i]); 
    		} 
    		return Integer.parseInt(strNum.toString()); 
    	}else if(sum%3 == 1){ 
    		if(list1.size()>0){ 
    			Collections.sort(list1); 
    			list1.remove(0); 
    		}else if(list2.size() >= 2){ 
    			Collections.sort(list2); 
    			list2.remove(1); 
    			list2.remove(0); 
    		}else{ 
    			return -1; 
    		} 
    	}else if(sum%3 == 2){ 
    		if(list2.size()>0){ 
    			Collections.sort(list2); 
    			list2.remove(0); 
    		}else if(list1.size() >= 2){ 
    			Collections.sort(list1); 
    			list1.remove(1); 
    			list1.remove(0); 
    			}else{ 
   				return -1; 
    			} 
    	} 
    	
    	list0.addAll(list1); 
    	list0.addAll(list2); 
    	StringBuilder strNum = new StringBuilder(); 
    	Collections.sort(list0); 
    	for(int i = list0.size()-1; i >= 0; i--) 
    	{ 
    		strNum.append(list0.get(i)); 
    	} 
    	return strNum.length() > 0 ? Integer.parseInt(strNum.toString()) : -1; 
    } 
    
    /* find maximum words can be created if given array of words length and max no of characters in sentence
     * https://careercup.com/question?id=5639859738771456
     *  "I love chicken", I can break the number of characters in each word, like so: [1] [4] [7] [1,4] [4,7], [1,4,7]. 
     */
    
    public static int longestPhraseWith(int[] input, int max) {
        if (input == null || input.length == 0 || max <= 0) {
            return 0;
        }

        int i = 0;
        while (input[i] > max) {
            i++;
        }

        if (i >= input.length) {
            return 0;
        }

        int words = 1, maxWords = 1;
        int leftIndex = i;
        int sum = input[i];

        for (int j = i + 1; j < input.length; j++) {
            sum += input[j];
            words++;
            if (sum <= max) {
                maxWords = Math.max(maxWords, words);
            }
            else {
                while (sum > max && leftIndex <= j) {
                    sum -= input[leftIndex++];
                    words--;
                }
            }
        }

        return maxWords;
    }

    /*  check if given matrix is Toepliz matrix. 
     * A matrix is "Toepliz" if each descending diagonal from left to right is constant
     */
    
    public static boolean checkToepliz(int[][] matrix){
		for(int i=0; i<matrix.length-1; i++){
			for(int j=0; j<matrix[0].length-1; j++){
				if(matrix[i][j] != matrix[i+1][j+1]){
					return false;
				}
			}
		}
		return true;
	}
    
    /* Add two integer array 
     * e.g. for example, 1) [1,2,3] + [2,3,4] = [3,5,7]   2)  [1,2,3] + [2,3,5,5] = [2,4,7,8]   3)  [9,9,2] + [0,1,3] = [1,0,0,5] 
     */

    public static int[] sumArrays(int[] a, int[] b) {
    	if (a.length <= b.length) {
    		return sumArraysFirstSmallerOrEqual(a, b);
    	} else {
    		return sumArraysFirstSmallerOrEqual(b, a);
    	}

    }

    public static int[] sumArraysFirstSmallerOrEqual(int[] a, int[] b) {
    	int[] result = new int[b.length + 1];
    	result[0] = 0;
    	boolean moreThanNine = false;

    	for (int i = 1; i <= b.length; ++i) {
    		if (i <= a.length) {
    			result[result.length - i] = a[a.length - i] + b[b.length - i];
    		} else {
    			result[result.length - i] = b[b.length - i];
    		}
    		
    		if (moreThanNine) {
    			++result[result.length - i];
    			moreThanNine = false;
    		}

    		if (result[result.length - i] > 9) {
    			result[result.length - i] -= 10;
    			moreThanNine = true;
    		}
    	}
    	if (moreThanNine) 	result[0] = 1;
    	
    	return result;
    }
    
    /* from given dictionary of words and target word write function which will return true 
     * if any word in dic has only 1 difference of character from target 
     */
    //time complexity of this approach is O(kn). it can be improved with trie approach
    private boolean isOneCharDiff(String s,String[] words)
    {

    //Examine each word and see if there's one which only differs by 1 character 
    	for(int i=0;i<words.length;i++)
    	{
               int x=words[i].length();
                 if(x==s.length())
                  {
                	 //Create a HashMap of all the characters in this string
                	 HashMap<Character,Integer> mp=new HashMap<Character,Integer>();
                	 for(int j=0;j<x;j++)
                	 {
                		 if(!mp.containsKey(words[i].charAt(j)))
                		 {
                			 mp.put(words[i].charAt(j),1);
                		 }else
                		 {
                			 int currCount=mp.get(words[i].charAt(j));
                			 mp.put(words[i].charAt(j),++currCount);
                		 }
                	 }
                	 //Check query word against characters in hash map
                	 for(int j=0;j<s.length();j++)
                	 {
                		 if(mp.containsKey(s.charAt(j)))
                		 {
                			 int currCount=mp.get(s.charAt(j)).intValue();
                			 --currCount;
                			 if(currCount==0)
                			 {
                				 mp.remove(s.charAt(j));
                			 }else
                			 {
                				 mp.put(s.charAt(j),currCount);
                			 }
                		 }
                	 }
                	 if(mp.size()==1)
                	 {
                		 return true;//If we find a string that differs by just one character, return true.
                	 }
             }
    	}
    	return false;
    }
    
    // solution using trie/prefix tree
    //https://careercup.com/question?id=5760697411567616
    /*struct Node {

    	bool exist;
    	Node *children[256];
    };

    Node *root;

    bool dfs(Node *n, const string &s, int pos, int cnt) {
    	if (cnt > 1)
    		return false;
    	if (pos == s.size())
    		return cnt == 1;

    	for (int i = 0; i < 256; ++i) {
    		Node *c = n->children[i];
    		if (c != nullptr) {
    			if (s[pos] == i && dfs(c, s, pos + 1, cnt) ||
    					s[pos] != i && dfs(c, s, pos + 1, cnt + 1))
    				return true;
    		}
    	}
    }

    bool hasSimilar(const string &s) {
    	return dfs(root, s, 0, 0);
    } */
    
    /* i18n -> internationalization problem 
     * create all possible combinations of given string 
     * e.g. "careercup"=>"c7p","ca6p","c6up","car5p","ca5up","care4p","car4up","caree3p","care3up"......
     
     logic - Maintain a prefix and suffix that start as the first and last letters of the input word.
    Maintain pointers to left and right indices indicating the range of the word currently being processed.
    For each "layer", we want to compute the left + sizeof(remainder to right), sizeof(remainder to left) + right, and sizeof(entire layer), 
    then sandwich these three into the prefix and suffix computed so far, then update the prefix and suffix
    */
    public void possibleCombo(String str){
    	
    	int left = 0;
    	int right = str.length()-1;
    	String prefix = "";
    	String suffix = "";
    	char [] a = str.toCharArray();
    			
    	ArrayList<String> results = new ArrayList<String>();
    	
    	while (left < right) {
    	    results.add(prefix + a[left] + (right - left - 1) + suffix);
    	    results.add(prefix + (right - left - 1) + a[right] + suffix);
    	    results.add(prefix + (right - left) + suffix);
    	    prefix = prefix + a[left];
    	    suffix = a[right] + suffix;
    	    left++;
    	    right--;
    	}
    	
    	// It is important to identify the last layer, which is when the layer's size is 3 or 2.
    	// For the 2 case, left and right cross over.
    	// For the 3 case, left and right hit the same character, which is handled below
    	if (left == right) {
    	    results.add(prefix + 1 + suffix);
    	    prefix = prefix + a[left];
    	}
    	
    	// Finally, add the original word into the result
    	results.add(prefix + suffix);
    	
    }
    
    /* given 2 arrays Xs and As. Sort array Xs as per order of values in As. 
     * e.g. For example, if Xs = 17, 5, 1,9, and As = 3, 2, 4, 1, output Xs = 9, 5, 17, 1
     * https://careercup.com/question?id=4669539153346560
     * 
     */
    
    public int[] sort(int[] Xs, int[] As) {
        for (int i = 0; i < Xs.length; i++) {
            int a = As[i];
            while(a < i) {
                a = As[a];
            }

            // can be slightly optimized with if(a != i) ...
            int x = Xs[i];
            Xs[i] = Xs[a];
            Xs[a] = x;
        }

        return Xs;
    }
    
    /* Return pivot in given array
     * pivot is index in array where sum of left elements == sum of right side elements
     */
    public int findPivot(int a[]){
    	int leftSum=0,rightSum=0;
    	for(int i=0;i<a.length;i++){
    	rightSum+=a[i];
    	}

    	for(int i=0;i<a.length;i++){
    	 rightSum-=a[i];
    	 if(leftSum == rightSum)
    		return i;
    	leftSum+=a[i];
    	}
    	//if no such index exist
    	return -1;
    }
    
    /*
     * 
     */
    
    public static int[] largestRange(int[] array)
    {
        int[] range = { 0, -1 };
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i :array) // O(n)
            set.add(i);

        while (set.size() > 0) // each element gets tested once.  Misses happen at most 2 times per entry (when there are no contiguous numbers) so the worst case is O(3n) for this part when largest range is length 1.
        {
            int j = set..First();
            set.Remove(j);
            int a, b;
            // iterate backwards and forwards from j
            for (a = j; set.Count > 0; a--)
            {
                if (!set.Contains(a-1))
                    break;
                set.Remove(a-1);
            }
            for (b = j; set.Count > 0; b++)
            {
                if (!set.Contains(b+1))
                    break;
                set.Remove(b + 1);
            }
            if (b - a > range[1] - range[0])
            {
                range[0] = a;
                range[1] = b;
            }
        }
        return range;
    }
    
    /* Eliminate all ‘b’ and ‘ac’ in an array of characters, you have to replace them in-place, 
     * and you are only allowed to iterate over the char array once. 
     * - we will use two pointer solution
     */
    
    void replace(char [] str)
    {
        int i=0; //current pointer
        int j=0; //result pointer
        char []q=str;
        while(i < q.length)
        {
            if(q[i]=='b')
            i++;
            else
            {
                if(q[i]=='c'&&(str[j-1]=='a'&& j>0))
                {
                    i++;
                    j--;
                }

                else
                {
                    str[j]=q[i];
                    i++;
                    j++;

                }
            }
        }
        str[i]='\0';
    }
    
    /* find longest consecutive sequence in array. Eg. {1,6,10,4,7,9,5} -> then ans is 4,5,6,7
     * Logic here is that each element in the hashtable keeps track of the sequence. So boundary elements of the sequence are important 
     * as the end element of the sequence points to beginning element of the sequence and beginning element of the sequence points to the end element. 
     * So whenever a new element is to be added to the sequence it picks up the boundary value and becomes the new boundary
     */
    
    public int[] longestConsecutiveSequence(int[] a) 
    {
            int first = Integer.MAX_VALUE; // the first number of the maximum consecutive sequence
            int length = 0; // the length of the maximum consecutive sequence
            Map<Integer, Integer> table = new HashMap<Integer, Integer>();
            for(int i: a) {
                    if(!table.containsKey(i)) {
                            int start = i;
                            int end = i;
                            if(table.containsKey(i + 1) && table.get(i + 1) >= i + 1) {
                                    end = table.get(i + 1);
                                    table.remove(i + 1);
                                    table.remove(end);
                            }
                            if(table.containsKey(i - 1) && table.get(i - 1) <= i - 1) {
                                    start = table.get(i - 1);
                                    table.remove(i - 1);
                                    table.remove(start);
                            }
                            table.put(start, end);
                            table.put(end, start);
                            if(end - start + 1 > length) {
                                    first = start;
                                    length = end - start + 1;
                            }
                    }
            }
            System.out.println(table);
            System.out.println(length);
            int[] s = new int[length];
            for(int i = 0; i < length; i++) s[i] = first + i;
            return s;
    }
    
    /* You are given a 1D array of integers, such as: 
	int[] array = [3,4,7,2,2,6,0,9]; 
	Suppose you need to treat this array as a 2D table with a given number of rows. 
	You want to sum the columns of the table. 
	One value of numRows is 4..in that case the resultant array would look like
	what if numRows==4? 
	3 4 
	7 2 
	2 6 
	0 9 
	—- 
	12 21
	
	* in this case we need to handle situation array.length%nRows should be == 0.
     * 
     */
    
    void findSumOfColumns(int[] array, int nRows) {
    	if (array.length < nRows)
    	{
    		System.out.println("Error: msg");
    		return;
    	}
    	int nCols = (int) Math.ceil((double)array.length/nRows);
    	int[] sums = new int[nCols];
    	//for loop to initialize sums to zero
    	int elems = array.length;	
    	for (int i=0; i<elems; i++) {
    		sums[i%nCols] += array[i];
    	}
    	for (int i=0; i<nCols; i++)
    		System.out.println(sums[i]);
    }

    /* for give integer array find maximum number that can be formed by concatinating numbers
     * idea is 
     * quick sort the input BUT
  		while comparing two inputs A and B
  		instead of doing the regular A > B, DO THIS
      if(ToInt('AB') > ToInt('BA')) return 1 
             else -1;
     */
    
    private Random r = new Random();
    public void quicksort_maxConcatenation(int[] a, int begin, int end) {
        if (begin < end) {
            int q = partition(a, begin, end);
            quicksort_maxConcatenation(a, begin, q);
            quicksort_maxConcatenation(a, q + 1, end);
        }
    }

    private int partition(int[] a, int begin, int end) {
        int p = begin + r.nextInt(end - begin + 1);
        int t1 = a[p];
        a[p] = a[end];
        a[end] = t1;

        int pivot = t1;
        int q = begin;
        for (int i = begin; i < end; i++) {
            if (compare_maxConcatenation(a[i], pivot) > 0) {
                int t2 = a[q];
                a[q] = a[i];
                a[i] = t2;
                q++;
            }
        }
        int t3 = a[q];
        a[q] = a[end];
        a[end] = t3;

        return q;
    }

    private int compare_maxConcatenation(int i, int j) {
        int ij = Integer.valueOf(String.valueOf(i).concat(String.valueOf(j)));
        int ji = Integer.valueOf(String.valueOf(j).concat(String.valueOf(i)));
        if (ij > ji)
            return 1;
        else if (ij == ji)
            return 0;
        return -1;
    }
 
    /* find index of element whose left side sum == right side sum in given array
     * 1. Caluculate the whole sum of elements presented in the Array 
		2. Maintain a variable which holds the sum of elements till that elements i.e.CumSum ( excludes present element). 
		3. Return the index of the array when 2*CumSum+ current element = sum of all elements in the array
     */
    
    /* Write program that takes integer, deletes one of two consecutive digits and return greatest of all results
     * 
     */
    
    	public static long consecutiveDig(long num) { 
    		if(num < 100) 
    			return getMaxDig(num); 

    		return getMaxDig(num %100) + 10 * consecutiveDig(num/100); 
    	} 
    	
    	public static long getMaxDig(long n) { 
    		if (n<10) 
    			return n; 

    		return n/10 > n%10 ? n/10:n%10; 
    	} 
    	
    	/* Given an unsorted array of integers, you need to return maximum possible n such that the array consists at least n values greater than or equals to n. Array can contain duplicate values. 
			Sample input : [1, 2, 3, 4] -- output : 2 
			Sample input : [900, 2, 901, 3, 1000] -- output: 3
    	 *   logic is
    	 *   Lets say the array has M numbers. For the purpose of this problem, negative values and 0s are irrelevant. Also, numbers larger than M can be treated as M because the answer is never larger than M. 
			So, we can count the number of existing values between 1 and M. Then, process the values backwards (M to 1) to find the answer, adding the counts of the values processed so far. 
			This yields an O(M) algorithm with extra O(M) memory.
    	 */
    	
    	public int Solve(int [] values) {
    		int n = values.length;
    		int [] count = new int[n+1];
    		
    		for (int val: values) {
    			if (val >= n)
    				count[n]++;
    			else if (val > 0) // ignore negative values
    				count[val]++;
    		}	
    		
    		int am = 0;
    		
    		for (int i = n; i > 0; i--) {
    			am += count[i];  // amount of numbers >= i
    			if (am >= i)
    				return i;
    		}
    		
    		return 0;
    	}
    
}
    
    

