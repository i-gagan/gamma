package companies.confluent;

import java.io.*;
        import java.util.*;
        import java.text.*;
        import java.math.*;
        import java.util.regex.*;

/** Example
 *  window size = 1 hour
 *  0:00:00 - put("foo", 42)
 *  0:05:00 - put("bar", 76)
 *  0:50:00 - get("foo") => 42, get("bar") => 76, getAverage() => 59
 *  1:02:00 - get("foo") => nothing, get("bar") => 76, getAverage() => 76
 *.  1:03:00  put("foo", 55)
 *  1:06:00 - get("foo") => nothing, get("bar") => nothing, getAverage() => undefined
 */


// Deque / Doubly Linked List -

// HashMap<Key, Node>

// foo ->. NOde

// Input -> Monotically increasing


// {
//     String key,
//     Integer value;
//     Integer timeStamp;
// }



class Node {
    String key;
    long value;
    long timeInMillis;

    Node next;
    Node prev;

    Node() {

    }

    Node(String key, long value) {
        this.key = key;
        this.value = value;
        this.timeInMillis = System.currentTimeMillis();
    }
}

class DoublyList {
    Node head;
    Node tail;
    int size;

    DoublyList() {
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node node) {
        Node nextNode = head.next;

        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
        size++;
    }

    public void addLast(Node node) {

        Node prevNode = tail.prev;

        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;

        size++;
    }

    public void delete(Node node) {

        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        size--;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node temp = head.next;
        while (temp != null && temp != tail) {
            stringBuilder.append(temp.key + " ");
            temp = temp.next;
        }
        return stringBuilder.toString();
    }
}



class WindowedMap {
    /** creates a new map of the specified size */

    long windowSizeMs;

    DoublyList doublyList;
    Map<String, Node> keyToNodeMap;

    long totalSum;

    public WindowedMap(long windowSizeMs) {
        this.windowSizeMs = windowSizeMs;

        this.doublyList = new DoublyList();
        this.keyToNodeMap = new HashMap<>();
        this.totalSum = 0;
    }

    /** puts or replaces a previous key value pairing */
    public void put(String key, long value) {
        cleanEntries();

        if (keyToNodeMap.containsKey(key)) {
            Node nodeToBeDeleted = keyToNodeMap.get(key);
            doublyList.delete(nodeToBeDeleted);

            totalSum = totalSum - nodeToBeDeleted.value;
        }
        Node newNode = new Node(key, value);
        keyToNodeMap.put(key, newNode);
        doublyList.addLast(newNode);

        totalSum = totalSum + newNode.value;
    }

    /** gets the most recent value for the key */
    public long get(String key) {
        cleanEntries();

        if (keyToNodeMap.containsKey(key)) {
            Node node = keyToNodeMap.get(key);
            return node.value;
        }
        return -1;
    }

    /** gets the average for all values within the window */
    public double getAverage() {
        cleanEntries();
        if (doublyList.size == 0) {
            return 0;
        }
        return totalSum / doublyList.size;
    }

    public void cleanEntries() {
        long currentTimeInMillis = System.currentTimeMillis();

        //System.out.println("Before " + doublyList);
        Node temp = doublyList.head.next;
        while (temp != null && temp != doublyList.tail && currentTimeInMillis - temp.timeInMillis >= windowSizeMs) {

            doublyList.delete(temp);
            keyToNodeMap.remove(temp.key);
            totalSum -= temp.value;
            temp = temp.next;
        }
        //System.out.println("After " + doublyList);
    }
}


public class Solution {
    public static void main(String[] args) {
        long windowSizeMs = 1000; // 1 hr
        WindowedMap windowedMap = new WindowedMap(windowSizeMs);
        windowedMap.put("foo", 42);
        windowedMap.put("bar", 76);
        System.out.println(windowedMap.get("foo"));
        System.out.println(windowedMap.get("bar"));
        System.out.println(windowedMap.getAverage());

        System.out.println("Thread Sleep");
        try {
            Thread.sleep(2000);
        } catch(Exception e) {

        }


        System.out.println(windowedMap.get("foo"));
        System.out.println(windowedMap.get("bar"));
        System.out.println(windowedMap.getAverage());

        windowedMap.put("foo", 55);
        System.out.println(windowedMap.get("foo"));
        System.out.println(windowedMap.get("bar"));
        System.out.println(windowedMap.getAverage());
    }
}
