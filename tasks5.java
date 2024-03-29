import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class tasks5 {
    public static void main(String[] args) {
        System.out.println("1.");
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println("2.");
        System.out.println(digitsCount(1289396387328L));
        System.out.println("3.");
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println("4.");
        System.out.println(sumsUp(new int[]{1, 2, 6, 7, 9}));
        System.out.println("5.");
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println("6.");
        System.out.println(caesarCipher(new String[]{"encode", "hello world"}, 3)); // " KHOOR ZRUOG"
        System.out.println(caesarCipher(new String[]{"decode", "almost last task!"}, 4));
        System.out.println("7.");
        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));
        System.out.println("8.");
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println("9.");
        System.out.println(isNew(45));
        System.out.println("10.");
        System.out.println(spiderVsFly("A3", "C2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));
    }

    public static boolean sameLetterPattern(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);

            if (map1.containsKey(char1)) {
                if (map1.get(char1) != char2) {
                    return false;
                }
            } else {
                map1.put(char1, char2);
            }

            if (map2.containsKey(char2)) {
                if (map2.get(char2) != char1) {
                    return false;
                }
            } else {
                map2.put(char2, char1);
            }
        }

        return true;
    }

    public static int digitsCount(long number) {
        if (number < 10) {
            return 1;
        } else {
            return 1 + digitsCount(number / 10);
        }
    }

    public static int totalPoints(String[] guessedWords, String scrambledWord) {
        int totalPoints = 0;

        for (String word : guessedWords) {
            if (isValidWord(word, scrambledWord)) {
                totalPoints += calculatePoints(word);
            }
        }

        return totalPoints;
    }

    // Проверка, является ли слово допустимым (может быть сформировано из scrambledWord)
    private static boolean isValidWord(String word, String scrambledWord) {
        int[] scrambledFreq = new int[26];

        for (char ch : scrambledWord.toCharArray()) {
            scrambledFreq[ch - 'a']++;
        }

        for (char ch : word.toCharArray()) {
            if (scrambledFreq[ch - 'a'] == 0) {
                return false; // Символ отсутствует в scrambledWord
            }
            scrambledFreq[ch - 'a']--;
        }

        return true;
    }

    // Расчет очков в соответствии с правилами игры
    private static int calculatePoints(String word) {
        int length = word.length();
        if (length == 3) {
            return 1;
        } else if (length == 4) {
            return 2;
        } else if (length == 5) {
            return 3;
        } else if (length == 6) {
            return 54; // 4 очка + 50 бонусных очков
        } else {
            return 0; // Недопустимая длина слова
        }
    }

    public static List<List<Integer>> sumsUp(int[] array) {
        List<List<Integer>> result = new ArrayList<>();

        // Перебираем все пары чисел в массиве
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                // Проверяем сумму пары
                if (array[i] + array[j] == 8) {
                    // Добавляем пару в результат
                    List<Integer> pair = Arrays.asList(array[i], array[j]);
                    result.add(pair);
                }
            }
        }

        // Сортируем результат по возрастанию разницы между числами в парах
        result.sort((pair1, pair2) -> {
            int diff1 = Math.abs(pair1.get(0) - pair1.get(1));
            int diff2 = Math.abs(pair2.get(0) - pair2.get(1));
            return Integer.compare(diff1, diff2);
        });

        return result;
    }

    public static String takeDownAverage(String[] classmatesGrades) {
        float sum = 0;

        for (String grade : classmatesGrades) {
            sum += Integer.parseInt(grade.substring(0, grade.length() - 1));
        }

        float averageBeforeReduction = sum / classmatesGrades.length;

        float averageAfterReduction = averageBeforeReduction - 5;

        float yourGrade = (averageAfterReduction * (classmatesGrades.length + 1)) - sum;

        return Math.round(yourGrade) + "%";
    }

    public static String caesarCipher(String[] operationAndMessage, int shift) {
        // Режим работы: "encode" или "decode"
        String operation = operationAndMessage[0].toLowerCase();
        String message = operationAndMessage[1];

        StringBuilder result = new StringBuilder();

        for (char ch : message.toCharArray()) {
            if (Character.isLetter(ch)) {
                char shiftedChar = shiftCharacter(ch, shift, operation.equals("decode"));
                result.append(shiftedChar);
            } else {
                // Оставляем другие символы без изменений
                result.append(ch);
            }
        }

        return result.toString().toUpperCase();
    }

    private static char shiftCharacter(char ch, int shift, boolean decode) {
        char base = Character.isUpperCase(ch) ? 'A' : 'a';
        int shifted = decode ? ch - base - shift : ch - base + shift;
        int result = (shifted + 26) % 26;
        return (char) (result + base);
    }

    // Метод для рекурсивного вычисления размещений без повторений
    public static int setSetup(int n, int k) {
        // Проверка условия n >= k
        if (n < k) {
            throw new IllegalArgumentException("n должно быть больше или равно k");
        }

        // Базовый случай: если k равно 0, количество размещений равно 1
        if (k == 0) {
            return 1;
        }

        // Рекурсивный случай: размещение n элементов по k элементов равно
        // произведению n и результату размещения (n-1) элементов по (k-1) элементов
        return n * setSetup(n - 1, k - 1);
    }

    public static String timeDifference(String cityA, String timestampA, String cityB) {
        // Создаем карту смещений для городов
        Map<String, Integer> cityOffsets = new HashMap<>();
        cityOffsets.put("Los Angeles", -8);
        cityOffsets.put("New York", -5);
        cityOffsets.put("Caracas", -4);
        cityOffsets.put("Buenos Aires", -3);
        cityOffsets.put("London", 0);
        cityOffsets.put("Rome", 1);
        cityOffsets.put("Moscow", 3);
        cityOffsets.put("Tehran", 3);
        cityOffsets.put("New Delhi", 5);
        cityOffsets.put("Beijing", 8);
        cityOffsets.put("Canberra", 10);

        // Парсим входные строки в формат даты и времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        LocalDateTime dateTimeA = LocalDateTime.parse(timestampA, formatter);

        // Получаем смещения для городов
        int offsetA = cityOffsets.get(cityA);
        int offsetB = cityOffsets.get(cityB);

        // Вычисляем разницу в смещениях
        int hourOffset = offsetB - offsetA;

        // Применяем разницу к исходному времени
        LocalDateTime dateTimeB = dateTimeA.plusHours(hourOffset);

        // Форматируем результат
        DateTimeFormatter resultFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
        return dateTimeB.format(resultFormatter);
    }

    public static boolean isNew(int number) {
        if (number < 21) return true;
        String numberStr = String.valueOf(number);
        for (int i = 0; i < numberStr.length() - 1; i++) {
            char currentChar = numberStr.charAt(i);
            char nextChar = numberStr.charAt(i + 1);
            if ((currentChar <= nextChar && currentChar != '0') || (i == 0 && nextChar == '0') || (i == 1 && numberStr.charAt(i-1) == currentChar && nextChar == '0') ||
                    (i == 1 && numberStr.charAt(i-1) < currentChar && nextChar == '0') || (i == 1 && currentChar == '0' && nextChar == '0') || (i == 1 && currentChar == '0' && numberStr.charAt(i-1) < nextChar) ||
                    (i == 1 && currentChar == '0' && numberStr.charAt(i-1) == nextChar)) continue;
            return false;
        }
        return true;
    }

    // 2
    public static String spiderVsFly(String A, String B) {
        System.out.println("From "+A+" to "+B+":");
        int AR = A.charAt(0) - 'A' + 1;
        System.out.println(AR);
        int AD = A.charAt(1) - '0';
        System.out.println(AD);
        int BR = B.charAt(0) - 'A' + 1;
        System.out.println(BR);
        int BD = B.charAt(1) - '0';
        System.out.println(BD);

        List<String> path = new ArrayList<>();
        while (AD > BD) {
            path.add("" + (char) (AR + 'A' - 1) + AD);
            AD--;
        }
        List<String> pathEnd = new ArrayList<>();
        while (AD < BD) {
            pathEnd.add("" + (char) (BR + 'A' - 1) + BD);
            BD--;
        }

        int d = Math.abs(AR - BR); // расстояние от A до B
        if (d > 4)
            d = 8 - d;
        if (d <= 2) { // по дуге
            path.add("" + (char) (AR + 'A' - 1) + AD);
            while (AR != BR) {
                if (Math.abs(AR - BR) > 4) {
                    AR--;
                    if (AR == 0)
                        AR = 8;
                } else {
                    AR++;
                    if (AR == 9)
                        AR = 1;
                }
                path.add("" + (char) (AR + 'A' - 1) + AD);
            }
        } else { // через центр
            while (AD != 0) {
                path.add("" + (char) (AR + 'A' - 1) + AD);
                AD--;
            }
            path.add("" + (char) ('A') + AD);
            while (AD != BD) {
                AD++;
                path.add("" + (char) (BR + 'A' - 1) + AD);
            }
        }

        // Собираем путь в строку
        StringBuilder result = new StringBuilder();
        for (String step : path) {
            result.append(step);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        for (int i = pathEnd.size() - 1; i >= 0; i--) {
            result.append("-");
            result.append(pathEnd.get(i));
        }
        return result.toString();
    }

}

