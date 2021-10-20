package com.elvisoperator.yugiohdex.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.model.Data


class CardAdapter(var card: MutableList<Data> = mutableListOf(), private val listener: (Data)->Unit) :
    RecyclerView.Adapter<CardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardHolder(layoutInflater.inflate(R.layout.item_cards, parent , false))

    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
     val item = card[position]
        holder.bind(item)

        holder.binding.buttonFavorite.setOnClickListener {
            listener(item)
        }

       /* holder.recyclerviewMovieBinding.layoutLike.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.layoutLike, card[position])
        }*/



    }

    override fun getItemCount(): Int {
       return card.size
    }



}