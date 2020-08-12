package com.johnlewis.pages;


import org.openqa.selenium.*;

import static com.johnlewis.util.Utility.*;


public class CartPage {
    WebDriver driver;
    By alertPopup = By.xpath(getElementFromProperties("anyPage_alertPopUp_xpath"));
    By quantityreducebutton = By.className(getElementFromProperties("cartPage_quantityReduceButton_className"));
    By cartemptytext = By.xpath(getElementFromProperties("cartPage_cartEmptyText_xpath"));
    By removequantitylink = By.className(getElementFromProperties("cartPage_removeQuantityLink_className"));


    public void reduceQuantity() {

        waitForPresenceOfElementLocated(quantityreducebutton);
        WebElement qtyreductbutton = driver.findElement(quantityreducebutton);
        qtyreductbutton.click();

    }

    public void removeItem() {
        waitForPresenceOfElementLocated(removequantitylink);
        WebElement qtyremovelink = driver.findElement(removequantitylink);
        qtyremovelink.click();
        waitForVisibleElement(driver.findElement(cartemptytext));

    }

    public Boolean cartEmptyTextPresent() {
        try {
            driver.findElement(cartemptytext);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
}
