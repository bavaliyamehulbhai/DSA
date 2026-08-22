class Solution {
    private int[][] moves = {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0) return false;
        return dfs(grid, 0, 0, 0, n);
    }
    private boolean dfs(int[][] grid, int step, int row, int col, int n) {
        if (step == n * n - 1) return true; 
        for (int[] move : moves) {
            int nr = row + move[0];
            int nc = col + move[1];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == step + 1) {
                return dfs(grid, step + 1, nr, nc, n);
            }
        }
        return false; 
    }
}
