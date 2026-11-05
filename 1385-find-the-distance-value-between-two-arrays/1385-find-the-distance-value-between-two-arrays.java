class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int x : arr1) {
            int idx = Arrays.binarySearch(arr2, x);

            if (idx >= 0) {
                if (0 > d) count++;
                continue;
            }

            idx = -idx - 1; // insertion point

            int minDiff = Integer.MAX_VALUE;

            if (idx < arr2.length) {
                minDiff = Math.min(minDiff, Math.abs(x - arr2[idx]));
            }

            if (idx > 0) {
                minDiff = Math.min(minDiff, Math.abs(x - arr2[idx - 1]));
            }

            if (minDiff > d) {
                count++;
            }
        }
        return count;
    }
}