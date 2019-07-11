package com.eywa_kitchen.eywalauncher.Recommendations.FoodAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private int COUNT_OF_ORDERS = 3;


    public FoodAdapter() {

    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_list_item, viewGroup, false);
        FoodViewHolder avh = new FoodViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, final int i) {
        switch (i){
            case 0:
                foodViewHolder.foodAuthor.setText("Якитория");
                foodViewHolder.foodName.setText("Сакура сет");
                foodViewHolder.foodPreview.setImageResource(R.drawable.sacura_set);
                break;
            case 1:
                foodViewHolder.foodAuthor.setText("Якитория");
                foodViewHolder.foodName.setText("Сет с лососем");
                foodViewHolder.foodPreview.setImageResource(R.drawable.set_losos);
                break;
            case 2:
                foodViewHolder.foodAuthor.setText("Пекарня Чегем");
                foodViewHolder.foodName.setText("Осетинский пирог с мясом «Традиционный»");
                foodViewHolder.foodPreview.setImageResource(R.drawable.pirog);
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return COUNT_OF_ORDERS;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView foodPreview;
        TextView foodName;
        TextView foodAuthor;

        FoodViewHolder(View itemView) {
            super(itemView);
            foodPreview = itemView.findViewById(R.id.previewContent);
            foodName = itemView.findViewById(R.id.foodName);
            foodAuthor = itemView.findViewById(R.id.RestName);
        }

    }
}
