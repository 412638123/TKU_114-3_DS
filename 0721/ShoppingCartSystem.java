import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static ArrayList<CartItem> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- 購物車系統 ---");
            System.out.println("1. 加入商品 / 累加數量");
            System.out.println("2. 修改商品數量");
            System.out.println("3. 移除商品");
            System.out.println("4. 查看購物車與計算總額");
            System.out.println("5. 離開");
            System.out.print("請選擇功能 (1-5)：");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    updateQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    viewCartAndTotal();
                    break;
                case "5":
                    System.out.println("系統已退出。");
                    return;
                default:
                    System.out.println("選項無效，請重新選擇。");
            }
        }
    }

    public static void addItem() {
        System.out.print("請輸入商品代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：代碼不得為空白。");
            return;
        }

        System.out.print("請輸入商品名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：名稱不得為空白。");
            return;
        }

        System.out.print("請輸入單價：");
        int price;
        try {
            price = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("錯誤：單價必須為數字。");
            return;
        }

        if (price < 0) {
            System.out.println("錯誤：單價不得為負數。");
            return;
        }

        System.out.print("請輸入數量：");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("錯誤：數量必須為數字。");
            return;
        }

        if (quantity <= 0) {
            System.out.println("錯誤：數量小於或等於 0 時不接受。");
            return;
        }

        CartItem existingItem = findItemByCode(code);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            System.out.println("商品已存在於購物車中，已為您增加數量！");
        } else {
            cart.add(new CartItem(code, name, price, quantity));
            System.out.println("成功加入購物車！");
        }
    }

    public static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼：");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(code);
        if (item == null) {
            System.out.println("找不到該代碼的商品。");
            return;
        }

        System.out.print("請輸入新的數量：");
        int newQuantity;
        try {
            newQuantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("錯誤：數量必須為數字。");
            return;
        }

        if (newQuantity <= 0) {
            System.out.println("錯誤：數量小於或等於 0 時不接受更新。");
            return;
        }

        item.setQuantity(newQuantity);
        System.out.println("數量修改成功！");
    }

    public static void removeItem() {
        System.out.print("請輸入要移除的商品代碼：");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(code);
        if (item != null) {
            cart.remove(item);
            System.out.println("移除成功！");
        } else {
            System.out.println("找不到");
        }
    }

    public static void viewCartAndTotal() {
        if (cart.isEmpty()) {
            System.out.println("\n購物車目前是空的。");
            return;
        }

        System.out.println("\n--- 購物車內容 ---");
        int total = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.println((i + 1) + ". " + item);
            total += item.getSubtotal();
        }
        System.out.println("------------------------");
        System.out.println("總金額：" + total);
    }

    private static CartItem findItemByCode(String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}