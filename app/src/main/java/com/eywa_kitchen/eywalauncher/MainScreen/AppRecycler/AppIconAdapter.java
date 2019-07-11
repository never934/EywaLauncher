package com.eywa_kitchen.eywalauncher.MainScreen.AppRecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppIconAdapter extends RecyclerView.Adapter<AppIconAdapter.AppViewHolder>{

    List<AppDetail> apps;

    public AppIconAdapter(List<AppDetail> apps){
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(com.eywa_kitchen.eywalauncher.R.layout.item_card, viewGroup, false);
        AppViewHolder avh = new AppViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder appViewHolder, int i) {
        appViewHolder.AppName.setText(apps.get(i).label);
        appViewHolder.AppIcon.setImageDrawable(apps.get(i).icon);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        FrameLayout cv;
        TextView AppName;
        ImageView AppIcon;

        AppViewHolder(View itemView) {
            super(itemView);
            cv = (FrameLayout)itemView.findViewById(com.eywa_kitchen.eywalauncher.R.id.card);
            AppName = (TextView)itemView.findViewById(com.eywa_kitchen.eywalauncher.R.id.appName);
            AppIcon = (ImageView)itemView.findViewById(com.eywa_kitchen.eywalauncher.R.id.appImage);
        }

    }

}
