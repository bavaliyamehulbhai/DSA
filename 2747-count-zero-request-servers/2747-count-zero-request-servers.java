class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int q = queries.length;

        int[][] qs = new int[q][2];

        for (int i = 0; i < q; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }

        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[q];

        int[] freq = new int[n + 1];

        int left = 0;
        int right = 0;

        int activeServers = 0;

        for (int[] query : qs) {

            int time = query[0];
            int idx = query[1];

            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                if (freq[server] == 0) {
                    activeServers++;
                }

                freq[server]++;
                right++;
            }

            while (left < logs.length && logs[left][1] < time - x) {
                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    activeServers--;
                }

                left++;
            }
            ans[idx] = n - activeServers;
        }
        return ans;
    }
}