import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int balance = 1000;
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇功能：");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depositAmount = readPositiveAmount(scanner, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    System.out.println("存款成功！目前餘額：" + balance + " 元");
                    break;
                case 3:
                    int withdrawAmount = readPositiveAmount(scanner, "請輸入提款金額：");
                    if (withdrawAmount > balance) {
                        System.out.println("錯誤：餘額不足！目前餘額：" + balance + " 元");
                    } else {
                        balance = withdraw(balance, withdrawAmount);
                        System.out.println("提款成功！目前餘額：" + balance + " 元");
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("感謝使用，再見！");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }
        scanner.close();
    }


    public static void printMenu() {
        System.out.println("\n--- ATM 選單 ---");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開");
    }


    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (amount <= 0) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount <= 0) {
                System.out.println("錯誤：金額必須大於 0，請重新輸入。");
            }
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }


    public static void printBalance(int balance) {
        System.out.println("目前餘額為：" + balance + " 元");
    }
}