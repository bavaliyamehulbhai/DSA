class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int k = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                k = i;
                break;
            }
        }
        boolean inc = true;
        for (int t = 0; t < n; t++) {
            if (nums[(k + t) % n] != t) {
                inc = false;
                break;
            }
        }
        boolean dec = true;
        for (int t = 0; t < n; t++) {
            if (nums[(k - t + n) % n] != t) {
                dec = false;
                break;
            }
        }
        int ans = Integer.MAX_VALUE;
        if (inc) {
            ans = Math.min(ans, k);
            ans = Math.min(ans, (n - k) + 2);
        }
        if (dec) {
            ans = Math.min(ans, (k + 1) + 1);
            ans = Math.min(ans, 1 + (n - 1 - k));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}