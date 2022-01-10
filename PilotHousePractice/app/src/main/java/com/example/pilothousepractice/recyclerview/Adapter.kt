package com.example.pilothousepractice.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pilothousepractice.R
import com.example.pilothousepractice.database.EntityDataClassDomain
import com.example.pilothousepractice.databinding.AdapterViewHolderBinding

class Adapter : ListAdapter<EntityDataClassDomain,viewHolder>(AdapterDiffCallback()) {
//val List = mutableListOf("1","2","3","4","5","6")
//var data = listOf<EntityDataClassDomain>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = AdapterViewHolderBinding.inflate(layoutInflater,parent,false)
        /*layoutInflater
            .inflate(R.layout.adapter_view_holder, parent, false) as viewHolder*/
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val mData = getItem(position)
        Log.i("test","mData: $mData")
        holder.binding.data = mData
        holder.binding.executePendingBindings()
    }

   /* override fun getItemCount(): Int {
        Log.i("test","size: ${data.size}")
    return data.size
    }*/
}


class viewHolder(val binding : AdapterViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
}
class AdapterDiffCallback : DiffUtil.ItemCallback<EntityDataClassDomain>() {
    override fun areItemsTheSame(oldItem: EntityDataClassDomain, newItem: EntityDataClassDomain): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: EntityDataClassDomain, newItem: EntityDataClassDomain): Boolean {
        return oldItem == newItem
    }
}