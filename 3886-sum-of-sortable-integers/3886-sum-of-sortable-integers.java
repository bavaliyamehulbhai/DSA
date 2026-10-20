class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int ans = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && isSortable(nums, sorted, k)) {
                ans += k;
            }
        }
        return ans;
    }

    private boolean isSortable(int[] nums, int[] sorted, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i += k) {
            int[] blockOriginal = Arrays.copyOfRange(nums, i, i + k);
            int[] blockSorted = Arrays.copyOfRange(sorted, i, i + k);
            if (!isRotation(blockOriginal, blockSorted)) {
                return false;
            }
        }
        return true;
    }
    private boolean isRotation(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        int m = arr1.length;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int x : arr1) sb1.append(x).append(",");
        for (int x : arr2) sb2.append(x).append(",");

        String s1 = sb1.toString();
        String s2 = sb2.toString();

        return (s2 + s2).contains(s1);
    }
}
