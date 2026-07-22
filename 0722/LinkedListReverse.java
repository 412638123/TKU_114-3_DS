public class LinkedListReverse {
    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;

    public LinkedListReverse() {
        this.head = null;
    }

    public void traverse() {
        if (head == null) {
            System.out.println("鏈結串列內容：(空串列)");
            return;
        }
        
        System.out.print("鏈結串列內容：");
        Node current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public void addLast(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void reverse() {
        if (head == null) {
            System.out.println("反轉失敗：串列為空。");
            return;
        }
        
        if (head.next == null) {
            System.out.println("反轉提示：串列只有單一節點，無需反轉。");
            return;
        }

        Node prev = null;
        Node current = head;
        Node nextNode = null;

        while (current != null) {
            nextNode = current.next; 
            current.next = prev;     
            prev = current;          
            current = nextNode;      
        }

        head = prev;
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1：空串列反轉 ===");
        LinkedListReverse list1 = new LinkedListReverse();
        list1.traverse();
        list1.reverse();
        list1.traverse();

        System.out.println("\n=== 測試 2：單一節點反轉 ===");
        LinkedListReverse list2 = new LinkedListReverse();
        list2.addLast(10);
        list2.traverse();
        list2.reverse();
        list2.traverse();

        System.out.println("\n=== 測試 3：多節點反轉 (10 -> 20 -> 30 -> 40) ===");
        LinkedListReverse list3 = new LinkedListReverse();
        list3.addLast(10);
        list3.addLast(20);
        list3.addLast(30);
        list3.addLast(40);
        
        list3.traverse();
        list3.reverse();
        System.out.print("反轉後：");
        list3.traverse();
    }
}
