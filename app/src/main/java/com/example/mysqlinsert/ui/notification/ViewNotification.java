package com.example.mysqlinsert.ui.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mysqlinsert.Activities.AdminUi.AdInterruption;
import com.example.mysqlinsert.Main2Activity;
import com.example.mysqlinsert.Main3Activity;
import com.example.mysqlinsert.R;

public class ViewNotification extends AppCompatActivity {
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);

        back=findViewById(R.id.backnoti);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(ViewNotification.this, Main2Activity.class);
                startActivity(register);
            }
        });
    }
}
