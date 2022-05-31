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

public class AddItemActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    EditText description, dateText;
    int maxid = 0;
    ItemGetSet itemGetSetToAdd;
    Button btn;
    private Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        description = findViewById(R.id.textFiled1);
        dateText = findViewById(R.id.textFiled2);

        btn = findViewById(R.id.btnStore);

        itemGetSetToAdd = new ItemGetSet();
        ref = database.getInstance().getReference().child("Items");

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

                itemGetSetToAdd.setDetails(description.getText().toString());
                itemGetSetToAdd.setDate(dateText.getText().toString());
                itemGetSetToAdd.setCollectionID(MainActivity.holder.getId());
                itemGetSetToAdd.setImagename(ImageActivity.filename);


                ref.child(String.valueOf(maxid+1)).setValue(itemGetSetToAdd);

                goHome();

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

        startActivity(new Intent(AddItemActivity.this, MainActivity.class));

    }

}