package linkedlist;

public class ListNode2Sum {

     //Definition for singly-linked list.
     public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
          ListNode dummy = new ListNode(0);
          ListNode current = dummy;
          int carry = 0;

          while (l1 != null || l2 != null || carry != 0) {
               int x = (l1 != null) ? l1.val : 0;
               int y = (l2 != null) ? l2.val : 0;
               int sum = x + y + carry;

               carry = sum / 10;
               current.next = new ListNode(sum % 10);
               current = current.next;

               if (l1 != null) {
                    l1 = l1.next;
               }
               if (l2 != null) {
                    l2 = l2.next;
               }
          }

          return dummy.next;
     }

     private ListNode buildList(int... digits) {
          ListNode dummy = new ListNode(0);
          ListNode tail = dummy;
          for (int digit : digits) {
               tail.next = new ListNode(digit);
               tail = tail.next;
          }
          return dummy.next;
     }

     private String listToString(ListNode head) {
          StringBuilder result = new StringBuilder();
          while (head != null) {
               result.append(head.val);
               if (head.next != null) {
                    result.append(" -> ");
               }
               head = head.next;
          }
          return result.toString();
     }

     public static void main(String[] args) {
          ListNode2Sum solver = new ListNode2Sum();

          ListNode l1 = solver.buildList(2, 4, 3);
          ListNode l2 = solver.buildList(5, 6, 4);
          System.out.println("342 + 465 = " + solver.listToString(solver.addTwoNumbers(l1, l2))); // 7 -> 0 -> 8

          ListNode l3 = solver.buildList(9, 9);
          ListNode l4 = solver.buildList(1);
          System.out.println("99 + 1 = " + solver.listToString(solver.addTwoNumbers(l3, l4))); // 0 -> 0 -> 1

          ListNode l5 = solver.buildList(0);
          ListNode l6 = solver.buildList(0);
          System.out.println("0 + 0 = " + solver.listToString(solver.addTwoNumbers(l5, l6))); // 0
     }


}
