package leetcode.easy.e_243_shortest_word_distance;

//https://leetcode.com/problems/shortest-word-distance/description/

class Solution {
    public static int shortestDistance(String[] wordsDict, String word1, String word2) {
        int minDistance = Integer.MAX_VALUE;

        int lastPosWord1 = -1;
        int lastPosWord2 = -1;

        for (int index = 0; index < wordsDict.length; index++) {
            if (wordsDict[index].equals(word1)) {
                lastPosWord1 = index;
            }
            if (wordsDict[index].equals(word2)) {
                lastPosWord2 = index;
            }
            if (lastPosWord1 != -1 && lastPosWord2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(lastPosWord1 - lastPosWord2));
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes", word2 = "coding";
        System.out.println(shortestDistance(wordsDict, word1, word2));
    }
}
