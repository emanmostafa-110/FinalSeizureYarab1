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
import com.example.finalseizureyarab.Controller.SignalsData;
import com.example.finalseizureyarab.Controller.VolleySingleton;
import com.example.finalseizureyarab.Models.Signals;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class EEGResult extends AppCompatActivity {
    EditText classificationEEG ,probabilityNonEEG,probabilitySeizureEEG;
    Button saveEEG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eegresult);
        classificationEEG =findViewById(R.id.classificationEEG);
        probabilityNonEEG =findViewById(R.id.probabilityNonEEG);
        probabilitySeizureEEG =findViewById(R.id.probabilitySeizureEEG);

        saveEEG =findViewById(R.id.saveEMG);
        saveEEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String my_classificationEEG = classificationEEG.getText().toString().trim();
                final String my_probabilityNonEEG = probabilityNonEEG.getText().toString().trim();
                final String my_probabilitySeizureEEG= probabilitySeizureEEG.getText().toString().trim();
                final String my_type = "EEG Signal";
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URLs.URL_symptomStore, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONObject userJson = obj.getJSONObject("data");

                            Signals signals = new Signals(userJson.getString("token"));
                            SignalsData.getInstance(getApplicationContext()).saveEEG(signals);

                       startActivity(new Intent(EEGResult.this,UploadEeg.class));
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
                        params.put("classification",my_classificationEEG );
                        params.put("probabilityNon", my_probabilityNonEEG);
                        params.put("probability_seizure", my_probabilitySeizureEEG);
                        params.put("type", my_type);

                        return params;

                    }
                };

                VolleySingleton.getInstance(EEGResult.this).addToRequestQueue(stringRequest);

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
                Intent intent1 =new Intent(EEGResult.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(EEGResult.this , SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(EEGResult.this , SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(EEGResult.this ,Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 =new Intent(EEGResult.this , Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(EEGResult.this ,Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 =new Intent(EEGResult.this , Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 =new Intent(EEGResult.this , Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(EEGResult.this , Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }
