package com.example.opscpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameScreen extends AppCompatActivity {
    private Button goBackhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        goBackhome = findViewById(R.id.btnHome2);
        goBackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetail();
            } // sets an OnClickListener to run a method
        });
    }

    public void goToDetail() {

        startActivity(new Intent(GameScreen.this, MainActivity.class)); // will sigh the user out and take them to the login screen

    }

    }


