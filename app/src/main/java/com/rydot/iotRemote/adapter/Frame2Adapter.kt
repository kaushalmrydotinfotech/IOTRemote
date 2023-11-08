package com.rydot.iotRemote.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.iotremote.R
import com.rydot.iotRemote.customSwitch.OnToggledListener
import com.rydot.iotRemote.model.SwitchModel
import com.rydot.iotRemote.customSwitch.ToggleableView

class Frame2Adapter(var context: Context, private var list:ArrayList<SwitchModel>,
                    private val onDelete:(SwitchModel)->Unit):RecyclerView.Adapter<Frame2Adapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val img: ImageView = itemView.findViewById(R.id.imgOnOff)
        val switch: ToggleableView = itemView.findViewById(R.id.toggle)
        val delete:ImageView = itemView.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.raw_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = list[position]
        holder.switch.isOn = obj.isOn
        if (obj.isOn){
            holder.img.setImageResource(R.drawable.bulb_on)
        }
        else{
            holder.img.setImageResource(R.drawable.bulb_off)
        }

        holder.switch.setOnToggledListener(object : OnToggledListener {
            override fun onSwitched(toggleableView: ToggleableView?, isOn: Boolean) {
                if (isOn){
                    obj.isOn = true
                    holder.img.setImageResource(R.drawable.bulb_on)
                }
                else{
                    obj.isOn = false
                    holder.img.setImageResource(R.drawable.bulb_off)
                }
            }

        })

        holder.delete.setOnClickListener {
            onDelete.invoke(list[position])
        }
    }
}