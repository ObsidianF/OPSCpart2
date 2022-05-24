package com.example.opscpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddCollectionActivity extends AppCompatActivity {

    private Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);

        Home = findViewById(R.id.btnHome);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    public void goHome(){
        startActivity(new Intent(AddCollectionActivity.this, MainActivity.class));

    }


}