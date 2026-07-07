public class LoginCheck {
    public static void main(String[] args) {
        int score = 75;
        int attendance = 90;
String username = "admin";
String password = "1234";
String inputUsername = "admin";
String inputPassword = "1234";
        boolean passCourse = score >= 60 && attendance >= 80;
        boolean loginSuccess = inputUsername.equals(username) && inputPassword.equals(password);

        System.out.println("Score: " + score);
        System.out.println("Attendance: " + attendance);
        System.out.println("Pass course: " + passCourse);
    }
}
