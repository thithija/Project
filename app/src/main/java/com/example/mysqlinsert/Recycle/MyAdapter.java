package com.example.mysqlinsert.Recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.R;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Connection conn;
    Context context;
    DbCon con1 = new DbCon();
    ResultSet rst;
    Array set[];


    public MyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        conn = con1.connectTODB();



        try {
            Statement selectstmt = null;
            String select = "SELECT name,password FROM complain";
            selectstmt = conn.createStatement();
            rst = selectstmt.executeQuery(select);

            while (rst.next()){
                holder.name.setText(rst.getString("u_name"));
                holder.desc.setText(rst.getString("u_complain"));
                holder.number.setText(rst.getString("u_tp"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,number,desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.cName);
            number = (TextView) itemView.findViewById(R.id.uacc);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }
}
