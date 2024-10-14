package com.Amazon.testcases;

import com.Amazon.common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import com.Amazon.Pages.SearchPage;

@Epic("Regression Test Amazon")
@Feature("Search Test")
public class SearchTest extends BaseTest {

    String Model ;
    String variant ;
    String title;

    @Test(priority = 1)
    public void TC_To_Verify_Search_Result_on_Iphone() throws Throwable {
        Model = CONFIG.getProperty("Model");
        variant = CONFIG.getProperty("Variant");
        title = CONFIG.getProperty("Title");
        getSearchPageCMS().UserChangeDropDownToElectronic("Electronics");
        getSearchPageCMS().AmazonSearchPage(Model);
        getSearchPageCMS().getAllTheDropdownSuggestionsAndValidateAllAreRelatedToSearchedProduct(Model);
        getSearchPageCMS().typeAgainVariantAndClickVariantFromDropdownResult(variant);
        getSearchPageCMS().fromResultsClickOnTheSearchedProductAndValidateNewTabIsOpened(title);
        getSearchPageCMS().navigateToNextTabAndClickOnVisitTheAppleStoreLinkAppearsBelowAppleVariant();
        getSearchPageCMS().clickOnAppleWatchDropdownAndSelectAppleWatchSE();
        getSearchPageCMS().hoverOnWatchImageAndVerifyQuickLookIsDisplayedForTheWatch();
        getSearchPageCMS().verifyNewlyOpenedModalIsRelatedToSameProductForWhichQuickLookIsPerformed();

    }

}
