package com.example.opscpart2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddCollectionActivity extends AppCompatActivity {

    FirebaseDatabase database; //creates an object that will store the database information
    DatabaseReference ref; // an object that will be used to get the reference of the database
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    EditText collectionname, goal; // 2 variables of type edit text, they will store values in the to be sent to the database
    int maxid = 0; // the next id of the object in the database. will get the latest id and add 1 to it for the next item
    Collections_Items collectionsItems; // this object will be populated with all the new values
    Button btn; // creaes a buton
    private Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);//sets this activity to the related xml file so they can communicate

        if (CollectionDetailActivity.checkEdit)
        {

            String nameHolder = MainActivity.holder.getName();
            int goalHolder = MainActivity.holder.getGoal();
            String uidHolder = MainActivity.holder.getUid();
            String idHolder = MainActivity.holder.getId();
            int noItems = MainActivity.holder.getNumberOfItems();

            collectionsItems = new Collections_Items(); // creates the object of type Collections_Items\

            collectionsItems.setName(nameHolder);
            collectionsItems.setGoal(goalHolder);
            collectionsItems.setUid(uidHolder);
            collectionsItems.setId(idHolder);
            collectionsItems.setNumberOfItems(noItems);

            collectionname = findViewById(R.id.textName);
            goal = findViewById(R.id.textGoal); // sets both edit text variables to the related edit text views in the xml file so they can store text entered
            collectionname.setText(nameHolder);
            goal.setText(goalHolder);



            ref = database.getInstance().getReference().child("Collection"); // gets the reference of the database under collections
            //Mamo, A., 2022. How to get the child from firebase in android studio?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/68889824/how-to-get-the-child-from-firebase-in-android-studio> [Accessed 2 June 2022].
            ref.addValueEventListener(new ValueEventListener() { // when called will start the process of adding a value to the database under coillections
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        maxid = (int) snapshot.getChildrenCount(); // gets the highest count value in the database and stores it

                    } else {

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




            Query collectionQuery = reference.child("Collection").orderByChild("id").equalTo(collectionsItems.getId());
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





            btn = findViewById(R.id.btnStore); //creates a link between the button object and the button inside the xml file
            btn.setOnClickListener(new View.OnClickListener() { // sets an onclick listener on the button
                @Override
                public void onClick(View v) {

                    collectionsItems.setName(collectionname.getText().toString());
                    collectionsItems.setGoal(Integer.parseInt(goal.getText().toString()));
                    //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].
                    //sets the values in the object

                    ref.child(String.valueOf(idHolder)).setValue(collectionsItems); // maxid + 1 sets the new id and setValue sets the object to be stored in the database
                    //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].

                    goHome(); // calls the go home method

                }
            });

        }
        else
        {


            collectionname = findViewById(R.id.textName);
            goal = findViewById(R.id.textGoal); // sets both edit text variables to the related edit text views in the xml file so they can store text entered

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); // gets the current logged in users id to be stored for reference
            //Tutorialspoint.com. 2022. How to get current user is system user or not in android?. [online] Available at: <https://www.tutorialspoint.com/how-to-get-current-user-is-system-user-or-not-in-android> [Accessed 2 June 2022].
            //Jha, S. and Hacks, S., 2022. How to get user uid from firebase on android?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/37566911/how-to-get-user-uid-from-firebase-on-android> [Accessed 2 June 2022].

            final int min = 1;// min random number
            final int max = 1000000000; // max random number
            final int random = new Random().nextInt((max - min) + 1) + min; //gets a random number between the min and max
            String id = String.valueOf(random);// sets the random number as the id
            //Waheed, A., 2022. How can I generate a random number in a certain range?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/21049747/how-can-i-generate-a-random-number-in-a-certain-range> [Accessed 2 June 2022].

            btn = findViewById(R.id.btnStore); //creates a link between the button object and the button inside the xml file

            collectionsItems = new Collections_Items(); // creates the object of type Collections_Items
            ref = database.getInstance().getReference().child("Collection"); // gets the reference of the database under collections
            //Mamo, A., 2022. How to get the child from firebase in android studio?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/68889824/how-to-get-the-child-from-firebase-in-android-studio> [Accessed 2 June 2022].
            ref.addValueEventListener(new ValueEventListener() { // when called will start the process of adding a value to the database under coillections
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        maxid = (int) snapshot.getChildrenCount(); // gets the highest count value in the database and stores it

                    } else {

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
            btn.setOnClickListener(new View.OnClickListener() { // sets an onclick listener on the button
                @Override
                public void onClick(View v) {

                    collectionsItems.setName(collectionname.getText().toString());
                    collectionsItems.setGoal(Integer.parseInt(goal.getText().toString()));
                    collectionsItems.setUid(uid);
                    collectionsItems.setId(id);
                    collectionsItems.setNumberOfItems(0);

                    //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].
                    //sets the values in the object

                    ref.child(String.valueOf(id)).setValue(collectionsItems); // maxid + 1 sets the new id and setValue sets the object to be stored in the database
                    //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].

                    goHome(); // calls the go home method

                }
            });


        }

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        Home = findViewById(R.id.btnHome); // button link
        Home.setOnClickListener(new View.OnClickListener() { // sets an on click lister to the button
            @Override
            public void onClick(View v) {
                goHome();
            } // calls the go home method when called
        });

    }

    //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
    public void goHome() {

        startActivity(new Intent(AddCollectionActivity.this, MainActivity.class)); // makes an activity where it takes the user to the main screen

    }


}