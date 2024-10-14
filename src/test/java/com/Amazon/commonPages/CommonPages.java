package com.Amazon.commonPages;

import com.Amazon.Pages.SearchPage;

public class CommonPages {

    private SearchPage searchPage;

    public SearchPage getSearchPageCMS() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }
}
