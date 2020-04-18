package test;

public class TestClass {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //**********create sample Tree********//
        TreeClass bt = new TreeClass();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.right = null;
        bt.root.right.left = new Node(6);
        bt.root.right.right = new Node(7);
        bt.root.left.left.left = null;
        bt.root.left.left.right = new Node(9);

        System.out.println((bt.root));
    }
}
