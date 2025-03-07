package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.commands.CommandsHandler;
import pages.components.Navbar;
import pages.components.Sidebar;

import java.time.Duration;

public class PagesBase {
    /*
        INITIALIZATION
     */
    protected WebDriver driver;
    public JavascriptExecutor jse;
    public Actions actions;
    public Wait<WebDriver> wait;
    public Navbar navbar;
    public Sidebar sidebar;
    public CommandsHandler commandsHandler;

    /*
        CONSTRUCTOR
     */
    public PagesBase(WebDriver driver)
    {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        this.navbar = new Navbar(driver);
        this.sidebar = new Sidebar(driver);
        this.commandsHandler = new CommandsHandler(driver);
        PageFactory.initElements(driver, this);
    }
}