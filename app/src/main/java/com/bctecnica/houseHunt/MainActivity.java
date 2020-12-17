package com.bctecnica.houseHunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup whereToPlayRadioGroup;
    RadioButton userSelectionRadioButton;
    ImageView playAreaImage;
    Button generateButton;
    int[] wherePictures = { R.drawable.temp_inside, R.drawable.temp_outside, R.drawable.temp_both};
    int passIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whereToPlayRadioGroup = findViewById(R.id.whereSelector);
        playAreaImage = findViewById(R.id.whereView);
        generateButton = findViewById(R.id.generateButton);

        whereToPlayRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                userSelectionRadioButton = radioGroup.findViewById(i);
                passIndex = radioGroup.indexOfChild(userSelectionRadioButton);
                playAreaImage.setImageResource(wherePictures[passIndex]);
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainGame.class);
                i.putExtra("PLAYER_SELECTION", passIndex);
                startActivity(i);
            }
        });
    }
    // Info icon in action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("info");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(R.drawable.email_icon);
        return true;
    }

    // Opens info pane when action bar icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bctecnica@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "House hunt app");
        try {
            startActivity(Intent.createChooser(i, "Send e-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}