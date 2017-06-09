package com.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends BasePage {

    public ItemPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        locators.put("rateBar$", ".pp-g-rating-and-results .g-rating-stars-i-medium");
        locators.put("emptyRateBar$", ".pp-g-rating-and-results .g-rating-stars-medium");
        locators.put("filterCheckbox$x", ".//label[contains(@class, 'filter-parametrs-i-l-i-label')]/a[contains(., '%s')]");
    }

    public double getRate() {
        Double rateBarWidth = getElementWidth(util.element(By.cssSelector(locators.get("rateBar$"))));
        Double emptyRateBarWidth = getElementWidth(util.element(By.cssSelector(locators.get("emptyRateBar$"))));
        return rateBarWidth / emptyRateBarWidth * 5;
    }

    private double getElementWidth(WebElement element) {
        return Double.parseDouble(element.getCssValue("width").replace("px", ""));
    }

    @Override
    public void waitFor() {
        util.elementVisible(By.cssSelector(locators.get("rateBar$")));
    }
}
