package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.finalseizureyarab.Controller.SeizureSymptoms;
import com.example.finalseizureyarab.Controller.VolleySingleton;
import com.example.finalseizureyarab.MainActivity;
import com.example.finalseizureyarab.Models.User;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Symptoms extends AppCompatActivity {
    Button back, save;
    EditText et_1,et_2,et_3,et_4,et_5,et_6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);
        et_4 = findViewById(R.id.et_4);
        et_5 = findViewById(R.id.et_5);
        et_6 = findViewById(R.id.et_6);


        back = findViewById(R.id.btn_back);
        save = findViewById(R.id.btn_save);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Symptoms.this, MainActivity.class));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String MyEt_1 = et_1.getText().toString().trim().toLowerCase(Locale.ROOT);
                final String MyEt_2 = et_2.getText().toString().trim().toLowerCase(Locale.ROOT);
                final String MyEt_3 = et_3.getText().toString().trim().toLowerCase(Locale.ROOT);
                final String MyEt_4 = et_4.getText().toString().trim().toLowerCase(Locale.ROOT);
                final String MyEt_5 = et_5.getText().toString().trim().toLowerCase(Locale.ROOT);
                final String MyEt_6 = et_6.getText().toString().trim().toLowerCase(Locale.ROOT);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URLs.URL_symptomStore, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONObject userJson = obj.getJSONObject("data");

                            User user = new User(userJson.getString("token"));
                            SeizureSymptoms.getInstance(getApplicationContext()).UserSaveSymptoms(user);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("Content-Type", "application/json");
                        params.put("et_1",MyEt_1 );
                        params.put("et_2", MyEt_2);
                        params.put("et_3", MyEt_3);
                        params.put("et_4", MyEt_4);
                        params.put("et_5", MyEt_5);
                        params.put("et_6", MyEt_6);


                        return params;

                    }
                };

                VolleySingleton.getInstance(Symptoms.this).addToRequestQueue(stringRequest);

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
        switch (id){
            case R.id.profile:
                Intent intent1 =new Intent(Symptoms.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(Symptoms.this , SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(Symptoms.this , SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(Symptoms.this ,Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 =new Intent(Symptoms.this , Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(Symptoms.this ,Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 =new Intent(Symptoms.this , Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 =new Intent(Symptoms.this , Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(Symptoms.this , Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}