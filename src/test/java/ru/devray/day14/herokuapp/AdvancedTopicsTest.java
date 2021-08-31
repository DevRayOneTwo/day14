package ru.devray.day14.herokuapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Set;

public class AdvancedTopicsTest {

    static RemoteWebDriver driver;

    @BeforeAll
    static void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver","bin/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //неявные ожидания - работает глобально
        //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @Test
    void testAlert(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        button.click();
        driver.switchTo().alert().accept();

        WebElement buttonConfirm = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        buttonConfirm.click();
        driver.switchTo().alert().dismiss();

        WebElement buttonPrompt = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        buttonPrompt.click();
        driver.switchTo().alert().sendKeys("sdfsdfsdfds");
        driver.switchTo().alert().accept();
    }

    @Test
    void testPopup(){
        driver.get("https://the-internet.herokuapp.com/windows");
        WebElement link = driver.findElement(By.xpath("//div[@id='content']/div/a"));
        link.click();

        String currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        String windowToClose = windows.stream().filter(s -> !s.equals(currentWindow)).findFirst().get();
        driver.switchTo().window(windowToClose);
        driver.close();
    }

    @Test
    void testIFrame(){
        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr");

        WebElement linkMail = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        linkMail.sendKeys("my-login");

        driver.switchTo().parentFrame();
        System.out.println(driver.findElement(By.xpath("//h3")).getText());
    }
}
