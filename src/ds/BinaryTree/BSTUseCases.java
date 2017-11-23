package ds.BinaryTree;

public class BSTUseCases {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//**********create sample Tree********//
		BinaryTree bt = new BinaryTree();
		bt.root = new Node(50);
		bt.root.left = new Node(40);
		bt.root.right = new Node(60);
		bt.root.left.left = new Node(30);
		bt.root.left.right = new Node (45);
		bt.root.right.left = new Node (55);
		bt.root.right.right = new Node (70);
		bt.root.left.left.left = new Node(20);
		bt.root.left.left.right = new Node(35);
		
		bt.searchBST(bt.root, 45);
	}	
}
