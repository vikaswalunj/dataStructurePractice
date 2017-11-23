package ds.stack;


public class MyStack<T> {
	private T[] stackArray;
	private int top;
	
	@SuppressWarnings("unchecked")
	public MyStack(){
		stackArray = (T[]) new Object[10]; // 10 being default size of array
		top = -1;
	}
	@SuppressWarnings("unchecked")
	public MyStack(int size){
		stackArray = (T[]) new Object[size];
		top = -1;
	}
	
	public void push(T value){
		stackArray[++top] = value;
	}
	
	public T pop(){
		return stackArray[top--];
	}
	
	public T peek(){
		return stackArray[top];
	}
	
	public boolean isEmpty(){
		return (top == -1);
	}

	
}
