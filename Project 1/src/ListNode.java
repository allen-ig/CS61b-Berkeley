public class ListNode {
    int[] val;
    ListNode next;
    ListNode prev;

    ListNode(int x, int y) {
        val = new int[]{x, x, x, y};
    }

    ListNode(int r, int g, int b, int l){
        val = new int[]{r, g, b, l};
    }

    ListNode(int x, int y, ListNode n){
        val = new int[]{x, x, x, y};
        next = n;
    }
}