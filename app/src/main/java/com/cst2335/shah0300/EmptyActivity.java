package com.cst2335.shah0300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        DetailsFragment fragment = new DetailsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}