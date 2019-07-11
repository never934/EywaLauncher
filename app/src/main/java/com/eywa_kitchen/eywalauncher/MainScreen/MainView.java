package com.eywa_kitchen.eywalauncher.MainScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.eywa_kitchen.eywalauncher.MainScreen.AppRecycler.AppIconAdapter;
import com.eywa_kitchen.eywalauncher.MainScreen.EywaIconRecycler.EywaIconAdapter;
import com.eywa_kitchen.eywalauncher.utils.ItemClickSupport;
import com.eywa_kitchen.eywalauncher.utils.OnSwipeTouchListener;
import com.eywa_kitchen.eywalauncher.R;
import com.eywa_kitchen.eywalauncher.BarGrid.IconAdapter;
import com.eywa_kitchen.eywalauncher.Recommendations.RecommendationsAdapter;
import com.squareup.picasso.Picasso;

import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN;

public class MainView extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener,MainContract.View {

    private MainContract.Presenter Presenter;
    private View llBottomSheet;
    private BottomSheetBehavior Bar;
    private TextView TimeView, AssistantView, ReceivingRecView;
    private TextView WeatherTemp;
    private RecyclerView IconRow,RecRow;
    public static Context MainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainContext = this;

        initViews();
        initActivity();
        setListeners();
        initPresenter();
        setStartView();
        Presenter.startAssistantAnimation(this, AssistantView);
        Presenter.getEywaIcons(this);
        Presenter.getResources();
        Presenter.getRecommendations();
        Presenter.getWeather();
    }

    private void initViews(){
        RecRow = (RecyclerView) findViewById(R.id.VideoRow);
        IconRow = (RecyclerView) findViewById(R.id.IconRow);
        TimeView = (TextView) findViewById(R.id.TimeView);
        ReceivingRecView = (TextView)findViewById(R.id.ReceivingRecView);
        WeatherTemp = (TextView)findViewById(R.id.CurrentWeatherView);
        AssistantView = (TextView)findViewById(R.id.assistantText);
        llBottomSheet = findViewById(R.id.AppBar);
        Bar = BottomSheetBehavior.from(llBottomSheet);

        LinearLayoutManager horizontalLinearLayoutManagerVideo = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLinearLayoutManagerApp = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecRow.setLayoutManager(horizontalLinearLayoutManagerVideo);
        IconRow.setLayoutManager(horizontalLinearLayoutManagerApp);
    }

    private void initActivity(){
        getSupportActionBar().hide();
    }

    private void setListeners(){
        AssistantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter.AssistantViewClicked(MainContext);
            }
        });

        ItemClickSupport.addTo(IconRow)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        {
                            Presenter.EywaIconClicked(position, MainContext);
                        }
                    }
                });

        Bar.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    Bar.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

    }

    private void initPresenter() {
        Presenter = new MainPresenter(this);
    }

    @Override
    public void setStartView(){
        WeatherTemp.setTextSize(8);
        WeatherTemp.setText("Покдлючение");
        Bar.setState(STATE_HIDDEN);
        ReceivingRecView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCurrentWeather(Bitmap image, int temp) {
      //  WeatherImage.setImageBitmap(image);
        WeatherTemp.setTextSize(TypedValue.COMPLEX_UNIT_PX,64);
        WeatherTemp.setText(temp + "°С");
    }

    @Override
    public void showRecommendations(RecommendationsAdapter VideoAdapter) {
        RecRow.setAdapter(VideoAdapter);
        ReceivingRecView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showTime(String Time, String Date) {
        TimeView.setText(Time);
    }

    @Override
    public void showEywaApps(EywaIconAdapter Adapter) {
        IconRow.setAdapter(Adapter);
    }

    @Override
    public void showBar(IconAdapter mAdapter) {


        final GridView g = (GridView) findViewById(R.id.AppGrid);
        g.setAdapter(mAdapter);
        g.setOnItemSelectedListener(this);
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Presenter.IconClickedInBar(position, MainContext);
            }
        });
        Bar.setState(STATE_EXPANDED);
    }

    @Override
    public void hideBar() {
        Bar.setState(STATE_HIDDEN);
    }

    @Override
    public void showWeatherInformer(String msg) {
            setText(WeatherTemp,msg);
            if(msg.equals("Нет подключения"))Presenter.getWeather();
    }

    @Override
    public void showRecommendationsInformer(String msg) {
            setText(ReceivingRecView,msg);
            if(msg.equals("Нет подключения к интернету"))Presenter.getRecommendations();
    }

    @Override
    public void onBackPressed()
    {

    }

    private void setText(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}