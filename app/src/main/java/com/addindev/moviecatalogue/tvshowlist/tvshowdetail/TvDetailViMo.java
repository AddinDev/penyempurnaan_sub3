package com.addindev.moviecatalogue.tvshowlist.tvshowdetail;

import androidx.lifecycle.ViewModel;

import com.addindev.moviecatalogue.tvshowlist.pojo.ItemResult;


public class TvDetailViMo extends ViewModel {

    private ItemResult resultsItem;

    public ItemResult getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ItemResult resultsItem) {
        this.resultsItem = resultsItem;
    }
}
