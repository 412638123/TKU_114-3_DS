import java.util.Stack;

public class BrowserUndoSystem {
    private Stack<String> history;
    private String currentPage;

    public BrowserUndoSystem() {
        this.history = new Stack<>();
        this.currentPage = null;
    }

    public void visitPage(String url) {
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("開啟新頁面：" + currentPage);
        showCurrentPage();
    }

    public void goBack() {
        if (history.isEmpty()) {
            System.out.println("返回失敗：沒有上一頁可返回。");
            return;
        }
        currentPage = history.pop();
        System.out.println("成功返回上一頁。");
        showCurrentPage();
    }

    public void showCurrentPage() {
        if (currentPage == null) {
            System.out.println("目前頁面：無（尚未瀏覽任何網頁）\n--------------------");
        } else {
            System.out.println("目前頁面：" + currentPage + "\n--------------------");
        }
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 操作 1：初始狀態檢視 ===");
        browser.showCurrentPage();

        System.out.println("=== 操作 2：嘗試無上一頁時返回 ===");
        browser.goBack();

        System.out.println("=== 操作 3：造訪首頁 (Google) ===");
        browser.visitPage("https://www.google.com");

        System.out.println("=== 操作 4：造訪第二頁 (GitHub) ===");
        browser.visitPage("https://github.com");

        System.out.println("=== 操作 5：造訪第三頁 (StackOverflow) ===");
        browser.visitPage("https://stackoverflow.com");

        System.out.println("=== 操作 6：返回上一頁 (回到 GitHub) ===");
        browser.goBack();

        System.out.println("=== 操作 7：再返回上一頁 (回到 Google) ===");
        browser.goBack();

        System.out.println("=== 操作 8：造訪新頁面 (YouTube) ===");
        browser.visitPage("https://www.youtube.com");
    }
}