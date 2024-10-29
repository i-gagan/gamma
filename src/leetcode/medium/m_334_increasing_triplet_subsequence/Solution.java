package leetcode.medium.m_334_increasing_triplet_subsequence;

//https://leetcode.com/problems/increasing-triplet-subsequence/description/

import java.util.Arrays;

class Solution {
    public static boolean increasingTriplet(int[] nums) {
        int k = 3;
        int[] sequence = new int[k];
        Arrays.fill(sequence, Integer.MAX_VALUE);

        for (int num : nums) {
            int index = 0;
            while (sequence[index] < num) {
                index++;
            }
            sequence[index] = num;

            if (index == k - 1) {
                System.out.println(Arrays.toString(sequence));
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {2, 1, 5, 0, 4, 6};
        //int[] nums = {20, 100, 10, 12, 5, 13};
        System.out.println(increasingTriplet(nums));
    }
}