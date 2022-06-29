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
import android.widget.Toast;

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
    public static Uri imageUri;
    ActivityImageBinding binding; //creates a binding so i can link objects to tags in the xml file
    //Android Developers. 2022. Generated binding classes  |  Android Developers. [online] Available at: <https://developer.android.com/topic/libraries/data-binding/generated-binding> [Accessed 2 June 2022].
    private ImageView myImageView;
    StorageReference storageReference; // a reference object to the storage for the image
    public static String filename;
    private boolean checkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkImage = false;

        binding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // sets up the connection between this class and the xml file
        //Android Developers. 2022. Generated binding classes  |  Android Developers. [online] Available at: <https://developer.android.com/topic/libraries/data-binding/generated-binding> [Accessed 2 June 2022].

        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                checkImage = true;
            }  // makes an OnClickListener to run a method

        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        btnStore = findViewById(R.id.btnStore);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkImage){

                    uploadImage();
                    addItemScreen();
                }
                else{

                    Toast.makeText(ImageActivity.this, "Select an image", Toast.LENGTH_SHORT).show();

                }

            }// makes an OnClickListener to run the methods
        });

        //Lion, L., 2022. how to go another screen in android studio on button click Code Example. [online] Codegrepper.com. Available at: <https://www.codegrepper.com/code-examples/java/how+to+go+another+screen+in+android+studio+on+button+click> [Accessed 2 June 2022].
        Home = findViewById(R.id.btnHome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }// makes an OnClickListener to run a method
        });

    }

    private void uploadImage() { // method to get the image that was taken and upload it

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);  //creates a format template
        Date now = new Date(); // gets the current time and date
        filename = formatter.format(now); // this will get the name of the image by setting it to the time it was taken and also formatting it based on the formatter

        storageReference = FirebaseStorage.getInstance().getReference("images/" + filename); // gets the reference of the storage that the image is going to be stored in and also gives sets the name
        //Mamo, A., 2022. How to get the child from firebase in android studio?. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/68889824/how-to-get-the-child-from-firebase-in-android-studio> [Accessed 2 June 2022].
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() { //sends the image to the storage
            //Youtube.com. n.d. Upload Image to Firebase in Android Studio | Upload Image to Firebase Storage | Android Studio. [online] Available at: <https://www.youtube.com/watch?v=g2Iibnnqga0> [Accessed 2 June 2022].
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                binding.imageView.setImageURI(null); // removes the image from the imageview

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        imageUri = uri; // gets the image download link o download the image from the storage in firebase

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    private void selectImage() {

        //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //startActivity(intent);
        //ACTION_GET_CONTENT

        //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //intent.setType("image/");
        //startActivityForResult(intent, 100);

        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
        //Youtube.com. n.d. Upload Image to Firebase in Android Studio | Upload Image to Firebase Storage | Android Studio. [online] Available at: <https://www.youtube.com/watch?v=g2Iibnnqga0> [Accessed 2 June 2022].
        //Guides.codepath.com. 2022. Accessing the Camera and Stored Media | CodePath Android Cliffnotes. [online] Available at: <https://guides.codepath.com/android/Accessing-the-Camera-and-Stored-Media> [Accessed 2 June 2022].
        // this will open the camera roll so the user can select an image to store

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data.getData() != null) {
            imageUri = data.getData(); // gets image uri
            binding.imageView.setImageURI(imageUri);
            //Youtube.com. n.d. Upload Image to Firebase in Android Studio | Upload Image to Firebase Storage | Android Studio. [online] Available at: <https://www.youtube.com/watch?v=g2Iibnnqga0> [Accessed 2 June 2022].
            //Android Developers. 2022. Generated binding classes  |  Android Developers. [online] Available at: <https://developer.android.com/topic/libraries/data-binding/generated-binding> [Accessed 2 June 2022].
            //Guides.codepath.com. 2022. Accessing the Camera and Stored Media | CodePath Android Cliffnotes. [online] Available at: <https://guides.codepath.com/android/Accessing-the-Camera-and-Stored-Media> [Accessed 2 June 2022].
        }
    }

    public void addItemScreen() {
        startActivity(new Intent(ImageActivity.this, AddItemActivity.class));
    }

    public void goHome() {

        startActivity(new Intent(ImageActivity.this, MainActivity.class));

    }

    public void goBack() {

        startActivity(new Intent(ImageActivity.this, CollectionDetailActivity.class));

    }

}