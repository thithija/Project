package com.example.mysqlinsert;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mysqlinsert.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class NewAcc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_acc);
        final EditText accname = findViewById( R.id.accname );
        final EditText acc = findViewById( R.id.acc );

        Button submit = findViewById( R.id.submitb );
        submit.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                if (accname.getText().toString().isEmpty()) {
                    accname.setError( "This field cannot be blank!" );
                }
                else if (acc.getText().toString().isEmpty()){
                    acc.setError("This field cannot be blank!");
                }

            }
        } );

        Button reset = findViewById( R.id.resetb );
        reset.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                accname.getText().clear();
                acc.getText().clear();
            }
        } );

    }

}
