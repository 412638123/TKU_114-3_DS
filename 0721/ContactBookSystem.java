import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    private static ArrayList<Contact> contactList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- 通訊錄管理系統 ---");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人");
            System.out.println("3. 修改電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 完整清單");
            System.out.println("6. 離開");
            System.out.print("請選擇功能 (1-6)：");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    updatePhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listContacts();
                    break;
                case "6":
                    System.out.println("系統已退出。");
                    return;
                default:
                    System.out.println("選項無效，請重新選擇。");
            }
        }
    }

    public static void addContact() {
        System.out.print("請輸入聯絡人代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：代碼不得為空白。");
            return;
        }

        if (findContactByCode(code) != null) {
            System.out.println("錯誤：代碼已存在，代碼不可重複。");
            return;
        }

        System.out.print("請輸入聯絡人姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：空白姓名不可加入。");
            return;
        }

        System.out.print("請輸入聯絡人電話：");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件：");
        String email = scanner.nextLine().trim();

        contactList.add(new Contact(code, name, phone, email));
        System.out.println("新增聯絡人成功！");
    }

    public static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名關鍵字：");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("關鍵字不得為空白。");
            return;
        }

        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (Contact c : contactList) {
            if (c.getCode().equalsIgnoreCase(keyword) || c.getName().contains(keyword)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的聯絡人。");
        }
    }

    public static void updatePhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        Contact c = findContactByCode(code);
        if (c == null) {
            System.out.println("找不到該代碼的聯絡人。");
            return;
        }

        System.out.print("請輸入新的電話：");
        String newPhone = scanner.nextLine().trim();

        c.setPhone(newPhone);
        System.out.println("電話修改成功！");
    }

    public static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = scanner.nextLine().trim();

        Contact c = findContactByCode(code);
        if (c != null) {
            contactList.remove(c);
            System.out.println("刪除成功！");
        } else {
            System.out.println("找不到");
        }
    }

    public static void listContacts() {
        if (contactList.isEmpty()) {
            System.out.println("\n目前通訊錄為空。");
            return;
        }

        System.out.println("\n--- 完整聯絡人清單 ---");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i + 1) + ". " + contactList.get(i));
        }
    }

    private static Contact findContactByCode(String code) {
        for (Contact c : contactList) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}
