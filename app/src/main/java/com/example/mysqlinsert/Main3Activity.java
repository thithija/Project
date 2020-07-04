package com.example.mysqlinsert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mysqlinsert.Activities.AdminUi.AdInterruption;
import com.example.mysqlinsert.Activities.AdminUi.Advertiecement;
import com.example.mysqlinsert.Activities.AdminUi.CreateAcc;
import com.example.mysqlinsert.Activities.AdminUi.Notification;
import com.example.mysqlinsert.Activities.AdminUi.PeakTime;
import com.example.mysqlinsert.Activities.AdminUi.ViewComplain;
import com.google.android.material.navigation.NavigationView;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        drawerLayout = findViewById(R.id.ad_drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       NavigationView navigationView = findViewById(R.id.navi_view);
       navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.ad_int){
            Intent interrupt = new Intent(Main3Activity.this, AdInterruption.class);
           startActivity(interrupt);
            Toast.makeText(this,"This is Add interruption",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.ad_adver){
            Intent interrupt = new Intent(Main3Activity.this, Advertiecement.class);
            startActivity(interrupt);
            Toast.makeText(this,"This is for advertisement",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.p_time){
            Intent interrupt = new Intent(Main3Activity.this, PeakTime.class);
            startActivity(interrupt);
            Toast.makeText(this,"This is for add peak time ",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.aAcc){
            Intent interrupt = new Intent(Main3Activity.this, CreateAcc.class);
            startActivity(interrupt);
            Toast.makeText(this,"This is Create Admin Account ",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.noti){
            Intent interrupt = new Intent(Main3Activity.this, Notification.class);
            startActivity(interrupt);
            Toast.makeText(this,"This is for notification ",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.com){
            Intent interrupt = new Intent(Main3Activity.this, ViewComplain.class);
            startActivity(interrupt);
            Toast.makeText(this,"This is for view complain ",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.logout){
            Intent interrupt = new Intent(Main3Activity.this, MainActivity.class);
            startActivity(interrupt);
            finish();
            Toast.makeText(this,"Please Sign in",Toast.LENGTH_SHORT).show();

        }

        return false;
    }
}
