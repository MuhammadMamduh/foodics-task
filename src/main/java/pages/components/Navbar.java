package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CartPage;

public class Navbar extends AbstractComponent{

    public Navbar(final WebDriver driver){
        super(driver);
    }

    @FindBy(id = "navbar")
    WebElement navbar;

    @FindBy(id = "nav-hamburger-menu")
    WebElement hamburgerMenuBtn;

    @FindBy(id = "nav-cart")
    WebElement cartBtn;


    public boolean isDisplayed() {
        return navbar.isDisplayed();
    }

    public Sidebar openSidebar(){
        commandsHandler.click(hamburgerMenuBtn);

        return new Sidebar(driver);
    }

    public CartPage openCart(){
        commandsHandler.click(cartBtn);

        return new CartPage(driver);
    }
}
