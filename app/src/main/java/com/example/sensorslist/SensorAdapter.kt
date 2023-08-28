package com.example.sensorslist

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SensorAdapter(
    private val items: List<Sensor>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<SensorAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            //.inflate(R.layout.list_item_simple, viewGroup, false)
            .inflate(R.layout.list_item_card, viewGroup, false)
        return MyViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.headingView.text = items[position].toString()
        viewHolder.textView.text = items[position].name
    }

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textView: TextView = itemView.findViewById(R.id.textview_list_item)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            // gradle     implementation "androidx.recyclerview:recyclerview:1.2.1"
            onItemClicked(position)
        }
    }
}