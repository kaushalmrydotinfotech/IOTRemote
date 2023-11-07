package com.rydot.iotRemote.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.iotremote.databinding.FragmentFrame3Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rydot.iotRemote.adapter.Frame3Adapter
import com.rydot.iotRemote.model.SwitchModel


class Frame3 : Fragment() {
private lateinit var binding: FragmentFrame3Binding
private lateinit var adapter: Frame3Adapter
 private val args: Frame3Args by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFrame3Binding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(javaClass.simpleName, "onViewCreated: "+args.list )

        val data: ArrayList<SwitchModel> =
            Gson().fromJson(args.list, object : TypeToken<List<SwitchModel>>() {}.type)

        adapter = Frame3Adapter(data)
        binding.recycler.adapter = adapter

    }

}