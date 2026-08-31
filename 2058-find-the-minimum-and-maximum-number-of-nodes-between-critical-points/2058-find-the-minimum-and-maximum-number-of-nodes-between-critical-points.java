/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = new int[]{-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; 

        int firstCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        while (curr != null && curr.next != null) {
            int nextVal = curr.next.val;
            if ((curr.val > prev.val && curr.val > nextVal) || 
                (curr.val < prev.val && curr.val < nextVal)) {
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevCritical);
                    result[1] = index - firstCritical;
                }
                prevCritical = index;
            }
            prev = curr;
            curr = curr.next;
            index++;
        }
        if (result[1] != -1) { 
            result[0] = minDistance;
        }

        return result;
    }
}
