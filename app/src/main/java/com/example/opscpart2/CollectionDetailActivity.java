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
    private final List<ItemGetSet> itemGetSetsList = new ArrayList<>();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Button Home;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

         final RecyclerView recyclerView = findViewById(R.id.recyclerview2);
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(CollectionDetailActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                itemGetSetsList.clear();
            for(DataSnapshot items : snapshot.child("Items").getChildren()){

                if (items.child("collectionID").getValue(String.class).equals(MainActivity.holder.getId()))
                {
                    if(items.hasChild("details") && items.hasChild("date"))
                    {

                        final  String getDetails = items.child("details").getValue(String.class);
                        final  String getDate = items.child("date").getValue(String.class);
                        final String getCollectionId = items.child("CollectionID").getValue(String.class);
                        final String getImagename = items.child("imagename").getValue(String.class);

                        ItemGetSet itemGetSet = new ItemGetSet(getDetails,getDate, getCollectionId, getImagename);

                        itemGetSetsList.add(itemGetSet);

                    }

                }

            }

            recyclerView.setAdapter(new MyAdpater(itemGetSetsList,CollectionDetailActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Home = findViewById(R.id.btnHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });


        btnAddItem = findViewById(R.id.btnAdd);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemScreen();
            }
        });

    }


    private void getSelectedCollection(){

        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        //selectedCollection = MainActivity.collections_itemsList.get(Integer.valueOf(parsedStringID));
    }

    public void addItemScreen() {
        startActivity(new Intent(CollectionDetailActivity.this, ImageActivity.class));
    }

    public void goHome(){

        startActivity(new Intent(CollectionDetailActivity.this, MainActivity.class));

    }

}