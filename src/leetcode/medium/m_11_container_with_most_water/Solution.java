package leetcode.medium.m_11_container_with_most_water;

//https://leetcode.com/problems/container-with-most-water/description/

class Solution {
    public static int maxArea(int[] height) {
        int leftIndex = 0, rightIndex = height.length - 1;

        int maxTrappedWater = 0;

        while (leftIndex < rightIndex) {
            int currentTrappedWater = Math.min(height[leftIndex], height[rightIndex]) * (rightIndex - leftIndex);
            maxTrappedWater = Math.max(maxTrappedWater, currentTrappedWater);

            if (height[leftIndex] < height[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
        return maxTrappedWater;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 12, 5, 12, 8, 3, 7};
        System.out.println(maxArea(height));
    }
}
