package companies.salesforce.min_weight;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static int findMinWeight(List<Integer> weights, int d) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.addAll(weights);

        for (int day = 0; day < d; day++) {
            if (maxHeap.isEmpty()) {
                break;
            }

            int maxWeight = maxHeap.poll();

            int remainingWeight = (int) Math.ceil((double) maxWeight / 2);
            if (remainingWeight > 0) {
                maxHeap.add(remainingWeight);
            }
        }

        int minWeight = 0;
        for (int weight : maxHeap) {
            minWeight += weight;
        }

        return minWeight;
    }

    public static void main(String[] args) {
        Integer[] chocolates = {30, 20, 25};
        int d = 4;
        System.out.println(findMinWeight(Arrays.asList(chocolates), d));
    }
}
