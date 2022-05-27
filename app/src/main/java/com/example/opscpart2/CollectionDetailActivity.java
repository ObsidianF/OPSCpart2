package com.example.opscpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CollectionDetailActivity extends AppCompatActivity {

    public final Collections_Items selectedCollection = new Collections_Items();

    private Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

        Home = findViewById(R.id.btnHome);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

    }


    private void getSelectedCollection(){

        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        //selectedCollection = MainActivity.collections_itemsList.get(Integer.valueOf(parsedStringID));



    }

    public void goHome(){

        startActivity(new Intent(CollectionDetailActivity.this, MainActivity.class));

    }

}