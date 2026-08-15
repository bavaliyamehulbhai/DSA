class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean allZero = true;
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                allZero = false;
            }
        }
        int n = nums.length;
        if (totalXor != 0) {
            return n; 
        } else {
            if (allZero) {
                return 0; 
            } else {
                return n == 1 ? 0 : n - 1; 
            }
        }
    }
}
