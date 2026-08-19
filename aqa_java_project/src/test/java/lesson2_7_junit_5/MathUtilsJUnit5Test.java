package lesson2_7_junit_5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsJUnit5Test {
    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    // 1. Факториал
    @Test
    @DisplayName("Факториал 0 → 1 (граничное значение)")
    void factorial_zero_returnsOne() {
        assertEquals(1, mathUtils.factorial(0));
    }

    @Test
    @DisplayName("Факториал 1 → 1 (граничное значение)")
    void factorial_one_returnsOne() {
        assertEquals(1, mathUtils.factorial(1));
    }

    @Test
    @DisplayName("Факториал валидных чисел (2–20) → валидный результат")
    void factorial_validRange_returnsExpected() {
        assertEquals(120, mathUtils.factorial(5));
    }

    @Test
    @DisplayName("Факториал 20 → максимальное валидное значение без переполнения (граничное значение)")
    void factorial_twenty_returnsMaxCorrectValue() {
        assertEquals(2432902008176640000L, mathUtils.factorial(20));
    }

    @Test
    @DisplayName("Факториал -1 → исключение (граничное значение)")
    void factorial_minusOne_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.factorial(-1));
    }

    @Test
    @DisplayName("Факториал 21 → переполнение, результат отрицательный (граничное значение)")
    void factorial_twentyOne_overflowsLong() {
        long result = mathUtils.factorial(21);
        assertTrue(result < 0, "21! должен дать отрицательное значение из-за переполнения");
    }

    // 2. Площадь треугольника
    @ParameterizedTest
    @CsvSource({
            "3, 4, 5, 6.0",
            "6, 8, 10, 24.0",
            "5, 5, 5, 10.8253175473",
            "2, 3, 4, 2.9047375097"
    })
    @DisplayName("Площадь треугольника: валидные стороны (разносторонние, равносторонние)")
    void triangleArea_validSides_returnsArea(double a, double b, double c, double expected) {
        assertEquals(expected, mathUtils.triangleArea(a, b, c), 0.0001);
    }

    @Test
    @DisplayName("Площадь треугольника: минимальный положительный треугольник (1,1,1)")
    void triangleArea_minimalPositive_returnsArea() {
        double area = mathUtils.triangleArea(1, 1, 1);
        assertEquals(0.4330127019, area, 0.0001);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 4, 5",
            "-1, 2, 3",
            "1, 0, 1",
            "1, -1, 1"
    })
    @DisplayName("Площадь треугольника: сторона <= 0 → IllegalArgumentException")
    void triangleArea_nonPositiveSide_throwsException(double a, double b, double c) {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.triangleArea(a, b, c));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 3",
            "2, 3, 6",
            "5, 5, 10",
            "1, 2, 3"
    })
    @DisplayName("Площадь треугольника: сумма двух сторон ≤ третьей → IllegalArgumentException")
    void triangleArea_invalidTriangle_throwsException(double a, double b, double c) {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.triangleArea(a, b, c));
    }

    @Test
    @DisplayName("Площадь треугольника: очень большие стороны (без переполнения)")
    void triangleArea_largeSides_works() {
        double area = mathUtils.triangleArea(1e9, 1e9, 1e9);
        assertTrue(area > 0);
    }

    // 3. Арифметические действия
    // 3.1 Сложение
    @ParameterizedTest
    @CsvSource({
            "5, 3, 8",
            "-5, -3, -8",
            "5, -3, 2",
            "0, 10, 10",
            "10, 0, 10",
            "0, 0, 0",
            "1, -1, 0",
            "-1, 1, 0"
    })
    @DisplayName("Сложение: валидные комбинации (положительные, отрицательные, ноль)")
    void add_validCombinations_returnsSum(int a, int b, int expected) {
        assertEquals(expected, mathUtils.add(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "2147483647, 1, -2147483648",   // переполнение MAX + 1
            "-2147483648, -1, 2147483647"   // переполнение MIN - 1
    })
    @DisplayName("Сложение: переполнение int (граничные значения)")
    void add_overflow_returnsOverflowedResult(int a, int b, int expected) {
        assertEquals(expected, mathUtils.add(a, b));
    }

    // 3.2 Вычитание
    @ParameterizedTest
    @CsvSource({
            "10, 3, 7",
            "3, 10, -7",
            "-5, -3, -2",
            "5, -3, 8",
            "0, 10, -10",
            "10, 0, 10",
            "0, 0, 0"
    })
    @DisplayName("Вычитание: валидные комбинации")
    void subtract_validCombinations_returnsDifference(int a, int b, int expected) {
        assertEquals(expected, mathUtils.subtract(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "-2147483648, 1, 2147483647",   // MIN - 1 → переполнение в положительную
            "2147483647, -1, -2147483648"   // MAX + 1 → переполнение в отрицательную
    })
    @DisplayName("Вычитание: переполнение int (граничные значения)")
    void subtract_overflow_returnsOverflowedResult(int a, int b, int expected) {
        assertEquals(expected, mathUtils.subtract(a, b));
    }

    // 3.3 Умножение
    @ParameterizedTest
    @CsvSource({
            "5, 3, 15",
            "-5, -3, 15",
            "5, -3, -15",
            "0, 10, 0",
            "10, 0, 0",
            "1, 100, 100",
            "-1, 100, -100"
    })
    @DisplayName("Умножение: валидные комбинации")
    void multiply_validCombinations_returnsProduct(int a, int b, int expected) {
        assertEquals(expected, mathUtils.multiply(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "2147483647, 2, -2",       // переполнение MAX * 2
            "-2147483648, 2, 0",       // MIN * 2 → переполнение
            "2147483647, -1, -2147483647" // без переполнения
            //"-2147483648, -1, 2147483648"  // переполнение, т.к. MIN * -1 = MAX+1
    })
    @DisplayName("Умножение: переполнение int (граничные значения)")
    void multiply_overflow_returnsOverflowedResult(int a, int b, int expected) {
        assertEquals(expected, mathUtils.multiply(a, b));
    }

    // 3.4 Деление
    @ParameterizedTest
    @CsvSource({
            "10, 2, 5.0",
            "7, 2, 3.5",
            "-9, 3, -3.0",
            "9, -3, -3.0",
            "0, 5, 0.0",
            "5, 1, 5.0",
            "5, -1, -5.0",
            "1, 3, 0.3333333333"
    })
    @DisplayName("Деление: валидные комбинации (включая деление на 1, -1, с остатком)")
    void divide_validCombinations_returnsDouble(int a, int b, double expected) {
        assertEquals(expected, mathUtils.divide(a, b), 0.0001);
    }

    @Test
    @DisplayName("Деление на ноль → ArithmeticException")
    void divide_byZero_throwsException() {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(10, 0));
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(-5, 0));
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(0, 0));
    }

    @Test
    @DisplayName("Деление: граничные значения (деление на Integer.MIN_VALUE и Integer.MAX_VALUE)")
    void divide_boundaryValues_works() {
        assertEquals(1.0, mathUtils.divide(Integer.MIN_VALUE, Integer.MIN_VALUE), 0.0001);
        assertEquals(1.0, mathUtils.divide(Integer.MAX_VALUE, Integer.MAX_VALUE), 0.0001);
        assertEquals(-1.0, mathUtils.divide(Integer.MAX_VALUE, Integer.MIN_VALUE), 0.0001);
        assertEquals(-1.0, mathUtils.divide(Integer.MIN_VALUE, Integer.MAX_VALUE), 0.0001);
    }

    // 4. Сравнение
    @ParameterizedTest
    @CsvSource({
            "3, 5",
            "-5, -3",
            "0, 10",
            "-1, 1",
            "-2147483648, 2147483647"
    })
    @DisplayName("a < b → отрицательный результат")
    void compare_aLessB_returnsNegative(int a, int b) {
        assertTrue(mathUtils.compare(a, b) < 0);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5",
            "-5, -5",
            "0, 0",
            "2147483647, 2147483647",
            "-2147483648, -2147483648"
    })
    @DisplayName("a == b → 0 ")
    void compare_aEqualsB_returnsZero(int a, int b) {
        assertEquals(0, mathUtils.compare(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3",
            "-3, -5",
            "10, 0",
            "1, -1",
            "2147483647, -2147483648"
    })
    @DisplayName("a > b → положительный результат")
    void compare_aGreaterB_returnsPositive(int a, int b) {
        assertTrue(mathUtils.compare(a, b) > 0);
    }

    @Test
    @DisplayName("a = 0, b = 0 (граничное значение)")
    void compare_zeroAndZero_returnsZero() {
        assertEquals(0, mathUtils.compare(0, 0));
    }

    @Test
    @DisplayName("a = -1, b = 0 → a < b (граничное значение)")
    void compare_minusOneAndZero_returnsNegative() {
        assertTrue(mathUtils.compare(-1, 0) < 0);
    }

    @Test
    @DisplayName("a = 0, b = 1 → a < b (граничное значение)")
    void compare_zeroAndOne_returnsNegative() {
        assertTrue(mathUtils.compare(0, 1) < 0);
    }
}
