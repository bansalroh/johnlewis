package com.johnlewis.pages;


import org.openqa.selenium.*;

import static com.johnlewis.util.Utility.getElementFromProperties;


public class HomePage {
    WebDriver driver;
    WebElement searchInputField;


    By cookiepopup = By.cssSelector(getElementFromProperties("homePage_cookiePopup_cssSelector"));
    By searchinputfield = By.id(getElementFromProperties("homePage_seachInputField_id"));

    public void navigationToSite() {
        driver.get(getElementFromProperties("url"));
    }

    public void clickCookiePopup() {
        driver.findElement(cookiepopup).click();

    }

    public void searchResultPage(String searchInput) {
        searchInputField = driver.findElement(searchinputfield);
        searchInputField.sendKeys(searchInput);
    }

    public SearchPage enterAndContiune() {
        searchInputField.sendKeys(Keys.RETURN);
        SearchPage searchp = new SearchPage(driver);
        return searchp;
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
}
