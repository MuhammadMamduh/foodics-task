package pages.components;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResult extends AbstractComponent {

    public WebElement searchResult;

    public WebElement titleElement;
    public String title;
    public  WebElement priceElement;
    public int price;
    public WebElement addToCartBtn;

    public Boolean hasPrice = true;
    public Boolean hasAddToCartBtn = true;


    public SearchResult(final WebDriver driver, WebElement element) {
        super(driver);
        log = LogManager.getLogger(SearchResult.class);
        this.searchResult = element;

        titleElement = searchResult.findElement(By.xpath(".//div[@data-cy=\"title-recipe\"]"));
        title = titleElement.getText();

        try {
            priceElement = searchResult.findElement(By.className("a-price-whole"));
            price = Integer.parseInt(priceElement.getText().replace(",", ""));
        }
        catch (NoSuchElementException e) {
            hasPrice = false;
        }

        try {
            addToCartBtn = searchResult.findElement(By.xpath(".//button[text()='Add to cart']"));
        }
        catch (NoSuchElementException e) {
            hasAddToCartBtn = false;
        }
    }



    public boolean isDisplayed(){ return searchResult.isDisplayed();}

    public Boolean addToCart()
    {
        if (!this.hasPrice || !this.hasAddToCartBtn) {
            System.out.println("Either this product does NOT have a price or it does NOT have an Add to Cart button");
            return false;
        }
        else {
            commandsHandler.scrollTo(this.addToCartBtn);
            commandsHandler.click(this.addToCartBtn);
            System.out.println("Product successfully added to cart");
            return true;
        }
    }
}
