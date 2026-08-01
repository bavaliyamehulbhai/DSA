class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n];
        return helper(nums, 0, n - 1, dp) >= 0;
    }

    private int helper(int[] nums, int i, int j, Integer[][] dp) {
        if (i == j) return nums[i];  // base case
        if (dp[i][j] != null) return dp[i][j];

        // choose left or right
        int pickLeft = nums[i] - helper(nums, i + 1, j, dp);
        int pickRight = nums[j] - helper(nums, i, j - 1, dp);

        dp[i][j] = Math.max(pickLeft, pickRight);
        return dp[i][j];
    }
}
