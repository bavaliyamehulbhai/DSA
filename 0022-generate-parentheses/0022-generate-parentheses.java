class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", n, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(current);
            return;
        }
        if (left > 0) {
            backtrack(result, current + "(", left - 1, right);
        }
        if (right > left) {
            backtrack(result, current + ")", left, right - 1);
        }
    }
}
