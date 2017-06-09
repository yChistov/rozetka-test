import com.home.PageFactory;
import com.home.pages.ResultsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {
    private WebDriver driver;
    private PageFactory pages;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.ie.driver",
                "src\\main\\resources\\IEDriverServer.exe");

        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://rozetka.com.ua");

        driver = new InternetExplorerDriver(caps);
//        driver = new ChromeDriver();
        pages = new PageFactory(driver, "http://rozetka.com.ua");
    //STEP 1
        driver.manage().window().maximize();
    }

    @Test
    public void mainTest() throws InterruptedException {
    //STEP 2
        pages.openHomePage();
    //STEP 3
        pages.getHomePage().openMenu("Смартфоны", "Мобильные телефоны");
    //STEP 4
        ResultsPage resultsPage = pages.getResultsPage();
        resultsPage.setMaxPriceFilter("6000");
        resultsPage.setScreenFilter("4.1\" - 4.5\"");
        resultsPage.setScreenFilter("4.5\" - 5\"");
    //STEP 6,7
        resultsPage.setManufacturerFilter("Samsung");
        resultsPage.setManufacturerFilter("Lenovo");
        resultsPage.setManufacturerFilter("Meizu");
        resultsPage.setManufacturerFilter("Huawei");
        resultsPage.setManufacturerFilter("Xiaomi");
    //STEP 8
        Assert.assertTrue(resultsPage.getItemsCount() > 1);
    //STEP 9
        String firstItemTitle = resultsPage.getItemTitleByIndex(0);
    //STEP 10
        resultsPage.setSortingType("популярные");
    //STEP 11
        resultsPage.openItemByTitle(firstItemTitle);
    //STEP 12
        double rate = pages.getItemPage().getRate();
        System.out.printf("Rate: %.1f", rate);
    }

    @AfterClass
    public void afterClass() {
    //STEP 13
        driver.quit();
    }
}
