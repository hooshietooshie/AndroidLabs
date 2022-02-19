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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String usrEmail, usrPass;
        Button btn = findViewById(R.id.button2);

        SharedPreferences spf;
        EditText et = findViewById(R.id.editText);
        EditText etp = findViewById(R.id.editText2);

        spf = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
        usrEmail = spf.getString("email", "");
        usrPass = spf.getString("pass", "");
        et.setText(usrEmail);
        etp.setText(usrPass);

        btn.setOnClickListener(v -> {
            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            goToProfile.putExtra("email", getResources().getString(R.string.email_hint));
            startActivity(goToProfile);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences spf;

        EditText et = findViewById(R.id.editText);
        EditText etp = findViewById(R.id.editText2);
        Button bt = findViewById(R.id.button2);

        String inputEmail = et.getText().toString();
        String inputPass = etp.getText().toString();

        spf = getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = spf.edit();

        edit.putString("email", et.getText().toString());
        edit.putString("pass", etp.getText().toString());
        edit.apply();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}