package graph.tree;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public int index;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, int index) {
        this.val = val;
        this.index = index;
    }
}
