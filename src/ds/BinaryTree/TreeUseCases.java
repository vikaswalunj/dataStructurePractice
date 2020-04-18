package ds.BinaryTree;

public class TreeUseCases {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//**********create sample Tree********//
		BinaryTree bt = new BinaryTree();
		bt.root = new Node(1);
		bt.root.left = new Node(2);
		bt.root.right = new Node(3);
		bt.root.left.left = new Node(4);
		bt.root.left.right = null;
		bt.root.right.left = new Node (6);
		bt.root.right.right = new Node (7);
		bt.root.left.left.left = null;
		bt.root.left.left.right = new Node(9);

		System.out.println(bt.serialize(bt.root));

		//bt.PostOrder(bt.root);
		//bt.printLevelOrder(bt.root);
		
		//bt.printkdistanceNode(bt.root, bt.root.left.left, 2);
		/*int res = bt.updateTree(bt.root);
		System.out.println("res=" +res);*/
		//bt.height(bt.root);
		/*print nodes at distance k */
		//bt.printDistantNodes(bt.root, 5);
		/*get maximum width*/
		//bt.getMaxWidth(bt.root);
		
		/*clone bt tree*/
		/*Node cNode = new Node();
		bt.cloneMyTree(bt.root, cNode);
		bt.printTree(cNode);*/
		
		/*bt.printLevelOrder(bt.root);
		
		 test InOrder 
		bt.InOrderRecur(bt.root);
		System.out.println("in order with stack");
		bt.InOrderStack(bt.root);
		System.out.println("print pre order");
		bt.PreOrder(bt.root);
		System.out.println("print post order");
		bt.PostOrder(bt.root);*/
		
		//5 6 3 1 2 4 - 6, 2, 4 = 3
		//9 7 5 3 1 - 5 , 7, 20 = -1
		int values[] = {5, 6, 3, 1, 2, 4};
		int result = bt.getDistance(values, 6, 2, 4);
		System.out.println("result is=" +result);
		
	}

}
