package lesson2_7_testng;

public class MathUtils {
    // 1. Факториал числа
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определён только для неотрицательных чисел");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 2. Площадь треугольника по трём сторонам (формула Герона)
    public double triangleArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Треугольник с такими сторонами не существует");
        }
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    // 3. Арифметические операции
    public int add(int a, int b) { return a + b; }

    public int subtract(int a, int b) { return a - b; }

    public int multiply(int a, int b) { return a * b; }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль запрещено");
        }
        return (double) a / b;
    }

    // 4. Сравнение двух целых чисел
    public int compare(int a, int b) {
        return Integer.compare(a, b); // возвращает -1, 0, 1
    }
}
