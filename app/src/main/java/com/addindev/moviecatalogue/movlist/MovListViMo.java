package com.addindev.moviecatalogue.movlist;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.addindev.moviecatalogue.MovieCatalogue;
import com.addindev.moviecatalogue.movlist.pojo.MovResp;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class MovListViMo extends ViewModel {

    private MutableLiveData<MovResp> responseMovies = new MutableLiveData<>();


    public MutableLiveData getMovies(){

        if(responseMovies==null){
            doRequestListMovies();
        }

        return responseMovies;

    }

    public void doRequestListMovies(){

        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", MovieCatalogue.MOVIE_DB_API_KEY)
                .addQueryParameter("language","en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(MovResp.class, new ParsedRequestListener<MovResp>() {
                    @Override
                    public void onResponse(MovResp response) {
                        responseMovies.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseMovies.setValue(new MovResp(anError));
                    }
                });
    }

}
