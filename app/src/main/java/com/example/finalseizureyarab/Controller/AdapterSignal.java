package com.example.finalseizureyarab.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalseizureyarab.Models.Request;
import com.example.finalseizureyarab.Models.Signals;
import com.example.finalseizureyarab.R;

import java.util.List;

public class AdapterSignal extends RecyclerView.Adapter<AdapterSignal.MyViewHolder3>{
    private Context context3;
    List<Request> signalList3;

    public AdapterSignal(Context context, List<Request> requestList) {
        this.context3 = context;
        this.signalList3 = requestList;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seizure_history_recycler,parent,false);

        return new MyViewHolder3(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, @SuppressLint("RecyclerView") final int position) {
        Signals signals = signalList3.get(position);
        holder.type.setText(signals.getType());
        holder.classification.setText(signals.getClassification());
        holder.probabilityNon.setText(signals.getProbabilityNon());
        holder.probabilitySeizure.setText(signals.getProbabilitySeizure());
    }


    @Override
    public int getItemCount() {
        return signalList3.size();
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder {
        public TextView type ,classification,probabilityNon,probabilitySeizure;

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
           //type=itemView.findViewById(R.id.)

        }
    }



}
