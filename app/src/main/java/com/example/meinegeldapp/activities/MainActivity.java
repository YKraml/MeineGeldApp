package com.example.meinegeldapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.meinegeldapp.fragments.MoreFragment;
import com.example.meinegeldapp.R;
import com.example.meinegeldapp.fragments.RegularlyFragment;
import com.example.meinegeldapp.fragments.StartFragment;
import com.example.meinegeldapp.fragments.StatisticFragment;
import com.example.meinegeldapp.fragments.HistoryFragment;
import com.example.meinegeldapp.model.TransactionManager;
import com.example.meinegeldapp.model.TransactionType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView navigationView;

    private boolean addButtonIsPressedDown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TransactionManager.createInstance(getApplicationContext());

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.containerView, StartFragment.class, null).commit();

        addListenersToBottomNavigationView();

        FloatingActionButton addFab = findViewById(R.id.extendButton);
        FloatingActionButton fab1 = findViewById(R.id.floatingActionButton1);
        FloatingActionButton fab2 = findViewById(R.id.floatingActionButton2);
        FloatingActionButton fab3 = findViewById(R.id.floatingActionButton3);
        FloatingActionButton fab4 = findViewById(R.id.floatingActionButton4);

        List<View> views = new ArrayList<>();
        views.add(fab1);
        views.add(fab2);
        views.add(fab3);
        views.add(fab4);
        views.add(findViewById(R.id.textView1));
        views.add(findViewById(R.id.textView2));
        views.add(findViewById(R.id.textView3));
        views.add(findViewById(R.id.textView4));

        fab1.setOnClickListener(view -> {
            onAddButtonClicked(addFab, views);
            Intent intent = new Intent(this, CreateTransactionActivity.class);
            intent.putExtra("Type", TransactionType.Expense);
            startActivity(intent);
        });

        fab2.setOnClickListener(view -> {
            onAddButtonClicked(addFab, views);
            Intent intent = new Intent(this, CreateTransactionActivity.class);
            intent.putExtra("Type", TransactionType.Income);
            startActivity(intent);
        });

        addFab.setOnClickListener(view -> {
            onAddButtonClicked(addFab, views);
        });
    }

    private void onAddButtonClicked(FloatingActionButton addButton, List<View> viewsToAnimate) {
        addButtonIsPressedDown = !addButtonIsPressedDown;
        setFabVisibility(viewsToAnimate);
        setFabAnimation(addButton, viewsToAnimate);

        final View fadeBackground = findViewById(R.id.fadeBackground);
        if (addButtonIsPressedDown) {
            fadeBackground.setVisibility(View.VISIBLE);
            fadeBackground.animate().alpha(0.7f);
        } else {
            fadeBackground.setVisibility(View.GONE);
            fadeBackground.animate().alpha(0);
        }

    }

    private void setFabAnimation(FloatingActionButton addButton, List<View> views) {

        Animation fromBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation);
        Animation toBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation);
        Animation rotateCloseAnimation = AnimationUtils.loadAnimation(this, R.anim.rotatae_close_animation);
        Animation rotateOpenAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_apen_animation);

        if (addButtonIsPressedDown) {
            addButton.startAnimation(rotateOpenAnimation);
            for (int i = 0; i < views.size(); i++) {
                views.get(i).startAnimation(fromBottomAnimation);
            }
        } else {
            addButton.startAnimation(rotateCloseAnimation);
            for (int i = 0; i < views.size(); i++) {
                views.get(i).startAnimation(toBottomAnimation);
            }
        }
    }

    private void setFabVisibility(List<View> Views) {
        if (addButtonIsPressedDown) {
            for (int i = 0; i < Views.size(); i++) {
                Views.get(i).setVisibility(View.VISIBLE);
                Views.get(i).setClickable(true);
            }
        } else {
            for (int i = 0; i < Views.size(); i++) {
                Views.get(i).setVisibility(View.INVISIBLE);
                Views.get(i).setClickable(false);
            }
        }
    }

    private void addListenersToBottomNavigationView() {
        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.history) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, HistoryFragment.class, null).commit();
                return true;
            } else if (itemId == R.id.start) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.containerView, StartFragment.class, null).commit();
                return true;
            } else if (itemId == R.id.regularly) {
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