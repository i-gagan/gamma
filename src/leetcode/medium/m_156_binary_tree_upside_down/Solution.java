package leetcode.medium.m_156_binary_tree_upside_down;

//https://leetcode.com/problems/binary-tree-upside-down/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

class Solution {
    public static TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.right = root;
        root.left.left = root.right;

        root.left = null;
        root.right = null;

        return newRoot;
    }

    public static void main(String[] args) {
        Integer[] nodes = {3, 9, 20, null, null, 15, 7};
        Tree tree = new Tree();
        TreeNode root = tree.buildTree(nodes);
        System.out.println(upsideDownBinaryTree(root));
    }
}
