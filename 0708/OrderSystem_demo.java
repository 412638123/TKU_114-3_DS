import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int totalItems = 0;
        int totalAmount = 0;
        boolean running = true;

        while (running) {
            
        
            System.out.println("=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            
            System.out.print("請輸入選項：");
            int choice = scanner.nextInt();

        
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    int price = 0;
                    if (choice == 1) price = 30;
                    else if (choice == 2) price = 35;
                    else price = 50;

                
                    int quantity = 0;
                    while (true) {
                        System.out.print("請輸入數量：");
                        quantity = scanner.nextInt();
                        if (quantity > 0) {
                            break;
                        }
                        System.out.println("數量必須大於 0，請重新輸入。");
                    }

                    
                    int subtotal = price * quantity;
                    totalItems += quantity;
                    totalAmount += subtotal;
                    System.out.println("Subtotal: " + subtotal);
                    System.out.println();
                    break;

                case 0:
                    
                    running = false;
                    break;

                default:
                    System.out.println("無效選項，請重新選擇。");
            }
        }

        
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);

        scanner.close();
    }
}