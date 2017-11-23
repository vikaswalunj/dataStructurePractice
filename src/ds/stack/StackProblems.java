package ds.stack;

public class StackProblems {

	/*
	 * reverse string using stack
	 */
	private String name = "Vikas";
	public void reverseString(char character[]){
		MyStack<String> stringStack = new MyStack<String>();
		for (char c:character){
			
		}
		
		
	}
	
	/*
	 * Find if given expression has balanced and matching paranthesis
	 * e.g. '{[]}()' should return true '1'. but '{{[]}()' should return false
	 */
	
	public Boolean findBalancedP(char st[]){
		
		MyStack<Character> sc = new MyStack<Character>();
		
		for (int i = 0; i < st.length; i++) {
			
			if (st[i] == '{' || st[i] == '(' || st[i] == '['){
				sc.push(st[i]);
			}
			
			if (st[i] == '}' || st[i] == ')' || st[i] == ']'){
				
				if (sc.isEmpty()) {
					return false;
				} else {
					Character c = sc.pop();
					if (!isMatchingPair(st[i], c))
						return false;
				}
			}
		}
		
		if (sc.isEmpty())
			return true;
		
		return false;
	}
	
	public Boolean isMatchingPair(char exp, char sc){
		
		if (exp == '}' && sc == '{') 
		   return true;
		
		if (exp == ')' && sc == '(')
			return true;
		
		if (exp == ']' && sc == '[')
			return true;
		
		return false;
		
	}
	
	/*
	 * Next Greater element for each element in given array
	 * e.g. {12, 13, 6, 9, 15} = 12 -> 13, 13 -> 15, 6 -> 9, 9 -> 15, 15 -> -1
	 * If element do not have next greater element in array -1 will be its NGE
	 */
	
	public void findNGE(int[] no) {
		
		MyStack<Integer> si  = new MyStack<Integer>();
		si.push(no[0]);

		for (int i=1; i < no.length; i++){
			
			int next = no[i];
			if (!si.isEmpty()) {
				int sp = si.pop();
			
				while (sp < next) {
					System.out.println("NGE for " +sp +" is " +next);
					if (!si.isEmpty()) {
						sp = si.pop();
					} else {
						break;
					}
				}
				
				if (sp > next)
					si.push(sp);
			}
			 
			si.push(next);
		}
		
		while(!si.isEmpty()) {
			System.out.println("NGE for " +si.pop() +" is -1");
		}
	}
	
	/*
	 * reverse given stack without using for, while or any loop. Recursion can be used
	 */
	
	public void reverseStack(MyStack<Integer> intStack){
		
		if (intStack.isEmpty())
			return;
		//remove Bottom element from stack
		int Bottom = popBottom(intStack);
		//reverse everything else in stack
		reverseStack(intStack);
		//add original bottom in top of stack
		intStack.push(Bottom);
		System.out.println("Bottom is=" +Bottom);
	}
	
	public int popBottom(MyStack<Integer> intStack){
		
		int top = intStack.pop();
		if (intStack.isEmpty()) {
			//if we removed last element return it
			return top;
		} else {
			
			// we dint remove last element, so remove last element from what remains
			int Bottom = popBottom(intStack);
			//since element we removed isnt last element, add it back onto top of stack
			intStack.push(top);
			return Bottom;
		}
	}
	
	/*
	 * sort stack - can not use loop constructs like for, while etc.. use recursion
	 */
	
	public void sortStack(MyStack<Integer> intstack){
		if (!intstack.isEmpty()) {
			int temp = intstack.pop();
			sortStack(intstack);
			sortedInsert(intstack, temp);
			System.out.println(intstack.peek());
		}
	}
	
	public void sortedInsert(MyStack<Integer> intstack, int var){
		
		if(intstack.isEmpty() || var > intstack.peek()){
			intstack.push(var);
			return;
		}
		
		int temp = intstack.pop();
		sortedInsert(intstack, temp);
		intstack.push(temp);
	}
	
	/*
	 * Stock span. Span means no of consecutive days before given day where price of 
	 * stok was less than or equal to given days price
	 */
	
	public int[] calculateSpan(int price[]){
		/* first element will always have span of one */
		int span[] = new int[price.length];
		span[0] = 1;
		
		MyStack<Integer> st = new MyStack<Integer>(); 
		/* push index of first element to it */
		st.push(0);
		
		for (int i =1; i < price.length; i ++ ) {
			
			while(!st.isEmpty() && price[st.peek()] < price[i]){
				st.pop();
			}
			
			if (st.isEmpty()) {
				span[i] = i + 1;
			} else {
				span[i] = i - st.peek();
			}
			
			st.push(i);
			
		}
		
		return span;
	}
	
	/*
	 * Find maximum length of valid string of parenthesis
	 */
	public int findMaxLengthString(String []args){
		int result= 0;
		MyStack<Integer> stk = new MyStack<Integer>();
		stk.push(-1);
		for (int i = 0; i <= args.length; i++){
			
			if (args[i] == "("){
				stk.push(i);
			} else {
				stk.pop();
				
				if (!stk.isEmpty()) {
					result = i - stk.peek();
				} else {
					stk.push(i);
				}
				
			}
			
		}
		
		return result;
	}
	
	/*
	 * find minimum reversal needed for making parenthesis expression balanced
	 */
	
	public int findMinRev(String[] br){
		
		int size = br.length;
		if (size%2 == 1)
			return -1;
		MyStack<String> stk = new MyStack<String>();
		for (int i = 0; i < size; i ++) {
			if (br[i] == "}" && !stk.isEmpty()){
				
				if (stk.peek() == "{"){
					stk.pop();
				} else {
					stk.push(br[i]);
				}
					
			} else {
				stk.push(br[i]);
			}
		}
		
		int n = 0;
		while (stk.peek() == "{") {
			stk.pop();
			n++;
		}
		/* here we should return n/2 and stk.size()/2 but since we do not have stk.size() function
		 * coded yet er sre returnign only n/2
		 */
		return (n/2);
	}
}
