package MergeKSortedLists;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {

    public static void main(String[] args) {

        ListNode[] lists = new ListNode[3];
        // [[1,4,5],[1,3,4],[2,6]]
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);


        Solution solution = new Solution();
        System.out.println(solution.mergeKLists(lists));
    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode answer = null;
        ListNode rear = answer;
        while(checkIndex(lists)) {
            int temp = -1;
            for(int i = 0, size = lists.length; i < size; i++) {
                if(lists[i] == null) continue;
                if(temp == -1 || lists[i].val < lists[temp].val) temp = i;
            }
            if(temp == -1) break;
            if(answer == null) {
                answer = lists[temp];
                rear = answer;
            } else {
                rear.next = lists[temp];
                rear = rear.next;
            }
            lists[temp] = lists[temp].next;
        }
        return answer;
    }

    // 返回true，表明仍有数据
    private boolean checkIndex(ListNode[] lists) {
        for(int i = 0, size = lists.length; i < size; i++) {
            if(lists[i] != null) {
                return true;
            }
        }
        return false;
    }
}