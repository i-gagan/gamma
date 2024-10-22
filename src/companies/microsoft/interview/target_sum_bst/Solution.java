package companies.microsoft.interview.target_sum_bst;

class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class Answer {
    boolean answer;
}

public class Solution {

    public static void main(String[] args) {
//        Node root = new Node(5);
//        root.left = new Node(3);
//        root.right = new Node(7);
//        root.left.left = new Node(1);
//        root.left.right = new Node(4);
//        root.right.right = new Node(8);

        Node root = new Node(8);
        root.left = new Node(6);
        //root.right = new Node(7);
        root.left.left = new Node(5);
        root.left.right = new Node(7);
        root.left.left.left = new Node(3);


        boolean result = isPathExists(root, 8, 0);
        System.out.println("Is Path Exists - " + result);
        result = isPathExists(root, 9, 0);
        System.out.println("Is Path Exists - " + result);
        result = isPathExists(root, 12, 0);
        System.out.println("Is Path Exists - " + result);
        result = isPathExists(root, 10, 0);
        System.out.println("Is Path Exists - " + result);
        result = isPathExists(root, 8, 0);
        System.out.println("Is Path Exists - " + result);

        Answer answer = new Answer();
        answer.answer = false;
        int sum = isPathExistsNoDuplicates(root, 7, 0, true, answer);

        System.out.println("Path Exists With No Duplicates " + sum + " " + answer.answer);
    }

    public static int isPathExistsNoDuplicates(Node root, int target, int currentSum, boolean isRoot, Answer answer) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) { // leaf node
            if (currentSum + root.value == target) {
                return target;
            }
        }

        int leftSum = isPathExistsNoDuplicates(root.left, target, root.value + currentSum, false, answer);
        int righSum = isPathExistsNoDuplicates(root.right, target, root.value + currentSum, false, answer);

        if (righSum != 0 && leftSum == righSum) {
            System.out.println(root.value);
            answer.answer = true;
        }
        return leftSum + righSum + root.value;
    }

    public static boolean isPathExists(Node root, int target, int currentSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) { // leaf node
            if (currentSum + root.value == target) {
                return true;
            }
        }

        boolean leftPath = isPathExists(root.left, target, root.value + currentSum);
        boolean rightPath = isPathExists(root.right, target, root.value + currentSum);

        return leftPath || rightPath;
    }
}

//         8
//     5       9
// 4

//if there exists 2 paths, sum == target, no elements in common except root node


// BST
// positive integer

// exist a pathre