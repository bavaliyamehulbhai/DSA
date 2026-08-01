class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int low = 1;
        int high = 0;
        for (int q : quantities) {
            high = Math.max(high, q);
        }
        int answer = high;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (canDistribute(quantities, n, mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    private boolean canDistribute(int[] quantities, int n, int maxAllowed) {
        int storesNeeded = 0;
        for (int q : quantities) {
            storesNeeded += (q + maxAllowed - 1) / maxAllowed;
            if (storesNeeded > n) return false;
        }
        return true;
    }
}
