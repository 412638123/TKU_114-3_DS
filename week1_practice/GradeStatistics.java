package week1_practice;
import java.util.Scanner;

public class GradeStatistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int count = 0;
        int total = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int passCount = 0;
        int failCount = 0;
        
        System.out.println("請輸入成績 (輸入 -1 結束)：");

        while (true) {
            System.out.print("輸入成績: ");
            int score = scanner.nextInt();

            // 結束條件
            if (score == -1) {
                break;
            }

            // 輸入驗證
            if (!isValidScore(score)) {
                System.out.println("無效輸入，請輸入 0 到 100 之間的成績。");
                continue;
            }

            // 更新統計資料
            count++;
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;

            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
        }

        // 判斷是否有輸入成績
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            double average = (double) total / count;
            String grade = getGrade(average);
            printSummary(count, total, average, max, min, passCount, failCount, grade);
        }

        scanner.close();
    }

    // 1. 驗證成績範圍
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    // 2. 判斷是否及格
    public static boolean isPassing(int score) {
        return score >= 60;
    }

    // 3. 根據平均取得等第
    public static String getGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }

    // 4. 輸出總結報表
    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount, String grade) {
        System.out.println("\n=== 成績統計報表 ===");
        System.out.println("總人數: " + count);
        System.out.println("總分: " + total);
        System.out.printf("平均分數: %.2f\n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("及格人數: " + passCount);
        System.out.println("不及格人數: " + failCount);
        System.out.println("平均等第: " + grade);
    }
}