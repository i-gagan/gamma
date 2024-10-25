package leetcode.medium.m_545_boundary_of_binary_tree;

//https://leetcode.com/problems/boundary-of-binary-tree/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;


class Solution {
    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);

        getLeftBoundaryNodes(root.left, list);
        //System.out.println(list);

        getLeafNodes(root.left, list);
        getLeafNodes(root.right, list);

        //System.out.println(list);

        getRightBoundaryNodes(root.right, list);
        //System.out.println(list);

        return list;
    }

    private static void getLeftBoundaryNodes(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            list.add(node.val);
            getLeftBoundaryNodes(node.left, list);
        } else if (node.right != null) {
            list.add(node.val);
            getLeftBoundaryNodes(node.right, list);
        }
    }

    private static void getRightBoundaryNodes(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            getRightBoundaryNodes(node.right, list);
            list.add(node.val);
        } else if (node.left != null) {
            getRightBoundaryNodes(node.left, list);
            list.add(node.val);
        }
    }

    private static void getLeafNodes(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        getLeafNodes(node.left, list);
        getLeafNodes(node.right, list);
    }

    public static void main(String[] args) {
        Integer[] nodes = {1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10};
        Tree tree = new Tree();
        TreeNode root = tree.buildTree(nodes);
        System.out.println(boundaryOfBinaryTree(root));
    }
}