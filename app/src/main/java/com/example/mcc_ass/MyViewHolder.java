package com.example.mcc_ass;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView personName;
    TextView PersonNumber;
    TextView personAddress;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        personName = itemView.findViewById(R.id.nameId);
        PersonNumber = itemView.findViewById(R.id.numId);
        personAddress = itemView.findViewById(R.id.addressId);

    }
}
