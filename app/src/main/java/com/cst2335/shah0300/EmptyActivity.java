package com.cst2335.shah0300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        Bundle extra = getIntent().getExtras();

        DetailsFragment fragment = new DetailsFragment();
        FragmentManager fm = getSupportFragmentManager();
        fragment.setArguments(extra);
        fm.beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }
}