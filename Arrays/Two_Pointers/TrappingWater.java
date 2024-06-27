class Solution {

    public int trap(int[] height) {
        int[] trappedWater = new int[height.length];
        int maxLeftHeight = 0;
        int maxRightHeight = 0;
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            maxLeftHeight = Math.max(maxLeftHeight, height[i]);
            trappedWater[i] = maxLeftHeight - height[i];
        }
        for (int i = height.length - 1; i > 0; i--) {
            maxRightHeight = Math.max(maxRightHeight, height[i]);
            trappedWater[i] = Math.min(
                trappedWater[i],
                maxRightHeight - height[i]
            );
            total += trappedWater[i];
        }
        return total;
    }
}
