package com.aaa.TicTacToeXO;

import android.widget.TextView;

class MatrixTextView {
    TextView tview;
    int rowIndex;
    int colIndex;
    MatrixTextView(TextView Tview, int row, int col){
        this.tview = Tview;
        this.rowIndex = row;
        this.colIndex = col;
    }

    void addData(Matrix matrix, int symbol){
        matrix.setData(this.rowIndex, this.colIndex, symbol);
    }
}
