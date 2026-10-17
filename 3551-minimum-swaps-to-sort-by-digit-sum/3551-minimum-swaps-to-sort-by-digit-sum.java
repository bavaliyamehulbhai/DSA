class Solution {
    public int minSwaps(int[] nums) { 
        int n = nums.length;
        
        int[][] arr = new int[n][3]; 
        for (int i = 0; i < n; i++) {
            arr[i][0] = digitSum(nums[i]);
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }
        
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int[] targetPos = new int[n];
        for (int i = 0; i < n; i++) {
            targetPos[arr[i][2]] = i;
        }
        
        boolean[] visited = new boolean[n];
        int swaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || targetPos[i] == i) continue;
            
            int cycleLen = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = targetPos[j];
                cycleLen++;
            }
            swaps += cycleLen - 1;
        }
        
        return swaps;
    }
    
    private int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
