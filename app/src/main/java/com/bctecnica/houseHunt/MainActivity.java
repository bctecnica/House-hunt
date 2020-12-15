package com.bctecnica.houseHunt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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


}