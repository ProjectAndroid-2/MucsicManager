package com.example.vidudemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private  static  int SPLASH_SCREEN =6800;
    Animation animationrotate;
    ImageView imageView,caydong,chudong;
    Animation  bottom;
    boolean running = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setcontroller();
        setEvent();

    }

    private void setcontroller() {
        imageView = (ImageView) findViewById(R.id.quay);
        caydong =(ImageView) findViewById(R.id.caydong);
        chudong =(ImageView) findViewById(R.id.chudong);
    }

    private void setEvent() {
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        animationrotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        imageView.startAnimation(animationrotate);
        final AnimationDrawable runlogo = (AnimationDrawable) caydong.getDrawable();
        runlogo.start();
        chudong.setAnimation(bottom);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,mainshow.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }

}