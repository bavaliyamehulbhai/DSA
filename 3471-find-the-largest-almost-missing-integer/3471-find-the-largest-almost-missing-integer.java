class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i <= n - k; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }
            for (int x : seen) {
                countMap.put(x, countMap.getOrDefault(x, 0) + 1);
            }
        }

        int result = -1;
        for (int x : countMap.keySet()) {
            if (countMap.get(x) == 1) {
                result = Math.max(result, x);
            }
        }
        return result;
    }
}
