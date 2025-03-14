package tests;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.VideogamesPage;

import java.util.HashMap;
import java.util.List;

public class LoginTest extends TestsBase {
    public LoginTest(){
        log = LogManager.getLogger(LoginTest.class);
    }
    @Test(priority=1)
    public void AddingItemsToCart()
    {
        HomePage homePage = new HomePage(driver);
        VideogamesPage videogamesPage = new VideogamesPage(driver);
        homePage.goToVideoGamesPage().applyFilters().sort();


        HashMap<String, Integer> itemsAddedToCart = videogamesPage.addItemsToCart();
        if(itemsAddedToCart.isEmpty())
        {
            videogamesPage.goToSecondSearchResultsPage();
        }


        itemsAddedToCart = videogamesPage.addItemsToCart();
        int  noOfItemsAddedToTheCart_expected = itemsAddedToCart.size();

        /*
            CART
         */
        CartPage cartPage = homePage.navbar.openCart();
        int  noOfItemsInTheCart_actual = cartPage.countCartItems();
        int  sumOfItemsInTheCart_expected = cartPage.calculateCartSum();
        int  subtotal_actual = cartPage.getActualSubTotal();


        log.info("(1) VALIDATING THAT: actual number of items in the cart =" + noOfItemsInTheCart_actual + " equals the expected = " + noOfItemsAddedToTheCart_expected);
        Assert.assertEquals(noOfItemsInTheCart_actual, noOfItemsAddedToTheCart_expected);
        log.info("(2) VALIDATING THAT: total price of the items in the cart =" + subtotal_actual + " equals the expected = " + sumOfItemsInTheCart_expected);
        Assert.assertEquals(subtotal_actual, sumOfItemsInTheCart_expected);
    }
}
