package com.example.techknights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){

            public void run(){
                try {
                    sleep(2300);

                }
                catch (Exception e){
                    e.printStackTrace();

                }
                finally {
                    Intent intent = new Intent(SplashScreenActivity.this , MainActivity.class);
                    startActivity(intent);

                }
            }
        };thread.start();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}