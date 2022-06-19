package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.example.finalseizureyarab.Models.User;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Registration extends AppCompatActivity {
    TextView haveAccount;
    Button registration;
    EditText et_email ;
    EditText et_password ;
    EditText date;
    EditText et_firstName, et_lastName, et_phone,
            et_city, et_country, et_nationalID, et_gender;
    ProgressBar progressBar;
    static String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        haveAccount = findViewById(R.id.tv_haveAccount);
        registration = findViewById(R.id.btn_registration);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_pass);
        date = findViewById(R.id.et_birthdate);
        progressBar=findViewById(R.id.progressBarRegister);
        et_firstName = findViewById(R.id.et_fname);
        et_lastName = findViewById(R.id.et_lname);
        et_phone = findViewById(R.id.et_phone);
        et_city = findViewById(R.id.et_city);
        et_country = findViewById(R.id.et_country);
        et_nationalID = findViewById(R.id.et_nationalID);
        et_gender = findViewById(R.id.et_gender);

        BuildDate();


        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        final String myFirstName = et_firstName.getText().toString().trim();
        final String myLastName = et_lastName.getText().toString().trim();
        final String myEmail = et_email.getText().toString().trim();
        final String myPassword = et_password.getText().toString().trim();
        final String myCity = et_city.getText().toString().trim();
        final String myCountry = et_country.getText().toString().trim();
        final String myPhone = et_phone.getText().toString().trim();
        final String myNational_id = et_nationalID.getText().toString().trim();
        final String myGender=  et_gender.getText().toString().trim();
        final String myBirthday=date.getText().toString().trim();



        if(TextUtils.isEmpty(myFirstName)){
            et_firstName.setError("Enter you first name  please");
            et_firstName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myLastName)){
            et_lastName.setError("Enter you last name please");
            et_lastName.requestFocus();
            return;
        }
        if (!et_email.getText().toString().trim().matches(emailPattern2.toLowerCase(Locale.ROOT))) {
            Toast.makeText(Registration.this,
                    "Enter valid email", Toast.LENGTH_SHORT).show();
        return;
        }
        if(TextUtils.isEmpty(myEmail)){
            et_email.setError("Enter your email please");
            et_email.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myPassword)){
            et_password.setError("Enter your password please");
            et_password.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(myPhone)){
            et_phone.setError("Enter you phone please");
            et_phone.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myCountry)){
            et_country.setError("Enter you country please");
            et_country.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myCity)){
            et_city.setError("Enter you city please");
            et_city.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myNational_id)){
            et_nationalID.setError("Enter you national id please");
            et_nationalID.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myGender)){
            et_gender.setError("Enter you gender please");
            et_gender.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(myBirthday)){
            date.setError("Enter you birthdate please");
            date.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.VISIBLE);


                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        progressBar.setVisibility(View.INVISIBLE);
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

                        Toast.makeText(getApplicationContext()
                                ,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        )

        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("firstName" ,myFirstName);
                params.put("lastName",myLastName);
                params.put("email" ,myEmail);
                params.put("password" ,myPassword);
                params.put("city" ,myCity);
                params.put("country" ,myCountry);
                params.put("gender" ,myGender);
                params.put("national_id" ,myNational_id);
                params.put("phone" ,myPhone);
                params.put("birth_day" ,myBirthday);

                return  params;

            }
        }   ;


        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
}



    private void BuildDate(){

        date.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}