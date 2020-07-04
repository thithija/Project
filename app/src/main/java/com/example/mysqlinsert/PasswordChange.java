package com.example.mysqlinsert;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysqlinsert.DBQuer.DatabaseMethods;
import com.example.mysqlinsert.DbCon;
import com.example.mysqlinsert.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.ProgressDialog.show;

public class PasswordChange extends AppCompatActivity {
    Button back;
    DatabaseMethods methods = new DatabaseMethods();
    String dbAcc, msg,title;
    String newpassword,uacc;

    UserDetails details = new UserDetails();
    Insert insert = new Insert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        back=findViewById(R.id.backpwd);
        final EditText opwd= findViewById( R.id.opwd );
        final EditText newpswd= findViewById( R.id.neww );
        final EditText repswd= findViewById( R.id.renew );

        uacc = details.getuAcc();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Register = new Intent(PasswordChange.this,MainActivity.class);
                startActivity(Register);
            }
        });
        Button reset = findViewById( R.id.resetbb );
        reset.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                newpswd.getText().clear();
                repswd.getText().clear();
                opwd.getText().clear();
            }
        } );

        Button submit = findViewById( R.id.submitbb );
        submit.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                 newpassword = newpswd.getText().toString();
                String repassword = repswd.getText().toString();
                String oldpassword = opwd.getText().toString();

                if (opwd.getText().toString().isEmpty()) {
                    opwd.setError("This field cannot be blank!");
                }
                else if (newpswd.getText().toString().isEmpty()) {
                    newpswd.setError("This field cannot be blank!");
                }
                else if (repswd.getText().toString().isEmpty()) {
                    repswd.setError("This field cannot be blank!");
                }

                else if (newpswd.getText().toString().length() < 8) {
                    newpswd.setError( "Your 'New Password' is less than 8 characters!" );
                }
                else if (repswd.getText().toString().length() < 8) {
                    repswd.setError( "Your 'Confirm Password' is less than 8 characters!" );
                }
                //"minimum eight characters, maximum 20 characters at least one letter,one number and one special character" strong password
                else if(!newpswd.getText().toString().matches( "^.*(?=.{8,20})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=*]).*$")) {
                    newpswd.setError( "Your 'New Password' should contain at least one alphabetic,numeric and special character!" );
                }
                else if (!repswd.getText().toString().matches( "^.*(?=.{8,20})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=*]).*$")) {
                    repswd.setError( "Your 'Confirm Password' should contain at least one alphabetic,numeric and special character!" );
                }
                else  if  (!newpswd.getText().toString().equals(repswd.getText().toString())) {
                    newpswd.setError("Your 'New password' and 'Confirm password' do not match!");
                    repswd.setError("Your 'New password' and 'Confirm password' do not match!");
                }
                else {


                    insert.execute();
                    details.setuPassword(newpassword);
                    opwd.setText("");
                    newpswd.setText("");
                    repswd.setText("");
                }

            }
        } );

    }
    public class Insert extends AsyncTask<String,String,String> {
        Context mContext;
        @Override
        protected String doInBackground(String... strings) {
            Connection con;
            DbCon conn = new DbCon();
            con = conn.connectTODB();
            ResultSet rs;

            String select = "UPDATE users SET password='"+newpassword +"' WHERE account_number='"+uacc+"'";

            try {
                Statement userd = con.createStatement();
                userd.executeUpdate(select);



            } catch (SQLException ex) {
                System.out.println("error: "+ ex);
            }
            return msg;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            new SweetAlertDialog( PasswordChange.this, SweetAlertDialog.WARNING_TYPE)
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
        }
    }
}
