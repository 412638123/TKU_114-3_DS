package week1_practice;
import java.util.Scanner;

public class PersonalProfileApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        System.out.print("請輸入姓名：");
        String name = scanner.nextLine();

        int age = readPositiveInt(scanner, "請輸入年齡：");
        double height = readPositiveDouble(scanner, "請輸入身高 (公尺)：");
        double weight = readPositiveDouble(scanner, "請輸入體重 (公斤)：");

        // 運算處理
        double bmi = calculateBmi(height, weight);
        String bmiLevel = getBmiLevel(bmi);
        boolean adult = isAdult(age);


        printReport(name, age, adult, height, weight, bmi, bmiLevel);

        scanner.close();
    }


    public static int readPositiveInt(Scanner sc, String message) {
        int value = 0;
        while (value <= 0) {
            System.out.print(message);
            value = sc.nextInt();
            if (value <= 0) System.out.println("輸入錯誤：數值必須大於 0，請重新輸入。");
        }
        return value;
    }


    public static double readPositiveDouble(Scanner sc, String message) {
        double value = 0;
        while (value <= 0) {
            System.out.print(message);
            value = sc.nextDouble();
            if (value <= 0) System.out.println("輸入錯誤：數值必須大於 0，請重新輸入。");
        }
        return value;
    }


    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }


    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) return "體重過輕";
        else if (bmi < 24) return "正常範圍";
        else if (bmi < 27) return "過重";
        else if (bmi < 30) return "輕度肥胖";
        else if (bmi < 35) return "中度肥胖";
        else return "重度肥胖";
    }


    public static boolean isAdult(int age) {
        return age >= 18;
    }

    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println("\n--- 個人健康資料報表 ---");
        System.out.println("姓名： " + name);
        System.out.println("年齡： " + age);
        System.out.println("是否成年： " + (adult ? "是" : "否"));
        System.out.println("身高： " + height + " 公尺");
        System.out.println("體重： " + weight + " 公斤");
        System.out.printf("BMI： %.2f\n", bmi);
        System.out.println("BMI 分級： " + level);
        System.out.println("-----------------------");
    }
}