package com.eywa_kitchen.eywalauncher.MainScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.MainScreen.AppRecycler.AppIconAdapter;
import com.eywa_kitchen.eywalauncher.BarGrid.IconAdapter;
import com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler.EywaIconAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.RecommendationsAdapter;

public interface MainContract {

    interface View {
        void showEywaApps(EywaIconAdapter adapter);
        void showCurrentWeather(Bitmap image, int temp);
        void showRecommendations(RecommendationsAdapter VideoAdapter);
        void showTime(String Time, String Date);
        void showBar(IconAdapter mAdapter);
        void hideBar();
        void setStartView();
        void showWeatherInformer(String msg);
        void showRecommendationsInformer(String msg);
    }

    interface Presenter {
        void getResources();
        void getRecommendations();
        void getWeather();
        void getEywaIcons(Context context);
        void startAssistantAnimation(Context context, TextView AssistantView);
        void MenuClicked(Context context);
        void IconClickedInBar(int position, Context context);
        void EywaIconClicked(int position, Context context);
        void AssistantViewClicked(Context context);
    }

    interface Model {
        void getRecommendationsAdapter(MainModel.YoutubeRecommendationsCallback callback);
        void getNewsAdapter(MainModel.NewsCallback callback);
        void getWeather(MainModel.WeatherCallback callback);
        IconAdapter getBarAdapter(Context context);
        EywaIconAdapter getAppAdapter(Context context);
    }
}
