package com.addindev.moviecatalogue.tvshowlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addindev.moviecatalogue.R;
import com.addindev.moviecatalogue.tvshowlist.pojo.ItemResult;
import com.addindev.moviecatalogue.tvshowlist.pojo.TvShowResp;
import com.addindev.moviecatalogue.tvshowlist.tvshowdetail.TvDetail;

public class TvShowListFrag extends Fragment {

    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer<TvShowResp> getTvShows = new Observer<TvShowResp>() {
        @Override
        public void onChanged(TvShowResp responseTvShows) {

            if(responseTvShows!=null){
                if(responseTvShows.getAnError()==null){
                    TvShowListAdapt mAdapter = new TvShowListAdapt(responseTvShows.getResults());
                    mAdapter.SetOnItemClickListener(new TvShowListAdapt.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ItemResult model) {
                            Intent goToDetailMovie = new Intent(view.getContext(), TvDetail.class);
                            goToDetailMovie.putExtra(TvDetail.SELECTED_TV_SHOWS,model);
                            startActivity(goToDetailMovie);
                        }
                    });

                    recyclerView.setAdapter(mAdapter);

                }else{

                    alertDialog.setMessage(responseTvShows.getAnError().getMessage());
                    alertDialog.show();

                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_tv_show_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        TvShowListViMo mViewModel = ViewModelProviders.of(this).get(TvShowListViMo.class);
        mViewModel.doRequestListTvShows();
        mViewModel.getTvShowList().observe(this, getTvShows);
    }
}
