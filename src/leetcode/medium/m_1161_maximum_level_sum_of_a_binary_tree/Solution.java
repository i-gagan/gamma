package leetcode.medium.m_1161_maximum_level_sum_of_a_binary_tree;

//https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

import java.util.*;
class Solution {
    public static int maxLevelSum(TreeNode root) {
        //return maxLevelSum1(root);
        return maxLevelSum2(root);
    }

    public static int maxLevelSum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minLevel = 1, maxSum = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int currentLevelSum = 0, currentLevel = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            currentLevelSum = 0;

            for (int i = 0; i < queueSize; i++) {
                TreeNode temp = queue.poll();
                currentLevelSum = currentLevelSum + temp.val;

                if (temp.left != null) {
                    queue.add(temp.left);
                }

                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            if (maxSum < currentLevelSum) {
                maxSum = currentLevelSum;
                minLevel = currentLevel;
            }
            currentLevel++;
        }
        return minLevel;
    }

    public static int maxLevelSum2(TreeNode root) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        maxLevelSumUtil(root, 1, hashMap);
        int maxSum = Integer.MIN_VALUE, minLevel = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > maxSum) {
                maxSum = entry.getValue();
                minLevel = entry.getKey();
            }
        }
        return minLevel;
    }

    public static void maxLevelSumUtil(TreeNode node, int currentLevel, HashMap<Integer, Integer> hashMap) {
        if (node ==  null) {
            return;
        }
        hashMap.put(currentLevel, hashMap.getOrDefault(currentLevel, 0) + node.val);
        maxLevelSumUtil(node.right, currentLevel + 1, hashMap);
        maxLevelSumUtil(node.left, currentLevel + 1, hashMap);
    }

    public static void main(String[] args) {
        //int[] input = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] input = {-100, -200, -300, -20, -5, -10, 0, 0};

        Tree bt = new Tree();
        bt.root = bt.insertInBT(bt.root, input);
        System.out.println(maxLevelSum(bt.root));
    }
}
