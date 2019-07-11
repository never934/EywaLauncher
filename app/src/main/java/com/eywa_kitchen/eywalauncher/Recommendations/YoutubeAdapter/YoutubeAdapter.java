package com.eywa_kitchen.eywalauncher.Recommendations.YoutubeAdapter;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.MainScreen.MainView;
import com.eywa_kitchen.eywalauncher.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder> {

    private List<VideoDetail> apps;

    YoutubeAdapter(List<VideoDetail> apps) {
        this.apps = apps;
    }


    @NotNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_list_item, viewGroup, false);
        YoutubeViewHolder avh = new YoutubeViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull final YoutubeViewHolder youtubeViewHolder, final int i) {
            youtubeViewHolder.Title.setText(apps.get(i).title);
            youtubeViewHolder.Preview.setImageBitmap(apps.get(i).preview);
            youtubeViewHolder.Author.setText(apps.get(i).author);

            youtubeViewHolder.ViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + apps.get(i).VideoId));
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + apps.get(i).VideoId));
                    try {
                        MainView.MainContext.startActivity(appIntent);
                    } catch (Exception e) {
                        MainView.MainContext.startActivity(webIntent);
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
        return apps.size();
    }

    class YoutubeViewHolder extends RecyclerView.ViewHolder{
        private ImageView Preview;
        private TextView Author;
        private TextView Title;
        private CardView ViewContainer;

        YoutubeViewHolder(View itemView) {
            super(itemView);
            Preview = (ImageView)itemView.findViewById(R.id.previewImage);
            Author = (TextView)itemView.findViewById(R.id.Author);
            Title = (TextView)itemView.findViewById(R.id.title);
            ViewContainer = (CardView)itemView.findViewById(R.id.ViewContainer);
        }
    }
}
