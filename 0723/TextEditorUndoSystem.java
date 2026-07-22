import java.util.Stack;

public class TextEditorUndoSystem {
    private StringBuilder currentText;
    private Stack<String> history;

    public TextEditorUndoSystem() {
        this.currentText = new StringBuilder();
        this.history = new Stack<>();
    }

    private void saveState() {
        history.push(currentText.toString());
    }

    public void appendText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        saveState();
        currentText.append(text);
        System.out.println("新增文字：「" + text + "」");
        showContent();
    }

    public void deleteLastChars(int count) {
        if (count <= 0) {
            return;
        }
        if (currentText.length() == 0) {
            System.out.println("刪除失敗：目前文字內容為空。");
            return;
        }
        
        saveState();
        int deleteCount = Math.min(count, currentText.length());
        int startIdx = currentText.length() - deleteCount;
        currentText.delete(startIdx, currentText.length());
        System.out.println("刪除最後 " + deleteCount + " 個字元。");
        showContent();
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("Undo 失敗：沒有歷史紀錄可返回（無法再復原）。");
            return;
        }
        
        String previousState = history.pop();
        currentText = new StringBuilder(previousState);
        System.out.println("執行 Undo（復原至上一個狀態）。");
        showContent();
    }

    public void showContent() {
        System.out.println("目前文字內容：[" + currentText.toString() + "]");
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 測試 1：初始狀態與空歷史 Undo ===");
        editor.showContent();
        editor.undo();

        System.out.println("\n=== 測試 2：依序新增文字 ===");
        editor.appendText("Hello");
        editor.appendText(" World");
        editor.appendText("!");

        System.out.println("\n=== 測試 3：刪除最後字元 ===");
        editor.deleteLastChars(2);

        System.out.println("\n=== 測試 4：連續 Undo 三次並驗證結果 ===");
        editor.undo();
        editor.undo();
        editor.undo();

        System.out.println("\n=== 測試 5：超過歷史紀錄的 Undo 測試 ===");
        editor.undo();
        editor.undo();
    }
}
