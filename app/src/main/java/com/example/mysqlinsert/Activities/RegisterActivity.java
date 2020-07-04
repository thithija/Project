package com.example.mysqlinsert.Activities;



import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysqlinsert.DBQuer.DatabaseMethods;
import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.MainActivity;
import com.example.mysqlinsert.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity {
    EditText unm,uacc,unic,utp,uemail,upwd1,upwd2;
    TextView login;
    Button register;
    String name,uAcc,uNic,uTp,uEmail,uPwd1,uPwd2;
    String dbAcc;
    String msg,title;
    DatabaseMethods methods = new DatabaseMethods();
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        unm = findViewById(R.id.uName);
        uacc = findViewById(R.id.uAcc);
        unic = findViewById(R.id.uNic);
        utp = findViewById(R.id.uTp);
        uemail = findViewById(R.id.uEmail);
        upwd1 = findViewById(R.id.uPwd1);
        upwd2 = findViewById(R.id.uPwd2);
        register = findViewById(R.id.registerButton);
        login = findViewById(R.id.signin);
        remember=findViewById(R.id.checkBox);


        //User Register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = unm.getText().toString();
                uAcc = uacc.getText().toString();
                uNic = unic.getText().toString();
                uTp = utp.getText().toString();
                uEmail = uemail.getText().toString();
                uPwd1 = upwd1.getText().toString();
                uPwd2 = upwd2.getText().toString();

                Insert insert = new Insert();
                insert.execute();
            }
        });

        //back to login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loin = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(loin);
            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(RegisterActivity.this,"Accept",Toast.LENGTH_SHORT).show();
                }
                else if (!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(RegisterActivity.this,"Decline",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class Insert extends AsyncTask<String,String,String>{
        Context mContext;
        @Override
        protected String doInBackground(String... strings) {
            Connection con;
            DbCon conn = new DbCon();
            con = conn.connectTODB();
            ResultSet rs;


            String select = "SELECT * FROM users";


            try {
                Statement userd = con.createStatement();
                rs = userd.executeQuery(select);

                if(uPwd1.equals(uPwd2)) {

                    while (rs.next()) {
                        dbAcc = rs.getString("account_number");

                        if (uAcc.equals(dbAcc)) {
                            title="Error";
                            msg = "Account is already exists";
                        } else {
                            title="Register Successfully!";
                            msg="Well come to CEB";
                            methods.UsRegister(name, uAcc, uNic, uEmail, uTp, uPwd1);
                            Intent login = new Intent(RegisterActivity.this, MainActivity.class);
                          startActivity(login);
                          finish();
                        }
                    }

                }
                else{
                    title="Error";
                    msg="Passwords are not matching";
                }


            } catch (SQLException e) {
                System.out.println("error: "+e);
            }

            return msg;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog
                                    .setTitleText(title)
                                    .setContentText(msg)
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.WARNING_TYPE);
                        }
                    }).show();
            Intent login = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(login);
        }
    }
}
