import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    static class Fenwick {
        private final int n;
        private final long[] bit;
        Fenwick(int n) {
            this.n = n;
            this.bit = new long[n + 1];
        }
        // add val at index idx (0-based)
        void add(int idx, long val) {
            int i = idx + 1;
            while (i <= n) {
                bit[i] += val;
                i += i & -i;
            }
        }
        long sum(int idx) {
            if (idx < 0) return 0L;
            int i = idx + 1;
            long s = 0L;
            while (i > 0) {
                s += bit[i];
                i -= i & -i;
            }
            return s;
        }
        long rangeSum(int l, int r) {
            if (r < l) return 0L;
            return sum(r) - sum(l - 1);
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> last = new HashMap<>();
        int[] prev = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            prev[i] = last.getOrDefault(x, -1);
            last.put(x, i);
        }

        Integer[] idxs = new Integer[n];
        for (int i = 0; i < n; ++i) idxs[i] = i;
        Arrays.sort(idxs, (a, b) -> Integer.compare(prev[b], prev[a])); 
        Integer[] queries = new Integer[n];
        for (int i = 0; i < n; ++i) queries[i] = i;
        Arrays.sort(queries, (a, b) -> Integer.compare(prev[b], prev[a])); 

        Fenwick bitCount = new Fenwick(n);
        Fenwick bitSumPrev = new Fenwick(n);

        int p = 0;
        long total = 0L;
        for (int mIdx = 0; mIdx < n; ++mIdx) {
            int m = queries[mIdx];
            int a = prev[m];
            while (p < n && prev[idxs[p]] > a) {
                int i = idxs[p];
                bitCount.add(i, 1L);
                bitSumPrev.add(i, prev[i]); 
                p++;
            }

            int l = a + 1;
            int r = m;

            long lenSeg = (long) m - a;
            long firstSum = lenSeg * (lenSeg + 1) / 2L;

            long cnt = bitCount.rangeSum(l, r);
            long sumPrev = bitSumPrev.rangeSum(l, r);
            long secondSum = sumPrev - cnt * (long) a;

            long S_m = firstSum - secondSum;

            long term = 2L * S_m - (m - a);
            long contrib = ((long)(n - m) % MOD) * ((term % MOD + MOD) % MOD) % MOD;
            total = (total + contrib) % MOD;
        }
        return (int)((total % MOD + MOD) % MOD);
    }
}
