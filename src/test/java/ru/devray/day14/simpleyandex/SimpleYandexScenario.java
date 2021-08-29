package ru.devray.day14.simpleyandex;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SimpleYandexScenario {

    /**
     * Тестовый сценарий для поисковика Яндекс.
     * 1. Открыть страницу yandex.ru
     * 2. Проверить, отображаются ли над поисковой строкой кнопки "Маркет", "Видео", "Картинки", "Карты"
     * (проверяется наличие элементов логотип + текст).
     * 3. Ввести в поле поиска запрос "porsche 356B 1:18 model"
     * 4. Нажать кнопку найти
     * 5. Проверить, что по данному поисковому запросу получено как минимум два результата
     * 6. Проверить, что по данному поисковому запросу есть как минимум 3 страницы результатов
     * 7. Перейти на 3-ю страницу результатов
     * 8. Проверить, что мы все еще находимся в поисковике Яндекс (URL)
     * 9. Открыть 2-й поисковый результат в выдаче на данной странице
     * 10. Убедиться что произошел переход со страницы поисковика на целевую страницу
     */

    static ChromeDriver driver;

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
    void testYandexSearch(){
        //1. Открыть страницу yandex.ru
        driver.get("https://yandex.ru");

        //2. Проверить, отображаются ли над поисковой строкой кнопки "Маркет", "Видео", "Картинки", "Карты"
        //(проверяется наличие элементов логотип + текст).
        for(Service service : Service.values()){
            WebElement itemLogo = driver.findElement(By.xpath(
                    String.format("//*[@data-id='%s']/div[@class='services-new__icon']", service.elementDataId)));
            Assertions.assertNotNull(itemLogo, String.format("Отсутствует иконка '%s'!", service.menuItemName));

            WebElement itemText = driver.findElement(By.xpath(
                    String.format("//*[@data-id='%s']/div[@class='services-new__item-title']", service.elementDataId)));
            Assertions.assertEquals(service.menuItemName, itemText.getText(), "Отсутствует текстовая подпись пункта Маркет!");
        }

        //3. Ввести в поле поиска запрос "porsche 356B 1:18 model"
        WebElement searchField = driver.findElement(By.xpath("//input[@id='text']"));
        searchField.sendKeys("porsche 356B 1:18 model");

        //4. Нажать кнопку найти
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        //явное ожидание
        WebDriverWait wait = new WebDriverWait(driver, 5, 250);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul[@id='search-result']/li[1]"))));

        //5. Проверить, что по данному поисковому запросу получено как минимум 2 результата
        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='search-result']/li"));
        Assertions.assertTrue(searchResults.size() >= 2, "В выдаче недостаточное количество результатов!");

        //6. Проверить, что по данному поисковому запросу есть как минимум 3 страницы результатов
        WebElement page3Link = driver.findElement(By.xpath("//div[@class='pager__items']/a[@aria-label='Страница 3']"));
        Assertions.assertNotNull(page3Link, "Отсутствует 3-я страница результатов!");

        //7. Перейти на 3-ю страницу результатов
        page3Link.click();

        //8. Проверить, что мы все еще находимся в поисковике Яндекс (URL)
        Assertions.assertTrue(driver.getCurrentUrl().contains("yandex.ru"));

        //9. Открыть 2-й поисковый результат в выдаче на данной странице
        WebElement searchResult = driver.findElement(By.xpath("//ul[@id='search-result']/li[2]/div/h2/a"));
        String resultUrl = searchResult.getAttribute("href");
        searchResult.click();

        //10. Убедиться что произошел переход со страницы поисковика на целевую страницу
        String currentTabId = driver.getWindowHandle();
        Set<String> tabIds = driver.getWindowHandles();

        String targetTabId = tabIds.stream().filter(tab -> !tab.equals(currentTabId)).findFirst().get();
        driver.switchTo().window(targetTabId);

        Assertions.assertTrue(!driver.getCurrentUrl().contains(resultUrl), "Переход на целевую страницу поискового результата не произошел");
    }

    @AfterAll
    static void tearDown(){
        //driver.close(); //закрывает текущую вкладку
        driver.quit(); //закрывает окно хром со всеми вкладками
    }
}
