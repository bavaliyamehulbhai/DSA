class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; 
            arr[i][1] = i;       
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> current = new ArrayList<>();
        current.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (arr[i][0] - arr[i-1][0] <= limit) {
                current.add(arr[i]);
            } else {
                groups.add(new ArrayList<>(current));
                current.clear();
                current.add(arr[i]);
            }
        }
        groups.add(current);
        
        int[] result = new int[n];
        for (List<int[]> group : groups) {
            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();
            
            for (int[] p : group) {
                indices.add(p[1]);
                values.add(p[0]);
            }
            
            Collections.sort(indices);
            Collections.sort(values);
            
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }
        return result;
    }
}
