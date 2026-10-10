public class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diffs = new int[n];
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diffs[i]);
        }

        long[] freq = new long[maxDiff + 1];
        for (int d : diffs) {
            freq[d]++;
        }

        long k = (long) k1 + k2;

        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] == 0) continue;
            long count = freq[d];

            if (k >= count) {
                freq[d - 1] += count;
                k -= count;
                freq[d] = 0;
            } else {
                freq[d] -= k;
                freq[d - 1] += k;
                k = 0;
            }
        }

        long ans = 0;
        for (int d = 0; d <= maxDiff; d++) {
            if (freq[d] > 0) {
                ans += (long) d * d * freq[d];
            }
        }

        return ans;
    }
}
