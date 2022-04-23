package com.example.meinegeldapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.meinegeldapp.HistoryAdapter;
import com.example.meinegeldapp.R;
import com.example.meinegeldapp.model.Transaction;
import com.example.meinegeldapp.model.TransactionType;
import com.google.android.material.chip.Chip;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Chip chip1;
    private Chip chip2;
    private Chip chip3;
    private Chip chip4;
    private Chip chip5;

    private TransactionType filterMode = TransactionType.All;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initChips();
    }

    private void initChips() {
        chip1 = getActivity().findViewById(R.id.chip1);
        chip2 = getActivity().findViewById(R.id.chip2);
        chip3 = getActivity().findViewById(R.id.chip3);
        chip4 = getActivity().findViewById(R.id.chip4);
        chip5 = getActivity().findViewById(R.id.chip5);

        Map<Chip, TransactionType> filterTypes = new HashMap<>();

        filterTypes.put(chip1, TransactionType.All);
        filterTypes.put(chip2, TransactionType.Expense);
        filterTypes.put(chip3, TransactionType.Income);
        filterTypes.put(chip4, TransactionType.RegularExpense);
        filterTypes.put(chip5, TransactionType.RegularIncome);

        for (Map.Entry<Chip, TransactionType> entry : filterTypes.entrySet()) {

            Chip key = entry.getKey();
            TransactionType value = entry.getValue();

            key.setOnCheckedChangeListener((compoundButton, checked) -> {

                if (checked) {
                    key.setChecked(true);
                    for (Map.Entry<Chip, TransactionType> entry1 : filterTypes.entrySet()) {
                        if (entry1.getKey().equals(key)) {
                            continue;
                        }
                        entry1.getKey().setChecked(false);
                    }
                    changeFilterMode(value);
                } else {

                    boolean someThingIsChecked = false;
                    for (Map.Entry<Chip, TransactionType> entry1 : filterTypes.entrySet()) {
                        if (entry1.getKey().isChecked()) {
                            someThingIsChecked = true;
                        }
                    }
                    if (!someThingIsChecked) {
                        chip1.setChecked(true);
                        changeFilterMode(TransactionType.All);
                    }
                }

            });

        }
    }

    private void changeFilterMode(TransactionType type) {

        HistoryAdapter.getInstance().filter(type);

    }


    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.fragment_history_RecyclerView);

        HistoryAdapter historyAdapter = HistoryAdapter.getInstance();
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

}