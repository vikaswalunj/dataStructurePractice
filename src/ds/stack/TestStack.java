package ds.stack;

public class TestStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackProblems sp = new StackProblems();
		
		/* find balanced paranthesis*/
		/*char[] sc = {'[', '(', '{', ')', '}', ']'};
		Boolean result = sp.findBalancedP(sc);
		System.out.println("result is=" +result);*/
		/* find balanced paranthesis*/
		
		/*find NGE */
		/*int[] ic = {13, 15, 3, 7, 20};
		sp.findNGE(ic);*/
		/*find NGE */
		
		/*MyStack<Integer> myStack = new MyStack<Integer>();
		myStack.push(12);
		myStack.push(13);
		myStack.push(11);
		myStack.push(9);
		
		 reverse stack using reursion 
		//sp.reverseStack(myStack);
		
		 sort stack 
		sp.sortStack(myStack);*/
		
		/*stock span calculation*/
		int price[] = {100, 20, 30, 54, 34, 25, 50, 200};
		int span[] = sp.calculateSpan(price);
		for (int i = 0; i < span.length; i++){
			System.out.print(span[i]+" ");
		}
		
	}

}
