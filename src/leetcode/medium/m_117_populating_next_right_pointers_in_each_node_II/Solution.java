package leetcode.medium.m_117_populating_next_right_pointers_in_each_node_II;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public void printSiblings(Node node) {
        if (node == null) {
            return;
        }

        if (node.next != null) {
            System.out.println("(" + node.val + ", " + node.next.val + ")");
        } else {
            System.out.println("(" + node.val + ")");
        }

        printSiblings(node.left);
        printSiblings(node.right);
    }

    @Override
    public String toString() {
        return val + "";
    }
}

class Solution {
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Node prevNode = queue.poll();
            if (prevNode.left != null) {
                queue.add(prevNode.left);
            }
            if (prevNode.right != null) {
                queue.add(prevNode.right);
            }

            for (int i = 1; i < size; i++) {
                Node currentNode = queue.poll();
                if (prevNode != null) {
                    prevNode.next = currentNode;
                }
                if (currentNode != null) {
                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                }
                prevNode = currentNode;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println(connect(root));
        root.printSiblings(root);
    }
}