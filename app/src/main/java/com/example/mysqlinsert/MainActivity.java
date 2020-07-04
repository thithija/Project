package com.example.mysqlinsert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysqlinsert.DBQuer.DatabaseMethods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.example.mysqlinsert.Activities.RegisterActivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialog;
    CheckBox remember;
    TextView uname,password,sgn,forgot;
    String name;
    String pswd;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname =  findViewById(R.id.username);
        password =  findViewById(R.id.password);
        sgn = findViewById(R.id.SignUp);
        remember=findViewById(R.id.checkBox);
        forgot=findViewById(R.id.textView2);


        sgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
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
                    Toast.makeText(MainActivity.this,"Checked",Toast.LENGTH_SHORT).show();
                }
                else if (!buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainActivity.this,"UnChecked",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showRecoverPasswordDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText emailEt = new EditText(this);
        emailEt.setHint("Email");
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEt.setMinEms(10);

        linearLayout.addView(emailEt);
        linearLayout.setPadding(50,10,10,10);

        builder.setView(linearLayout);

        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = emailEt.getText().toString().trim();
                beginRecovery(email);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    private void beginRecovery(String email) {

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Email Sent",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed...",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void login(View view) {
        name = uname.getText().toString();
        pswd = password.getText().toString();
        Send objSend = new Send();
        objSend.execute();
    }

    private class Send extends AsyncTask<String,String,String>{
        String msg = " ";
        String title = " ";
       DatabaseMethods method1 = new DatabaseMethods();

        @Override
        protected void onPreExecute() {
            // uname.setText("Please wait inserting Data....!");
        }
        @Override
        protected String doInBackground(String... strings) {
            String fname;
            String pwd;
            Connection conn;
            UserDetails userDetails = new UserDetails();
            DbCon con1 = new DbCon();
            conn = con1.connectTODB();
            try {

                if (conn == null){
                    title = "Error";
                    msg = "Connection goes wrong";
                }
                else{
                    if (name.equals("") || pswd.equals("")){
                        msg = "Please fill all the fields!!!";
                    }else{
                        String select = "SELECT name,password FROM users";
                        Statement selectstmt = conn.createStatement();
                        ResultSet rst = selectstmt.executeQuery(select);


                        while(rst.next()){
                            fname = rst.getString("name");
                            pwd = rst.getString("password");

                            if(name.equals(fname)){
                                if (pswd.equals(pwd)){
                                    userDetails.setuName(name);
                                    userDetails.setuPassword(pswd);
                                    userDetails.details();
                                    method1.setInterruptionDates();
                                    Intent log = new Intent(MainActivity.this,Main2Activity.class);
                                    startActivity(log);
                                    finish();
                                    // break;
                                }
                                else{
                                    title = "Error";
                                    msg="Invalid Password!!!";
                                }
                            }
                            else{
                                title = "Error";
                                msg="Invalid User Name!!!";
                            }
                        }
                    }

                }

//check login admin

                if (conn == null){
                    msg = "Connection goes wrong";
                }
                else{
                    if (name.equals("") || pswd.equals("")){
                        msg = "Please fill all the fields!!!";
                    }else {
                        String select = "SELECT ad_name,ad_pwd FROM admin";
                        Statement selectstmt = conn.createStatement();
                        ResultSet rst = selectstmt.executeQuery(select);




                        while(rst.next()){
                            fname = rst.getString("ad_name");
                            pwd = rst.getString("ad_pwd");

                            if(name.equals(fname)){
                                if (pswd.equals(pwd)){
                                    Intent log = new Intent(MainActivity.this,Main3Activity.class);
                                    startActivity(log);
                                    // break;
                                }
                                else{
                                    title = "Error";
                                    msg="Invalid Password!!!";
                                }
                            }
                            else{
                                title = "Error";
                                msg="Invalid User Name!!!";
                            }
                        }
                    }


                }
                conn.close();
            } catch (Exception e) {
                title = "Error";
                msg = "Connection goes wrong";
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {

                    new SweetAlertDialog(MainActivity.this)
                            .setTitleText(msg)
                            .show();

        }

    }
}
