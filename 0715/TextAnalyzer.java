import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        String text = getInputText(scanner);
        
        
        System.out.println("原始字元數：" + getOriginalCharCount(text));
        
        
        System.out.println("有效字元數：" + getEffectiveCharCount(text));
        
        
        String[] words = splitIntoWords(text);
        
        System.out.println("單字數量：" + getWordCount(words));
        
        
        System.out.println("母音總數 (a, e, i, o, u)：" + countVowels(text));
        
        
        System.out.println("最長單字：" + findLongestWord(words));
        
        
        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = scanner.nextLine().trim();
        System.out.println("關鍵字 [" + keyword + "] 出現次數：" + countKeywordOccurrences(words, keyword));
        
        scanner.close();
    }

    
    public static String getInputText(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("請輸入一行文字（不可為空字串或全空白）：");
            input = scanner.nextLine();
            
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("【錯誤】輸入無效，請重新輸入！\n");
        }
        return input;
    }

    
    public static int getOriginalCharCount(String text) {
        return text.length();
    }

    
    public static int getEffectiveCharCount(String text) {
        return text.trim().length();
    }

    
    public static String[] splitIntoWords(String text) {
        
        return text.trim().split("\\s+");
    }

    
    public static int getWordCount(String[] words) {
        
        if (words.length == 1 && words[0].isEmpty()) {
            return 0;
        }
        return words.length;
    }

    
    public static int countVowels(String text) {
        int count = 0;
        
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    
    public static String findLongestWord(String[] words) {
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    
    public static int countKeywordOccurrences(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}