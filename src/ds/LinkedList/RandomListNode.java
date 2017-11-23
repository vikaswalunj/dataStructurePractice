package ds.LinkedList;

public class RandomListNode {

	int data;
	RandomListNode next;
	RandomListNode random;
	
	public RandomListNode(int d){
		this.data = d;
		this.next = null;
		this.random = null;
	}
	
	public RandomListNode(){
		this.data = 0;
		this.next = null;
		this.random = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public RandomListNode getNext() {
		return next;
	}

	public void setNext(RandomListNode next) {
		this.next = next;
	}

	public RandomListNode getRandom() {
		return random;
	}

	public void setRandom(RandomListNode random) {
		this.random = random;
	}

}
