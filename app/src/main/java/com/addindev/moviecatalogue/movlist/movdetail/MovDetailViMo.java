package com.addindev.moviecatalogue.movlist.movdetail;

import androidx.lifecycle.ViewModel;

import com.addindev.moviecatalogue.movlist.pojo.ItemResult;

public class MovDetailViMo extends ViewModel {

    private ItemResult resultsItem;

    public ItemResult getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ItemResult resultsItem) {
        this.resultsItem = resultsItem;
    }
}
