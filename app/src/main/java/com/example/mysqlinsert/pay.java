package com.example.mysqlinsert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mysqlinsert.Activities.AdminUi.AdInterruption;
import com.example.mysqlinsert.ui.billcalculator.ShareFragment;

public class pay extends AppCompatActivity {
Button back,next;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pay);

        back=findViewById(R.id.backpay);
        next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(pay.this, Main2Activity.class);
                startActivity(register);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(pay.this, pgatway.class);
                startActivity(register);
            }
        });
    }
}
