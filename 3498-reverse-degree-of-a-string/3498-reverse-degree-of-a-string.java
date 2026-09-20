class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        
        // Loop through each character with 1-indexed position
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int revIndex = 26 - (ch - 'a');
            
            total += revIndex * (i + 1);
        }
        return total;
    }
}
