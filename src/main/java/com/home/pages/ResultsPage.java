package com.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResultsPage extends BasePage {

    public ResultsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        locators.put("inputMaxPrice$", "[name='price[max]']");
        locators.put("inputMinPrice$", "[name='price[min]']");
        locators.put("submitButtonPrice$", "#submitprice");
        locators.put("filterCheckbox$x", ".//label[contains(@class, 'filter-parametrs-i-l-i-label')]/a[contains(., '%s')]");
        locators.put("resultItems$", "[name='goods_list']>.g-i-tile-catalog");
        locators.put("resultItemsTitle$", ".g-i-tile-i-title");
        locators.put("resultSortingTypeButton$", ".sort-view-link");
        locators.put("resultSortingTypeListItems$x", ".//li[contains(@class, 'sort-view-l-i')]/a[contains(., '%s')]");

    }

    public void setPriceFilter(String min, String max) {
        if(min != null) util.elementVisible(By.cssSelector(locators.get("inputMinPrice$"))).sendKeys(min);
        util.elementVisible(By.cssSelector(locators.get("inputMaxPrice$"))).sendKeys(max);
        util.elementVisible(By.cssSelector(locators.get("submitButtonPrice$"))).click();
        waitForLoad();
    }

    public void setMaxPriceFilter(String max) {
        setPriceFilter(null, max);
    }

    public void setManufacturerFilter(String manufacturer) {
        util.elementVisible(By.xpath(String.format(locators.get("filterCheckbox$x"), manufacturer))).click();
        waitForLoad();
    }

    public void setScreenFilter(String size) {
        util.elementVisible(By.xpath(String.format(locators.get("filterCheckbox$x"), size))).click();
        waitForLoad();
    }

    public void setSortingType(String type) {
        util.elementVisible(By.cssSelector(locators.get("resultSortingTypeButton$"))).click();
        util.elementVisible(By.xpath(String.format(locators.get("resultSortingTypeListItems$x"), type))).click();
        waitForLoad();
    }

    public String getItemTitleByIndex (int index){
        return getItems().get(index)
                .findElement(By.cssSelector(locators.get("resultItemsTitle$"))).getText();
    }

    public void openItemByTitle (String title) {
        WebElement filteredItem = getItems().stream()
                .filter(item -> item.findElement(By.cssSelector(locators.get("resultItemsTitle$"))).getText().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Result with title [" + title + "] not found"));
        util.scrollToElement(filteredItem);
        filteredItem.findElement(By.cssSelector(locators.get("resultItemsTitle$"))).click();
    }

    public int getItemsCount() {
        return getItems().size();
    }

    private List<WebElement> getItems() {
        return driver.findElements(By.cssSelector(locators.get("resultItems$")));
    }

    private void waitForLoad() {
        WebElement progressBar = util.element(By.cssSelector(".progress-b"));
        try {
//            wait.until(ExpectedConditions.visibilityOf(progressBar));
            wait.withTimeout(5, TimeUnit.SECONDS).until(ExpectedConditions.invisibilityOf(progressBar));
        }catch (TimeoutException e) {
            System.out.println("Progressbar was not shown");
        }

    }

    @Override
    public void waitFor() {
        util.elementVisible(By.cssSelector(locators.get("inputMaxPrice$")));
        waitForLoad();
    }
}
