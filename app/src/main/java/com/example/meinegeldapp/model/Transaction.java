package com.example.meinegeldapp.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Transaction {

    private String id;
    private Date date;
    private int amount;
    private Group group;
    private String description;

    public Transaction(Date date, int amount, Group group, String description) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.amount = amount;
        this.group = group;
        this.description = description;
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
}
