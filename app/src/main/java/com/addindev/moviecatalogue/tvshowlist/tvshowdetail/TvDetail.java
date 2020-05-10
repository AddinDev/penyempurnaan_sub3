package com.addindev.moviecatalogue.tvshowlist.tvshowdetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.addindev.moviecatalogue.R;
import com.addindev.moviecatalogue.databinding.ActivityTvShowsBinding;
import com.addindev.moviecatalogue.tvshowlist.pojo.ItemResult;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class TvDetail extends AppCompatActivity {

    public static final String SELECTED_TV_SHOWS = "selected_tv_shows";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TvDetailViMo viewModel = ViewModelProviders.of(this).get(TvDetailViMo.class);
        ActivityTvShowsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows);
        ItemResult movieModel = getIntent().getParcelableExtra(SELECTED_TV_SHOWS);
        viewModel.setResultsItem(movieModel);

        binding.setTitle(viewModel.getResultsItem().getName());
        binding.setOriginalLanguage(viewModel.getResultsItem().getOriginalLanguage());
        binding.setReleaseDate(viewModel.getResultsItem().getFirstAirDate());
        binding.setOverview(viewModel.getResultsItem().getOverview());
        binding.setVote(String.valueOf(viewModel.getResultsItem().getVoteCount()));

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+movieModel.getPosterPath()).into(binding.imgPoster);
        setTitle(viewModel.getResultsItem().getName());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

