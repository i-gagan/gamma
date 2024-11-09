package companies.oracle.jdk;

import java.util.*;

public class Anagrams {
    //TC - O(N) Array Iteration + O(1) HashMap Insertion
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> strMap = new HashMap<>();
        for (String str : strs) {
            if (str != null) {
                String origStr = new String(str);
                str = str.toLowerCase();

                char[] charStrArray = str.toCharArray();
                Arrays.sort(charStrArray);

                String key = new String(charStrArray);

                if (!strMap.containsKey(key)) {
                    strMap.put(key, new ArrayList<>());
                }
                List<String> anagramsList = strMap.get(key);
                anagramsList.add(origStr);
                strMap.put(key, anagramsList);
            }
        }

        for (Map.Entry<String, List<String>> entry : strMap.entrySet()) {
            List<String> anagramsList = entry.getValue();
            result.add(anagramsList);
        }
        return result;
    }
}