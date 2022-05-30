package com.example.opscpart2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLogout;
    private Button btnAddCollection;
    private String uid;
    public static Collections_Items holder;



    public final List<Collections_Items> collections_itemsList = new ArrayList<>();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();
        btnLogout = findViewById(R.id.btnlogout);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collections_itemsList.clear();

                for(DataSnapshot name : snapshot.child("Collection").getChildren()){

                    if (name.child("uid").getValue(String.class).equals(uid))
                    {
                        if(name.hasChild("name") && name.hasChild("goal") && name.hasChild("id") && name.hasChild("uid")){
                            final String getName = name.child("name").getValue(String.class);
                            final String getGoal = name.child("goal").getValue(String.class);
                            final String getUID = name.child("uid").getValue(String.class);
                            final String getID = name.child("id").getValue(String.class);

                            Collections_Items collections_items = new Collections_Items(getName, getGoal, getUID, getID);
                            collections_itemsList.add(collections_items);
                        }

                    }

                }
                recyclerView.setAdapter(new CollectionAdapter(collections_itemsList, MainActivity.this, new CollectionAdapter.ItemClickListner() {
                    @Override
                    public void onItemClick(Collections_Items details) {

                        holder = new Collections_Items();
                        holder.setUid(details.getUid());
                        holder.setName(details.getName());
                        holder.setGoal(details.getGoal());
                        holder.setId(details.getId());

                        goDetails();

                    }
                }));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                logout();

            }

        });

        btnAddCollection = findViewById(R.id.btnlogout2);

        btnAddCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCollection();
            }
        });



    }

    @Override

    public void onStart(){

        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();


        if(currentUser==null)

        { startActivity(new Intent(MainActivity.this, LoginActivity.class));

        } }

    public void logout() {

        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }

    public void addCollection() {


        startActivity(new Intent(MainActivity.this, AddCollectionActivity.class));

    }


    public void goDetails() {


        startActivity(new Intent(MainActivity.this, CollectionDetailActivity.class));

    }






}