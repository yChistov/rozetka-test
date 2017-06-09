package com.home;

import com.home.pages.BasePage;
import com.home.pages.HomePage;
import com.home.pages.ItemPage;
import com.home.pages.ResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactory {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    public PageFactory(WebDriver driver, String baseUrl) {
        this.baseUrl = baseUrl;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 3);
    }

    public HomePage getHomePage() {
        HomePage page = new HomePage(driver, wait);
        initPage(page);
        return page;
    }

    public ResultsPage getResultsPage() {
        ResultsPage page = new ResultsPage(driver, wait);
        initPage(page);
        return page;
    }

    public ItemPage getItemPage() {
        ItemPage page = new ItemPage(driver, wait);
        initPage(page);
        return page;
    }

    public void openHomePage() {
        driver.get(baseUrl);
    }

    private void initPage(BasePage page) {
        page.waitFor();
    }
}
