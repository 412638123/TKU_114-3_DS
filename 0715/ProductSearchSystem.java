import java.util.Scanner;

/**
 * 測試案例紀錄 (Test Cases)：
 * 
 * [測試案例 1] 功能：1. 顯示全部商品
 *   - 操作：在主選單輸入 "1"
 *   - 預期結果：印出所有 6 項商品的名稱、價格與庫存（格式對齊）。
 * 
 * [測試案例 2] 功能：2. 完整名稱搜尋（精確匹配，忽略大小寫與前後空白）
 *   - 操作：在主選單輸入 "2"，接著輸入關鍵字 "  IpHoNe 15 PrO  "
 *   - 預期結果：成功找到該商品，顯示「名稱：iPhone 15 Pro, 價格：36900, 庫存：15」。
 *   - 額外測試：輸入 "iPhone 15" -> 預期結果：顯示「找不到此商品（需完整名稱）」。
 * 
 * [測試案例 3] 功能：3. 部分名稱搜尋（模糊匹配，可顯示多筆）
 *   - 操作：在主選單輸入 "3"，接著輸入關鍵字 "Galaxy"
 *   - 預期結果：顯示多筆結果，包含：
 *     1. Samsung Galaxy S24 Ultra (價格: 43900, 庫存: 8)
 *     2. Samsung Galaxy Buds3 Pro (價格: 7490, 庫存: 25)
 * 
 * [測試案例 4] 功能：4. 顯示名稱最長的商品
 *   - 操作：在主選單輸入 "4"
 *   - 預期結果：系統分析所有名稱後，印出名稱最長的商品為 "Sony WH-1000XM5 Wireless Headphones"（字元數：35）。
 * 
 * [測試案例 5] 功能：5. 顯示商品名稱與搜尋關鍵字第一次出現的位置
 *   - 操作：在主選單輸入 "5"，接著輸入關鍵字 "pro"（忽略大小寫）
 *   - 預期結果：
 *     - "iPhone 15 Pro" -> 關鍵字 'pro' 首次出現位置：10
 *     - "Samsung Galaxy Buds3 Pro" -> 關鍵字 'pro' 首次出現位置：21
 *     - (其他不含 'pro' 的商品則不顯示，或提示未找到)
 * 
 * [測試案例 6] 功能：6. 結束與防呆輸入
 *   - 操作：在主選單輸入 "8" (無效選項) 或 "abc" (非數字)
 *   - 預期結果：系統提示錯誤，要求重新輸入。
 *   - 操作：在主選單輸入 "6"
 *   - 預期結果：顯示「感謝使用本系統，再見！」，程式安全結束。
 */
public class ProductSearchSystem {

    // 0714 商品資料陣列（名稱、價格、庫存一對一對應）
    private static final String[] PRODUCT_NAMES = {
        "iPhone 15 Pro",
        "Samsung Galaxy S24 Ultra",
        "Sony WH-1000XM5 Wireless Headphones",
        "iPad Air 11-inch",
        "Samsung Galaxy Buds3 Pro",
        "Nintendo Switch OLED"
    };

    private static final int[] PRODUCT_PRICES = {
        36900,  // iPhone 15 Pro
        43900,  // Samsung Galaxy S24 Ultra
        11900,  // Sony WH-1000XM5
        19900,  // iPad Air 11-inch
        7490,   // Samsung Galaxy Buds3 Pro
        10480   // Nintendo Switch OLED
    };

    private static final int[] PRODUCT_STOCKS = {
        15,  // iPhone 15 Pro
        8,   // Samsung Galaxy S24 Ultra
        20,  // Sony WH-1000XM5
        12,  // iPad Air 11-inch
        25,  // Samsung Galaxy Buds3 Pro
        30   // Nintendo Switch OLED
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("====== 歡迎使用商品搜尋系統 (0714版) ======");

        while (running) {
            printMenu();
            System.out.print("請輸入選項 (1-6): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    showAllProducts();
                    break;
                case "2":
                    System.out.print("請輸入要搜尋的完整商品名稱：");
                    String exactKeyword = scanner.nextLine();
                    searchExactProduct(exactKeyword);
                    break;
                case "3":
                    System.out.print("請輸入要搜尋的部分商品名稱：");
                    String partialKeyword = scanner.nextLine();
                    searchPartialProduct(partialKeyword);
                    break;
                case "4":
                    showLongestProductName();
                    break;
                case "5":
                    System.out.print("請輸入要尋找位置的關鍵字：");
                    String indexKeyword = scanner.nextLine();
                    showKeywordFirstOccurrence(indexKeyword);
                    break;
                case "6":
                    System.out.println("\n感謝使用本系統，再見！");
                    running = false;
                    break;
                default:
                    System.out.println("【錯誤】無效的選項，請輸入 1 到 6 的數字。\n");
                    break;
            }
        }
        scanner.close();
    }

