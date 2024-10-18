package companies.atlassian.collection_of_files;

import java.util.*;

class File {
    String collectionName;
    String fileName;
    int fileSize;

    public File(String collectionName, String fileName, int fileSize) {
        this.collectionName = collectionName;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "[" + collectionName +
                " " + fileName +
                " " + fileSize +
                "]";
    }
}

class Collection {
    String collectionName;
    PriorityQueue<File> topFiles;
    int collectionSize;

    public Collection(String collectionName) {
        this.collectionName = collectionName;
        this.topFiles = new PriorityQueue<>(new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                int diff = f2.fileSize - f1.fileSize; // Decreasing
                if (diff == 0) {
                    return f1.fileName.compareTo(f2.fileName); // Increasing
                }
                return diff;
            }
        });
        this.collectionSize = 0;
    }

    @Override
    public String toString() {
        return "[" + collectionName + " " + collectionSize + "]";
    }
}

class Storage {
    Map<String, Collection> collectionFilesMap;
    PriorityQueue<String> topCollections;

    public Storage() {
        collectionFilesMap = new HashMap<>();
        topCollections = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String c1, String c2) {
                Collection collection1 = collectionFilesMap.get(c1);
                Collection collection2 = collectionFilesMap.get(c2);
                int diff = collection2.collectionSize - collection1.collectionSize;
                if (diff == 0) {
                    return collection1.collectionName.compareTo(collection2.collectionName);
                }
                return diff;
            }
        });
    }

    public void addFile(File file) {
        if (!collectionFilesMap.containsKey(file.collectionName)) {
            collectionFilesMap.put(file.collectionName, new Collection(file.collectionName));
        }

        Collection collection = collectionFilesMap.get(file.collectionName);
        collection.topFiles.add(file);
        collection.collectionSize += file.fileSize;

        updateTopNCollection(file.collectionName);
    }

    public void updateTopNCollection(String collection) {
        topCollections.remove(collection);
        topCollections.add(collection);
    }

    public int getAllCollectionSizes() {
        int totalSize = 0;

        for (Map.Entry<String, Collection> collection : collectionFilesMap.entrySet()) {
            PriorityQueue<File> files = collection.getValue().topFiles;

            for (File file : files) {
                totalSize += file.fileSize;
            }
        }
        return totalSize;
    }

    public List<File> getTopNFilesCollection(int N, String collection) {
        List<File> result = new LinkedList<>();
        if (collectionFilesMap.containsKey(collection)) {
            Collection collectionObj = collectionFilesMap.get(collection);
            PriorityQueue<File> files = collectionObj.topFiles;
            for (File file : files) {
                if (N < 0) {
                    break;
                }
                result.add(file);
                N--;
            }
        }
        return result;
    }

    public List<Collection> getTopNCollections(int N) {
        List<Collection> result = new LinkedList<>();
        for (String collectionNameKey : topCollections) {
            Collection collection = collectionFilesMap.get(collectionNameKey);
            if (N < 0) {
                break;
            }
            result.add(collection);
            N--;
        }
        return result;
    }
}

public class Solution {
    public static void main(String[] args) {
        File file1 = new File("ABC", "A", 10);
        File file2 = new File("ABC", "B", 12);

        File file3 = new File("XYZ", "X", 15);
        File file4 = new File("XYZ", "Y", 2);
        File file5 = new File("XYZ", "Z", 5);

        Storage storage = new Storage();
        storage.addFile(file1);
        storage.addFile(file2);
        storage.addFile(file3);
        storage.addFile(file4);
        storage.addFile(file5);

        int totalSize = storage.getAllCollectionSizes();
        System.out.println("Total Size = " + totalSize);

        List<File> topNFilesInCollection = storage.getTopNFilesCollection(3, "ABC");

        System.out.println(topNFilesInCollection);

        List<Collection> topNCollections = storage.getTopNCollections(3);
        System.out.println(topNCollections);
    }
}

//Building a system
//Store files
//        group in to logical collections
//        Property
//        size

