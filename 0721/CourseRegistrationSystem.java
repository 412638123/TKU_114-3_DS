import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static ArrayList<Course> courseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- 選課系統 ---");
            System.out.println("1. 新增課程");
            System.out.println("2. 選課");
            System.out.println("3. 退選");
            System.out.println("4. 刪除課程");
            System.out.println("5. 搜尋課程");
            System.out.println("6. 統計資訊（總課程數、總選課人次、額滿課程）");
            System.out.println("7. 離開");
            System.out.print("請選擇功能 (1-7)：");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    registerCourse();
                    break;
                case "3":
                    dropCourse();
                    break;
                case "4":
                    deleteCourse();
                    break;
                case "5":
                    searchCourse();
                    break;
                case "6":
                    showStatistics();
                    break;
                case "7":
                    System.out.println("系統已退出。");
                    return;
                default:
                    System.out.println("選項無效，請重新選擇。");
            }
        }
    }

    public static void addCourse() {
        System.out.print("請輸入課程代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("錯誤：代碼不得為空白。");
            return;
        }

        if (findCourseByCode(code) != null) {
            System.out.println("錯誤：代碼已存在，代碼不可重複。");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("錯誤：名稱不得為空白。");
            return;
        }

        System.out.print("請輸入課程容量：");
        int capacity;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("錯誤：容量必須為數字。");
            return;
        }

        if (capacity <= 0) {
            System.out.println("錯誤：容量必須大於 0。");
            return;
        }

        courseList.add(new Course(code, name, capacity));
        System.out.println("新增課程成功！");
    }

    public static void registerCourse() {
        System.out.print("請輸入要選課的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("找不到該代碼的課程。");
            return;
        }

        if (course.isFull()) {
            System.out.println("選課失敗：課程已額滿，不可再增加人數。");
            return;
        }

        course.setCurrentEnrolled(course.getCurrentEnrolled() + 1);
        System.out.println("選課成功！");
    }

    public static void dropCourse() {
        System.out.print("請輸入要退選的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("找不到該代碼的課程。");
            return;
        }

        if (course.getCurrentEnrolled() <= 0) {
            System.out.println("退選失敗：目前人數已經是 0。");
            return;
        }

        course.setCurrentEnrolled(course.getCurrentEnrolled() - 1);
        System.out.println("退選成功！");
    }

    public static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼：");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);
        if (course != null) {
            courseList.remove(course);
            System.out.println("刪除成功！");
        } else {
            System.out.println("找不到");
        }
    }

    public static void searchCourse() {
        System.out.print("請輸入要搜尋的課程代碼或名稱關鍵字：");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("關鍵字不得為空白。");
            return;
        }

        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(keyword) || course.getName().contains(keyword)) {
                System.out.println(course);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的課程。");
        }
    }

    public static void showStatistics() {
        int totalCourses = courseList.size();
        int totalEnrolled = 0;
        ArrayList<Course> fullCourses = new ArrayList<>();

        for (Course course : courseList) {
            totalEnrolled += course.getCurrentEnrolled();
            if (course.isFull()) {
                fullCourses.add(course);
            }
        }

        System.out.println("\n--- 課程統計資訊 ---");
        System.out.println("總課程數：" + totalCourses);
        System.out.println("總選課人次：" + totalEnrolled);
        System.out.println("額滿課程列表：");
        if (fullCourses.isEmpty()) {
            System.out.println("  (目前沒有額滿課程)");
        } else {
            for (Course course : fullCourses) {
                System.out.println("  - " + course);
            }
        }
    }

    private static Course findCourseByCode(String code) {
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}