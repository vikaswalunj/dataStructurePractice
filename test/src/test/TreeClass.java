package test;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class TreeClass {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        // use tree level order traversal to serialize the tree
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            if(n == null) {
                sb.append("null ");
            } else{
                sb.append(n.val + " ");
                q.add(n.left);
                q.add(n.right);
            }
        }
        return sb.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == "") return null;
        String[] vals = data.split(" ");
        if(vals.length == 0) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        q.add(root);
        TreeNode p = null;
        String val;
        for(int i = 1; i < vals.length; ) {
            p = q.poll();
            val = vals[i++];
            if(val.equals("null")) {
                p.left = null;
            } else {
                p.left = new TreeNode(Integer.valueOf(val));
                q.add(p.left);
            }
            if(i < vals.length) {
                val = vals[i++];
                if(val.equals("null")) {
                    p.right = null;
                } else {
                    p.right = new TreeNode(Integer.valueOf(val));
                    q.add(p.right);
                }
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));