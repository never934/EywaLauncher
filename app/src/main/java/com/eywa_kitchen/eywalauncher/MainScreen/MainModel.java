package com.eywa_kitchen.eywalauncher.MainScreen;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler.EywaAppIcon;
import com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler.EywaIconAdapter;
import com.eywa_kitchen.eywalauncher.R;
import com.eywa_kitchen.eywalauncher.BarGrid.IconAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.NewsAdapter.NewsAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.NewsAdapter.getNewsTask;
import com.eywa_kitchen.eywalauncher.Recommendations.YoutubeAdapter.YoutubeAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.YoutubeAdapter.getRecommendationsTask;
import com.eywa_kitchen.eywalauncher.Weather.getWeatherTask;

import java.util.Timer;
import java.util.TimerTask;

public class MainModel implements MainContract.Model {
    private Timer timer = new Timer();

    public interface WeatherCallback {
        void Received(Bitmap image, String temp);
        void Error();
        void Loading();
    }

    public interface YoutubeRecommendationsCallback {
        void Received(YoutubeAdapter youtubeAdapter);
        void Error();
        void Loading();
    }

    public interface NewsCallback {
        void Received(NewsAdapter adapter);
        void Error();
    }

    @Override
    public void getRecommendationsAdapter(final YoutubeRecommendationsCallback callback) {

        Runnable runnable = new Runnable() {
            public void run() {
                getRecommendationsTask getRecommendations = new getRecommendationsTask(callback);
                getRecommendations.execute();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void getNewsAdapter(final NewsCallback callback) {

        Runnable runnable = new Runnable() {
            public void run() {
                getNewsTask getNews = new getNewsTask(callback);
                getNews.execute();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void getWeather(final WeatherCallback callback) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getWeatherTask getWeather = new getWeatherTask(callback);
                getWeather.execute();
            }
        }, 1000,1800000);
    }

    @Override
    public EywaIconAdapter getAppAdapter(Context context) {
        try{
            EywaAppIcon.LoadApps(context);
            EywaIconAdapter AppAdapter = new EywaIconAdapter(EywaAppIcon.appsIcons);
            return AppAdapter;
        }
        catch (Exception e){
            Log.e("AppIconAdapter", "Exception with receiving");
            return null;
        }
    }

    @Override
    public IconAdapter getBarAdapter(Context context) {
        try{
            PackageManager manager = context.getPackageManager();
            Drawable Arrow_down = context.getDrawable(R.drawable.ic_down);
            IconAdapter.LoadIcons(manager, Arrow_down);
            IconAdapter BarAdapter = new IconAdapter(context.getApplicationContext());
            return BarAdapter;
        }
        catch (Exception e){
            Log.e("BarAdapter", "Exception with receiving");
            return null;
        }
    }


}
