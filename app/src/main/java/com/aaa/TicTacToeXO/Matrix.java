package com.aaa.TicTacToeXO;
class Matrix {

    private int rows;
    private int columns;
    int [][] matrix;

    //The constructor
    Matrix(int numRows, int numColumns) {
        this.rows = numRows;
        this.columns = numColumns;
        this.matrix = new int[rows][columns];
        this.setMatrixData();
    }

    void setMatrixData() {
        for(int row=0; row<this.rows; row++) {
            for(int col=0; col<this.columns; col++) {
                matrix[row][col] = 4;
            }
        }
    }

    void setData(int rowIndex, int columnIndex, int data) {
        matrix[rowIndex][columnIndex] = data;
    }

    String getBoxSymbol(MatrixTextView Tview){
        int boxNum = matrix[Tview.rowIndex][Tview.colIndex];
        String letter;
        switch(boxNum){
            case 1:
                letter = "X";
                break;
            case 0:
                letter = "O";
                break;
            default:
                letter = "";
                break;
        }
        return letter;
    }

    int isRowEqual(int rowIndex) {
        int numWon = 4;
        int sumOfData = 0;
        for(int i=0; i<this.columns; i++) {
            sumOfData += matrix[rowIndex][i];
        }
        if (sumOfData == 3) {
            numWon = 1;
        }else if(sumOfData == 0) {
            numWon = 0;
        }
        return numWon;
    }

    int isColumnEqual(int columnIndex) {
        int numWon = 4;
        int sumOfData = 0;
        for(int i=0; i<this.rows; i++) {
            sumOfData += matrix[i][columnIndex];
        }
        if (sumOfData == 3) {
            numWon = 1;
        }else if(sumOfData == 0) {
            numWon = 0;
        }
        return numWon;
    }

    int isDiagonalEqual1() {
        int numWon = 4;
        int sumOfData = 0;
        for(int i = 0; i<matrix.length; i++) {
            sumOfData += matrix[i][i];
        }
        if(sumOfData == 3) {
            numWon = 1;
        }else if(sumOfData == 0) {
            numWon = 0;
        }
        return numWon;
    }

    int isDiagonalEqual2() {
        int numWon = 4;

        if (matrix[0][2] == 1 && matrix[1][1] == 1 && matrix[2][0] == 1){
            numWon = 1;
        }else if(matrix[0][2] == 0 && matrix[1][1] == 0 && matrix[2][0] == 0){
            numWon = 0;
        }
        return numWon;
    }
}
