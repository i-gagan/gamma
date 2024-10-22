package leetcode.medium.m_1166_design_file_system;

//https://leetcode.com/problems/design-file-system/description/

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<String, TrieNode> children = new HashMap<>();
    int value;

    TrieNode(int value) {
        this.value = value;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode(-1);
    }

    boolean insert(String path, int value) {
        TrieNode node = root;
        String[] parts = path.split("/");

        for (int i = 1; i < parts.length - 1; i++) {
            String part = parts[i];

            if (!node.children.containsKey(part)) {
                return false;
            }
            node = node.children.get(part);
        }
        if (node.children.containsKey(parts[parts.length - 1])) {
            return false;
        }
        node.children.put(parts[parts.length - 1], new TrieNode(value));
        return true;
    }

    int search(String path) {
        TrieNode node = root;

        String[] parts = path.split("/");

        for (int i = 1; i < parts.length; ++i) {
            String part = parts[i];
            if (!node.children.containsKey(part)) {
                return -1;
            }
            node = node.children.get(part);
        }
        return node.value;
    }
}

class FileSystem {
    Trie trie;

    public FileSystem() {
        trie = new Trie();
    }

    public boolean createPath(String path, int value) {
        return trie.insert(path, value);
    }

    public int get(String path) {
        return trie.search(path);
    }

    public static void main(String[] args) {
//        String[] sequence = {"FileSystem", "createPath", "get"};
//        String[][] input = {{}, {"/a", "1"}, {"/a"}};
        String[] sequence = {"FileSystem", "createPath", "createPath", "get", "createPath", "get"};
        String[][] input = {{}, {"/leet", "1"}, {"/leet/code", "2"}, {"/leet/code"}, {"/c/d", "1"}, {"/c"}};

        FileSystem fileSystem = null;
        int inputIndex = 0;
        for (String s : sequence) {
            switch (s) {
                case "FileSystem":
                    fileSystem = new FileSystem();
                    inputIndex++;
                    break;
                case "createPath":
                    assert fileSystem != null;
                    System.out.println(fileSystem.createPath(input[inputIndex][0], Integer.parseInt(input[inputIndex][1])));
                    inputIndex++;
                    break;

                case "get":
                    assert fileSystem != null;
                    System.out.println(fileSystem.get(input[inputIndex][0]));
                    inputIndex++;
                    break;
            }
        }
    }
}