package graph.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    public TreeNode root;

    public void Tree() {
    }

    public TreeNode insertInBST(TreeNode node, int data) {
        if (node == null) {
            node = new TreeNode(data);
            return node;
        } else {
            if (data < node.val) {
                node.left = insertInBST(node.left, data);
            } else if (data > node.val) {
                node.right = insertInBST(node.right, data);
            }
        }
        return node;
    }

    public TreeNode insertInBT(TreeNode node, int[] input) {
        node = new TreeNode(input[0]);
        node.left = new TreeNode(input[1]);
        node.right = new TreeNode(input[2]);
        node.left.left = new TreeNode(input[3]);
        node.left.right = new TreeNode(input[4]);
        node.right.left = new TreeNode(input[5]);
        node.right.right = new TreeNode(input[6]);
        node.left.left.left = new TreeNode(input[7]);
        return node;
    }

    public TreeNode buildTree(Integer[] nodes) {
        int nodeIndex = 1;
        TreeNode root = new TreeNode(nodes[0], nodeIndex);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty() && nodeIndex < nodes.length) {
            TreeNode node = queue.poll();

            int leftNodeIndex = 2 * node.index - 1;
            int rightNodeIndex = 2 * node.index;

            if (leftNodeIndex < nodes.length && nodes[leftNodeIndex] != null) {
                node.left = new TreeNode(nodes[leftNodeIndex], leftNodeIndex + 1);
                queue.add(node.left);
                nodeIndex++;
            }

            if (rightNodeIndex < nodes.length && nodes[rightNodeIndex] != null) {
                node.right = new TreeNode(nodes[rightNodeIndex], rightNodeIndex + 1);
                queue.add(node.right);
                nodeIndex++;
            }
        }
        return root;
    }
}
