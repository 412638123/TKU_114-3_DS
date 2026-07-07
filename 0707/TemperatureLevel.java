public class TemperatureLevel {
    public static void main(String[] args) {
        double temperature = 30;

        if (temperature < 15) {
            System.out.println("Temperature level:  Cold");
        } else if (15< temperature && temperature < 28) {
            System.out.println("Temperature level: Comfortable");
        } else if (temperature > 28) {
            System.out.println("Temperature level:  Hot");
        }
    }
}
