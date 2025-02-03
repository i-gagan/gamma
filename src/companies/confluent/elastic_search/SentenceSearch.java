//package companies.confluent.elastic_search;
//
//public class Solution {
//}
//import java.io.*;
//        import java.util.*;
//        import java.text.*;
//        import java.math.*;
//        import java.util.regex.*;
//
//// 0. "Cloud computing is booming in the market."
//// 1. "I am going to introduce Cloud Monitoring in the following paragraphs. I have been working in the cloud industry for 10 years."
//// 2. "Scientists have investigated Venus Monitoring Camera images and have tried to identify the possibility of bacteria living in cloud tops."
//
//// 1. Return Empty if no match found
//// 2. Case sensitive - No
//
//
//// Inverted Index
//
//// Cloud - 0, 1, 2
//
//// computing - 0
//// is
//
//// Map
//// Key - Word - String
//// Value - Set <Integer> - Indexs of the words docs
//
//
//
//// continuos text we need to search in order
//// no limit on no of words in search query
//
////Cloud Monitoring in the following paragraphs I have been working in the cloud industry for 10 years fsdfd fsdfd fsdfdsfsd
//
//
//// Cloud -
//
//// 1, -> 5, 17
//// 2
//
//// Montioring
//// 1 -> 6,
//
//
//// Complexity - Linear
//
//
//
//// UserDefinedPair {
////     String word;
////     int index;
//// }
//// Set<UserDefinedPair>
//
//// 1 ->
//
//// HashMap<Key, Set<Integer>>
//
//
//// String Word
//
//// String NextWord
//
//
//class WordInfo {
//    int docIndex;
//    String word;
//    int wordIndex;
//
//
//    WordInfo(int docIndex, String word, int wordIndex) {
//        this.docIndex = docIndex;
//        this.word = word;
//        this.wordIndex = wordIndex;
//    }
//
//    @Override
//    public int hashCode() {
//        String key = word + "-" + docIndex + "_" + wordIndex;
//        return key.hashCode();
//    }
//}
//
//public class Solution {
//
//    private static Map<String, Set<WordInfo>> invertedIndexMap = new HashMap<>();
//
//    public static void main(String[] args) {
//        String[] docs =  {
//                "Cloud computing is booming in the market" ,
//                "I am going to introduce Cloud Monitoring in the following paragraphs I have been working in the cloud industry for 10 years",
//                "Scientists have investigated Venus Monitoring Camera images and have tried to identify the possibility of bacteria living in cloud tops"
//        };
//
//        initInvertedIndexMap(docs);
//
//        String searchString = "Cloud Monitoring in the following paragraphs I have been working in the cloud industry for 10 years";
//        Set<Integer> docsIndexesSet = searchStringInDocs(searchString);
//
//        System.out.println("Search Word " + searchWord + " Found in Docs " + docsIndexesSet);
//
//    }
//
//    public static Set<WordInfo> searchStringInDocs(List<String> searchString) {
//        Set<WordInfo> docsIndexesSet = new HashSet<>();
//        for (int i = 0; i < searchString.size(); i++) {
//            Set<WordInfo> tempDocsIndexSet = new HashSet<>();
//            docsIndexesSet.retainAll(tempDocsIndexSet);
//
//
//            String key = word + "-" + docIndex + "_" + wordIndex - 1;
//            tempDocsIndexSet = searchWordInDocs(searchString.get(i));
//        }
//    }
//
//    public boolean validate(Set<WordInfo> docsIndexesSet, String key ) {
//
//    }
//
//    public static void initInvertedIndexMap(String[] docs) {
//        if (docs != null) {
//
//            for (int docIndex = 0; docIndex < docs.length; docIndex++) {
//                String[] wordsInDoc = docs[docIndex].split(" ");
//
//                for (int i = 0; i < wordsInDoc.length; i++) {
//
//                    String key = wordsInDoc[i].toLowerCase();
//
//                    if (!invertedIndexMap.containsKey(key)) {
//                        invertedIndexMap.put(key, new HashSet<>());
//                    }
//
//                    invertedIndexMap.get(key).add(docIndex);
//                }
//            }
//        }
//    }
//
//    public static Set<Integer> searchWordInDocs(String searchWord) {
//        Set<Integer> docsIndexesSet = new HashSet<>();
//
//        String key = searchWord.toLowerCase();
//
//        if (invertedIndexMap.containsKey(key)) {
//            docsIndexesSet = invertedIndexMap.get(key);
//        }
//
//        return docsIndexesSet;
//    }
//}
//
//
