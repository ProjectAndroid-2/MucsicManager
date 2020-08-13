package com.example.doanltdd.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.doanltdd.R;

public class MainActivity extends AppCompatActivity {
    private  static  int SPLASH_SCREEN =5000;

    ImageView imageView;
    ImageView textView1, textView2;
    Animation top, bottom;
    boolean running = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setController();
        setEvent();
    }

    private void setEvent() {
        //top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        final AnimationDrawable runlogo = (AnimationDrawable) imageView.getDrawable();
        runlogo.start();
        textView1.setAnimation(bottom);
        textView2.setAnimation(bottom);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainDangNhap.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }

    private void setController() {
        imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }
}