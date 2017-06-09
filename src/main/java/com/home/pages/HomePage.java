package com.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        locators.put("menuRoot$", "#fat-menu");
        locators.put("menuFirstLevel$x", ".//li[contains(@class, 'f-menu-l-i')][./a[contains(., '%s')]]");
        locators.put("menuSecondLevel$x", ".//li[contains(@class, 'f-menu-sub-l-i') or contains(@class, 'f-menu-pop-l-i')][./a[contains(., '%s')]]");
    }

    public void openMenu(String mainItem, String subItem) {
        util.elementVisible(By.xpath(String.format(locators.get("menuFirstLevel$x"), mainItem))).click();
        util.elementVisible(By.xpath(String.format(locators.get("menuSecondLevel$x"), subItem))).click();
    }

    @Override
    public void waitFor() {
        util.elementVisible(By.cssSelector(locators.get("menuRoot$")));
    }
}
