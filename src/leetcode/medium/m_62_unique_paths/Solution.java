package leetcode.medium.m_62_unique_paths;

//https://leetcode.com/problems/unique-paths/description/

import java.util.Arrays;

class Solution {
    public static int uniquePaths(int m, int n) {
        //return solve1(m, n, 0, 0);

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve2(m, n, 0, 0, dp);
    }

    private static int solve1(int m, int n, int row, int col) {
        if (row < 0 || col < 0 || row >= m || col >= n) {
            return 0;
        }
        if (row == m - 1 && col == n - 1) {
            return 1;
        }
        int down = solve1(m, n, row + 1, col);
        int right = solve1(m, n, row, col + 1);
        return down + right;
    }

    private static int solve2(int m, int n, int row, int col, int[][] dp) {
        if (row < 0 || col < 0 || row >= m || col >= n) {
            return 0;
        }
        if (row == m - 1 && col == n - 1) {
            return 1;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int down = solve2(m, n, row + 1, col, dp);
        int right = solve2(m, n, row, col + 1, dp);
        return dp[row][col] = down + right;
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }
}
