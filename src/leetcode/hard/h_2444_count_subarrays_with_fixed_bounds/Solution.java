package leetcode.hard.h_2444_count_subarrays_with_fixed_bounds;

//https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/

class Solution {
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int lastMinIndex = -1, lastMaxIndex = -1, lastInvalidIndex = -1;
        long count = 0;
        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            if (nums[currentIndex] < minK || nums[currentIndex] > maxK) {
                lastInvalidIndex = currentIndex;
            }
            if (nums[currentIndex] == minK) {
                lastMinIndex = currentIndex;
            }
            if (nums[currentIndex] == maxK) {
                lastMaxIndex = currentIndex;
            }

            if (lastMinIndex != -1 && lastMaxIndex != -1) {
                int validStartIndex = Math.min(lastMinIndex, lastMaxIndex);
                if (validStartIndex > lastInvalidIndex) {
                    count += validStartIndex - lastInvalidIndex;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 7, 5};
        int minK = 1, maxK = 5;
        System.out.println(countSubarrays(nums, minK, maxK));
    }
}
