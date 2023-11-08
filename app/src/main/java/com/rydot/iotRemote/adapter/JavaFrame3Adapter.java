package com.rydot.iotRemote.adapter;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iotremote.R;
import com.rydot.iotRemote.model.SwitchModel;

import java.util.ArrayList;

public class JavaFrame3Adapter extends RecyclerView.Adapter<JavaFrame3Adapter.ViewHolder> {
    private ArrayList<SwitchModel> list;

    public JavaFrame3Adapter(ArrayList<SwitchModel> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText edtNumber;
        Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            edtNumber = itemView.findViewById(R.id.edt);
            btn = itemView.findViewById(R.id.edtBtn);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_rw_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SwitchModel obj = list.get(position);
        if (obj.isOn()) {
            holder.edtNumber.setEnabled(true);
        } else {
            holder.btn.setAlpha(0.50f);
            holder.edtNumber.setAlpha(0.50f);
        }
        holder.edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                obj.setNumber(s.toString());
                Log.e(getClass().getSimpleName(), "onTextChanged: " + s);
            }
        });
    }
}


