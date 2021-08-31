package ru.devray.day14.simpleyandex.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class SearchResultPage extends AbstractPage {

    public SearchResultPage() {
        super("https://yandex.ru/search/");
    }

    public void checkPageHasResultItems(int resultCount){
        log.info("Проверяю что на странице поисковых результатов присутствует {} результатов.", resultCount);
        List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@id='search-result']/li"));
        Assertions.assertTrue(searchResults.size() >= resultCount, "В выдаче недостаточное количество результатов!");
    }

    public WebElement checkResultPagesCount(int pageCount){
        log.info("Проверяю что количество страниц поисковых результатов не меньше {}", pageCount);
        WebElement pageLink = driver.findElement(By.xpath(String.format("//div[@class='pager__items']/a[@aria-label='Страница %d']", pageCount)));
        Assertions.assertNotNull(pageLink, String.format("Отсутствует %d-я страница результатов!", pageCount));
        return pageLink;
    }

    public void clickSearchResultPage(int pageNumber){
        log.info("Перехожу на страницу поисковых результатов номер '{}'", pageNumber);
        WebElement pageLink = checkResultPagesCount(pageNumber);
        pageLink.click();
    }

    public String clickSearchResult(int resultNumber){
        WebElement searchResult = driver.findElement(By.xpath(String.format("//ul[@id='search-result']/li[%d]/div/h2/a", resultNumber)));
        String resultUrl = searchResult.getAttribute("href");
        searchResult.click();

        String currentTabId = driver.getWindowHandle();
        Set<String> tabIds = driver.getWindowHandles();

        String targetTabId = tabIds.stream().filter(tab -> !tab.equals(currentTabId)).findFirst().get();
        driver.switchTo().window(targetTabId);

        return resultUrl;
    }

}
