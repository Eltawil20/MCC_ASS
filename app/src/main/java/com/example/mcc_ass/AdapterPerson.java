package com.example.mcc_ass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPerson extends RecyclerView.Adapter<MyViewHolder> {


    ArrayList<Person> persons;
    int itemLayoutId;

    public AdapterPerson(ArrayList<Person> persons, int itemLayoutId) {
        this.persons = persons;
        this.itemLayoutId = itemLayoutId;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Person p = persons.get(position);

        holder.personName.setText(p.getName());
        holder.PersonNumber.setText(p.getNumber());
        holder.personAddress.setText(p.getAddress());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
