class Solution {
    static final long MOD = 1_000_000_007;

    public int maxSum(List<Integer> nums, int k) {
        int n = nums.size();
        int[] bitCount = new int[32]; 
        for (int num : nums) {
            for (int b = 0; b < 32; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }
        long[] constructed = new long[n];
        for (int b = 0; b < 32; b++) {
            int count = bitCount[b];
            for (int i = 0; i < count; i++) {
                constructed[i] |= (1L << b);
            }
        }
        Arrays.sort(constructed);
        long ans = 0;
        for (int i = n - 1; i >= n - k; i--) {
            ans = (ans + (constructed[i] * constructed[i]) % MOD) % MOD;
        }
        return (int) ans;
    }
}
