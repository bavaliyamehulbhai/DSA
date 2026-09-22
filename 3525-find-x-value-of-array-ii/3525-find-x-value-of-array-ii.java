import java.util.*;

class Solution {
    static class Node {
        int total;
        int[] pref;
        Node(int k) {
            pref = new int[k];
            total = 1 % k;
        }
    }
    static class SegTree {
        int n, k;
        Node[] tree;
        int[] arr;
        SegTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.arr = nums;
            tree = new Node[4 * n];
            for (int i = 0; i < tree.length; i++) tree[i] = new Node(k);
            build(1, 0, n - 1);
        }
        void build(int node, int l, int r) {
            if (l == r) {
                Arrays.fill(tree[node].pref, 0);
                int rem = arr[l] % k;
                tree[node].total = rem;
                tree[node].pref[rem] = 1;
                return;
            }
            int mid = (l + r) >>> 1;
            build(node << 1, l, mid);
            build(node << 1 | 1, mid + 1, r);
            mergeNode(node);
        }
        void mergeNode(int node) {
            Node left = tree[node << 1];
            Node right = tree[node << 1 | 1];
            Node cur = tree[node];
            Arrays.fill(cur.pref, 0);
            cur.total = (left.total * right.total) % k;
            for (int t = 0; t < k; t++) cur.pref[t] += left.pref[t];
            for (int r = 0; r < k; r++) {
                int cnt = right.pref[r];
                if (cnt == 0) continue;
                int combined = (left.total * r) % k;
                cur.pref[combined] += cnt;
            }
        }
        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val % k);
        }
        void update(int node, int l, int r, int idx, int remVal) {
            if (l == r) {
                Arrays.fill(tree[node].pref, 0);
                tree[node].total = remVal;
                tree[node].pref[remVal] = 1;
                return;
            }
            int mid = (l + r) >>> 1;
            if (idx <= mid) update(node << 1, l, mid, idx, remVal);
            else update(node << 1 | 1, mid + 1, r, idx, remVal);
            mergeNode(node);
        }
        Node queryRange(int ql, int qr) {
            return queryRange(1, 0, n - 1, ql, qr);
        }
        Node queryRange(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) {
                Node neutral = new Node(k);
                Arrays.fill(neutral.pref, 0);
                neutral.total = 1 % k;
                return neutral;
            }
            if (ql <= l && r <= qr) {
                Node res = new Node(k);
                res.total = tree[node].total;
                System.arraycopy(tree[node].pref, 0, res.pref, 0, k);
                return res;
            }
            int mid = (l + r) >>> 1;
            Node leftNode = queryRange(node << 1, l, mid, ql, qr);
            Node rightNode = queryRange(node << 1 | 1, mid + 1, r, ql, qr);
            Node res = new Node(k);
            Arrays.fill(res.pref, 0);
            res.total = (leftNode.total * rightNode.total) % k;
            for (int t = 0; t < k; t++) res.pref[t] += leftNode.pref[t];
            for (int rrem = 0; rrem < k; rrem++) {
                int cnt = rightNode.pref[rrem];
                if (cnt == 0) continue;
                int combined = (leftNode.total * rrem) % k;
                res.pref[combined] += cnt;
            }
            return res;
        }
    }
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        int[] ans = new int[q];
        int[] remArr = new int[n];
        for (int i = 0; i < n; i++) remArr[i] = nums[i] % k;
        SegTree seg = new SegTree(remArr, k);
        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            seg.update(idx, val % k);
            Node res = seg.queryRange(start, n - 1);
            ans[i] = res.pref[x];
        }
        return ans;
    }
}
