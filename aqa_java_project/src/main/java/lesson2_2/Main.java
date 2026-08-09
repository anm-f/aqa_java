package lesson2_2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("=== Задание 1 ===");
        printThreeWords();

        System.out.println("\n=== Задание 2 ===");
        checkSumSign();

        System.out.println("\n=== Задание 3 ===");
        printColor();

        System.out.println("\n=== Задание 4 ===");
        compareNumbers();

        System.out.println("\n=== Задание 5 ===");
        int firstNumber = 5;
        int secondNumber = 10;
        System.out.println("Сумма чисел " + firstNumber + " и " + secondNumber + " лежит в пределах от 10 до 20 (включительно): " + sumInRange(firstNumber, secondNumber));

        System.out.println("\n=== Задание 6 ===");
        checkPositiveNegative(1);
        checkPositiveNegative(-1);
        checkPositiveNegative(0);

        System.out.println("\n=== Задание 7 ===");
        int number = 1;
        System.out.println("Число " + number + " негативное?: " + isNegative(number));
        number = -1;
        System.out.println("Число " + number + " негативное?: " + isNegative(number));
        number = 0;
        System.out.println("Число " + number + " негативное?: " + isNegative(number));

        System.out.println("\n=== Задание 8 ===");
        printStringMultipleTimes("Hello", 3);

        System.out.println("\n=== Задание 9 ===");
        System.out.println("2024 високосный? → " + isLeapYear(2024));
        System.out.println("1900 високосный? → " + isLeapYear(1900));
        System.out.println("2000 високосный? → " + isLeapYear(2000));

        System.out.println("\n=== Задание 10 ===");
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertBinaryArray(binaryArray);
        System.out.println(arrayToString(binaryArray));

        System.out.println("\n=== Задание 11 ===");
        int[] sequentialArray = createSequentialArray();
        System.out.println("Массив 1..100: " + arrayToString(sequentialArray));

        System.out.println("\n=== Задание 12 ===");
        int[] multiplyArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLessThanSix(multiplyArray);
        System.out.println(arrayToString(multiplyArray));

        System.out.println("\n=== Задание 13 ===");
        int[][] diagonalMatrix = createDiagonalMatrix(5);
        printMatrix(diagonalMatrix);

        System.out.println("\n=== Задание 14 ===");
        int len = 10;
        int initialValue = 10;
        int[] filledArray = createFilledArray(len, initialValue);
        System.out.println(arrayToString(filledArray));
    }

    // 1.
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2.
    public static void checkSumSign() {
        int a = 15;
        int b = -100;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3.
    public static void printColor() {
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4.
    public static void compareNumbers() {
        int a = 25;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5.
    public static boolean sumInRange(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }

    // 6.
    public static void checkPositiveNegative(int number) {
        if (number >= 0) {
            System.out.println(number + " — положительное");
        } else {
            System.out.println(number + " — отрицательное");
        }
    }

    // 7.
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8.
    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // 9.
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // 10.
    public static void invertBinaryArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
    }

    // 11.
    public static int[] createSequentialArray() {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // 12.
    public static void multiplyLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 13.
    public static int[][] createDiagonalMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    // 14.
    public static int[] createFilledArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }

    // Вспомогательный метод для вывода массива в строку
    public static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Вспомогательный метод для печати двумерного массива
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
