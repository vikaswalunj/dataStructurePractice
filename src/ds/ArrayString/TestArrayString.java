package ds.ArrayString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArrayString {
	public static final String name = "vikassonalwalunj";
	public static String s = "baaprebaap";
	public static String p = "?*re?*a";
	
	public static void main (String[] args) {
		ArrayString arst = new ArrayString();
		
		/*arst.lengthOfLongestSubstring(name);*/
		
		/*group anagram testing */
		/*String[] strs = new String[5];
		strs[0] = "ccat";
		strs[1] = "dog";
		strs[2] = "tcac";
		strs[3] = "god";
		strs[4] = "odg";
		arst.groupAnagrams(strs);*/
		
		/* wild card pattern test*/
		//System.out.println(arst.isMatch(s, p));
		//String newStr = new String("VikasVias");
		//arst.firstNonRepeating(newStr);
		
		/*List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		List<Integer> inputList1 = new ArrayList<Integer>();
		inputList1.add(4);
		inputList1.add(6);
		list.add(inputList1);
		
		List<Integer> inputList2 = new ArrayList<Integer>();
		inputList2.add(2);
		inputList2.add(3);
		list.add(inputList2);
		
		List<Integer> inputList3 = new ArrayList<Integer>();
		inputList3.add(6);
		inputList3.add(9);
		list.add(inputList3);
		
		List<Integer> inputList4 = new ArrayList<Integer>();
		inputList4.add(0);
		inputList4.add(0);
		list.add(inputList4);
		
		arst.closestLocations(3, list, 2);*/
		
		//int arr[] = {2,1,5,6,2,3};
		System.out.println(arst.simplifyPath("/home/"));
		System.out.println(arst.simplifyPath("/a/./b/../../c/"));
		System.out.println(arst.simplifyPath("/../"));
		System.out.println(arst.simplifyPath("/home//foo/"));
//		arst.rotate(arr, -3);
		//arst.maxFrequencyNumber(arr, 2);
		/*findTotal - amazon que*/
		/*String unStr = "\u4E2D";
		System.out.println(unStr);*/
		//int[] strArray = {5,2,8,8,5,5,8,1,1,2};
		//arst.printFrequencySortedArray(strArray);
	}
	
	
	
	

}
