package leetcode.hard.h_2742_painting_the_walls;

//https://leetcode.com/problems/painting-the-walls/description/
//https://www.youtube.com/watch?v=FkJ2_hr6DRo&ab_channel=codestorywithMIK

import java.util.Arrays;

class Solution {
    public static int paintWalls(int[] cost, int[] time) {
        int totalWalls = cost.length;
//        return solve1(cost, time, totalWalls, 0, totalWalls);

        int[][] dp = new int[totalWalls + 1][totalWalls + 1];
        for (int i = 0; i < totalWalls; i++) {
            Arrays.fill(dp[i], -1);
        }
        return solve2(cost, time, totalWalls, 0, totalWalls, dp);
    }

    private static int solve1(int[] cost, int[] time, int totalWalls, int currentWallIndex, int remainingWalls) {
        if (remainingWalls <= 0) {
            return 0;
        }
        if (currentWallIndex >= totalWalls) {
            return Integer.MAX_VALUE / 2;
        }

        int paint = cost[currentWallIndex] + solve1(cost, time, totalWalls, currentWallIndex + 1, remainingWalls - 1 - time[currentWallIndex]);
        int not_paint = solve1(cost, time, totalWalls, currentWallIndex + 1, remainingWalls);

        return Math.min(paint, not_paint);
    }

    private static int solve2(int[] cost, int[] time, int totalWalls, int currentWallIndex, int remainingWalls, int[][] dp) {
        if (remainingWalls <= 0) {
            return 0;
        }
        if (currentWallIndex >= totalWalls) {
            return Integer.MAX_VALUE / 2;
        }

        if (dp[currentWallIndex][remainingWalls] != -1) {
            return dp[currentWallIndex][remainingWalls];
        }
        int paint = cost[currentWallIndex] + solve2(cost, time, totalWalls, currentWallIndex + 1, remainingWalls - 1 - time[currentWallIndex], dp);
        int not_paint = solve2(cost, time, totalWalls, currentWallIndex + 1, remainingWalls, dp);

        return dp[currentWallIndex][remainingWalls] = Math.min(paint, not_paint);
    }

    public static void main(String[] args) {
        int[] cost = {1, 2, 3, 2};
        int[] time = {1, 2, 3, 2};
        System.out.println(paintWalls(cost, time));
    }
}
