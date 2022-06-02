package com.example.opscpart2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollectionDetailActivity extends AppCompatActivity {

    public final Collections_Items selectedCollection = new Collections_Items();
    private final List<ItemGetSet> itemGetSetsList = new ArrayList<>(); //makes a list to store all the items in as objects
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(); // ges the reference of the database
    private Button Home;
    private Button btnAddItem; // buttons that will be used to do actions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview2); // sets the recyclerView up so that we can call the object and set data to it
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionDetailActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                itemGetSetsList.clear();
                for (DataSnapshot items : snapshot.child("Items").getChildren()) { // will keep running the loop until it no longer has data in it

                    if (items.child("collectionID").getValue(String.class).equals(MainActivity.holder.getId())) { // compares the id of the object that was clicked on in the prevois screen to the id of the of the object and if it matches it will continue
                        if (items.hasChild("details") && items.hasChild("date")) { // checks if the data has information

                            final String getDetails = items.child("details").getValue(String.class);
                            final String getDate = items.child("date").getValue(String.class);
                            final String getCollectionId = items.child("CollectionID").getValue(String.class);
                            final String getImagename = items.child("imagename").getValue(String.class);

                            ItemGetSet itemGetSet = new ItemGetSet(getDetails, getDate, getCollectionId, getImagename);
                            //sets all of the values to string and stores them in a object

                            itemGetSetsList.add(itemGetSet);//stores the object in the list

                        }

                    }

                }

                recyclerView.setAdapter(new MyAdpater(itemGetSetsList, CollectionDetailActivity.this)); // sets the recyclerView to the adapter and sends the adapter the list to populate the cells
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        Home = findViewById(R.id.btnHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            } // sets an OnClickListener to run a method
        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        btnAddItem = findViewById(R.id.btnAdd);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemScreen();
            }// sets an OnClickListener to run a method
        });

    }

    private void getSelectedCollection() {

        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        //selectedCollection = MainActivity.collections_itemsList.get(Integer.valueOf(parsedStringID));
    }

    public void addItemScreen() {
        startActivity(new Intent(CollectionDetailActivity.this, ImageActivity.class)); //takes user to add item screen
    }

    public void goHome() {

        startActivity(new Intent(CollectionDetailActivity.this, MainActivity.class)); // takes user to main screen

    }

}