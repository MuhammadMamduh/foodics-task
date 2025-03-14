package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class HomePage extends PagesBase {
    public HomePage(WebDriver driver) {
        super(driver);
        log = LogManager.getLogger(HomePage.class);
    }

    public VideogamesPage goToVideoGamesPage(){

        navbar.openSidebar();
        sidebar.openVideogamesPage();

        return new VideogamesPage(driver);
    }
}
