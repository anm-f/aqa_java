package lesson2_7_testng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MathUtilsTestNGTest {

    private MathUtils mathUtils;

    @BeforeMethod
    void setUp() {
        mathUtils = new MathUtils();
    }

    // 1. Факториал
    @Test
    public void factorial_zero_returnsOne() {
        assertEquals(mathUtils.factorial(0), 1);
    }

    @Test
    public void factorial_one_returnsOne() {
        assertEquals(mathUtils.factorial(1), 1);
    }

    @Test
    public void factorial_validRange_returnsExpected() {
        assertEquals(mathUtils.factorial(5), 120);
    }

    @Test
    public void factorial_twenty_returnsMaxCorrectValue() {
        assertEquals(mathUtils.factorial(20), 2432902008176640000L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void factorial_minusOne_throwsException() {
        mathUtils.factorial(-1);
    }

    @Test
    public void factorial_twentyOne_overflowsLong() {
        long result = mathUtils.factorial(21);
        assertTrue(result < 0, "21! должен дать отрицательное значение из-за переполнения");
    }

    // 2. Площадь треугольника
    @Test(dataProvider = "triangleValidData")
    public void triangleArea_validSides_returnsArea(double a, double b, double c, double expected) {
        assertEquals(mathUtils.triangleArea(a, b, c), expected, 0.0001);
    }

    @DataProvider(name = "triangleValidData")
    public Object[][] getTriangleValidData() {
        return new Object[][]{
                {3, 4, 5, 6.0},
                {6, 8, 10, 24.0},
                {5, 5, 5, 10.8253175473},
                {2, 3, 4, 2.9047375097}
        };
    }

    @Test
    public void triangleArea_minimalPositive_returnsArea() {
        double area = mathUtils.triangleArea(1, 1, 1);
        assertEquals(area, 0.4330127019, 0.0001);
    }

    @Test(dataProvider = "nonPositiveSides", expectedExceptions = IllegalArgumentException.class)
    public void triangleArea_nonPositiveSide_throwsException(double a, double b, double c) {
        mathUtils.triangleArea(a, b, c);
    }

    @DataProvider(name = "nonPositiveSides")
    public Object[][] getNonPositiveSides() {
        return new Object[][]{
                {0, 4, 5},
                {-1, 2, 3},
                {1, 0, 1},
                {1, -1, 1}
        };
    }

    @Test(dataProvider = "invalidTriangleSides", expectedExceptions = IllegalArgumentException.class)
    public void triangleArea_invalidTriangle_throwsException(double a, double b, double c) {
        mathUtils.triangleArea(a, b, c);
    }

    @DataProvider(name = "invalidTriangleSides")
    public Object[][] getInvalidTriangleSides() {
        return new Object[][]{
                {1, 1, 3},
                {2, 3, 6},
                {5, 5, 10},
                {1, 2, 3}
        };
    }

    @Test
    public void triangleArea_largeSides_works() {
        double area = mathUtils.triangleArea(1e9, 1e9, 1e9);
        assertTrue(area > 0);
    }

    // 3. Арифметические действия
    // 3.1 Сложение
    @Test(dataProvider = "addData")
    public void add_validCombinations_returnsSum(int a, int b, int expected) {
        assertEquals(mathUtils.add(a, b), expected);
    }

    @DataProvider(name = "addData")
    public Object[][] getAddData() {
        return new Object[][]{
                {5, 3, 8},
                {-5, -3, -8},
                {5, -3, 2},
                {0, 10, 10},
                {10, 0, 10},
                {0, 0, 0},
                {1, -1, 0},
                {-1, 1, 0}
        };
    }

    @Test(dataProvider = "addOverflowData")
    public void add_overflow_returnsOverflowedResult(int a, int b, int expected) {
        assertEquals(mathUtils.add(a, b), expected);
    }

    @DataProvider(name = "addOverflowData")
    public Object[][] getAddOverflowData() {
        return new Object[][]{
                {2147483647, 1, -2147483648},
                {-2147483648, -1, 2147483647}
        };
    }

    // 3.2 Вычитание
    @Test(dataProvider = "subtractData")
    public void subtract_validCombinations_returnsDifference(int a, int b, int expected) {
        assertEquals(mathUtils.subtract(a, b), expected);
    }

    @DataProvider(name = "subtractData")
    public Object[][] getSubtractData() {
        return new Object[][]{
                {10, 3, 7},
                {3, 10, -7},
                {-5, -3, -2},
                {5, -3, 8},
                {0, 10, -10},
                {10, 0, 10},
                {0, 0, 0}
        };
    }

    @Test(dataProvider = "subtractOverflowData")
    public void subtract_overflow_returnsOverflowedResult(int a, int b, int expected) {
        assertEquals(mathUtils.subtract(a, b), expected);
    }

    @DataProvider(name = "subtractOverflowData")
    public Object[][] getSubtractOverflowData() {
        return new Object[][]{
                {-2147483648, 1, 2147483647},
                {2147483647, -1, -2147483648}
        };
    }

    // 3.3 Умножение
    @Test(dataProvider = "multiplyData")
    public void multiply_validCombinations_returnsProduct(int a, int b, int expected) {
        assertEquals(mathUtils.multiply(a, b), expected);
    }

    @DataProvider(name = "multiplyData")
    public Object[][] getMultiplyData() {
        return new Object[][]{
                {5, 3, 15},
                {-5, -3, 15},
                {5, -3, -15},
                {0, 10, 0},
                {10, 0, 0},
                {1, 100, 100},
                {-1, 100, -100}
        };
    }

    @Test(dataProvider = "multiplyOverflowData")
    public void multiply_overflow_returnsOverflowedResult(int a, int b, int expected) {
        assertEquals(mathUtils.multiply(a, b), expected);
    }

    @DataProvider(name = "multiplyOverflowData")
    public Object[][] getMultiplyOverflowData() {
        return new Object[][]{
                {2147483647, 2, -2},
                {-2147483648, 2, 0},
                {2147483647, -1, -2147483647}
                // "-2147483648, -1, 2147483648" закомментировано в JUnit
        };
    }

    // 3.4 Деление
    @Test(dataProvider = "divisionData")
    public void divide_validCombinations_returnsDouble(int a, int b, double expected) {
        assertEquals(mathUtils.divide(a, b), expected, 0.0001);
    }

    @DataProvider(name = "divisionData")
    public Object[][] getDivisionData() {
        return new Object[][]{
                {10, 2, 5.0},
                {7, 2, 3.5},
                {-9, 3, -3.0},
                {9, -3, -3.0},
                {0, 5, 0.0},
                {5, 1, 5.0},
                {5, -1, -5.0},
                {1, 3, 0.3333333333}
        };
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divide_byZero_throwsException() {
        mathUtils.divide(10, 0);
    }

    @Test
    public void divide_boundaryValues_works() {
        assertEquals(mathUtils.divide(Integer.MIN_VALUE, Integer.MIN_VALUE), 1.0, 0.0001);
        assertEquals(mathUtils.divide(Integer.MAX_VALUE, Integer.MAX_VALUE), 1.0, 0.0001);
        assertEquals(mathUtils.divide(Integer.MAX_VALUE, Integer.MIN_VALUE), -1.0, 0.0001);
        assertEquals(mathUtils.divide(Integer.MIN_VALUE, Integer.MAX_VALUE), -1.0, 0.0001);
    }

    // 4. Сравнение
    @Test(dataProvider = "compareLessData")
    public void compare_aLessB_returnsNegative(int a, int b) {
        assertTrue(mathUtils.compare(a, b) < 0);
    }

    @DataProvider(name = "compareLessData")
    public Object[][] getCompareLessData() {
        return new Object[][]{
                {3, 5},
                {-5, -3},
                {0, 10},
                {-1, 1},
                {-2147483648, 2147483647}
        };
    }

    @Test(dataProvider = "compareEqualData")
    public void compare_aEqualsB_returnsZero(int a, int b) {
        assertEquals(mathUtils.compare(a, b), 0);
    }

    @DataProvider(name = "compareEqualData")
    public Object[][] getCompareEqualData() {
        return new Object[][]{
                {5, 5},
                {-5, -5},
                {0, 0},
                {2147483647, 2147483647},
                {-2147483648, -2147483648}
        };
    }

    @Test(dataProvider = "compareGreaterData")
    public void compare_aGreaterB_returnsPositive(int a, int b) {
        assertTrue(mathUtils.compare(a, b) > 0);
    }

    @DataProvider(name = "compareGreaterData")
    public Object[][] getCompareGreaterData() {
        return new Object[][]{
                {5, 3},
                {-3, -5},
                {10, 0},
                {1, -1},
                {2147483647, -2147483648}
        };
    }

    @Test
    public void compare_zeroAndZero_returnsZero() {
        assertEquals(mathUtils.compare(0, 0), 0);
    }

    @Test
    public void compare_minusOneAndZero_returnsNegative() {
        assertTrue(mathUtils.compare(-1, 0) < 0);
    }

    @Test
    public void compare_zeroAndOne_returnsNegative() {
        assertTrue(mathUtils.compare(0, 1) < 0);
    }
}