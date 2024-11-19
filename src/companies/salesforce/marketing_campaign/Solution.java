package companies.salesforce.marketing_campaign;

import java.util.*;

class Solution {
    public static int minimumWeeklyInput(List<Integer> costs, int weeks) {
        int n = costs.size();
        int[][] dp = new int[n + 1][weeks + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = getMaxCostInRange(costs, 0, i - 1);
        }

        for (int week = 2; week <= weeks; week++) {
            for (int campaign = 1; campaign <= n; campaign++) {
                int maxCostInGroup = 0;

                for (int previousCampaign = campaign - 1; previousCampaign >= 0; previousCampaign--) {
                    maxCostInGroup = Math.max(maxCostInGroup, costs.get(previousCampaign));

                    if (dp[previousCampaign][week - 1] != Integer.MAX_VALUE) {
                        dp[campaign][week] = Math.min(dp[campaign][week], dp[previousCampaign][week - 1] + maxCostInGroup);
                    }
                }
            }
        }

        return dp[n][weeks];
    }

    private static int getMaxCostInRange(List<Integer> costs, int start, int end) {
        int maxCost = 0;
        for (int i = start; i <= end; i++) {
            maxCost = Math.max(maxCost, costs.get(i));
        }
        return maxCost;
    }

    public static void main(String[] args) {
        List<Integer> costs = Arrays.asList(1000, 500, 2000, 3000, 800);
        int weeks = 3;
        System.out.println(minimumWeeklyInput(costs, weeks));
    }
}
