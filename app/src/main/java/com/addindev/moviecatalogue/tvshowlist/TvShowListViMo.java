package com.addindev.moviecatalogue.tvshowlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.addindev.moviecatalogue.MovieCatalogue;
import com.addindev.moviecatalogue.tvshowlist.pojo.TvShowResp;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class TvShowListViMo extends ViewModel {

    private MutableLiveData<TvShowResp> responseTvShows = new MutableLiveData<>();

    MutableLiveData getTvShowList() {

        if (responseTvShows == null) {
            doRequestListTvShows();
        }
        return responseTvShows;
    }

    void doRequestListTvShows() {

        AndroidNetworking.get("https://api.themoviedb.org/3/discover/tv")
                .addQueryParameter("api_key", MovieCatalogue.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(TvShowResp.class, new ParsedRequestListener<TvShowResp>() {

                    @Override
                    public void onResponse(TvShowResp response) {
                        responseTvShows.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseTvShows.setValue(new TvShowResp(anError));
                    }
                });
    }
}
