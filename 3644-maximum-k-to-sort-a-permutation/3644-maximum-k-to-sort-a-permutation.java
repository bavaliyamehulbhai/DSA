class Solution {
    public int sortPermutation(int[] nums) {
        int ans = (1 << 30) - 1;
        boolean sorted = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                sorted = false;
                ans &= i;
                ans &= nums[i];
            }
        }
        return sorted ? 0 : ans;
    }
}