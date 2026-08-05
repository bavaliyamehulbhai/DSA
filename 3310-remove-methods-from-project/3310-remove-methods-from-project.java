class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        // Build graph
        for (int[] edge : invocations) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // Step 1: Find suspicious using DFS
        boolean[] suspicious = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(k);
        suspicious[k] = true;
        
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int nei : graph.get(cur)) {
                if (!suspicious[nei]) {
                    suspicious[nei] = true;
                    stack.push(nei);
                }
            }
        }
        
        // Step 2: Validate removal
        for (int[] edge : invocations) {
            int a = edge[0], b = edge[1];
            if (!suspicious[a] && suspicious[b]) {
                // Invalid removal
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }
        
        // Step 3: Return non-suspicious
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) result.add(i);
        }
        return result;
    }
}
