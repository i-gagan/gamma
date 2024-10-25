package leetcode.medium.m_314_binary_tree_vertical_order_traversal;

//https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/

import java.util.ArrayList;
import java.util.Comparator;
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
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);

        nodes.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] node1, int[] node2) {
                if (node1[0] != node2[0]) {
                    return Integer.compare(node1[0], node2[0]);
                }

                return Integer.compare(node1[1], node2[1]);
            }
        });

        List<List<Integer>> result = new ArrayList<>();
        int previousColumn = Integer.MIN_VALUE;
        for (int[] currentNode : nodes) {
            if (previousColumn != currentNode[0]) {
                result.add(new ArrayList<>());
                previousColumn = currentNode[0];
            }

            result.get(result.size() - 1).add(currentNode[2]);
        }
        return result;
    }

    private static void dfs(TreeNode node, int column, int row, List<int[]> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(new int[]{column, row, node.val});
        dfs(node.left, column - 1, row + 1, nodes);
        dfs(node.right, column + 1, row + 1, nodes);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        System.out.println(verticalTraversal(treeNode));
    }
}
