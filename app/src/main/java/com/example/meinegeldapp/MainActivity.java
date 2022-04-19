package com.example.meinegeldapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.containerView, StartFragment.class, null).commit();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, AddSpendingIncomeFragment.class, null).commit();
        });


        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.history) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, HistoryFragment.class, null).commit();
                return true;
            } else if (itemId == R.id.start) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, StartFragment.class, null).commit();
                return true;
            }else if (itemId == R.id.regularly) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, RegularlyFragment.class, null).commit();
                return true;
            } else if (itemId == R.id.statistic) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, StatisticFragment.class, null).commit();
                return true;
            } else if (itemId == R.id.more) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, MoreFragment.class, null).commit();
                return true;
            }
            return super.onOptionsItemSelected(item);

        });

    }



}