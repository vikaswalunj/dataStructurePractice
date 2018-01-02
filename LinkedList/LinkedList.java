package ds.LinkedList;

import java.util.HashMap;

import ds.LinkedList.flatNode.doubleNode;
import ds.LinkedList.flatNode.pointNode;

public class LinkedList {

	Node head;
	pointNode phead;
	doubleNode dhead;
	
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
	
	/* delete nth node from end
	 */
	
	public Node removeNthFromEnd(Node head, int n) {
	    Node dummy = new Node(0);
	    dummy.next = head;
	    Node first = dummy;
	    Node second = dummy;
	    // Advances first pointer so that the gap between first and second is n nodes apart
	    for (int i = 1; i <= n + 1; i++) {
	        first = first.next;
	    }
	    // Move first to the end, maintaining the gap
	    while (first != null) {
	        first = first.next;
	        second = second.next;
	    }
	    second.next = second.next.next;
	    return dummy.next;
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
			q = q.next;   // it is same as q = q.next;
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
	public Node oddEvenList(Node head){
		if (head == null)
				return head;
		
		Node odd = head;
		Node even = head.next;
		Node connectNode = even;
		
		while (even != null && even.next != null){
			odd.next = even.next;
			odd = odd.next;
			
			even.next = odd.next;
			even = even.next;
			
		}
		odd.next = connectNode;
			
		return head;
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
	            Node dupeNode = p.next;
	            while(dupeNode!=null&&dupeNode.data==dup){
	                dupeNode = dupeNode.next;
	            }
	            p.next = dupeNode;
	            p = p.next;
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
 
        Node fakeHead1 = new Node(0); //fakehead for smaller than x
        Node fakeHead2 = new Node(0); //fakehead for greater than X
        fakeHead1.next = head;
 
        Node p = head;
        Node smallerThanX = fakeHead1;
        Node greaterThanX = fakeHead2;
 
        while(p != null){
            if(p.data < x){
                p = p.next;
                smallerThanX = smallerThanX.next;
            }else{
 
                greaterThanX.next = p;
                smallerThanX.next = p.next;
 
                p = smallerThanX.next;
                greaterThanX = greaterThanX.next;
            } 
        }
 
        // close the list
        greaterThanX.next = null;
 
        smallerThanX.next = fakeHead2.next;
 
        return fakeHead1.next;
    }
	
	/* Intersection of two singly linked list
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
	
	/* Utility function for calculating length of linked list */
    public int countNodes()
    {
        int count = 0;
        Node s = head;
        while (s != null)
        {
            count++;
            s = s.next;
        }
        return count;
    }
    
    /* Function for swapping kth nodes from both ends of
    linked list */
	 public void swapKth(int k)
	 {
	     // Count nodes in linked list
	     int n = countNodes();
	
	     // Check if k is valid
	     if (n < k)
	         return;
	
	     // If x (kth node from start) and y(kth node from end)
	     // are same
	     if (2*k - 1 == n)
	         return;
	
	     // Find the kth node from beginning of linked list.
	     // We also find previous of kth node because we need
	     // to update next pointer of the previous.
	     Node x = head;
	     Node x_prev = null;
	     for (int i = 1; i < k; i++)
	     {
	         x_prev = x;
	         x = x.next;
	     }
	
	     // Similarly, find the kth node from end and its 
	     // previous. kth node from end is (n-k+1)th node
	     // from beginning
	     Node y = head;
	     Node y_prev = null;
	     for (int i = 1; i < n - k + 1; i++)
	     {
	         y_prev = y;
	         y = y.next;
	     }
	
	     // If x_prev exists, then new next of it will be y.
	     // Consider the case when y->next is x, in this case,
	     // x_prev and y are same. So the statement 
	     // "x_prev->next = y" creates a self loop. This self
	     // loop will be broken when we change y->next.
	     if (x_prev != null)
	         x_prev.next = y;
	
	     // Same thing applies to y_prev
	     if (y_prev != null)
	         y_prev.next = x;
	
	     // Swap next pointers of x and y. These statements
	     // also break self loop if x->next is y or y->next
	     // is x
	     Node temp = x.next;
	     x.next = y.next;
	     y.next = temp;
	
	     // Change head pointers when k is 1 or n
	     if (k == 1)
	         head = y;
	
	     if (k == n)
	         head = x;
	 }
	
	/* Sort linked list which is already sorted on absolute values
	 */
	
	// To sort a linked list by actual values.
    // The list is assumed to be sorted by absolute
    // values.
	public Node sortedList(Node head)
	{
	    // Initialize previous and current nodes
	    Node prev = head;
	    Node curr = head.next;
	     
	    // Traverse list
	    while(curr != null)
	    {
	        // If curr is smaller than prev, then
	                    // it must be moved to head
	        if(curr.data < prev.data)
	        {
	            // Detach curr from linked list
	            prev.next = curr.next;
	             
	            // Move current node to beginning
	            curr.next = head;
	            head = curr;
	             
	            // Update current
	            curr = prev;
	        }
	         
	        // Nothing to do if current element
	                    // is at right place
	        else
	        prev = curr;
	     
	        // Move current
	        curr = curr.next;
	    }
	    return head;
	}
	
	/* Check if a linked list of strings forms a palindrome */
	
	// Returns true if string formed by linked
    // list is palindrome
    public boolean isPalindrome()
    {
        Node node = head;
 
        // Append all nodes to form a string
        String str = "";
        while (node != null)
        {
            str = str.concat(String.valueOf(node.data));
            node = node.next;
        }
 
        // Check if the formed string is palindrome  
        //return isPalidromeUtil(str);    // this functions is implemented in ArrayString
        return true;   // this is just compilation replacement of isPalidromeUtil(str) method 
    }
    
    /* one more way to ind if liked list is palindrome or not */
    public boolean isPalindrome(Node head) {
        if(head == null)
            return true;
     
        Node p = head;
        Node prev = new Node(head.data);
     
        while(p.next != null){
            Node temp = new Node(p.next.data);
            temp.next = prev;
            prev = temp;
            p = p.next;
        }
     
        Node p1 = head;
        Node p2 = prev;
     
        while(p1!=null){
            if(p1.data != p2.data)
                return false;
     
            p1 = p1.next;
            p2 = p2.next;
        }
     
        return true;
    }
    /* Find a triplet from three linked lists with sum equal to a given number
     * Lets assume we have three lists a,b,c. We can sort b in ascending order and c in descending order
     * Then loop through each element of a, for which try to find pair in b and c using loop which will match given number
     */
    
    /* A function to chech if there are three elements in a, b
    and c whose sum is equal to givenNumber.  The function
    assumes that the list b is sorted in ascending order and
    c is sorted in descending order. */
	 public boolean isSumSorted(LinkedList la, LinkedList lb, LinkedList lc,
	                     int givenNumber)
	 {
	    Node a = la.head;
	
	    // Traverse all nodes of la
	    while (a != null)
	    {
	        Node b = lb.head;
	        Node c = lc.head;
	
	        // for every node in la pick 2 nodes from lb and lc
	        while (b != null && c!=null)
	        {
	            int sum = a.data + b.data + c.data;
	            if (sum == givenNumber)
	            {
	               System.out.println("Triplet found " + a.data +
	                                   " " + b.data + " " + c.data);
	               return true;
	            }
	
	            // If sum is smaller then look for greater value of b
	            else if (sum < givenNumber)
	              b = b.next;
	
	            else
	              c = c.next;
	        }
	        a = a.next;
	    }
	    System.out.println("No Triplet found");
	    return false;
	 }
	 
	 /* Implement merge sort for Linked List 
	  * It includes 3 functions
	  * 1) mergeSort(Node node) - it looks through list and calls next two functions. It uses recursion to merge
	  * 2) getMiddle(Node node) - it gives back middle element for passed list
	  * 3) sortedMerge(Node n1, Node n2) - merge two input lists
	  */
	 
	 public Node mergeSort(Node h) 
	    {
	        // Base case : if head is null
	        if (h == null || h.next == null)
	        {
	            return h;
	        }
	 
	        // get the middle of the list
	        Node middle = getMiddle(h);
	        Node nextofmiddle = middle.next;
	 
	        // set the next of middle node to null
	        middle.next = null;
	 
	        // Apply mergeSort on left list
	        Node left = mergeSort(h);
	 
	        // Apply mergeSort on right list
	        Node right = mergeSort(nextofmiddle);
	 
	        // Merge the left and right lists
	        Node sortedlist = sortedMerge(left, right);
	        return sortedlist;
	    }
	 
	 	// Utility function to get the middle of the linked list
	    public Node getMiddle(Node h) 
	    {
	        //Base case
	        if (h == null)
	            return h;
	        Node fastptr = h.next;
	        Node slowptr = h;
	         
	        // Move fastptr by two and slow ptr by one
	        // Finally slowptr will point to middle node
	        while (fastptr != null)
	        {
	            fastptr = fastptr.next;
	            if(fastptr!=null)
	            {
	                slowptr = slowptr.next;
	                fastptr=fastptr.next;
	            }
	        }
	        return slowptr;
	    }
	    
	    public Node sortedMerge(Node a, Node b) 
	    {
	        Node result = null;
	        /* Base cases */
	        if (a == null)
	            return b;
	        if (b == null)
	            return a;
	 
	        /* Pick either a or b, and recur */
	        if (a.data <= b.data) 
	        {
	            result = a;
	            result.next = sortedMerge(a.next, b);
	        } 
	        else
	        {
	            result = b;
	            result.next = sortedMerge(a, b.next);
	        }
	        return result;
	 
	    }
	 
	    /* Rotate a Linked List 
	     * 10->20->30->40->50->60 k = 4; 50->60->10->20->30->40
	     */
	    // This function rotates a linked list counter-clockwise
	    // and updates the head. The function assumes that k is
	    // smaller than size of linked list. It doesn't modify
	    // the list if k is greater than or equal to size
	    public void rotate(int k)
	    {
	        if (k == 0) return;
	 
	        // Let us understand the below code for example k = 4
	        // and list = 10->20->30->40->50->60.
	        Node current  = head;
	 
	        // current will either point to kth or NULL after this
	        // loop. current will point to node 40 in the above example
	        int count = 1;
	        while (count < k && current !=  null)
	        {
	            current = current.next;
	            count++;
	        }
	 
	        // If current is NULL, k is greater than or equal to count
	        // of nodes in linked list. Don't change the list in this case
	        if (current == null)
	            return;
	 
	        // current points to kth node. Store it in a variable.
	        // kthNode points to node 40 in the above example
	        Node kthNode = current;
	 
	        // current will point to last node after this loop
	        // current will point to node 60 in the above example
	        while (current.next != null)
	            current = current.next;
	 
	        // Change next of last node to previous head
	        // Next of 60 is now changed to node 10
	 
	        current.next = head;
	 
	        // Change head to (k+1)th node
	        // head is now changed to node 50
	        head = kthNode.next;
	 
	        // change next of kth node to null
	        kthNode.next = null;
	 
	    }
    
    
	    /* Flatten a multilevel linked list */
	    
	    /* The main function that flattens a multilevel linked list */
	    public void flattenList(flatNode node) {
	         
	        /*Base case*/
	        if (node == null) {
	            return;
	        }
	         
	        flatNode tmp = null;
	 
	        /* Find tail node of first level linked list */
	        flatNode tail = node;
	        while (tail.next != null) {
	            tail = tail.next;
	        }
	 
	        // One by one traverse through all nodes of first level
	        // linked list till we reach the tail node
	        flatNode cur = node;
	        while (cur != tail) {
	 
	            // If current node has a child
	            if (cur.child != null) {
	 
	                // then append the child at the end of current list
	                tail.next = cur.child;
	 
	                // and update the tail to new last node
	                tmp = cur.child;
	                while (tmp.next != null) {
	                    tmp = tmp.next;
	                }
	                tail = tmp;
	            }
	 
	            // Change current node
	            cur = cur.next;
	        }
	    }
	    
	    /* Given a linked list of line segments, remove middle points
	     * i/p - (0,10)->(1,10)->(5,10)->(7,10)
                                  |
                                (7,5)->(20,5)->(40,5)
            o/p - (0,10)->(7,10)
                  |
                (7,5)->(40,5)                    
	     */
	    // This function deletes middle nodes in a sequence of
	    // horizontal and vertical line segments represented
	    // by linked list.
	    public pointNode deleteMiddle()
	    {
	        // If only one node or no node...Return back
	        if (phead == null || phead.next == null ||
	            phead.next.next == null)
	            return phead;
	 
	        pointNode Next = phead.next;
	        pointNode NextNext = Next.next;
	 
	        // check if this is vertical or horizontal line
	        if (phead.x == Next.x)
	        {
	            // Find middle nodes with same value as x and
	            // delete them.
	            while (NextNext != null && Next.x == NextNext.x)
	            {
	                phead.next = Next.next;
	                Next.next = null;
	 
	                // Update NextNext for the next iteration
	                Next = NextNext;
	                NextNext = NextNext.next;
	            }
	        }
	 
	        // if horizontal
	        else if (phead.y == Next.y)
	        {
	            // find middle nodes with same value as y and
	            // delete them
	            while (NextNext != null && Next.y == NextNext.y)
	            {
	                phead.next = Next.next;
	                Next.next = null;
	 
	                // Update NextNext for the next iteration
	                Next = NextNext;
	                NextNext = NextNext.next;
	            }
	        }
	 
	        // Adjacent points should have same x or same y
	        else
	        {
	            System.out.println("Given list is not valid");
	            return null;
	        }
	 
	        // recur for other segment
	 
	        // temporarily store the head and move head forward.
	        pointNode temp = phead;
	        phead = phead.next;
	 
	        // call deleteMiddle() for next segment
	        this.deleteMiddle();
	 
	        // restore head
	        phead = temp;
	 
	        // return the head
	        return phead;
	    }
	    
	    /* Rearrange a Linked List in Zig-Zag fashion
	     * Given a linked list, rearrange it such that converted list should be of the form a < b > c < d > e < f .. 
	     * where a, b, c.. are consecutive data node of linked list
	     */
	    
	    // This function distributes the Node in zigzag fashion
	    public void zigZagList(Node head)
	    {
	        // If flag is true, then next node should be greater
	        // in the desired output.
	        boolean flag = true;
	     
	        // Traverse linked list starting from head.
	        Node current = head;
	        while (current.next != null)
	        {
	            if (flag)  /* "<" relation expected */
	            {
	                /* If we have a situation like A > B > C
	                   where A, B and C are consecutive Nodes
	                   in list we get A > B < C by swapping B
	                   and C */
	                if (current.data > current.next.data) {
	                    //swap(current.data, current.next.data);  /
	                }	
	            }
	            else /* ">" relation expected */
	            {
	                /* If we have a situation like A < B < C where
	                   A, B and C  are consecutive Nodes in list we
	                   get A < C > B by swapping B and C */
	                if (current.data < current.next.data) {
	                    //swap(current.data, current.next.data);
	                }	
	            }
	     
	            current = current.next;
	            flag = !flag;  /* flip flag for reverse checking */
	        }
	    }
	    
	    ///////////////////////////////////////////////////////////////////////////////////////////
	    ///               Circular Linked List      /////////////////////////////////
	    /////////////////////////////////////////////////////////////////////////////
	    /* circular list traversal */
	    /* Function to traverse a given Circular linked list and print nodes */
	    public void printList(Node first)
	    {
	        Node temp = first; 
	     
	        // If linked list is not empty
	        if (first != null) 
	        {
	            // Keep printing nodes till we reach the first node again
	            do
	            {
	                System.out.println("node value " +temp.data);
	                temp = temp.next;
	            }
	            while (temp != first);
	        }
	    }
	    
	    /* Divide circular list into 2 halves */
	    
	    /* Function to split a list (starting with head) into two lists.
	     head1_ref and head2_ref are references to head nodes of 
	     the two resultant linked lists */
	    public void splitList() {
	        Node slow_ptr = head;
	        Node fast_ptr = head;
	 
	        if (head == null) {
	            return;
	        }
	 
	        /* If there are odd nodes in the circular list then
	         fast_ptr->next becomes head and for even nodes 
	         fast_ptr->next->next becomes head */
	        while (fast_ptr.next != head
	                && fast_ptr.next.next != head) {
	            fast_ptr = fast_ptr.next.next;
	            slow_ptr = slow_ptr.next;
	        }
	 
	        /* If there are even elements in list then move fast_ptr */
	        if (fast_ptr.next.next == head) {
	            fast_ptr = fast_ptr.next;
	        }
	 
	        /* Set the head pointer of first half */
	        Node head1 = head;
	 
	        /* Set the head pointer of second half */
	        if (head.next != head) {
	            Node head2 = slow_ptr.next;
	        }
	        /* Make second half circular */
	        fast_ptr.next = slow_ptr.next;
	 
	        /* Make first half circular */
	        slow_ptr.next = head;
	    }
	    
	    /* Sorted insert for circular linked list */
	    /* function to insert a new_node in a list in sorted way.
	     Note that this function expects a pointer to head node
	     as this can modify the head of the input linked list */
	    public void sortedInsert(Node new_node)
	    {
	        Node current = head;
	 
	        // Case 1 of the above algo
	        if (current == null)
	        {
	            new_node.next = new_node;
	            head = new_node;
	 
	        }
	 
	        // Case 2 of the above algo
	        else if (current.data >= new_node.data)
	        {
	 
	            /* If value is smaller than head's value then
	             we need to change next of last node */
	            while (current.next != head)
	                current = current.next;
	 
	            current.next = new_node;
	            new_node.next = head;
	            head = new_node;
	        }
	 
	        // Case 3 of the above algo
	        else
	        {
	 
	            /* Locate the node before the point of insertion */
	            while (current.next != head &&
	                   current.next.data < new_node.data)
	                current = current.next;
	 
	            new_node.next = current.next;
	            current.next = new_node;
	        }
	    }
	    
	    /////////////////////////////////////////////////////////////////////////////
	    ///               Doubly Linked List        /////////////////////////////////
	    /////////////////////////////////////////////////////////////////////////////
	    /* Given a reference (pointer to pointer) to the head of a list
	    and an int, inserts a new node on the front of the list. */
		 /*public void push(doubleNode head_ref, int new_data)
		 {
		      1. allocate node 
		     //doubleNode new_node = (doubleNode) malloc(sizeof(doubleNode));
		  
		      2. put in the data  
		     new_node.data = new_data;
		  
		      3. Make next of new node as head and previous as NULL 
		     new_node.next = (*head_ref);
		     new_node.prev = null;
		  
		      4. change prev of head node to new node 
		     if((*head_ref) !=  null)
		       (*head_ref).prev = new_node ;
		  
		      5. move the head to point to the new node 
		     (*head_ref)    = new_node;
		 }*/
		 
		 /*Function to delete a node in a Doubly Linked List.
		    head_ref --> pointer to head node pointer.
		    del  -->  pointer to node to be deleted. */
		    void deleteNode(doubleNode head_ref, doubleNode del) {
		 
		        /* base case */
		        if (dhead == null || del == null) {
		            return;
		        }
		 
		        /* If node to be deleted is head node */
		        if (dhead == del) {
		            dhead = del.next;
		        }
		 
		        /* Change next only if node to be deleted is NOT the last node */
		        if (del.next != null) {
		            del.next.prev = del.prev;
		        }
		 
		        /* Change prev only if node to be deleted is NOT the first node */
		        if (del.prev != null) {
		            del.prev.next = del.next;
		        }
		 
		        /* Finally, free the memory occupied by del*/
		        return;
		    }
		    
		    /* UTILITY FUNCTIONS */
		    /* Function to insert a node at the beginning of the Doubly Linked List */
		    void pushBeginning(doubleNode head_ref, int new_data) {
		 
		        /* allocate node */
		        doubleNode new_node = new doubleNode(new_data);
		 
		        /* since we are adding at the begining,
		         prev is always NULL */
		        new_node.prev = null;
		 
		        /* link the old list off the new node */
		        new_node.next = (dhead);
		 
		        /* change prev of head node to new node */
		        if ((dhead) != null) {
		            (dhead).prev = new_node;
		        }
		 
		        /* move the head to point to the new node */
		        (dhead) = new_node;
		    }
	    
		    /* reverse doubly linked list */
		    /* Function to reverse a Doubly Linked List */
		    void reverse() {
		        doubleNode temp = null;
		        doubleNode current = dhead;
		 
		        /* swap next and prev for all nodes of 
		         doubly linked list */
		        while (current != null) {
		            temp = current.prev;
		            current.prev = current.next;
		            current.next = temp;
		            current = current.prev;
		        }
		 
		        /* Before changing head, check for the cases like empty 
		         list and list with only one node */
		        if (temp != null) {
		            dhead = temp.prev;
		        }
		    }
}
