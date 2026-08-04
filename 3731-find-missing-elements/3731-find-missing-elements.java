class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);  // sort the array
        List<Integer> missing = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 1; i++) {
            int current = nums[i];
            int next = nums[i + 1];
            
            // add numbers between current and next
            for (int j = current + 1; j < next; j++) {
                missing.add(j);
            }
        }
        
        return missing;
    }
}
