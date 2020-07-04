package com.example.mysqlinsert;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysqlinsert.Recycle.MyAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComplainHistoryFragment extends Fragment {
    RecyclerView recyclerView;
    MyAdapter adapter;
    public ComplainHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_complain_history, container, false);
        recyclerView = v.findViewById(R.id.recycleView);

        adapter = new MyAdapter(getContext());
        recyclerView.setAdapter(adapter);


        return v;


    }
}
