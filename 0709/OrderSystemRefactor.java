import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalQuantity = 0;
        int totalAmount = 0;
        boolean ordering = true;

        printMenu();

        while (ordering) {
            System.out.print("\n請選擇商品編號 (0-3): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                ordering = false;
            } else if (choice >= 1 && choice <= 3) {
                int price = getPrice(choice);
                int quantity = readValidQuantity(scanner);
                int subtotal = calculateSubtotal(price, quantity);

                totalQuantity += quantity;
                totalAmount += subtotal;
                
                System.out.println("本次小計: " + subtotal + " 元");
            } else {
                System.out.println("無效的選項，請重新選擇。");
            }
        }

        printReceipt(totalQuantity, totalAmount);
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("--- 歡迎點餐系統 ---");
        System.out.println("1. Black tea: 30元");
        System.out.println("2. Green tea: 35元");
        System.out.println("3. Coffee: 50元");
        System.out.println("0. 結帳");
    }


    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }


    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("請輸入數量: ");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("數量必須大於 0，請重新輸入。");
            }
        }
        return quantity;
    }


    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }


    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n--- 結帳資訊 ---");
        System.out.println("總數量: " + totalItems);
        System.out.println("總金額: " + totalAmount + " 元");}}