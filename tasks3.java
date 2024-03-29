import java.util.ArrayList;
import java.util.Arrays;

public class tasks3 {
    public static void main(String[] args) {
        String replaceVowels = replaceVowels("apple");
        System.out.println(replaceVowels);
        String stringTransform = stringTransform("bookkeeper");
        System.out.println(stringTransform);
        boolean doesBlockFit = doesBlockFit(1, 3, 5, 4, 5);
        System.out.println(doesBlockFit);
        boolean numCheck = numCheck(243);
        System.out.println(numCheck);
        int countRoots = countRoots(new int[] {1, -3, 2});
        System.out.println(countRoots);
        String[][] data = {
                {"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}
        };
        String[] result = salesData(data);
        System.out.println(Arrays.toString(result));
        System.out.println(validSplit("cat dog goose fish"));
        System.out.println(waveForm(new int[]{3, 1, 4, 2, 7, 5}));
        System.out.println(commonVowel("Actions speak louder than words."));
        int[][] matrix1 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {5, 5, 5, 5, 5},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}
        };

        int[][] result1 = processData(matrix1);
        for (int[] row : result1) {
            System.out.println(Arrays.toString(row));
        }

    }
    public static String replaceVowels(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == 'a' || currentChar == 'e' || currentChar == 'i' || currentChar == 'o' || currentChar == 'u') {
                result.append('*');
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static String stringTransform(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (i + 1 < input.length()) {
                char nextChar = input.charAt(i + 1);

                if (currentChar == nextChar) {
                    result.append("Double");
                    char upperChar = Character.toUpperCase(currentChar);
                    result.append(upperChar);
                    i += 1;
                }
                else {
                    result.append(currentChar);
                }
            }
            else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        if (((a * b) <= (w * h)) || ((a * c) <= (w * h)) || ((b * c) <= (w * h))) {
            return true;
        }

        return false;
    }

    public static boolean numCheck(int number) {
        int sumOfSquares = 0;
        int num = Math.abs(number);

        while (num > 0) {
            int digit = num % 10;
            sumOfSquares += digit * digit;
            num /= 10;
        }

        return (number % 2 == 0 && sumOfSquares % 2 == 0) || (number % 2 != 0 && sumOfSquares % 2 != 0);
    }

    public static int countRoots(int[] coefficients) {
        if (coefficients.length != 3) {
            System.out.println("Массив коэффицентов должен содержать 3 элемента");
        }

        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        int discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            return 2;
        } else if (discriminant == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static String[] salesData(String[][] data) {
        ArrayList<String> shops = new ArrayList<String>();
        int max = 0;
        ArrayList<String> products = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            String[] row = data[i];

            for (int j = 1; j < row.length; j++) {
                shops.add(row[j]);
            }
            if (shops.size() >= max) {
                max = shops.size();
            }
            shops = new ArrayList<String>();

        }
        for (int i = 0; i < data.length; i++) {
            if ((data[i].length - 1) == max) {
                products.add(data[i][0]);
            }
        }

        String[] productsArray = new String[products.size()];
        productsArray = products.toArray(productsArray);

        return productsArray;
    }

    public static boolean validSplit(String sentence) {
        String[] words = sentence.split(" ");

        if (words.length <= 1) {
            return false;
        }

        for (int i = 1; i < words.length; i++) {
            String prevWord = words[i - 1];
            String currentWord = words[i];

            if (prevWord.charAt(prevWord.length() - 1) != currentWord.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean waveForm(int[] arr) {
        boolean increasing = arr[0] < arr[1];
        for (int i = 1; i < arr.length - 1; i++) {
            if ((increasing && arr[i] <= arr[i + 1]) || (!increasing && arr[i] >= arr[i + 1])) {
                return false;
            }
            increasing = !increasing;
        }

        return true;
    }

    public static char commonVowel(String sentence) {
        String vowels = "aeiou";
        int[] count = new int[5];

        sentence = sentence.toLowerCase();
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (vowels.indexOf(ch) != -1) {
                int index = vowels.indexOf(ch);
                count[index]++;
            }
        }

        int maxCount = 0;
        char mostCommonVowel = ' ';

        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                mostCommonVowel = vowels.charAt(i);
            }
        }

        return mostCommonVowel;
    }
    public static int[][] processData(int[][] arrays) {
        int n = arrays.length;
        int m = arrays[0].length;

        // Создаем массив, чтобы хранить суммы элементов в каждом столбце, кроме текущего
        int[] columnSums = new int[m];

        // Вычисляем суммы элементов в каждом столбце, кроме текущего
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (i != j) {
                    columnSums[j] += arrays[i][j];
                }
            }
        }

        // Заменяем элементы в каждом массиве на среднее арифметическое элементов соответствующего столбца
        for (int i = 0; i < n; i++) {
            arrays[i][i] = Math.round(columnSums[i] / (n - 1));
            System.out.println(columnSums[i]);
        }

        return arrays;
    }
}