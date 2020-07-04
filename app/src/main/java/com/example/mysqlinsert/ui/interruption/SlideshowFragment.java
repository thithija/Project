package com.example.mysqlinsert.ui.interruption;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.mysqlinsert.DBQuer.DatabaseMethods;
import com.example.mysqlinsert.R;

import java.sql.ResultSet;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.vo.DateData;

public class SlideshowFragment extends Fragment {
    TextView testd;
    MCalendarView calView;
    String setDates;
    int cyear,cmonth;
    String getYear,setYear;
   Calendar calendar = Calendar.getInstance();
    DatabaseMethods mt1 = new DatabaseMethods();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_interruption, container, false);

        cyear = calendar.get(Calendar.YEAR);
        cmonth = calendar.get(Calendar.MONTH)+1;
        testd = root.findViewById(R.id.testDate);
       calView  = ((MCalendarView) root.findViewById(R.id.intCal));


       setInt();

        calView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                ShowMessage msg = new ShowMessage();
                getYear=String.valueOf(cyear)+"-"+String.valueOf(cmonth)+"-"+String.valueOf(date.getDay());
                msg.execute();
            }
        });

        return root;
    }

    //set Interruption Dates
    public void setInt(){

        setDates  = mt1.getCollectDate();
        if (setDates == null){
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }
        DateData d = new DateData(2020,04,Integer.parseInt(setDates));
        calView.markDate(d);

    }

public class ShowMessage extends AsyncTask<String,String,String>{
    String msg=null;

    @Override
    protected String doInBackground(String... strings) {

        setYear = mt1.getInterMessage(getYear);
        if(setYear == null){
            testd.setText("nothing interruption!!");
        }
        else{
            testd.setText(setYear);
        }

        return msg;
    }
}
}