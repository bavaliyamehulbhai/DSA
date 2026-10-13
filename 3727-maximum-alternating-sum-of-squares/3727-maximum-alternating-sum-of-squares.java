import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        Integer[] arr = Arrays.stream(nums)
                              .map(Math::abs)
                              .boxed()
                              .toArray(Integer[]::new);
        Arrays.sort(arr);
        long score = 0;
        int left = 0, right = n - 1;
        boolean positiveTurn = true;

        while (left <= right) {
            if (positiveTurn) {
                long sq = (long) arr[right] * arr[right];
                score += sq;
                right--;
            } else {
                long sq = (long) arr[left] * arr[left];
                score -= sq;
                left++;
            }
            positiveTurn = !positiveTurn;
        }
        return score;
    }
}
