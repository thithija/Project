package com.example.mysqlinsert.Activities.AdminUi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mysqlinsert.Main3Activity;
import com.example.mysqlinsert.R;

public class ViewComplain extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complain);
        back=findViewById(R.id.backe);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(ViewComplain.this, Main3Activity.class);
                startActivity(register);
            }
        });

    }
}
