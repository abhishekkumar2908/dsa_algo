class Solution {

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // while(nums[right] > target){
        //     right --;
        // }
        while (left < right) {
            int sum = nums[right] + nums[left];
            if (sum < target) left++;
            else if (sum > target) right--;
            else break;
        }
        return new int[] { left + 1, right + 1 };
    }
}
