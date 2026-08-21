class Solution {
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
    private long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;  
        for (int mask = 1; mask < (1 << n); mask++) {
            long l = 1;
            int bits = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    l = lcm(l, coins[i]);
                    bits++;
                    if (l > x) break; // optimization
                }
            }
            if (l <= x) {
                if (bits % 2 == 1) total += x / l;
                else total -= x / l;
            }
        }
        return total;
    }
    public long findKthSmallest(int[] coins, long k) {
        long low = Arrays.stream(coins).min().getAsInt();
        long high = low * k;
        long ans = -1;

        while (low <= high) {
            long mid = (low + high) / 2;
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
