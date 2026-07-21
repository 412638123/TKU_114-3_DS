import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {

    private static ArrayList<String> names = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- 姓名管理系統 ---");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部");
            System.out.println("6. 離開");
            System.out.print("請選擇功能 (1-6)：");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addName();
                    break;
                case "2":
                    searchName();
                    break;
                case "3":
                    updateName();
                    break;
                case "4":
                    deleteName();
                    break;
                case "5":
                    listNames();
                    break;
                case "6":
                    System.out.println("系統已退出。");
                    return;
                default:
                    System.out.println("選項無效，請重新選擇。");
            }
        }
    }

    public static void addName() {
        System.out.print("請輸入要新增的姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：不得加入空白姓名。");
            return;
        }

        names.add(name);
        System.out.println("新增成功！");
    }

    public static void searchName() {
        System.out.print("請輸入要搜尋的姓名：");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("搜尋關鍵字不得為空白。");
            return;
        }

        boolean found = false;
        System.out.println("搜尋結果：");
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(keyword)) {
                System.out.println("索引 " + i + "：" + names.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的姓名。");
        }
    }

    public static void updateName() {
        System.out.print("請輸入要修改的原本姓名：");
        String oldName = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(oldName);
        if (index == -1) {
            System.out.println("找不到指定的姓名，無法修改。");
            return;
        }

        System.out.print("請輸入新的姓名：");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("錯誤：不得修改為空白姓名。");
            return;
        }

        names.set(index, newName);
        System.out.println("修改成功！");
    }

    public static void deleteName() {
        System.out.print("請輸入要刪除的姓名：");
        String targetName = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(targetName);
        if (index != -1) {
            names.remove(index);
            System.out.println("刪除成功！");
        } else {
            System.out.println("找不到");
        }
    }

    public static void listNames() {
        if (names.isEmpty()) {
            System.out.println("目前名單為空。");
            return;
        }

        System.out.println("\n--- 全部姓名名單 ---");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    private static int findIndexIgnoreCase(String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}
