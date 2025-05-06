package ReverseNodesInKGroup;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    @Override
    public String toString() {
        return val + "->" + (next != null ? next.toString() : "null");
    }
}
class Solution {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ListNode reverse = new Solution().reverseKGroup(node, 2);
        System.out.println(reverse);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 最终答案
        ListNode answer = head;
        ListNode tail = head;
        ListNode preSegamentTail = null;
        int i = 0, step = k - 1;
        while(i++ < step) {
            if(tail == null || tail.next == null) break;
            tail = tail.next;
            // 找到翻转位置
            if(i == step) {
                // 现将后续断开
                ListNode nextSegament = tail.next;
                tail.next = null;
                // 进行翻转,实际上等于
                reverse(head);
                // 再将原先的列表放置回原先列表中
                if(answer == head) {
                    // 新列表表头
                    answer = tail;
                } else {
                    // 将上个表头链接起来
                    preSegamentTail.next = tail;
                }
                head.next = nextSegament;
                // 更新上个片段尾
                preSegamentTail = head;
                // 当前head为下个片段
                head = nextSegament;
                // 将tail与head同步，方便查找下k个内容
                tail = head;
                // 置空i，进行下次循环
                i=0;
            }
        }
        return answer;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = head;
        ListNode current = head.next;
        ListNode next = null;
        pre.next = null;
        while(current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }
}