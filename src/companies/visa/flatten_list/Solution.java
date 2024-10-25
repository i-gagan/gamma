package companies.visa.flatten_list;

import java.util.*;

class Node {
    int value;
    Node next;
    Node down;

    public Node(int value) {
        this.value = value;
        this.next = null;
        this.down = null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return value + " ";
    }
}

class Solution {
    public static void main(String[] arg) {

        Node head = new Node(1);
        head.down = new Node(4);
        head.down.down = new Node(6);
        head.down.down.down = new Node(8);

        head.next = new Node(2);
        head.next.down = new Node(3);
        head.next.down.down = new Node(7);

        head.next.next = new Node(5);
        head.next.next.down = new Node(9);
        head.next.next.down.down = new Node(12);

        head.next.next.next = new Node(10);
        head.next.next.next.down = new Node(11);

        List<Node> resultList = getFlattenList(head);

        System.out.println("Result = " + resultList);
    }

    public static List<Node> getFlattenList(Node head) {
        List<Node> resultList = new ArrayList<>();

        PriorityQueue<Node> minHeapNodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.value, n2.value);
            }
        });

        Node temp = head;

        while (temp != null) {
            minHeapNodes.add(temp);
            temp = temp.next;
        }

        while (!minHeapNodes.isEmpty()) {
            Node minNode = minHeapNodes.poll();
            resultList.add(minNode);
            minNode = minNode.down;
            if (minNode != null) {
                minHeapNodes.add(minNode);
            }
        }

        return resultList;
    }
}
