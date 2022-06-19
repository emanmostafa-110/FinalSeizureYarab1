package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.finalseizureyarab.Controller.SessionManager;
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

public class Login extends AppCompatActivity {
    Button btn_login ;
    TextView tv_forgetPassword;
    TextView tv_registration;
    private ProgressBar progressBar;
    EditText et_email,et_password;
    static String emailPattern3 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        btn_login =findViewById(R.id.btn_login);
        tv_forgetPassword=findViewById(R.id.tv_forgetPassword);
        tv_registration=findViewById(R.id.tv_registration);
        progressBar = findViewById(R.id.progressBar2);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        tv_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        tv_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Login.this ,Registration.class);
                startActivity(intent);
            }
        });
    }
    private void userLogin(){
        final String myEmail = et_email.getText().toString().trim();
        final String myPassword = et_password.getText().toString().trim();

        if (!et_email.getText().toString().trim().matches(emailPattern3.toLowerCase(Locale.ROOT))) {
            Toast.makeText(Login.this,
                    "Enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(myEmail)){
            et_email.setError("Enter you email please");
            et_email.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(myPassword)){
            et_password.setError("Enter you password please");
            et_password.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.VISIBLE);


                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getJSONObject("success") != null) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),
                                "Welcome", Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("success");

                        User user = new User(userJson.getString("token"));

                        SessionManager.getInstance(getApplicationContext()).userLogin(user);

                        finish();
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Login failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        )

        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("email", myEmail);
                params.put("password", myPassword);
                return  params;

            }
        }   ;


        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}