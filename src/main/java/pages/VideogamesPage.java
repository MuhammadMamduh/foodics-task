package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideogamesPage extends PagesBase{
    public VideogamesPage(WebDriver driver) {
        super(driver);
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
    List<WebElement> searchResultsList;

    HashMap<String, Integer> productsAddedToCartList = new HashMap<>();

    @FindBy(xpath = "//span[@class=\"a-list-item\"]//a[(text() = '2')]")
    WebElement secondSearchResultsPageLink;


    public VideogamesPage applyFilters(){
        commandsHandler.click(freeShippingChkbx);
        commandsHandler.click(NewConditionBtn);

        return this;
    }

    public VideogamesPage sort(){
        Select dropdown = new Select(sortingDropdown);

        commandsHandler.clickObscured(sortingDropdown);
        commandsHandler.clickObscured(priceHighToLowFilter);

        // essential-step
        driver.navigate().refresh();
        return this;
    }

    public HashMap<String, Integer> addItemsToCart(){
        /*
            INITIALIZE
         */
        WebElement productTitleElement;
        String productTitle;
        WebElement productPriceElement;
        int productPrice;
        WebElement addToCartBtn;

        /*
            LOOP OVER THE PRODUCTS
         */
        for (WebElement searchResult : searchResultsList) {
            commandsHandler.scrollTo(searchResult);
            System.out.println("Scrolling search results ...");

            try {
                productTitleElement = searchResult.findElement(By.xpath(".//div[@data-cy=\"title-recipe\"]"));
                productPriceElement = searchResult.findElement(By.className("a-price-whole")); // throws an exception if the price element is not found
                addToCartBtn = searchResult.findElement(By.xpath(".//button[text()='Add to cart']")); // throws an exception if the button element not found

                productTitle = productTitleElement.getText();
                productPrice = Integer.parseInt(productPriceElement.getText().replace(",", ""));

                /*
                    ADD SUITABLE ITEMS TO CART
                 */
                if (productPrice < 15000)
                {
                    System.out.println("************ Price is less than 15000 ************");
                    productsAddedToCartList.put(productTitle, productPrice);

                    commandsHandler.scrollTo(addToCartBtn);
                    commandsHandler.click(addToCartBtn);
                }
            }
            catch (NoSuchElementException e) {
                System.out.println("Either this product does NOT have a price or it does NOT have an Add to Cart button");
//                System.out.println(e.getMessage());
            }
        }

        // essential-step
        driver.navigate().refresh();
        return productsAddedToCartList;
    }

    public VideogamesPage goToSecondSearchResultsPage(){
        commandsHandler.click(secondSearchResultsPageLink);

        // essential-step
        driver.navigate().refresh();
        return this;
    }
}
