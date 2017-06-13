package ds.LinkedList;

import java.util.HashMap;

public class LinkedList {

	Node head;
	
	/*Add Node at front of linked list
	 * i.e. new node becomes head of linked list
	 * Time complexity - O(1)
	 */
	
	public void push(int new_data){
		//create new node for new data passed in input
		Node newNode = new Node(new_data);
		// assign current head as next in newly created node
		newNode.next = head;
		//make newNode as head of linked list
		head = newNode;
	}
	
	/*
	 * Insert node after given node i.e. in between linked list
	 */
	
	public void insertAfter(Node prev_node, int new_data){
		//check if previous node is not empty
		if (prev_node == null) {
			System.out.println("previous node is null");
			return;
		}
		
		//create new node for new data passed in input
		Node newNode = new Node(new_data);
		// allocate next of previous node to next of newNode
		newNode.next = prev_node.next;
		//allocate newNode as next of prev_node
		prev_node.next = newNode;
	}
	
	/*
	 * Append node at last of linked list
	 */
	public void append(Node lastNode, int new_data){
		
		Node newNode = new Node(new_data);
		
		//check if Linked List is null or not
		if (head == null)
			head = newNode;
		
		Node last = head;
		newNode.next = null;
		while (last.next != null) {
			last = last.next;
		}
		
		last.next = newNode;
		return;
	}
	
	/*
	 * delete node whose data is equal to data passed in input
	 */
	public void deleteNode(int key){
		//if head itself contains key then 
		if (head.data == key)
			head = head.next;
		
		Node temp = head;
		Node prev = null;
		
		while (temp.next != null && temp.data != key) {
			prev = temp;
			temp = temp.next;
		}
		
		if (temp.next == null)
			return;
		
		prev.next = temp.next;
		
	}
	
	
	/*
	 * delete node present at position passed in input
	 */
	
	public void deletePosition(int position){
		
		if (position == 0)
			head = head.next;
		
		Node temp = head;
		Node prev = null;
		
		for (int i = 0; i < position && temp.next != null; i++){
			prev = temp;
			temp = temp.next;
		}
		
		if (temp.next == null){
			return;
		}
		
		prev.next = temp.next;
	}
	
	/*
	 * reverse linked list
	 */
	public static Node reverse(Node node){

		Node prev = null;
		Node current = node;
		Node next = null;
		
		while (current != null){
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}
	
	/*
	 * Reverse Linked List in groups of given data
	 */
	public static Node reverseSize(Node head, int k){
		Node prev = null;
		Node current = head;
		Node next = null;
		
		int count = 0;
		
		while (count < k && current!= null){
			next = current.next;
			current.next = prev;
			prev = current; 
			current = next;
			count++;
		}
		
		if (next!=null){
			head.next = reverseSize(next, k);
		}
		
		return prev;
	}
	
	/* Reorder linked list - given linked list L0->L1->L2->L3->...->Ln-1->Ln
	 * output L0→Ln→L1→Ln-1→L2→Ln-2→...
	 * In-place - i.e. not creating result linked list (extra memory)
	 * algorithm
	 * 1. Break list in the middle to two lists (use fast & slow pointers)
	 * 2. Reverse the order of the second list
	 * 3. Merge two list back together
	 */
	
	public static void reorderList(Node head) {
		 
		if (head != null && head.next != null) {
 
			Node slow = head;
			Node fast = head;
 
			//use a fast and slow pointer to break the link to two parts.
			while (fast != null && fast.next != null && fast.next.next!= null) {
				//why need third/second condition?
				System.out.println("pre "+slow.data + " " + fast.data);
				slow = slow.next;
				fast = fast.next.next;
				System.out.println("after " + slow.data + " " + fast.data);
			}
 
			Node second = slow.next;
			slow.next = null;// need to close first part
 
			// now should have two lists: head and fast
 
			// reverse order for second part
			second = reverse(second);
 
			Node p1 = head;
			Node p2 = second;
 
			//merge two lists here
			while (p2 != null) {
				Node temp1 = p1.next;
				Node temp2 = p2.next;
 
				p1.next = p2;
				p2.next = temp1;		
 
				p1 = temp1;
				p2 = temp2;
			}
		}
	}
	
	/* Determine if Linked list has cycle
	 * If it has fast pointer will definitely meet slow at that point 
	 */
	public boolean hasCycle(Node head) {
        Node fast = head;
        Node slow = head;
 
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
 
            if(slow == fast)
                return true;
        }
 
        return false;
    }
	
	/*Copy Linked list random pointer
	 * A linked list is given such that each node contains an additional random pointer 
	 * which could point to any node in the list or null
	 */
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode newHead = new RandomListNode(head.data);
	 
		RandomListNode p = head;
		RandomListNode q = newHead;
		map.put(head, newHead);
	 
		p = p.next;
		while (p != null) {
			RandomListNode temp = new RandomListNode(p.data);
			map.put(p, temp);
			q.next = temp;
			q = temp;   // it is same as q = q.next;
			p = p.next;
		}
	 
		p = head;
		q = newHead;
		while (p != null) {
			if (p.random != null)
				q.random = map.get(p.random);
			else
				q.random = null;
	 
			p = p.next;
			q = q.next;
		}
	 
