package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("goog:chromeOptions", Map.of("w3c", true)); // BiDi option
            options.setCapability("webSocketUrl", true);
            return new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("edge"))
        {
            return new EdgeDriver();
        }
        else if (browserName.equalsIgnoreCase("safari")) {
            return new SafariDriver();
        }
        else{
            throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
    }
}
