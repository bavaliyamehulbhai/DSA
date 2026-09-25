class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }
    private Set<String> parse(String expr) {
        List<Set<String>> parts = new ArrayList<>();
        Set<String> curr = new HashSet<>();
        curr.add("");
        int i = 0;

        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (c == '{') {
                int j = i, bal = 0;
                do {
                    if (expr.charAt(j) == '{') bal++;
                    if (expr.charAt(j) == '}') bal--;
                    j++;
                } while (bal > 0);
                Set<String> sub = parse(expr.substring(i + 1, j - 1));
                curr = concat(curr, sub);
                i = j;
            } else if (c == ',') {
                parts.add(curr);
                curr = new HashSet<>();
                curr.add("");
                i++;
            } else {
                Set<String> sub = new HashSet<>();
                sub.add(String.valueOf(c));
                curr = concat(curr, sub);
                i++;
            }
        }
        parts.add(curr);
        Set<String> res = new HashSet<>();
        for (Set<String> p : parts) res.addAll(p);
        return res;
    }
    private Set<String> concat(Set<String> a, Set<String> b) {
        Set<String> res = new HashSet<>();
        for (String x : a) {
            for (String y : b) {
                res.add(x + y);
            }
        }
        return res;
    }
}
