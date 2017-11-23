package practice.expedia;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	static LinkedList<Integer> stk = new LinkedList<Integer>();
	
    static String onceInATram(int x) {
    	
    	if (x == 0)
    		return "";
    	
    	/*String inputNumber = Integer.toString(x);
    	String first3 = inputNumber.substring(0, 2);
    	String second3 = inputNumber.substring(3);
    	
    	int first3Add = (first3.charAt(0))+*/ 		
    	
    	
    	while (x>0){
    		stk.push(x%10);
    		x = x/10;
    	}
    	
    	int count = 1;
    	
    	int first3=0;
    	int second3=0;
    	int firstSum= 0;
    	int secondSum = 0;
    	
    	while (!stk.isEmpty()){
    		if (count <=3){
    			firstSum += stk.peek(); 
    			first3 =  first3 * 10 + stk.pop();
    		} else {
    			secondSum += stk.peek();
    			second3 = second3 * 10 + stk.pop();
    		}
    	}
    	
    	int diff=0;
        if (firstSum > secondSum) {
    	    diff = firstSum - secondSum;
            second3 += diff; 
            return (Integer.toString(first3)+Integer.toString(second3));
        } else { 
        	diff = secondSum - firstSum;
        	
        }
        
    	while(diff>0){
    		
    		secondSum += 1;
    	}
    	
    	
    }
    
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        String result = onceInATram(x);
        System.out.println(result);
    }
}