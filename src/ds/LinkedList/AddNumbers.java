package ds.LinkedList;

public class AddNumbers {
	
	public LinkedList addtwoNumbers(Node a, Node b){
		
		LinkedList list = new LinkedList();
		Node result = list.head;
		int carry = 0;
		while (a != null || b != null) {
			if (a != null ) {
				carry = carry + a.data;
				a = a.next;
			}	
			
			if (b != null) {
				carry = carry + b.data;
				b = b.next;
			}
			
			result.data = carry%10;
			result = result.next;
			carry = carry/10;
		}
		
		if (carry == 1){
			result.next = new Node(1);
		}
		
		return list;
		
	}
	
	
	public static void main (String[] args) {
		LinkedList list1 = new LinkedList();
		list1.head = new Node(2);
		list1.head.next = new Node(4);
		list1.head.next.next = new Node(3);
		
		LinkedList list2 = new LinkedList();
		list2.head = new Node(5);
		list2.head.next = new Node(6);
		list2.head.next.next = new Node(7);
		
		AddNumbers addNumbers = new AddNumbers();
		LinkedList result = addNumbers.addtwoNumbers(list1.head, list2.head);
		Node rs = result.head;
		while (rs.next != null) {
			System.out.println(rs.data);
			rs = rs.next;
		}
	}

}
