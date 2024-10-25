package leetcode.medium.m_1245_tree_diameter;

//https://leetcode.com/problems/tree-diameter/description/

import java.util.*;

class Result {
    int farthestNode;
    int maxDiameter;

    public void reset() {
        this.farthestNode = 0;
        this.maxDiameter = 0;
    }
}

class Solution {
    public static int treeDiameter(int[][] edges) {
        if (edges.length == 0) {
            return 0;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        int startNode = edges[0][0];
        Result result = new Result();
        boolean[] visited = new boolean[edges.length + 1];

        dfs(graph, startNode, 0, visited, result);

        startNode = result.farthestNode;
        result.reset();
        Arrays.fill(visited, false);

        dfs(graph, startNode, 0, visited, result);

        return result.maxDiameter;
    }

    private static void dfs(Map<Integer, Set<Integer>> graph, int currentNode, int distance, boolean[] visited, Result result) {
        visited[currentNode] = true;

        if (distance > result.maxDiameter) {
            result.maxDiameter = distance;
            result.farthestNode = currentNode;
        }

        for (int adjacentNode : graph.get(currentNode)) {
            if (!visited[adjacentNode]) {
                dfs(graph, adjacentNode, distance + 1, visited, result);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {0, 3}, {3, 4}, {2, 5}, {3, 6}};
        System.out.println(treeDiameter(edges));
    }
}
