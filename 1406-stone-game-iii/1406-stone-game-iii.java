class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[4]; 
        for (int i = n - 1; i >= 0; i--) {
            int take = 0;
            int best = Integer.MIN_VALUE;
            for (int k = 0; k < 3 && i + k < n; k++) {
                take += stoneValue[i + k];
                best = Math.max(best, take - dp[(i + k + 1) % 4]);
            }
            dp[i % 4] = best;
        }
        int result = dp[0];
        if (result > 0) return "Alice";
        if (result < 0) return "Bob";
        return "Tie";
    }
}
