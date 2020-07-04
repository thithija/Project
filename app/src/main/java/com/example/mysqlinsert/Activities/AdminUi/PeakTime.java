package com.example.mysqlinsert.Activities.AdminUi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.mysqlinsert.Main3Activity;
import com.example.mysqlinsert.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class PeakTime extends AppCompatActivity {

    String startTime,EndTime,Pprice;
    EditText sTime,eTime,pPrice;
    Button pAdd,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peak_time);

        sTime = findViewById(R.id.startT);
        eTime = findViewById(R.id.endT);
        pPrice = findViewById(R.id.pPrices);
        pAdd = findViewById(R.id.addP);
        back=findViewById(R.id.backp);

        pAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = sTime.getText().toString();
                EndTime = eTime.getText().toString();
                Pprice = pPrice.getText().toString();

                AddPeakTime add = new AddPeakTime();
                add.execute();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(PeakTime.this, Main3Activity.class);
                startActivity(register);
            }
        });


    }

    public class AddPeakTime extends AsyncTask<String,String,String>{
        String msg;
        @Override
        protected String doInBackground(String... strings) {
            AdminMethods addpeak = new AdminMethods();

            if (startTime.equals("") || EndTime.equals("") || Pprice.equals("")){
                msg = "Peace fill all the fields!!!";
            }
            else{
                msg = "Update successfully!!!";
                addpeak.addPeakTime(startTime,EndTime,Pprice);
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new SweetAlertDialog(PeakTime.this)
                    .setTitleText(msg)
                    .show();

            sTime.setText("");
            eTime.setText("");
            pPrice.setText("");
        }
    }
}
