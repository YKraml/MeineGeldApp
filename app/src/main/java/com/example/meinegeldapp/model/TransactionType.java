package com.example.meinegeldapp.model;

public enum TransactionType {

    All(""),
    Expense("Ausgabe"),
    Income("Einnahme"),
    RegularExpense("Regelmäßige Ausgabe"),
    RegularIncome("Regelmäßige Einnahme");

    private final String title;

    TransactionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
