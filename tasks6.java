import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;
import java.util.stream.Collectors;

public class tasks6 {
    public static void main(String[] args) {
        System.out.println('1');
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println('2');
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(Arrays.toString(collect("strengths", 3)));
        System.out.println(Arrays.toString(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15)));
        System.out.println('3');
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh")); // "yowmledrovlvsnieesrh"
        System.out.println(nicoCipher("andiloveherso", "tesha")); // "lnidaevheo s or"
        System.out.println(nicoCipher("mubashirhassan", "crazy")); // "bmusarhiahass n"
        System.out.println(nicoCipher("edabitisamazing", "matt")); // "deabtiismaaznig "
        System.out.println(nicoCipher("iloveher", "612345")); // "lovehir    e"
        System.out.println('4');
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1,}, 15)));
        System.out.println('5');
        System.out.println(Arrays.toString(isExact(6)));    // [6, 3]
        System.out.println(Arrays.toString(isExact(24)));   // [24, 4]
        System.out.println(Arrays.toString(isExact(125)));  // []
        System.out.println('6');
        System.out.println(fractions("0.(3)"));      // "2/3"
        System.out.println(fractions("1.(1)"));      // "10/9"
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println('7');
        System.out.println(pilish_string("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println(pilish_string("X"));
        System.out.println('8');
        System.out.println(evaluateExpression("3 + 5 * (2 - 6)")); // -17
        System.out.println(evaluateExpression("6 - 18 / (-1 + 4)")); // 0
        System.out.println('9');
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println("10");
        System.out.println(findLCS("abcd", "bd")); // "bd"
        System.out.println(findLCS("aggtab", "gxtxamb")); // "gtab"

    }

    public static String hiddenAnagram(String sentence, String word) {
        sentence = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
        word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for (int i = 0; i <= sentence.length() - word.length(); i++) {
            String substring = sentence.substring(i, i + word.length());
            char[] substringChars = substring.toCharArray();
            char[] wordChars = word.toCharArray();
            Arrays.sort(substringChars);
            Arrays.sort(wordChars);

            if (Arrays.equals(substringChars, wordChars)) {
                return substring;
            }
        }

        return "notfound";
    }

    public static String[] collect(String input, int n) {
        if (input.length() < n) {
            // Возвращает пустой массив, если заданная строка меньше n.
            return new String[0];
        }

        ArrayList<String> result = new ArrayList<>();
        collectHelper(input, n, result);

        // Сортирует результат лексикографически.
        Collections.sort(result);

        return result.toArray(new String[0]);
    }

    private static void collectHelper(String remaining, int n, ArrayList<String> result) {
        if (remaining.length() < n) {
            return;
        }
        // Получаем срез длины n из текущей подстроки.
        String substring = remaining.substring(0, n);
        // Преобразуем строку в массив символов для удобства удаления символов.
        char[] chars = substring.toCharArray();

        // Удаляем использованные символы из оставшейся подстроки.
        StringBuilder remainingBuilder = new StringBuilder(remaining);
        for (char c : chars) {
            int index = remainingBuilder.indexOf(String.valueOf(c));
            remainingBuilder.deleteCharAt(index);
        }

        // Добавляем срез в результат.
        result.add(substring);

        // Вызываем рекурсивно для оставшейся подстроки.
        collectHelper(remainingBuilder.toString(), n, result);
    }

    public static String nicoCipher(String message, String key) {
        message = message.replaceAll(" ", "");
        char[] messageChars = message.toCharArray();

        //заполнение символами и проеобразование к коллекции типа лист
        List<Character> keys = key.chars().mapToObj(it -> (char) it).collect(Collectors.toList());
        //возвращает отсортированные данные
        List<Character> sortedKeys = keys.stream().sorted().collect(Collectors.toList());

        Map<Integer, char[]> columns = new HashMap<>();
        //округляет дробь в большую сторону
        int rowsCount = (int) Math.ceil(messageChars.length / (double) keys.size());

        for (int i = 0; i < keys.size(); i++) {
            char[] columnChars = new char[rowsCount];
            Arrays.fill(columnChars, ' ');//заполнение массива
            for (int j = i; j < messageChars.length; j += keys.size()) {
                columnChars[j / keys.size()] = messageChars[j];
            }
            //Найти в строке символ и его индекс
            int index = sortedKeys.indexOf(keys.get(i));
            columns.put(index, columnChars);
            sortedKeys.set(index, ' ');//добав эл в колл, если нет
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columns.size(); j++) {
                //обновление значения объекта +
                resultBuilder.append(columns.get(j)[i]);
            }
        }
        return resultBuilder.toString();
    }

    public static int[] twoProduct(int[] arr, int n) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] * arr[i] == n) return new int[]{arr[j], arr[i]};
            }
        }
        return new int[0];
    }

    public static int[] isExact(int n) {
        try {
            return new int[]{n, isExact(n, 2)};
        } catch (Exception e) {
            return new int[0];
        }
    }

    private static int isExact(float n, float divider) {
        float result = n / divider;

        if (result > 1) return isExact(result, ++divider);
        else if (result == 1) return (int) n;
        else throw new IllegalArgumentException("Первый аргумент не является факториалом!");
    }

    public static String fractions(String number) {
        int dotIndex = number.indexOf(".");//ищем индекс .
        int bracketIndex = number.indexOf("(");
        int wholePart = Integer.parseInt(number.substring(0, dotIndex));
        String unrepeatPart = number.substring(dotIndex + 1, bracketIndex);
        String repeatPart = number.substring(bracketIndex + 1, number.length() - 1);
        int firstBit = 0;
        if (unrepeatPart.length() > 0) {
            firstBit = Integer.parseInt(unrepeatPart);
        }
        int chisl = Integer.parseInt(unrepeatPart + repeatPart) - firstBit;
        String znam_str = "";
        for (int i = 0; i < repeatPart.length(); i++) {
            znam_str += "9";
        }
        for (int i = 0; i < unrepeatPart.length(); i++) {
            znam_str += "0";
        }
        int znam = Integer.parseInt(znam_str);
        int k = 2;
        int t = Math.max(chisl, znam);//максимальное число из двух
        while (k < t) {
            if (chisl % k == 0 && znam % k == 0) {
                chisl /= k;
                znam /= k;
            } else {
                k++;
            }

        }
        return Integer.toString(wholePart * znam + chisl) + "/" + Integer.toString(znam);
    }

    public static String pilish_string(String txt) {
        if (txt.isEmpty()) {
            return "";
        }

        String piDigits = "314159265358979";
        StringBuilder result = new StringBuilder();
        int index = 0;

        for (char piDigit : piDigits.toCharArray()) {
            int currentDigit = Character.getNumericValue(piDigit);
            int wordLength = currentDigit;

            if (index >= txt.length() || wordLength <= 0) {
                break;
            }

            if (index + wordLength > txt.length()) {
                int count = wordLength;
                while (count > 0) {
                    result.append(txt.charAt(Math.max(index - 1, 0)));
                    count--;
                }
            } else {
                result.append(txt.substring(index, index + wordLength));
            }

            result.append(" ");
            System.out.println(wordLength);
            index += wordLength;
        }

        return result.toString();
    }

    public static double evaluateExpression(String expression) {
        try {
            // Удаляем пробелы из выражения
            expression = expression.replaceAll("\\s+", "");

            // Создаем стеки для операндов и операторов
            Stack<Double> operandStack = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();

            // Проходим по каждому символу в выражении
            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);

                if (currentChar == '-' && (i == 0 || expression.charAt(i - 1) == '(')) {
                    // Унарный минус
                    operandStack.push(-1.0);
                    operatorStack.push('*'); // Умножаем на -1
                } else if (Character.isDigit(currentChar) || currentChar == '.') {
                    // Если текущий символ - цифра, собираем число из последовательности цифр
                    StringBuilder operand = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        operand.append(expression.charAt(i));
                        i++;
                    }
                    i--;

                    // Парсим строку в число и добавляем в стек операндов
                    operandStack.push(Double.parseDouble(operand.toString()));
                } else if (currentChar == '(') {
                    // Если текущий символ - открывающая скобка, добавляем ее в стек операторов
                    operatorStack.push(currentChar);
                } else if (currentChar == ')') {
                    // Если текущий символ - закрывающая скобка, вычисляем выражение внутри скобок
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                        performOperation(operandStack, operatorStack);
                    }
                    operatorStack.pop(); // Убираем открывающую скобку из стека операторов
                } else if (isOperator(currentChar)) {
                    // Если текущий символ - оператор, обрабатываем его
                    while (!operatorStack.isEmpty() && hasPrecedence(currentChar, operatorStack.peek())) {
                        performOperation(operandStack, operatorStack);
                    }
                    operatorStack.push(currentChar);
                } else {
                    // Некорректный символ в выражении
                    throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
                }
            }

            // Выполняем оставшиеся операции в стеке операторов
            while (!operatorStack.isEmpty()) {
                performOperation(operandStack, operatorStack);
            }

            // Результат находится на вершине стека операндов
            return operandStack.pop();
        } catch (Exception e) {
            // Обработка ошибок
            System.out.println("Error: " + e.getMessage());
            return 0; // Возвращаем 0 в случае ошибки
        }
    }

    // Метод для выполнения операции над двумя операндами
    private static void performOperation(Stack<Double> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        double result = applyOperator(operand1, operand2, operator);
        operandStack.push(result);
    }

    // Метод для применения оператора к двум операндам
    private static double applyOperator(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // Метод для определения приоритета оператора
    private static boolean hasPrecedence(char operator1, char operator2) {
        return (operator2 != '(' && operator2 != ')' && getPrecedence(operator1) <= getPrecedence(operator2));
    }

    // Метод для получения приоритета оператора
    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    // Метод для определения, является ли символ оператором
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static String isValid(String str) {
        // Создаем карту для подсчета частоты встречаемости символов
        Map<Character, Integer> charFrequency = new HashMap<>();

        // Подсчитываем частоту каждого символа в строке
        for (char c : str.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Создаем карту для подсчета частоты частот
        Map<Integer, Integer> frequencyCount = new HashMap<>();

        // Подсчитываем, сколько символов имеют одинаковую частоту
        for (int frequency : charFrequency.values()) {
            frequencyCount.put(frequency, frequencyCount.getOrDefault(frequency, 0) + 1);
        }

        // Если у нас есть более одной различной частоты, строка недействительна
        if (frequencyCount.size() > 2) {
            return "NO";
        }

        // Если у нас есть две различные частоты, проверяем, можно ли сделать строку действительной
        if (frequencyCount.size() == 2) {
            int frequency1 = 0, frequency2 = 0;
            for (int frequency : frequencyCount.keySet()) {
                if (frequency1 == 0) {
                    frequency1 = frequency;
                } else {
                    frequency2 = frequency;
                }
            }

            // Проверяем, можно ли сделать строку действительной
            if ((frequency1 == 1 && frequencyCount.get(frequency1) == 1) ||
                    (frequency2 == 1 && frequencyCount.get(frequency2) == 1) ||
                    (Math.abs(frequency1 - frequency2) == 1 && (frequencyCount.get(frequency1) == 1 || frequencyCount.get(frequency2) == 1))) {
                return "YES";
            } else {
                return "NO";
            }
        }

        // Если у нас есть только одна частота, строка уже действительна
        return "YES";
    }

    public static String findLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Создаем двумерный массив для хранения длин наибольших общих подпоследовательностей
        int[][] lcsLengths = new int[m + 1][n + 1];

        // Заполняем массив с использованием алгоритма динамического программирования
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    lcsLengths[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    lcsLengths[i][j] = lcsLengths[i - 1][j - 1] + 1;
                } else {
                    lcsLengths[i][j] = Math.max(lcsLengths[i - 1][j], lcsLengths[i][j - 1]);
                }
            }
        }

        // Восстанавливаем наибольшую общую подпоследовательность
        int length = lcsLengths[m][n];
        char[] lcsChars = new char[length];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcsChars[--length] = str1.charAt(i - 1);
                i--;
                j--;
            } else if (lcsLengths[i - 1][j] > lcsLengths[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Преобразуем массив символов в строку
        return new String(lcsChars);
    }
}



