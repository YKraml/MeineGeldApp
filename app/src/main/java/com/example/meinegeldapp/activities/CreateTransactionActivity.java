package com.example.meinegeldapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meinegeldapp.HistoryAdapter;
import com.example.meinegeldapp.R;
import com.example.meinegeldapp.model.Group;
import com.example.meinegeldapp.model.GroupManager;
import com.example.meinegeldapp.model.Transaction;
import com.example.meinegeldapp.model.TransactionManager;
import com.example.meinegeldapp.model.TransactionType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateTransactionActivity extends AppCompatActivity {


    private final Calendar calendar = Calendar.getInstance();
    private EditText editText;

    private EditText amountEditText;
    private EditText groupEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;

    private FloatingActionButton saveFab;

    private TextView titleTextView;

    private TransactionType transactionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        amountEditText = findViewById(R.id.amountTextInputEditText);
        groupEditText = findViewById(R.id.groupAutoCompleteTextView);
        descriptionEditText = findViewById(R.id.descriptionTextInputEditText);
        dateEditText = findViewById(R.id.dateTextInputEditText);

        saveFab = findViewById(R.id.save_fab);
        saveFab.setOnClickListener(this::saveTransaction);


        transactionType = (TransactionType) getIntent().getExtras().get("Type");
        titleTextView = findViewById(R.id.textViewToolbar);
        titleTextView.setText(transactionType.getTitle() + " erstellen");

        initDatePicker();
        initGroups();
    }


    private void initGroups() {
        GroupManager gm = GroupManager.getInstance();
        List<Group> groupList = gm.getGroups();
        List<String> groupNames = new ArrayList<>(groupList.size());
        for (Group group : groupList) {
            groupNames.add(group.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CreateTransactionActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, groupNames);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.groupAutoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void initDatePicker() {
        editText = findViewById(R.id.dateTextInputEditText);
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.dateFormat), Locale.GERMAN);
        editText.setText(dateFormat.format(calendar.getTime()));

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        editText.setOnClickListener(view -> new DatePickerDialog(CreateTransactionActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.GERMAN);
        editText.setText(dateFormat.format(calendar.getTime()));
    }

    private void saveTransaction(View view) {

        String amount = amountEditText.getText().toString();
        String group = groupEditText.getText().toString().isEmpty() ? "Keine Gruppe" : groupEditText.getText().toString();
        String description = descriptionEditText.getText().toString().isEmpty() ? "Keine Beschreibung" : descriptionEditText.getText().toString();
        String date = dateEditText.getText().toString();


        double parsedAmount = 0;
        Date parsedDate = new Date();
        Group parsedGroup = GroupManager.getInstance().getGroupByName(group);
        try {
            parsedAmount = Double.parseDouble(amount);
            parsedDate = new SimpleDateFormat(getString(R.string.dateFormat), Locale.GERMAN).parse(date);
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
        }

        Transaction transaction = new Transaction(parsedDate, transactionType.equals(TransactionType.Expense) ? -parsedAmount : parsedAmount, parsedGroup, description, transactionType);
        TransactionManager.getInstance().addTransaction(transaction);
        HistoryAdapter.getInstance().addTransaction(transaction);

        finish();
    }
}