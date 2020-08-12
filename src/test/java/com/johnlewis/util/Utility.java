package com.johnlewis.util;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


import java.io.*;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Utility {

    public static JavascriptExecutor js;
    public static WebDriver driver;
    static int time = 30;
    public static WebDriverWait wait;
    static String filePath = "src/test/resources/output/screenshots/screenshot";


    public static WebDriver initializeBrowser(String browser, String vrsn) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            getDriverForMac(browser, vrsn);
        } else {
            getDriverForWindows(browser, vrsn);
        }
        return driver;
    }


    private static void getDriverForWindows(String browser, String vrsn) {
        if (browser.equals("chrome")) {

            if (vrsn.equals("84")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
            }

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            implicitWait(time);
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
            // TODO: Need to implement it
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
            // TODO: Need to implement it
        } else {
            // TODO: Need to implement it for IE
        }
    }

    private static void getDriverForMac(String browser, String vrsn) {
        if (browser.equals("chrome")) {
            if (vrsn.equals("77")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver77");
            } else {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver84");
            }
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            implicitWait(time);
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
            // TODO: Need to implement it
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
            // TODO: Need to implement it
        } else {
            // TODO: Need to implement it for IE
        }
    }


    public static void implicitWait(int waitTime) {
        driver.manage().timeouts()
                .implicitlyWait(waitTime, TimeUnit.SECONDS);
    }


    public static void waitForPresenceOfElementLocated(By element) {
        wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));


    }

    public static void waitForVisibleElement(WebElement element) {
        wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(element));


    }

    public static void deleteCookies() {
        Set<Cookie> allCookies = driver.manage().getCookies();
        System.out.println("list of cookies " + allCookies);
        driver.manage().deleteAllCookies();
        allCookies = driver.manage().getCookies();
        System.out.println("list of cookies after deletion " + allCookies);


    }


    public static byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    public static void scrollpageupdown(int xcordinate, int ycordinate) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + xcordinate + "," + ycordinate + ");", "");
    }

    public static boolean isElementVisible(By by) {
        try {
            driver.findElement(by);
        } catch (Exception e) {
            System.out.println("Element is Not Displayed: " + by);
            return false;
        }
        return true;
    }

    public static String getElementFromProperties(String locator) {
        try {
            Properties obj = new Properties();
            FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/objectrepository/object.properties");
            obj.load(objfile);
            return obj.getProperty(locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void isAlertPresent(WebDriver driver, By elemLocator) {
        try {
            if (driver.findElement(elemLocator).isDisplayed()) {
                driver.switchTo().alert();
                driver.findElement(elemLocator).click();

            }

        } catch (Exception e) {

        }

    }
}