package com.example.meinegeldapp.model;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Transaction implements Comparable<Transaction>, Serializable {

    private String id;
    private Date date;
    private double amount;
    private Group group;
    private String description;
    TransactionType transactionType;

    public Transaction(Date date, double amount, Group group, String description, TransactionType transactionType) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.amount = amount;
        this.group = group;
        this.description = description;
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", group=" + group +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Group getGroup() {
        return group;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public int compareTo(Transaction transaction) {
        return date.compareTo(transaction.date);
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
