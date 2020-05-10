package com.addindev.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.addindev.moviecatalogue.menu.MainActivity;

public class SplashScreen extends AppCompatActivity {
    private ImageView logo;
    private static int splashTimeout = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = (ImageView) findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, splashTimeout);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splashanimation);
        logo.startAnimation(myanim);
    }
}
