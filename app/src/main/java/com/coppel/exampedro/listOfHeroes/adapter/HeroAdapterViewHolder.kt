package com.coppel.exampedro.listOfHeroes.adapter

import Hero
import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.coppel.exampedro.R
import com.coppel.exampedro.databinding.ItemSuperHeroeBinding

class HeroAdapterViewHolder(
    var binding: ItemSuperHeroeBinding,
    var actionClick: (item_list: Hero) -> Unit
):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(position:Int,item:Hero){
        binding.apply {
            if(position % 1 == 0){
                root.backgroundTintList = ColorStateList.valueOf(root.resources.getColor(R.color.first))
            }
            if(position % 2 == 0){
                root.backgroundTintList = ColorStateList.valueOf(root.resources.getColor(R.color.second))
            }
            if(position % 3 == 0){
                root.backgroundTintList = ColorStateList.valueOf(root.resources.getColor(R.color.thr))
            }
            if(position % 4 == 0){
                root.backgroundTintList = ColorStateList.valueOf(root.resources.getColor(R.color.four))
            }
            if(position % 5 == 0){
                root.backgroundTintList = ColorStateList.valueOf(root.resources.getColor(R.color.five))
            }

            root.setOnClickListener {
                actionClick.invoke(item)
            }

            Glide.with(binding.root.context).load("${item.thumbnail.path}.${item.thumbnail.extension}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photo)

            firstName.text =item.name.toString().substringBefore(" ")
            secondName.text = item.name.toString().substringAfter(" ")

            id.text = item.id.toString()
        }
    }
}