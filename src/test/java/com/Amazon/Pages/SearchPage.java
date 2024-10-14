package com.Amazon.Pages;

import com.Amazon.common.Reusable;
import com.Amazon.commonPages.CommonPages;
import com.Amazon.common.BaseTest;
import com.utility.LogCapture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static com.Amazon.common.BaseTest.*;

public class SearchPage extends CommonPages {

    public void UserChangeDropDownToElectronic(String data) throws Throwable {
        LogCapture.info("User Changes Category Type To " + data + " From Dropdown");
        String vSearch_DropDown = AmazonOR.getProperty("Search_DropDown");
        Assert.assertEquals("PASS", key.SelectDropDown(vSearch_DropDown,data));
    }

    public void AmazonSearchPage(String keyword) throws Throwable {
        LogCapture.info("User Search for a product "+keyword);
        String vSearch_Box = AmazonOR.getProperty("Search_Box");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vSearch_Box,""));
        Assert.assertEquals("PASS", key.click(vSearch_Box,""));
        Assert.assertEquals("PASS", key.clear(vSearch_Box,""));
        Assert.assertEquals("PASS", key.writeInInput(vSearch_Box,keyword));
    }

    public void getAllTheDropdownSuggestionsAndValidateAllAreRelatedToSearchedProduct(String keyword) throws Exception {
        LogCapture.info("Verifying All Search Result Related to "+keyword);
        String vSearch_Suggestion_List = AmazonOR.getProperty("Search_Suggestion_List");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vSearch_Suggestion_List,""));
        List<WebElement> list = driver.findElements(By.xpath(vSearch_Suggestion_List));
        for (WebElement webElement : list) {
            String check = webElement.getText();
            if (check.contains(keyword)) {
                assert true;
            }else {
                assert false;
            }
        }
    }

    public void typeAgainVariantAndClickVariantFromDropdownResult(String Variant) throws Throwable {
        AmazonSearchPage(Variant);
        String vSearchResult = "//*[@aria-label=" + "\"" +Variant.toLowerCase()+ "\"" + "]";
        Assert.assertEquals("PASS", key.VisibleConditionWait(vSearchResult,""));
        Assert.assertEquals("PASS", key.click(vSearchResult,""));
        LogCapture.info("User Clicked on Search result Successfully");
    }

    public void fromResultsClickOnTheSearchedProductAndValidateNewTabIsOpened(String title) throws Exception {
        LogCapture.info("Validation Selected product is opened in new tab");
        String parentWindow = driver.getWindowHandle();
        String vSearch_Result = AmazonOR.getProperty("Search_Result");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vSearch_Result,""));
        Assert.assertEquals("PASS", key.click(vSearch_Result,""));
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                //Constants.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                key.waitForPageToLoad(driver, Duration.ofSeconds(20));
                String newWindowTitle = driver.getTitle();
                assert newWindowTitle != null;
                if (newWindowTitle.equals(title)) {
                    LogCapture.info("New window opened successfully!");
                    assert true;
                } else {
                    LogCapture.info("New window title mismatch!");
                    assert false;
                }
            }
        }
    }

    public void navigateToNextTabAndClickOnVisitTheAppleStoreLinkAppearsBelowAppleVariant() throws Exception {
        LogCapture.info("User is clicking on Apple Store Link");
        String vApple_Store_Link = AmazonOR.getProperty("Apple_Store_Link");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vApple_Store_Link,""));
        Assert.assertEquals("PASS", key.click(vApple_Store_Link,""));
    }

    public void clickOnAppleWatchDropdownAndSelectAppleWatchSE() throws Exception {
        LogCapture.info("User is clicking on Apple Watch Link");
        String vApple_Watch_Tab = AmazonOR.getProperty("Apple_Watch_Tab");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vApple_Watch_Tab,""));
        Assert.assertEquals("PASS", key.MouseFunctions(vApple_Watch_Tab,"MoveToElementClick"));
        LogCapture.info("User is clicking on Apple Watch SE");
        String vApple_Watch_SE = AmazonOR.getProperty("Apple_Watch_SE");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vApple_Watch_SE,""));
        Assert.assertEquals("PASS", key.MouseFunctions(vApple_Watch_SE,"MoveToElementClick"));
    }

    public void hoverOnWatchImageAndVerifyQuickLookIsDisplayedForTheWatch() throws Exception {
        LogCapture.info("User Hover on Apple Watch SE Image");
        String vApple_Watch_SE_Image = AmazonOR.getProperty("Apple_Watch_SE_Image");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vApple_Watch_SE_Image,""));
        Assert.assertEquals("PASS", key.MouseFunctions(vApple_Watch_SE_Image,"MoveToElement"));
        String text = key.getTitleValue(vApple_Watch_SE_Image,"");
        String message = CONFIG.getProperty("Image_Tool_Tip");
        if (text.contains(message)){
            assert true;
        }else {
            assert false;
        }
        String vApple_Watch_SE_QC = AmazonOR.getProperty("Apple_Watch_SE_QC");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vApple_Watch_SE_QC,""));
        Assert.assertEquals("PASS", key.MouseFunctions(vApple_Watch_SE_QC,"MoveToElementClick"));
    }

    public void verifyNewlyOpenedModalIsRelatedToSameProductForWhichQuickLookIsPerformed() throws Exception {
        LogCapture.info("Verifying product opened on Quick Look");
        String vQuick_Look_Label = AmazonOR.getProperty("Quick_Look_Label");
        Assert.assertEquals("PASS", key.VisibleConditionWait(vQuick_Look_Label,""));
        String Product = key.getText(vQuick_Look_Label,"");
        if (Product.contains("Apple Watch SE")){
            assert true;
        }else {
            assert false;
        }
    }

}
