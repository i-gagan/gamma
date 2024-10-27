package leetcode.medium.m_221_maximal_square;

//https://leetcode.com/problems/maximal-square/description/

class Solution {
    public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows + 1][cols + 1];

        int maxSquareSize = 0;

        for (int i = 0; i <= rows; ++i) {
            for (int j = 0; j <= cols; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (matrix[i - 1][j - 1] == '1') {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                        maxSquareSize = Math.max(maxSquareSize, dp[i][j]);
                    }
                }
            }
        }

        return maxSquareSize * maxSquareSize;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare(matrix));
    }
}