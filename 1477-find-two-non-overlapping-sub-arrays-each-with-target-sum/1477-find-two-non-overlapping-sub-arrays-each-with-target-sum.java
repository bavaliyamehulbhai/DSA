class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        int sum = 0, left = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;
                minLen = Math.min(minLen, len);
                best[right] = len;
            }

            if (right > 0) {
                best[right] = Math.min(best[right], best[right - 1]);
            }
        }

        int ans = Integer.MAX_VALUE;
        sum = 0;
        left = 0;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, best[left - 1] + len);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