//Report -
//
//        file {
//            size
//            name
//                    Collection -> tag
//        }
//        Scale -
//
//1. Give me the total size of all the files stored // No Tag
//2. Top N collection by filesize , N - User input // Tag
//        //Sorted
//
//abc
//    F1 - 20 bytes
//    F2 - 10 bytes
//
//
//1. Priority Queue - > Max Heap -> Insertion (Logn) Deletion(log n)
//
//2. Doubly List -> Insertion sort //O(N) n ->


//package org.example;
//
//import java.util.*;
//
//class File {
//    String fileName;
//    int size;   // bytes
//    String collection;
//
//    public File(String fileName, int size, String collection) {
//        this.fileName = fileName;
//        this.size = size;
//        this.collection = collection;
//    }
//
//    @Override
//    public String toString() {
//        return "[" + fileName +
//                " - " + size +
//                " " + collection +
//                "]";
//    }
//}
//
//class Storage {
//    Map<String, PriorityQueue<File>> storageMap;
//
//    Map<String, PriorityQueue<String>> collectionMap;
//
//    public Storage() {
//        storageMap = new HashMap<>();
//        collectionMap = new HashMap<>();
//    }
//
//    public void addFile(File file) {
//        if (!storageMap.containsKey(file.collection)) {
//            storageMap.put(file.collection, new PriorityQueue<>(new Comparator<File>() {
//                @Override
//                public int compare(File f1, File f2) {
//                    int diff = f2.size - f1.size; // Decreasing
//                    if (diff == 0) {
//                        return f1.fileName.compareTo(f2.fileName); // Increasing
//                    }
//                    return diff;
//                }
//            }));
//        }
//        storageMap.get(file.collection).add(file);
//    }
//
//    public int getAllCollectionSizes() {
//        // TC - No. of Tags (T) * No. of Files in each Tag (N) // O (T * N )
//        int totalSize = 0;
//
//        for (Map.Entry<String, PriorityQueue<File>> collection : storageMap.entrySet()) {
//            PriorityQueue<File> files = collection.getValue();
//
//            for (File file : files) {
//                totalSize += file.size;
//            }
//        }
//        return totalSize;
//    }
//
//    public List<File> getTopNFilesCollection(int M, String collection) {
//        // TC - No. of Tags (1) * M
//        List<File> result = new LinkedList<>();
//        if (storageMap.containsKey(collection)) {
//            PriorityQueue<File> files = storageMap.get(collection);
//            for (File file : files) {
//                if (M < 0) {
//                    break;
//                }
//                result.add(file);
//                M--;
//            }
//        }
//        return result;
//    }
//
//    public List<String> getTopNCollections(int i) {
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        File file1 = new File("A", 10, "ABC");
//        File file2 = new File("B", 20, "ABC");
//
//        File file3 = new File("C", 15, "XYZ");
//        File file4 = new File("A", 10, "XYZ");
//
//        Storage storage = new Storage();
//        storage.addFile(file1);
//        storage.addFile(file2);
//        storage.addFile(file3);
//        storage.addFile(file4);
//
//        // TC - N * log(N)
//
//        int totalSize = storage.getAllCollectionSizes();
//        System.out.println("Total Size = " + totalSize);
//
//        List<File> topNFilesInCollection = storage.getTopNFilesCollection(3, "ABC");
//
//        System.out.println(topNFilesInCollection);
//
//
//        List<String> topNCollections = storage.getTopNCollections(3);
//    }
//}
//
////Building a system
////Store files
////        group in to logical collections
////        Property
////        size
//
////Report -
////
////        file {
////            size
////            name
////                    Collection -> tag
////        }
////        Scale -
////
////1. Give me the total size of all the files stored // No Tag
////2. Top N collection by filesize , N - User input // Tag
////        //Sorted
////
////abc
////    F1 - 20 bytes
////    F2 - 10 bytes
////
////
////1. Priority Queue - > Max Heap -> Insertion (Logn) Deletion(log n)
////
////2. Doubly List -> Insertion sort //O(N) n ->
//
