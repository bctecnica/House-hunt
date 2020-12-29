package com.bctecnica.houseHunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainGame extends AppCompatActivity {

    private ItemsToFind itemsToFind = new ItemsToFind();
    private ColorWheel colorWheel = new ColorWheel();
    private TextView itemToFindText, nextFindTopText, roundCountText, companyLogo, skipButton;
    private Button nextItemButton;
    private ConstraintLayout mainGameLayout;
    int passedNumber;
    int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        skipButton = findViewById(R.id.skipButton);
        roundCountText = findViewById(R.id.roundCountText);
        itemToFindText = findViewById(R.id.itemToFindText);
        nextFindTopText = findViewById(R.id.nextFindText);
        nextItemButton = findViewById(R.id.nextItemButton);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        companyLogo = findViewById(R.id.companyLogo);

        MediaPlayer click = MediaPlayer.create(this,R.raw.button_click);

        // Sets player selection as int to pass to the findNextItem method
        passedNumber = getIntent().getIntExtra("PLAYER_SELECTION", 0);

        // Hides action bar in main game activity
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Updates the main game and moves to the next round
        nextItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                updateLayout();

                // Creates each round using random items and color
                if(roundCount < 11) {
                    updateGame();
                }
            }
        });

        // Button to skips a round
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundCount--;
                updateLayout();
                updateGame();
            }
        });
    }

    // Used to generate each round
    public void updateGame(){
        // Gets random item and color for respective arrays
        String item = itemsToFind.getNextItem(passedNumber);
        int color = colorWheel.getColor();
        // Sets the corresponding fields
        roundCountText.setText("Round: "+ roundCount);
        itemToFindText.setText(item);
        mainGameLayout.setBackgroundColor(color);
        nextItemButton.setTextColor(color);
    }

    // Updates the layout of main game through the rounds
    public void updateLayout(){
        switch (roundCount) {
            case 0:
                nextFindTopText.setText("First up can you find...");
                nextItemButton.setText("NEXT");
                skipButton.setText("Skip");
                break;
            case 1:
            case 5:
                nextFindTopText.setText("Next can you find...");
                break;
            case 4:
                nextFindTopText.setText(R.string.half_way);
                break;
            case 9:
                nextFindTopText.setText("Finally can you find.");
                nextItemButton.setText("FINISH");
                break;
            case 10:
                companyLogo.setText(R.string.company_logo);
                roundCountText.setVisibility(View.INVISIBLE);
                nextFindTopText.setVisibility(View.INVISIBLE);
                nextItemButton.setVisibility(View.INVISIBLE);
                itemToFindText.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                mainGameLayout.setBackgroundColor(Color.parseColor("#000000"));
                endGamePopUp();
                break;
        }
        roundCount++;
    }

    // Window for when game ends at 10 rounds and resets
    public void endGamePopUp() {
        AlertDialog.Builder gameOverPopUp = new AlertDialog.Builder(this);
        gameOverPopUp.setCancelable(false);
        gameOverPopUp.setIcon(R.drawable.winner_icon);
        gameOverPopUp.setTitle("             -GAME OVER-");
        gameOverPopUp.setMessage("Time to count up each players pile and see who the winner is.");

        gameOverPopUp.setNegativeButton(
                "Play Again",
                (dialog, id) -> finish());

        AlertDialog alert11 = gameOverPopUp.create();
        alert11.show();
    }

}

