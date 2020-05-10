package com.addindev.moviecatalogue.tvshowlist.pojo;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("com.robohorse.robopojogenerator")
public class TvShowResp {
	private ANError anError;

	public TvShowResp(ANError anError) {
		this.anError = anError;
	}

	public TvShowResp() {
	}

	public ANError getAnError() {
		return anError;
	}

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ItemResult> results;

	@SerializedName("total_results")
	private int totalResults;

	public List<ItemResult> getResults(){
		return results;
	}

	@NonNull
	@Override
 	public String toString(){
		return 
			"TvShowResp{" +
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}