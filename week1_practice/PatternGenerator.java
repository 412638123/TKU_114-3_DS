package week1_practice;

import java.util.Scanner;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇功能：");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2:
                    int h1 = readPositiveInt(scanner, "請輸入正三角形高度：");
                    printTriangle(h1);
                    break;
                case 3:
                    int h2 = readPositiveInt(scanner, "請輸入倒三角形高度：");
                    printReverseTriangle(h2);
                    break;
                case 4:
                    int rows = readPositiveInt(scanner, "請輸入列數 (rows)：");
                    int cols = readPositiveInt(scanner, "請輸入欄數 (cols)：");
                    printNumberGrid(rows, cols);
                    break;
                case 0:
                    running = false;
                    System.out.println("程式結束。");
                    break;
                default:
                    System.out.println("無效選項，請重新選擇。");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n=== 圖形與表格產生器 ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int val = 0;
        while (val <= 0) {
            System.out.print(message);
            val = sc.nextInt();
            if (val <= 0) System.out.println("數值必須大於 0！");
        }
        return val;
    }

    public static void printMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%d*%d=%-2d  ", i, j, i * j);
            }
            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
