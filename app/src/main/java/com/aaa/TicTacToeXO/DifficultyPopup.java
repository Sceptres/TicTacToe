package com.aaa.TicTacToeXO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DifficultyPopup extends AppCompatDialogFragment {

    private View mView;

    private RadioButton hard;
    private RadioButton easy;

    private boolean isSelected;

    private String difficultyLevel;

    private difficultyInterface interfaces;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.difficultypopup, null);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext())
                .setView(mView)
                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!isSelected) {
                            Toast.makeText(getContext(), "Select a difficulty level.", Toast.LENGTH_LONG).show();
                        } else {
                            interfaces.sendDifficulty(difficultyLevel);
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        setUpBTNListeners();

        return mBuilder.create();
    }

    private void setUpBTNListeners(){
        hard = mView.findViewById(R.id.hard);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: Should deselect");
                easy.setChecked(false);
                difficultyLevel = "Hard";
                isSelected = true;
            }
        });

        easy = mView.findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: Should deselect");
                hard.setChecked(false);
                difficultyLevel = "Easy";
                isSelected = true;
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interfaces = (difficultyInterface) context;
    }

    interface difficultyInterface{
        void sendDifficulty(String difficulty);
    }
}
