package leetcode.hard.h_84_largest_rectangle_in_histogram;

//https://leetcode.com/problems/largest-rectangle-in-histogram/description/

import java.util.Stack;

class Solution {
    public static int largestRectangleArea(int[] heights) {
        int[] prefix = nextSmallerElementLeftSide(heights);
        int[] suffix = nextSmallerElementRightSide(heights);
        int largestRectangleArea = Integer.MIN_VALUE;

        for (int i = 0; i < heights.length; i++) {
            largestRectangleArea = Math.max(largestRectangleArea, heights[i] * (suffix[i] - prefix[i] - 1));
        }
        return largestRectangleArea;
    }

    private static int[] nextSmallerElementLeftSide(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] prefix = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.empty()) {
                prefix[i] = -1;
            } else {
                prefix[i] = stack.peek();
            }

            stack.push(i);
        }
        return prefix;
    }

    private static int[] nextSmallerElementRightSide(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] suffix = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.empty()) {
                suffix[i] = n;
            } else {
                suffix[i] = stack.peek();
            }
            stack.push(i);
        }
        return suffix;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        //int[] heights = {1,2,2};
        System.out.println(largestRectangleArea(heights));
    }
}
