package tests;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.VideogamesPage;

import java.util.HashMap;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class LoginTest extends TestsBase {
    public LoginTest(){
        log = LogManager.getLogger(LoginTest.class);
    }
    @Test(priority=1)
    @Description("This tests adds video games to the cart and verifies the cart number of items and sum")
    @Severity(CRITICAL)
    @Owner("Muhammad Mamduh")
    @Link(name = "Website", url = "https://www.amazon.eg/-/en/%D8%A3%D9%84%D8%B9%D8%A7%D8%A8-%D8%A7%D9%84%D9%81%D9%8A%D8%AF%D9%8A%D9%88/b/?ie=UTF8&node=18022560031&ref_=nav_cs_videogames")
    public void AddingItemsToCart()
    {
        HomePage homePage = new HomePage(driver);
        VideogamesPage videogamesPage = new VideogamesPage(driver);
        homePage.goToVideoGamesPage().applyFilters().sort();


        HashMap<String, Integer> itemsAddedToCart = videogamesPage.addSuitableItemsToCart();
        if(itemsAddedToCart.isEmpty())
        {
            log.info("The cart is empty, then, NONE of the items from the first page was suitable so we go to the second page");
            videogamesPage.goToSecondSearchResultsPage();
        }


        itemsAddedToCart = videogamesPage.addSuitableItemsToCart();
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
