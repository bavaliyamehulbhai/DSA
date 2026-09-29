class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Boolean[][][] memo = new Boolean[m][n][m+n+1];
        return dfs(0, 0, 0, grid, memo);
    }
    private boolean dfs(int i, int j, int balance, char[][] grid, Boolean[][][] memo) {
        if (grid[i][j] == '(') balance++;
        else balance--;

        if (balance < 0) return false;
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return balance == 0;

        if (memo[i][j][balance] != null) return memo[i][j][balance];

        boolean res = false;
        if (i + 1 < grid.length) res |= dfs(i+1, j, balance, grid, memo);
        if (j + 1 < grid[0].length) res |= dfs(i, j+1, balance, grid, memo);

        return memo[i][j][balance] = res;
    }
}
