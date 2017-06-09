package com.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Helpers {
    private WebDriver driver;
    private WebDriverWait wait;

    public Helpers(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement element(By locator) {
        return wait.withTimeout(10, TimeUnit.SECONDS)
                .withMessage("Element not found")
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement elementVisible(By locator) {
        return wait.withTimeout(10, TimeUnit.SECONDS)
                .withMessage("Element not visible")
                .until(ExpectedConditions.visibilityOf(element(locator)));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }
}
