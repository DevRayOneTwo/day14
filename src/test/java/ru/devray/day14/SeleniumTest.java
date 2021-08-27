package ru.devray.day14;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumTest {

    @Test
    void testYandexMainPage(){
        System.setProperty("webdriver.chrome.driver","bin/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://ya.ru");

    }

}
