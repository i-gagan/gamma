package leetcode.medium.m_113_path_sum_II;

//https://leetcode.com/problems/path-sum-ii/description/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        solve(root, targetSum, list, result);
        return result;
    }

    public static void solve(TreeNode root, int targetSum, List<Integer> answerList, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        answerList.add(root.val);

        if (root.left == null && root.right == null) {
            if (targetSum - root.val == 0) {
                List<Integer> list = new ArrayList<>(answerList);
                result.add(list);
            }
        }

        if (root.left != null) {
            solve(root.left, targetSum - root.val, answerList, result);
        }

        if (root.right != null) {
            solve(root.right, targetSum - root.val, answerList, result);
        }
        answerList.remove(answerList.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(pathSum(root, 8));
    }
}
