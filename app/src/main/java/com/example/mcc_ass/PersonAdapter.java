package com.example.mcc_ass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter {

    ArrayList<Person> persons;
    int layoutId;
    Context context;

    public PersonAdapter(Context context, ArrayList<Person> persons, int LayoutId) {
        this.persons = persons;
        this.layoutId = LayoutId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layoutId,null);
        Person p = persons.get(position);

        TextView nameTv = view.findViewById(R.id.nameId);
        TextView numTv = view.findViewById(R.id.numId);
        TextView addressTv = view.findViewById(R.id.addressId);

        nameTv.setText(p.getName());
        numTv.setText(p.getNumber());
        addressTv.setText(p.getAddress());


        return view;
    }
}
