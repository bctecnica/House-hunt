package com.bctecnica.houseHunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainGame extends AppCompatActivity {

    private ItemsToFind itemsToFind = new ItemsToFind();
    private ColorWheel colorWheel = new ColorWheel();
    private TextView itemToFindText, instructionText, roundCountText, companyLogo, skipButton;
    private Button nextItemButton;
    private ConstraintLayout mainGameLayout;
    int passedNumber;
    int roundCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        skipButton = findViewById(R.id.skipButton);
        roundCountText = findViewById(R.id.roundCountText);
        itemToFindText = findViewById(R.id.itemToFindText);
        instructionText = findViewById(R.id.nextFindText);
        nextItemButton = findViewById(R.id.nextItemButton);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        companyLogo = findViewById(R.id.companyLogo);

        MediaPlayer click = MediaPlayer.create(this,R.raw.button_click);

        // Sets player selection as int to pass to the findNextItem method
        passedNumber = getIntent().getIntExtra("PLAYER_SELECTION", 0);

        // Hides action bar in main game activity
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Updates the main game each round using random items and colors
        nextItemButton.setOnClickListener(view -> {
            click.start();
            updateGame();
            updateLayout();
        });

        // Button to skips a round
        skipButton.setOnClickListener(view -> {
            roundCount--;
            updateGame();
            updateLayout();
        });
    }

    // Used to generate each round by getting a random item and color from the correct array passed in
    public void updateGame(){
        String item = itemsToFind.getNextItem(passedNumber);
        int color = colorWheel.getColor();
        roundCountText.setText("Round: "+ roundCount);
        itemToFindText.setText(item);
        mainGameLayout.setBackgroundColor(color);
        nextItemButton.setTextColor(color);
    }

    // Updates the layout of main game through the rounds
    public void updateLayout(){
        switch (roundCount) {
            case 1:
                instructionText.setText("First up can you find...");
                nextItemButton.setText("NEXT");
                skipButton.setText("Skip");
                break;
            case 4:
            case 7:
            case 9:
                instructionText.setText("Next can you find...");
                break;
            case 3 :
                instructionText.setText("You're doing great next can you find...");
                break;
            case 2:
            case 6:
                instructionText.setText("Ok next can you find...");
                break;
            case 5:
                instructionText.setText(R.string.half_way);
                break;
            case 8:
                instructionText.setText("You're nearly at the end now can you find..");
                break;
            case 10:
                instructionText.setText("Finally can you find...");
                nextItemButton.setText("FINISH");
                break;
            case 11:
                companyLogo.setText(R.string.company_logo);
                roundCountText.setVisibility(View.INVISIBLE);
                instructionText.setVisibility(View.INVISIBLE);
                nextItemButton.setVisibility(View.INVISIBLE);
                itemToFindText.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.INVISIBLE);
                mainGameLayout.setBackgroundColor(Color.parseColor("#000000"));
                endGamePopUp();
                break;
        }
        roundCount++;
    }

    // Popup window for when game ends at 10 rounds and resets
    public void endGamePopUp() {
        AlertDialog.Builder gameOverPopUp = new AlertDialog.Builder(this);
        gameOverPopUp.setCancelable(false);
        gameOverPopUp.setIcon(R.drawable.winner_icon);
        gameOverPopUp.setTitle("             -WELL DONE-");
        gameOverPopUp.setMessage("Time to count up each players pile and see who the winner is.");

        gameOverPopUp.setNegativeButton(
                "Play Again",
                (dialog, id) -> finish());

        AlertDialog alert11 = gameOverPopUp.create();
        alert11.show();
    }

}

