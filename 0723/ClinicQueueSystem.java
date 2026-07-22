import java.util.LinkedList;
import java.util.Queue;

public class ClinicQueueSystem {
    private Queue<Patient> waitingQueue;
    private Queue<Patient> serviceHistory;
    private int nextTicketNumber;

    public ClinicQueueSystem() {
        this.waitingQueue = new LinkedList<>();
        this.serviceHistory = new LinkedList<>();
        this.nextTicketNumber = 1;
    }

    public boolean isTicketExists(int ticketNumber) {
        for (Patient p : waitingQueue) {
            if (p.ticketNumber == ticketNumber) {
                return true;
            }
        }
        for (Patient p : serviceHistory) {
            if (p.ticketNumber == ticketNumber) {
                return true;
            }
        }
        return false;
    }

    public void registerPatient(String name, String department) {
        int ticketNumber = nextTicketNumber++;
        if (isTicketExists(ticketNumber)) {
            System.out.println("掛號失敗：號碼 " + ticketNumber + " 已存在（號碼不可重複）。");
            return;
        }

        Patient patient = new Patient(ticketNumber, name, department);
        waitingQueue.offer(patient);
        System.out.println("掛號成功 -> 號碼牌：" + patient.ticketNumber + " 號，姓名：" + patient.name + "，科別：" + patient.department);
    }

    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("叫號失敗：目前候診佇列為空，沒有病患在等待。");
            return;
        }

        Patient patient = waitingQueue.poll();
        serviceHistory.offer(patient);
        System.out.println("【正在叫號】請號碼牌 " + patient.ticketNumber + " 號，" + patient.name + " 病患至 " + patient.department + " 診間報到。");
    }

    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("查看下一位：目前沒有病患在等候。");
            return;
        }

        Patient nextPatient = waitingQueue.peek();
        System.out.println("下一位等候病患 -> 號碼牌：" + nextPatient.ticketNumber + " 號，姓名：" + nextPatient.name + "，科別：" + nextPatient.department);
    }

    public void showWaitingList() {
        System.out.println("=== 目前等候清單 ===");
        if (waitingQueue.isEmpty()) {
            System.out.println("(目前沒有等候中的病患)");
        } else {
            for (Patient p : waitingQueue) {
                System.out.println("號碼：" + p.ticketNumber + " 號 | 姓名：" + p.name + " | 科別：" + p.department);
            }
        }
        System.out.println("====================");
    }

    public void showStatistics() {
        int internalCount = 0;
        int surgicalCount = 0;
        int pediatricsCount = 0;

        for (Patient p : waitingQueue) {
            if (p.department.equals("內科")) {
                internalCount++;
            } else if (p.department.equals("外科")) {
                surgicalCount++;
            } else if (p.department.equals("小兒科")) {
                pediatricsCount++;
            }
        }

        System.out.println("--- 診所候診與服務統計 ---");
        System.out.println("各科別等候人數：");
        System.out.println(" - 內科：" + internalCount + " 人");
        System.out.println(" - 外科：" + surgicalCount + " 人");
        System.out.println(" - 小兒科：" + pediatricsCount + " 人");
        System.out.println("總服務人數（已叫號完成）：" + serviceHistory.size() + " 人");
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 測試 1：空 Queue 安全操作 ===");
        clinic.callNext();
        clinic.peekNext();
        clinic.showWaitingList();
        clinic.showStatistics();

        System.out.println("\n=== 測試 2：病患掛號 ===");
        clinic.registerPatient("王小明", "內科");
        clinic.registerPatient("李小華", "外科");
        clinic.registerPatient("張小美", "小兒科");
        clinic.registerPatient("陳大明", "內科");

        System.out.println("\n=== 測試 3：查看等候清單與下一位 ===");
        clinic.showWaitingList();
        clinic.peekNext();

        System.out.println("\n=== 測試 4：叫號與統計檢查 ===");
        clinic.callNext();
        clinic.callNext();
        clinic.showStatistics();

        System.out.println("\n=== 測試 5：後續等候與最終狀態 ===");
        clinic.showWaitingList();
        clinic.callNext();
        clinic.showStatistics();
    }
}