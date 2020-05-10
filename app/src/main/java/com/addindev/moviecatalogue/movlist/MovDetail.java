package com.addindev.moviecatalogue.movlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.addindev.moviecatalogue.R;
import com.addindev.moviecatalogue.databinding.ActivityDetailMovieBinding;
import com.addindev.moviecatalogue.movlist.movdetail.MovDetailViMo;
import com.addindev.moviecatalogue.movlist.pojo.ItemResult;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class MovDetail extends AppCompatActivity {

    public static final String SELECTED_MOVIE = "selected_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        MovDetailViMo viewModel = ViewModelProviders.of(this).get(MovDetailViMo.class);
        ActivityDetailMovieBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie);
        ItemResult movieModel = getIntent().getParcelableExtra(SELECTED_MOVIE);
        viewModel.setResultsItem(movieModel);
        binding.setViewmodel(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+movieModel.getPosterPath()).into(binding.imgPoster);
        setTitle(viewModel.getResultsItem().getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return true;

    }
}

