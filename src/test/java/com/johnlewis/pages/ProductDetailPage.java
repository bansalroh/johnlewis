package com.johnlewis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.johnlewis.util.Utility.*;

public class ProductDetailPage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    By qtyBox = By.id(getElementFromProperties("productDetailPage_qtyBox_id"));
    By addtocartbutton = By.id(getElementFromProperties("productDetailPage_addToCartButton_id"));
    By carticon = By.cssSelector(getElementFromProperties("productDetailPage_cartIcon_cssSelector"));


    public void pdpIncreaseQuantity(String qty) {
        waitForPresenceOfElementLocated(qtyBox);
        scrollpageupdown(0, 300);
        WebElement qtybox = driver.findElement(qtyBox);
        qtybox.click();
        qtybox.clear();
        qtybox.sendKeys(qty);
    }


    public void pdpToAddToCart() {
        waitForPresenceOfElementLocated(addtocartbutton);
        driver.findElement(addtocartbutton).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scrollpageupdown(0, -250);
    }

    public CartPage clickAndContinue() {
        driver.findElement(carticon).click();
        CartPage cart = new CartPage(driver);
        return cart;
    }

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }
}