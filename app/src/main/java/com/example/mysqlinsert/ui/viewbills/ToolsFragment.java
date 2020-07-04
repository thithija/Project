package com.example.mysqlinsert.ui.viewbills;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.R;
import com.example.mysqlinsert.ui.phistory.SendFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ToolsFragment extends Fragment {


    TextView amount;
    Connection connect=null;
    ListView bill;

    ResultSet rst;
    String s[] ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_viewbills, container, false);

        amount = root.findViewById(R.id.tAmmount);
        amount.setText("50000");

        bill = root.findViewById(R.id.bills);
        ToolsFragment.BilllHistrory h = new ToolsFragment.BilllHistrory();
        h.execute();


        return root;
    }
    public class BilllHistrory extends AsyncTask<Void,Void,Void> {
        String msg = " ";
        StringBuffer buffer = new StringBuffer();
        ArrayList<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);

        @Override
        protected Void doInBackground(Void... voids) {
            DbCon cont = new DbCon();
            Connection cn = cont.connectTODB();




            String bh = "SELECT * FROM payment";
            try {
                Statement stmt1 = cn.createStatement();
                rst = stmt1.executeQuery(bh);



                while (rst.next()){
                    buffer.append("Account: "+ rst.getString("u_acc")+"\n");
                    buffer.append("Amount: "+ rst.getString("amount")+"\n");
                    buffer.append("Date: "+ rst.getString("p_date")+"\n\n");
                }

                s = buffer.toString().split("\n\n");

                if (s!=null){
                    msg = "OK";
                }

                for(int i=0;i<s.length;i++){
                    data.add(s[i]);
                }



            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            bill.setAdapter(adapter);


        }
    }
}