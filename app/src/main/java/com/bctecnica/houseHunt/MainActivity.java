package com.bctecnica.houseHunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    ImageView imageView;
    Button button;
    int[] wherePictures = { R.drawable.temp_inside, R.drawable.temp_outside, R.drawable.temp_both};
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.whereSelector);
        imageView = findViewById(R.id.whereView);
        button = findViewById(R.id.generateButton);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = radioGroup.findViewById(i);
                index = radioGroup.indexOfChild(radioButton);
                imageView.setImageResource(wherePictures[index]);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainGame.class);
                i.putExtra("TOTAL_COUNT", index);
                startActivity(i);
            }
        });


    }


}