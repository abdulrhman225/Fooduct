package com.example.fooduct.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fooduct.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


       Thread thread = new Thread(){
           @Override
           public void run() {
               super.run();
               try {
                   Thread.sleep(1000);
                   Intent intent = new Intent(SplashScreen.this , MainActivity.class);
                   startActivity(intent);
                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
       thread.start();
    }
}