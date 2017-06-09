package com.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Helpers util;
    protected Map<String, String> locators = new HashMap<>();

    public BasePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        this.util = new Helpers(driver, wait);
    }

    public abstract void waitFor();
}
