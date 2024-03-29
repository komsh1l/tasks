public class tasks1 {
    public static void main(String[] args) {
        double liters = convertGallonsToLiters(5);
        System.out.println("Результат: " + liters + " литров");
        System.out.println("Введите время тренировки в минутах:");
        int burnedCalories = fitCalc(24, 2);
        System.out.println("Сожжено калорий: " + burnedCalories + " ккал");
        int totalItems = containers(4,1, 4);
        System.out.println("Общее количество товаров на складе: " + totalItems + " шт.");
        String triangleType = triangleType(5, 4, 5);
        System.out.println("Тип треугольника: " + triangleType);
        int maxNumber = ternaryEvaluation(8, 4);
        System.out.println("Большее число: " + maxNumber);
        int items = howManyItems(45, 1.8, 1.9);
        System.out.println("Количество пододеяльников: " + items);
        long factorialResult = factorial(5);
        System.out.println("Факториал числа равен: " + factorialResult);
        int gcdResult = gcd(48, 18);
        System.out.println("Наибольший общий делитель: " + gcdResult);
        double revenue = ticketSaler(24, 950);
        System.out.println("Общая выручка от продажи билетов: " + revenue);
        int desksNeeded = tables(123, 58);
        System.out.println("Количество нехватающих столов: " + desksNeeded);
    }

    public static double convertGallonsToLiters(double gallons) {
        return gallons * 3.78541;
    }

    public static int fitCalc(int minutes, int intensity) {
        return minutes * intensity;
    }

    public static int containers(int boxes, int bags, int barrels) {
        return (boxes * 20) + (bags * 50) + (barrels * 100);
    }

    public static String triangleType(double x, double y, double z) {
        if (x <= 0 || y <= 0 || z <= 0 || x + y <= z || x + z <= y || y + z <= x) {
            return "not a triangle";
        } else if (x == y && y == z) {
            return "equilateral";
        } else if (x == y || y == z || x == z) {
            return "isosceles";
        } else {
            return "different-sided";
        }
    }

    public static int ternaryEvaluation(int a, int b) {
        return a > b ? a : b;
    }

    public static int howManyItems(double fabricLength, double fabricWidthOnePiece, double fabricLengthOnePiece) {
        double availableArea = fabricLength * 2;
        double itemArea = fabricLengthOnePiece * fabricWidthOnePiece;
        int itemCount = (int) (availableArea / itemArea);
        return itemCount;
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static double ticketSaler(int numTickets, double ticketPrice) {
        double commission = 0.28 * ticketPrice;
        double revenue = numTickets * (ticketPrice - commission);
        return revenue;
    }

    public static int tables(int numStudents, int numDesks) {
        int desksNeeded = (numStudents + 1) / 2 - numDesks;
        return desksNeeded < 0 ? 0 : desksNeeded;
    }
}