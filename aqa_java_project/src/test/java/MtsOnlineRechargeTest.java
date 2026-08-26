import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlineRechargeTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final String BASE_URL = "https://www.mts.by/";
    private static final String BLOCK_HEADER_XPATH = "//div[contains(@class, 'pay__wrapper')]//h2";
    private static final String PAYMENT_SYSTEM_LOGO_XPATH = "//div[contains(@class, 'pay__partners')]//img";
    private static final String MORE_DETAILS_LINK_XPATH = "//a[contains(text(),'Подробнее о сервисе')]";
    private static final String PHONE_INPUT_XPATH = "//input[@id='connection-phone']";
    private static final String AMOUNT_INPUT_XPATH = "//input[@id='connection-sum']";
    private static final String CONTINUE_BUTTON_XPATH = "//form[@id='pay-connection']//button[@type='submit']";
    private static final String IFRAME_XPATH = "//iframe[contains(@class, 'payment-widget-iframe')]";

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeEach
    public void openHomePage() {
        driver.get(BASE_URL);
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieWindow = shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cookie.show")));
            WebElement acceptButton = cookieWindow.findElement(By.id("cookie-agree"));
            acceptButton.click();
        } catch (TimeoutException e) {
            System.out.println("Куки-баннер не найден, продолжаем тест.");
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBlockHeader() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BLOCK_HEADER_XPATH)));
        String actualHeader = header.getText();
        assertEquals("ОНЛАЙН ПОПОЛНЕНИЕ\nБЕЗ КОМИССИИ", actualHeader, "Заголовок блока не соответствует ожидаемому");
    }

    @Test
    public void testPaymentSystemLogos() {
        List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(PAYMENT_SYSTEM_LOGO_XPATH)));

        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены");

        for (WebElement logo : logos) {
            String src = logo.getAttribute("src");
            assertNotNull(src, "Атрибут src отсутствует у логотипа");
            assertFalse(src.isEmpty(), "Атрибут src пустой");
            assertTrue(logo.isDisplayed(), "Логотип не отображается на странице");
        }
    }

    @Test
    public void testMoreDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(MORE_DETAILS_LINK_XPATH)));

        link.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("poryadok-oplaty") || currentUrl.contains("help"),
                "Ссылка не привела на страницу с описанием сервиса. Текущий URL: " + currentUrl);
    }

    @Test
    public void testContinueButton() {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(PHONE_INPUT_XPATH)));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement amountInput = driver.findElement(By.xpath(AMOUNT_INPUT_XPATH));
        amountInput.clear();
        amountInput.sendKeys("10");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(CONTINUE_BUTTON_XPATH)));
        assertTrue(continueButton.isDisplayed(), "Кнопка 'Продолжить' не отображается");

        continueButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(IFRAME_XPATH)));
        assertTrue(iframe.isDisplayed(), "Платёжный iframe не появился");
    }
}