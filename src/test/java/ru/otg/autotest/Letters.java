package ru.otg.autotest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Letters {
    /**
     * Поле ввода поиска писем в почте
     */
    private static WebElement searchBar;

    /**
     * Кнопка создания нового письма
     */
    private static WebElement newMessage;

    /**
     * Поле ввода почты получателя
     */
    private static WebElement mailTo;

    /**
     * Тема отправляемого письма
     */
    private static WebElement letterSubject;

    /**
     * Текст отправляемого письма
     */
    private static WebElement letterText;

    /**
     * Кнопка отправки письма
     */
    private static WebElement sendButton;

    /**
     * Ищет количество писем полученных от указанного e-mail
     * @param driver - экземпляр WebDriver
     * @param searchParam - почта, по которой производится поиск
     * @return int - количество писем
     * @throws InterruptedException
     */
    public static int searchForLetters(WebDriver driver, String searchParam) throws InterruptedException {
        int lettersReadBefore = driver.findElements(By.xpath("//tr[@class='zA yO']")).size();
        int lettersUnreadBefore = driver.findElements(By.xpath("//tr[@class='zA zE']")).size();

        searchBar = driver.findElement(By.className("gb_qf"));
        searchBar.sendKeys(searchParam, Keys.ENTER);
        Thread.sleep(3000); // Крайне спорно, но на данный момент нашёл только такой выход из ситуации.

        int lettersReadAfter = driver.findElements(By.xpath("//tr[@class='zA yO']")).size();
        int lettersUnreadAfter = driver.findElements(By.xpath("//tr[@class='zA zE']")).size();

        return (lettersReadAfter + lettersUnreadAfter) - (lettersReadBefore + lettersUnreadBefore);
    }

    /**
     * Отправляет количество найденных писем на указанный e-mail
     * @param driver - экземпляр WebDriver
     * @param emailTo - почта получателя
     * @param lettersCount - количество найденных писем
     */
    public static void sendLettersCountToEmail(WebDriver driver, String emailTo, int lettersCount) {
        newMessage = driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']"));
        newMessage.click();

        mailTo = driver.findElement(By.className("vO"));
        mailTo.sendKeys(emailTo);

        letterSubject = driver.findElement(By.className("aoT"));
        letterSubject.sendKeys("Тестовое задание. Матвеев");

        letterText = driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']"));
        letterText.sendKeys("Отправленных писем: " + lettersCount);

        sendButton = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']"));
        sendButton.click();
    }
}
