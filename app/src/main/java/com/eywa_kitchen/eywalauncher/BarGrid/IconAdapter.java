package com.eywa_kitchen.eywalauncher.BarGrid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.R;

import java.util.ArrayList;
import java.util.List;

public class IconAdapter extends BaseAdapter {
    private Context mContext;
    public static List<AppInfo> appsIcons;
    private static int CountOfApps = 0;

    public IconAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return CountOfApps;
    }

    public CharSequence getItem(int position) {
        return appsIcons.get(position).name;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.item_card, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.appImage);
        TextView textView = (TextView) grid.findViewById(R.id.appName);
            imageView.setImageDrawable(appsIcons.get(position).icon);
            textView.setText(appsIcons.get(position).label);
        textView.setTextColor(Color.WHITE);

        return grid;
    }

    public static void LoadIcons(PackageManager manager,Drawable arrow_down){
        appsIcons = new ArrayList<AppInfo>();
        CountOfApps=0;

        AppInfo closeMenu = new AppInfo();
        closeMenu.label = "";
        closeMenu.name = "";
        closeMenu.icon = arrow_down;
        appsIcons.add(closeMenu);
        CountOfApps++;

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for (ResolveInfo ri : availableActivities) {
            AppInfo app = new AppInfo();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            appsIcons.add(app);
            CountOfApps++;
        }
    }

}
