package leetcode.medium.m_2685_count_the_number_of_complete_components;

//https://leetcode.com/problems/count-the-number-of-complete-components/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result {
    int noOfNodes;
    int noOfEdges;
}

class Solution {
    public static int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        boolean[] visited = new boolean[n];
        Arrays.setAll(graph, k -> new ArrayList<>());

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int completeComponents = 0;

        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                Result result = new Result();
                dfs(node, graph, visited, result);
                int noOfNodes = result.noOfNodes;
                int noOfEdges = result.noOfEdges / 2;

                if (noOfNodes * (noOfNodes - 1) / 2 == noOfEdges) {
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    private static void dfs(int node, List<Integer>[] graph, boolean[] visited, Result result) {
        visited[node] = true;

        result.noOfNodes = result.noOfNodes + 1;
        result.noOfEdges = result.noOfEdges + graph[node].size();

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, result);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {3, 4}};
        System.out.println(countCompleteComponents(n, edges));
    }
}
