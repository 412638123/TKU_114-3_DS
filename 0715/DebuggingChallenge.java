/**
 * 
 *
 *
 * 1. 【編譯錯誤】
 *    - 錯誤位置：`System.out.println("系統結束，年齡：" + age)` (原本的第 22 行)
 *    - 錯誤類型：語法錯誤 (Syntax Error)
 *    - 原因：該行程式碼末端漏掉了分號 `;`。
 *    - 修正方式：在該行結尾加上分號。
 *
 * 2. 【陣列越界錯誤】
 *    - 錯誤位置：`for (int i = 0; i <= scores.length; i++)` (原本的第 8 行)
 *    - 錯誤類型：執行期異常 (ArrayIndexOutOfBoundsException)
 *    - 原因：迴圈終止條件使用了 `<=`。當 `i` 等於 `scores.length` (即 3) 時，
 *            試圖存取 `scores[3]`，但陣列合法索引只有 0, 1, 2。
 *    - 修正方式：將 `<=` 修改為 `<`。
 * 
 * 3. 【整數除法邏輯錯誤】
 *    - 錯誤位置：`double average = total / scores.length;` (原本的第 12 行)
 *    - 錯誤類型：邏輯錯誤 (Logical Error / Precision Loss)
 *    - 原因：`total` 與 `scores.length` 皆為整數（`int`）。在 Java 中，
 *            「整數 / 整數」的結果依然是整數（會直接無條件捨去小數點），之後才轉為 `double`。
 *            這會導致平均值失去精準度（例如：247 / 3 本應為 82.33，卻會先算出 82.00）。
 *    - 修正方式：將其中一個運算元強制轉型為 `double`，例如：`(double) total / scores.length`。
 * 
 * 4. 【Scanner 換行問題】
 *    - 錯誤位置：在 `sc.nextInt()` 之後直接呼叫 `sc.nextLine()` (原本的第 17-20 行)
 *    - 錯誤類型：輸入流邏輯錯誤 (Input Mismatch / Skipping Input)
 *    - 原因：`sc.nextInt()` 只讀取了數字，卻將使用者按下 Enter 鍵所產生的「換行符號（\n）」留在緩衝區中。
 *            隨後的 `sc.nextLine()` 會直接讀取到這個殘留的換行符，導致程式沒有等待使用者輸入指令就直接跳過。
 *    - 修正方式：在 `sc.nextInt()` 之後，額外呼叫一次 `sc.nextLine()` 來清空緩衝區中的換行符。
 * 
 * 5. 【字串比較錯誤】
 *    - 錯誤位置：`if (command == "exit")` (原本的第 21 行)
 *    - 錯誤類型：邏輯錯誤 (Object Reference Comparison)
 *    - 原因：在 Java 中，`==` 用於比較基本資料型態的值，或物件的「記憶體位址（Reference）」。
 *            比較字串的實際「內容」必須使用 `.equals()` 方法。
 *    - 修正方式：修改為 `if ("exit".equals(command))` 或 `if (command.equals("exit"))`。
 */

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        
        
        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        
        if ("exit".equals(command)) {
            
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}