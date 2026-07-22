import java.util.Stack;

public class BracketValidationSystem {

    public boolean isValid(String expression) {
        if (expression == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } 
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("錯誤：偵測到多餘的右括號 '" + ch + "'（缺少左括號）。");
                    return false;
                }

                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    System.out.println("錯誤：括號順序/型態不匹配，期望結尾能對應 '" + top + "'，卻遇到了 '" + ch + "'。");
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("錯誤：尚有未閉合的左括號（缺少右括號）。");
            return false;
        }

        return true;
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
            (open == '[' && close == ']') ||
            (open == '{' && close == '}');
    }

    public static void testExpression(BracketValidationSystem validator, String expr) {
        System.out.println("測試表達式：" + expr);
        boolean result = validator.isValid(expr);
        System.out.println("驗證結果：" + (result ? "合法 (Valid)" : "不合法 (Invalid)"));
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        BracketValidationSystem validator = new BracketValidationSystem();

        System.out.println("=== 括號合法性驗證測試 ===\n");

        // 1. 正常的多層巢狀與混合非括號字元
        testExpression(validator, "{[a + (b * c)] - d}");

        // 2. 順序錯誤
        testExpression(validator, "([)]");

        // 3. 缺少左括號（多餘右括號）
        testExpression(validator, "abc) + 123");

        // 4. 缺少右括號（左括號未閉合）
        testExpression(validator, "{[a + b}");

        // 5. 空字串處理
        testExpression(validator, "");

        // 6. 完全沒有括號的純文字
        testExpression(validator, "hello world 123");

        // 7. 單一不匹配的括號
        testExpression(validator, "(");

        // 8. 多重正確的簡單括號
        testExpression(validator, "()[]{}");
    }
}