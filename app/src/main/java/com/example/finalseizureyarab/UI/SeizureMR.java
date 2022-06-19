package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.finalseizureyarab.Controller.SeizureSymptoms;
import com.example.finalseizureyarab.Controller.SessionManager;
import com.example.finalseizureyarab.Controller.VolleySingleton;
import com.example.finalseizureyarab.Models.User;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SeizureMR extends AppCompatActivity {

    EditText et_1, et_2, et_3, et_4, et_5, et_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seizure_mr);
        et_1 = findViewById(R.id.et_7);
        et_2 = findViewById(R.id.et_8);
        et_3 = findViewById(R.id.et_9);
        et_4 = findViewById(R.id.et_10);
        et_5 = findViewById(R.id.et_11);
        et_6 = findViewById(R.id.et_12);

        if (!SessionManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URLs.URL_symptomData, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getBoolean("success")) {
                            Toast.makeText(getApplicationContext(),
                                    obj.getString("message"), Toast.LENGTH_SHORT).show();

                            JSONObject userJson = obj.getJSONObject("data");

                            User user = new User(userJson.getString("token"));
                            SeizureSymptoms.getInstance(getApplicationContext()).getSeizureSymptoms(user);

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
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

                    return params;

                }
            };


            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
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
                Intent intent1 = new Intent(SeizureMR.this, MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 = new Intent(SeizureMR.this, SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 = new Intent(SeizureMR.this, SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 = new Intent(SeizureMR.this, Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 = new Intent(SeizureMR.this, Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 = new Intent(SeizureMR.this, Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 = new Intent(SeizureMR.this, Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 = new Intent(SeizureMR.this, Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 = new Intent(SeizureMR.this, Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}