package com.typicalgeek.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final int TEXT_FADE_DELAY = 1000;
        final int TEXT_DISPLAY_DURATION = 2000;
        final int SPLASH_DISPLAY_lENGTH = 2*(TEXT_FADE_DELAY)+TEXT_DISPLAY_DURATION+1500;
        final TextView tvSplash  = (TextView) findViewById(R.id.tvSplash);
        final ProgressBar pbSplash = (ProgressBar) findViewById(R.id.pbSplash);
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(TEXT_FADE_DELAY);
        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(TEXT_FADE_DELAY);

        AnimationSet tvSet = new AnimationSet(true);
        tvSet.addAnimation(in);
        out.setStartOffset(2000);
        tvSet.addAnimation(out);

        final AnimationSet pbSet = new AnimationSet(true);
        tvSet.addAnimation(in);

        tvSplash.startAnimation(tvSet);
        tvSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvSplash.setVisibility(View.GONE);
                pbSplash.setVisibility(View.VISIBLE);
                pbSplash.startAnimation(pbSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent that will start MainActivity
                i = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_lENGTH);
    }
}
