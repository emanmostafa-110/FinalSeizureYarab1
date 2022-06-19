package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.finalseizureyarab.Controller.SessionManager;
import com.example.finalseizureyarab.Controller.VolleySingleton;
import com.example.finalseizureyarab.Models.User;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

public class ForgetPassword extends AppCompatActivity {
    Button send;
    EditText et_email,et_resetPassword;
    static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressBar progressBar3;
    Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        send = findViewById(R.id.submit);
        et_email = findViewById(R.id.et_email1);
        et_resetPassword=findViewById(R.id.et_resetPassword);
        progressBar3 = findViewById(R.id.progressBar3);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_email.getText().toString().trim().matches(emailPattern.toLowerCase(Locale.ROOT)) &&
                        et_email.getText().toString().isEmpty()) {
                    if (!et_email.getText().toString().trim().matches(emailPattern.toLowerCase(Locale.ROOT))) {
                        Toast.makeText(ForgetPassword.this,
                                "Enter valid email", Toast.LENGTH_SHORT).show();
                    }
                    if (et_email.getText().toString().isEmpty()) {
                        Toast.makeText(ForgetPassword.this, "Enter your email", Toast.LENGTH_SHORT).show();
                    }
                }
                if (et_email.getText().toString().isEmpty()) {
                    Toast.makeText(ForgetPassword.this, "Enter your new password", Toast.LENGTH_SHORT).show();
                }else {
                    passwordForget();
                }
            }
        });

    }

    public void passwordForget() {
        final String myEmail = et_email.getText().toString().trim();
        final String myPassword = et_resetPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_forgetPassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar3.setVisibility(View.VISIBLE);


                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        progressBar3.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),
                                obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("data");

                        User user = new User(userJson.getString("token"));

                        SessionManager.getInstance(getApplicationContext()).userLogin(user);

                        finish();
                        startActivity(new Intent(getApplicationContext(),
                                Login.class));
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
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        )

        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");

                params.put("email" ,myEmail);
                params.put("password" ,myPassword);

                return  params;

            }
        }   ;


        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    }

