package companies.microsoft.online_assesment.string_differ_by_one_char;

class Solution {
    public static String solution(String[] words) {
        int k = words[0].length();

        String referenceWord = words[0];

        for (int i = 0; i < k; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String tempWord = referenceWord.substring(0, i) + ch + referenceWord.substring(i + 1);

                if (isValidReplacement(tempWord, words)) {
                    return tempWord;
                }
            }
        }

        return "";
    }

    private static boolean isValidReplacement(String candidate, String[] words) {
        for (String word : words) {
            if (!isOneCharacterDifferent(candidate, word)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOneCharacterDifferent(String word1, String word2) {
        int differenceCount = 0;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                differenceCount++;
                if (differenceCount > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] words = {"zzz", "bcb", "zcb"};
        System.out.println(solution(words));
    }
}
