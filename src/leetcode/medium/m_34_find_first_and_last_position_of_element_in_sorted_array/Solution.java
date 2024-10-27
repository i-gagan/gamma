package leetcode.medium.m_34_find_first_and_last_position_of_element_in_sorted_array;

//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

import java.util.Arrays;

class Solution {
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};

        if (nums.length == 0) {
            return res;
        }

        int leftIndex = findPosition(nums, target, true);

        if (leftIndex == -1) {
            return res;
        }

        int rightIndex = findPosition(nums, target, false);

        res[0] = leftIndex;
        res[1] = rightIndex;
        return res;
    }

    private static int findPosition(int[] nums, int target, boolean findFirst) {
        int leftIndex = 0, rightIndex = nums.length - 1;
        int result = -1;

        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            if (nums[midIndex] == target) {
                result = midIndex;
                if (findFirst) {
                    rightIndex = midIndex - 1;
                } else {
                    leftIndex = midIndex + 1;
                }
            } else if (nums[midIndex] < target) {
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
