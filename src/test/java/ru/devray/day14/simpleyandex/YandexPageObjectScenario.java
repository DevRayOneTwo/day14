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
import ru.devray.day14.simpleyandex.pages.SearchResultPage;
import ru.devray.day14.simpleyandex.pages.StartPage;
import ru.devray.day14.simpleyandex.pages.TargetPage;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class YandexPageObjectScenario {

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

//    static ChromeDriver driver;

//    @BeforeAll
//    static void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//
//        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
//        //неявные ожидания - работает глобально
//        //driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
//    }

    @Test
    void testYandexSearch() {
        StartPage startPage = new StartPage();

        startPage.open();
        startPage.checkMenuItemsPresent(Arrays.asList(Service.values()));
        startPage.setSearchField("porsche 356B 1:18 model");
        startPage.clickSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage();

        searchResultPage.checkPageHasResultItems(2);
        searchResultPage.checkResultPagesCount(3);
        searchResultPage.clickSearchResultPage(3);
        searchResultPage.checkPage();
        String targetUrl = searchResultPage.clickSearchResult(2);

        TargetPage targetPage = new TargetPage(targetUrl);
        targetPage.checkPage();
    }

//    @AfterAll
//    static void tearDown() {
//        //driver.close(); //закрывает текущую вкладку
//        driver.quit(); //закрывает окно хром со всеми вкладками
//    }
}
