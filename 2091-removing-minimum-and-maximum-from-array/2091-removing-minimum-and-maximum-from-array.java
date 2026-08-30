class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        int front = Math.max(minIndex, maxIndex) + 1;
        int back = n - Math.min(minIndex, maxIndex);
        int mixed = (Math.min(minIndex, maxIndex) + 1) + (n - Math.max(minIndex, maxIndex));
        return Math.min(front, Math.min(back, mixed));
    }
}
