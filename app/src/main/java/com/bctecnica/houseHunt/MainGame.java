package com.bctecnica.houseHunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainGame extends AppCompatActivity {
    int passedNumber;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        passedNumber = getIntent().getIntExtra("TOTAL_COUNT",0);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"number passed: " + passedNumber, Toast.LENGTH_SHORT).show();
            }
        });
    }
}