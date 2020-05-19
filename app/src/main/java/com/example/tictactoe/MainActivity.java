package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    private TextView text;
    private Player x;
    private Player o;
    private Player currPlayer;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Buttons
        CreateButtons();

        //Initialize textView
        text = findViewById(R.id.textView);

        //Play
        StartGame();
    }

    public void newGame(View v){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        StartGame();
    }

    private void CreateButtons(){
        buttons = new Button[][]{
                {findViewById(R.id.button2), findViewById(R.id.button5), findViewById(R.id.button8)},
                {findViewById(R.id.button3), findViewById(R.id.button6), findViewById(R.id.button9)},
                {findViewById(R.id.button4), findViewById(R.id.button7), findViewById(R.id.button10)}
        };
    }

    private void StartGame(){
        x = new Player("X");
        o = new Player("O");

        currPlayer = x;

        PlayerDisplay(currPlayer);
    }

    public void onClick(View v){
        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[0].length; j++){
                if(v == buttons[i][j] && buttons[i][j].getText() ==""){
                    buttons[i][j].setText(currPlayer.playerName);
                }
            }
        };

        if(CheckWinner("X")){
            text.setText("Player X Wins");
            GameOver();
        }
        else if(CheckWinner("O")){
            text.setText("Player O Wins");
            GameOver();
        }
        else if(TieGame()){
            text.setText("Your Both Losers and Winners");
            GameOver();
        }
        else {
            SwitchPlayer();
        }
    }

    private void GameOver() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void SwitchPlayer(){
        if(currPlayer == x){
            currPlayer = o;
        }
        else{
            currPlayer = x;
        }

        PlayerDisplay(currPlayer);
    }

    private boolean TieGame() {
        //Check to see if it is full or nah
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[i][j].getText() == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean CheckWinner(String player){
        //Horizontal combination
        if (buttons[0][0].getText() == player && buttons[0][1].getText() == player && buttons[0][2].getText() == player ||
                buttons[1][0].getText() == player && buttons[1][1].getText() == player && buttons[1][2].getText() == player ||
                buttons[2][0].getText() == player && buttons[2][1].getText() == player && buttons[2][2].getText() == player) {
            return true;
        }
        //Vertical combination
        else if (buttons[0][0].getText() == player && buttons[1][0].getText() == player && buttons[2][0].getText() == player ||
                buttons[0][1].getText() == player && buttons[1][1].getText() == player && buttons[2][1].getText() == player ||
                buttons[0][2].getText() == player && buttons[1][2].getText() == player && buttons[2][2].getText() == player) {
            return true;
        }
        //Diagonal combination
        else if (buttons[0][0].getText() == player && buttons[1][1].getText() == player && buttons[2][2].getText() == player ||
                buttons[0][2].getText() == player && buttons[1][1].getText() == player && buttons[2][0].getText() == player) {
            return true;
        }
        return false;
    }


    private void PlayerDisplay(Player currPlayer) {
        text.setText("Player " + currPlayer.playerName + "'s turn");
    }
}
