package com.rydot.iotRemote.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iotremote.databinding.FragmentFrame3Binding;
import com.rydot.iotRemote.adapter.Frame3Adapter;


public class JavaFrame3 extends Fragment {
    private Frame3Adapter adapter;
    /*private Frame3Args args;*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.example.iotremote.databinding.FragmentFrame3Binding binding = FragmentFrame3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* Log.e(getClass().getSimpleName(), "onViewCreated: " + args.getList());*/
       /* ArrayList<SwitchModel> data = new Gson().fromJson(args.getList(), new TypeToken<ArrayList<SwitchModel>>() {}.getType());
        adapter = new Frame3Adapter(data);
        binding.getRecycler().setAdapter(adapter);*/
    }
}


