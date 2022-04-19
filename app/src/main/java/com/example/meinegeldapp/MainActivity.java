package com.example.meinegeldapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.containerView, StartScreenFragment.class, null).commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, AddSpendingIncomeFragment.class, null).commit();
        });


        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.history) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, HistoryFragment.class, null).commit();
                return true;
            } else if (itemId == R.id.regularly) {
                return true;
            } else if (itemId == R.id.statistic) {
                return true;
            } else if (itemId == R.id.more) {
                return true;
            }
            return super.onOptionsItemSelected(item);

        });

    }



}