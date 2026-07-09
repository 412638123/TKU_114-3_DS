import java.util.Scanner;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入輸入商品數量：");
        int quantity = sc.nextInt();

        while (quantity < 0 || quantity ==0) {
            System.out.print("重新輸入(不得小於0)：");
            quantity = sc.nextInt();
        }

        System.out.println("Quantity: " + quantity);

        sc.close();
    }
}