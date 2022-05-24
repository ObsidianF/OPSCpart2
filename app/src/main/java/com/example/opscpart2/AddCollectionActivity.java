package com.example.opscpart2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCollectionActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    EditText collectionname,goal;
    int maxid = 0;
    Collections collections;
    Button btn;

    private Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);

        collectionname= findViewById(R.id.textName);
        goal = findViewById(R.id.textGoal);
        btn = findViewById(R.id.btnStore);

        collections = new Collections();
        ref = database.getInstance().getReference().child("Collection");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    maxid = (int) snapshot.getChildrenCount();

                }else{

                    ///

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            collections.setName(collectionname.getText().toString());
            collections.setName(goal.getText().toString());

            ref.child(String.valueOf(maxid+1)).setValue(collections);

        }
    });



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