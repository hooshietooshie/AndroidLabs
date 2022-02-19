package com.cst2335.shah0300;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


public class ProfileActivity extends AppCompatActivity {

    public static final String TAG = "PROFILE_ACTIVITY";
    public static final String onCreate = "onCreate";
    public static final String onStart = "onStart";
    public static final String onResume = "onResume";
    public static final String onPause = "onPause";
    public static final String onStop = "onStop";
    public static final String onDestroy = "onDestroy";
    public static final String onActivityResult = "onActivityResult";

    EditText et = findViewById(R.id.editText);
    SharedPreferences spf;


    ImageView imgView;

    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
                        imgView.setImageBitmap(imgbitmap);
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED)
                        Log.i(TAG, getResources().getString(R.string.warning));
                    Log.e(TAG, "In function: " + onActivityResult);
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton imgBtn = (ImageButton) findViewById(R.id.imageButton);
        Intent fromMain = getIntent();
        String usrEmail = fromMain.getStringExtra("email");
        spf = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
        et.setText(usrEmail);


        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        Log.e(TAG, "In function: " + onCreate);

    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG, "In function: " + onStart);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(TAG, "In function: " + onResume);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(TAG, "In function: " + onPause);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(TAG, "In function: " + onStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "In function: " + onDestroy);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }

}
