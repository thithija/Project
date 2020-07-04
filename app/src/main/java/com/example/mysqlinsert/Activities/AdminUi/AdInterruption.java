package com.example.mysqlinsert.Activities.AdminUi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.Main3Activity;
import com.example.mysqlinsert.R;

import java.sql.Connection;
import java.sql.ResultSet;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdInterruption extends AppCompatActivity {
EditText dt,desc,st,et;
Button intadd,back;
CalendarView setCalendar;
String sDate,sDesc,StartTime,EndTime;

//object from admin method
    AdminMethods intrtn = new AdminMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_interruption);

        dt = findViewById(R.id.intdt);
        desc = findViewById(R.id.intdesc);
        st = findViewById(R.id.to);
        et = findViewById(R.id.from);
        intadd = findViewById(R.id.adint);
        back=findViewById(R.id.backd);
        setCalendar = findViewById(R.id.calendarView);



        //show selected date
        setCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                int nmonth = month+1;
                dt.setText(String.valueOf(year)+"-"+String.valueOf(nmonth)+"-"+String.valueOf(date));
            }
        });

        //adInterruption
        intadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sDate = dt.getText().toString();
                sDesc = desc.getText().toString();
                StartTime = st.getText().toString();
                EndTime = et.getText().toString();

                InterruptionAd int1  = new InterruptionAd();
                int1.execute();

            }
        });

        //back to home page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(AdInterruption.this, Main3Activity.class);
                startActivity(register);
            }
        });
    }




    public class InterruptionAd extends AsyncTask<String,String,String>{

        String msg = "";
        String title = "";
        @Override
        protected String doInBackground(String... strings) {
            try {
                if (dt.equals(null) || desc.equals(null)){
                    title = "Error";
                    msg = "Please fill all the fields";
                }
                else{
                    title = "Success!!!";
                    msg = "Update successfully!!!";
                    intrtn.adInterruption(sDate,sDesc,StartTime,EndTime);
                }

            }catch (Exception e){
                System.out.println(e);
            }



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new SweetAlertDialog(AdInterruption.this)
                    .setTitleText(msg)
                    .show();

            dt.setText("");
            desc.setText("");
            st.setText("");
            et.setText("");
        }
    }
}
