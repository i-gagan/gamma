package leetcode.hard.h_154_find_minimum_in_rotated_sorted_array_II;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/

class Solution {
    public static int findMin(int[] nums) {
        int leftIndex = 0, rightIndex = nums.length - 1;
        int result = nums[0];

        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            result = Math.min(result, nums[midIndex]);

            if (nums[midIndex] == nums[rightIndex]) {
                rightIndex--;
            } else {
                if (nums[midIndex] > nums[rightIndex]) {
                    leftIndex = midIndex + 1;
                } else {
                    rightIndex = midIndex - 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 0, 1};
        System.out.println(findMin(nums));
    }
}
