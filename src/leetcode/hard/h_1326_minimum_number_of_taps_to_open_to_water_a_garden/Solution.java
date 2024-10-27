package leetcode.hard.h_1326_minimum_number_of_taps_to_open_to_water_a_garden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pair {
    int start;
    int end;

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "" + start + " " + end  ;
    }
}

class Solution {
    public static int minTaps(int n, int[] ranges) {
        List<Pair> intervals = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            int leftRange = Math.max(0, i - ranges[i]);
            int rightRange = Math.min(n, i + ranges[i]);
            if (leftRange != rightRange) {
                intervals.add(new Pair(leftRange, rightRange));
            }
        }

        Collections.sort(intervals, (a, b) -> a.start - b.start);

        int result = mergeIntervals(intervals);
        return result;
    }

    private static int mergeIntervals(List<Pair> intervals) {
        for (int i = 0; i < intervals.size() - 1; i++) {
            while (i + 1 <= intervals.size() - 1 && intervals.get(i).end >= intervals.get(i + 1).start) {
                intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i + 1).end);
                intervals.remove(i + 1);
            }
        }
        return intervals.isEmpty() ? -1 : intervals.size();
    }

    public static void main(String[] args) {
        int n = 7;
        //int[] ranges = {3, 4, 1, 1, 0, 0};
        int[] ranges = {1,2,1,0,2,1,0,1};

        System.out.println(minTaps(n, ranges));
    }
}
