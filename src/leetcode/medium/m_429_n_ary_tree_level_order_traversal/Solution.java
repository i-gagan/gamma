package leetcode.medium.m_429_n_ary_tree_level_order_traversal;

//https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                Node node = queue.poll();
                assert node != null;
                levelNodes.add(node.val);

                List<Node> children = node.children;
                queue.addAll(children);
            }
            result.add(levelNodes);
        }
        return result;
    }
}
