package com.coppel.exampedro.listOfHeroes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.coppel.exampedro.databinding.ItemImageTittleBinding
import imagenDescription

class imagenDescriptionViewHolder(
    var binding: ItemImageTittleBinding
):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item:imagenDescription){
        binding.apply {
           tittle.text = item.title
            Glide.with(binding.root.context).load("${item.imgpath}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photo)

        }
    }
}