package com.aaa.TicTacToeXO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Name: loadPage
 * Date: 7/5/2020
 * Functionality: The loading page of the app. Will show for 5 seconds then close and send the user to the main page.
 * */

public class LoadPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_page);

        int SPLASH_TIME = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadPage.this, MainPage.class));
                finish();
            }
        }, SPLASH_TIME);
    }
}
