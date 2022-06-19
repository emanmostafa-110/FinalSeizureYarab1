package com.example.finalseizureyarab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.finalseizureyarab.Controller.SessionManager;
import com.example.finalseizureyarab.UI.Alarm;
import com.example.finalseizureyarab.UI.Connection;
import com.example.finalseizureyarab.UI.Diet;
import com.example.finalseizureyarab.UI.Login;
import com.example.finalseizureyarab.UI.MyProfile;
import com.example.finalseizureyarab.UI.Questions;
import com.example.finalseizureyarab.UI.RecordEMG;
import com.example.finalseizureyarab.UI.SeizureHistory;
import com.example.finalseizureyarab.UI.SeizureMR;
import com.example.finalseizureyarab.UI.Symptoms;
import com.example.finalseizureyarab.UI.UploadEeg;

public class MainActivity extends AppCompatActivity {

    Button uploadMri;
    Button uploadEeg;
    Button recordEmg;
    RequestQueue queue;
    String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordEmg = findViewById(R.id.recordEmg);
        uploadEeg = findViewById(R.id.uploadEeg);
        uploadMri = findViewById(R.id.uploadMri);

        queue = Volley.newRequestQueue(this);

        if(SessionManager.getInstance(this).isLoggedIn()){
            if(SessionManager.getInstance(this).getToken() != null){
                token = SessionManager.getInstance(this).getToken().getToken();
            }
        }else {
            finish();
            startActivity(new Intent(this , Login.class));
            return;
        }

        recordEmg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordEMG.class);
                startActivity(intent);
            }
        });


        uploadEeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UploadEeg.class);
                startActivity(intent);
            }
        });
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
        switch (id) {
            case R.id.profile:
                Intent intent1 = new Intent(MainActivity.this, MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 = new Intent(MainActivity.this, SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 = new Intent(MainActivity.this, SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 = new Intent(MainActivity.this, Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 = new Intent(MainActivity.this, Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 = new Intent(MainActivity.this, Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 = new Intent(MainActivity.this, Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 = new Intent(MainActivity.this, Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                SessionManager.getInstance(this).userLogout();
                finish();
                Intent intent9 = new Intent(MainActivity.this, Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}