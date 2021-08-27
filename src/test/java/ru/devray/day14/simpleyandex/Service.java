package ru.devray.day14.simpleyandex;

public enum Service {
    MARKET("market", "Маркет"),
    VIDEO("video","Видео"),
    IMAGES("images","Картинки"),
    MAPS("maps","Карты");

    public String elementDataId;
    public String menuItemName;

    Service(String elementDataId, String menuItemName) {
        this.elementDataId = elementDataId;
        this.menuItemName = menuItemName;
    }
}
