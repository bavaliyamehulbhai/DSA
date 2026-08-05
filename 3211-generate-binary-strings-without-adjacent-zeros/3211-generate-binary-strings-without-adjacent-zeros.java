class Solution {
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        generate(n, "", result);
        return result;
    }
    private void generate(int n, String str, List<String> result) {
        if (str.length() == n) {
            result.add(str);
            return;
        }
        generate(n, str + "1", result);
        
        if (str.isEmpty() || str.charAt(str.length() - 1) != '0') {
            generate(n, str + "0", result);
        }
    }
}
