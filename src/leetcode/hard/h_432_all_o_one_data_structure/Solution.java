package leetcode.hard.h_432_all_o_one_data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {
    private final Node root = new Node();
    private final Map<String, Node> hashMap = new HashMap<>();

    public AllOne() {
        root.next = root;
        root.prev = root;
    }

    public void inc(String key) {
        if (!hashMap.containsKey(key)) {
            if (root.next == root || root.next.cnt > 1) {
                hashMap.put(key, root.insert(new Node(key, 1)));
            } else {
                root.next.keys.add(key);
                hashMap.put(key, root.next);
            }
        } else {

            Node current = hashMap.get(key);
            Node next = current.next;
            if (next == root || next.cnt > current.cnt + 1) {
                hashMap.put(key, current.insert(new Node(key, current.cnt + 1)));
            } else {
                next.keys.add(key);
                hashMap.put(key, next);
            }
            // Remove key from current Node and check if it should be deleted
            current.keys.remove(key);
            if (current.keys.isEmpty()) {
                current.remove();
            }
        }
    }

    public void dec(String key) {
        // Decrease the count for the key
        Node current = hashMap.get(key);
        if (current.cnt == 1) {
            // If count goes to 0, remove key from system
            hashMap.remove(key);
        } else {
            // Move key to the previous Node or create a new Node
            Node prev = current.prev;
            if (prev == root || prev.cnt < current.cnt - 1) {
                hashMap.put(key, prev.insert(new Node(key, current.cnt - 1)));
            } else {
                prev.keys.add(key);
                hashMap.put(key, prev);
            }
        }

        // Remove key from current Node and remove Node if empty
        current.keys.remove(key);
        if (current.keys.isEmpty()) {
            current.remove();
        }
    }

    public String getMaxKey() {
        // Get and return a key with the maximum count
        if (root.prev == root) return ""; // Handle case with no keys
        return root.prev.keys.iterator().next();
    }

    public String getMinKey() {
        // Get and return a key with the minimum count
        if (root.next == root) return ""; // Handle case with no keys
        return root.next.keys.iterator().next();
    }
}

class Node {
    Node prev;
    Node next;
    int cnt;
    Set<String> keys = new HashSet<>();

    public Node() {
        this("", 0);
    }

    public Node(String key, int cnt) {
        this.cnt = cnt;
        keys.add(key);
    }


    public Node insert(Node node) {
        node.prev = this;
        node.next = this.next;
        node.prev.next = node;
        node.next.prev = node;
        return node;
    }

    public void remove() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
    }
}

