package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import pages.components.SearchResult;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideogamesPage extends PagesBase{
    public VideogamesPage(WebDriver driver) {
        super(driver);
        log = LogManager.getLogger(VideogamesPage.class);
    }

    @FindBy(xpath = "//ul[@aria-labelledby=\"p_n_free_shipping_eligible-title\"]")
    WebElement freeShippingChkbx;

    @FindBy(xpath = "//ul[@aria-labelledby=\"p_n_condition-type-title\"]//span[contains(text(), 'New')]")
    WebElement NewConditionBtn;

    @FindBy(id = "s-result-sort-select")
    WebElement sortingDropdown;

    @FindBy(id = "s-result-sort-select_2")
    WebElement priceHighToLowFilter;

    @FindBy(xpath = "//div[@data-component-type=\"s-search-result\"]")
    List<WebElement> searchResults;

    SearchResult searchResult;

    HashMap<String, Integer> productsAddedToCart = new HashMap<>();

    @FindBy(xpath = "//span[@class=\"a-list-item\"]//a[(text() = '2')]")
    WebElement secondSearchResultsPageLink;


    public VideogamesPage applyFilters(){
        log.info("Applying filters in [VideogamesPage]");
        commandsHandler.click(freeShippingChkbx);
        commandsHandler.click(NewConditionBtn);

        return this;
    }

    public VideogamesPage sort(){
        log.info("Sorting results in [VideogamesPage]");
        Select dropdown = new Select(sortingDropdown);

        commandsHandler.clickObscured(sortingDropdown);
        commandsHandler.clickObscured(priceHighToLowFilter);

        // essential-step
        driver.navigate().refresh();
        return this;
    }

    public HashMap<String, Integer> addItemsToCart(){
        log.info("Adding items to cart from [VideogamesPage]");
        /*
            LOOP OVER THE PRODUCTS
         */
        for (WebElement result : searchResults) {
            this.searchResult = new SearchResult(driver, result);
//            wait.until(ExpectedConditions.visibilityOf(this.searchResult.searchResult));

            System.out.println("Scrolling search results ...");
            commandsHandler.scrollTo(searchResult.searchResult);

            /*
                ADD SUITABLE ITEMS TO CART
             */
            if (searchResult.price < 15000)
            {
                System.out.println("************ Price is less than 15000 ************");
                if (searchResult.addToCart()) productsAddedToCart.put(searchResult.title, searchResult.price);
            }
        }

        // essential-step
        driver.navigate().refresh();
        return productsAddedToCart;
    }
    public VideogamesPage goToSecondSearchResultsPage(){
        log.info("Navigating to [SecondSearchResultsPage]");
        commandsHandler.click(secondSearchResultsPageLink);

        // essential-step
        driver.navigate().refresh();
        return this;
    }
}
