package com.eywa_kitchen.eywalauncher.MainScreen.Assistant;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Thread.sleep;

public class AssistantTextAnimation implements Runnable {

    private Animation mFadeInAnimation, mFadeOutAnimation;
    private Context Context;
    private TextView AssistView;
    private Handler Update = new Handler();
    final Random random = new Random();
    private static String TextOfAssist[] = {
            "Попробуйте выполнить поиск по запросу “Рецепт шоколадного фондана”",
            "Попробуйте выполнить поиск по запросу “Виды заправок для салата”",
            "Попробуйте выполнить поиск по запросу “Рецепт вишневого пирога”",
            "Попробуйте выполнить поиск по запросу “Лучший рецепт борща”",
            "Попробуйте выполнить поиск по запросу “Джейми Оливер видео”",
            "Попробуйте выполнить поиск по запросу “Лучшие кулинарные шоу”",
            "Попробуйте выполнить поиск по запросу “«Едим дома» с Юлией Высоцкой”",
            "Попробуйте выполнить поиск по запросу “Спортивное питание”",
            "Попробуйте выполнить поиск по запросу “Маринад для шашлыка из семги”",
            "Спросите ”Рецепт борща”",
            "Спросите “Сколько минут варить рис?”",
            "Спросите ”Какая погода завтра в Москве?”",
            "Спросите ”Рецепт пасты карбонара”",
            "Спросите ”Сколько минут варить спагетти?”",
            "Спросите “Как приготовить суп минестроне?”",
            "Спросите “Кто такой Гордон Рамзи?”",
            "Спросите “Как приготовить диетический салат?”",
            "Спросите “Как приготовить песочное тесто?”",
            "Спросите “Как приготовить пиццу «4 сыра»?”",
            "Спросите “Как готовить суши?”",
            "Спросите “Как варить кальмаров?”",
            "Спросите “Как правильно жарить стейк?”",
            "Спросите “Как приготовить салат «чука»?”",
            "Скажите ”Открыть YouTube”",
    };

    public static int AssistViewArrayLength = TextOfAssist.length;

    public AssistantTextAnimation(Context context, TextView View){
        this.Context=context;
        this.AssistView = View;
        AssistView.setText(TextOfAssist[random.nextInt(AssistViewArrayLength)]);
    }

    @Override
    public void run() {
        mFadeInAnimation = AnimationUtils.loadAnimation(Context, com.eywa_kitchen.eywalauncher.R.anim.fadein);
        mFadeOutAnimation = AnimationUtils.loadAnimation(Context, com.eywa_kitchen.eywalauncher.R.anim.fadeout);
        mFadeOutAnimation.setAnimationListener(animationFadeOutListener);
        mFadeInAnimation.setAnimationListener(animationFadeInListener);
        AssistView.startAnimation(mFadeOutAnimation);
    }
    private Animation.AnimationListener animationFadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            UpdateAnim();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    private Animation.AnimationListener animationFadeOutListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            AssistView.setText(TextOfAssist[random.nextInt(AssistViewArrayLength)]);
            AssistView.startAnimation(mFadeInAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    private void UpdateAnim(){
        Update.postDelayed(this, 6000);
    }
}
