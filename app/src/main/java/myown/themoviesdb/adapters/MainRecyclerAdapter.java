package myown.themoviesdb.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import myown.themoviesdb.Constants;
import myown.themoviesdb.R;
import myown.themoviesdb.adapters.viewholders.MainRecyclerViewHolder;
import myown.themoviesdb.models.MoviesResponse;
import myown.themoviesdb.utils.NavigationController;

/**
 * Created by Netaq on 10/5/2017.
 *
 * This Recycler Adapter is responsible for setting up the main recycler view in Main Activity.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {


    ArrayList<MoviesResponse.Result> mDataSet;
    Context mContext;

    /**
     *
     * @param mDataSet is the list of movies returned by Get Popular Movies BAL.
     * @param mContext is the context of the parent activity which is MainActivity
     */
    public MainRecyclerAdapter(ArrayList<MoviesResponse.Result> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MainRecyclerViewHolder viewHolder = new MainRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item, parent, false));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, final int position) {

        holder.movieName.setText(mDataSet.get(position).getTitle());
        holder.moviePoster.setImageURI(Uri.parse(Constants.IMAGE_BASE_URL + mDataSet.get(position).getPoster_path()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call the navigation controller to start movie details activity
                NavigationController.startMovieDetailsActivity(mContext, mDataSet.get(position).getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     *
     * @param moreMovies will contain the list of additional movies loaded in case of infinite scroll.
     */
    public void loadMore (ArrayList<MoviesResponse.Result> moreMovies){

        for (MoviesResponse.Result mr : moreMovies){

            mDataSet.add(mr);

        }
    }
}
