package leetcode.medium.m_666_path_sum_IV;

//https://leetcode.com/problems/path-sum-iv/description/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int index;

    public TreeNode(int val, int index) {
        this.val = val;
        this.index = index;
    }

    @Override
    public String toString() {
        return "val " + val + " index " + index;
    }
}

class Result {
    int totalSum;
}

class Solution {
    public static int pathSum(int[] nums) {
        int[] nodes = new int[32];
        Arrays.fill(nodes, -1);

        for (int num : nums) {
            int depth = num / 100;
            int pos = (num % 100) / 10;
            int value = (num % 100) % 10;

            int depthStartIndex = (int) Math.pow(2, depth - 1) - 1;
            int index = depthStartIndex + pos - 1;
            nodes[index] = value;
        }

        TreeNode root = buildTree(nodes);

        Result answer = new Result();

        findAllPathSum(root, 0, answer);
        return answer.totalSum;
    }

    private static void findAllPathSum(TreeNode root, int currentSum, Result result) {
        if (root == null) {
            return;
        }

        currentSum = currentSum + root.val;

        if (root.left == null && root.right == null) {
            result.totalSum = result.totalSum + currentSum;
        } else {
            findAllPathSum(root.left, currentSum, result);
            findAllPathSum(root.right, currentSum, result);
        }
    }

    private static TreeNode buildTree(int[] nodes) {
        int nodeIndex = 1;
        TreeNode root = new TreeNode(nodes[0], nodeIndex);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty() && nodeIndex < nodes.length) {
            TreeNode node = queue.poll();

            int leftNodeIndex = 2 * node.index - 1;
            int rightNodeIndex = 2 * node.index;

            if (nodes[leftNodeIndex] != -1) {
                node.left = new TreeNode(nodes[leftNodeIndex], leftNodeIndex + 1);
                queue.add(node.left);
                nodeIndex++;
            }

            if (nodes[rightNodeIndex] != -1) {
                node.right = new TreeNode(nodes[rightNodeIndex], rightNodeIndex + 1);
                queue.add(node.right);
                nodeIndex++;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] input = {113, 229, 330, 466};
        System.out.println(pathSum(input));
    }
}

