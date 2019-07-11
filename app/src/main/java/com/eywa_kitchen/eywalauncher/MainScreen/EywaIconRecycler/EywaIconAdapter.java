package com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.MainScreen.AppRecycler.AppDetail;
import com.eywa_kitchen.eywalauncher.MainScreen.AppRecycler.AppIconAdapter;
import com.eywa_kitchen.eywalauncher.R;

import java.util.List;

public class EywaIconAdapter extends RecyclerView.Adapter<EywaIconAdapter.IconViewHolder> {

    private List<EywaIconDetail> apps;

    public EywaIconAdapter(List<EywaIconDetail> apps) {
        this.apps = apps;
    }

    @NonNull
    @Override
    public EywaIconAdapter.IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(com.eywa_kitchen.eywalauncher.R.layout.eywa_icon_card, viewGroup, false);
        EywaIconAdapter.IconViewHolder avh = new EywaIconAdapter.IconViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull final EywaIconAdapter.IconViewHolder iconViewHolder, int i) {
        iconViewHolder.AppName.setText(apps.get(i).label);
        iconViewHolder.AppIcon.setImageDrawable(apps.get(i).icon);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView AppName;
        ImageView AppIcon;

        IconViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(com.eywa_kitchen.eywalauncher.R.id.AppCard);
            AppName = (TextView) itemView.findViewById(com.eywa_kitchen.eywalauncher.R.id.IconName);
            AppIcon = (ImageView) itemView.findViewById(com.eywa_kitchen.eywalauncher.R.id.IconImage);
        }

    }
}
