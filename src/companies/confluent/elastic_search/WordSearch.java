package companies.confluent.elastic_search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordSearch {

     private static Map<String, Set<Integer>> invertedIndexMap = new HashMap<>();

     public static void main(String[] args) {
         String[] docs =  {
            "Cloud computing is booming in the market" ,
            "I am going to introduce Cloud Monitoring in the following paragraphs I have been working in the cloud industry for 10 years",
             "Scientists have investigated Venus Monitoring Camera images and have tried to identify the possibility of bacteria living in cloud tops"
         };

         initInvertedIndexMap(docs);

         String searchWord = "Cloud";
         Set<Integer> docsIndexesSet = searchWordInDocs(searchWord);

         System.out.println("Search Word " + searchWord + " Found in Docs " + docsIndexesSet);


         searchWord = "market";
         docsIndexesSet = searchWordInDocs(searchWord);

         System.out.println("Search Word " + searchWord + " Found in Docs " + docsIndexesSet);

         searchWord = "marketfdfdfd";
         docsIndexesSet = searchWordInDocs(searchWord);

         System.out.println("Search Word " + searchWord + " Found in Docs " + docsIndexesSet);
     }

     public static void initInvertedIndexMap(String[] docs) {
         if (docs != null) {

             for (int docIndex = 0; docIndex < docs.length; docIndex++) {
                 String[] wordsInDoc = docs[docIndex].split(" ");

                 for (int i = 0; i < wordsInDoc.length; i++) {
                     String key = wordsInDoc[i].toLowerCase();

                     if (!invertedIndexMap.containsKey(key)) {
                         invertedIndexMap.put(key, new HashSet<>());
                     }

                     invertedIndexMap.get(key).add(docIndex);
                 }
             }
         }
     }

     public static Set<Integer> searchWordInDocs(String searchWord) {
         Set<Integer> docsIndexesSet = new HashSet<>();

         String key = searchWord.toLowerCase();

         if (invertedIndexMap.containsKey(key)) {
             docsIndexesSet = invertedIndexMap.get(key);
         }

         return docsIndexesSet;
     }
 }