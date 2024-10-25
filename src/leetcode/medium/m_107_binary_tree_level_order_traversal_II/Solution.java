package leetcode.medium.m_107_binary_tree_level_order_traversal_II;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();

        queue.offerLast(root);

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = queue.size(); i > 0; --i) {
                TreeNode node = queue.pollFirst();

                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.offerLast(node.left);
                }

                if (node.right != null) {
                    queue.offerLast(node.right);
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
