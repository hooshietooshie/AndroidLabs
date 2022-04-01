package com.cst2335.shah0300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class TestToolbar extends AppCompatActivity {

    Toolbar t_bar;
    ActionBarDrawerToggle abdt;
    DrawerLayout drawer;
    NavigationView nav_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        t_bar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.navigation_view);


        drawer = findViewById(R.id.navigation_drawer);
        abdt = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);

        drawer.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nav_view.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.chat_page:
                    Intent goToChatPage = new Intent(TestToolbar.this, ChatRoomActivity.class);
                    startActivity(goToChatPage);
                    break;
                case R.id.weather_forecast:
                    Intent weather_intent = new Intent(TestToolbar.this, WeatherForecast.class);
                    startActivity(weather_intent);
                    break;
                case R.id.back_to_login:
                    finish();
                    break;
            }

            drawer = findViewById(R.id.navigation_drawer);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.overflow_action) {
            Toast.makeText(this, "You clicked on the overflow menu", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.choice_1) {
            Toast.makeText(this, "You clicked on item 1", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.choice_2) {
            Toast.makeText(this, "You clicked on item 2", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.choice_3) {
            Toast.makeText(this, "You clicked on item 3", Toast.LENGTH_SHORT).show();
        } else return abdt.onOptionsItemSelected(item);

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menu_inflate = getMenuInflater();
        menu_inflate.inflate(R.menu.main_menu, menu);
        return true;
    }
}