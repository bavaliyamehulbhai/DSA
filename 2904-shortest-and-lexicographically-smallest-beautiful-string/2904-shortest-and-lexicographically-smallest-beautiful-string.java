class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, countOnes = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }
            while (countOnes >= k) {
                if (countOnes == k) {
                    int currLen = right - left + 1;
                    String candidate = s.substring(left, right + 1);
                    if (currLen < minLen) {
                        minLen = currLen;
                        result = candidate;
                    } else if (currLen == minLen && candidate.compareTo(result) < 0) {
                        result = candidate;
                    }
                }
                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }
        return result;
    }
}
