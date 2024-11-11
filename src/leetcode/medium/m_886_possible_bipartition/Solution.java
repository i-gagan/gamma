package leetcode.medium.m_886_possible_bipartition;

//https://leetcode.com/problems/possible-bipartition/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum COLOR {
    WHITE,
    BLACK,
    NO_COLOUR
}

class Solution {
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());

        for (int[] edge : dislikes) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        COLOR[] colours = new COLOR[n + 1];
        Arrays.fill(colours, COLOR.NO_COLOUR);

        for (int node = 1; node <= n; node++) {
            if (colours[node] == COLOR.NO_COLOUR) {
                if (!dfs(node, COLOR.WHITE, graph, colours)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(int currentNode, COLOR cuurentNodeColor, List<Integer>[] graph, COLOR[] colors) {
        colors[currentNode] = cuurentNodeColor;

        for (int neighbor : graph[currentNode]) {
            if (colors[neighbor] == cuurentNodeColor) {
                return false;
            }
            if (colors[neighbor] == COLOR.NO_COLOUR) {
                COLOR neighborColor = (colors[currentNode] == COLOR.WHITE ? COLOR.BLACK : COLOR.WHITE);
                if (!dfs(neighbor, neighborColor, graph, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 4}};
        System.out.println(possibleBipartition(n, dislikes));
    }
}
