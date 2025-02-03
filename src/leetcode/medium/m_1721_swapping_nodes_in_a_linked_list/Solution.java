package leetcode.medium.m_1721_swapping_nodes_in_a_linked_list;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int value) {
        this.val = value;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        String str = "";
        ListNode temp = this;
        while (temp != null) {
            str += temp.val + " ";
            temp = temp.next;
        }
        return str + "|";
    }
}

class Solution {
    public static ListNode swapNodes(ListNode head, int k) {
        ListNode prevFirst = null, first = null, prevLast = null, last = null;
        int tempK = k;
        ListNode temp = head;
        tempK--;
        while (tempK > 0) {
            prevFirst = temp;
            temp = temp.next;
            tempK--;
        }
        first = temp;

        last = head;
        while (temp.next != null) {
            prevLast = last;
            temp = temp.next;
            last = last.next;
        }

        System.out.println(prevFirst);
        System.out.println(first);

        System.out.println(prevLast);
        System.out.println(last);

        return null;

    }

    public static ListNode createList(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }

        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createList(arr);

        //System.out.println(head);
        swapNodes(head, 2);
        //System.out.println(head);
    }
}
