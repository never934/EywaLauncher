package com.eywa_kitchen.eywalauncher.MainScreen.Time;

import android.os.Handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeController implements Runnable {

    private Handler Update = new Handler();
    private TimeCallback callback;

    public TimeController(TimeCallback timeCallback) {
        this.callback=timeCallback;
    }

    public interface TimeCallback {
        void Updated(String Time, String Date);
    }

    @Override
    public void run() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEEEEEEEEEE, d", Locale.getDefault());
        String date = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = timeFormat.format(currentDate);
        callback.Updated(time, date);
        Update.postDelayed(this, 1000);
    }
}
