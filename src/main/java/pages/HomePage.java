package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends PagesBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public VideogamesPage goToVideoGamesPage(){
        navbar.openSidebar();
        sidebar.openVideogamesPage();

        return new VideogamesPage(driver);
    }
}
