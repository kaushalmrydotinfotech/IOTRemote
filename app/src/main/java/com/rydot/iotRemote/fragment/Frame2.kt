package com.rydot.iotRemote.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.iotremote.R
import com.example.iotremote.databinding.FragmentFrame2Binding
import com.google.gson.Gson
import com.rydot.iotRemote.adapter.Frame2Adapter
import com.rydot.iotRemote.model.SwitchModel


class Frame2 : Fragment() {
    private lateinit var binding: FragmentFrame2Binding
    lateinit var adapter: Frame2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFrame2Binding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = Frame2Adapter(requireContext(), arrayListOf())
        binding.recycler.adapter = adapter
        val list:ArrayList<SwitchModel> = arrayListOf()
        list.add(SwitchModel(true,R.drawable.bulb_on))
        list.add(SwitchModel(false,R.drawable.bulb_off))
        list.add(SwitchModel(true,R.drawable.bulb_on))
        list.add(SwitchModel(false,R.drawable.bulb_off))
        adapter = Frame2Adapter(requireContext(),list)
        binding.recycler.adapter = adapter

        binding.btnNext.setOnClickListener {
            findNavController().navigate(Frame2Directions.actionFrame2ToFrame3(Gson().toJson(list)))
        }
    }


}