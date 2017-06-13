package ds.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {
    /*                                   1
     *                                 /   \
     *                                2     3
     *                               / \   / \
     *                              4   5 6   7
     *                             / \
     *                            8   9   
     */
	Node root;
	
	BinaryTree(){
		root = null;
	}
	
	BinaryTree(int key){
		root = new Node(key);
	}
	
	//*** Traversing through tree***//
	/*
	 * PostOrder traversing (Left, Right, Root)
	 * bottom to up process - can be used when want to process all leafs first
	 * 8 9 4 5 2 6 7 3 1
	 */
	public void PostOrder(Node node){
		if (node == null)
			return;
		
		/* first recur on left child */
		PostOrder(node.left);
		/* secondly recur on right child */
		PostOrder(node.right);
		/*Lastly print data of node */
		System.out.println(node.key + " ");
	}
	
	/*
	 * PreOrder traversing (Root, Left, Right)
	 * top to bottom pocess
	 * 1 2 4 8 9 5 3 6 7
	 */
	public void PreOrder(Node node){
		if (node == null)
			return;
		
		/* first print data of node */
		System.out.println(node.key + " ");
		/* secondly recur on left child */
		PreOrder(node.left);
		/* lastly recur on right child */
		PreOrder(node.right);
	}
	
	/*
	 * InOrder traversing (Left, Root, Right) with recursion
	 * bottom up process - used in case of BST a lot because of BST's property. Not used this when you want to process leafs first
	 * 8 4 9 2 5 1 6 3 7
	 */
	public void InOrderRecur(Node node){
		if (node == null)
			return;
		
		/*firstly recur on left child */
		InOrderRecur (node.left);
		/*secondly print data of node */
		System.out.println(node.key + " ");
		/* Lastly recur on right child */
		InOrderRecur(node.right);
	}
	
	/*
	 * InOrderStack - In order with Stack without recursion- 8, 4, 9, 2, 5, 1, 6, 3, 7
	 * 1) Create empty stack
	 * 2) Initialize current node as root
	 * 3) Push current node to Stack and set current = current.left until current is null
	 * 4) If current is NULL and Stack is not empty then 
	 * 		a) Pop the top item from Stack
	 * 		b) Print popped item and set current to poppedItem.right
	 * 		c) Go to step 3.
	 * 5) If current is NULL and stack is empty then we are done.
	 */
	public void InOrderStack(Node node){
		
		if(node == null)
			return;
		
		Stack<Node> stack = new Stack<Node>();
		while (node != null){
			stack.push(node);
			node = node.left;
		}
		
		while (stack.size() > 0) {
			node = stack.pop();
			
			System.out.println(node.key + " ");
			if (node.right != null) {
				node = node.right;
				
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}
	}
	
	/* find maximum in binary tree
	 * Since it is not Binary search tree we need to compare each element. In BST rightmost elemnt will be
	 * max and left most min. 
	 * Same approach can be taken to find minimum
	 */
	
	public int findMax(Node root){
		if (root == null)
			return Integer.MAX_VALUE;
		
		int result = root.key;
		int lMax = findMax(root.left);
		int rMax = findMax(root.right);
		if (lMax > result)
			result = lMax;
		if (rMax > result)
			result = rMax;
		
		return rMax;
		
	}
	
	/*
	 * find kth smallest element in binary search tree
	 * InOrder traversal in BST can be used - left, root, right 
	 */
	
	public int findKthSmallestEle (Node node, int k) {
		int result =0;
		// check if BST is not null by checking root
		if (node == null){
			return 0;
		}
		
		//create stack to hold nodes in InOrder traversal order
		Stack<Node> stack = new Stack<Node>();
		
		//first load stack with left most nodes
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
		
		//itereate through each node in stack if it has right node then store that in stack 
		// and repeat procedure of left nodes again for that right node
		while(stack.size() > 0) {
			node = stack.pop();
			/*for each element popped out of stack count no of element processed in InOrder traversal*/
			k --;
			if ( k == 0) {
				result = node.key;
			}
			if (node.right != null) {
				node = node.right;
				
				while(node != null){
					stack.push(node);
					node = node.left;
				}
			}
		}
		
		return result;
	}
	/*
	 * Print height of root -- no of nodes along longest path from root to down at farthest leaf
	 * Algorithm is 
	 * a) If root is null return height as 0
	 * b) compute height of each sub tree (left node , right node)
	 * c) return larger height between left and right + 1
	 */
	
	public int height(Node node){
		
		if (node == null)
			return 0;
		
		/* b) compute for each sub tree */
		int lheight = height (node.left);
		int rheight = height (node.right);
		
		/* return larger between two + 1*/
		if (lheight > rheight) {
			return (lheight + 1);
		} else {
			return (rheight + 1);
		}
	}
	
	/*
	 * check if tree is balanced binary tree - if difference in height of two sub tree (left, right) is not more than 1 
	 * then it is balanced binary tree
	 */
	
	public boolean isBalanced(Node root){
		if (root == null)
			return true;
		
		if (getHeight(root) ==  -1)
			return false;
		
		return true;
	}
	
	public int getHeight(Node root) {
		if (root == null)
			return 0;
		
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		
		if (left == -1 || right == -1)
			return -1;
		
		if ((left-right)>1)
			return -1;
		
		return Math.max(left, right) +1;
	}
	
	/*
	 * find is given tree is symmetric or not. //slight modification will be if tree is foldable or not
 	 * The key is finding the conditions that return false, such as value is not equal, only one node(left or right) has value.
	 */
	public boolean isSymmetric(Node root) {
		if (root == null)
			return true;
		return isSymmetric(root.left, root.right);
	}
	 
	public boolean isSymmetric(Node l, Node r) {
		if (l == null && r == null) {
			return true;
		} else if (r == null || l == null) {
			return false;
		}
		//if we want to check if  tree is foldable or not then instead of checking values of key just confirm both are not null
		if (l.key != r.key)
			return false;
	 
		if (!isSymmetric(l.left, r.right))
			return false;
		if (!isSymmetric(l.right, r.left))
			return false;
	 
		return true;
	}
	
	
	/*
	 * BFS - Breadth First Traversal Binary Tree a.k.a. Level order tree traversal
	 * it prints all nodes at level for all levels from root to leaf. 
	 * There are two functions - 
	 * 1) To print all nodes at given level 'printGivenLevel(node, level) 
	 * 2) To print level order traversal of tree 'printLevelOrder(node)' which makes use of first
	 */
	
	/*printLevelOrder function
	 * 1) first compute height of tree 
	 * 2) print each level starting from root till farthest leaf
	 * Tree is node -> (left, right)
	 * root 1 -> (2, 3), 2 -> (4, 5), 3 -> (6, 7), 4-> (8, 9), 5 -> (null, null), 6 -> (null, null)
	 *      7 -> (null, null)
	 */
	public void printLevelOrder(Node root){
		/* compute height of tree*/
		int h = height(root);      /* height is 4 */
		/* print nodes from each level one by one starting from root */
		for (int i = 1; i <=h ; i++)
			printGivenLevel(root, i);  /* (1,1), (1,2), (1,3), (1,4) are sent iteratively */
	}
	
	/* printGivelnLevel
	 *  call stack
	 *  ==============================================================================
	 *   Node  |  Level  | node.left | node.right | level -1  | key@ level==1
	 *  ==============================================================================
	 *   1        1                                                     1
	 *   1		  2
	 *   					2						    1				2
	 *   								3			    1				3
	 *   1		  3			
	 *   					2							2
	 *   					4							1				4
	 *   								5				1				5
	 *   								3				2				
	 *   					6							1				6
	 *   								7				1				7
	 *   1		 4			
	 *   					2							3
	 *   					4							2
	 *   					8							1				8
	 *   								9				1				9
	 *   								5				2				
	 *   					null						1
	 *   								null			1
	 *   								3				3
	 *   					6							2
	 *   					null						1
	 *   								null			1
	 *   								7				2
	 *   					null						1
	 *   								null			1				
	 */
	public void printGivenLevel(Node node, int level){
		
		if(node == null)
			return;
		
		if (level == 1) {
			System.out.println(node.key + " ");
		} else if (level > 1){
			printGivenLevel(node.left, level-1);
			printGivenLevel(node.right, level-1);
		}
	}
	
	/*
	 * Print tree in spiral traversal
	 * This is extention of BFS/Level order traversal just added one flag to direct order of printing
	 */
	void printSpiral(Node node) 
    {
        int h = height(node);
        int i;
  
        /* ltr -> left to right. If this variable is set then the
           given label is transversed from left to right */
        boolean ltr = false;
        for (i = 1; i <= h; i++) 
        {
            printSpiralGivenLevel(node, i, ltr);
  
            /*Revert ltr to traverse next level in opposite order*/
            ltr = !ltr;
        }
  
    }
	
	/* Print nodes at a given level */
    void printSpiralGivenLevel(Node node, int level, boolean ltr) 
    {
        if (node == null) 
            return;
        if (level == 1) 
            System.out.print(node.key + " ");
        else if (level > 1) 
        {
            if (ltr != false) 
            {
            	printSpiralGivenLevel(node.left, level - 1, ltr);
            	printSpiralGivenLevel(node.right, level - 1, ltr);
            } 
            else
            {
            	printSpiralGivenLevel(node.right, level - 1, ltr);
            	printSpiralGivenLevel(node.left, level - 1, ltr);
            }
        }
    }
    
    /*
     * Get level of given key
     */
    /* Returns level of given data value */
    int getLevel(Node node, int data) 
    {
        return getLevelUtil(node, data, 1);
    }
    
    /* Helper function for getLevel().  It returns level of the data
    if data is present in tree, otherwise returns 0.*/
    int getLevelUtil(Node node, int data, int level) 
    {
        if (node == null)
            return 0;
  
        if (node.key == data)
            return level;
  
        int downlevel = getLevelUtil(node.left, data, level + 1);
        if (downlevel != 0)
            return downlevel;
  
        downlevel = getLevelUtil(node.right, data, level + 1);
        return downlevel;
    }
  
    
    
    /* children sum property problem
     * returns 1 if children sum property holds for the given
    node and both of its children*/
    int isSumProperty(Node node) 
    {
          
        /* left_data is left child data and right_data is for right 
           child data*/
        int left_data = 0, right_data = 0;
  
        /* If node is NULL or it's a leaf node then
        return true */
        if (node == null
                || (node.left == null && node.right == null))
            return 1;
        else
        {
             
            /* If left child is not present then 0 is used
               as data of left child */
            if (node.left != null) 
                left_data = node.left.key;
  
            /* If right child is not present then 0 is used
               as data of right child */
            if (node.right != null) 
                right_data = node.right.key;
  
            /* if the node and both of its children satisfy the
               property return 1 else 0*/
            if ((node.key == left_data + right_data)
                    && (isSumProperty(node.left)!=0)
                    && isSumProperty(node.right)!=0)
                return 1;
            else
                return 0;
        }
    }
    
    /*
     * check if tree is height balanced or not
     * An empty tree is height-balanced. A non-empty binary tree T is balanced if:
		1) Left subtree of T is balanced
		2) Right subtree of T is balanced
		3) The difference between heights of left subtree and right subtree is not more than 1.
     */
    
    /* Returns true if binary tree with root as root is height-balanced */
    boolean isHeightBalanced(Node node) 
    {
        int lh; /* for height of left subtree */
  
        int rh; /* for height of right subtree */
  
        /* If tree is empty then return true */
        if (node == null)
            return true;
  
        /* Get the height of left and right sub trees */
        lh = height(node.left);
        rh = height(node.right);
  
        if (Math.abs(lh - rh) <= 1
                && isHeightBalanced(node.left)
                && isHeightBalanced(node.right)) 
            return true;
  
        /* If we reach here then tree is not height-balanced */
        return false;
    }
    
    /*
     * In given 2 trees check if one tree is subtree of another tree
     * can be done in pre order fashion
     */
    
    /* This function returns true if S is a subtree of T, otherwise false */
    boolean isSubtree(Node T, Node S) 
    {
        /* base cases */
        if (S == null) 
            return true;
  
        if (T == null)
            return false;
  
        /* Check the tree with root as current node */
        if (areIdentical(T, S)) 
            return true;
  
        /* If the tree with root as current node doesn't match then
           try left and right subtrees one by one */
        return isSubtree(T.left, S)
                || isSubtree(T.right, S);
    }
    
    /* A utility function to check whether trees with roots as root1 and
    root2 are identical or not */
	 boolean areIdentical(Node root1, Node root2) 
	 {
	
	     /* base cases */
	     if (root1 == null && root2 == null)
	         return true;
	
	     if (root1 == null || root2 == null)
	         return false;
	
	     /* Check if the data of both roots is same and data of left and right
	        subtrees are also same */
	     return (root1.key == root2.key
	             && areIdentical(root1.left, root2.left)
	             && areIdentical(root1.right, root2.right));
	 }
    
	 /*
	  * return sum tree - node.key = node.left.key + node.roght.key
	  */
	 	// Convert a given tree to a tree where every node contains sum of
	    // values of nodes in left and right subtrees in the original tree
	    int toSumTree(Node node) 
	    {
	        // Base case
	        if (node == null)
	            return 0;
	  
	        // Store the old value
	        int old_val = node.key;
	  
	        // Recursively call for left and right subtrees and store the sum
	        // as new value of this node
	        node.key = toSumTree(node.left) + toSumTree(node.right);
	  
	        // Return the sum of values of nodes in left and right subtrees
	        // and old_value of this node
	        return node.key + old_val;
	    }
	
	/*
	 * Clone binary tree with recursion
	 */
	
	public Node cloneMyTree(Node tNode, Node cNode) {
		if(tNode == null || tNode.key == 0)
			return tNode;
		
		Node cLeftNode = new Node();
		Node cRightNode = new Node();
		
		if (tNode.left != null) {
			cLeftNode = cloneMyTree(tNode.left, cLeftNode);
		}
		
		if (tNode.right != null) {
			cRightNode = cloneMyTree(tNode.right, cRightNode);
		}
		
		cNode.left = cLeftNode;
		cNode.right = cRightNode;
		cNode.key = tNode.key;
		
		return cNode;
	}
	
	/*
	 * print tree using recursion
	 */
	
	public void printTree(Node node){
		if (node == null || node.key == 0)
			return;
		
		System.out.println("value of node is" +node.key);
		System.out.println("left of node" +node.key);
		printTree(node.left);
		
		System.out.println("right of node" +node.key);
		printTree(node.right);
	}
	
	/*
	 * Build tree from inorder and preorder traversal
	 * a) PreOrder starts from root so pick first value in pre order traversal array
	 * b) Then search for this value in inOrder array. Values to left of this will be left nodes and values to right will be right nodes
	 * c) select next value from preOrder and repeat process recursively to form a binary tree
	 * e.g. pre order = 1, 2, 4, 8, 9, 5, 3, 6, 7
	 * 		in order = 8, 4, 9, 2, 5, 1, 6, 3, 7 
	 */
	public static int preIndex = 0;
	public Node buildTree(int in[], int pre[], int inStart, int inEnd) {
		if (inStart > inEnd)
			return null;
		
		Node tNode = new Node(pre[preIndex++]);
		if (inStart == inEnd){
			return tNode;
		}
		int inIndex = search(in, tNode.key, inStart, inEnd);
		tNode.left = buildTree(in, pre, inStart, inIndex-1);
		tNode.right = buildTree(in, pre, inIndex+1, inEnd);
		
		return tNode;
	}
	
	public int search(int in[], int value, int inStart, int inEnd){
	
		int i;
		for (i = inStart; i <= inEnd; i++) {
			if (value == in[i]) 
				return i;
		}
		return i;
	}
	
	/* Build tree using level order and in order
	 * Level order will start with root node. Find that in Inorder, left elements to that
	 * will be left subtree
	 */
	
	public Node constructTree(Node startNode, int[] levelOrder, int[] inOrder,
            int inStart, int inEnd) 
    {
  
        // if start index is more than end index
        if (inStart > inEnd)
            return null;
  
        if (inStart == inEnd)
            return new Node(inOrder[inStart]);
             
        boolean found = false;
        int index = 0;
  
        // it represents the index in inOrder array of element that
        // appear first in levelOrder array.
        for (int i = 0; i < levelOrder.length - 1; i++) 
        {
            int data = levelOrder[i];
            for (int j = inStart; j < inEnd; j++) 
            {
                if (data == inOrder[j]) 
                {
                    startNode = new Node(data);
                    index = j;
                    found = true;
                    break;
                }
            }
            if (found == true)
                break;
        }
  
        //elements present before index are part of left child of startNode.
        //elements present after index are part of right child of startNode.
        startNode.setLeft(constructTree(startNode, levelOrder, inOrder, 
                                                    inStart, index - 1));
        startNode.setRight(constructTree(startNode, levelOrder, inOrder, 
                                                     index + 1, inEnd));
  
        return startNode;
    }
	/*
	 * Get maximum width of tree
	 * 2 functions 1) getMaxWidth(Node node), getWidth(Node node, int i)
	 */
	
	public int getMaxWidth(Node node){
		int width, maxWidth = 0;
		int h = height(node);
		int i;
		for (i = 1; i<=h; i++){
			width = getWidth(node, i);
			if(width > maxWidth)
				maxWidth = width;
		}
		
		return maxWidth;
	}
	
	public int getWidth(Node node, int level){
		if (node == null)
			return 0;
		if (level == 1){
			return 1;
		} else  if (level > 1){
			return getWidth(node.left, level-1) + getWidth(node.right, level -1);
		}
		
		return 0;
	}
	
	/*
	 * find minimum depth of binary tree 
	 * 2 queues will be used 1) to store nodes traversed through 2) count of nodes traversed through
	 */
	
	public int minDepth(Node root){
		if (root == null)
			return 0;
		// form two queues
		LinkedList<Node> nodes = new LinkedList<Node>();
		LinkedList<Integer> counts = new LinkedList<Integer>();
		
		//add details for first node passed as input
		nodes.add(root);
		counts.add(1);
		
		while(!nodes.isEmpty()){
			Node curr = nodes.remove();
			int count = counts.remove();
			
			//if it is leaf node return count
			if (curr.left == null && curr.right == null)
				return count;
			
			//if curr.left exist , add its details in queues
			if(curr.left != null ) {
				nodes.add(curr.left);
				counts.add(count+1);
			}
			
			//if curr.right exist, add its details in queues
			if(curr.right != null) {
				nodes.add(curr.right);
				counts.add(count+1);
			}
		}
		
		return 0;
	}
	
	/*
	 * Print nodes at K distance
	 * if we do not care about printing error message of out of bound, we do not need height and hence printDistantNodes method itself
	 * Alone printNodes(Node node, int k) will be enough
	 */
	
	public void printDistantNodes(Node node, int k) {
		if (node == null)
			return;
		int height = height(node);
		printNodes(node, k, height);
	}
	public void printNodes(Node node, int k, int h) {
		if (node == null)
			return;
		if (k == 0) {
			System.out.println(node.key + " ");
		} else if (k < h+1) {
			printNodes(node.left, k-1, h);
			printNodes(node.right, k-1, h);
		} else {
			System.out.println("Tree out of bound exception");
		}
	}
	
	/* Print all nodes at K distance from given target node
	 * Two steps 1) Nodes in the subtree rooted with target node 2)  Other nodes, may be an ancestor of target, or a node in some other subtree (k-d) 
	 */
	
	// Prints all nodes at distance k from a given target node.
    // The k distant nodes may be upward or downward.This function
    // Returns distance of root from target node, it returns -1
    // if target node is not present in tree rooted with root.
    int printkdistanceNode(Node node, Node target, int k) 
    {
        // Base Case 1: If tree is empty, return -1
        if (node == null) {
        	System.out.println("this is null node");
            return -1;
        }
        // If target is same as root.  Use the downward function
        // to print all nodes at distance k in subtree rooted with
        // target or root
        if (node.key == target.key) 
        {
            System.out.println("current node is target");
        	printDistantNodes(node, k);
            return 0;
        }
  
        // Recur for left subtree
        int dl = printkdistanceNode(node.left, target, k);
        System.out.println("dl after passing left node=" +dl);
        // Check if target node was found in left subtree
        if (dl != -1) 
        {
              
            // If root is at distance k from target, print root
            // Note that dl is Distance of root's left child from 
            // target
            if (dl + 1 == k) 
            {
            	System.out.println("dl+1=k so current node is below");
                System.out.print(node.key);
                System.out.println("");
            }
              
            // Else go to right subtree and print all k-dl-2 distant nodes
            // Note that the right child is 2 edges away from left child
            else
            			if (node.right != null)
            				System.out.println("going to right node:" +node.right.key);
            			else 
            				System.out.println("right node is null");
                        System.out.println("k - dl -2 =" +(k-dl-2));
                printDistantNodes(node.right, k - dl - 2);
  
            // Add 1 to the distance and return value for parent calls
            return 1 + dl;
        }
  
        // MIRROR OF ABOVE CODE FOR RIGHT SUBTREE
        // Note that we reach here only when node was not found in left 
        // subtree
        int dr = printkdistanceNode(node.right, target, k);
        System.out.println("dr is=" +dr);
        if (dr != -1) 
        {
            if (dr + 1 == k) 
            {
            	System.out.println("dr+1=k so current node is below");
                System.out.print(node.key);
                System.out.println("");
            } 
            else
            	if (node.left != null)
    				System.out.println("going to left node:" +node.left.key);
    			else 
    				System.out.println("left node is null");
                System.out.println("k - dr -2 =" +(k-dr-2));
                printDistantNodes(node.left, k - dr - 2);
            return 1 + dr;
        }
  
        // If target was neither present in left nor in right subtree
        return -1;
    }
	
	
	/*
	 * Search Binary Searh Tree
	 */
	public Node searchBST(Node root, int key){
		System.out.println("node is=" +root.key);
		System.out.println("Key is=" +key);
		if (root==null || root.key == key)
			return root;
		
		if (root.key > key)
			return searchBST(root.left, key);
		
		return searchBST(root.right, key);
	}
	
	/*
	 * Delete BST key
	 */
	
	public void deleteBSTkey(int key){
		root = deleteBSTRec(root, key);
	}
	
	/*
	 * Delete given node from BST
	 */
	public Node deleteBSTRec(Node root, int key){
		if (root == null)
			return root;
		
		if (key < root.key) {
			root.left = deleteBSTRec(root.left, key);
		} else if (key > root.key) {
			root.right = deleteBSTRec(root.right, key);
		} else {
			//node with only one child or no child
			if (root.left == null)
				return root.right;
			if (root.right == null)
				return root.left;
			
			//node with two children: get inorder successor (smallest in the right subtree)
			root.key = minBSTValue(root.right);
			
			//Delete inorder successor
			root.right = deleteBSTRec(root.right, root.key);
		}
		
		return root;
	}
	/*
	 * find minimum key value in BST
	 */
	public int minBSTValue(Node node){
		int minv = node.key;
		while(node.left != null){
			minv = node.left.key;
			node = node.left;
		}
		return minv;
	}
	
	/*
	 * find longest consecutive path in BST. Path allowed from from parent to child
	 * it needs 2 methods 1) initialize result and currentsize and call 2nd method which 
	 * can be called on  
	 */
	
	public int longestPath(Node root){
		
		if (root == null){
			return 0;
		}
		
		int result = 0;
		int currentSize = 0;
	
		longestConsecutivePath(root, currentSize, root.key, result);
		
		return result;
	}
	
	public void longestConsecutivePath(Node root, int curLength, int expected, int res){
		
		if (root == null)
			return;
		
		/* if root data is parent+1 then increase curLength by 1 */
		if (root.key == expected)
			curLength ++;
		else 
			curLength = 1;
		
		/* update maximum by current length*/
		res = Math.max(res, curLength);
		
		/* recursively call left and right subtree with expected value 1 more than root data */
		longestConsecutivePath(root.left, curLength, root.key-1, res);
		longestConsecutivePath(root.right, curLength, root.key+1, res);
	}
	
	public Node insertNodeBST(Node node, int data){
		/* if node is empty */
		if(node == null) {
			return (new Node(data));
		} else {
			/* otherwise recur down tree*/
			if (data <= node.key) {
				node.left = insertNodeBST(node.left, data);
			} else {
				node.right = insertNodeBST(node.right, data);
			}
			/*return unchanged node pointer*/
			return node;
		}
	}
	
	/*
	 * Find predecessor and successor of key in BST
	 * Node:- The maximum value(root.right) in left subtree is predecessor
	 * 			The minimum(root.left) value in right subtree is successor
	 */
	public void findPreSucBST(Node root, Node pre, Node suc, int key) {
		
		if (root == null) 
			return;
		
		if (root.key == key){
			/*The maximum value(root.right) in left subtree is predecessor*/
			if (root.left != null) {
				Node temp = root.left;
				while (temp.right != null){
					temp = temp.right;
				}
				pre = temp;
			}
			/*The minimum(root.left) value in right subtree is successor*/
			if (root.right != null){
				Node temp = root.right;
				while(temp.left != null){
					temp = temp.left;
				}
				suc = temp;
			}
		}
		/* if key is smaller than root key go to left sub tree*/
		if (key < root.key) {
			suc = root;
			findPreSucBST(root.left, pre, suc, key);
		}
		/* if key is greater than root key go to right sub tree*/
		if (key > root.key) {
			pre = root;
			findPreSucBST(root.right, pre, suc, key);
		}
	}
	
	/*
	 * Find Lowest common ancestor in BST for given two nodes values.
	 * Traverse through BST from root and find node which in inbetween given two nodes n1 <= n <= n2
	 * 		If current node is less than two nodes (n < n1 && n < n2) then LCA lies on right side
	 * 		If current node is greater than two nodes (n > n1 && n > n2) then LCA lies on left side.
	 * 		if at start itself n1 < n < n2 then n is root. 
	 * 
	 */
	
	public Node findLCABST(Node node, int n1, int n2){
		if (node == null)
			return null;
		
		if (node.key > n1 && node.key > n2)
			return findLCABST(node.left, n1, n2);
		
		if (node.key < n1 && node.key < n2)
			return findLCABST(node.right, n1, n2);
		
		return node;
	}
	
	/* Find Lowest Common ancestor in "Binary Tree"
	 * Since it is Binary Tree and not BST we can not use property that n1 < n < n2.
	 * Here we need to first get path from root to both numbers in arrays. Then first element in 
	 * those arrays which is not common is LCA. Hence we need 2 functions  
	 */
	
	public int findLCA(Node node, int n1, int n2) {
		if (node == null)
			return 0;
		//path1 - array for root to n1, path2 - array for root to n2
		ArrayList<Integer> path1 = new ArrayList<Integer>();
		ArrayList<Integer> path2 = new ArrayList<Integer>();
		
		if (!findPath(node, path1, n1) || !findPath(node, path2, n2))
				return -1;   //if either n1 or n2 are not present in paths
		
		//if found in paths traverse throuh path till common elements
		int i;
		for (i=0; i< path1.size() && i < path2.size(); i++)
			if (path1.get(i) != path2.get(i))
				break;
		
		return path1.get(i-1);
	}
	
	//function to create path array from root to given number
	public boolean findPath(Node root, ArrayList<Integer> path, int n){
		if (root == null)
			return false;
		
		path.add(root.key);   //appends element
		
		if (root.key == n)
			return true;
		
		//check if n exist in left or right subtree
		if ((root.left != null && findPath(root.left, path, n)) ||
				(root.right != null && findPath(root.right, path, n)))
			return true;
		
		//if you reached here that means its not in tree. So remove root from path
		path.remove(root.key);
		return false;
	}
	
	/*
	 * Find ceil for given key in BST
	 * ceil - smallest number in BST which is greater than (or equal to) given key(data) value
	 */
	
	public int findCeilBST(Node root, int data){
		// if key(data) is below leaf nodes (i.e. greater than max or smaller than least node) return -1
		if (root == null)
			return -1;
		//if key(data) equal to current node's value
		if (root.key == data)
			return root.key;
		//if roots key is smaller then data must be on right sub tree
		if (root.key < data) 
			return findCeilBST(root.right, data);
		//Either left sub tree or root itself has ceil value
		int ceil = findCeilBST(root.left, data);
		return (ceil >= data) ? ceil : root.key; 
	}
	
	/*
	 * validate BST
	 * make sure root.left.key <= root.key && root.right.key >= root.key
	 */
	
	public boolean isValidBST(Node root){
		if (root == null)
			return true;
		
		if (root.left != null && root.left.key > root.key)
			return false;
		
		if (root.right != null && root.right.key < root.key)
			return false;
		/* return false even if at any node it is not BST */
		return isValidBST(root.left) && isValidBST(root.right);
	}
	
	/*
	 * flatten binary tree i.e. convert binary tree into linked list like structure - i.e. everything becomes 'right' 
	 * 1) first traverse through all root.left, move them to right and make left nodes null.
	 * 2) While doing so if root also has root.right, then store it tempororily in stack which will be
	 *    marked as root.right after all left nodes are done.
	 *    Data structure to store nodes is stack because we want to process LIFO. If we wanted FIFO 
	 *    we can use queue 
	 */
	
	public Node flattenBinaryTree(Node root){
		if (root == null)
			return root;
		
		Stack<Node> stack = new Stack<Node>();
		while (root != null || !stack.isEmpty()) {
			//if right node exists push it to temporory storage 'stack'
			if (root.right != null)
				stack.push(root.right);
			
			//if left node exist make it right node and m
			if (root.left != null) {
				root.right = root.left;
				root.left = null;
			} else if (!stack.isEmpty()) {
				root.right = stack.pop();
			}
			//move onto next node
			root = root.right;
		}
		//need to figure out how we can send first node resultant structure 
		return root;
	}
	
	/*
	 * PathSum - check if given tree has path (root to leaf) whos sum == given number.
	 * This is usually BFS problem and can be solved using 2 queues 
	 * One queue will store nodes and another will store values within nodes  
	 */
	
	//this solutuion is not correct
	public boolean hasSumPath(Node root, int sum){
		if (root == null)
			return false;
		
		//create two queues - linked list implementaion of queue
		LinkedList<Node> nodes = new LinkedList<Node>();
		LinkedList<Integer> values = new LinkedList<Integer>();
		//add first node and its value to queues
		nodes.add(root);
		values.add(root.key);
		
		while(nodes != null) {
			//retrieve current node and sum from 
			Node curr = nodes.poll();
			int sumValue = values.poll();
			
			//if its leaf node and sumVlaue = given sum return true
			if (curr.left == null && curr.right == null && sumValue == sum)
				return true;
			
			if(curr.left != null) {
				nodes.add(curr.left);
				values.add(sumValue + curr.left.key);
			}
			
			if(curr.right != null){
				nodes.add(curr.right);
				values.add(sumValue + curr.right.key);
			}
			
		}
		return false;
	}
	//this is recursive solution for path sum problem
	/* The basic idea is to subtract the value of current node from sum until it reaches a leaf node and the subtraction equals 0, 
	 * then we know that we got a hit. Otherwise the subtraction at the end could not be 0
	 */
	public boolean hasPathSum(Node root, int sum) {
		if(root == null) return false;
		
		if(root.left == null && root.right == null && sum - root.key == 0) return true;
		
		return hasPathSum(root.left, sum - root.key) || hasPathSum(root.right, sum - root.key);
	}
	
	/* Find maximum path sum between two leaves of of binary tree
	 * http://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
	 */
	
	public static int res = Integer.MIN_VALUE;
	// A utility function to find the maximum sum between any
    // two leaves.This function calculates two values:
    // 1) Maximum path sum between two leaves which is stored
    //    in res.
    // 2) The maximum root to leaf path sum which is returned.
    // If one side of root is empty, then it returns INT_MIN
    int maxPathSumUtil(Node node, int res) {
 
        // Base cases
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return node.key;
 
        // Find maximum sum in left and right subtree. Also
        // find maximum root to leaf sums in left and right
        // subtrees and store them in ls and rs
        int ls = maxPathSumUtil(node.left, res);
        int rs = maxPathSumUtil(node.right, res);
 
        // If both left and right children exist
        if (node.left != null && node.right != null) {
 
            // Update result if needed
            res = Math.max(res, ls + rs + node.key);
 
            // Return maxium possible value for root being
            // on one side
            return Math.max(ls, rs) + node.key;
        }
 
        // If any of the two children is empty, return
        // root sum for root being on one side
        return (node.left == null) ? rs + node.key
                : ls + node.key;
    }
 
    // The main function which returns sum of the maximum
    // sum path between two leaves. This function mainly
    // uses maxPathSumUtil()
    int maxPathSum(Node node)
    {
        maxPathSumUtil(root, res);
        return res;
    }
	
	
	/*
	 * sortedArray to Binary searh tree - Google question, 
	 * DFS problem with recursion. 
	 * Generally root of Binary search tree will be mid of array as left < root and right > root 
	 */
	
	public Node sortedArrayToBST(int[] num){
		if (num.length == 0)
			return null;
		//pass array, starting point, end index of array
		return sortedArrayToBST(num, 0, num.length -1);
	}
	
	public Node sortedArrayToBST(int[] num, int start, int end){
		if (start > end)
			return null;
		//get middle index of passed array
		int mid = (start + end)/2;
		//root of BST will mid point of passed array
		Node root = new Node(num[mid]);
		//left side of mid point will form left sub tree in BST
		root.left = sortedArrayToBST(num, start, mid-1);
		//right side of mid point will form right sub tree in BST
		root.right = sortedArrayToBST(num, mid+1, end);
		
		return root;
	}
	
	/*
	 * list all rightmost nodes values in binary search tree
	 */
	
	public List<Integer> rightSideView(Node root) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	 
	    if(root == null) return result;
	 
	    LinkedList<Node> queue = new LinkedList<Node>();
	    queue.add(root);
	 
	    while(queue.size() > 0){
	        //get size here
	        int size = queue.size();
	 
	        for(int i=0; i<size; i++){
	            Node top = queue.remove();
	 
	            //the first element in the queue (right-most of the tree)
	            if(i==0){
	                result.add(top.key);
	            }
	            //add right first - if we need left view then we will add left element first and that will be top element in queue 
	            if(top.right != null){
	                queue.add(top.right);
	            }
	            //add left
	            if(top.left != null){
	                queue.add(top.left);
	            }
	        }
	    }
	 
	    return result;
	}
	
	/* Top view of binary tree
	 *  
	 */
	// This method prints nodes in top view of binary tree
    public void printTopView()
    {
        // base case
        if (root == null) {  return;  }
 
        // Creates an empty hashset
        HashSet<Integer> set = new HashSet<>();
 
        // Create a queue and add root to it
        LinkedList<QItem> Q = new LinkedList<QItem>();
        Q.add(new QItem(root, 0)); // Horizontal distance of root is 0
 
        // Standard BFS or level order traversal loop
        while (!Q.isEmpty())
        {
            // Remove the front item and get its details
            QItem qi = Q.remove();
            int hd = qi.hd;
            Node n = qi.node;
 
            // If this is the first node at its horizontal distance,
            // then this node is in top view
            if (!set.contains(hd))
            {
                set.add(hd);
                System.out.print(n.key + " ");
            }
 
            // Enqueue left and right children of current node
            if (n.left != null)
                Q.add(new QItem(n.left, hd-1));
            if (n.right != null)
                Q.add(new QItem(n.right, hd+1));
        }
    }
	
	/*serialize using level order traversal
	 * Note -last character in string in java is '/o' which needs to be deleted 
	 */
	
	// Encodes a tree to a single string.
	public String serialize(Node root) {
	    if(root==null){
	        return "";
	    }
	 
	    StringBuilder sb = new StringBuilder();
	 
	    LinkedList<Node> queue = new LinkedList<Node>();
	 
	    queue.add(root);
	    while(!queue.isEmpty()){
	        Node t = queue.poll();
	        if(t!=null){
	            sb.append(String.valueOf(t.key) + ",");
	            queue.add(t.left);
	            queue.add(t.right);
	        }else{
	            sb.append("#,");
	        }
	    }
	 
	    sb.deleteCharAt(sb.length()-1);
	    System.out.println(sb.toString());
	    return sb.toString();
	}
	
	/* deserialize string to binary tree using level order traversal
	 * 
	 */
	// Decodes your encoded data to tree.
	public Node deserialize(String data) {
	    if(data==null || data.length()==0)
	        return null;
	 
	    String[] arr = data.split(",");
	    Node root = new Node(Integer.parseInt(arr[0]));
	 
	 
	    LinkedList<Node> queue = new LinkedList<Node>();
	    queue.add(root);
	 
	    int i=1;
	    while(!queue.isEmpty()){
	        Node t = queue.poll();
	 
	        if(t==null)
	            continue;
	 
	        if(!arr[i].equals("#")){
	            t.left = new Node(Integer.parseInt(arr[i]));    
	            queue.offer(t.left);
	 
	        }else{
	            t.left = null;
	            queue.offer(null);
	        }
	        i++;
	 
	        if(!arr[i].equals("#")){
	            t.right = new Node(Integer.parseInt(arr[i]));    
	            queue.offer(t.right);
	 
	        }else{
	            t.right = null;
	            queue.offer(null);
	        }
	        i++;
	    }
	 
	    return root;
	}
	
	/*
	 * check if given two trees are isoMorphic or not
	 */
	public boolean isIsomorphic(Node n1, Node n2){
		if (n1 == null && n2== null)
			return true;
		if (n1 == null || n2 == null)
			return false;
		if (n1.key != n2.key)
			return false;
		
		return (isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right, n2.right)) ||
			(isIsomorphic(n1.left, n2.right) && isIsomorphic(n1.right, n2.left));
	}

	/* find all possible intereretation of integer array of digits
	 * 'a' - 1, 'b' -2, .. 'z' - 26
	 * input: {9, 1, 8}
		Output: {"iah", "ir"} 
		[2 interpretations: iah(9,1,8), ir(9,18)]
	 */
	
	/*public static Node createTree(int data, String pString, int[] arr) {
		
		//invalid input as array maps till alphabet 26
		if(data > 26)
			return null;
		
		String dataToStr = pString + alphabet[data];
		
		Node root = new Node(dataToStr);
		
		if (arr.length != 0){
			data = arr[0];
			
			int newArr[] = Arrays.copyOfRange(arr, 1, arr.length);
			
			root.left = createTree(data, dataToStr, newArr);
			
			if (arr.length > 1) {
				data = arr[0] * 10 + arr[1];
				
				newArr = Arrays.copyOfRange(arr, 2, arr.length);
			
				root.right = createTree(data, dataToStr, newArr);	
			}
		}
		return root;
	}*/
	/* Given a binary tree, where every node value is a Digit from 1-9 .
	 * Find the sum of all the numbers which are formed from root to leaf paths.
	 * Uses Preorder traversal
	 */
	public static int treeSumPath(Node node, int val){
		if (node == null) 
			return 0;
		val = val *10 + node.key;
		if (node.left == null && node.right == null)
			return val;
		return treeSumPath(node.left, val) + treeSumPath(node.right, val);
		
	}

	/*
	 * Connect nodes at same level 
	 * using constant extra space - i.e. first create nextRight for all nodes at same level before going to next level 
	 */
	/* Set next right of all descendents of p. This function makes sure that
    nextRight of nodes ar level i is set before level i+1 nodes. */
	 public void connectRecur(Node p) 
	 {
	     // Base case
	     if (p == null)
	         return;
	
	     /* Before setting nextRight of left and right children, set nextRight
	        of children of other nodes at same level (because we can access 
	        children of other nodes using p's nextRight only) */
	     if (p.nextRight != null)
	         connectRecur(p.nextRight);
	
	     /* Set the nextRight pointer for p's left child */
	     if (p.left != null)
	     {
	         if (p.right != null) 
	         {
	             p.left.nextRight = p.right;
	             p.right.nextRight = getNextRight(p);
	         } 
	         else
	             p.left.nextRight = getNextRight(p);
	
	         /* Recursively call for next level nodes.  Note that we call only
	          for left child. The call for left child will call for right child */
	         connectRecur(p.left);
	     }
	       
	     /* If left child is NULL then first node of next level will either be
	      p->right or getNextRight(p) */
	     else if (p.right != null) 
	     {
	         p.right.nextRight = getNextRight(p);
	         connectRecur(p.right);
	     } 
	     else
	         connectRecur(getNextRight(p));
	 }
	
	 /* This function returns the leftmost child of nodes at the same
	    level as p. This function is used to getNExt right of p's right child
	    If right child of p is NULL then this can also be used for 
	    the left child */
	 public Node getNextRight(Node p) 
	 {
	     Node temp = p.nextRight;
	
	     /* Traverse nodes at p's level and find and return
	      the first node's first child */
	     while (temp != null) 
	     {
	         if (temp.left != null)
	             return temp.left;
	         if (temp.right != null)
	             return temp.right;
	         temp = temp.nextRight;
	     }
	
	     // If all the nodes at p's level are leaf nodes then return NULL
	     return null;
	 }
	 
	 /*vertical sum in given binary tree. 
	  * hD = horizontal distance from root. e.g. root's hD = 0, root.lef's hD = -1, root.right's hD = 1   
	  */
	    // A wrapper over VerticalSumUtil()
	    public void VerticalSum(Node root) {
	  
	        // base case
	        if (root == null) { return; }
	  
	        // Creates an empty hashMap hM
	        HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>();
	  
	        // Calls the VerticalSumUtil() to store the vertical sum values in hM
	        VerticalSumUtil(root, 0, hM);
	  
	        // Prints the values stored by VerticalSumUtil()
	        if (hM != null) {
	            System.out.println(hM.entrySet());
	        }
	    }
	  
	    // Traverses the tree in Inoorder form and builds a hashMap hM that
	    // contains the vertical sum
	    private void VerticalSumUtil(Node root, int hD,
	                                          HashMap<Integer, Integer> hM) {
	  
	        // base case
	        if (root == null) {  return; }
	  
	        // Store the values in hM for left subtree
	        VerticalSumUtil(root.left, hD - 1, hM);
	  
	        // Update vertical sum for hD of this node
	        int prevSum = (hM.get(hD) == null) ? 0 : hM.get(hD);
	        hM.put(hD, prevSum + root.key);
	  
	        // Store the values in hM for right subtree
	        VerticalSumUtil(root.right, hD + 1, hM);
	    }
	    /*
	     * Print boundary nodes - all left nodes, all leafs, all right nodes. in circular way. Make sure leaf nodes are 
	     * not repeated 
	     */
	 // A function to do boundary traversal of a given binary tree
	    public void printBoundary(Node node) 
	    {
	        if (node != null) 
	        {
	            System.out.print(node.key + " ");
	  
	            // Print the left boundary in top-down manner.
	            printBoundaryLeft(node.left);
	  
	            // Print all leaf nodes
	            printLeaves(node.left);
	            printLeaves(node.right);
	  
	            // Print the right boundary in bottom-up manner
	            printBoundaryRight(node.right);
	        }
	    }
	    

	    // A simple function to print leaf nodes of a binary tree
	    public void printLeaves(Node node) 
	    {
	        if (node != null) 
	        {
	            printLeaves(node.left);
	  
	            // Print it if it is a leaf node
	            if (node.left == null && node.right == null)
	                System.out.print(node.key + " ");
	            printLeaves(node.right);
	        }
	    }
	  
	    // A function to print all left boundry nodes, except a leaf node.
	    // Print the nodes in TOP DOWN manner
	    public void printBoundaryLeft(Node node) 
	    {
	        if (node != null) 
	        {
	            if (node.left != null) 
	            {
	                  
	                // to ensure top down order, print the node
	                // before calling itself for left subtree
	                System.out.print(node.key + " ");
	                printBoundaryLeft(node.left);
	            } 
	            else if (node.right != null) 
	            {
	                System.out.print(node.key + " ");
	                printBoundaryLeft(node.right);
	            }
	  
	            // do nothing if it is a leaf node, this way we avoid
	            // duplicates in output
	        }
	    }
	  
	    // A function to print all right boundry nodes, except a leaf node
	    // Print the nodes in BOTTOM UP manner
	    public void printBoundaryRight(Node node) 
	    {
	        if (node != null) 
	        {
	            if (node.right != null) 
	            {
	                // to ensure bottom up order, first call for right
	                //  subtree, then print this node
	                printBoundaryRight(node.right);
	                System.out.print(node.key + " ");
	            } 
	            else if (node.left != null) 
	            {
	                printBoundaryRight(node.left);
	                System.out.print(node.key + " ");
	            }
	            // do nothing if it is a leaf node, this way we avoid
	            // duplicates in output
	        }
	    }
	    
	    /* Remove half nodes - half node means it has only one child. 
	     */
	 // Removes all nodes with only one child and returns
	    // new root (note that root may change)
	    public Node RemoveHalfNodes(Node node) 
	    {
	        if (node == null)
	            return null;
	  
	        node.left = RemoveHalfNodes(node.left);
	        node.right = RemoveHalfNodes(node.right);
	  
	        if (node.left == null && node.right == null)
	            return node;
	  
	        /* if current nodes is a half node with left
	         child NULL left, then it's right child is
	         returned and replaces it in the given tree */
	        if (node.left == null) 
	        {
	            Node new_root = node.right;
	            return new_root;
	        }
	  
	        /* if current nodes is a half node with right
	           child NULL right, then it's right child is
	           returned and replaces it in the given tree  */
	        if (node.right == null) 
	        {
	            Node new_root = node.left;
	            return new_root;
	        }
	  
	        return node;
	    }
	    
	    /* change binary tree so every node stores sum of all nodes in left subtree */
	    
	    public int updateTree(Node root){
	    	
	    	//base cases
	    	if (root == null)
	    		return 0;
	    	
	    	System.out.println("current root=" +root.key);
	    	if (root.left == null && root.right == null)
	    		return root.key;
	    	
	    	//update left and right subtree
	    	System.out.println("calling left node=" +root.left.key);
	    	int leftSum = updateTree(root.left);
	    	System.out.println("calling right node=" +root.right.key);
	    	int rightSum = updateTree(root.right);
	    	
	    	//add left sum to current node
	    	System.out.println("root and left before addition=" +root.key +" and " +leftSum);
	    	root.key += leftSum;
	    	
	    	System.out.println("returning value =" +root.key +"+" +rightSum);
	    	//return sum of values under root
	    	return root.key + rightSum;
	    	
	    }
	    
	    /* Evaluate tree expression. Also called as Expression tree. Generally all leaf nodes will have integer values
	     * and internal nodes will have operators
	     * Mathematical expression Some nodes will have maths operators
	     * In this case key of node should be String or character
	     */
	    
	    public int evaluateExpression(StringNode root) {
	    	String operatots = "+-*/";
	    	
	    	if (root == null)
	    		return 0;
	    	
	    	if (root.left == null && root.right == null)
	    		return Integer.valueOf(root.key);
	    	
	  //  	Stack<Integer> stk = new Stack<Integer>();
	    	
	    	int lValue = evaluateExpression(root.left);
	    	int rValue = evaluateExpression(root.right);
	    	
	    	if (operatots.contains(root.key)){
	     		
	    		switch (root.key){
	    		case "+" :
	    			      return (lValue+rValue);
	    		case "-" :
	    			      return (lValue-rValue);
	    		case "*" :
	    			      return (lValue*rValue);
	    		case "/" :
	    			      return (lValue/rValue);
	    		}
	    		
	    		/*int a = stk.pop();
	    		int b = stk.pop();
	    		
	    		switch (root.key){
	    		case "+" :
	    			      stk.push(a+b);
	    			      break;
	    		case "-" :
	    			      stk.push(a-b);
	    			      break;
	    		case "*" :
	    			      stk.push(a*b);
	    			      break;
	    		case "/" :
	    			      stk.push(a/b);
	    			      break;
	    		}*/
	    		
	    	} else {
	    		return Integer.valueOf(root.key);
	    	}
	    	//instead we can assign above evaluations to result and return that here
	    	return 0;
	    }
	    /* Distance between two nodes is given by this formaula
	     * Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) where lca = lowest common ancestor
	     * 
	     */
	    
	    /* Construct Binary Tree from given Parent Array representation
	     * The value of the root node index would always be -1 as there is no parent for root.
	     */
	    /* Creates tree from parent[0..n-1] and returns root of 
	       the created tree */
	    Node createTree(int parent[], int n) 
	    {    
	        // Create an array created[] to keep track
	        // of created nodes, initialize all entries
	        // as NULL
	        Node[] created = new Node[n];
	        for (int i = 0; i < n; i++) 
	            created[i] = null;
	  
	        for (int i = 0; i < n; i++)
	            createNode(parent, i, created);
	  
	        return root;
	    }
	    
	    // Creates a node with key as 'i'.  If i is root, then it changes
	    // root.  If parent of i is not created, then it creates parent first
	    void createNode(int parent[], int i, Node created[]) 
	    {
	        // If this node is already created
	        if (created[i] != null)
	            return;
	  
	        // Create a new node and set created[i]
	        created[i] = new Node(i);
	  
	        // If 'i' is root, change root pointer and return
	        if (parent[i] == -1) 
	        {
	            root = created[i];
	            return;
	        }
	  
	        // If parent is not created, then create parent first
	        if (created[parent[i]] == null)
	            createNode(parent, parent[i], created);
	  
	        // Find parent pointer
	        Node p = created[parent[i]];
	  
	        // If this is first child of parent
	        if (p.left == null)
	            p.left = created[i];
	        else // If second child
	          
	            p.right = created[i];
	    }
	    
	    /* Find Count of Single Valued Subtrees
	     */
	    // This function increments count by number of single 
	    // valued subtrees under root. It returns true if subtree 
	    // under root is Singly, else false.
	    public static int count = 0; 
	    boolean countSingleRec(Node node) 
	    {
	        // Return false to indicate NULL
	        if (node == null)
	            return true;
	          
	        // Recursively count in left and right subtrees also
	        boolean left = countSingleRec(node.left);
	        boolean right = countSingleRec(node.right);
	  
	        // If any of the subtrees is not singly, then this
	        // cannot be singly.
	        if (left == false || right == false)
	            return false;
	  
	        // If left subtree is singly and non-empty, but data
	        // doesn't match
	        if (node.left != null && node.key != node.left.key)
	            return false;
	  
	        // Same for right subtree
	        if (node.right != null && node.key != node.right.key)
	            return false;
	  
	        // If none of the above conditions is true, then
	        // tree rooted under root is single valued, increment
	        // count and return true.
	        count++;
	        return true;
	    }
}

 class Node  {
	
	 int key;
	 Node left, right, nextRight;
	 
	 public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getNextRight() {
		return nextRight;
	}

	public void setNextRight(Node nextRight) {
		this.nextRight = nextRight;
	}

	Node(int value){
		 key = value;
		 left = right = nextRight = null;
	 }
	 
	 Node () {
		 key = 0;
		 left = right = null;
	 }
}
 
 class StringNode {
	 
	 String key;
	 StringNode left, right;
	 
	 public StringNode(String value){
		 this.key = value;
		 left = right = null;
	 }
 }
