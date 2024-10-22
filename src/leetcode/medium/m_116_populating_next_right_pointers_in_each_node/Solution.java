package leetcode.medium.m_116_populating_next_right_pointers_in_each_node;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/

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
}

class Solution {
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            }
        }
        if (root.right != null) {
            if (root.next != null && root.next.left != null) {
                root.right.next = root.next.left;
            }
        }

        connect(root.left);
        connect(root.right);

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
