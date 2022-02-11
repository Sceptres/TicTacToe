package com.aaa.TicTacToeXO;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainPage extends AppCompatActivity implements DifficultyPopup.difficultyInterface {

    //The four buttons that are on the main page
    Button playFriendBTN;
    Button playComputerBTN;
    Button feedbackBTN;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setUpVariables();

        //Determines what the playFriendBTN does. When pressed it should open the MainActivity so that the user can play with a friend
        playFriendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playFriendIntent = new Intent(MainPage.this, MainActivity.class);
                startActivity(playFriendIntent);
                finish();
            }
        });

        //Determines what the playcomputerBTN does. For now it should just show an AlertDialog that says that this feature is still under development
        playComputerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DifficultyPopup dialog = new DifficultyPopup();
                dialog.show(getSupportFragmentManager(), "Test");
            }
        });

        //Determines what the feedbackBTN does. It should open the formActivity that will open the form in a WebView.
        feedbackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formIntent = new Intent(MainPage.this, FormActivity.class);
                startActivity(formIntent);
                finish();
            }
        });


    }

    //A function that declares the variables stated at the top
    public void setUpVariables(){
        playFriendBTN = findViewById(R.id.playFriendBTN);
        playComputerBTN = findViewById(R.id.playComputerBTN);
        feedbackBTN = findViewById(R.id.feedBackBTN);
    }

    @Override
    public void sendDifficulty(String difficulty) {
        Intent compInt = new Intent(MainPage.this, PlayComputerActivity.class);
        compInt.putExtra("Difficulty", difficulty);
        startActivity(compInt);
        finish();

    }
}
