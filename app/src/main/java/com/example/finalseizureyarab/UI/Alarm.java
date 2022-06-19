package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalseizureyarab.R;

public class Alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menue, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.profile:
                Intent intent1 =new Intent(Alarm.this ,MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(Alarm.this ,SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(Alarm.this ,SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(Alarm.this ,Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 =new Intent(Alarm.this ,Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(Alarm.this ,Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 =new Intent(Alarm.this ,Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 =new Intent(Alarm.this ,Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(Alarm.this ,Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}