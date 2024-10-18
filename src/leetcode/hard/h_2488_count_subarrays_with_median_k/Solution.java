package leetcode.hard.h_2488_count_subarrays_with_median_k;

//https://leetcode.com/problems/count-subarrays-with-median-k/description/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int countSubarrays(int[] nums, int k) {
        int indexK = 0;
        while (indexK < nums.length) {
            if (nums[indexK] == k) {
                break;
            }
            indexK++;
        }

        Map<Integer, Integer> numbersGreaterThanKMap = new HashMap<>();
        int numberGreaterThanKCount = 0;

        for (int i = indexK; i < nums.length; i++) {
            if (nums[i] > k) {
                numberGreaterThanKCount++;
            } else if (nums[i] < k) {
                numberGreaterThanKCount--;
            }
            numbersGreaterThanKMap.put(numberGreaterThanKCount, numbersGreaterThanKMap.getOrDefault(numberGreaterThanKCount, 0) + 1);
        }

        int subArraysCount = 0;
        int numberLessThanKCount = 0;

        for (int i = indexK; i >= 0; i--) {
            if (nums[i] > k) {
                numberLessThanKCount--;
            } else if (nums[i] < k) {
                numberLessThanKCount++;
            }

            subArraysCount += numbersGreaterThanKMap.getOrDefault(numberLessThanKCount, 0);
            subArraysCount += numbersGreaterThanKMap.getOrDefault(numberLessThanKCount + 1, 0);
        }

        return subArraysCount;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 4, 5};
        int k1 = 4;
        System.out.println(countSubarrays(nums1, k1));
    }
}
