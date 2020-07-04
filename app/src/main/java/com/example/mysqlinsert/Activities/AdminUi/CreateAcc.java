package com.example.mysqlinsert.Activities.AdminUi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mysqlinsert.Activities.RegisterActivity;
import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.Main3Activity;
import com.example.mysqlinsert.MainActivity;
import com.example.mysqlinsert.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CreateAcc extends AppCompatActivity {
EditText adNm,adMail,adNic,adPwd,adPwd2;
Button submit,back;
String sName,sMail,sNic,sPwd,sPwd2;

//create object from adminMethods
AdminMethods acc = new AdminMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        adNm = findViewById(R.id.aName);
        adMail = findViewById(R.id.aEmail);
        adNic = findViewById(R.id.aNic);
        adPwd = findViewById(R.id.apwd);
        adPwd2 = findViewById(R.id.apwd2);
        submit = findViewById(R.id.createAcc);
        back=findViewById(R.id.backn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sName = adNm.getText().toString();
                sMail = adMail.getText().toString();
                sNic = adNic.getText().toString();
                sPwd = adPwd.getText().toString();
                sPwd2 = adPwd2.getText().toString();

                AdminCreate adAcc = new AdminCreate();
                adAcc.execute();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(CreateAcc.this, Main3Activity.class);
                startActivity(register);
            }
        });

    }

    public class AdminCreate extends AsyncTask<String,String,String>{
        String msg="";
        String title = "";
        String dbname,dbnic;

        @Override
        protected String doInBackground(String... strings) {
            Connection con;
            DbCon conn = new DbCon();
            con = conn.connectTODB();
            ResultSet rs;


            String select = "SELECT * FROM admin";


            try {
                Statement userd = con.createStatement();
                rs = userd.executeQuery(select);
                if (adNm.equals(null) || adMail.equals(null) || adNic.equals(null) || adPwd.equals(null) || adPwd2.equals(null)){

                }else {
                    if(sPwd.equals(sPwd2)) {

                        while (rs.next()) {
                            dbname = rs.getString("ad_name");
                            dbnic = rs.getString("ad_nic");

                            if (dbname.equals(sName) || dbnic.equals(sNic)) {
                                title="Error";
                                msg = "Account is already exists";
                            } else {
                                title="Register Successfully!";
                                msg="Well come to CEB";
                               acc.createAminAcc(sName,sPwd,sMail,sNic);
                            }
                        }
                    }
                    else{
                        title="Error";
                        msg="Passwords are not matching";
                    }
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("error: "+e);
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new SweetAlertDialog(CreateAcc.this)
                    .setTitleText(msg)
                    .show();

            adNm.setText("");
            adMail.setText("");
            adNic.setText("");
            adPwd.setText("");
            adPwd2.setText("");
        }
    }
}
