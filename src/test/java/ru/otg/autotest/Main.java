package ru.otg.autotest;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Platform.LINUX;

public class Main {

    private static final String gmailURL = "https://www.gmail.com/"; // Url адрес почты Google
    private static final String emailAddress = "simbtest111@gmail.com"; // Email отправителя
    private static final String passForLogin = "asd123fgh456"; // Пароль отправителя
    private static final String searchParam = "from:cesar1640@gmail.com"; // Фильтр поиска сообщений
    private static final String emailTo = "cesar1640@gmail.com"; // Email получателя

    @Test
    public void sendMessageWithLetterCount() throws InterruptedException, MalformedURLException {
        DesiredCapabilities dr = DesiredCapabilities.chrome();
        dr.setBrowserName("chrome");
        dr.setPlatform(LINUX);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), dr);

        Login.openGmailPage(driver, gmailURL);
        Login.enterEmail(driver, emailAddress);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        Login.enterPassword(driver, passForLogin);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        Letters.sendLettersCountToEmail(driver, emailTo, Letters.searchForLetters(driver, searchParam));
        driver.close();
    }
}
