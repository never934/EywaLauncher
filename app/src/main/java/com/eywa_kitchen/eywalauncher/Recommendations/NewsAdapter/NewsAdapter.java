package com.eywa_kitchen.eywalauncher.Recommendations.NewsAdapter;



import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.MainScreen.MainView;
import com.eywa_kitchen.eywalauncher.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> news;

    NewsAdapter(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        NewsAdapter.NewsViewHolder avh = new NewsAdapter.NewsViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.NewsViewHolder newsViewHolder, final int i) {
        newsViewHolder.Title.setText(news.get(i).title);
        newsViewHolder.Author.setText(news.get(i).publisher);
        newsViewHolder.Preview.setImageBitmap(news.get(i).image);


        newsViewHolder.NewsItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainView.MainContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(news.get(i).ClickUrl)));
                }catch (Exception e){
                    Log.e("NewsAdapter", "couldn't open link " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView Preview;
        private TextView Author;
        private TextView Title;
        private ConstraintLayout NewsItemContainer;

         NewsViewHolder(View itemView) {
            super(itemView);
            Preview = (ImageView)itemView.findViewById(R.id.previewContent);
            Author = (TextView)itemView.findViewById(R.id.Author);
            Title = (TextView)itemView.findViewById(R.id.title);
            NewsItemContainer = (ConstraintLayout)itemView.findViewById(R.id.NewsItemContainer);

        }

    }
}
