package leetcode.easy.e_112_path_sum;

//https://leetcode.com/problems/path-sum/description/

import graph.tree.*;

class Solution {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return solve(root, targetSum);
    }

    private static boolean solve(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        targetSum = targetSum - root.val;

        if (root.left == null && root.right== null && targetSum == 0) {
            return true;
        } else {
            boolean left = solve(root.left, targetSum);
            boolean right = solve(root.right, targetSum);
            return left || right;
        }
    }

    public static void main(String[] args) {
        Integer[] nodes = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        Tree tree = new Tree();
        TreeNode root = tree.buildTree(nodes);
        System.out.println(hasPathSum(root, 22));
    }
}
