package com.elvisoperator.yugiohdex.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.elvisoperator.yugiohdex.R
import com.elvisoperator.yugiohdex.data.database.DatabaseImpl
import com.elvisoperator.yugiohdex.data.model.BasicCard
import com.elvisoperator.yugiohdex.data.model.CardProvider
import com.elvisoperator.yugiohdex.data.model.Data
import com.elvisoperator.yugiohdex.viewmodel.RecyclerCardViewModel


class CardAdapter(var card: List<BasicCard> = mutableListOf(), private val listener: (BasicCard)->Unit) :
    RecyclerView.Adapter<CardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardHolder(layoutInflater.inflate(R.layout.item_cards, parent , false))

    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
     val item = card[position]
        holder.bind(item)

        holder.binding.buttonFavoriteFilled.setOnClickListener {
            card[position].fav = false
            CardProvider.cards[position].fav = false
            holder.bindFavorite(card[position])
        }

        holder.binding.buttonFavorite.setOnClickListener {
            card[position].fav = true
            CardProvider.cards[position].fav = true
            holder.bindFavorite(card[position])
        }


       /* holder.recyclerviewMovieBinding.layoutLike.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.layoutLike, card[position])
        }*/
    }

    override fun getItemCount(): Int {
       return card.size
    }



}