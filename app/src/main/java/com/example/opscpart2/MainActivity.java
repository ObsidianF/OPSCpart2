package com.example.opscpart2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

//import com.google.firebase.ktx.Firebase;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button btnLogout;

    private Button btnAddCollection;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnLogout = findViewById(R.id.btnlogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                logout();

            } });

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

}