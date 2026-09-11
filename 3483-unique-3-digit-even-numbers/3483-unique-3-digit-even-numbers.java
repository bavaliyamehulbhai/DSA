public class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) freq[d]++;

        int count = 0;
        for (int num = 100; num <= 999; num++) {
            if (num % 2 != 0) continue; 
            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            int[] need = new int[10];
            need[a]++; need[b]++; need[c]++;

            boolean ok = true;
            for (int d = 0; d < 10; d++) {
                if (need[d] > freq[d]) {
                    ok = false;
                    break;
                }
            }
            if (ok) count++;
        }
        return count;
    }
}
