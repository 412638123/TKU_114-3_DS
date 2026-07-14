import java.util.Scanner;

public class ProductArraySystem {

    private static final int LOW_STOCK_THRESHOLD = 10;
    

    private static int totalTransactions = 0;
    private static int totalRestocks = 0;

    public static void main(String[] args) {
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("====== 歡迎使用商品陣列管理系統 ======");

        do {
            displayMenu();
            System.out.print("請輸入選項 (1-7): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                System.out.println("------------------------------------");
                
                switch (choice) {
                    case 1:
                        showAllProducts(names, prices, stocks);
                        break;
                    case 2:
                        queryProduct(names, prices, stocks, scanner);
                        break;
                    case 3:
                        purchaseProduct(names, prices, stocks, scanner);
                        break;
                    case 4:
                        restockProduct(names, stocks, scanner);
                        break;
                    case 5:
                        showLowStock(names, prices, stocks);
                        break;
                    case 6:
                        showTotalValue(names, prices, stocks);
                        break;
                    case 7:
                        showSummary();
                        break;
                    default:
                        System.out.println("錯誤：無此選項，請輸入 1 到 7 之間的數字。");
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數數字！");
                scanner.next();
                choice = 0;
            }
            System.out.println("====================================");
        } while (choice != 7);

        scanner.close();
    }


    public static void displayMenu() {
        System.out.println("\n【功能選單】");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品（扣庫存）");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (<" + LOW_STOCK_THRESHOLD + ")");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束程式");
    }


    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("編號\t商品名稱\t\t單價\t庫存");
        System.out.println("------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d\t%-15s\t%d\t%d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void queryProduct(String[] names, int[] prices, int[] stocks, Scanner scanner) {
        System.out.print("請輸入要查詢的商品編號 (1-" + names.length + "): ");
        int id = scanner.nextInt();
        int index = id - 1;

        if (isValidIndex(index, names.length)) {
            System.out.println("【查詢結果】");
            System.out.println("商品名稱: " + names[index]);
            System.out.println("商品單價: " + prices[index] + " 元");
            System.out.println("目前庫存: " + stocks[index] + " 個");
        } else {
            System.out.println("錯誤：查無此商品編號！");
        }
    }


    public static void purchaseProduct(String[] names, int[] prices, int[] stocks, Scanner scanner) {
        System.out.print("請輸入要購買的商品編號 (1-" + names.length + "): ");
        int id = scanner.nextInt();
        int index = id - 1;

        if (!isValidIndex(index, names.length)) {
            System.out.println("錯誤：商品編號不存在！");
            return;
        }

        System.out.println("您選擇的是: " + names[index] + " (目前庫存: " + stocks[index] + ")");
        System.out.print("請輸入欲購買數量: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("錯誤：購買數量必須大於 0！");
        } else if (quantity > stocks[index]) {
            System.out.println("錯誤：庫存不足！目前僅剩 " + stocks[index] + " 個。");
        } else {
            stocks[index] -= quantity;
            int cost = prices[index] * quantity;
            System.out.println("成功購買！總金額: " + cost + " 元。");
            System.out.println(names[index] + " 剩餘庫存: " + stocks[index]);
            totalTransactions++;
        }
    }


    public static void restockProduct(String[] names, int[] stocks, Scanner scanner) {
        System.out.print("請輸入要補貨的商品編號 (1-" + names.length + "): ");
        int id = scanner.nextInt();
        int index = id - 1;

        if (!isValidIndex(index, names.length)) {
            System.out.println("錯誤：商品編號不存在！");
            return;
        }

        System.out.println("您選擇的是: " + names[index] + " (目前庫存: " + stocks[index] + ")");
        System.out.print("請輸入欲補貨數量: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("錯誤：補貨數量必須大於 0！");
        } else {
            stocks[index] += quantity;
            System.out.println("補貨成功！" + names[index] + " 更新後庫存: " + stocks[index]);
            totalRestocks++;
        }
    }


    public static void showLowStock(String[] names, int[] prices, int[] stocks) {
        System.out.println("【低庫存警示商品列表 (庫存 < " + LOW_STOCK_THRESHOLD + ")】");
        System.out.println("編號\t商品名稱\t\t單價\t庫存");
        System.out.println("------------------------------------");
        boolean hasLowStock = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < LOW_STOCK_THRESHOLD) {
                System.out.printf("%d\t%-15s\t%d\t%d (請補貨!)\n", (i + 1), names[i], prices[i], stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前所有商品庫存充足！");
        }
    }


    public static void showTotalValue(String[] names, int[] prices, int[] stocks) {
        int totalValue = 0;
        System.out.println("【各商品庫存價值試算】");
        for (int i = 0; i < names.length; i++) {
            int value = prices[i] * stocks[i];
            System.out.printf("%s 的庫存價值: %d 元 (%d 元 * %d 個)\n", names[i], value, prices[i], stocks[i]);
            totalValue += value;
        }
        System.out.println("------------------------------------");
        System.out.println("★ 全店商品庫存總價值為: " + totalValue + " 元");
    }


    public static boolean isValidIndex(int index, int length) {
        return index >= 0 && index < length;
    }


    public static void showSummary() {
        System.out.println("謝謝使用！正在關閉系統...");
        System.out.println("\n====== 本次操作摘要 ======");
        System.out.println("・ 成功商品交易次數: " + totalTransactions + " 次");
        System.out.println("・ 成功商品補貨次數: " + totalRestocks + " 次");
        System.out.println("==========================");
    }
}
