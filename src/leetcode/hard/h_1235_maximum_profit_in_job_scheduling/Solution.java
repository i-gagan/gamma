package leetcode.hard.h_1235_maximum_profit_in_job_scheduling;

//https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/

import java.util.Arrays;
import java.util.Comparator;

class Job {
    int startTime;
    int endTime;
    int profit;

    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
}

class Solution {
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int noOfJobs = startTime.length;

        Job[] jobs = new Job[noOfJobs];
        for (int i = 0; i < noOfJobs; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2) {
                return j1.startTime - j2.startTime;
            }
        });

        //return solve1(jobs, 0);
        int[] dp = new int[noOfJobs + 1];
        Arrays.fill(dp, -1);
        return solve2(jobs, 0, dp);
    }

    private static int solve1(Job[] jobs, int index) {
        if (index >= jobs.length) {
            return 0;
        }
        int nextIndex = getNextJobIndex1(jobs, index, jobs[index].endTime);
        //int nextIndex = getNextJobIndex2(jobs, index, jobs[index].endTime);

        int include = jobs[index].profit + solve1(jobs, nextIndex);

        int exclude = solve1(jobs, index + 1);
        return Math.max(include, exclude);
    }

    private static int solve2(Job[] jobs, int index, int[] dp) {
        if (index >= jobs.length) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int nextIndex = getNextJobIndex1(jobs, index, jobs[index].endTime);
        //int nextIndex = getNextJobIndex2(jobs, index, jobs[index].endTime);

        int include = jobs[index].profit + solve2(jobs, nextIndex, dp);

        int exclude = solve2(jobs, index + 1, dp);
        return dp[index] = Math.max(include, exclude);
    }

    private static int getNextJobIndex1(Job[] jobs, int currentIndex, int currentEndTime) {
        int leftIndex = currentIndex + 1, rightIndex = jobs.length - 1;

        int result = jobs.length;

        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (jobs[midIndex].startTime >= currentEndTime) {
                result = midIndex;
                rightIndex = midIndex - 1;
            } else {
                leftIndex = midIndex + 1;
            }
        }
        return result;
    }

    private static int getNextJobIndex2(Job[] jobs, int currentIndex, int currentEndTime) {
        for (int i = currentIndex + 1; i < jobs.length; i++) {
            if (jobs[i].startTime >= currentEndTime) {
                return i;
            }
        }
        return jobs.length;
    }

    public static void main(String[] args) {
        int[] startTime = {1, 2, 3, 3}, endTime = {3, 4, 5, 6}, profit = {50, 10, 40, 70};
        System.out.println(jobScheduling(startTime, endTime, profit));
    }
}
