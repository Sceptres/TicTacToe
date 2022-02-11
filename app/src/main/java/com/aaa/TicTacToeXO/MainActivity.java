package com.aaa.TicTacToeXO;

//All the import statements required by this class.
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String playerSymbol = "X";
    int playerTurn = 1;
    int player1Score = 0;
    int player2Score = 0;

    Button restartBTN;

    Matrix gameMatrix = new Matrix(3, 3);

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


    /**
     * Name: onCreate
     * Date: 4/5/2020
     * Functionality: Overrides the onCreate function of the AppCompatActivity class.
     * */
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Shows the user a dialog that informs them the symbols of each player
        AlertDialog.Builder db = new AlertDialog.Builder(MainActivity.this);
        db.setMessage("Player1 is X\nPlayer2 is O\nIt is player1 turn.")
                .setTitle("ENJOY!")
                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast turnToast = Toast.makeText(getApplicationContext(), "It is player1(X) turn.", Toast.LENGTH_SHORT);
                        turnToast.show();
                    }
                });
        AlertDialog alertDialog = db.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        setUpVariables();
        setClickListeners();

    }


    /**
     * Name: setUpVariables
     * Date: 4/5/2020
     * Functionality: Initializes all the variables that are declared at the top of the class
     * Input: Uses all the variables declared at the top of the class
     * Output: Initializes all the variables
     * */
    public void setUpVariables() {
        playerTScore = findViewById(R.id.playerScore);
        computerTScore = findViewById(R.id.computerScore);
        restartBTN = findViewById(R.id.restartBTN);
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
    }

    /**
     * Name: onBackPressed
     * Date: 4/5/2020
     * Functionality: Overrides the onBackPressed. Controls what the arrow on the phone does. I want it to send the user back to the Main_Page.
     * */
    @Override
    public void onBackPressed(){
        Intent backIntent = new Intent(MainActivity.this, MainPage.class);
        startActivity(backIntent);
        finish();
    }

    /**
     * Name: refresh
     * Date: 4/5/2020
     * Functionality: Refreshes the XO grid.
     * Input: The MatrixTextViews and the gameMatrix.
     * Output: Sets the grid to the right symbols
     * */
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

    /**
     * Name: setPlayerTurn
     * Date: 4/5/2020
     * Functionality: Changes the turn.
     * */
    public void setPlayerTurn(boolean isShowNow){
        if(playerTurn == 1){
            playerTurn = 0;
            playerSymbol = "O";
            if(isFull() || (isPlayer1Win() || isPlayer2Win()) || isShowNow) {
                Toast turnToast = Toast.makeText(getApplicationContext(), "It is player2(O) turn.", Toast.LENGTH_SHORT);
                turnToast.show();
            }
        }else{
            playerTurn = 1;
            playerSymbol = "X";
            if(isFull() || (isPlayer1Win() || isPlayer2Win()) || isShowNow) {
                Toast turnToast = Toast.makeText(getApplicationContext(), "It is player1(X) turn.", Toast.LENGTH_SHORT);
                turnToast.show();
            }
        }
    }

    /**A function that takes a TextView and an Integer
     * The TextView is which TextView we are trying to set the clickListener for
     * The integer is the number of the TextView so we can set the Symbol for that TextView using the switch statement*/
    public void setClickListener(final MatrixTextView Tview){
        Tview.tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tview.addData(gameMatrix, playerTurn);
                refresh();
                if(!isFull() && !(isPlayer1Win() || isPlayer2Win())){
                    setPlayerTurn(false);
                }
                isTie(isWin());
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
        restartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearGame(true);
            }
        });
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
            player2Score = 0;
            playerTScore.setText(String.valueOf(player1Score));
            computerTScore.setText(String.valueOf(player2Score));
            varReset();

        }else {
            varReset();
        }
    }

    //Checks to see if it is a tie. Takes a boolean from the isWin() functions.
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
        AlertDialog.Builder db = new AlertDialog.Builder(MainActivity.this);
        db.setMessage(dialogMessageText)
                .setTitle(dialogTitleText)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearGame(false);
                        removeClickListener();
                        setClickListeners();
                        setPlayerTurn(true);
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
                                setPlayerTurn(true);
                            }
                        }, SPLASHTIME);
                    }
                });

        AlertDialog alertDialog = db.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    /**A function that returns a boolean whether or not player1 won
     * true if player1 did win
     * false if he didn't*/
    public boolean isPlayer1Win(){
        return gameMatrix.isRowEqual(0) == 1 || gameMatrix.isRowEqual(1) == 1 || gameMatrix.isRowEqual(2) == 1 || gameMatrix.isColumnEqual(0) == 1 || gameMatrix.isColumnEqual(1) == 1 || gameMatrix.isColumnEqual(2) == 1 || gameMatrix.isDiagonalEqual1() == 1 || gameMatrix.isDiagonalEqual2() == 1;
    }

    /**A function that returns a boolean whether or not player2 won
     * true if player2 did win
     * false if he didn't*/
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
            removeClickListener();
            showWinLoseOrTieDialog("YOU HAVE WON!", "Player1 Won!");
            return false;
        }else if(isPlayer2Win()){
            player2Score += 1;
            computerTScore.setText(String.valueOf(player2Score));
            removeClickListener();
            showWinLoseOrTieDialog("PLAYER2 WON!", "Player2 Won!");
            return false;
        }
        return true;
    }

}
