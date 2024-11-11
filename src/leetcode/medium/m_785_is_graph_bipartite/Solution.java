package leetcode.medium.m_785_is_graph_bipartite;

//https://leetcode.com/problems/count-the-number-of-complete-components/description/

import java.util.Arrays;

enum COLOR {
    WHITE,
    BLACK,
    NO_COLOUR
}

class Solution {
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        COLOR[] colours = new COLOR[n];
        Arrays.fill(colours, COLOR.NO_COLOUR);

        for (int node = 0; node < n; node++) {
            if (colours[node] == COLOR.NO_COLOUR) {
                if (!dfs(node, COLOR.WHITE, graph, colours)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(int currentNode, COLOR cuurentNodeColor, int[][] graph, COLOR[] colors) {
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
        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println(isBipartite(graph));
    }
}
