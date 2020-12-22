package com.bctecnica.houseHunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private TextView itemToFindText, nextFindTopText;
    private Button nextItemButton;
    private RelativeLayout mainGameLayout;
    int passedNumber;
    int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        // Sets player selection as int to pass to the findNextItem method
        passedNumber = getIntent().getIntExtra("PLAYER_SELECTION", 0);

        // Hides action bar in main game activity
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Link view elements to there corresponding java variables
        itemToFindText = findViewById(R.id.itemToFindText);
        nextFindTopText = findViewById(R.id.nextFindText);
        nextItemButton = findViewById(R.id.nextItemButton);
        mainGameLayout = findViewById(R.id.mainGameLayout);

        // When next item button is clicked
        nextItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLayout();

                // Creates each round using random items and color
                if(roundCount < 11) {
                    // Gets random item and color for respective arrays
                    String item = itemsToFind.getNextItem(passedNumber);
                    int color = colorWheel.getColor();
                    // Sets the corresponding fields
                    itemToFindText.setText(item);
                    mainGameLayout.setBackgroundColor(color);
                    nextItemButton.setTextColor(color);
                }
            }
        });
    }

    // Updates the text in the layout of main game through the rounds
    public void updateLayout(){
        if(roundCount == 0){
            nextFindTopText.setText("First up can you find...");
            nextItemButton.setText("NEXT");
        }
        if(roundCount == 1){
            nextFindTopText.setText("Next can you find..");
        }
        if(roundCount == 9){
            nextFindTopText.setText("Finally can you find.");
            nextItemButton.setText("FINISH");
        }
        if(roundCount == 10){
            endGamePopUp();
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