		return newHead;
	}
	
	/* Merge two sorted lists */
	
	public Node mergeTwoLists(Node l1, Node l2) {
	    Node head = new Node(0);
	    Node p = head;
	 
	    while(l1!=null||l2!=null){
	        if(l1!=null&&l2!=null){
	            if(l1.data < l2.data){
	                p.next = l1;
	                l1=l1.next;
	            }else{
	                p.next=l2;
	                l2=l2.next;
	            }
	            p = p.next;
	        }else if(l1==null){
	            p.next = l2;
	            break;
	        }else if(l2==null){
	            p.next = l1;
	            break;
	        }
	    }
	 
	    return head.next;
	}
	
	/* Oddeven list
	 * Given a singly linked list, group all odd nodes together followed by the even nodes. 
	 * Please note here we are talking about the node number and not the value in the nodes.
	 */
	public Node oddEvenList(Node head) {
	    if(head == null) 
	        return head;
	 
	    Node result = head;
	    Node p1 = head;
	    Node p2 = head.next;
	    Node connectNode = head.next;
	 
	    while(p1 != null && p2 != null){
	            Node t = p2.next;
	            if(t == null)
	                break;
	 
	            p1.next = p2.next;
	            p1 = p1.next;
	 
	            p2.next = p1.next;
	            p2 = p2.next;
	    }
	 
	    p1.next = connectNode;
	 
	    return result;
	}
	
	/* Remove duplicates from sorted list
	 */
	
    public Node deleteDuplicates(Node head) {
	        if(head == null || head.next == null)
	            return head;
	 
	        Node p = head;
	 
	        while( p!= null && p.next != null){
	            if(p.data == p.next.data){
	                p.next = p.next.next;
	            }else{
	                p = p.next; 
	            }
	        }
	 
	        return head;
    }
	
	/* Remove nodes/values which are duplicates from list leaving only distinct members
	 * 
	 */
	
	public Node keepOnlyDistinct(Node head) {
	    Node t = new Node(0);
	    t.next = head;
	 
	    Node p = t;
	    while(p.next!=null&&p.next.next!=null){
	        if(p.next.data == p.next.next.data){
	            int dup = p.next.data;
	            while(p.next!=null&&p.next.data==dup){
	                p.next = p.next.next;
	            }
	        }else{
	            p=p.next;
	        }
	 
	    }
	 
	    return t.next;
	}
	
	/* Partition list 
	 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
		You should preserve the original relative order of the nodes in each of the two partitions.
		For example, given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5
	 */
	
	public Node partition(Node head, int x) {
        if(head == null) return null;
 
        Node fakeHead1 = new Node(0);
        Node fakeHead2 = new Node(0);
        fakeHead1.next = head;
 
        Node p = head;
        Node prev = fakeHead1;
        Node p2 = fakeHead2;
 
        while(p != null){
            if(p.data < x){
                p = p.next;
                prev = prev.next;
            }else{
 
                p2.next = p;
                prev.next = p.next;
 
                p = prev.next;
                p2 = p2.next;
            } 
        }
 
        // close the list
        p2.next = null;
 
        prev.next = fakeHead2.next;
 
        return fakeHead1.next;
    }
	
	/* Intersection of two singly linsked list
	 */
	public Node getIntersectionNode(Node headA, Node headB) {
        int len1 = 0;
        int len2 = 0;
        Node p1=headA, p2=headB;
        if (p1 == null || p2 == null)
            return null;
 
        while(p1 != null){
            len1++;
            p1 = p1.next;
        }
        while(p2 !=null){
            len2++;
            p2 = p2.next;
        }
 
        int diff = 0;
        p1=headA;
        p2=headB;
 
        if(len1 > len2){
            diff = len1-len2;
            int i=0;
            while(i<diff){
                p1 = p1.next;
                i++;
            }
        }else{
            diff = len2-len1;
            int i=0;
            while(i<diff){
                p2 = p2.next;
                i++;
            }
        }
 
        while(p1 != null && p2 != null){
            if(p1.data == p2.data){
                return p1;
            }else{
 
            }
            p1 = p1.next;
            p2 = p2.next;
        }
 
        return null;
    }
	
	// another way to do this is add first list to HashSet. Then for each node in 2nd list,
	// check if hashset contains that value if yes then return that from 2nd list as intersection node
	
	/* Swap nodes in pairs
	 * For example, given 1->2->3->4, you should return the list as 2->1->4->3
	 */
	
	public Node swapPairs(Node head) {
	    if(head==null || head.next==null)
	        return head;
	 
	    //a fake head
	    Node h = new Node(0);
	    h.next = head;
	 
	    Node p1 = head;
	    Node p2 = head.next;
	 
	    Node pre = h;
	    while(p1!=null && p2!=null){
	        pre.next = p2;
	 
	        Node t = p2.next;
	        p2.next = p1;
	        pre = p1;
	        p1.next = t;
	 
	        p1 = p1.next;
	 
	        if(t!=null)
	            p2 = t.next;
	    }
	 
	    return h.next;
	}
}
