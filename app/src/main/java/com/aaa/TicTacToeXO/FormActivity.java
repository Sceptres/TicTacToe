package com.aaa.TicTacToeXO;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;

public class FormActivity extends AppCompatActivity {

    WebView formWebView;
    @SuppressLint({"SetJavaScriptEnabled", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formactivity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        formWebView = findViewById(R.id.formWebView);

        formWebView.getSettings().setJavaScriptEnabled(true);
        formWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeZWyz1x_Y6DSaGSzPVVnaKO-my5iUvNT3aI5VMU510P5ikgQ/viewform?vc=0&c=0&w=1");
    }

    @Override
    public void onBackPressed(){
        Intent mainPageIntent = new Intent(FormActivity.this, MainPage.class);
        startActivity(mainPageIntent);
        finish();
    }
}
