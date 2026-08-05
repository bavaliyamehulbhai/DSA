class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> reverse = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
        for (int[] edge : invocations) {
            int a = edge[0], b = edge[1];
            graph.get(a).add(b);
            reverse.get(b).add(a);
        }
        Set<Integer> suspicious = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(k);
        suspicious.add(k);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nei : graph.get(cur)) {
                if (!suspicious.contains(nei)) {
                    suspicious.add(nei);
                    q.add(nei);
                }
            }
        }
        for (int s : suspicious) {
            for (int caller : reverse.get(s)) {
                if (!suspicious.contains(caller)) {
                    // Invalid removal
                    List<Integer> all = new ArrayList<>();
                    for (int i = 0; i < n; i++) all.add(i);
                    return all;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious.contains(i)) result.add(i);
        }
        return result;
    }
}
