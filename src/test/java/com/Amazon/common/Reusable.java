package com.Amazon.common;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.Amazon.common.BaseTest.driver;

public class Reusable {

    private static final String KEYWORD_FAIL = "FAIL";
    private static final String KEYWORD_PASS = "PASS";

    public String navigate(String Data) throws Throwable{
        try {
            driver.navigate().to(Data);
        }catch (Exception e){
            return KEYWORD_FAIL + "Not able to navigae";
        }
        return KEYWORD_PASS;
    }

    public String writeInInput(String object, String data) throws Exception {
        try {
            driver.findElement(By.xpath(object)).sendKeys(data);
        } catch (Exception e) {
            return KEYWORD_FAIL + "Unable to write " + e.getMessage();
        }
        return KEYWORD_PASS;
    }

    public String click(String object, String data) throws Exception {
        try {
            driver.findElement(By.xpath(object)).click();
        } catch (Exception e) {
            return KEYWORD_FAIL + "Not able to click...." + e.getMessage();
        }
        return KEYWORD_PASS;
    }

    public String clear(String object, String data) throws Exception {
        try {
            driver.findElement(By.xpath(object)).clear();
        } catch (Exception e) {
            return KEYWORD_FAIL + "Not able to click...." + e.getMessage();
        }
        return KEYWORD_PASS;
    }

    public String verifyText(String object, String data) throws Exception {
        try {
            String actual = driver.findElement(By.xpath(object)).getText();
            if (actual.isEmpty()) {
                actual = driver.findElement(By.xpath(object)).getAttribute("value");
                System.out.println("actual value->>>>" + actual);
            }
            System.out.println("actual value->>>>" + actual);
            System.out.println("data value->>>>" + data);
            if (actual.equals(data)) {
                return KEYWORD_PASS;
            } else {
                return KEYWORD_FAIL + "...text not verified " + actual + "  " + data;
            }
        } catch (Exception e) {
            return KEYWORD_FAIL + "Object not found...." + e.getMessage();
        }

    }

    public String pause(String object, String data) throws NumberFormatException, InterruptedException {
        long time = (long) Double.parseDouble(object);
        Thread.sleep(time * 1000L);
        return KEYWORD_PASS;

    }

    public String exist(String object, String data) throws Exception {
        try {
            driver.findElement(By.xpath(object));
        } catch (Exception e) {
            return KEYWORD_FAIL + "object does not exist " + e.getMessage();
        }
        return KEYWORD_PASS;
    }

    public String navigateSubMenu(String object, String data) throws Exception {
        try {
            WebElement elem = driver.findElement(By.xpath(object));
            String js = "argements[0].click();";
            ((JavascriptExecutor) driver).executeScript(js, elem);
        } catch (Exception e) {
            return KEYWORD_FAIL + "object does not exist " + e.getMessage();
        }
        return KEYWORD_PASS;
    }

    public String VisibleConditionWait(String object, String data) throws Exception {
        try {
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(120));
            w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
        } catch (Exception e) {
            return KEYWORD_FAIL;
        }
        return KEYWORD_PASS;
    }

    public String invisibleConditionWait(String object, String data) throws Exception {
        try {
            WebDriverWait w = new WebDriverWait(driver,  Duration.ofSeconds(300));
            w.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(object)));
        } catch (Exception e) {
            return KEYWORD_FAIL;
        }
        return KEYWORD_PASS;
    }

    public String MouseFunctions(String object, String data) throws Exception {
        try {
            WebElement Element = driver.findElement(By.xpath(object));
            Actions action = new Actions(driver);
            if (data.equalsIgnoreCase("clickAndHold")) {
                action.moveToElement(Element).build().perform();
                action.clickAndHold(Element).build().perform();
            } else if (data.equalsIgnoreCase("ReleaseMouseClick")) {
                action.moveToElement(Element).build().perform();
                action.release(Element).build().perform();
            } else if (data.equalsIgnoreCase("DoubleClick")) {
                action.doubleClick(Element).build().perform();
            } else if (data.equalsIgnoreCase("MoveToElement")) {
                action.moveToElement(Element);
                action.perform();
            } else if (data.equalsIgnoreCase("RightClick")) {
                action.contextClick(Element).build().perform();
            } else if (data.equalsIgnoreCase("MoveToElementClick")) {
                action.moveToElement(Element).click();
                action.perform();
            }

        } catch (Exception e) {
            return KEYWORD_FAIL + "Not able to clickAndHold...." + e.getMessage();
        }
        return KEYWORD_PASS;
    }

    public void waitForPageToLoad(WebDriver driver, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until((ExpectedCondition<Boolean>) wd -> {
            try {
                assert wd != null;
                return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
            } catch (WebDriverException e) {
                return false;
            }
        });
    }

    public String getText(String object, String data) throws Exception {
        try {
            String actual = driver.findElement(By.xpath(object)).getText();
            if (actual.isEmpty()) {
                actual = driver.findElement(By.xpath(object)).getAttribute("value");
            }
            return actual;
        } catch (Exception e) {
            return KEYWORD_FAIL + "Object not found...." + e.getMessage();
        }

    }


    public String getTitleValue(String object, String data) throws Exception {
        try {
            return driver.findElement(By.xpath(object)).getAttribute("title");
        } catch (Exception e) {
            return KEYWORD_FAIL + "Object not found...." + e.getMessage();
        }
    }

    public String SelectDropDown(String object, String data){
        try {
            Select select = new Select(driver.findElement(By.xpath(object)));
            select.selectByVisibleText(data);
            return KEYWORD_PASS;
        }catch (Exception e){
            return KEYWORD_FAIL;
        }
    }

}
