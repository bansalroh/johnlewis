package com.johnlewis.stepdefinations;

import com.johnlewis.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static com.johnlewis.util.Utility.*;

public class StepDefincation {

    WebDriver driver;
    HomePage home;
    SearchPage search;
    ProductDetailPage pdp;
    CartPage cartpage;


    @Given("Initialize {string} browser version {string}")
    public void initializeHomeBrowser(String browser, String vrsn) {
        //browser will initialize in Utility class
        driver = initializeBrowser(browser, vrsn);
    }

    @Given("Navigate to Johnlewis url")
    public void navigationToSite() {
        //initialized browser will navigate user to provided url
        home = new HomePage(driver);
        home.navigationToSite();
    }

    @And("Click on accept cookies to submit popup")
    public void clickCookiePopup() {
        //User accepting cookies on home page
        home.clickCookiePopup();

    }

    @And("Search {string} in search input to land on search result page")
    public SearchPage searchResultPage(String searchInput) {
        //On Home Page,When user type keyword in search input field and hit enter to land on search result page
        search = new SearchPage(driver);
        home.searchResultPage(searchInput);
        search = home.enterAndContiune();
        return search;
    }

    @When("Select first available product on SRP and land user to Product Details Page")
    public ProductDetailPage srpToPdp() {
        //Navigating to first available product
        pdp = search.clickproduct();
        return pdp;
    }

    @And("Add {string} items to cart")
    public CartPage productToAddToCart(String qty) {
        //On Pdp, User increasing quantity of product
        pdp.pdpIncreaseQuantity(qty);
        // Adding item to cart
        pdp.pdpToAddToCart();
        // On Pdp, User navigating to cart page by click basket icon
        cartpage = pdp.clickAndContinue();
        return cartpage;
    }

    @And("Reduce product quantity from cart")
    public void reduceQty() {
        //On cart Page, reducing 1 item from added  quantity
        cartpage.reduceQuantity();

    }

    @And("Remove items from cart")
    public void removeItem() {
        //removing all available quantities on cart page
        cartpage.removeItem();

    }

    @Then("Check all products has been removed from cart")
    public void emptycartCheck() {
        // Validating that basket is empty now
        Assert.assertTrue("abb",cartpage.cartEmptyTextPresent());

    }

    @Then("Delete cookies from cart and print cookie state in console")
    public void deleteCookiesFromCartPage() {
        //Deleting cookies and printing before/after state on console
        deleteCookies();

    }


    @After
    public void finish(Scenario scenario) {
        // Capturing screenshot of failed scenario and embedding with cucumber report in target folder
        if (scenario.isFailed()) {
            final byte[] screenshot = takeScreenShot();
            scenario.embed(screenshot, "image/png");
        }
        // Final step, After execution closing browser instance
        driver.close();
    }
}

