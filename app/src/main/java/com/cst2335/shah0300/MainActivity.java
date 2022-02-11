package com.cst2335.shah0300;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);

        //button
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(
                (v) -> {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_message), LENGTH_LONG).show();
                }
        );

        Switch swh = findViewById(R.id.switch2);
        swh.setOnCheckedChangeListener((cb, check) -> {
            Snackbar.make(swh, getResources().getString(R.string.snack_message), Snackbar.LENGTH_LONG).setAction(getResources().getString(R.string.undo), click -> cb.setChecked(!check)).show();
        });

    }
}