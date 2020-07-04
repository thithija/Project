package com.example.mysqlinsert.Activities.AdminUi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mysqlinsert.Main3Activity;
import com.example.mysqlinsert.MainActivity;
import com.example.mysqlinsert.R;
import com.google.android.gms.ads.AdView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Advertiecement extends AppCompatActivity {
    EditText description,endDate,adverUrl;
    Button adAdvertisement,back;
    String sDesc,sDate,sUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertiecement);

        description = findViewById(R.id.adDesc);
        endDate = findViewById(R.id.adDate);
        adverUrl = findViewById(R.id.adLink);
        adAdvertisement = findViewById(R.id.enterADD);
        back=findViewById(R.id.backad);


        adAdvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sDesc = description.getText().toString();
                sDate = endDate.getText().toString();
                sUrl = adverUrl.getText().toString();

                EnterAdvertisement enter = new EnterAdvertisement();
                enter.execute();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Advertiecement.this, Main3Activity.class);
                startActivity(register);
            }
        });

    }

    public class EnterAdvertisement extends AsyncTask<String,String,String>{
        AdminMethods method = new AdminMethods();
        String msg;
        @Override
        protected String doInBackground(String... strings) {

            if (sDesc.equals("") || sDate.equals("") || sUrl.equals("")){
                msg = "Please fill all the fields!!!";
            }
            else{
                msg = "Add Successfully!!!";
                method.addAdvertisement(sDesc,sDate,sUrl);
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new SweetAlertDialog(Advertiecement.this)
                    .setTitleText(msg)
                    .show();

            description.setText("");
            endDate.setText("");
            adverUrl.setText("");
        }
    }
}
