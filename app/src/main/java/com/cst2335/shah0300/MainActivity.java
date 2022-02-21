package com.cst2335.shah0300;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    EditText email_et;
    EditText pass_et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences spf = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);

        String usrEmail = spf.getString("email", "");
        String usrPass = spf.getString("password", "");

        email_et = findViewById(R.id.editText);
        email_et.setText(usrEmail);
        pass_et = findViewById(R.id.editText2);
        pass_et.setText(usrPass);

        btn = findViewById(R.id.button2);

        btn.setOnClickListener((v) -> {
            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            goToProfile.putExtra("email", usrEmail);
            startActivity(goToProfile);

        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences spf = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = spf.edit();

        edit.putString("email", email_et.getText().toString());
        edit.putString("password", pass_et.getText().toString());
        edit.apply();


    }

}