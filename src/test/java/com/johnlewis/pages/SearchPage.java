package com.johnlewis.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.johnlewis.util.Utility.*;

public class SearchPage {
    WebDriver driver;
    By products = By.className(getElementFromProperties("searchPage_productTile_className"));

    public ProductDetailPage clickproduct() {
        waitForPresenceOfElementLocated(products);
        scrollpageupdown(0, 300);
        List<WebElement> productstile = driver.findElements(products);
        if (null != productstile && !(productstile.isEmpty())) {
            productstile.get(0).click();
        }
        ProductDetailPage pdp = new ProductDetailPage(driver);
        return pdp;
    }


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
}
