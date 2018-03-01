package com.albertou.study.doubantop250;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.albertou.study.doubantop250.model.api.ApiClient;
import com.albertou.study.doubantop250.model.entity.Cast;
import com.albertou.study.doubantop250.model.entity.Director;
import com.albertou.study.doubantop250.model.entity.Movie;
import com.albertou.study.doubantop250.model.entity.Top250Res;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oujianfeng on 2018/3/1.
 */

public class Top250Fragment extends Fragment{

    private static final String TAG = "Top250Fragment";

    private RecyclerView top250RecyclerView;

    protected static Fragment newInstance(){
        return new Top250Fragment();
    }

    private List<Movie> mMovieList =new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<Top250Res> observable = ApiClient.service.getTop250(0,30);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Top250Res>() {
                    @Override
                    public void accept(Top250Res top250Res) throws Exception {
                        mMovieList.addAll(top250Res.getSubjects());
                        setAdapter();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "onFailure: "+throwable.getMessage(),throwable);
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top250,container,false);
        top250RecyclerView = view.findViewById(R.id.top250_recycler_view);
        top250RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        return view;
    }

    private void setAdapter(){
        if(isAdded()){
            top250RecyclerView.setAdapter(new MovieAdapter(mMovieList));
        }
    }

    private class MovieRow extends RecyclerView.ViewHolder{

        private Movie movie;
        private SimpleDraweeView movieImage;
        private TextView movieTitle;
        private RatingBar movieRatingBar;
        private TextView movieRate;
        private TextView directors;
        private TextView casts;
        private TextView genres;
        private TextView year;

        public MovieRow(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_top250, parent, false));
            this.movieImage = itemView.findViewById(R.id.movie_image);
            this.movieTitle = itemView.findViewById(R.id.movie_title);
            this.movieRatingBar = itemView.findViewById(R.id.movie_rating_bar);
            this.movieRate = itemView.findViewById(R.id.movie_rate);
            this.directors = itemView.findViewById(R.id.directors);
            this.casts = itemView.findViewById(R.id.casts);
            this.genres = itemView.findViewById(R.id.genres);
            this.year = itemView.findViewById(R.id.year);
        }

        public void bind(Movie movie){
            this.movie = movie;
            this.movieImage.setImageURI(Uri.parse(movie.getImage().getSmall()));
            this.movieTitle.setText(movie.getTitle());
            this.movieRatingBar.setRating((movie.getRating().getAverage() * 5f) / 10f);
            this.movieRate.setText(movie.getRating().getAverage()+"");
            this.directors.setText(combinationDirectors());
            this.casts.setText(combinationCasts());
            this.genres.setText(combinationGenres());
            this.year.setText("年份："+ movie.getYear());
        }

        private String combinationDirectors(){
            StringBuilder sb = new StringBuilder("导演：");
            for(Director director: movie.getDirectors()){
                sb.append(director.getName()).append(" / ");
            }
            if(sb.toString().endsWith(" / ")){
                return sb.substring(0,sb.length()-3);
            }else{
                return sb.toString();
            }
        }

        private String combinationCasts(){
            StringBuilder sb = new StringBuilder("主演：");
            for(Cast cast: movie.getCasts()){
                sb.append(cast.getName()).append(" / ");
            }
            if(sb.toString().endsWith(" / ")){
                return sb.substring(0,sb.length()-3);
            }else{
                return sb.toString();
            }
        }

        private String combinationGenres(){
            StringBuilder sb = new StringBuilder("类型：");
            for(String genre: movie.getGenres()){
                sb.append(genre).append(" / ");
            }
            if(sb.toString().endsWith(" / ")){
                return sb.substring(0,sb.length()-3);
            }else{
                return sb.toString();
            }
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieRow>{
        private List<Movie> movieList;

        public MovieAdapter(List<Movie> movieList) {
            this.movieList = movieList;
        }

        @Override
        public MovieRow onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MovieRow(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MovieRow holder, int position) {
            Movie movie = movieList.get(position);
            holder.bind(movie);
        }

        @Override
        public int getItemCount() {
            return movieList==null?0:movieList.size();
        }
    }
}
