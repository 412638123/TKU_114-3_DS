public class BuildLinkedList {
    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;

    public BuildLinkedList() {
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
            System.out.println("串列目前為空");
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

    public int countNodes() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int calculateSum() {
        if (head == null) {
            return 0;
        }
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.val;
            current = current.next;
        }
        return sum;
    }

    public boolean search(int target) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.val == target) {
                System.out.println("找到資料 " + target + "，位於第 " + position + " 個節點。");
                return true;
            }
            current = current.next;
            position++;
        }
        System.out.println("找不到資料：值 " + target + " 不存在於此鏈結串列中。");
        return false;
    }

    public static void main(String[] args) {
        BuildLinkedList list = new BuildLinkedList();
        
        list.traverse();
        System.out.println("節點數：" + list.countNodes());
        System.out.println("總和：" + list.calculateSum());
        list.search(20);

        list.initDefaultList();
        
        list.traverse();
        System.out.println("節點總數：" + list.countNodes());
        System.out.println("數值總和：" + list.calculateSum());
        
        list.search(30);
        list.search(99);
    }
}
