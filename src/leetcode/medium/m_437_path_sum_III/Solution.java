package leetcode.medium.m_437_path_sum_III;

//https://leetcode.com/problems/path-sum-iii/description/

import graph.tree.Tree;
import graph.tree.TreeNode;

import java.util.HashMap;

class Counter {
    int counter;
}
class Solution {
    public static int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> hashMap = new HashMap<>();
        Counter counter = new Counter();
        long prefixSum = 0;
        solve(root, targetSum, prefixSum, hashMap, counter);
        return counter.counter;
    }

    public static void solve(TreeNode root, int targetSum, long prefixSum, HashMap<Long, Integer> hashMap, Counter counter) {
        if (root == null) {
            return;
        }
        prefixSum = prefixSum + root.val;

        if (hashMap.containsKey(prefixSum - targetSum)) {
            counter.counter += hashMap.get(prefixSum - targetSum);
        }

        if (targetSum == prefixSum) {
            counter.counter++;
        }

        hashMap.put(prefixSum, hashMap.getOrDefault(prefixSum, 0) + 1);

        solve(root.left, targetSum, prefixSum, hashMap, counter);
        solve(root.right, targetSum, prefixSum, hashMap, counter);

        hashMap.put(prefixSum, hashMap.get(prefixSum) - 1);
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 3, 7, 8};

        Tree bt = new Tree();
        bt.root = bt.insertInBT(bt.root, input);
        System.out.println(pathSum(bt.root, 7));
    }
}

