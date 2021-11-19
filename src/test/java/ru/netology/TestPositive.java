package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.sql.Driver;
import java.util.List;

import org.junit.jupiter.api.Assertions.*;

public class TestPositive {
    private WebDriver driver;

    @BeforeAll
    static void setupAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver=null;
    }


    @Test
    public void positiveTest() {
        List<WebElement> name = driver.findElements(By.className("input__control"));
        name.get(0).sendKeys("Ильина Наталья");
        name.get(1).sendKeys("+79168455555");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.className("button__text")).click();
        String actual = driver.findElement(By.className("paragraph")).getText().strip();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(actual, expected);

    }
}


