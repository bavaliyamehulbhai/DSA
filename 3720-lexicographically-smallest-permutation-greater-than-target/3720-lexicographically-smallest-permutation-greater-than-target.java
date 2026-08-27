public class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        StringBuilder result = new StringBuilder();

        if (dfs(0, n, freq, target, result, false)) {
            return result.toString();
        }
        return "";
    }
    private boolean dfs(int idx, int n, int[] freq, String target, StringBuilder result, boolean greater) {
        if (idx == n) return greater;
        int start = greater ? 0 : target.charAt(idx) - 'a';
        for (int c = start; c < 26; c++) {
            if (freq[c] == 0) continue;
            result.append((char)(c + 'a'));
            freq[c]--;

            boolean newGreater = greater || (c > target.charAt(idx) - 'a');
            if (dfs(idx + 1, n, freq, target, result, newGreater)) return true;
            result.deleteCharAt(result.length() - 1);
            freq[c]++;
        }
        return false;
    }
}
