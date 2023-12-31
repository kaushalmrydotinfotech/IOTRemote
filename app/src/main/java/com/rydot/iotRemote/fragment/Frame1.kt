package com.rydot.iotRemote.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.iotremote.databinding.FragmentFrame1Binding
import com.rydot.iotRemote.utils.SharedPrefs


class Frame1 : Fragment() {
    private lateinit var binding: FragmentFrame1Binding
    private var BTisSelected: Boolean? = null
    private var WFisSelected: Boolean? = null
    private var WIisSelected: Boolean? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFrame1Binding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BTisSelected =
            SharedPrefs.getValueBoolean(requireContext(), "BTisSelected", false)
        WFisSelected =
            SharedPrefs.getValueBoolean(requireContext(), "WFisSelected", false)
        WIisSelected =
            SharedPrefs.getValueBoolean(requireContext(), "WIisSelected", false)
        isSelected(BTisSelected!!, WFisSelected!!, WIisSelected!!)


        binding.btnNext.setOnClickListener {
            Log.e(
                javaClass.simpleName,
                "btnBluetooth isSelected: " + binding.btnBluetooth.isSelected
            )
            Log.e(javaClass.simpleName, "btnWifi isSelected: " + binding.btnWifi.isSelected)
            Log.e(javaClass.simpleName, "btnWired isSelected: " + binding.btnWired.isSelected)
            if (!binding.btnBluetooth.isSelected && !binding.btnWifi.isSelected && !binding.btnWired.isSelected) {
                Toast.makeText(requireContext(), "Please Select any One Type", Toast.LENGTH_SHORT)
                    .show()
            } else {
                BTisSelected = SharedPrefs.setValueBoolean(
                    requireContext(),
                    "BTisSelected",
                    binding.btnBluetooth.isSelected
                )
                WFisSelected = SharedPrefs.setValueBoolean(
                    requireContext(),
                    "WFisSelected",
                    binding.btnWifi.isSelected
                )
                WIisSelected = SharedPrefs.setValueBoolean(
                    requireContext(),
                    "WIisSelected",
                    binding.btnWired.isSelected
                )
                findNavController().navigate(Frame1Directions.actionFrame1ToFrame2())
            }
        }
    }


    private fun isSelected(BisSelected :Boolean, WFBisSelected :Boolean,WIBisSelected:Boolean  ) {
        if (BTisSelected != null || WFisSelected != null || WIisSelected != null) {
            binding.btnBluetooth.isSelected = BisSelected
            binding.btnWifi.isSelected = WFBisSelected
            binding.btnWired.isSelected = WIBisSelected
            if (BisSelected){
                binding.btnBluetooth.alpha = 0.5F
            }
            else if (WFBisSelected){
                binding.btnWifi.alpha = 0.5F
            }
            else if (WIBisSelected){
                binding.btnWired.alpha = 0.5F
            }
        }
        binding.btnBluetooth.setOnClickListener {
                binding.btnBluetooth.isSelected = true
                binding.btnBluetooth.alpha = 0.5f
                binding.btnWifi.isSelected = false
                binding.btnWired.isSelected = false
                binding.btnWifi.alpha = 1.0f
                binding.btnWired.alpha = 1.0f
        }
        binding.btnWifi.setOnClickListener {
                binding.btnWifi.isSelected = true
                binding.btnWifi.alpha = 0.5f
                binding.btnBluetooth.isSelected = false
                binding.btnWired.isSelected = false
                binding.btnBluetooth.alpha = 1.0f
                binding.btnWired.alpha = 1.0f


        }
        binding.btnWired.setOnClickListener {
            binding.btnWired.isSelected = true
            binding.btnWired.alpha = 0.5f
            binding.btnBluetooth.isSelected = false
            binding.btnWifi.isSelected = false
            binding.btnBluetooth.alpha = 1.0f
            binding.btnWifi.alpha = 1.0f
        }


    }

    override fun onStop() {
        super.onStop()

    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e(javaClass.simpleName, "onDestroy: ", )
    }

}