import java.util.Scanner;
public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入姓名: ");
        String name = sc.nextLine();
        System.out.print("請輸入java english math分數: ");
        int javaScore = sc.nextInt();
        int englishScore = sc.nextInt();
        int mathScore = sc.nextInt();
        double average = (javaScore + englishScore + mathScore) / 3.0;
        System.out.println("請輸入數字選擇功能: 1.顯示平均分數 2.顯示及格狀態 3.顯示等第 0.離開");
        int option = sc.nextInt();
        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println("平均分數: " + average);
                    break;
                case 2:
                    if (javaScore >= 60 && englishScore >= 60 && mathScore >= 60) {
                        System.out.println("及格");
                    } else {
                        System.out.println("不及格");
                    }
                    break;
                case 3:
                    if (average >= 90) {
                        System.out.println("等第: A");
                    } else if (average >= 80&& average < 90) {
                        System.out.println("等第: B");
                    } else if (average >= 70&& average < 80) {
                        System.out.println("等第: C");
                    } else if (average >= 60&& average < 70) {
                        System.out.println("等第: D");
                    } else {
                        System.out.println("等第: F");
                    }
                    break;
                default:
                    System.out.println("請輸入正確的選項!");
                    break;}
                    option = sc.nextInt();
        }
        
        System.out.println("程式已結束，謝謝使用！");
        sc.close();
            }}


