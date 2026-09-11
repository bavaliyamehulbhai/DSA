public class Solution {
    static final int MOD = 1000000007;

    public int numberOfPermutations(int n, int[][] requirements) {
        int MAXCNT = 400;
        int[] need = new int[n + 1];
        Arrays.fill(need, -1);
        for (int[] r : requirements) need[r[0] + 1] = r[1];

        int[][] dp = new int[n + 1][MAXCNT + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            long prefix = 0;
            for (int s = 0; s <= MAXCNT; s++) {
                prefix = (prefix + dp[i - 1][s]) % MOD;
                if (s - i >= 0) {
                    prefix = (prefix - dp[i - 1][s - i] + MOD) % MOD;
                }
                dp[i][s] = (int) prefix;
            }
            if (need[i] != -1) {
                int want = need[i];
                for (int s = 0; s <= MAXCNT; s++) {
                    if (s != want) dp[i][s] = 0;
                }
            }
        }

        if (need[n] != -1) {
            return dp[n][need[n]];
        } else {
            long ans = 0;
            for (int s = 0; s <= MAXCNT; s++) ans = (ans + dp[n][s]) % MOD;
            return (int) ans;
        }
    }
}
