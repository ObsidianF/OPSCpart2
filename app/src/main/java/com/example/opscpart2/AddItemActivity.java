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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AddItemActivity extends AppCompatActivity {

    FirebaseDatabase database; // creates a an object to link to the firebase database
    DatabaseReference ref; // will be used to create a refence to the database and its child within the database
    DatabaseReference reference;
    DatabaseReference referenceDel = FirebaseDatabase.getInstance().getReference();
    EditText description; // creates 2 edit text varables that will be linked to corosponding edit text fields in the xml file
    int maxid = 0; // id that will be stored
    int madidtow = 0;
    ItemGetSet itemGetSetToAdd; // makes an object to store values in and send object to the database to be stored

    Button btn;
    Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item); // connects activity and the xml file

        description = findViewById(R.id.textFiled1);
        //sets the edit text fields to the ones in the xml file

        btn = findViewById(R.id.btnStore);
        //sets btn to the button in the xml file

        itemGetSetToAdd = new ItemGetSet(); // makes the object of type ItemGetSet
        ref = database.getInstance().getReference().child("Items");//gets te reference point in the database and the child location of Items to store the information in
        //Mamo, A., 2022. How to get the child from firebase in android studio?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/68889824/how-to-get-the-child-from-firebase-in-android-studio> [Accessed 2 June 2022].

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxid = (int) snapshot.getChildrenCount(); // this will get the highest id under the Items collection

                } else {

                }
            }
            //test coment
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final int min = 1;// min random number
        final int max = 1000000000; // max random number
        final int random = new Random().nextInt((max - min) + 1) + min; //gets a random number between the min and max
        String id = String.valueOf(random);// sets the random number as the id
        //Waheed, A., 2022. How can I generate a random number in a certain range?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/21049747/how-can-i-generate-a-random-number-in-a-certain-range> [Accessed 2 June 2022].




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




        reference = database.getInstance().getReference().child("Collection"); // gets the reference of the database under collections
        //Mamo, A., 2022. How to get the child from firebase in android studio?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/68889824/how-to-get-the-child-from-firebase-in-android-studio> [Accessed 2 June 2022].
        reference.addValueEventListener(new ValueEventListener() { // when called will start the process of adding a value to the database under coillections
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    madidtow = (int) snapshot.getChildrenCount(); // gets the highest count value in the database and stores it

                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btn.setOnClickListener(new View.OnClickListener() { // creates an on click listener so that it will run the code within when the button is clicked
            @Override
            public void onClick(View v) {
                String filename;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd", Locale.CANADA);  //creates a format template
                Date now = new Date(); // gets the current time and date
                filename = formatter.format(now); // this will get the name of the image by setting it to the time it was taken and also formatting it based on the formatter


                itemGetSetToAdd.setId(id);
                itemGetSetToAdd.setDetails(description.getText().toString());
                itemGetSetToAdd.setDate(filename);
                itemGetSetToAdd.setCollectionID(MainActivity.holder.getId());
                itemGetSetToAdd.setImagename(ImageActivity.filename);
                //sets all the values in the object to be stored
                //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].

                ref.child(String.valueOf(id)).setValue(itemGetSetToAdd); // stores the id + 1 and the object in the database
                //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].



                int itemNo = MainActivity.holder.getNumberOfItems()+ 1;
                MainActivity.holder.setNumberOfItems(itemNo);

                //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].
                //sets the values in the object

                reference.child(String.valueOf(MainActivity.holder.getId())).setValue(MainActivity.holder); // maxid + 1 sets the new id and setValue sets the object to be stored in the database
                //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].


                goHome(); // calls go home method

            }
        });
        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].

    }


    public void goHome() {

        startActivity(new Intent(AddItemActivity.this, CollectionDetailActivity.class)); // an activity to take the user to the main screen

    }

}