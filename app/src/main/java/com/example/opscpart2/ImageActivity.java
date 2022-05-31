package com.example.opscpart2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.opscpart2.databinding.ActivityImageBinding;
import com.example.opscpart2.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ImageActivity extends AppCompatActivity {

    private Button btnGet;
    private Button btnStore;
    private Button Home;
    Uri imageUri;
    ActivityImageBinding binding;
    private ImageView myImageView;
    StorageReference storageReference;
    public static String filename;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }

        });


        btnStore = findViewById(R.id.btnStore);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
                addItemScreen();

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

    private void uploadImage() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        filename = formatter.format(now);

        storageReference = FirebaseStorage.getInstance().getReference("images/"+filename);
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                binding.imageView.setImageURI(null);





            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data.getData() != null){

            imageUri = data.getData();
            binding.imageView.setImageURI(imageUri);






        }

    }

    public void addItemScreen() {
        startActivity(new Intent(ImageActivity.this, AddItemActivity.class));
    }

    public void goHome(){

        startActivity(new Intent(ImageActivity.this, MainActivity.class));

    }

}