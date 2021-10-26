package com.elvisoperator.yugiohdex.ui.fragments

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.elvisoperator.yugiohdex.data.model.BasicCard

import com.elvisoperator.yugiohdex.databinding.ItemCardsBinding
import com.elvisoperator.yugiohdex.viewmodel.RecyclerCardViewModel
import com.squareup.picasso.Picasso


class CardHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var recyclerviewMovieBinding: ItemCardsBinding


    val binding = ItemCardsBinding.bind(view)
    fun bind(card: BasicCard) {

        binding.tvName.text = card.name
        binding.tvType.text = card.type
        binding.level.text = card.level.toString()
        Picasso.get().load(card.image.image_url).into(binding.ivCards)
        Log.println(Log.INFO, "Valor Imagen", card.image.image_url_small)
        bindFavorite(card)
    }

    fun bindFavorite(card: BasicCard){
        binding.buttonFavoriteFilled.isVisible = card.fav
    }
}