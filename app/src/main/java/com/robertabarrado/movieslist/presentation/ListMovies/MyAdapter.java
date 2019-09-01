package com.robertabarrado.movieslist.presentation.ListMovies;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robertabarrado.domain.Movie;
import com.robertobarrado.movieslist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TitleHolder> {
    private List<Movie> mDataset;
    private static RecyclerItemClickListener.OnItemClickListener listener;


    static class TitleHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RatingBar rbVotes;


        TitleHolder(final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.movie_title);
            tvOverview = itemView.findViewById(R.id.movie_overview);
            ivPoster = itemView.findViewById(R.id.movie_poster);
            rbVotes = itemView.findViewById(R.id.movie_ratingBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLongItemClick(itemView, getPosition());
                }
            });
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    MyAdapter(RecyclerItemClickListener.OnItemClickListener listener) {
        mDataset = new ArrayList<>();
        MyAdapter.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        return new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view, null));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TitleHolder holder, int position) {

        Movie movie = mDataset.get(position);
        holder.tvTitle.setText(movie.getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getPoster_path()).fit().into(holder.ivPoster);
        holder.rbVotes.setRating((float)movie.getVote_average()/2);
        holder.tvOverview.setText(movie.getOverview());

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void refresh(List<Movie> newMovies) {
        mDataset.addAll(newMovies);
        notifyDataSetChanged();
    }

    Movie getItem(int position) {
        return mDataset.get(position);
    }
}
