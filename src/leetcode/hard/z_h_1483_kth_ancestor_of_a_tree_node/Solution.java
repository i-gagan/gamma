package leetcode.hard.z_h_1483_kth_ancestor_of_a_tree_node;

class TreeAncestor {
    int[] parent;
    int n;

    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        this.parent = parent;
    }

    public int getKthAncestor(int node, int k) {
        while (k > 0) {
            if (node < n) {
                if (node == 0) {
                    return -1;
                }
                node = parent[node];
            }
            k--;
        }
        return node;
    }
}

class Solution {
    public static void main(String[] args) {
        int n = 7;
        int[] parents = {-1, 0, 0, 1, 1, 2, 2};
        TreeAncestor treeAncestor = new TreeAncestor(n, parents);
        System.out.println(treeAncestor.getKthAncestor(3, 1));
        System.out.println(treeAncestor.getKthAncestor(5, 2));
        System.out.println(treeAncestor.getKthAncestor(6, 3));
    }
}
