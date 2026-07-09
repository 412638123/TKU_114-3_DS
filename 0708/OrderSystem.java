import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalQuantity = 0;
        int totalAmount = 0;
        boolean ordering = true;

        System.out.println("--- 歡迎點餐系統 ---");
        System.out.println("1. Black tea: 30元");
        System.out.println("2. Green tea: 35元");
        System.out.println("3. Coffee: 50元");
        System.out.println("0. 結帳");

        while (ordering) {
            System.out.print("\n請選擇商品編號 (0-3): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                ordering = false;
            } else if (choice >= 1 && choice <= 3) {
                int price = 0;
                switch (choice) {
                    case 1: price = 30; break;
                    case 2: price = 35; break;
                    case 3: price = 50; break;
                }

                int quantity = 0;
                while (quantity <= 0) {
                    System.out.print("請輸入數量: ");
                    quantity = scanner.nextInt();
                    if (quantity <= 0) {
                        System.out.println("數量必須大於 0，請重新輸入。");
                    }
                }

                int subtotal = price * quantity;
                totalQuantity += quantity;
                totalAmount += subtotal;
                System.out.println("本次小計: " + subtotal + " 元");
            } else {
                System.out.println("無效的選項，請重新選擇。");
            }
        }

        System.out.println("\n--- 結帳資訊 ---");
        System.out.println("總數量: " + totalQuantity);
        System.out.println("總金額: " + totalAmount + " 元");
        System.out.println("謝謝惠顧！");

        scanner.close();
    }
}