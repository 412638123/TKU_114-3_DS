public class LinkedListSearchRemove {
    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;

    public LinkedListSearchRemove() {
        this.head = null;
    }

    public void initDefaultList() {
        head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
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

    public boolean contains(int target) {
        Node current = head;
        while (current != null) {
            if (current.val == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            System.out.println("刪除失敗：串列為空，找不到 " + target);
            return false;
        }

        if (head.val == target) {
            head = head.next;
            System.out.println("成功刪除頭節點：" + target);
            traverse();
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.val != target) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("刪除失敗：找不到資料 " + target);
            return false;
        }

        current.next = current.next.next;
        System.out.println("成功刪除資料：" + target);
        traverse();
        return true;
    }

    public static void main(String[] args) {
        LinkedListSearchRemove list = new LinkedListSearchRemove();

        System.out.println("=== 測試空串列操作 ==   ");
        list.traverse();
        System.out.println("contains(20): " + list.contains(20));
        list.removeValue(10);

        System.out.println("\n=== 初始化串列 (10, 20, 30, 40) ===");
        list.initDefaultList();
        list.traverse();

        System.out.println("\n=== 測試 contains 功能 ===");
        System.out.println("contains(30): " + list.contains(30));
        System.out.println("contains(99): " + list.contains(99));

        System.out.println("\n=== 測試刪除不存在的資料 (99) ===");
        list.removeValue(99);

        System.out.println("\n=== 測試刪除中間節點 (20) ===");
        list.removeValue(20);

        System.out.println("\n=== 測試刪除最後一個節點 (40) ===");
        list.removeValue(40);

        System.out.println("\n=== 測試刪除 Head 節點 (10) ===");
        list.removeValue(10);

        System.out.println("\n=== 最終串列狀態 ===");
        list.traverse();
    }
}