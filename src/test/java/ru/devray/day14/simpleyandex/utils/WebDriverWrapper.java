package ru.devray.day14.simpleyandex.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Паттерн синглтон - одиночка
 */
public class WebDriverWrapper {

    private static RemoteWebDriver driver;
    private static WebDriverWrapper wrap;

    private static Logger log = LogManager.getRootLogger();

    private WebDriverWait wait;

    private WebDriverWrapper(){
        log.debug("Инициализирую экземпляр обертки над веб драйвером.");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        this.wait = new WebDriverWait(driver, 5, 100);

        log.debug("Инициализация обертки завершена.");
    }

    public static WebDriverWrapper getInstance(){
        log.debug("Запрошен экземпляр драйвера.");
        if (wrap == null){
            wrap = new WebDriverWrapper();
        }
        return wrap;
    }

    public void get(String baseUrl) {
        log.debug("Открываю страницу по адресу '%s'");
        driver.get(baseUrl);
        log.debug("Открываю страницу по адресу '%s'");
    }


    public String getCurrentUrl() {
        log.debug("Получаю адрес текущей страницы...");
        return driver.getCurrentUrl();
    }

    public void close() {
        log.debug("Закрываю текущую страницу");
        driver.close();
    }


    public String getWindowHandle() {
        return driver.getWindowHandle();
    }


    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }


    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public WebElement findElement(By xpath) {
        try {
            log.debug("Ищу элемент по локатору '{}'", xpath);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
            ((JavascriptExecutor)driver).executeScript("arguments[0]['style']['backgroundColor']='darksalmon';", element);
            log.debug("Элемент по локатору '{}' найден.", xpath);
            return element;
        } catch (RuntimeException e){
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String newFileName = String.format(
                    "%s\\screenshots\\%s.png", System.getProperty("user.dir"), LocalDateTime.now().toString().replace(":", "-"));
            File destination = new File(newFileName);

            try {
                FileUtils.copyFile(source, destination);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            driver.quit();
            throw e;
        }
    }

    public List<WebElement> findElements(By xpath) {
        log.debug("Ищу элемент по локатору '{}'", xpath);
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
        log.debug("Элемент по локатору '{}' найден.", xpath);
        return elements;
    }
}
