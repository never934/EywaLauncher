package com.eywa_kitchen.eywalauncher.Recommendations;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eywa_kitchen.eywalauncher.MainScreen.MainView;
import com.eywa_kitchen.eywalauncher.R;
import com.eywa_kitchen.eywalauncher.Recommendations.FoodAdapter.FoodAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.NewsAdapter.NewsAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.TvAdapter.TvAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.YoutubeAdapter.YoutubeAdapter;
import com.eywa_kitchen.eywalauncher.utils.DividerItemDecoration;


public class RecommendationsAdapter extends RecyclerView.Adapter<RecommendationsAdapter.RecommendationsViewHolder> {

    private YoutubeAdapter VideoAdapter;
    private NewsAdapter NewsAdapter;
    private TvAdapter TvAdapter;
    private FoodAdapter FoodAdapter;
    private int CardNumber;
    private final int COUNT_OF_CARDS = 4;

    public RecommendationsAdapter(YoutubeAdapter VideoAdapter, NewsAdapter NewsAdapter){
        this.VideoAdapter = VideoAdapter;
        this.NewsAdapter = NewsAdapter;
        this.TvAdapter = new TvAdapter();
        this.FoodAdapter = new FoodAdapter();
        CardNumber=0;
    }

    @NonNull
    @Override
    public RecommendationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (CardNumber){
            case 0:
                return InflateLayout(R.layout.rec_item, viewGroup);
            case 1:
                return InflateLayout(R.layout.rec_item_news, viewGroup);
            case 2:
                return InflateLayout(R.layout.rec_item_tv, viewGroup);
            case 3:
                return InflateLayout(R.layout.rec_item_food_offer, viewGroup);
            default:
                return InflateLayout(R.layout.rec_item_empty, viewGroup);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationsViewHolder recViewHolder, int i) {
        LinearLayoutManager LinearLayoutManagerApp = new LinearLayoutManager(MainView.MainContext, LinearLayoutManager.VERTICAL, false);
        switch (i){
            case 0:
                recViewHolder.Reclist.setLayoutManager(LinearLayoutManagerApp);
                recViewHolder.Reclist.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(MainView.MainContext, R.drawable.youtube_video_devider)));
                recViewHolder.Reclist.setAdapter(VideoAdapter);
                break;
            case 1:
                recViewHolder.Reclist.setLayoutManager(LinearLayoutManagerApp);
                recViewHolder.Reclist.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(MainView.MainContext, R.drawable.items_divider)));
                recViewHolder.Reclist.setAdapter(NewsAdapter);
                break;
            case 2:
                recViewHolder.Reclist.setLayoutManager(new GridLayoutManager(MainView.MainContext,3));
                recViewHolder.Reclist.setAdapter(TvAdapter);
                break;
            case 3:
                recViewHolder.Reclist.setLayoutManager(LinearLayoutManagerApp);
                recViewHolder.Reclist.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(MainView.MainContext, R.drawable.items_divider)));
                recViewHolder.Reclist.setAdapter(FoodAdapter);
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public int getItemCount() {
        return COUNT_OF_CARDS;
    }

    private RecommendationsViewHolder InflateLayout(int link, ViewGroup viewGroup){
        View v;
        RecommendationsViewHolder avh;

        v = LayoutInflater.from(viewGroup.getContext()).inflate(link, viewGroup, false);
        avh = new RecommendationsViewHolder(v);
        CardNumber++;
        return avh;
    }

    protected class RecommendationsViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView Reclist;

        RecommendationsViewHolder(View itemView) {
            super(itemView);
            Reclist = (RecyclerView)itemView.findViewById(R.id.Content);
        }



    }
}
