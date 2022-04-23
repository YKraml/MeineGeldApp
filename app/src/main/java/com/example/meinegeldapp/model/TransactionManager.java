package com.example.meinegeldapp.model;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TransactionManager implements Serializable {

    private static TransactionManager instance;

    transient private Context context;

    private final List<Transaction> transactions;

    public static void createInstance(Context context) {
        if (instance == null) {
            instance = load(context);
            instance.context = context;
        }
    }

    public static TransactionManager getInstance() {
        return instance;
    }

    private TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        save(context);
    }

    private static TransactionManager load(Context context) {

        try {
            FileInputStream fi = context.openFileInput("TransactionManager.txt");
            ObjectInputStream oi = new ObjectInputStream(fi);
            TransactionManager transactionManager = (TransactionManager) oi.readObject();
            oi.close();
            fi.close();
            return transactionManager;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new TransactionManager();
    }

    private void save(Context context) {

        try {

            File file = new File(context.getFilesDir(), "TransactionManager.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream f = context.openFileOutput("TransactionManager.txt", Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(this);
            o.close();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public Transaction getTransactionById(String id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

}
