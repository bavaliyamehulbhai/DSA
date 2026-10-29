class Solution {
    List<Integer>[] g;
    int[] parent, depth, size, heavy;
    int[] head, pos;
    int curPos;
    SegmentTree seg;
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        dfs(0, -1);
        head = new int[n];
        pos = new int[n];
        curPos = 0;
        decompose(0, 0);
        char[] chars = s.toCharArray();
        int[] base = new int[n];
        for (int i = 0; i < n; i++) {
            base[pos[i]] = 1 << (chars[i] - 'a');
        }
        seg = new SegmentTree(base);
        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                chars[node] = c;
                seg.update(
                    pos[node],
                    1 << (c - 'a')
                );
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }
    private int dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int maxSize = 0;
        for (int v : g[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            int childSize = dfs(v, u);
            size[u] += childSize;
            if (childSize > maxSize) {
                maxSize = childSize;
                heavy[u] = v;
            }
        }
        return size[u];
    }
    private void decompose(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        for (int v : g[u]) {
            if (v == parent[u] || v == heavy[u]) {
                continue;
            }
            decompose(v, v);
        }
    }
    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            res ^= seg.query(
                pos[head[u]],
                pos[u]
            );
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        res ^= seg.query(pos[u], pos[v]);
        return res;
    }
    static class SegmentTree {
        int n;
        int[] tree;
        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[n << 1];
            for (int i = 0; i < n; i++) {
                tree[n + i] = arr[i];
            }
            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[i << 1] ^ tree[i << 1 | 1];
            }
        }
        void update(int idx, int val) {
            idx += n;
            tree[idx] = val;
            while ((idx >>= 1) > 0) {
                tree[idx] =
                    tree[idx << 1]
                    ^ tree[idx << 1 | 1];
            }
        }
        int query(int l, int r) {
            int res = 0;
            l += n;
            r += n;
            while (l <= r) {
                if ((l & 1) == 1) {
                    res ^= tree[l++];
                }
                if ((r & 1) == 0) {
                    res ^= tree[r--];
                }
                l >>= 1;
                r >>= 1;
            }
            return res;
        }
    }
}