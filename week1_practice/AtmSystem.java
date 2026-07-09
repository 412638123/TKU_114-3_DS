package week1_practice;

import java.util.Scanner;

public class AtmSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;
        
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
                    int depAmount = readPositiveAmount(scanner, "請輸入存款金額：");
                    balance = deposit(balance, depAmount);
                    totalDeposit += depAmount;
                    depositCount++;
                    System.out.println("存款成功！");
                    break;
                case 3:
                    int witAmount = readPositiveAmount(scanner, "請輸入提款金額：");
                    if (canWithdraw(balance, witAmount)) {
                        balance = withdraw(balance, witAmount);
                        totalWithdraw += witAmount;
                        withdrawCount++;
                        System.out.println("提款成功！");
                    } else {
                        System.out.println("提款失敗：餘額不足！");
                    }
                    break;
                case 4:
                    System.out.println("目前存款次數: " + depositCount + ", 提款次數: " + withdrawCount);
                    break;
                case 0:
                    running = false;
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;
                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n--- ATM 系統 ---");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("4. 顯示目前操作統計");
        System.out.println("0. 離開");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (amount <= 0) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount <= 0) System.out.println("金額必須大於 0！");
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額：" + balance + " 元");
    }

    public static void printSummary(int balance, int depCount, int witCount, int totDep, int totWit) {
        System.out.println("\n=== 操作摘要 ===");
        System.out.println("最終餘額: " + balance + " 元");
        System.out.println("存款次數: " + depCount + " 次，總存款金額: " + totDep + " 元");
        System.out.println("提款次數: " + witCount + " 次，總提款金額: " + totWit + " 元");
        System.out.println("謝謝使用！");
    }
}