package leetcode.medium.m_107_binary_tree_level_order_traversal_II;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

import java.util.*;

class Solution {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ;
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.addFirst(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] nodes = {3, 9, 20, null, null, 15, 7};
        Tree tree = new Tree();
        TreeNode root = tree.buildTree(nodes);
        System.out.println(levelOrderBottom(root));
    }
}
