package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class TestNegative {private WebDriver driver;

    @BeforeAll
    static void setupAll(){
        System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver(2).exe");
    }

    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver=null;
    }


    @Test
    public void negativeTestNameEnglish() {
        List<WebElement> name = driver.findElements(By.className("input__control"));
        name.get(0).sendKeys("Ilina Natalya");
        driver.findElement(By.className("button__text")).click();
        String actual = driver.findElement(By.className("input__sub")).getText().strip();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(actual, expected);

    }

    @Test
    public void negativeTestNameNull() {
        List<WebElement> name = driver.findElements(By.className("input__control"));
        name.get(0).sendKeys("");
        driver.findElement(By.className("button__text")).click();
        String actual = driver.findElement(By.className("input__sub")).getText().strip();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(actual, expected);

    }

    @Test
    public void negativeTestPhoneNull(){
        List<WebElement> name = driver.findElements(By.className("input__control"));
        name.get(0).sendKeys("Ильина Наталья");
        name.get(1).sendKeys("");
        driver.findElement(By.className("button__text")).click();
        List<WebElement> phone = driver.findElements(By.className("input__sub"));
        String actual = phone.get(1).getText().strip();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void negativeTestPhoneIncorrect(){
        List<WebElement> name = driver.findElements(By.className("input__control"));
        name.get(0).sendKeys("Ильина Наталья");
        name.get(1).sendKeys("79101256666");
        driver.findElement(By.className("button__text")).click();
        List<WebElement> phone = driver.findElements(By.className("input__sub"));
        String actual = phone.get(1).getText().strip();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void negativeTestNoCheckbox(){
            List<WebElement> name = driver.findElements(By.className("input__control"));
            name.get(0).sendKeys("Ильина Наталья");
            name.get(1).sendKeys("+79168455555");
            driver.findElement(By.className("button__text")).click();
            String actual = driver.findElement(By.className("input_invalid")).getCssValue("color").strip();
            String expected = "rgba(255, 92, 92, 1)";
            Assertions.assertEquals(actual, expected);
    }
}
