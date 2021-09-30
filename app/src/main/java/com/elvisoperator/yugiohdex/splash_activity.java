package com.elvisoperator.yugiohdex;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class splash_activity extends AppCompatActivity {

    private final int DURACION_SPLASH = 5000;
    private final int DURACION_MUSICA = 10000;

    private MediaPlayer mMP;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        mMP = MediaPlayer.create(splash_activity.this, R.raw.opening);
        mMP.start();

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(splash_activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            ;
        }, DURACION_SPLASH);

    }
}
