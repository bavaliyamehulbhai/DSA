class Solution {
    public String removeDuplicates(String s) {
        char[] arr = s.toCharArray();
        int top = -1; // stack pointer
        for (char ch : arr) {
            if (top >= 0 && arr[top] == ch) {
                top--; // pop
            } else {
                arr[++top] = ch; // push
            }
        }
        return new String(arr, 0, top + 1);
    }
}