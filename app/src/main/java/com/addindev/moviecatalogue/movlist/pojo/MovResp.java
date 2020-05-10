package com.addindev.moviecatalogue.movlist.pojo;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("com.robohorse.robopojogenerator")
public class MovResp {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ItemResult> results;

	@SerializedName("total_results")
	private int totalResults;

	private ANError anError;

	public ANError getAnError() {
		return anError;
	}

	public MovResp() {
	}

	public MovResp(ANError anError) {
		this.anError = anError;
	}

    public List<ItemResult> getResults(){
		return results;
	}

    @NonNull
	@Override
 	public String toString(){
		return 
			"MovResp{" +
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}