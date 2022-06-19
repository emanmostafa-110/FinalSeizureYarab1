package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyProfile extends AppCompatActivity {

    EditText firstName, lastName, id, email, phone, country, city, birthDate, gender, nationalId;
    ImageView editImage, editFirstName, editLastname, editEmail, editPhone, editCountry, editCity, editNationalId;
    Button edit, back, update, cancel;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        editImage = findViewById(R.id.edit_image);
        editFirstName = findViewById(R.id.edit_firstName);
        editLastname = findViewById(R.id.edit_lastName);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editCountry = findViewById(R.id.edit_country);
        editCity = findViewById(R.id.edit_city);
        editNationalId = findViewById(R.id.edit_nationalId);
        edit = findViewById(R.id.Btn_edit);
        back = findViewById(R.id.Btn_back);
        update = findViewById(R.id.Btn_update);
        cancel = findViewById(R.id.Btn_cancel);
        firstName = findViewById(R.id.firstName_edit);
        lastName = findViewById(R.id.lastName_edit);
        id = findViewById(R.id.id_edit);
        email = findViewById(R.id.email_edit);
        phone = findViewById(R.id.phone_edit);
        country = findViewById(R.id.country_edit);
        city = findViewById(R.id.city_edit);
        birthDate = findViewById(R.id.birthDate_edit);
        gender = findViewById(R.id.gender_edit);
        nationalId = findViewById(R.id.nationalId_edit);
        BuildDate();

        if (!SessionManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URLs.URL_profileShow, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.getBoolean("success")) {
                            Toast.makeText(getApplicationContext(),
                                    obj.getString("message"), Toast.LENGTH_SHORT).show();

                            JSONObject userJson = obj.getJSONObject("data");

                            User user = new User(userJson.getString("token"));
                            SessionManager.getInstance(getApplicationContext()).getUser(user);

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


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelEditing();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEditing();
                final String myFirstName = firstName.getText().toString().trim();
                final String myLastName = lastName.getText().toString().trim();
                final String myEmail = email.getText().toString().trim();
                final String myCity = city.getText().toString().trim();
                final String myCountry = country.getText().toString().trim();
                final String myPhone = phone.getText().toString().trim();
                final String myNational_id = nationalId.getText().toString().trim();
                final String myGender=  gender.getText().toString().trim();
                final String myBirthday=birthDate.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URLs.URL_profileUpdate, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                        params.put("firstName", myFirstName);
                        params.put("lastName", myLastName);
                        params.put("email", myEmail);
                        params.put("city", myCity);
                        params.put("country", myCountry);
                        params.put("gender", myGender);
                        params.put("national_id", myNational_id);
                        params.put("phone", myPhone);
                        params.put("birth_day", myBirthday);

                        return params;

                    }
                };

                VolleySingleton.getInstance(MyProfile.this).addToRequestQueue(stringRequest);
            }


    });

        editFirstName.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        firstName.setEnabled(true);
    }

    });

        editLastname.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        lastName.setEnabled(true);
    }
    });
        editEmail.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        email.setEnabled(true);
    }
    });
        editPhone.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        phone.setEnabled(true);
    }
    });
        editCountry.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        country.setEnabled(true);
    }
    });
        editCity.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        city.setEnabled(true);
    }
    });
        editNationalId.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){nationalId.setEnabled(true);
    }
    });

}


    private void updateEditing() {
        enableFun();
    }

    private void editProfile() {

        editImage.setVisibility(LinearLayout.VISIBLE);
        editFirstName.setVisibility(LinearLayout.VISIBLE);
        editLastname.setVisibility(LinearLayout.VISIBLE);
        editEmail.setVisibility(LinearLayout.VISIBLE);
        editPhone.setVisibility(LinearLayout.VISIBLE);
        editCountry.setVisibility(LinearLayout.VISIBLE);
        editCity.setVisibility(LinearLayout.VISIBLE);
        editNationalId.setVisibility(LinearLayout.VISIBLE);

        edit.setVisibility(LinearLayout.INVISIBLE);
        back.setVisibility(LinearLayout.INVISIBLE);
        update.setVisibility(LinearLayout.VISIBLE);
        cancel.setVisibility(LinearLayout.VISIBLE);
    }

    private void cancelEditing() {
        enableFun();
    }

    private void enableFun() {
        editImage.setVisibility(LinearLayout.INVISIBLE);
        editFirstName.setVisibility(LinearLayout.INVISIBLE);
        editLastname.setVisibility(LinearLayout.INVISIBLE);
        editEmail.setVisibility(LinearLayout.INVISIBLE);
        editPhone.setVisibility(LinearLayout.INVISIBLE);
        editCountry.setVisibility(LinearLayout.INVISIBLE);
        editCity.setVisibility(LinearLayout.INVISIBLE);
        editNationalId.setVisibility(LinearLayout.INVISIBLE);

        edit.setVisibility(LinearLayout.VISIBLE);
        back.setVisibility(LinearLayout.VISIBLE);
        update.setVisibility(LinearLayout.INVISIBLE);
        cancel.setVisibility(LinearLayout.INVISIBLE);

        firstName.setEnabled(false);
        lastName.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        country.setEnabled(false);
        city.setEnabled(false);
        nationalId.setEnabled(false);
    }

    private void BuildDate() {

        birthDate.addTextChangedListener(new TextWatcher() {
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

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);

                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    birthDate.setText(current);
                    birthDate.setSelection(sel < current.length() ? sel : current.length());


                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
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
                Intent intent1 = new Intent(MyProfile.this, MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 = new Intent(MyProfile.this, SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 = new Intent(MyProfile.this, SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 = new Intent(MyProfile.this, Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 = new Intent(MyProfile.this, Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 = new Intent(MyProfile.this, Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 = new Intent(MyProfile.this, Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 = new Intent(MyProfile.this, Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 = new Intent(MyProfile.this, Login.class);
                startActivity(intent9);
                return true;
            case R.id.seizure:
                Intent intent10 = new Intent(MyProfile.this, SeizureInfo.class);
                startActivity(intent10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
