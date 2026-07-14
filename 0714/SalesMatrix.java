import java.util.Scanner;
import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        int[][] sales = new int[3][4];
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 歡迎使用商品銷售統計系統 ===");
        inputSalesData(sales, scanner);
        
        System.out.println("\n=== 銷售數據報表 ===");
        displaySalesTable(sales);
        
        System.out.println("\n=== 統計分析結果 ===");
    
        int[] productTotals = calculateProductTotals(sales);
        calculateDayTotals(sales);
        
        
        findTopProduct(productTotals);
        
        scanner.close();
    }

    public static void inputSalesData(int[][] sales, Scanner scanner) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                int value = -1;
            
                while (value < 0) {
                    System.out.print("請輸入 商品 " + (i + 1) + " 在第 " + (j + 1) + " 天的銷售量: ");
                    if (scanner.hasNextInt()) {
                        value = scanner.nextInt();
                        if (value < 0) {
                            System.out.println("錯誤：銷售量不得小於 0，請重新輸入！");
                        }
                    } else {
                        System.out.println("錯誤：請輸入有效的整數數字！");
                        scanner.next();
                    }
                }
                sales[i][j] = value;
            }
        }
    }

    
    public static void displaySalesTable(int[][] sales) {
        System.out.println("商品\\日期\t第1天\t第2天\t第3天\t第4天");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < sales.length; i++) {
            System.out.print("商品 " + (i + 1) + "\t");
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static int[] calculateProductTotals(int[][] sales) {
        int[] productTotals = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            int sum = 0;
            for (int j = 0; j < sales[i].length; j++) {
                sum += sales[i][j];
            }
            productTotals[i] = sum;
            System.out.println("商品 " + (i + 1) + " 的總銷售量: " + sum);
        }
        return productTotals;
    }

    public static void calculateDayTotals(int[][] sales) {
        
        for (int j = 0; j < sales[0].length; j++) {
            int sum = 0;
            // 外層固定欄，內層走訪列
            for (int i = 0; i < sales.length; i++) {
                sum += sales[i][j];
            }
            System.out.println("第 " + (j + 1) + " 天的全部商品總銷售量: " + sum);
        }
    }


    public static void findTopProduct(int[] productTotals) {
        int maxSales = productTotals[0];
        int topProductIndex = 0;
        
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > maxSales) {
                maxSales = productTotals[i];
                topProductIndex = i;
            }
        }
        System.out.println("★ 總銷售量最高的商品是: 商品 " + (topProductIndex + 1) + " (總銷量: " + maxSales + ")");
    }
}