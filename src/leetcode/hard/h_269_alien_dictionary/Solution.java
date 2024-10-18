package leetcode.hard.h_269_alien_dictionary;

//https://leetcode.com/problems/alien-dictionary/description/

import java.util.*;

class Solution {
    public static String alienOrder(String[] words) {
        Map<Character, List<Character>> adjacencyListMap = new HashMap<>();
        int[] inDegree = new int[26];

        Arrays.fill(inDegree, -1);

        int charactersUsedCount = 0;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (inDegree[c - 'a'] == -1) {
                    inDegree[c - 'a'] = 0;
                    charactersUsedCount++;
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char char1 = word1.charAt(j);
                char char2 = word2.charAt(j);

                if (char1 != char2) {
                    adjacencyListMap.putIfAbsent(char1, new ArrayList<>());
                    adjacencyListMap.get(char1).add(char2);
                    inDegree[char2 - 'a']++;
                    break;
                }
            }
        }
        System.out.println(adjacencyListMap);

        StringBuilder result = new StringBuilder();

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                queue.offer((char) (i + 'a'));
            }
        }

        while (!queue.isEmpty()) {
            char currentNode = queue.poll();
            result.append(currentNode);
            List<Character> neighbors = adjacencyListMap.getOrDefault(currentNode, new ArrayList<>());
            for (char neighbor : neighbors) {
                inDegree[neighbor - 'a']--;
                if (inDegree[neighbor - 'a'] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return result.length() == charactersUsedCount ? result.toString() : "";
    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(words));
    }
}
