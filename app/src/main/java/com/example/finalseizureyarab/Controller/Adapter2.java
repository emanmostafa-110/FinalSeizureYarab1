package com.example.finalseizureyarab.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finalseizureyarab.Models.Request;
import com.example.finalseizureyarab.R;
import com.example.finalseizureyarab.Surver.URLs;
import com.example.finalseizureyarab.UI.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder2>{

    private Context context;
    private List<Request> requestList2;
    public Adapter2(Context context, List<Request> requestList) {
        this.context = context;
        this.requestList2 = requestList;
    }
    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_request_of_connection,parent,false);

        return new MyViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder,@SuppressLint("RecyclerView") final int position) {

        Request request =requestList2.get(position);
        holder.doctor_name1.setText(request.getDoctor_name());
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData2(position,request.getId());
            }
        });


        /* holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditData.class);
                intent.putExtra("id",book.getId());
                intent.putExtra("name",book.getName());
                intent.putExtra("author",book.getAuthor());
                context.startActivity(intent);
            }
        });*/
    }




    @Override
    public int getItemCount() {
        return requestList2.size();
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        public TextView  doctor_name1;
        public Button accept ,reject;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            doctor_name1 =itemView.findViewById(R.id.doctor_name1);
            accept =itemView.findViewById(R.id.btn_accept);
            reject =itemView.findViewById(R.id.btn_reject);
        }
    }
    public void deleteData2(int position , int id){

        final String token = SessionManager.getInstance(context).getToken().getToken();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( com.android.volley.Request.Method.GET,
                URLs.URL_connectionDelete+id, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getBoolean("success")){
                        Toast.makeText(context,response.getString("message")
                                ,Toast.LENGTH_SHORT).show();


                    }else{
                        Toast.makeText(context,"error"
                                ,Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {
            public Map<String,String> getHeaders(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("Accept","application/json");
                params.put("Authorization","Bearer  "+ token);
                return params;
            }
        }

                ;

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

        requestList2.remove(position);
        Connection.notifyAdapter2();
    }


}
