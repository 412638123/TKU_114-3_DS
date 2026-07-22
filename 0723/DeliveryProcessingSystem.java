import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeliveryProcessingSystem {
    private Queue<DeliveryTask> waitingQueue;
    private Stack<DeliveryTask> completedStack;

    public DeliveryProcessingSystem() {
        this.waitingQueue = new LinkedList<>();
        this.completedStack = new Stack<>();
    }

    public void addTask(String taskId, String recipient, String address) {
        DeliveryTask task = new DeliveryTask(taskId, recipient, address);
        waitingQueue.offer(task);
        System.out.println("新增配送任務 -> 代碼：" + task.taskId + " | 收件人：" + task.recipient + " | 地址：" + task.address);
    }

    public void processNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("配送失敗：目前沒有等待中的配送任務。");
            return;
        }

        DeliveryTask task = waitingQueue.poll();
        completedStack.push(task);
        System.out.println("【完成配送】任務代碼：" + task.taskId + " | 收件人：" + task.recipient + " 已送達。");
    }

    public void peekNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("查看下一筆：目前等待佇列為空。");
            return;
        }

        DeliveryTask nextTask = waitingQueue.peek();
        System.out.println("下一筆待配送任務 -> 代碼：" + nextTask.taskId + " | 收件人：" + nextTask.recipient + " | 地址：" + nextTask.address);
    }

    public void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("復原失敗：沒有已完成的任務可供復原。");
            return;
        }

        DeliveryTask task = completedStack.pop();
        waitingQueue.offer(task);
        System.out.println("【復原任務】已將完成的任務 (" + task.taskId + ") 重新放回等待佇列尾端。");
    }

    public void showStatus() {
        System.out.println("--- 配送系統狀態統計 ---");
        System.out.println("等待配送數量：" + waitingQueue.size());
        System.out.println("完成配送數量：" + completedStack.size());
        System.out.println("------------------------");
    }

    public void showRecords() {
        System.out.println("=== 所有已完成的配送紀錄 (由最近到最舊) ===");
        if (completedStack.isEmpty()) {
            System.out.println("(尚無完成紀錄)");
        } else {
            for (int i = completedStack.size() - 1; i >= 0; i--) {
                DeliveryTask t = completedStack.get(i);
                System.out.println("- 代碼：" + t.taskId + " | 收件人：" + t.recipient + " | 地址：" + t.address);
            }
        }
        System.out.println("============================================");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 測試 1：空結構安全操作 ===");
        system.peekNextTask();
        system.processNextTask();
        system.undoLastCompleted();
        system.showStatus();

        System.out.println("\n=== 測試 2：新增多筆配送任務 ===");
        system.addTask("D001", "王小明", "台北市信義路五段7號");
        system.addTask("D002", "李小華", "台中市西屯區台灣大道三段99號");
        system.addTask("D003", "張小美", "高雄市前鎮區中山二路2號");
        system.showStatus();

        System.out.println("\n=== 測試 3：查看下一筆與完成配送 ===");
        system.peekNextTask();
        system.processNextTask();
        system.showStatus();

        System.out.println("\n=== 測試 4：連續完成與查看紀錄 ===");
        system.processNextTask();
        system.showRecords();
        system.showStatus();

        System.out.println("\n=== 測試 5：復原最近完成的任務 ===");
        system.undoLastCompleted();
        system.showStatus();
        system.peekNextTask();
    }
}