package com.example.meinegeldapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TransactionManager {

    private static TransactionManager instance;

    private final List<Transaction> transactions;

    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
            // TODO: 20.04.2022 Delete the following line:
            instance.fillListWithDummyData();
        }
        return instance;
    }

    private TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addIncome(Date date, int amount, Group group, String description) {
        transactions.add(new Transaction(date, amount, group, description));
    }

    public void addExpense(Date date, int amount, Group group, String description) {
        transactions.add(new Transaction(date, -amount, group, description));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Transaction> getTransactions() {
        transactions.sort((transaction, t1) -> 0);
        return Collections.unmodifiableList(transactions);
    }

    public void fillListWithDummyData() {
        Group group = new Group("Eine Gruppe");
        addExpense(Calendar.getInstance().getTime(), 10, group, "Beschreibung 1");
        addExpense(Calendar.getInstance().getTime(), 20, group, "Beschreibung 2");
        addExpense(Calendar.getInstance().getTime(), 30, group, "Beschreibung 3");
        addExpense(Calendar.getInstance().getTime(), 30, group, "Beschreibung 4");
        addExpense(Calendar.getInstance().getTime(), 40, group, "Beschreibung 5");
        addIncome(Calendar.getInstance().getTime(), 10, group, "Beschreibung 1");
        addIncome(Calendar.getInstance().getTime(), 20, group, "Beschreibung 2");
        addIncome(Calendar.getInstance().getTime(), 30, group, "Beschreibung 3");
        addIncome(Calendar.getInstance().getTime(), 40, group, "Beschreibung 4");
        addIncome(Calendar.getInstance().getTime(), 40, group, "Beschreibung 5");
    }
}
