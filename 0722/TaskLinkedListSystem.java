public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 1. 測試空清單操作 ===");
        taskList.displaySummary();
        taskList.listIncompleteTasks();
        taskList.completeTask("T001");
        taskList.removeTask("T001");

        System.out.println("\n=== 2. 測試新增工作 (一般與緊急) ===");
        taskList.addNormalTask("T002", "寫作業");
        taskList.addNormalTask("T003", "洗衣服");
        taskList.addEmergencyTask("T001", "修復系統重大Bug");
        taskList.displaySummary();

        System.out.println("\n=== 3. 測試列出未完成工作 ===");
        taskList.listIncompleteTasks();

        System.out.println("\n=== 4. 測試完成工作 ===");
        taskList.completeTask("T002");
        taskList.displaySummary();
        taskList.listIncompleteTasks();

        System.out.println("\n=== 5. 測試刪除工作 (刪除頭節點與一般節點) ===");
        taskList.removeTask("T001"); // 刪除頭節點
        taskList.removeTask("T003"); // 刪除最後節點
        taskList.displaySummary();

        System.out.println("\n=== 6. 測試找不到資料或重複代碼處理 ===");
        taskList.addNormalTask("T004", "買晚餐");
        taskList.addNormalTask("T004", "重複代碼測試");
        taskList.completeTask("T999");
        taskList.removeTask("T999");
        taskList.displaySummary();
    }
}