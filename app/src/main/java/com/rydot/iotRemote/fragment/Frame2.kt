package com.rydot.iotRemote.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* adapter = Frame2Adapter(requireContext(), arrayListOf(), onDelete = {

        })
        binding.recycler.adapter = adapter*/
        var list:ArrayList<SwitchModel> = arrayListOf()
        binding.add.setOnClickListener {
            addGpioDialog(list)
        }


             Log.e(javaClass.simpleName, "editedList: "+Gson().toJson(SharedPrefs.getValue(requireContext(),"updateList","").toString() ))
             if (SharedPrefs.getValue(requireContext(),"updateList","").toString().isNotEmpty()){
                 list =
                     Gson().fromJson(SharedPrefs.getValue(requireContext(),"updateList","").toString(), object : TypeToken<List<SwitchModel>>() {}.type)
                 adapter =  Frame2Adapter(requireContext(), list, onDelete = {
                     alertDialog(list,it)
                 })
                 binding.recycler.adapter = adapter
             }





        binding.btnNext.setOnClickListener {
            SharedPrefs.setValue(requireContext(),"updateList",Gson().toJson(list)).toString()
            Log.e(javaClass.simpleName, "list: "+ SharedPrefs.getValue(requireContext(),"updateList","").toString())
            findNavController().navigate(Frame2Directions.actionFrame2ToFrame3(SharedPrefs.getValue(requireContext(),"updateList","").toString()))
        }
    }


        @SuppressLint("NotifyDataSetChanged")
        private fun addGpioDialog(list:ArrayList<SwitchModel> = arrayListOf()){
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.gpio_add_layout)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)

            val edtId: EditText = dialog.findViewById(R.id.edtId)
            val edtMessage: EditText = dialog.findViewById(R.id.edtMessage)
            val btnSubmit:Button = dialog.findViewById(R.id.btnSubmit)
            val imgCancel:ImageView = dialog.findViewById(R.id.imgCancel)

            btnSubmit.setOnClickListener {
                val inputMethodManager =
                    requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                if (TextUtils.isEmpty(edtId.text)){
                    Toast.makeText(requireContext(),"Id is Require",Toast.LENGTH_SHORT).show()
                }
                else if(TextUtils.isEmpty(edtMessage.text)){
                    Toast.makeText(requireContext(),"Message is Require",Toast.LENGTH_SHORT).show()
                }
                else{
                    list.addAll(arrayListOf(SwitchModel(isOn = false, img = R.drawable.bulb_off)))
                    SharedPrefs.setValue(requireContext(),"updateList",Gson().toJson(list)).toString()
                    adapter = Frame2Adapter(requireContext(),list, onDelete = { it1->
                       alertDialog(list,it1)
                    })
                    binding.recycler.adapter = adapter
                    dialog.dismiss()
                    Log.e(javaClass.simpleName, "addGpioDialog: $list")
                }
            }
            imgCancel.setOnClickListener {
             dialog.dismiss()
            }

            dialog.show()

        }

    @SuppressLint("NotifyDataSetChanged")
    private fun alertDialog(list: ArrayList<SwitchModel>, it:SwitchModel){
        val builder = AlertDialog.Builder(requireContext())
        val title =  "Do you want to delete."
        builder.setTitle(title)
        builder.setPositiveButton("Yes") { _, _ ->
            Toast.makeText(requireContext(), "Item is deleted", Toast.LENGTH_SHORT).show()
            list.remove(it)
            adapter.notifyDataSetChanged()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}