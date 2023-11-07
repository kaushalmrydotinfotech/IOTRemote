package com.rydot.iotRemote.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.iotremote.R
import com.example.iotremote.databinding.FragmentFrame2Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rydot.iotRemote.adapter.Frame2Adapter
import com.rydot.iotRemote.model.SwitchModel
import com.rydot.iotRemote.utils.SharedPrefs


class Frame2 : Fragment() {
    private lateinit var binding: FragmentFrame2Binding
    private lateinit var adapter: Frame2Adapter
    private var updateList:String = ""
    private var editedList:String = ""



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
        var data: ArrayList<SwitchModel> = arrayListOf()
        list.add(SwitchModel(true,R.drawable.bulb_on))
        list.add(SwitchModel(false,R.drawable.bulb_off))
        list.add(SwitchModel(true,R.drawable.bulb_on))
        list.add(SwitchModel(false,R.drawable.bulb_off))

        if (updateList.isEmpty()){
            adapter = Frame2Adapter(requireContext(),list)
            binding.recycler.adapter = adapter
        }
        else{
             editedList = SharedPrefs.getValue(requireContext(),"updateList","").toString()
            Log.e(javaClass.simpleName, "editedList: "+Gson().toJson(editedList ))
            data =
                Gson().fromJson(editedList, object : TypeToken<List<SwitchModel>>() {}.type)
            adapter = Frame2Adapter(requireContext(),data)
            binding.recycler.adapter = adapter
        }

        binding.btnNext.setOnClickListener {
            if (updateList.isEmpty()){
                updateList = SharedPrefs.setValue(requireContext(),"updateList",Gson().toJson(list)).toString()
                findNavController().navigate(Frame2Directions.actionFrame2ToFrame3(Gson().toJson(list)))
            }
            else{
                updateList = SharedPrefs.setValue(requireContext(),"updateList",Gson().toJson(data)).toString()
                findNavController().navigate(Frame2Directions.actionFrame2ToFrame3(Gson().toJson(data)))
            }

        }
    }


}