    // 自訂方法 1：顯示選單
    public static void printMenu() {
        System.out.println("\n-------------------------------------------");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (可多筆)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與關鍵字首次出現位置");
        System.out.println("6. 結束");
        System.out.println("-------------------------------------------");
    }

    // 自訂方法 2：顯示所有商品（格式化輸出）
    public static void showAllProducts() {
        System.out.println("\n=============== 全部商品列表 ===============");
        System.out.printf("%-38s\t%-8s\t%-6s\n", "商品名稱", "價格", "庫存");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            System.out.printf("%-38s\tNT$%,d\t%3d 件\n", PRODUCT_NAMES[i], PRODUCT_PRICES[i], PRODUCT_STOCKS[i]);
        }
    }

    // 自訂方法 3：完整名稱搜尋（精確匹配，忽略大小寫與前後空白）
    public static void searchExactProduct(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("【提示】搜尋關鍵字不可為空。");
            return;
        }
        
        // 去除前後空白並轉為小寫
        String cleanKeyword = keyword.trim().toLowerCase();
        boolean found = false;

        System.out.println("\n[完整名稱搜尋結果]:");
        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            // 商品名稱同樣轉小寫進行精確比對
            if (PRODUCT_NAMES[i].toLowerCase().equals(cleanKeyword)) {
                System.out.printf("找到商品！ -> 名稱：%s, 價格：NT$%,d, 庫存：%d 件\n", 
                        PRODUCT_NAMES[i], PRODUCT_PRICES[i], PRODUCT_STOCKS[i]);
                found = true;
                break; // 完整名稱理論上唯一，找到即可結束
            }
        }

        if (!found) {
            System.out.println("【結果】找不到此商品（需輸入完全一致的名稱，請確認拼字）。");
        }
    }

    // 自訂方法 4：部分名稱搜尋（模糊匹配，可顯示多筆）
    public static void searchPartialProduct(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("【提示】搜尋關鍵字不可為空。");
            return;
        }

        String cleanKeyword = keyword.trim().toLowerCase();
        boolean found = false;
        int count = 0;

        System.out.println("\n[部分名稱搜尋結果]:");
        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            // 使用 contains 進行子字串模糊匹配
            if (PRODUCT_NAMES[i].toLowerCase().contains(cleanKeyword)) {
                count++;
                System.out.printf("%d. %s (價格: NT$%,d, 庫存: %d)\n", 
                        count, PRODUCT_NAMES[i], PRODUCT_PRICES[i], PRODUCT_STOCKS[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("【結果】找不到包含該關鍵字的商品。");
        }
    }

    // 自訂方法 5：找出名稱最長的商品
    public static void showLongestProductName() {
        if (PRODUCT_NAMES.length == 0) {
            System.out.println("【提示】目前系統中無商品。");
            return;
        }

        String longestName = PRODUCT_NAMES[0];
        int longestIndex = 0;

        for (int i = 1; i < PRODUCT_NAMES.length; i++) {
            if (PRODUCT_NAMES[i].length() > longestName.length()) {
                longestName = PRODUCT_NAMES[i];
                longestIndex = i;
            }
        }

        System.out.println("\n[名稱最長的商品]:");
        System.out.printf("名稱：%s (字元長度：%d)\n", longestName, longestName.length());
        System.out.printf("價格：NT$%,d, 庫存：%d 件\n", PRODUCT_PRICES[longestIndex], PRODUCT_STOCKS[longestIndex]);
    }

    // 自訂方法 6：顯示商品名稱與搜尋關鍵字第一次出現的位置
    public static void showKeywordFirstOccurrence(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("【提示】搜尋關鍵字不可為空。");
            return;
        }

        String cleanKeyword = keyword.trim().toLowerCase();
        boolean foundAny = false;

        System.out.println("\n[關鍵字首次出現位置比對] (忽略大小寫):");
        for (String name : PRODUCT_NAMES) {
            String lowerName = name.toLowerCase();
            int index = lowerName.indexOf(cleanKeyword);

            if (index != -1) {
                System.out.printf("商品：\"%s\" -> 關鍵字 '%s' 首次出現於索引：[%d]\n", name, keyword.trim(), index);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("【結果】所有商品名稱中皆未出現該關鍵字。");
        }
    }
}