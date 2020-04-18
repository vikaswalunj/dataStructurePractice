package ds.ArrayString;

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
		
		/*findTotal - amazon que*/
		String[] strArray = {"5", "-2", "4", "Z", "X", "9", "+", "+"};
		String str = "IIV";
		System.out.println(arst.romanToInteger(str));
		
	}
	
	

}
