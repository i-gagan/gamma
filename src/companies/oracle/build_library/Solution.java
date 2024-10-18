//package companies.oracle.build_library;
//
//package org.example;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }
//}
//
//List1 -> Unsorted (M) 1
//List2 -> Sorted (N) // million
//
//Brute Force - O(M * N)
//
//Extra Space - Array for List2
//If O(M * logN)
//
//List1, List2 can have +ve, -ve numbers
//Read elements from list 1 and find out if they exist in list2 or not
//
//
//If building the library from List1 , List2
//
//
//Interface
//
//class LibraryX {
//    //int[] arr;
//
//    //HashSet<Integer>
//
//            32 numbers - 1 byte
//    bytes[] positiveNumber
//    bytes[] negativeNumber
//
//
//
//    List list1;
//    public LibraryX(List list1, List inputList) {
//        this.list1 = list1;
//        arr = covertToArray(inputList);
//        //markInBytes(inputList);
//    }
//
//            -5, -1,
//                    4, 50
//    int byteIndex = 4 / 32 = 0
//    int bitIndex = 4 % 32 = 4
//
//    byteIndex = 50 / 32 = 1
//    int bitIndex = 50 % 32 = 18
//
//    byte[0]  0 0 0 1 0 0 0 ...0
//    byte[1]  0 0 0 0 0 00 0.... 1. 000000
//    byte[2]
//
//    List 2 - 0, 32, 64, 128, 256
//
//            0
//    byteIndex = 0
//    bitIndex = 0
//
//            32
//    byteIndex = 32 / 32 = 1
//    bitIndex = 32 % 32 = 0
//    byte[0]  1 0 0 0 0 0 0 ...0 1 1 byte = 32 bits
//    byte[1]  1 0 0 0 0 0 0 ...0
//    byte[2]
//    byte[3]  1 0 0 0 0 0 0 ...0
//
//
//            2^8
//
//            8 * (size of 1 bytes)
//
//
//
//
//
//    boolean exists(int element) {
//
//    }
//
//    boolean List<Boolean> checkExists(int[] arr, List List1) {
//        exists
//    }
//}
//
//
//        <true, false, true>
//
//
//        0  Node(1)
//        1  Node(2)
//        2  Node(3)
//        3  Node(4)
//        4  Node(8)
//        5  Node(10)
//        6  Node(100000)
//
//
//List1 -> 2000, 4, 10, 9, 9
//List 2 -> 1, 2, 3, 4, 8, 10, 100000
//
//
////Feedback -
//Create dictionary on the Go, and check previous search, dont create it firstly
