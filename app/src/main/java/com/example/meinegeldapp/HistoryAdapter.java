package com.example.meinegeldapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meinegeldapp.model.Transaction;
import com.example.meinegeldapp.model.TransactionManager;
import com.example.meinegeldapp.model.TransactionType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private static HistoryAdapter instance;

    private final List<Transaction> transactions = new ArrayList<>();

    public static HistoryAdapter getInstance() {
        if (instance == null) {
            instance = new HistoryAdapter();
            instance.transactions.addAll(TransactionManager.getInstance().getTransactions());
        }
        return instance;
    }

    private HistoryAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_transaction, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        String myFormat = "dd/MM/yyyy  hh:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.GERMAN);

        ((TextView) holder.itemView.findViewById(R.id.fragment_transaction_amount)).setText(transaction.getAmount() + "€");
        ((TextView) holder.itemView.findViewById(R.id.fragment_transaction_description)).setText(transaction.getDescription());
        ((TextView) holder.itemView.findViewById(R.id.fragment_transaction_date)).setText(dateFormat.format(transaction.getDate()));
        ((TextView) holder.itemView.findViewById(R.id.fragment_transaction_group)).setText(transaction.getGroup().getName());

    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void filter(TransactionType transactionType) {

        transactions.clear();
        List<Transaction> allTransactions = TransactionManager.getInstance().getTransactions();

        if (transactionType.equals(TransactionType.All)) {
            transactions.addAll(allTransactions);
        } else {
            for (Transaction t : allTransactions) {
                if (t.getTransactionType().equals(transactionType) && !transactions.contains(t)) {
                    transactions.add(t);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(0, transaction);
        notifyItemInserted(0);
    }
}
