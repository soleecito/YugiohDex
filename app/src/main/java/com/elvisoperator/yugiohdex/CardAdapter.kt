package com.elvisoperator.yugiohdex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val card: MutableList<Data>) :
    RecyclerView.Adapter<CardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardHolder(layoutInflater.inflate(R.layout.item_cards, parent , false))

    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
     val item = card[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return card.size
    }


}