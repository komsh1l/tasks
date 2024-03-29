import java.util.Arrays;
import java.security.SecureRandom;

public class tasks2 {
    public static void main(String[] args) {
        boolean hasDuplicates = duplicateChars("donald");
        System.out.println("Есть ли повторяющиеся символы: " + hasDuplicates);
        String initials = getInitials("Ryan Gosling");
        System.out.println("Инициалы: " + initials);
        int result1 = differenceEvenOdd(new int[] {44, 32, 86, 19});
        System.out.println("Разница между суммами четных и нечетных чисел: " + result1);
        boolean isEqual = equalToAvg(new int[]{1,2,3,4,5});
        System.out.println("Есть ли здесь элемент равный среднему арифмитическому всех элементов: " + isEqual);
        String multiplied = indexMult(new int[]{3, 3, -2, 408, 3, 31});
        System.out.println("Multiplied by index: " + multiplied);
        String reversedString = reverse("Hello World");
        System.out.println("Обратная строка: " + reversedString);
        int tribonacciNumber = tribonacci(11);
        System.out.println("Число Трибоначчи под указанным номером равно: " + tribonacciNumber);
        String pseudoHashResult = pseudoHash(5);
        System.out.println("Сгенерированный квази-хэш: " + pseudoHashResult);
        System.out.println(botHelper("Hello, I’m under the water, please help me"));
        boolean areAnagrams = isAnagram("eleven plus two", "twelve plus one");
        if (areAnagrams) {
            System.out.println("Эти строки являются анаграммами.");
        } else {
            System.out.println("Эти строки не являются анаграммами.");
        }
    }

    public static boolean duplicateChars(String inputStr) {
        for (int i = 0; i < inputStr.length(); i++) {
            for (int j = i + 1; j < inputStr.length(); j++) {
                if (inputStr.charAt(i) == inputStr.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getInitials(String fullName) {
        StringBuilder initials = new StringBuilder();
        String[] words = fullName.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                initials.append(word.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }

    public static int differenceEvenOdd(int[] numbers) {
        int evenSum = 0;
        int oddSum = 0;

        for (int num : numbers) {
            if (num % 2 == 0) {
                evenSum += num;
            } else {
                oddSum += num;
            }
        }

        return Math.abs(evenSum - oddSum);
    }

    public static boolean equalToAvg(int[] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double avg = (double) sum / numbers.length;
        for (int num : numbers) {
            if (num == avg) {
                return true;
            }
        }
        return false;
    }

    public static String indexMult(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] * i;
        }
        return Arrays.toString(numbers);
    }

    public static String reverse(String inputStr) {
        StringBuilder reversed = new StringBuilder();
        for (int i = inputStr.length() - 1; i >= 0; i--) {
            reversed.append(inputStr.charAt(i));
        }
        return reversed.toString();
    }

    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        int[] tribonacciSeq = new int[n + 1];
        tribonacciSeq[0] = 0;
        tribonacciSeq[1] = 0;
        tribonacciSeq[2] = 1;

        for (int i = 3; i <= n; i++) {
            tribonacciSeq[i] = tribonacciSeq[i - 1] + tribonacciSeq[i - 2] + tribonacciSeq[i - 3];
        }
        return tribonacciSeq[n-1];
    }
    public static String pseudoHash(int length) {
        if (length <= 0) {
            return "";
        }
        SecureRandom random = new SecureRandom();
        StringBuilder pseudoHash = new StringBuilder();

        String characters = "0123456789abcdef";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            pseudoHash.append(randomChar);
        }
        return pseudoHash.toString();
    }
    public static String botHelper(String input) {
        if (input.toLowerCase().contains("help")) {
            return "Calling for a staff member";
        } else {
            return "Keep waiting";
        }
    }

    public static boolean isAnagram(String str1, String str2) {
        char[] chars1 = str1.toLowerCase().toCharArray();
        char[] chars2 = str2.toLowerCase().toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }
}
