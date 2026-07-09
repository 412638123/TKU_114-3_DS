public class PassCounter {
    public static void main(String[] args) {
      int[] grades = {80, 55, 70};
        int passCount = 0;

        for (int grade : grades) {
            if (grade >= 60) {
                passCount++;
            }
        }

       
        System.out.println("Pass count: " + passCount);


    }
}