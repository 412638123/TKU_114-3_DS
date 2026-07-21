public class Course2 {
    private String code;
    private String name;
    private int capacity;
    private int currentEnrolled;

    public Course2(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.currentEnrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentEnrolled() {
        return currentEnrolled;
    }

    public void setCurrentEnrolled(int currentEnrolled) {
        this.currentEnrolled = currentEnrolled;
    }

    public boolean isFull() {
        return currentEnrolled >= capacity;
    }

    @Override
    public String toString() {
        return "代碼: " + code + " | 名稱: " + name + " | 容量: " + capacity + " | 目前人數: " + currentEnrolled + (isFull() ? " (額滿)" : "");
    }
}
