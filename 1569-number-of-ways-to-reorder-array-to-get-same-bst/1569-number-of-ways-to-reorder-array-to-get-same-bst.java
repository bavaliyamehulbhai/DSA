class Solution {
    static final int MOD = 1_000_000_007;
    long[][] comb; 
    
    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
       
        long ways = dfs(toList(nums));
        
        return (int)((ways - 1 + MOD) % MOD);
    }
    
    private long dfs(List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) return 1; // Base case
        
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }
        
        long leftWays = dfs(left);
        long rightWays = dfs(right);

        long ways = comb[n - 1][left.size()];
        ways = ways * leftWays % MOD;
        ways = ways * rightWays % MOD;
        
        return ways;
    }
    
    private List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);
        return list;
    }
}
