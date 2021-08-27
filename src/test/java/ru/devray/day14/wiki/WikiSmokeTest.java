package ru.devray.day14.wiki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikiSmokeTest {

    /**
     * Зайти на wikipedia.org
     * Указать в поисковом поле поисковый запрос 'Java'
     * Нажать на кнопку поиска
     * Убедиться, что заголовок статьи содержит слово Java
     */
    @Test
    void testSearch(){
        System.setProperty("webdriver.chrome.driver","bin/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
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

        driver.close();
    }
}
