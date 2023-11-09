package com.rydot.iotRemote.adapter

import android.app.AlertDialog.Builder
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.iotremote.R
import com.rydot.iotRemote.model.SwitchModel

class Frame3Adapter(private var list: ArrayList<SwitchModel>,val onClick:(SwitchModel)->Unit): RecyclerView.Adapter<Frame3Adapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
      var edtNumber: EditText = itemView.findViewById(R.id.edt)
      var btn:Button = itemView.findViewById(R.id.edtBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.raw_rw_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = list[position]
        if (obj.isOn){
            holder.edtNumber.isEnabled = true
        }
        else{
            holder.btn.alpha = 0.50f
            holder.edtNumber.alpha = 0.50f
        }


        holder.btn.setOnClickListener {
            onClick.invoke(list[position])
        }

        holder.edtNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                obj.number = s.toString()
                Log.e(javaClass.simpleName, "onTextChanged: ${obj.number}")
            }
        })
    }
}