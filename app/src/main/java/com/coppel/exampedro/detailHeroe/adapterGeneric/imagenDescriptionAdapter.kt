package com.coppel.exampedro.listOfHeroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coppel.exampedro.databinding.ItemImageTittleBinding
import imagenDescription

class imagenDescriptionAdapter(
    var list: ArrayList<imagenDescription>
) : RecyclerView.Adapter<imagenDescriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagenDescriptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemImageTittleBinding.inflate(layoutInflater, parent, false)
        return imagenDescriptionViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: imagenDescriptionViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updatelist(l: ArrayList<imagenDescription>) {
        list = l
        notifyDataSetChanged()
    }
}