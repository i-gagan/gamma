package leetcode.hard.h_85_maximal_rectangle;

//https://leetcode.com/problems/maximal-rectangle/description/

import java.util.Stack;

class Solution {
    public static int maximalRectangle(char[][] matrix) {
        int numColumns = matrix[0].length;
        int[] heights = new int[numColumns];

        int largestRectangleArea = 0;
        for (char[] row : matrix) {
            for (int j = 0; j < numColumns; ++j) {
                heights[j] = row[j] == '1' ? heights[j] + 1 : 0;
            }
            largestRectangleArea = Math.max(largestRectangleArea, largestRectangleArea(heights));
        }
        return largestRectangleArea;
    }

    private static int largestRectangleArea(int[] heights) {
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
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(matrix));
    }
}
