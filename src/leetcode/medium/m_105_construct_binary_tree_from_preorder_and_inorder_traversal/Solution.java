package leetcode.medium.m_105_construct_binary_tree_from_preorder_and_inorder_traversal;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

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
    private static int preorderIdx = 0;

    private static TreeNode construct(int[] preorder, HashMap<Integer, Integer> map, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootVal = preorder[preorderIdx];
        int inorderRootIndex = map.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        preorderIdx++;
        root.left = construct(preorder, map, left, inorderRootIndex - 1);
        root.right = construct(preorder, map, inorderRootIndex + 1, right);
        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        return construct(preorder, map, 0, len - 1);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
    }
}


