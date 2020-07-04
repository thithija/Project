package com.example.mysqlinsert.ui.uhistory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysqlinsert.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragmentUser extends Fragment {

    public HistoryFragmentUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uhistory_graph, container, false);
    }
}
