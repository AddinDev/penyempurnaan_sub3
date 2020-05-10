package com.addindev.moviecatalogue.movlist;

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
import com.addindev.moviecatalogue.movlist.pojo.ItemResult;
import com.addindev.moviecatalogue.movlist.pojo.MovResp;

public class MovListFrag extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    private Observer<MovResp> getMovies = new Observer<MovResp>() {
        @Override
        public void onChanged(MovResp responseMovies) {

            if(responseMovies!=null){
                if(responseMovies.getAnError()==null){
                    MovListAdapt mAdapter = new MovListAdapt(responseMovies.getResults());
                    mAdapter.SetOnItemClickListener(new MovListAdapt.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ItemResult model) {
                            Intent goToDetailMovie = new Intent(view.getContext(), MovDetail.class);
                            goToDetailMovie.putExtra(MovDetail.SELECTED_MOVIE,model);
                            startActivity(goToDetailMovie);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);

                }else{

                    alertDialog.setMessage(responseMovies.getAnError().getMessage());
                    alertDialog.show();

                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_movies_fragment, container, false);

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
        MovListViMo mViewModel = ViewModelProviders.of(this).get(MovListViMo.class);
        mViewModel.doRequestListMovies();
        mViewModel.getMovies().observe(this,getMovies);
    }

}
