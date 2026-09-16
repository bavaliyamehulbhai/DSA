class Solution {
    static final int MOD = 1000000007;
    public int numberOfSets(int n, int k) {
        int max = n + k; 
        long[] fact = new long[max+1];
        long[] invFact = new long[max+1];

        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = fact[i-1] * i % MOD;
        }

        invFact[max] = modInverse(fact[max]);
        for (int i = max-1; i >= 0; i--) {
            invFact[i] = invFact[i+1] * (i+1) % MOD;
        }

        return (int) nCr(n+k-1, 2*k, fact, invFact);
    }

    private long nCr(int n, int r, long[] fact, long[] invFact) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n-r] % MOD;
    }

    private long modInverse(long x) {
        return pow(x, MOD-2);
    }

    private long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}
