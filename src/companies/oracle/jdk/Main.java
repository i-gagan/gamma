package companies.oracle.jdk;//package org.example;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }
//}
//
////package oracle.test;
////
////import java.util.Queue;
//
///**
// * Provides waiting thread-safe access to a java.util.Queue instance.
// *
// * Requirements:
// * - Usage of API from java.util.concurrent package is prohibited.
// * - Limit the amount of additional consumed memory to O(1).
// * - The implementation is supposed to be acceptable for usage in a highly
// * multi-thread environment.
// *
// * Useful tips a.k.a. common pitfalls:
// * - Please note that you do not need to implement java.util.Queue.
// * - Readiness to accept or provide elements is solely dependent on the
// * underlying queue. Any additional queue capacity limitations break contract
// * defined in the javadoc.
// */
////public class BlockingQueue<E> {
////    // implement code here ...
////    /**
////     * @param queue The underlying "wrapped" queue.
////     */
////    public BlockingQueue(Queue<E> queue) {
////        // implement code here ...
////    }
////    /**
////     * Inserts the specified element into the underlying queue, waiting if
////     * necessary for the underlying queue to be ready to accept new elements.
////     * @param e the element to insert.
////     */
////    public void push(E e) {
////        // implement code here ...
////    }
////
////    /**
////     * Retrieves and removes the head of the underlying queue, waiting if
////     * necessary until it is capable of providing an element.
////     * @return the retrieved element
////     */
////    public E pull() {
////        // implement code here ...
////    }
////}
//
//
////SECOND QUESTION FOLLOWS
////Given an array of strings strs, group the anagrams together. You can return the answer in any order.
////An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
////        Example 1:
////Input: strs = ["eat","tea","tan","ate","nat","bat"]
////Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
////Example 2:
////Input: strs = [""]
////Output: [[""]]
////Example 3:
////Input: strs = ["a"]
////Output: [["a"]]
////Example 4:
////Input: strs = ["Tea", "ate", "tae", "dsf", "fDS" , null]
////Output: [["Tea", "ate", "tae"], ["dsf", "fDS"]]
////
////Example 5:
////Input: strs = ["kbc", "kad", "dsf", "fDS" ]
////Output: [["kbc"], ["kad"], ["dsf", "fDS"]]
////Constraints:
////        1 <= strs.length <= 104
////        0 <= strs[i].length <= 100
////strs[i] consists of English letters.
//
//
////THIRD QUESTION FOLLOWS
////Given an integer n. print all twin prime number pairs between 2 to n.
//
////17, 19, 5, 7, 3, 5
////20 ->
////3, 5 -> diff 2
////5, 7
////        7, 11
////
////11, 13
////17, 19
////
////2, 3, 5, 7, 11, 13, 17, 19