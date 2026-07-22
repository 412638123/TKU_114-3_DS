public class NumberHistoryList {
    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("前端新增成功：" + val);
        traverse();
    }

    public void addLast(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("尾端新增成功：" + val);
        traverse();
    }

    public boolean search(int target) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.val == target) {
                System.out.println("搜尋結果：找到資料 " + target + "，位於第 " + position + " 個節點。");
                return true;
            }
            current = current.next;
            position++;
        }
        System.out.println("搜尋結果：找不到資料 " + target)。");
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            System.out.println("刪除失敗：串列為空，找不到 " + target);
            return false;
        }

        if (head.val == target) {
            head = head.next;
            size--;
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
        size--;
        System.out.println("成功刪除資料：" + target);
        traverse();
        return true;
    }

    public void traverse() {
        if (head == null) {
            System.out.println("鏈結串列內容：(空串列)");
        } else {
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
        printStatistics();
        System.out.println("----------------------------------------");
    }

    public void printStatistics() {
        System.out.println("--- 統計資訊 ---");
        System.out.println("大小 (Size)：" + size);
        if (head == null) {
            System.out.println("總和 (Sum)：0");
            System.out.println("最大值 (Max)：無 (空串列)");
            System.out.println("最小值 (Min)：無 (空串列)");
        } else {
            int sum = 0;
            int max = head.val;
            int min = head.val;
            Node current = head;
            while (current != null) {
                sum += current.val;
                if (current.val > max) {
                    max = current.val;
                }
                if (current.val < min) {
                    min = current.val;
                }
                current = current.next;
            }
            System.out.println("總和 (Sum)：" + sum);
            System.out.println("最大值 (Max)：" + max);
            System.out.println("最小值 (Min)：" + min);
        }
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 操作 1：初始空串列檢視 ===");
        list.traverse();

        System.out.println("=== 操作 2：尾端新增 20 ===");
        list.addLast(20);

        System.out.println("=== 操作 3：前端新增 10 ===");
        list.addFirst(10);

        System.out.println("=== 操作 4：尾端新增 40 ===");
        list.addLast(40);

        System.out.println("=== 操作 5：中間插入/前端新增 30 ===");
        list.addFirst(5); // 串列目前: 5 -> 10 -> 20 -> 40

        System.out.println("=== 操作 6：搜尋存在的資料 (20) ===");
        list.search(20);

        System.out.println("=== 操作 7：搜尋不存在的資料 (99) ===");
        list.search(99);

        System.out.println("=== 操作 8：刪除存在的資料 (10) ===");
        list.removeValue(10);

        System.out.println("=== 操作 9：刪除不存在的資料 (50) ===");
        list.removeValue(50);
    }
}