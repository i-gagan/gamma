package leetcode.medium.m_179_largest_number;

//https://leetcode.com/problems/largest-number/description/

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public static String largestNumber(int[] nums) {
        Integer[] numsArray = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsArray[i] = nums[i];
        }
        Arrays.sort(numsArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                String strN1 = String.valueOf(n1);
                String strN2 = String.valueOf(n2);
                for (int i = 0, j = 0; i < strN1.length() && j < strN2.length(); i++, j++) {
                    int diff = Integer.compare(strN2.charAt(j), strN1.charAt(i));
                    if (diff == 0) {
                        continue;
                    }
                    return diff;
                }
                String case1 = strN1 + strN2;
                String case2 = strN2 + strN1;
                return case2.compareTo(case1);
            }
        });

        StringBuilder sb = new StringBuilder();
        boolean isNonZeroNumExists = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                isNonZeroNumExists = true;
            }
            sb.append(numsArray[i]);
        }
        if (!isNonZeroNumExists) {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {0, 0};
        System.out.println(largestNumber(nums));
    }
}