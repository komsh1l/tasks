import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class task4 {
    public static void main(String[] args) {
        String result1 = nonRepeatable("abracadabra");
        System.out.println(result1);
        List<String> combinations = generateBrackets(3);
        System.out.println(String.join(" ", combinations));
        List<String> combinations1 = binarySystem(3);
        System.out.println(String.join(" ", combinations1));
        System.out.println(alphabeticRow("klmabzyxw"));
        System.out.println(countAndSort("aaabbcdd"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(uniqueSubstring("77897898"));
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(shortestWay(grid1));
        String input2 = "re6sponsibility Wit1h gr5eat power3 4comes g2reat";
        System.out.println(numericOrder(input2));
        System.out.println(switchNums(6274, 71259));
    }
    public static String nonRepeatable(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (result.indexOf(String.valueOf(currentChar)) == -1) {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateBracketsHelper(n, n, "", result);
        return result;
    }

    private static void generateBracketsHelper(int left, int right, String current, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current);
            return;
        }

        if (left > 0) {
            generateBracketsHelper(left - 1, right, current + "(", result);
        }

        if (right > left) {
            generateBracketsHelper(left, right - 1, current + ")", result);
        }
    }

    public static List<String> binarySystem(int n) {
        List<String> result = new ArrayList<>();
        generateBinaryCombinations("", n, result);
        return result;
    }

    private static void generateBinaryCombinations(String current, int n, List<String> result) {
        if (current.length() == n) {
            result.add(current);
            return;
        }

        generateBinaryCombinations(current + "1", n, result);

        if (current.isEmpty() || current.charAt(current.length() - 1) != '0') {
            generateBinaryCombinations(current + "0", n, result);
        }
    }

    public static String alphabeticRow(String str) {
        String longestRow = "";
        String currentRow = String.valueOf(str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            char previousChar = str.charAt(i - 1);

            if (Math.abs(currentChar - previousChar) == 1) {
                currentRow += currentChar;
            } else {
                if (currentRow.length() > longestRow.length()) {
                    longestRow = currentRow;
                }
                currentRow = String.valueOf(currentChar);
            }
        }

        if (currentRow.length() > longestRow.length()) {
            longestRow = currentRow;
        }

        return longestRow;
    }

    public static String countAndSort(String str) {
        List<String> patterns = new ArrayList<>();
        char currentChar = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            char nextChar = str.charAt(i);

            if (nextChar == currentChar) {
                count++;
            } else {
                patterns.add(currentChar + Integer.toString(count));
                currentChar = nextChar;
                count = 1;
            }
        }

        patterns.add(currentChar + Integer.toString(count));

        Comparator<String> numberComparator = (s1, s2) -> {
            int num1 = Integer.parseInt(s1.substring(1));
            int num2 = Integer.parseInt(s2.substring(1));
            return Integer.compare(num1, num2);
        };

        Collections.sort(patterns, numberComparator);

        return String.join("", patterns);
    }

    public static int convertToNum(String input) {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        numberMap.put("ten", 10);
        numberMap.put("eleven", 11);
        numberMap.put("twelve", 12);
        numberMap.put("thirteen", 13);
        numberMap.put("fourteen", 14);
        numberMap.put("fifteen", 15);
        numberMap.put("sixteen", 16);
        numberMap.put("seventeen", 17);
        numberMap.put("eighteen", 18);
        numberMap.put("nineteen", 19);
        numberMap.put("twenty", 20);
        numberMap.put("thirty", 30);
        numberMap.put("forty", 40);
        numberMap.put("fifty", 50);
        numberMap.put("sixty", 60);
        numberMap.put("seventy", 70);
        numberMap.put("eighty", 80);
        numberMap.put("ninety", 90);
        numberMap.put("hundred", 100);

        String[] words = input.split(" ");
        int result = 0;
        int currentNumber = 0;

        for (String word : words) {
            int value = numberMap.get(word);
            if (value == 100) {
                currentNumber *= value;
            } else {
                currentNumber += value;
            }
        }

        result += currentNumber;
        return result;
    }

    public static String uniqueSubstring(String s) {

        int maxLength = 0;  // Максимальная длина найденной подстроки
        String maxSubstring = "";  // Максимальная подстрока

        int startIndex = 0;  // Начальный индекс текущей подстроки
        int currentLength = 0;  // Текущая длина текущей подстроки
        Map<Character, Integer> charIndexMap = new HashMap<>();  // Мап для отслеживания индексов символов

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charIndexMap.containsKey(c)) {
                // Если символ уже встречался в текущей подстроке, обновляем начальный индекс
                startIndex = Math.max(startIndex, charIndexMap.get(c) + 1);
            }

            charIndexMap.put(c, i);
            currentLength = i - startIndex + 1;

            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxSubstring = s.substring(startIndex, i + 1);
            }
        }

        return maxSubstring;
    }

    public static int shortestWay(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static String numericOrder(String input) {
        String[] words = input.split(" ");
        String[] orderedWords = new String[words.length];

        for (String word : words) {
            StringBuilder wordWithoutDigits = new StringBuilder();
            StringBuilder digitPart = new StringBuilder();

            for (char c : word.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitPart.append(c);
                } else {
                    wordWithoutDigits.append(c);
                }
            }

            if (digitPart.length() > 0) {
                int position = Integer.parseInt(digitPart.toString());
                orderedWords[position - 1] = wordWithoutDigits.toString();
            }
        }

        return String.join(" ", orderedWords);
    }

    public static int switchNums(int num1, int num2) {
        String num1Str = Integer.toString(num1);
        String num2Str = Integer.toString(num2);

        for (int i = 0; i < num1Str.length(); i++) {
            char maxDigit = '0';
            for (char digit : num1Str.toCharArray()) {
                if (digit > maxDigit) {
                    maxDigit = digit;
                }
            }

            for (int j = 0; j < num2Str.length(); j++) {
                if (num2Str.charAt(j) < maxDigit) {
                    num2Str = num2Str.substring(0, j) + maxDigit + num2Str.substring(j + 1);
                    break;
                }
            }

            num1Str = num1Str.replaceFirst(String.valueOf(maxDigit), "");
        }

        return Integer.parseInt(num2Str);
    }
}
