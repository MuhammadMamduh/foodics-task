package pages.components;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.VideogamesPage;

public class Sidebar extends AbstractComponent{
    public Sidebar(final WebDriver driver){
        super(driver);
        log = LogManager.getLogger(Sidebar.class);
    }

    @FindBy(id = "hmenu-canvas")
    WebElement sidebar;

    @FindBy(xpath = "//div[contains(text(), 'See all')]")
    WebElement navSeeAllBtn;

    @FindBy(xpath = "//li/a/div[contains(text(), 'Video Games')]")
    WebElement navVideogamesBtn;

    @FindBy(xpath = "//li/a[contains(text(), 'All Video Games')]")
    WebElement subNavAllvideogamesBtn;

    public boolean isDisplayed() {
        return sidebar.isDisplayed();
    }


    public VideogamesPage openVideogamesPage() {
        log.info("Opening [VideogamesPage]");
        commandsHandler.click(navSeeAllBtn);
        commandsHandler.click(navVideogamesBtn);
        commandsHandler.clickObscured(subNavAllvideogamesBtn);
        commandsHandler.clickObscured(subNavAllvideogamesBtn);

        return new VideogamesPage(driver);
    }
}
