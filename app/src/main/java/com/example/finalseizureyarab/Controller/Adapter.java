package com.example.finalseizureyarab.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalseizureyarab.Models.Request;
import com.example.finalseizureyarab.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    List<Request> requestList;

    public Adapter(Context context, List<Request> requestList) {
        this.context = context;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_history_of_connection,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Request request =requestList.get(position);
        holder.doctor_name.setText(request.getDoctor_name());
        holder.doctor_name1.setText(request.getDoctor_name());
        holder.time.setText(formatDate(request.getCreated_at()));


    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView doctor_name ,doctor_name1,time;
        public Button accept ,reject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor_name =itemView.findViewById(R.id.doctor_name);
            doctor_name1 =itemView.findViewById(R.id.doctor_name1);
            time =itemView.findViewById(R.id.time);
            accept =itemView.findViewById(R.id.btn_accept);
            reject =itemView.findViewById(R.id.btn_reject);
        }
    }
    private  String formatDate(String dataStr){
        try{
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dataStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        }catch (ParseException e){
            Log.d("error", e.getMessage());
        }
        return "";
    }



}
