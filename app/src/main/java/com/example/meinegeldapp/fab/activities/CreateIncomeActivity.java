package com.example.meinegeldapp.fab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.meinegeldapp.R;
import com.example.meinegeldapp.model.Group;
import com.example.meinegeldapp.model.GroupManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

public class CreateIncomeActivity extends AppCompatActivity {


    private final Calendar calendar = Calendar.getInstance();
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_income);

        initDatePicker();

        GroupManager gm = GroupManager.getInstance();
        List<Group> groupList = gm.getGroups();
        List<String> groupNames = new ArrayList<>(groupList.size());
        for (Group group : groupList) {
            groupNames.add(group.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CreateIncomeActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, groupNames);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.groupAutoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void initDatePicker() {
        editText = findViewById(R.id.dateTextInputEditText);
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.GERMAN);
        editText.setText(dateFormat.format(calendar.getTime()));

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        editText.setOnClickListener(view -> new DatePickerDialog(CreateIncomeActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.GERMAN);
        editText.setText(dateFormat.format(calendar.getTime()));
    }

}