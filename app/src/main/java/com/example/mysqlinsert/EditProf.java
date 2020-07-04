package com.example.mysqlinsert;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysqlinsert.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditProf extends AppCompatActivity {
Button back;
String name,mail,tp,unic,acc;
UserDetails details = new UserDetails();
EditProfile pro = new EditProfile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prof);
        final EditText uname = findViewById( R.id.uname );
        final EditText email = findViewById( R.id.email );
        final EditText tpnum = findViewById( R.id.tpnum );
        final EditText nic   = findViewById( R.id.nic);
        back=findViewById(R.id.backprof);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(EditProf.this, Main2Activity.class);
                startActivity(register);
            }
        });

        Button submit = findViewById( R.id.submitb );
        submit.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                name = uname.getText().toString();
                mail = email.getText().toString();
                tp = tpnum.getText().toString();
                unic = nic.getText().toString();

                if (uname.getText().toString().isEmpty()){
                    uname.setError( "This field cannot be blank!" );
                } else if (email.getText().toString().isEmpty()) {
                    email.setError( "This field cannot be blank!" );
                } else if (tpnum.getText().toString().isEmpty()) {
                    tpnum.setError( "This field cannot be blank!" );
                } else if (nic.getText().toString().isEmpty()) {
                    nic.setError( "This field cannot be blank!" );
                }
                else{
                    pro.execute();
                    details.setuName(name);
                    details.setuEmail(mail);
                    details.setuTp(tp);
                    uname.setText("");
                    email.setText("");
                    tpnum.setText("");
                    nic.setText("");
                }
            }
        } );

        Button reset = findViewById( R.id.resetb );
        reset.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                uname.getText().clear();
                email.getText().clear();
                tpnum.getText().clear();
                nic.getText().clear();
            }
        } );
    }

    public class EditProfile extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            Connection con;
            DbCon conn = new DbCon();
            con = conn.connectTODB();
            ResultSet rs;
            acc = details.getuAcc();

            String select = "UPDATE users SET name='"+name+"',nic_num='"+unic+"',email='"+mail+"',u_tp='"+tp+"' WHERE account_number='"+acc+"'";

            try {
                Statement userd = con.createStatement();
                userd.executeUpdate(select);



            } catch (SQLException ex) {
                System.out.println("error: "+ ex);
            }


            return null;
        }
    }
}