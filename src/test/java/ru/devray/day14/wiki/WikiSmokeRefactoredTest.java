package ru.devray.day14.wiki;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WikiSmokeRefactoredTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver","bin/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    /**
     * Зайти на wikipedia.org
     * Указать в поисковом поле поисковый запрос 'Java'
     * Нажать на кнопку поиска
     * Убедиться, что заголовок статьи содержит слово Java
     */
    @Test
    void testSearch(){

        driver.get("https://wikipedia.org");

        //Указать в поисковом поле поисковый запрос 'Java'
//        WebElement searchField = driver.findElement(By.id("searchInput"));
//        WebElement searchField = driver.findElement(By.xpath("/html/body/div/form/fieldset/div/input"));
        WebElement searchField = driver.findElement(By.xpath("//*[@id='searchInput']"));
        searchField.sendKeys("Java");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        WebElement articleHeading = driver.findElement(By.xpath("//h1[@id='firstHeading']"));
        Assertions.assertEquals("Java", articleHeading.getText());


    }

    @Test
    void testSearch1(){

        driver.get("https://wikipedia.org");

        //Указать в поисковом поле поисковый запрос 'Java'
//        WebElement searchField = driver.findElement(By.id("searchInput"));
//        WebElement searchField = driver.findElement(By.xpath("/html/body/div/form/fieldset/div/input"));
        WebElement searchField = driver.findElement(By.xpath("//*[@id='searchInput']"));
        searchField.sendKeys("Java");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        WebElement articleHeading = driver.findElement(By.xpath("//h1[@id='firstHeading']"));
        Assertions.assertEquals("Java", articleHeading.getText());


    }

    @Test
    void testSearch2(){

        driver.get("https://wikipedia.org");

        //Указать в поисковом поле поисковый запрос 'Java'
//        WebElement searchField = driver.findElement(By.id("searchInput"));
//        WebElement searchField = driver.findElement(By.xpath("/html/body/div/form/fieldset/div/input"));
        WebElement searchField = driver.findElement(By.xpath("//*[@id='searchInput']"));
        searchField.sendKeys("Java");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        WebElement articleHeading = driver.findElement(By.xpath("//h1[@id='firstHeading']"));
        Assertions.assertEquals("Java", articleHeading.getText());


    }

    @AfterAll
    static void tearDown(){
        driver.close();
    }
}
