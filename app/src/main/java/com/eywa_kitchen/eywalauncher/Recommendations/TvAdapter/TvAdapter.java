package com.eywa_kitchen.eywalauncher.Recommendations.TvAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eywa_kitchen.eywalauncher.R;


public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private int COUNT_OF_CHANNELS = 16;


    public TvAdapter() {

    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_list_item, viewGroup, false);
        TvViewHolder avh = new TvViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder tvViewHolder, final int i) {
        switch (i){
            case 0:
                tvViewHolder.icon.setImageResource(R.drawable.ic_domashniy_tv);
                break;
            case 1:
                tvViewHolder.icon.setImageResource(R.drawable.ic_mtv_tv);
                break;
            case 2:
                tvViewHolder.icon.setImageResource(R.drawable.ic_mus_tv);
                break;
            case 3:
                tvViewHolder.icon.setImageResource(R.drawable.ic_otr_2013_tv);
                break;
            case 4:
                tvViewHolder.icon.setImageResource(R.drawable.ic_semyorka_tv);
                break;
            case 5:
                tvViewHolder.icon.setImageResource(R.drawable.ic_2x2_tv);
                break;
            case 6:
                tvViewHolder.icon.setImageResource(R.drawable.ic_sts_tv);
                break;
            case 7:
                tvViewHolder.icon.setImageResource(R.drawable.ic_animal_planet_tv);
                break;
            case 8:
                tvViewHolder.icon.setImageResource(R.drawable.ic_nickelodeon_tv);
                break;
            case 9:
                tvViewHolder.icon.setImageResource(R.drawable.ic_ren_tv);
                break;
            case 10:
                tvViewHolder.icon.setImageResource(R.drawable.ic_first_tv);
                break;
            case 11:
                tvViewHolder.icon.setImageResource(R.drawable.ic_zvezda_tv);
                break;
            case 12:
                tvViewHolder.icon.setImageResource(R.drawable.ic_match_tv);
                break;
            case 13:
                tvViewHolder.icon.setImageResource(R.drawable.ic_ntv_tv);
                break;
            case 14:
                tvViewHolder.icon.setImageResource(R.drawable.ic_russia1_tv);
                break;
            case 15:
                tvViewHolder.icon.setImageResource(R.drawable.ic_tnt_tv);
                break;
            case 16:
                tvViewHolder.icon.setImageResource(R.drawable.ic_che_tv);
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return COUNT_OF_CHANNELS;
    }

    class TvViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;

        TvViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.ChannelImage);

        }

    }
}
