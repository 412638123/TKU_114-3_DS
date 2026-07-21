import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("請輸入成績（輸入 -1 結束輸入）：");

        while (true) {
            System.out.print("請輸入成績：");
            int input = scanner.nextInt();

            if (input == -1) {
                break;
            }

            if (input >= 0 && input <= 100) {
                scores.add(input);
            } else {
                System.out.println("輸入無效！成績必須介於 0 到 100 之間，請重新輸入。");
            }
        }

        scanner.close();

        if (scores.isEmpty()) {
            System.out.println("\n未輸入任何有效成績。");
            return;
        }

        printStatistics(scores);

        ArrayList<Integer> passingScores = filterPassingScores(scores);
        System.out.println("及格名單（成績 >= 60）： " + passingScores);
    }

    public static void printStatistics(ArrayList<Integer> scores) {
        int count = scores.size();
        int sum = 0;
        int max = scores.get(0);
        int min = scores.get(0);

        for (int score : scores) {
            sum += score;
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }

        double average = (double) sum / count;

        System.out.println("\n--- 成績統計結果 ---");
        System.out.println("總筆數：" + count);
        System.out.printf("平均成績：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
    }

    public static ArrayList<Integer> filterPassingScores(ArrayList<Integer> scores) {
        ArrayList<Integer> passingList = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passingList.add(score);
            }
        }
        return passingList;
    }
}
