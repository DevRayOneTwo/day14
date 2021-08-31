package ru.devray.day14.simpleyandex.pages;

import org.junit.jupiter.api.Assertions;

public class TargetPage extends AbstractPage{

    public TargetPage(String baseUrl) {
        super(baseUrl);
    }

    @Override
    public void checkPage() {
        Assertions.assertFalse(
            driver.getCurrentUrl().contains("yandex.ru")
        );
    }
}
