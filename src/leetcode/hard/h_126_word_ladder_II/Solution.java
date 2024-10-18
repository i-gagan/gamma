package leetcode.hard.h_126_word_ladder_II;

//https://leetcode.com/problems/word-ladder-ii/description/

import java.util.*;

class Solution {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }

        Map<String, Set<String>> predecessorMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        wordSet.remove(beginWord);

        boolean foundEndWord = false;

        while (!queue.isEmpty() && !foundEndWord) {
            int levelSize = queue.size();
            Set<String> currentLevelWords = new HashSet<>();

            for (int i = 0; i < levelSize; i++) {
                String word = queue.poll();
                char[] wordChars = word.toCharArray();

                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char replacedChar = 'a'; replacedChar <= 'z'; replacedChar++) {
                        if (replacedChar == originalChar) {
                            continue;
                        }

                        wordChars[j] = replacedChar;
                        String newWord = new String(wordChars);

                        if (wordSet.contains(newWord)) {
                            if (!predecessorMap.containsKey(newWord)) {
                                predecessorMap.put(newWord, new HashSet<>());
                            }
                            predecessorMap.get(newWord).add(word);
                            currentLevelWords.add(newWord);

                            if (newWord.equals(endWord)) {
                                foundEndWord = true;
                            }
                        }
                    }
                    wordChars[j] = originalChar;
                }
            }

            queue.addAll(currentLevelWords);
            wordSet.removeAll(currentLevelWords);
        }

        if (!foundEndWord) {
            return result;
        }

        List<String> path = new ArrayList<>();
        path.add(endWord);
        backtrack(endWord, beginWord, predecessorMap, result, path);
        return result;
    }

    private static void backtrack(String currentWord, String beginWord, Map<String, Set<String>> predecessorMap,
                                  List<List<String>> result, List<String> path) {
        if (currentWord.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }

        if (!predecessorMap.containsKey(currentWord)) {
            return;
        }

        for (String predecessor : predecessorMap.get(currentWord)) {
            path.add(predecessor);
            backtrack(predecessor, beginWord, predecessorMap, result, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(findLadders(beginWord, endWord, List.of(wordList)));
    }
}
