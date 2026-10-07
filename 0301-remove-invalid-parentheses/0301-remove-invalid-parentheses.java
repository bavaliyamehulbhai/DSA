class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();
        if (s == null) return new ArrayList<>();

        int leftRem = 0, rightRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) leftRem--;
                else rightRem++;
            }
        }

        dfs(s, 0, leftRem, rightRem, 0, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, int leftRem, int rightRem, int balance,
                     StringBuilder path, Set<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                result.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = path.length();

        if (c == '(') {
            if (leftRem > 0) {
                dfs(s, index + 1, leftRem - 1, rightRem, balance, path, result);
            }
            path.append(c);
            dfs(s, index + 1, leftRem, rightRem, balance + 1, path, result);
            path.setLength(len);
        } else if (c == ')') {
            if (rightRem > 0) {
                dfs(s, index + 1, leftRem, rightRem - 1, balance, path, result);
            }
            if (balance > 0) {
                path.append(c);
                dfs(s, index + 1, leftRem, rightRem, balance - 1, path, result);
                path.setLength(len);
            }
        } else {
            path.append(c);
            dfs(s, index + 1, leftRem, rightRem, balance, path, result);
            path.setLength(len);
        }
    }
}
