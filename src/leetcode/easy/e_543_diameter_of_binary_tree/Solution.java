package leetcode.easy.e_543_diameter_of_binary_tree;

//https://leetcode.com/problems/diameter-of-binary-tree/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

class Result {
    int maxDiameter;
}

class Solution {
    public static int diameterOfBinaryTree(TreeNode root) {
        Result result = new Result();
        solve(root, result);
        return result.maxDiameter;
    }

    private static int solve(TreeNode node, Result result) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = solve(node.left, result);
            int rightHeight = solve(node.right, result);

            int diameter = leftHeight + rightHeight;
            result.maxDiameter = Math.max(diameter, result.maxDiameter);

            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public static void main(String[] args) {
        Integer[] nodes = {1, 2};
        Tree tree = new Tree();
        TreeNode root = tree.buildTree(nodes);
        System.out.println(diameterOfBinaryTree(root));
    }
}