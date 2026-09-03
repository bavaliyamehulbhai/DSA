class Solution {
    public boolean uniformArray(int[] nums1) {
        Arrays.sort(nums1); 
        boolean allOdd = true, allEven = true;
        for (int num : nums1) {
            if (num % 2 == 0) allOdd = false;
            else allEven = false;
        }
        if (allOdd || allEven) return true;
        int smallestOdd = Integer.MAX_VALUE;
        int smallestEven = Integer.MAX_VALUE;
        for (int num : nums1) {
            if (num % 2 == 0) smallestEven = Math.min(smallestEven, num);
            else smallestOdd = Math.min(smallestOdd, num);
        }
        boolean canAllOdd = true;
        for (int num : nums1) {
            if (num % 2 == 0) { 
                if (num - smallestOdd < 1 || (num - smallestOdd) % 2 == 0) {
                    canAllOdd = false;
                    break;
                }
            }
        }
        boolean canAllEven = true;
        for (int num : nums1) {
            if (num % 2 == 1) { 
                if (num - smallestEven < 1 || (num - smallestEven) % 2 == 1) {
                    canAllEven = false;
                    break;
                }
            }
        }
        return canAllOdd || canAllEven;
    }
}
