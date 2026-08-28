class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int halfLen = n / 2;

        // Count characters in s
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // A palindrome can exist only when at most one char has odd frequency
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Counts available for the first half
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        /*
         * pref[i][c] = number of character c in target[0 ... i-1]
         *
         * Used to efficiently check whether target's prefix
         * can be formed using halfFreq.
         */
        int[][] pref = new int[halfLen + 1][26];

        for (int i = 0; i < halfLen; i++) {
            System.arraycopy(pref[i], 0, pref[i + 1], 0, 26);
            pref[i + 1][target.charAt(i) - 'a']++;
        }

        // ------------------------------------------------------------
        // Case 1:
        // target's first half itself can be used.
        // ------------------------------------------------------------
        boolean exactPossible = true;

        for (int c = 0; c < 26; c++) {
            if (pref[halfLen][c] != halfFreq[c]) {
                exactPossible = false;
                break;
            }
        }

        if (exactPossible) {
            String candidate = buildPalindrome(target.substring(0, halfLen), middle, n);

            // Strictly greater than target
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // ------------------------------------------------------------
        // Case 2:
        // Find the lexicographically smallest half > target's half.
        // ------------------------------------------------------------

        for (int i = halfLen - 1; i >= 0; i--) {

            // Prefix target[0 .. i-1]
            int[] remaining = new int[26];
            boolean validPrefix = true;

            for (int c = 0; c < 26; c++) {
                remaining[c] = halfFreq[c] - pref[i][c];

                if (remaining[c] < 0) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';

            // Find the smallest character > target[i]
            // that is available.
            for (int bigger = targetChar + 1; bigger < 26; bigger++) {

                if (remaining[bigger] == 0) {
                    continue;
                }

                // Use this bigger character
                remaining[bigger]--;

                // Build the smallest possible suffix
                StringBuilder half = new StringBuilder();

                // Original target prefix
                for (int j = 0; j < i; j++) {
                    half.append(target.charAt(j));
                }

                // Bigger character at position i
                half.append((char) ('a' + bigger));

                // Remaining characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (remaining[c] > 0) {
                        half.append((char) ('a' + c));
                        remaining[c]--;
                    }
                }

                return buildPalindrome(half.toString(), middle, n);
            }
        }

        return "";
    }

    private String buildPalindrome(String half, char middle, int n) {
        StringBuilder ans = new StringBuilder();

        // First half
        ans.append(half);

        // Middle character for odd length
        if ((n & 1) == 1) {
            ans.append(middle);
        }
        for (int i = half.length() - 1; i >= 0; i--) {
            ans.append(half.charAt(i));
        }

        return ans.toString();
    }
}