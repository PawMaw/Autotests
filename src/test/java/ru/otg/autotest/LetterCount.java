package ru.otg.autotest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class LetterCount {

    private static String gmailURL = "https://www.gmail.com/"; // Url адрес почты Google
    private static String emailAddress = "pmatveev2020@gmail.com"; // Email отправителя
    private static String passForLogin = "*****"; // Пароль отправителя
    private static String searchParam = "from:farit.valiahmetov@simbirsoft.com"; // Фильтр поиска сообщений
    private static String emailTo = "farit.valiahmetov@simbirsoft.com"; // Email получателя
    private static WebDriver driver;

    /**
     * Почта
     */
    @FindBy(id = "userEmail")
    private WebElement email;

    /**
     * Пароль
     */
    @FindBy(id = "userPassword")
    private WebElement password;

    /**
     * Поле ввода поиска писем в почте
     */
    @FindBy(id = "letterSearchBar")
    private WebElement searchBar;

    /**
     * Кнопка создания нового письма
     */
    @FindBy(id = "createNewMessage")
    private WebElement newMessage;

    /**
     * Поле ввода почты получателя
     */
    @FindBy(id = "emailWhereToSend")
    private WebElement mailTo;

    /**
     * Тема отправляемого письма
     */
    @FindBy(id = "letterMainSubject")
    private WebElement letterSubject;

    /**
     * Текст отправляемого письма
     */
    @FindBy(id = "letterMainText")
    private WebElement letterText;

    /**
     * Кнопка отправки письма
     */
    @FindBy(id = "sendToButton")
    private WebElement sendButton;

    public void openChrome() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver/chromedriver");
        driver = new ChromeDriver();
    }

    public void openGmailPage(String url) {
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void enterEmail(String emailAddress) {
        email = driver.findElement(By.xpath("//input[@type='email']"));
        email.sendKeys(emailAddress, Keys.ENTER);
    }

    public void enterPassword(String pass) {
        password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys(pass, Keys.ENTER);
    }

    public String searchForLetters(String searchParam) throws InterruptedException {
        searchBar = driver.findElement(By.className("gb_qf"));
        searchBar.sendKeys(searchParam, Keys.ENTER);
        Thread.sleep(3000);
        int letters = driver.findElements(By.xpath("//span[@class='zA yO']")).size();
        return "Отправленных писем: " + letters;
    }

    public void sendLettersCountToEmail(String emailTo, String searchParam) throws InterruptedException {
        newMessage = driver.findElement(By.className("T-I T-I-KE L3"));
        newMessage.click();

        mailTo = driver.findElement(By.className("vO"));
        mailTo.sendKeys(emailTo);

        letterSubject = driver.findElement(By.className("aoT"));
        letterSubject.sendKeys("Тестовое задание. Матвеев");

        letterText = driver.findElement(By.className("Am Al editable LW-avf tS-tW"));
        letterText.sendKeys(searchForLetters(searchParam));

        sendButton = driver.findElement(By.className("T-I J-J5-Ji aoO v7 T-I-atl L3"));
        sendButton.click();
    }

    @Test
    public void sendMessageWithLetterCount() throws InterruptedException {
        openChrome();
        openGmailPage(gmailURL);

        enterEmail(emailAddress);
            Thread.sleep(3000);
        enterPassword(passForLogin);
            Thread.sleep(3000);

        sendLettersCountToEmail(emailTo, searchParam);
    }
}
