package lesson2_5;

public class Main {
    public static void main(String[] args) {
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "invalid value"}
        };
        String[][] wrongSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        ArrayProcessor processor = new ArrayProcessor();
        // Массив с валидными данными и размером
        try {
            int result = processor.sumArray(validArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Массив с невалидными данными
        try {
            int result = processor.sumArray(invalidDataArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Массив с невалидным размером
        try {
            int result = processor.sumArray(wrongSizeArray);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // 4. ArrayIndexOutOfBoundsException
        try {
            int[] array = new int[3];
            int element = array[5];  // индекс 5 за пределами массива длиной 3
            System.out.println(element);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Поймано ArrayIndexOutOfBoundsException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
