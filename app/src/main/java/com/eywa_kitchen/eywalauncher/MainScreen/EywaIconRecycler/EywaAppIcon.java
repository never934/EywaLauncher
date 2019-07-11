package com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.eywa_kitchen.eywalauncher.MainScreen.AppRecycler.AppDetail;

import java.util.ArrayList;
import java.util.List;

public class EywaAppIcon {
    public static List<EywaIconDetail> appsIcons;


    public static void LoadApps(Context context) {
        appsIcons = new ArrayList<EywaIconDetail>();

        EywaIconDetail app = new EywaIconDetail();
        app.label ="Ютуб";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_youtube);
        appsIcons.add(app);

        app = new EywaIconDetail();
        app.label ="Рецепты";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_recipe);
        appsIcons.add(app);

        app = new EywaIconDetail();
        app.label ="Заказ еды";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_food);
        appsIcons.add(app);

        app = new EywaIconDetail();
        app.label ="Телевизор";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_tv);
        appsIcons.add(app);

        app = new EywaIconDetail();
        app.label ="Музыка";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_music);
        appsIcons.add(app);

        app = new EywaIconDetail();
        app.label ="Детям";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_children);
        appsIcons.add(app);

        app = new EywaIconDetail();
        app.label ="Приложения";
        app.icon = context.getDrawable(com.eywa_kitchen.eywalauncher.R.drawable.ic_apps);
        appsIcons.add(app);

    }
}

