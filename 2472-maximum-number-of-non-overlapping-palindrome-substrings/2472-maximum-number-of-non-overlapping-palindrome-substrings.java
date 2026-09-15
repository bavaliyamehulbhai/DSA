public class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        List<int[]> palindromes = new ArrayList<>();
        
        for (int center = 0; center < n; center++) {
            expand(s, center, center, k, palindromes);
            expand(s, center, center + 1, k, palindromes);
        }
        
        palindromes.sort((a, b) -> a[1] - b[1]);
        
        int count = 0, lastEnd = -1;
        for (int[] p : palindromes) {
            if (p[0] > lastEnd) {
                count++;
                lastEnd = p[1];
            }
        }
        return count;
    }
    
    private void expand(String s, int left, int right, int k, List<int[]> palindromes) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 >= k) {
                palindromes.add(new int[]{left, right});
                break;
            }
            left--;
            right++;
        }
    }
}
