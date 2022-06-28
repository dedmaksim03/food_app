package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    ImageView progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progress_bar = findViewById(R.id.progress_bar);

        final Animation progress_bar_anim = AnimationUtils.loadAnimation(this, R.anim.progress_bar_anim);
        progress_bar.startAnimation(progress_bar_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash_screen.this, MainActivity.class);
                splash_screen.this.startActivity(mainIntent);
                splash_screen.this.finish();
            }
            },SPLASH_DISPLAY_LENGTH);
        }

        @Override
    public void onBackPressed(){
        super.onBackPressed();
        }
    }
