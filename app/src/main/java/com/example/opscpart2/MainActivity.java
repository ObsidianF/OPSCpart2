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

    private FirebaseAuth mAuth; // will get the instance of the logged in user if they are logged in
    private Button btnLogout;
    private Button btnAddCollection; // creates buttons
    private String uid; // string to store the user uid
    public static Collections_Items holder; // will create on an object to store tge clicked on item

    public final List<Collections_Items> collections_itemsList = new ArrayList<>(); // makes a list of Collections_Items objects
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(); // gets the reference of the realtime database

    @Override
    protected void onCreate(Bundle savedInstanceState) { // runs this method when the activity is started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // connects the activity to the related xml file

        mAuth = FirebaseAuth.getInstance();// gets the instance of the logged in user
        uid = mAuth.getUid(); // gets the uid of the current instance
        //Tutorialspoint.com. 2022. How to get current user is system user or not in android?. [online] Available at: <https://www.tutorialspoint.com/how-to-get-current-user-is-system-user-or-not-in-android> [Accessed 2 June 2022].
        //Jha, S. and Hacks, S., 2022. How to get user uid from firebase on android?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/37566911/how-to-get-user-uid-from-firebase-on-android> [Accessed 2 June 2022].

        btnLogout = findViewById(R.id.btnlogout); //sets a button up

        final RecyclerView recyclerView = findViewById(R.id.recyclerview); // sets the recyclerView to an object so we can manipulate it

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); // sets the constraints of the recyclerView

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collections_itemsList.clear(); // clears the item list incase it has been used

                for (DataSnapshot name : snapshot.child("Collection").getChildren()) { // keeps running the loop while there is still a child collection

                    if (name.child("uid").getValue(String.class).equals(uid)) { // checks if the uid of the user matches the uid of the collection
                        if (name.hasChild("name") && name.hasChild("goal") && name.hasChild("id") && name.hasChild("uid")) { // makes sure that the information that is being pulled is populated and isnt going to break the app
                            final String getName = name.child("name").getValue(String.class);
                            final String getGoal = name.child("goal").getValue(String.class);
                            final String getUID = name.child("uid").getValue(String.class);
                            final String getID = name.child("id").getValue(String.class); // stores the data from the object into local variables

                            Collections_Items collections_items = new Collections_Items(getName, getGoal, getUID, getID); // stores the variables in an object
                            collections_itemsList.add(collections_items); // adds the object to the list
                        }
                    }
                }
                recyclerView.setAdapter(new CollectionAdapter(collections_itemsList, MainActivity.this, new CollectionAdapter.ItemClickListner() { // sets the adapter for the recyclerView so that it can use the adapter to populate each cell. it passes the list through to the adapter so that each item can be placed in its own cell
                    @Override
                    public void onItemClick(Collections_Items details) { // sets an onItemClick for each item on the recyclerView so when it is clicked it will take the user to another screen

                        holder = new Collections_Items();
                        holder.setUid(details.getUid());
                        holder.setName(details.getName());
                        holder.setGoal(details.getGoal());
                        holder.setId(details.getId());
                        //sets the object of the item that was clicked

                        goDetails(); // runs the method to go to the details screen

                    }
                }));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout(); // makes a on click listener to log the user out

            }

        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        btnAddCollection = findViewById(R.id.btnlogout2);
        btnAddCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCollection();
            } // runs an setOnClickListener on a button to send the user to another screen
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser(); // gets the information of the currently logged in user

        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class)); // if no suer is logged in it will take you back to the login screen
        }
    }

    public void logout() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class)); // will sigh the user out and take them to the login screen

    }

    public void addCollection() {

        startActivity(new Intent(MainActivity.this, AddCollectionActivity.class)); // takes the user to the AddCollectionActivity screen

    }


    public void goDetails() {

        startActivity(new Intent(MainActivity.this, CollectionDetailActivity.class)); // takes the user to the CollectionDetailActivity screen

    }

}