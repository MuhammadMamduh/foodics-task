package pages.components;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.commands.CommandsHandler;

import java.time.Duration;

public abstract class AbstractComponent {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected CommandsHandler commandsHandler;

    public AbstractComponent(final WebDriver driver){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.commandsHandler = new CommandsHandler(driver);
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isDisplayed();
}
