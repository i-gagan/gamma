package companies.visa.find_pairs;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] arr = {5, 16, 18, 3, 9, 12, 7, -3};
        int target = 15;
        List<int[]> result = findPairs(arr, target);

        for (int i = 0; i < result.size(); i++) {
            int[] pair = result.get(i);
            System.out.println(Arrays.toString(pair) + " ");
        }
    }

    public static List<int[]> findPairs(int[] arr, int target) {
        List<int[]> result = new ArrayList<>();

        Arrays.sort(arr); //nlogn

        int leftIndex = 0, rightIndex = arr.length - 1;

        while (leftIndex < rightIndex) { //n
            if (arr[leftIndex] + arr[rightIndex] == target) {
                int[] pair = new int[2];
                pair[0] = arr[leftIndex];
                pair[1] = arr[rightIndex];
                result.add(pair);
                leftIndex++;
                rightIndex--;
            } else {
                if (arr[leftIndex] + arr[rightIndex] < target) {
                    leftIndex++;
                } else {
                    rightIndex--;
                }
            }
        }
        return result;
    }
}

