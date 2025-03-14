package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends PagesBase {

    public CartPage(WebDriver driver) {
        super(driver);
        log = LogManager.getLogger(CartPage.class);
    }

    @FindBy(className = "sc-list-item-content")
    List<WebElement> cartItems;

    @FindBy(id = "sc-subtotal-amount-buybox")
    WebElement cartSubTotal;


    public int countCartItems() {
        System.out.println("# Cart Items: " + cartItems.size());
        return cartItems.size();
    }

    public int calculateCartSum() {
        int sum = 0;
        String priceStr;
        for (WebElement cartItem : cartItems) {

            priceStr = cartItem.findElement(By.className("sc-item-price-block")).getText();
            priceStr = priceStr.replace("EGP", "");
            priceStr = priceStr.replace(",", "");
            priceStr = priceStr.replace(".00", "");
            System.out.println(priceStr);
            sum += Integer.parseInt(priceStr);
        }
        return sum;
    }

    public int getActualSubTotal() {
        String priceStr = cartSubTotal.getText();
        priceStr = priceStr.replace("EGP", "");
        priceStr = priceStr.replace(",", "");
        priceStr = priceStr.replace(".00", "");
        priceStr = priceStr.replaceAll(" ", "");
        System.out.println(priceStr);
        return Integer.parseInt(priceStr);
    }
}
