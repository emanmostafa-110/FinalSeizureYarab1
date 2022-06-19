package com.example.finalseizureyarab.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalseizureyarab.Controller.Adapter;
import com.example.finalseizureyarab.Controller.VolleySingleton;
import com.example.finalseizureyarab.Models.Request;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryConnection extends AppCompatActivity {
RecyclerView recyclerView;
    public static Adapter adapter;
    private List<Request> requestList;
    private RequestQueue queue;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_connection);

        queue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.rv_list_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestList = new ArrayList<>();
        requestList = getData();
        adapter=new Adapter(this,requestList);
        recyclerView.setAdapter(adapter);
    }
    public static void notifyAdapter(){
            adapter.notifyDataSetChanged();
        }

    public List<Request> getData(){

        requestList.clear();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( com.android.volley.Request.Method.GET,
                URLs.URL_connectionData, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray requestArray = response.getJSONArray("data");


                    for (int i = 0; i < requestArray.length(); i++) {
                        JSONObject requestObj = requestArray.getJSONObject(i);
                        Request request = new Request();
                        request.setId(requestObj.getInt("id"));
                        request.setDoctor_id(requestObj.getInt("doctor_id"));
                        request.setUser_id(requestObj.getInt("user_id"));
                        request.setDoctor_name(requestObj.getString("doctor_name"));
                        request.setCreated_at(requestObj.getString("created_at"));
                        request.setUpdate_at(requestObj.getString("update_at"));

                        requestList.add(request);
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }


                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HistoryConnection.this, "Error", Toast.LENGTH_SHORT).show();

            }
        } )
        {
            public Map<String,String> getHeaders(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("Accept","application/json");
                params.put("Authorization","Bearer  "+ token);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        return requestList;
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
                Intent intent1 =new Intent(HistoryConnection.this ,MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(HistoryConnection.this ,SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(HistoryConnection.this ,SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(HistoryConnection.this ,Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 =new Intent(HistoryConnection.this ,Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(HistoryConnection.this ,Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 =new Intent(HistoryConnection.this , Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 =new Intent(HistoryConnection.this , Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(HistoryConnection.this ,Login.class);
                startActivity(intent9);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}