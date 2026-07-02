import java.util.Scanner;

public class HealthCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入身高：");
        double height = sc.nextDouble();
        System.out.print("請輸入體重：");
        double weight = sc.nextDouble();
        double BMI = weight / (height * height);
        System.out.println("Name: " + name);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + BMI);

        sc.close();
    }
}