class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rightDest = new int[m][n];
        int[][] downDest = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    int id = i * n + j;
                    rightDest[i][j] = id;
                    downDest[i][j] = id;
                } else {
                    rightDest[i][j] =
                            (i + 1 < m) ? downDest[i + 1][j] : -1;

                    downDest[i][j] =
                            (j + 1 < n) ? rightDest[i][j + 1] : -1;
                }
            }
        }
        long[][] dp = new long[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                long ways = 0;
                if (j + 1 < n) {
                    int dest = rightDest[i][j + 1];
                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;
                        ways += dp[r][c];
                    }
                }
                if (i + 1 < m) {
                    int dest = downDest[i + 1][j];
                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;
                        ways += dp[r][c];
                    }
                }
                dp[i][j] = ways % MOD;
            }
        }
        return (int) dp[0][0];
    }
}