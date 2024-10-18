package leetcode.hard.z_h_995_minimum_number_of_k_consecutive_bit_flips;

//https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/description/

class Solution {
    public static int minKBitFlips(int[] nums, int k) {
        int length = nums.length;
        int[] flips = new int[length + 1];
        int totalFlips = 0;
        int flipCounter = 0;

        for (int i = 0; i < length; ++i) {
            flipCounter += flips[i];

            if (nums[i] % 2 == flipCounter % 2) {
                if (i + k > length) {
                    return -1;
                }
                flips[i]++;
                flips[i + k]--;
                flipCounter++;
                totalFlips++;
            }
        }
        return totalFlips;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 0, 1, 1, 0};
        int k = 3;
        System.out.println(minKBitFlips(nums, k));
    }
}
