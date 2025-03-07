package pages.commands;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommandsHandler {
    protected WebDriver driver;
    public Wait<WebDriver> wait;
    public JavascriptExecutor jse;
    public Actions actions;

    public CommandsHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.jse = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
    }

    public void clickObscured(WebElement element){
        wait.until(d -> element.isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).click().perform();
//      jse.executeScript("arguments[0].click();", element); // this also works :)
    }

    public void scrollTo(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
//      actions.moveToElement(element).perform(); // not reliable
    }
}
