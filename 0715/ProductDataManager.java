import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductDataManager {

    // 原始資料來源
    private static final String[] INITIAL_RECORDS = {
        "Keyboard,890,12",
        "Mouse,490,20",
        "Monitor,5200,5",
        "USB Cable,250,30",
        "Headset,1290,8"
    };

    // 使用動態列表 (List) 方便後續新增資料，並保持陣列操作的特性
    private static List<String> names = new ArrayList<>();
    private static List<Integer> prices = new ArrayList<>();
    private static List<Integer> stocks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== 系統初始化：開始解析原始資料 ===");
        for (String record : INITIAL_RECORDS) {
            parseAndAddRecord(record, false); // 初始化時不重複印出成功訊息
        }
        
        // 1. 顯示解析後的商品表格
        displayProductTable();

        // 2. 顯示庫存總價值
        displayTotalValue();

        // 3. 顯示低庫存商品 (假設庫存小於等於 10 為低庫存)
        displayLowStock(10);

        // 4. 關鍵字搜尋測試 (支援完整與部分名稱)
        System.out.println("\n=== 關鍵字搜尋測試 ===");
        searchProduct("Mouse");      // 完整名稱
        searchProduct("key");        // 部分名稱 (不區分大小寫)
        searchProduct("NotExists");  // 不存在的商品

        // 5. 測試案例執行 (包含新增資料與驗證格式)
        runTestCases();
    }

    /**
     * 解析單筆資料並存入對應的 List (陣列) 中，內含嚴格的格式與型態驗證
     */
    private static boolean parseAndAddRecord(String record, boolean verbose) {
        if (record == null || record.trim().isEmpty()) {
            System.out.println("❌ 錯誤：輸入資料不可為空。");
            return false;
        }

        // 使用 split(",") 解析資料
        String[] tokens = record.split(",");

        // 驗證欄位數量是否為 3
        if (tokens.length != 3) {
            System.out.println("❌ 錯誤 [" + record + "]：欄位數量不正確，必須包含名稱、價格、庫存共 3 個欄位。");
            return false;
        }

        String name = tokens[0].trim();
        String priceStr = tokens[1].trim();
        String stockStr = tokens[2].trim();

        // 驗證名稱不可為空
        if (name.isEmpty()) {
            System.out.println("❌ 錯誤 [" + record + "]：商品名稱不可為空白。");
            return false;
        }

        int price;
        int stock;

        // 數字轉換錯誤處理 (Try-Catch)
        try {
            price = Integer.parseInt(priceStr);
            if (price < 0) {
                System.out.println("❌ 錯誤 [" + record + "]：價格不能為負數 (" + priceStr + ")。");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 錯誤 [" + record + "]：價格必須為有效的整數數字 (" + priceStr + ")。");
            return false;
        }

        try {
            stock = Integer.parseInt(stockStr);
            if (stock < 0) {
                System.out.println("❌ 錯誤 [" + record + "]：庫存不能為負數 (" + stockStr + ")。");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 錯誤 [" + record + "]：庫存必須為有效的整數數字 (" + stockStr + ")。");
            return false;
        }

        // 驗證全數通過，存入陣列 (List)
        names.add(name);
        prices.add(price);
        stocks.add(stock);

        if (verbose) {
            System.out.println("✅ 成功新增商品：" + name + " | 價格：" + price + " | 庫存：" + stock);
        }
        return true;
    }

    /**
     * 顯示商品表格
     */
    private static void displayProductTable() {
        System.out.println("\n=== 目前商品庫存列表 ===");
        System.out.printf("%-15s %-10s %-10s\n", "商品名稱", "價格 (元)", "庫存量");
        System.out.println("-------------------------------------");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%-15s %-10d %-10d\n", names.get(i), prices.get(i), stocks.get(i));
        }
        System.out.println("-------------------------------------");
    }

    /**
     * 商品關鍵字搜尋 (不區分大小寫)
     */
    private static void searchProduct(String keyword) {
        System.out.println("🔍 搜尋關鍵字 [" + keyword + "] 的結果:");
        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("  👉 找到商品 -> 名稱: %s, 價格: %d, 庫存: %d\n", names.get(i), prices.get(i), stocks.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("  ❌ 找不到符合條件的商品。");
        }
    }

    /**
     * 顯示低庫存商品
     */
    private static void displayLowStock(int threshold) {
        System.out.println("\n⚠️ 低庫存警示 (庫存 <= " + threshold + ") :");
        boolean hasLowStock = false;
        for (int i = 0; i < names.size(); i++) {
            if (stocks.get(i) <= threshold) {
                System.out.printf("  🚨 商品: %-12s | 剩餘庫存: %d\n", names.get(i), stocks.get(i));
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("  一切正常，目前無低庫存商品。");
        }
    }

    /**
     * 計算並顯示庫存總價值
     */
    private static void displayTotalValue() {
        long totalValue = 0;
        for (int i = 0; i < names.size(); i++) {
            totalValue += (long) prices.get(i) * stocks.get(i);
        }
        System.out.println("\n💰 庫存總價值: $" + totalValue + " 元");
    }

    /**
     * 執行 8 組測試案例 (包含正常與各種異常狀況)
     */
    private static void runTestCases() {
        System.out.println("\n=================================");
        System.out.println("🧪 開始執行 8 組指定測試案例");
        System.out.println("=================================");

        String[] testCases = {
            "Speaker,1500,15",       // Case 1: 正常資料入庫
            "Type-C Hub,990,0",      // Case 2: 正常資料 (庫存為0)
            "Microphone,abc,10",     // Case 3: 價格非數字 (轉換錯誤)
            "Webcam,2500,xyz",       // Case 4: 庫存非數字 (轉換錯誤)
            "Pad,120",               // Case 5: 欄位不足 (遺失庫存)
            "Router,1990,20,Extra",  // Case 6: 欄位過多
            ",300,5",                // Case 7: 商品名稱空白
            "Screen,-500,10"         // Case 8: 價格為負數 (邏輯錯誤)
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\n[測試案例 " + (i + 1) + "] 輸入字串: \"" + testCases[i] + "\"");
            parseAndAddRecord(testCases[i], true);
        }

        // 測試完後再次展示最終表格與總價值，證明中途出錯程式並未中斷
        displayProductTable();
        displayTotalValue();
    }
}