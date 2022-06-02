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

    FirebaseDatabase database; // creates a an object to link to the firebase database
    DatabaseReference ref; // will be used to create a refence to the database and its child within the database
    EditText description, dateText; // creates 2 edit text varables that will be linked to corosponding edit text fields in the xml file
    int maxid = 0; // id that will be stored
    ItemGetSet itemGetSetToAdd; // makes an object to store values in and send object to the database to be stored

    Button btn;
    Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item); // connects activity and the xml file

        description = findViewById(R.id.textFiled1);
        dateText = findViewById(R.id.textFiled2);
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() { // creates an on click listener so that it will run the code within when the button is clicked
            @Override
            public void onClick(View v) {

                itemGetSetToAdd.setDetails(description.getText().toString());
                itemGetSetToAdd.setDate(dateText.getText().toString());
                itemGetSetToAdd.setCollectionID(MainActivity.holder.getId());
                itemGetSetToAdd.setImagename(ImageActivity.filename);
                //sets all the values in the object to be stored
                //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].

                ref.child(String.valueOf(maxid + 1)).setValue(itemGetSetToAdd); // stores the id + 1 and the object in the database
                //Firebase. 2022. Read and Write Data on Android  |  Firebase Documentation. [online] Available at: <https://firebase.google.com/docs/database/android/read-and-write> [Accessed 2 June 2022].

                goHome(); // calls go home method

            }
        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        Home = findViewById(R.id.btnHome);
        Home.setOnClickListener(new View.OnClickListener() { // creates a button and it listener so that when it is cliked it will run the ho home method
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

    }


    public void goHome() {

        startActivity(new Intent(AddItemActivity.this, MainActivity.class)); // an activity to take the user to the main screen

    }

}