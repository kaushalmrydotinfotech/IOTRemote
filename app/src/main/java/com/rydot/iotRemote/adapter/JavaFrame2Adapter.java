package com.rydot.iotRemote.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotremote.R;
import com.rydot.iotRemote.customSwitch.OnToggledListener;
import com.rydot.iotRemote.customSwitch.ToggleableView;
import com.rydot.iotRemote.model.SwitchModel1;

import java.util.ArrayList;

public class JavaFrame2Adapter extends RecyclerView.Adapter<JavaFrame2Adapter.ViewHolder> {
    private Context context;
    private ArrayList<SwitchModel1> list;
    private OnDeleteListener onDelete;

    public JavaFrame2Adapter(Context context, ArrayList<SwitchModel1> list, OnDeleteListener onDelete) {
        this.context = context;
        this.list = list;
        this.onDelete = onDelete;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ToggleableView toggle;
        ImageView delete;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgOnOff);
            toggle = itemView.findViewById(R.id.toggle);
            delete = itemView.findViewById(R.id.imgDelete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SwitchModel1 obj = list.get(position);
        holder.toggle.setOn(obj.isOn());
        if (obj.isOn()) {
            holder.img.setImageResource(R.drawable.bulb_on);
        } else {
            holder.img.setImageResource(R.drawable.bulb_off);
        }
        holder.toggle.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if (isOn) {
                    obj.setOn(true);
                    holder.img.setImageResource(R.drawable.bulb_on);
                } else {
                    obj.setOn(false);
                    holder.img.setImageResource(R.drawable.bulb_off);
                }
            }
        });
        holder.delete.setOnClickListener(v -> onDelete.invoke(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnDeleteListener {
        void invoke(SwitchModel1 switchModel);
    }
}



