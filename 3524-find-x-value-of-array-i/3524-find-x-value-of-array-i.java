class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; 

        for (int num : nums) {
            long[] newDp = new long[k];
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newR = (int)((long)r * num % k);
                    newDp[newR] += dp[r];
                }
            }
            newDp[num % k]++;
            dp = newDp;
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }
        return result;
    }
}
