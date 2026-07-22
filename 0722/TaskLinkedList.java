public class TaskLinkedList {
    private TaskNode head;
    private int totalCount;
    private int incompleteCount;

    public TaskLinkedList() {
        this.head = null;
        this.totalCount = 0;
        this.incompleteCount = 0;
    }

    public boolean contains(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equals(taskId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void addEmergencyTask(String taskId, String description) {
        if (contains(taskId)) {
            System.out.println("新增失敗：工作代碼 " + taskId + " 已存在。");
            return;
        }
        TaskNode newNode = new TaskNode(taskId, description);
        newNode.next = head;
        head = newNode;
        totalCount++;
        incompleteCount++;
        System.out.println("成功新增緊急工作(前端)：[" + taskId + "] " + description);
    }

    public void addNormalTask(String taskId, String description) {
        if (contains(taskId)) {
            System.out.println("新增失敗：工作代碼 " + taskId + " 已存在。");
            return;
        }
        TaskNode newNode = new TaskNode(taskId, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        totalCount++;
        incompleteCount++;
        System.out.println("成功新增一般工作(尾端)：[" + taskId + "] " + description);
    }

    public void completeTask(String taskId) {
        if (head == null) {
            System.out.println("操作失敗：工作清單目前為空。");
            return;
        }

        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equals(taskId)) {
                if (!current.isCompleted) {
                    current.isCompleted = true;
                    incompleteCount--;
                    System.out.println("工作已標記為完成：[" + taskId + "] " + current.description);
                } else {
                    System.out.println("提示：工作 [" + taskId + "] 已經是完成狀態。");
                }
                return;
            }
            current = current.next;
        }
        System.out.println("操作失敗：找不到工作代碼 " + taskId);
    }

    public void removeTask(String taskId) {
        if (head == null) {
            System.out.println("刪除失敗：工作清單目前為空。");
            return;
        }

        if (head.taskId.equals(taskId)) {
            if (!head.isCompleted) {
                incompleteCount--;
            }
            totalCount--;
            System.out.println("成功刪除工作：[" + head.taskId + "] " + head.description);
            head = head.next;
            return;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.taskId.equals(taskId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("刪除失敗：找不到工作代碼 " + taskId);
            return;
        }

        TaskNode targetNode = current.next;
        if (!targetNode.isCompleted) {
            incompleteCount--;
        }
        totalCount--;
        System.out.println("成功刪除工作：[" + targetNode.taskId + "] " + targetNode.description);
        current.next = targetNode.next;
    }

    public void listIncompleteTasks() {
        if (head == null || incompleteCount == 0) {
            System.out.println("未完成工作清單：目前沒有未完成的工作。");
            return;
        }

        System.out.println("=== 未完成工作清單 ===");
        TaskNode current = head;
        int index = 1;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println(index + ". 代碼：" + current.taskId + " | 說明：" + current.description);
                index++;
            }
            current = current.next;
        }
        System.out.println("======================");
    }

    public void displaySummary() {
        System.out.println("--- 工作狀態統計 ---");
        System.out.println("工作總數：" + totalCount);
        System.out.println("未完成數量：" + incompleteCount);
        System.out.println("--------------------");
    }
}