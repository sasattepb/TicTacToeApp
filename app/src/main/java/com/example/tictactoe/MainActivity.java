package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0-right click 1-Cross Mark
    int activePlayer=0;
    //2 means overplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean isGameActive=true;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        Log.i("info", "clicked!!");

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && isGameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                //counter.setTranslationY(-100f);
                counter.setImageResource(R.drawable.redtitan);
                //counter.setTranslationY(100f);
                activePlayer = 1;
            } else {
                //counter.setTranslationY(-100f);
                counter.setImageResource(R.drawable.combopanda);
                //counter.setTranslationY(100f);
                activePlayer = 0;
            }
        }

        for(int[] winPos : winningPositions){
            if(gameState[winPos[0]] == gameState[winPos[1]]
                    && gameState[winPos[1]] == gameState[winPos[2]]
                    && gameState[winPos[0]] != 2){
                isGameActive = false;
                System.out.println("Winner is: " + activePlayer);

                String winner = "Combo Panda";
                if(gameState[winPos[0]] == 0){
                    winner = "Red Titan";
                }
                //someone is won
                TextView winnertext = findViewById(R.id.message);
                winnertext.setText(winner + " has won!!");
                LinearLayout linearLayout = findViewById(R.id.linearlayoutid);
                linearLayout.setVisibility(View.VISIBLE);
            }

            if(isGameActive) {
                int cnt = 0;
                for (int i = 0; i < gameState.length; i++) {
                    if (gameState[i] != 2) {
                        cnt++;
                    }
                }

                if (cnt == 9) {
                    TextView winnertext = findViewById(R.id.message);
                    winnertext.setText("Game is draw!!");
                    LinearLayout linearLayout = findViewById(R.id.linearlayoutid);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        isGameActive=true;
        LinearLayout linearLayout = findViewById(R.id.linearlayoutid);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer=0;

        for(int i=0; i < gameState.length; i++){
            gameState[i]=2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i < gridLayout.getChildCount(); i++){
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
