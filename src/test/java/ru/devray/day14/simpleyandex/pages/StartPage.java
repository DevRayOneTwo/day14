package ru.devray.day14.simpleyandex.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.devray.day14.simpleyandex.Service;

import java.util.List;

public class StartPage extends AbstractPage{

    public StartPage() {
        super("https://yandex.ru");
    }

    public void setSearchField(String searchQuery){
        WebElement searchField = driver.findElement(By.xpath("//input[@id='text']"));
        searchField.sendKeys(searchQuery);
    }

    public void clickSearchButton(){
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
    }

    public void checkMenuItemsPresent(List<Service> menuItems){
        for(Service service : menuItems){
            WebElement itemLogo = driver.findElement(By.xpath(
                    String.format("//*[@data-id='%s']/div[@class='services-new__icon']", service.elementDataId)));
            Assertions.assertNotNull(itemLogo, String.format("Отсутствует иконка '%s'!", service.menuItemName));

            WebElement itemText = driver.findElement(By.xpath(
                    String.format("//*[@data-id='%s']/div[@class='services-new__item-title']", service.elementDataId)));
            Assertions.assertEquals(service.menuItemName, itemText.getText(), "Отсутствует текстовая подпись пункта Маркет!");
        }
    }

}
