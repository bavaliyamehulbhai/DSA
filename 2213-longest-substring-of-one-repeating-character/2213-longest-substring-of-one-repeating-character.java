class Solution {
    static class Node {
        char prefixChar, suffixChar;
        int prefixLen, suffixLen, maxLen;
    }

    Node[] tree;
    char[] arr;
    int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            res[i] = tree[1].maxLen; // root stores global answer
        }
        return res;
    }

    private void build(int idx, int l, int r) {
        tree[idx] = new Node();
        if (l == r) {
            tree[idx].prefixChar = tree[idx].suffixChar = arr[l];
            tree[idx].prefixLen = tree[idx].suffixLen = tree[idx].maxLen = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);
        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1], l, r, mid);
    }

    private void update(int idx, int l, int r, int pos, char c) {
        if (l == r) {
            arr[pos] = c;
            tree[idx].prefixChar = tree[idx].suffixChar = c;
            tree[idx].prefixLen = tree[idx].suffixLen = tree[idx].maxLen = 1;
            return;
        }
        int mid = (l + r) / 2;
        if (pos <= mid) update(idx * 2, l, mid, pos, c);
        else update(idx * 2 + 1, mid + 1, r, pos, c);
        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1], l, r, mid);
    }

    private Node merge(Node left, Node right, int l, int r, int mid) {
        Node res = new Node();
        res.prefixChar = left.prefixChar;
        res.suffixChar = right.suffixChar;

        // Prefix
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == (mid - l + 1) && left.prefixChar == right.prefixChar) {
            res.prefixLen += right.prefixLen;
        }

        // Suffix
        res.suffixLen = right.suffixLen;
        if (right.suffixLen == (r - mid) && right.suffixChar == left.suffixChar) {
            res.suffixLen += left.suffixLen;
        }

        // Max
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffixChar == right.prefixChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }
}
