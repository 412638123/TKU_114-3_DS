public class SubtotalCalculator {
    public static void main(String[] args) {
        int subtotal = calculateSubtotal(3, 5);
        System.out.println("Subtotal: " + subtotal);
    }

    public static int calculateSubtotal(int quantity, int price) {
        return quantity * price;
    }
}
