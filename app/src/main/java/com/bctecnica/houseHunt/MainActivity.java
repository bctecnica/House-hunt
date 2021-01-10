package com.bctecnica.houseHunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup whereToPlayRadioGroup;
    RadioButton playerSelectionRadioButton;
    ImageView currentWhereToPlayImage;
    Button generateButton;
    int[] whereToPlayPictures = { R.drawable.final_inside, R.drawable.final_outside, R.drawable.final_both};
    int playerSelectionToPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whereToPlayRadioGroup = findViewById(R.id.whereSelector);
        currentWhereToPlayImage = findViewById(R.id.whereView);
        generateButton = findViewById(R.id.generateButton);

        MediaPlayer click = MediaPlayer.create(MainActivity.this,R.raw.button_click);

        // Changes image whe radio buttons clicked and saves the value of selection to pass through intent
        whereToPlayRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            click.start();
            playerSelectionRadioButton = radioGroup.findViewById(i);
            playerSelectionToPass = radioGroup.indexOfChild(playerSelectionRadioButton);
            currentWhereToPlayImage.setImageResource(whereToPlayPictures[playerSelectionToPass]);
        });

        generateButton.setOnClickListener(view -> {
            click.start();
            Intent i = new Intent(getApplicationContext(), MainGame.class);
            i.putExtra("PLAYER_SELECTION", playerSelectionToPass);
            startActivity(i);
        });
    }

    // Drop down menu to send email
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bctecnica@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Scavenger Hunt app");
        try {
            startActivity(Intent.createChooser(i, "Send e-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}