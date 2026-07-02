import java.util.Scanner;

public class ScoreReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入 Java 成績：");
        int javaScore = sc.nextInt();
        System.out.print("請輸入 English 成績：");
        int englishScore = sc.nextInt();
        System.out.print("請輸入 Math 成績：");
        int mathScore = sc.nextInt();
        double averageScore = (javaScore + englishScore + mathScore) / 3.0;
            // 計算平均分數
        System.out.println("Name: " + name);
        System.out.println("Java Score: " + javaScore);
        System.out.println("English Score: " + englishScore);
        System.out.println("Math Score: " + mathScore);
        System.out.println("Average Score: " + averageScore);
            // 顯示平均分數
        sc.close();
    }
}