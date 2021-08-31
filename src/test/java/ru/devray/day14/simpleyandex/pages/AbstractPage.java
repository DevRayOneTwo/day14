package ru.devray.day14.simpleyandex.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.devray.day14.simpleyandex.utils.WebDriverWrapper;

public abstract class AbstractPage {

    public static Logger log = LogManager.getRootLogger();
    public WebDriverWrapper driver = WebDriverWrapper.getInstance();

    public String baseUrl;

    public AbstractPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void open(){
        driver.get(baseUrl);
        log.info("Страница по адресу '{}' открыта.", baseUrl);
    }

    public void close(){
        driver.close();
        log.info("Страница по адресу '{}' была закрыта.", baseUrl);
    }

    public void checkPage(){
        log.info("Осуществляю проверку что мы находимся на странице по адресу '{}'.", baseUrl);
        Assertions.assertTrue(
            driver.getCurrentUrl().contains(baseUrl)
        );
    }

}
