package com.example.mysqlinsert.ui.billcalculator;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mysqlinsert.DBQuer.DatabaseMethods;
import com.example.mysqlinsert.R;
import com.example.mysqlinsert.pay;
import com.example.mysqlinsert.ui.phistory.SendFragment;

import java.sql.ResultSet;

public class ShareFragment extends Fragment {
    TextView fixCharge;
    TextView mDate;
    TextView nDays;
    TextView mCharge;
    TextView uTotal;
    Button button;
    DatabaseMethods methods = new DatabaseMethods();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate( R.layout.fragment_billcalculator, container, false );



        fixCharge = root.findViewById(R.id.fCharge);
        fixCharge.setText(methods.getfCharge());

        mDate = root.findViewById(R.id.mDate);
        mDate.setText(methods.getpDate());
        nDays = root.findViewById(R.id.nDates);
        nDays.setText(methods.getnDates());
        System.out.println("NDates"+methods.getnDates());
        mCharge = root.findViewById(R.id.mCharge);
        mCharge.setText(methods.getAmount());
        uTotal = root.findViewById(R.id.total);
        uTotal.setText(methods.getTotal().toString());
        button=root.findViewById(R.id.pay);



        button.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent( getActivity(), pay.class );
                startActivity( intent );
            }

        } );


        return root;
    }



}