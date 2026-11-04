class Solution {

    static class DualHeap {
        PriorityQueue<Integer> small;
        PriorityQueue<Integer> large;
        Map<Integer, Integer> delayed;

        long smallSum;
        long largeSum;

        int smallSize;
        int largeSize;

        DualHeap() {
            small = new PriorityQueue<>((a, b) -> b - a);
            large = new PriorityQueue<>();
            delayed = new HashMap<>();
        }

        private void pruneSmall() {
            while (!small.isEmpty()) {
                int num = small.peek();
                Integer cnt = delayed.get(num);
                if (cnt == null || cnt == 0) break;

                small.poll();
                if (cnt == 1) delayed.remove(num);
                else delayed.put(num, cnt - 1);
            }
        }

        private void pruneLarge() {
            while (!large.isEmpty()) {
                int num = large.peek();
                Integer cnt = delayed.get(num);
                if (cnt == null || cnt == 0) break;

                large.poll();
                if (cnt == 1) delayed.remove(num);
                else delayed.put(num, cnt - 1);
            }
        }

        private void rebalance() {
            if (smallSize > largeSize + 1) {
                int x = small.poll();

                smallSum -= x;
                smallSize--;

                large.offer(x);
                largeSum += x;
                largeSize++;

                pruneSmall();
            } else if (smallSize < largeSize) {
                int x = large.poll();

                largeSum -= x;
                largeSize--;

                small.offer(x);
                smallSum += x;
                smallSize++;

                pruneLarge();
            }
        }

        void add(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                smallSum += num;
                smallSize++;
            } else {
                large.offer(num);
                largeSum += num;
                largeSize++;
            }

            rebalance();
        }

        void remove(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);

            if (num <= small.peek()) {
                smallSize--;
                smallSum -= num;

                if (num == small.peek()) {
                    pruneSmall();
                }
            } else {
                largeSize--;
                largeSum -= num;

                if (!large.isEmpty() && num == large.peek()) {
                    pruneLarge();
                }
            }

            rebalance();
        }

        long getCost() {
            pruneSmall();
            pruneLarge();

            long median = small.peek();

            return median * smallSize - smallSum
                    + largeSum - median * largeSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;

        long[] cost = new long[m];

        DualHeap dh = new DualHeap();

        for (int i = 0; i < x; i++) {
            dh.add(nums[i]);
        }

        cost[0] = dh.getCost();

        for (int i = x; i < n; i++) {
            dh.add(nums[i]);
            dh.remove(nums[i - x]);

            cost[i - x + 1] = dh.getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][m + x + 1];

        for (int t = 1; t <= k; t++) {
            Arrays.fill(dp[t], INF);
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        for (int t = 1; t <= k; t++) {
            for (int i = m - 1; i >= 0; i--) {
                long skip = dp[t][i + 1];

                int next = Math.min(m, i + x);
                long take = cost[i] + dp[t - 1][next];

                dp[t][i] = Math.min(skip, take);
            }
        }
        return dp[k][0];
    }
}