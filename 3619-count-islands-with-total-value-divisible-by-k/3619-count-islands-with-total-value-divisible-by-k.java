class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int ans = 0;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] <= 0 || visited[r][c]) {
                    continue;
                }
                long sum = 0;
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[]{r, c});
                visited[r][c] = true;
                while (!stack.isEmpty()) {
                    int[] cur = stack.pop();
                    int x = cur[0];
                    int y = cur[1];
                    sum += grid[x][y];
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dr[d];
                        int ny = y + dc[d];

                        if (nx >= 0 && nx < m &&
                            ny >= 0 && ny < n &&
                            grid[nx][ny] > 0 &&
                            !visited[nx][ny]) {

                            visited[nx][ny] = true;
                            stack.push(new int[]{nx, ny});
                        }
                    }
                }
                if (sum % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}