package leetcode.medium.m_1004_max_consecutive_ones_III;

//https://leetcode.com/problems/max-consecutive-ones-iii/description/

class Solution {
    public static int longestOnes(int[] nums, int k) {
       // return longestOnes1(nums, k);
        return longestOnes2(nums, k);
    }

    public static int longestOnes1(int[] nums, int k) {
        int leftIndex = 0, rightIndex = 0, maxOnesCount = 0;

        while (rightIndex < nums.length) {
            if (nums[rightIndex] == 0) {
                --k;
            }
            rightIndex++;
            while (k < 0) {
                if (nums[leftIndex] == 0) {
                    ++k;
                }
                leftIndex++;
            }
            maxOnesCount = Math.max(maxOnesCount, rightIndex - leftIndex);
        }
        return maxOnesCount;
    }

    public static int longestOnes2(int[] nums, int k) {
        int leftIndex = 0, rightIndex = 0;

        while (rightIndex < nums.length) {
            if (nums[rightIndex] == 0) {
                k--;
            }
            if (k < 0) {
                if (nums[leftIndex] == 0) {
                    k++;
                }
                leftIndex++;
            }

            rightIndex++;
        }
        return rightIndex - leftIndex;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0};
        int k = 2;
        System.out.println(longestOnes(nums, k));
    }
}
