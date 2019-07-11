package com.eywa_kitchen.eywalauncher.MainScreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.MainScreen.Assistant.AssistantTextAnimation;
import com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler.EywaIconAdapter;
import com.eywa_kitchen.eywalauncher.MainScreen.Time.TimeController;
import com.eywa_kitchen.eywalauncher.BarGrid.IconAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.NewsAdapter.NewsAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.RecommendationsAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.YoutubeAdapter.YoutubeAdapter;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View View;
    private MainContract.Model Model;
    private Thread AnimationThread, mTimeController;
    private int CompleteTasks=0;
    private YoutubeAdapter YoutubeVideoContainer;
    private NewsAdapter NewsContainer;

    public MainPresenter(MainContract.View View) {
        this.View = View;
        this.Model = new MainModel();
    }


    @Override
    public void getResources() {
        mTimeController = new Thread(new TimeController(new TimeController.TimeCallback() {
            @Override
            public void Updated(String Time, String Date) {
                View.showTime(Time,Date);
            }
        }));
        mTimeController.start();
    }

    @Override
    public void getRecommendations() {
        try {
            Model.getRecommendationsAdapter(new MainModel.YoutubeRecommendationsCallback() {

                @Override
                public void Received(YoutubeAdapter youtubeAdapter) {
                    CompleteTasks++;
                    YoutubeVideoContainer=youtubeAdapter;
                    if(CompleteTasks>=2) {
                        RecommendationsAdapter recAdapter = new RecommendationsAdapter(YoutubeVideoContainer, NewsContainer);
                        CompleteTasks=0;
                        View.showRecommendations(recAdapter);
                    }
                }

                @Override
                public void Error() {
                    View.showRecommendationsInformer("Нет подключения к интернету");
                }

                @Override
                public void Loading() {
                    View.showRecommendationsInformer("Получение рекомендаций");
                }
            });
        }catch (Exception e){
            View.showRecommendationsInformer("Нет подключения к интернету");
        }

        try {
            Model.getNewsAdapter(new MainModel.NewsCallback() {
                @Override
                public void Received(NewsAdapter NewsAdapter) {
                    CompleteTasks++;
                    NewsContainer=NewsAdapter;
                    if(CompleteTasks>=2) {
                        RecommendationsAdapter recAdapter = new RecommendationsAdapter(YoutubeVideoContainer, NewsContainer);
                        CompleteTasks=0;
                        View.showRecommendations(recAdapter);
                    }
                }

                @Override
                public void Error() {
                    View.showRecommendationsInformer("Нет подключения к интернету");
                }
            });
        }catch (Exception e){
            View.showRecommendationsInformer("Нет подключения к интернету");
        }

    }

    @Override
    public void getWeather() {
        try{
            Model.getWeather(new MainModel.WeatherCallback() {

                @Override
                public void Received(Bitmap image, String temp) {
                    int temperature = (int)Float.parseFloat(temp);
                    View.showCurrentWeather(image, temperature);
                }

                @Override
                public void Error() {
                    View.showWeatherInformer("Нет подключения");
                }

                @Override
                public void Loading() {
                    View.showWeatherInformer("Подключение");
                }

            });
        }catch (Exception e){
            View.showWeatherInformer("Нет подключения");
        }
    }

    @Override
    public void getEywaIcons(Context context) {
        EywaIconAdapter AppAdapter = Model.getAppAdapter(context);
        View.showEywaApps(AppAdapter);
    }

    @Override
    public void startAssistantAnimation(Context context, TextView AssistantView) {
        AnimationThread = new Thread(new AssistantTextAnimation(context,AssistantView));
        AnimationThread.start();
    }

    @Override
    public void AssistantViewClicked(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VOICE_COMMAND).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void MenuClicked(Context context) {
        IconAdapter Adapter = Model.getBarAdapter(context);
        View.showBar(Adapter);
    }

    @Override
    public void IconClickedInBar(int position, Context context) {
        if(position==0){
            View.hideBar();
        }else{
            Intent i = context.getPackageManager().getLaunchIntentForPackage(IconAdapter.appsIcons.get(position).name.toString());
            context.startActivity(i);}
    }

    @Override
    public void EywaIconClicked(int position, Context context) {
        switch (position){
            case 0:
                try {
                    Intent i = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                    context.startActivity(i);
                }catch (Exception e){
                    Intent i = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube.tv");
                    context.startActivity(i);
                }
                break;
            case 1:
                // recipes
                break;
            case 2:
                // food
                break;
            case 3:
                // tv
                break;
            case 4:
                // music
                break;
            case 5:
                // children
                break;
            case 6:
                MenuClicked(context);
                break;
        }
    }
}
