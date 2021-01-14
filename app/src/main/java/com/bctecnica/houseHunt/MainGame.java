package com.bctecnica.houseHunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;
import java.util.Objects;

public class MainGame extends AppCompatActivity {

    private ItemsToFind itemsToFind = new ItemsToFind();
    private ColorWheel colorWheel = new ColorWheel();
    private TextView itemToFindText, instructionText, roundCountText, companyLogo, skipButton, countdownTimerText, howToText;
    private Button nextItemButton;
    private ImageView countdownIcon;
    private ConstraintLayout mainGameLayout;
    private static final long START_TIME_IN_MILLIS = 120000;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean isTimerRunning;
    private CountDownTimer countdownTimer;
    int passedNumber;
    int roundCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        howToText = findViewById(R.id.howToText);
        skipButton = findViewById(R.id.skipButton);
        roundCountText = findViewById(R.id.roundCountText);
        itemToFindText = findViewById(R.id.itemToFindText);
        instructionText = findViewById(R.id.nextFindText);
        nextItemButton = findViewById(R.id.nextItemButton);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        companyLogo = findViewById(R.id.companyLogo);
        countdownIcon = findViewById(R.id.countdownIconView);
        countdownTimerText = findViewById(R.id.countdownTimerText);

        MediaPlayer click = MediaPlayer.create(this,R.raw.button_click);
        MediaPlayer ringing = MediaPlayer.create(this,R.raw.alarm_clock);

        // Hides action bar in main game activity
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Sets player selection as int to pass to the findNextItem method
        passedNumber = getIntent().getIntExtra("PLAYER_SELECTION", 0);

        // Starts 2 min timer that rings when complete
        countdownIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdownTimerText.setVisibility(View.VISIBLE);
                countdownIcon.setVisibility(View.INVISIBLE);
                countdownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }
                @Override
                public void onFinish() {
                    ringing.start();
                }
            }.start();

                isTimerRunning = true;
        }

            // Formats to countdown to 00:00
            private void updateCountDownText() {
                int minutes = (int) (timeLeftInMillis / 1000) / 60;
                int seconds = (int) (timeLeftInMillis / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                countdownTimerText.setText(timeLeftFormatted);
            }
        });

        // Resets timer
        countdownTimerText.setOnClickListener(view -> resetTimer());

        // Updates the main game each round using random items and colors
        nextItemButton.setOnClickListener(view -> {
            click.start();
            resetTimer();
            updateGame();
            updateLayout();
        });

        // Button to skips a round
        skipButton.setOnClickListener(view -> {
            roundCount--;
            resetTimer();
            updateGame();
            updateLayout();
        });
    }

    // Resets count but only if counter is running
    private void resetTimer() {
        if(isTimerRunning) {
            timeLeftInMillis = START_TIME_IN_MILLIS;
            countdownIcon.setVisibility(View.VISIBLE);
            countdownTimerText.setVisibility(View.INVISIBLE);
            countdownTimer.cancel();
            isTimerRunning = false;
        }
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
                howToText.setVisibility(View.INVISIBLE);
                countdownIcon.setVisibility(View.VISIBLE);
                itemToFindText.setTextSize(39);
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
                instructionText.setText("Ok now can you find...");
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
                countdownIcon.setVisibility(View.INVISIBLE);
                mainGameLayout.setBackgroundResource(R.drawable.end_game_background);
                endGamePopUp();
                break;
        }
        roundCount++;
    }

    // Popup window with fanfare for when game ends at 10 rounds and resets
    public void endGamePopUp() {
        MediaPlayer clapping = MediaPlayer.create(this,R.raw.clapping);
        clapping.start();

        AlertDialog.Builder gameOverPopUp = new AlertDialog.Builder(this);
        gameOverPopUp.setCancelable(false);
        gameOverPopUp.setIcon(R.drawable.winner_icon);
        gameOverPopUp.setTitle("            -CONGRATULATIONS-");
        gameOverPopUp.setMessage("Well done scavengers, hope you had fun. " +
                "Time to count up each players collection and see who is the winner.");

        gameOverPopUp.setNegativeButton(
                "Play Again",
                (dialog, id) -> finish());

        AlertDialog alert11 = gameOverPopUp.create();
        alert11.show();
    }

}






