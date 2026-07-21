import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {

    private static ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- 設備管理系統 ---");
            System.out.println("1. 新增設備");
            System.out.println("2. 依代碼搜尋設備");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出可借設備");
            System.out.println("6. 離開");
            System.out.print("請選擇功能 (1-6)：");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEquipment();
                    break;
                case "2":
                    searchEquipment();
                    break;
                case "3":
                    borrowEquipment();
                    break;
                case "4":
                    returnEquipment();
                    break;
                case "5":
                    listAvailableEquipment();
                    break;
                case "6":
                    System.out.println("系統已退出。");
                    return;
                default:
                    System.out.println("選項無效，請重新選擇。");
            }
        }
    }

    public static void addEquipment() {
        System.out.print("請輸入設備代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：代碼不得為空白。");
            return;
        }

        if (findEquipmentByCode(code) != null) {
            System.out.println("錯誤：代碼已存在，代碼不可重複。");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：名稱不得為空白。");
            return;
        }

        equipmentList.add(new Equipment(code, name, true));
        System.out.println("新增設備成功！");
    }

    public static void searchEquipment() {
        System.out.print("請輸入要搜尋的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment eq = findEquipmentByCode(code);
        if (eq != null) {
            System.out.println("搜尋結果：" + eq);
        } else {
            System.out.println("找不到該代碼的設備。");
        }
    }

    public static void borrowEquipment() {
        System.out.print("請輸入要借出的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment eq = findEquipmentByCode(code);
        if (eq == null) {
            System.out.println("找不到該代碼的設備。");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("借出失敗：該設備目前已借出。");
            return;
        }

        eq.setAvailable(false);
        System.out.println("借出成功！");
    }

    public static void returnEquipment() {
        System.out.print("請輸入要歸還的設備代碼：");
        String code = scanner.nextLine().trim();

        Equipment eq = findEquipmentByCode(code);
        if (eq == null) {
            System.out.println("找不到該代碼的設備。");
            return;
        }

        if (eq.isAvailable()) {
            System.out.println("歸還失敗：該設備本來就是可借用狀態。");
            return;
        }

        eq.setAvailable(true);
        System.out.println("歸還成功！");
    }

    public static void listAvailableEquipment() {
        boolean hasAvailable = false;
        System.out.println("\n--- 可借用設備名單 ---");
        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {目前沒有可借用的設備。");
        }
    }

    private static Equipment findEquipmentByCode(String code) {
        for (Equipment eq : equipmentList) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq;
            }
        }
        return null;
    }
}