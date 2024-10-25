package leetcode.medium.m_129_sum_root_to_leaf_numbers;

//https://leetcode.com/problems/sum-root-to-leaf-numbers/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

class Result {
    int totalSum;
}

class Solution {
    public static int sumNumbers(TreeNode root) {
        Result result = new Result();
        solve(root, 0, result);
        return result.totalSum;
    }

    private static void solve(TreeNode root, int rootVal, Result result) {
        if (root == null) {
            return;
        }

        rootVal = root.val + rootVal;

        if (root.left == null && root.right == null) {
            result.totalSum = result.totalSum + rootVal;
        } else {
            if (root.left != null) {
                solve(root.left, rootVal * 10, result);
            }
            if (root.right != null) {
                solve(root.right, rootVal * 10, result);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nodes = {1, 2, 3};
        Tree tree = new Tree();
        TreeNode root = tree.buildTree(nodes);
        System.out.println(sumNumbers(root));
    }
}
