import java.util.Scanner;
public class HealthAdvisor {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("請輸入姓名: ");
    String name = sc.nextLine();
    System.out.println("Name: " + name );
    System.out.print("請輸入身高: ");
    double height = sc.nextDouble();
    System.out.println("Height: " + height );
    System.out.print("請輸入體重: ");
    double weight = sc.nextDouble();
    System.out.println("Weight: " + weight );
    double bmi = weight / (height * height);
    System.out.println("BMI: " + bmi );
    if (bmi < 18.5) {
        System.out.println("BMI level: Underweight");
    } else if (bmi < 24&& bmi >= 18.5) {
        System.out.println("BMI level: Normal");
    } else if (bmi < 27 && bmi >= 24) {
        System.out.println("BMI level: Overweight");
    } else {
        System.out.println("BMI level: Obese");
    }
    while (true) {
        System.out.println("是否輸入下一筆: ");
        String answer = sc.next();
        if (answer=="Y") {
            
}
        else if (answer=="N") { {
            sc.close();;
        }
    }
}}}
