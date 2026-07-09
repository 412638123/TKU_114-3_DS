public class PricePrinter {
    public static void main(String[] args) {
        printItem("Black tea", 2);
        printItem("Green tea", 2);
        printItem("Coffee", 3);
    }

public static void printItem(String itemName, int price) {
        System.out.println(itemName + ": $" + price);
    }
}
