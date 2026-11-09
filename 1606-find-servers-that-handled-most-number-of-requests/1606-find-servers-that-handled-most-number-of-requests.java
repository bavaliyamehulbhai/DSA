class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<long[]> busy =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        int[] count = new int[k];
        int maxCount = 0;

        for (int i = 0; i < arrival.length; i++) {

            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                long[] curr = busy.poll();
                available.add((int) curr[1]);
            }

            if (available.isEmpty()) {
                continue;
            }
            int start = i % k;
            Integer server = available.ceiling(start);
            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;
            maxCount = Math.max(maxCount, count[server]);

            long finishTime = (long) arrival[i] + load[i];
            busy.offer(new long[]{finishTime, server});
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                ans.add(i);
            }
        }
        return ans;
    }
}