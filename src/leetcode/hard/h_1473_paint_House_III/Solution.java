package leetcode.hard.h_1473_paint_House_III;

//https://leetcode.com/problems/paint-house-iii/description/

import java.util.Arrays;

class Solution {
    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
//        int result = paintHouses1(houses, cost, m, n, target, 0, 0, 0);
//        return result >= Integer.MAX_VALUE / 2 ? -1 : result;

        int[][][] dp = new int[m][n + 1][target + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int result = paintHouses2(houses, cost, m, n, target, 0, 0, 0, dp);
        return result >= Integer.MAX_VALUE / 2 ? -1 : result;
    }

    public static int paintHouses1(int[] houses, int[][] cost, int m, int n, int target, int currentHouseIndex, int prevColourIndex, int currentNeighborhoodsCount) {
        if (currentNeighborhoodsCount > target) {
            return Integer.MAX_VALUE / 2;
        }

        if (currentHouseIndex == m) {
            return currentNeighborhoodsCount == target ? 0 : Integer.MAX_VALUE / 2;
        }

        if (houses[currentHouseIndex] != 0) {
            int currentHouseColourIndex = houses[currentHouseIndex];
            int neighborhoodsCount = currentNeighborhoodsCount + (currentHouseColourIndex != prevColourIndex ? 1 : 0);

            return paintHouses1(houses, cost, m, n, target, currentHouseIndex + 1, currentHouseColourIndex, neighborhoodsCount);
        } else {
            int minCost = Integer.MAX_VALUE / 2;
            for (int colorIndex = 1; colorIndex <= n; colorIndex++) {
                int neighborhoodsCount = currentNeighborhoodsCount + (colorIndex != prevColourIndex ? 1 : 0);
                int currentCost = cost[currentHouseIndex][colorIndex - 1] + paintHouses1(houses, cost, m, n, target, currentHouseIndex + 1, colorIndex, neighborhoodsCount);
                minCost = Math.min(minCost, currentCost);
            }
            return minCost;
        }
    }

    public static int paintHouses2(int[] houses, int[][] cost, int m, int n, int target, int currentHouseIndex, int prevColourIndex, int currentNeighborhoodsCount, int[][][] dp) {
        if (currentNeighborhoodsCount > target) {
            return Integer.MAX_VALUE / 2;
        }

        if (currentHouseIndex == m) {
            return currentNeighborhoodsCount == target ? 0 : Integer.MAX_VALUE / 2;
        }

        if (dp[currentHouseIndex][prevColourIndex][currentNeighborhoodsCount] != -1) {
            return dp[currentHouseIndex][prevColourIndex][currentNeighborhoodsCount];
        }

        if (houses[currentHouseIndex] != 0) {
            int currentHouseColor = houses[currentHouseIndex];
            int neighborhoodsCount = currentNeighborhoodsCount + (currentHouseColor != prevColourIndex ? 1 : 0);
            return dp[currentHouseIndex][prevColourIndex][currentNeighborhoodsCount] = paintHouses2(houses, cost, m, n, target, currentHouseIndex + 1, currentHouseColor, neighborhoodsCount, dp);
        } else {
            int minCost = Integer.MAX_VALUE / 2;
            for (int color = 1; color <= n; ++color) {
                int neighborhoodsCount = currentNeighborhoodsCount + (color != prevColourIndex ? 1 : 0);
                int currentCost = cost[currentHouseIndex][color - 1] + paintHouses2(houses, cost, m, n, target, currentHouseIndex + 1, color, neighborhoodsCount, dp);
                minCost = Math.min(minCost, currentCost);
            }

            return dp[currentHouseIndex][prevColourIndex][currentNeighborhoodsCount] = minCost;
        }
    }

    public static void main(String[] args) {
//        int[] houses = {3, 1, 2, 3};
//        int[][] cost = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//        int m = 4, n = 3, target = 3;

        int[] houses = {0, 0, 0, 0, 0};
        int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int m = 5, n = 2, target = 3;

        System.out.println(minCost(houses, cost, m, n, target));
    }
}


