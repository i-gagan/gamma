package leetcode.hard.h_834_sum_of_distances_in_tree;

import java.util.*;

public class Solution {
    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();


        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }
        int[] result = new int[n];
        int[] noOfNodesInSubtree = new int[n];
        for (int node = 0; node < n; node++) {
            result[node] = calculateDistances(graph, n, node, noOfNodesInSubtree);
        }
        return result;
    }

    private static int calculateDistances(Map<Integer, Set<Integer>> graph, int n, int node, int[] noOfNodesInSubtree) {
        boolean[] visited = new boolean[n];
        int distance = 1, totalDistance = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currentNode = queue.poll();
                visited[currentNode] = true;

                Set<Integer> neighbours = graph.get(currentNode);
                for (Integer neighbour : neighbours) {
                    if (!visited[neighbour]) {
                        queue.add(neighbour);
                        totalDistance += distance;
                    }
                }
            }
            distance = distance + 1;
        }
        return totalDistance;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        System.out.println(Arrays.toString(sumOfDistancesInTree(n, edges)));
    }
}
