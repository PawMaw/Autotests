package ru.otg.autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class Login {
    /**
     * Почта
     */
    private static WebElement email;

    /**
     * Пароль
     */
    private static WebElement password;

    /**
     * Открывает страницу почты Gmail
     * @param driver - экземпляр WebDriver
     * @param url - адрес почты
     */
    public static void openGmailPage(WebDriver driver, String url) {
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    /**
     * Вводит почту пользователя
     * @param driver - экземпляр WebDriver
     * @param emailAddress - почта пользователя
     */
    public static void enterEmail(WebDriver driver, String emailAddress) {
        email = driver.findElement(By.xpath("//input[@type='email']"));
        email.sendKeys(emailAddress, Keys.ENTER);
    }

    /**
     * Вводит пароль пользователя
     * @param driver - экземпляр WebDriver
     * @param pass - пароль пользователя
     */
    public static void enterPassword(WebDriver driver, String pass) {
        password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(pass, Keys.ENTER);
    }
}
