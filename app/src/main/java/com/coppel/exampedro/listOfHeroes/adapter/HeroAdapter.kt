package com.coppel.exampedro.listOfHeroes.adapter

import Hero
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coppel.exampedro.databinding.ItemSuperHeroeBinding

class HeroAdapter(
    var list:ArrayList<Hero>,
    val actionClick: (item_list: Hero) -> Unit): RecyclerView.Adapter<HeroAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemSuperHeroeBinding.inflate(layoutInflater, parent, false)
        return HeroAdapterViewHolder(itemBinding,actionClick)
    }

    override fun onBindViewHolder(holder: HeroAdapterViewHolder, position: Int) {
        val item = list[position]
        holder.bind(position,item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updatelist(l:ArrayList<Hero>){
        this.list.addAll(l)
        notifyDataSetChanged()
    }
}