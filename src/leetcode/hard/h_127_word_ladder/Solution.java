package leetcode.hard.h_127_word_ladder;

//https://leetcode.com/problems/word-ladder/description/
//https://leetcode.com/problems/word-ladder/solutions/5676871/easiest-explanation-with-images-commented-code/

import java.util.*;

class Solution {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 0;

        while (!queue.isEmpty()) {
            level++;
            int levelSize = queue.size();

            System.out.println("Queue " + queue);
            for (int i = 0; i < levelSize; i++) {
                String word = queue.poll();

                assert word != null;
                char[] wordChars = word.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    for (char replacedChar = 'a'; replacedChar <= 'z'; replacedChar++) {
                        char originalChar = wordChars[j];

                        if (replacedChar == originalChar) {
                            continue;
                        }

                        wordChars[j] = replacedChar;
                        String newWord = new String(wordChars);

                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }
                        if (wordSet.contains(newWord)) {
                            queue.add(newWord);
                            wordSet.remove(newWord);
                        }
                        wordChars[j] = originalChar;
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(ladderLength(beginWord, endWord, List.of(wordList)));
    }
}
