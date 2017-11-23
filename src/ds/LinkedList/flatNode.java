package ds.LinkedList;

public class flatNode {

	int data;
    flatNode next, child;
     
    flatNode(int d) {
        data = d;
        next = child = null;
    }
    
    class pointNode {
    	int x,y;
        pointNode next;
        pointNode(int x, int y)
        {
            this.x = x;
            this.y = y;
            next = null;
        }
    	
    }
    
    class doubleNode {
    	int data;
    	doubleNode next;
    	doubleNode prev;
    	public doubleNode(int d){
    		this.data = d;
    		this.next = null;
    		this.prev = null;
    	}
    	
    }
    
}
