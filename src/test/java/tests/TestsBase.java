package tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.ScreenshotsHandler;


import java.io.IOException;
import java.time.Duration;

public class TestsBase {

    public static WebDriver driver ;
    public static WebDriverWait wait;
    public static Logger log;
    public  String testCaseName;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName)
    {
        driver = DriverFactory.createDriver(browserName);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // vip
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("https://www.amazon.eg/?language=en_AE");
        System.out.println("Redirected to the HOMEPAGE");
        System.out.println("________________________________________");
    }

    // ***********************************************
    // ***********************************************
    // ***********************************************

    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        testCaseName = result.getMethod().getMethodName();
        System.out.println("Currently Executing TestCase: " + testCaseName);
        log.info("Currently Executing TestCase: " + testCaseName);
        System.out.println("==================================================");
        log.info("==================================================");
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            ScreenshotsHandler.TakeScreenshot(result.getName());
        }
    }

    @AfterClass
    public void moveToHomePage()
    {
//        driver.navigate().to("https://www.amazon.eg/?language=en_AE");
//        System.out.println("********* Navigating To [HOME PAGE] *********");
    }

    @AfterSuite
    public void stopDriver()
    {
        driver.quit(); // closes all browser windows which are running & will end the webdriver session.
//        driver.close(); // Will close only the currently focused window/tab
    }
}
