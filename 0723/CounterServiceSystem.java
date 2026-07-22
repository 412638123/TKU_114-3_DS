import java.util.LinkedList;
import java.util.Queue;

public class CounterServiceSystem {
    public static class Customer {
        int ticketNumber;
        String name;

        public Customer(int ticketNumber, String name) {
            this.ticketNumber = ticketNumber;
            this.name = name;
        }
    }

    private Queue<Customer> waitingQueue;
    private Queue<Customer> serviceHistory;
    private int nextTicketNumber;

    public CounterServiceSystem() {
        this.waitingQueue = new LinkedList<>();
        this.serviceHistory = new LinkedList<>();
        this.nextTicketNumber = 1;
    }

    public void takeTicket(String name) {
        Customer customer = new Customer(nextTicketNumber++, name);
        waitingQueue.offer(customer);
        System.out.println("取號成功 -> 號碼牌：" + customer.ticketNumber + "，姓名：" + customer.name);
    }

    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("叫號失敗：目前等待佇列為空，沒有顧客在等待。");
            return;
        }

        Customer customer = waitingQueue.poll();
        serviceHistory.offer(customer);
        System.out.println("【正在叫號】請號碼牌 " + customer.ticketNumber + " 號，" + customer.name + " 先生/小姐到櫃台辦理。");
    }

    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("查看下一位：目前沒有人在排隊。");
            return;
        }

        Customer nextCustomer = waitingQueue.peek();
        System.out.println("下一位等待顧客 -> 號碼牌：" + nextCustomer.ticketNumber + " 號，姓名：" + nextCustomer.name);
    }

    public void showWaitingCount() {
        System.out.println("目前等待人數：" + waitingQueue.size() + " 人");
    }

    public void showHistory() {
        System.out.println("=== 已服務之辦理紀錄 ===");
        if (serviceHistory.isEmpty()) {
            System.out.println("(尚無服務紀錄)");
            return;
        }
        for (Customer c : serviceHistory) {
            System.out.println("已完成 -> 號碼牌：" + c.ticketNumber + " 號，姓名：" + c.name);
        }
        System.out.println("========================");
    }

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 測試 1：空 Queue 叫號與查看安全防護 ===");
        system.callNext();
        system.peekNext();
        system.showWaitingCount();

        System.out.println("\n=== 測試 2：顧客依序取號 ===");
        system.takeTicket("小明");
        system.takeTicket("小華");
        system.takeTicket("小美");
        system.showWaitingCount();

        System.out.println("\n=== 測試 3：查看下一位與叫號 ===");
        system.peekNext();
        system.callNext();
        system.showWaitingCount();

        System.out.println("\n=== 測試 4：再次叫號並檢查歷史紀錄 ===");
        system.callNext();
        system.showWaitingCount();
        system.showHistory();

        System.out.println("\n=== 測試 5：新顧客取號與叫完剩餘所有人 ===");
        system.takeTicket("大寶");
        system.callNext();
        system.callNext();
        
        System.out.println("\n=== 測試 6：最終歷史紀錄確認 ===");
        system.showHistory();
        system.showWaitingCount();
    }
}