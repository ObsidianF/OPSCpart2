package com.example.opscpart2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollectionDetailActivity extends AppCompatActivity {

    public final Collections_Items selectedCollection = new Collections_Items();
    private final List<ItemGetSet> itemGetSetsList = new ArrayList<>(); //makes a list to store all the items in as objects
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(); // ges the reference of the database
    private final DatabaseReference referenceDel = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private int maxidt;
    private Button Home;
    private Button btnAddItem; // buttons that will be used to do actions
    private Button btnEdit;
    public static boolean checkEdit;
    private boolean stopChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

        checkEdit = false;
        stopChange = true;

        final RecyclerView recyclerView = findViewById(R.id.recyclerview2); // sets the recyclerView up so that we can call the object and set data to it
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionDetailActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

                itemGetSetsList.clear();
                for (DataSnapshot items : snapshot.child("Items").getChildren()) { // will keep running the loop until it no longer has data in it

                    if (items.child("collectionID").getValue(String.class).equals(MainActivity.holder.getId())) { // compares the id of the object that was clicked on in the prevois screen to the id of the of the object and if it matches it will continue
                        if (items.hasChild("details") && items.hasChild("date")) { // checks if the data has information

                            final String getDetails = items.child("details").getValue(String.class);
                            final String getDate = items.child("date").getValue(String.class);
                            final String getCollectionId = items.child("CollectionID").getValue(String.class);
                            final String getImagename = items.child("imagename").getValue(String.class);
                            final String getImageUri = items.child("imageUri").getValue(String.class);
                            final String getID = items.child("id").getValue(String.class);

                            ItemGetSet itemGetSet = new ItemGetSet(getDetails, getDate, getCollectionId, getImagename, getID);
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

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goEdit();
            } // sets an OnClickListener to run a method
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
        if (stopChange){

            startActivity(new Intent(CollectionDetailActivity.this, MainActivity.class)); // takes user to main screen

        }



    }

    public void goEdit() {

        checkEdit = true;
        startActivity(new Intent(CollectionDetailActivity.this, AddCollectionActivity.class)); // takes user to main screen


    }




    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int x = viewHolder.getAdapterPosition();
            ItemGetSet delete_item = itemGetSetsList.get(x);
            stopChange = false;

            Query itemsQuery = databaseReference.child("Items").orderByChild("id").equalTo(delete_item.getId());
            itemsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot Snapshot: dataSnapshot.getChildren()) {
                        Snapshot.getRef().removeValue();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Query collectionQuery = referenceDel.child("Collection").orderByChild("id").equalTo(MainActivity.holder.getId());
            collectionQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot Snapshot: dataSnapshot.getChildren()) {
                        Snapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Toast.makeText(CollectionDetailActivity.this, delete_item.getDetails() + " deleted!", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    reference = database.getInstance().getReference().child("Collection"); // gets the reference of the database under collections
                    //Mamo, A., 2022. How to get the child from firebase in android studio?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/68889824/how-to-get-the-child-from-firebase-in-android-studio> [Accessed 2 June 2022].
                    reference.addValueEventListener(new ValueEventListener() { // when called will start the process of adding a value to the database under coillections
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                maxidt = (int) snapshot.getChildrenCount(); // gets the highest count value in the database and stores it

                            } else {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    int itemNo = MainActivity.holder.getNumberOfItems();
                    itemNo = itemNo - 1;
                    MainActivity.holder.setNumberOfItems(itemNo);

                    //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].
                    //sets the values in the object



                    reference.child(String.valueOf(MainActivity.holder.getId())).setValue(MainActivity.holder); // maxid + 1 sets the new id and setValue sets the object to be stored in the database
                    //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].
                    stopChange = true;



                }
            }, 5000);



        }
    };




}