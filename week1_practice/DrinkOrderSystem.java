package week1_practice;

import java.util.Scanner;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 商品銷售數量統計
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        
        int totalItems = 0;
        int totalAmount = 0;
        boolean ordering = true;

        while (ordering) {
            printMenu();
            System.out.print("請選擇商品選項：");
            int choice = scanner.nextInt();

            if (choice == 0) {
                ordering = false;
            } else if (choice >= 1 && choice <= 4) {
                int price = getPrice(choice);
                int quantity = readValidQuantity(scanner);
                int subtotal = calculateSubtotal(price, quantity);

                // 更新總計與各別商品統計
                totalAmount += subtotal;
                totalItems += quantity;
                
                switch (choice) {
                    case 1: blackTeaCount += quantity; break;
                    case 2: greenTeaCount += quantity; break;
                    case 3: milkTeaCount += quantity; break;
                    case 4: coffeeCount += quantity; break;
                }
                
                System.out.println("本次小計: " + subtotal + " 元");
            } else {
                System.out.println("無效選項，請重新輸入。");
            }
        }

        // 結帳並計算折扣
        int finalAmount = calculateDiscountedTotal(totalAmount);
        
        // 輸出收據
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);
        
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n=== 飲料選單 ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "Unknown";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("請輸入數量：");
            quantity = sc.nextInt();
            if (quantity <= 0) System.out.println("數量必須大於 0！");
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }

    public static void printReceipt(int bt, int gt, int mt, int cf, int totalItems, int totalAmount, int finalAmount) {
        System.out.println("\n=== 結帳收據 ===");
        System.out.println("Black tea: " + bt + " 杯");
        System.out.println("Green tea: " + gt + " 杯");
        System.out.println("Milk tea:  " + mt + " 杯");
        System.out.println("Coffee:    " + cf + " 杯");
        System.out.println("-------------------");
        System.out.println("總杯數: " + totalItems);
        System.out.println("折扣前總金額: " + totalAmount + " 元");
        
        if (totalAmount >= 300) {
            System.out.println("優惠狀態: 滿 300 元享 9 折");
        } else {
            System.out.println("優惠狀態: 無折扣");
        }
        
        System.out.println("折扣後總金額: " + finalAmount + " 元");
        System.out.println("謝謝惠顧！");
    }
}