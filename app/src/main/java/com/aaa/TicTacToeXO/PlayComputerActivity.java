package com.aaa.TicTacToeXO;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PlayComputerActivity extends AppCompatActivity {
    Matrix gameMatrix = new Matrix(3, 3);
    String playerSymbol = "X";
    int playerTurn = 1;
    int player1Score = 0;
    int computerScore = 0;

    String difficulty;

    Button restartBTN;

    //The score TextViews and the game TextViews
    TextView playerTScore;
    TextView computerTScore;
    TextView tview1;
    TextView tview2;
    TextView tview3;
    TextView tview4;
    TextView tview5;
    TextView tview6;
    TextView tview7;
    TextView tview8;
    TextView tview9;
    TextView vsView;

    //The MatrixTextViews
    MatrixTextView mTview1;
    MatrixTextView mTview2;
    MatrixTextView mTview3;
    MatrixTextView mTview4;
    MatrixTextView mTview5;
    MatrixTextView mTview6;
    MatrixTextView mTview7;
    MatrixTextView mTview8;
    MatrixTextView mTview9;

    ArrayList<MatrixTextView> viewsArray;

    //The built-in onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVariables();
        setClickListeners();
        //Determines what the restartBTN should do. It should restart the whole game. Meaning it will clear the game and set the scores to 0
        restartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearGame(true);
            }
        });

        //Shows the user a dialog that informs them of their symbol and the computers
        AlertDialog.Builder db = new AlertDialog.Builder(PlayComputerActivity.this);
        db.setMessage("Player1 is X\nThe Computer is O\nIt is player1 turn.")
                .setTitle("ENJOY!")
                .setPositiveButton("Play", null);
        AlertDialog alertDialog = db.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    //Function that sets up the variables that are declared at the top.
    public void setUpVariables() {
        playerTScore = findViewById(R.id.playerScore);
        computerTScore = findViewById(R.id.computerScore);
        restartBTN = findViewById(R.id.restartBTN);
        difficulty = getIntent().getStringExtra("Difficulty");
        //Initializing all the TextViews
        tview1 = findViewById(R.id.tview1);
        tview2 = findViewById(R.id.tview2);
        tview3 = findViewById(R.id.tview3);
        tview4 = findViewById(R.id.tview4);
        tview5 = findViewById(R.id.tview5);
        tview6 = findViewById(R.id.tview6);
        tview7 = findViewById(R.id.tview7);
        tview8 = findViewById(R.id.tview8);
        tview9 = findViewById(R.id.tview9);
        vsView = findViewById(R.id.vsTextView);
        vsView.setText("Player1 vs Computer");
        //Initializing all the MatrixTextViews, giving them their correct TextView, rowIndex and colIndex.
        mTview1 = new MatrixTextView(tview1, 0, 0);
        mTview2 = new MatrixTextView(tview2, 0, 1);
        mTview3 = new MatrixTextView(tview3, 0, 2);
        mTview4 = new MatrixTextView(tview4, 1, 0);
        mTview5 = new MatrixTextView(tview5, 1, 1);
        mTview6 = new MatrixTextView(tview6, 1, 2);
        mTview7 = new MatrixTextView(tview7, 2, 0);
        mTview8 = new MatrixTextView(tview8, 2, 1);
        mTview9 = new MatrixTextView(tview9, 2, 2);

        viewsArray = new ArrayList<>();
        viewsArray.add(mTview1);
        viewsArray.add(mTview2);
        viewsArray.add(mTview3);
        viewsArray.add(mTview4);
        viewsArray.add(mTview5);
        viewsArray.add(mTview6);
        viewsArray.add(mTview7);
        viewsArray.add(mTview8);
        viewsArray.add(mTview9);
    }


    public void refresh(){
        mTview1.tview.setText(gameMatrix.getBoxSymbol(mTview1));
        mTview2.tview.setText(gameMatrix.getBoxSymbol(mTview2));
        mTview3.tview.setText(gameMatrix.getBoxSymbol(mTview3));
        mTview4.tview.setText(gameMatrix.getBoxSymbol(mTview4));
        mTview5.tview.setText(gameMatrix.getBoxSymbol(mTview5));
        mTview6.tview.setText(gameMatrix.getBoxSymbol(mTview6));
        mTview7.tview.setText(gameMatrix.getBoxSymbol(mTview7));
        mTview8.tview.setText(gameMatrix.getBoxSymbol(mTview8));
        mTview9.tview.setText(gameMatrix.getBoxSymbol(mTview9));
    }


    //Override onBackPressed() function to make the page go back to the MainPage when the arrow is pressed
    @Override
    public void onBackPressed(){
        Intent backIntent = new Intent(PlayComputerActivity.this, MainPage.class);
        startActivity(backIntent);
        finish();
    }

    //Function that checks whos turn it is and then changes the turn to the other player after the he finishes his turn
    public void setPlayerTurn(){
        if(playerTurn == 0){
            playerTurn = 1;
            playerSymbol = "X";
        }else{
            playerTurn = 0;
            playerSymbol = "O";
        }
    }

    public void playCompTurn(){
        setPlayerTurn();
        if(playerTurn == 0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    compTurn();
                    isTie(isWin());
                }
            }, 600);
        }
    }

    /**A function that takes a TextView and an Integer
     * The TextView is which TextView we are trying to set the clickListener for
     * The integer is the number of the TextView so we can set the Symbol for that TextView using the switch statement*/
    public void setClickListener(final MatrixTextView Tview){
        Tview.tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tview.tview.getText().toString().isEmpty() && playerSymbol.equals("X")){
                    Tview.addData(gameMatrix, playerTurn);
                    refresh();
                    playCompTurn();
                }

            }
        });
    }

    //A function that sets the clickListeners for the 9 TextViews
    public void setClickListeners(){
        setClickListener(mTview1);
        setClickListener(mTview2);
        setClickListener(mTview3);
        setClickListener(mTview4);
        setClickListener(mTview5);
        setClickListener(mTview6);
        setClickListener(mTview7);
        setClickListener(mTview8);
        setClickListener(mTview9);
    }

    //A function that removes the clickListeners by setting them to null
    public void removeClickListener(){
        tview1.setOnClickListener(null);
        tview2.setOnClickListener(null);
        tview3.setOnClickListener(null);
        tview4.setOnClickListener(null);
        tview5.setOnClickListener(null);
        tview6.setOnClickListener(null);
        tview7.setOnClickListener(null);
        tview8.setOnClickListener(null);
        tview9.setOnClickListener(null);
    }

    //Function that resets all the TextViews and tviewSymbol variables
    public void varReset(){
        playerTurn = 1;
        playerSymbol = "X";
        tview1.setText("");
        tview2.setText("");
        tview3.setText("");
        tview4.setText("");
        tview5.setText("");
        tview6.setText("");
        tview7.setText("");
        tview8.setText("");
        tview9.setText("");
        gameMatrix.setMatrixData();
    }

    /**A function that resets the game UI. It takes the boolean parameter whole.
     *If set to true, the entire game will be cleared. The game is emptied and the scores are set to 0.
     *If false it will just clear the game.*/
    public void clearGame(boolean whole){
        if(whole){
            player1Score = 0;
            computerScore = 0;
            playerTScore.setText(String.valueOf(player1Score));
            computerTScore.setText(String.valueOf(computerScore));
            varReset();

        }else {
            varReset();
        }
    }

    public void isTie(boolean tie){
        if (isFull() && tie) {
            showWinLoseOrTieDialog("You have tied!", "ITS A TIE!");
        }
    }

    /**Function that checks the game to see if all the boxes have been used. Takes the boolean parameter tie.
     * If true, it means that no one has one and so the game clears the boxes
     * If false, then someone has won and the function doesn't clear out the game if it is full.
     * */
    public boolean isFull() {
        return !tview1.getText().toString().isEmpty() && !tview2.getText().toString().isEmpty() && !tview3.getText().toString().isEmpty() && !tview4.getText().toString().isEmpty() && !tview5.getText().toString().isEmpty() && !tview6.getText().toString().isEmpty() && !tview7.getText().toString().isEmpty() && !tview8.getText().toString().isEmpty() && !tview9.getText().toString().isEmpty();
    }

    /**A function that displays an AlertDialog to show the user whether he won, lost or tied
     * Takes 2 String parameters
     * dialogMessageText is what the message of the AlertDialog will be
     * dialogTitleText is what the title of the AlertDialog will be*/
    public void showWinLoseOrTieDialog(String dialogMessageText, String dialogTitleText){
        final int SPLASHTIME = 5000;
        AlertDialog.Builder db = new AlertDialog.Builder(PlayComputerActivity.this);
        db.setMessage(dialogMessageText)
                .setTitle(dialogTitleText)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearGame(false);
                        removeClickListener();
                        setClickListeners();
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeClickListener();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearGame(false);
                                setClickListeners();
                            }
                        }, SPLASHTIME);
                    }
                });

        AlertDialog alertDialog = db.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public boolean isPlayer1Win(){
        return gameMatrix.isRowEqual(0) == 1 || gameMatrix.isRowEqual(1) == 1 || gameMatrix.isRowEqual(2) == 1 || gameMatrix.isColumnEqual(0) == 1 || gameMatrix.isColumnEqual(1) == 1 || gameMatrix.isColumnEqual(2) == 1 || gameMatrix.isDiagonalEqual1() == 1 || gameMatrix.isDiagonalEqual2() == 1;
    }

    public boolean isPlayer2Win(){
        return gameMatrix.isRowEqual(0) == 0 || gameMatrix.isRowEqual(1) == 0 || gameMatrix.isRowEqual(2) == 0 || gameMatrix.isColumnEqual(0) == 0 || gameMatrix.isColumnEqual(1) == 0 || gameMatrix.isColumnEqual(2) == 0 || gameMatrix.isDiagonalEqual1() == 0 || gameMatrix.isDiagonalEqual2() == 0;
    }

    /**Function that checks who wins, player1 or computer
     * Returns a boolean value so that the value returned can be used in the checkIfFull(boolean tie) function
     * Uses the symbols to check whether it an "X" = 1 or an "O" = 0.
     * */
    public boolean isWin(){
        if(isPlayer1Win()){
            player1Score += 1;
            playerTScore.setText(String.valueOf(player1Score));
            showWinLoseOrTieDialog("YOU HAVE WON!", "YOU WON!");
            return false;
        }else if(isPlayer2Win()){
            computerScore += 1;
            computerTScore.setText(String.valueOf(computerScore));
            showWinLoseOrTieDialog("THE COMPUTER WON", "YOU LOST!");
            return false;
        }
        return true;
    }

    //The function that will run the algorithm for the computers turn.
    public void compTurn(){
        switch (difficulty){
            case "Easy":
                easyDiff();
                break;
            case "Hard":
                hardDiff();
                break;

        }
    }

    /**
     * Name: easyDiff
     * Date: 4/5/2020
     * Functionality: The easy difficulty for the computer
     * */
    public void easyDiff(){
        Random rd = new Random();
        int randomNum = rd.nextInt(9) + 1;
        boolean isTurnPlayed = false;
        if(!isFull() && !isPlayer1Win() && playerTurn == 0){
            while(!isTurnPlayed){
                if (!gameMatrix.getBoxSymbol(viewsArray.get(randomNum - 1)).isEmpty()) randomNum = rd.nextInt(9) + 1;
                else{
                    viewsArray.get(randomNum - 1).addData(gameMatrix, playerTurn);
                    isTurnPlayed = true;
                }
            }
            refresh();
            setPlayerTurn();
        }
    }

    /**
     * Name: hardDiff
     * Date: 3/5/2020
     * Functionality: The hard difficulty
     * */
    public void hardDiff() {
        if (!isFull() && !isPlayer1Win() && playerTurn == 0) { //Did anyone win, is it a tie and is it the computers turn
            ArrayList<MatrixTextView> wins = new ArrayList<>();
            ArrayList<MatrixTextView> stops = new ArrayList<>();

            for (MatrixTextView tview : viewsArray) {
                if (gameMatrix.getBoxSymbol(tview).isEmpty()) { //Is the spot available?
                    tview.addData(gameMatrix, 0);

                    if (isPlayer2Win()) { //Does the player win on this spot?
                        Log.d("TAG", "hardDiff: Win added.");
                        wins.add(tview);
                    }
                    tview.addData(gameMatrix, 4);
                }
            }

            for (MatrixTextView view : viewsArray) {
                if (gameMatrix.getBoxSymbol(view).isEmpty()) { //Is the spot available?
                    view.addData(gameMatrix, 1);

                    if (isPlayer1Win()) { //Do win on this spot?
                        Log.d("TAG", "hardDiff: Stop added.");
                        stops.add(view);
                    }
                    Log.d("TAG", "hardDiff: " + Arrays.deepToString(gameMatrix.matrix));
                    view.addData(gameMatrix, 4);
                    Log.d("TAG", "hardDiff: " + Arrays.deepToString(gameMatrix.matrix));
                }
            }

            Log.d("TAG", "hardDiff: " + wins.size());
            Log.d("TAG", "hardDiff: " + stops.size());

            if (wins.size() != 0){
                wins.get(0).addData(gameMatrix, 0);
                setPlayerTurn();
            }else if (stops.size() != 0){
                stops.get(0).addData(gameMatrix, 0);
                setPlayerTurn();
            }else easyDiff();


            refresh();
        }
    }
}
